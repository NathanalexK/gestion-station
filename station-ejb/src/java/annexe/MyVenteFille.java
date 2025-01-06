/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package annexe;

import bean.CGenUtil;
import java.sql.Connection;

/**
 *
 * @author Nathanalex
 */
public class MyVenteFille {
    private String id, idMvtStock, idProduit;
    private double qte;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMvtStock() {
        return idMvtStock;
    }

    public void setIdMvtStock(String idMvtStock) {
        this.idMvtStock = idMvtStock;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }
    
    public Produit getProduit(Connection conn) throws Exception {
        Produit[] produits = (Produit[])CGenUtil.rechercher(new Produit(), null, null, conn, " AND ID LIKE '" + idProduit + "'");
        if(produits.length == 0) {
            throw new Exception("Aucun produit trouvee ayant: " + idProduit);
        }
        return produits[0];
    }
}
