package org.chromium.content.browser.androidoverlay;

import defpackage.a21;
import defpackage.a5;
import defpackage.c5;
import defpackage.ch1;
import defpackage.f21;
import defpackage.k00;
import defpackage.ki1;
import defpackage.w4;
import defpackage.x4;
import defpackage.xc2;
import defpackage.y11;
import defpackage.z4;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidOverlayProviderImpl implements a5 {
    public int e;
    public final a f = new a();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AndroidOverlayProviderImpl.this.getClass();
            Object obj = ThreadUtils.a;
            r1.e--;
        }
    }

    public static class b implements a21<a5> {
        public static AndroidOverlayProviderImpl e;

        @Override // defpackage.a21
        public final y11 d() {
            if (e == null) {
                e = new AndroidOverlayProviderImpl();
            }
            return e;
        }
    }

    @CalledByNative
    public static boolean areOverlaysSupported() {
        return true;
    }

    @Override // defpackage.a5
    public final void h(f21<w4> f21Var, x4 x4Var, z4 z4Var) {
        Object obj = ThreadUtils.a;
        int i = this.e;
        if (i >= 1) {
            x4Var.E();
            x4Var.close();
            return;
        }
        this.e = i + 1;
        DialogOverlayImpl dialogOverlayImpl = new DialogOverlayImpl(x4Var, z4Var, this.f);
        ch1 ch1VarF0 = f21Var.f0();
        xc2 xc2Var = new xc2(ch1VarF0);
        k00 k00VarW0 = ch1VarF0.w0();
        xc2Var.e.i = dialogOverlayImpl;
        xc2Var.f = new c5.d(k00VarW0, dialogOverlayImpl);
        xc2Var.q();
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
