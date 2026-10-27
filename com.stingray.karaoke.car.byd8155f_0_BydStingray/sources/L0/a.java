package L0;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3875a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m7.c f3876b;

    public a(String str, m7.c cVar) {
        this.f3875a = str;
        this.f3876b = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return A7.m.a(this.f3875a, aVar.f3875a) && A7.m.a(this.f3876b, aVar.f3876b);
    }

    public final int hashCode() {
        String str = this.f3875a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        m7.c cVar = this.f3876b;
        return iHashCode + (cVar != null ? cVar.hashCode() : 0);
    }

    public final String toString() {
        return "AccessibilityAction(label=" + this.f3875a + ", action=" + this.f3876b + ')';
    }
}
