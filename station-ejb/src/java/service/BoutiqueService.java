/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import annexe.Produit;
import bean.CGenUtil;
import java.sql.Connection;
import utilitaire.UtilDB;

/**
 *
 * @author Nathanalex
 */
public class BoutiqueService {
    public static void insererProduitBoutique(String nom, double puAchat, double puVente) throws Exception {
        Connection conn = new UtilDB().GetConn();
        Produit produit = new Produit();
        produit.setVal(nom);
        produit.setPuAchat(puAchat);
        produit.setPuVente(puVente);
        produit.insererDansBoutique();
        return;
    }
    
    public static Produit[] rechercherProduitBoutique(String cle) {
        cle = cle != null ? cle: "";
        Produit[] produits = new Produit[0];
        
        try (Connection conn = new UtilDB().GetConn()) {
            produits = (Produit[])CGenUtil.rechercher(new Produit(), null, null, conn, " AND IDTYPEPRODUIT LIKE 'TPR000081' AND VAL LIKE '%" + cle + "%'");
        } catch (Exception e) {
            System.err.println("Aucun produit trouvee pour la cle: " + cle);
            e.printStackTrace();
        }
        return produits;
    }
    
}
