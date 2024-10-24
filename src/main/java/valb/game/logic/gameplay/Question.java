package valb.game.logic.gameplay;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Random;

// Is it redundancy to seperate question?
// Nah, can upgrade the question to be saved into a file later separated from round
class Question {

    private String questionTitle;
    private HashMap<String, Integer> answers = new HashMap<>(); // Should be the easiest and fastest O(1) lookup

    Question(){
        //pickRandomQuestion();
    }
    String getQuestionTitle(){
        return this.questionTitle;
    }
    HashMap<String, Integer> getAnswers() {
        return this.answers;
    }

    // Select a random file
    void pickRandomQuestion(){
        String filePath = "src/main/resources/valb/game/questions";
        File directory = new File(filePath);
        File[] files = directory.listFiles(File::isFile);
        Random random = new Random();
        assert files !=null;
        int randomIndex = random.nextInt(files.length);
        importData(filePath+"/"+files[randomIndex].getName());
    }

    // Import .txt file, might change later
    void importData(String filePath){
        String line;
        String word;
        int score;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            // Getting the first line/title
            line = br.readLine();
            this.questionTitle = line;
            HashMap<String, Integer> importedAnswer = new HashMap<>();
            while ((line = br.readLine() )!= null) {
                word = line.substring(0,line.length()-2); // Remove the score at the end
                score = Integer.parseInt(line.substring(line.length()-1));
                importedAnswer.put(word, score);
            }
            this.answers = importedAnswer;
        } catch (IOException e) {
            System.err.println("Ohnyo importData in Question messed up for file "+ filePath);
        }
    }

    // It will check if the answer is close enough, implement Fuzzy here
    AbstractMap.SimpleEntry<String, Integer> checkAnswer(String answer){
        String lowerCasedAnswer = answer.toLowerCase();
        ExtractedResult extractedResult = FuzzySearch.extractOne(lowerCasedAnswer, this.answers.keySet());
        String extractedWord = extractedResult.getString();
        // Pretty relax in terms of matching
        if (FuzzySearch.ratio(lowerCasedAnswer, extractedWord.toLowerCase()) >= 80){
            return new AbstractMap.SimpleEntry<>(extractedWord, this.answers.get(extractedWord));
        } else {
            return new AbstractMap.SimpleEntry<>(answer, 0); // return the original typing
        }
    }
}

