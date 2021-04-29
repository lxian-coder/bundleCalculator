package darcyxian.bundle_calculator.buddleFormatsMap;


import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.dataModel.DataModel;
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

    // convert the datamodels list to Hashmap which includs the key: foramtCode and the value: buddle map
    public HashMap<String, HashMap<String, String>> createFormatsMap() {
        List<DataModel> dataModels = dataBootstrap.loadData();
        HashMap<String, HashMap<String, String>> formatsMap = new HashMap<String, HashMap<String, String>>();
        dataModels.forEach(dataModel -> {
            formatsMap.put(dataModel.getFormatCode().toLowerCase(), dataModel.getBundles());
        });

        return formatsMap;
    }
    // give a format code, this method can return all bundles(Integer type) in descending.
    public Set<Integer> getDescendingBundles(String code) {
        // get all bundle from budleFormats map
        Set<Integer> bundles = createFormatsMap().get(code).keySet()
                .stream()
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toSet());
        // revers order of bundle
        SortedSet<Integer> descendingBundles = new TreeSet<Integer>(Collections.reverseOrder());
        descendingBundles.addAll(bundles);
        return descendingBundles;
    }

    // given a format code and a bundles number, this method will return money number(BigDecimal).
    public BigDecimal getBundleMoney(String code, String bundle) {

        String result = createFormatsMap().get(code).get(bundle);
        BigDecimal money = new BigDecimal(result);

        return money;
    }


}
