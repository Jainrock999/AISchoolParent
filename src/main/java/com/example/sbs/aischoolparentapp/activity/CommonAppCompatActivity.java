package com.example.sbs.aischoolparentapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class CommonAppCompatActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getSupportActionBar().setHomeButtonEnabled(true);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = null;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_academic:
                intent = new Intent(this, AcademicActivity.class);
                break;
            case R.id.action_behaviour:
                intent = new Intent(this, BehaviourActivity.class);
                break;
            case R.id.action_nutirition:
                intent = new Intent(this, NutritionActivity.class);
                break;
            case R.id.action_performance:
                intent = new Intent(this, MonthNameActivity.class);
                break;
            case R.id.action_activity:

                break;
            case R.id.action_logout:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CommonAppCompatActivity.this);
                alertDialog.setTitle("Logout...");
                alertDialog.setMessage("Are you sure you want to Logout this App ?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SaveData.logout(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Log out SuccessFully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(CommonAppCompatActivity.this, LoginActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(CommonAppCompatActivity.this, DashBoadActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                });

                alertDialog.show();
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    private Map<String, String> getParam() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("student_id", SaveData.getchildid(getApplicationContext()));
        return hashMap;
    }


}
