package com.amazon.podcast.redhoodPlus;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.podcast.Podcast;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class Playable {
    public static final boolean isPlayable(final List<String> contentTraits, final String playbackMode, final String availabilityDate) {
        return !Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled() || (StringUtils.isBlank(playbackMode) || !PlaybackMode.NONE.equals(PlaybackModes.normalizeWithAvailabilityDate(contentTraits, availabilityDate, playbackMode)));
    }

    public static final boolean isPlayable(final String playbackMode) {
        return !Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled() || (StringUtils.isBlank(playbackMode) || !PlaybackMode.NONE.equals(PlaybackModes.mode(playbackMode)));
    }
}
