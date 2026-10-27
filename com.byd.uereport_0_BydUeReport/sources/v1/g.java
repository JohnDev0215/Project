package v1;

import q1.p;
import u1.l;

/* JADX INFO: compiled from: Repeater.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8547a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u1.b f8548b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.b f8549c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f8550d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f8551e;

    public g(String str, u1.b bVar, u1.b bVar2, l lVar, boolean z7) {
        this.f8547a = str;
        this.f8548b = bVar;
        this.f8549c = bVar2;
        this.f8550d = lVar;
        this.f8551e = z7;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new p(gVar, aVar, this);
    }

    public u1.b b() {
        return this.f8548b;
    }

    public String c() {
        return this.f8547a;
    }

    public u1.b d() {
        return this.f8549c;
    }

    public l e() {
        return this.f8550d;
    }

    public boolean f() {
        return this.f8551e;
    }
}
