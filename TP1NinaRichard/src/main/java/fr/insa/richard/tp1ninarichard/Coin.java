/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author nrichard01
 */
public class Coin {
    private int id;
    private double x;
    private double y;
    private static int nbrCoin = 0;
    // private double 
    /*
     * WIKIVERSITY
     */

    //Constructeurs
    public Coin() {
    }
    public Coin(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        nbrCoin ++;
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
    //coordonnee x
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    //Getter et Setter
    //Coordonnee y
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "Le coin numero " + id + " a pour coordonnees x=" + x + " et y=" + y + ".";
    }
}
