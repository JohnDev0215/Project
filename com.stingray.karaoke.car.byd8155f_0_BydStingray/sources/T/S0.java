package T;

/* JADX INFO: loaded from: classes.dex */
public final class S0 implements T0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8225a;

    public S0(Object obj) {
        this.f8225a = obj;
    }

    @Override // T.T0
    public final Object a(InterfaceC0744h0 interfaceC0744h0) {
        return this.f8225a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof S0) && A7.m.a(this.f8225a, ((S0) obj).f8225a);
    }

    public final int hashCode() {
        Object obj = this.f8225a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "StaticValueHolder(value=" + this.f8225a + ')';
    }
}
