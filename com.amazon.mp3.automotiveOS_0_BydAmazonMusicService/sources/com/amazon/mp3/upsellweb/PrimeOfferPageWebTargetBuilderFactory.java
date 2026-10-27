package com.amazon.mp3.upsellweb;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeOfferPageWebTargetBuilderFactory {
    private final Context context;

    public PrimeOfferPageWebTargetBuilderFactory(final Context context) {
        this.context = context;
    }

    public PrimeOfferPageWebTargetBuilder newBuilder() {
        return new PrimeOfferPageWebTargetBuilder(this.context);
    }
}
