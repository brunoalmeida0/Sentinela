package com.example.cybersecurity.hackaton.sentinela2.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cybersecurity.hackaton.sentinela2.Models.Mensagem;
import com.example.cybersecurity.hackaton.sentinela2.R;

import java.util.List;

public class MensagemAdapter extends ArrayAdapter<Mensagem> {

    public MensagemAdapter(Context context, int resource, List<Mensagem> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_mensagem, parent, false);
        }

        TextView messageTextView = convertView.findViewById(R.id.messageTextView);

        Mensagem message = getItem(position);

        if(message.getDescription().equals("chat"))
            messageTextView.setTextColor(getContext().getColor(R.color.colorPrimary));
        else
            messageTextView.setTextColor(getContext().getColor(R.color.colorDark));

        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(message.getText());

        return convertView;
    }


}
