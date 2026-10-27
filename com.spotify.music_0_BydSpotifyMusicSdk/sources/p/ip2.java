package p;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class ip2 implements ls2 {
    public static final ip2 i = new ip2("", "", "", v03.B(jp2.f), false, "", "");
    public final String a;
    public final String b;
    public final String c;
    public final List d;
    public final boolean e;
    public final String f;
    public final String g;
    public final jp2 h;

    public ip2(String str, String str2, String str3, List list, boolean z, String str4, String str5) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = z;
        this.f = str4;
        this.g = str5;
        this.h = (jp2) ou.c0(list);
    }

    @Override // p.ls2
    public final List a() {
        ArrayList arrayListH = ci1.H(this.d);
        jp2 jp2Var = this.h;
        int length = jp2Var.a.length();
        String str = this.c;
        if (length > 0 && str.length() > 0 && arrayListH.isEmpty()) {
            return EmptyList.c;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(arrayListH);
        if (jp2Var.a.length() == 0) {
            arrayList.add("spec id is empty");
        }
        if (str.length() == 0) {
            arrayList.add("app is empty");
        }
        return arrayList;
    }

    public final hp2 b() {
        hp2 hp2VarM = qo.m();
        String str = this.c;
        v03.j(str, "app");
        hp2VarM.h = str;
        String str2 = this.b;
        v03.j(str2, "generatorVersion");
        hp2VarM.g = str2;
        jp2 jp2Var = this.h;
        String str3 = jp2Var.a;
        v03.j(str3, "specificationId");
        hp2VarM.a = str3;
        String str4 = this.a;
        v03.j(str4, "specificationVersion");
        hp2VarM.f = str4;
        hp2VarM.b = jp2Var.b;
        hp2VarM.d = jp2Var.c;
        hp2VarM.c = jp2Var.d;
        hp2VarM.e = jp2Var.e;
        List list = this.d;
        List listSubList = list.subList(1, list.size());
        v03.j(listSubList, "path");
        ArrayList arrayList = hp2VarM.i;
        arrayList.clear();
        arrayList.addAll(listSubList);
        hp2VarM.j = this.e;
        String str5 = this.f;
        v03.j(str5, "annotatorVersion");
        hp2VarM.k = str5;
        String str6 = this.g;
        v03.j(str6, "annotatorConfigurationVersion");
        hp2VarM.l = str6;
        return hp2VarM;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !v03.b(ip2.class, obj.getClass())) {
            return false;
        }
        ip2 ip2Var = (ip2) obj;
        if (v03.b(this.a, ip2Var.a) && v03.b(this.b, ip2Var.b) && v03.b(this.c, ip2Var.c) && v03.b(this.h, ip2Var.h) && v03.b(this.d, ip2Var.d) && this.e == ip2Var.e && v03.b(this.f, ip2Var.f)) {
            return v03.b(this.g, ip2Var.g);
        }
        return false;
    }

    public final int hashCode() {
        return this.g.hashCode() + hf2.b(this.f, (Boolean.hashCode(this.e) + ((this.d.hashCode() + ((this.h.hashCode() + hf2.b(this.c, hf2.b(this.b, this.a.hashCode() * 31, 31), 31)) * 31)) * 31)) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.c);
        sb.append("][");
        sb.append(this.d.isEmpty() ? "no path" : ou.h0(this.d, "/", null, null, null, 62));
        sb.append("] <spec version ");
        sb.append(this.a);
        sb.append("><generator version ");
        return hf2.d(sb, this.b, '>');
    }
}
