package сustomized;

import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;

import java.util.HashMap;

public class PassedActions extends ActionsCustomJUnit {

    public static final String DIRTEMP = "temp/";
    public static final String FILENAMEJSON = "juco.json";
    public static final String PATHREPORTSCREENSHOT = DIRTEMP + "screenshot/";


    //Запустить кастомные действия, после выполнения теста на статус — PASSED
    public static void startCustomAction(PickleStepTestStep testStep, Result result){

        String screenPath = screenshot(testStep, PATHREPORTSCREENSHOT);

    //Сохраняем данные в Json
        HashMap<String, HashMap<String, Object>> data = new HashMap<>();

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("screenshotPath", screenPath);

        data.put(testStep.getStepText(), dataMap);

        writeToJson(data, DIRTEMP, FILENAMEJSON);

    }




}
