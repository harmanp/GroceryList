package ca.georgebrown.comp3074.assigment_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.OnDbOperationListener,
        ReadItemFragment.OnListFragmentInteractionListener

{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragmentContainer)!=null){
            if(savedInstanceState !=null) {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, homeFragment).commit();
        }






    }


    public void dbOperationPerform(int method) {
        switch (method) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new AddItemFragment()).addToBackStack(null).commit();
                break;

            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new ReadItemFragment()).addToBackStack(null).commit();
                break;


        }

    }


    public void onListFragmentInteraction(MyItems.Element item) {

//        String value = item.content;
        Intent intent = new Intent(MainActivity.this, Main2DetailsActivity.class);
        intent.putExtra("id", item.id);
        intent.putExtra("content", item.content);
        startActivity(intent);

    }






}
