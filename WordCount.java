import java.util.HashMap;

public class WordCount {
    public HashMap<String, Integer> countWords(String input) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        String[] words = input.trim().split("\\s+");
        
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }

    public static void main(String[] args) {
        WordCount solution = new WordCount();
        
        String input = "This this is is done by Ashwin Ashwin";
        HashMap<String, Integer> result = solution.countWords(input);
        System.out.println("result: " + result);
    }
}