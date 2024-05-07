package fr.insa.richard.tp1ninarichard;

//import java.io.IOException;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
        
        /*GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        */
        /*
        var label = new Label("Bonjour, bienvenue dans notre calculateur de devis! ");
        var commencer = new Button("Je veux commencer");
        commencer.setOnAction(evt -> {label.setText("Alors commençons");
                                        System.out.println("Bonjour !");
                                       });
        var nouvellefenetre = new Button("Créer mon batiment.");
    
    var plustard = new Button("Peut etre plus tard");
        plustard.setOnAction(evt -> {label.setText("Attention, la procrastination est un vilain defaut");
                                        System.out.println("Bonjour !");
                                       });
        
        HBox buttonBar = new HBox(20, commencer, plustard);
        buttonBar.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(label);
        root.setBottom(buttonBar);
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
*/
        BorderPane mainPane = new BorderPane();
    
        RadioButton rbCreation = new RadioButton("Créer");
        RadioButton rbEnlever = new RadioButton("Enlever");
        RadioButton rbChanger = new RadioButton("(é-)Changer");
        
        
        VBox vbDroite = new VBox(rbCreation, rbEnlever, rbChanger);
        mainPane.setRight(vbDroite);
        
        Button bCreation = new Button("Menu Création");
        Button bRevetement = new Button("Menu Revetement");
        
        HBox buttonBar = new HBox(20, bCreation,bRevetement);
        mainPane.setTop(buttonBar);
        
        
        TreeItem<String> root2 = new TreeItem<String>("Etage");
        root2.setExpanded(true);
        root2.getChildren().addAll(
        new TreeItem<String>("Item 1"),
        new TreeItem<String>("Item 2"),
        new TreeItem<String>("Item 3")
 );
        TreeItem<TreeView> root = new TreeItem<TreeView>(new TreeView<String>(root2));
        root.setExpanded(true);
        root.getChildren().addAll(
        new TreeItem<TreeView>(new TreeView<String>(root2)),
        new TreeItem<TreeView>(new TreeView<String>(root2)),
        new TreeItem<TreeView>(new TreeView<String>(root2))
        );
        TreeView<TreeView> treeView = new TreeView<TreeView>(root);
        
        mainPane.setLeft(treeView);
        
        Scene sc = new Scene(mainPane);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
          stage.show();
}

    public static void main(String[] args) {
        launch();
    }

}