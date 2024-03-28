
import fr.insa.richard.tp1ninarichard.Piece;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author inspi
 */
public class Revetement {
    private int id;
    private List<Piece> utiliser = new ArrayList();

    public Revetement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Piece> getUtiliser() {
        return utiliser;
    }

    public void setUtiliser(List<Piece> utiliser) {
        this.utiliser = utiliser;
    }
    
    public void ajouterPiece( Piece nouvellePiece){
        utiliser.add(nouvellePiece);
    }
    
}
