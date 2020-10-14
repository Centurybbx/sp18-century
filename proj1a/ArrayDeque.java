public class ArrayDeque<T> {
    private T[] items;
    private T[] initArr;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double memoryUsageRatio;
    private static final int INIT_CAPACITY = 8;
    private static final double RATIONAL_RATIO = 0.25;

    public ArrayDeque() {
        initArr = (T[]) new Object[INIT_CAPACITY];
        items = initArr;
        size = 0;
        nextFirst = initArr.length - 1;
        nextLast = 0;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        int currentFirst = (nextFirst + 1) % items.length;
        int currentLast = (nextLast - 1 + items.length) % items.length;
        if (currentFirst < currentLast) {
            int length = currentLast - currentFirst + 1;
            System.arraycopy(initArr, currentFirst, newArr, 0, length);
            nextFirst = capacity - 1;
            nextLast = length;
        }
        initArr = newArr;
        items = initArr;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        initArr[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        initArr[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int currentFirst = (nextFirst + 1) % items.length;
        while (currentFirst != nextLast) {
            System.out.print(items[currentFirst] + " ");
            currentFirst = (currentFirst + 1) % items.length;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T removedFirst = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return removedFirst;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        T removedLast = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return removedLast;
    }

    public T get(int index) {
        if (index > items.length) {
            return null;
        }
        return items[(index + nextFirst + 1) % items.length];
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> A = new ArrayDeque<>();
//        int i = 1;
//        while (i <= 9) {
//            A.addLast(i);
//            i += 1;
//        }
//        while (i < 15) {
//            A.addFirst(i);
//            i += 1;
//        }
//        System.out.println(A.removeLast());
//        A.printDeque();
//        System.out.println(A.get(8));
//        System.out.println(A.get(7));
//    }

}
