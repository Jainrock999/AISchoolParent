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
import android.widget.Toast;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.model.StemModel;
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

public class StemActivity extends CommonAppCompatActivity {
    StemModel stemModel;
    ImageView imStemOneId, imStremTwoId, imStemThreeId, imStemFourId, imStemFiveId, imStemSixId, imStemsevenId, imStemeightId;
    List<StemModel> stemlist = new ArrayList<>();
    ImageView backiconId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stem);
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
                Intent intent = new Intent(StemActivity.this, DashBoadActivity.class);
                startActivity(intent);
            }
        });

        imStemOneId = (ImageView) findViewById(R.id.imStemOneId);
        imStremTwoId = (ImageView) findViewById(R.id.imStremTwoId);
        imStemThreeId = (ImageView) findViewById(R.id.imStemThreeId);
        imStemFourId = (ImageView) findViewById(R.id.imStemFourId);
        imStemFiveId = (ImageView) findViewById(R.id.imStemFiveId);
        imStemSixId = (ImageView) findViewById(R.id.imStemSixId);
        imStemsevenId = (ImageView) findViewById(R.id.imStemsevenId);
        imStemeightId = (ImageView) findViewById(R.id.imStemeightId);
        getAllEvent();
    }


    private void getAllEvent() {
        new JSONParser(StemActivity.this).parseVollyStringRequest(ConstValue.GET_STEM, 1, getParam(), new Helper() {
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);
                    if (object.getString("responce").equals("true")) {
                        JSONArray content = object.getJSONArray("data");
                        for (int i = 0; i < content.length(); i++) {
                            JSONObject jsonObject = content.getJSONObject(i);
                            stemModel = new StemModel();
                            stemModel.setBadge_icon(jsonObject.getString("badge_icon"));
                            stemModel.setBadge_id(jsonObject.getString("badge_id"));
                            stemModel.setBadge_title(jsonObject.getString("badge_title"));
                            stemModel.setBadge_type(jsonObject.getString("badge_type"));
                            stemModel.setStatus(jsonObject.getString("status"));
                            switch (i) {
                                case 0:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemOneId);
                                    break;
                                case 1:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStremTwoId);
                                    break;
                                case 2:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemThreeId);
                                    break;
                                case 3:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemFourId);
                                    break;
                                case 4:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemFiveId);
                                    break;
                                case 5:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemSixId);
                                    break;
                                case 6:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemsevenId);
                                    break;
                                case 7:
                                    Picasso.with(getApplicationContext())
                                            .load(ConstValue.Badges_EVENT + stemModel.getBadge_icon())
                                            .into(imStemeightId);
                                    break;
                            }
                            stemlist.add(stemModel);
                        }

                    } else {
                        Toast.makeText(StemActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("badge_type", "Stem");
        return params;

    }
}
