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
        nextFirst = (initArr.length - 1) / 2;
        nextLast = nextFirst + 1;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        nextFirst = (newArr.length - 1) / 2 - initArr.length / 2;
        nextLast = nextFirst + initArr.length + 1;
        System.arraycopy(initArr, 0, newArr, nextFirst + 1, initArr.length);
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
        if (nextFirst < nextLast) {
            int flag = nextFirst + 1;
            while (flag < nextLast) {
                System.out.print(items[flag] + " ");
                flag += 1;
            }
        } else {
            // When nextFirst is greater than nextLast:
            int flag = nextFirst;
            while (flag < items.length) {
                System.out.print(items[flag] + " ");
                flag += 1;
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }

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
//        A.addFirst(15);
//        A.addFirst(10);
//        A.addFirst(5);
//        A.addLast(20);
//        A.addLast(25);
//        A.addFirst(3);
//        A.addFirst(2);
//        A.addFirst(1);
//        A.addFirst(0);
//        A.addFirst(-1);
//        A.addFirst(-2);
//        A.addLast(100);
//        A.addLast(100);
//        A.addLast(100);
//        A.addLast(100);
//        A.addLast(100);
//        A.addLast(100);
//        A.addLast(100);
//        A.printDeque();
//    }

}
