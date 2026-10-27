package org.chromium.base;

import J.N;
import defpackage.InterfaceC1789xt;
import defpackage.Ys;
import org.chromium.base.MemoryPressureListener;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MemoryPressureListener {
    public static final Ys a = new Ys();

    public static final void a(int i) {
        N.MZJzyjAa(i);
    }

    public static void addNativeCallback() {
        a.f(new InterfaceC1789xt() { // from class: Us
            @Override // defpackage.InterfaceC1789xt
            public void a(int i) {
                MemoryPressureListener.a(i);
            }
        });
    }
}
