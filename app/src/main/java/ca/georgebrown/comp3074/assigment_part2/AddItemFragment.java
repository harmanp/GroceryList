package ca.georgebrown.comp3074.assigment_part2;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {

    private Button btnSave;
    EditText  Name;

    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        btnSave = view.findViewById(R.id.btnSave);
        Name = view.findViewById(R.id.editItemName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();

                ItemDbHelper itemDbHelper = new ItemDbHelper(getActivity());
                SQLiteDatabase database = itemDbHelper.getWritableDatabase();
                itemDbHelper.putItemInfo(name, database);
                itemDbHelper.close();
                Name.setText("");
                Toast.makeText(getActivity(),
                        "Item Name: " + name + " saved successfuly", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

}
