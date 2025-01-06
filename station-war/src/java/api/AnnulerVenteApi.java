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
import util.CustomHttpResponse;
import util.JsonUtil;
import utilitaire.UtilDB;
import vente.VenteDetails;

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/boutique/annuler-vente")
public class AnnulerVenteApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CustomHttpResponse chr = null;
        
        try (Connection conn = new UtilDB().GetConn()){
            String idVd = req.getParameter("idVD");
            double qte = Double.parseDouble(req.getParameter("qte"));
            VenteDetails vd = VenteDetails.rechercherParId(idVd, conn) ;
            
            vd.annuler(conn, qte);
            chr = new CustomHttpResponse(200, "Success");
            
            
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
            chr = new CustomHttpResponse(500, "Erreur: " + e.getMessage());
        }
        out.print(JsonUtil.stringify(chr));
        out.close();
    }
    
}
