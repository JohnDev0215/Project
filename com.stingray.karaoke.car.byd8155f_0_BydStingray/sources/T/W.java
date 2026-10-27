package T;

import u.C2484x;

/* JADX INFO: loaded from: classes.dex */
public final class W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2484x f8226a;

    public final boolean equals(Object obj) {
        if (obj instanceof W) {
            return A7.m.a(this.f8226a, ((W) obj).f8226a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8226a.hashCode();
    }

    public final String toString() {
        return "MutableScatterMultiMap(map=" + this.f8226a + ')';
    }
}
