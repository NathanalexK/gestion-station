/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;

/**
 *
 * @author Nathanalex
 */
public class JsonUtil {
    public static JSONObject getJSONObject(HttpServletRequest req) throws IOException, JSONException {
        StringBuilder jsonBuffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        
        while((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        System.out.println(jsonBuffer.toString());
        
        return new JSONObject(jsonBuffer.toString());
    }
    
    public static String stringify(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
