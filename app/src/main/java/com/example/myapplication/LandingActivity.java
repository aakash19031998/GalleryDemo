package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LandingActivity extends AppCompatActivity {

    private GridView gridView;
    String url = "https://api.unsplash.com/search/photos?query=rocket&amp;per_page=40&amp;client_id=3LG8LwNcmXYZQCplMqCw93lAlSu52v2QecSbAe-xWj8";
    RequestQueue mRequestQueue;
    ArrayList<GalleryModel> galleryModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(LandingActivity.this);
        init();
    }

    private void init(){

        gridView = (GridView)findViewById(R.id.gridView);
        if (isConnected()) {
            getGalleryList();

        }
    }

    void getGalleryList(){
        ProgressDialog pd = new ProgressDialog(LandingActivity.this);
        pd.setMessage("loading");
        pd.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pd.dismiss();
                Log.v("response",response.toString());

                try {
                    if (response.getInt("total")==792){
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 0;i<jsonArray.length();i++){
                            GalleryModel galleryModel = new GalleryModel();
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            JSONObject urlsObject = jsonObject.getJSONObject("urls");
                            galleryModel.setThumb(urlsObject.getString("thumb"));
                            galleryModel.setRegular(urlsObject.getString("regular"));
                            galleryModels.add(galleryModel);


                        }
                    }
                    GalleryAdapter adapter = new GalleryAdapter(LandingActivity.this, 0,galleryModels);
                    gridView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(LandingActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    private Boolean isConnected(){
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                return true;
            }else {
                Toast.makeText(LandingActivity.this, "Please check your internet connectivity!", Toast.LENGTH_LONG).show();
                return false;

            }
        } else {
            //No internet
            Toast.makeText(LandingActivity.this, "Please check your internet connectivity!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
