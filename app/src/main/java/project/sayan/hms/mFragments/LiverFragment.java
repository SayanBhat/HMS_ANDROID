package project.sayan.hms.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import project.sayan.hms.Model.LiverAttributesModel;

import project.sayan.hms.R;
import project.sayan.hms.mServices.LiverHttpService;
import project.sayan.hms.mInterface.LiverVolleyCallback;

public class LiverFragment extends Fragment {

    EditText et_age,et_total_bili,et_dir_bili,et_alka,et_alamine,et_aspa,et_protiens,et_albu,et_albu_globu_ratio;
    Spinner spiner_gender;
    LinearLayout lin_layout;
    TextView tv_regression;
    Button btn_calc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.liver_fragment, container,false);

        et_age=rootView.findViewById(R.id.etLivAge);
        et_total_bili=rootView.findViewById(R.id.etLivTotBili);
        et_dir_bili=rootView.findViewById(R.id.etLivDirBili);
        et_alka=rootView.findViewById(R.id.etLivAlkaline_Phosphotase);
        et_alamine=rootView.findViewById(R.id.etLivAlamine_Aminotransferase);
        et_aspa=rootView.findViewById(R.id.etLivAspartate_Aminotransferase);
        et_protiens=rootView.findViewById(R.id.etLivTotal_Protiens);
        et_albu=rootView.findViewById(R.id.etLivAlbumin);
        et_albu_globu_ratio=rootView.findViewById(R.id.etLivAlbumin_and_Globulin_Ratio);

        lin_layout=rootView.findViewById((R.id.linLiver_result_layout));
        tv_regression=rootView.findViewById(R.id.tvLivRegression);
        btn_calc=rootView.findViewById(R.id.btn_calcLiver);

        spiner_gender = rootView.findViewById(R.id.spiner_LivGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
               R.array.gender,R.layout.spinner_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_gender.setAdapter(adapter);


        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiverAttributesModel lam= new LiverAttributesModel();
                lam=setInputvalues();
                LiverHttpService lhs= new LiverHttpService(lam,getContext());
                lhs.Postexecute(new LiverVolleyCallback() {
                    @Override
                    public void onSuccess(LiverAttributesModel result) {
                        lin_layout.setVisibility(View.VISIBLE);
                        tv_regression.setText(String.valueOf(result.getLogisticProbability()));
                    }
                });
            }
        });

        return rootView;
    }
    public LiverAttributesModel setInputvalues()
    {
        LiverAttributesModel lam= new LiverAttributesModel();
        if(TextUtils.isEmpty(et_age.getText().toString().trim())){
            Toast.makeText(getContext(),"Select Age",Toast.LENGTH_SHORT).show();
        }
        else {

            lam.setAge(Double.parseDouble( et_age.getText().toString().trim()));

            String gender=spiner_gender.getSelectedItem().toString();
            if(gender.equalsIgnoreCase("male"))
                lam.setGender(1);
            else lam.setGender(2);

            if(TextUtils.isEmpty(et_alamine.getText().toString().trim()))
                lam.setAlamine_Aminotransferase(0);
            else lam.setAlamine_Aminotransferase(Double.parseDouble(et_alamine.getText().toString().trim()));

            if(TextUtils.isEmpty(et_albu.getText().toString().trim()))
                lam.setAlbumin(0);
            else lam.setAlbumin(Double.parseDouble(et_albu.getText().toString().trim()));

            if(TextUtils.isEmpty((et_albu_globu_ratio.getText())))
                lam.setAlbumin_and_Globulin_Ratio(0);
            else lam.setAlbumin_and_Globulin_Ratio(Double.parseDouble(et_albu_globu_ratio.getText().toString()));

            if(TextUtils.isEmpty(et_alka.getText()))
                lam.setAlkaline_Phosphotase(0);
            else lam.setAlkaline_Phosphotase(Double.parseDouble(et_alka.getText().toString()));

            if(TextUtils.isEmpty(et_aspa.getText()))
                lam.setAspartate_Aminotransferase(0);
            else lam.setAspartate_Aminotransferase(Double.parseDouble(et_aspa.getText().toString()));

            if(TextUtils.isEmpty(et_total_bili.getText()))
                lam.setAspartate_Aminotransferase(0);
            else lam.setAspartate_Aminotransferase(Double.parseDouble(et_total_bili.getText().toString()));

            if(TextUtils.isEmpty(et_dir_bili.getText()))
                lam.setAspartate_Aminotransferase(0);
            else lam.setAspartate_Aminotransferase(Double.parseDouble(et_dir_bili.getText().toString()));

            if(TextUtils.isEmpty(et_protiens.getText()))
                lam.setAspartate_Aminotransferase(0);
            else lam.setAspartate_Aminotransferase(Double.parseDouble(et_protiens.getText().toString()));
        }
        return  lam;
    }
}
