package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class pk0 extends l13 {
    public final List f;
    public final nk0 g;

    public pk0(nk0 nk0Var, List list) {
        v03.j(list, "data");
        v03.j(nk0Var, "endpoint");
        this.f = list;
        this.g = nk0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pk0)) {
            return false;
        }
        pk0 pk0Var = (pk0) obj;
        return v03.b(this.f, pk0Var.f) && v03.b(this.g, pk0Var.g);
    }

    public final int hashCode() {
        return this.g.hashCode() + (this.f.hashCode() * 31);
    }

    public final String toString() {
        return "Some(data=" + this.f + ", endpoint=" + this.g + ')';
    }
}
