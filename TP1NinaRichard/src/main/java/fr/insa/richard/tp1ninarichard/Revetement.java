
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
    private String revetement;
    private double prixaum2; //a faire
    private List<Piece> utiliser = new ArrayList();
    boolean pourMur;
    boolean pourSol;
    boolean pourPlafond; 
    double prixUnitaire;
    
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
    
    public void Parametres(int Type){
        try{
        Scanner fileScanner = new Scanner(new File("CatalogueRevetements.txt"));
        Scanner lineScanner=fileScanner;
        for (int compt=0; compt<Type; compt++){
            lineScanner=new Scanner(fileScanner.nextLine());//lit toutes les lignes jusqu'a la bonne
            lineScanner.useDelimiter(";");
        }
        int compt=lineScanner.nextInt();
        revetement=lineScanner.next();
        pourMur=lineScanner.nextBoolean();
        pourSol=lineScanner.nextBoolean();
        pourPlafond=lineScanner.nextBoolean();
        prixUnitaire=lineScanner.nextDouble();
        }catch (FileNotFoundException e){
           e.printStackTrace();
        }
    }
}
