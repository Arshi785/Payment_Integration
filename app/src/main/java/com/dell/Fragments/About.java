package com.dell.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dell.paymentintegration.R;

public class About extends Fragment {
    ImageButton bt_toggle_text;
    Button bt_hide_text;
    LinearLayout lyt_expand_text;
    NestedScrollView nested_content;
    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_about, container, false);
                view.findViewById(R.id.website).setOnClickListener(v->openWebsite());

        // Inflate the layout for this fragment
        return view;
    }

    public void openWebsite(){
        String url = "https://www.thesparksfoundationsingapore.org/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}