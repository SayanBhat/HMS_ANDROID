package project.sayan.hms.mFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import project.sayan.hms.Model.DiabeticModel;
import project.sayan.hms.R;
import project.sayan.hms.mServices.DiabetisHttpService;

import static android.content.ContentValues.TAG;
import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Sayan on 10/26/2017.
 */

public class DiabeticFragment extends Fragment {

    private EditText etGlucose,etPressure,etBMI,etInsulin,etAge;
    private TextView tvTree,tvRegression;
    private  LinearLayout resultLayout;
    private  Button btResultPredict,save_result;
    private  ProgressBar progressBar;
    private  boolean loginStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.diabetic_fragment_layout, container,false);

        etGlucose=rootView.findViewById(R.id.etdbtGlucose);
        etPressure=rootView.findViewById(R.id.etdbtBloodPressure);
        etInsulin=rootView.findViewById(R.id.etdbtInsulin);
        etBMI=rootView.findViewById(R.id.etdbtBMI);
        etAge=rootView.findViewById(R.id.etdbtAge);
        tvTree=rootView.findViewById(R.id.tvDBTdecisiontree);
        tvRegression=rootView.findViewById(R.id.tvDBTregression);
        resultLayout=rootView.findViewById(R.id.linDBT_result_layout);
        btResultPredict=rootView.findViewById(R.id.btpredictDiabetis);
        progressBar=rootView.findViewById(R.id.progress_loaderDBT);
        save_result=rootView.findViewById(R.id.save_buttonDiabetes);



        SharedPreferences sp= getActivity().getSharedPreferences(getString(R.string.LoginCred), Context.MODE_PRIVATE);
        loginStatus= sp.getBoolean("IS_LOGIN",false);
        if(!loginStatus){
            save_result.setAlpha(0.5f);
        }

        save_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!loginStatus)
                {
                    Snackbar mySnackbar = Snackbar.make(rootView.findViewById(R.id.mainLayoutDiabetes),
                            "Sign In to save result", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                }
            }
        });

        btResultPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try  {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    Log.e(TAG, "onClick: ",e );
                }

                if(isFieldEmpty())
                                {
                    new DiabetisPost().execute();
                }
                else Toast.makeText(getActivity(), "Please fill up all fields", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

     class DiabetisPost extends AsyncTask<String, Integer, DiabeticModel> {
         DiabetisHttpService dbtCal;
         DiabeticModel model= new DiabeticModel();
         DiabeticModel resultModel;
        @Override
        protected DiabeticModel doInBackground(String... strings) {
            if(model!=null){
                dbtCal=new DiabetisHttpService(model,getContext());
                dbtCal.performOperation();


                do{
                    resultModel= dbtCal.getResult();
                }while (resultModel==null);

//                synchronized (this){
//                    try {
//                        wait(6*1000);
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                resultModel= dbtCal.getResult();
//                                resultLayout.setVisibility(View.VISIBLE);
//                                tvTree.setText(String.valueOf(resultModel.getDecisionResult()));
//                                tvRegression.setText(String.valueOf(resultModel.getLogisticProbability()));
//                            }
//                        });
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            return resultModel;
        }

        @Override
        protected void onPreExecute() {

            progressBar.setVisibility(View.VISIBLE);

            model.glucose= Double.parseDouble(etGlucose.getText().toString());
            model.bloodPressure= Double.parseDouble(etPressure.getText().toString());
            model.bmi= Double.parseDouble(etBMI.getText().toString());
            model.insulin= Double.parseDouble(etInsulin.getText().toString());
            model.age= Double.parseDouble(etAge.getText().toString());
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(DiabeticModel response) {

            if(response!=null) {
                progressBar.setVisibility(View.INVISIBLE);
                resultLayout.setVisibility(View.VISIBLE);
                tvTree.setText(String.valueOf(response.getDecisionResult()));
                tvRegression.setText(String.valueOf(response.getLogisticProbability()));
            }
            super.onPostExecute(response);
        }
    }

    private boolean isFieldEmpty() {
        boolean flag=true;
        if(TextUtils.isEmpty(etGlucose.getText())) flag= false;
        if(TextUtils.isEmpty(etPressure.getText())) flag= false;
        if(TextUtils.isEmpty(etInsulin.getText())) flag= false;
        if(TextUtils.isEmpty(etBMI.getText())) flag= false;
        if(TextUtils.isEmpty(etAge.getText())) flag= false;

        return flag;
    }
}
