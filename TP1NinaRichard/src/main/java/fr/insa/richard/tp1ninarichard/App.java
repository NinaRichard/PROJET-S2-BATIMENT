package fr.insa.richard.tp1ninarichard;

//import java.io.IOException;
import static fr.insa.richard.tp1ninarichard.TP1NinaRichard.menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JDialog;

/**
 * JavaFX App
 */

public class App extends Application {

    private int type; // 0 si immeuble et 1 si maison
    private String addresse;
    private Immeuble immeuble;
    private Maison maison;
    private int nbrEtage;
    private List<EtageI> liste_EtageI = new ArrayList();
    private List<EtageM> liste_EtageM = new ArrayList();
    private TreeView<String> treeView;
    
    @Override
    public void start(Stage stage) {
        nbrEtage = 0;
        BorderPane mainPane = new BorderPane();

        Alert entree = new Alert(AlertType.CONFIRMATION);
        entree.setTitle("Bienvenue, sur notre calculateur de devis estimatif des revetements d'un batiment. ");
        entree.setHeaderText("Veuillez Choisir votre type de batiment:");

        ButtonType bMaison = new ButtonType("Maison");
        ButtonType bImmeuble = new ButtonType("Immeuble");
        entree.getButtonTypes().setAll(bMaison,bImmeuble);
        Optional<ButtonType> choice = entree.showAndWait();
        if(choice.get() == bMaison){
            type = 1;
        } else{
            type = 0;
        }

        TextInputDialog inDialog = new TextInputDialog("Addresse");
        if(type == 0){
           inDialog.setTitle("Creation de votre immeuble"); 
        }else{
            inDialog.setTitle("Creation de votre maison"); 
        }
        inDialog.setHeaderText("Veiller entrer l'addresse du batiment en question.");
        inDialog.setContentText("adresse");

        Optional<String> textIn = inDialog.showAndWait();

        if (textIn.isPresent()){
            System.out.println(textIn);
            if (type == 0){
                immeuble = new Immeuble(1,textIn.orElse(addresse));
                System.out.println(textIn.orElse(addresse));
            } else {
                maison = new Maison(1,textIn.orElse(addresse));
                System.out.println(textIn.orElse(addresse));
            }
        }
        treeView =  updateTreeView();
        mainPane.setLeft(treeView);
        
        
        Button bCPoint = new Button("Créer un Point");
        Button bCMur = new Button("Créer un mur");
        Button bCPiece = new Button("Créer une Piece");
        Button bCEtage = new Button("Créer un étage");
        bCEtage.setOnAction((new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            
            TextInputDialog inDialog = new TextInputDialog("2,20");
            inDialog.setTitle("Création d'un Etage");
            inDialog.setHeaderText("Création d'un Etage");
            String contentText = "Numéro de l'étage: " + nbrEtage + "\n Hauteur sous plafond:";
            inDialog.setContentText(contentText);

            Optional<String> hauteursousplafondO = inDialog.showAndWait();
            if (hauteursousplafondO.isPresent()){
                String hauteursousplafond = hauteursousplafondO.orElse("2,20");
                Scanner lineScanner = new Scanner(hauteursousplafond);
                double hsp = lineScanner.nextDouble();
                System.out.println(hauteursousplafond);
                System.out.println(hsp);
                
                if (type == 0){
                    EtageI etage = new EtageI(nbrEtage,hsp);
                    immeuble.ajouterEtage(etage);
                    nbrEtage ++;
                }else{
                    EtageM etage = new EtageM(nbrEtage,hsp);
                    nbrEtage ++;
                    maison.ajouterEtage(etage);
                }
            }
            treeView =updateTreeView();
            mainPane.setLeft(treeView);
            
        }}));
        if (type == 0 ){
            Button bCLogement = new Button("Créer un appartement");
            HBox bBar = new HBox(20, bCPoint, bCMur, bCPiece , bCLogement , bCEtage);
            mainPane.setBottom(bBar);
        }else{
            HBox bBar = new HBox(20,bCPoint, bCMur, bCPiece , bCEtage );
            mainPane.setBottom(bBar);
        }
        bCPiece.setOnAction((new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent t){
                if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer une piece dans un batiment sans étage , Veuillez en créée et un avant en appuillant sur OK.");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    
                    if(answer.get()== ButtonType.OK){
                        System.out.println("OK");
                        TextInputDialog inDialog = new TextInputDialog("2,20");
                        inDialog.setTitle("Création d'un Etage");
                        inDialog.setHeaderText("Création d'un Etage");
                        String contentText = "Numéro de l'étage: " + nbrEtage + "\n Hauteur sous plafond:";
                        inDialog.setContentText(contentText);

                        Optional<String> hauteursousplafondO = inDialog.showAndWait();
                        if (hauteursousplafondO.isPresent()){
                            String hauteursousplafond = hauteursousplafondO.orElse("2,20");
                            Scanner lineScanner = new Scanner(hauteursousplafond);
                            double hsp = lineScanner.nextDouble();
                            System.out.println(hauteursousplafond);
                            System.out.println(hsp);

                            if (type == 0){
                                EtageI etage = new EtageI(nbrEtage,hsp);
                                immeuble.ajouterEtage(etage);
                                nbrEtage ++;
                            }else{
                                EtageM etage = new EtageM(nbrEtage,hsp);
                                nbrEtage ++;
                                maison.ajouterEtage(etage);
                            }
                        }
                        treeView =updateTreeView();
                        mainPane.setLeft(treeView);
                    }
                }else if (type == 0){
                    
                } else {
                    //NE FONCTIONNE PAS A TESTER AVEC DES PRINTS PARTOUT
                    ArrayList<String> choices = new ArrayList();
                    int h = 0;
                    for(EtageM etage : liste_EtageM){
                        String choice = "Etage " + h;
                        choices.add(choice);
                        h ++;
                    }
                    String[] choicesA = (String[]) choices.toArray();
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Slection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter une piece.");
                    cDial.setContentText("Choix :");
                    
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("2,20");
                        Scanner lineScanner = new Scanner(selectionStr);
                        double numEtageAMod = lineScanner.nextDouble();
                        //fenetre pour cree la piece
                    }
                }
            }
        }));
        
    
        
        
        
        // Add the Canvas
        Canvas canvas = new Canvas();
        StackPane canvasContainer = new StackPane(canvas);
        mainPane.setCenter(canvasContainer);
 
        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("Peinture 1", new Label("Prix: 10,95€  \n Valide pour: \n Mur \n Plafond"));
        TitledPane pane2 = new TitledPane("Carrelage 1", new Label("Prix: 49,75€  \n Valide pour: \n Mur \n Sol"));
        TitledPane pane3 = new TitledPane("Lambris 1", new Label("Prix: 50,60€  \n Valide pour: \n Mur \nSol \n Plafond"));
        TitledPane pane4 = new TitledPane("Marbre", new Label("Prix: 97,85€  \n Valide pour: \n Mur \n Sol"));
        TitledPane pane5 = new TitledPane("Crepi", new Label("Prix: 67,80€  \n Valide pour: \n Mur "));
        TitledPane pane6 = new TitledPane("Papier peint", new Label("Prix: 32,90€  \n Valide pour: \n Mur"));
        TitledPane pane7 = new TitledPane("Plaquettes de parement", new Label("Prix: 15,90€  \n Valide pour: \n Mur"));
        TitledPane pane8 = new TitledPane("Peinture 2", new Label("Prix: 77,30€  \n Valide pour: \n Mur \n Plafond"));
        TitledPane pane9 = new TitledPane("Peinture 3", new Label("Prix: 29,90€  \n Valide pour: \n Mur \n Plafond"));
        TitledPane pane10 = new TitledPane("Carrelage 2", new Label("Prix: 89,45€  \n Valide pour: \n Mur \n Sol"));
        TitledPane pane11 = new TitledPane("Lambris 2", new Label("Prix: 42,50€  \n Valide pour: \n Mur \nSol"));
        TitledPane pane12 = new TitledPane("Liege 1", new Label("Prix: 25,40€  \n Valide pour: \n Mur "));
        TitledPane pane13 = new TitledPane("Parquet", new Label("Prix: 46,36€  \n Valide pour: \n Sol"));
        TitledPane pane14 = new TitledPane("Vinyle Lino", new Label("Prix: 23,55€  \n Valide pour: \nSol"));
        TitledPane pane15 = new TitledPane("Moquette", new Label("Prix: 48,10€  \n Valide pour: \n Sol"));
        TitledPane pane16 = new TitledPane("Stratifie", new Label("Prix: 31,99€  \n Valide pour: \nSol"));
        TitledPane pane17 = new TitledPane("Gazon", new Label("Prix: 17,95€  \n Valide pour: \n Sol "));
        TitledPane pane18 = new TitledPane("Liege 2", new Label("Prix: 33,90€  \n Valide pour: \n Sol"));
        TitledPane pane19 = new TitledPane("Carrelage 3", new Label("Prix: 10,35€  \n Valide pour: \n Mur \nSol"));
        accordion.getPanes().addAll(pane1, pane2,pane3, pane4,pane5, pane6,pane7, pane8, pane9, pane10,pane11, pane12, pane13, pane14,pane15, pane16,pane17, pane18,pane19);
        mainPane.setRight(accordion);

         // Bind the canvas size to the container size
         canvasContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
             canvas.setWidth(newVal.doubleValue());
             redraw(canvas);
         });
         canvasContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
             canvas.setHeight(newVal.doubleValue());
             redraw(canvas);
         });
         
        Scene scene = new Scene(mainPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Nouveau");
        stage.show();


}
    private TreeView updateTreeView(){
        int k; int i;int j;
        List<Logement> logement;
        List<Piece> pieces;

        TreeItem<String> root_Menu = new TreeItem<>("MENU");
        root_Menu.setExpanded(true);
        if(type == 0){
            liste_EtageI = immeuble.getBatiment();
            k =0;
            for (EtageI etageaC : liste_EtageI) {
                
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
                    root_Menu.getChildren().add(root_etage);
                    k++;
            } 
        }else {
            liste_EtageM = maison.getBatiment();
            k=0;
            for (EtageM etageaC : liste_EtageM) {
                TreeItem<String> root_etage = new TreeItem<>("Etage " + k);
                pieces = etageaC.getPieceEtage();
                j=0;
                for(Piece pieceaC : pieces){
                    j++;
                    root_etage.getChildren().add(new TreeItem<>("Piece " + j));
                }
                root_Menu.getChildren().add(root_etage);
                k++;
            }
        }
        TreeView<String> treeView = new TreeView<>(root_Menu);
        return treeView;
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