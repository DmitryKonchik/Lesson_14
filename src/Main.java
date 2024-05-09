import java.io.*;

public class Main {
    public static void main(String[] args) {
// First Task
        File resultFile = new File("src/Result.txt");
        File book = new File("src/romeo-and-juliet.txt");
        String line;
        String longestWord = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(book))){
            while ((line = reader.readLine()) != null){
                String[] words = line.split("\\s+");
//                Если это слово считается за два, тогда надо эту строку заменить и получится unthankfulness
//                String[] words = line.split("[\\s-]+");
                for (String w : words) {
                    String newString = w.replaceAll("[,/+=.!?]+", "");
                    if (newString.length() > longestWord.length()) {
                        longestWord = newString;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(longestWord);

        try (FileWriter outputStream = new FileWriter(resultFile)){
            outputStream.write(longestWord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}