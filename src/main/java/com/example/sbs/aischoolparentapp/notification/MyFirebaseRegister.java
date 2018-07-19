package com.example.sbs.aischoolparentapp.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Rajesh on 2017-09-09.
 */

public class MyFirebaseRegister {
    Context context;


    public MyFirebaseRegister(Activity context) {


    }

    public void RegisterUser() {
        // [START subscribe_topics]
        String token = FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic(ConstValue.TOPIC_GLOBAL);
        // [END subscribe_topics]
        SaveData.setreqId(context, token);
        Log.e("token", token);
        sendRegistrationToServer(token);
        //  new loadDataTask(token).execute();
        Intent registrationComplete = new Intent(ConstValue.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", token);
        LocalBroadcastManager.getInstance(context).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
        update(token);

    }

    public void update(String token) {
        String result = ConstValue.FCM_REGISTER_URL;
        new JSONParser(context).parseVollyStringRequestWithautProgressBar(result, 1, getParms(token), new Helper() {

            @Override
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParms(String token) {
        HashMap<String, String> params = new HashMap<>();
        params.put("student_id", SaveData.getchildid(context));
        params.put("token", token);
        params.put("device", "android");
        return params;

    }
}


