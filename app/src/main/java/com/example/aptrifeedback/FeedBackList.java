package com.example.aptrifeedback;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class FeedBackList extends ArrayAdapter<Feedback> {

    private Activity context;

    private List<Feedback> FeedBackList;

    public FeedBackList(Activity context,List<Feedback> FeedBackList)
    {
        super(context,R.layout.list,FeedBackList);
        this.context=context;
        this.FeedBackList=FeedBackList;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.list,null,true);
        TextView ID=(TextView)listViewItem.findViewById(R.id.fid);
        TextView textViewName=(TextView)listViewItem.findViewById(R.id.fname);
        TextView textViewIns=(TextView) listViewItem.findViewById(R.id.fins);
        TextView title=(TextView)listViewItem.findViewById(R.id.ttitle);


        Feedback feedback=FeedBackList.get(position);

        ID.setText(feedback.getFeedid());
        textViewName.setText(feedback.getFeedname());
        title.setText(feedback.getTitle());
        textViewIns.setText(feedback.getFeedins());


        return listViewItem;
    }
}
