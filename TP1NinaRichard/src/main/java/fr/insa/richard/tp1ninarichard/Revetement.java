
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
    private double surface;
    
    //Constructeurs
    public Revetement(int id) {
        this.id = id;
    }
    public Revetement(int id, int type) {
        this.id = id;
        this.type = type;
    }

    //Getter et Setter
    //Type
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    //Getter et Setter
    //Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //Getter et Setter
    //Utiliser
    public List<Piece> getUtiliser() {
        return utiliser;
    }
    public void setUtiliser(List<Piece> utiliser) {
        this.utiliser = utiliser;
    }
    
    public void ajouterPiece( Piece nouvellePiece){
        utiliser.add(nouvellePiece);
    }

    //Getter
    //Prix unitaire
    public double getPrixUnitaire() {//test
        return prixUnitaire;
    }
    
    //Getter et Setter
    //Id revetement
    public int getIdRevetement() {
        return idRevetement;
    }
    public void setIdRevetement(int idRevetement) {
        this.idRevetement = idRevetement;
    }
    
    //Getter et Setter
    //Surface
    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    
    //Getter et Setter
    //tests boolean
    public boolean isPourMur() {
        return pourMur;
    }

    public void setPourMur(boolean pourMur) {
        this.pourMur = pourMur;
    }

    public boolean isPourSol() {
        return pourSol;
    }

    public void setPourSol(boolean pourSol) {
        this.pourSol = pourSol;
    }

    public boolean isPourPlafond() {
        return pourPlafond;
    }

    public void setPourPlafond(boolean pourPlafond) {
        this.pourPlafond = pourPlafond;
    }
    
    
   //instancie les parametres en fonction de la ligne dans le catalogue revetement 
    public void Parametres(int Type){
    
    String lignelue="";
    int test=0;
    try{
        File file = new File("C://Users/mmarescaux01/Desktop/CatalogueRevetements.txt");
        BufferedReader flux=new BufferedReader(new FileReader(file));
        while (test<=Type){
            lignelue=flux.readLine();
            test=test+1;
        }
        System.out.println(lignelue);
        lignelue=lignelue;
        System.out.println(lignelue);
        Scanner lineScanner=new Scanner( lignelue);
        lineScanner.useDelimiter(";");
        int pourmur, poursol, pourplafond;
        this.idRevetement=lineScanner.nextInt();
        System.out.println(this.idRevetement);
        this.revetement=lineScanner.next();
        pourmur=lineScanner.nextInt();
        System.out.println(pourmur);
        poursol=lineScanner.nextInt();
        System.out.println(poursol);
        pourplafond=lineScanner.nextInt();
        System.out.println(pourplafond);
        String str=lineScanner.next();
        this.prixUnitaire=Double.parseDouble(str);
        
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