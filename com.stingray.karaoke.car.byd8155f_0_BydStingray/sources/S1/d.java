package S1;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7868a;

    public d(String str) {
        m.f(str, "name");
        this.f7868a = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        return m.a(this.f7868a, ((d) obj).f7868a);
    }

    public final int hashCode() {
        return this.f7868a.hashCode();
    }

    public final String toString() {
        return this.f7868a;
    }
}
