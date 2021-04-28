package darcyxian.bundle_calculator.grabDataFromConsole;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Darcy Xian  27/4/21  2:30 pm      Bundle_Calculator
 */
@Component
@AllArgsConstructor
public class GrabDataFromConsole {

    public List<String> scanDataFromConsole() {
        System.out.println("Please input Data (case insensitive and double click Enter to finish input): ");
        Scanner s = new Scanner(System.in);
        // i is used to prevent user from quiting the program by type enter key at first
        int i = 0;
        String inputString = " ";
        while (true) {
            i++;
            String line2 = s.nextLine().toLowerCase();
            inputString += " " + line2;
            // if newline is empty and it is not the first data inputted, end the input function.
            if (line2.length() == 0 && i != 1) break;
        }
        // convert string to a cleanlist
        List<String> inputList = stringToList(inputString);

        return inputList;
    }

    public List<String> stringToList(String s) {
        // convert string to arraylist
        List<String> inputData = new ArrayList<String>(Arrays.asList(s.split(" ")));
        // remove empty elements from the list
        inputData.removeAll(Arrays.asList(""));
        // System.out.println("inptData : " + inputData);
        return inputData;
    }

}
