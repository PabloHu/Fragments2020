package com.software.huaman.fragments2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity

        implements FirstFragment.OnFragmentInteractionListener,
        SecondFragment.OnFragmentInteractionListener {

    public static final String TAG = "MainActivity";
    Button firstFragment, secondFragment, btnSecondFragSendData;
    TextView txtFromFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFromFragment = (TextView) findViewById(R.id.txtFromFragment);
        firstFragment = (Button) findViewById(R.id.firstFragment);
        secondFragment = (Button) findViewById(R.id.secondFragment);
        btnSecondFragSendData = (Button) findViewById(R.id.btnSecondFragSendData);

        btnSecondFragSendData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    SecondFragment secondFragment = (SecondFragment) getFragmentManager().findFragmentById(R.id.frameLayout);
                    secondFragment.activityToFragmentData("yellow from Main Activity!!!!!!!!!!!!!!!!!!!!!!");
                }catch(Exception e){
                    Log.wtf(TAG, "onClick: "+ e.toString());
                }

            }
        });


        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load First Fragment
                loadFragment(new FirstFragment());
            }
        });

        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                loadFragment(new SecondFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
//      create a FragmentManager
        FragmentManager fm = getFragmentManager();
//      create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//      replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
//      save the changes
        fragmentTransaction.commit();
    }

    public void test() {

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.d(TAG, "onFragmentInteraction: " + uri.toString());
    }


    @Override
    public void sendDataToActivity(String data) {

        try {
            txtFromFragment.setText(data);
            Log.d(TAG, "sendDataToActivity: " + data);
        } catch (Exception e) {

            Log.d(TAG, "sendDataToActivity: " + e.toString());
        }
    }
}