package com.dreamworld.demotest;

import android.content.Context;
import android.support.v4.content.Loader;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by faizan on 21/07/2017.
 */

public class LoaderAdapter  extends BaseAdapter{
    //@Override
    private List<String> data = new ArrayList<>();
    private LayoutInflater layoutInflater ;
    public LoaderAdapter(Context context)
    {
        layoutInflater = LayoutInflater.from(context);

    }

    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null )
        {
            convertView = layoutInflater.inflate(R.layout.list_item_loader , parent,false);


        }
        TextView textView =(TextView)convertView.findViewById(R.id.textView);
         textView.setText(data.get(position));

        return convertView;
    }

public void swapData(Collection<String> data)
{
    this.data.clear();
    this.data.addAll(data);
    notifyDataSetChanged();

}
}
