package com.company.impl;

import com.company.exception.ArrayIsFullException;
import com.company.exception.ElementIsNullException;
import com.company.exception.IndexMoreThanElementsException;
import com.company.exception.RemoveNonExistentElementException;
import com.company.interfaces.IntegerList;
import com.company.interfaces.StringList;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] integerList;
    private int size;

    public IntegerListImpl() {
        integerList = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        if (initSize <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным");
        }
        integerList = new Integer[initSize];
    }


    @Override
    public Integer add(Integer item) {
        sizeValid();
        itemValid(item);
        integerList[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        sizeValid();
        itemValid(item);
        indexValid(index);

        for (int i = size; i > index; i--) {
            integerList[i] = integerList[i - 1];
        }
        size++;
        return integerList[index] = item;
    }

    @Override
    public Integer set(int index, Integer item) {
        indexValid(index);
        itemValid(item);
        return integerList[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        itemValid(item);

        int index = lastIndexOf(item);
        if (index == -1) {
            throw new RemoveNonExistentElementException();
        }

        if (index != size) {
            System.arraycopy(integerList, index + 1, integerList, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        indexValid(index);

        Integer item = integerList[index];

        if (index != size) {
            System.arraycopy(integerList, index + 1, integerList, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        itemValid(item);
        sort();
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(integerList[mid])) {
                return true;
            }

            if (item < integerList[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = integerList[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            Integer s = integerList[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        indexValid(index);
        return integerList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        return Arrays.copyOf(integerList, size);
    }

    private void sort() {
        for (int i = 1; i < size; i++) {
            int temp = integerList[i];
            int j = i;
            while (j > 0 && integerList[j - 1] >= temp) {
                integerList[j] = integerList[j - 1];
                j--;
            }
            integerList[j] = temp;
        }
    }


    private void itemValid(Integer item) {
        if (item == null) {
            throw new ElementIsNullException();
        }
    }

    private void sizeValid() {
        if (size == integerList.length) {
            throw new ArrayIsFullException();
        }
    }

    private void indexValid(int index) {
        if (index < 0 || index > size) {
            throw new IndexMoreThanElementsException();
        }
    }
}
