package k4;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18197a;

    public j(String str) {
        this.f18197a = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f18197a.equals(((j) obj).f18197a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f18197a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("StringHeaderFactory{value='"), this.f18197a, "'}");
    }
}
