package project.sayan.hms.mFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import project.sayan.hms.Model.HealthNewsModel;
import project.sayan.hms.Model.LiverAttributesModel;
import project.sayan.hms.R;
import project.sayan.hms.mInterface.HealthNewsVolleyCallback;
import project.sayan.hms.mInterface.LiverVolleyCallback;
import project.sayan.hms.mServices.HealthNewsHttpService;

/**
 * Created by Sayan on 3/25/2018.
 */

public class HealthNewsFragment extends Fragment {

    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.healthnews_fragment_layout,container,false);

        listView=rootView.findViewById(R.id.listview_healthNews);
        HealthNewsHttpService service=new HealthNewsHttpService();

        service.getExecute(new HealthNewsVolleyCallback() {
            @Override
            public void onSuccess(HealthNewsModel response) {

            }
        });

        return rootView;
    }
}
