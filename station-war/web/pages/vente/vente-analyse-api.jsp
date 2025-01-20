<%@ page import="module.DbUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.google.gson.Gson" %><%--<%@ page import="affichage.PageRecherche" %>--%>
<%--<%@ page import="vente.VenteDetailsLib" %>--%>
<%--<%@ page import="user.UserEJB" %><%--%>
<%--    try {--%>
<%--        VenteDetailsLib mvt = new VenteDetailsLib();--%>
<%--        String nomTable = "VENTE_DETAILS_CPL_2";--%>
<%--        mvt.setNomTable(nomTable);--%>

<%--        String[] colAffiches = request.getParameter("colAffiches").split(",");--%>
<%--        PageRecherche pr = new PageRecherche(mvt, request, new String[]{}, new String[]{}, 0, colAffiches, colAffiches.length);--%>
<%--        String apresWhere = request.getParameter("awhere");--%>
<%--        pr.setTableauAffiche(colAffiches);--%>
<%--        pr.setAWhere(apresWhere);--%>
<%--        pr.setUtilisateur(((UserEJB) session.getAttribute("u")));--%>
<%--        pr.getTableau().setLien(new String[0]);--%>
<%--        pr.getTableau().setColonneLien(new String[]{"id"});--%>
<%--        pr.getTableau().setLibelleAffiche(new String[]{"", "", "", ""});--%>
<%--%>--%>

<%--<%pr.getTableau().getHtml();%>--%>
<%--<%--%>
<%--    } catch (Exception e) {--%>
<%--        e.printStackTrace(response.getWriter());--%>
<%--    }--%>
<%--%>--%>

<%
    String nomTable = "VENTE_DETAILS_CPL_2";
    String[] colAffiche = {"idproduit", "qte", "putotal"};
    if(request.getParameter("colAffiches") != null) {
        colAffiche = request.getParameter("colAffiches").split(",");
    }
    String apresWhere = "";
    if(request.getParameter("apresWhere") != null) {
        apresWhere += request.getParameter("apresWhere");
    }
    List<Map<String, Object>> list = DbUtils.rechercher(nomTable, colAffiche, apresWhere);
    Gson gson = new Gson();
    out.println(gson.toJson(list));
%>
