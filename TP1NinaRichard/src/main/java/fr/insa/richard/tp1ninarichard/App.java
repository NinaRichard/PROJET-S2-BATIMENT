package fr.insa.richard.tp1ninarichard;

//import java.io.IOException;
import java.awt.event.MouseEvent;
import java.util.List;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
/*
        BorderPane mainPane = new BorderPane();
        //NE DEVRAIT APPARATRE QUE EN MENU 
        RadioButton rbCreation = new RadioButton("Créer");
        RadioButton rbEnlever = new RadioButton("Enlever");
        RadioButton rbChanger = new RadioButton("(é-)Changer");
        
        
        VBox vbDroite = new VBox(rbCreation, rbEnlever, rbChanger);
        mainPane.setRight(vbDroite);
        
        Button bCreation = new Button("Menu Création");
        //Premiere syntaxe plus explicite

        bCreation.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                System.out.println("bouton creation cliquer");
                // Créé root3_appartement contemant des Strings(indiquant les pieces)
                TreeItem<String> root3_appartement = new TreeItem<String>("Appartement "); //peut on mettre ça après pour avoir le num de l'appart
                //Créé root2_Etage contenant des appartements
                TreeItem<TreeView> root2 = new TreeItem<TreeView>(new TreeView<String>(root3_appartement));
                root2.setExpanded(true);
                //variable de test (à changer avec la taille des listes d'appart
                int nbrAppartement= 3;
                int nbrPiece=3;
                //pour la quantite d'appartement faire un tree Item de root2_Etage contant des pieces
                for (int i =0; i<nbrAppartement ; i++){
                    for(int j = 0 ; j<nbrPiece; j++){
                    root3_appartement.getChildren().add(
                            new TreeItem<String>("Piece "+ j)
                    );
                    }
                    root2.getChildren().add(
                       new TreeItem<TreeView>(new TreeView<String>(root3_appartement)) 
                    );    
                 }
                
                TreeItem<TreeView> root = new TreeItem<TreeView>(new TreeView<TreeView>(root2));
                root.setExpanded(true);
                root.getChildren().addAll(
                    new TreeItem<TreeView>(new TreeView<TreeView>(root2)),
                    new TreeItem<TreeView>(new TreeView<TreeView>(root2)),
                    new TreeItem<TreeView>(new TreeView<TreeView>(root2))
                );
                TreeView<TreeView> treeView = new TreeView<TreeView>(root);
        
                mainPane.setLeft(treeView);
            }
        });


        Button bRevetement = new Button("Menu Revetement");
        //Deuxieme syntaxe plus compact
        bRevetement.setOnAction((t) -> {
            System.out.println("bouton Revetement cliquer");
            Accordion accordion = new Accordion();
            //mettre plus de truc addapté avec des for
            TitledPane pane1 = new TitledPane("Revetement 1", new Label("Prix: "));
            TitledPane pane2 = new TitledPane("Revetement 2", new Label("Prix: "));
            accordion.getPanes().add(pane1);
            accordion.getPanes().add(pane2);
            mainPane.setLeft(accordion);
        });
        //ici la premier redac ne marche pas on sait pas pk!
        bRevetement.setOnMouseEntered((t)->{
                    System.out.println("enterder  on: " + t.getX() +", "+ t.getY());
        }
        );
        HBox buttonBar = new HBox(20, bCreation,bRevetement);
        mainPane.setTop(buttonBar);
        
        Scene sc = new Scene(mainPane);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
          stage.show();
*/
BorderPane mainPane = new BorderPane();

// Menu items
RadioButton rbCreation = new RadioButton("Créer");
RadioButton rbEnlever = new RadioButton("Enlever");
RadioButton rbChanger = new RadioButton("(é-)Changer");

// Right VBox for menu items
VBox vbDroite = new VBox(rbCreation, rbEnlever, rbChanger);
mainPane.setRight(vbDroite);

// Menu buttons
Button bCreation = new Button("Menu Création");
bCreation.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent t) {
        System.out.println("bouton creation cliquer");
        Immeuble batiment = new Immeuble(1, "112 rue du test, 67154 Informatique-sur-Le-Rhin");
        
        TreeItem<String> root_Bat = new TreeItem<>("Batiment");
        root_Bat.setExpanded(true);
        int k; int i;
        List<EtageI> liste_EtageI = batiment.getBatiment();
        List<Logement> logement;
        List<Piece> pieces;
        for (EtageI etageaC : liste_EtageI) {
            k =0;
            TreeItem<String> root_etage = new TreeItem<>("Etage " + k);
            logement = etageaC.getAppartementEtage();
            for (Logement logementaC : logement){
                i=0;
                TreeItem<String> root_app = new TreeItem<>("Appartement " + i);
                pieces = logementaC.getAppartement();
                for(Piece pieceaC : pieces){
                    root_app.getChildren().add(new TreeItem<>("Piece " + j));
                }
                /*
                for (int j = 0; j < nbrPiece; j++) {
                    root_app.getChildren().add(new TreeItem<>("Piece " + j));
                }
                */
                root_etage.getChildren().add(root_app);
                i++;
            }
            /*
            int nbrAppartement = 3;
            int nbrPiece = 3;
            */
            
            /*
            for (int i = 0; i < nbrAppartement; i++) {
                TreeItem<String> root_app = new TreeItem<>("Appartement " + i);
                for (int j = 0; j < nbrPiece; j++) {
                    root_app.getChildren().add(new TreeItem<>("Piece " + j));
                }
                root_etage.getChildren().add(root_app);
            }*/
            root_Bat.getChildren().add(root_etage);
            k++;
        } 
        /*
        for (int k = 0; k < nbrEtage; k++ ){
            TreeItem<String> root_etage = new TreeItem<>("Etage");
            int nbrAppartement = 3;
            int nbrPiece = 3;

            for (int i = 0; i < nbrAppartement; i++) {
                TreeItem<String> root_app = new TreeItem<>("Appartement " + i);
                for (int j = 0; j < nbrPiece; j++) {
                    root_app.getChildren().add(new TreeItem<>("Piece " + j));
                }
                root_etage.getChildren().add(root_app);
            }
            root_Bat.getChildren().add(root_etage);
        }
        */

        TreeView<String> treeView = new TreeView<>(root_Bat);
        
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
HBox buttonBar = new HBox(20, bCreation, bRevetement);
mainPane.setTop(buttonBar);

Scene scene = new Scene(mainPane, 800, 600);
stage.setScene(scene);
stage.setTitle("Nouveau");
stage.show();

}

    public static void main(String[] args) {
        launch();
    }

}