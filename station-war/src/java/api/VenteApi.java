/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import annexe.MyAchat;
import annexe.MyAchatFille;
import annexe.MyVente;
import annexe.MyVenteFille;
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
import vente.Vente;

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/boutique/vente")
public class VenteApi extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        Vente[] ventes = new Vente[0];
        try {
            ventes = Vente.rechercherVenteBoutique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(JsonUtil.stringify(ventes));
        out.close();
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        CustomHttpResponse chr = null; 

        try {
            JSONObject json = JsonUtil.getJSONObject(req);
            JSONArray array = json.getJSONArray("Filles");
            
            MyVenteFille[] filles = new MyVenteFille[array.length()];
            for(int i = 0; i < array.length(); i++) {
                MyVenteFille fille = new MyVenteFille();
                fille.setIdProduit(array.getJSONObject(i).getString("IdProduit"));
                fille.setQte(array.getJSONObject(i).getDouble("Qte"));
                System.out.println("P API: " + fille.getIdProduit());
                filles[i] = fille;
            }
            
            MyVente vente = new MyVente();
            boolean isDirect = json.getBoolean("IsDirect");
            
            if(isDirect) {
                vente.setIsDirect(isDirect);
                vente.setIdClient(json.getString("IdClient"));
            } else {
                vente.setIsDirect(false);
                vente.setIdClient(json.getString("IdClient"));
            }
            
            vente.setFilles(filles);
            vente.effectuerUneVente();
            chr = new CustomHttpResponse(200, "Success: Vente effectue");
        } catch (Exception e) {
            e.printStackTrace();
            chr = new CustomHttpResponse(500, "Erreur: " + e.getMessage());
        }
        out.print(JsonUtil.stringify(chr));
        out.close();
    }
    
}
