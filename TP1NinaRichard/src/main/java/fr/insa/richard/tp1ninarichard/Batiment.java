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
public class Batiment {
    private int id;
    private String type;
    private int typeIM;
    private int nbrEtage;
    private String address;
    private List<Etage> batiment= new ArrayList();

    public Batiment(int id, String type) {
        this.id = id;
        this.type = type;
        if (type.equals("Maison")){
            this.typeIM = 1;
        } else{
            this.typeIM = 0;
        }  
        this.nbrEtage = 0;
    }
    
    public Batiment(int id, String type, int nbrEtage) {
        this.id = id;
        this.type = type;
        if (type.equals("Maison")){
            this.typeIM = 1;
        } else{
            this.typeIM = 0;
        } 
        this.nbrEtage = nbrEtage;
    }

    public Batiment(int id, String type, String address) {
        this.id = id;
        this.type = type;
        if (type.equals("Maison")){
            this.typeIM = 1;
        } else{
            this.typeIM = 0;
        } 
        this.address = address;
        this.nbrEtage = 0;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    //ne devrai pas etre modifiable
    public void setType(String type) {
        this.type = type;
        if (type.equals("Maison")){
            this.typeIM = 1;
        } else{
            this.typeIM = 0;
        } 
    }

    public int getNbrEtage() {
        return nbrEtage;
    }

    public void setNbrEtage(int nbrEtage) {
        this.nbrEtage = nbrEtage;
    }

    
    public List<Etage> getBatiment() {
        return batiment;
    }

    public void setBatiment(List<Etage> batiment) {
        this.batiment = batiment;
        nbrEtage = 1;// mettre le nbr de truc dans l'autre
    }
    
     public void ajouterEtage( Etage nouvelEtage){
        batiment.add(nouvelEtage);
        this.nbrEtage ++;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTypeIM() {
        return typeIM;
    }

    public void setTypeIM(int typeIM) {
        this.typeIM = typeIM;
    }

    
    @Override
    public String toString() {
        return "Batiment{" + "id=" + id + ", type=" + type + ", nbrEtage=" + nbrEtage + ", address=" + address + ", batiment=" + batiment + '}';
    }
     
     
    
    
}
