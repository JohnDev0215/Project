package com.amazon.avod.content.event;

/* JADX INFO: loaded from: classes2.dex */
public class AuxCacheKeyEvent {
    private final boolean mHasAuxCacheKey;

    public AuxCacheKeyEvent(boolean z) {
        this.mHasAuxCacheKey = z;
    }

    public boolean hasAuxCacheKey() {
        return this.mHasAuxCacheKey;
    }
}
