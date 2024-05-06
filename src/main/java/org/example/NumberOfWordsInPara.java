package org.example;
import java.util.*;
import java.util.stream.Collectors;

public class NumberOfWordsInPara {
    public static void main(String[] args) {
        String[] words_array = getStrings();
        int numberOfWords = words_array.length;
        Map<String, Integer> wordToFreqMap = new HashMap<>();
        for(String each_word : words_array){
            if(wordToFreqMap.containsKey(each_word)){
                int new_val = wordToFreqMap.get(each_word) + 1;
                wordToFreqMap.put(each_word,new_val);
            }
            else {
                wordToFreqMap.put(each_word,1);
            }
        }
        Map<String, Long> wordToFreqMap2 = Arrays.stream(words_array)
                .collect(Collectors.groupingBy(word -> word.toLowerCase(), Collectors.counting()));

        System.out.println("numberOfWords : " + numberOfWords);
        System.out.println(wordToFreqMap2);
        System.out.println(wordToFreqMap);
        Set<Map.Entry<String, Integer>> collect = wordToFreqMap.entrySet().
                stream().filter(entry ->  entry.getValue() > 1).
                collect(Collectors.toSet());
        Map<String, Integer> collect1 = wordToFreqMap.entrySet().
                stream().filter(entry -> entry.getValue() > 1).
                collect(Collectors.toMap(entry -> {
                    return entry.getKey();
                }, entry -> {
                    return entry.getValue();
                }));
        System.out.println(collect1);
        System.out.println(collect);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to search: ");
        String wordToSearch = scanner.nextLine();
        if (wordToFreqMap.containsKey(wordToSearch)){
            System.out.println("Contains");
        }
        else {
            throw new WordNotFoundException();
        }
    }

    private static String[] getStrings() {
        String para = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        return para.split(" ");
    }
}

class WordNotFoundException extends RuntimeException{
    public WordNotFoundException() {
        super("No such word found");
    }
}
