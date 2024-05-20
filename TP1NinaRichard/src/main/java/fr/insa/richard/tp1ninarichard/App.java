package fr.insa.richard.tp1ninarichard;

//import java.io.IOException;
import static fr.insa.richard.tp1ninarichard.TP1NinaRichard.menu;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {

    BorderPane mainPane = new BorderPane();

    // Menu items
    RadioButton rbCreation = new RadioButton("Créer");
    RadioButton rbEnlever = new RadioButton("Enlever");
    RadioButton rbChanger = new RadioButton("(é-)Changer");

    // Menu buttons
    Button bCreation = new Button("Menu Création");
    bCreation.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            System.out.println("bouton creation cliquer");
            
            int nbrdeBatiment = 0; 
            int nbrCoin=0, nbrMur=0;
            ArrayList<Revetement> liste_Revetement=new ArrayList<Revetement>();
            int nbrevetement=0;
            Revetement revetement1=new Revetement(nbrevetement);
            liste_Revetement.add(revetement1);
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
            
            int k; int i;int j; int m;
            List<EtageI> liste_EtageI;
            List<EtageM> liste_EtageM;
            List<Logement> logement;
            List<Piece> pieces;
            
            TreeItem<String> root_Menu = new TreeItem<>("MENU");
            root_Menu.setExpanded(true);
            
            m=0;
            for (Immeuble immeuble1 : liste_Immeuble) {
                TreeItem<String> root_Bat = new TreeItem<>("Batiment " + m);
                liste_EtageI = immeuble1.getBatiment();
                for (EtageI etageaC : liste_EtageI) {
                    k =0;
                    TreeItem<String> root_etage = new TreeItem<>("Etage " + k);
                    logement = etageaC.getAppartementEtage();
                    for (Logement logementaC : logement){
                        i=0;
                        TreeItem<String> root_app = new TreeItem<>("Appartement " + i);
                        pieces = logementaC.getAppartement();
                        j=0;
                        for(Piece pieceaC : pieces){
                            j++;
                            root_app.getChildren().add(new TreeItem<>("Piece " + j));
                        }
                        root_etage.getChildren().add(root_app);
                        i++;
                    }
                    root_Bat.getChildren().add(root_etage);
                    k++;
                }
                root_Menu.getChildren().add(root_Bat);
            }
             
            TreeView<String> treeView = new TreeView<>(root_Menu);

            mainPane.setLeft(treeView);
        }
    });

    Button bRevetement = new Button("Menu Revetement");
    bRevetement.setOnAction((t) -> {
        System.out.println("bouton Revetement cliquer");
        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("Revetement 1", new Label("Prix: "));
        TitledPane pane2 = new TitledPane("Revetement 2", new Label("Prix: "));
        accordion.getPanes().addAll(pane1, pane2);
        mainPane.setLeft(accordion);
    });

    // Button bar
    HBox buttonBar = new HBox(20, bCreation, bRevetement,rbCreation, rbEnlever, rbChanger);
    mainPane.setTop(buttonBar);

    // Add the Canvas
     Canvas canvas = new Canvas();
     StackPane canvasContainer = new StackPane(canvas);
     mainPane.setCenter(canvasContainer);

     // Bind the canvas size to the container size
     canvasContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
         canvas.setWidth(newVal.doubleValue());
         redraw(canvas);
     });
     canvasContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
         canvas.setHeight(newVal.doubleValue());
         redraw(canvas);
     });
     TP1NinaRichard menu;
    Scene scene = new Scene(mainPane, 800, 600);
    stage.setScene(scene);
    stage.setTitle("Nouveau");
    stage.show();


}

    // Method to redraw the content on the canvas when resized
    private void redraw(Canvas canvas) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.PINK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getWidth());
        // Add your drawing code here
        context.strokeText("Plan", 10, 10);
    }
    
    public static void main(String[] args) {
        launch();
    }

}