package darcyxian.bundle_calculator.input;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Darcy Xian  26/4/21  10:01 pm      Bundle_Calculator
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Input {


     public void grabInputData(){
         System.out.println("Please input Data (double click Enter to finish input): ");
         Scanner s = new Scanner(System.in);
         // i is used to prevent user from quiting the program by type enter key at first
         int  i = 0;
         String inputString = " ";
         while (true){
             i++;
             String line2 = s.nextLine();
             inputString += " "+ line2;
             // if newline is empty and it is not the first data inputted, end the input function.
             if(line2.length() == 0 && i != 1) break;
         }
         // convert string to a cleanlist
         stringToList(inputString);


     }

     public List<String>  stringToList(String s){
         // convert string to arraylist
         List<String> inputData = new ArrayList<String>(Arrays.asList(s.split(" ")));
          // remove empty elements from the list
         inputData.removeAll(Arrays.asList(""));
         System.out.println("inptData : " + inputData);
         return inputData ;
     }


}
