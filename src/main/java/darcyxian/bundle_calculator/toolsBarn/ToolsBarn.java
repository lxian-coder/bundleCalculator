package darcyxian.bundle_calculator.toolsBarn;

import org.springframework.stereotype.Component;

/**
 * Darcy Xian  29/4/21  8:18 pm      Bundle_Calculator
 */
@Component
public class ToolsBarn {

    public boolean isStrToNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
