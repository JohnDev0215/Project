package Q3;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class s implements c, R3.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f6218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f6219b = new ArrayList();
    public final int c;
    public final R3.f d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final R3.f f6220e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final R3.f f6221f;

    public s(X3.b bVar, W3.p pVar) {
        pVar.getClass();
        this.f6218a = pVar.f9810e;
        this.c = pVar.f9808a;
        R3.f fVarT0 = pVar.f9809b.t0();
        this.d = fVarT0;
        R3.f fVarT02 = pVar.c.t0();
        this.f6220e = fVarT02;
        R3.f fVarT03 = pVar.d.t0();
        this.f6221f = fVarT03;
        bVar.d(fVarT0);
        bVar.d(fVarT02);
        bVar.d(fVarT03);
        fVarT0.a(this);
        fVarT02.a(this);
        fVarT03.a(this);
    }

    @Override // R3.a
    public final void b() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f6219b;
            if (i7 >= arrayList.size()) {
                return;
            }
            ((R3.a) arrayList.get(i7)).b();
            i7++;
        }
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
    }

    public final void d(R3.a aVar) {
        this.f6219b.add(aVar);
    }
}
