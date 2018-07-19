package com.example.sbs.aischoolparentapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.model.BehaviourPointModel;
import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BehaviourActivity extends CommonAppCompatActivity {
    private ProgressBar progressBar;
    TextView txtProgress;
    ImageView backiconId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behaviour);
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
                Intent intent = new Intent(BehaviourActivity.this, DashBoadActivity.class);
                startActivity(intent);
            }
        });

        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        getAllBehaviourPoint();

    }

    private void getAllBehaviourPoint() {
        new JSONParser(BehaviourActivity.this).parseVollyStringRequest(ConstValue.GET_BEHAVIOUR_POINT, 1, getParam(), new Helper() {
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);
                    if (object.getString("responce").equals("true")) {
                        String content = object.getString("data");
                        JSONObject jsonObject = new JSONObject(content);

                        if (jsonObject.getString("total_bp_point").equals("0")) {
                            progressBar.setProgress(0);
                            txtProgress.setText("0 %");
                        } else {
                            progressBar.setProgress(Integer.parseInt(jsonObject.getString("total_bp_point")));
                            txtProgress.setText(jsonObject.getString("total_bp_point") + " %");
                        }


                    } else {
                        Toast.makeText(BehaviourActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("student_id", SaveData.getchildid(getApplicationContext()));
        return params;


    }
}

