package O7;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f5179a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5180b;

    public d(e eVar, int i7) {
        this.f5179a = eVar;
        this.f5180b = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f5179a == dVar.f5179a && this.f5180b == dVar.f5180b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f5180b) + (this.f5179a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KindWithArity(kind=");
        sb.append(this.f5179a);
        sb.append(", arity=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f5180b, ')');
    }
}
