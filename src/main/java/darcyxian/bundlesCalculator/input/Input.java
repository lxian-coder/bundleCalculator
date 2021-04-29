package darcyxian.bundlesCalculator.input;


import darcyxian.bundlesCalculator.inputScanner.InputScanner;
import darcyxian.bundlesCalculator.inputCheck.InputCheck;
import darcyxian.bundlesCalculator.output.Output;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Darcy Xian  26/4/21  10:01 pm      Bundle_Calculator
 */
@Slf4j
@Component
@AllArgsConstructor
public class Input {

    private final InputCheck inputCheck;
    private final Output output;
    private final InputScanner inputScanner;

    // grab data inputted by users from console
    public List<String> grabInputData() {
        List<String> resultList ;
        output.displayFormats();
        while (true) {
            List<String> list = inputScanner.scanDataFromConsole();
            // if check failed, users need to input again, otherwise input will be finished
            resultList = inputCheck.checkTheInputList(list);
            if (resultList != null) break;
        }
       return  resultList;
    }
}