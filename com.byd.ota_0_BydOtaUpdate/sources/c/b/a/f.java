package c.b.a;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.LongSerializationPolicy;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GsonBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f2908h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c.b.a.t.c f2901a = c.b.a.t.c.f2940c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LongSerializationPolicy f2902b = LongSerializationPolicy.DEFAULT;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f2903c = FieldNamingPolicy.IDENTITY;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map<Type, g<?>> f2904d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<r> f2905e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<r> f2906f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2907g = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2909i = 2;
    public int j = 2;
    public boolean k = false;
    public boolean l = false;
    public boolean m = true;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;

    public final void a(String str, int i2, int i3, List<r> list) {
        a aVar;
        if (str != null && !"".equals(str.trim())) {
            aVar = new a(str);
        } else if (i2 == 2 || i3 == 2) {
            return;
        } else {
            aVar = new a(i2, i3);
        }
        list.add(c.b.a.t.i.l.f(c.b.a.u.a.a(Date.class), aVar));
        list.add(c.b.a.t.i.l.f(c.b.a.u.a.a(Timestamp.class), aVar));
        list.add(c.b.a.t.i.l.f(c.b.a.u.a.a(java.sql.Date.class), aVar));
    }

    public e b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f2905e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f2906f);
        a(this.f2908h, this.f2909i, this.j, arrayList);
        return new e(this.f2901a, this.f2903c, this.f2904d, this.f2907g, this.k, this.o, this.m, this.n, this.p, this.l, this.f2902b, arrayList);
    }

    public f c() {
        this.k = true;
        return this;
    }

    public f d() {
        this.f2907g = true;
        return this;
    }

    public f e(String str) {
        this.f2908h = str;
        return this;
    }

    public f f() {
        this.n = true;
        return this;
    }
}
