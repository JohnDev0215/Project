package x;

/* JADX INFO: loaded from: classes.dex */
public final class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Float f23438a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InterfaceC2612A f23439b;
    public int c;

    public M(Float f10, InterfaceC2612A interfaceC2612A) {
        this.f23438a = f10;
        this.f23439b = interfaceC2612A;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof M)) {
            return false;
        }
        M m10 = (M) obj;
        return m10.f23438a.equals(this.f23438a) && A7.m.a(m10.f23439b, this.f23439b) && m10.c == this.c;
    }

    public final int hashCode() {
        return this.f23439b.hashCode() + AbstractC2641j.b(this.c, this.f23438a.hashCode() * 31, 31);
    }
}
