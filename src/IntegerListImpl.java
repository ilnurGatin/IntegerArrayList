
import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;

    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[1];
    }

    public IntegerListImpl(int size) {
        this.storage = new Integer[size];
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Integer[] sort(Integer[] storage) {
        sortInsertion(storage);
        return storage;
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        validateSize();
        this.storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            add(item);
            return item;
        }
        System.arraycopy(storage, index, storage, index +1 , size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        return storage[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        validateIndex(index);
        System.arraycopy(storage, index +1, storage, index, size - index);
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = get(index);
        System.arraycopy(storage, index +1, storage, index, size - index);
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return binarySearch(storage, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = storage[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            Integer s = storage[i];
            if (s.equals(item)) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("otherList is null");
        }
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    public void validateItem(Integer item) {
        if (item == null) {
            throw new ItemIsNullException("Item should not be null");
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index == size) {
            throw new InvalidIndexException("Index is out of range");
        }
    }

    public void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException("Array is full");
        }
    }
    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static boolean binarySearch(Integer[] arr, int element) {
        sortInsertion(arr);
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == arr[mid]) {
                return true;
            }
            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
