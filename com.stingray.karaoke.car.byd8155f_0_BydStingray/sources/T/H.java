package T;

/* JADX INFO: loaded from: classes.dex */
public final class H implements T0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0738e0 f8184a;

    public H(C0738e0 c0738e0) {
        this.f8184a = c0738e0;
    }

    @Override // T.T0
    public final Object a(InterfaceC0744h0 interfaceC0744h0) {
        return this.f8184a.getValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof H) && this.f8184a.equals(((H) obj).f8184a);
    }

    public final int hashCode() {
        return this.f8184a.hashCode();
    }

    public final String toString() {
        return "DynamicValueHolder(state=" + this.f8184a + ')';
    }
}
