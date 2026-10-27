package com.amazon.mp3.library.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class EmptySmartPlaylistActivity extends LibraryBaseActivity {
    private static final int LEARN_MORE_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "EmptySmartPlaylistActivity";
    private long mPlaylistId;
    private String mSourceId;

    public static Intent getStartIntent(Context context, String sourceId, Uri playlistUri) {
        if (!"cirrus".equals(sourceId)) {
            throw new IllegalArgumentException("EmptySmartPlaylistActivity currently only supports Cirrus smart playlists!");
        }
        Intent intent = new Intent(context, (Class<?>) EmptySmartPlaylistActivity.class);
        intent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_SMART_PLAYLIST_ID", PlaylistUtil.getPlaylistId(playlistUri));
        return intent;
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        init();
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        initializeAlexa();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        handleBackButtonForAlexa();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity
    protected Dialog onCreateDialog(int id) {
        if (id == LEARN_MORE_DIALOG_ID) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dmusic_library_empty_smart_uploaded_dialog_title);
            builder.setMessage(getEmptySmartPurchaseDescription());
            builder.setPositiveButton(getString(R.string.dmusic_button_ok), (DialogInterface.OnClickListener) null);
            return builder.create();
        }
        return super.onCreateDialog(id);
    }

    protected Uri getContentUri() {
        return MediaProvider.SmartPlaylists.getContentUri(this.mSourceId, this.mPlaylistId);
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity
    protected String getSourceId() {
        return this.mSourceId;
    }

    private CharSequence getEmptySmartPurchaseDescription() {
        return Html.fromHtml(String.format(getResources().getString(R.string.dmusic_library_empty_smart_uploaded_dialog_desc), SettingsUtil.getCloudPlayerUrl(getApplicationContext())));
    }

    private void init() {
        setContentView(R.layout.library_smart_playlist_empty);
        this.mSourceId = getIntent().getStringExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        this.mPlaylistId = getIntent().getLongExtra("com.amazon.mp3.library.activity.EXTRA_SMART_PLAYLIST_ID", -1L);
        TextView textView = (TextView) findViewById(R.id.PlaylistName);
        TextView textView2 = (TextView) findViewById(R.id.TrackCount);
        TextView textView3 = (TextView) findViewById(R.id.EmptyLatestPurchasesDesc);
        textView.setText(PlaylistUtil.getPlaylistName(this, getContentUri()));
        textView2.setText(String.format(getResources().getQuantityText(R.plurals.dmusic_library_songs_count, 0).toString(), 0));
        textView3.setText(getEmptySmartPurchaseDescription());
        long j = this.mPlaylistId;
        if (j == 0) {
            setupLatestPurchases();
        } else if (j == 1) {
            setupLatestUploads();
        } else {
            if (j == 3) {
                Log.error(TAG, "Recently Playlist Tracks Smart Playlist not implemented in Empty class!");
                return;
            }
            throw new IllegalArgumentException("Uknonwn smart playlist ID specified");
        }
    }

    private void setupLatestPurchases() {
        findViewById(R.id.EmptyLatestUploads).setVisibility(8);
        findViewById(R.id.ShopStoreButton).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.activity.EmptySmartPlaylistActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                EmptySmartPlaylistActivity.this.startActivity(HTMLStoreActivityFactory.getStartIntent(EmptySmartPlaylistActivity.this));
            }
        });
    }

    private void setupLatestUploads() {
        findViewById(R.id.EmptyLatestPurchases).setVisibility(8);
        findViewById(R.id.EmptyLatestUploadsLearnMore).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.activity.EmptySmartPlaylistActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                EmptySmartPlaylistActivity.this.removeDialog(EmptySmartPlaylistActivity.LEARN_MORE_DIALOG_ID);
                EmptySmartPlaylistActivity.this.showDialog(EmptySmartPlaylistActivity.LEARN_MORE_DIALOG_ID);
            }
        });
    }
}
