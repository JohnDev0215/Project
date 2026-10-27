package com.android.webview.chromium;

import android.os.Build;
import defpackage.InterfaceC0318Rn;
import defpackage.InterfaceC0555bc;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DrawGLFunctor implements InterfaceC0318Rn {
    public static final boolean c;
    public final InterfaceC0555bc a;
    public long b;

    static {
        c = Build.VERSION.SDK_INT >= 24;
    }

    public DrawGLFunctor(long j, InterfaceC0555bc interfaceC0555bc) {
        this.b = nativeCreateGLFunctor(j);
        this.a = interfaceC0555bc;
    }

    public static native long nativeCreateGLFunctor(long j);

    public static native void nativeDestroyGLFunctor(long j);

    public static native void nativeSetChromiumAwDrawGLFunction(long j);
}
