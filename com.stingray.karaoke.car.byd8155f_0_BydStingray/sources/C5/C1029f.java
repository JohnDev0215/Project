package c5;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: renamed from: c5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1029f implements InterfaceC1028e, Serializable {
    public final List l;

    public C1029f(List list) {
        this.l = list;
    }

    @Override // c5.InterfaceC1028e
    public final boolean apply(Object obj) {
        int i7 = 0;
        while (true) {
            List list = this.l;
            if (i7 >= list.size()) {
                return true;
            }
            if (!((InterfaceC1028e) list.get(i7)).apply(obj)) {
                return false;
            }
            i7++;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1029f) {
            return this.l.equals(((C1029f) obj).l);
        }
        return false;
    }

    public final int hashCode() {
        return this.l.hashCode() + 306654252;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Predicates.and(");
        boolean z6 = true;
        for (Object obj : this.l) {
            if (!z6) {
                sb.append(',');
            }
            sb.append(obj);
            z6 = false;
        }
        sb.append(')');
        return sb.toString();
    }
}
