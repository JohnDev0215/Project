package com.amazon.mp3.upsellweb;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public class OfferPageWebTargetBuilderFactory {
    private final Context context;

    public OfferPageWebTargetBuilderFactory(final Context context) {
        this.context = context;
    }

    public OfferPageWebTargetBuilder newBuilder() {
        return new OfferPageWebTargetBuilder(this.context);
    }
}
