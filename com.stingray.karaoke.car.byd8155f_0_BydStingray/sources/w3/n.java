package W3;

import Q3.q;

/* JADX INFO: loaded from: classes.dex */
public final class n implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f9800b;
    public final V3.a c;
    public final boolean d;

    public n(String str, int i7, V3.a aVar, boolean z6) {
        this.f9799a = str;
        this.f9800b = i7;
        this.c = aVar;
        this.d = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new q(jVar, bVar, this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShapePath{name=");
        sb.append(this.f9799a);
        sb.append(", index=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f9800b, '}');
    }
}
