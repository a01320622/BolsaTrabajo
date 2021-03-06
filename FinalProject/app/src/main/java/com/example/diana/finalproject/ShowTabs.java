package com.example.diana.finalproject;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
/**
 * Created by Diana on 11/16/15.
 */
public class ShowTabs extends Activity {

    private TabHost myTabHost;
    int current;
    String image;
    String matricula;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        Bundle b = getIntent().getExtras();
        image = b.getString("image");
        current = b.getInt("current");
        matricula = b.getString("matricula");

//        widget = (TabWidget)findViewById(R.id.lltab1);
//        get tabHost

        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this,false);
        mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate


        myTabHost = (TabHost) findViewById(R.id.tabHost);


//        set up the tabhost

        myTabHost.setup(mLocalActivityManager);


//        FIRST TAB - Favorites  SECOND TAB - Vacancy THIRD TAB - Profile

        TabHost.TabSpec tabFav,tabVacancy,tabProfile;

        tabFav = myTabHost.newTabSpec("favorites");
        tabVacancy = myTabHost.newTabSpec("vacancy");
        tabProfile = myTabHost.newTabSpec("tabProfile");

//        FAVORITES

        Intent intentFav = new Intent().setClass(this, Favorites.class);
        Bundle dis = new Bundle();
        dis.putString("matricula",matricula);
        intentFav.putExtras(dis);
        tabFav.setContent(intentFav);
        tabFav.setIndicator("Favoritos");
        myTabHost.addTab(tabFav);

//        VACANCY
        Intent intentVacancy = new Intent().setClass(this, Inicio.class);
        Bundle di = new Bundle();
        di.putString("matricula",matricula);
        intentVacancy.putExtras(di);
        tabVacancy.setContent(intentVacancy);
        tabVacancy.setIndicator("Vacantes");
        myTabHost.addTab(tabVacancy);

//        PROFILE

        Intent intentProfile = new Intent().setClass(this, Perfil.class);
        Bundle d = new Bundle();
        d.putString("image", image);
        d.putString("matricula", matricula);
        intentProfile.putExtras(d);
        tabProfile.setContent(intentProfile);
        tabProfile.setIndicator("Perfil");
        myTabHost.addTab(tabProfile);
        myTabHost.setCurrentTab(current);

    }

    @Override
    public void onBackPressed()
    {
        Log.d("CREATION", "go back pressed!");
        if(getFragmentManager().getBackStackEntryCount()>0)
        {
            Intent intent = new Intent(this, Inicio.class);
            startActivity(intent);

        }else
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


}
