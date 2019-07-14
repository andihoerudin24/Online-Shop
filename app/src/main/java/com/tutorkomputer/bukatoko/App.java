package com.tutorkomputer.bukatoko;

import android.app.Application;

import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.database.SQLiteHelper;

import java.util.HashMap;

public class App extends Application {


    public static SQLiteHelper sqLiteHelper;

    public static PrefsManager prefsManager;
    public static HashMap<String,String> sessPref;

    @Override
    public void onCreate() {
        super.onCreate();

        sqLiteHelper = new SQLiteHelper(this);

        prefsManager = new PrefsManager(this);
        sessPref = prefsManager.getUserDetail();


    }
}
