package hyard.r.questionse4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by admin on 27/05/2018.
 */

public class ImageDesClasses extends AppCompatActivity {

    private Integer[] imgClassesIds = {
            R.drawable.cra,
            R.drawable.ecaflip,
            R.drawable.eliotrope,
            R.drawable.eniripsa,
            R.drawable.enutrof,
            R.drawable.feca,
            R.drawable.huppermage,
            R.drawable.iop,
            R.drawable.osamodas,
            R.drawable.ouginak,
            R.drawable.pandawa,
            R.drawable.roublard,
            R.drawable.sacrieur,
            R.drawable.sadida,
            R.drawable.sram,
            R.drawable.steamer,
            R.drawable.xelor,
            R.drawable.zobal,
    };

    /*Propriétés*/
    ImageView imgClasses;
    TextView nomView;
    TextView difficulteView;
    TextView descriptionView;
    TextView dieuView;
    ArrayList<String> lesClasses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_des_classes);
        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesClasses = sgdb.donneLesClasses();
        Intent intent = getIntent();
        int laPosition = intent.getIntExtra("leChoix", 0);
        Toast.makeText(this, "le choix : "+laPosition, Toast.LENGTH_LONG).show();
        Log.i("dans activ3", "la position : "+laPosition);
        Classes lechoix = sgbd.donneUneClasse(classeChoisi);

        imgClasse = (ImageView) findViewById(R.id.laClasse);
        imgClasse.setImageResource(imgClasseIds[laPosition]);

        /*Recupération des données View*/
        nomView = (TextView) findViewById(R.id.nomView);
        difficulteView = (TextView) findViewById(R.id.difficulteView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);
        dieuView = (TextView) findViewById(R.id.dieuView);

        /*Set de tous les champs*/
        nom.setText(lechoix.getNom());
        difficulte.setText(lechoix.getDifficulte());
        description.setText(lechoix.getDescription());
        dieu.setText(lechoix.getDieu());
        }
}
