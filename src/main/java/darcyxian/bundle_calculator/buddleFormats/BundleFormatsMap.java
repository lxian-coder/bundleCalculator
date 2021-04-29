package darcyxian.bundle_calculator.buddleFormats;


import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.dataModel.DataModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

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

}
