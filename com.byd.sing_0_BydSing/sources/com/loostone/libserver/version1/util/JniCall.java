package com.loostone.libserver.version1.util;

/* JADX INFO: loaded from: classes.dex */
public final class JniCall {

    /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
    public static final JniCall f3810OooO00o = new JniCall();

    static {
        System.loadLibrary("PureNet");
    }

    public final native void initNetConfig();
}
