package darcyxian.bundle_calculator;

import darcyxian.bundle_calculator.buddleFormats.BuddleFormats;
import darcyxian.bundle_calculator.dataBootstrap.DataBootstrap;
import darcyxian.bundle_calculator.dataModel.DataModel;
import darcyxian.bundle_calculator.input.Input;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BundleCalculatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(BundleCalculatorApplication.class, args);

//      Input a = new Input();
//      a.grabInputData();
        DataBootstrap dataBootstrap = new DataBootstrap();
        DataModel dataModel = new DataModel();
        List<DataModel> test = dataBootstrap.loadData(dataModel);
        BuddleFormats buddleFormats = new BuddleFormats();
        buddleFormats.createFormatsMap(test);
    }

}
