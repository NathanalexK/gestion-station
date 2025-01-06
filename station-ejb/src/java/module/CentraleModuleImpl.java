/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import annexe.Produit;
import avoir.AvoirFC;
import bean.CGenUtil;
import client.Client;
import java.sql.Connection;
import javax.ejb.Stateful;
import prevision.Prevision;
import utilitaire.UtilDB;

/**
 *
 * @author Nathanalex
 */
@Stateful
public class CentraleModuleImpl implements CentraleModule {

    @Override
    public String gigi() {
        return "gigi works";
    }

    @Override
    public Client[] rechercherClients(String nom, Connection conn) throws Exception
    {
        boolean hasConn = true;
        if(conn == null)
        {
            hasConn = false;
            conn = new UtilDB().GetConn();
        }
        Client[] clients = (Client[])CGenUtil.rechercher(new Client(), null, null, conn, " AND NOM like '%" + nom + "%'");
        if(!hasConn) 
        {
            conn.close();
        }
        return clients;
    }

    @Override
    public void genererAvoir(AvoirFC avoirFC, Connection conn) throws Exception {
        boolean hasConn = true;
        
        if(conn != null) {
            conn = new UtilDB().GetConn();
            hasConn = false;
        }
        
//        return;
        
        if(!hasConn) {
            conn.close();
        }
        
        return;
    }

    @Override
    public void genererPrevision(Prevision prevision, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void insererProduitBoutique(Produit produit) throws Exception {
        Connection conn = new UtilDB().GetConn();
//        produit.setIsBoutique(1);
        produit.createObject("1060", conn);
        conn.close();
        return;
    }
    
    
    
}
