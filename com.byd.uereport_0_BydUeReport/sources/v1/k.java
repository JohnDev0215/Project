package v1;

import q1.q;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.h f8566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f8567d;

    public k(String str, int i8, u1.h hVar, boolean z7) {
        this.f8564a = str;
        this.f8565b = i8;
        this.f8566c = hVar;
        this.f8567d = z7;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new q(gVar, aVar, this);
    }

    public String b() {
        return this.f8564a;
    }

    public u1.h c() {
        return this.f8566c;
    }

    public boolean d() {
        return this.f8567d;
    }

    public String toString() {
        return "ShapePath{name=" + this.f8564a + ", index=" + this.f8565b + '}';
    }
}
