/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.richard.tp1ninarichard;

/**
 *
 * @author nrichard01
 */
public class TP1NinaRichard {

    public static void main(String[] args) {
        //Coin c1 = new Coin(1,3.15,4.5);
        //System.out.println(c1.toString());
        System.out.println("Bonjour, de quel type est votre batiment?");
        String batimenttype = Lire.S();
        Batiment batiment = new Batiment(1, batimenttype);
        System.out.println("Combien d'étage comporte votre batiment?");
        int nbrEtage = Lire.i();
        int nbrCoin =0;
        for (int i=0; i<nbrEtage ; i++){
            Etage etage = new Etage(i);
            batiment.ajouterEtage(etage);
            System.out.println("Combien de logements comporte votre étage " + i + " ?");
            int nbrAppartement = Lire.i();
            for (int j=1; j<=nbrAppartement ; j++){
                Logement appartement = new Logement(j);
                etage.ajouterAppartement(appartement);
                System.out.println("Combien de piece comporte votre logement " + i + " ?");
                int nbrPiece = Lire.i();
                for (int k=1; k<=nbrAppartement ; k++){
                    System.out.println("Quel est l'utilité de votre piece " + i + " ?");
                    String typePiece = Lire.S();
                    System.out.println("Quels sont les coordonnées des coins de votre mur 1 " + i + " ?");
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
                    //Piece piece = new Piece(k,); //ajouter des coins pour faire des murs pour créé la piece
                    etage.ajouterAppartement(appartement);
                    
                    
            
                }   
            
            }
        }
        
        Porte p1 = new Porte(1);
        double s = p1.Surface();
        System.out.println("la surface vaut "+s);
    }
}
