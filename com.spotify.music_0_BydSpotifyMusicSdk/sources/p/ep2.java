package p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class ep2 implements ls2 {
    public static final ep2 e = new ep2("", 0, "", new HashMap());
    public final String a;
    public final int b;
    public final String c;
    public final Map d;

    public ep2(String str, int i, String str2, HashMap map) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = map;
    }

    @Override // p.ls2
    public final List a() {
        String str = this.a;
        int length = str.length();
        int i = this.b;
        String str2 = this.c;
        if (length > 0 && str2.length() > 0 && i > 0) {
            return EmptyList.c;
        }
        ArrayList arrayList = new ArrayList();
        if (str.length() == 0) {
            arrayList.add("Empty action id");
        }
        if (i <= 0) {
            arrayList.add("Non-positive version");
        }
        if (str2.length() == 0) {
            arrayList.add("Empty interaction type");
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !v03.b(ep2.class, obj.getClass())) {
            return false;
        }
        ep2 ep2Var = (ep2) obj;
        if (this.b == ep2Var.b && v03.b(this.a, ep2Var.a) && v03.b(this.c, ep2Var.c)) {
            return v03.b(this.d, ep2Var.d);
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() + hf2.b(this.c, ((this.a.hashCode() * 31) + this.b) * 31, 31);
    }

    public final String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        sb.append(this.c);
        sb.append('/');
        sb.append(this.a);
        sb.append('(');
        sb.append(this.b);
        sb.append('=');
        Map map = this.d;
        if (map.isEmpty()) {
            string = "";
        } else {
            StringBuilder sb2 = new StringBuilder("(");
            boolean z = true;
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (!z) {
                    sb2.append(",");
                }
                sb2.append(str + '=' + str2);
                if (z) {
                    z = false;
                }
            }
            sb2.append(")");
            string = sb2.toString();
            v03.i(string, "paramsAsString.append(\")\").toString()");
        }
        return hf2.d(sb, string, ')');
    }
}
