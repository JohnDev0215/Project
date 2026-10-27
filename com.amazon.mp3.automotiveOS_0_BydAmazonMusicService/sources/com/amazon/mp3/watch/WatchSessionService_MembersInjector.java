package com.amazon.mp3.watch;

import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class WatchSessionService_MembersInjector implements MembersInjector<WatchSessionService> {
    public static void injectSkyFireProvider(WatchSessionService instance, SkyFireProvider skyFireProvider) {
        instance.skyFireProvider = skyFireProvider;
    }

    public static void injectExternalActionProvider(WatchSessionService instance, ExternalActionProvider externalActionProvider) {
        instance.externalActionProvider = externalActionProvider;
    }

    public static void injectPlaybackProvider(WatchSessionService instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }
}
