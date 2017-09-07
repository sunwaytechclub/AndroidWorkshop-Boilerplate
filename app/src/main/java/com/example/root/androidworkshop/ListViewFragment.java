package com.example.root.androidworkshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by root on 9/7/17.
 */

public class ListViewFragment extends ArrayAdapter<ToDo> {

    //Constructor
    ListViewFragment(Context context, ToDo[] todo) {
        //modify listview_fragment to the custom .xml name you created
        super(context, R.layout.listview_fragment, todo);
    }

    //Set the view of the custom list view
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //modify listview_fragment to the custom .xml name you created
        View customView = inflater.inflate(R.layout.listview_fragment, parent, false);

        //getItem(position) shall return the one of the object within the collection that you had
        //pass in when you instantiate this class according to the position
        ToDo todo = getItem(position);
        //Adjust accordingly to your own custom list view fragment alignment
        //refer to listview_fragment.xml to gain more understanding
        TextView textView_Name = (TextView) customView.findViewById(R.id.textView_Name);
        TextView textView_Date = (TextView) customView.findViewById(R.id.textView_Date);

        textView_Name.setText(todo.getName());
        textView_Date.setText(todo.getDate().displayDate());
        return customView;
    }
}
