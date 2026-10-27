package com.amazon.podcast.haptics;

import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.HapticsProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastHapticsUtil {
    public static void triggerHaptics() {
        HapticsProvider hapticsProvider = (HapticsProvider) Providers.INSTANCE.get(HapticsProvider.class);
        if (hapticsProvider != null) {
            hapticsProvider.triggerHaptics(new PodcastHapticsTypeImpl(Contexts.Upsell.playback));
        }
    }
}
