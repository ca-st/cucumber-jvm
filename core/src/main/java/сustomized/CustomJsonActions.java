package сustomized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class CustomJsonActions extends ActionsCustomCore {

    public static final String TEMPJSONPATH = "temp/juco.json";

    public static HashMap<String, Object> getData(String stepName){
        return readFromJson(TEMPJSONPATH).get(stepName);
    }

    public static Object getValue(HashMap<String, Object> hashMap, String key){
        if(hashMap == null) return null;
        if(hashMap.size() == 0) return null;
        if(!hashMap.containsKey(key)) return null;

        return hashMap.get(key);
    }


    public static void deleteTempFile(){
        try {
            Files.delete(Paths.get(TEMPJSONPATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
