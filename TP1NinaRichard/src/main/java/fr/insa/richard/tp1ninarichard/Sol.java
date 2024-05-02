/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author inspi
 */
public class Sol{
    public int id;
    public Piece piece;
    public Revetement revetement;

    //Constructeurs
    public Sol(int id, Piece piece) {
        this.id = id;
        this.piece = piece;
    }
    public Sol(Piece piece) {
        this.piece = piece;
    }
    public Sol(int id) {
        this.id = id;
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
    //Revetement
    public Revetement getRevetement() {
        return revetement;
    }
    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
    }

    

    //Getter et Setter
    //Piece
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    //renvoie surface sol (pour piece)
    public double Surface(){
        return piece.Surface();
    }
    
}
