package com.amazon.mp3.featuregate;

import com.amazon.mp3.capability.Capabilities;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class Feature$car_mode_podcast_episode_list$getRule$1 extends FunctionReferenceImpl implements Function0<Boolean> {
    Feature$car_mode_podcast_episode_list$getRule$1(Object obj) {
        super(0, obj, Capabilities.class, "isCarModeV2Enabled", "isCarModeV2Enabled()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(((Capabilities) this.receiver).isCarModeV2Enabled());
    }
}
