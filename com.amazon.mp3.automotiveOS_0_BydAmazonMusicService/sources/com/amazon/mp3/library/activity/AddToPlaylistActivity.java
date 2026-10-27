package com.amazon.mp3.library.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment;
import com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment;
import com.amazon.mp3.library.fragment.EditPlaylistFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistActivity extends BaseActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_playlist);
        init();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        initializeAlexa();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        handleBackButtonForAlexa();
    }

    private void init() {
        Fragment addDetailTracksToPlaylistFragment;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new RuntimeException("cannot start Playlist edit with empty intent.");
        }
        Uri uri = (Uri) extras.get("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        Uri uri2 = (Uri) extras.get("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
        String source = MediaProvider.getSource(uri);
        if (uri2 == null && "true".equals(uri.getQueryParameter("edit"))) {
            addDetailTracksToPlaylistFragment = new EditPlaylistFragment();
        } else if (MediaProvider.Playlists.isPlaylist(uri)) {
            addDetailTracksToPlaylistFragment = new AddPlaylistDetailToPlaylistFragment();
        } else {
            addDetailTracksToPlaylistFragment = new AddDetailTracksToPlaylistFragment();
        }
        addDetailTracksToPlaylistFragment.setArguments(appendAddToPlaylistExtras(new Intent(getIntent()), uri, uri2, source).getExtras());
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.FragmentContainer, addDetailTracksToPlaylistFragment);
        fragmentTransactionBeginTransaction.commit();
    }

    private Intent appendAddToPlaylistExtras(Intent i, Uri contentUri, Uri playlistUri, String sourceId) {
        boolean zEquals = "true".equals(contentUri.getQueryParameter("mutated"));
        i.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        i.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
        i.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", false);
        i.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", playlistUri);
        i.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", zEquals);
        return i;
    }
}
