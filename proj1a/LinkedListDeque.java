public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node() {
            this.item = null;
            this.prev = null;
            this.next = null;
        }

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0 && sentinel.next == sentinel && sentinel.prev == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node pointer = sentinel;
        while (pointer.next != sentinel) {
            pointer = pointer.next;
            System.out.print(pointer.item + " ");
        }
    }

    public T removeFirst() {
        Node firstNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstNode.item;
    }

    public T removeLast() {
        Node lastNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return lastNode.item;
    }

    public T get(int index) {
        Node pointer = sentinel;
        int flag = 0;
        while (flag < index) {
            pointer = pointer.next;
            flag += 1;
        }
        return pointer.next.item;
    }

    private T getRecursiveHelper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<String> L = new LinkedListDeque<>();
//        L.addFirst("1");
//        L.addFirst("2");
//        L.addLast("3");
//        L.addLast("4");
//        System.out.println(L.get(0));
//        System.out.println(L.get(3));
//        System.out.println(L.removeFirst());
//        System.out.println(L.size());
//        System.out.println(L.removeLast());
//        System.out.println(L.size());
//        L.getRecursive(3);
//    }

}
