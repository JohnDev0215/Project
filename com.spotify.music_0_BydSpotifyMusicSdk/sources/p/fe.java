package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class fe {
    public final ce a;
    public final List b;
    public final ee c;
    public final String d;
    public final String e;

    public fe(ce ceVar, List list, ee eeVar, String str, String str2) {
        this.a = ceVar;
        this.b = list;
        this.c = eeVar;
        this.d = str;
        this.e = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof fe)) {
            return false;
        }
        fe feVar = (fe) obj;
        return v03.b(this.a, feVar.a) && v03.b(this.b, feVar.b) && v03.b(this.c, feVar.c) && v03.b(this.d, feVar.d) && v03.b(this.e, feVar.e);
    }

    public final int hashCode() {
        ce ceVar = this.a;
        int iHashCode = (ceVar == null ? 0 : ceVar.hashCode()) * 31;
        List list = this.b;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        ee eeVar = this.c;
        return this.e.hashCode() + hf2.b(this.d, (iHashCode2 + (eeVar != null ? eeVar.hashCode() : 0)) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AudiobookInfo(accessInfo=");
        sb.append(this.a);
        sb.append(", authors=");
        sb.append(this.b);
        sb.append(", coverArt=");
        sb.append(this.c);
        sb.append(", name=");
        sb.append(this.d);
        sb.append(", uri=");
        return hf2.d(sb, this.e, ')');
    }
}
