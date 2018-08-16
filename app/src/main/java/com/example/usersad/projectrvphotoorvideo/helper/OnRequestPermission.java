package com.example.usersad.projectrvphotoorvideo.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class OnRequestPermission {

    public static void checkPermission (Activity activity, String permissionString, int permissionCode) {
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M)) return;
        int existingPermissionStatus = ContextCompat.checkSelfPermission(activity,
                permissionString);
        if (existingPermissionStatus == PackageManager.PERMISSION_GRANTED) return;
        ActivityCompat.requestPermissions(activity, new String[]{permissionString}, permissionCode);
    }
}
