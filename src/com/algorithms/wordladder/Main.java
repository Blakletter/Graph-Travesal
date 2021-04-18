package com.algorithms.wordladder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>();

        Solution s = new Solution();

        words.addAll(Arrays.asList(new String[] {"dad", "dab", "bad", "tad", "dat", "rat", "tar", "cat", "cam", "mac", "rap", "par", "map", "mep", "men"}));
        //rad->dad->dab->
        List<String> path = s.ladderLength("rad", "men", words, 1);

        System.out.println(path);
    }
}
