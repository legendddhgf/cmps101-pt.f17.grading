import java.util.BitSet;

class ObjectTestClass {
  int i;
  double d;
  String str;

  ObjectTestClass(int i, double d, String str) {
    this.i = i;
    this.d = d;
    this.str = str;
  }

  public String toString() {
    return i + "," + d + "," + str;
  }
}

class ModelListTest {

  static int Empty_length;
  static int Append_length;
  static int Prepend_length;
  static int InsertAfter_length;
  static int InsertBefore_length;
  static int DeleteFront_length;
  static int DeleteBack_length;
  static int Delete_length;

  static int EmptyList_index;
  static int MoveFront_index;
  static int MoveBack_index;
  static int MoveNext_index;
  static int MovePrev_index;
  static int Append_index;
  static int Prepend_index;
  static int InsertAfter_index;
  static int InsertBefore_index;
  static int DeleteFront_index;
  static int DeleteBack_index;
  static int Delete_index;

  static int Append_equals;
  static int Prepend_equals;
  static int InsertAfter_equals;
  static int InsertBefore_equals;
  static int DeleteFront_equals;
  static int DeleteBack_equals;
  static int Delete_equals;

  static int Empty_clear;
  static int NonEmpty_clear;

  static int Set_get;
  static int Set_front;
  static int NonEmpty_front;
  static int Set_back;
  static int NonEmpty_back;

  static int Empty_toString;
  static int NonEmpty_toString;


  static String testName(int test) {
    if (test == Empty_length) return "Empty_length";
    if (test == Append_length) return "Append_length";
    if (test == Prepend_length) return "Prepend_length";
    if (test == InsertAfter_length) return "InsertAfter_length";
    if (test == InsertBefore_length) return "InsertBefore_length";
    if (test == DeleteFront_length) return "DeleteFront_length";
    if (test == DeleteBack_length) return "DeleteBack_length";
    if (test == Delete_length) return "Delete_length";

    if (test == EmptyList_index) return "EmptyList_index";
    if (test == MoveFront_index) return "MoveFront_index";
    if (test == MoveBack_index) return "MoveBack_index";
    if (test == MoveNext_index) return "MoveNext_index";
    if (test == MovePrev_index) return "MovePrev_index";
    if (test == Append_index) return "Append_index";
    if (test == Prepend_index) return "Prepend_index";
    if (test == InsertAfter_index) return "InsertAfter_index";
    if (test == InsertBefore_index) return "InsertBefore_index";
    if (test == DeleteFront_index) return "DeleteFront_index";
    if (test == DeleteBack_index) return "DeleteBack_index";
    if (test == Delete_index) return "Delete_index";

    if (test == Append_equals) return "Append_equals";
    if (test == Prepend_equals) return "Prepend_equals";
    if (test == InsertAfter_equals) return "InsertAfter_equals";
    if (test == InsertBefore_equals) return "InsertBefore_equals";
    if (test == DeleteFront_equals) return "DeleteFront_equals";
    if (test == DeleteBack_equals) return "DeleteBack_equals";
    if (test == Delete_equals) return "Delete_equals";

    if (test == Empty_clear) return "Empty_clear";
    if (test == NonEmpty_clear) return "NonEmpty_clear";

    if (test == Set_get) return "Set_get";
    if (test == Set_front) return "Set_front";
    if (test == NonEmpty_front) return "NonEmpty_front";
    if (test == Set_back) return "Set_back";
    if (test == NonEmpty_back) return "NonEmpty_back";

    if (test == Empty_toString) return "Empty_toString";
    if (test == NonEmpty_toString) return "NonEmpty_toString";

    return "";
  }

