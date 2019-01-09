package сustomized;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;

public class ActionsCustomCore {

    public static HashMap<String, HashMap<String, Object>> readFromJson(String path){

        try (Reader reader = new FileReader(path)) {

            return new Gson().fromJson(reader, new TypeToken<HashMap<String, HashMap<String, Object>>>(){}.getType());

        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }

}
