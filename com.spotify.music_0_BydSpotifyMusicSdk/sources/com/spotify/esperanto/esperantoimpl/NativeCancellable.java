package com.spotify.esperanto.esperantoimpl;

import io.reactivex.rxjava3.functions.f;

/* JADX INFO: loaded from: classes.dex */
public final class NativeCancellable implements f {
    private final long nThis;

    private final native void internalCancel();

    @Override // io.reactivex.rxjava3.functions.f
    public void cancel() {
        internalCancel();
        destroy();
    }

    public final native void destroy();
}
