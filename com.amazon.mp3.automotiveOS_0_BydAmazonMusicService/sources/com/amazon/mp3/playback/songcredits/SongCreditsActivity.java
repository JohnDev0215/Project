package com.amazon.mp3.playback.songcredits;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.TrackLookup;

/* JADX INFO: loaded from: classes3.dex */
public class SongCreditsActivity extends Activity implements SongCreditsContract$View {
    private static final String TAG = "SongCreditsActivity";
    private SongCreditsContract$Presenter presenter;
    private TextView songwritersTextView;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_credits);
        this.songwritersTextView = (TextView) findViewById(R.id.songwriters);
        if (savedInstanceState != null) {
            setSongWriterText(savedInstanceState.getString("com.amazon.mp3.SONG_WRITER_TEXT"));
            return;
        }
        this.presenter = new SongCreditsPresenter(this, BrowseFactory.createBrowseService(this));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("com.amazon.mp3.EXTRA_SONG_CREDITS_ASIN");
            if (!TextUtils.isEmpty(string)) {
                this.presenter.presentSongWriterData(string);
            } else {
                Log.error(TAG, "No asin passed to SongCreditsActivity");
            }
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        TextView textView = this.songwritersTextView;
        if (textView != null) {
            savedInstanceState.putString("com.amazon.mp3.SONG_WRITER_TEXT", textView.getText().toString());
        }
    }

    @Override // com.amazon.mp3.playback.songcredits.SongCreditsContract$View
    public void setSongWriterText(String s) {
        this.songwritersTextView.setText(s);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SongCreditsContract$Presenter songCreditsContract$Presenter = this.presenter;
        if (songCreditsContract$Presenter != null) {
            songCreditsContract$Presenter.onDestroy();
        }
    }

    @Override // com.amazon.mp3.playback.songcredits.SongCreditsContract$View
    public TrackLookup.Request getTrackLookupRequest(String asin) {
        return new TrackLookup.Request(asin, AccountDetailUtil.getMusicTerritoryOfResidence(), new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(getApplicationContext()), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get(this).getDeviceType(), new BrowseLanguageProvider().getLocale(this), AmazonApplication.getCapabilities().isVideosEnabled(), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this));
    }
}
