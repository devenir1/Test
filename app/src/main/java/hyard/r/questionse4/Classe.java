package hyard.r.questionse4;

/**
 * Created by admin on 27/05/2018.
 */

public class Classe {

    /*Propriétés*/
    //private int idClasse;
    private String nom;
    private int difficulte;
    private String description;
    private int dieu;

    /*Constructeurs*/
    public Classe(String nom, int difficulte, String description, int dieu){
        this.nom = nom;
        this.difficulte = difficulte;
        this.description = description;
        this.dieu = dieu;
    }

    public Classe(){
        super();
    }

    /*Getters*/

    public String getNom() {
        return nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public String getDescription() {
        return description;
    }

    public int getDieu() {
        return dieu;
    }

    /*Setters*/

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDieu(int dieu) {
        this.dieu = dieu;
    }
}
