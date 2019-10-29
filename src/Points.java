import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Points {
    public static void main(String[] args) throws IOException {
        Scanner csvInput = new Scanner(new File("Points - Form Responses 1.csv"));
        PrintWriter output = new PrintWriter(new File("PointsOutput.csv"));
        String firstLine = csvInput.nextLine();
        String[] split = firstLine.split(",");

        //MODIFY POINT VALUES FOR DIFFERENT ACTIVITIES HERE
        int pointValue = 0;
        if (split[split.length-1].contains("Theory Lesson"))
            pointValue = 5;
        if (split[split.length-1].contains("Volunteering"))
            pointValue = 10;

        output.println(firstLine);
        while (csvInput.hasNextLine()) {
            String[] inputArr = csvInput.nextLine().split(",");
            inputArr = expandArr(inputArr);
            if (containsID(inputArr[3]))
                inputArr[inputArr.length-1] = pointValue+"";
            else
                inputArr[inputArr.length-1] = ""; //EMPTY CHARACTER
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

    public static String[] expandArr(String[] input) {
        String[] newArr = new String[input.length+1];
        for (int i = 0; i < input.length; i++) {
            newArr[i] = input[i];
        }
        return newArr;
    }
}
