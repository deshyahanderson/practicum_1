import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductReader {
    public static void main(String[] args) {
        // Initialize JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Product File");

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
                int lineNum = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.printf("Line %d: %s%n", lineNum++, line);
                }
            } catch (IOException e) {
                System.out.println("❌ Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
