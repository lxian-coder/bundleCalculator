package darcyxian.bundlesCalculator.toolsBarn;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Integer>  createPostList(List<String> inputList){
        List<Integer> orderedPosts = inputList.stream()
                .filter(element -> isStrToNum(element) == true)
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        return orderedPosts;
    }
    public List<String> createFormatCodeList(List<String> inputList){
        List<String> orderedFormatCodes = inputList.stream()
                .filter(element -> isStrToNum(element) == false)
                .collect(Collectors.toList());
        return orderedFormatCodes;
    }
}
