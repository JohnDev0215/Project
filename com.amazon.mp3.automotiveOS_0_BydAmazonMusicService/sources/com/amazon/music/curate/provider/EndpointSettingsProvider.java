package com.amazon.music.curate.provider;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public interface EndpointSettingsProvider {
    String getCurrentOverrideId();

    String getMarketPlaceId(final Context context);
}
