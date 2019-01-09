package сustomized;

import com.codeborne.selenide.Screenshots;
import cucumber.api.PickleStepTestStep;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ActionsCustomJUnit {

    public static String screenshot(PickleStepTestStep testStep, String dir) {

        try {

            File file = Screenshots.takeScreenShotAsFile(); //Скриншот

            if(!Files.isDirectory(Paths.get(dir))) //Создаем директорию, если ее нет
                Files.createDirectories(Paths.get(dir));

            FileUtils.copyToDirectory(file, Paths.get(dir).toFile()); //Копируем скриншот в директорию со скриншотами

            String pathToFeature = Paths.get("./").toAbsolutePath().toString().replace("\\.", "")+"/"+testStep.getStepLocation().split(":")[0];
            String pathToScreen = Paths.get(dir).toAbsolutePath()+"\\"+file.getName();


            System.out.println("\n✅ " + testStep.getStepText() + " | file:/" + pathToFeature);
            System.out.println("\uD83D\uDCBE Screenshot: file:\\" + pathToScreen + "\n"); //Выводим

            return pathToScreen;

        } catch (IOException e) { e.printStackTrace(); }

        return null;

    }



    public static void writeToJson(HashMap<String, HashMap<String, Object>> data, String dir, String fileName){

        if(!Files.isDirectory(Paths.get(dir))){ //Создаем директорию, если ее нет

            try {

                Files.createDirectories(Paths.get(dir));

            } catch (IOException e) {
                new Exception("Не удалось создать директорию: " + dir);
                e.printStackTrace();
            }
        }

        String pathToFile = dir + fileName;

        HashMap<String, HashMap<String, Object>> tempData = readFromJson(pathToFile);

        try (Writer writer = new FileWriter(pathToFile)) {

            if(tempData == null) tempData = new HashMap<>();

            for(Map.Entry<String, HashMap<String, Object>> value: data.entrySet())
                    tempData.put(value.getKey(), value.getValue());

            Gson gson = new GsonBuilder().create();
            gson.toJson(tempData, writer);

        } catch (IOException e) {
            new Exception("Не удалось записать данные в файл: " + pathToFile);
            e.printStackTrace();
        }

    }

    public static HashMap<String, HashMap<String, Object>> readFromJson(String path){

        if(!Files.exists(Paths.get(path))) return null;

        try (Reader reader = new FileReader(path)) {

            return new Gson().fromJson(reader, new TypeToken<HashMap<String, HashMap<String, Object>>>(){}.getType());

        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }

}
