package com.amazon.mp3.library.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class NumberOfPlaylistsLimitReachedActivity extends DialogActivity {
    public static Intent getStartIntent() {
        return new Intent(AmazonApplication.getContext(), (Class<?>) NumberOfPlaylistsLimitReachedActivity.class);
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(537397256));
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.activity.NumberOfPlaylistsLimitReachedActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                NumberOfPlaylistsLimitReachedActivity.this.finish();
            }
        });
        bauhausDialogBuilder.setTitle(R.string.dmusic_playlist_max_playlists_size_title);
        bauhausDialogBuilder.setMessage(String.format(AmazonApplication.getContext().getString(R.string.dmusic_playlist_max_playlists_size_desc), Integer.valueOf(PlaylistConfigurationStorage.get().getMaximumNumberOfPlaylistsAllowed())));
        return bauhausDialogBuilder.create();
    }
}
