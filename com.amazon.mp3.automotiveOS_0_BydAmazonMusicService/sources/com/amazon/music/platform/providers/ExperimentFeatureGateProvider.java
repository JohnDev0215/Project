package com.amazon.music.platform.providers;

import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import kotlin.Metadata;

/* JADX INFO: compiled from: ExperimentFeatureGateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/providers/ExperimentFeatureGateProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "evaluateFeatureAndGetTreatment", "Lcom/amazon/music/platform/data/WeblabTreatment;", "feature", "Lcom/amazon/music/platform/featuregate/Feature;", "abWeblab", "", "aaWeblab", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ExperimentFeatureGateProvider extends InterfaceProvider {
    WeblabTreatment evaluateFeatureAndGetTreatment(Feature feature, String abWeblab, String aaWeblab);
}
