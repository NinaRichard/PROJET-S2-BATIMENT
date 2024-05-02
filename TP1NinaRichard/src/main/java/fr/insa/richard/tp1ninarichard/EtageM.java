/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inspi
 */
public class EtageM extends Etage{
    private int nbrdePiece;
    private List<Piece> pieceEtage= new ArrayList();
    
    //Constructeur
    public EtageM(int id, double hauteursousplafond) {
        super(id, hauteursousplafond);
    }
    
    //Getter et Setter
    //nbr de piece
    public int getNbrdePiece() {
        return nbrdePiece;
    }
    public void setNbrdePiece(int nbrdePiece) {
        this.nbrdePiece = nbrdePiece;
    }

    // Liste de piece
    public List<Piece> getPieceEtage() {
        return pieceEtage;
    }
    public void setPieceEtage(List<Piece> pieceEtage) {
        this.pieceEtage = pieceEtage;
    }
    //Ajouter un piece à la liste
    public void ajouterPiece( Piece nouvellePiece){
        pieceEtage.add(nouvellePiece);
        nbrdePiece ++;
    }

    // redefinir avec l'id et tout de la classe super
    @Override
    public String toString() {
        return "EtageM{" + "nbrdePiece=" + nbrdePiece + ", pieceEtage=" + pieceEtage + '}';
    }
    
    
}
