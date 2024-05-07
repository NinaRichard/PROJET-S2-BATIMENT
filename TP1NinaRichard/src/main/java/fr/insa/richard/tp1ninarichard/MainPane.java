/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import java.awt.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author inspi
 */
public class MainPane extends BorderPane {
    
    private RadioButton rbCreation;
    private RadioButton rbEnlever;
    private RadioButton rbChanger;
    
    private Button bCreation;
    private Button bRevetement;
    
    public MainPane(){
        this.rbCreation = new RadioButton("Créer");
        this.rbEnlever = new RadioButton("Enlever");
        this.rbChanger = new RadioButton("(é-)Changer");
        
        VBox vbDroite = new VBox(this.rbCreation, this.rbEnlever, this.rbChanger);
        this.setRight(vbDroite);
        
        HBox buttonBar = new HBox(20, this.bCreation,this.bRevetement);
        this.setTop(buttonBar);
        
        /*this.cPlan = new Canvas();
        this.setCenter(this.cPlan);*/
    }
}
