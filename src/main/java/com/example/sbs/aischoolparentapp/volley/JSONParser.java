package com.example.sbs.aischoolparentapp.volley;

import android.content.Context;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sbs.aischoolparentapp.other.AppController;
import com.example.sbs.aischoolparentapp.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null, response;
    static String json = "";
    private Context cx;

    // constructor
    public JSONParser(Context cx) {
        this.cx = cx;
    }

    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod


    public static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;

    }

    public void parseVollyJSONObject(String url, int method, final JSONObject param, final Helper h) {
        //method GET=0,POST=1
       /* String perameters;
        if (param == null) {
            perameters = null;
        } else {
            perameters = param.toString();
        }*/
        if (method == 0 || method == 1) {
            final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (method, url, param, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.e("Response: ", response.toString());
                            if (response != null) {
                                h.backResponse(response.toString());

                            } else {
                                M.E("Something went wrong.!");
                            }
                        }

                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String err = (error.getMessage() == null) ? "Parse Fail" : error.getMessage();
                            h.backResponse("error");
                            Log.e("sdcard-err2:", err);
                            M.E("Something went wrong.!");
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };

            jsObjRequest.setShouldCache(true);
            // Adding request to request queue
            AppController.getInstance().
                    addToRequestQueue(jsObjRequest);
        } else {
            M.E("Invalid Request Method");
        }
    }

    public void parseVollyStringRequest(String url, int method, final Map<String, String> params, final Helper h) {
        //method GET=0,POST=1
        if (NetworkUtil.getConnectivityStatus(cx)) {
            if (method == 0 || method == 1) {
                final MaterialDialog materialDialog = M.initProgressDialog(cx);
                final StringRequest jsObjRequest = new StringRequest
                        (method, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //M.E("Response: " + response.toString());
                                if (response != null) {
                                    materialDialog.dismiss();
                                    h.backResponse(response.toString());
                                } else {
                                    M.E("Invalid Request Method");
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String err = (error.getMessage() == null) ? "Parse Fail" : error.getMessage();
                                materialDialog.dismiss();
                                h.backResponse("error");
                                M.E("sdcard-err2:" + err);
                                M.E("Something went wrong.!");
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        //returning parameters
                        return params;
                    }
                };
                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(jsObjRequest);


            } else {
                M.E("Invalid Request Method");
            }
        } else {
            M.T(cx, cx.getString(R.string.no_internet));
        }
    }

    public void parseVollyStringRequestWithautProgressBar(String url, int method, final Map<String, String> params, final Helper h) {
        //method GET=0,POST=1
        if (NetworkUtil.getConnectivityStatus(cx)) {
            if (method == 0 || method == 1) {
//                final MaterialDialog materialDialog=M.initProgressDialog(cx);
                final StringRequest jsObjRequest = new StringRequest
                        (method, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //M.E("Response: " + response.toString());
                                if (response != null) {
//                                    materialDialog.dismiss();
                                    h.backResponse(response.toString());
                                } else {
                                    M.E("Invalid Request Method");
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String err = (error.getMessage() == null) ? "Parse Fail" : error.getMessage();
//                                materialDialog.dismiss();
                                h.backResponse("error");
                                M.E("sdcard-err2:" + err);
                                M.E("Something went wrong.!");
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        //returning parameters
                        return params;
                    }
                };
                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(jsObjRequest);


            } else {
                M.E("Invalid Request Method");
            }
        } else {
            M.T(cx, cx.getString(R.string.no_internet));
        }
    }

    public String makeHttpRequest(String url, String method,
                                  List<NameValuePair> params) throws IOException {

        // Making HTTP request
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);

                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                HttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            //Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
	    /*try {
	        jObj = new JSONObject(json);
	    } catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
	    }*/

        // return JSON String
        return json;

    }
}