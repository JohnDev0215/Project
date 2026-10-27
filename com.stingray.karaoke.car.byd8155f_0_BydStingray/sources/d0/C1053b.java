package d0;

import K7.K;
import T.InterfaceC0769u0;
import T.S;
import e0.InterfaceC1176p;
import l3.P;

/* JADX INFO: renamed from: d0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1053b implements InterfaceC0769u0 {
    public n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public k f13255m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f13256n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object[] f13257p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public j f13258q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final K f13259r = new K(this, 28);

    public C1053b(n nVar, k kVar, String str, Object obj, Object[] objArr) {
        this.l = nVar;
        this.f13255m = kVar;
        this.f13256n = str;
        this.o = obj;
        this.f13257p = objArr;
    }

    @Override // T.InterfaceC0769u0
    public final void a() {
        b();
    }

    public final void b() {
        String strM;
        k kVar = this.f13255m;
        if (this.f13258q != null) {
            throw new IllegalArgumentException(("entry(" + this.f13258q + ") is not null").toString());
        }
        if (kVar != null) {
            K k10 = this.f13259r;
            Object objA = k10.a();
            if (objA == null || kVar.b(objA)) {
                this.f13258q = kVar.a(this.f13256n, k10);
                return;
            }
            if (objA instanceof InterfaceC1176p) {
                InterfaceC1176p interfaceC1176p = (InterfaceC1176p) objA;
                if (interfaceC1176p.f() == S.f8222n || interfaceC1176p.f() == S.f8224q || interfaceC1176p.f() == S.o) {
                    strM = "MutableState containing " + interfaceC1176p.getValue() + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it as a stateSaver parameter to rememberSaveable().";
                } else {
                    strM = "If you use a custom SnapshotMutationPolicy for your MutableState you have to write a custom Saver";
                }
            } else {
                strM = w0.c.M(objA);
            }
            throw new IllegalArgumentException(strM);
        }
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
        j jVar = this.f13258q;
        if (jVar != null) {
            ((P) jVar).H();
        }
    }

    @Override // T.InterfaceC0769u0
    public final void d() {
        j jVar = this.f13258q;
        if (jVar != null) {
            ((P) jVar).H();
        }
    }
}
