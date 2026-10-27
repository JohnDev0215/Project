package C0;

/* JADX INFO: renamed from: C0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0105l implements InterfaceC0103j {
    @Override // C0.InterfaceC0103j
    public final long a(long j3, long j10) {
        return c0.b(1.0f, 1.0f);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0105l)) {
            return false;
        }
        ((C0105l) obj).getClass();
        return Float.compare(1.0f, 1.0f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(1.0f);
    }

    public final String toString() {
        return "FixedScale(value=1.0)";
    }
}
