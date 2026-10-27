package com.amazon.podcast.media.playback;

import androidx.annotation.Nullable;
import com.amazon.music.platform.providers.AdvertisingPreferencesProvider;
import com.amazon.podcast.Podcast;

/* JADX INFO: loaded from: classes5.dex */
final class OptOut {
    OptOut() {
    }

    @Nullable
    private static String getOptOutHeaderValue(AdvertisingPreferencesProvider advertisingPreferencesProvider) {
        if (advertisingPreferencesProvider.isBehavioralAdsEnabled()) {
            return null;
        }
        return "1";
    }

    public static String getOptOutHeaderValue() {
        return getOptOutHeaderValue(Podcast.getAdvertisingPreferencesProvider());
    }
}
