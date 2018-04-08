package project.sayan.hms.mFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;


import project.sayan.hms.R;
import project.sayan.hms.mServices.BMICalculation;

import static android.content.ContentValues.TAG;
import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Sayan on 10/18/2017.
 */

public class BMIFragment extends Fragment {

    EditText etweight, etHeight;
    LinearLayout linearLayout1, linearLayout2;
    TextView tvweight, tvHeight, tvResult, tvMessage, tvCategory;
    double weight, height;
    Button save_result;

    SharedPreferences sp;
    boolean loginStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        final View rootView = inflater.inflate(R.layout.bmi_fragment_layout, container, false);

        etweight = rootView.findViewById(R.id.etBMIweight);
        etHeight = rootView.findViewById(R.id.etBMIHeight);
        tvweight = rootView.findViewById(R.id.tvBmiweight);
        tvHeight = rootView.findViewById(R.id.tvBmiHeight);
        tvResult = rootView.findViewById(R.id.tvBMIResult);
        tvCategory = rootView.findViewById(R.id.tvBMICategory);
        tvMessage = rootView.findViewById(R.id.tvBmiMessage);
        linearLayout1 = rootView.findViewById(R.id.hiddenBMILayout1);
        linearLayout2 = rootView.findViewById(R.id.hiddenBMILayout2);
        save_result = rootView.findViewById(R.id.save_buttonBmi);


        sp = getActivity().getSharedPreferences(getString(R.string.LoginCred), Context.MODE_PRIVATE);
        loginStatus = sp.getBoolean("IS_LOGIN", false);
        if (!loginStatus) {
            save_result.setAlpha(0.5f);
        }

        save_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginStatus) {
                    Snackbar mySnackbar = Snackbar.make(rootView.findViewById(R.id.mainLayoutBmi),
                            "Sign In to save result", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                }
            }
        });

        Button calcBmi = rootView.findViewById(R.id.fgf);

        calcBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (NullPointerException e) {
                    Log.e(TAG, "onClick: ", e);
                }


                if (!TextUtils.isEmpty(etweight.getText().toString().trim())
                        && !TextUtils.isEmpty(etHeight.getText().toString().trim())) {

                    weight = Double.parseDouble(etweight.getText().toString().trim());
                    height = Double.parseDouble(etHeight.getText().toString().trim());

                    BMICalculation bmiCalculation = new BMICalculation();
                    bmiCalculation.setweight(weight);
                    bmiCalculation.setHeight(height);
                    bmiCalculation.calcBMI();

                    if (bmiCalculation.getBmiResult() != 0) {

                        tvweight.setText(String.valueOf(weight) + " kg");
                        tvHeight.setText(String.valueOf(height) + " cm");

                        try {
                            if (String.valueOf(bmiCalculation.getBmiResult()).length() > 3) {
                                tvResult.setText(String.valueOf(bmiCalculation.getBmiResult()).substring(0, 4));
                            } else
                                tvResult.setText(String.valueOf(bmiCalculation.getBmiResult()).substring(0, 3));
                        } catch (StringIndexOutOfBoundsException e) {
                            Log.e(TAG, "onClick: ", e);
                        }

                        tvCategory.setText(String.valueOf(bmiCalculation.getTextCategory()));
                        tvMessage.setText(bmiCalculation.getMessage());

                        tvweight.setVisibility(View.VISIBLE);
                        tvHeight.setVisibility(View.VISIBLE);
                        tvResult.setVisibility(View.VISIBLE);
                        tvCategory.setVisibility(View.VISIBLE);
                        tvMessage.setVisibility(View.VISIBLE);

                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.VISIBLE);
                    }

                } else
                    Toast.makeText(getActivity(), "Please fill up all fields", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        sp = getActivity().getSharedPreferences(getString(R.string.LoginCred), Context.MODE_PRIVATE);
        loginStatus = sp.getBoolean("IS_LOGIN", false);
        if (!loginStatus) {
            save_result.setAlpha(0.5f);
        }

    }
}
