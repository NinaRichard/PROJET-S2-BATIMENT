/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author mmarescaux01
 */
public class Porte extends Ouverture{
    private int id;
    private double hauteur=2.10 ;
    private double largeur=0.90 ;

    public Porte(int id) {
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
     return Surface(this.hauteur, this.largeur);
    public double Surface(){
       
    }
}
