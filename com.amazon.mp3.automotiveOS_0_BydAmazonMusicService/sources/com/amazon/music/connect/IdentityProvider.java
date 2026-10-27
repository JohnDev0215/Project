package com.amazon.music.connect;

/* JADX INFO: loaded from: classes4.dex */
public interface IdentityProvider {
    String getCustomerId();

    String getDeviceId();

    String getDeviceType();

    String getToken();
}
