package K5;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3660a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3661b;

    public v(String str, String str2) {
        this.f3660a = str;
        this.f3661b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return A7.m.a(this.f3660a, vVar.f3660a) && A7.m.a(this.f3661b, vVar.f3661b);
    }

    public final int hashCode() {
        String str = this.f3660a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f3661b;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        return "SongTheme(textColor=" + this.f3660a + ", wipeColor=" + this.f3661b + ")";
    }
}
