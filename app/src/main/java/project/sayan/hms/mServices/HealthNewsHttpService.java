package project.sayan.hms.mServices;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import project.sayan.hms.Model.HealthNewsModel;
import project.sayan.hms.R;
import project.sayan.hms.mInterface.HealthNewsVolleyCallback;
import project.sayan.hms.mInterface.LiverVolleyCallback;

/**
 * Created by Sayan on 3/25/2018.
 */

public class HealthNewsHttpService {

    final String newsApiurl="https://newsapi.org/v2/top-headlines?sources=medical-news-today&apiKey="+ R.string.news_apiKey;
    HealthNewsModel model;
    final String TAG="HealthNewsHttpService";


    public HealthNewsHttpService(){
        this.model= new HealthNewsModel();
    }

    public void getExecute(HealthNewsVolleyCallback callback)
    {
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, newsApiurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "onResponse() returned: " + response );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
