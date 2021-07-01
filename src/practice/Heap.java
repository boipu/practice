package practice;

import java.util.Collections;
import java.util.PriorityQueue;

public class Heap {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap =  new PriorityQueue<>();

        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(3);

        while(!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);

        while(!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
        }
    }
}
