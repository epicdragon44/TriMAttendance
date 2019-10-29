import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MeetingAttendance {
    static int columnOfIDs = 3;

    public static void main(String[] args) throws IOException {
        Scanner csvInput = new Scanner(new File("Meeting Attendance - Form Responses 1.csv"));
        PrintWriter output = new PrintWriter(new File("MeetingAttendanceOutput.csv"));
        output.println(csvInput.nextLine());
        while (csvInput.hasNextLine()) {
            String[] inputArr = csvInput.nextLine().split(",");
            if (containsID(inputArr[columnOfIDs]))
                inputArr[inputArr.length-1] = "TRUE";
            else
                inputArr[inputArr.length-1] = "FALSE";
            output.println(assembleStr(inputArr));
        }
        output.close();
        csvInput.close();
    }

    public static boolean containsID(String ID) throws IOException {
        Scanner idInput = new Scanner(new File("ScannedIDs.txt"));
        while (idInput.hasNextLine()) {
            if (idInput.nextLine().contains(ID)) {
                idInput.close();
                return true;
            }
        }
        idInput.close();
        return false;
    }

    public static String assembleStr(String[] input) {
        StringBuilder sb = new StringBuilder();
        for (String s : input)
            sb.append(s + ",");
        return sb.toString().substring(0, sb.toString().length()-1);
    }
}
