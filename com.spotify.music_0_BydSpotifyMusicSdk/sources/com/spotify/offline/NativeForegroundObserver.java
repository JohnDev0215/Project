package com.spotify.offline;

/* JADX INFO: loaded from: classes.dex */
public final class NativeForegroundObserver {
    private final long nThis;

    public final native void destroy();

    public final native void onForeground(boolean z);
}
