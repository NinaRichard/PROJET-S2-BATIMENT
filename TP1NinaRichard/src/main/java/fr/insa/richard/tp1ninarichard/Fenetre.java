/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author emeli
 */
public class Fenetre extends Ouverture{
    private int id; 
    private double cote= 1.20;     

    //Constructeur
    public Fenetre(int id) {
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
    //cote
    public double getCote() {
        return cote;
    }
    public void setCote(double cote) {
        this.cote = cote;
    }

    @Override
    public String toString() {
        return "La fenetre numero " + id + " a un cote de " + cote + ".";
    }

    //renvoyer surface
    public double Surface(){
       return Surface(this.cote, this.cote);
    }
}

   
    

    
    

