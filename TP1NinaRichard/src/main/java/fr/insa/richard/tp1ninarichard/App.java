package fr.insa.richard.tp1ninarichard;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        
        var label = new Label("Bonjour, bienvenue dans notre calculateur de devis! ");
        var commencer = new Button("Je veux commencer");
        commencer.setOnAction(evt -> {label.setText("Alors commençons");
                                        System.out.println("Bonjour !");
                                       });
        var nouvellefenetre = new Button("Créer mon batiment.");
        nouvellefenetre.setOnAction((ActionEvent event) -> {
            Parent root2;
            try {
                Stage stage1 = new Stage();
                stage1.setTitle("My New Stage Title");
                stage1.setScene(new Scene(root2, 450, 450));
                stage1.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }var plustard = new Button("Peut etre plus tard");
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
    }

    public static void main(String[] args) {
        launch();
    }

}