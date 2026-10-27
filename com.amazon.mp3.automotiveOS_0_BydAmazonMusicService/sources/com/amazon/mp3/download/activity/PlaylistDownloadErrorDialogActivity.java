package com.amazon.mp3.download.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistDownloadErrorDialogActivity extends DialogActivity {
    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_cannot_download_full_playlist_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_cannot_download_full_playlist);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_cannot_download_full_playlist_dismiss, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.download.activity.PlaylistDownloadErrorDialogActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                PlaylistDownloadErrorDialogActivity.this.finish();
            }
        });
        bauhausDialogBuilder.setCancelable(false);
        return bauhausDialogBuilder.create();
    }
}
