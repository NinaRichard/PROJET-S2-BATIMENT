/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author mmarescaux01
 */
public class Devis {
    
    //Constructeur
    public Devis() {     
    }
    
    //methode pour creation fichier devis
    public void fichier(double peinture1, double carrelage1, double lambris1, double marbre, double crepi, double papierpeint, double plaquettesdeparement, double peinture2, double peinture3, double carrelage2, double lambris2, double liege1, double parquet, double vinylelino, double moquette, double stratifie, double gazon, double liege2, double carrelage3){
        String adresse="adresse a remplir";
        File file = new File("C:/Users/mmarescaux01/Desktop/Devis.txt");//chemin a modifier si changement de machine __ voir pour choisir ou enregisterer ?
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("fichier cree");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/mmarescaux01/Desktop/Devis.txt"))) {//chemin a modifier si changement de machine
            double a=46;
        //Remplissage entete
            Date dateanglais = new Date();
            DateFormat format=DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
            writer.write("Strasbourg, le "+format.format(dateanglais) );
            writer.newLine();
            writer.write("Adresse : "+adresse);
            writer.newLine();
            writer.newLine();

            // Exemple de tableau à deux dimensions
        String[][] tableau = {
                {"Peinture 1        ", "10,95€  "},
                {"Carrelage 1       ", "49,75€  "},
                {"Lambris 1         ", "50,60€  "},
                {"Marbre            ", "97,85€  "},
                {"Crepi             ", "67,80€  "},
                {"Papier peint      ", "32,90€  "},
                {"Plaquettes de parement", "15,20€  "},
                {"Peinture 2        ", "77,30€  "},
                {"Peinture 3        ", "29,90€  "},
                {"Carrelage 2       ", "89,45€  "},
                {"Lambris 2         ", "42,50€  "},
                {"Liege 1           ", "25,40€  "},
                {"Parquet           ", "46,36€  "},
                {"Vinyle Lino       ", "23,55€  "},
                {"Moquette          ", "48,10€  "},
                {"Stratifie         ", "31,99€  "},
                {"Gazon             ", "17,95€  "},
                {"Liege 2           ", "25,40€  "},
                {"Carrelage 3       ", "10,35€  "},
        };
        double[] tableau3={10.95,49.75,50.60,97.85,67.80,32.90,15.20,77.30,29.90,89.45,42.50,25.40,46.36,23.55,48.10,31.99,17.95,33.90,10.35};
        double[][] tableau2 ={
            {peinture1,tableau3[0]*peinture1},
            {carrelage1,tableau3[1]*carrelage1},
            {lambris1,tableau3[2]*lambris1},
            {marbre,tableau3[3]*marbre},
            {crepi,tableau3[4]*crepi},
            {papierpeint,tableau3[5]*papierpeint},
            {plaquettesdeparement,tableau3[6]*plaquettesdeparement},
            {peinture2,tableau3[7]*peinture2},
            {peinture3,tableau3[8]*peinture3},
            {carrelage2,tableau3[9]*carrelage2},
            {lambris2,tableau3[10]*lambris2},
            {liege1,tableau3[11]*liege1},
            {parquet,tableau3[12]*parquet},
            {vinylelino,tableau3[13]*vinylelino},
            {moquette,tableau3[14]*moquette},
            {stratifie,tableau3[15]*stratifie},
            {gazon,tableau3[16]*gazon},
            {liege2,tableau3[17]*liege2},
            {carrelage3,tableau3[18]*carrelage3},
        };
        
        
        
//calcul total devis
        double total=0;
        int k=0;
        for(k=1;k<19;k++){
            total=total+tableau2[k][1];
        }
        
        
        //Remplissage Tableau devis
        //premiere ligne
        writer.write("Revetement        ");
        writer.write("\t"+"\t"); // Utilisation de tabulation comme séparateur pour les colonnes
        writer.write("Prix Unitaire");
        writer.write("\t"+"\t");
        writer.write("Surface");
        writer.write("\t"+"\t");
        writer.write("Total");
        writer.write("\t"+"\t");
        writer.newLine(); // Ajouter un saut de ligne après chaque ligne du tableau
        for (k=1;k<19;k++){
            //premier tableau :
            writer.write(tableau[k][0]);
            writer.write("\t"+"\t"); // Utilisation de tabulation comme séparateur pour les colonnes
            writer.write(tableau[k][1]);
            writer.write("\t"+"\t");
            //deuxieme tableau :
            writer.write(String.format("%.2f",tableau2[k][0])+" m²");
            if (tableau2[k][0]>=10.00){
                writer.write("\t");
            }else{
                writer.write("\t"+"\t");
            }
            writer.write(String.format("%.2f",tableau2[k][1])+" €");
            writer.write("\t"+"\t");
            
            writer.newLine(); // Ajouter un saut de ligne après chaque ligne du tableau
        }
        writer.write("Total"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+String.format("%.2f",total)+"€");
 
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
}
