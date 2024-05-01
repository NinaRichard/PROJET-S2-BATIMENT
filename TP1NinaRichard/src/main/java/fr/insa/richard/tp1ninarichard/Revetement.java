
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package fr.insa.richard.tp1ninarichard;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        /*int pourmur=0, poursol=0, pourplafond=0;
        try (Scanner fileScanner = new Scanner(new File("CatalogueRevetements.txt"))){
        String ligne=fileScanner.nextLine();
        
        //if (Type>1){
            for (int compt=1; compt<=Type; compt++){
                ligne=fileScanner.nextLine();//lit toutes les lignes jusqu'a la bonne
                System.out.println(ligne);
            }
        */
        
        
   
    String lignelue="";
    int test=0;
    try{
        BufferedReader flux=new BufferedReader(new FileReader("CatalogueRevetements.txt"));
        while (test<=Type){
            lignelue=flux.readLine();
            test=test+1;
        }
        System.out.println(lignelue);
        Scanner lineScanner=new Scanner( lignelue);
        lineScanner.useDelimiter(";");
        int pourmur, poursol, pourplafond;
        this.idRevetement=lineScanner.nextInt();
        this.revetement=lineScanner.next();
        pourmur=lineScanner.nextInt();
        poursol=lineScanner.nextInt();
        pourplafond=lineScanner.nextInt();
        this.prixUnitaire=lineScanner.nextDouble();
        
        if (pourmur==1){
            this.pourMur=true;
        }else{
            this.pourMur=false;
        }
        
        if (poursol==1){
            this.pourSol=true;
        }else{
            this.pourSol=false;
        }
        
        if (pourplafond==1){
            this.pourPlafond=true;
        }else{
            this.pourPlafond=false;
        }
        
       }catch (FileNotFoundException e){
           e.printStackTrace();
       } catch (IOException ex) {
            Logger.getLogger(Revetement.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}