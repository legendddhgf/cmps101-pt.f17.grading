#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.f17.grading/master/pa1
NUMTESTS=3
PNTSPERTEST=5
MAXPTS=NUMTESTS*PNTSPERTEST


if [ ! -e backup ]; then
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi

cp *.java Makefile backup   # copy all files of importance into backup

for NUM in $(seq 1 $NUMTESTS); do
  curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
  curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
done

curl $SRCDIR/ModelListTest.java > ModelListTest.java

make

if [ ! -e Lex ] || [ ! -x Lex ]; then # exist and executable
  echo ""
  echo "Makefile doesn't correctly create Executable!!!"
  echo ""
  rm -f *.class
  javac -Xlint Lex.java List.java
  echo "Main-class: Lex" > Manifest
  jar cvfm Lex Manifest *.class
  rm Manifest
  chmod +x Lex
fi

echo ""
echo ""

lextestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/stderr"
echo "Lex tests: If nothing between '=' signs, then test is passed: (press enter to continue)"
read garbage
for NUM in $(seq 1 $NUMTESTS); do
  echo "Test $NUM:"
  echo "=========="
  timeout 5 Lex infile$NUM.txt outfile$NUM.txt > garbage &>> garbage
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt > diff$NUM.txt
  cat diff$NUM.txt
  echo "=========="
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then
    let lextestspassed+=1
  fi
done

echo ""
echo ""

let lextestpoints=5*lextestspassed

echo "Passed $lextestspassed / $NUMTESTS Lex tests"
echo "This gives a total of $lextestpoints / $MAXPTS points"

echo ""
echo ""

make clean

if [ -e Lex ] || [ -e *.class ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
fi

echo ""

echo "Press Enter To Continue with ListTest Results"
read garbage

javac ModelListTest.java List.java
java ModelListTest > ListTest-out.txt &>> ListTest-out.txt
cat ListTest-out.txt

rm *.class ModelListTest.java

cd ..

