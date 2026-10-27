package H7;

/* JADX INFO: loaded from: classes.dex */
public final class A {
    public static final A c = new A(null, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B f2839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x f2840b;

    public A(B b10, x xVar) {
        String str;
        this.f2839a = b10;
        this.f2840b = xVar;
        if ((b10 == null) == (xVar == null)) {
            return;
        }
        if (b10 == null) {
            str = "Star projection must have no type specified.";
        } else {
            str = "The projection variance " + b10 + " requires type to be specified.";
        }
        throw new IllegalArgumentException(str.toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof A)) {
            return false;
        }
        A a10 = (A) obj;
        return this.f2839a == a10.f2839a && A7.m.a(this.f2840b, a10.f2840b);
    }

    public final int hashCode() {
        B b10 = this.f2839a;
        int iHashCode = (b10 == null ? 0 : b10.hashCode()) * 31;
        x xVar = this.f2840b;
        return iHashCode + (xVar != null ? xVar.hashCode() : 0);
    }

    public final String toString() {
        B b10 = this.f2839a;
        int i7 = b10 == null ? -1 : z.f2854a[b10.ordinal()];
        if (i7 == -1) {
            return "*";
        }
        x xVar = this.f2840b;
        if (i7 == 1) {
            return String.valueOf(xVar);
        }
        if (i7 == 2) {
            return "in " + xVar;
        }
        if (i7 != 3) {
            throw new A9.k();
        }
        return "out " + xVar;
    }
}
