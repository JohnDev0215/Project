package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class l80 {
    public final List a;
    public final String b;

    public l80(String str, List list) {
        this.a = list;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l80)) {
            return false;
        }
        l80 l80Var = (l80) obj;
        return v03.b(this.a, l80Var.a) && v03.b(this.b, l80Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        String str = this.b;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeferredFragmentIdentifier(path=");
        sb.append(this.a);
        sb.append(", label=");
        return hf2.d(sb, this.b, ')');
    }
}
