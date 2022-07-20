package com.company;

import com.company.impl.IntegerListImpl;
import com.company.interfaces.IntegerList;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();

        integerList.add(4);
        integerList.add(5);
        integerList.add(6);
        integerList.add(7);
        integerList.add(4);
        integerList.add(5);
        integerList.add(55);
        integerList.add(666);
        integerList.add(777);
        integerList.add(456);
        integerList.add(456456);
        integerList.add(45645333);
        integerList.add(57658);
        integerList.add(456456547);
        integerList.add(3466454);
        integerList.add(3466454);
        integerList.add(346);

        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.size());
    }
}
