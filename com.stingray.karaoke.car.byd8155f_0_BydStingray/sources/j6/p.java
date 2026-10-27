package j6;

/* JADX INFO: loaded from: classes.dex */
public final class p extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17062a;

    public p(String str) {
        A7.m.f(str, "assetId");
        this.f17062a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p) && A7.m.a(this.f17062a, ((p) obj).f17062a);
    }

    public final int hashCode() {
        return this.f17062a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("HistoryChanged(assetId="), this.f17062a, ")");
    }
}
