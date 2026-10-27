package com.amazon.music.skyfire.core.network;

import CoreInterface.v1_0.ClientInformation;

/* JADX INFO: loaded from: classes4.dex */
public interface ClientInformationCache {
    ClientInformation get(String str);

    String queryKey(ClientInformation clientInformation);
}
