package q2;

import h2.AbstractC1343A;
import java.util.concurrent.CopyOnWriteArrayList;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f21209a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2492C f21210b;
    public final CopyOnWriteArrayList c;

    public k(CopyOnWriteArrayList copyOnWriteArrayList, int i7, C2492C c2492c) {
        this.c = copyOnWriteArrayList;
        this.f21209a = i7;
        this.f21210b = c2492c;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, q2.l] */
    public final void a() {
        for (j jVar : this.c) {
            AbstractC1343A.K(jVar.f21207a, new i(this, jVar.f21208b, 2));
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, q2.l] */
    public final void b() {
        for (j jVar : this.c) {
            AbstractC1343A.K(jVar.f21207a, new i(this, jVar.f21208b, 1));
        }
    }

    public final void c(int i7) {
        for (j jVar : this.c) {
            AbstractC1343A.K(jVar.f21207a, new h.k(this, jVar.f21208b, i7, 5));
        }
    }

    public final void d(Exception exc) {
        for (j jVar : this.c) {
            AbstractC1343A.K(jVar.f21207a, new T1.k(this, jVar.f21208b, exc, 17));
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, q2.l] */
    public final void e() {
        for (j jVar : this.c) {
            AbstractC1343A.K(jVar.f21207a, new i(this, jVar.f21208b, 0));
        }
    }
}
