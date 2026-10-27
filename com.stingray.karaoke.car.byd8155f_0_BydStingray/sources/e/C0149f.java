package E;

/* JADX INFO: renamed from: E.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0149f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f1351a;

    public /* synthetic */ C0149f(D d) {
        this.f1351a = d;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, java.util.List] */
    public float a(int i7) {
        Object obj;
        t tVarH = this.f1351a.h();
        if (tVarH.f1415j.isEmpty()) {
            return 0.0f;
        }
        ?? r22 = tVarH.f1415j;
        int size = r22.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                obj = null;
                break;
            }
            obj = r22.get(i10);
            if (((u) obj).f1422a == i7) {
                break;
            }
            i10++;
        }
        if (((u) obj) != null) {
            return r6.f1433p;
        }
        int size2 = r22.size();
        int i11 = 0;
        for (int i12 = 0; i12 < size2; i12++) {
            i11 += ((u) r22.get(i12)).f1434q;
        }
        return ((i7 - b()) * ((i11 / r22.size()) + tVarH.f1420q)) - r0.d.c.i();
    }

    public int b() {
        return this.f1351a.d.f1443b.i();
    }
}
