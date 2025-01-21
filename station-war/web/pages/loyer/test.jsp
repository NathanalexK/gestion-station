<%@ page import="affichage.Carte" %><%
    Carte carte = new Carte();

//    carte.set
%>

<div class="content-wrapper">
    <div class="content-header">
        <h1>Test</h1>
    </div>

    <div class="content">
        <%=carte.getHtml()%>
    </div>
</div>

