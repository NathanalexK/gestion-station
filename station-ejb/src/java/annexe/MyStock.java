/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package annexe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import java.sql.Connection;
import utilitaire.UtilDB;

/**
 *
 * @author Nathanalex
 */
public class MyStock extends ClassMAPTable{
    private String idProduit, val;
    private double entree, sortie, reste, puVente;
    
    public MyStock() {
        setNomTable("MYSTOCK");
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public double getPuVente() {
        return puVente;
    }

    public void setPuVente(double puVente) {
        this.puVente = puVente;
    }
    
   
    @Override
    public String getTuppleID() {
        return idProduit;
    }

    @Override
    public String getAttributIDName() {
        return "IDPRODUIT";
    }
    
    public static MyStock[] rechercherTout() throws Exception{
        Connection conn = new UtilDB().GetConn();
        MyStock[] stocks = rechercherTout(conn);
        conn.close();
        return stocks;
    }
    
    public static MyStock[] rechercherTout(Connection  connection) throws Exception {
        if(connection == null) throw new Exception("Connection is null at MyStock.rechercher(Connection)");
        return (MyStock[]) CGenUtil.rechercher(new MyStock(), null, null, connection, " ");
    }
    
    public static MyStock rechercherParIdProduit(String idProduit, Connection conn) throws Exception {
        if(idProduit == null || idProduit.trim().isEmpty()) throw new Exception("L'id du produit est requis!");
        if(conn == null) throw new Exception("Connection is null at MyStock.rechercherParIdProduit(String, Connection)");
        MyStock[] stocks = (MyStock[])CGenUtil.rechercher(new MyStock(), null, null, conn, " AND IDPRODUIT LIKE '" + idProduit + "'");
        
        if(stocks.length == 0) {
            MyStock stock = new MyStock();
            Produit produit = Produit.rechercherProduitParId(idProduit, conn);
            stock.setIdProduit(idProduit);
            stock.setVal(produit.getVal());
            stock.setEntree(0);
            stock.setSortie(0);
            stock.setReste(0);
            stock.setPuVente(produit.getPuVente());
            return stock;
        }

        return stocks[0];
    }
    
//    public boolean estSuffisant(double qte) {
//        
//    }
}
