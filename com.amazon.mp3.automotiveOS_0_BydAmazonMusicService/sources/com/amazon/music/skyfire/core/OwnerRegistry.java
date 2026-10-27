package com.amazon.music.skyfire.core;

/* JADX INFO: loaded from: classes4.dex */
public interface OwnerRegistry {
    boolean isOwnerAvailable(String str);

    void registerOwner(String str);

    void unregisterOwner(String str);
}
