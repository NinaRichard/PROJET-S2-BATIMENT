/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrichard01
 */
public class Logement {
    private int id;
    private int nbrpiece;
    private List<Piece> appartement= new ArrayList();
    
    //Constructeur
    public Logement(int id) {
        this.id = id;
        this.nbrpiece = 0;
    }

    //Getter et Setter
    //Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //Liste de pieces
    public List<Piece> getAppartement() {
        return appartement;
    }
    public void setAppartement(List<Piece> appartement) {
        this.appartement = appartement;
    }
    //Ajouter une piece
    public void ajouterPiece( Piece nouvellePiece){
        appartement.add(nouvellePiece);
        this.nbrpiece ++;
    }

    @Override
    public String toString() {
        return "Le logement numero " + id + " contient " + nbrpiece + " pieces.";//
    }
    
    
    
}
