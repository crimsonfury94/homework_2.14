package com.company.impl;

import com.company.exception.ArrayIsFullException;
import com.company.exception.ElementIsNullException;
import com.company.exception.IndexMoreThanElementsException;
import com.company.exception.RemoveNonExistentElementException;
import com.company.interfaces.StringList;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] stringList;
    private int size;

    public StringListImpl() {
        stringList = new String[10];
    }

    public StringListImpl(int initSize) {
        if (initSize <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным");
        }
        stringList = new String[initSize];
    }


    @Override
    public String add(String item) {
        sizeValid();
        itemValid(item);
        stringList[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        sizeValid();
        itemValid(item);
        indexValid(index);

        for (int i = size; i > index; i--) {
            stringList[i] = stringList[i - 1];
        }
        size++;
       return stringList[index] = item;
    }

    @Override
    public String set(int index, String item) {
        indexValid(index);
        itemValid(item);
       return stringList[index] = item;
    }

    @Override
    public String remove(String item) {
        itemValid(item);

        int index = lastIndexOf(item);
        if (index == -1) {
            throw new RemoveNonExistentElementException();
        }

        if (index != size) {
            System.arraycopy(stringList, index + 1, stringList, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        indexValid(index);

        String item = stringList[index];

        if (index != size) {
            System.arraycopy(stringList, index + 1, stringList, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            String s = stringList[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            String s = stringList[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        indexValid(index);
        return stringList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(stringList, size);
    }

    private void itemValid(String item) {
        if (item == null) {
            throw new ElementIsNullException();
        }
    }

    private void sizeValid() {
        if (size == stringList.length) {
            throw new ArrayIsFullException();
        }
    }

    private void indexValid(int index) {
        if (index < 0 || index > size) {
            throw new IndexMoreThanElementsException();
        }
    }
}
