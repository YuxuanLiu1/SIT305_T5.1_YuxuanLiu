package com.example.task51;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BlankFragment extends Fragment {

    TextView Names, contents;
    ImageView ImageURL;
    private Context A;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rView = inflater.inflate(R.layout.frag,container,false);

        Names = (TextView) rView.findViewById(R.id.content_title);
        contents = (TextView) rView.findViewById(R.id.content_overview);
        ImageURL = (ImageView) rView.findViewById(R.id.image);

        Bundle bundle = getArguments();

        String ImageNames = bundle.getString("Names");
        String content = bundle.getString("content");
        String ImageURLs = bundle.getString("image");

        Names.setText(ImageNames);
        contents.setText(content);
        Glide.with(getContext())
                .load(ImageURLs)
                .into(ImageURL);


        ImageURL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });

        return rView;
    }}