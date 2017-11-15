#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.f17.grading/master/pa4
NUMTESTS=6
PNTSPERTEST=2
let MAXPTS=$NUMTESTS*$PNTSPERTEST
let MAXPTS=10

if [ ! -e backup ]; then
  mkdir backup
fi

cp *.c *.h Makefile backup   # copy all files of importance into backup

for NUM in $(seq 1 $NUMTESTS); do
  curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
  curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
  rm -f outfile$NUM.txt
done

curl $SRCDIR/ModelListTest.c > ModelListTest.c
curl $SRCDIR/ModelGraphTest.c > ModelGraphTest.c

echo ""
echo ""

rm -f *.o FindPath

make

if [ ! -e FindPath ] || [ ! -x FindPath ]; then # exist and executable
  echo ""
  echo "Makefile doesn't correctly create Executable!!!"
  echo ""
  rm -f *.o FindPath
  gcc -c -std=c99 -Wall -g FindPath.c Graph.c List.c
  gcc -o FindPath FindPath.o Graph.o List.o
fi

echo ""
echo ""

pathtestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/print stderr separately"
echo "FindPath tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue (Type: \"v\" + enter, for more details)"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 FindPath infile$NUM.txt outfile$NUM.txt &> garbage >> garbage #all stdout/stderr thrown away
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt > diff$NUM.txt &>> diff$NUM.txt
  if [ "$verbose" == "v" ]; then
    echo "FindPath Test $NUM:"
    echo "=========="
    cat diff$NUM.txt
    echo "=========="
  fi
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then # increment number of tests passed counter
    let pathtestspassed+=1
  fi
done

echo ""
echo ""

let pathtestspoints=2*$pathtestspassed
if [ "$pathtestspoints" -gt "$MAXPTS" ]; then # max 10 points
  let pathtestspoints=$(expr $MAXPTS)
fi
echo "Passed $pathtestspassed FindPath tests for a total of $pathtestspoints / $MAXPTS points"

echo ""
echo ""

make clean

if [ -e FindPath ] || [ -e *.o ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
fi

echo ""
echo ""
echo ""

echo "Press Enter To Continue with ListTest Results (type (\"v\" + enter) for more details)"
read verbose

gcc -c -std=c99 -Wall -g ModelListTest.c List.c
gcc -o ModelListTest ModelListTest.o List.o

if [ "$verbose" = "v" ]; then
  timeout 5 ./ModelListTest -v
else
  timeout 5 ./ModelListTest
fi

echo ""
echo ""

echo "Press Enter To Continue with GraphTest Results (type (\"v\" + enter) for more details)"
read verbose

echo ""
echo ""

gcc -c -std=c99 -Wall -g ModelGraphTest.c Graph.c List.c
gcc -o ModelGraphTest ModelGraphTest.o Graph.o List.o

if [ "$verbose" = "v" ]; then
  timeout 5 valgrind --leak-check=full -v ./ModelGraphTest -v
else
  timeout 5 valgrind ./ModelGraphTest
fi

rm -f *.o ModelListTest* ModelGraphTest* FindPath garbage

