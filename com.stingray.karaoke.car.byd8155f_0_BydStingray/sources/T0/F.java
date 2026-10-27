package T0;

import N0.C0411f;

/* JADX INFO: loaded from: classes.dex */
public final class F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f8425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f8426b;

    public F(C0411f c0411f, s sVar) {
        this.f8425a = c0411f;
        this.f8426b = sVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof F)) {
            return false;
        }
        F f10 = (F) obj;
        return A7.m.a(this.f8425a, f10.f8425a) && A7.m.a(this.f8426b, f10.f8426b);
    }

    public final int hashCode() {
        return this.f8426b.hashCode() + (this.f8425a.hashCode() * 31);
    }

    public final String toString() {
        return "TransformedText(text=" + ((Object) this.f8425a) + ", offsetMapping=" + this.f8426b + ')';
    }
}
