/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import static java.lang.Math.sqrt;

/**
 *
 * @author nrichard01
 */
public class Piece {
    //Ne marche que si rectange
    private int id;
    private Mur mur1;
    private Mur mur2;
    private Mur mur3;
    private Mur mur4;


    public Piece(Mur mur1, Mur mur2, Mur mur3, Mur mur4) {
        this.mur1 = mur1;
        this.mur2 = mur2;
        this.mur3 = mur3;
        this.mur4 = mur4;
    }

    public Mur getMur1() {
        return mur1;
    }

    public void setMur1(Mur mur1) {
        this.mur1 = mur1;
    }

    public Mur getMur2() {
        return mur2;
    }

    public void setMur2(Mur mur2) {
        this.mur2 = mur2;
    }

    public Mur getMur3() {
        return mur3;
    }

    public void setMur3(Mur mur3) {
        this.mur3 = mur3;
    }

    public Mur getMur4() {
        return mur4;
    }

    public void setMur4(Mur mur4) {
        this.mur4 = mur4;
    }

  
    public double Surface(){
        double surface= sqrt( mur1.Longueur()*mur2.Longueur()*mur3.Longueur()*mur4.Longueur());
        return surface;
    }
    
}
