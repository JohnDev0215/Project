package d8;

import o8.C2061f;

/* JADX INFO: renamed from: d8.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1130q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2061f f13621a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final W7.n f13622b;

    public C1130q(C2061f c2061f, W7.n nVar) {
        A7.m.f(c2061f, "name");
        this.f13621a = c2061f;
        this.f13622b = nVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1130q) {
            if (A7.m.a(this.f13621a, ((C1130q) obj).f13621a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f13621a.hashCode();
    }
}
