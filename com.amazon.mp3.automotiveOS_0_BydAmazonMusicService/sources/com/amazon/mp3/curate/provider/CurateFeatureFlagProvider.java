package com.amazon.mp3.curate.provider;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.weblab.ExperimentWeblabs;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.subscription.UserSubscription;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: CurateFeatureFlagProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateFeatureFlagProvider;", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "()V", "featureFlags", "", "", "isMyLikesInMadeForYouEnabled", "", "isPlaylistDetailRecommendationsEnabled", "isUserPlaylistSharingEnabled", "DMSFFeatureFlag", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateFeatureFlagProvider implements FeatureFlagProvider {

    /* JADX INFO: compiled from: CurateFeatureFlagProvider.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateFeatureFlagProvider$DMSFFeatureFlag;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ENTITY_PLAYLISTS", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private enum DMSFFeatureFlag {
        ENTITY_PLAYLISTS("entityPlaylistSupported");

        private final String value;

        DMSFFeatureFlag(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    @Override // com.amazon.music.curate.provider.FeatureFlagProvider
    public boolean isUserPlaylistSharingEnabled() {
        return AmazonApplication.getCapabilities().isSharingSupported();
    }

    @Override // com.amazon.music.curate.provider.FeatureFlagProvider
    public boolean isPlaylistDetailRecommendationsEnabled() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        return !userSubscription.isHawkfireHome() && userSubscription.hasSubscription() && ((PlatformUtil.isFireOS() && ExperimentWeblabs.DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET.getTreatment() == WeblabTreatment.T1) || (!PlatformUtil.isFireOS() && ExperimentWeblabs.DM_CURATE_PLAYLIST_RECOMMENDATIONS.getTreatment() == WeblabTreatment.T1));
    }

    @Override // com.amazon.music.curate.provider.FeatureFlagProvider
    public boolean isMyLikesInMadeForYouEnabled() {
        return !PlatformUtil.isFireOS() || ExperimentWeblabs.DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET.getTreatment() == WeblabTreatment.T1;
    }

    @Override // com.amazon.music.curate.provider.FeatureFlagProvider
    public List<String> featureFlags() {
        ArrayList arrayList = new ArrayList();
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            arrayList.add(DMSFFeatureFlag.ENTITY_PLAYLISTS.getValue());
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }
}
