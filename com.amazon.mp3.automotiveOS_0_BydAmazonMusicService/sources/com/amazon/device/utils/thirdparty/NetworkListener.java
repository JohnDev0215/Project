package com.amazon.device.utils.thirdparty;

/* JADX INFO: loaded from: classes2.dex */
public interface NetworkListener {
    boolean onEthernetAvailable();

    boolean onWanAvailable();

    boolean onWifiAvailable();
}
