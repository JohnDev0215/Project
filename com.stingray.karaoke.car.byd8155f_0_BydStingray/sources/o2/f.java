package o2;

import e2.e0;
import e2.f0;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20413b;
    public long c;
    public final C2492C d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f20414e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f20415f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ g f20416g;

    public f(g gVar, String str, int i7, C2492C c2492c) {
        this.f20416g = gVar;
        this.f20412a = str;
        this.f20413b = i7;
        this.c = c2492c == null ? -1L : c2492c.d;
        if (c2492c == null || !c2492c.c()) {
            return;
        }
        this.d = c2492c;
    }

    public final boolean a(C2024a c2024a) {
        C2492C c2492c = c2024a.d;
        if (c2492c == null) {
            return this.f20413b != c2024a.c;
        }
        long j3 = this.c;
        if (j3 == -1) {
            return false;
        }
        if (c2492c.d > j3) {
            return true;
        }
        C2492C c2492c2 = this.d;
        if (c2492c2 == null) {
            return false;
        }
        f0 f0Var = c2024a.f20397b;
        int iB = f0Var.b(c2492c.f22663a);
        int iB2 = f0Var.b(c2492c2.f22663a);
        if (c2492c.d < c2492c2.d || iB < iB2) {
            return false;
        }
        if (iB > iB2) {
            return true;
        }
        boolean zC = c2492c.c();
        int i7 = c2492c2.f22664b;
        if (!zC) {
            int i10 = c2492c.f22665e;
            return i10 == -1 || i10 > i7;
        }
        int i11 = c2492c.f22664b;
        if (i11 > i7) {
            return true;
        }
        if (i11 == i7) {
            if (c2492c.c > c2492c2.c) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(f0 f0Var, f0 f0Var2) {
        int i7 = this.f20413b;
        if (i7 < f0Var.o()) {
            g gVar = this.f20416g;
            f0Var.n(i7, gVar.f20419a);
            e0 e0Var = gVar.f20419a;
            for (int i10 = e0Var.f14095n; i10 <= e0Var.o; i10++) {
                int iB = f0Var2.b(f0Var.l(i10));
                if (iB != -1) {
                    i7 = f0Var2.f(iB, gVar.f20420b, false).c;
                    break;
                }
            }
            i7 = -1;
        } else if (i7 >= f0Var2.o()) {
            i7 = -1;
        }
        this.f20413b = i7;
        if (i7 == -1) {
            return false;
        }
        C2492C c2492c = this.d;
        return c2492c == null || f0Var2.b(c2492c.f22663a) != -1;
    }
}
