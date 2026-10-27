package com.amazon.mp3.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class UnsupportedFileTypeActivity extends DialogActivity {
    private static final String LOGTAG = "UnsupportedFileTypeActivity";
    private DialogInterface.OnClickListener mOnOkClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.UnsupportedFileTypeActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            UnsupportedFileTypeActivity.this.finish();
        }
    };

    public static Intent getStartIntent(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, UnsupportedFileTypeActivity.class);
        intent.setData(uri);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleNewIntent(getIntent());
    }

    @Override // com.amazon.mp3.activity.DialogActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleNewIntent(intent);
    }

    private void handleNewIntent(Intent intent) {
        Uri data;
        if (intent == null || (data = intent.getData()) == null) {
            return;
        }
        Log.warning(LOGTAG, "Rejecting file due to incompatibility [host: %s; path: %s; scheme: %s]", data.getHost(), data.getPath(), data.getScheme());
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_playback_error_unsupported_type_dialog_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_playback_error_unsupported_type_dialog_desc);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.mOnOkClickListener);
        return bauhausDialogBuilder.create();
    }
}
