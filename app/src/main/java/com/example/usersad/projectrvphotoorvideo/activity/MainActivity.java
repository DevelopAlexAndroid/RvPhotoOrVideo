package com.example.usersad.projectrvphotoorvideo.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.usersad.projectrvphotoorvideo.helper.Dialog;
import com.example.usersad.projectrvphotoorvideo.helper.DialogClick;
import com.example.usersad.projectrvphotoorvideo.helper.OnItemClick;
import com.example.usersad.projectrvphotoorvideo.R;
import com.example.usersad.projectrvphotoorvideo.adapter.RecViewAdapterPhotoVideo;
import com.example.usersad.projectrvphotoorvideo.helper.OnRequestPermission;
import com.example.usersad.projectrvphotoorvideo.model.mFile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClick,DialogClick {


    private List<mFile> mFileList = new ArrayList<>();
    private RecViewAdapterPhotoVideo adapterPhotoVideo = new RecViewAdapterPhotoVideo(mFileList);
    private int PERMISSION_CODE_STORAGE = 100;
    private Dialog dialog = new Dialog();
    private String head,message,butPos,butNegat,format;
    private int size = 6,possition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerPhotoVideo = findViewById(R.id.recyclerPhotoVideo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerPhotoVideo.setLayoutManager(linearLayoutManager);
        adapterPhotoVideo.setListener(this);
        recyclerPhotoVideo.setAdapter(adapterPhotoVideo);
        OnRequestPermission.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                PERMISSION_CODE_STORAGE);

        addInfo();
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(format);
        startActivityForResult(intent,PERMISSION_CODE_STORAGE);
    }

    @Override
    public void onItem(int possition) {
        dialog.getDialog(this,head,
                butPos,butNegat,message,this);
        this.possition = possition;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 100:
                if(resultCode == RESULT_OK){
                    Uri imageUri = data.getData();
                    mFile mFile = new mFile();
                    mFile.setmFiles(imageUri);
                    if(format.equals("image/*")){
                        mFile.setFormatImage(true);
                    }else {mFile.setFormatImage(false);}

                    mFileList.set(possition,mFile);
                    adapterPhotoVideo.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void clickButton(String info) {
        if (info.equals(""))return;
        format = info;
        selectImage();
    }

    private void addInfo(){
        mFile mFile = new mFile();
        mFile.setmFiles(null);
        for (int i = 0; i<=size;i++){
            mFileList.add(mFile);
        }
        head = "Прикрепить файл";
        message = "Выберите какой файл хотите прикрепить";
        butNegat = "Видео";
        butPos = "Картинка";
    }
}
