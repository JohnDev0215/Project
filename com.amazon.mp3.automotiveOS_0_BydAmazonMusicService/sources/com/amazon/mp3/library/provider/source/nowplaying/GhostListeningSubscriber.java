package com.amazon.mp3.library.provider.source.nowplaying;

import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.media.playback.ControlSource;
import org.greenrobot.eventbus.Subscribe;

/* JADX INFO: loaded from: classes3.dex */
public class GhostListeningSubscriber {
    @Subscribe
    public void onEvent(final UserInteractionAppEvent event) {
        PlaybackControllerProvider.getController(ControlSource.APP_UI).onUserInteraction();
    }

    @Subscribe
    public void onEvent(final NavigationAppEvent event) {
        PlaybackControllerProvider.getController(ControlSource.APP_UI).onUserInteraction();
    }
}
