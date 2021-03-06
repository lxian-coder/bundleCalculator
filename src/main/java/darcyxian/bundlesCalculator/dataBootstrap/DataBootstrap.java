package darcyxian.bundlesCalculator.dataBootstrap;

import darcyxian.bundlesCalculator.dataModel.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Darcy Xian  27/4/21  9:47 am      Bundle_Calculator
 */
@Component
@Slf4j
public class DataBootstrap {

    public List<DataModel> loadData() {
        List<DataModel> dataModels = new ArrayList<>();
        DataModel image = new DataModel();
        image.setFormatName("Image");
        image.setFormatCode("Img");
        image.getBundles().put("5", "450");
        image.getBundles().put("10", "800");
        dataModels.add(image);

        DataModel audio = new DataModel();
        audio.setFormatName("Audio");
        audio.setFormatCode("Flac");
        audio.getBundles().put("3", "427.50");
        audio.getBundles().put("6", "810");
        audio.getBundles().put("9", "1147.50");
        dataModels.add(audio);

        DataModel video = new DataModel();
        video.setFormatName("Video");
        video.setFormatCode("Vid");
        video.getBundles().put("3", "570");
        video.getBundles().put("5", "900");
        video.getBundles().put("9", "1530");
        dataModels.add(video);

        DataModel test = new DataModel();
        test.setFormatName("Test");
        test.setFormatCode("test");
        test.getBundles().put("3", "100.50");
        test.getBundles().put("5", "210");
        test.getBundles().put("10", "557.50");
        dataModels.add(test);

        return dataModels;
    }


}
