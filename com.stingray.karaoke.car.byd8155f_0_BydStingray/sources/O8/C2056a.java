package o8;

import A7.m;
import Q8.o;

/* JADX INFO: renamed from: o8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2056a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2058c f20562a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2061f f20563b;

    static {
        C2058c.j(AbstractC2063h.f20578f);
    }

    public C2056a(C2058c c2058c, C2061f c2061f) {
        m.f(c2058c, "packageName");
        this.f20562a = c2058c;
        this.f20563b = c2061f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2056a)) {
            return false;
        }
        C2056a c2056a = (C2056a) obj;
        return m.a(this.f20562a, c2056a.f20562a) && m.a(null, null) && this.f20563b.equals(c2056a.f20563b) && m.a(null, null);
    }

    public final int hashCode() {
        return (this.f20563b.hashCode() + (this.f20562a.hashCode() * 961)) * 31;
    }

    public final String toString() {
        String str = o.w0(this.f20562a.b(), '.', '/') + "/" + this.f20563b;
        m.e(str, "StringBuilder().apply(builderAction).toString()");
        return str;
    }
}
