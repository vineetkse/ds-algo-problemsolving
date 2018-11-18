package com.vineetkse.ds.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList<T extends Comparable<T>> {
    
    private Node head;

    private int count = 0;

    public Node getHead() {
        return head;
    }

    public boolean prepend(T data) {
        
        return add(data, 0);
    }

    public boolean append(T data) {

        return add(data, size() - 1);
    }

    public boolean add(T elem, int position) {

        if(position < 0) position = 0;

        if(position > size()) position = size();

        Node node = new Node(elem);

        if(head == null) {
            head = node;
        } else if(position == 0) {
            node.next = head;
            head = node;    
        } else {
            Node current = head;
            for(int index = 1; index < position; index++) {
                current = current.next;
            }

            node.next = current.next;
            current.next = node;
        }

        count++;

        return true;
    }

    public Node remove(T elem, int position) {

        if(position < count) return null;
        if(position > size() - 1) return null;

        if(position == 0) {
            
            head = head.next;
        } else {

            Node current = head;

            for(int index = 0; index < position; index++) {
                current = current.next;
            }

            Node node = current;

            current.next = current.next.next;

            return node;
        }
        return null;
    }

    public int size() {
        
        return count;
    }

    public boolean isEmpty() {
        
        return count == 0;
    }

    public void traverse() {
        List<T> listOfNodes = toList();

        System.out.println(listOfNodes);
    }

    public List<T> toList() {
        List<T> listOfNodes = new ArrayList<T>();

        Node current = head;

        while(current != null) {
            listOfNodes.add(current.data);
            current = current.next;
        }

        return listOfNodes;
    }

    public T[] toArray() {
        Object[] array = (Object[]) toList().toArray();

        return (T[]) array;
    }

    public boolean containsLoop() {
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) return true;
        }

        return false;
    }

    public Node reverse() {

        Node current = head;
        Node previous = null;

        while(current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    public int indexOf(T elem) {

        Node node = head;

        int index = 0;
        while(node != null) {
            if(elem.compareTo(node.data) == 0) {
                return index;
            } 

            node = node.next;

            index++;
        }

        return -1;
    }
    

    /** Data node for linkedlist */
    class Node {
        T data;

        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        list.add(9, 0);
        list.add(11, 1);
        list.add(1, 2);
        list.add(12, 1);

        list.traverse();

        System.out.println(list.indexOf(11));
    }
}