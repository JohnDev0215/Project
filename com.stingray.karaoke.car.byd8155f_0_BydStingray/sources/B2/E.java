package B2;

/* JADX INFO: loaded from: classes.dex */
public final class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G f508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final G f509b;

    public E(G g7, G g10) {
        this.f508a = g7;
        this.f509b = g10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || E.class != obj.getClass()) {
            return false;
        }
        E e10 = (E) obj;
        return this.f508a.equals(e10.f508a) && this.f509b.equals(e10.f509b);
    }

    public final int hashCode() {
        return this.f509b.hashCode() + (this.f508a.hashCode() * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("[");
        G g7 = this.f508a;
        sb.append(g7);
        G g10 = this.f509b;
        if (g7.equals(g10)) {
            str = "";
        } else {
            str = ", " + g10;
        }
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, str, "]");
    }
}
