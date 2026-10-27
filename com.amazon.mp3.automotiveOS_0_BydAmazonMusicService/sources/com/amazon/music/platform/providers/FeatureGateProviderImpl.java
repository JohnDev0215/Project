package com.amazon.music.platform.providers;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.providers.FeatureGateProvider;
import kotlin.Metadata;

/* JADX INFO: compiled from: FeatureGateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/providers/FeatureGateProviderImpl;", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class FeatureGateProviderImpl implements FeatureGateProvider {
    public FeatureGateProviderImpl() {
        PlatformProviderUtilKt.freeze(this);
    }

    @Override // com.amazon.music.platform.providers.FeatureGateProvider
    public boolean isFeatureAvailable(Feature feature) {
        return FeatureGateProvider.DefaultImpls.isFeatureAvailable(this, feature);
    }
}
