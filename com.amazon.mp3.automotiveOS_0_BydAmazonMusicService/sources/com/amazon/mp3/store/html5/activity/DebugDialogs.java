package com.amazon.mp3.store.html5.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.ClipboardManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.DevModeCapabilities;

/* JADX INFO: loaded from: classes4.dex */
final class DebugDialogs {
    private static final DevModeCapabilities sDevModeCapabilities = AmazonApplication.getDevModeCapabilities();
    private static boolean sConnectionErrorDialogVisible = false;

    DebugDialogs() {
    }

    public static void showUrlDialog(final Activity context, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(url);
        builder.setPositiveButton("close", (DialogInterface.OnClickListener) null);
        builder.setNegativeButton("copy", new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.store.html5.activity.DebugDialogs.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                ((ClipboardManager) context.getSystemService("clipboard")).setText(url);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void showDebugModeConnectionErrorDialog(final Activity context) {
        if (sConnectionErrorDialogVisible) {
            return;
        }
        String string = context.getString(R.string.dmusic_connection_dialog_title);
        sConnectionErrorDialogVisible = new AlertDialog.Builder(context).setTitle(string).setMessage(context.getString(R.string.dmusic_connection_dialog_message)).setPositiveButton("ok", new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.store.html5.activity.DebugDialogs.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                boolean unused = DebugDialogs.sConnectionErrorDialogVisible = false;
                dialog.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.store.html5.activity.DebugDialogs.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dlg) {
                boolean unused = DebugDialogs.sConnectionErrorDialogVisible = false;
            }
        }).show().isShowing();
    }
}
