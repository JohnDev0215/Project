package com.amazon.mp3.upsellweb;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public class LandingPageWebTargetBuilderFactory {
    private final Context context;

    public LandingPageWebTargetBuilderFactory(final Context context) {
        this.context = context;
    }

    public LandingPageWebTargetBuilder newBuilder() {
        return new LandingPageWebTargetBuilder(this.context);
    }
}
