package A;

import G.C0303m;
import S8.InterfaceC0710g;
import java.util.concurrent.CancellationException;
import u.C2472l;
import y0.C2729g;

/* JADX INFO: renamed from: A.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0006c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V.d f158a;

    public C0006c(int i7) {
        switch (i7) {
            case 1:
                this.f158a = new V.d(new C0303m[16]);
                break;
            case 2:
                this.f158a = new V.d(new C2729g[16]);
                break;
            default:
                this.f158a = new V.d(new C0020j[16]);
                break;
        }
    }

    public boolean a(C2472l c2472l, C0.r rVar, t4.e eVar, boolean z6) {
        V.d dVar = this.f158a;
        int i7 = dVar.f9279n;
        if (i7 <= 0) {
            return false;
        }
        Object[] objArr = dVar.l;
        int i10 = 0;
        boolean z10 = false;
        do {
            z10 = ((C2729g) objArr[i10]).a(c2472l, rVar, eVar, z6) || z10;
            i10++;
        } while (i10 < i7);
        return z10;
    }

    public void b(CancellationException cancellationException) {
        V.d dVar = this.f158a;
        int i7 = dVar.f9279n;
        InterfaceC0710g[] interfaceC0710gArr = new InterfaceC0710g[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            interfaceC0710gArr[i10] = ((C0020j) dVar.l[i10]).f188b;
        }
        for (int i11 = 0; i11 < i7; i11++) {
            interfaceC0710gArr[i11].l(cancellationException);
        }
        if (!dVar.l()) {
            throw new IllegalStateException("uncancelled requests present");
        }
    }

    public void c(t4.e eVar) {
        V.d dVar = this.f158a;
        int i7 = dVar.f9279n;
        while (true) {
            i7--;
            if (-1 >= i7) {
                return;
            }
            if (((C2729g) dVar.l[i7]).c.f15229a == 0) {
                dVar.o(i7);
            }
        }
    }

    public void d() {
        int i7 = 0;
        while (true) {
            V.d dVar = this.f158a;
            if (i7 >= dVar.f9279n) {
                return;
            }
            C2729g c2729g = (C2729g) dVar.l[i7];
            if (c2729g.f24059b.f14695x) {
                i7++;
                c2729g.d();
            } else {
                c2729g.f();
                dVar.o(i7);
            }
        }
    }

    public void e() {
        V.d dVar = this.f158a;
        int i7 = 0;
        int i10 = new G7.d(0, dVar.f9279n - 1, 1).f2712m;
        if (i10 >= 0) {
            while (true) {
                ((C0020j) dVar.l[i7]).f188b.resumeWith(m7.q.f19267a);
                if (i7 == i10) {
                    break;
                } else {
                    i7++;
                }
            }
        }
        dVar.g();
    }
}
