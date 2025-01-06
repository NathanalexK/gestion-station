/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import annexe.MyStock;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsonUtil;

/**
 *
 * @author Nathanalex
 */
@WebServlet("/api/boutique/stock")
public class StockApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {
             MyStock[] stocks = MyStock.rechercherTout();
             out.print(JsonUtil.stringify(stocks));
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
    }
    
}
