package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends ArrayAdapter<GalleryModel> {

    Context context;
    List<GalleryModel> galleryModels;


    public GalleryAdapter(@NonNull Context context, int resource, @NonNull List<GalleryModel> objects) {
        super(context, resource, objects);
        this.context= context;
        this.galleryModels = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, parent, false);
        }
        GalleryModel galleryModel = getItem(position);
        ImageView imageView = listitemView.findViewById(R.id.image);
        Glide
                .with(context)
                .load(galleryModel.getThumb())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity) (context);
                FragmentManager fm = activity.getSupportFragmentManager();
                FullSceenDialog alertDialog = FullSceenDialog.newInstance(galleryModel.getRegular());
                alertDialog.show(fm, "fragment_alert");

            }
        });
        return listitemView;
    }
}
