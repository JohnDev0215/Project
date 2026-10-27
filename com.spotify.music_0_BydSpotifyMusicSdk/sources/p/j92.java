package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class j92 {
    public final List a;
    public final List b;

    public j92(ArrayList arrayList, ArrayList arrayList2) {
        this.a = arrayList;
        this.b = arrayList2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j92)) {
            return false;
        }
        j92 j92Var = (j92) obj;
        return v03.b(this.a, j92Var.a) && v03.b(this.b, j92Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TopResults(featured=");
        sb.append(this.a);
        sb.append(", itemsV2=");
        return hf2.e(sb, this.b, ')');
    }
}
