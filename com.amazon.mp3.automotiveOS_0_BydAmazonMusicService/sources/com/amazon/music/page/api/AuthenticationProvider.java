package com.amazon.music.page.api;

/* JADX INFO: loaded from: classes4.dex */
public interface AuthenticationProvider {
    String getCustomerId();

    String getDeviceId();

    String getDeviceType();

    String getToken();
}
