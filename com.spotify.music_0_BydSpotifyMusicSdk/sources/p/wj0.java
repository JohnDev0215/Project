package p;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class wj0 {
    public final List a;
    public final Map b;
    public final List c;

    public wj0(ArrayList arrayList, LinkedHashMap linkedHashMap, ArrayList arrayList2) {
        this.a = arrayList;
        this.b = linkedHashMap;
        this.c = arrayList2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wj0)) {
            return false;
        }
        wj0 wj0Var = (wj0) obj;
        return v03.b(this.a, wj0Var.a) && v03.b(this.b, wj0Var.b) && v03.b(this.c, wj0Var.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ESSEvents(lastStatsEvents=");
        sb.append(this.a);
        sb.append(", lastRateLimitedEvents=");
        sb.append(this.b);
        sb.append(", lastDropEvents=");
        return hf2.e(sb, this.c, ')');
    }
}
