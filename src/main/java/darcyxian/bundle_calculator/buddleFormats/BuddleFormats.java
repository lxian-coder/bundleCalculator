package darcyxian.bundle_calculator.buddleFormats;



import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.dataModel.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Darcy Xian  27/4/21  8:59 am      Bundle_Calculator
 */

@Component
@Slf4j
public class BuddleFormats {

     // convert the datamodels list to Hashmap which includs the key: foramtCode and the value: buddle map
    public HashMap<String,HashMap<String,String>> createFormatsMap(List<DataModel> dataModels){

        HashMap<String,HashMap<String,String>> formatsMap = new HashMap<String,HashMap<String,String>>();
        log.info("datamodels size :"+dataModels.size());
        dataModels.forEach(dataModel->{
             formatsMap.put(dataModel.getFormatCode(),dataModel.getBundles());
         });
         log.info("formatsMap:"+formatsMap.size());

         return formatsMap;
    }

}
