package hyard.r.questionse4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Classe> lesClasses;
    TraitementJSON traiteJSON;
    GestionBD sgbd;
    ArrayList<String> lesNoms = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button aff;
        lesClasses = new TraitementJSON(this);
        sgbd = new GestionBD(this);
        sgbd.open();
        sgbd.suppimeClasse();

        traiteJSON.execute("ADDRSERVER");

        sgbd.close();

        aff = (Button)findViewById(R.id.bafficher);
        aff.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){
        sgbd.open();
        lesNoms = sgbd.donneChaineClasses();
        Integer nb = lesNoms.size();
        TextView lecture = (TextView)findViewById(R.id.txtlecture);
        lecture.setText(lesNoms.toString());
        sgbd.close();
        Intent liste = new Intent(this, ListeDesClasses.class);
        startActivity(liste);

    }

}

