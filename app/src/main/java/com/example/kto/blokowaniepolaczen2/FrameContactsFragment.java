package com.example.kto.blokowaniepolaczen2;

import android.app.Fragment;
import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FrameContactsFragment extends Fragment {

     ArrayList<String> data;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

       data=new ArrayList<>();
       data.add("1");
        data.add("2");
        data.add("3");
        View view=inflater.inflate(R.layout.lv_layout_general,container,false);
        ListView lv;
        lv = (ListView) view.findViewById(R.id.listview);
        lv.setAdapter(new MyListAdapter(getActivity(),R.layout.lv_layout_fragment,data));
        return view;

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class MyListAdapter extends ArrayAdapter<String>{                               //Moj wlasny adapter
        private int layout;
        private List<String> myObjects;
        private MyListAdapter(Context context, int resource, List<String> object){
            super(context, resource, object);
            myObjects=object;
            layout=resource;
        }

            int log;

            @NonNull
            public View getView(final int possition, View converView, @NonNull ViewGroup parent){
            ViewHolder mainViewHolder=null;
            if(converView==null){
                LayoutInflater inflater=LayoutInflater.from(getContext());
                converView=inflater.inflate(layout,parent, false);
                ViewHolder viewHolder=new ViewHolder();
                viewHolder.textView1=(TextView) converView.findViewById(R.id.textview1);
                viewHolder.textView2=(TextView) converView.findViewById(R.id.textview2);
                viewHolder.switchbutton=(Switch) converView.findViewById(R.id.switch1);
                converView.setTag(viewHolder);
            }
            mainViewHolder=(ViewHolder) converView.getTag();
            mainViewHolder.switchbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){            //listener do switcha
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if(isChecked){
                         log=1;
                     }else{
                         log=0;
                     }
                }
            });




            return converView;
        }
    }


    public class ViewHolder{
        TextView textView1;
        TextView textView2;
       Switch switchbutton;
    }

}
