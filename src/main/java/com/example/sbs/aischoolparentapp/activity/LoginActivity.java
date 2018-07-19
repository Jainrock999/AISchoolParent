package com.example.sbs.aischoolparentapp.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.other.UserAccount;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.model.LoginModel;
import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etLoginId, etPasswordId;
    TextView txtSignInId;
    LoginModel loginModel;
    List<LoginModel> loinlist = new ArrayList<>();
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        etLoginId = (EditText) findViewById(R.id.etLoginId);
        etPasswordId = (EditText) findViewById(R.id.etPasswordId);
        txtSignInId = (TextView) findViewById(R.id.txtSignInId);


        txtSignInId.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtSignInId:
                if (UserAccount.isEmpty(etLoginId, etPasswordId)) {
                    getlogin();
                } else {
                    UserAccount.EditTextPointer.setError("Fields Can't be Empty!");
                    UserAccount.EditTextPointer.requestFocus();
                }
                break;
        }

    }

    private void getlogin() {
        new JSONParser(LoginActivity.this).parseVollyStringRequest(ConstValue.LOGIN_URL, 1, getParam(), new Helper() {
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);
                    if (object.getString("responce").equals("true")) {
                        JSONObject content = object.getJSONObject("data");
                        for (int i = 0; i < content.length(); i++) {
                            loginModel = new LoginModel();
                            loginModel.setParent_user_name(content.getString("parent_user_name"));
                            loginModel.setChild_id(content.getString("student_id"));
                            loginModel.setStandard_id(content.getString("student_standard"));
                            loginModel.setSchool_id(content.getString("school_id"));
                            SaveData.setStandardId(getApplicationContext(), content.getString("student_standard"));
                            SaveData.setParentName(getApplicationContext(), content.getString("parent_user_name"));
                            SaveData.setchildid(getApplicationContext(), content.getString("student_id"));
                            SaveData.setSchoolId(getApplicationContext(), content.getString("school_id"));
                            //Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DashBoadActivity.class);
                            startActivity(intent);
                        }
                        loinlist.add(loginModel);
                    } else {
                        Toast.makeText(LoginActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("parent_user_name", etLoginId.getText().toString());
        params.put("password", etPasswordId.getText().toString());
        return params;

    }

}
