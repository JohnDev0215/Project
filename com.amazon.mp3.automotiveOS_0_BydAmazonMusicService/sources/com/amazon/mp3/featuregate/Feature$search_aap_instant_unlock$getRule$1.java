package com.amazon.mp3.featuregate;

import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class Feature$search_aap_instant_unlock$getRule$1 extends FunctionReferenceImpl implements Function0<Boolean> {
    Feature$search_aap_instant_unlock$getRule$1(Object obj) {
        super(0, obj, FMPMFeatureGating.class, Constants.ENABLE_DISABLE, "isEnabled()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(((FMPMFeatureGating) this.receiver).isEnabled());
    }
}
