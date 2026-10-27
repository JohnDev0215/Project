package G;

import A.I0;
import java.util.Arrays;
import n0.InterfaceC1897z;

/* JADX INFO: loaded from: classes.dex */
public final class B {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a1.a f2492b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2494f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2495g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ androidx.compose.foundation.lazy.layout.a f2496h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0314y[] f2491a = E.f2499a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2493e = 1;

    public B(androidx.compose.foundation.lazy.layout.a aVar) {
        this.f2496h = aVar;
    }

    public static void b(B b10, N n10, X8.c cVar, InterfaceC1897z interfaceC1897z, int i7, int i10) {
        b10.f2496h.getClass();
        long jG = n10.g(0);
        b10.a(n10, cVar, interfaceC1897z, i7, i10, (int) (!n10.d() ? jG & 4294967295L : jG >> 32));
    }

    public final void a(N n10, X8.c cVar, InterfaceC1897z interfaceC1897z, int i7, int i10, int i11) {
        C0314y[] c0314yArr = this.f2491a;
        int length = c0314yArr.length;
        int i12 = 0;
        while (true) {
            if (i12 >= length) {
                this.f2494f = i7;
                this.f2495g = i10;
                break;
            } else {
                C0314y c0314y = c0314yArr[i12];
                if (c0314y != null && c0314y.f2621e) {
                    break;
                } else {
                    i12++;
                }
            }
        }
        int length2 = this.f2491a.length;
        for (int iC = n10.c(); iC < length2; iC++) {
            C0314y c0314y2 = this.f2491a[iC];
            if (c0314y2 != null) {
                c0314y2.c();
            }
        }
        if (this.f2491a.length != n10.c()) {
            Object[] objArrCopyOf = Arrays.copyOf(this.f2491a, n10.c());
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f2491a = (C0314y[]) objArrCopyOf;
        }
        this.f2492b = new a1.a(n10.j());
        this.c = i11;
        this.d = n10.i();
        this.f2493e = n10.b();
        int iC2 = n10.c();
        for (int i13 = 0; i13 < iC2; i13++) {
            Object objH = n10.h(i13);
            C0302l c0302l = objH instanceof C0302l ? (C0302l) objH : null;
            if (c0302l == null) {
                C0314y c0314y3 = this.f2491a[i13];
                if (c0314y3 != null) {
                    c0314y3.c();
                }
                this.f2491a[i13] = null;
            } else {
                C0314y c0314y4 = this.f2491a[i13];
                if (c0314y4 == null) {
                    c0314y4 = new C0314y(cVar, interfaceC1897z, new I0(this.f2496h, 18));
                    this.f2491a[i13] = c0314y4;
                }
                c0314y4.d = c0302l.f2599y;
            }
        }
    }
}
