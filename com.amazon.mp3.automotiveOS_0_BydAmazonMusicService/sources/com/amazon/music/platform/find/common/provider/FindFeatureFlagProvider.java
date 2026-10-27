package com.amazon.music.platform.find.common.provider;

import kotlin.Metadata;

/* JADX INFO: compiled from: FindFeatureFlagProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/find/common/provider/FindFeatureFlagProvider;", "", "isAlexaEnabled", "", "isAlexaSearchFindBrowseEnabled", "isFindDJModeEnabled", "isFindFreeTierOnDemandEnabled", "isFindGenreArtworkEnabled", "isFindMerchShopEnabled", "isFindSonicRushOnDemandEnabled", "isFindUpsellEnabled", "isMusicVideosEnabled", "isPodcastEnabled", "isSonicRushEnabled", "isSpatialAudioEnabled", "isUHDEnabled", "isWoodstockEnabled", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface FindFeatureFlagProvider {
    boolean isAlexaEnabled();

    boolean isAlexaSearchFindBrowseEnabled();

    boolean isFindDJModeEnabled();

    boolean isFindFreeTierOnDemandEnabled();

    boolean isFindGenreArtworkEnabled();

    boolean isFindMerchShopEnabled();

    boolean isFindSonicRushOnDemandEnabled();

    boolean isFindUpsellEnabled();

    boolean isMusicVideosEnabled();

    boolean isPodcastEnabled();

    boolean isSonicRushEnabled();

    boolean isSpatialAudioEnabled();

    boolean isUHDEnabled();

    boolean isWoodstockEnabled();
}
