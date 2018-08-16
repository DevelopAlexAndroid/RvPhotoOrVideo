package com.example.usersad.projectrvphotoorvideo.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.usersad.projectrvphotoorvideo.helper.OnItemClick;
import com.example.usersad.projectrvphotoorvideo.R;
import com.example.usersad.projectrvphotoorvideo.model.mFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class RecViewAdapterPhotoVideo extends RecyclerView.Adapter<RecViewAdapterPhotoVideo.ViewHolder> {

    private OnItemClick onItemClick;
    private List<mFile> mFiles;

    public RecViewAdapterPhotoVideo(List<mFile> mFiles){this.mFiles = mFiles;}

    @NonNull
    @Override
    public RecViewAdapterPhotoVideo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photovideo,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecViewAdapterPhotoVideo.ViewHolder holder, final int position) {

        holder.bind(mFiles.get(position));
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView image;
        private TextView name;
        private VideoView videoView;
        private LinearLayout item;

        ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.nameFile);
            videoView = itemView.findViewById(R.id.videoView);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItem(getAdapterPosition());
                }
            });
        }

        void bind(final mFile mFile){
            if(mFile.getmFiles() != null) {
                name.setText(mFile.getmFiles().getPath());
                InputStream imageStream;
                try {
                    if(mFile.getFormatImage()){
                        image.setVisibility(View.VISIBLE);
                        videoView.setVisibility(View.GONE);
                        imageStream = name.getContext().getContentResolver().openInputStream(mFile.getmFiles());
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        image.setImageBitmap(selectedImage);
                    }else {
                        image.setVisibility(View.GONE);
                        videoView.setVisibility(View.VISIBLE);
                        MediaController mediaController = new MediaController(name.getContext());
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setKeepScreenOn(true);
                        videoView.setVideoURI(mFile.getmFiles());
                        videoView.start();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else {
                name.setText("");
                image.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                image.setImageResource(R.drawable.iconsorwardarrow64);
            }
        }

    }

    public void setOnItemClickListener(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }
}
