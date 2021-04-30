package darcyxian.bundlesCalculator.bundleFormatsMap;


import darcyxian.bundlesCalculator.dataBootstrap.DataBootstrap;
import darcyxian.bundlesCalculator.dataModel.DataModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Darcy Xian  27/4/21  8:59 am      Bundle_Calculator
 */

@Component
@Slf4j
@AllArgsConstructor
public class BundleFormatsMap {
    DataBootstrap dataBootstrap;

    public HashMap<String, HashMap<String, String>> createFormatsMap() {
        List<DataModel> dataModels = dataBootstrap.loadData();
        HashMap<String, HashMap<String, String>> formatsMap = new HashMap<String, HashMap<String, String>>();
        dataModels.forEach(dataModel -> {
            formatsMap.put(dataModel.getFormatCode().toLowerCase(), dataModel.getBundles());
        });
        return formatsMap;
    }
    public Set<Integer> getDescendingBundles(String code) {
        Set<Integer> bundles = createFormatsMap().get(code).keySet()
                .stream()
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toSet());
        SortedSet<Integer> descendingBundles = new TreeSet<Integer>(Collections.reverseOrder());
        descendingBundles.addAll(bundles);
        return descendingBundles;
    }

    public BigDecimal getBundleMoney(String code, String bundle) {

        String result = createFormatsMap().get(code).get(bundle);
        BigDecimal money = new BigDecimal(result);

        return money;
    }


}
