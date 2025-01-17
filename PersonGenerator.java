import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        String ID, firstName, lastName, title, rec;
        int YOB;
        ArrayList<String> people = new ArrayList<>();

        // Step 1: Collect User Input
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Enter your title");
            YOB = SafeInput.getRangedInt(in, "Enter your year of birth", 1000, 9999);

            rec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            people.add(rec);
            System.out.println("Record added: " + rec);

            doneInput = SafeInput.getYNConfirm(in, "Are you done entering persons? [Y/N]");
        } while (!doneInput);

        // Step 2: Ask for File Name and Create NIO Path
        String fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save (e.g., PersonTestData.txt)");
        Path filePath = Paths.get(fileName);

        // Step 3: Write Data to File Using NIO
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String person : people) {
                writer.write(person);
                writer.newLine();
            }
            System.out.println("✅ Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }

        // Step 4: Read the File Back and Display Contents Using NIO
        System.out.println("\n🔍 Verifying written data:");
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                System.out.printf("Line %d: %s%n", lineNum++, line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }

        in.close();
    }
}
