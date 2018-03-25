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

import org.json.JSONObject;


import project.sayan.hms.Model.LiverAttributesModel;
import project.sayan.hms.mInterface.LiverVolleyCallback;


/**
 * Created by Sayan on 3/22/2018.
 */

public class LiverHttpService {
    private static final String TAG = "inLiverHttpService";
    private Context mContext;
    private LiverAttributesModel lam;
    private final String getUserURL="http://10.0.2.2:2012/api/liver";
    ProgressDialog progressDialog;

    public LiverHttpService(LiverAttributesModel lam, Context context){
        this.mContext=context;
        this.lam=lam;
        progressDialog=new ProgressDialog(context);
    }

    private LiverAttributesModel parseJsonData(JSONObject response) {
        lam= new LiverAttributesModel();
        lam.setDecisionResult(response.optDouble(LiverAttributesModel.DECISION_RESULT));
        lam.setLogisticProbability(response.optDouble(LiverAttributesModel.LOGISTIC_PROBABILITY));
        progressDialog.hide();
        return lam;
    }

    public LiverAttributesModel getResult(LiverAttributesModel mod)
    {
        return mod;
    }
    public void Postexecute(final LiverVolleyCallback callback){

        final LiverAttributesModel[] result = {new LiverAttributesModel()};
        try{
            progressDialog.show();
            //progressDialog.setCancelable(false);
            progressDialog.setMessage("Wait...");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(lam.AGE,lam.getAge());
            jsonObject.put(lam.GENDER,lam.getGender());
            jsonObject.put(lam.TOTAL_BILIRUBIN,lam.getTotal_Bilirubin());
            jsonObject.put(lam.DIRECT_BILIRUBIN,lam.getDirect_Bilirubin());
            jsonObject.put(lam.ALKALINE_PHOSPHOTASE,lam.getAlkaline_Phosphotase());
            jsonObject.put(lam.ALAMINE_AMINOTRANSFERASE,lam.getAlamine_Aminotransferase());
            jsonObject.put(lam.ASPARTATE_AMINOTRANSFERASE,lam.getAspartate_Aminotransferase());
            jsonObject.put(lam.TOTAL_PROTIENS,lam.getTotal_Protiens());
            jsonObject.put(lam.ALBUMIN,lam.getAlbumin());
            jsonObject.put(lam.ALBUMIN_AND_GLOBULIN_RATIO,lam.getAlbumin_and_Globulin_Ratio());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getUserURL,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d(TAG, "onResponse: " + response.toString());
                    result[0] =parseJsonData(response);
                    callback.onSuccess(result[0]);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "onErrorResponse: ", error);
                    if(progressDialog!=null)
                        progressDialog.hide();

                    final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
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
            int socketTimeout = 5*1000;//5 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);
            RequestQueue rQueue = Volley.newRequestQueue(mContext);
            rQueue.add(jsonObjectRequest);

        }catch(Exception e)
        {
            Log.e(TAG, "PostRequest: ",e );

            progressDialog.hide();

            final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage(e.getMessage());
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.hide();
                }
            });

            alertDialog.show();
        }
    }
}
