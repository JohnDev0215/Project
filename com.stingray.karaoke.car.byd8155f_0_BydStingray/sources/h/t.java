package h;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class t implements OnBackAnimationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f15138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f15139b;
    public final /* synthetic */ InterfaceC2772a c;
    public final /* synthetic */ InterfaceC2772a d;

    public t(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2) {
        this.f15138a = interfaceC2782k;
        this.f15139b = interfaceC2782k2;
        this.c = interfaceC2772a;
        this.d = interfaceC2772a2;
    }

    public final void onBackCancelled() {
        this.d.a();
    }

    public final void onBackInvoked() {
        this.c.a();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        this.f15139b.b(new C1330b(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        this.f15138a.b(new C1330b(backEvent));
    }
}
