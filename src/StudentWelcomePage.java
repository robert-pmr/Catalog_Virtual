import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.awt.BorderLayout;

public class StudentWelcomePage {
    private static final String FILE_NAME = "grades.txt";
    private JFrame frame;
    private JTextArea gradesArea;

    public StudentWelcomePage(String userID) {
        frame = new JFrame("Catalog - Note Student");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bună, " + userID + "! Notele și absențele tale:");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(welcomeLabel, BorderLayout.NORTH);

        gradesArea = new JTextArea();
        gradesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gradesArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> loadGrades(userID));
        frame.add(refreshButton, BorderLayout.SOUTH);

        loadGrades(userID);
        frame.setVisible(true);
    }

    private void loadGrades(String studentUsername) {
        List<String> grades = new ArrayList<>();
        int absente = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(studentUsername)) {
                    if (parts[1].equals("ABSENTA")) {
                        absente++;
                    } else {
                        grades.add(parts[1] + ": " + parts[2]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        if (grades.isEmpty()) {
            sb.append("Nu ai note.\n");
        } else {
            for (String grade : grades) {
                sb.append(grade).append("\n");
            }
        }
        sb.append("\nTotal absențe: ").append(absente);
        gradesArea.setText(sb.toString());
    }
}
