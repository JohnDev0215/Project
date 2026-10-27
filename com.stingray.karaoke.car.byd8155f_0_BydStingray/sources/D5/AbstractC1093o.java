package d5;

/* JADX INFO: renamed from: d5.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1093o {
    public transient C1083e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient C1092n f13446m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient C1082d f13447n;

    public abstract C1082d a();

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC1093o) {
            return ((Y) this).a().equals(((Y) ((AbstractC1093o) obj)).a());
        }
        return false;
    }

    public final int hashCode() {
        return a().f13414n.hashCode();
    }

    public final String toString() {
        return a().f13414n.toString();
    }
}
