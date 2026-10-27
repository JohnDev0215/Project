package com.amazon.mp3.upsellweb;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public class OneClickWebTargetBuilderFactory {
    private final Context context;

    public OneClickWebTargetBuilderFactory(final Context context) {
        this.context = context;
    }

    public OneClickWebTargetBuilder newBuilder() {
        return new OneClickWebTargetBuilder(this.context);
    }
}
