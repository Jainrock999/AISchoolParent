package com.example.sbs.aischoolparentapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.model.AcademicPointModel;
import com.example.sbs.aischoolparentapp.model.BehaviourPointModel;
import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicActivity extends CommonAppCompatActivity {
    RadarChart mChart;
    ArrayList<RadarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    String myArray[] = new String[6];
    XAxis xAxis;
    ImageView backiconId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mChart = (RadarChart) findViewById(R.id.chart);
        backiconId = (ImageView) findViewById(R.id.backiconId);
        backiconId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcademicActivity.this, DashBoadActivity.class);
                startActivity(intent);
            }
        });

        mChart.getDescription().setEnabled(false);
        xAxis = mChart.getXAxis();
        Legend l = mChart.getLegend();
        l.setEnabled(false);

        getAllAcademicPoint();


    }

    private void getAllAcademicPoint() {
        new JSONParser(AcademicActivity.this).parseVollyStringRequest(ConstValue.GET_ACADEMIC_POINT, 1, getParams(), new Helper() {
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);
                    if (object.getString("responce").equals("true")) {
                        JSONArray content = object.getJSONArray("data");
                        for (int i = 0; i < content.length(); i++) {
                            JSONObject jsonObject = content.getJSONObject(i);
                            entries.add(new RadarEntry(Float.valueOf(jsonObject.getString("point"))));
                            labels.add(jsonObject.getString("subject_title"));

                        }
                        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "Academic Point");
                        dataset_comp1.setColor(Color.RED);
                        dataset_comp1.setFillColor(Color.RED);
                        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
                        sets.add(dataset_comp1);
                        RadarData data = new RadarData(sets);
                        data.setDrawValues(false);
                        mChart.setData(data);
                        mChart.invalidate();
                        mChart.animate();


                    } else {
                        Toast.makeText(AcademicActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(AcademicActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("student_id", SaveData.getchildid(getApplicationContext()));
        params.put("standard_id", SaveData.getStandardId(getApplicationContext()));
        return params;


    }
}

