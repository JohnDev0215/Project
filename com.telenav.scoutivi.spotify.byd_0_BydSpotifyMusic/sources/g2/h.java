package g2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import q1.k;
import q1.q;
import q1.v;

/* JADX INFO: compiled from: SingleRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<R> implements c, h2.g, g {
    private static final boolean D = Log.isLoggable("Request", 2);
    private int A;
    private boolean B;
    private RuntimeException C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l2.c f6493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f6494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final e<R> f6495d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f6496e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Context f6497f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final com.bumptech.glide.e f6498g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Object f6499h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Class<R> f6500i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final g2.a<?> f6501j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int f6502k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int f6503l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final com.bumptech.glide.g f6504m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final h2.h<R> f6505n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final List<e<R>> f6506o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final i2.e<? super R> f6507p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final Executor f6508q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private v<R> f6509r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private k.d f6510s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private long f6511t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private volatile k f6512u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private a f6513v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private Drawable f6514w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private Drawable f6515x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private Drawable f6516y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f6517z;

    /* JADX INFO: compiled from: SingleRequest.java */
    private enum a {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    private h(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class<R> cls, g2.a<?> aVar, int i7, int i8, com.bumptech.glide.g gVar, h2.h<R> hVar, e<R> eVar2, List<e<R>> list, d dVar, k kVar, i2.e<? super R> eVar3, Executor executor) {
        this.f6492a = D ? String.valueOf(super.hashCode()) : null;
        this.f6493b = l2.c.a();
        this.f6494c = obj;
        this.f6497f = context;
        this.f6498g = eVar;
        this.f6499h = obj2;
        this.f6500i = cls;
        this.f6501j = aVar;
        this.f6502k = i7;
        this.f6503l = i8;
        this.f6504m = gVar;
        this.f6505n = hVar;
        this.f6495d = eVar2;
        this.f6506o = list;
        this.f6496e = dVar;
        this.f6512u = kVar;
        this.f6507p = eVar3;
        this.f6508q = executor;
        this.f6513v = a.PENDING;
        if (this.C == null && eVar.i()) {
            this.C = new RuntimeException("Glide request origin trace");
        }
    }

    private void A() {
        if (l()) {
            Drawable drawableP = this.f6499h == null ? p() : null;
            if (drawableP == null) {
                drawableP = o();
            }
            if (drawableP == null) {
                drawableP = q();
            }
            this.f6505n.f(drawableP);
        }
    }

    private void j() {
        if (this.B) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    private boolean k() {
        d dVar = this.f6496e;
        return dVar == null || dVar.b(this);
    }

    private boolean l() {
        d dVar = this.f6496e;
        return dVar == null || dVar.f(this);
    }

    private boolean m() {
        d dVar = this.f6496e;
        return dVar == null || dVar.a(this);
    }

    private void n() {
        j();
        this.f6493b.c();
        this.f6505n.j(this);
        k.d dVar = this.f6510s;
        if (dVar != null) {
            dVar.a();
            this.f6510s = null;
        }
    }

    private Drawable o() {
        if (this.f6514w == null) {
            Drawable drawableJ = this.f6501j.j();
            this.f6514w = drawableJ;
            if (drawableJ == null && this.f6501j.i() > 0) {
                this.f6514w = s(this.f6501j.i());
            }
        }
        return this.f6514w;
    }

    private Drawable p() {
        if (this.f6516y == null) {
            Drawable drawableK = this.f6501j.k();
            this.f6516y = drawableK;
            if (drawableK == null && this.f6501j.l() > 0) {
                this.f6516y = s(this.f6501j.l());
            }
        }
        return this.f6516y;
    }

    private Drawable q() {
        if (this.f6515x == null) {
            Drawable drawableR = this.f6501j.r();
            this.f6515x = drawableR;
            if (drawableR == null && this.f6501j.s() > 0) {
                this.f6515x = s(this.f6501j.s());
            }
        }
        return this.f6515x;
    }

    private boolean r() {
        d dVar = this.f6496e;
        return dVar == null || !dVar.j();
    }

    private Drawable s(int i7) {
        return z1.a.a(this.f6498g, i7, this.f6501j.x() != null ? this.f6501j.x() : this.f6497f.getTheme());
    }

    private void t(String str) {
        Log.v("Request", str + " this: " + this.f6492a);
    }

    private static int u(int i7, float f7) {
        return i7 == Integer.MIN_VALUE ? i7 : Math.round(f7 * i7);
    }

    private void v() {
        d dVar = this.f6496e;
        if (dVar != null) {
            dVar.e(this);
        }
    }

    private void w() {
        d dVar = this.f6496e;
        if (dVar != null) {
            dVar.k(this);
        }
    }

    public static <R> h<R> x(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class<R> cls, g2.a<?> aVar, int i7, int i8, com.bumptech.glide.g gVar, h2.h<R> hVar, e<R> eVar2, List<e<R>> list, d dVar, k kVar, i2.e<? super R> eVar3, Executor executor) {
        return new h<>(context, eVar, obj, obj2, cls, aVar, i7, i8, gVar, hVar, eVar2, list, dVar, kVar, eVar3, executor);
    }

    private void y(q qVar, int i7) {
        boolean zB;
        this.f6493b.c();
        synchronized (this.f6494c) {
            qVar.k(this.C);
            int iG = this.f6498g.g();
            if (iG <= i7) {
                Log.w("Glide", "Load failed for " + this.f6499h + " with size [" + this.f6517z + "x" + this.A + "]", qVar);
                if (iG <= 4) {
                    qVar.g("Glide");
                }
            }
            this.f6510s = null;
            this.f6513v = a.FAILED;
            boolean z6 = true;
            this.B = true;
            try {
                List<e<R>> list = this.f6506o;
                if (list != null) {
                    Iterator<e<R>> it = list.iterator();
                    zB = false;
                    while (it.hasNext()) {
                        zB |= it.next().b(qVar, this.f6499h, this.f6505n, r());
                    }
                } else {
                    zB = false;
                }
                e<R> eVar = this.f6495d;
                if (eVar == null || !eVar.b(qVar, this.f6499h, this.f6505n, r())) {
                    z6 = false;
                }
                if (!(zB | z6)) {
                    A();
                }
                this.B = false;
                v();
            } catch (Throwable th) {
                this.B = false;
                throw th;
            }
        }
    }

    private void z(v<R> vVar, R r7, n1.a aVar) {
        boolean zA;
        boolean zR = r();
        this.f6513v = a.COMPLETE;
        this.f6509r = vVar;
        if (this.f6498g.g() <= 3) {
            Log.d("Glide", "Finished loading " + r7.getClass().getSimpleName() + " from " + aVar + " for " + this.f6499h + " with size [" + this.f6517z + "x" + this.A + "] in " + k2.f.a(this.f6511t) + " ms");
        }
        boolean z6 = true;
        this.B = true;
        try {
            List<e<R>> list = this.f6506o;
            if (list != null) {
                Iterator<e<R>> it = list.iterator();
                zA = false;
                while (it.hasNext()) {
                    zA |= it.next().a(r7, this.f6499h, this.f6505n, aVar, zR);
                }
            } else {
                zA = false;
            }
            e<R> eVar = this.f6495d;
            if (eVar == null || !eVar.a(r7, this.f6499h, this.f6505n, aVar, zR)) {
                z6 = false;
            }
            if (!(z6 | zA)) {
                this.f6505n.k(r7, this.f6507p.a(aVar, zR));
            }
            this.B = false;
            w();
        } catch (Throwable th) {
            this.B = false;
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // g2.g
    public void a(v<?> vVar, n1.a aVar) {
        this.f6493b.c();
        v<?> vVar2 = null;
        try {
            synchronized (this.f6494c) {
                try {
                    this.f6510s = null;
                    if (vVar == null) {
                        b(new q("Expected to receive a Resource<R> with an object of " + this.f6500i + " inside, but instead got null."));
                        return;
                    }
                    Object obj = vVar.get();
                    try {
                        if (obj != null && this.f6500i.isAssignableFrom(obj.getClass())) {
                            if (m()) {
                                z(vVar, obj, aVar);
                                return;
                            }
                            this.f6509r = null;
                            this.f6513v = a.COMPLETE;
                            this.f6512u.k(vVar);
                            return;
                        }
                        this.f6509r = null;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected to receive an object of ");
                        sb.append(this.f6500i);
                        sb.append(" but instead got ");
                        sb.append(obj != null ? obj.getClass() : "");
                        sb.append("{");
                        sb.append(obj);
                        sb.append("} inside Resource{");
                        sb.append(vVar);
                        sb.append("}.");
                        sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                        b(new q(sb.toString()));
                        this.f6512u.k(vVar);
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
                this.f6512u.k(vVar2);
            }
            throw th3;
        }
    }

    @Override // g2.g
    public void b(q qVar) {
        y(qVar, 5);
    }

    @Override // g2.c
    public void c() {
        synchronized (this.f6494c) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @Override // g2.c
    public void clear() {
        synchronized (this.f6494c) {
            j();
            this.f6493b.c();
            a aVar = this.f6513v;
            a aVar2 = a.CLEARED;
            if (aVar == aVar2) {
                return;
            }
            n();
            v<R> vVar = this.f6509r;
            if (vVar != null) {
                this.f6509r = null;
            } else {
                vVar = null;
            }
            if (k()) {
                this.f6505n.i(q());
            }
            this.f6513v = aVar2;
            if (vVar != null) {
                this.f6512u.k(vVar);
            }
        }
    }

    @Override // g2.c
    public void d() {
        synchronized (this.f6494c) {
            j();
            this.f6493b.c();
            this.f6511t = k2.f.b();
            if (this.f6499h == null) {
                if (k2.k.s(this.f6502k, this.f6503l)) {
                    this.f6517z = this.f6502k;
                    this.A = this.f6503l;
                }
                y(new q("Received null model"), p() == null ? 5 : 3);
                return;
            }
            a aVar = this.f6513v;
            a aVar2 = a.RUNNING;
            if (aVar == aVar2) {
                throw new IllegalArgumentException("Cannot restart a running request");
            }
            if (aVar == a.COMPLETE) {
                a(this.f6509r, n1.a.MEMORY_CACHE);
                return;
            }
            a aVar3 = a.WAITING_FOR_SIZE;
            this.f6513v = aVar3;
            if (k2.k.s(this.f6502k, this.f6503l)) {
                f(this.f6502k, this.f6503l);
            } else {
                this.f6505n.e(this);
            }
            a aVar4 = this.f6513v;
            if ((aVar4 == aVar2 || aVar4 == aVar3) && l()) {
                this.f6505n.d(q());
            }
            if (D) {
                t("finished run method in " + k2.f.a(this.f6511t));
            }
        }
    }

    @Override // g2.g
    public Object e() {
        this.f6493b.c();
        return this.f6494c;
    }

    @Override // h2.g
    public void f(int i7, int i8) throws Throwable {
        Object obj;
        this.f6493b.c();
        Object obj2 = this.f6494c;
        synchronized (obj2) {
            try {
                try {
                    boolean z6 = D;
                    if (z6) {
                        t("Got onSizeReady in " + k2.f.a(this.f6511t));
                    }
                    if (this.f6513v == a.WAITING_FOR_SIZE) {
                        a aVar = a.RUNNING;
                        this.f6513v = aVar;
                        float fW = this.f6501j.w();
                        this.f6517z = u(i7, fW);
                        this.A = u(i8, fW);
                        if (z6) {
                            t("finished setup for calling load in " + k2.f.a(this.f6511t));
                        }
                        obj = obj2;
                        try {
                            this.f6510s = this.f6512u.f(this.f6498g, this.f6499h, this.f6501j.v(), this.f6517z, this.A, this.f6501j.u(), this.f6500i, this.f6504m, this.f6501j.h(), this.f6501j.y(), this.f6501j.H(), this.f6501j.D(), this.f6501j.o(), this.f6501j.B(), this.f6501j.A(), this.f6501j.z(), this.f6501j.n(), this, this.f6508q);
                            if (this.f6513v != aVar) {
                                this.f6510s = null;
                            }
                            if (z6) {
                                t("finished onSizeReady in " + k2.f.a(this.f6511t));
                            }
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obj = obj2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // g2.c
    public boolean g(c cVar) {
        int i7;
        int i8;
        Object obj;
        Class<R> cls;
        g2.a<?> aVar;
        com.bumptech.glide.g gVar;
        int size;
        int i9;
        int i10;
        Object obj2;
        Class<R> cls2;
        g2.a<?> aVar2;
        com.bumptech.glide.g gVar2;
        int size2;
        if (!(cVar instanceof h)) {
            return false;
        }
        synchronized (this.f6494c) {
            i7 = this.f6502k;
            i8 = this.f6503l;
            obj = this.f6499h;
            cls = this.f6500i;
            aVar = this.f6501j;
            gVar = this.f6504m;
            List<e<R>> list = this.f6506o;
            size = list != null ? list.size() : 0;
        }
        h hVar = (h) cVar;
        synchronized (hVar.f6494c) {
            i9 = hVar.f6502k;
            i10 = hVar.f6503l;
            obj2 = hVar.f6499h;
            cls2 = hVar.f6500i;
            aVar2 = hVar.f6501j;
            gVar2 = hVar.f6504m;
            List<e<R>> list2 = hVar.f6506o;
            size2 = list2 != null ? list2.size() : 0;
        }
        return i7 == i9 && i8 == i10 && k2.k.b(obj, obj2) && cls.equals(cls2) && aVar.equals(aVar2) && gVar == gVar2 && size == size2;
    }

    @Override // g2.c
    public boolean h() {
        boolean z6;
        synchronized (this.f6494c) {
            z6 = this.f6513v == a.COMPLETE;
        }
        return z6;
    }

    @Override // g2.c
    public boolean i() {
        boolean z6;
        synchronized (this.f6494c) {
            z6 = this.f6513v == a.CLEARED;
        }
        return z6;
    }

    @Override // g2.c
    public boolean isRunning() {
        boolean z6;
        synchronized (this.f6494c) {
            a aVar = this.f6513v;
            z6 = aVar == a.RUNNING || aVar == a.WAITING_FOR_SIZE;
        }
        return z6;
    }
}
