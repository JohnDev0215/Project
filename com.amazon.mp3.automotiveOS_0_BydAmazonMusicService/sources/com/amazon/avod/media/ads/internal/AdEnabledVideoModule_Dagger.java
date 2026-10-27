package com.amazon.avod.media.ads.internal;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledVideoModule_Dagger {
    AdPlanFactory provideAdPlanFactory(AdManagerBasedAdPlanFactory adManagerBasedAdPlanFactory) {
        return adManagerBasedAdPlanFactory;
    }

    @Deprecated
    AdEnabledVideoPlayer provideAdEnabledVideoPlayer() {
        return new AdEnabledVideoPlayer();
    }
}
