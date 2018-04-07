package project.sayan.hms.mServices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.sayan.hms.Model.HealthNewsModel;
import project.sayan.hms.R;
import project.sayan.hms.mInterface.HealthNewsVolleyCallback;
import project.sayan.hms.mInterface.LiverVolleyCallback;

/**
 * Created by Sayan on 3/25/2018.
 */

public class HealthNewsHttpService {

    final String newsApiurl = "https://newsapi.org/v2/top-headlines?sources=medical-news-today&apiKey=3805df345f784180a999685b1b887428";
    HealthNewsModel model;
    final String TAG = "HealthNewsHttpService";
    Context context;
    ProgressDialog progressDialog;


    public HealthNewsHttpService(Context context) {
        this.model = new HealthNewsModel();
        this.context = context;
    }

    public void getExecute(final HealthNewsVolleyCallback callback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newsApiurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "onResponse() returned: " + response);
                callback.onSuccess(parseJsonResponse(response));
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
                if(progressDialog!=null)
                    progressDialog.hide();

                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(error.getMessage());
                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.hide();
                    }
                });
            }
        });
        int socketTimeout = 3 * 1000;//5 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(request);

    }

    private HealthNewsModel parseJsonResponse(JSONObject response) {
        try {

            model.totalResults = response.getInt(model.TOTALRESULTS);
            model.status = response.getString(model.STATUS);
            if (model.status.equalsIgnoreCase("Ok") && model.totalResults > 0) {
                JSONArray arrayArticles = response.getJSONArray(model.ARTICLES);
                model.articles = new ArrayList<>(0);
                for (int i = 0; i < arrayArticles.length(); i++) {
                    JSONObject obj = arrayArticles.getJSONObject(i);
                    HealthNewsModel hnm = new HealthNewsModel();
                    hnm.title = obj.getString(hnm.TITLE);
                    hnm.publishedAt = obj.getString(hnm.PUBLISHEDAT);
                    hnm.author = obj.getString(hnm.AUTHOR);
                    hnm.description = obj.getString(hnm.DESCRIPTION);
                    hnm.url = obj.getString(hnm.URL);
                    hnm.urlToImage = obj.getString(hnm.URLTOIMAGE);

                    model.articles.add(hnm);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return model;

    }
}
