package hyard.r.questionse4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by admin on 28/05/2018.
 */

public class GestionBD {
    private SQLiteDatabase maBase;
    private BDHelper monBDHelper;

    public GestionBD(Context context){
        monBDHelper = new BDHelper(context, "baseClasse", null, 1);
    }

    public void open(){
        maBase = monBDHelper.getWritableDatabase();
    }

    public void close(){
        maBase.close();
    }

    public long ajouteClasse(Classes classes){
        ContentValues v = new ContentValues();
        v.put("nom", classes.getNom());
        v.put("difficulte", classes.getDifficulte());
        v.put("description", classes.getDescription());
        v.put("dieu", classes.isDieu());
        return maBase.insert("classes", null, v);
    }

    public void suppimeClasse(){
        maBase.delete("classes", null, null);
    }

    public ArrayList<String> donneChaineClasses(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select nom, difficulte, description, dieu from classes order by nom", null);
        while(c.moveToNext())
            liste.add(c.getString(0)+"_"+c.getString(1));
        if(liste==null){
            liste.add("erreur de bdd !");
        }
        return liste;
    }

    public ArrayList<String> donneLesClasses(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select nom from classes order by nom", null);
        while(c.moveToNext())
            liste.add(c.getString(0));
        if(liste==null){
            liste.add("erreur de bdd !");
        }
        return liste;
    }

    public Classes donneUneClasse(String choix){
        Classes laClasse;
        String laRequete = "select nom, difficulte, description, dieu from classes where nom like'"+choix+"'";
        Cursor c = maBase.rawQuery(laRequete, null);
        if(c.moveToNext()){
            laClasse = new Classes(c.getString(0),c.getString(1));
        }
        else{
            laClasse = new Classes("erreur bdd", "erreur bdd");
        }
        return laClasse;
    }

}
