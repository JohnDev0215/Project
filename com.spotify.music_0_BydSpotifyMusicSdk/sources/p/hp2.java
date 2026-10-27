package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class hp2 {
    public String a;
    public String b;
    public Integer c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public ArrayList i;
    public boolean j;
    public String k;
    public String l;

    public final ip2 a() {
        ArrayList arrayList = new ArrayList();
        String str = this.a;
        ci1.C(str, "name");
        arrayList.add(new jp2(str, this.b, this.d, this.c, this.e));
        ArrayList arrayList2 = this.i;
        if (!arrayList2.isEmpty()) {
            arrayList.addAll(arrayList2);
        }
        String str2 = this.f;
        String str3 = this.g;
        String str4 = this.h;
        ci1.C(str4, "app");
        return new ip2(str2, str3, str4, arrayList, this.j, this.k, this.l);
    }
}
