package darcyxian.bundle_calculator.input;


import darcyxian.bundle_calculator.grabDataFromConsole.GrabDataFromConsole;
import darcyxian.bundle_calculator.inputCheck.InputCheck;
import darcyxian.bundle_calculator.output.Output;
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
public class Input implements ApplicationListener<ContextClosedEvent> {

    private final InputCheck inputCheck;
    private final Output output;
    private final GrabDataFromConsole grabDataFromConsole;

    // grab data inputted by users from console
    public void grabInputData() {

        output.displayFormats();
        while (true) {
            List<String> list = grabDataFromConsole.scanDataFromConsole();
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
