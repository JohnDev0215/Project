package com.loostone.libbottom.util;

import OooO00o.OooO00o.OooO0O0.b;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class JniUtil {

    /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
    public static final JniUtil f3799OooO00o;

    static {
        JniUtil jniUtil = new JniUtil();
        f3799OooO00o = jniUtil;
        System.loadLibrary("UsbMic");
        b.f41a.b("JniUtil", h.i("native init ", Integer.valueOf(jniUtil.nativeInit())));
    }

    public final native String decryption(String str, int i);

    public final native String encryption(String str, int i);

    public final native void init();

    public final native int nativeInit();

    public final native int recordCreate(int i, int i2, int i3);

    public final native int recordRead(byte[] bArr, int i, int i2);

    public final native int recordRead2Cache();

    public final native int recordRelease();
}
