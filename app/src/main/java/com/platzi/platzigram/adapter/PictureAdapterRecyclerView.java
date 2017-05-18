package com.platzi.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Picture;
import com.platzi.platzigram.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 75785 on 07/05/2017.
 */

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private ArrayList<Picture> pictures;
    private int resourse;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resourse, Activity activity) {
        this.pictures = pictures;
        this.resourse = resourse;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourse, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUsername());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLike_number());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                } else {
                    activity.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;

        public PictureViewHolder(View itemView) {
            super(itemView);

            pictureCard = (ImageView) itemView.findViewById(R.id.pictureCard2);
            usernameCard = (TextView) itemView.findViewById(R.id.userNameCard);
            timeCard = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);

        }

    }

}
