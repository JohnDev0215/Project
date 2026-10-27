package org.chromium.ui.modelutil;

import android.content.res.Resources;
import defpackage.v62;
import defpackage.zj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class PropertyModel extends org.chromium.ui.modelutil.k<v62> {
    public final Map<v62, m> b;

    public static class a extends m {
        public boolean a;

        public final boolean equals(Object obj) {
            return obj != null && (obj instanceof a) && ((a) obj).a == this.a;
        }

        public final String toString() {
            return this.a + " in " + super.toString();
        }
    }

    public static class b {
        public final Map<v62, m> a;

        public b(v62... v62VarArr) {
            this.a = PropertyModel.e(v62VarArr);
        }

        public final PropertyModel a() {
            return new PropertyModel(this.a);
        }

        public final void b(h hVar, boolean z) {
            int i = zj.a;
            a aVar = new a();
            aVar.a = z;
            this.a.put(hVar, aVar);
        }

        public final void c(j jVar, int i) {
            int i2 = zj.a;
            d dVar = new d();
            dVar.a = i;
            this.a.put(jVar, dVar);
        }

        public final void d(l lVar, Resources resources, int i) {
            if (i != 0) {
                e(lVar, resources.getString(i));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void e(l lVar, Object obj) {
            int i = zj.a;
            g gVar = new g();
            gVar.a = obj;
            this.a.put(lVar, gVar);
        }

        public final void f(o oVar, float f) {
            int i = zj.a;
            c cVar = new c();
            cVar.a = f;
            this.a.put(oVar, cVar);
        }
    }

    public static class c extends m {
        public float a;

        public final boolean equals(Object obj) {
            return obj != null && (obj instanceof c) && ((c) obj).a == this.a;
        }

        public final String toString() {
            return this.a + " in " + super.toString();
        }
    }

    public static class d extends m {
        public int a;

        public final boolean equals(Object obj) {
            return obj != null && (obj instanceof d) && ((d) obj).a == this.a;
        }

        public final String toString() {
            return this.a + " in " + super.toString();
        }
    }

    public static class e extends m {
        public long a;

        public final boolean equals(Object obj) {
            return obj != null && (obj instanceof e) && ((e) obj).a == this.a;
        }

        public final String toString() {
            return this.a + " in " + super.toString();
        }
    }

    public static class f implements v62 {
        public final String a;

        public f(String str) {
            this.a = str;
        }

        public final String toString() {
            String str = this.a;
            return str == null ? super.toString() : str;
        }
    }

    public static class g<T> extends m {
        public T a;

        public final boolean equals(Object obj) {
            return obj != null && (obj instanceof g) && Objects.equals(((g) obj).a, this.a);
        }

        public final String toString() {
            return this.a + " in " + super.toString();
        }
    }

    public static class h extends f {
        public h() {
            super(null);
        }

        public h(String str) {
            super(str);
        }
    }

    public static class i extends f {
        public i() {
            super(null);
        }
    }

    public static class j extends f {
        public j() {
            super(null);
        }

        public j(String str) {
            super(str);
        }
    }

    public static class k extends f {
        public k() {
            super(null);
        }
    }

    public static class l<T> extends f {
        public l() {
            super(null);
        }

        public l(String str) {
            super(str);
        }
    }

    public static class m {
    }

    public static final class n extends h {
        public n() {
            super(null);
        }

        public n(String str) {
            super(str);
        }
    }

    public static final class o extends i {
    }

    public static final class p extends j {
        public p() {
            super(null);
        }

        public p(String str) {
            super(str);
        }
    }

    public static final class q extends k {
    }

    public static final class r<T> extends l<T> {
        public final boolean b;

        public r() {
            this(null, false);
        }

        public r(String str, boolean z) {
            super(str);
            this.b = z;
        }
    }

    public PropertyModel(v62... v62VarArr) {
        this(e(v62VarArr));
    }

    public static HashMap e(v62[] v62VarArr) {
        HashMap map = new HashMap();
        for (v62 v62Var : v62VarArr) {
            if (map.containsKey(v62Var)) {
                throw new IllegalArgumentException("Duplicate key: " + v62Var);
            }
            map.put(v62Var, null);
        }
        return map;
    }

    public static v62[] f(v62[] v62VarArr, v62[] v62VarArr2) {
        v62[] v62VarArr3 = new v62[v62VarArr.length + v62VarArr2.length];
        System.arraycopy(v62VarArr, 0, v62VarArr3, 0, v62VarArr.length);
        System.arraycopy(v62VarArr2, 0, v62VarArr3, v62VarArr.length, v62VarArr2.length);
        return v62VarArr3;
    }

    @Override // org.chromium.ui.modelutil.k
    public final ArrayList b() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<v62, m> entry : this.b.entrySet()) {
            if (entry.getValue() != null) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public final float g(o oVar) {
        int i2 = zj.a;
        c cVar = (c) this.b.get(oVar);
        if (cVar == null) {
            return 0.0f;
        }
        return cVar.a;
    }

    public final int h(j jVar) {
        int i2 = zj.a;
        d dVar = (d) this.b.get(jVar);
        if (dVar == null) {
            return 0;
        }
        return dVar.a;
    }

    public final long i(q qVar) {
        int i2 = zj.a;
        e eVar = (e) this.b.get(qVar);
        if (eVar == null) {
            return 0L;
        }
        return eVar.a;
    }

    public final <T> T j(l<T> lVar) {
        int i2 = zj.a;
        g gVar = (g) this.b.get(lVar);
        if (gVar == null) {
            return null;
        }
        return gVar.a;
    }

    public final boolean k(h hVar) {
        int i2 = zj.a;
        a aVar = (a) this.b.get(hVar);
        if (aVar == null) {
            return false;
        }
        return aVar.a;
    }

    public final void l(n nVar, boolean z) {
        int i2 = zj.a;
        Map<v62, m> map = this.b;
        a aVar = (a) map.get(nVar);
        if (aVar == null) {
            aVar = new a();
            map.put(nVar, aVar);
        } else if (aVar.a == z) {
            return;
        }
        aVar.a = z;
        c(nVar);
    }

    public final void m(o oVar, float f2) {
        int i2 = zj.a;
        Map<v62, m> map = this.b;
        c cVar = (c) map.get(oVar);
        if (cVar == null) {
            cVar = new c();
            map.put(oVar, cVar);
        } else if (cVar.a == f2) {
            return;
        }
        cVar.a = f2;
        c(oVar);
    }

    public final void n(p pVar, int i2) {
        int i3 = zj.a;
        Map<v62, m> map = this.b;
        d dVar = (d) map.get(pVar);
        if (dVar == null) {
            dVar = new d();
            map.put(pVar, dVar);
        } else if (dVar.a == i2) {
            return;
        }
        dVar.a = i2;
        c(pVar);
    }

    public final void o(q qVar, long j2) {
        int i2 = zj.a;
        Map<v62, m> map = this.b;
        e eVar = (e) map.get(qVar);
        if (eVar == null) {
            eVar = new e();
            map.put(qVar, eVar);
        } else if (eVar.a == j2) {
            return;
        }
        eVar.a = j2;
        c(qVar);
    }

    public final <T> void p(r<T> rVar, T t) {
        int i2 = zj.a;
        Map<v62, m> map = this.b;
        g gVar = (g) map.get(rVar);
        if (gVar == null) {
            gVar = new g();
            map.put(rVar, gVar);
        } else if (!rVar.b && Objects.equals(gVar.a, t)) {
            return;
        }
        gVar.a = t;
        c(rVar);
    }

    public PropertyModel(List list) {
        this(e((v62[]) list.toArray(new v62[list.size()])));
    }

    public PropertyModel(Map<v62, m> map) {
        this.b = map;
    }
}
