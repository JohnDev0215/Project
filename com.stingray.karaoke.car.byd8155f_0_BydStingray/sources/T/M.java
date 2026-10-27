package T;

/* JADX INFO: loaded from: classes.dex */
public final class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f8202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f8203b;

    public M(Integer num, Object obj) {
        this.f8202a = num;
        this.f8203b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof M)) {
            return false;
        }
        M m10 = (M) obj;
        return this.f8202a.equals(m10.f8202a) && A7.m.a(this.f8203b, m10.f8203b);
    }

    public final int hashCode() {
        int iHashCode = this.f8202a.hashCode() * 31;
        Object obj = this.f8203b;
        return (obj instanceof Enum ? ((Enum) obj).ordinal() : obj != null ? obj.hashCode() : 0) + iHashCode;
    }

    public final String toString() {
        return "JoinedKey(left=" + this.f8202a + ", right=" + this.f8203b + ')';
    }
}
