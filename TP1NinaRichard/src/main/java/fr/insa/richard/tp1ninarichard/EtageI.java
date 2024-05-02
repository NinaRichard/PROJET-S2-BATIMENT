/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inspi
 */
public class EtageI extends Etage{

    private int nbrdappart;
    private List<Logement> appartementEtage= new ArrayList();
    
    //Constructeur
    public EtageI(int id, double hauteursousplafond) {
        super(id, hauteursousplafond);
        this.nbrdappart =0;
    }
    
    //Getter et Setter
    //nbrd'appart
    public int getNbrdappart() {
        return nbrdappart;
    }
    public void setNbrdappart(int nbrdappart) {
        this.nbrdappart = nbrdappart;
    }
    
    //AppartementEtage
    public List<Logement> getAppartementEtage() {
        return appartementEtage;
    }
    public void setAppartementEtage(List<Logement> appartementEtage) {
        this.appartementEtage = appartementEtage;
    }
    //Ajouter un appartement à la liste
    public void ajouterAppartement( Logement nouvelAppartement){
        appartementEtage.add(nouvelAppartement);
        nbrdappart ++;
    }

    // redefinir avec l'id et tout de la classe super
    @Override
    public String toString() {
        return "EtageI{" + "nbrdappart=" + nbrdappart + ", appartementEtage=" + appartementEtage + '}';
    }

}
