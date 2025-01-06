/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

//import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;

/**
 *
 * @author Nathanalex
 */
public class HttpJson {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private JSONObject jsonObject;
    
    public HttpJson(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException {
        this.req = req;
        this.resp = resp;
        this.jsonObject = JsonUtil.getJSONObject(req);
    }
    
    public void write() throws IOException{
//        PrintWriter
    }
    
    
}
