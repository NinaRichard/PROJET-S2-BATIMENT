/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author inspi
 */
public class Plafond {
    public int id;
    public Piece piece;
    public Revetement revetement;

    public Plafond(int id) {
        this.id = id;
    }

    public Plafond(Piece piece) {
        this.piece = piece;
    }

    public Plafond(int id, Piece piece) {
        this.id = id;
        this.piece = piece;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public Revetement getRevetement() {
        return revetement;
    }

    public void setRevetement(Revetement revetement) {
        this.revetement = revetement;
    }
    
     public double Surface(){
        return piece.Surface();
    }
}
