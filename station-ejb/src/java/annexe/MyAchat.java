/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package annexe;

import faturefournisseur.FactureFournisseur;
import faturefournisseur.FactureFournisseurDetails;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import stock.MvtStock;
import stock.MvtStockFille;
import utilitaire.UtilDB;
import utils.ConstanteStation;

/**
 *
 * @author Nathanalex
 */
public class MyAchat {
    private Date  daty;
    private MyAchatFille[] filles;
    

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public MyAchatFille[] getFilles() {
        return filles;
    }

    public void setFilles(MyAchatFille[] filles) {
        this.filles = filles;
    }
    
    
    
    public void effectuerUnAchat() throws Exception {
        Connection connection = new UtilDB().GetConn();
        this.effectuerUnAchat(connection);
        connection.commit();
        connection.close();
    }
    
    public FactureFournisseur genererFacture(Connection conn) throws Exception{
        if(this.getFilles().length == 0) {
            throw new Exception("Impossible de generer une factue pour l'achat: Aucun produit");
        }
        
        FactureFournisseur ff = new FactureFournisseur();
        ff.setDaty(Date.valueOf(LocalDate.now()));
        ff.setDesignation("Achat boutique");
        ff.createObject("1060", conn);

        FactureFournisseurDetails[] ffds = new FactureFournisseurDetails[this.getFilles().length];
        for(int i = 0; i < this.getFilles().length; i++) {
            MyAchatFille fille = this.getFilles()[i];
            Produit produit = fille.getProduit(conn);

            FactureFournisseurDetails ffd = new FactureFournisseurDetails();
            ffd.setIdFactureFournisseur(ff.getId());
            ffd.setIdProduit(fille.getIdProduit());
            ffd.setPu(produit.getPuAchat());
            ffd.setQte(fille.getQte());
            ffd.createObject("1060", conn);
            ffds[i] = ffd;
        }
        
        ff.setFille(ffds);
        return ff;
    }
    
    public MvtStock ajouterEnStock(Connection conn) throws Exception{
        if(this.getFilles().length == 0) {
            throw new Exception("Impossible d'ajouter en stock pour l'achat: Aucun produit");
        }
        
        MvtStock mvtStock = new MvtStock();
        mvtStock.setIdMagasin("CV000063");
        mvtStock.setIdVente(null);
        mvtStock.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKENTREE);
        mvtStock.setDaty(Date.valueOf(LocalDate.now()));
        mvtStock.setDesignation("Mouvement de Stock");
        mvtStock.createObject("1060", conn);

        MvtStockFille[] mvtStockFilles = new MvtStockFille[this.getFilles().length];
        for(int i = 0; i < this.getFilles().length; i++) {
            MyAchatFille fille = this.getFilles()[i];
            MvtStockFille mvtStockFille = new MvtStockFille();
            mvtStockFille.setIdMvtStock(mvtStock.getId());
            mvtStockFille.setIdProduit(fille.getIdProduit());
            mvtStockFille.setEntree(fille.getQte());
            mvtStockFille.setSortie(0);
            mvtStockFille.createObject("1060", conn);
            mvtStockFilles[i] = mvtStockFille;
        }
        
        mvtStock.setFille(mvtStockFilles);
        return mvtStock;
    }
    
    public void effectuerUnAchat(Connection conn) throws Exception {
        if(conn == null) throw new Exception("Connection is null in MyAchat.effectuerUnAchat(Connection)");
        try {
            conn.setAutoCommit(false);
            this.genererFacture(conn);
            this.ajouterEnStock(conn);
            
//            conn.setAutoCommit(false);
//            FactureFournisseur ff = new FactureFournisseur();
//            ff.setDaty(Date.valueOf(LocalDate.now()));
//            ff.setDesignation("Achat boutique");
//            ff.createObject("1060", conn);
//            
//            for(int i = 0; i < this.getFilles().length; i++) {
//                MyAchatFille fille = this.getFilles()[i];
//                Produit produit = fille.getProduit(conn);
//                
//                FactureFournisseurDetails ffd = new FactureFournisseurDetails();
//                ffd.setIdFactureFournisseur(ff.getId());
//                ffd.setIdProduit(fille.getIdProduit());
//                ffd.setPu(produit.getPuAchat());
//                ffd.setQte(fille.getQte());
//                ffd.createObject("1060", conn);
//            }
//            
//            MvtStock mvtStock = new MvtStock();
//            mvtStock.setIdMagasin("CV000063");
//            mvtStock.setIdVente(null);
//            mvtStock.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKENTREE);
//            mvtStock.setDaty(Date.valueOf(LocalDate.now()));
//            mvtStock.setDesignation("Mouvement de Stock");
//            mvtStock.createObject("1060", conn);
//
//            for(int i = 0; i < this.getFilles().length; i++) {
//                MyAchatFille fille = this.getFilles()[i];
//                MvtStockFille mvtStockFille = new MvtStockFille();
//                mvtStockFille.setIdMvtStock(mvtStock.getId());
//                mvtStockFille.setIdProduit(fille.getIdProduit());
//                mvtStockFille.setEntree(fille.getQte());
//                mvtStockFille.setSortie(0);
//                mvtStockFille.createObject("1060", conn);
//            }
            
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            throw e;
        }
        
    }
    
    
}
