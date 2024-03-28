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
    private List<Piece> appartement= new ArrayList();
    private int nbrpiece;

    public Logement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Piece> getAppartement() {
        return appartement;
    }

    public void setAppartement(List<Piece> appartement) {
        this.appartement = appartement;
    }
    
    public void ajouterPiece( Piece nouvellePiece){
        appartement.add(nouvellePiece);
        this.nbrpiece ++;
    }
    
    
    
}
