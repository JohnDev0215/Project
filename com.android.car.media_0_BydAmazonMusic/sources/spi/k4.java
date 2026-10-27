package spi;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GsonBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k4 {
    public s3 a;
    public k6 b;
    public x3 c;
    public final Map<Type, b5<?>> d;
    public final List<ob> e;
    public final List<ob> f;
    public boolean g;
    public String h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;

    public k4() {
        this.a = s3.f;
        this.b = k6.a;
        this.c = w3.a;
        this.d = new HashMap();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = false;
        this.i = 2;
        this.j = 2;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = false;
        this.o = false;
        this.p = false;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<spi.ob>] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<spi.ob>] */
    public final j4 a() {
        f3 f3Var;
        f3 f3Var2;
        f3 f3Var3;
        ArrayList arrayList = new ArrayList(this.f.size() + this.e.size() + 3);
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        String str = this.h;
        int i = this.i;
        int i2 = this.j;
        if (str == null || "".equals(str.trim())) {
            if (i != 2 && i2 != 2) {
                f3 f3Var4 = new f3(Date.class, i, i2);
                f3 f3Var5 = new f3(Timestamp.class, i, i2);
                f3 f3Var6 = new f3(java.sql.Date.class, i, i2);
                f3Var = f3Var4;
                f3Var2 = f3Var5;
                f3Var3 = f3Var6;
            }
            return new j4(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, this.h, this.i, this.j, this.e, this.f, arrayList);
        }
        f3Var = new f3((Class<? extends Date>) Date.class, str);
        f3Var2 = new f3((Class<? extends Date>) Timestamp.class, str);
        f3Var3 = new f3((Class<? extends Date>) java.sql.Date.class, str);
        nb<Class> nbVar = qb.a;
        arrayList.add(new rb(Date.class, f3Var));
        arrayList.add(new rb(Timestamp.class, f3Var2));
        arrayList.add(new rb(java.sql.Date.class, f3Var3));
        return new j4(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, this.h, this.i, this.j, this.e, this.f, arrayList);
    }

    public k4(j4 j4Var) {
        this.a = s3.f;
        this.b = k6.a;
        this.c = w3.a;
        HashMap map = new HashMap();
        this.d = map;
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.f = arrayList2;
        this.g = false;
        this.i = 2;
        this.j = 2;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = false;
        this.o = false;
        this.p = false;
        this.a = j4Var.f;
        this.c = j4Var.g;
        map.putAll(j4Var.h);
        this.g = j4Var.i;
        this.k = j4Var.j;
        this.o = j4Var.k;
        this.m = j4Var.l;
        this.n = j4Var.m;
        this.p = j4Var.n;
        this.l = j4Var.o;
        this.b = j4Var.s;
        this.h = j4Var.p;
        this.i = j4Var.q;
        this.j = j4Var.r;
        arrayList.addAll(j4Var.t);
        arrayList2.addAll(j4Var.u);
    }
}
