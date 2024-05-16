/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.richard.tp1ninarichard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nrichard01
 */
public class TP1NinaRichard {
    
    // voir mettre dans des tableau
    // 
    public static void menu(ArrayList<Batiment> liste_Batiment,ArrayList<Maison> liste_Maison, ArrayList<Immeuble> liste_Immeuble, ArrayList<Coin> liste_Coin, ArrayList<Mur> liste_Mur, ArrayList<Revetement> liste_Revetement, int nbrevetement){
        
        int choix;
        int nbrBatiment = 1;
        int type;
        int i;
        int batimentChoisi;
        int etageChoisi;
        int maisonChoisi;
        int immeubleChoisi;
        int logementChoisi;
        int pieceChoisi;
        List<Etage> liste_Etage;
        List<EtageI> liste_EtageI;
        List<EtageM> liste_EtageM;
        List<Logement> liste_Logement;
        List<Piece> liste_Piece;
        Logement logementMod;
        do{
        System.out.println("1) Créer un batiment");
        System.out.println("2) Créer un étage et ajouter un étage dans un batiment") ;
        System.out.println("3) Créer un logement et ajouter un logement dans un étage");
        System.out.println("4) Créer une piece et ajouter une piece dans un logement");
        System.out.println("5) Créer un mur");
        System.out.println("6) suprimer un étage");
        System.out.println("7) suprimer un logement ou changer un logement d'étage");
        System.out.println("8) suprimer une piece et changer une piece de logement");
        System.out.println("9) suprimer un mur, le modifier ou changer de piece");
        System.out.println("10) suprimer un point ou le modifier");
        System.out.println("11) Passer à la partie revetement");
        System.out.println("Pour annuler ou arreter taper 0");
        choix = Lire.i();
        switch(choix){
            case 1://creer un batiment
                nbrBatiment ++;
                System.out.println("Votre Batiment est-il 1) une maison; 2) un immeuble?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 OU 2 ! Votre Batiment est-il 1) une maison; 2) un immeuble?");
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
            
            case 2://Créer un étage et ajouter un étage dans un batiment
                System.out.println("Voulez vous modifier 1) une maison; 2) un immeuble ?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! Votre Batiment est il 1) une maison; 2) un immeuble?");
                    type = Lire.i(); 
                }
                if (type == 1){
                    i=0;
                    for (Maison maison : liste_Maison) {//indique les maisons existentes
                        System.out.print("Batiment "+ i);
                        i++;
                        maison.toString();
                    }
                    System.out.print("Indiquer les numeros de la Maison selectionnee");
                    maisonChoisi = Lire.i();
                    Maison maisonMod = liste_Maison.get(maisonChoisi);

                    System.out.println("Quelle est la hauteur sous plafond de cet etage?");
                    double hauteurSousPlafont=Lire.d();
                    EtageM etageM = new EtageM(maisonMod.getNbrEtage(),hauteurSousPlafont);
                    //maisonMod.setNbrEtage(maisonMod.getNbrEtage()+1);
                    maisonMod.ajouterEtage(etageM);
                } else {
                    i=0;
                    for (Immeuble immeuble : liste_Immeuble) {
                        System.out.print("Batiment "+ i);
                        i++;
                        immeuble.toString();
                    }
                    System.out.print("Indiquer les numeros de l'Immeuble sélectionner");
                    immeubleChoisi = Lire.i();
                    Immeuble immeubleMod = liste_Immeuble.get(immeubleChoisi);

                    System.out.println("Quel est la hauteur sous plafond de cet étage?");
                    double hauteurSousPlafont=Lire.d();
                    EtageI etageI = new EtageI(immeubleMod.getNbrEtage(),hauteurSousPlafont);
                    //immeubleMod.setNbrEtage(immeubleMod.getNbrEtage()+1);
                    immeubleMod.ajouterEtage(etageI);
                    
                }   
            break;
            
            case 3: //Créer un logement et ajouter un logement dans un étage
                System.out.println("A quelle immeuble vouler vous ajouter un logement?");
                i=0;
                for (Immeuble immeuble : liste_Immeuble) {
                    System.out.print("Batiment "+ i);
                    i++;
                    immeuble.toString();
                }
                
                System.out.print("Indiquer le numero de l'Immeuble sélectionner");
                immeubleChoisi = Lire.i();
                Immeuble immeubleMod3 = liste_Immeuble.get(immeubleChoisi);
                
                System.out.println("A quel étage vouler vous ajouter un Logement?");
                liste_EtageI = immeubleMod3.getBatiment();
                i=0;
                for (Etage etageaC : liste_EtageI) {
                    System.out.print("Etage "+ i);
                    i++;
                    etageaC.toString();
                }
                System.out.print("Indiquer le numero de l'étage sélectionne");
                etageChoisi = Lire.i();
                EtageI etageMod = liste_EtageI.get(etageChoisi);
                Logement appartement = new Logement(etageMod.getNbrdappart());
                //etageMod.setNbrdappart(etageMod.getNbrdappart()+1);
                etageMod.ajouterAppartement(appartement);
                
            break;
            case 4://Créer une piece et ajouter une piece dans un logement
                System.out.println("Voulez vous modifier 1) une maison,2) un immeuble ?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 ou 2! Votre Batiment est il 1) une maison 2) un immeuble?");
                    type = Lire.i(); 
                }
                if (type == 1){
                    i=0;
                    for (Maison maison : liste_Maison) {
                        System.out.print("Batiment "+ i);
                        i++;
                        maison.toString();
                    }
                    System.out.print("Indiquer le numero de la Maison sélectionner");
                    maisonChoisi = Lire.i();
                    Maison maisonMod = liste_Maison.get(maisonChoisi);

                    System.out.println("A quel étage voulez vous ajouter un étage?");
                    liste_EtageM = maisonMod.getBatiment();
                    i=0;
                    for (EtageM etageaC : liste_EtageM) {
                        System.out.print("Etage "+ i);
                        i++;
                        etageaC.toString();
                    }
                    System.out.print("Indiquer les numero de l'étage sélectionner");
                    etageChoisi = Lire.i();
                    EtageM etageMod4 = liste_EtageM.get(etageChoisi);
                    Piece pieceMod=new Piece(liste_Mur, liste_Coin);
                    etageMod4.ajouterPiece(pieceMod);
                } else {
                    i=0;
                    for (Immeuble immeuble : liste_Immeuble) {
                        System.out.print("Batiment "+ i);
                        i++;
                        immeuble.toString();
                    }
                    System.out.print("Indiquer les numero de l'Immeuble sélectionner");
                    immeubleChoisi = Lire.i();
                    Immeuble immeubleMod = liste_Immeuble.get(immeubleChoisi);  
                    
                    System.out.println("A quel étage voulez vous ajouter un étage?");
                    liste_EtageI = immeubleMod.getBatiment();
                    i=0;
                    for (EtageI etageaC : liste_EtageI) {
                        System.out.print("Etage "+ i);
                        i++;
                        etageaC.toString();
                    }
                    System.out.print("Indiquer les numero de l'étage sélectionner");
                    etageChoisi = Lire.i();
                    EtageI etageMod4 = liste_EtageI.get(etageChoisi);
                    //choix Logement
                    i=0;
                    liste_Logement=etageMod4.getAppartementEtage();
                    for (Logement logement : liste_Logement){//choix etage
                        System.out.print("Etage "+ i);
                        i++;
                        logement.toString();
                    }
                    System.out.print("Indiquer le numero du Logement dans lequel ajouter une piece");
                    logementChoisi = Lire.i();
                    logementMod=liste_Logement.get(logementChoisi);
                    
                    //Ajouter piece dans logement choisi
                    Piece pieceMod=new Piece(liste_Mur, liste_Coin);
                    logementMod.ajouterPiece(pieceMod);
                }
            break;
            case 5://Créer un mur
            //ATTENTION : LE MUR CREE AINSI N'EST PAS RELIE A UN ETAGE MEME SI ON A DEMANDE L'EMPLACEMENT
                System.out.println("Voulez-vous modifier 1) une maison; 2) un immeuble ?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! Votre Batiment est il 1) une maison; 2) un immeuble?");
                    type = Lire.i(); 
                }
                //cas maison
                if (type == 1){
                    i=0;
                    for (Maison maison : liste_Maison) {//indique les maisons existentes
                        System.out.print("Maison "+ i);
                        i++;
                        maison.toString();
                    }
                    System.out.print("Indiquer le numero de la Maison selectionnee");
                    maisonChoisi = Lire.i();
                    Maison maisonMod = liste_Maison.get(maisonChoisi);
                    
                    //choix etage
                    liste_EtageM = maisonMod.getBatiment();
                    i=0;
                    for (Etage etage : liste_EtageM) {
                        System.out.print("Etage "+ i);
                        i++;
                        etage.toString();
                    }
                    System.out.print("Indiquez le numero de l'étage sélectionne");
                    etageChoisi = Lire.i();
                    EtageM etageModified = liste_EtageM.get(etageChoisi);
                    
                    //Creation mur
                    Mur mur= new Mur(liste_Mur, liste_Coin);

                } else {
                    i=0;
                    for (Immeuble immeuble : liste_Immeuble) {
                        System.out.print("immeuble "+ i);
                        i++;
                        immeuble.toString();
                    }
                    System.out.print("Indiquer le numero de l'Immeuble sélectionner");
                    immeubleChoisi = Lire.i();
                    immeubleMod3 = liste_Immeuble.get(immeubleChoisi);

                    liste_EtageI = immeubleMod3.getBatiment();
                    i=0;
                    for (Etage etageaC : liste_EtageI) {
                        System.out.print("Etage "+ i);
                        i++;
                        etageaC.toString();
                    }
                    System.out.print("Indiquer le numero de l'étage sélectionne");
                    etageChoisi = Lire.i();
                    etageMod = liste_EtageI.get(etageChoisi);
                    
                    //Creation mur
                    Mur mur= new Mur(liste_Mur, liste_Coin);
                }
            break;
            case 6:
                System.out.println("Voulez vous modifier 1) une maison; 2) un immeuble ?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 OU 2! Votre Batiment est il 1) une maison; 2) un immeuble?");
                    type = Lire.i(); 
                }
                if (type == 1){
                    i=0;
                    for (Maison maison : liste_Maison) {//choix maison
                        System.out.print("Maison "+ i);
                        i++;
                        maison.toString();
                    }
                    System.out.print("Indiquer les numeros de la Maison selectionnee");
                    maisonChoisi = Lire.i();
                    Maison maisonMod = liste_Maison.get(maisonChoisi);
                    
                    //choix etage a supprimer
                    liste_EtageM=maisonMod.getBatiment();
                    for (Etage etage : liste_EtageM){//choix etage
                        System.out.print("Etage "+ i);
                        i++;
                        etage.toString();
                    }
                    System.out.print("Indiquer le numero de l'Etage a supprimer");
                    etageChoisi = Lire.i();
                    liste_EtageM.remove(etageChoisi);
                    maisonMod.setBatiment(liste_EtageM);

                } else {
                    i=0;
                    for (Immeuble immeuble : liste_Immeuble) {//choix immeuble
                        System.out.print("Immeuble "+ i);
                        i++;
                        immeuble.toString();
                    }
                    System.out.print("Indiquer les numeros de l'Immeuble sélectionne");
                    immeubleChoisi = Lire.i();
                    Immeuble immeubleMod = liste_Immeuble.get(immeubleChoisi);
                    
                    //choix etage a supprimer
                    liste_EtageI=immeubleMod.getBatiment();
                    for (Etage etage : liste_EtageI){//choix etage
                        System.out.print("Etage "+ i);
                        i++;
                        etage.toString();
                    }
                    System.out.print("Indiquer le numero de l'Etage a supprimer");
                    etageChoisi = Lire.i();
                    liste_EtageI.remove(etageChoisi);
                    immeubleMod.setBatiment(liste_EtageI);
                     
                }
            break;
            
            case 7 :
                System.out.println("Dans quel immeuble voulez-vous supprimer ou changer d'etage un logement?");
                i=0;
                for (Immeuble immeuble : liste_Immeuble) {//choix immeuble
                    System.out.print("Immeuble "+ i);
                    i++;
                    immeuble.toString();
                }
                System.out.print("Indiquer le numero de l'Immeuble sélectionne");
                immeubleChoisi = Lire.i();
                immeubleMod3 = liste_Immeuble.get(immeubleChoisi);
                
                System.out.println("Voulez vous 1)supprimer une logement; 2)changer un logement d'etage ?");
                type=Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 OU 2! Voulez vous 1)supprimer une logement; 2)changer un logement d'etage ?");
                    type = Lire.i(); 
                }
                //choix etage
                System.out.println("A quel étage se trouve ce Logement?");
                liste_EtageI = immeubleMod3.getBatiment();
                i=0;
                for (Etage etageaC : liste_EtageI) {
                    System.out.print("Etage "+ i);
                    i++;
                    etageaC.toString();
                }
                System.out.print("Indiquer le numero de l'étage choisi");
                etageChoisi = Lire.i();
                etageMod=liste_EtageI.get(etageChoisi);
                
                //choix logement
                i=0;
                liste_Logement=etageMod.getAppartementEtage();
                for (Logement logement : liste_Logement){//choix etage
                    System.out.print("Etage "+ i);
                    i++;
                    logement.toString();
                }
                System.out.print("Indiquer le numero du Logement a supprimer");
                logementChoisi = Lire.i();
                //garde en memoire logement a supprimer (pour si deplacement)
                logementMod=liste_Logement.get(logementChoisi);
                //suppression
                liste_Logement.remove(logementChoisi);
                etageMod.setAppartementEtage(liste_Logement);
                
                //Changement etage
                if (type==2){
                    //choix nouvel etage
                    System.out.println("A quel étage déplacer ce Logement?");
                    liste_EtageI = immeubleMod3.getBatiment();
                    i=0;
                    for (Etage etageaC : liste_EtageI) {
                        System.out.print("Etage "+ i);
                        i++;
                        etageaC.toString();
                    }
                    System.out.print("Indiquer le numero de l'étage choisi");
                    etageChoisi = Lire.i();
                    etageMod=liste_EtageI.get(etageChoisi);
                    //ajout logement supprime dans nouvel etage
                    etageMod.ajouterAppartement(logementMod);
                }
            break;
            case 8://Suprimer une piece et changer une piece de logement 
                System.out.println("Voulez-vous modifier 1) une maison; 2) un immeuble ?");
                type = Lire.i();
                while(type != 1 && type != 2){
                    System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! Votre Batiment est il 1) une maison; 2) un immeuble?");
                    type = Lire.i(); 
                }
                //cas maison
                if (type == 1){
                    //choix maison
                    i=0;
                    for (Maison maison : liste_Maison) {//indique les maisons existentes
                        System.out.print("Maison "+ i);
                        i++;
                        maison.toString();
                    }
                    System.out.print("Indiquer le numero de la Maison selectionnee");
                    maisonChoisi = Lire.i();
                    Maison maisonMod = liste_Maison.get(maisonChoisi);
                    
                    //choix etage
                    liste_EtageM = maisonMod.getBatiment();
                    i=0;
                    for (Etage etage : liste_EtageM) {
                        System.out.print("Etage "+ i);
                        i++;
                        etage.toString();
                    }
                    System.out.print("Indiquez le numero de l'étage sélectionne");
                    etageChoisi = Lire.i();
                    EtageM etageModified = liste_EtageM.get(etageChoisi);
                    
                    //choix piece
                    i=0;
                    liste_Piece=etageModified.getPieceEtage();
                    for (Piece piece : liste_Piece){//choix etage
                        System.out.print("Etage "+ i);
                        i++;
                        piece.toString();
                    }
                    System.out.print("Indiquer le numero de la piece a supprimer/deplacer");
                    pieceChoisi = Lire.i();
                    //garde en memoire piece a supprimer (pour si deplacement)
                    Piece pieceMod=liste_Piece.get(pieceChoisi);
                    //suppression
                    liste_Piece.remove(pieceChoisi);
                    etageModified.setPieceEtage(liste_Piece);
                    
                    //Deplacement
                    System.out.println("voulez-vous 1) déplacer ou 2)supprimer la piece ?");
                    type=Lire.i();
                    while(type != 1 && type != 2){
                        System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! voulez-vous 1) déplacer ou 2)supprimer la piece ?");
                        type = Lire.i(); 
                    }
                    if (type==1){
                    //choix etage
                    System.out.println("Dans quel étage de la maison voulez vous déplacer cette pièce ?");
                    liste_EtageM = maisonMod.getBatiment();
                    i=0;
                    for (Etage etage : liste_EtageM) {
                        System.out.print("Etage "+ i);
                        i++;
                        etage.toString();
                    }
                    System.out.print("Indiquez le numero de l'étage sélectionne");
                    etageChoisi = Lire.i();
                    etageModified = liste_EtageM.get(etageChoisi);
                    etageModified.ajouterPiece(pieceMod);
                    }
                } else {
                    //Choix immeuble
                    i=0;
                    for (Immeuble immeuble : liste_Immeuble) {
                        System.out.print("immeuble "+ i);
                        i++;
                        immeuble.toString();
                    }
                    System.out.print("Indiquer le numero de l'Immeuble sélectionne");
                    immeubleChoisi = Lire.i();
                    immeubleMod3 = liste_Immeuble.get(immeubleChoisi);
                    
                    //choix etage
                    liste_EtageI = immeubleMod3.getBatiment();
                    i=0;
                    for (Etage etageaC : liste_EtageI) {
                        System.out.print("Etage "+ i);
                        i++;
                        etageaC.toString();
                    }
                    System.out.print("Indiquer le numero de l'étage sélectionne");
                    etageChoisi = Lire.i();
                    etageMod = liste_EtageI.get(etageChoisi);
                    
                    //choix logement
                    i=0;
                    liste_Logement=etageMod.getAppartementEtage();
                    for (Logement logement : liste_Logement){
                        System.out.print("Logement "+ i);
                        i++;
                        logement.toString();
                    }
                    System.out.print("Indiquer le numero du Logement choisi");
                    logementChoisi = Lire.i();
                    logementMod=liste_Logement.get(logementChoisi);
                    //choix piece
                    i=0;
                    liste_Piece=logementMod.getAppartement();
                    for (Piece piece : liste_Piece){//choix etage
                        System.out.print("Etage "+ i);
                        i++;
                        piece.toString();
                    }
                    System.out.print("Indiquer le numero de la piece a supprimer/deplacer");
                    pieceChoisi = Lire.i();
                    //garde en memoire piece a supprimer (pour si deplacement)
                    Piece pieceMod=liste_Piece.get(pieceChoisi);
                    //suppression
                    liste_Piece.remove(pieceChoisi);
                    logementMod.setAppartement(liste_Piece);
                    
                    //Deplacement
                    System.out.println("voulez-vous 1) déplacer ou 2)supprimer la piece ?");
                    type=Lire.i();
                    while(type != 1 && type != 2){
                        System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! voulez-vous 1) déplacer ou 2)supprimer la piece ?");
                        type = Lire.i(); 
                    }
                    if (type==1){
                        //choix logement
                        i=0;
                        liste_Logement=etageMod.getAppartementEtage();
                        for (Logement logement : liste_Logement){
                            System.out.print("Logement "+ i);
                            i++;
                            logement.toString();
                        }
                        System.out.print("Indiquer le numero du Logement ou deplacer la piece");
                        logementChoisi = Lire.i();
                        logementMod=liste_Logement.get(logementChoisi);
                        logementMod.ajouterPiece(pieceMod);
                    }
                }
            break;
            case 9 : //Suprimer un mur, le modifier ou changer de piece //attention autres listes : piece + attention pas supprimer piece, liste murs 
                //Choix Mur
                i=0;
                    for (Mur mur : liste_Mur) {
                        System.out.print("Mur "+ i);
                        i++;
                        mur.toString();
                    }
                System.out.print("Indiquer le numero du mur selectionne");
                int murChoisi = Lire.i();
                Mur murMod = liste_Mur.get(murChoisi);
                //Teste si appartient a une piece
                i=0;int j,k;
                int reponse=1;
                //cherche dans maisons existentes
                for (Maison maison : liste_Maison) {
                    liste_EtageM = maison.getBatiment();
                    j=0;
                //cherche dans etages
                     for (EtageM etage : liste_EtageM) {
                        liste_Piece=etage.getPieceEtage();
                        j++;
                        k=0;
                //cherche dans pieces
                        for (Piece piece : liste_Piece){
                            if(reponse==0){
                            if ((piece.getMur1().equals(murMod))||(piece.getMur2().equals(murMod))||(piece.getMur3().equals(murMod))||(piece.getMur4().equals(murMod))){
                                System.out.println("Attention, si vous supprimez ce mur, la piece "+piece.toString()+" dans l'etage "+etage.toString()+" de la maison "+maison.toString()+" sera detruite.");
                                System.out.print("Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                reponse=Lire.i();
                                while(reponse != 0 && reponse != 1){
                                    System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                    type = Lire.i(); 
                                }
                            }}
                        }
                         
                    }   
                    
                }
                if (reponse==0){
                    System.out.println("Aucune action sur le mur selectionne n'a ete effectue");
                }else{
                    liste_Mur.remove(murChoisi);
                    System.out.println("Voulez-vous 1) seulement detruire le mur; 2) le modifier; 3) le changer de piece");
                    
                }
            break;
            case 10 ://System.out.println("10) suprimer un point ou le modifier");
            //Choix coin
                i=0;
                    for (Coin coin : liste_Coin) {
                        System.out.print("Coin "+ i);
                        i++;
                        coin.toString();
                    }
                System.out.print("Indiquer le numero du coin selectionne");
                int coinChoisi = Lire.i();
                Coin coinMod = liste_Coin.get(coinChoisi);
                //teste si appartient a un mur
                reponse=1;
                for (Mur mur : liste_Mur){
                    if (mur.getCoin1().equals(coinMod)||mur.getCoin2().equals(coinMod)){
                       System.out.print("attention : si vous supprimez ce coin, le mur "+mur.toString()+" sera supprime egalement.");
                       //Teste si mur appartient a une piece
                        i=0;
                        //cherche dans maisons existentes
                        for (Maison maison : liste_Maison) {
                            liste_EtageM = maison.getBatiment();
                            j=0;
                        //cherche dans etages
                             for (EtageM etage : liste_EtageM) {
                                liste_Piece=etage.getPieceEtage();
                                j++;
                                k=0;
                        //cherche dans pieces
                                for (Piece piece : liste_Piece){
                                    if(reponse==0){
                                    if ((piece.getMur1().equals(mur))||(piece.getMur2().equals(mur))||(piece.getMur3().equals(mur))||(piece.getMur4().equals(mur))){
                                        System.out.println("Attention, si vous supprimez ce mur, la piece "+piece.toString()+" dans l'etage "+etage.toString()+" de la maison "+maison.toString()+" sera detruite.");
                                        System.out.print("Voulez-vous vraiment detruire le coin ? (taper 0 si oui, 1 si non)");
                                        reponse=Lire.i();
                                        while(reponse != 0 && reponse != 1){
                                            System.out.println("ATENTION LES CHOIX POSSIBLES SONT 0 OU 1! Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                            type = Lire.i(); 
                                        }
                                    }}
                                }

                            }   

                        }
                    }
                }
                
                if (reponse==1){
                    System.out.println("Aucune action sur le coin selectionne n'a ete effectuee");
                }else{
                    liste_Coin.remove(coinChoisi);
                    System.out.println("Voulez-vous 1) seulement detruire le coin; 2) le modifier ?");
                    if (Lire.i()==2){
                        System.out.print("Donnez la nouvelle coordonnee X :");
                        coinMod.setX(Lire.d());
                        System.out.print("Donnez la nouvelle coordonnee Y :");
                        coinMod.setY(Lire.d());
                    }
                }
            break;
            case 11://Passer à la partie revetement
                menuRevetement(liste_Mur, liste_Revetement, nbrevetement);
            break;
            default :
                System.out.println("Veuillez entrer un nombre entre 0 et 11.");
            break;
        }
        }while (choix != 0);
    }
    
    public static void menuRevetement(ArrayList<Mur> liste_Mur, ArrayList<Revetement> liste_Revetement, int nbrevetement){
        do{
        System.out.println("Voulez-vous mettre un revetement sur 1) un mur; 2) un sol; 3) un plafond ? Tapez 0 pour arreter");
        switch(Lire.i()){
            case 1:
                //Choix Mur
                int i=0;
                    for (Mur mur : liste_Mur) {
                        System.out.print("Mur "+ i);
                        i++;
                        mur.toString();
                    }
                System.out.print("Indiquer le numero du mur selectionne");
                int murChoisi = Lire.i();
                Mur murMod = liste_Mur.get(murChoisi);
                //interieur/exterieur
                boolean test;
                System.out.println("ce mur est-il 1) interieur ou 2) exterieur ? (cote revetement)");
                if (Lire.i()==1){
                    test=true;
                }else{
                    test=false;
                }
                murMod.setInterior(test);
                //appelle le choix de revetements
                System.out.println("Veuillez choisir un revetement :");
                System.out.println("1) Peinture 1");
                System.out.println("2) Carrelage 1");
                System.out.println("3) Lambris 1");
                System.out.println("4) Marbre");
                System.out.println("5) Crepi");
                System.out.println("6) Papier peint");
                System.out.println("7) Plaquettes de parement");
                System.out.println("8) Peinture 2");
                System.out.println("9) Peinture 3");
                System.out.println("10) Carrelage 2");
                System.out.println("11) Lambris 2");
                System.out.println("12) Liege 1");
                System.out.println("13) Parquet");
                System.out.println("14) Vinyle Lino");
                System.out.println("15) Moquette");
                System.out.println("16) Stratifie");
                System.out.println("17) Gazon");
                System.out.println("18) Liege 2");
                System.out.println("19) Carrelage 3");
                int type=Lire.i();
                while(type != 1 && type != 2 && type != 13 && type != 4 && type != 5 && type != 6 && type != 7 && type != 8 && type != 9 && type != 10 && type != 11 && type != 12 && type != 13 && type != 14 && type != 15 && type != 16 && type != 17 && type != 18 && type != 19 ){
                    System.out.println("ATENTION LES CHOIX POSSIBLE SONT ENTRE 1 ET 19 ! Veuillez choisir un revetement :");
                    type = Lire.i(); 
                }
                Revetement revetement=new Revetement(nbrevetement);
                revetement.Parametres(type);
                if (revetement.isPourMur()==true){
                    revetement.getSurface(3);
                }
            break;
            case 2:
                
            break;
            case 3:
                
            break;
            default:
                System.out.println("Veuillez entrer un nombre entre 0 et 3.");
            break;
        }
        }while(Lire.i()!=0);

        
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
    int nbrCoin=0, nbrMur=0;
    ArrayList<Revetement> liste_Revetement=new ArrayList<Revetement>();
    int nbrevetement=0;
    Revetement revetement=new Revetement(nbrevetement);
    liste_Revetement.add(revetement);
    ArrayList<Batiment> liste_Batiment=new ArrayList<Batiment>();
    ArrayList<Maison> liste_Maison=new ArrayList<Maison>();
    ArrayList<Immeuble> liste_Immeuble=new ArrayList<Immeuble>();
    ArrayList<Coin> liste_Coin=new ArrayList<Coin>();
    ArrayList<Mur> liste_Mur=new ArrayList<Mur>();
    System.out.println("Bonjour, bienvenue dans notre générateur de devis, dans un premier temps nous allons modéliser votre batiment, puis vous pourrez choisir vos revetements de murs, sols et plafonds.");
    System.out.println("Première étape: Créer un batiment pour cela veuiller nous renseigner:");
    System.out.print("L'adresse du Batiment: ");
    String adresse = Lire.S();
    System.out.println("Votre Batiment est il 1) une maison 0) un immeuble?");
    int type = Lire.i();
    while(type != 1 && type != 0){
        System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 ou 2! Votre Batiment est il 1) une maison 2) un immeuble?");
        type = Lire.i(); 
    }
    nbrdeBatiment ++;
    Maison maison= new Maison(nbrdeBatiment,adresse);
    liste_Maison.add(maison);
    nbrdeBatiment ++;
    Immeuble immeuble = new Immeuble(nbrdeBatiment,adresse);
    liste_Immeuble.add(immeuble);
    nbrdeBatiment ++;
    if (type == 1){
        String typeB="Maison";
        Batiment batiment =new Batiment(nbrdeBatiment, typeB);
        //Maison batiment = new Maison(nbrdeBatiment,adresse);
        liste_Batiment.add(batiment);
    }else {
        String typeB="Immeuble";
        Batiment batiment =new Batiment(nbrdeBatiment, typeB);
        //Immeuble batiment = new Immeuble(nbrdeBatiment,adresse);
        liste_Batiment.add(batiment);
    }
    //Batiment batiment=new Batiment();
    //liste_Batiment.add(batiment);
    Coin coin=new Coin(nbrCoin,0,0);
    nbrCoin++;
    liste_Coin.add(coin);
    Mur mur=new Mur(nbrMur, coin, coin);
    liste_Mur.add(mur);
    //liste_Batiment.add(batiment); //pk?
    menu(liste_Batiment, liste_Maison, liste_Immeuble, liste_Coin, liste_Mur, liste_Revetement, nbrevetement);
    
    //menu(liste_Batiment,liste_Coin ,liste_Mur); //les listes ne peuvent pas etre transmise vide?
    
    //calcul des surfaces par revetement
    
    
    
    
    
    }
}
