/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package annexe;

import caisse.MvtCaisse;
import faturefournisseur.FactureFournisseur;
import faturefournisseur.FactureFournisseurDetails;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import stock.MvtStock;
import stock.MvtStockFille;
import utilitaire.UtilDB;
import utils.ConstanteStation;
import vente.Vente;
import vente.VenteDetails;

/**
 *
 * @author Nathanalex
 */
public class MyVente {
    private Date daty;
    private MyVenteFille[] filles;
    private boolean isDirect = true;
    private String idClient;
//    private double total = 0.00;
    
    public MyVente() {
        
    }
    
    public MyVente(Vente vente) {
        this.setDaty(vente.getDaty());
//        VenteDetails[] vds = vente.get
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    
    
    

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date date) {
        this.daty = date;
    }

    public MyVenteFille[] getFilles() {
        return filles;
    }

    public void setFilles(MyVenteFille[] filles) {
        double total = 0.00;
        for(MyVenteFille fille: filles) {
//            total += fille.ge
        }
        
        this.filles = filles;
    }

    public boolean isIsDirect() {
        return isDirect;
    }

    public void setIsDirect(boolean isDirect) {
        this.isDirect = isDirect;
    }
    
    
    
//    public static V rechercherToutVente(Connection conn) {
//        
//    }
    
    public void peuxEffectuerUneVente(VenteDetails vd) {
        
    }
    
    
    public void effectuerUneVente() throws Exception {
        Connection connection = new UtilDB().GetConn();
        this.effectuerUneVente(connection);
        connection.commit();
        connection.close();
    }
    
    public void effectuerUneVente(Connection conn) throws Exception {
        if(conn == null) throw new Exception("Connection is null in MyAchat.effectuerUneVente(Connection)");
        try {
            System.out.println("Effectuation de la vente");
//            conn.setAutoCommit(false);
            
            String dateVenteStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm"));
            Vente vente = new Vente();
            vente.setDesignation("Vente du " + dateVenteStr);
            vente.setDaty(Date.valueOf(LocalDate.now()));
            vente.setIdMagasin("MAG000245");
            vente.createObject("1060", conn);
            
            System.out.println("ID vente: " + vente.getId() );
            
//            System.out.println("1");
            
            
            VenteDetails[] vds = new VenteDetails[this.getFilles().length];
            for(int i = 0; i < this.getFilles().length; i++) {
                MyVenteFille fille = this.getFilles()[i];
                System.out.println("f: " + fille.getIdProduit());
                Produit produit = fille.getProduit(conn);
                
                MyStock stock = MyStock.rechercherParIdProduit(produit.getId(), conn);
                if(stock.getReste() < fille.getQte()) {
                    throw new Exception("Quantite insuffisante au stock pour le produit: " + produit.getId() + " - " + produit.getVal() + "| Demande: " + fille.getQte() + ", Reste: " + stock.getReste());
                }

                VenteDetails vd = new VenteDetails();
                
                vd.setIdVente(vente.getId());
                vd.setIdProduit(produit.getId());
                vd.setPu(produit.getPuVente());
                vd.setPuAchat(produit.getPuAchat());
                vd.setPuVente(produit.getPuVente());
                vd.setQte(fille.getQte());
                
                vd.createObject("1060", conn);
                vds[i] = vd;
            }
//            vente.setFille(vds);
//            vente.creat
//            vente.setLiaisonFille("idVente");
//            vente.createObjectMultiple("1060", conn);
            
            System.out.println("2");
            
            if(this.isDirect) {
                vente.setEtat(11);
                vente.setIdClient("CLI000054");
                vente.payer("1060", conn);
                System.out.println("Direct");
            } else {
                vente.setIdClient(this.getIdClient());
                System.out.println("Par credit");
            }
            
            
            MvtStock mvtStock = new MvtStock();
            mvtStock.setIdMagasin("MAG000245");
            mvtStock.setIdVente(null);
            mvtStock.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKSORTIE);
            mvtStock.setDaty(Date.valueOf(LocalDate.now()));
            mvtStock.setDesignation("Mouvement de Stock: Vente");
            mvtStock.createObject("1060", conn);

            for(int i = 0; i < this.getFilles().length; i++) {
                MyVenteFille fille = this.getFilles()[i];
                MvtStockFille mvtStockFille = new MvtStockFille();
                mvtStockFille.setIdMvtStock(mvtStock.getId());
                mvtStockFille.setIdProduit(fille.getIdProduit());
                mvtStockFille.setEntree(0);
                mvtStockFille.setSortie(fille.getQte());
                mvtStockFille.createObject("1060", conn);
            }
            
//            conn.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            throw e;
        }
        
    }
    
}
