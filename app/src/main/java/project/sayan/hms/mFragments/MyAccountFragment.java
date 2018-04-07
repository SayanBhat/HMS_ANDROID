package project.sayan.hms.mFragments;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.sayan.hms.Activities.LoginActivity;
import project.sayan.hms.R;

/**
 * Created by Sayan on 4/4/2018.
 */

public class MyAccountFragment extends Fragment {
    SharedPreferences sharedPref;
    private final String LOGINCRED="LoginCredentials";
    Context context;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.myaccount_fragment, container,false);
        context=getActivity();

        sharedPref=context.getSharedPreferences(LOGINCRED, Context.MODE_PRIVATE);
        boolean result= sharedPref.getBoolean("IS_LOGIN",false);
        if(!result)
        {
            startActivity(new Intent(context, LoginActivity.class));

        }
        return rootView;
    }


}
