package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: TranslateOptions.java */
/* JADX INFO: loaded from: classes3.dex */
public final class if3 {
    public String a;
    public String b;
    public final ArrayList<a> c;
    public String[] d;
    public final HashMap e = new HashMap();
    public final HashMap f = new HashMap();
    public final boolean g;
    public final boolean[] h;

    /* JADX INFO: compiled from: TranslateOptions.java */
    public static class a {
        public final String a;
        public final String b;
        public final Integer c;

        public a(String str, String str2, Integer num) {
            this.a = str;
            this.b = str2;
            this.c = num;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a.equals(aVar.a) && this.b.equals(aVar.b) && this.c.equals(aVar.c);
        }

        public final int hashCode() {
            return (this.a + this.b).hashCode();
        }

        public final String toString() {
            return "mLanguageCode:" + this.a + " - mLanguageRepresentation " + this.b + " - mLanguageUMAHashCode " + this.c;
        }
    }

    public if3(String str, String str2, ArrayList arrayList, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4) {
        this.h = new boolean[]{z, z2, z3};
        this.a = str;
        this.b = str2;
        this.g = z4;
        this.c = arrayList;
        this.d = strArr;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            this.e.put(aVar.a, aVar.b);
            this.f.put(aVar.a, aVar.c);
        }
    }

    public final String a(String str) {
        return b(str) ? (String) this.e.get(str) : "";
    }

    public final boolean b(String str) {
        return !TextUtils.isEmpty(str) && this.e.containsKey(str);
    }

    public final String c() {
        return a(this.a);
    }

    public final void d(boolean z) {
        boolean[] zArr = this.h;
        if (zArr[0] && z) {
            boolean z2 = zArr[2];
            zArr[0] = false;
        }
        zArr[2] = z;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append(" -> ");
        sb.append(this.b);
        sb.append(" - Never Language:");
        boolean[] zArr = this.h;
        sb.append(zArr[0]);
        sb.append(" Always Language:");
        sb.append(zArr[2]);
        sb.append(" Never Domain:");
        sb.append(zArr[1]);
        return sb.toString();
    }
}
