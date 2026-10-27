package io.sentry.instrumentation.file;

import d5.I;
import e2.F;
import k3.C1628b0;
import k3.C1679y0;
import k3.I0;
import k3.InterfaceC1655m;
import k3.X;
import k3.l1;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements a, X, l1 {
    public final /* synthetic */ long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16561m;

    public /* synthetic */ c(long j3, Object obj) {
        this.f16561m = obj;
        this.l = j3;
    }

    @Override // k3.l1
    public Object a(I0 i02, C1679y0 c1679y0, int i7) {
        return i02.p(c1679y0, I.B((F) this.f16561m), 0, this.l);
    }

    @Override // io.sentry.instrumentation.file.a
    public Object call() {
        return Long.valueOf(((e) this.f16561m).l.skip(this.l));
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        interfaceC1655m.O0(((C1628b0) this.f16561m).c, i7, this.l);
    }
}
