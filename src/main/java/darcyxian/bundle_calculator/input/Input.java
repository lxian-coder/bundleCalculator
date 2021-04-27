package darcyxian.bundle_calculator.input;


import darcyxian.bundle_calculator.grabDataFromConsole.GrabDataFromConsole;
import darcyxian.bundle_calculator.inputCheck.InputCheck;
import darcyxian.bundle_calculator.output.Output;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.io.ObjectInputValidation;
import java.math.BigDecimal;
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
@AllArgsConstructor
public class Input implements ApplicationListener<ContextClosedEvent> {

  private InputCheck inputCheck;
  private Output output;
  private GrabDataFromConsole grabDataFromConsole;

    // grab data inputted by users from console
     public void grabInputData(){

         System.out.println(new BigDecimal("1"));
         output.displayFormats();
        while (true){
            List<String> list =grabDataFromConsole.scanDataFromConsole();
           // if check faild, users need to input again, otherwise input will be finished
           if (inputCheck.checkTheInputList(list)) break;
        }

     }
    // convert string to a list eliminated empty elements;


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        grabInputData();
    }
}
