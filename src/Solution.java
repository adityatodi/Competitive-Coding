import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord;
        int wordsInTrie;
        public TrieNode() {
            for(int i=0; i<26; i++){
                this.children[i] = null;
            }
            endOfWord = false;
            wordsInTrie = 0;
        }
    }
    private static TrieNode root;

    public static int getCharIdx(char ch) {
        return ch - 'a';
    }

    public static void insertIntoTrie(TrieNode current, String s) {
        // System.out.println("Adding word: " + s);
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            TrieNode node;
            if (current.children[getCharIdx(ch)] == null) {
                node = new TrieNode();
                current.children[getCharIdx(ch)] = node;
            }
            node = current.children[getCharIdx(ch)];
            current = node;
            current.wordsInTrie = current.wordsInTrie + 1;
            // System.out.println("Current key list: " +
            //  Arrays.toString(current.children.keySet().toArray()));
            // System.out.println("WordsInTrie: " + current.wordsInTrie);
        }
        current.endOfWord = true;
    }

    public static int findCommonPrefixWords(TrieNode current, String word) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.children[getCharIdx(ch)] == null) {
                return 0;
            }
            current = current.children[getCharIdx(ch)];
        }
        return current.wordsInTrie;
    }

    public static List<Integer> contacts(List<List<String>> queries) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if (root == null)
            root = new TrieNode();
        for (List<String> query: queries) {
            if (query.get(0).equals("add")) {
                insertIntoTrie(root, query.get(1));
            }
            else {
                res.add(findCommonPrefixWords(root, query.get(1)));
            }
        }
        return res;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int queriesRows = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> queries = new ArrayList<>();

        IntStream.range(0, queriesRows).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.contacts(queries);
        System.out.println(result);
    }
}