package a.k;

import a.k.c;
import a.k.n;

/* JADX INFO: loaded from: classes.dex */
public class h extends c<n.a, n, b> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a.h.j.e<b> f919f = new a.h.j.e<>(10);
    public static final c.a<n.a, n, b> g = new a();

    public static class a extends c.a<n.a, n, b> {
        @Override // a.k.c.a
        public void a(n.a aVar, n nVar, int i, b bVar) {
            n.a aVar2 = aVar;
            n nVar2 = nVar;
            b bVar2 = bVar;
            if (i == 1) {
                aVar2.e(nVar2, bVar2.f920a, bVar2.f921b);
                return;
            }
            if (i == 2) {
                aVar2.f(nVar2, bVar2.f920a, bVar2.f921b);
                return;
            }
            if (i == 3) {
                aVar2.g(nVar2, bVar2.f920a, bVar2.f922c, bVar2.f921b);
            } else if (i != 4) {
                aVar2.d(nVar2);
            } else {
                aVar2.h(nVar2, bVar2.f920a, bVar2.f921b);
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f920a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f921b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f922c;
    }

    public h() {
        super(g);
    }

    public static b i(int i, int i2, int i3) {
        b bVarA = f919f.a();
        if (bVarA == null) {
            bVarA = new b();
        }
        bVarA.f920a = i;
        bVarA.f922c = i2;
        bVarA.f921b = i3;
        return bVarA;
    }

    @Override // a.k.c
    public /* bridge */ /* synthetic */ void c(n nVar, int i, b bVar) {
        j(nVar, i, null);
    }

    public synchronized void j(n nVar, int i, b bVar) {
        super.c(nVar, i, bVar);
        if (bVar != null) {
            f919f.b(bVar);
        }
    }
}
