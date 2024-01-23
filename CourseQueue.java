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
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the
 * max-heap invariant, so that the Course at the root should have the highest score, and all
 * children always have a score lower than or equal to their parent's.
 * <p>
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT<Course> {

    // data fields
    private final Course[] queue; // array max-heap of courses representing this priority queue
    private final int size;       // number of courses currently in this priority queue

    /**
     * Creates a new, empty CourseQueue with the given capacity
     *
     * @param capacity the capacity of this CourseQueue
     * @throws IllegalArgumentException if the capacity is not a positive integer
     */
    public CourseQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be a positive integer");
        }
        queue = new Course[capacity];
        size = 0;
    }

    /**
     * Returns a deep copy of this CourseQueue containing all of its elements in the same order.
     * This method does not return the deepest copy, meaning that you do not need to duplicate
     * courses. Only the instance of the heap (including the array and its size) will be duplicated.
     *
     * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
     */
    public CourseQueue deepCopy() {
        CourseQueue q2 = new CourseQueue(queue.length);
        for (int i = 0; i < size; i++) {
            q2.enqueue(queue[i]);
        }
        return q2;
    }

    /**
     * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
     * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY
     * of this queue.
     *
     * @return an Iterator for this CourseQueue
     * @see CourseIterator
     */
    @Override public Iterator<Course> iterator() {
        return new CourseIterator(deepCopy());
    }

    ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
    // Add the @Override annotation to these methods once this class implements PriorityQueueADT!

    /**
     * Checks whether this CourseQueue is empty
     *
     * @return {@code true} if this CourseQueue is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the size of this CourseQueue
     *
     * @return the size of this CourseQueue
     */
    public int size() {
        return this.size;
    }

    /**
     * Adds the given Course to this CourseQueue and use the percolateUp() method to
     * maintain max-heap invariant of CourseQueue. Courses should be compared using
     * the Course.compareTo() method.
     *
     * @param toAdd Course to add to this CourseQueue
     * @throws NullPointerException  if the given Course is null
     * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
     */
    public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
        if (toAdd == null) {
            throw new NullPointerException("cannot enqueue a null course");
        }
        if (size == queue.length) {
            throw new IllegalStateException("cannot add to a full queue");
        }
        queue[size] = toAdd;
        percolateUp(size + 1);
    }

    /**
     * Removes and returns the Course at the root of this CourseQueue, i.e. the Course
     * with the highest priority. Use the percolateDown() method to maintain max-heap invariant of
     * CourseQueue. Courses should be compared using the Course.compareTo() method.
     *
     * @return the Course in this CourseQueue with the highest priority
     * @throws NoSuchElementException with a descriptive error message if this CourseQueue is
     *                                empty
     */
    public Course dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("cannot dequeue from an empty queue");
        }
        Course temp = this.queue[0];
        this.queue[size - 1] = null;
        if (size != 0) {
            percolateDown(0);
        }
        return temp;
    }


    /**
     * Returns the Course at the root of this CourseQueue, i.e. the Course with
     * the highest priority.
     *
     * @return the Course in this CourseQueue with the highest priority
     * @throws NoSuchElementException if this CourseQueue is empty
     */
    public Course peek() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException("cannot peek an empty heap");
        }
        return this.queue[0];
    }

    ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////

    /**
     * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If
     * the element at the given index does not violate the max-heap invariant (it is higher priority
     * than its children), then this method does not modify the heap.
     * <p>
     * Otherwise, if there is a heap violation, then swap the element with the correct child and
     * continue percolating the element down the heap.
     * <p>
     * This method may be implemented iteratively or recursively.
     *
     * @param index index of the element in the heap to percolate downwards
     * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
     */
    protected void percolateDown(int index) throws IndexOutOfBoundsException {
        if (!(index < size && index >= 0)) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (!(left < size && left >= 0) && !(right < size && right >= 0)) {
            throw new IndexOutOfBoundsException("index for left or right is out of bounds");
        }
        int max = right;
        if ((left < size && left >= 0) && (right < size && right >= 0)) {
            max = this.queue[left].compareTo(this.queue[right]);
        } else if ((left < size && left >= 0)) {
            max = left;
        }
        if (this.queue[index].compareTo(this.queue[max]) >= 0) {
            return;
        }
        Course temp = this.queue[index];
        this.queue[index] = this.queue[max];
        this.queue[max] = temp;
        percolateDown(max);
    }

    /**
     * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element
     * at the given index does not violate the max-heap invariant (it is lower priority than its
     * parent), then this method does not modify the heap.
     * <p>
     * Otherwise, if there is a heap violation, swap the element with its parent and continue
     * percolating the element up the heap.
     * <p>
     * This method may be implemented iteratively or recursively.
     *
     * @param index index of the element in the heap to percolate upwards
     * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
     */
    protected void percolateUp(int index) throws IndexOutOfBoundsException {
        if (!(index < size)) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        if (!(index >= 0)) {
            throw new IndexOutOfBoundsException("index out of bounds 2");
        }
        if (index == 0) {
            return;
        }
        int parent = (index - 1) / 2;
        if (this.queue[parent].compareTo(this.queue[index]) < 0) {
            Course temp = this.queue[index];
            this.queue[index] = this.queue[parent];
            this.queue[parent] = temp;
            percolateUp(parent);
        }
    }

    ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////

    /**
     * Returns a String representing this CourseQueue, where each element (course) of the queue is
     * listed on a separate line, in order from the highest priority to the lowest priority.
     *
     * @return a String representing this CourseQueue
     * @author yiwei
     * @see Course#toString()
     * @see CourseIterator
     */
    @Override public String toString() {
        StringBuilder val = new StringBuilder();

        for (Course c : this) {
            val.append(c).append("\n");
        }

        return val.toString().trim();
    }

}
