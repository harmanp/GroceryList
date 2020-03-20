package ca.georgebrown.comp3074.assigment_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2DetailsActivity extends AppCompatActivity
implements DetailsFragment.OnFragmentInteractionListener {

    public ItemDbHelper itemDbHelper=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_details);

        String id = this.getIntent().getStringExtra("id");
        String content = this.getIntent().getStringExtra("content");

        DetailsFragment detailsFragment = new DetailsFragment(id,content);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer1, detailsFragment).commit();

    }


    @Override
    public void onFragmentInteraction(String id) {
        int id1 = Integer.parseInt(id);

        MyItems.ITEMS.remove(MyItems.ITEM_MAP.get(id1));
        MyItems.ITEM_MAP.remove(id1);

        itemDbHelper=new ItemDbHelper(this);
        final SQLiteDatabase database = itemDbHelper.getWritableDatabase();
        itemDbHelper.deleteItem(id1, database);

        finish();

    }






}
