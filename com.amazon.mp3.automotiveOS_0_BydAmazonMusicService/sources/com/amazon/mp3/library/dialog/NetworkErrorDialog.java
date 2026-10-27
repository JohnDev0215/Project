package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ResourceUtil;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkErrorDialog {
    private static boolean sIsShowing = false;

    public interface INetworkErrorDialogHandler {
        void onConnectionErrorDialogCancel();

        void onDialogDismiss();

        void onRetryConnection();
    }

    public static boolean isShowing() {
        return sIsShowing;
    }

    public static Dialog create(final Context context, final INetworkErrorDialogHandler callback) {
        BauhausDialogBuilder positiveButton = new BauhausDialogBuilder(context).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.library.dialog.NetworkErrorDialog.4
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                callback.onConnectionErrorDialogCancel();
            }
        }).setTitle(R.string.dmusic_no_connection_title).setMessage(ResourceUtil.getNoConnectionStringId()).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.library.dialog.NetworkErrorDialog.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                boolean unused = NetworkErrorDialog.sIsShowing = false;
            }
        }).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.NetworkErrorDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onConnectionErrorDialogCancel();
            }
        }).setPositiveButton(R.string.dmusic_button_retry, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.NetworkErrorDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onRetryConnection();
            }
        });
        sIsShowing = true;
        return positiveButton.create();
    }

    public static Dialog createStoreDialog(final Context context, final INetworkErrorDialogHandler callback) {
        BauhausDialogBuilder negativeButton = new BauhausDialogBuilder(context).setTitle(R.string.dmusic_no_connection_title).setMessage(ResourceUtil.getNoConnectionStringId()).setNegativeButton(R.string.dmusic_store_network_error_dialog_button_text, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.NetworkErrorDialog.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onDialogDismiss();
            }
        });
        negativeButton.setPositiveButton(R.string.dmusic_button_retry, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.NetworkErrorDialog.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onRetryConnection();
            }
        });
        return negativeButton.create();
    }
}
