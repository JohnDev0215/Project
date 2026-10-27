package com.amazon.mp3.playback.service.metrics;

import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.metrics.mts.event.definition.playback.TrackPlaybackInfo;
import com.amazon.music.platform.data.subscription.MusicUnlimitedSubscriptionTracking;
import com.amazon.music.platform.data.subscription.NoSubscriptionTracking;
import com.amazon.music.platform.data.subscription.PrimeSubscriptionTracking;
import com.amazon.music.platform.providers.SubscriptionTracking;
import com.amazon.music.subscription.BrowseMode;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackEventManager {
    private static final long NANO_SECONDS_PER_HALF_MILLISECOND;
    private static final long NANO_SECONDS_PER_MILLISECOND;

    static {
        long nanos = TimeUnit.MILLISECONDS.toNanos(1L);
        NANO_SECONDS_PER_MILLISECOND = nanos;
        NANO_SECONDS_PER_HALF_MILLISECOND = nanos / 2;
    }

    public static TrackPlaybackInfo createPlaybackInfo(MusicTrack track) {
        SubscriptionTracking musicUnlimitedSubscriptionTracking;
        if (track.isAllOwned()) {
            musicUnlimitedSubscriptionTracking = new NoSubscriptionTracking();
        } else if (new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME)) {
            musicUnlimitedSubscriptionTracking = new PrimeSubscriptionTracking();
        } else {
            musicUnlimitedSubscriptionTracking = new MusicUnlimitedSubscriptionTracking();
        }
        String strContentSubscriptionMode = musicUnlimitedSubscriptionTracking.contentSubscriptionMode();
        String strSubContentSubscriptionMode = musicUnlimitedSubscriptionTracking.subContentSubscriptionMode();
        String luid = track.getLuid();
        if (luid != null && IdGenerator.isPrimeLuid(luid)) {
            luid = "";
        }
        TrackPlaybackInfo trackPlaybackInfo = new TrackPlaybackInfo(track.getAsin(), luid, strContentSubscriptionMode);
        trackPlaybackInfo.setSubContentSubscriptionMode(strSubContentSubscriptionMode);
        return trackPlaybackInfo;
    }
}
