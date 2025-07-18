import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ProfesorWelcomePage {
    private static final String FILE_NAME = "grades.txt";
    private JFrame frame;
    private JTextField studentField, subjectField, gradeField;
    private JTextArea gradesArea;
    private JCheckBox absentCheckBox;
    private JButton submitButton, updateButton, modifyButton;

    ProfesorWelcomePage(String userID) {
        frame = new JFrame("Catalog - Profesor");
        frame.setSize(500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel welcomeLabel = new JLabel("Introduceți o notă sau marcați o absență:");
        welcomeLabel.setBounds(20, 20, 400, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 16));
        frame.add(welcomeLabel);

        JLabel studentLabel = new JLabel("Nume utilizator elev:");
        studentLabel.setBounds(20, 50, 150, 20);
        frame.add(studentLabel);

        studentField = new JTextField();
        studentField.setBounds(20, 70, 200, 25);
        frame.add(studentField);

        JLabel subjectLabel = new JLabel("Materie:");
        subjectLabel.setBounds(20, 90, 100, 20);
        frame.add(subjectLabel);

        subjectField = new JTextField();
        subjectField.setBounds(20, 110, 200, 25);
        frame.add(subjectField);

        JLabel gradeLabel = new JLabel("Notă:");
        gradeLabel.setBounds(20, 130, 100, 20);
        frame.add(gradeLabel);

        gradeField = new JTextField();
        gradeField.setBounds(20, 150, 200, 25);
        frame.add(gradeField);

        absentCheckBox = new JCheckBox("Marchează absență");
        absentCheckBox.setBounds(240, 150, 200, 25);
        frame.add(absentCheckBox);

        submitButton = new JButton("Salvează");
        submitButton.setBounds(20, 190, 150, 30);
        submitButton.addActionListener(e -> saveGrade());

        updateButton = new JButton("Actualizează notele");
        updateButton.setBounds(20, 230, 150, 30);
        updateButton.addActionListener(e -> loadGrades(studentField.getText()));

        modifyButton = new JButton("Modifică notă");
        modifyButton.setBounds(20, 270, 150, 30);
        modifyButton.addActionListener(e -> modifyGrade());

        gradesArea = new JTextArea();
        gradesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gradesArea);
        scrollPane.setBounds(20, 320, 450, 150);

        frame.add(submitButton);
        frame.add(updateButton);
        frame.add(modifyButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    private void saveGrade() {
        String studentUsername = studentField.getText();
        String subject = subjectField.getText();
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        if (absentCheckBox.isSelected()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(studentUsername + ",ABSENTA,1," + date);
                writer.newLine();
                JOptionPane.showMessageDialog(frame, "Absența a fost înregistrată!");
                loadGrades(studentUsername);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            int grade = Integer.parseInt(gradeField.getText());
            if (grade < 1 || grade > 10) {
                JOptionPane.showMessageDialog(frame, "Introduceți o notă între 1 și 10.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(studentUsername + "," + subject + "," + grade + "," + date);
                writer.newLine();
                JOptionPane.showMessageDialog(frame, "Nota a fost salvată!");
                loadGrades(studentUsername);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Nota trebuie să fie un număr.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGrades(String studentUsername) {
        List<String> grades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(studentUsername) && !parts[1].equals("ABSENTA")) {
                    grades.add(parts[1] + ": " + parts[2] + " (" + parts[3] + ")");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gradesArea.setText(grades.isEmpty() ? "Nu există note." : String.join("\n", grades));
    }

    private void modifyGrade() {
        String studentUsername = studentField.getText();
        String subject = subjectField.getText();
        String newGrade = gradeField.getText();
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        List<String> allLines = new ArrayList<>();
        int lastIndex = -1;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = allLines.size() - 1; i >= 0; i--) {
            String[] parts = allLines.get(i).split(",");
            if (parts.length >= 3 && parts[0].equals(studentUsername) && parts[1].equals(subject)) {
                lastIndex = i;
                break;
            }
        }

        if (lastIndex != -1) {
            allLines.set(lastIndex, studentUsername + "," + subject + "," + newGrade + "," + date);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String line : allLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(frame, "Nota a fost modificată!");
            loadGrades(studentUsername);
        } else {
            JOptionPane.showMessageDialog(frame, "Nota nu a fost găsită.");
        }
    }
}
