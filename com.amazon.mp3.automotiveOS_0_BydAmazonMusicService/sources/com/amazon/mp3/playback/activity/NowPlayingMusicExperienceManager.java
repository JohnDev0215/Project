package com.amazon.mp3.playback.activity;

import android.content.Intent;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingMusicExperienceManager {
    private final NowPlayingFragmentActivity activity;
    private PlaybackFragment fragment;

    public NowPlayingMusicExperienceManager(final NowPlayingFragmentActivity activity) {
        this.activity = activity;
    }

    public void checkIfMusicExperienceOpened(final Intent intent) {
        if (intent.getBooleanExtra(PlaybackFragment.class.getSimpleName(), false)) {
            this.activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            Playback.getInstance().getPlaybackController(ControlSource.APP_UI).stop(ChangeReason.NEW_SOURCE);
            String stringExtra = intent.getStringExtra("experienceId");
            this.fragment = ExploreFactory.openMusicExperience(this.activity, intent.getStringExtra("artistAsin"), stringExtra);
            FragmentTransaction fragmentTransactionBeginTransaction = this.activity.getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(R.id.now_playing_activity_root, this.fragment, PlaybackFragment.class.getSimpleName());
            fragmentTransactionBeginTransaction.addToBackStack(null);
            fragmentTransactionBeginTransaction.commit();
            intent.removeExtra(PlaybackFragment.class.getSimpleName());
            intent.removeExtra("artistAsin");
            if (stringExtra != null) {
                intent.removeExtra("experienceId");
            }
        }
    }

    public boolean isMusicExperienceOpen() {
        return this.fragment != null;
    }

    public boolean willPlayAdditionalContent() {
        return this.fragment.willPlayAdditionalContent();
    }

    public void handleBackButtonClicked() {
        this.fragment.handleBackButtonClicked();
    }
}
