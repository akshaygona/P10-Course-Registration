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


/**
 * A application handler for course registration using a priority queue.
 */
public class CourseReg {

    // data fields
    private final CourseQueue courses;  // the priority queue of all courses
    private int creditLoad;       // the maximum number of credits you want to take

    /**
     * Creates a new course registration object
     *
     * @param capacity   the maximum number of courses to store in the priority queue
     * @param creditLoad the maximum number of credits to take next semester
     * @throws IllegalArgumentException if either capacity or creditLoad are not a positive integer
     */
    public CourseReg(int capacity, int creditLoad) throws IllegalArgumentException {
        if (creditLoad < 1 || capacity < 1) {
            throw new IllegalArgumentException(
                "either creditLoad or capacity is not a positive integer," + "which it must be");
        }
        this.courses = new CourseQueue(capacity);
        this.creditLoad = creditLoad;
    }

    /**
     * Returns a string representation of the highest-priority courses with a total number of credits
     * less than or equal to the creditLoad of this CourseReg object. Use the Iterable property of the
     * CourseQueue to help you out!
     * <p>
     * Note that this is NOT a "knapsack" problem - you're trying to maximize priority, not number of
     * credits. Just add courses to your result String until adding the next would take you over this
     * CourseReg object's creditLoad limit.
     *
     * @return a string representation with one course on each line, where the total number of credits
     * represented is less than or equal to the current creditLoad value
     */
    public String getRecommendedCourses() {
        String temp = "";
        int credits = 0;
        for (Course c : courses) {
            if ((credits = credits + c.getNumCredits()) > creditLoad) {
                break;
            }
            temp = temp + c + "\n";
        }
        return temp;
    }

    /**
     * Tries to add the given course to the priority queue; return false if the queue is full
     *
     * @param toAdd the course to add
     * @return true if the course was successfully added to the queue
     */
    public boolean add(Course toAdd) {
        try {
            courses.enqueue(toAdd);
        } catch (IllegalArgumentException e) {
            System.out.println("queue is full, cannot add");
            return false;
        }
        return true;
    }

    /**
     * Updates the creditLoad data field to the provided value
     *
     * @param creditLoad the maximum number of credits to take next semester
     * @throws IllegalArgumentException if creditLoad is not a positive integer
     */
    public void setCreditLoad(int creditLoad) throws IllegalArgumentException {
        if (creditLoad < 1) {
            throw new IllegalArgumentException("creditLoad must be a postive integer");
        }
        this.creditLoad = creditLoad;
    }
}
