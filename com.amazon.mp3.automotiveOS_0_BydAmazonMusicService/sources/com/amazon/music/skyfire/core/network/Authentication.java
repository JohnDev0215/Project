package com.amazon.music.skyfire.core.network;

/* JADX INFO: loaded from: classes4.dex */
public interface Authentication {
    String getSessionId();

    String getVideoAuthenticationHeader();

    String playbackAuthentication();
}
