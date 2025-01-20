package api;

import com.google.gson.Gson;
import module.DbUtils;
import utilitaire.UtilDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@WebServlet("/api/vente-analyse")
public class AnalyseVenteApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String nomTable = "VENTE_DETAILS_CPL_2";
            String[] colAffiche = {"idproduitlib", "qte", "putotal"};
            if (request.getParameter("colAffiches") != null) {
                colAffiche = request.getParameter("colAffiches").split(",");
            }
            String apresWhere = "";
            if (request.getParameter("apresWhere") != null) {
                apresWhere += request.getParameter("apresWhere");
            }
            List<Map<String, Object>> list = null;
            Connection connection = new UtilDB().GetConn();
            list = DbUtils.rechercher(apresWhere, connection);
            connection.close();
            Gson gson = new Gson();
            out.print(gson.toJson(list));
            out.close();

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
