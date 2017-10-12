#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.f17.grading/master/pa2
NUMTESTS=3
PNTSPERTEST=5
let MAXPTS=$NUMTESTS*$PNTSPERTEST

if [ ! -e backup ]; then
   mkdir backup
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi


cp *.c *.h Makefile backup   # copy all files of importance into backup

for NUM in $(seq 1 $NUMTESTS); do
   curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
   curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
done

curl $SRCDIR/ModelListTest.c > ModelListTest.c

echo ""
echo ""

make

if [ ! -e Lex ] || [ ! -x Lex ]; then # exist and executable
   echo ""
   echo "Makefile doesn't correctly create Executable!!!"
   echo ""
   rm -f *.o Lex
   gcc -c -std=c99 -Wall Lex.c List.c
   gcc -o Lex Lex.o List.o
fi

lextestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout while reserving stderr for valgrind output"
echo "Lex tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue (Type (\"v\" + enter) for more details)"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 valgrind Lex infile$NUM.txt outfile$NUM.txt &> valgrind-out$NUM.txt
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt &> diff$NUM.txt >> diff$NUM.txt
  if [ "$verbose" == "v" ]; then
    echo "Lex Test $NUM:"
    echo "=========="
    cat diff$NUM.txt
    echo "=========="
  fi
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then
    let lextestspassed+=1
  fi
done

let lextestpoints=5*lextestspassed

echo "Passed $lextestspassed / $NUMTESTS Lex tests"
echo "This gives a total of $lextestpoints / $MAXPTS points"
echo ""
echo ""

echo "Press Enter To Continue with Valgrind Results for Lex"
#TODO find a way to automate detecting if leaks and errors are found and how many
read garbage

for NUM in $(seq 1 $NUMTESTS); do
   echo "Lex Valgrind Test $NUM:"
   echo "=========="
   cat valgrind-out$NUM.txt
   echo "=========="
done

echo ""
echo ""

make clean

echo ""
echo ""

if [ -e Lex ] || [ -e *.o ]; then
   echo "WARNING: Makefile didn't successfully clean all files"
fi

echo ""


echo "Press Enter To Continue with ListTest Results (type (\"v\" + enter) for more details)"
read verbose

echo ""
echo ""

gcc -c -std=c99 -Wall ModelListTest.c List.c
gcc -o ModelListTest ModelListTest.o List.o

if [ "$verbose" = "v" ]; then
   timeout 5 valgrind ./ModelListTest -v > ListTest-out.txt
else
   timeout 5 valgrind ./ModelListTest > ListTest-out.txt
fi

cat ListTest-out.txt

rm -f *.o ModelListTest* Lex

