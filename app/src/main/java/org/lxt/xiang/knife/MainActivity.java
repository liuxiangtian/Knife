package org.lxt.xiang.knife;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import org.lxt.xiang.library.ShareTool;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        initViews(this);
    }

    private void initViews(Context context) {
        PrefsApi prefsApi = ShareTool.create(this, PrefsApi.class);
        StringBuilder builder = new StringBuilder();
        prefsApi.saveId(9527);
        prefsApi.saveName("Tom and Jerry");
        prefsApi.saveVersion(12);
        int id = prefsApi.getId();
        builder.append("Id: ").append(prefsApi.getId()).append("\n")
                .append("name: ").append(prefsApi.getName()).append("\n")
                .append("version: ").append(prefsApi.getVersion());
        mTextView.setText(builder.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
