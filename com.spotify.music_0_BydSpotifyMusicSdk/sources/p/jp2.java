package p;

import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class jp2 implements ls2 {
    public static final jp2 f = new jp2("", null, null, null, null);
    public final String a;
    public final String b;
    public final String c;
    public final Integer d;
    public final String e;

    public jp2(String str, String str2, String str3, Integer num, String str4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = num;
        this.e = str4;
    }

    @Override // p.ls2
    public final List a() {
        return this.a.length() == 0 ? v03.B("empty path node name") : EmptyList.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(jp2.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type com.spotify.ubi.android.eventdefinitions.internal.UbiEventPathNode");
        jp2 jp2Var = (jp2) obj;
        return v03.b(this.a, jp2Var.a) && v03.b(this.b, jp2Var.b) && v03.b(this.c, jp2Var.c) && v03.b(this.d, jp2Var.d) && v03.b(this.e, jp2Var.e);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        String str = this.b;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.c;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.d;
        int iIntValue = (iHashCode3 + (num != null ? num.intValue() : 0)) * 31;
        String str3 = this.e;
        return iIntValue + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.e;
        Integer num = this.d;
        String str3 = this.c;
        String str4 = this.b;
        if (str4 == null && str3 == null && num == null && str2 == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append("(");
        boolean zJ = u11.j(sb, "id", str4, false);
        boolean zJ2 = zJ & u11.j(sb, "uri", str3, zJ);
        u11.j(sb, "reason", str2, zJ2 & u11.j(sb, "pos", num, zJ2));
        sb.append(")");
        String string = sb.toString();
        v03.i(string, "{\n            val asStri…\")\").toString()\n        }");
        return string;
    }
}
