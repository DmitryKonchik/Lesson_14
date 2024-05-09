import java.io.*;
import java.util.regex.Pattern;

public class HardTask {
        public static void main(String[] args) {
            String line;

//        Scanner scanner = new Scanner(System.in);
//            System.out.println("Введите путь к файлу где храняться  номера документов");
            File fileWithNumberOfDocuments = new File("src/numberOfDocuments.txt");
//        File fileWithNumberOfDocuments = new File(scanner.nextLine());
            File badDocumentNumber = new File("src/badDocumentNumber.txt");
            File goodDocumentNumber = new File("src/goodDocumentNumber.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(fileWithNumberOfDocuments))){
                while ((line = reader.readLine()) != null){

                    if (line.length() != 15) {
                        try (FileWriter outputStream = new FileWriter(badDocumentNumber, true)){
                            outputStream.write(line + " Номер не подходит по размеру\n");
                            continue;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (Pattern.matches("^contrct[A-z0-9]{8}$",line) || Pattern.matches("^docnum[A-z0-9]{9}$",line)) {
                        try (FileWriter outputStream = new FileWriter(goodDocumentNumber, true)){
                            outputStream.write(line + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try (FileWriter outputStream = new FileWriter(badDocumentNumber, true)){
                            outputStream.write(line + " Номер должен начинаться с docnum или contrct и содержать только буквы и цифры\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

}
