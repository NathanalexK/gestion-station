/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import annexe.MyAchat;
import annexe.MyAchatFille;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hornetq.utils.json.JSONArray;
import org.hornetq.utils.json.JSONObject;
import util.CustomHttpResponse;
import util.JsonUtil;

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/boutique/achat")
public class AchatApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");

        try {
            JSONObject json = JsonUtil.getJSONObject(req);
            JSONArray array = json.getJSONArray("Filles");
            
            MyAchatFille[] filles = new MyAchatFille[array.length()];
            for(int i = 0; i < array.length(); i++) {
                MyAchatFille fille = new MyAchatFille();
                fille.setIdProduit(array.getJSONObject(i).getString("IdProduit"));
                fille.setQte(array.getJSONObject(i).getDouble("Qte"));
                filles[i] = fille;
            }
            
            MyAchat achat = new MyAchat();
            achat.setFilles(filles);
            achat.effectuerUnAchat();
            
            CustomHttpResponse chr = new CustomHttpResponse(200, "Succes: Achat bien effectue");
            out.print(JsonUtil.stringify(chr));
            
        } catch (Exception e) {
            CustomHttpResponse chr = new CustomHttpResponse(500, "Erreur: " + e.getMessage());
            out.print(JsonUtil.stringify(chr));
            e.printStackTrace();
        }
        out.close();
    }
    
}
