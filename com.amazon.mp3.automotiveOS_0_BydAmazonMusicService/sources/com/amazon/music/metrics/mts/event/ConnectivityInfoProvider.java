package com.amazon.music.metrics.mts.event;

/* JADX INFO: loaded from: classes4.dex */
public interface ConnectivityInfoProvider {
    String getCarrierName();

    boolean isEthernetConnected();

    boolean isWifiConnected();

    boolean isWifiOnlyDevice();
}
