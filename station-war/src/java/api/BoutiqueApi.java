/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import annexe.Produit;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hornetq.utils.json.JSONObject;
import service.BoutiqueService;
import util.CustomHttpResponse;
import util.JsonUtil;

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/boutique/produit")
public class BoutiqueApi extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String recherche = req.getParameter("recherche");
        Produit[] produits = BoutiqueService.rechercherProduitBoutique(recherche);
        Gson gson = new Gson();
        out.print(gson.toJson(produits));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        
        try {
            JSONObject json = JsonUtil.getJSONObject(req);
            
            Produit produit = new Produit();
            produit.setVal(json.getString("Val"));
            produit.setPuAchat(json.getDouble("PuAchat"));
            produit.setPuVente(json.getDouble("PuVente"));
            produit.insererDansBoutique();
            
            CustomHttpResponse chr = new CustomHttpResponse(200, "Succes: Produit bien ajoute");
            out.print(JsonUtil.stringify(chr));
            
        } catch (Exception e) {
            CustomHttpResponse chr = new CustomHttpResponse(500, "Erreur: " + e.getMessage());
            out.print(JsonUtil.stringify(chr));
            
            e.printStackTrace();
        }
        out.close();
    }
}
