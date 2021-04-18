package com.algorithms.wordladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class Node {
    public ArrayList<Node> children = new ArrayList<>();
    public String word = "";
    public Node(String word) {
        this.word = word;
    }
}

class Solution {

    public List<String> ladderLength(String beginWord, String endWord, List<String> wordList, int difference) {
        Node head = createGraph(beginWord, wordList, difference);
        return dfs(head, endWord);
    }

    //Now depth first search it up
    private List<String> dfs(Node node, String word) {
        ArrayList<String> ladder = new ArrayList<>();
        List<String> rung;

        ladder.add(node.word);
        if (node.word.equals(word)) return ladder;

        for (int i = 0; i<node.children.size(); i++) {
            rung = dfs(node.children.get(i), word);
            if (rung != null) {
                ladder.addAll(rung);
                return ladder;
            }
        }

        return null;
    }
    
    //This will generate a graph with no repeating nodes
    private Node createGraph(String start, List<String> wordList, int difference) {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        List<String> children;
        Node current;

        //For each child, only go so far as to get to the end node
        //don't keep going. Also to avoid circular stuff we need to make sure we add each node once
        Node head = new Node(start);
        queue.add(head);
        while (queue.size()>0) {
            //Get the next node
            current = queue.poll();
            //Once the children have been picked, remove the word from the list
            children = getChildren(current.word, wordList, difference);
            for (String s : children) {
                wordList.remove(s);
                //Just place all of the nodes inside of the graph in the shortest manner
                Node child = new Node(s);
                current.children.add(child);
                queue.add(child);
            }
        }
        return head;
    }


    //Given a word, this will return all of the valid children
    private List<String> getChildren (String word, List<String> wordList, int difference) {
        List<String> result = new ArrayList<>();
        String cwrd;
        int count;
        for (int i=0; i<wordList.size(); i++) {
            cwrd = wordList.get(i);
            count = 0;
            for (int j = 0; j<cwrd.length() && j<word.length(); j++) {
                if (word.charAt(j)!=cwrd.charAt(j)) count++;
            };
            count += Math.abs(word.length() - cwrd.length());
            if (count<difference+1 && !cwrd.equals(word)) result.add(cwrd);
        }
        return result;
    }
}
