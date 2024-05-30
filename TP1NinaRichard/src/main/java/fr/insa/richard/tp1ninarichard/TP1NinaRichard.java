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
    //Test constructeur pour faire marcher App
    public TP1NinaRichard(){
    }
  
    
    public static void menu(Batiment batiment, ArrayList<Coin> liste_Coin, ArrayList<Mur> liste_Mur, ArrayList<Revetement> liste_Revetement, int nbrevetement){
        //initialisation des variables
        int choix;
        //int nbrBatiment = 1;
        int type;
        int i;
        int batimentChoisi;
        int etageChoisi;
        int maisonChoisi;
        int immeubleChoisi;
        int logementChoisi;
        int pieceChoisi;
        int nbrEtage=0;
        List<Etage> liste_Etage;
        List<EtageI> liste_EtageI;
        List<EtageM> liste_EtageM;
        List<Logement> liste_Logement;
        List<Piece> liste_Piece;
        Logement logementMod;
        
        //creation du batiment (maison ou immeuble suivant cas)
        //cas maison
        if (batiment.getTypeIM()==1){
            Maison maison=new Maison(0, batiment.getAddress());
            
            do{
                //menu
                System.out.println("Voulez-vous : ") ;
                System.out.println("1) Créer un étage et ajouter un étage dans le batiment") ;
                System.out.println("2) Créer une pièce et ajouter une pièce dans un logement");
                System.out.println("3) Créer un mur");
                System.out.println("4) supprimer un étage");
                System.out.println("5) supprimer une pièce ou changer une pièce d'etage");
                System.out.println("6) supprimer un mur, le modifier ou changer de piece");
                System.out.println("7) supprimer un point ou le modifier");
                System.out.println("8) Passer à la partie revetement");
                System.out.println("Pour (annuler ou) arreter taper 0");
                choix = Lire.i();
                switch(choix){
                    case 1://Créer un étage et ajouter un étage dans un batiment
                            System.out.println("Quelle est la hauteur sous plafond de l'etage a ajouter ?");
                            double hauteurSousPlafond=Lire.d();
                            EtageM etageM = new EtageM(maison.getNbrEtage(),hauteurSousPlafond);
                            maison.ajouterEtage(etageM);
                            nbrEtage++;
                    break;
                    case 2://Créer une pièce et ajouter une pièce dans la maison
                        //vérification de l'existence d'au moins un étage
                        if (nbrEtage==0){
                            System.out.println("Vous devez d'abord créer un étage");
                        }else{
                            //choix étage
                            System.out.println("A quel étage voulez-vous ajouter une pièce ?");
                            liste_EtageM = maison.getBatiment();
                            i=0;
                            for (EtageM etageaC : liste_EtageM) {
                                System.out.println("Etage "+ i);
                                System.out.println(etageaC.toString());
                                i++;
                                
                            }
                            System.out.println("Indiquer le numero de l'étage sélectionné : ");
                            etageChoisi = Lire.i();
                            EtageM etageMod4 = liste_EtageM.get(etageChoisi);
                            //creation pièce
                            Piece pieceMod=new Piece(liste_Mur, liste_Coin);
                            etageMod4.ajouterPiece(pieceMod);
                        }
                    break;
                    case 3://Créer un mur
                        //vérification de l'existence d'au moins un étage
                        if (nbrEtage==0){
                            System.out.println("Vous devez d'abord créer un étage");
                        }else{
                            //choix étage
                            liste_EtageM = maison.getBatiment();
                            i=0;
                            for (Etage etage : liste_EtageM) {
                                System.out.println("Etage "+ i);
                                System.out.println(etage.toString());
                                i++;
                            }
                            System.out.println("Indiquez le numero de l'étage sélectionné :");
                            etageChoisi = Lire.i();
                            EtageM etageModified = liste_EtageM.get(etageChoisi);

                            //Creation mur
                            Mur mur= new Mur(liste_Mur, liste_Coin, etageModified);
                            liste_Mur.add(mur);
                            List<Mur> list=etageModified.getMurEtage();
                            list.add(mur);
                            liste_EtageM.get(etageChoisi).setMurEtage(list);
                            System.out.println("Combien de portes voulez-vous ajouter ?");
                            int nbporte=Lire.i();
                            Porte porte=new Porte(1);
                            double surface=mur.getSurface()-nbporte*porte.Surface();
                            if ((surface<=0)||(etageModified.getHauteursousplafond()<=porte.getHauteur())){
                                System.out.println("Il n'y a pas assez de place sur le mur.");
                            }else{
                                mur.setSurface(surface);
                            }
                            System.out.println("Combien de fenetres voulez-vous ajouter ?");
                            int nbfenetre=Lire.i();
                            Fenetre fenetre=new Fenetre(1);
                            surface=mur.getSurface()-nbfenetre*fenetre.Surface();
                            if ((surface<=0)||(etageModified.getHauteursousplafond()<=fenetre.getCote())){
                                System.out.println("Il n'y a pas assez de place sur le mur.");
                            }else{
                                mur.setSurface(surface);
                            }
                            
                        }
                    break;
                    case 4: //supprimer un étage
                        //vérification de l'existence d'au moins un étage
                        if (nbrEtage==0){
                            System.out.println("Vous n'avez pas d'etage a supprimer");
                        }else{
                            //choix étage à supprimer
                            i=0;
                            liste_EtageM=maison.getBatiment();
                            for (Etage etage : liste_EtageM){
                                System.out.println("Etage "+ i);
                                System.out.println(etage.toString());
                                i++;
                            }
                            System.out.println("Indiquer le numero de l'Etage a supprimer :");
                            etageChoisi = Lire.i();
                            //suppression étage
                            liste_EtageM.remove(etageChoisi);
                            maison.setBatiment(liste_EtageM);
                        }
                    break;
                    case 5://Suprimer une pièce ou changer une pièce d'étage
                            //choix étage
                            liste_EtageM = maison.getBatiment();
                            i=0;
                            for (Etage etage : liste_EtageM) {
                                System.out.println("Etage "+ i);
                                System.out.println(etage.toString());
                                i++;
                            }
                            System.out.println("Indiquez le numero de l'étage sélectionné :");
                            etageChoisi = Lire.i();
                            EtageM etageModified = liste_EtageM.get(etageChoisi);
                            
                            //choix pièce
                            i=0;
                            liste_Piece=etageModified.getPieceEtage();
                            for (Piece piece : liste_Piece){//choix étage
                                System.out.println("Piece "+ i);
                                System.out.println(piece.toString());
                                i++;
                            }
                            System.out.println("Indiquer le numero de la piece a supprimer/deplacer :");
                            pieceChoisi = Lire.i();
                            
                            //garde en mémoire pièce a supprimer (pour si deplacement)
                            Piece pieceMod=liste_Piece.get(pieceChoisi);
                            
                            //suppression
                            liste_Piece.remove(pieceChoisi);
                            etageModified.setPieceEtage(liste_Piece);

                            //Déplacement
                            System.out.println("voulez-vous 1) déplacer ou 2)supprimer la piece ?");
                            type=Lire.i();
                            while(type != 1 && type != 2){
                                System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! voulez-vous 1) déplacer ou 2)supprimer la piece ?");
                                type = Lire.i(); 
                            }
                            if (type==1){
                            //choix étage
                            System.out.println("Dans quel étage de la maison voulez-vous déplacer cette pièce ?");
                            liste_EtageM = maison.getBatiment();
                            i=0;
                            for (Etage etage : liste_EtageM) {
                                System.out.println("Etage "+ i);
                                System.out.println(etage.toString());
                                i++;
                            }
                            System.out.println("Indiquez le numero de l'étage sélectionné :");
                            etageChoisi = Lire.i();
                            etageModified = liste_EtageM.get(etageChoisi);
                            etageModified.ajouterPiece(pieceMod);
                            }
                    break;
                    case 6 : //Suprimer un mur, le modifier ou changer de piece //attention autres listes : piece + attention pas supprimer piece, liste murs 
                        //vérification de l'existence d'au moins un mur
                        if (liste_Mur.isEmpty()){
                            System.out.println("Vous n'avez pas de mur a supprimer");
                        }else{
                        //Choix Mur
                        i=0;
                            for (Mur mur1 : liste_Mur) {
                                System.out.println("Mur "+ i);
                                System.out.println(mur1.toString());
                                i++;
                            }
                        System.out.println("Indiquer le numero du mur selectionne :");
                        int murChoisi = Lire.i();
                        Mur murMod = liste_Mur.get(murChoisi);
                        Etage etagem= new Etage();
                        //Teste si appartient a une pièce
                        i=0; int j,k;
                        int reponse=1;
                        liste_EtageM=maison.getBatiment();
                        j=0;
                        //cherche dans etages
                             for (EtageM etage : liste_EtageM) {
                                liste_Piece=etage.getPieceEtage();
                                j++;
                                k=0;
                        //cherche dans pieces
                                for (Piece piece : liste_Piece){
                                    if(reponse==1){
                                        if ((piece.getMur1().equals(murMod))||(piece.getMur2().equals(murMod))||(piece.getMur3().equals(murMod))||(piece.getMur4().equals(murMod))){
                                            System.out.println("Attention, si vous supprimez ce mur, la piece "+piece.toString()+" dans l'etage "+etage.toString()+" de la maison "+maison.toString()+" sera detruite.");
                                            System.out.println("Voulez-vous vraiment detruire le mur ? (taper 1 si oui, 0 si non)");
                                            reponse=Lire.i();
                                            etagem=etage;
                                            while(reponse != 0 && reponse != 1){
                                                System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                                reponse = Lire.i(); 
                                            }
                                        }
                                    }
                                    k++;
                                }

                            }   
                            if (reponse==0){
                                System.out.println("Aucune action sur le mur selectionné n'a été effectue");
                            }else{
                                liste_Mur.remove(murChoisi);
                                List<Mur> list=etagem.getMurEtage();
                                list.remove(murChoisi);
                                etagem.setMurEtage(list);
                                
                                j=0;
                                //suppression pieces si besoin
                                //cherche dans etages
                                     for (EtageM etage : liste_EtageM) {
                                        liste_Piece=etage.getPieceEtage();
                                        j++;
                                        k=0;
                                //cherche dans pieces
                                        for (Piece piece : liste_Piece){
                                            if(reponse==1){
                                                if ((piece.getMur1().equals(murMod))||(piece.getMur2().equals(murMod))||(piece.getMur3().equals(murMod))||(piece.getMur4().equals(murMod))){
                                                    etage.getPieceEtage().remove(piece);
                                                }
                                            }
                                            k++;
                                        }

                                    }   
                                
                            }
                        }
                    break;
                    case 7 :// supprimer un point ou le modifier
                    //Choix coin
                        i=0;
                            for (Coin coin : liste_Coin) {
                                System.out.println("Coin "+ i);
                                System.out.println(coin.toString());
                                i++;
                            }
                        System.out.println("Indiquer le numéro du coin sélectionné :");
                        int coinChoisi = Lire.i();
                        Coin coinMod = liste_Coin.get(coinChoisi);
                        //teste si appartient à un mur
                        int reponse=0;
                        for (Mur mur1 : liste_Mur){
                            if (mur1.getCoin1().equals(coinMod)||mur1.getCoin2().equals(coinMod)){
                               System.out.println("attention : si vous supprimez ce coin, le mur "+mur1.toString()+" sera supprime egalement.");
                               //Teste si mur appartient à une pièce
                                i=0;int j=0;
                                //cherche dans étages
                                liste_EtageM=maison.getBatiment();
                                     for (EtageM etage : liste_EtageM) {
                                        liste_Piece=etage.getPieceEtage();
                                        j++;
                                        int k=0;
                                //cherche dans pieces
                                        for (Piece piece : liste_Piece){
                                            if(reponse==0){
                                                if ((piece.getMur1().equals(mur1))||(piece.getMur2().equals(mur1))||(piece.getMur3().equals(mur1))||(piece.getMur4().equals(mur1))){
                                                    System.out.println("Attention, si vous supprimez ce mur, la piece "+piece.toString()+" dans l'étage "+etage.toString()+" sera detruite.");
                                                    System.out.println("Voulez-vous vraiment detruire le coin ? (taper 0 si oui, 1 si non)");
                                                    reponse=Lire.i();
                                                    while(reponse != 0 && reponse != 1){
                                                        System.out.println("ATENTION LES CHOIX POSSIBLES SONT 0 OU 1! Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                                        reponse = Lire.i(); 
                                                    }
                                                }
                                            }
                                            k++;
                                        }

                                    }   

                                }
                            }
                        if (reponse==1){
                            System.out.println("Aucune action sur le coin selectionne n'a ete effectuee");
                        }else{
                            System.out.println("Voulez-vous 1) seulement detruire le coin; 2) le modifier ?");
                            if (Lire.i()==1){
                                liste_Coin.remove(coinChoisi);
                            }else{
                                System.out.println("Donnez la nouvelle coordonnee X :");
                                coinMod.setX(Lire.d());
                                System.out.println("Donnez la nouvelle coordonnee Y :");
                                coinMod.setY(Lire.d());
                                System.out.println("Le coin a ete effectue avec succes");
                            }
                        }
                    break;
                    case 8://Passer à la partie revêtement
                        int compt;
                        pieceMod=new Piece();
                        do {
                            System.out.println("Voulez-vous mettre un revetement sur 1) un mur; 2) un sol; 3) un plafond ? Tapez 0 pour arreter");
                            compt=Lire.i();
                            if ((compt==2)||(compt==3)){//cherche piece si plafond ou sol
                                    //choix étage
                                    liste_EtageM = maison.getBatiment();
                                    i=0;
                                    for (Etage etage : liste_EtageM) {
                                        System.out.println("Etage "+ i);
                                        System.out.println(etage.toString());
                                        i++;
                                    }
                                    System.out.println("Indiquez le numero de l'étage sélectionné :");
                                    etageChoisi = Lire.i();
                                    etageModified = liste_EtageM.get(etageChoisi);

                                    //choix piece
                                    i=0;
                                    liste_Piece=etageModified.getPieceEtage();
                                    for (Piece piece : liste_Piece){//choix étage
                                        System.out.println("Piece "+ i);
                                        System.out.println(piece.toString());
                                        i++;
                                    }
                                    System.out.println("Indiquer le numero de la piece a supprimer/deplacer :");
                                    pieceChoisi = Lire.i();
                                    pieceMod=liste_Piece.get(pieceChoisi);
                            }
                            menuRevetement(liste_Mur, liste_Revetement, nbrevetement, compt, pieceMod);
                        }while(compt!=0);
                    break;
                    default :
                        System.out.println("Veuillez entrer un nombre entre 0 et 11.");
                    break;
                }
                }while (choix != 0);

        //choix immeuble
        }else{
            int nbrLogement=0;
            Immeuble immeuble=new Immeuble(0,batiment.getAddress());
            
            //lancement actions pour construire l'immeuble
            do{
                //menu
                System.out.println("Voulez-vous : ") ;
                System.out.println("2) Créer un étage et ajouter un étage dans le batiment") ;
                System.out.println("3) Créer un logement et ajouter un logement dans l'étage");
                System.out.println("4) Créer une pièce et ajouter une pièce dans un logement");
                System.out.println("5) Créer un mur");
                System.out.println("6) supprimer un étage");
                System.out.println("7) supprimer un logement ou changer un logement d'étage");
                System.out.println("8) supprimer une pièce et changer une pièce de logement");
                System.out.println("9) supprimer un mur, le modifier ou changer de piece");
                System.out.println("10) supprimer un point ou le modifier");
                System.out.println("11) Passer à la partie revetement");
                System.out.println("Pour (annuler ou) arreter taper 0");
                choix = Lire.i();
                switch(choix){
                    case 2://Créer un étage et ajouter un étage dans un batiment
                        System.out.println("Quelle est la hauteur sous plafond de cet étage?");
                        double hauteurSousPlafond=Lire.d();
                        EtageI etageI = new EtageI(immeuble.getNbrEtage(),hauteurSousPlafond);
                        immeuble.ajouterEtage(etageI);
                        nbrEtage++;
                break;
                case 3: //Créer un logement et ajouter un logement dans un étage
                    //vérification de l'existence d'un etage
                    if (nbrEtage==0){
                            System.out.println("Vous devez d'abord créer un étage");
                    }else{
                    //choix étage
                    System.out.println("A quel étage voulez-vous ajouter un Logement ?");
                    liste_EtageI = immeuble.getBatiment();
                    i=0;
                    for (Etage etageaC : liste_EtageI) {
                        System.out.println("Etage "+ i);
                        System.out.println(etageaC.toString());
                        i++;
                    }
                    System.out.println("Indiquer le numero de l'étage sélectionné :");
                    etageChoisi = Lire.i();
                    EtageI etageMod = liste_EtageI.get(etageChoisi);
                    //creation logement
                    Logement appartement = new Logement(etageMod.getNbrdappart());
                    etageMod.ajouterAppartement(appartement);
                    nbrLogement++;
                    }
                break;
                case 4://Créer une pièce et ajouter une pièce dans un logement
                    //vérification de l'existence d'un etage
                    if (nbrEtage==0){
                            System.out.println("Vous devez d'abord créer un étage");
                        }else{
                        if (nbrLogement==0){
                            System.out.println("Vous devez d'abord créer un logement");
                        }else{
                    
                        i=0;
                        //choix étage
                        System.out.println("A quel étage voulez vous ajouter un étage ?");
                        liste_EtageI = immeuble.getBatiment();
                        i=0;
                        for (EtageI etageaC : liste_EtageI) {
                            System.out.println("Etage "+ i);
                            System.out.println(etageaC.toString());
                            i++;
                        }
                        System.out.println("Indiquez le numero de l'étage sélectionné :");
                        etageChoisi = Lire.i();
                        EtageI etageMod4 = liste_EtageI.get(etageChoisi);
                        
                        //choix Logement
                        i=0;
                        liste_Logement=etageMod4.getAppartementEtage();
                        for (Logement logement : liste_Logement){//choix étage
                            System.out.println("Logement "+ i);
                            System.out.println(logement.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero du Logement dans lequel ajouter une pièce :");
                        logementChoisi = Lire.i();
                        logementMod=liste_Logement.get(logementChoisi);

                        //Ajouter piece dans logement choisi
                        Piece pieceMod=new Piece(liste_Mur, liste_Coin);
                        logementMod.ajouterPiece(pieceMod);
                        }}
                break;
                case 5://Créer un mur
                        i=0;
                        //vérification de l'existence d'un etage
                        if (nbrEtage==0){
                            System.out.println("Vous devez d'abord créer un étage");
                        }else{
                        //choix étage
                        liste_EtageI = immeuble.getBatiment();
                        i=0;
                        for (Etage etageaC : liste_EtageI) {
                            System.out.println("Etage "+ i);
                            System.out.println(etageaC.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero de l'étage sélectionné :");
                        etageChoisi = Lire.i();
                        EtageI etageMod = liste_EtageI.get(etageChoisi);

                        //Creation mur
                        Mur mur= new Mur(liste_Mur, liste_Coin, etageMod);
                        liste_Mur.add(mur);
                        List<Mur> list=liste_EtageI.get(etageChoisi).getMurEtage();
                        liste_EtageI.get(etageChoisi).setMurEtage(list);
                        }
                break;
                case 6://supprimer un étage
                        i=0;
                        //vérification de l'existence d'un etage
                        if (nbrEtage==0){
                            System.out.println("Vous n'avez pas d'etage a supprimer");
                        }else{
                        //choix étage a supprimer
                        liste_EtageI=immeuble.getBatiment();
                        for (Etage etage : liste_EtageI){//choix étage
                            System.out.println("Etage "+ i);
                            System.out.println(etage.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero de l'Etage a supprimer :");
                        etageChoisi = Lire.i();
                        //suppression etage
                        liste_EtageI.remove(etageChoisi);
                        immeuble.setBatiment(liste_EtageI);
                        }
                break;
                case 7 ://supprimer un logement ou changer un logement d'étage
                    System.out.println("Voulez vous 1)supprimer une logement; 2)changer un logement d'etage ?");
                    type=Lire.i();
                    while(type != 1 && type != 2){
                        System.out.println("ATENTION LES CHOIX POSSIBLE SONT 1 OU 2! Voulez vous 1)supprimer une logement; 2)changer un logement d'etage ?");
                        type = Lire.i(); 
                    }
                    //choix étage
                    System.out.println("A quel étage se trouve ce Logement?");
                    liste_EtageI = immeuble.getBatiment();
                    i=0;
                    for (Etage etageaC : liste_EtageI) {
                        System.out.println("Etage "+ i);
                        System.out.println(etageaC.toString());
                        i++;
                    }
                    System.out.println("Indiquer le numero de l'étage choisi :");
                    etageChoisi = Lire.i();
                    EtageI etageMod=liste_EtageI.get(etageChoisi);

                    //choix logement
                    i=0;
                    liste_Logement=etageMod.getAppartementEtage();
                    for (Logement logement : liste_Logement){
                        System.out.println("Logement "+ i);
                        System.out.println(logement.toString());
                        i++;
                    }
                    System.out.println("Indiquer le numero du Logement a supprimer :");
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
                        liste_EtageI = immeuble.getBatiment();
                        i=0;
                        for (Etage etageaC : liste_EtageI) {
                            System.out.println("Etage "+ i);
                            System.out.println(etageaC.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero du logement choisi :");
                        etageChoisi = Lire.i();
                        etageMod=liste_EtageI.get(etageChoisi);
                        //ajout du logement supprimé dans nouvel etage
                        etageMod.ajouterAppartement(logementMod);
                    }
                break;
                case 8://Suprimer une pièce et changer une pièce de logement 
                        //choix étage
                        liste_EtageI = immeuble.getBatiment();
                        i=0;
                        for (Etage etageaC : liste_EtageI) {
                            System.out.println("Etage "+ i);
                            System.out.println(etageaC.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero de l'étage sélectionné :");
                        etageChoisi = Lire.i();
                        etageMod = liste_EtageI.get(etageChoisi);

                        //choix logement
                        i=0;
                        liste_Logement=etageMod.getAppartementEtage();
                        for (Logement logement : liste_Logement){
                            System.out.println("Logement "+ i);
                            System.out.println(logement.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero du Logement choisi :");
                        logementChoisi = Lire.i();
                        logementMod=liste_Logement.get(logementChoisi);
                        
                        //choix piece
                        i=0;
                        liste_Piece=logementMod.getAppartement();
                        for (Piece piece : liste_Piece){//choix étage
                            System.out.println("Piece "+ i);
                            System.out.println(piece.toString());
                            i++;
                        }
                        System.out.println("Indiquer le numero de la piece a supprimer/deplacer :");
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
                                System.out.println("Logement "+ i);
                                System.out.println(logement.toString());
                                i++;
                            }
                            System.out.println("Indiquer le numero du Logement où deplacer la piece :");
                            logementChoisi = Lire.i();
                            logementMod=liste_Logement.get(logementChoisi);
                            logementMod.ajouterPiece(pieceMod);
                        }
                break;
                case 9 : //Suprimer un mur, le modifier ou changer de piece 
                    //Choix Mur
                    i=0;
                        for (Mur mur1 : liste_Mur) {
                            System.out.println("Mur "+ i);
                            System.out.println(mur1.toString());
                            i++;
                        }
                    System.out.println("Indiquer le numero du mur selectionne :");
                    int murChoisi = Lire.i();
                    Mur murMod = liste_Mur.get(murChoisi);
                    //Teste si appartient a une pièce qui serait supprimee
                    i=0;int j,k;
                    int reponse=0;
                    liste_EtageI=immeuble.getBatiment();
                    //cherche dans etages
                    for (EtageI etage : liste_EtageI) {
                        liste_Logement=etage.getAppartementEtage();
                        i++;
                        j=0;
                    //cherche dans logements    
                        for (Logement logement : liste_Logement){
                            liste_Piece=logement.getAppartement();
                            k=0;
                    //cherche dans pieces
                            for (Piece piece : liste_Piece){
                                if(reponse==0){
                                    if ((piece.getMur1().equals(murMod))||(piece.getMur2().equals(murMod))||(piece.getMur3().equals(murMod))||(piece.getMur4().equals(murMod))){
                                        System.out.println("Attention, si vous supprimez ce mur, la piece "+piece.toString()+" dans le logement  "+logement.toString()+" dans l'etage "+etage.toString()+" sera detruite.");
                                        System.out.println("Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                        reponse=Lire.i();
                                        while(reponse != 0 && reponse != 1){
                                            System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 OU 2! Voulez-vous vraiment detruire le mur ? (taper 0 si oui, 1 si non)");
                                            reponse = Lire.i(); 
                                        }
                                    }
                                }
                                k++;
                            }

                        }   

                    }
                    if (reponse==1){
                        System.out.println("Aucune action sur le mur selectionne n'a ete effectue");
                    }else{
                        liste_Mur.remove(murChoisi);
                        liste_EtageI=immeuble.getBatiment();
                        //suppression pieces si besoin
                        //cherche dans etages
                        for (EtageI etage : liste_EtageI) {
                            liste_Logement=etage.getAppartementEtage();
                            i++;
                            j=0;
                        //cherche dans logements    
                            for (Logement logement : liste_Logement){
                                liste_Piece=logement.getAppartement();
                                k=0;
                        //cherche dans pieces
                                for (Piece piece : liste_Piece){
                                        if ((piece.getMur1().equals(murMod))||(piece.getMur2().equals(murMod))||(piece.getMur3().equals(murMod))||(piece.getMur4().equals(murMod))){
                                            logement.getAppartement().remove(piece);
                                        }
                                    k++;
                                }
                            }
                        }
                        System.out.println("Le mur a ete supprime avec succes");
                    }
                break;
                case 10 ://System.out.println("10) supprimer un coin ou le modifier");
                //Choix coin
                    i=0;
                        for (Coin coin : liste_Coin) {
                            System.out.println("Coin "+ i);
                            System.out.println(coin.toString());
                            i++;
                        }
                    System.out.println("Indiquer le numero du coin selectionne :");
                    int coinChoisi = Lire.i();
                    Coin coinMod = liste_Coin.get(coinChoisi);
                    //teste si appartient a un mur
                    reponse=0;
                    for (Mur mur1 : liste_Mur){
                        if (mur1.getCoin1().equals(coinMod)||mur1.getCoin2().equals(coinMod)){
                           System.out.println("attention : si vous supprimez ce coin, le mur "+mur1.toString()+" sera supprime egalement.");
                           //Teste si mur appartient a une pièce
                            i=0;
                            liste_EtageI=immeuble.getBatiment();
                            //cherche dans etages
                            for (EtageI etage : liste_EtageI) {
                                liste_Logement=etage.getAppartementEtage();
                                i++;
                                j=0;
                            //cherche dans logements
                                for (Logement logement : liste_Logement){
                                    liste_Piece=logement.getAppartement();
                                    j++;
                                    k=0;
                            //cherche dans pieces
                                    for (Piece piece : liste_Piece){
                                        if(reponse==0){
                                            if ((piece.getMur1().equals(mur1))||(piece.getMur2().equals(mur1))||(piece.getMur3().equals(mur1))||(piece.getMur4().equals(mur1))){
                                                System.out.println("Attention, si vous supprimez ce coin, la piece "+piece.toString()+" dans le logement  "+logement.toString()+" dans l'etage "+etage.toString()+" sera detruite.");
                                                System.out.println("Voulez-vous vraiment detruire le coin ? (taper 0 si oui, 1 si non)");
                                                reponse=Lire.i();
                                                while(reponse != 0 && reponse != 1){
                                                    System.out.println("ATENTION LES CHOIX POSSIBLES SONT 0 OU 1! Voulez-vous vraiment detruire le coin ? (taper 0 si oui, 1 si non)");
                                                    reponse = Lire.i(); 
                                                }
                                            }
                                        }
                                        k++;
                                    }

                                }   

                            }
                        }
                    }

                    if (reponse==1){
                        System.out.println("Aucune action sur le coin selectionne n'a ete effectuee");
                    }else{
                        System.out.println("Voulez-vous 1) seulement detruire le coin; 2) le modifier ?");
                        if (Lire.i()==1){
                            liste_Coin.remove(coinChoisi);
                        }else{
                            System.out.println("Donnez la nouvelle coordonnee X :");
                            coinMod.setX(Lire.d());
                            System.out.println("Donnez la nouvelle coordonnee Y :");
                            coinMod.setY(Lire.d());
                        }
                    }
                break;
                case 11://Passer à la partie revetement
                    int compt;
                    pieceMod=new Piece();
                    do {
                        System.out.println("Voulez-vous mettre un revetement sur 1) un mur; 2) un sol; 3) un plafond ? Tapez 0 pour arreter");
                        compt=Lire.i();
                        if ((compt==2)||(compt==3)){//cherche piece si plafond ou sol
                            //choix étage
                                liste_EtageI = immeuble.getBatiment();
                                i=0;
                                for (Etage etageaC : liste_EtageI) {
                                    System.out.println("Etage "+ i);
                                    System.out.println(etageaC.toString());
                                    i++;
                                }
                                System.out.println("Indiquer le numero de l'étage sélectionné :");
                                etageChoisi = Lire.i();
                                etageMod = liste_EtageI.get(etageChoisi);

                            //choix logement
                                i=0;
                                liste_Logement=etageMod.getAppartementEtage();
                                for (Logement logement : liste_Logement){
                                    System.out.println("Logement "+ i);
                                    System.out.println(logement.toString());
                                    i++;
                                }
                                System.out.println("Indiquer le numero du Logement choisi :");
                                logementChoisi = Lire.i();
                                logementMod=liste_Logement.get(logementChoisi);
                                
                            //choix piece
                                i=0;
                                liste_Piece=logementMod.getAppartement();
                                for (Piece piece : liste_Piece){
                                    System.out.println("Piece "+ i);
                                    System.out.println(piece.toString());
                                    i++;
                                }
                                System.out.println("Indiquer le numero de la piece a supprimer/deplacer :");
                                pieceChoisi = Lire.i();
                                pieceMod=liste_Piece.get(pieceChoisi);
                                }
                                menuRevetement(liste_Mur, liste_Revetement, nbrevetement, compt, pieceMod);
                    }while(compt!=0);
                break;
                default :
                    System.out.println("Veuillez entrer un nombre entre 0 et 11.");
                break;
            }
            }while (choix != 0);
        }
    }
        
    
    public static void menuRevetement(ArrayList<Mur> liste_Mur, ArrayList<Revetement> liste_Revetement, int nbrevetement, int compt, Piece pieceMod){
        switch(compt){
            case 1:
                //Choix Mur
                int i=0;
                    for (Mur mur : liste_Mur) {
                        System.out.println("Mur "+ i);
                        i++;
                        mur.toString();
                    }
                System.out.println("Indiquer le numero du mur selectionne");
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
                Revetement revetement=new Revetement(nbrevetement);
                nbrevetement++;
                revetement.setPourMur(false);
                do{
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
                revetement.Parametres(type);
                }while(revetement.isPourMur()==false);
                revetement.setSurface(murMod.getSurface());
                liste_Revetement.add(revetement);
            break;
            case 2:
                //appelle le choix de revetements
                revetement=new Revetement(nbrevetement);
                nbrevetement++;
                revetement.setPourSol(false);
                do{
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
                    System.out.println("ATENTION LES CHOIX POSSIBLES SONT ENTRE 1 ET 19 ! Veuillez choisir un revetement :");
                    type = Lire.i(); 
                }
                revetement.Parametres(type);
                }while(revetement.isPourSol()==false);
                revetement.setSurface(pieceMod.Surface());
                liste_Revetement.add(revetement);
            break;
            case 3:
                //appelle le choix de revetements
                revetement=new Revetement(nbrevetement);
                nbrevetement++;
                revetement.setPourPlafond(false);
                do{
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
                revetement.Parametres(type);
                }while(revetement.isPourPlafond()==false);
                revetement.setSurface(pieceMod.Surface());
                liste_Revetement.add(revetement);
            break;
            default:
                System.out.println("Veuillez entrer un nombre entre 0 et 3.");
            break;
        }
    }

    public static void main(String[] args) {
        
        Revetement revet=new Revetement(1);
        revet.Parametres(5);
        System.out.println("lecture fichier reussie");
        
        
    int nbrCoin=0, nbrMur=0;
    ArrayList<Revetement> liste_Revetement=new ArrayList<Revetement>();
    int nbrevetement=0;
    Revetement revetement1=new Revetement(nbrevetement);
    liste_Revetement.add(revetement1);
    ArrayList<Coin> liste_Coin=new ArrayList<Coin>();
    ArrayList<Mur> liste_Mur=new ArrayList<Mur>();
    System.out.println("Bonjour, bienvenue dans notre générateur de devis, dans un premier temps nous allons modéliser votre batiment, puis vous pourrez choisir vos revetements de murs, sols et plafonds.");
    System.out.println("Première étape: Créer un batiment. Pour cela veuiller nous renseigner :");
    System.out.println("L'adresse du Batiment: ");
    String adresse = Lire.S();
    System.out.println("Votre Batiment est il 1) une maison 0) un immeuble?");
    int type = Lire.i();
    while(type != 1 && type != 0){
        System.out.println("ATENTION LES CHOIX POSSIBLES SONT 1 ou 2! Votre Batiment est il 1) une maison 2) un immeuble?");
        type = Lire.i(); 
    }
    Batiment batiment=new Batiment(0,type);
    
    //initialisation des listes coin et mur
    Coin coin=new Coin(nbrCoin,0,0);
    nbrCoin++;
    liste_Coin.add(coin);
    Mur mur=new Mur(nbrMur, coin, coin);
    liste_Mur.add(mur);
    
    //lancement menu pour creer le batiment et choisir les revetements
    menu(batiment, liste_Coin, liste_Mur, liste_Revetement, nbrevetement);
    
    //calcul des surfaces par revetement
    int i=0;
    double peinture1=0, carrelage1=0, lambris1=0, marbre=0, crepi=0, papierpeint=0, plaquettesdeparement=0, peinture2=0, peinture3=0, carrelage2=0, lambris2=0, liege1=0, parquet=0, vinylelino=0, moquette=0, stratifie=0, gazon=0, liege2=0, carrelage3=0;
    for (Revetement revetement : liste_Revetement){
        int id=revetement.getIdRevetement();
        switch (id) {
            case 125 :
                peinture1=peinture1+revetement.getSurface();
            break;
            case 23 :
                carrelage1=carrelage1+revetement.getSurface();
            break;
            case 43 :
                lambris1=lambris1+revetement.getSurface();
            break;
            case 48 :
                marbre=marbre+revetement.getSurface();
            break;
            case 105 :
                crepi=crepi+revetement.getSurface();
            break;
            case 60 :
                papierpeint=papierpeint+revetement.getSurface();
            break;
            case 75 :
                plaquettesdeparement=plaquettesdeparement+revetement.getSurface();
            break;
            case 8 :
                peinture2=peinture2+revetement.getSurface();
            break;
            case 19 :
                peinture3=peinture3+revetement.getSurface();
            break;
            case 15 :
                carrelage2=carrelage2+revetement.getSurface();
            break;
            case 110 :
                lambris2=lambris2+revetement.getSurface();
            break;
            case 102 :
                liege1=liege1+revetement.getSurface();
            break;
            case 132 :
                parquet=parquet+revetement.getSurface();
            break;
            case 114 :
                vinylelino=vinylelino+revetement.getSurface();
            break;
            case 156 :
                moquette=moquette+revetement.getSurface();
            break;
            case 1126 :
                stratifie=stratifie+revetement.getSurface();
            break;
            case 174 :
                gazon=gazon+revetement.getSurface();
            break;
            case 180 :
                liege2=liege2+revetement.getSurface();
            break;
            case 115 :
                carrelage3=carrelage3+revetement.getSurface();
            break;
        }    
    }
    //creation devis
    Devis devis=new Devis();
    devis.setAdresse(adresse);
    devis.fichier(peinture1, carrelage1, lambris1, marbre, crepi, papierpeint, plaquettesdeparement, peinture2, peinture3, carrelage2, lambris2, liege1, parquet, 
            vinylelino, moquette, stratifie, gazon, liege2, carrelage3);
    
    
    
    }
}