  public static void main(String args[]) {

    if (args.length > 1 || (args.length == 1 && !args[0].equals("-v"))) {
      System.err.println("Usage: ./ListTest [-v]");
      System.exit(1);
    }
    boolean verbose = false;
    if (args.length == 1) verbose = true;

    int testCount = 0;
    // form is TESTCASE_FUNCTION
    Empty_length = testCount++;
    Append_length = testCount++;
    Prepend_length = testCount++;
    InsertAfter_length = testCount++;
    InsertBefore_length = testCount++;
    DeleteFront_length = testCount++;
    DeleteBack_length = testCount++;
    Delete_length = testCount++;

    EmptyList_index = testCount++;
    MoveFront_index = testCount++;
    MoveBack_index = testCount++;
    MoveNext_index = testCount++;
    MovePrev_index = testCount++;
    Append_index = testCount++;
    Prepend_index = testCount++;
    InsertAfter_index = testCount++;
    InsertBefore_index = testCount++;
    DeleteFront_index = testCount++;
    DeleteBack_index = testCount++;
    Delete_index = testCount++;

    Append_equals = testCount++;
    Prepend_equals = testCount++;
    InsertAfter_equals = testCount++;
    InsertBefore_equals = testCount++;
    DeleteFront_equals = testCount++;
    DeleteBack_equals = testCount++;
    Delete_equals = testCount++;

    Empty_clear = testCount++;
    NonEmpty_clear = testCount++;

    Set_get = testCount++;
    Set_front = testCount++;
    NonEmpty_front = testCount++;
    Set_back = testCount++;
    NonEmpty_back = testCount++;

    Empty_toString = testCount++;
    NonEmpty_toString = testCount++;

    // set for bits where tests are passed based off above bitmasks
    BitSet testsPassed = new BitSet(testCount); // all tests are 0 (failed) by default

    List A;
    List B;

    int i = 0;
    for (i = 0; i < testCount; i++) {
      try {
        if (i == Empty_length) {
          A = new List();
          if (A.length() == 0) testsPassed.set(i);
        } else if (i == Append_length) {
          A = new List();
          A.append(1);
          A.append(2);
          A.append(3);
          A.append(5);
          if (A.length() == 4) testsPassed.set(i);
        } else if (i == Prepend_length) {
          A = new List();
          A.prepend(6);
          A.prepend(4);
          A.prepend(2);
          A.prepend(1);
          if (A.length() == 4) testsPassed.set(i);
        } else if (i == InsertAfter_length) {
          A = new List();
          A.append(1);
          A.append(2);
          A.append(3);
          A.append(5);
          A.moveFront();
          A.insertAfter(12);
          if (A.length() == 5) testsPassed.set(i);
        } else if (i == InsertBefore_length) {
          A = new List();
          A.prepend(76);
          A.prepend(4);
          A.prepend(3);
          A.prepend(1);
          A.moveFront();
          A.insertBefore(115);
          if (A.length() == 5) testsPassed.set(i);
        } else if (i == DeleteFront_length) {
          A = new List();
          A.prepend(76);
          A.prepend(4);
          A.deleteFront();
          A.prepend(3);
          A.prepend(1);
          A.moveFront();
          A.insertBefore(115);
          A.deleteFront();
          if (A.length() == 3) testsPassed.set(i);
        } else if (i == DeleteBack_length) {
          A = new List();
          A.append(1);
          A.deleteBack();
          A.append(2);
          A.append(3);
          A.append(5);
          A.moveFront();
          A.insertAfter(12);
          A.deleteBack();
          if (A.length() == 3) testsPassed.set(i);
        } else if (i == Delete_length) {
          A = new List();
          A.append(1);
          A.append(2);
          A.moveFront();
          A.delete();
          A.append(3);
          A.append(5);
          A.moveFront();
          A.insertAfter(12);
          A.delete();
          if (A.length() == 3) testsPassed.set(i);
        } else if (i == EmptyList_index) {
          A = new List();
          if (A.index() == -1) testsPassed.set(i);
        } else if (i == MoveFront_index) {
          A = new List();
          A.append(1);
          A.append(5);
          A.append(16);
          A.append(176);
          A.append(3214);
          A.moveFront();
          if (A.index() == 0) testsPassed.set(i);
        } else if (i == MoveBack_index) {
          A = new List();
          A.append(1);
          A.append(5);
          A.append(16);
          A.append(176);
          A.append(3214);
          A.moveBack();
          if (A.index() == 4) testsPassed.set(i);
        } else if (i == MoveNext_index) {
          A = new List();
          A.append(1);
          A.append(5);
          A.append(16);
          A.append(176);
          A.append(3214);
          A.moveFront();
          A.moveNext();
          A.moveNext();
          if (A.index() != 2) continue; // didn't pass part 1 of test
          A.moveNext();
          A.moveNext();
          A.moveNext();
          if (A.index() == -1) testsPassed.set(i);
        } else if (i == MovePrev_index) {
          A = new List();
          A.append(1);
          A.append(5);
          A.append(3214);
          A.moveBack();
          A.movePrev();
          if (A.index() != 1) continue; // didn't pass part 1 of test
          A.movePrev();
          A.movePrev();
          if (A.index() == -1) testsPassed.set(i);
        } else if (i == Append_index) {
          A = new List();
          A.append(1);
          A.append(5);
          A.append(7);
          A.moveBack();
          A.append(45);
          A.append(51);
          A.append(3214);
          if (A.index() != 2) continue; // didn't pass part 1 of test
          A.moveBack();
          A.movePrev();
          A.movePrev();
          if (A.index() != 3) continue; // didn't pass part 2 of test
          A.moveFront();
          A.movePrev();
          if (A.index() == -1) testsPassed.set(i);
        } else if (i == Prepend_index) {
          A = new List();
          A.prepend(1);
          A.prepend(5);
          A.prepend(7);
          A.moveFront();
          A.prepend(45);
          A.prepend(51);
          A.prepend(3214);
          A.prepend(314);
          A.prepend(324);
          if (A.index() != 5) continue; // didn't pass part 1 of test
          A.moveBack();
          A.movePrev();
          A.prepend(234);
          A.movePrev();
          if (A.index() != 6) continue; // didn't pass part 2 of test
          A.moveFront();
          A.movePrev();
          if (A.index() == -1) testsPassed.set(i);
        } else if (i == InsertAfter_index) {
          A = new List();
          A.append(5);
          A.append(6);
          A.append(4);
          A.append(33);
          A.append(2);
          A.append(1);
          A.moveBack();
          A.insertAfter(75);
          A.moveNext();
          if (A.index() != 6) continue; // didn't pass part 1 of test
          A.insertAfter(345);
          A.moveBack();
          if (A.index() == 7) testsPassed.set(i);
        } else if (i == InsertBefore_index) {
          A = new List();
          A.prepend(34);
          A.prepend(4);
          A.prepend(354);
          A.prepend(3674);
          A.moveBack();
          A.insertBefore(435);
          if (A.index() != 4) continue; // didn't pass part 1 of test
          A.prepend(324);
          A.prepend(33464);
          A.prepend(3498);
          A.moveFront();
          A.insertBefore(67);
          if (A.index() == 1) testsPassed.set(i);
        } else if (i == DeleteFront_index) {
          A = new List();
          A.prepend(5);
          A.prepend(65);
          A.prepend(43);
          A.prepend(2);
          A.prepend(8);
          A.prepend(1);
          A.moveFront();
          A.deleteFront();
          if (A.index() != -1) continue; // didn't pass part 1 of test
          A.moveBack();
          A.deleteFront();
          if (A.index() == 3) testsPassed.set(i);
        } else if (i == DeleteBack_index) {
          A = new List();
          A.prepend(5);
          A.prepend(65);
          A.prepend(43);
          A.prepend(2);
          A.prepend(8);
          A.prepend(1);
          A.moveBack();
          A.deleteBack();
          if (A.index() != -1) continue; // didn't pass part 1 of test
          A.moveFront();
          A.deleteBack();
          A.moveNext();
          if (A.index() == 1) testsPassed.set(i);
        } else if (i == Delete_index) {
          A = new List();
          A.prepend(5);
          A.prepend(65);
          A.prepend(43);
          A.moveBack();
          A.delete();
          if (A.index() != -1) continue; // didn't pass part 1 of test
          A.prepend(2);
          A.prepend(8);
          A.prepend(1);
          A.moveBack();
          if (A.index() != 4) continue; // didn't pass part 2 of test
          A.delete();
          A.moveBack();
          if (A.index() != 3) continue; // didn't pass part 3 of test
          A.moveFront();
          A.delete();
          A.moveFront();
          if (A.index() != 0) continue; // didn't pass part 4 of test
          A.delete();
          if (A.index() == -1) testsPassed.set(i);
        } else if (i == Append_equals) {
          A = new List();
          B = new List();
          A.append(1);
          B.append(1);
          A.append(2);
          if (A.equals(B)) continue; // didn't pass part 1 of test
          B.append(2);
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == Prepend_equals) {
          A = new List();
          B = new List();
          A.prepend(1);
          B.prepend(1);
          A.prepend(2);
          if (A.equals(B)) continue; // didn't pass part 1 of test
          B.prepend(2);
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == InsertAfter_equals) {
          A = new List();
          B = new List();
          A.append(1);
          B.append(1);
          A.append(2);
          B.moveFront();
          B.insertAfter(2);
          if (!A.equals(B)) continue; // didn't pass part 1 of test
          B.append(3);
          A.moveBack();
          A.insertAfter(3);
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == InsertBefore_equals) {
          A = new List();
          B = new List();
          A.prepend(1);
          B.prepend(1);
          A.prepend(2);
          B.moveFront();
          B.insertBefore(2);
          if (!A.equals(B)) continue; // didn't pass part 1 of test
          B.prepend(3);
          A.moveFront();
          A.insertBefore(3);
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == DeleteFront_equals) {
          A = new List();
          B = new List();
          A.append(1);
          B.append(1);
          A.append(2);
          B.append(2);
          A.deleteFront();
          if (A.equals(B)) continue; // didn't pass part 1 of test
          B.deleteFront();
          if (!A.equals(B)) continue; // didn't pass part 2 of test
          A.prepend(3);
          B.prepend(3);
          A.deleteFront();
          B.deleteFront();
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == DeleteBack_equals) {
          A = new List();
          B = new List();
          A.prepend(1);
          B.prepend(1);
          A.prepend(2);
          B.prepend(2);
          A.deleteBack();
          if (A.equals(B)) continue; // didn't pass part 1 of test
          B.deleteBack();
          if (!A.equals(B)) continue; // didn't pass part 2 of test
          A.append(3);
          B.append(3);
          A.deleteBack();
          B.deleteBack();
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == Delete_equals) {
          A = new List();
          B = new List();
          A.prepend(1);
          B.prepend(1);
          A.prepend(2);
          B.prepend(2);
          A.moveBack();
          A.delete();
          if (A.equals(B)) continue; // didn't pass part 1 of test
          B.moveBack();
          B.delete();
          if (!A.equals(B)) continue; // didn't pass part 2 of test
          A.append(3);
          B.append(3);
          A.moveBack();
          A.delete();
          B.moveBack();
          B.delete();
          if (A.equals(B)) testsPassed.set(i);
        } else if (i == Empty_clear) {
          A = new List();
          A.clear();
          if (A.index() == -1 && A.length() == 0) testsPassed.set(i);
        } else if (i == NonEmpty_clear) {
          A = new List();
          A.append(1);
          A.prepend(2);
          A.moveFront();
          A.clear();
          if (A.index() == -1 && A.length() == 0) testsPassed.set(i);
        } else if (i == Set_get) {
          A = new List();
          A.append(1);
          A.prepend(2);
          A.deleteFront();
          A.moveBack();
          if ((int) A.get() == 1) testsPassed.set(i);
        } else if (i == Set_front) {
          A = new List();
          A.append(1);
          A.prepend(5);
          A.moveBack();
          if ((int) A.front() == 5) testsPassed.set(i);
        } else if (i == NonEmpty_front) {
          A = new List();
          A.prepend(5);
          A.append(7);
          A.prepend(2);
          A.moveFront();
          A.insertBefore(43);
          A.deleteFront();
          A.delete();
          if ((int) A.front() == 5) testsPassed.set(i);
        } else if (i == Set_back) {
          A = new List();
          A.prepend(1);
          A.append(5);
          A.moveFront();
          if ((int) A.back() == 5) testsPassed.set(i);
        } else if (i == NonEmpty_back) {
          A = new List();
          A.append(5);
          A.prepend(7);
          A.append(2);
          A.moveBack();
          A.insertAfter(43);
          A.deleteBack();
          A.delete();
          if ((int) A.back() == 5) testsPassed.set(i);
        } else if (i == Empty_toString) {
          A = new List();
          if (A.toString().equals("")) testsPassed.set(i);
        } else if (i == NonEmpty_toString) {
          A = new List();
          A.append(1);
          A.prepend(5);
          A.deleteBack();
          A.append(7);
          A.append(1);
          if (!A.toString().equals("5 7 1")) continue;
          A = new List();
          A.append(1.0);
          A.prepend(5.0);
          A.deleteBack();
          A.append(7.5);
          A.append(1.43);
          if (!A.toString().equals("5.0 7.5 1.43")) continue;
          A = new List();
          ObjectTestClass obj1 = new ObjectTestClass(1, 4.5, "hello");
          A.append(obj1);
          ObjectTestClass obj2 = new ObjectTestClass(5, 3.14, "how");
          A.prepend(obj2);
          A.deleteBack();
          ObjectTestClass obj3 = new ObjectTestClass(7, 75.5, "are");
          A.append(obj3);
          ObjectTestClass obj4 = new ObjectTestClass(1, 1.43, "you");
          A.append(obj4);
          if (A.toString().equals("5,3.14,how 7,75.5,are 1,1.43,you")) testsPassed.set(i);
        }
      }catch (Exception e) {
        if (verbose) {
          System.out.println("\nUnfortunately your program crashed on test " + testName(i) + " With an exception of:\n");
          e.printStackTrace();
          System.out.println();
        }
      }
    }

    if (verbose) {
      System.out.println("\nList of tests passed/failed:\n");
      for (i = 0; i < testCount; i++) {
        System.out.printf("%s %s\n", testName(i), testsPassed.get(i) ? "PASSED" : "FAILED");
      }
      System.out.println();
    }

    System.out.printf("\nPassed %d tests out of %d possible\n", testsPassed.cardinality(), testCount);

    final int maxScore = 10;

    final int totalPoints = (maxScore - testCount / 4) + testsPassed.cardinality() / 4;

    System.out.printf("\nThis gives you a score of %d out of %d for ListTest\n\n", totalPoints, maxScore);
  }
}

