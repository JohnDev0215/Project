package com.amazon.podcast.redhoodPlus;

import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class MetricsEntityIds {
    public static final String podcastShow(final boolean isRedhoodPlusMetricsEnabled, final String podcastShowId, final String podcastShowVariantId) {
        return (isRedhoodPlusMetricsEnabled && !StringUtils.isBlank(podcastShowVariantId)) ? podcastShowVariantId : podcastShowId;
    }

    public static final String podcastEpisode(final boolean isRedhoodPlusMetricsEnabled, final String podcastEpisodeId, final String podcastEpisodeVariantId) {
        return (isRedhoodPlusMetricsEnabled && !StringUtils.isBlank(podcastEpisodeVariantId)) ? podcastEpisodeVariantId : podcastEpisodeId;
    }
}
