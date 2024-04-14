
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package fr.insa.richard.tp1ninarichard;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

/**
 *
 * @author inspi
 */
public class Revetement {
    private int id;
    private int type;
    public int idRevetement;
    private String revetement;
    private List<Piece> utiliser = new ArrayList();
    private boolean pourMur;
    private boolean pourSol;
    private boolean pourPlafond; 
    private double prixUnitaire;
    
    public Revetement(int id) {
        this.id = id;
    }

    public Revetement(int id, int type) {
        this.id = id;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Piece> getUtiliser() {
        return utiliser;
    }

    public void setUtiliser(List<Piece> utiliser) {
        this.utiliser = utiliser;
    }
    
    public void ajouterPiece( Piece nouvellePiece){
        utiliser.add(nouvellePiece);
    }

    public double getPrixUnitaire() {//test
        return prixUnitaire;
    }

    public int getIdRevetement() {
        return idRevetement;
    }

    public void setRevetement(int idRevetement) {
        this.idRevetement = idRevetement;
    }
    
    
    
    public void Parametres(int Type){
        int idrevetement=0;
        String revetements="";
        int pourmur=0, poursol=0, pourplafond=0;
        boolean mur=true, sol=true, plafond=true;
        Double prixunitaire=0.0;
        try (Scanner fileScanner = new Scanner(new File("CatalogueRevetements.txt"))){
        String ligne=fileScanner.nextLine();
        
        //if (Type>1){
            for (int compt=1; compt<=Type; compt++){
                ligne=fileScanner.nextLine();//lit toutes les lignes jusqu'a la bonne
                System.out.println(ligne);
            }
        
            
        Scanner lineScanner=new Scanner( ligne);
        lineScanner.useDelimiter(";");
        
        idrevetement=lineScanner.nextInt();
        revetements=lineScanner.next();
        System.out.println(revetements);
        pourmur=lineScanner.nextInt();
        System.out.println(pourmur);
        poursol=lineScanner.nextInt();
        pourplafond=lineScanner.nextInt();
        prixunitaire=lineScanner.nextDouble();
        
        if (pourmur==1){
            mur=true;
        }else{
            mur=false;
        }
        
        if (poursol==1){
            sol=true;
        }else{
            sol=false;
        }
        
        if (pourplafond==1){
            plafond=true;
        }else{
            plafond=false;
        }
        
        System.out.println(prixunitaire);
        
        this.idRevetement=idrevetement;
        this.revetement=revetements;
        this.pourMur=mur;
        this.pourSol=sol;
        this.pourPlafond=plafond;
        this.prixUnitaire=prixunitaire;
       }catch (FileNotFoundException e){
           e.printStackTrace();
       }
        //this.idRevetement=idrevetement;
        
    }
}
