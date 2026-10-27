package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class bp2 {
    public final ip2 a;
    public final cp2 b;
    public final List c;

    public bp2(ip2 ip2Var, cp2 cp2Var, ArrayList arrayList) {
        v03.j(ip2Var, "location");
        v03.j(cp2Var, "parentAbsoluteLocation");
        v03.j(arrayList, "errors");
        this.a = ip2Var;
        this.b = cp2Var;
        this.c = arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.ubi.android.eventdefinitions.UbiEvent<*, *>");
        bp2 bp2Var = (bp2) obj;
        return v03.b(this.a, bp2Var.a) && v03.b(this.b, bp2Var.b) && v03.b(this.c, bp2Var.c);
    }

    public int hashCode() {
        return this.c.hashCode() + ((this.b.a.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{where=");
        sb.append(this.a);
        sb.append("; parent=");
        sb.append(this.b);
        return hf2.d(sb, this.c.isEmpty() ? "" : "; errors=".concat(ou.h0(this.c, ";", null, null, null, 62)), '}');
    }
}
