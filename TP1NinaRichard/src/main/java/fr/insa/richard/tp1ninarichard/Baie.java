/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author mmarescaux01
 */
public class Baie extends Ouverture{
    private int id;
    private double hauteur;//ASSURER HAUTEUR OUVERTURE INFERIEURE A HAUTEUR MUR
    private double largeur;//IDEM LARGEUR

    public Baie(int id, double hauteur, double largeur) {
        this.id = id;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public Baie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    @Override
    public String toString() {
        return "Porte{" + "id=" + id + ", hauteur=" + hauteur + ", largeur=" + largeur + '}';
    }
     
    public double Surface(){
       return Surface(this.hauteur, this.largeur);
    }
}
