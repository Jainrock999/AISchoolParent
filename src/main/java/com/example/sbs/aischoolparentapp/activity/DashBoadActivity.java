package com.example.sbs.aischoolparentapp.activity;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.crashlytics.android.Crashlytics;
import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.adapter.AnnounceAdapter;

import com.example.sbs.aischoolparentapp.model.EventModel;
import com.example.sbs.aischoolparentapp.notification.Constants;

import com.example.sbs.aischoolparentapp.savedata.SaveData;
import com.example.sbs.aischoolparentapp.volley.Helper;
import com.example.sbs.aischoolparentapp.volley.JSONParser;
import com.example.sbs.aischoolparentapp.volley.M;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import io.fabric.sdk.android.Fabric;

public class DashBoadActivity extends CommonAppCompatActivity implements View.OnClickListener {
    Button btnBriId, btnEcoId, btnStemId, btnOtherId;
    ImageView ivStudentImageId;
    public BroadcastReceiver mRegistrationBroadcastReceiver;
    List<EventModel> eventist = new ArrayList<>();
    String strDate;
    ImageView ivEventImageId;
    TextView tvEventNameId, tvEventStartTimeId, tvLocationId;
    TextView tvNoEventId;
    RelativeLayout rlOneId;
    private final SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.ENGLISH);
    String server_url;
    AnnounceAdapter announceAdapter;
    RecyclerView rvAnnounceMentId;
    ArrayList<HashMap<String, String>> ann_list;
    ImageView menu;
    TextView backbutton;
    Button btnEcaId;

    //add calendar
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;
    CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_dash_boad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar day = eventDay.getCalendar();
                Log.e("Gourav", "" + day);

            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
        rvAnnounceMentId = (RecyclerView) findViewById(R.id.rvAnnounceMentId);
        tvNoEventId = (TextView) findViewById(R.id.tvNoEventId);
        rlOneId = (RelativeLayout) findViewById(R.id.rlOneId);
        tvEventNameId = (TextView) findViewById(R.id.tvEventNameId);
        ivEventImageId = (ImageView) findViewById(R.id.ivEventImageId);
        tvEventStartTimeId = (TextView) findViewById(R.id.tvEventStartTimeId);
        tvLocationId = (TextView) findViewById(R.id.tvLocationId);
        btnBriId = (Button) findViewById(R.id.btnBriId);
        btnEcoId = (Button) findViewById(R.id.btnEcoId);
        btnOtherId = (Button) findViewById(R.id.btnOtherId);
        btnStemId = (Button) findViewById(R.id.btnStemId);
        ivStudentImageId = (ImageView) findViewById(R.id.ivStudentImageId);
        // compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
//        compactCalendarView.setLocale(TimeZone.getDefault(), /*Locale.getDefault()*/Locale.ENGLISH);
//        Calendar calendar = Calendar.getInstance();
//        strDate = "" + dFormat.format(calendar.getTime());
//        compactCalendarView.setShouldDrawDaysHeader(true);


        btnStemId.setOnClickListener(this);
        btnOtherId.setOnClickListener(this);
        btnEcoId.setOnClickListener(this);
        btnBriId.setOnClickListener(this);

        tvEventNameId.setText("");
        tvEventStartTimeId.setText("");
        tvLocationId.setText("");
        getAllEvent();

        new GetAnnounceAsynctask().execute();
        ann_list = new ArrayList<>();
