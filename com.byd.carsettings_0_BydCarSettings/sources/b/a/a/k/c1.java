package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SerializeFilterable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<k> f342a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<c> f343b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<v0> f344c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<g1> f345d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<r0> f346e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<w0> f347f = null;
    public List<l0> g = null;
    public List<v> h = null;
    public boolean i = true;

    public void b(b1 b1Var) {
        if (b1Var == null) {
            return;
        }
        if (b1Var instanceof w0) {
            m().add((w0) b1Var);
        }
        if (b1Var instanceof r0) {
            k().add((r0) b1Var);
        }
        if (b1Var instanceof g1) {
            n().add((g1) b1Var);
        }
        if (b1Var instanceof v) {
            i().add((v) b1Var);
        }
        if (b1Var instanceof v0) {
            l().add((v0) b1Var);
        }
        if (b1Var instanceof k) {
            h().add((k) b1Var);
        }
        if (b1Var instanceof c) {
            g().add((c) b1Var);
        }
        if (b1Var instanceof l0) {
            j().add((l0) b1Var);
        }
    }

    public boolean e(i0 i0Var, Object obj, String str, Object obj2) {
        List<v0> list = i0Var.f344c;
        if (list != null) {
            Iterator<v0> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().b(obj, str, obj2)) {
                    return false;
                }
            }
        }
        List<v0> list2 = this.f344c;
        if (list2 == null) {
            return true;
        }
        Iterator<v0> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (!it2.next().b(obj, str, obj2)) {
                return false;
            }
        }
        return true;
    }

    public boolean f(i0 i0Var, Object obj, String str) {
        List<w0> list = i0Var.f347f;
        if (list != null) {
            Iterator<w0> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().e(i0Var, obj, str)) {
                    return false;
                }
            }
        }
        List<w0> list2 = this.f347f;
        if (list2 == null) {
            return true;
        }
        Iterator<w0> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (!it2.next().e(i0Var, obj, str)) {
                return false;
            }
        }
        return true;
    }

    public List<c> g() {
        if (this.f343b == null) {
            this.f343b = new ArrayList();
            this.i = false;
        }
        return this.f343b;
    }

    public List<k> h() {
        if (this.f342a == null) {
            this.f342a = new ArrayList();
            this.i = false;
        }
        return this.f342a;
    }

    public List<v> i() {
        if (this.h == null) {
            this.h = new ArrayList();
            this.i = false;
        }
        return this.h;
    }

    public List<l0> j() {
        if (this.g == null) {
            this.g = new ArrayList();
            this.i = false;
        }
        return this.g;
    }

    public List<r0> k() {
        if (this.f346e == null) {
            this.f346e = new ArrayList();
            this.i = false;
        }
        return this.f346e;
    }

    public List<v0> l() {
        if (this.f344c == null) {
            this.f344c = new ArrayList();
            this.i = false;
        }
        return this.f344c;
    }

    public List<w0> m() {
        if (this.f347f == null) {
            this.f347f = new ArrayList();
            this.i = false;
        }
        return this.f347f;
    }

    public List<g1> n() {
        if (this.f345d == null) {
            this.f345d = new ArrayList();
            this.i = false;
        }
        return this.f345d;
    }

    public String o(i0 i0Var, Object obj, String str, Object obj2) {
        List<r0> list = i0Var.f346e;
        if (list != null) {
            Iterator<r0> it = list.iterator();
            while (it.hasNext()) {
                str = it.next().a(obj, str, obj2);
            }
        }
        List<r0> list2 = this.f346e;
        if (list2 != null) {
            Iterator<r0> it2 = list2.iterator();
            while (it2.hasNext()) {
                str = it2.next().a(obj, str, obj2);
            }
        }
        return str;
    }

    public Object p(i0 i0Var, j jVar, Object obj, String str, Object obj2, int i) {
        boolean z;
        if (obj2 != null) {
            int i2 = i0Var.k.i;
            SerializerFeature serializerFeature = SerializerFeature.WriteNonStringValueAsString;
            if ((SerializerFeature.isEnabled(i2, i, serializerFeature) || !(jVar == null || (jVar.a() & serializerFeature.mask) == 0)) && (((z = obj2 instanceof Number)) || (obj2 instanceof Boolean))) {
                String strB = null;
                if (z && jVar != null) {
                    strB = jVar.b();
                }
                obj2 = strB != null ? new DecimalFormat(strB).format(obj2) : obj2.toString();
            } else if (jVar != null && jVar.c()) {
                obj2 = b.a.a.a.parse((String) obj2);
            }
        }
        List<g1> list = i0Var.f345d;
        if (list != null) {
            Iterator<g1> it = list.iterator();
            while (it.hasNext()) {
                obj2 = it.next().a(obj, str, obj2);
            }
        }
        List<g1> list2 = this.f345d;
        if (list2 != null) {
            Iterator<g1> it2 = list2.iterator();
            while (it2.hasNext()) {
                obj2 = it2.next().a(obj, str, obj2);
            }
        }
        List<v> list3 = i0Var.h;
        if (list3 != null) {
            Iterator<v> it3 = list3.iterator();
            while (it3.hasNext()) {
                obj2 = it3.next().d(jVar, obj, str, obj2);
            }
        }
        List<v> list4 = this.h;
        if (list4 != null) {
            Iterator<v> it4 = list4.iterator();
            while (it4.hasNext()) {
                obj2 = it4.next().d(jVar, obj, str, obj2);
            }
        }
        return obj2;
    }
}
