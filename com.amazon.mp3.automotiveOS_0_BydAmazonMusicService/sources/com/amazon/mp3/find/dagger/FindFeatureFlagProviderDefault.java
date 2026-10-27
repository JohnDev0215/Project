package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.FireOSSonicRushFeature;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindFeatureFlagProviderDefault.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/find/dagger/FindFeatureFlagProviderDefault;", "Lcom/amazon/music/platform/find/common/provider/FindFeatureFlagProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isAlexaEnabled", "", "isAlexaSearchFindBrowseEnabled", "isFindDJModeEnabled", "isFindFreeTierOnDemandEnabled", "isFindGenreArtworkEnabled", "isFindMerchShopEnabled", "isFindSonicRushOnDemandEnabled", "isFindUpsellEnabled", "isMusicVideosEnabled", "isPodcastEnabled", "isSonicRushEnabled", "isSpatialAudioEnabled", "isUHDEnabled", "isWoodstockEnabled", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindFeatureFlagProviderDefault implements FindFeatureFlagProvider {
    private final Context context;

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isFindFreeTierOnDemandEnabled() {
        return true;
    }

    public FindFeatureFlagProviderDefault(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isMusicVideosEnabled() {
        return AmazonApplication.getCapabilities().isMusicVideosEnabled();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isWoodstockEnabled() {
        return AmazonApplication.getCapabilities().isWoodstockEnabled() && !PlatformUtil.isFireOS();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isPodcastEnabled() {
        return PodcastFeatureGating.PODCAST.isEnabled();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isSpatialAudioEnabled() {
        return AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.context) && !PlatformUtil.isFireOS();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isUHDEnabled() {
        return UserSubscriptionUtil.isHawkfireKatana();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isFindDJModeEnabled() {
        return FindFeatureGating.FIND_DJ_MODE_ENABLED.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isFindMerchShopEnabled() {
        return AmazonApplication.getCapabilities().isMerchEnabled() && FindFeatureGating.FIND_MERCH_SHOP_ENABLED.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isFindSonicRushOnDemandEnabled() {
        if (AmazonApplication.getCapabilities().isFireOS(this.context)) {
            return new FireOSSonicRushFeature().isEnabled();
        }
        return true;
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isSonicRushEnabled() {
        return AmazonApplication.getCapabilities().isFireOS(this.context) ? new FireOSSonicRushFeature().isEnabled() : AmazonApplication.getCapabilities().isSonicRushEnabled();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isAlexaEnabled() {
        return AmazonApplication.getCapabilities().isAlexaEnabled();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isAlexaSearchFindBrowseEnabled() {
        return AlexaUtil.isAlexaSearchFindBrowseEnabled();
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isFindUpsellEnabled() {
        return FindFeatureGating.UPSELL_ON_FIND_PAGE.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    @Override // com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider
    public boolean isFindGenreArtworkEnabled() {
        return FindFeatureGating.FIND_GENRE_ARTWORK_ENABLED.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }
}
