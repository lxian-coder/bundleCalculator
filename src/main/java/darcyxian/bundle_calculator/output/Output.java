package darcyxian.bundle_calculator.output;

import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.dataModel.DataModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Darcy Xian  27/4/21  11:28 am      Bundle_Calculator
 */
@Component
@Slf4j
@AllArgsConstructor
public class Output {
  private DataBootstrap dataBootstrap;

  // display the form
    public void displayFormats(){
        List<DataModel> dataModels = dataBootstrap.loadData();
         String  foramtsDisplay = "\nSubmission Format | format code | Bundles \n----------------- | ----------- | -------";
         System.out.println(foramtsDisplay);
         dataModels.forEach(dataModel -> {
             String buddleMoney = "";
             for(Map.Entry<String,String> entry : dataModel.getBundles().entrySet()){
                  buddleMoney += entry.getKey() +" @ " + "$" + entry.getValue()+"    ";
             }
           String s = dataModel.getFormatName()+"             | "+dataModel.getFormatCode()+"         |"+" "+buddleMoney ;
               System.out.println(s);
        });

    }

   // display the wrong message when input check faild
    public void displayWrongMessage(){
        System.out.println("Sorry, the format of your input is not correct.Please follow the format:<Numbers of buddles> <formatcode> ");
        System.out.println("According to the form below, input again.");
        displayFormats();
    }



}
