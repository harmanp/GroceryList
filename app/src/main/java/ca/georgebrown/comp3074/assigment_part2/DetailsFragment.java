package ca.georgebrown.comp3074.assigment_part2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView t;

    public MyItems.Element getItem() {
        return item;
    }

    public void setItem(MyItems.Element item) {
        this.item = item;
        Activity a = getActivity();
        if(a != null) {
            TextView t = a.findViewById(R.id.textViewDetail);
            if (t != null) {
                t.setText(item.content);
            }
        }
    }
    private Button btnDelete;
    private MyItems.Element item;

    // TODO: Rename and change types of parameters
    private String itemId;
    private String content;

    private OnFragmentInteractionListener mListener;

    public DetailsFragment(String itemId, String content) {
        // Required empty public constructor
        this.itemId = itemId;
        this.content = content;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param itemId Parameter 1.
     * @param content Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String itemId, String content) {
        DetailsFragment fragment = new DetailsFragment("itemId" , "content");
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, itemId);
        args.putString(ARG_PARAM2, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            itemId = getArguments().getString(ARG_PARAM1);
            content = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        t = view.findViewById(R.id.textViewDetail);

        t.setText(content);

        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(itemId);
                //deleteItem();
                Toast.makeText(getActivity(),"The item name:  " + content + " is deleted.",Toast.LENGTH_LONG).show();
               Intent i=new Intent(getContext(),MainActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(MyItems.Element item) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(item);
//            //deleteItem();
//            Toast.makeText(getActivity(),"The item is deleted 1.",Toast.LENGTH_LONG).show();
//            Intent i=new Intent(getContext(),MainActivity.class);
//            startActivity(i);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void deleteItem(){
        ItemDbHelper itemDbHelper = new ItemDbHelper(getActivity());
        SQLiteDatabase database = itemDbHelper.getWritableDatabase();


        int id = Integer.parseInt(t.getText().toString());
        itemDbHelper.deleteItem(id, database);

        itemDbHelper.close();
        //edit_id.setText("");
        //Toast.makeText(getActivity(),"Items Deleted successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String itemId);
    }

}
