import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;

        String ID = "";
        String firstName = "";
        String lastname = "";
        String title = "";
        String rec = "";
        int YOB = 0;

        ArrayList<String> people = new ArrayList<>();

        // Create a loop to input the person data
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            lastname = SafeInput.getNonZeroLenString(in, "Enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Enter your title");
            YOB = SafeInput.getRangedInt(in, "Enter your year of birth", 1000, 9999);

            rec = ID + ", " + firstName + ", " + lastname + ", " + title + ", " + YOB;

            System.out.println(rec);
            people.add(rec);

            // Corrected variable name
            doneInput = SafeInput.getYNConfirm(in, "Are you done [Y/N]");

        } while (!doneInput);

        // Close Scanner
        in.close();
    }
}
