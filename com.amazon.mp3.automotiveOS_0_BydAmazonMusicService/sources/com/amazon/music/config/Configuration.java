package com.amazon.music.config;

/* JADX INFO: loaded from: classes4.dex */
public interface Configuration {
    void clearConfiguration();

    String getEndpoint();

    int getInteger(String str, int i);

    long getLong(String str, long j);

    String getStage();

    String getString(String str);

    String getString(String str, String str2);

    boolean isRemoteEndpointLoaded();

    void reloadRemoteEndpoint();
}
