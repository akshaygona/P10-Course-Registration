//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 Course Registration
// Course:   CS 300 Fall 2022
//
// Author:   Akshay Gona
// Email:    gona@wisc.edu
// Lecturer: Hobbes LeGault
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   none
// Online Sources:  none
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Course,  CourseIterator,
 * CourseQueue and CourseReg classes in P10.
 * <p>
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {

    /**
     * START HERE, and continue with testCompareTo() after this.
     * <p>
     * This method must test the Course constructor, accessor, and mutator methods, as well as its
     * toString() implementation. The compareTo() method will get its own test.
     *
     * @return true if the Course implementation is correct; false otherwise
     * @see Course
     */
    public static boolean testCourse() {
        try {
            {
                try {
                    Course akshay = new Course("   ", 23094, 123490, 123490);
                    System.out.println("IAE expected");
                    return false;
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    e.printStackTrace();
                    return false;
                }
                try {
                    Course gona = new Course(null, 34921, 1234, 2341);
                    System.out.println("IAE expected");
                    return false;
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    e.printStackTrace();
                    return false;
                }
                try {
                    Course is = new Course("c", 0, 432, 977);
                    System.out.println("IAE expected");
                    return false;
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    e.printStackTrace();
                    return false;
                }
                try {
                    Course soo = new Course("a", 1423, 0, 9897);
                    System.out.println("IAE expected");
                    return false;
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    e.printStackTrace();
                    return false;
                }
                try {
                    Course daamn = new Course("a", 123871, 69, 10000);
                    System.out.println("IAE expected");
                    return false;
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    e.printStackTrace();
                    return false;
                }
                try {
                    Course cool = new Course("a", 40931, 332, -10);
                    System.out.println("IAE expected");
                    return false;
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    return false;
                }
            }
            {
                Course deez;
                try {
                    deez = new Course("deezN", 100, 3, 30);
                } catch (Exception e) {
                    System.out.println("bro how did you get a random exception");
                    return false;
                }
                if (deez.getNumCredits() != 3) {
                    System.out.println("getNumCredits is busted");
                    return false;
                }
                deez.setProfessor("dr.deez", 4.0);
                deez.setSeatsAvailable(5);
                if (!deez.toString().equals("deezN 100 (5 seats) with dr.deez (4.0)")) {
                    System.out.println("toString() or setProfessor or setSeatsAvailable be busted");
                    return false;
                }
                try {
                    deez.setProfessor(null, 6);
                } catch (Exception e) {
                    System.out.println("setprof shouldnt get to rating because name is null");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels
     * of the comparison here!
     * <p>
     * Once you complete this test, finish the Course implementation if you have not done so already,
     * then move to testCourseQueue() and testEnqueueDequeue().
     *
     * @return true if the compareTo() implementation is correct; false otherwise
     * @see Course#compareTo(Course)
     */
    public static boolean testCompareTo() {
        try {
            {
                Course akshay = new Course("CS", 1, 1, 0);
                Course gona = new Course("bruh", 100, 2, 34);
                if (akshay.compareTo(gona) <= 0 || gona.compareTo(akshay) >= 0) {
                    System.out.println("major dept should be higher");
                    return false;
                }
            }
            {
                Course akshay = new Course("CS", 300, 3, 1);
                Course gona = new Course("CS", 300, 3, 0);
                if (akshay.compareTo(gona) <= 0 || gona.compareTo(akshay) >= 0) {
                    System.out.println("seat comparison fails");
                    return false;
                }
            }
            {
                Course akshay = new Course("CS", 1000, 4, 1030);
                akshay.setProfessor("reddy", 3.0);
                Course gona = new Course("CS", 1000, 4, 10);
                if (akshay.compareTo(gona) <= 0 || gona.compareTo(akshay) >= 0) {
                    System.out.println(
                        "course with professor should give higher ranking that course without"
                            + " professor");
                    return false;
                }
            }
            {
                Course akshay = new Course("math", 2021, 4, 10);
                Course gona = new Course("math", 2021, 4, 10);
                if (akshay.compareTo(gona) != 0 || gona.compareTo(akshay) != 0) {
                    System.out.println("0 expected but was not there");
                    return false;
                }
            }
            {
                Course akshay = new Course("CS", 130, 3, 1000);
                akshay.setProfessor("reddy", 4.0);
                Course gona = new Course("CS", 130, 3, 1000);
                gona.setProfessor("rao", 1.0);
                if (akshay.compareTo(gona) <= 0 || gona.compareTo(akshay) >= 0) {
                    System.out.println("higher prof comparison fails");
                    return false;
                }
            }
            {
                Course akshay = new Course("science", 130, 4, 10);
                akshay.setProfessor(null, 2.0);
                Course gona = new Course("science", 130, 4, 10);
                gona.setProfessor(null, 1.0);
                if (akshay.compareTo(gona) != 0 || gona.compareTo(akshay) != 0) {
                    System.out.println("0 expected but was not there");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal
     * cases and error cases, as well as a filled and re-emptied queue.
     * <p>
     * Once you have completed this method, implement the required methods in CourseQueue and verify
     * that they work correctly.
     *
     * @return true if CourseQueue's other methods are implemented correctly; false otherwise
     * @see CourseQueue
     */
    public static boolean testCourseQueue() {
        try {
            CourseQueue queue = null;
            try {
                queue = new CourseQueue(0);
                System.out.println("IAE expected");
                return false;
            } catch (IllegalArgumentException e) {
            } catch (Exception e) {
                System.out.println("IAE expected");
                return false;
            }
            queue = new CourseQueue(2);
            if (!queue.isEmpty() || queue.size() != 0) {
                System.out.println("isempty or size could be busted");
                return false;
            }
            try {
                queue.peek();
                System.out.println("NSEE expected");
                return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                System.out.println("NSEE expected");
                return false;
            }
            Course akshay = new Course("physics", 100, 3, 100);
            Course gona = new Course("CS", 6000, 3, 1302);
            queue.enqueue(akshay);
            if (queue.size() != 1) {
                System.out.println("bro how tf u forget to update size come on cuh");
                return false;
            }
            queue.enqueue(gona);
            if (queue.peek() != gona) {
                System.out.println("peek be busted");
                return false;
            }
            queue.dequeue();
            queue.dequeue();
            if (queue.size() != 0) {
                System.out.println("bro how tf u forget to update size come on cuh");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
     * error cases, as well as filling and emptying the queue.
     * <p>
     * You may also test the percolate methods directly, though this is not required.
     * <p>
     * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
     * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
     *
     * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
     * @see CourseQueue#enqueue(Course)
     * @see CourseQueue#dequeue()
     */
    public static boolean testEnqueueDequeue() {
        try {
            CourseQueue queue = new CourseQueue(4);
            Course akshay = new Course("Physics", 100, 2, 0);
            Course is = new Course("Math", 100, 2, 100);
            Course cool = new Course("CS", 100, 2, 1000);
            queue.enqueue(akshay);
            if (queue.peek() != akshay) {
                System.out.println("peek is busted, enq too");
                return false;
            }
            queue.enqueue(is);
            if (queue.peek() != is) {
                System.out.println("peek is busted, enq too");
                return false;
            }
            queue.enqueue(cool);
            if (queue.peek() != cool) {
                System.out.println("peek is busted, enq too");
                return false;
            }
            queue.enqueue(akshay);
            if (queue.peek() != cool) {
                System.out.println("peek is busted, enq too");
                return false;
            }
            try {
                queue.enqueue(akshay);
                System.out.println("IAE expected");
                return false;
            } catch (IllegalStateException e) {
            } catch (Exception e) {
                System.out.println("IAE expected");
                return false;
            }
            try {
                queue.enqueue(null);
                System.out.println("NPE expected");
                return false;
            } catch (NullPointerException e) {
            } catch (Exception e) {
                System.out.println("NPE expected");
                return false;
            }
            if (queue.dequeue() != cool && queue.peek() != is) {
                System.out.println("enq or deq busted");
                return false;
            }
            if (queue.dequeue() != is && queue.peek() != akshay) {
                System.out.println("enq or deq busted");
                return false;
            }
            if (queue.dequeue() != akshay) {
                System.out.println("enq or deq busted");
                return false;
            }
            queue.dequeue();
            try {
                queue.dequeue();
                System.out.println("NSEE expected");
                return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                System.out.println("NSEE expected");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
     * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
     * <p>
     * Once you have completed this method, implement the CourseIterator class and make CourseQueue
     * into an Iterable class. Verify that this works correctly, and then move on to the final test
     * method: testCourseReg().
     *
     * @return true if the CourseIterator implementation is correct; false otherwise
     * @see CourseIterator
     */
    public static boolean testCourseIterator() {
        try {
            CourseQueue queue = new CourseQueue(3);
            Course akshay = new Course("science", 100, 1, 0);
            Course gona = new Course("math", 100, 2, 1);
            Course bro = new Course("CS", 100, 3, 1);
            queue.enqueue(gona);
            queue.enqueue(bro);
            queue.enqueue(akshay);
            Course[] queueArray = new Course[3];
            queueArray[0] = bro;
            queueArray[1] = gona;
            queueArray[2] = akshay;
            int i = 0;
            Iterator<Course> iterator = queue.iterator();
            while (iterator.hasNext()) {
                if (queueArray[i++] != iterator.next()) {
                    System.out.println("iterator be busted");
                    return false;
                }
            }
            try {
                iterator.next();
                System.out.println("next() should throw exception");
                return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                System.out.println("where the NSEE at");
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
     * add, and getRecommendedCourses). Verify normal cases and error cases.
     * <p>
     * Once you have completed this method, implement CourseReg and verify that it works correctly.
     * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
     * assignments in CS 300 !!!
     *
     * @return true if CourseReg has been implemented correctly; false otherwise
     * @see CourseReg
     */
    public static boolean testCourseReg() {
        try {
            CourseReg courseReg = new CourseReg(-4, -5);
        } catch (Exception e) {
        }
        try {
            // test add
            CourseReg courseReg = new CourseReg(3, 4);
            Course akshay = new Course("CS", 300, 3, 100);
            Course is = new Course("mane", 10, 3, 30);
            Course cool = new Course("balls", 9, 1, 30);
            Course asf = new Course("math", 2323, 1, 42);

            courseReg.add(akshay);
            courseReg.add(is);
            courseReg.add(cool);
            try {
                courseReg.add(null);
            } catch (Exception e) {
                return false;

            }
            try {
                courseReg.add(asf);
            } catch (Exception e) {
            }
            try {
                courseReg.setCreditLoad(-1);
            } catch (Exception e) {
            }
            if (!courseReg.getRecommendedCourses().equals("CS 300 (100 seats)\n")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * This method calls all test methods defined by us; you may add additional methods to this if
     * you like. All test methods must be public static boolean.
     *
     * @return true if all tests in this class return true; false otherwise
     */
    public static boolean runAllTests() {
        boolean testVal = true;

        // test Course
        System.out.print("testCourse(): ");
        if (!testCourse()) {
            System.out.println("FAIL");
            testVal = false;
        } else {
            System.out.println("pass");
        }

        // test compareTo
        System.out.print("testCompareTo(): ");
        if (!testCompareTo()) {
            System.out.println("FAIL");
            testVal = false;
        } else {
            System.out.println("pass");
        }

        // test CourseIterator
        System.out.print("testCourseIterator(): ");
        if (!testCourseIterator()) {
            System.out.println("FAIL");
            testVal = false;
        } else {
            System.out.println("pass");
        }

        // test CourseQueue enqueue/dequeue
        System.out.print("testEnqueueDequeue(): ");
        if (!testEnqueueDequeue()) {
            System.out.println("FAIL");
            testVal = false;
        } else {
            System.out.println("pass");
        }

        // test CourseQueue
        System.out.print("testCourseQueue(): ");
        if (!testCourseQueue()) {
            System.out.println("FAIL");
            testVal = false;
        } else {
            System.out.println("pass");
        }

        // test CourseReg
        System.out.print("testCourseReg(): ");
        if (!testCourseReg()) {
            System.out.println("FAIL");
            testVal = false;
        } else {
            System.out.println("pass");
        }

        return testVal;
    }

    /**
     * Calls runAllTests() so you can verify your program
     *
     * @param args input arguments if any.
     */
    public static void main(String[] args) {
        runAllTests();
    }
}
