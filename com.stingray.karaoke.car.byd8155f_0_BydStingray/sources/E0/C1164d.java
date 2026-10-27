package e0;

import java.util.Arrays;
import java.util.HashMap;
import u.C2453A;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1164d extends C1163c {
    public final C1163c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f13701p;

    public C1164d(int i7, C1171k c1171k, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, C1163c c1163c) {
        super(i7, c1171k, interfaceC2782k, interfaceC2782k2);
        this.o = c1163c;
        c1163c.k();
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void c() {
        if (this.c) {
            return;
        }
        super.c();
        if (this.f13701p) {
            return;
        }
        this.f13701p = true;
        this.o.l();
    }

    @Override // e0.C1163c
    public final AbstractC1179s v() {
        C1163c c1163c = this.o;
        if (c1163c.f13700m || c1163c.c) {
            return new C1168h();
        }
        C2453A c2453a = this.f13696h;
        int i7 = this.f13707b;
        HashMap mapC = c2453a != null ? AbstractC1174n.c(c1163c, this, c1163c.e()) : null;
        Object obj = AbstractC1174n.f13721b;
        synchronized (obj) {
            try {
                AbstractC1174n.d(this);
                if (c2453a == null || c2453a.d == 0) {
                    a();
                } else {
                    AbstractC1179s abstractC1179sY = y(this.o.d(), mapC, this.o.e());
                    if (!abstractC1179sY.equals(C1169i.c)) {
                        return abstractC1179sY;
                    }
                    C2453A c2453aW = this.o.w();
                    if (c2453aW != null) {
                        c2453aW.i(c2453a);
                    } else {
                        this.o.A(c2453a);
                        this.f13696h = null;
                    }
                }
                if (this.o.d() < i7) {
                    this.o.u();
                }
                C1163c c1163c2 = this.o;
                c1163c2.r(c1163c2.e().l(i7).d(this.f13698j));
                this.o.z(i7);
                C1163c c1163c3 = this.o;
                int i10 = this.d;
                this.d = -1;
                if (i10 >= 0) {
                    int[] iArr = c1163c3.f13699k;
                    A7.m.f(iArr, "<this>");
                    int length = iArr.length;
                    int[] iArrCopyOf = Arrays.copyOf(iArr, length + 1);
                    iArrCopyOf[length] = i10;
                    c1163c3.f13699k = iArrCopyOf;
                } else {
                    c1163c3.getClass();
                }
                C1163c c1163c4 = this.o;
                C1171k c1171k = this.f13698j;
                c1163c4.getClass();
                synchronized (obj) {
                    c1163c4.f13698j = c1163c4.f13698j.r(c1171k);
                    C1163c c1163c5 = this.o;
                    int[] iArr2 = this.f13699k;
                    c1163c5.getClass();
                    if (iArr2.length != 0) {
                        int[] iArr3 = c1163c5.f13699k;
                        if (iArr3.length != 0) {
                            int length2 = iArr3.length;
                            int length3 = iArr2.length;
                            int[] iArrCopyOf2 = Arrays.copyOf(iArr3, length2 + length3);
                            System.arraycopy(iArr2, 0, iArrCopyOf2, length2, length3);
                            A7.m.c(iArrCopyOf2);
                            iArr2 = iArrCopyOf2;
                        }
                        c1163c5.f13699k = iArr2;
                    }
                }
                this.f13700m = true;
                if (!this.f13701p) {
                    this.f13701p = true;
                    this.o.l();
                }
                return C1169i.c;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
