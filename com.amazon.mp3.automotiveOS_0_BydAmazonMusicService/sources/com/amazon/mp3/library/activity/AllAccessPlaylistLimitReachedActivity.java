package com.amazon.mp3.library.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class AllAccessPlaylistLimitReachedActivity extends DialogActivity {
    public static Intent getStartIntent(String errorReason) {
        Intent intent = new Intent(AmazonApplication.getApplication().getApplicationContext(), (Class<?>) AllAccessPlaylistLimitReachedActivity.class);
        intent.putExtra("errorReason", errorReason);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(R.color.background_color));
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        Bundle extras = getIntent().getExtras();
        String string = extras != null ? extras.getString("errorReason") : null;
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.activity.AllAccessPlaylistLimitReachedActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                AllAccessPlaylistLimitReachedActivity.this.finish();
            }
        });
        if (string != null) {
            if (string.equals(AllAccessUserPlaylistLimitTracker.Response.AAUP_PLAYLIST_TRACK_LIMIT.toString())) {
                bauhausDialogBuilder.setTitle(getString(R.string.all_access_user_playlist_max_size_title));
                bauhausDialogBuilder.setMessage(getString(R.string.all_access_user_playlist_max_size_body));
            }
            if (string.equals(AllAccessUserPlaylistLimitTracker.Response.AAUP_RESTRICTION_LIMIT.toString())) {
                bauhausDialogBuilder.setTitle(getString(R.string.all_access_user_playlist_change_limit_title));
                bauhausDialogBuilder.setMessage(getString(R.string.all_access_user_playlist_change_limit_body));
            }
        }
        return bauhausDialogBuilder.create();
    }
}
