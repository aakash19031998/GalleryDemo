package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

public class FullSceenDialog extends DialogFragment {

    ImageView fullImage;

    public FullSceenDialog() {
    }

    public static FullSceenDialog newInstance(String title) {
        FullSceenDialog frag = new FullSceenDialog();
        Bundle args = new Bundle();
        args.putString("url", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_full_image, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        fullImage = (ImageView) view.findViewById(R.id.fullImage);
        // Fetch arguments from bundle and set title
        String url = getArguments().getString("url", "Enter Name");
        Glide
                .with(getActivity())
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(fullImage);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}
