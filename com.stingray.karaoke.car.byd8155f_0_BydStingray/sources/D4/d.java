package D4;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1182a;

    public d(String str) {
        m.f(str, "url");
        this.f1182a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof d) && m.a(this.f1182a, ((d) obj).f1182a);
    }

    public final int hashCode() {
        return this.f1182a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("Url(url="), this.f1182a, ')');
    }
}
