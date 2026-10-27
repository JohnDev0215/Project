package q1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import q1.h;
import u1.n;

/* JADX INFO: compiled from: DecodeHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class g<Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n.a<?>> f8964a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<n1.f> f8965b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.e f8966c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f8967d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f8968e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f8969f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Class<?> f8970g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private h.e f8971h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private n1.i f8972i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Map<Class<?>, n1.m<?>> f8973j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Class<Transcode> f8974k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f8975l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f8976m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private n1.f f8977n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private com.bumptech.glide.g f8978o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private j f8979p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f8980q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f8981r;

    g() {
    }

    void a() {
        this.f8966c = null;
        this.f8967d = null;
        this.f8977n = null;
        this.f8970g = null;
        this.f8974k = null;
        this.f8972i = null;
        this.f8978o = null;
        this.f8973j = null;
        this.f8979p = null;
        this.f8964a.clear();
        this.f8975l = false;
        this.f8965b.clear();
        this.f8976m = false;
    }

    r1.b b() {
        return this.f8966c.b();
    }

    List<n1.f> c() {
        if (!this.f8976m) {
            this.f8976m = true;
            this.f8965b.clear();
            List<n.a<?>> listG = g();
            int size = listG.size();
            for (int i7 = 0; i7 < size; i7++) {
                n.a<?> aVar = listG.get(i7);
                if (!this.f8965b.contains(aVar.f9613a)) {
                    this.f8965b.add(aVar.f9613a);
                }
                for (int i8 = 0; i8 < aVar.f9614b.size(); i8++) {
                    if (!this.f8965b.contains(aVar.f9614b.get(i8))) {
                        this.f8965b.add(aVar.f9614b.get(i8));
                    }
                }
            }
        }
        return this.f8965b;
    }

    s1.a d() {
        return this.f8971h.a();
    }

    j e() {
        return this.f8979p;
    }

    int f() {
        return this.f8969f;
    }

    List<n.a<?>> g() {
        if (!this.f8975l) {
            this.f8975l = true;
            this.f8964a.clear();
            List listI = this.f8966c.h().i(this.f8967d);
            int size = listI.size();
            for (int i7 = 0; i7 < size; i7++) {
                n.a<?> aVarB = ((u1.n) listI.get(i7)).b(this.f8967d, this.f8968e, this.f8969f, this.f8972i);
                if (aVarB != null) {
                    this.f8964a.add(aVarB);
                }
            }
        }
        return this.f8964a;
    }

    <Data> t<Data, ?, Transcode> h(Class<Data> cls) {
        return this.f8966c.h().h(cls, this.f8970g, this.f8974k);
    }

    Class<?> i() {
        return this.f8967d.getClass();
    }

    List<u1.n<File, ?>> j(File file) {
        return this.f8966c.h().i(file);
    }

    n1.i k() {
        return this.f8972i;
    }

    com.bumptech.glide.g l() {
        return this.f8978o;
    }

    List<Class<?>> m() {
        return this.f8966c.h().j(this.f8967d.getClass(), this.f8970g, this.f8974k);
    }

    <Z> n1.l<Z> n(v<Z> vVar) {
        return this.f8966c.h().k(vVar);
    }

    n1.f o() {
        return this.f8977n;
    }

    <X> n1.d<X> p(X x6) {
        return this.f8966c.h().m(x6);
    }

    Class<?> q() {
        return this.f8974k;
    }

    <Z> n1.m<Z> r(Class<Z> cls) {
        n1.m<Z> mVar = (n1.m) this.f8973j.get(cls);
        if (mVar == null) {
            Iterator<Map.Entry<Class<?>, n1.m<?>>> it = this.f8973j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, n1.m<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    mVar = (n1.m) next.getValue();
                    break;
                }
            }
        }
        if (mVar != null) {
            return mVar;
        }
        if (!this.f8973j.isEmpty() || !this.f8980q) {
            return w1.c.c();
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    int s() {
        return this.f8968e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean t(Class<?> cls) {
        return h(cls) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    <R> void u(com.bumptech.glide.e eVar, Object obj, n1.f fVar, int i7, int i8, j jVar, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar, n1.i iVar, Map<Class<?>, n1.m<?>> map, boolean z6, boolean z7, h.e eVar2) {
        this.f8966c = eVar;
        this.f8967d = obj;
        this.f8977n = fVar;
        this.f8968e = i7;
        this.f8969f = i8;
        this.f8979p = jVar;
        this.f8970g = cls;
        this.f8971h = eVar2;
        this.f8974k = cls2;
        this.f8978o = gVar;
        this.f8972i = iVar;
        this.f8973j = map;
        this.f8980q = z6;
        this.f8981r = z7;
    }

    boolean v(v<?> vVar) {
        return this.f8966c.h().n(vVar);
    }

    boolean w() {
        return this.f8981r;
    }

    boolean x(n1.f fVar) {
        List<n.a<?>> listG = g();
        int size = listG.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (listG.get(i7).f9613a.equals(fVar)) {
                return true;
            }
        }
        return false;
    }
}
