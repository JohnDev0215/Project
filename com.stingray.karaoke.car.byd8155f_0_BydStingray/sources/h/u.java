package h;

import android.window.OnBackInvokedCallback;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f15140a = new u();

    public final OnBackInvokedCallback a(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2) {
        A7.m.f(interfaceC2782k, "onBackStarted");
        A7.m.f(interfaceC2782k2, "onBackProgressed");
        A7.m.f(interfaceC2772a, "onBackInvoked");
        A7.m.f(interfaceC2772a2, "onBackCancelled");
        return new t(interfaceC2782k, interfaceC2782k2, interfaceC2772a, interfaceC2772a2);
    }
}
