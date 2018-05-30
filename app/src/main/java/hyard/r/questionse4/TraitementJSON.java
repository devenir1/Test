package hyard.r.questionse4;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 27/05/2018.
 */

public class TraitementJSON extends AsyncTask<String, Void, Boolean>{

    private List<Classe> lesClasses = new ArrayList<Classe>();
    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    GestionBD sgbd;

    public TraitementJSON(Context context){
        this.context = context;
        sgbd = new GestionBD(context);
    }

    @Override
    protected Boolean doInBackground(String...urls) {

        Log.i("doInBack", "Depart: ");
        sgbd.open();

        try {
            url = new URL(urls[0]);
            Log.i("doInBack", "le fichier distant : " + urls[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Boolean result = false;
        try{
            String ficClasses;
            //Récupération du fichier json sur serveur
            ficClasses = lectureFichierDistant();
            Log.i("doInBack", "le fichier distant : "+ficClasses);
            //Transformation du fichier obtenu en objet JSON
            JSONObject jsonClasses = parseClasses(ficClasses);
            //Traitement de l'objet JSON pour obtenir des instances des Classes
            Log.i("doInBack","le fichier json : "+jsonClasses.toString());
            recClasses(jsonClasses);
            Log.i("doInBack", "nombre de classes : "+lesClasses.size());
            int num = 1 ;
            StringBuilder message = new StringBuilder("les classes :");
            long id;
            for(Classe classe : lesClasses){
                message.append(classe.getNom());
                message.append(", ");
                message.append(classe.getDifficulte());
                message.append(", ");
                message.append(classe.getDescription());
                message.append(", ");
                message.append(classe.getDieu());
                message.append("\n");
                num++;
                id = sgbd.ajouteClasse(classe);
            }
            Log.i("doInBack","messgae : "+message);
            sgbd.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void recClasses(JSONObject jsonClasses){

        JSONArray lesClasses = null;
        try{
            lesClasses = jsonClasses.getJSONArray("lesclasses");
        }catch(JSONException e){
            e.printStackTrace();;
            Log.i("recClasse", "erreurJSArray");
        }

        for(int i = 0; i < lesClasses.length(); i++){
            JSONObject nuplet = null;
            String nom, description;
            Long id;
            int difficulte;
            int dieu;
            Classe classe;
            try{
                nuplet = lesClasses.getJSONObject(i);
                nom = nuplet.getString("nom");
                difficulte = nuplet.getInt("difficulte");
                description = nuplet.getString("description");
                dieu = nuplet.getInt("dieu");
                classe = new Classe(nom, difficulte, description, dieu);
                lesClasses.add(classe);
                id = sgbd.ajouteClasse(classe);
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }

    private  JSONObject parseClasses (String textClasses){
        if(textClasses != null){
            try{
                jObj = new JSONObject(textClasses);
            }catch (JSONException e){
                e.printStackTrace();
                Log.i("parper", "erreurJSObj");
            }
            return jObj;
        }
        return null;
    }

    private String lectureFichierDistant(){
        StringBuilder builder = new StringBuilder();

        //adresse du serveur (modification pour passage en production
        try{
            connexion = (HttpURLConnection) url.openConnection();
        }catch(IOException e){
            e.printStackTrace();
        }

        String line;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
        }catch(IOException e1){
            e1.printStackTrace();
        }

        try{
            while((line = br.readLine()) != null){
                builder.append(line).append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public List<Classe> getLesClasses(){
        return lesClasses;
    }

    public String getLesNoms(){
        String liste = "";
        for(Classe classe : lesClasses)
            liste += classe.getNom()+"\n";
        return liste;
    }
}
