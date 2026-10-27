package n3;

/* JADX INFO: renamed from: n3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1936a extends y {
    @Override // n3.y
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C1936a)) {
            return false;
        }
        if (super.equals(obj)) {
            if (A7.m.a(null, null)) {
                return true;
            }
        }
        return false;
    }

    @Override // n3.y
    public final int hashCode() {
        return super.hashCode() * 961;
    }
}
