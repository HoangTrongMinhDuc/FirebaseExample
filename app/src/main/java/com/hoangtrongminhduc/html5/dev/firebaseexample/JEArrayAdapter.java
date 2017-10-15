package com.hoangtrongminhduc.html5.dev.firebaseexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HTML5 on 14/10/2017.
 */

public class JEArrayAdapter extends ArrayAdapter<JournalEntry>{
    private Context context;
    private int resource;
    private List<JournalEntry> arrJ;
    public JEArrayAdapter(Context context, int resource, ArrayList<JournalEntry> arrJ){
        super(context, resource, arrJ);
        this.context = context;
        this.resource = resource;
        this.arrJ = arrJ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.journal, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JournalEntry journalEntry = arrJ.get(position);
        viewHolder.tvTitle.setText(journalEntry.getTitle());
        viewHolder.tvDate.setText(journalEntry.getDate());
        viewHolder.tvContent.setText(journalEntry.getContent());
        return convertView;
    }
    public class ViewHolder {
        TextView tvTitle, tvDate, tvContent;
    }
}
