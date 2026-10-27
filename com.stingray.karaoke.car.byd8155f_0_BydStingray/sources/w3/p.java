package W3;

import Q3.s;

/* JADX INFO: loaded from: classes.dex */
public final class p implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V3.b f9809b;
    public final V3.b c;
    public final V3.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f9810e;

    public p(String str, int i7, V3.b bVar, V3.b bVar2, V3.b bVar3, boolean z6) {
        this.f9808a = i7;
        this.f9809b = bVar;
        this.c = bVar2;
        this.d = bVar3;
        this.f9810e = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new s(bVar, this);
    }

    public final String toString() {
        return "Trim Path: {start: " + this.f9809b + ", end: " + this.c + ", offset: " + this.d + "}";
    }
}