//        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
//            @Override
//            public void onDayClick(Date dateClicked) {
//                strDate = dFormat.format(dateClicked);
//                getAllEvent();
//
//            }
//
//            @Override
//            public void onMonthScroll(Date firstDayOfNewMonth) {
//
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            Log.e("gou", "" + myEventDay);
            mCalendarView.setEvents(mEventDays);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBriId:
                Intent briIntent = new Intent(DashBoadActivity.this, BriActivity.class);
                startActivity(briIntent);
                break;
            case R.id.btnEcoId:
                Intent ecoIntent = new Intent(DashBoadActivity.this, EcoActivity.class);
                startActivity(ecoIntent);
                break;

            case R.id.btnOtherId:
                Intent otherIntent = new Intent(DashBoadActivity.this, OtherActivity.class);
                startActivity(otherIntent);
                break;
            case R.id.btnStemId:
                Intent stemIntent = new Intent(DashBoadActivity.this, StemActivity.class);
                startActivity(stemIntent);
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(ConstValue.REGISTRATION_COMPLETE));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(ConstValue.PUSH_NOTIFICATION));

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_item, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        Intent intent = null;
//        //noinspection SimplifiableIfStatement
//        switch (id) {
//            //noinspection SimplifiableIfStatement
//            case R.id.action_academic:
//                intent = new Intent(this, AcademicActivity.class);
//                break;
//            case R.id.action_activity:
//                intent = new Intent(this, Activity.class);
//                break;
//            case R.id.action_behaviour:
//                intent = new Intent(this, BehaviourActivity.class);
//                break;
//            case R.id.action_nutirition:
//                intent = new Intent(this, NutritionActivity.class);
//                break;
//            case R.id.action_performance:
//                intent = new Intent(this, PerformanceActivity.class);
//                break;
//            case R.id.action_logout:
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DashBoadActivity.this);
//                alertDialog.setTitle("Logout...");
//                alertDialog.setMessage("Are you sure you want to Logout this App ?");
//                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        SaveData.logout(getApplicationContext());
//                        Toast.makeText(getApplicationContext(), "Log out SuccessFully", Toast.LENGTH_SHORT).show();
//                        Intent intent1 = new Intent(DashBoadActivity.this, LoginActivity.class);
//                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent1);
//                    }
//                });
//
//                // Setting Negative "NO" Button
//                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent1 = new Intent(DashBoadActivity.this, DashBoadActivity.class);
//                        startActivity(intent1);
//                    }
//                });
//
//                alertDialog.show();
//
//                break;
//
//        }
//        if (intent != null) {
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }


    class GetAnnounceAsynctask extends AsyncTask<String, String, String> {
        String output = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(DashBoadActivity.this);
            dialog.setMessage("Processing");
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                server_url = "http://beis.xyz/aischool/index.php/api/get_school_noticeboard?school_id=" + SaveData.getSchoolid(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            output = HttpHandler.makeServiceCall(server_url);
            System.out.println("getcomment_url" + output);
            return output;
        }

        @Override
        protected void onPostExecute(String output) {
            if (output == null) {
                dialog.dismiss();
            } else {
                try {
                    dialog.dismiss();
                    JSONObject json = new JSONObject(output);
                    String responce = json.getString("responce");
                    JSONArray data_array = json.getJSONArray("data");
                    for (int i = 0; i < data_array.length(); i++) {
                        JSONObject c = data_array.getJSONObject(i);
                        String notice_id = c.getString("notice_id");
                        String school_id = c.getString("school_id");
                        String notice_description = c.getString("notice_description");
                        String notice_type = c.getString("notice_type");
                        String notice_status = c.getString("notice_status");
                        String on_date = c.getString("on_date");

                        HashMap<String, String> contact = new HashMap<>();
                        contact.put("notice_id", notice_id);
                        contact.put("school_id", school_id);
                        contact.put("notice_description", notice_description);
                        contact.put("notice_type", notice_type);
                        contact.put("notice_status", notice_status);
                        contact.put("on_date", on_date);
                        ann_list.add(contact);
                    }

                    announceAdapter = new AnnounceAdapter(DashBoadActivity.this, ann_list);
                    rvAnnounceMentId.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashBoadActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvAnnounceMentId.setLayoutManager(linearLayoutManager);
                    rvAnnounceMentId.setAdapter(announceAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
                super.onPostExecute(output);
            }
        }
    }

    private void getAllEvent() {
        new JSONParser(DashBoadActivity.this).parseVollyStringRequest(ConstValue.EVENT_DETAILS, 1, getParams(), new Helper() {
            public void backResponse(String response) {
                try {
                    M.E(response);
                    JSONObject object = new JSONObject(response);
                    if (object.getString("responce").equals("true")) {
                        JSONArray content = object.getJSONArray("data");
                        for (int i = 0; i < content.length(); i++) {
                            JSONObject jsonObject = content.getJSONObject(i);
                            EventModel eventModel = new EventModel();
                            eventModel.setEvent_id(jsonObject.getString("event_id"));
                            eventModel.setStandard_id(jsonObject.getString("standard_id"));
                            eventModel.setEvent_title(jsonObject.getString("event_title"));
                            eventModel.setEvent_description(jsonObject.getString("event_description"));
                            eventModel.setLocation(jsonObject.getString("location"));
                            eventModel.setEvent_image(jsonObject.getString("event_image"));
                            eventModel.setEvent_start(jsonObject.getString("event_start"));
                            eventModel.setEvent_end(jsonObject.getString("event_end"));
                            eventModel.setEvent_status(jsonObject.getString("event_status"));
                            eventModel.setOn_date(jsonObject.getString("on_date"));
                            eventModel.setSchool_id(jsonObject.getString("school_id"));
                            eventModel.setStandard_title(jsonObject.getString("standard_title"));
                            eventist.add(eventModel);
                            Picasso.with(DashBoadActivity.this)
                                    .load(ConstValue.IMAGE_BASE_URL + eventModel.getEvent_image())
                                    .into(ivEventImageId);
                            tvEventNameId.setText(eventModel.getEvent_title());
                            tvEventStartTimeId.setText(eventModel.getEvent_start());
                            tvLocationId.setText(eventModel.getLocation());

                        }

                    } else {
                        rlOneId.setVisibility(View.GONE);
                        tvNoEventId.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, String> getParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("start_date", strDate);
        params.put("next_date", "");
        return params;
    }
}
