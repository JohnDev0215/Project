package com.spotify.core.core;

/* JADX INFO: loaded from: classes.dex */
public interface NativeApplicationScope {
    void destroy();

    byte[] getCacheId();

    byte[] getCacheSalt();

    long getNThis();
}
