package org.chromium.components.crash.browser;

import defpackage.C0996jQ;
import defpackage.InterfaceC1078kx;
import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ChildProcessCrashObserver {
    public static InterfaceC1078kx a;

    public static void childCrashed(int i) {
        InterfaceC1078kx interfaceC1078kx = a;
        if (interfaceC1078kx == null) {
            Ts.f("ChildCrashObserver", "Ignoring crash observed before a callback was registered...", new Object[0]);
        } else {
            ((C0996jQ) interfaceC1078kx).a.i();
        }
    }
}
