package com.amazon.music.platform.providers;

import com.amazon.music.platform.featuregate.Feature;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeatureGateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/platform/providers/FeatureGateProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "isFeatureAvailable", "", "feature", "Lcom/amazon/music/platform/featuregate/Feature;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface FeatureGateProvider extends InterfaceProvider {
    boolean isFeatureAvailable(Feature feature);

    /* JADX INFO: compiled from: FeatureGateProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static boolean isFeatureAvailable(FeatureGateProvider featureGateProvider, Feature feature) {
            Intrinsics.checkNotNullParameter(feature, "feature");
            return feature.getRule().getValue();
        }
    }
}
