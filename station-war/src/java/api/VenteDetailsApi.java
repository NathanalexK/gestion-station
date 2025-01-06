/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsonUtil;
import utilitaire.UtilDB;
import vente.Vente;
import vente.VenteDetails;

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/boutique/vente-details")
public class VenteDetailsApi extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        VenteDetails[] vds = new VenteDetails[0];
        Vente vente = new Vente();
        
        try (Connection conn = new UtilDB().GetConn()){
            String idVente = req.getParameter("idVente");
            if(idVente == null) {
                throw new Exception("No param idVente provided from request: VenteDetailsApi.doGet(HttpServletRequest, HttpServletResponse)");
            }
            System.out.println("idVente: " + idVente);
            
            
            vente.setId(idVente);
            vente.rechercherParId(conn);
            vds = vente.getVenteDetails(conn);
            vente.setVenteDetails(vds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.print(JsonUtil.stringify(vente));
        out.close();
    }
    
    
    
}
