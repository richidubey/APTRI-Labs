package com.example.aptrifeedback;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AttendanceList extends ArrayAdapter<Attendance> {

    private Activity context;

    private List<Attendance> FeedBackList;

    public AttendanceList(Activity context, List<Attendance> FeedBackList)
    {
        super(context,R.layout.listatt,FeedBackList);
        this.context=context;
        this.FeedBackList=FeedBackList;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.listatt,null,true);
        TextView ID=(TextView)listViewItem.findViewById(R.id.fid);
        TextView textViewName=(TextView)listViewItem.findViewById(R.id.fname);
        TextView textViewIns=(TextView) listViewItem.findViewById(R.id.fins);
        TextView checkin=(TextView)listViewItem.findViewById(R.id.checkin);

        TextView checkout=(TextView)listViewItem.findViewById(R.id.checkout);


        Attendance feedback=FeedBackList.get(position);

        ID.setText(feedback.getId());
        textViewName.setText(feedback.getTitle());
        textViewIns.setText(feedback.getDate());

        checkin.setText(feedback.getInhour()+ " : "+feedback.getInmin() + " : "+ feedback.getInsec());

        checkout.setText(feedback.getOuthour()+ " : "+feedback.getOutmin() + " : "+ feedback.getOutsec());

        return listViewItem;
    }
}
