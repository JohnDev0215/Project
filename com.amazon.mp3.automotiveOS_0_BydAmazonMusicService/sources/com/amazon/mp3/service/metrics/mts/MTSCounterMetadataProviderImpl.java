package com.amazon.mp3.service.metrics.mts;

import UpsellInterface.v1_0.PlaybackMode;
import UpsellInterface.v1_0.UpsellType;
import android.content.Context;
import com.amazon.music.metrics.mts.MTSEventHandler;

/* JADX INFO: loaded from: classes4.dex */
public final class MTSCounterMetadataProviderImpl implements MTSEventHandler.MTSCounterMetadataProvider {
    private final Context mContext;
    private final String PRIME_CUSTOMER = UpsellType.PRIME;
    private final String HAWKFIRE_CUSTOMER = "HAWKFIRE";
    private final String NONE = PlaybackMode.NONE;

    public MTSCounterMetadataProviderImpl(final Context context) {
        this.mContext = context;
    }
}
