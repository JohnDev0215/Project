package p;

/* JADX INFO: loaded from: classes.dex */
public final class vb {
    public final tb a;

    public vb(tb tbVar) {
        this.a = tbVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof vb) && v03.b(this.a, ((vb) obj).a);
    }

    public final int hashCode() {
        tb tbVar = this.a;
        if (tbVar == null) {
            return 0;
        }
        return tbVar.hashCode();
    }

    public final String toString() {
        return "Visuals(avatarImage=" + this.a + ')';
    }
}
