package com.example.sbs.aischoolparentapp.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.savedata.SaveData;

public class EcaActivity extends CommonAppCompatActivity {
    ImageView backiconId, imstatusoffId, imStatusOnId, imProfilestudentId;
    int location = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        backiconId = (ImageView) findViewById(R.id.backiconId);
        backiconId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcaActivity.this, DashBoadActivity.class);
                startActivity(intent);
            }
        });
        imStatusOnId = (ImageView) findViewById(R.id.imStatusOnId);
        if (location == 0) {
            imStatusOnId.setVisibility(View.GONE);
        } else {
            imStatusOnId.setVisibility(View.VISIBLE);

        }


    }
}
