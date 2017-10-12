#!/bin/bash
# cmps101-pt.f17 grading
# usage: pa2.sh
# (run within your pa2 directory to test your code)

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.f17.grading/master/pa2
EXE="pa2-check.sh"
SCRIPT=$(mktemp)

curl $SRCDIR/$EXE > $EXE
chmod +x $EXE
./$EXE
rm -f $EXE
