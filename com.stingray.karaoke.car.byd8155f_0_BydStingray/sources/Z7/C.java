package Z7;

import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2061f f10907a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f10908b;

    public C(C2061f c2061f, String str) {
        A7.m.f(str, "signature");
        this.f10907a = c2061f;
        this.f10908b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C)) {
            return false;
        }
        C c = (C) obj;
        return A7.m.a(this.f10907a, c.f10907a) && A7.m.a(this.f10908b, c.f10908b);
    }

    public final int hashCode() {
        return this.f10908b.hashCode() + (this.f10907a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NameAndSignature(name=");
        sb.append(this.f10907a);
        sb.append(", signature=");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, this.f10908b, ')');
    }
}
