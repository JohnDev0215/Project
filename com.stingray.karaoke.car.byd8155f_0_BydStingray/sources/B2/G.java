package B2;

/* JADX INFO: loaded from: classes.dex */
public final class G {
    public static final G c = new G(0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f511b;

    public G(long j3, long j10) {
        this.f510a = j3;
        this.f511b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || G.class != obj.getClass()) {
            return false;
        }
        G g7 = (G) obj;
        return this.f510a == g7.f510a && this.f511b == g7.f511b;
    }

    public final int hashCode() {
        return (((int) this.f510a) * 31) + ((int) this.f511b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[timeUs=");
        sb.append(this.f510a);
        sb.append(", position=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f511b, "]", sb);
    }
}
