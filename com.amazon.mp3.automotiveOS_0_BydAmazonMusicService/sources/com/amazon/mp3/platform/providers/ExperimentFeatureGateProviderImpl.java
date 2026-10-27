package com.amazon.mp3.platform.providers;

import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.providers.ExperimentFeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExperimentFeatureGateProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/platform/providers/ExperimentFeatureGateProviderImpl;", "Lcom/amazon/music/platform/providers/ExperimentFeatureGateProvider;", "weblabProvider", "Lcom/amazon/music/platform/providers/WeblabProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "(Lcom/amazon/music/platform/providers/WeblabProvider;Lcom/amazon/music/platform/providers/FeatureGateProvider;)V", "evaluateFeatureAndGetTreatment", "Lcom/amazon/music/platform/data/WeblabTreatment;", "feature", "Lcom/amazon/music/platform/featuregate/Feature;", "abWeblab", "", "aaWeblab", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExperimentFeatureGateProviderImpl implements ExperimentFeatureGateProvider {
    private final FeatureGateProvider featureGateProvider;
    private final WeblabProvider weblabProvider;

    public ExperimentFeatureGateProviderImpl(WeblabProvider weblabProvider, FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(weblabProvider, "weblabProvider");
        Intrinsics.checkNotNullParameter(featureGateProvider, "featureGateProvider");
        this.weblabProvider = weblabProvider;
        this.featureGateProvider = featureGateProvider;
    }

    @Override // com.amazon.music.platform.providers.ExperimentFeatureGateProvider
    public WeblabTreatment evaluateFeatureAndGetTreatment(Feature feature, String abWeblab, String aaWeblab) {
        Intrinsics.checkNotNullParameter(feature, "feature");
        Intrinsics.checkNotNullParameter(abWeblab, "abWeblab");
        if (this.featureGateProvider.isFeatureAvailable(feature)) {
            if (aaWeblab != null) {
                this.weblabProvider.getTreatment(aaWeblab, true);
            }
            return this.weblabProvider.getTreatment(abWeblab, true);
        }
        return WeblabTreatment.C;
    }
}
