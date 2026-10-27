package com.amazon.mp3.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ResourceUtil;

/* JADX INFO: loaded from: classes3.dex */
public enum StorageDialog {
    LOW_STORAGE_WARNING,
    LOW_STORAGE_ERROR;

    public static Dialog create(Context context, final DialogInterface.OnClickListener listener, StorageDialog type) {
        int messageID = getMessageID(type);
        if (messageID == -1) {
            return null;
        }
        return createUsingStockAndroid(context, R.string.dmusic_lowspace_error_title, messageID, false, listener);
    }

    private static int getMessageID(StorageDialog type) {
        if (type.equals(LOW_STORAGE_WARNING)) {
            return ResourceUtil.getLowspaceWarningMessageStringId();
        }
        if (type.equals(LOW_STORAGE_ERROR)) {
            return ResourceUtil.getLowspaceErrorMessageStringId();
        }
        return -1;
    }

    private static Dialog createUsingStockAndroid(Context context, int title, int message, boolean cancelable, final DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setNeutralButton(R.string.dmusic_button_ok, listener);
        return builder.create();
    }
}
