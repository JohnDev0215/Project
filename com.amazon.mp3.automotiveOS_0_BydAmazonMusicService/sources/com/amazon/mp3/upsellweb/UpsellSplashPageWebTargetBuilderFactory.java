package com.amazon.mp3.upsellweb;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellSplashPageWebTargetBuilderFactory {
    private final Context context;

    public UpsellSplashPageWebTargetBuilderFactory(final Context context) {
        this.context = context;
    }

    public UpsellSplashPageWebTargetBuilder newBuilder() {
        return new UpsellSplashPageWebTargetBuilder(this.context);
    }
}
