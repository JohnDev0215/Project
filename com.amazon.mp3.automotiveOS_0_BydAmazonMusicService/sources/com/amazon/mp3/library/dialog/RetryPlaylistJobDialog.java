package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.ResourceUtil;

/* JADX INFO: loaded from: classes3.dex */
public class RetryPlaylistJobDialog {
    private Context mContext;
    private Dialog mDialog;
    private Job mJob;
    private Listener mListener;
    private final DialogInterface.OnClickListener mOnButtonClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.RetryPlaylistJobDialog.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            if (which != -1) {
                RetryPlaylistJobDialog.this.mListener.onCancel();
            } else {
                RetryPlaylistJobDialog.this.mListener.onRetryJob(RetryPlaylistJobDialog.this.mJob);
            }
        }
    };
    private final DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.library.dialog.RetryPlaylistJobDialog.2
        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            RetryPlaylistJobDialog.this.mListener.onCancel();
        }
    };

    public interface Listener {
        void onCancel();

        void onRetryJob(Job job);
    }

    public RetryPlaylistJobDialog(Context context, Listener listener, Job job) {
        if (context == null || listener == null || job == null) {
            throw new IllegalArgumentException();
        }
        this.mContext = context;
        this.mListener = listener;
        this.mJob = job;
    }

    public Dialog create() {
        if (this.mDialog != null) {
            throw new IllegalStateException("Dialog already created");
        }
        if (this.mJob.getFailureCause() instanceof AbstractHttpClient.NoWifiException) {
            this.mDialog = createNoWifiDialog();
        } else {
            this.mDialog = createRetryDialog();
        }
        DialogUtil.installDefaultKeyListener(this.mDialog);
        return this.mDialog;
    }

    private Dialog createRetryDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.mContext);
        bauhausDialogBuilder.setOnCancelListener(this.mOnCancelListener);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_retry, this.mOnButtonClickListener);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_cancel, this.mOnButtonClickListener);
        bauhausDialogBuilder.setTitle(R.string.dmusic_playlist_retry_cirrus_error_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_playlist_retry_cirrus_error_desc);
        return bauhausDialogBuilder.create();
    }

    private Dialog createNoWifiDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.mContext);
        bauhausDialogBuilder.setOnCancelListener(this.mOnCancelListener);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_retry, this.mOnButtonClickListener);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_cancel, this.mOnButtonClickListener);
        bauhausDialogBuilder.setTitle(R.string.dmusic_playlist_retry_network_error_title);
        bauhausDialogBuilder.setMessage(ResourceUtil.getNoConnectionStringId());
        return bauhausDialogBuilder.create();
    }
}
