package com.deltasoftwares.fueltofitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Diet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Diet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Diet extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public AdView mAdView;



    TextView content;
    String[] bulk = {"* Protein Shake + 2 Boiled Potato Sandwich(Brown Bread),immedietly after bed",
            "*100gm of oats + 4 Boiled Eggs (2 complete + 2 whites",
            "* Breakfast : anyhting expect fried, curd is must","* Fruit Salad",
            "* Lunch:- 90gm Rice + vegetable + pulses + chicken \n   veg : can have curd instead of chicken, rest same ",
            "* Before Workout :- 100gm of Oats + 4 slices of Brown Bread",
            "* After Workout :- Protein Shake + 6 egg white + 1 banana",
            "Dinner :- One bowl of brown rice + chicken \n veg :- pulses and vegetables instead of chicken",
    "--> Drinnk 3-4 Litre of water a day "};



    private OnFragmentInteractionListener mListener;

    public Diet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a mat instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A mat instance of fragment Diet.
     */
    // TODO: Rename and change types and number of parameters
    public static Diet newInstance(String param1, String param2) {
        Diet fragment = new Diet();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_diet, container, false);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, bulk);

        ListView listView = (ListView)v.findViewById(R.id.diet_list);
        listView.setAdapter(adapter);

        //adds
        mAdView = (AdView)v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





        return v;
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        void onFragmentInteraction(Uri uri);
    }
}
