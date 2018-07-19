package com.example.sbs.aischoolparentapp.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbs.aischoolparentapp.R;

public class MonthNameActivity extends CommonAppCompatActivity {
    TextView janId, febId, marId, aprId, mayId, juneId, julyId, augId, sepId, octId, novId, decId;
    ImageView backiconId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_name);
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
                Intent intent = new Intent(MonthNameActivity.this, DashBoadActivity.class);
                startActivity(intent);
            }
        });

        janId = (TextView) findViewById(R.id.janId);
        febId = (TextView) findViewById(R.id.febId);
        marId = (TextView) findViewById(R.id.marId);
        aprId = (TextView) findViewById(R.id.aprId);
        mayId = (TextView) findViewById(R.id.mayId);
        juneId = (TextView) findViewById(R.id.juneId);
        julyId = (TextView) findViewById(R.id.julyId);
        augId = (TextView) findViewById(R.id.augId);
        sepId = (TextView) findViewById(R.id.sepId);
        octId = (TextView) findViewById(R.id.octId);
        novId = (TextView) findViewById(R.id.novId);
        decId = (TextView) findViewById(R.id.decId);
        janId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "January");
                startActivity(intent);
            }
        });
        febId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "Febuary");
                startActivity(intent);
            }
        });
        marId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "March");
                startActivity(intent);
            }
        });
        aprId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "april");
                startActivity(intent);
            }
        });
        mayId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "May");
                startActivity(intent);
            }
        });
        juneId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "June");
                startActivity(intent);
            }
        });
        julyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "July");
                startActivity(intent);
            }
        });
        augId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "Augest");
                startActivity(intent);
            }
        });
        sepId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "September");
                startActivity(intent);
            }
        });
        octId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "Octomber");
                startActivity(intent);
            }
        });
        novId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "Novemeber");
                startActivity(intent);
            }
        });
        decId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthNameActivity.this, PerformanceActivity.class);
                intent.putExtra("monthkey", "December");
                startActivity(intent);
            }
        });


    }
}

