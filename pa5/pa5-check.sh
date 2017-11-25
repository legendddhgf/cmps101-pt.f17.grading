#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.f17.grading/master/pa5
NUMTESTS=7
PNTSPERTEST=3
let MAXPTS=20

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

rm -f *.o FindComponents

make

if [ ! -e FindComponents ] || [ ! -x FindComponents ]; then # exist and executable
  echo ""
  echo "Makefile doesn't correctly create Executable!!!"
  echo ""
  gcc -c -std=c99 -Wall -g FindComponents.c Graph.c List.c
  gcc -o FindComponents FindComponents.o Graph.o List.o
fi

echo ""
echo ""

componenttestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/stderr"
echo "FindComponents tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue (Type: \"v\" + enter, for more details)"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 FindComponents infile$NUM.txt outfile$NUM.txt &> garbage >> garbage #all stdout / stderr printing thrown away
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt > diff$NUM.txt &>> diff$NUM.txt
  if [ "$verbose" == "v" ]; then
    echo "FindComponents Test $NUM:"
    echo "=========="
    cat diff$NUM.txt
    echo "=========="
  fi
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then # increment number of tests passed counter
    let componenttestspassed+=1
  fi
done

echo ""
echo ""

let componenttestspoints=$PNTSPERTEST*$componenttestspassed
if [ "$componenttestspoints" -gt "20" ]; then # max 10 points
  let componenttestspoints=$(expr 20)
fi
echo "Passed $componenttestspassed / $NUMTESTS FindComponents tests for a total of $componenttestspoints / $MAXPTS points"

echo ""
echo ""

make clean

if [ -e FindComponents ] || [ -e *.o ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
fi

echo ""
echo ""
echo ""

echo "Press Enter To Continue with ListTest Results (type: \"v\" + enter, for more details"
read verbose

gcc -c -std=c99 -Wall ModelListTest.c List.c
gcc -o ModelListTest ModelListTest.o List.o

if [ "$verbose" = "v" ]; then
  timeout 5 valgrind --leak-check=full -v ./ModelListTest -v
else
  timeout 5 valgrind ./ModelListTest
fi

echo ""
echo ""

echo "Press Enter To Continue with GraphTest Results (type \"v\" + enter, for more details)"
read verbose

gcc -c -std=c99 -Wall ModelGraphTest.c Graph.c List.c
gcc -o ModelGraphTest ModelGraphTest.o Graph.o List.o

if [ "$verbose" = "v" ]; then
  timeout 5 valgrind --leak-check=full -v ./ModelGraphTest -v
else
  timeout 5 valgrind ./ModelGraphTest
fi

rm -f *.o ModelListTest ModelGraphTest FindComponents garbage

