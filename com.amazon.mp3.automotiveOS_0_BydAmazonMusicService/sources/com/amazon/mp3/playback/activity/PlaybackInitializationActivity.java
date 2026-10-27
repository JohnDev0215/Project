package com.amazon.mp3.playback.activity;

import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackInitializationActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\u0007H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/playback/activity/PlaybackInitializationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onStart", "", "startPlayerActivity", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackInitializationActivity extends AppCompatActivity {
    private final String TAG = PlaybackInitializationActivity.class.getSimpleName();

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        startPlayerActivity();
        finish();
    }

    @VisibleForTesting(otherwise = 2)
    public final void startPlayerActivity() {
        MediaCollectionInfo mediaCollectionInfo = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getMediaCollectionInfo();
        MediaCollectionId id = mediaCollectionInfo == null ? null : mediaCollectionInfo.getId(MediaCollectionId.Type.PROGRAM_ID);
        if (mediaCollectionInfo == null || id == null) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this, MediaProvider.NowPlaying.CONTENT_URI);
            if (intentForContentUri == null) {
                Log.error(this.TAG, "Unable to determine Intent to start player");
                return;
            }
            Log.info(this.TAG, "Sending Intent to start player");
            intentForContentUri.setFlags(603979776);
            startActivity(intentForContentUri);
        }
    }
}
