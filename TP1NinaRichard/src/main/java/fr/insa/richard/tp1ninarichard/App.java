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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javax.swing.JTextField;

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
    private int nbrevetement;
    private List<EtageI> liste_EtageI = new ArrayList();
    private List<EtageM> liste_EtageM = new ArrayList();
    private List<Revetement> liste_Revetement = new ArrayList();
    private List<Canvas> liste_Canvas = new ArrayList();
    private TreeView<String> treeView;
    private EtageI  etagei;
    private EtageM etagem;
    
    @Override
    public void start(Stage stage) {
        nbrEtage = 0; nbrCoin=0; nbrevetement=0;
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
            if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer une Coin dans un batiment sans étage , Veuillez en créer un avant en appuyant sur OK.");
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
            }
            if (type==1){
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
                    etagem = liste_EtageM.get(etagenum);
                    Optional<String> ordonnee = inDialog2.showAndWait();
                    if (ordonnee.isPresent()){
                        String ord = ordonnee.orElse("2,20");
                        Scanner lineScanner1 = new Scanner(ord);
                        double ord1 = lineScanner1.nextDouble();
                        System.out.println(ord);
                        System.out.println(ord1); 
                        Coin coin = new Coin(nbrCoin,abs1, ord1);
                        etagem.ajouterCoin(coin);
                        nbrCoin ++;
                        int i=0;
                        
                        
                    }}
                }
            //cas immeuble
            }else{
                String[] choicesA = new String[nbrEtage];
                System.out.println(2);
                int h = 0;
                for(EtageI etage : liste_EtageI){
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
                    etagei = liste_EtageI.get(etagenum);
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
                        etagem.ajouterCoin(coin);
                        nbrCoin ++;
                        int i=0; 
                    }}
                }
            }
            treeView =updateTreeView();
            mainPane.setLeft(treeView);
        });
        
        //reaction bouton Creer un mur
        Button bCMur = new Button("Créer un mur");
        bCMur.setOnAction((t) -> {
            //empeche creation mur sans etage
            if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer une piece dans un batiment sans étage , Veuillez en créer un avant en appuyant sur OK.");
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
                //cas ou il y a deja au moins un etage
                //cas immeuble
                }else if (type == 0){
                    //choix etage
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageI etage : liste_EtageI){
                        String choice2 = "Etage " + h;
                        choicesA[h] = choice2;
                        h ++;
                    }
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter un mur.");
                    cDial.setContentText("Choix :");
                    
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagei = liste_EtageI.get(etagenum);
                    }
                    //mettre si OK est clique
                    System.out.println(11);
                     JDialog fenetreMur = new JDialog();
                     fenetreMur.setSize(550, 270);
                     fenetreMur.setLocationRelativeTo(null);
                     fenetreMur.setVisible(true);
                     JLabel coin1label, coin2Label;
                     JRadioButton rbM1Existant, rbM1Acree;
                     JRadioButton rbM2Existant, rbM2Acree;
                     
                     //Coin 1 
                     JPanel panCoin1 = new JPanel();
                     panCoin1.setBorder(BorderFactory.createTitledBorder("Coin 1"));
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
                     panCoin2.setBorder(BorderFactory.createTitledBorder("Coin 2"));
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
                            //pas assez de coins existants
                            if (existant>=etagei.getCoinEtage().size()){
                                int result = JOptionPane.showConfirmDialog(fenetreMur, "Vous n'avez pas assez de coins déjà existants", "Erreur dans la creation de la pièce", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    fenetreMur.setVisible(true);
                                }
                            //assez de coins existants
                            }else{//CHANGER A PARTIR DE LA
                                String compteRendu = "Vous avez selectionné:  \n  Coin 1 : " + getCoin1() + "\n Coin 2 : "+ getCoin2();
                                int result = JOptionPane.showConfirmDialog(fenetreMur,compteRendu,"Veuiller verifier votre selection",JOptionPane.OK_CANCEL_OPTION);
                                System.out.println("test1");
                                if (result == JOptionPane.OK_OPTION) {
                                    Coin coin1;
                                    Coin coin2;
                                    if(!getCoin1().equals("Existant")){
                                         int h = 0;
                                    
                                    for(Coin coin : etagei.getCoinEtage()){
                                        String choice2 = coin.toString();
                                        choicesA[h] = choice2;
                                        h ++;
                                    }
                                    ChoiceDialog<String> cDial47 = new ChoiceDialog<>(choicesA[h-1],choicesA);
                                    cDial47.setTitle("Selection du Coin 1");
                                    cDial47.setHeaderText("Veuillez selectionner le premier coin.");
                                    cDial47.setContentText("Choix :");

                                    Optional<String> selection = cDial47.showAndWait();
                                    if(selection.isPresent()){
                                        String selectionStr = selection.orElse("0");
                                        int length = selectionStr.length();
                                        String c = Character.toString(selectionStr.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr.charAt(i);
                                        }
                                        Scanner lineScanner = new Scanner(c);
                                        int coinnum1 = lineScanner.nextInt();
                                        coin1 = etagei.getCoinEtage().get(coinnum1);
                                    }
                                    }else {
                                        //rentre  Absysse et ordonné
                                    }
                                    if(getCoin2().equals("Existant")){
                                         int h = 0;
                                    h ++;
                                    for(Coin coin : etagei.getCoinEtage()){
                                        String choice3 = coin.toString();
                                        choicesA[h] = choice3;
                                        h ++;
                                    }
                                    ChoiceDialog<String> cDial6 = new ChoiceDialog<>(choicesA[h-1],choicesA);
                                    cDial6.setTitle("Selection du Coin 3");
                                    cDial6.setHeaderText("Veuillez selectionner le deuxième coin.");
                                    cDial6.setContentText("Choix :");

                                    Optional<String> selection = cDial6.showAndWait();
                                    if(selection.isPresent()){
                                        String selectionStr = selection.orElse("0");
                                        int length = selectionStr.length();
                                        String c = Character.toString(selectionStr.charAt(15));
                                        for(int i= 16 ; i<length ; i++){
                                            c = c + selectionStr.charAt(i);
                                        }
                                        Scanner lineScanner = new Scanner(c);
                                        int coinnum1 = lineScanner.nextInt();
                                        coin1 = etagei.getCoinEtage().get(coinnum1);
                                    }
                                    }else {
                                        //rentre  Absysse et ordonné
                                    }
                                    //choix coin1
                                    /*String[] choicesA = new String[nbrCoin];
                                    int h = 0;
                                    List<Coin> list_Coin=etagei.getCoinEtage();
                                    for(Coin coin : list_Coin){
                                        String choice2 = "Point " + h;
                                        choicesA[h] = choice2;
                                        h ++;
                                    }
                                    ChoiceDialog<String> cDial5 = new ChoiceDialog<>(choicesA[h-1],choicesA);
                                    cDial5.setTitle("Selection du point 1");
                                    cDial5.setHeaderText("Veuillez selectionner le premier coin.");
                                    cDial5.setContentText("Choix :");

                                    Optional<String> selection = cDial5.showAndWait();
                                    if(selection.isPresent()){
                                        String selectionStr = selection.orElse("0");
                                        int length = selectionStr.length();
                                        String c = Character.toString(selectionStr.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr.charAt(i);
                                        }
                                        Scanner lineScanner = new Scanner(c);
                                        int coinnum1 = lineScanner.nextInt();
                                        coinMod1 = list_Coin.get(coinnum1);
                                    }
                                    //choix coin2
                                    String[] choicesB = new String[nbrCoin];
                                    int k = 0;
                                    list_Coin=etagei.getCoinEtage();
                                    for(Coin coin : list_Coin){
                                        String choice2 = "Point " + h;
                                        choicesB[h] = choice2;
                                        k ++;
                                    }
                                    ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesB[h-1],choicesB);
                                    cDial.setTitle("Selection du point 1");
                                    cDial.setHeaderText("Veuillez selectionner le premier coin.");
                                    cDial.setContentText("Choix :");

                                    Optional<String> selection2 = cDial2.showAndWait();
                                    if(selection2.isPresent()){
                                        String selectionStr = selection2.orElse("0");
                                        int length = selectionStr.length();
                                        String c = Character.toString(selectionStr.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr.charAt(i);
                                        }
                                        Scanner lineScanner = new Scanner(c);
                                        int coinnum2 = lineScanner.nextInt();
                                        coinMod2 = list_Coin.get(coinnum2);
                                    }
                                    //creation mur
                                    Mur murmMod=new Mur(coinMod1, coinMod2);
                                    List<Mur> liste_Mur = etagei.getMurEtage();
                                    liste_Mur.add(murmMod);
                                    etagei.setMurEtage(liste_Mur);
                                }
                                else {
                                fenetreMur.setVisible(true);
                                }*/
                            }
                        }}
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
                     JButton cancelButton = new JButton("Cancel");
                     cancelButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                            fenetreMur.setVisible(false);
                        } 

                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            fenetreMur.setVisible(false);
                        }
                     });
                     control.add(okButton);
                     control.add(cancelButton);
                     
                     fenetreMur.getContentPane().setLayout(new BorderLayout());
                     fenetreMur.getContentPane().add(content,BorderLayout.CENTER);
                     fenetreMur.getContentPane().add(control,BorderLayout.SOUTH);
                     
                     fenetreMur.setVisible(true);
                
                    
                //cas maison
                } else {
                    //choix etage
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageM etage : liste_EtageM){
                        String choice2 = "Etage " + h;
                        choicesA[h] = choice2;
                        h ++;
                    }
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter un mur.");
                    cDial.setContentText("Choix :");
                    
                    Optional<String> selection = cDial.showAndWait();
                    System.out.println(10);
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagem = liste_EtageM.get(etagenum);
                    }
                    //mettre si OK est clique
                    System.out.println(11);
                     JDialog fenetreMur = new JDialog();
                     fenetreMur.setSize(550, 270);
                     fenetreMur.setLocationRelativeTo(null);
                     fenetreMur.setVisible(true);
                     JLabel coin1label, coin2Label;
                     JRadioButton rbM1Existant, rbM1Acree;
                     JRadioButton rbM2Existant, rbM2Acree;
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
                            //pas assez de coins existants
                            if (existant>=etagem.getCoinEtage().size()){
                                int result = JOptionPane.showConfirmDialog(fenetreMur, "Vous n'avez pas assez de coins déjà existants", "Erreur dans la creation de la pièce", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    fenetreMur.setVisible(true);
                                }
                            //assez de coins existants
                            }else{ //CHANGER A PARTIR DE LA
                                String compteRendu = "Vous avez selectionné:  \n  Coin 1 : " + getCoin1() + "\n Coin 2 : "+ getCoin2();
                                int result = JOptionPane.showConfirmDialog(fenetreMur,compteRendu,"Veuiller verifier votre selection",JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    System.out.println("User chose OK");
                                    Coin coinMod1=new Coin(), coinMod2=new Coin();
                                    
                                    //choix coin1
                                    String[] choicesF = new String[nbrCoin];
                                    int h = 0;
                                    List<Coin> list_Coin=etagem.getCoinEtage();
                                    for(Coin coin : list_Coin){
                                        String choice2 = "Point " + h;
                                        choicesF[h] = choice2;
                                        h ++;
                                    }
                                    ChoiceDialog<String> cDial4 = new ChoiceDialog<>(choicesF[h-1],choicesF);
                                    cDial4.setTitle("Selection du point 1");
                                    cDial4.setHeaderText("Veuillez selectionner le premier coin.");
                                    cDial4.setContentText("Choix :");

                                    Optional<String> selection = cDial4.showAndWait();
                                    if(selection.isPresent()){
                                        String selectionStr = selection.orElse("0");
                                        int length = selectionStr.length();
                                        String c = Character.toString(selectionStr.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr.charAt(i);
                                        }
                                        Scanner lineScanner = new Scanner(c);
                                        int coinnum1 = lineScanner.nextInt();
                                        coinMod1 = list_Coin.get(coinnum1);
                                    }
                                    //choix coin2
                                    String[] choicesB = new String[nbrCoin];
                                    int k = 0;
                                    list_Coin=etagem.getCoinEtage();
                                    for(Coin coin : list_Coin){
                                        String choice2 = "Point " + h;
                                        choicesB[h] = choice2;
                                        k ++;
                                    }
                                    ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesB[h-1],choicesB);
                                    cDial2.setTitle("Selection du point 1");
                                    cDial2.setHeaderText("Veuillez selectionner le premier coin.");
                                    cDial2.setContentText("Choix :");

                                    Optional<String> selection2 = cDial2.showAndWait();
                                    if(selection2.isPresent()){
                                        String selectionStr = selection2.orElse("0");
                                        int length = selectionStr.length();
                                        String c = Character.toString(selectionStr.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr.charAt(i);
                                        }
                                        Scanner lineScanner = new Scanner(c);
                                        int coinnum2 = lineScanner.nextInt();
                                        coinMod2 = list_Coin.get(coinnum2);
                                    }
                                    //creation mur
                                    Mur murmMod=new Mur(coinMod1, coinMod2);
                                    List<Mur> liste_Mur = etagem.getMurEtage();
                                    liste_Mur.add(murmMod);
                                    etagem.setMurEtage(liste_Mur);
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
                     JButton cancelButton = new JButton("Cancel");
                     cancelButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                            fenetreMur.setVisible(false);
                        } 

                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            fenetreMur.setVisible(false);
                        }
                     });
                     control.add(okButton);
                     control.add(cancelButton);
                     
                     fenetreMur.getContentPane().setLayout(new BorderLayout());
                     fenetreMur.getContentPane().add(content,BorderLayout.CENTER);
                     fenetreMur.getContentPane().add(control,BorderLayout.SOUTH);
                     
                     fenetreMur.setVisible(true);
                }
                         
        });
                     
                     
        Button bCPiece = new Button("Créer une Piece");
        Button bCEtage = new Button("Créer un étage");
        Button bCLogement = new Button("Créer un appartement");
        Button bCRevetement = new Button("Ajouter un Revetement");
        Button bCMaj = new Button("Affichage plan");
        Button bCDevis = new Button("Générer le devis");
        
        
            
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
                            //cas immeuble
                            if (type == 0){
                                EtageI etage = new EtageI(nbrEtage,hsp);
                                immeuble.ajouterEtage(etage);
                                nbrEtage ++;
                            //cas maison
                            }else{
                                EtageM etage = new EtageM(nbrEtage,hsp);
                                nbrEtage ++;
                                maison.ajouterEtage(etage);
                            }
                        }
                        treeView =updateTreeView();
                        mainPane.setLeft(treeView);
}
})) ;

        
        if (type == 0 ){
            HBox bBar = new HBox(20, bCPoint, bCMur, bCPiece , bCLogement , bCEtage, bCRevetement, bCDevis, bCMaj);
            mainPane.setBottom(bBar);
        }else{
            HBox bBar = new HBox(20,bCPoint, bCMur, bCPiece , bCEtage, bCRevetement, bCDevis, bCMaj);
            mainPane.setBottom(bBar);
        }
        
        
        bCMaj.setOnAction((new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent t){
                //si pas d'etage a afficher, erreur
                if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible d'afficher un étage inexistant, Veuillez en créer un avant");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    
                    if(answer.get()== ButtonType.OK){
                        dialogC.close();
                    }
                //si minimum 1 etage existant
                }else if (type == 0){//cas immeuble
                    //choix etage afficher
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageI etage : liste_EtageI){
                        String choice = "Etage " + h;
                        choicesA[h] = choice;
                        h ++;
                    }
                   ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage à afficher.");
                    cDial.setContentText("Choix :");
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagei = liste_EtageI.get(etagenum);
                    }
                    
                    
                    
                    
                    
                    
            } else {//cas maison
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageM etage : liste_EtageM){
                        String choice = "Etage " + h;
                        choicesA[h] = choice;
                        h ++;
                    }
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage à afficher.");
                    cDial.setContentText("Choix :");
                    
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagem = liste_EtageM.get(etagenum);
                    for (Canvas can : liste_Canvas){
                        GraphicsContext context = can.getGraphicsContext2D();
                        context.clearRect(0, 0, can.getWidth(), can.getHeight());
                        context.setFill(Color.PINK);
                        can.setVisible(false);
                    }
                    System.out.println(1);
                    liste_Canvas.get(etagenum).setVisible(true);
                    }
                    
                    
                    
            /*treeView=updateTreeView();
            mainPane.setLeft(treeView);
            int k=0;
            for (EtageM etageaC : liste_EtageM) {//prend liste etages sur la gauche
                TreeItem<String> root_etage =treeView.getTreeItem(k);//renvoie l'etage
                 canvas=liste_Canvas.get(k);
                root_etage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                    for (Canvas can : liste_Canvas){
                        can.setVisible(false);
                    }
                    StackPane canvasContainer = new StackPane(canvas);
                    mainPane.setCenter(canvasContainer);
                    canvasContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
                    canvas.setWidth(newVal.doubleValue());});
                    canvas.setVisible(true);*/
                //});
            }
        
            
            
        }}));
        bCLogement.setOnAction((new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent t){
	if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer un logement dans un batiment sans étage , Veuillez en créer un avant en appuyant sur OK.");
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
                }else{
			String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageI etage : liste_EtageI){
                        String choice = "Etage " + h;
                        choicesA[h] = choice;
                        h ++;
                    }
                   ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter une piece.");
                    cDial.setContentText("Choix :");
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagei = liste_EtageI.get(etagenum);
                    }
		etagei.ajouterAppartement(new Logement(etagei.getNbrdappart()));	
		treeView =updateTreeView();
                mainPane.setLeft(treeView);
}

}
})) ;

        bCPiece.setOnAction((new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent t){
                //cree un etage s'il n'y a pas ecore d'etage dans le batiment
                if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer une piece dans un batiment sans étage , Veuillez en créer un avant en appuyant sur OK.");
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
                //cas ou il y a deja un etage
                //immeuble
                }else if (type == 0){
                    //choix etage
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageI etage : liste_EtageI){
                        String choice = "Etage " + h;
                        choicesA[h] = choice;
                        h ++;
                    }
                   ChoiceDialog<String> cDial = new ChoiceDialog<>(choicesA[h-1],choicesA);
                    cDial.setTitle("Selection de l'étage");
                    cDial.setHeaderText("Veuillez selectionner l'Etage dans lequel vous voulez ajouter une piece.");
                    cDial.setContentText("Choix :");
                    Optional<String> selection = cDial.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selection.orElse("0");
                        int length = selectionStr.length();
                        String c = Character.toString(selectionStr.charAt(6));
                        for(int i= 7 ; i<length ; i++){
                            c = c + selectionStr.charAt(i);
                        }
                        Scanner lineScanner = new Scanner(c);
                        int etagenum = lineScanner.nextInt();
                        System.out.println(etagenum + " " + selection + " "+ selectionStr);
                        etagei = liste_EtageI.get(etagenum);
                    }
                //interdit creation piece s'il n'y a pas de logement
		if (etagei.getNbrdappart()==0){
			Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    dialogC.setTitle("Erreur: Etage sans logement");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible de creer une piece dans un étage sans logement , en appuyant sur OK vous en créerez un logement dans l'étage " + etagei.getId());
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    
                    if(answer.get()== ButtonType.OK){
			etagei.ajouterAppartement(new Logement(etagei.getNbrdappart()));
                    }
                //cas ou il existe au moins un logement dans l'etage
		} else{
                    //choix appartement
                    String[] choicesE = new String[etagei.getNbrdappart()];
                    h = 0;
                    for(Logement appart : etagei.getAppartementEtage()){
                        String choice = appart.toString();
                        choicesE[h] = choice;
                        h ++;
                    }
                   ChoiceDialog<String> cDialE = new ChoiceDialog<>(choicesE[h-1],choicesE);
                    cDialE.setTitle("Selection de l'appartement");
                    cDialE.setHeaderText("Veuillez selectionner l'appartement dans lequel vous voulez ajouter une piece.");
                    cDialE.setContentText("Choix :");
                    Optional<String> selectionE = cDialE.showAndWait();
                    if(selection.isPresent()){
                        String selectionStr = selectionE.orElse("0");int i =0;
                        System.out.print("selectionStr = " + selectionStr);
			while(!(selectionStr.equals(etagei.getAppartementEtage().get(i).toString()))){
                            i++;
                            System.out.println(i);
                            System.out.println(selectionStr);
                            System.out.println(etagei.getAppartementEtage().get(i).toString());
                        }
                    }
                    
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
                            System.out.println(0.0);
                            int existant = 0;
                            if (getMur1().equals("Existant")){
                                existant ++;
                            }
                            System.out.println(0.1);
                            if (getMur2().equals("Existant")){
                                existant ++;
                            }
                            System.out.println(0.2);
                            if (getMur3().equals("Existant")){
                                existant ++;
                            }
                            System.out.println(0.3);
                            if (getMur4().equals("Existant")){
                                existant ++;
                            }
                            System.out.println(0.4);
                            fenetrePiece.setVisible(false);
                            System.out.println(0.5);
                            if (existant>=etagei.getMurEtage().size()){
                                System.out.println(0.6);
                                int result = JOptionPane.showConfirmDialog(fenetrePiece, "Vous n'avez pas assez de murs déja existants", "Erreur dans la creation ce la pièce", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    fenetrePiece.setVisible(true);
                                }
                            }else{
                                String compteRendu = "Vous avez selectionné: \n Utilité de la pièce: "+ cbUtilite.getSelectedItem() +" \n  Mur 1 : " + getMur1() + "\n Mur 2 : "+ getMur2() + "\n Mur3 : "+ getMur3() + "\n Mur 4 : " + getMur4();
                                int result = JOptionPane.showConfirmDialog(fenetrePiece,compteRendu,"Veuiller verifier votre selection",JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    System.out.println("User chose OK");
                                    JDialog choixM = new JDialog();
                                    JPanel content = new JPanel();
                                    JPanel control = new JPanel();
                                    // Mur 1 Existant
                                    JPanel panChoixM11 = new JPanel();
                                    panChoixM11.setPreferredSize(new Dimension(200,60));
                                    panChoixM11.setBorder(BorderFactory.createTitledBorder("Choix Mur 1"));
                                    JComboBox cbChoixM1 = new JComboBox();
                                    for(Mur mur : etagei.getMurEtage()){
                                       cbChoixM1.addItem(mur.toString()); 
                                    }
                                    panChoixM11.add(cbChoixM1);
                                   //Mur 1 A crée
                                   JPanel panChoixM12 = new JPanel();
                                    panChoixM12.setPreferredSize(new Dimension(200,160));
                                    panChoixM12.setBorder(BorderFactory.createTitledBorder("Creation M1"));
                                    JRadioButton rbM1C1Existant = new JRadioButton("Coin 1 Existant");
                                    rbM1C1Existant.setSelected(true);
                                    JRadioButton rbM1C1Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg11 = new ButtonGroup();
                                    bg11.add(rbM1C1Acree);
                                    bg11.add(rbM1C1Existant);
                                    JRadioButton rbM1C2Existant = new JRadioButton("Coin 1 Existant");
                                    rbM1C2Existant.setSelected(true);
                                    JRadioButton rbM1C2Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg21 = new ButtonGroup();
                                    bg21.add(rbM1C2Acree);
                                    bg21.add(rbM1C2Existant);
                                    panChoixM12.add(rbM1C1Acree);
                                    panChoixM12.add(rbM1C1Existant);
                                    panChoixM12.add(rbM1C2Acree);
                                    panChoixM12.add(rbM1C2Existant);
                                    //Mur 2  Existant
                                    JPanel panChoixM21 = new JPanel();
                                    panChoixM21.setPreferredSize(new Dimension(200,200));
                                    panChoixM21.setBorder(BorderFactory.createTitledBorder("Choix Mur 2"));
                                    JComboBox cbChoixM2 = new JComboBox();
                                    for(Mur mur : etagei.getMurEtage()){
                                       cbChoixM2.addItem(mur.toString()); 
                                    }
                                    panChoixM21.add(cbChoixM2);
                                     //Mur 2 A creer
                                     JPanel panChoixM22 = new JPanel();
                                    panChoixM22.setPreferredSize(new Dimension(200,160));
                                    panChoixM22.setBorder(BorderFactory.createTitledBorder("Creation M2"));
                                    JRadioButton rbM2C1Existant = new JRadioButton("Coin 1 Existant");
                                    rbM2C1Existant.setSelected(true);
                                    JRadioButton rbM2C1Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg12 = new ButtonGroup();
                                    bg12.add(rbM2C1Acree);
                                    bg12.add(rbM2C1Existant);
                                    JRadioButton rbM2C2Existant = new JRadioButton("Coin 1 Existant");
                                    rbM2C2Existant.setSelected(true);
                                    JRadioButton rbM2C2Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg22 = new ButtonGroup();
                                    bg22.add(rbM2C2Acree);
                                    bg22.add(rbM2C2Existant);
                                    panChoixM22.add(rbM2C1Acree);
                                    panChoixM22.add(rbM2C1Existant);
                                    panChoixM22.add(rbM2C2Acree);
                                    panChoixM22.add(rbM2C2Existant);
                                    // Mur 3 Existant
                                    JPanel panChoixM31 = new JPanel();
                                    panChoixM31.setPreferredSize(new Dimension(200,200));
                                    panChoixM31.setBorder(BorderFactory.createTitledBorder("Choix Mur 2"));
                                    JComboBox cbChoixM3 = new JComboBox();
                                    for(Mur mur : etagei.getMurEtage()){
                                       cbChoixM3.addItem(mur.toString()); 
                                    }
                                    panChoixM31.add(cbChoixM3);
                                    //Mur 3 A créer
                                    JPanel panChoixM32 = new JPanel();
                                    panChoixM32.setPreferredSize(new Dimension(200,160));
                                    panChoixM32.setBorder(BorderFactory.createTitledBorder("Creation M2"));
                                    JRadioButton rbM3C1Existant = new JRadioButton("Coin 1 Existant");
                                    rbM3C1Existant.setSelected(true);
                                    JRadioButton rbM3C1Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg13 = new ButtonGroup();
                                    bg13.add(rbM3C1Acree);
                                    bg13.add(rbM3C1Existant);
                                    JRadioButton rbM3C2Existant = new JRadioButton("Coin 1 Existant");
                                    rbM3C2Existant.setSelected(true);
                                    JRadioButton rbM3C2Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg23 = new ButtonGroup();
                                    bg23.add(rbM3C2Acree);
                                    bg23.add(rbM3C2Existant);
                                    panChoixM32.add(rbM3C1Acree);
                                    panChoixM32.add(rbM3C1Existant);
                                    panChoixM32.add(rbM3C2Acree);
                                    panChoixM32.add(rbM3C2Existant);
                                    //Mur 4 Existant
                                    JPanel panChoixM41 = new JPanel();
                                    panChoixM41.setPreferredSize(new Dimension(200,200));
                                    panChoixM41.setBorder(BorderFactory.createTitledBorder("Choix Mur 4"));
                                    JComboBox cbChoixM4 = new JComboBox();
                                    for(Mur mur : etagei.getMurEtage()){
                                       cbChoixM4.addItem(mur.toString()); 
                                    }
                                    panChoixM41.add(cbChoixM4);
                                    //Mur 4 A créer
                                    JPanel panChoixM42 = new JPanel();
                                    panChoixM42.setPreferredSize(new Dimension(200,160));
                                    panChoixM42.setBorder(BorderFactory.createTitledBorder("Creation M4"));
                                    JRadioButton rbM4C1Existant = new JRadioButton("Coin 1 Existant");
                                    rbM4C1Existant.setSelected(true);
                                    JRadioButton rbM4C1Acree = new JRadioButton("Coin 1 A créer");
                                    ButtonGroup bg14 = new ButtonGroup();
                                    bg14.add(rbM4C1Acree);
                                    bg14.add(rbM4C1Existant);
                                    JRadioButton rbM4C2Existant = new JRadioButton("Coin 2 Existant");
                                    rbM4C2Existant.setSelected(true);
                                    JRadioButton rbM4C2Acree = new JRadioButton("Coin 2 A créer");
                                    ButtonGroup bg24 = new ButtonGroup();
                                    bg24.add(rbM4C2Acree);
                                    bg24.add(rbM4C2Existant);
                                    panChoixM42.add(rbM4C1Acree);
                                    panChoixM42.add(rbM4C1Existant);
                                    panChoixM42.add(rbM4C2Acree);
                                    panChoixM42.add(rbM4C2Existant); 
                                    
                                    //On ajoute dans content pour chaque mur que le panel qui nous interesse en fonction de si le mur existe déjà ou non
                                    if (getMur1().equals("Existant")){
                                        content.add(panChoixM11);
                                    } else{
                                        content.add(panChoixM12);
                                    }
                                    if (getMur2().equals("Existant")){
                                        content.add(panChoixM21);
                                    } else{
                                        content.add(panChoixM22);
                                   }
                                    if (getMur3().equals("Existant")){
                                        content.add(panChoixM31);
                                    } else{
                                        content.add(panChoixM32);
                                   }
                                   if (getMur4().equals("Existant")){
                                        
                                        content.add(panChoixM41);
                                    } else{
                                        content.add(panChoixM42);
                                   }

System.out.println("bla");
JButton jbOk = new JButton("OK");
JButton jbCancel = new JButton("OK");
jbCancel.addActionListener(new ActionListener(){
    @Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
            choixM.setVisible(false);
            fenetrePiece.setVisible(true);
        }
});
control.add(jbOk,jbCancel);
choixM.add(control);
jbOk.addActionListener(new ActionListener(){
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		int pExistant = 0;
		if (getM1C1().equals("Existant")){
                                pExistant ++;
                            }
                            System.out.println(0.1);
                            if (getM1C2().equals("Existant")){
                                pExistant ++;
                            }
                            System.out.println(0.2);
                            if (getM2C1().equals("Existant")){
                                pExistant ++;
                            }
                            System.out.println(0.3);
                            if (getM2C2().equals("Existant")){
                                pExistant ++;
                            }
if (getM3C1().equals("Existant")){
                                pExistant ++;
                            }
                            System.out.println(0.2);
                            if (getM3C2().equals("Existant")){
                                pExistant ++;
                            }
                            System.out.println(0.3);
                            if (getM4C1().equals("Existant")){
                                pExistant ++;
                            }
if (getM4C2().equals("Existant")){
                                pExistant ++;
                            }
choixM.setVisible(false);
if (pExistant > nbrCoin){
	int result2 = JOptionPane.showConfirmDialog(choixM, "Vous n'avez pas assez de Coin déja existants : vous en avez: " + nbrCoin, "Erreur dans la creation des mur", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    choixM.setVisible(true);
                                }
}
else {
JDialog coinMur1 = new JDialog();
JDialog coinMur2 = new JDialog();
JDialog coinMur3 = new JDialog();
JDialog coinMur4 = new JDialog();
coinMur1.setSize(200, 60);
coinMur1.setLocationRelativeTo(null);

coinMur2.setSize(200, 60);
coinMur2.setLocationRelativeTo(coinMur1);
coinMur2.setVisible(true);
coinMur3.setSize(200, 60);
coinMur3.setLocationRelativeTo(coinMur2);
coinMur3.setVisible(true);
coinMur4.setSize(200, 60);
coinMur4.setLocationRelativeTo(coinMur3);
coinMur4.setVisible(true);
JPanel content = new JPanel();
JPanel control = new JPanel();
JPanel coin1Mur1 = new JPanel();
JPanel coin2Mur1 = new JPanel();
JPanel coin1Mur2 = new JPanel();
JPanel coin2Mur2 = new JPanel();
JPanel coin1Mur3 = new JPanel();
JPanel coin2Mur3 = new JPanel();
JPanel coin1Mur4 = new JPanel();
JPanel coin2Mur4 = new JPanel();
//Mur 1
Mur mur1;
Mur mur2;
Mur mur3;
Mur mur4;
if (getMur1().equals("Existant")){
    // eregistree le mur à mettre dans la pièce
    String strmur1 = (String) cbChoixM1.getSelectedItem();
    int n = strmur1.length();
    String mur1deter = Character.toString(strmur1.charAt(14));
    for(int i=15; i<n;i++){
        mur1deter = mur1deter + Character.toString(strmur1.charAt(i));
    }
    Scanner lineScanner = new Scanner(mur1deter);
                        int murnum = lineScanner.nextInt();
                        mur1 = etagei.getMurEtage().get(murnum);
}else if(getM1C1().equals("Existant")){
	String[] choicesG = new String[nbrCoin]; 
        int k = 0;
        List<Coin> liste_Coin=etagei.getCoinEtage();
        for(Coin coin : liste_Coin){
            String choice3 = coin.toString();
            choicesG[k] = choice3;
            k ++;
        }
        coin1Mur1.setPreferredSize(new Dimension(200,200));
        coin1Mur1.setBorder(BorderFactory.createTitledBorder("Choix Coin 1 du Mur 1"));
        JComboBox cbChoixC1 = new JComboBox();
        for(Coin coin : etagei.getCoinEtage()){
           cbChoixC1.addItem(coin.toString()); 
        }
        coin1Mur1.add(cbChoixC1);
        coinMur1.setVisible(true);
} else {
    JTextField absysse11 = new JTextField();
    JTextField ordonne11 = new JTextField();
    absysse11.setBounds(57, 36, 175, 20);
    ordonne11.setBounds(57, 36, 175, 20);
    coin1Mur1.add(absysse11);
    coin1Mur1.add(ordonne11);
    coinMur1.setVisible(true);
} //manque C2
if (getMur2().equals("Existant")){
    // eregistree le mur à mettre dans la pièce
    String strmur2 = (String) cbChoixM2.getSelectedItem();
    int n = strmur2.length();
    String mur2deter = Character.toString(strmur2.charAt(14));
    for(int i=15; i<n;i++){
        mur2deter = mur2deter + Character.toString(strmur2.charAt(i));
    }
    Scanner lineScanner = new Scanner(mur2deter);
                        int murnum = lineScanner.nextInt();
                        mur2 = etagei.getMurEtage().get(murnum);
}else if(getM2C1().equals("Existant")){
	String[] choicesG = new String[nbrCoin]; 
        int k = 0;
        List<Coin> liste_Coin=etagei.getCoinEtage();
        for(Coin coin : liste_Coin){
            String choice3 = coin.toString();
            choicesG[k] = choice3;
            k ++;
        }
        coin1Mur2.setPreferredSize(new Dimension(200,200));
        coin1Mur2.setBorder(BorderFactory.createTitledBorder("Choix Coin 1 du Mur 1"));
        JComboBox cbChoixC1m2 = new JComboBox();
        for(Coin coin : etagei.getCoinEtage()){
           cbChoixC1m2.addItem(coin.toString()); 
        }
        coin1Mur2.add(cbChoixC1m2);
        coinMur2.setVisible(true);
} else {
    JTextField absysse21 = new JTextField();
    JTextField ordonne21 = new JTextField();
    absysse21.setBounds(57, 36, 175, 20);
    ordonne21.setBounds(57, 36, 175, 20);
    coin1Mur1.add(absysse21);
    coin1Mur1.add(ordonne21);
    coinMur1.setVisible(true);
}
if (getMur4().equals("Existant")){
    // eregistree le mur à mettre dans la pièce
    String strmur4 = (String) cbChoixM4.getSelectedItem();
    int n = strmur4.length();
    String mur4deter = Character.toString(strmur4.charAt(14));
    for(int i=15; i<n;i++){
        mur4deter = mur4deter + Character.toString(strmur4.charAt(i));
    }
    Scanner lineScanner = new Scanner(mur4deter);
                        int murnum = lineScanner.nextInt();
                        mur4 = etagei.getMurEtage().get(murnum);
}else {
    if(getM4C1().equals("Existant")){
	
        coin1Mur4.setPreferredSize(new Dimension(200,200));
        coin1Mur4.setBorder(BorderFactory.createTitledBorder("Choix Coin 1 du Mur 4"));
        JComboBox cbChoixC1 = new JComboBox();
        for(Coin coin : etagei.getCoinEtage()){
           cbChoixC1.addItem(coin.toString()); 
        }
        coin1Mur4.add(cbChoixC1);
        coinMur4.setVisible(true);
    } else {
        JTextField absysse41 = new JTextField();
        JTextField ordonne41 = new JTextField();
        absysse41.setBounds(57, 36, 175, 20);
        ordonne41.setBounds(57, 36, 175, 20);
        coin1Mur4.add(absysse41);
        coin1Mur4.add(ordonne41);
        coinMur4.setVisible(true);
    }
    if(getM4C2().equals("Existant")){
	
        coin2Mur4.setPreferredSize(new Dimension(200,200));
        coin2Mur4.setBorder(BorderFactory.createTitledBorder("Choix Coin 1 du Mur 4"));
        JComboBox cbChoixC2 = new JComboBox();
        for(Coin coin : etagei.getCoinEtage()){
           cbChoixC2.addItem(coin.toString()); 
        }
        coin2Mur4.add(cbChoixC2);
        coinMur4.setVisible(true);
    } else {
        JTextField absysse42 = new JTextField();
        JTextField ordonne42 = new JTextField();
        absysse42.setBounds(57, 36, 175, 20);
        ordonne42.setBounds(57, 36, 175, 20);
        coin2Mur4.add(absysse42);
        coin2Mur4.add(ordonne42);
        coinMur4.setVisible(true);
    }


}
}


		
}
public String getM1C1(){
                             return (rbM1C1Existant.isSelected()) ? rbM1C1Existant.getText() :
                                     (rbM1C1Acree.isSelected()) ? rbM1C1Acree.getText() :
                                    rbM1C1Existant.getText() ;
    }
public String getM1C2(){
                             return (rbM1C2Existant.isSelected()) ? rbM1C2Existant.getText() :
                                     (rbM1C2Acree.isSelected()) ? rbM1C2Acree.getText() :
                                    rbM1C2Existant.getText() ;
                         }
public String getM2C1(){
                             return (rbM2C1Existant.isSelected()) ? rbM2C1Existant.getText() :
                                     (rbM2C1Acree.isSelected()) ? rbM2C1Acree.getText() :
                                    rbM2C1Existant.getText() ;
                         }
public String getM2C2(){
                             return (rbM2C2Existant.isSelected()) ? rbM2C2Existant.getText() :
                                     (rbM2C2Acree.isSelected()) ? rbM2C2Acree.getText() :
                                    rbM2C2Existant.getText() ;
                         }
public String getM3C1(){
                             return (rbM3C1Existant.isSelected()) ? rbM3C1Existant.getText() :
                                     (rbM3C1Acree.isSelected()) ? rbM3C1Acree.getText() :
                                    rbM3C1Existant.getText() ;
                         }
public String getM3C2(){
                             return (rbM3C2Existant.isSelected()) ? rbM3C2Existant.getText() :
                                     (rbM3C2Acree.isSelected()) ? rbM3C2Acree.getText() :
                                    rbM3C2Existant.getText() ;
                         }
public String getM4C1(){
                             return (rbM4C1Existant.isSelected()) ? rbM4C1Existant.getText() :
                                     (rbM4C1Acree.isSelected()) ? rbM4C1Acree.getText() :
                                    rbM4C1Existant.getText() ;
                         }
public String getM4C2(){
                             return (rbM4C2Existant.isSelected()) ? rbM4C2Existant.getText() :
                                     (rbM4C2Acree.isSelected()) ? rbM4C2Acree.getText() :
                                    rbM4C2Existant.getText() ;
                         }



});
                                   
                                     
                                    choixM.getContentPane().setLayout(new BorderLayout());
                                    choixM.getContentPane().add(content,BorderLayout.CENTER);
                                    
                                    
                                    
                                   choixM.setSize(900, 700);
                                    choixM.setLocationRelativeTo(null);
                                   choixM.setVisible(true);
                                }else {
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
                            if (existant>=etagem.getMurEtage().size()){
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
        bCRevetement.setOnAction((t) -> {
            if (nbrEtage == 0){
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
                    
                    dialogC.setTitle("Erreur: Batiment sans Etage");
                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Attention, il est impossible d'ajouter un revetement dans un batiment sans étage , Veuillez en créer un avant en appuyant sur OK.");
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
                //cas immeuble
                }else if (type == 0){
                    Revetement revetement=new Revetement(nbrevetement);
                    String[] choicesA = new String[nbrEtage];
                    int h = 0;
                    for(EtageI etage : liste_EtageI){
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
                        etagei = liste_EtageI.get(etagenum);
                    }
                    //mettre si OK est cliquer
                    String[] choicesB = new String[3];
                    choicesB[0] = "Mur";
                    choicesB[1] = "Sol";
                    choicesB[2] = "Plafond";

                    ChoiceDialog<String> cDial1 = new ChoiceDialog<>(choicesB[2],choicesB);
                    cDial.setTitle("Selection de la surface");
                    cDial.setHeaderText("Veuillez selectionner la surface sur laquelle vous voulez ajouter un revetement.");
                    cDial.setContentText("Choix :");
                    Optional<String> selection1 = cDial1.showAndWait();
                    System.out.println(46);
                    cDial.close();
                    if(selection1.isPresent()){
                        String rep = selection1.orElse("0");//convertit en String
                        
                        //Revetement sur mur
                        if (rep.equals("Mur")){
                            //choix mur
                              List<Mur> liste_Mur=etagei.getMurEtage();
                                String[] choicesC = new String[liste_Mur.size()];
                                h = 0;
                                for(Mur mur : liste_Mur){
                                    String choice2 = "Mur " + h+mur.toString();
                                    choicesC[h] = choice2;
                                    h ++;
                                }
                                ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                cDial.setTitle("Selection du mur");
                                cDial.setHeaderText("Veuillez selectionner le mur auquel vous voulez ajouter un revetement.");
                                cDial.setContentText("Choix :");

                                Optional<String> selection2 = cDial2.showAndWait();
                                Mur murMod=new Mur();
                                if(selection2.isPresent()){
                                    String selectionStr = selection2.orElse("0");
                                    int length = selectionStr.length();
                                    //int length_min = length - 6;
                                    String c = Character.toString(selectionStr.charAt(6));
                                    for(int i= 7 ; i<length ; i++){
                                        c = c + selectionStr.charAt(i);
                                    }
                                    Scanner lineScanner = new Scanner(c);
                                    int murnum = lineScanner.nextInt();
                                    System.out.println(murnum + " " + selection2 + " "+ selectionStr);
                                    murMod = liste_Mur.get(murnum);
                                }
                                
                                
                            //choix revetement 1
                                String[] choicesD={"Peinture 1", "Carrelage 1","Lambris 1","Marbre","Crepi","Papier peint","Plaquettes de parement","Peinture 2","Peinture 3","Carrelage 2","Lambris 2","Liege 1","Parquet","Vinyle Lino","Moquette", "Stratifie", "Gazon","Liege 2","Carrelage 3"};
                                ChoiceDialog<String> cDial11 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection3 = cDial11.showAndWait();
                                cDial.close();
                                if(selection3.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0, m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                    }
                                revetement.setSurface(murMod.getSurface());
                                liste_Revetement.add(revetement);
                                //choix revetement 2
                                ChoiceDialog<String> cDial111 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection4 = cDial111.showAndWait();
                                cDial.close();
                                if(selection4.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                        revetement.Parametres(type);
                                    }
                                revetement.setSurface(murMod.getSurface());
                                liste_Revetement.add(revetement);
                        //Revetement sol 
                        }else if (rep.equals("Sol")){
                            //choix logement
                            List<Logement> liste_Logement = etagei.getAppartementEtage();//cherche dans logements de l'etage
                            String[] choicesC = new String[liste_Logement.size()];
                                h = 0;
                                for(Logement logement : liste_Logement){
                                    String choice2 = "Logement " + h+logement.toString();
                                    choicesC[h] = choice2;
                                    h ++;
                                }
                                ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                cDial.setTitle("Selection du logement");
                                cDial.setHeaderText("Veuillez selectionner le logement dans lequel vous voulez ajouter un revetement.");
                                cDial.setContentText("Choix :");

                                Optional<String> selection2 = cDial2.showAndWait();
                                Piece pieceMod= new Piece();
                                if(selection2.isPresent()){
                                    String selectionStr = selection2.orElse("0");
                                    int length = selectionStr.length();
                                    String c = Character.toString(selectionStr.charAt(6));
                                    for(int i= 7 ; i<length ; i++){
                                        c = c + selectionStr.charAt(i);
                                    }
                                    Scanner lineScanner = new Scanner(c);
                                    int logementnum = lineScanner.nextInt();
                                    System.out.println(logementnum + " " + selection2 + " "+ selectionStr);
                                    Logement logementMod = liste_Logement.get(logementnum);
                                
                                    List<Piece> liste_Piece = logementMod.getAppartement();
                                    String[] choicesD = new String[liste_Piece.size()];
                                    h = 0;
                                    for(Piece piece : liste_Piece){
                                        String choice2 = "Piece " + h+piece.toString();
                                        choicesD[h] = choice2;
                                        h ++;
                                    }
                                    ChoiceDialog<String> cDial3 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                    cDial.setTitle("Selection de la piece");
                                    cDial.setHeaderText("Veuillez selectionner la piece a laquelle vous voulez ajouter un revetement.");
                                    cDial.setContentText("Choix :");

                                    Optional<String> selection3 = cDial3.showAndWait();
                                    pieceMod= new Piece();
                                    if(selection2.isPresent()){
                                        String selectionStr2 = selection3.orElse("0");
                                        length = selectionStr2.length();
                                        c = Character.toString(selectionStr2.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr2.charAt(i);
                                        }
                                        Scanner lineScanner1 = new Scanner(c);
                                        int piecenum = lineScanner1.nextInt();
                                        System.out.println(piecenum + " " + selection2 + " "+ selectionStr2);
                                        pieceMod = liste_Piece.get(piecenum);
                                }}
                                
                                //choix revetement
                                String[] choicesD={"Peinture 1", "Carrelage 1","Lambris 1","Marbre","Crepi","Papier peint","Plaquettes de parement","Peinture 2","Peinture 3","Carrelage 2","Lambris 2","Liege 1","Parquet","Vinyle Lino","Moquette", "Stratifie", "Gazon","Liege 2","Carrelage 3"};
                                ChoiceDialog<String> cDial11 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection3 = cDial11.showAndWait();
                                cDial.close();
                                if(selection3.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0, m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                }
                                revetement.setSurface(pieceMod.Surface());
                                liste_Revetement.add(revetement);
                            
                        }else {//plafond
                            //choix logement
                            List<Logement> liste_Logement = etagei.getAppartementEtage();//cherche dans logements de l'etage
                            String[] choicesC = new String[liste_Logement.size()];
                                h = 0;
                                for(Logement logement : liste_Logement){
                                    String choice2 = "Logement " + h+logement.toString();
                                    choicesC[h] = choice2;
                                    h ++;
                                }
                                ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                cDial.setTitle("Selection du logement");
                                cDial.setHeaderText("Veuillez selectionner le logement dans lequel vous voulez ajouter un revetement.");
                                cDial.setContentText("Choix :");

                                Optional<String> selection2 = cDial2.showAndWait();
                                Piece pieceMod= new Piece();
                                if(selection2.isPresent()){
                                    String selectionStr = selection2.orElse("0");
                                    int length = selectionStr.length();
                                    String c = Character.toString(selectionStr.charAt(6));
                                    for(int i= 7 ; i<length ; i++){
                                        c = c + selectionStr.charAt(i);
                                    }
                                    Scanner lineScanner = new Scanner(c);
                                    int logementnum = lineScanner.nextInt();
                                    System.out.println(logementnum + " " + selection2 + " "+ selectionStr);
                                    Logement logementMod = liste_Logement.get(logementnum);
                                
                                    List<Piece> liste_Piece = logementMod.getAppartement();
                                    String[] choicesD = new String[liste_Piece.size()];
                                    h = 0;
                                    for(Piece piece : liste_Piece){
                                        String choice2 = "Piece " + h+piece.toString();
                                        choicesD[h] = choice2;
                                        h ++;
                                    }
                                    ChoiceDialog<String> cDial3 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                    cDial.setTitle("Selection de la piece");
                                    cDial.setHeaderText("Veuillez selectionner la piece a laquelle vous voulez ajouter un revetement.");
                                    cDial.setContentText("Choix :");

                                    Optional<String> selection3 = cDial3.showAndWait();
                                    pieceMod= new Piece();
                                    if(selection2.isPresent()){
                                        String selectionStr2 = selection3.orElse("0");
                                        length = selectionStr2.length();
                                        c = Character.toString(selectionStr2.charAt(6));
                                        for(int i= 7 ; i<length ; i++){
                                            c = c + selectionStr2.charAt(i);
                                        }
                                        Scanner lineScanner1 = new Scanner(c);
                                        int piecenum = lineScanner1.nextInt();
                                        System.out.println(piecenum + " " + selection2 + " "+ selectionStr2);
                                        pieceMod = liste_Piece.get(piecenum);
                                }}
                                
                                //choix revetement
                                String[] choicesD={"Peinture 1", "Carrelage 1","Lambris 1","Marbre","Crepi","Papier peint","Plaquettes de parement","Peinture 2","Peinture 3","Carrelage 2","Lambris 2","Liege 1","Parquet","Vinyle Lino","Moquette", "Stratifie", "Gazon","Liege 2","Carrelage 3"};
                                ChoiceDialog<String> cDial11 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection3 = cDial11.showAndWait();
                                cDial.close();
                                if(selection3.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0,m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                }
                                revetement.setSurface(pieceMod.Surface());
                                liste_Revetement.add(revetement);
                        }
                    }
                } else {
                    Revetement revetement=new Revetement(nbrevetement);
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
            
                    String[] choicesB = new String[3];
                    choicesB[0] = "Mur";
                    choicesB[1] = "Sol";
                    choicesB[2] = "Plafond";

                    ChoiceDialog<String> cDial1 = new ChoiceDialog<>(choicesB[2],choicesB);
                    cDial.setTitle("Selection de la surface");
                    cDial.setHeaderText("Veuillez selectionner la surface sur laquelle vous voulez ajouter un revetement.");
                    cDial.setContentText("Choix :");
                    Optional<String> selection1 = cDial1.showAndWait();
                    System.out.println(46);
                    cDial.close();
                    if(selection1.isPresent()){
                        String rep = selection1.orElse("0");//convertit en String
                        //Revetement sur mur
                        if (rep.equals("Mur")){
                            //choix mur
                              List<Mur> liste_Mur=etagem.getMurEtage();
                                String[] choicesC = new String[liste_Mur.size()];
                                h = 0;
                                for(Mur mur : liste_Mur){
                                    String choice2 = "Mur " + h+mur.toString();
                                    choicesC[h] = choice2;
                                    h ++;
                                }
                                ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                cDial.setTitle("Selection du mur");
                                cDial.setHeaderText("Veuillez selectionner le mur auquel vous voulez ajouter un revetement.");
                                cDial.setContentText("Choix :");

                                Optional<String> selection2 = cDial2.showAndWait();
                                Mur murMod=new Mur();
                                if(selection2.isPresent()){
                                    String selectionStr = selection2.orElse("0");
                                    int length = selectionStr.length();
                                    //int length_min = length - 6;
                                    String c = Character.toString(selectionStr.charAt(6));
                                    for(int i= 7 ; i<length ; i++){
                                        c = c + selectionStr.charAt(i);
                                    }
                                    Scanner lineScanner = new Scanner(c);
                                    int murnum = lineScanner.nextInt();
                                    System.out.println(murnum + " " + selection2 + " "+ selectionStr);
                                    murMod = liste_Mur.get(murnum);
                                    
                                }
                                
                                //choix revetement 1
                                String[] choicesD={"Peinture 1", "Carrelage 1","Lambris 1","Marbre","Crepi","Papier peint","Plaquettes de parement","Peinture 2","Peinture 3","Carrelage 2","Lambris 2","Liege 1","Parquet","Vinyle Lino","Moquette", "Stratifie", "Gazon","Liege 2","Carrelage 3"};
                                ChoiceDialog<String> cDial11 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection3 = cDial11.showAndWait();
                                cDial.close();
                                if(selection3.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0,m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                }
                                revetement.setSurface(murMod.getSurface());
                                liste_Revetement.add(revetement);
                                //choix revetement 2
                                ChoiceDialog<String> cDial111 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection4 = cDial111.showAndWait();
                                cDial.close();
                                if(selection4.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0,m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                }
                                revetement.setSurface(murMod.getSurface());
                                liste_Revetement.add(revetement);
                        //Revetement sol 
                        }else if (rep.equals("Sol")){
                            //choix piece
                            List<Piece> liste_Piece = etagem.getPieceEtage();
                            String[] choicesC = new String[liste_Piece.size()];
                                h = 0;
                                for(Piece piece : liste_Piece){
                                    String choice2 = "Piece " + h+piece.toString();
                                    choicesC[h] = choice2;
                                    h ++;
                                }
                                ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                cDial.setTitle("Selection de la piece");
                                cDial.setHeaderText("Veuillez selectionner la piece a laquelle vous voulez ajouter un revetement.");
                                cDial.setContentText("Choix :");

                                Optional<String> selection2 = cDial2.showAndWait();
                                Piece pieceMod= new Piece();
                                if(selection2.isPresent()){
                                    String selectionStr = selection2.orElse("0");
                                    int length = selectionStr.length();
                                    String c = Character.toString(selectionStr.charAt(6));
                                    for(int i= 7 ; i<length ; i++){
                                        c = c + selectionStr.charAt(i);
                                    }
                                    Scanner lineScanner = new Scanner(c);
                                    int piecenum = lineScanner.nextInt();
                                    System.out.println(piecenum + " " + selection2 + " "+ selectionStr);
                                    pieceMod = liste_Piece.get(piecenum);
                                }
                                
                                //choix revetement
                                String[] choicesD={"Peinture 1", "Carrelage 1","Lambris 1","Marbre","Crepi","Papier peint","Plaquettes de parement","Peinture 2","Peinture 3","Carrelage 2","Lambris 2","Liege 1","Parquet","Vinyle Lino","Moquette", "Stratifie", "Gazon","Liege 2","Carrelage 3"};
                                ChoiceDialog<String> cDial11 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection3 = cDial11.showAndWait();
                                cDial.close();
                                if(selection3.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0,m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                }
                                revetement.setSurface(pieceMod.Surface());
                                liste_Revetement.add(revetement);
                            
                        }else {//plafond
                            //choix piece
                            List<Piece> liste_Piece = etagem.getPieceEtage();
                            String[] choicesC = new String[liste_Piece.size()];
                                h = 0;
                                for(Piece piece : liste_Piece){
                                    String choice2 = "Piece " + h+piece.toString();
                                    choicesC[h] = choice2;
                                    h ++;
                                }
                                ChoiceDialog<String> cDial2 = new ChoiceDialog<>(choicesC[h-1],choicesC);
                                cDial.setTitle("Selection de la piece");
                                cDial.setHeaderText("Veuillez selectionner la piece a laquelle vous voulez ajouter un revetement.");
                                cDial.setContentText("Choix :");

                                Optional<String> selection2 = cDial2.showAndWait();
                                Piece pieceMod= new Piece();
                                if(selection2.isPresent()){
                                    String selectionStr = selection2.orElse("0");
                                    int length = selectionStr.length();
                                    String c = Character.toString(selectionStr.charAt(6));
                                    for(int i= 7 ; i<length ; i++){
                                        c = c + selectionStr.charAt(i);
                                    }
                                    Scanner lineScanner = new Scanner(c);
                                    int piecenum = lineScanner.nextInt();
                                    System.out.println(piecenum + " " + selection2 + " "+ selectionStr);
                                    pieceMod = liste_Piece.get(piecenum);
                                }
                                
                                //choix revetement
                                String[] choicesD={"Peinture 1", "Carrelage 1","Lambris 1","Marbre","Crepi","Papier peint","Plaquettes de parement","Peinture 2","Peinture 3","Carrelage 2","Lambris 2","Liege 1","Parquet","Vinyle Lino","Moquette", "Stratifie", "Gazon","Liege 2","Carrelage 3"};
                                ChoiceDialog<String> cDial11 = new ChoiceDialog<>(choicesB[2],choicesB);
                                cDial.setTitle("Selection du revetement (deuxieme cote)");
                                cDial.setHeaderText("Veuillez selectionner le revetement.");
                                cDial.setContentText("Choix :");
                                Optional<String> selection3 = cDial11.showAndWait();
                                cDial.close();
                                if(selection3.isPresent()){
                                    String selectionStr = selection.orElse("0");//convertit en String
                                    //boucle pour utiliser la methode Parametre de la classe revetement
                                    int n=0,m=0;
                                    for (String revet : choicesD ){
                                        if (revet.equals(selectionStr)){
                                            n=m;
                                        }
                                        m++;
                                    }
                                    revetement.Parametres(n);
                                }
                                revetement.setSurface(pieceMod.Surface());
                                liste_Revetement.add(revetement);
                        }
                    }


                }
            
            
        });
        
        bCDevis.setOnAction((t) -> {
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
            Devis devis=new Devis();
            devis.setAdresse(this.addresse);
            devis.fichier(peinture1, carrelage1, lambris1, marbre, crepi, papierpeint, plaquettesdeparement, peinture2, peinture3, carrelage2, lambris2, liege1, parquet, 
                    vinylelino, moquette, stratifie, gazon, liege2, carrelage3);
        });
        
        // Add the Canvas
        Canvas canvas = new Canvas();
        StackPane canvasContainer = new StackPane(canvas);
        mainPane.setCenter(canvasContainer);
        // Bind the canvas size to the container size
        canvas=redraw();
        canvasContainer.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                //canvas.setWidth(newVal.doubleValue());
            }
        })
                
                ;
         canvas=redraw();
         canvasContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
         //canvas.setHeight(newVal.doubleValue());
            // redraw(canvas);
            mainPane.setCenter(canvasContainer);
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

         
         
        Scene scene = new Scene(mainPane, 1500, 600);
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
        //cas immeuble
        if(type == 0){
            liste_EtageI = immeuble.getBatiment();
            k =0;
            for (EtageI etageaC : liste_EtageI) {
                    TreeItem<String> root_etage = new TreeItem<>("Etage " + k);
                    logement = etageaC.getAppartementEtage();
                        
                    i=0;
                    for (Logement logementaC : logement){
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
        //cas maison
        }else {
            liste_EtageM = maison.getBatiment();
            k=0;
            for (EtageM etageaC : liste_EtageM) {//affiche liste etages sur la gauche
                TreeItem<String> root_etage = new TreeItem<>("Etage " + k);
                Canvas canvas = redraw();//cree un canva caché
                this.liste_Canvas.add(canvas);//ajoute le canvas a la liste avec le meme indice que l'etage associe
                pieces = etageaC.getPieceEtage();
                j=0;
                for(Piece pieceaC : pieces){//affiche pieces dans etages
                    j++;
                    root_etage.getChildren().add(new TreeItem<>("Piece " + j));
                }
                root_Menu.getChildren().add(root_etage);
                
                k++;
            }
        }
        //affichage arbre
        TreeView<String> treeView = new TreeView<>(root_Menu);
        return treeView;
    }

    // Method to redraw the content on the canvas when resized
    private Canvas redraw() {
        Canvas canvas = new Canvas(200, 200);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.PINK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getWidth());
        canvas.setVisible(true);
        context.strokeText("Plan", 10, 10);
        return canvas;
    }
    
    public static void main(String[] args) {
        launch();
    }

}