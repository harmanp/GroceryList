package ca.georgebrown.comp3074.assigment_part2;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements  View.OnClickListener
{
    private Button btnAdd, btnView;
    OnDbOperationListener dbOperationListener;

    public HomeFragment() {
        // Required empty public constructor
    }
    public interface OnDbOperationListener
    {
        public void dbOperationPerform(int method);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnView = view.findViewById(R.id.btnView);
        btnView.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnAdd:
                dbOperationListener.dbOperationPerform(0);
                break;

            case R.id.btnView:
                dbOperationListener.dbOperationPerform(1);
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try{
            dbOperationListener = (OnDbOperationListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement the interface method");
        }

    }
}
