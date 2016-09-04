package org.lxt.xiang.knife;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.lxt.xiang.library.ShareTool;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews(this);
    }

    private void initViews(Context context) {
        PrefsApi prefsApi = ShareTool.create(this, PrefsApi.class);
        Log.i(TAG, "------------------------------");
        prefsApi.saveId(119);
        Log.i(TAG, "initViews: " + prefsApi.getId());
        Log.i(TAG, "------------------------------");
        prefsApi.saveName("Tom");
        Log.i(TAG, "initViews: " + prefsApi.getName());
        Log.i(TAG, "------------------------------");
        prefsApi.saveVersion(3);
        Log.i(TAG, "initViews: " + prefsApi.getVersion());
    }


}
