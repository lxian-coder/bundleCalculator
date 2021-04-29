package darcyxian.bundle_calculator.inputCheck;

import darcyxian.bundle_calculator.buddleFormats.BundleFormatsMap;
import darcyxian.bundle_calculator.calculator.Calculator;
import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.output.Output;
import darcyxian.bundle_calculator.toolsBarn.ToolsBarn;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Darcy Xian  27/4/21  12:18 pm      Bundle_Calculator
 */
@Component
@AllArgsConstructor
public class InputCheck {
    private  DataBootstrap dataBootstrap;
    private  Output output;
    private  BundleFormatsMap bundleFormatsMap;
    private  Calculator calculator;
    private ToolsBarn toolsBarn;


    public boolean checkTheInputList(List<String> list) {
        int listSize = list.size();
        int dataSize = dataBootstrap.loadData().size();
        // if input data list can not be divided by 2, or it is bigger than dataSize*2
        // (each entry has 2 elements inludes bundles and formatcode), wrong message be displayed
        if (listSize % 2 != 0 || listSize > dataSize * 2) {
            output.displayWrongMessage();
            return false;
        }
        // check if the input content is correct
        for (int i = 0; i < listSize; i++) {
            // check if the string is number or not
            if (i % 2 == 0) {
                if (!toolsBarn.isStrToNum(list.get(i))) {
                    System.out.println('"' + list.get(i) + '"' + " is not a number! Please check and input again!");
                    return false;
                }
                // check if the string is the format code or not
            } else if (!bundleFormatsMap.createFormatsMap().containsKey(list.get(i))) {
                System.out.println('"' + list.get(i) + '"' + " is Not a format code! Please check and input again!");
                return false;
            }
        }
        calculator.calculateTheInput(list);
        return true;
    }


}


