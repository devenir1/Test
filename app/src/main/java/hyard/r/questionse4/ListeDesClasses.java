package hyard.r.questionse4;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by admin on 27/05/2018.
 */

public class ListeDesClasses extends ListActivity implements AdapterView.OnItemClickListener{

    ArrayList<String> lesClasses = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Changer layout
        Intent intent = getIntent();

        GestionBD sgbd = new GestionBD(this);
        sgbd.open();

        lesClasses = sgbd.donneLesNoms();
        Log.i("liste2", "les noms :"+lesClasses);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesClasses);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        sgbd.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent image = new Intent(this, ImageDesClasses.class);
        Log.i("clic","après avoir cliqué : "+position);
        Toast.makeText(this, " position : "+position, Toast.LENGTH_LONG).show();
        image.putExtra("leChoix", position);
        startActivity(image);
    }
}
