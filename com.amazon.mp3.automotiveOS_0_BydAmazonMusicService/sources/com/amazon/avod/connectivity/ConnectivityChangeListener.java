package com.amazon.avod.connectivity;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ConnectivityChangeListener {
    void onConnectionChange(@Nonnull DetailedNetworkInfo detailedNetworkInfo, @Nonnull DetailedNetworkInfo detailedNetworkInfo2);
}
