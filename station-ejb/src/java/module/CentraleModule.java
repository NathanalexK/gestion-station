/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import annexe.Produit;
import avoir.AvoirFC;
import client.Client;
import java.sql.Connection;
import javax.ejb.Remote;
import prevision.Prevision;

/**
 *
 * @author Nathanalex
 */

@Remote
public interface CentraleModule {
    String gigi();
    
    Client[] rechercherClients(String name, Connection conn) throws Exception;
    void genererAvoir(AvoirFC avoirFC, Connection conn) throws Exception;
    void genererPrevision(Prevision prevision, Connection conn) throws Exception;
    public void insererProduitBoutique(Produit produit) throws Exception;
//    public Produit[] getProd
    
}
