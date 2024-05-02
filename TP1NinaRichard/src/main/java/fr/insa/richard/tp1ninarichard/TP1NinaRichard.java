/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.richard.tp1ninarichard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author nrichard01
 */
public class TP1NinaRichard {
    
    // voir mettre dans des tableau
    // 
    public static void menu(ArrayList<Batiment> liste_Batiment, ArrayList<Coin> liste_Coin, ArrayList<Mur> liste_Mur){
        
        int choix;
        int nbrBatiment = 1;
        int type;
        do{
        System.out.println("1) Créer un batiment");
        System.out.println("2) Créer un étage et ajouter un étage dans un batiment") ;
        System.out.println("3) Créer un logement et ajouter un logement dans un étage");
        System.out.println("4) Créer une piece et ajouter une piece dans un logement");
        System.out.println("5) Créer un mur");
        System.out.println("6) Créer un point");
        System.out.println("7) suprimer un étage");
        System.out.println("8) suprimer un logement ou changer un logement d'étage");
        System.out.println("9) suprimer une piece et changer une piece de logement");
        System.out.println("10) suprimer un mur, le modifier ou changer de piece");
        System.out.println("11) suprimer un point ou le modifier");
        System.out.println("12) Passer à la partie revetement");
        System.out.println("Pour annuler ou arreter taper 0");
        choix = Lire.i();
        switch(choix){
            case 1:
                nbrBatiment ++;
                System.out.println("Votre Batiment est il 1) une maison 2) un immeuble?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 ou 2! Votre Batiment est il 1) une maison 2) un immeuble?");
                    type = Lire.i(); 
                }
                if (type == 1){
                    Batiment batiment = new Batiment(nbrBatiment,"Maison");
                    liste_Batiment.add(batiment);
                } else {
                    Batiment batiment = new Batiment(nbrBatiment,"Immeuble");
                    liste_Batiment.add(batiment);
                }
                
                //mettre option annuler
            
            case 2:
                System.out.println("A quel batiment vouler vous ajouter un étage? Voici les batiments à votre disposition");
                int i=0;
                for (Batiment batiment : liste_Batiment) {
                    System.out.print("Batiment "+ i);
                    i++;
                     batiment.toString();
                }
                System.out.print("Indiquer les numero du batiment sélectionner");
                int batimentChoisi = Lire.i();
                Batiment batimentMod = liste_Batiment.get(batimentChoisi);
                System.out.println("Quel est la hauteur sous plafont de cet étage?");
                double hauteurSousPlafont=Lire.d();
                Etage etage = new Etage(batimentMod.getNbrEtage(),hauteurSousPlafont);
                batimentMod.setNbrEtage(batimentMod.getNbrEtage()+1);
                batimentMod.ajouterEtage(etage);
            break;
            case 3:
                System.out.println("A quel étage vouler vous ajouter un étage?");
                //peut on choper un object par son id?
                Logement appartement = new Logement(etage.getNbrdappart());
                etage.setNbrdappart(etage.getNbrdappart()+1);
                etage.ajouterAppartement(appartement);
            break;
            /*
            case 4:
                System.out.println("A quel étage vouler vous ajouter un étage?");
                //peut on choper un object par son id?
                Logement appartement = new Logement(etage.getNbrdappart());
                etage.setNbrdappart(etage.getNbrdappart()+1);
                etage.ajouterAppartement(appartement);
            break;
            */
            case 12:
                //menuRevetement()mettre les nbrs ect..
            break;
        }
        }while (choix != 0);
    }
    
    public static void menuRevetement(){
        System.out.println("1) Je veux mettre tel revetement sur un sol un mur ...");
    }

    public static void main(String[] args) {
        /*
         // Définition du chemin du fichier de sortie
        String filePath = "batiment.txt";
        // Écriture du code LaTeX avec TikZ dans le fichier de sortie
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        //Coin c1 = new Coin(1,3.15,4.5);
        //System.out.println(c1.toString());
        System.out.println("Bonjour, de quel type est votre batiment?");
        String batimenttype = Lire.S();
        Batiment batiment = new Batiment(1, batimenttype);
        System.out.println("Combien d'étage comporte votre batiment?");
        int nbrEtage = Lire.i();
        int nbrCoin =0;
        int nbrMur = 0;
        for (int i=0; i<nbrEtage ; i++){
            Etage etage = new Etage(i);
            batiment.ajouterEtage(etage);
            System.out.println("Combien de logements comporte votre étage " + i + " ?");
            int nbrAppartement = Lire.i();
            for (int j=1; j<=nbrAppartement ; j++){
                Logement appartement = new Logement(j);
                etage.ajouterAppartement(appartement);
                System.out.println("Combien de piece comporte votre logement " + j + " ?");
                int nbrPiece = Lire.i();
                for (int k=1; k<=nbrPiece ; k++){
                    System.out.println("Quel est l'utilité de votre piece " + k + " ?");
                    String typePiece = Lire.S();
                    Mur[] murs = new Mur[4];
                    for (int f=0; f<4; f++){
                        System.out.println("Quels sont les coordonnées des coins de votre mur  " + (f + 1) + " ?");
                        System.out.print("Coin 1 x: ");
                        double x1 = Lire.d();
                        System.out.print("Coin 1 y: ");
                        double y1 = Lire.d();
                        Coin coin1 = new Coin(nbrCoin, x1,y1);
                        nbrCoin ++;
                        System.out.print("Coin 2 x: ");
                        double x2 = Lire.d();
                        System.out.print("Coin 2 y: ");
                        double y2 = Lire.d();
                        Coin coin2 = new Coin(nbrCoin, x2,y2);
                        nbrCoin ++;
                        murs[f] = new Mur(nbrMur, coin1, coin2);
                        nbrMur ++;
                    }
                    Sol sol = new Sol(nbrPiece);
                    Plafond plafond = new Plafond(nbrPiece);
                    Piece piece = new Piece(k,murs[0],murs[1],murs[2],murs[3], sol , plafond, typePiece);
                    //ajouter la piece à l'appart
                    appartement.ajouterPiece(piece);
                }   
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        Porte p1 = new Porte(1);
        double s = p1.Surface();
        System.out.println("la surface vaut "+s);*/
    int nbrdeBatiment = 0;  
    ArrayList<Batiment> liste_Batiment;
    ArrayList<Coin> liste_Coin;
    ArrayList<Mur> liste_Mur;
    System.out.println("Bonjour, bienvenue dans notre générateur de devis, dans un premier temps nous allons modéliser votre batiment, puis vous pourrez choisir vos revetements de murs, sols et plafonds.");
    System.out.println("Première étape: Créer un batiment pour cela veuiller nous renseigner:");
    System.out.print("L'adresse du Batiment: ");
    String adresse = Lire.S();
    System.out.println("Votre Batiment est il 1) une maison 2) un immeuble?");
    int type = Lire.i();
    while(type != 1 && type != 2){
        System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 ou 2! Votre Batiment est il 1) une maison 2) un immeuble?");
        type = Lire.i(); 
    }
    String typeB = "Immeuble";
    if (type == 1){
        typeB = "Maison";
    }
    nbrdeBatiment ++;
    Batiment batiment = new Batiment(nbrdeBatiment,typeB,adresse);
    liste_Batiment.add(batiment);
    
    menu(liste_Batiment,liste_Coin ,liste_Mur); //les listes ne peuvent pas etre transmise vide?
     
    }
}
