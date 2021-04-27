package darcyxian.bundle_calculator.output;

import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.dataModel.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Darcy Xian  27/4/21  11:28 am      Bundle_Calculator
 */
@Component
@Slf4j
public class Output {


    public void displayFormats(List<DataModel> dataModels){
         String  foramtsDisplay = "\nSubmission Format | format code | Bundles \n----------------- | ----------- | -------";
         System.out.println(foramtsDisplay);
         dataModels.forEach(dataModel -> {
             String buddleMoney = "";
             for(Map.Entry<String,String> entry : dataModel.getBundles().entrySet()){
                  buddleMoney += entry.getKey() + " $" + entry.getValue()+"    ";
             }
           String s = dataModel.getFormatName()+"             | "+dataModel.getFormatCode()+"         |"+" "+buddleMoney ;
               System.out.println(s);
        });

        System.out.println();

    }


}
