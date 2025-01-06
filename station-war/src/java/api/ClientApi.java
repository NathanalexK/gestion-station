/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import client.Client;
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

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/client")
public class ClientApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Client[] clients = null;
        try (Connection conn = new UtilDB().GetConn()) {
            clients = Client.rechercherClientsNonDivers(conn);
        } catch (Exception e) {
            clients = new Client[0];
            e.printStackTrace();
        }
        out.print(JsonUtil.stringify(clients));
        out.close();
    }
    
}
