package com.amazon.music.curate.provider;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: FeatureFlagProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\b\u001a\u00020\u0006H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "", "featureFlags", "", "", "isMyLikesInMadeForYouEnabled", "", "isPlaylistDetailRecommendationsEnabled", "isUserPlaylistSharingEnabled", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface FeatureFlagProvider {
    List<String> featureFlags();

    boolean isMyLikesInMadeForYouEnabled();

    boolean isPlaylistDetailRecommendationsEnabled();

    boolean isUserPlaylistSharingEnabled();
}
