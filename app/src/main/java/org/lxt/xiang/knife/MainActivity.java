package org.lxt.xiang.knife;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import org.lxt.xiang.library.Knife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        initViews(this);
    }

    private void initViews(Context context) {
        PrefsApi prefsApi = Knife.create(this, PrefsApi.class);
        prefsApi.putType("Harry Potty");
        String name = prefsApi.getTheme("Dark");
        Log.i(TAG, "initViews: " + name);
        prefsApi.putType("JK LUOLIN");
        name = prefsApi.getTheme("Dark");
        Log.i(TAG, "initViews: " + name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
