package com.bumptech.glide.r;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.load.n.k;
import com.bumptech.glide.load.n.q;
import com.bumptech.glide.load.n.v;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class h<R> implements c, com.bumptech.glide.r.j.d, g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f2505a = Log.isLoggable("Request", 2);
    private int A;
    private int B;
    private boolean C;
    private RuntimeException D;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.t.l.c f2507c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Object f2508d;
    private final e<R> e;
    private final d f;
    private final Context g;
    private final com.bumptech.glide.e h;
    private final Object i;
    private final Class<R> j;
    private final com.bumptech.glide.r.a<?> k;
    private final int l;
    private final int m;
    private final com.bumptech.glide.g n;
    private final com.bumptech.glide.r.j.e<R> o;
    private final List<e<R>> p;
    private final com.bumptech.glide.r.k.c<? super R> q;
    private final Executor r;
    private v<R> s;
    private k.d t;
    private long u;
    private volatile k v;
    private a w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    private enum a {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    private h(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class<R> cls, com.bumptech.glide.r.a<?> aVar, int i, int i2, com.bumptech.glide.g gVar, com.bumptech.glide.r.j.e<R> eVar2, e<R> eVar3, List<e<R>> list, d dVar, k kVar, com.bumptech.glide.r.k.c<? super R> cVar, Executor executor) {
        this.f2506b = f2505a ? String.valueOf(super.hashCode()) : null;
        this.f2507c = com.bumptech.glide.t.l.c.a();
        this.f2508d = obj;
        this.g = context;
        this.h = eVar;
        this.i = obj2;
        this.j = cls;
        this.k = aVar;
        this.l = i;
        this.m = i2;
        this.n = gVar;
        this.o = eVar2;
        this.e = eVar3;
        this.p = list;
        this.f = dVar;
        this.v = kVar;
        this.q = cVar;
        this.r = executor;
        this.w = a.PENDING;
        if (this.D == null && eVar.h()) {
            this.D = new RuntimeException("Glide request origin trace");
        }
    }

    private void A(v<R> vVar, R r, com.bumptech.glide.load.a aVar) {
        boolean zA;
        boolean zS = s();
        this.w = a.COMPLETE;
        this.s = vVar;
        if (this.h.f() <= 3) {
            Log.d("Glide", "Finished loading " + r.getClass().getSimpleName() + " from " + aVar + " for " + this.i + " with size [" + this.A + "x" + this.B + "] in " + com.bumptech.glide.t.f.a(this.u) + " ms");
        }
        boolean z = true;
        this.C = true;
        try {
            List<e<R>> list = this.p;
            if (list != null) {
                Iterator<e<R>> it = list.iterator();
                zA = false;
                while (it.hasNext()) {
                    zA |= it.next().a(r, this.i, this.o, aVar, zS);
                }
            } else {
                zA = false;
            }
            e<R> eVar = this.e;
            if (eVar == null || !eVar.a(r, this.i, this.o, aVar, zS)) {
                z = false;
            }
            if (!(z | zA)) {
                this.o.d(r, this.q.a(aVar, zS));
            }
            this.C = false;
            x();
        } catch (Throwable th) {
            this.C = false;
            throw th;
        }
    }

    private void B() {
        if (m()) {
            Drawable drawableQ = this.i == null ? q() : null;
            if (drawableQ == null) {
                drawableQ = p();
            }
            if (drawableQ == null) {
                drawableQ = r();
            }
            this.o.e(drawableQ);
        }
    }

    private void k() {
        if (this.C) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    private boolean l() {
        d dVar = this.f;
        return dVar == null || dVar.l(this);
    }

    private boolean m() {
        d dVar = this.f;
        return dVar == null || dVar.k(this);
    }

    private boolean n() {
        d dVar = this.f;
        return dVar == null || dVar.a(this);
    }

    private void o() {
        k();
        this.f2507c.c();
        this.o.b(this);
        k.d dVar = this.t;
        if (dVar != null) {
            dVar.a();
            this.t = null;
        }
    }

    private Drawable p() {
        if (this.x == null) {
            Drawable drawableJ = this.k.j();
            this.x = drawableJ;
            if (drawableJ == null && this.k.i() > 0) {
                this.x = t(this.k.i());
            }
        }
        return this.x;
    }

    private Drawable q() {
        if (this.z == null) {
            Drawable drawableK = this.k.k();
            this.z = drawableK;
            if (drawableK == null && this.k.l() > 0) {
                this.z = t(this.k.l());
            }
        }
        return this.z;
    }

    private Drawable r() {
        if (this.y == null) {
            Drawable drawableQ = this.k.q();
            this.y = drawableQ;
            if (drawableQ == null && this.k.r() > 0) {
                this.y = t(this.k.r());
            }
        }
        return this.y;
    }

    private boolean s() {
        d dVar = this.f;
        return dVar == null || !dVar.c().b();
    }

    private Drawable t(int i) {
        return com.bumptech.glide.load.p.f.a.a(this.h, i, this.k.w() != null ? this.k.w() : this.g.getTheme());
    }

    private void u(String str) {
        Log.v("Request", str + " this: " + this.f2506b);
    }

    private static int v(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    private void w() {
        d dVar = this.f;
        if (dVar != null) {
            dVar.d(this);
        }
    }

    private void x() {
        d dVar = this.f;
        if (dVar != null) {
            dVar.g(this);
        }
    }

    public static <R> h<R> y(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class<R> cls, com.bumptech.glide.r.a<?> aVar, int i, int i2, com.bumptech.glide.g gVar, com.bumptech.glide.r.j.e<R> eVar2, e<R> eVar3, List<e<R>> list, d dVar, k kVar, com.bumptech.glide.r.k.c<? super R> cVar, Executor executor) {
        return new h<>(context, eVar, obj, obj2, cls, aVar, i, i2, gVar, eVar2, eVar3, list, dVar, kVar, cVar, executor);
    }

    private void z(q qVar, int i) {
        boolean zB;
        this.f2507c.c();
        synchronized (this.f2508d) {
            qVar.k(this.D);
            int iF = this.h.f();
            if (iF <= i) {
                Log.w("Glide", "Load failed for " + this.i + " with size [" + this.A + "x" + this.B + "]", qVar);
                if (iF <= 4) {
                    qVar.g("Glide");
                }
            }
            this.t = null;
            this.w = a.FAILED;
            boolean z = true;
            this.C = true;
            try {
                List<e<R>> list = this.p;
                if (list != null) {
                    Iterator<e<R>> it = list.iterator();
                    zB = false;
                    while (it.hasNext()) {
                        zB |= it.next().b(qVar, this.i, this.o, s());
                    }
                } else {
                    zB = false;
                }
                e<R> eVar = this.e;
                if (eVar == null || !eVar.b(qVar, this.i, this.o, s())) {
                    z = false;
                }
                if (!(zB | z)) {
                    B();
                }
                this.C = false;
                w();
            } catch (Throwable th) {
                this.C = false;
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.r.g
    public void a(q qVar) {
        z(qVar, 5);
    }

    @Override // com.bumptech.glide.r.c
    public boolean b() {
        boolean z;
        synchronized (this.f2508d) {
            z = this.w == a.COMPLETE;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.r.g
    public void c(v<?> vVar, com.bumptech.glide.load.a aVar) {
        this.f2507c.c();
        v<?> vVar2 = null;
        try {
            synchronized (this.f2508d) {
                try {
                    this.t = null;
                    if (vVar == null) {
                        a(new q("Expected to receive a Resource<R> with an object of " + this.j + " inside, but instead got null."));
                        return;
                    }
                    Object objB = vVar.b();
                    try {
                        if (objB != null && this.j.isAssignableFrom(objB.getClass())) {
                            if (n()) {
                                A(vVar, objB, aVar);
                                return;
                            }
                            this.s = null;
                            this.w = a.COMPLETE;
                            this.v.k(vVar);
                            return;
                        }
                        this.s = null;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected to receive an object of ");
                        sb.append(this.j);
                        sb.append(" but instead got ");
                        sb.append(objB != null ? objB.getClass() : "");
                        sb.append("{");
                        sb.append(objB);
                        sb.append("} inside Resource{");
                        sb.append(vVar);
                        sb.append("}.");
                        sb.append(objB != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                        a(new q(sb.toString()));
                        this.v.k(vVar);
                    } catch (Throwable th) {
                        vVar2 = vVar;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (vVar2 != null) {
                this.v.k(vVar2);
            }
            throw th3;
        }
    }

    @Override // com.bumptech.glide.r.c
    public void clear() {
        synchronized (this.f2508d) {
            k();
            this.f2507c.c();
            a aVar = this.w;
            a aVar2 = a.CLEARED;
            if (aVar == aVar2) {
                return;
            }
            o();
            v<R> vVar = this.s;
            if (vVar != null) {
                this.s = null;
            } else {
                vVar = null;
            }
            if (l()) {
                this.o.h(r());
            }
            this.w = aVar2;
            if (vVar != null) {
                this.v.k(vVar);
            }
        }
    }

    @Override // com.bumptech.glide.r.g
    public Object d() {
        this.f2507c.c();
        return this.f2508d;
    }

    @Override // com.bumptech.glide.r.c
    public void e() {
        synchronized (this.f2508d) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public void f() {
        synchronized (this.f2508d) {
            k();
            this.f2507c.c();
            this.u = com.bumptech.glide.t.f.b();
            if (this.i == null) {
                if (com.bumptech.glide.t.k.r(this.l, this.m)) {
                    this.A = this.l;
                    this.B = this.m;
                }
                z(new q("Received null model"), q() == null ? 5 : 3);
                return;
            }
            a aVar = this.w;
            a aVar2 = a.RUNNING;
            if (aVar == aVar2) {
                throw new IllegalArgumentException("Cannot restart a running request");
            }
            if (aVar == a.COMPLETE) {
                c(this.s, com.bumptech.glide.load.a.MEMORY_CACHE);
                return;
            }
            a aVar3 = a.WAITING_FOR_SIZE;
            this.w = aVar3;
            if (com.bumptech.glide.t.k.r(this.l, this.m)) {
                g(this.l, this.m);
            } else {
                this.o.i(this);
            }
            a aVar4 = this.w;
            if ((aVar4 == aVar2 || aVar4 == aVar3) && m()) {
                this.o.c(r());
            }
            if (f2505a) {
                u("finished run method in " + com.bumptech.glide.t.f.a(this.u));
            }
        }
    }

    @Override // com.bumptech.glide.r.j.d
    public void g(int i, int i2) throws Throwable {
        Object obj;
        this.f2507c.c();
        Object obj2 = this.f2508d;
        synchronized (obj2) {
            try {
                try {
                    boolean z = f2505a;
                    if (z) {
                        u("Got onSizeReady in " + com.bumptech.glide.t.f.a(this.u));
                    }
                    if (this.w == a.WAITING_FOR_SIZE) {
                        a aVar = a.RUNNING;
                        this.w = aVar;
                        float fV = this.k.v();
                        this.A = v(i, fV);
                        this.B = v(i2, fV);
                        if (z) {
                            u("finished setup for calling load in " + com.bumptech.glide.t.f.a(this.u));
                        }
                        obj = obj2;
                        try {
                            this.t = this.v.f(this.h, this.i, this.k.u(), this.A, this.B, this.k.t(), this.j, this.n, this.k.h(), this.k.x(), this.k.F(), this.k.C(), this.k.n(), this.k.A(), this.k.z(), this.k.y(), this.k.m(), this, this.r);
                            if (this.w != aVar) {
                                this.t = null;
                            }
                            if (z) {
                                u("finished onSizeReady in " + com.bumptech.glide.t.f.a(this.u));
                            }
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                obj = obj2;
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public boolean h(c cVar) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        com.bumptech.glide.r.a<?> aVar;
        com.bumptech.glide.g gVar;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        com.bumptech.glide.r.a<?> aVar2;
        com.bumptech.glide.g gVar2;
        int size2;
        if (!(cVar instanceof h)) {
            return false;
        }
        synchronized (this.f2508d) {
            i = this.l;
            i2 = this.m;
            obj = this.i;
            cls = this.j;
            aVar = this.k;
            gVar = this.n;
            List<e<R>> list = this.p;
            size = list != null ? list.size() : 0;
        }
        h hVar = (h) cVar;
        synchronized (hVar.f2508d) {
            i3 = hVar.l;
            i4 = hVar.m;
            obj2 = hVar.i;
            cls2 = hVar.j;
            aVar2 = hVar.k;
            gVar2 = hVar.n;
            List<e<R>> list2 = hVar.p;
            size2 = list2 != null ? list2.size() : 0;
        }
        return i == i3 && i2 == i4 && com.bumptech.glide.t.k.b(obj, obj2) && cls.equals(cls2) && aVar.equals(aVar2) && gVar == gVar2 && size == size2;
    }

    @Override // com.bumptech.glide.r.c
    public boolean i() {
        boolean z;
        synchronized (this.f2508d) {
            z = this.w == a.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f2508d) {
            a aVar = this.w;
            z = aVar == a.RUNNING || aVar == a.WAITING_FOR_SIZE;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.c
    public boolean j() {
        boolean z;
        synchronized (this.f2508d) {
            z = this.w == a.CLEARED;
        }
        return z;
    }
}
