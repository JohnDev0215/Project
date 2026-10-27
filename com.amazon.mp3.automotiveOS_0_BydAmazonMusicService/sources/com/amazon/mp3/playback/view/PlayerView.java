package com.amazon.mp3.playback.view;

import android.graphics.Bitmap;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.PlayerVerticalSwipeListener;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.music.media.playback.RepeatMode;

/* JADX INFO: loaded from: classes3.dex */
public interface PlayerView {
    void addPlayerClickListener(OnPlayerClickListener onPlayerClickListener);

    void addPlayerNavigationListener(PlayerNavigationListener playerNavigationListener);

    void addPlayerVerticalSwipeListener(PlayerVerticalSwipeListener playerVerticalSwipeListener);

    void addedToLibrary(boolean added);

    void animateToNext();

    void animateToPrev();

    void castingStateChanged(boolean connected);

    PlayerContentView getCurrentContentView();

    PlayerContentView getNextContentView();

    PlayerViewConfig getPlayerViewConfig();

    PlayerContentView getPrevContentView();

    void hasNextTrack(boolean hasNext);

    void hasPrevTrack(boolean hasPrev);

    void ratingChanged(int rating);

    void repeatStateChanged(RepeatMode repeatMode);

    void setCurrentBitmap(Bitmap bitmap);

    void setNextBitmap(Bitmap bitmap);

    void setPlayerState(PlayerViewModel.PlayerState playerState);

    void setPrevBitmap(Bitmap bitmap);

    void shuffleStateChanged(boolean shuffled);

    void updateDuration(long duration);

    void updatePlaybackControl(PlayerViewConfig playerViewConfig);

    void updateProgress(long progress);
}
