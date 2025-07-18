import java.io.*;
import java.util.*;

class GradesManager {
    private static final String FILE_NAME = "grades.txt";

    public static void saveGrade(String studentUsername, String subject, int grade) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(studentUsername + "," + subject + "," + grade);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getGrades(String studentUsername) {
        List<String> grades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(studentUsername)) {
                    grades.add(parts[1] + ": " + parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grades;
    }
}
