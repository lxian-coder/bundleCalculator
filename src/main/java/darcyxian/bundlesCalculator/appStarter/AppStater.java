package darcyxian.bundlesCalculator.appStarter;

import darcyxian.bundlesCalculator.calculator.Calculator;
import darcyxian.bundlesCalculator.input.Input;
import darcyxian.bundlesCalculator.output.Output;
import darcyxian.bundlesCalculator.toolsBarn.ToolsBarn;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Darcy Xian  30/4/21  9:05 am      Bundle_Calculator
 */
@Component
@AllArgsConstructor
public class AppStater implements ApplicationListener<ContextClosedEvent> {
    private Input input;
    private Calculator calculator;
    private Output output;
    private ToolsBarn toolsBarn;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

        List<String> inputList = input.grabInputData();
        Map<String, Map<Integer, Integer>> finalResultMap = calculator.getCalculationResultMap(inputList);
        output.displayTheFinalResult(finalResultMap, inputList);

    }
}


