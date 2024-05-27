package fr.insa.richard.tp1ninarichard;

//import java.io.IOException;
import static fr.insa.richard.tp1ninarichard.TP1NinaRichard.menu;
import java.awt.Dimension;
import java.awt.event.ActionListener;
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
import java.awt.BorderLayout;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
 * JavaFX App
 */

public class App extends Application {

    private int type; // 0 si immeuble et 1 si maison
    private String addresse;
    private Immeuble immeuble;
    private Maison maison;
    private int nbrEtage;
    private int nbrCoin;
    private List<EtageI> liste_EtageI = new ArrayList();
    private List<EtageM> liste_EtageM = new ArrayList();
    private TreeView<String> treeView;
    private EtageI  etagei;
    private EtageM etagem;
    
    @Override
    public void start(Stage stage) {
        nbrEtage = 0; nbrCoin=0;
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

        TextInputDialog inDialog = new TextInputDialog("Adresse");
        if(type == 0){
           inDialog.setTitle("Creation de votre immeuble"); 
        }else{
            inDialog.setTitle("Creation de votre maison"); 
        }
        inDialog.setHeaderText("Veiller entrer l'adresse du batiment en question.");
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
        
        //reaction bouton Creer un point
        Button bCPoint = new Button("Créer un Point");
        bCPoint.setOnAction((t) -> {
            //ArrayList<String> choices = new ArrayList();
            String[] choicesA = new String[nbrEtage];
            System.out.println(2);
            int h = 0;
            for(EtageM etage : liste_EtageM){
                String choice1 = "Etage " + h;
                choicesA[h] = choice1;
                h ++;
            }
            //String[] choicesA = (String[]) choices.toArray();
            ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
            cDial.setTitle("Selection de l'étage");
            cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter un point.");
            cDial.setContentText("Choix :");
            Optional<String> selection = cDial.showAndWait();
            System.out.println(46);
            cDial.close();
            if(selection.isPresent()){
                System.out.println(47);
                String selectionStr = selection.orElse("0");
                int length = selectionStr.length();
                //int length_min = length - 6;
                String c = Character.toString(selectionStr.charAt(6));
                System.out.println(48);
                for(int i= 7 ; i<length ; i++){
                    c = c + selectionStr.charAt(i);
                }
                Scanner lineScanner = new Scanner(c);
                int etagenum = lineScanner.nextInt();
                System.out.println(etagenum + " " + selection + " "+ selectionStr);
                //etagem = liste_EtageM.get(etagenum);
                System.out.println(49);
             
            //boite de dialogue choix abscisse
            TextInputDialog inDialog1 = new TextInputDialog("0,0");
            inDialog1.setTitle("Création d'un Coin");
            inDialog1.setHeaderText("Création d'un Coin");
            String contentText = "Abscisse du coin : " + nbrEtage ;
            inDialog1.setContentText(contentText);
            
            Optional<String> abscisse = inDialog1.showAndWait();
            if (abscisse.isPresent()){
                String abs = abscisse.orElse("2,20");
                lineScanner = new Scanner(abs);
                double abs1 = lineScanner.nextDouble();
                System.out.println(abs);
                System.out.println(abs1);  
                
                //boite de dialogue choix ordonnee
                TextInputDialog inDialog2 = new TextInputDialog("0,0");
                inDialog2.setTitle("Création d'un Coin");
                inDialog2.setHeaderText("Création d'un Coin");
                contentText = "Ordonnee du coin : " + nbrEtage ;
                inDialog2.setContentText(contentText);
                Optional<String> ordonnee = inDialog2.showAndWait();
                if (ordonnee.isPresent()){
                    String ord = ordonnee.orElse("2,20");
                    Scanner lineScanner1 = new Scanner(ord);
                    double ord1 = lineScanner1.nextDouble();
                    System.out.println(ord);
                    System.out.println(ord1); 
                    Coin coin = new Coin(nbrCoin,abs1, ord1);
                    nbrCoin ++;
                    int i=0;
                    if (type == 0){
                        for (EtageI etage : liste_EtageI){
                            if (etagenum==etage.getId()){
                               etage.ajouterCoin(coin);
                            }
                            i++;
                        }
                    }else{
                        for (EtageM etage : liste_EtageM){
                            if (etagenum==etage.getId()){
                               etage.ajouterCoin(coin);
                            }
                            i++;
                        }
                    }
                }}
            }
            treeView =updateTreeView();
            mainPane.setLeft(treeView);
        });
        
        //reaction bouton Creer un mur
        Button bCMur = new Button("Créer un mur");
        bCMur.setOnAction((t) -> {
            if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer une piece dans un batiment sans étage , Veuillez en créer un avant en appuillant sur OK.");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    
                    if(answer.get()== ButtonType.OK){
                        TextInputDialog inDialog1 = new TextInputDialog("2,20");
                        inDialog1.setTitle("Création d'un Etage");
                        inDialog1.setHeaderText("Création d'un Etage");
                        String contentText = "Numéro de l'étage: " + nbrEtage + "\n Hauteur sous plafond:";
                        inDialog1.setContentText(contentText);

                        Optional<String> hauteursousplafondO = inDialog1.showAndWait();
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
                    //ArrayList<String> choices = new ArrayList();
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageM etage : liste_EtageM){
                        String choice2 = "Etage " + h;
                        choicesA[h] = choice2;
                        h ++;
                    }
                    //String[] choicesA = (String[]) choices.toArray();
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter une piece.");
                    cDial.setContentText("Choix :");
                    
                    Optional<String> selection = cDial.showAndWait();
                    System.out.println(10);
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        //int length_min = length - 6;
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagem = liste_EtageM.get(etagenum);
                    }
                    //mettre si OK est cliquer
                    System.out.println(11);
                     JDialog fenetreMur = new JDialog();
                     fenetreMur.setSize(550, 270);
                     fenetreMur.setLocationRelativeTo(null);
                     fenetreMur.setVisible(true);
                     JLabel coin1label, coin2Label;
                     JRadioButton rbM1Existant, rbM1Acree;
                     JRadioButton rbM2Existant, rbM2Acree;
                     //JRadioButton rbM3Existant, rbM3Acree;
                     //JRadioButton rbM4Existant, rbM4Acree;
                     System.out.println(12);
                     
                     //Coin 1 
                     JPanel panCoin1 = new JPanel();
                     panCoin1.setBorder(BorderFactory.createTitledBorder("Mur 1"));
                     panCoin1.setPreferredSize(new Dimension(200,60));
                     rbM1Existant = new JRadioButton("Existant");
                     rbM1Existant.setSelected(true);
                     rbM1Acree = new JRadioButton("A créer");
                     ButtonGroup bg1 = new ButtonGroup();
                     bg1.add(rbM1Acree);
                     bg1.add(rbM1Existant);
                     panCoin1.add(rbM1Acree);
                     panCoin1.add(rbM1Existant);
                     
                     //Coin 2 
                     JPanel panCoin2 = new JPanel();
                     panCoin2.setBorder(BorderFactory.createTitledBorder("Mur 2"));
                     panCoin2.setPreferredSize(new Dimension(200,60));
                     rbM2Existant = new JRadioButton("Existant");
                     rbM2Existant.setSelected(true);
                     rbM2Acree = new JRadioButton("A créer");
                     ButtonGroup bg2 = new ButtonGroup();
                     bg2.add(rbM2Acree);
                     bg2.add(rbM2Existant);
                     panCoin2.add(rbM2Acree);
                     panCoin2.add(rbM2Existant);
                     
                     JPanel content = new JPanel();
                     content.add(panCoin1);
                     content.add(panCoin2);
                     
                     JPanel control = new JPanel();
                     JButton okButton = new JButton("OK");
                     okButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            int existant = 0;
                            if (getCoin1().equals("Existant")){
                                existant ++;
                            }
                            if (getCoin2().equals("Existant")){
                                existant ++;
                            }
                            fenetreMur.setVisible(false);
                            if (existant>etagem.getMurEtage().size()){
                                int result = JOptionPane.showConfirmDialog(fenetreMur, "Vous n'avez pas assez de coins déjà existants", "Erreur dans la creation de la pièce", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    fenetreMur.setVisible(true);
                                }
                            }else{
                                String compteRendu = "Vous avez selectionné:  \n  Coin 1 : " + getCoin1() + "\n Mur 2 : "+ getCoin2();
                                int result = JOptionPane.showConfirmDialog(fenetreMur,compteRendu,"Veuiller verifier votre selection",JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    System.out.println("User chose OK");
                                }
                                else {
                                fenetreMur.setVisible(true);
                                }
                            }
                        }
                         public String getCoin1(){
                             return (rbM1Existant.isSelected()) ? rbM1Existant.getText() :
                                     (rbM1Acree.isSelected()) ? rbM1Acree.getText() :
                                    rbM1Existant.getText() ;
                         }
                         public String getCoin2(){
                             return (rbM2Existant.isSelected()) ? rbM2Existant.getText() :
                                     (rbM2Acree.isSelected()) ? rbM2Acree.getText() :
                                    rbM2Existant.getText() ;
                         }
                     });
                }
                         
        });
                     
                     
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
                    dialogC.setContentText("Attention, il est impossible de creer une piece dans un batiment sans étage , Veuillez en créer un avant en appuillant sur OK.");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    
                    if(answer.get()== ButtonType.OK){
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
                    System.out.println(1);
                    //ArrayList<String> choices = new ArrayList();
                    String[] choicesA = new String[nbrEtage];
                    System.out.println(2);
                    int h = 0;
                    for(EtageM etage : liste_EtageM){
                        
                       
                        String choice = "Etage " + h;
                        choicesA[h] = choice;
                        h ++;
                    }
                    System.out.println("3 ");
                    //String[] choicesA = (String[]) choices.toArray();
                    System.out.println("4 ");
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    System.out.println("5");
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter une piece.");
                    cDial.setContentText("Choix :");
                    
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        //int length_min = length - 6;
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagem = liste_EtageM.get(etagenum);
                    }
                    //mettre si OK est cliquer
                     JDialog fenetrePiece = new JDialog();
                     fenetrePiece.setSize(550, 270);
                     fenetrePiece.setLocationRelativeTo(null);
                     fenetrePiece.setVisible(true);
                     JLabel EtageLabel, mur1Lable, mur2Label,mur3Label, mur4Label,UtiliteLabel;
                     JRadioButton rbM1Existant, rbM1Acree;
                     JRadioButton rbM2Existant, rbM2Acree;
                     JRadioButton rbM3Existant, rbM3Acree;
                     JRadioButton rbM4Existant, rbM4Acree;
                     JComboBox cbUtilite;
                     
                     //Utilité de la piece
                     JPanel panUtilite = new JPanel();
                     panUtilite.setPreferredSize(new Dimension(200,60));
                     panUtilite.setBorder(BorderFactory.createTitledBorder("Utilisation de la pièce"));
                     cbUtilite = new JComboBox();
                     cbUtilite.addItem("Salon");
                     cbUtilite.addItem("Couloir");
                     cbUtilite.addItem("Chambre");
                     cbUtilite.addItem("Cuisine");
                     cbUtilite.addItem("Salle de Bain");
                     cbUtilite.addItem("Autre");
                     //UtiliteLabel = new JLabel("Type de pièce: ");
                     panUtilite.add(cbUtilite);
                    //panUtilite.add(UtiliteLabel);
                     
                     //Mur 1 
                     JPanel panMur1 = new JPanel();
                     panMur1.setBorder(BorderFactory.createTitledBorder("Mur 1"));
                     panMur1.setPreferredSize(new Dimension(200,60));
                     rbM1Existant = new JRadioButton("Existant");
                     rbM1Existant.setSelected(true);
                     rbM1Acree = new JRadioButton("A créer");
                     ButtonGroup bg1 = new ButtonGroup();
                     bg1.add(rbM1Acree);
                     bg1.add(rbM1Existant);
                     panMur1.add(rbM1Acree);
                     panMur1.add(rbM1Existant);
                     
                     //Mur 2 
                     JPanel panMur2 = new JPanel();
                     panMur2.setBorder(BorderFactory.createTitledBorder("Mur 2"));
                     panMur2.setPreferredSize(new Dimension(200,60));
                     rbM2Existant = new JRadioButton("Existant");
                     rbM2Existant.setSelected(true);
                     rbM2Acree = new JRadioButton("A créer");
                     ButtonGroup bg2 = new ButtonGroup();
                     bg2.add(rbM2Acree);
                     bg2.add(rbM2Existant);
                     panMur2.add(rbM2Acree);
                     panMur2.add(rbM2Existant);
                     
                     //Mur 3 
                     JPanel panMur3 = new JPanel();
                     panMur3.setBorder(BorderFactory.createTitledBorder("Mur 3"));
                     panMur3.setPreferredSize(new Dimension(200,60));
                     rbM3Existant = new JRadioButton("Existant");
                     rbM3Existant.setSelected(true);
                     rbM3Acree = new JRadioButton("A créer");
                     ButtonGroup bg3 = new ButtonGroup();
                     bg3.add(rbM3Acree);
                     bg3.add(rbM3Existant);
                     panMur3.add(rbM3Acree);
                     panMur3.add(rbM3Existant);
                     
                     //Mur 4 
                     JPanel panMur4 = new JPanel();
                     panMur4.setBorder(BorderFactory.createTitledBorder("Mur 4"));
                     panMur4.setPreferredSize(new Dimension(200,60));
                     rbM4Existant = new JRadioButton("Existant");
                     rbM4Existant.setSelected(true);
                     rbM4Acree = new JRadioButton("A créer");
                     ButtonGroup bg4 = new ButtonGroup();
                     bg4.add(rbM4Acree);
                     bg4.add(rbM4Existant);
                     panMur4.add(rbM4Acree);
                     panMur4.add(rbM4Existant);
                     
                     JPanel content = new JPanel();
                     content.add(panUtilite);
                     content.add(panMur1);
                     content.add(panMur2);
                     content.add(panMur3);
                     content.add(panMur4);
                     
                     JPanel control = new JPanel();
                     
                     JButton okButton = new JButton("OK");
                     okButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            int existant = 0;
                            if (getMur1().equals("Existant")){
                                existant ++;
                            }
                            if (getMur2().equals("Existant")){
                                existant ++;
                            }
                            if (getMur3().equals("Existant")){
                                existant ++;
                            }
                            if (getMur4().equals("Existant")){
                                existant ++;
                            }
                            fenetrePiece.setVisible(false);
                            if (existant>etagem.getMurEtage().size()){
                                int result = JOptionPane.showConfirmDialog(fenetrePiece, "Vous n'avez pas assez de murs déja existants", "Erreur dans la creation ce la pièce", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    fenetrePiece.setVisible(true);
                                }
                            }else{
                                String compteRendu = "Vous avez selectionné: \n Utilité de la pièce: "+ cbUtilite.getSelectedItem() +" \n  Mur 1 : " + getMur1() + "\n Mur 2 : "+ getMur2() + "\n Mur3 : "+ getMur3() + "\n Mur 4 : " + getMur4();
                                int result = JOptionPane.showConfirmDialog(fenetrePiece,compteRendu,"Veuiller verifier votre selection",JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    System.out.println("User chose OK");
                                }
                                else {
                                fenetrePiece.setVisible(true);
                                }
                            }
                            
                            
                        }
                         public String getMur1(){
                             return (rbM1Existant.isSelected()) ? rbM1Existant.getText() :
                                     (rbM1Acree.isSelected()) ? rbM1Acree.getText() :
                                    rbM1Existant.getText() ;
                         }
                         public String getMur2(){
                             return (rbM2Existant.isSelected()) ? rbM2Existant.getText() :
                                     (rbM2Acree.isSelected()) ? rbM2Acree.getText() :
                                    rbM2Existant.getText() ;
                         }
                         public String getMur3(){
                             return (rbM3Existant.isSelected()) ? rbM3Existant.getText() :
                                     (rbM3Acree.isSelected()) ? rbM3Acree.getText() :
                                    rbM3Existant.getText() ;
                         }
                         public String getMur4(){
                             return (rbM4Existant.isSelected()) ? rbM4Existant.getText() :
                                     (rbM4Acree.isSelected()) ? rbM4Acree.getText() :
                                    rbM4Existant.getText() ;
                         }
                         
                     });
                     JButton cancelButton = new JButton("Cancel");
                     cancelButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                            fenetrePiece.setVisible(false);
                        } 

                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            fenetrePiece.setVisible(false);
                        }
                     });
                     control.add(okButton);
                     control.add(cancelButton);
                     
                     fenetrePiece.getContentPane().setLayout(new BorderLayout());
                     fenetrePiece.getContentPane().add(content,BorderLayout.CENTER);
                     fenetrePiece.getContentPane().add(control,BorderLayout.SOUTH);
                     
                     fenetrePiece.setVisible(true);
                     
                }
            }
        }));
        
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
         
        //menu avec revetements sur la droite
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