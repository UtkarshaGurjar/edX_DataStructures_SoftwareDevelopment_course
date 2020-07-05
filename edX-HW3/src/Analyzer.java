import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) 
	{

		List<Sentence> l = new ArrayList<>();
	    if (filename == null) 
	    	return l;
	    Sentence s;
	    String line;
	    int score;
	    String text;
	    Pattern pattern = Pattern.compile("^([-]*\\d\\s.+)");
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        while ((line = br.readLine()) != null) {
	            try {
	                if (!pattern.matcher(line).matches()) continue;
	                score = Integer.parseInt(line.substring(0, 2).trim());
	                if (score < -2 || score > 2) continue;
	                text = line.substring(2).trim();
                } catch (NumberFormatException n) {
	                n.printStackTrace();
	                continue;
                }
	            s = new Sentence(score, text);
	            l.add(s);
            }
        } catch (IOException e) {
	        e.printStackTrace();
        }
        return l;
	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {

		HashMap<String, Word> w = new HashMap<>();
        if (sentences == null || sentences.isEmpty()) return new HashSet<>();
        for (Sentence sentence : sentences) {
            if (sentence == null || sentence.getText() == null) continue;
            for (String text : sentence.getText().split("\\s")) {
                text = text.toLowerCase();
                if (!text.matches("^[a-z].*")) continue;
                Word word = new Word(text);
                if (w.containsKey(text)) {
                    word = w.get(text);
                    word.increaseTotal(sentence.getScore());
                } else {
                    word.increaseTotal(sentence.getScore());
                    w.put(text, word);
                }
            }
        }
        return new HashSet<>(w.values());
	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		HashMap<String, Double> h = new HashMap<>();
	    if (words == null || words.isEmpty()) return h;
	    for (Word word : words) {
	        if (word == null) continue;
	        h.put(word.getText(), word.calculateScore());
        }
        return h;
	}
	public static boolean isNonCharacter(char input) {
        return input < 'a' || input > 'z';
    }
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		if (sentence == null || wordScores == null || sentence.isEmpty()) {
            return 0.0;
        }
        Double totalScore = 0.0;
        String[] words = sentence.split(" ");
        int wordCount = words.length;
        for (String word : words) {
            String token = word.toLowerCase();
            if (isNonCharacter(token.charAt(0))) {
                wordCount--;
                continue;
            }
            totalScore += wordScores.getOrDefault(token, 0.0);
        }

        return wordCount == 0 ? 0.0 : totalScore / wordCount;
    }
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
