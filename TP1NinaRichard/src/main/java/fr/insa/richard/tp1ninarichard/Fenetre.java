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

    public Fenetre(int id) {
        this.id = id;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCote() {
        return cote;
    }

    public void setCote(double cote) {
        this.cote = cote;
    }

    @Override
    public String toString() {
        return "Fenetre{" + "id=" + id + ", cote=" + cote + '}';
    }


    public double Surface(){
       return Surface(this.cote, this.cote);
    }
}

   
    

    
    

