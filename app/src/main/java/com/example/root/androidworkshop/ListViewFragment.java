package com.example.root.androidworkshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by root on 9/7/17.
 */

public class ListViewFragment extends ArrayAdapter<ToDo> {

    ListViewFragment(Context context, ToDo[] todo) {
        super(context, R.layout.listview_fragment, todo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.listview_fragment, parent, false);

        ToDo todo = getItem(position);
        TextView textView_Name = (TextView) customView.findViewById(R.id.textView_Name);
        TextView textView_Date = (TextView) customView.findViewById(R.id.textView_Date);

        textView_Name.setText(todo.getName());
        textView_Date.setText(todo.getDate().displayDate());
        return customView;
    }
}
