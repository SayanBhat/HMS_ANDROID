package project.sayan.hms.mServices;

import android.content.Context;
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

import project.sayan.hms.Model.DiabeticModel;

/**
 * Created by Sayan on 10/26/2017.
 */

public class DiabetisHttpService {

    private static final String TAG ="DBT_CAL" ;
    private final String getUserURL="http://10.0.2.2:2012/api/diabetic";
    private final String getUserUrl_hosted="http://sayanbhatt-001-site1.btempurl.com/api/diabetic";

    private Context mContext;
    private DiabeticModel resultModel;
    private DiabeticModel model;

    public DiabetisHttpService(DiabeticModel model, Context context) {
        this.model=model;
        this.mContext=context;
    }

    public void performOperation() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DiabeticModel.GLUCOSE, model.getGlucose());
            jsonObject.put(DiabeticModel.BLOOD_PRESSURE, model.getBloodPressure());
            jsonObject.put(DiabeticModel.INSULIN, model.getInsulin());
            jsonObject.put(DiabeticModel.BMI, model.getBmi());
            jsonObject.put(DiabeticModel.AGE, model.getAge());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getUserURL,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d(TAG, "onResponse: " + response.toString());
                    parseJsonData(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "onErrorResponse: ", error);
                }
            });
            int socketTimeout = 3*1000;//5 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);
            RequestQueue rQueue = Volley.newRequestQueue(mContext);
            rQueue.add(jsonObjectRequest);
        }catch(Exception e){
            Log.e(TAG, "performOperation: "+e );
        }
    }

    private void parseJsonData(JSONObject response) {
        resultModel= new DiabeticModel();
        resultModel.setDecisionResult(response.optDouble(DiabeticModel.DECISION_RESULT));
        resultModel.setLogisticProbability(response.optDouble(DiabeticModel.LOGISTIC_PROBABILITY));
    }

    public DiabeticModel getResult() {
        return resultModel;
    }
}

