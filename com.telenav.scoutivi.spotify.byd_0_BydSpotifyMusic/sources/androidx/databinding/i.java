package androidx.databinding;

import androidx.databinding.c;
import androidx.databinding.m;

/* JADX INFO: compiled from: ListChangeRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c<m.a, m, b> {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final h0.g<b> f2072k = new h0.g<>(10);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final c.a<m.a, m, b> f2073l = new a();

    /* JADX INFO: compiled from: ListChangeRegistry.java */
    class a extends c.a<m.a, m, b> {
        a() {
        }

        @Override // androidx.databinding.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(m.a aVar, m mVar, int i7, b bVar) {
            if (i7 == 1) {
                aVar.e(mVar, bVar.f2074a, bVar.f2075b);
                return;
            }
            if (i7 == 2) {
                aVar.f(mVar, bVar.f2074a, bVar.f2075b);
                return;
            }
            if (i7 == 3) {
                aVar.g(mVar, bVar.f2074a, bVar.f2076c, bVar.f2075b);
            } else if (i7 != 4) {
                aVar.c(mVar);
            } else {
                aVar.h(mVar, bVar.f2074a, bVar.f2075b);
            }
        }
    }

    /* JADX INFO: compiled from: ListChangeRegistry.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2074a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2075b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2076c;

        b() {
        }
    }

    public i() {
        super(f2073l);
    }

    private static b l(int i7, int i8, int i9) {
        b bVarB = f2072k.b();
        if (bVarB == null) {
            bVarB = new b();
        }
        bVarB.f2074a = i7;
        bVarB.f2076c = i8;
        bVarB.f2075b = i9;
        return bVarB;
    }

    @Override // androidx.databinding.c
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public synchronized void d(m mVar, int i7, b bVar) {
        super.d(mVar, i7, bVar);
        if (bVar != null) {
            f2072k.a(bVar);
        }
    }

    public void o(m mVar, int i7, int i8) {
        d(mVar, 1, l(i7, 0, i8));
    }

    public void p(m mVar, int i7, int i8) {
        d(mVar, 2, l(i7, 0, i8));
    }

    public void q(m mVar, int i7, int i8) {
        d(mVar, 4, l(i7, 0, i8));
    }
}
