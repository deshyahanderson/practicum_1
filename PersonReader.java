import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PersonReader {
    public static void main(String[] args) {
        // Initialize JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person File");

        // Show the file chooser and get the result
        int result = fileChooser.showOpenDialog(null);

        // If the user selects a file
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            System.out.println("File selected: " + fileName);

            // Step 2: Read the File Back and Display Contents Using NIO
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                System.out.printf("%-10s %-15s %-15s %-10s %-5s%n", "ID", "First Name", "Last Name", "Title", "YOB");
                System.out.println("------------------------------------------------------------");

                while ((line = reader.readLine()) != null) {
                    // Split the line assuming it is space-separated
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 5) {  // Ensure there are at least 5 parts
                        String id = parts[0];
                        String firstName = parts[1];
                        String lastName = parts[2];
                        String title = parts[3];
                        String yob = parts[4];

                        // Neatly format the output using String.format
                        System.out.printf("%-10s %-15s %-15s %-10s %-5s%n", id, firstName, lastName, title, yob);
                    } else {
                        System.out.println("Invalid record format: " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("❌ Error reading file: " + e.getMessage());
            }
        }
    }
}