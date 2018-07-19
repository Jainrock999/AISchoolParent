package com.example.sbs.aischoolparentapp.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.model.EcoModel;
import com.example.sbs.aischoolparentapp.model.StemModel;
import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoActivity extends CommonAppCompatActivity {
    ImageView imTreeZeroId, imTreeOneId, imTreeTwoId, imTreeThreeId, imTreeFourId, imTreeFiveId, imTreeSixId, imTreeSevenId, imTreeEightId,
            imTreeNineId, imTreeTenId;
    List<EcoModel> ecolist = new ArrayList<>();
    int setEcoValue;
    EcoModel ecoModel;
    ImageView backiconId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco);
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
                Intent intent = new Intent(EcoActivity.this, DashBoadActivity.class);
                startActivity(intent);
            }
        });
        imTreeZeroId = (ImageView) findViewById(R.id.imTreeZeroId);
        imTreeOneId = (ImageView) findViewById(R.id.imTreeOneId);
        imTreeTwoId = (ImageView) findViewById(R.id.imTreeTwoId);
        imTreeThreeId = (ImageView) findViewById(R.id.imTreeThreeId);
        imTreeFourId = (ImageView) findViewById(R.id.imTreeFourId);
        imTreeFiveId = (ImageView) findViewById(R.id.imTreeFiveId);
        imTreeSixId = (ImageView) findViewById(R.id.imTreeSixId);
        imTreeSevenId = (ImageView) findViewById(R.id.imTreeSevenId);
        imTreeEightId = (ImageView) findViewById(R.id.imTreeEightId);
        imTreeNineId = (ImageView) findViewById(R.id.imTreeNineId);
        imTreeTenId = (ImageView) findViewById(R.id.imTreeTenId);

        getEcoTreePoint();


    }

    private void getEcoTreePoint() {
        new JSONParser(EcoActivity.this).parseVollyStringRequest(ConstValue.GETECO_POINT, 1, getParames(), new Helper() {
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);
                    if (object.getString("responce").equals("true")) {
                        JSONArray content = object.getJSONArray("data");
                        for (int i = 0; i < content.length(); i++) {
                            JSONObject jsonObject = content.getJSONObject(i);
                            ecoModel = new EcoModel();
                            ecoModel.setTotal_point(jsonObject.getInt("point"));
                            ecolist.add(ecoModel);
                            int EcoValue = ecoModel.getTotal_point();
                            setEcoValue = (EcoValue / 10);
                            switch (setEcoValue) {
                                case 0:
                                    imTreeZeroId.setImageDrawable(getResources().getDrawable(R.drawable.tree_zero));
                                    break;
                                case 1:
                                    imTreeOneId.setImageDrawable(getResources().getDrawable(R.drawable.tree_one));

                                    break;
                                case 2:
                                    imTreeTwoId.setImageDrawable(getResources().getDrawable(R.drawable.tree_two));
                                    break;
                                case 3:
                                    imTreeThreeId.setImageDrawable(getResources().getDrawable(R.drawable.tree_three));
                                    break;
                                case 4:
                                    imTreeFourId.setImageDrawable(getResources().getDrawable(R.drawable.tree_four));
                                    break;
                                case 5:
                                    imTreeFiveId.setImageDrawable(getResources().getDrawable(R.drawable.tree_five));
                                    break;
                                case 6:
                                    imTreeSixId.setImageDrawable(getResources().getDrawable(R.drawable.tree_six));
                                    break;
                                case 7:
                                    imTreeSevenId.setImageDrawable(getResources().getDrawable(R.drawable.tree_seven));
                                    break;
                                case 8:
                                    imTreeEightId.setImageDrawable(getResources().getDrawable(R.drawable.tree_eight));
                                    break;
                                case 9:
                                    imTreeNineId.setImageDrawable(getResources().getDrawable(R.drawable.tree_nine));
                                    break;
                                case 10:
                                    imTreeTenId.setImageDrawable(getResources().getDrawable(R.drawable.tree_ten));
                                    break;
                            }

                        }
                    } else {
                        Toast.makeText(EcoActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParames() {
        HashMap<String, String> params = new HashMap<>();
        params.put("point_type", "ECO");
        params.put("student_id", SaveData.getchildid(getApplicationContext()));
        return params;

    }
}
