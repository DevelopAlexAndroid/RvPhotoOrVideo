package com.example.usersad.projectrvphotoorvideo.model;

import android.net.Uri;

public class mFile {

    private Uri mFiles;
    private Boolean formatImage;

    public Uri getmFiles() {
        return mFiles;
    }

    public void setmFiles(Uri s) {
        this.mFiles = s;
    }

    public Boolean getFormatImage() {
        return formatImage;
    }

    public void setFormatImage(Boolean formatImage) {
        this.formatImage = formatImage;
    }
}
