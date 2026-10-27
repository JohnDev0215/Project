package o1;

import p1.C2074b;
import p1.InterfaceC2075c;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends i {

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f20389t0 = 0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f20390u0 = 0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f20391v0 = 0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f20392w0 = 0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public boolean f20393x0 = false;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f20394y0 = 0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f20395z0 = 0;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public final C2074b f20388A0 = new C2074b();
    public InterfaceC2075c B0 = null;

    public abstract void X(int i7, int i10, int i11, int i12);

    public final void Y(int i7, int i10, int i11, int i12, d dVar) {
        InterfaceC2075c interfaceC2075c;
        e eVar;
        while (true) {
            interfaceC2075c = this.B0;
            if (interfaceC2075c != null || (eVar = this.f20276U) == null) {
                break;
            } else {
                this.B0 = eVar.f20336v0;
            }
        }
        C2074b c2074b = this.f20388A0;
        c2074b.f20644a = i7;
        c2074b.f20645b = i11;
        c2074b.c = i10;
        c2074b.d = i12;
        interfaceC2075c.b(dVar, c2074b);
        dVar.S(c2074b.f20646e);
        dVar.N(c2074b.f20647f);
        dVar.f20261F = c2074b.f20649h;
        dVar.J(c2074b.f20648g);
    }
}
