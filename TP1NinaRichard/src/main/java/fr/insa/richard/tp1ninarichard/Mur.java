/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author nrichard01
 */
public class Mur {
    // devrait on y associer in revetement
    // champs point 
    private int id;
    private Coin coin1;
    private Coin coin2;
    private boolean interior;
    private static int nbrMur;
    private double surface;

    //Constructeurs
    
    public Mur() {
    }

    public Mur(int id, Coin coin1, Coin coin2) {
        this.id = id;
        this.coin1 = coin1;
        this.coin2 = coin2;
        nbrMur ++;
    }
    public Mur(Coin coin1, Coin coin2) {
        this.id = nbrMur;//voir si mettre après
        this.coin1 = coin1;
        this.coin2 = coin2;
        nbrMur ++;
    }
    public Mur(int id, Coin coin1, Coin coin2, boolean interior) {
        this.id = id;
        this.coin1 = coin1;
        this.coin2 = coin2;
        this.interior = interior;
        nbrMur++;
    }
    public Mur(ArrayList<Mur> liste_Mur, ArrayList<Coin> liste_Coin, Etage etage){
        this.id=nbrMur;
        nbrMur++;
        int i=0;
        for (Coin coin : liste_Coin) {
                        System.out.print("Coin "+ i);
                        i++;
                        coin.toString();
        }
        System.out.print("Premier Coin (renseigner le numero du coin, si il n'existe pas taper 0) ");
        int choixCoin = Lire.i();
        if (choixCoin == 0){
            System.out.print("Donner l'absisse de votre nouveau point:");
            double x = Lire.i();
            System.out.print("Donner l'ordonnée de votre nouveau point:");
            double y = Lire.i();
            this.coin1 = new Coin(x,y);
            liste_Coin.add(this.coin1);
        } else{
            this.coin1 = liste_Coin.get(choixCoin);
        }
        System.out.print("Deuxieme Coin (renseigner le numero du coin, si il n'existe pas taper 0) ");
        choixCoin = Lire.i();
        if (choixCoin == 0){
            System.out.print("Donner l'absisse de votre nouveau point:");
            double x = Lire.i();
            System.out.print("Donner l'ordonnée de votre nouveau point:");
            double y = Lire.i();
            this.coin2 = new Coin(x,y);
            liste_Coin.add(this.coin2);
        } else{
            this.coin2 = liste_Coin.get(choixCoin);
        }
        this.surface=Longueur()*etage.getHauteursousplafond();
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
    //Coin1
    public Coin getCoin1() {
        return coin1;
    }
    public void setCoin1(Coin coin1) {
        this.coin1 = coin1;
    }

    //Getter et Setter
    //Coin2
    public Coin getCoin2() {
        return coin2;
    }
    public void setCoin2(Coin coin2) {
        this.coin2 = coin2;
    }
    
    //Getter et Setter
    //Surface
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }

    //dit si mur interieur ou exterieur
    public boolean isInterior() {
        return interior;
    }

    //Setter
    //boolean interieur
    public void setInterior(boolean interior) {
        this.interior = interior;
    }
    
    //calcule longueur du mur
    public  double Longueur(){
        double longueur = sqrt((coin1.getX() - coin2.getX())*(coin1.getX() - coin2.getX()) + (coin1.getY() - coin2.getY())* (coin1.getY() - coin2.getY()) );
        return longueur;
    }

    //calcule surface mur
    public  double Surface(double hauteur){
       double surface = Longueur() * hauteur;
       return surface;
   }

    @Override
    public String toString() {
        if (interior=true) {
            return "Le mur numero " + id + ", de premier coin " + coin1 + " et de second coin " + coin2 + ", est un mur interieur.";
        }else{
            return "Le mur numero " + id + ", de premier coin " + coin1 + " et de second coin " + coin2 + ", est un mur exterieur.";
        }
    }
    


   
    
    
}
