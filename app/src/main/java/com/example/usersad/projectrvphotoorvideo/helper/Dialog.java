package com.example.usersad.projectrvphotoorvideo.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Dialog {

    private String userClick;

    public void getDialog(final Context context,
                          String head, String butPositive, String butNegative, String message, final onDialogButtonClickListener onDialogButtonClickListenerListener){

        AlertDialog.Builder alertD = new AlertDialog.Builder(context);
        alertD.setTitle(head);
        alertD.setMessage(message);
        alertD.setPositiveButton(butPositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userClick = "image/*";
                onDialogButtonClickListenerListener.onDialogButtonClick(userClick);
            }
        });
        alertD.setNegativeButton(butNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userClick = "video/*";
                onDialogButtonClickListenerListener.onDialogButtonClick(userClick);
            }
        });
        alertD.setCancelable(true);
        alertD.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(context,"Вы не выбрали",Toast.LENGTH_SHORT).show();
                userClick = "";
                onDialogButtonClickListenerListener.onDialogButtonClick(userClick);
            }
        });
        alertD.show();
    }
}
