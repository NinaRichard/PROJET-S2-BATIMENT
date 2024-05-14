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

    //Constructeurs
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

    
    
    //Getter et Setter
    //Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //Getter et Setter
    //Type
    public String getType() {
        return type;
    }
    //ne devrait pas etre modifiable
    public void setType(String type) {
        this.type = type;
        if (type.equals("Maison")){
            this.typeIM = 1;
        } else{
            this.typeIM = 0;
        } 
    }

    //Getter et Setter
    //NbrEtage
    public int getNbrEtage() {
        return nbrEtage;
    }
    public void setNbrEtage(int nbrEtage) {
        this.nbrEtage = nbrEtage;
    }


    //Getter et Setter
    //Address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    //Getter et Setter
    //typeIM
    public int getTypeIM() {
        return typeIM;
    }
    public void setTypeIM(int typeIM) {
        this.typeIM = typeIM;
    }

    
    @Override
    public String toString() {
        return "Le Batiment numéro " + id + " est un(e) " + type + " avec " + nbrEtage + " étages, a l'adresse " + address + ".";
    }   
    
}
