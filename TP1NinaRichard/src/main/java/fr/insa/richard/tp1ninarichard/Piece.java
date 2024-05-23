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
public class Piece {
    //Ne marche que si rectange
    private int id;
    private Mur mur1;
    private Mur mur2;
    private Mur mur3;
    private Mur mur4;
    private Sol sol;
    private String usage;
    private Plafond plafond;
    private int Etage;
    //"attribut" general ajouter pour l'essai si dessus potentiellement il faudrai modifier le code pour que ça marche et soit optimiser
    private static int nbrDePiece;
    //tentative de constructeur qui permet à l'utilisateur de renseigner les informationn au fur et a mesure
    public Piece(ArrayList<Mur> liste_Mur, ArrayList<Coin> liste_Coin){
        this.id = nbrDePiece; // ou nbr de pice +1 à voir
        nbrDePiece++; //a mettre avant en fct de si on commence à 0 ou pas
        System.out.println("Quels murs definie votre piece?");
        int i=1;
        for (Mur mur : liste_Mur) {
                        System.out.print("Mur "+ i);
                        i++;
                        mur.toString();
        }
        System.out.print("Mur 1 (renseigner le numero du mur, si il n'exite pas taper 0) ");
        int choixMur = Lire.i();
        if (choixMur == 0){
            this.mur1 = creationMur(liste_Mur,liste_Coin);
        } else{
            this.mur1 = liste_Mur.get(choixMur);
        }
        System.out.print("Mur 2 (renseigner le numero du mur, si il n'exite pas taper 0) ");
        choixMur = Lire.i();
        if (choixMur == 0){
            this.mur2 = creationMur(liste_Mur,liste_Coin);
        } else{
            this.mur2 = liste_Mur.get(choixMur);
        }
        System.out.print("Mur 3 (renseigner le numero du mur, si il n'exite pas taper 0) ");
        choixMur = Lire.i();
        if (choixMur == 0){
           this.mur3 = creationMur(liste_Mur,liste_Coin);
        } else{
            this.mur3 = liste_Mur.get(choixMur);
        }
        System.out.print("Mur 4 (renseigner le numero du mur, si il n'exite pas taper 0) ");
        choixMur = Lire.i();
        if (choixMur == 0){
            this.mur4 = creationMur(liste_Mur,liste_Coin);
        } else{
            this.mur4 = liste_Mur.get(choixMur);
        }
    }
    public Mur creationMur(ArrayList<Mur> liste_Mur, ArrayList<Coin> liste_Coin){
        int i=0; Coin coin1;
        Coin coin2;
        for (Coin coin : liste_Coin) {
                        System.out.print("Coin "+ i);
                        i++;
                        coin.toString();
        }
        System.out.print("Premier Coin (renseigner le numero du coin, si il n'exite pas taper 0) ");
        int choixCoin = Lire.i();
        if (choixCoin == 0){
            System.out.print("Donner l'absisse de votre nouveau point:");
            double x = Lire.i();
            System.out.print("Donner l'ordonnée de votre nouveau point:");
            double y = Lire.i();
            coin1 = new Coin(x,y);
            liste_Coin.add(coin1);
        } else{
            coin1 = liste_Coin.get(choixCoin);
        }
        System.out.print("Deuxieme Coin (renseigner le numero du coin, si il n'exite pas taper 0) ");
        choixCoin = Lire.i();
        if (choixCoin == 0){
            System.out.print("Donner l'absisse de votre nouveau point:");
            double x = Lire.i();
            System.out.print("Donner l'ordonnée de votre nouveau point:");
            double y = Lire.i();
            coin2 = new Coin(x,y);
            liste_Coin.add(coin2);
        } else{
            coin2 = liste_Coin.get(choixCoin);
        }
        return new Mur(coin1,coin2);
    }
    
    //Constructeurs
    public Piece(int id, Mur mur1, Mur mur2, Mur mur3, Mur mur4,Sol sol, Plafond plafond) {
        this.id = id;
        this.mur1 = mur1;
        this.mur2 = mur2;
        this.mur3 = mur3;
        this.mur4 = mur4;
        sol.setPiece(this); 
        plafond.setPiece(this);
    }
    public Piece(int id, Mur mur1, Mur mur2, Mur mur3, Mur mur4,Sol sol, Plafond plafond, String usage) {
        this.id = id;
        this.mur1 = mur1;
        this.mur2 = mur2;
        this.mur3 = mur3;
        this.mur4 = mur4;
        this.usage = usage;
        sol.setPiece(this); 
        plafond.setPiece(this);
    }

    public Piece() {
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
    //usage
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    } 
    
    //Getter et Setter
    //Mur1
    public Mur getMur1() {
        return mur1;
    }
    public void setMur1(Mur mur1) {
        this.mur1 = mur1;
    }

    //Getter et Setter
    //Mur2
    public Mur getMur2() {
        return mur2;
    }
    public void setMur2(Mur mur2) {
        this.mur2 = mur2;
    }

    //Getter et Setter
    //Mur3
    public Mur getMur3() {
        return mur3;
    }
    public void setMur3(Mur mur3) {
        this.mur3 = mur3;
    }

    //Getter et Setter
    //Mur4
    public Mur getMur4() {
        return mur4;
    }
    public void setMur4(Mur mur4) {
        this.mur4 = mur4;
    }

    //Getter et Setter
    //Sol
    public Sol getSol() {
        return sol;
    }
    public void setSol(Sol sol) {
        this.sol = sol;
    }

    //Getter et Setter
    //Plafond
    public Plafond getPlafond() {
        return plafond;
    }
    public void setPlafond(Plafond plafond) {
        this.plafond = plafond;
    }

    public int getEtage() {
        return Etage;
    }

    public void setEtage(int Etage) {
        this.Etage = Etage;
    }

    
    //Calcul surface
    public double Surface(){
        double surface= sqrt( mur1.Longueur()*mur2.Longueur()*mur3.Longueur()*mur4.Longueur());
        return surface;
    }
    
}
