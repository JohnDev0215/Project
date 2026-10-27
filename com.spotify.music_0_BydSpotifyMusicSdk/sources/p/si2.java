package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class si2 {
    public final String a;
    public final String b;
    public final String c;
    public final List d;
    public final List e;

    public si2(String str, String str2, String str3, List list, List list2) {
        v03.j(list, "columnNames");
        v03.j(list2, "referenceColumnNames");
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof si2)) {
            return false;
        }
        si2 si2Var = (si2) obj;
        if (v03.b(this.a, si2Var.a) && v03.b(this.b, si2Var.b) && v03.b(this.c, si2Var.c) && v03.b(this.d, si2Var.d)) {
            return v03.b(this.e, si2Var.e);
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode() + ((this.d.hashCode() + hf2.b(this.c, hf2.b(this.b, this.a.hashCode() * 31, 31), 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ForeignKey{referenceTable='");
        sb.append(this.a);
        sb.append("', onDelete='");
        sb.append(this.b);
        sb.append(" +', onUpdate='");
        sb.append(this.c);
        sb.append("', columnNames=");
        sb.append(this.d);
        sb.append(", referenceColumnNames=");
        return hf2.e(sb, this.e, '}');
    }
}
