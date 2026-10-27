package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.helper.DownloadErrorConverter;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.downloads.ErrorReason;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadRetryDialog {

    public interface RetryListener {
        void cancel();

        void retry();
    }

    public static Dialog create(final Context context, final ErrorReason errorReason, final RetryListener callback) {
        return new BauhausDialogBuilder(context).setTitle(R.string.dmusic_download_failed).setMessage(DownloadErrorConverter.toMessage(context, errorReason)).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.DownloadRetryDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.cancel();
            }
        }).setPositiveButton(R.string.dmusic_button_retry, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.DownloadRetryDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.retry();
            }
        }).create();
    }
}
