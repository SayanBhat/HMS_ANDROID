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

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

import project.sayan.hms.Model.LiverAttributesModel;
import project.sayan.hms.Model.UserModel;
import project.sayan.hms.mInterface.CallBackInterFace;

/**
 * Created by Sayan on 4/4/2018.
 */

public class LoginService {

    private static final String TAG = "Login Service";
    private final String hosted_apiUrl = "http://www.healthsystem.somee.com/api/login/";
    private Context mContext;
    private ProgressDialog progressDialog;
    private String email;
    private String password;

    public LoginService(Context context,String email, String password) {
        this.mContext = context;
        this.email=email;
        this.password=password;
        progressDialog = new ProgressDialog(context);
    }

    public void authenticateUser(final CallBackInterFace callBack) {

        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Wait...");

        UserModel model = new UserModel();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(model.EMAIL, email);
            jsonObject.put(model.PASSWORD, password);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, hosted_apiUrl, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callBack.onSuccss(parseJsonData(response));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    BuildAlertDialog(error.getMessage());
                }
            });
            int socketTimeout = 5 * 1000;//5 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);
            RequestQueue rQueue = Volley.newRequestQueue(mContext);
            rQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Authenticate : ", e);

            progressDialog.hide();
            BuildAlertDialog(e.getMessage());
        }

    }

    private UserModel parseJsonData(JSONObject response) {
        UserModel user = new UserModel();
        user.setUserid(response.optInt(user.USERID));
        user.setEmail(response.optString(user.EMAIL));
        user.setName(response.optString(user.NAME));
        user.setResult(response.optString(user.RESULT));
        user.setMessage(response.optString(user.MESSAGE));
        progressDialog.hide();
        return user;
    }

    private void BuildAlertDialog(String Error) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(Error);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }

}
