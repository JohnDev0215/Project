package y;

import E0.AbstractC0164n;
import k0.C1590b;
import k0.C1591c;
import n0.AbstractC1887p;
import n0.InterfaceC1866P;
import q0.C2162a;

/* JADX INFO: renamed from: y.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2715u extends AbstractC0164n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C2712q f24007A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public AbstractC1887p f24008C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public InterfaceC1866P f24009D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C1590b f24010E;

    public C2715u(float f10, AbstractC1887p abstractC1887p, InterfaceC1866P interfaceC1866P) {
        this.B = f10;
        this.f24008C = abstractC1887p;
        this.f24009D = interfaceC1866P;
        C1590b c1590b = new C1590b(new C1591c(), new C2162a(this, 8));
        H0(c1590b);
        this.f24010E = c1590b;
    }
}
