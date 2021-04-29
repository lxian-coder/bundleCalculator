package darcyxian.bundlesCalculator.dataModel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Darcy Xian  27/4/21  9:43 am      Bundle_Calculator
 */
@Getter
@Setter
@Component
public class DataModel {
    String formatName;
    String formatCode;
    HashMap<String, String> bundles = new HashMap<>();
}
