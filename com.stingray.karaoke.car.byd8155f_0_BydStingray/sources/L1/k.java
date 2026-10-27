package l1;

import i1.C1401a;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o1.d f18438a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18439b;
    public int c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f18440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f18441f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f18442g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f18443h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f18444i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f18445j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f18446k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f18447m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f18448n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final HashMap f18449p;

    public k(o1.d dVar) {
        this.f18438a = null;
        this.f18439b = 0;
        this.c = 0;
        this.d = Float.NaN;
        this.f18440e = Float.NaN;
        this.f18441f = Float.NaN;
        this.f18442g = Float.NaN;
        this.f18443h = Float.NaN;
        this.f18444i = Float.NaN;
        this.f18445j = Float.NaN;
        this.f18446k = Float.NaN;
        this.l = Float.NaN;
        this.f18447m = Float.NaN;
        this.f18448n = Float.NaN;
        this.o = 0;
        this.f18449p = new HashMap();
        this.f18438a = dVar;
    }

    public final void a(k kVar) {
        if (kVar == null) {
            return;
        }
        this.d = kVar.d;
        this.f18440e = kVar.f18440e;
        this.f18441f = kVar.f18441f;
        this.f18442g = kVar.f18442g;
        this.f18443h = kVar.f18443h;
        this.f18444i = kVar.f18444i;
        this.f18445j = kVar.f18445j;
        this.f18446k = kVar.f18446k;
        this.l = kVar.l;
        this.f18447m = kVar.f18447m;
        this.f18448n = kVar.f18448n;
        this.o = kVar.o;
        HashMap map = this.f18449p;
        map.clear();
        for (C1401a c1401a : kVar.f18449p.values()) {
            String str = c1401a.f15666a;
            C1401a c1401a2 = new C1401a();
            c1401a2.c = Integer.MIN_VALUE;
            c1401a2.d = Float.NaN;
            c1401a2.f15666a = str;
            c1401a2.f15667b = c1401a.f15667b;
            c1401a2.c = c1401a.c;
            c1401a2.d = c1401a.d;
            map.put(str, c1401a2);
        }
    }

    public k(k kVar) {
        this.f18438a = null;
        this.f18439b = 0;
        this.c = 0;
        this.d = Float.NaN;
        this.f18440e = Float.NaN;
        this.f18441f = Float.NaN;
        this.f18442g = Float.NaN;
        this.f18443h = Float.NaN;
        this.f18444i = Float.NaN;
        this.f18445j = Float.NaN;
        this.f18446k = Float.NaN;
        this.l = Float.NaN;
        this.f18447m = Float.NaN;
        this.f18448n = Float.NaN;
        this.o = 0;
        this.f18449p = new HashMap();
        this.f18438a = kVar.f18438a;
        this.f18439b = kVar.f18439b;
        this.c = kVar.c;
        a(kVar);
    }
}
