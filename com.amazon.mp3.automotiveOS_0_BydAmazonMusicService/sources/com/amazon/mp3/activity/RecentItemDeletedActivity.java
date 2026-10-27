package com.amazon.mp3.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public class RecentItemDeletedActivity extends DialogActivity {
    int mStringId = R.string.dmusic_recent_album_deleted;
    private DialogInterface.OnClickListener mOnOkClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.RecentItemDeletedActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            RecentItemDeletedActivity.this.finish();
        }
    };

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int intExtra = getIntent().getIntExtra("com.amazon.mp3.EXTRA_MESSAGE_STRING_ID", R.string.dmusic_recent_album_deleted);
        this.mStringId = intExtra;
        if (savedInstanceState != null) {
            this.mStringId = savedInstanceState.getInt("com.amazon.mp3.EXTRA_MESSAGE_STRING_ID", intExtra);
        }
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("com.amazon.mp3.EXTRA_MESSAGE_STRING_ID", this.mStringId);
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        AlertDialog.Builder builderNewAlertDialogBuilder = newAlertDialogBuilder();
        builderNewAlertDialogBuilder.setTitle(R.string.dmusic_recent_item_deleted_title);
        builderNewAlertDialogBuilder.setMessage(this.mStringId);
        builderNewAlertDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.mOnOkClickListener);
        return builderNewAlertDialogBuilder.create();
    }
}
