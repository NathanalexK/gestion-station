<%@ page import="affichage.ElementCarte" %><%
    "<div class=\"row\">\n<div class=\"col-md-12\">\t\t\t<div id=\"mapid\"></div></div>\t\n\t\t</div>\n<script>\n\tvar map = L.map('mapid').setView([-18.9987355,47.5301833], 15);\n  var circle=[];var marker=[];var polyline=[];\n\tvar tiles=L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {\n\tattribution: 'Map data &copy; <a href=\"https://www.openstreetmap.org/\">OpenStreetMap</a> contributors, <a href=\"https://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, Imagery Â© <a href=\"https://www.mapbox.com/\">Mapbox</a>',\n\tmaxZoom: 18,\n\tid: 'mapbox.streets',\n\taccessToken: 'pk.eyJ1IjoibWV2YTEyNTgiLCJhIjoiY2p6djRudzU2MDB6bTNwbXI0M3BlcXNjMyJ9.Knx_B_WAZUJyu8Mzv97jag'\n\t}).addTo(map);\n\tvar osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',\n            osmAttrib = '&copy; <a href=\"http://openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n            osm = L.tileLayer(osmUrl, { maxZoom: 18, attribution: osmAttrib }),\n            drawnItems = L.featureGroup().addTo(map);\n    L.control.layers({\n        'osm': osm.addTo(map),\n        \"google\": L.tileLayer('http://www.google.cn/maps/vt?lyrs=s@189&gl=cn&x={x}&y={y}&z={z}', {\n            attribution: 'google'\n        })\n    }, { 'drawlayer': drawnItems }, { position: 'topleft', collapsed: false }).addTo(map);\n\n</script>";

%>

<div class="content-wrapper">
    <div class="content-header">
        <h1>Test</h1>
    </div>
</div>

<%--<h1>Test Works</h1>--%>