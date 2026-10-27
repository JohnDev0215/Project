package com.spotify.connectivity.pubsub;

/* JADX INFO: loaded from: classes.dex */
public interface PubSubStats {
    void onSessionLogout();

    void registerFailedConversion(String str);

    void registerMessage(String str);
}
