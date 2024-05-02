/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author inspi
 */ 

public abstract class Ouverture {
   private int id;
    
   //Calcul surface
   public double Surface(double hauteur, double largeur) {
       return hauteur*largeur;
   }
}
