package com.amazon.mp3.store.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ResourceUtil;

/* JADX INFO: loaded from: classes4.dex */
public class NetworkErrorDialog {

    public interface INetworkErrorDialogHandler {
        void onConnectionErrorDialogCancel();

        void onRetryConnection();
    }

    public static Dialog create(final BaseActivity activity, final INetworkErrorDialogHandler callback) {
        return createUsingStockAndroid(activity, callback);
    }

    private static Dialog createUsingStockAndroid(final BaseActivity activity, final INetworkErrorDialogHandler callback) {
        BauhausDialogBuilder negativeButton = new BauhausDialogBuilder(activity).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.store.dialog.NetworkErrorDialog.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                callback.onConnectionErrorDialogCancel();
            }
        }).setTitle(R.string.dmusic_no_connection_title).setMessage(ResourceUtil.getNoConnectionStringId()).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.store.dialog.NetworkErrorDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onConnectionErrorDialogCancel();
                activity.finish();
            }
        });
        negativeButton.setPositiveButton(R.string.dmusic_button_retry, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.store.dialog.NetworkErrorDialog.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (activity.isPaused()) {
                    return;
                }
                callback.onRetryConnection();
            }
        });
        return negativeButton.create();
    }
}
