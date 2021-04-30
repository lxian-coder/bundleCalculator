package darcyxian.bundlesCalculator.output;

import darcyxian.bundlesCalculator.bundleFormatsMap.BundleFormatsMap;
import darcyxian.bundlesCalculator.dataBootstrap.DataBootstrap;
import darcyxian.bundlesCalculator.dataModel.DataModel;
import darcyxian.bundlesCalculator.toolsBarn.ToolsBarn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * Darcy Xian  27/4/21  11:28 am      Bundle_Calculator
 */
@Component
@Slf4j
@AllArgsConstructor
public class Output {
    private final DataBootstrap dataBootstrap;
    private final BundleFormatsMap bundleFormatsMap;
    private final ToolsBarn toolsBarn;

    public void displayFormats() {
        List<DataModel> dataModels = dataBootstrap.loadData();
        String foramtsDisplay = "\nSubmission Format | format code | Bundles \n----------------- | ----------- | -------";
        System.out.println(foramtsDisplay);
        dataModels.forEach(dataModel -> {
            String buddleMoney = "";
            for (Map.Entry<String, String> entry : dataModel.getBundles().entrySet()) {
                buddleMoney += entry.getKey() + " @ " + "$" + entry.getValue() + "    ";
            }
            String s = dataModel.getFormatName() + "             | " + dataModel.getFormatCode() + "         |" + " " + buddleMoney;
            System.out.println(s);
        });
    }

    public void displayWrongMessage() {
        System.out.println("Sorry, the format of your input is not correct. A legal order looks like:<Numbers of buddles> <formatcode> ");
        System.out.println("You can check the form below and make an order again.");
        displayFormats();
    }

    public void displayTheFinalResult(Map<String, Map<Integer, Integer>> calculationMap, List<String> inputList) {
       List<String> codes = toolsBarn.createFormatCodeList(inputList);
       List<Integer> posts = toolsBarn.createPostList(inputList);

        int size = codes.size();
        for (int i = 0; i < size; i++) {
            String code = codes.get(i);
            Set<Integer> displayBundles = calculationMap.get(code).keySet();
            displayTheFinalResultHelper(code, displayBundles, calculationMap, posts.get(i));
        }
    }

    public void displayTheFinalResultHelper(String code, Set<Integer> displayBundles, Map<String, Map<Integer, Integer>> calculationMap, Integer post) {
        BigDecimal moneySum = new BigDecimal(0);
        BigDecimal money;

        Iterator<Integer> it = displayBundles.iterator();
        List<String> ss = new ArrayList<>();
        for (int i = 0; i < displayBundles.size(); i++) {
            int bundles = it.next();
            int num = calculationMap.get(code).get(bundles);
            BigDecimal queryMoney = bundleFormatsMap.getBundleMoney(code, String.valueOf(bundles));
            money = BigDecimal.valueOf(num).multiply(queryMoney);
            String s = ("    " + num + " X " + bundles + " $" + money);
            ss.add(s);
            moneySum = moneySum.add(money);
        }
        System.out.println(post + " " + code + " $" + moneySum);
        ss.stream().forEach(e -> System.out.println(e));
    }


}
