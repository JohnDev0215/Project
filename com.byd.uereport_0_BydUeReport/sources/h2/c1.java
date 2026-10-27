package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SerializeFilterable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<k> f5192a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<c> f5193b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<v0> f5194c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<g1> f5195d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<r0> f5196e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<w0> f5197f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<l0> f5198g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List<v> f5199h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5200i = true;

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
        List<v0> list = i0Var.f5194c;
        if (list != null) {
            Iterator<v0> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().b(obj, str, obj2)) {
                    return false;
                }
            }
        }
        List<v0> list2 = this.f5194c;
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
        List<w0> list = i0Var.f5197f;
        if (list != null) {
            Iterator<w0> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().e(i0Var, obj, str)) {
                    return false;
                }
            }
        }
        List<w0> list2 = this.f5197f;
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
        if (this.f5193b == null) {
            this.f5193b = new ArrayList();
            this.f5200i = false;
        }
        return this.f5193b;
    }

    public List<k> h() {
        if (this.f5192a == null) {
            this.f5192a = new ArrayList();
            this.f5200i = false;
        }
        return this.f5192a;
    }

    public List<v> i() {
        if (this.f5199h == null) {
            this.f5199h = new ArrayList();
            this.f5200i = false;
        }
        return this.f5199h;
    }

    public List<l0> j() {
        if (this.f5198g == null) {
            this.f5198g = new ArrayList();
            this.f5200i = false;
        }
        return this.f5198g;
    }

    public List<r0> k() {
        if (this.f5196e == null) {
            this.f5196e = new ArrayList();
            this.f5200i = false;
        }
        return this.f5196e;
    }

    public List<v0> l() {
        if (this.f5194c == null) {
            this.f5194c = new ArrayList();
            this.f5200i = false;
        }
        return this.f5194c;
    }

    public List<w0> m() {
        if (this.f5197f == null) {
            this.f5197f = new ArrayList();
            this.f5200i = false;
        }
        return this.f5197f;
    }

    public List<g1> n() {
        if (this.f5195d == null) {
            this.f5195d = new ArrayList();
            this.f5200i = false;
        }
        return this.f5195d;
    }

    public String o(i0 i0Var, Object obj, String str, Object obj2) {
        List<r0> list = i0Var.f5196e;
        if (list != null) {
            Iterator<r0> it = list.iterator();
            while (it.hasNext()) {
                str = it.next().a(obj, str, obj2);
            }
        }
        List<r0> list2 = this.f5196e;
        if (list2 != null) {
            Iterator<r0> it2 = list2.iterator();
            while (it2.hasNext()) {
                str = it2.next().a(obj, str, obj2);
            }
        }
        return str;
    }

    public Object p(i0 i0Var, j jVar, Object obj, String str, Object obj2, int i8) {
        boolean z7;
        if (obj2 != null) {
            int i9 = i0Var.f5241k.f5215l;
            SerializerFeature serializerFeature = SerializerFeature.WriteNonStringValueAsString;
            if ((SerializerFeature.isEnabled(i9, i8, serializerFeature) || !(jVar == null || (jVar.a() & serializerFeature.mask) == 0)) && (((z7 = obj2 instanceof Number)) || (obj2 instanceof Boolean))) {
                String strB = null;
                if (z7 && jVar != null) {
                    strB = jVar.b();
                }
                obj2 = strB != null ? new DecimalFormat(strB).format(obj2) : obj2.toString();
            } else if (jVar != null && jVar.c()) {
                obj2 = c2.a.parse((String) obj2);
            }
        }
        List<g1> list = i0Var.f5195d;
        if (list != null) {
            Iterator<g1> it = list.iterator();
            while (it.hasNext()) {
                obj2 = it.next().a(obj, str, obj2);
            }
        }
        List<g1> list2 = this.f5195d;
        if (list2 != null) {
            Iterator<g1> it2 = list2.iterator();
            while (it2.hasNext()) {
                obj2 = it2.next().a(obj, str, obj2);
            }
        }
        List<v> list3 = i0Var.f5199h;
        if (list3 != null) {
            Iterator<v> it3 = list3.iterator();
            while (it3.hasNext()) {
                obj2 = it3.next().d(jVar, obj, str, obj2);
            }
        }
        List<v> list4 = this.f5199h;
        if (list4 != null) {
            Iterator<v> it4 = list4.iterator();
            while (it4.hasNext()) {
                obj2 = it4.next().d(jVar, obj, str, obj2);
            }
        }
        return obj2;
    }
}
