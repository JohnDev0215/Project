package com.amazon.mp3.playback;

import android.view.ContextMenu;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;

/* JADX INFO: loaded from: classes3.dex */
public interface PlayerNavigationListener {
    void addTrackToLibrary();

    void onCreateContextMenu(ContextMenu menu);

    void showCastingView();

    void showNowPlaying(InteractionType type);

    void showPlayQueue();

    void showPodcastEpisodeList();
}
