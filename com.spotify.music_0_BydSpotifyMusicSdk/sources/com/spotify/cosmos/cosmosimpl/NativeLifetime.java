package com.spotify.cosmos.cosmosimpl;

import com.spotify.cosmos.cosmos.Lifetime;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLifetime implements Lifetime {
    private final AtomicBoolean destroyed = new AtomicBoolean();
    private long nThis;

    private NativeLifetime() {
    }

    public final native void destroy();

    @Override // com.spotify.cosmos.cosmos.Lifetime
    public void release() {
        if (this.destroyed.compareAndSet(false, true)) {
            destroy();
        }
    }
}
