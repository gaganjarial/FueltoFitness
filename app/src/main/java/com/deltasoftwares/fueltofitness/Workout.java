package com.deltasoftwares.fueltofitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
 import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;


public class Workout extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TabHost tabHost;
    TextView exe,note,tueexe,wedexe,thuexe,friexe,satexe;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String bulk_note = new String("NOTE :- Go for lifting maximum weight your body can and try increasing it every week with small amount. You are always better than yesterday.");
    String lean_note = new String("NOTE :- Lift moderate weight with controlled repetetion, concentrate on your muscle while you exercise. dont forget to do the completely inhale and exhale while exercising.  Rest 40-60 sec in between sets");
    String shredding_note = new String("NOTE :- Go for maximum repetetion lift comparatively less wight, lwt your muscle pump . You are always better than yesterday.");


    private OnFragmentInteractionListener mListener;

    public Workout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a mat instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A mat instance of fragment Workout.
     */
    // TODO: Rename and change types and number of parameters
    public static Workout newInstance(String param1, String param2) {
        Workout fragment = new Workout();
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
        View v=inflater.inflate(R.layout.fragment_workout, container, false);
        // Inflate the layout for this fragment
        TabHost host = (TabHost)v.findViewById(R.id.tabHost);

         exe = (TextView)v.findViewById(R.id.exe);
         note = (TextView)v.findViewById(R.id.note);
         tueexe = (TextView)v.findViewById(R.id.tue_exe);
         wedexe = (TextView)v.findViewById(R.id.wed_exe);
         thuexe = (TextView)v.findViewById(R.id.thu_exe);
         friexe = (TextView)v.findViewById(R.id.fri_exe);
         satexe = (TextView)v.findViewById(R.id.sat_exe);

               host.setup();





        TabHost.TabSpec spec = host.newTabSpec("Mon");
                 spec.setContent(R.id.Mon);
                 spec.setIndicator("MON");

                host.addTab(spec);


        spec = host.newTabSpec("TUE");
                 spec.setContent(R.id.TUE);
                 spec.setIndicator("TUE");
                 host.addTab(spec);


                //Tab 3
                 spec = host.newTabSpec("WED");
        spec.setContent(R.id.WED);
        spec.setIndicator("WED");
        host.addTab(spec);


        spec = host.newTabSpec("THU");
        spec.setContent(R.id.THU);
        spec.setIndicator("THU");
        host.addTab(spec);

        spec = host.newTabSpec("FRI");
        spec.setContent(R.id.FRI);
        spec.setIndicator("FRI");
        host.addTab(spec);

        spec = host.newTabSpec("SAT");
        spec.setContent(R.id.SAT);
        spec.setIndicator("SAT");
        host.addTab(spec);



        SharedPreferences sp =this.getActivity().getSharedPreferences("SP", Context.MODE_PRIVATE);
       int i= sp.getInt("category",-1);
        if( i == 0)
        {}
        else if(i==1)
            b();
        else if (i==2)
            l();



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void l()
    {

        note.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+lean_note);


        exe.setText("  1. Inclined Dumbell      4        15 each\n\n  2. Inclined Fly                3    15-18 each\n\n  3. Flat Dumbell              3        15 each\n\n  4. Lower Cable Cross   3        15 each\n\n  5. Pull Over                    3        15 each\n\n  6. Push Ups                   4     Till Failure");

        tueexe.setText("  1. Med. Rod Curl           3          10-12 \n\n  2. Hammer Curl             4          10-12 \n\n  3. Dumbell Curl             3           10-12\n\n  4. Push Down                4        15 each\n\n  5. D-Skull Crusher       4        15 each\n\n  6. Behind Neck Press  4          12-15\n  7. Forearm Curl            4       Till Failure");

        wedexe.setText("  1. Front Squats              2         15 each\n\n  2. Back Squats              4          10-15\n\n  3. Leg Extension           4          20 each\n\n  4. Walking Lunges        3        16 steps\n\n  5. Leg Press                  4          20 each \n\n  6. Stiff Leg Deadlift      3        15 each\n  7. Weighted Calfes       3        15 each ");

        thuexe.setText("        ");

        friexe.setText("  1. Wide Pullups             4          10-15\n\n  2. Seated Rowing         4           12-15\n\n  3. Dumbell Rowing       4           12-15\n\n  4. T-Bar                          4            12-15\n\n  5. Lats Pulldown          4           15 each\n\n  6. Deadlift                     4           10 each");

        satexe.setText("  1. Dumbell Press          4          12-16\n\n  2. Military Press           4          12-15\n\n  3. Side Raise                 3          12-15\n\n  4. Front Raise               3           12-15\n\n  5. Side Raise                 2         10 each\n\n  6. Revere Delts             3         10 each\n  7. Uprights                    4         10 each "  );

    }
    public void b()
    {
        note.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+bulk_note);


        exe.setText("  1. FLat Bench(Rod)       4      12,10,8,6\n\n  2. Inclined Dumbell       4      12,10,8,6\n\n  3. Inclined Fly                 3      15,15,15\n\n  4. Lower Cable Cross    3      15,12,10");

        tueexe.setText("  1. Rod Curl                     4        10,8,6,4 \n\n  2. Dumbell Curl             3          10,8,6 \n\n  3. Hammer Curl            5     10,10,8,6,4\n\n  4. zig-zag Close Grip   4           8-10\n\n  5. Skull Crusher            4           8-10\n\n  6. Pushdown  +             3      10,10,10\n       KickBack");

        wedexe.setText("  1. Squats                       5            8-10\n\n  2. Half Squat                 3       10,10,10\n\n  3. Leg Press                  4            8-10\n\n  4. Leg Extension           4      15,12,10,8\n\n  5. Walking Lunges        3        10 steps\n\n  6. Leg Curls                   4      10,10,8,8\n  7. Weighted Calfes       4        10 each ");

        thuexe.setText("        ");

        friexe.setText("  1. Wide Pullups             4        15 each\n\n  2. Bendover rowing      4           8-10\n\n  3. Deadlift                      4         10,8,6,4\n\n  4. Seated Rowing         4      15,12,10,8\n\n  5. Lats Pulldown          4           10-12\n\n  6. Lower Back               4       15 each");

        satexe.setText("  1. Rod Shoulder Press 4          8-10\n\n  2. Dumbell Press           4          8-10\n\n  3. Side Raise                  4          8-10\n\n  4. Front Raise                3           8-10\n\n  5. Reverse Delts            3         10-12\n\n  6. Shrugs                        4        10 each\n  7.Uprights                      4           8-10 "  );


    }
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


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
