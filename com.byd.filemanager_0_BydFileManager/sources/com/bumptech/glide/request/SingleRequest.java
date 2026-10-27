package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.o.k;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class SingleRequest<R> implements c, com.bumptech.glide.request.h.d, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3477a = Log.isLoggable("Request", 2);
    private int A;
    private int B;
    private boolean C;
    private RuntimeException D;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.o.l.c f3479c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Object f3480d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d<R> f3481e;
    private final RequestCoordinator f;
    private final Context g;
    private final com.bumptech.glide.e h;
    private final Object i;
    private final Class<R> j;
    private final a<?> k;
    private final int l;
    private final int m;
    private final Priority n;
    private final com.bumptech.glide.request.h.e<R> o;
    private final List<d<R>> p;
    private final com.bumptech.glide.request.i.c<? super R> q;
    private final Executor r;
    private s<R> s;
    private i.d t;
    private long u;
    private volatile i v;
    private Status w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    private SingleRequest(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class<R> cls, a<?> aVar, int i, int i2, Priority priority, com.bumptech.glide.request.h.e<R> eVar2, d<R> dVar, List<d<R>> list, RequestCoordinator requestCoordinator, i iVar, com.bumptech.glide.request.i.c<? super R> cVar, Executor executor) {
        this.f3478b = f3477a ? String.valueOf(super.hashCode()) : null;
        this.f3479c = com.bumptech.glide.o.l.c.a();
        this.f3480d = obj;
        this.g = context;
        this.h = eVar;
        this.i = obj2;
        this.j = cls;
        this.k = aVar;
        this.l = i;
        this.m = i2;
        this.n = priority;
        this.o = eVar2;
        this.f3481e = dVar;
        this.p = list;
        this.f = requestCoordinator;
        this.v = iVar;
        this.q = cVar;
        this.r = executor;
        this.w = Status.PENDING;
        if (this.D == null && eVar.h()) {
            this.D = new RuntimeException("Glide request origin trace");
        }
    }

    private void A(s<R> sVar, R r, DataSource dataSource) {
        boolean zA;
        boolean zS = s();
        this.w = Status.COMPLETE;
        this.s = sVar;
        if (this.h.f() <= 3) {
            Log.d("Glide", "Finished loading " + r.getClass().getSimpleName() + " from " + dataSource + " for " + this.i + " with size [" + this.A + "x" + this.B + "] in " + com.bumptech.glide.o.f.a(this.u) + " ms");
        }
        boolean z = true;
        this.C = true;
        try {
            List<d<R>> list = this.p;
            if (list != null) {
                Iterator<d<R>> it = list.iterator();
                zA = false;
                while (it.hasNext()) {
                    zA |= it.next().a(r, this.i, this.o, dataSource, zS);
                }
            } else {
                zA = false;
            }
            d<R> dVar = this.f3481e;
            if (dVar == null || !dVar.a(r, this.i, this.o, dataSource, zS)) {
                z = false;
            }
            if (!(z | zA)) {
                this.o.d(r, this.q.a(dataSource, zS));
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
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.l(this);
    }

    private boolean m() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.k(this);
    }

    private boolean n() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.a(this);
    }

    private void o() {
        k();
        this.f3479c.c();
        this.o.b(this);
        i.d dVar = this.t;
        if (dVar != null) {
            dVar.a();
            this.t = null;
        }
    }

    private Drawable p() {
        if (this.x == null) {
            Drawable drawableN = this.k.n();
            this.x = drawableN;
            if (drawableN == null && this.k.m() > 0) {
                this.x = t(this.k.m());
            }
        }
        return this.x;
    }

    private Drawable q() {
        if (this.z == null) {
            Drawable drawableO = this.k.o();
            this.z = drawableO;
            if (drawableO == null && this.k.p() > 0) {
                this.z = t(this.k.p());
            }
        }
        return this.z;
    }

    private Drawable r() {
        if (this.y == null) {
            Drawable drawableU = this.k.u();
            this.y = drawableU;
            if (drawableU == null && this.k.v() > 0) {
                this.y = t(this.k.v());
            }
        }
        return this.y;
    }

    private boolean s() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || !requestCoordinator.c().b();
    }

    private Drawable t(int i) {
        return com.bumptech.glide.load.j.e.a.a(this.h, i, this.k.A() != null ? this.k.A() : this.g.getTheme());
    }

    private void u(String str) {
        Log.v("Request", str + " this: " + this.f3478b);
    }

    private static int v(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    private void w() {
        RequestCoordinator requestCoordinator = this.f;
        if (requestCoordinator != null) {
            requestCoordinator.d(this);
        }
    }

    private void x() {
        RequestCoordinator requestCoordinator = this.f;
        if (requestCoordinator != null) {
            requestCoordinator.g(this);
        }
    }

    public static <R> SingleRequest<R> y(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class<R> cls, a<?> aVar, int i, int i2, Priority priority, com.bumptech.glide.request.h.e<R> eVar2, d<R> dVar, List<d<R>> list, RequestCoordinator requestCoordinator, i iVar, com.bumptech.glide.request.i.c<? super R> cVar, Executor executor) {
        return new SingleRequest<>(context, eVar, obj, obj2, cls, aVar, i, i2, priority, eVar2, dVar, list, requestCoordinator, iVar, cVar, executor);
    }

    private void z(GlideException glideException, int i) {
        boolean zB;
        this.f3479c.c();
        synchronized (this.f3480d) {
            glideException.setOrigin(this.D);
            int iF = this.h.f();
            if (iF <= i) {
                Log.w("Glide", "Load failed for " + this.i + " with size [" + this.A + "x" + this.B + "]", glideException);
                if (iF <= 4) {
                    glideException.logRootCauses("Glide");
                }
            }
            this.t = null;
            this.w = Status.FAILED;
            boolean z = true;
            this.C = true;
            try {
                List<d<R>> list = this.p;
                if (list != null) {
                    Iterator<d<R>> it = list.iterator();
                    zB = false;
                    while (it.hasNext()) {
                        zB |= it.next().b(glideException, this.i, this.o, s());
                    }
                } else {
                    zB = false;
                }
                d<R> dVar = this.f3481e;
                if (dVar == null || !dVar.b(glideException, this.i, this.o, s())) {
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

    @Override // com.bumptech.glide.request.f
    public void a(GlideException glideException) {
        z(glideException, 5);
    }

    @Override // com.bumptech.glide.request.c
    public boolean b() {
        boolean z;
        synchronized (this.f3480d) {
            z = this.w == Status.COMPLETE;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.f
    public void c(s<?> sVar, DataSource dataSource) {
        this.f3479c.c();
        s<?> sVar2 = null;
        try {
            synchronized (this.f3480d) {
                try {
                    this.t = null;
                    if (sVar == null) {
                        a(new GlideException("Expected to receive a Resource<R> with an object of " + this.j + " inside, but instead got null."));
                        return;
                    }
                    Object obj = sVar.get();
                    try {
                        if (obj != null && this.j.isAssignableFrom(obj.getClass())) {
                            if (n()) {
                                A(sVar, obj, dataSource);
                                return;
                            }
                            this.s = null;
                            this.w = Status.COMPLETE;
                            this.v.k(sVar);
                            return;
                        }
                        this.s = null;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected to receive an object of ");
                        sb.append(this.j);
                        sb.append(" but instead got ");
                        sb.append(obj != null ? obj.getClass() : "");
                        sb.append("{");
                        sb.append(obj);
                        sb.append("} inside Resource{");
                        sb.append(sVar);
                        sb.append("}.");
                        sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                        a(new GlideException(sb.toString()));
                        this.v.k(sVar);
                    } catch (Throwable th) {
                        sVar2 = sVar;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (sVar2 != null) {
                this.v.k(sVar2);
            }
            throw th3;
        }
    }

    @Override // com.bumptech.glide.request.c
    public void clear() {
        synchronized (this.f3480d) {
            k();
            this.f3479c.c();
            Status status = this.w;
            Status status2 = Status.CLEARED;
            if (status == status2) {
                return;
            }
            o();
            s<R> sVar = this.s;
            if (sVar != null) {
                this.s = null;
            } else {
                sVar = null;
            }
            if (l()) {
                this.o.h(r());
            }
            this.w = status2;
            if (sVar != null) {
                this.v.k(sVar);
            }
        }
    }

    @Override // com.bumptech.glide.request.f
    public Object d() {
        this.f3479c.c();
        return this.f3480d;
    }

    @Override // com.bumptech.glide.request.c
    public void e() {
        synchronized (this.f3480d) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public void f() {
        synchronized (this.f3480d) {
            k();
            this.f3479c.c();
            this.u = com.bumptech.glide.o.f.b();
            if (this.i == null) {
                if (k.r(this.l, this.m)) {
                    this.A = this.l;
                    this.B = this.m;
                }
                z(new GlideException("Received null model"), q() == null ? 5 : 3);
                return;
            }
            Status status = this.w;
            Status status2 = Status.RUNNING;
            if (status == status2) {
                throw new IllegalArgumentException("Cannot restart a running request");
            }
            if (status == Status.COMPLETE) {
                c(this.s, DataSource.MEMORY_CACHE);
                return;
            }
            Status status3 = Status.WAITING_FOR_SIZE;
            this.w = status3;
            if (k.r(this.l, this.m)) {
                g(this.l, this.m);
            } else {
                this.o.i(this);
            }
            Status status4 = this.w;
            if ((status4 == status2 || status4 == status3) && m()) {
                this.o.c(r());
            }
            if (f3477a) {
                u("finished run method in " + com.bumptech.glide.o.f.a(this.u));
            }
        }
    }

    @Override // com.bumptech.glide.request.h.d
    public void g(int i, int i2) throws Throwable {
        Object obj;
        this.f3479c.c();
        Object obj2 = this.f3480d;
        synchronized (obj2) {
            try {
                try {
                    boolean z = f3477a;
                    if (z) {
                        u("Got onSizeReady in " + com.bumptech.glide.o.f.a(this.u));
                    }
                    if (this.w == Status.WAITING_FOR_SIZE) {
                        Status status = Status.RUNNING;
                        this.w = status;
                        float fZ = this.k.z();
                        this.A = v(i, fZ);
                        this.B = v(i2, fZ);
                        if (z) {
                            u("finished setup for calling load in " + com.bumptech.glide.o.f.a(this.u));
                        }
                        obj = obj2;
                        try {
                            this.t = this.v.f(this.h, this.i, this.k.y(), this.A, this.B, this.k.x(), this.j, this.n, this.k.l(), this.k.B(), this.k.J(), this.k.G(), this.k.r(), this.k.E(), this.k.D(), this.k.C(), this.k.q(), this, this.r);
                            if (this.w != status) {
                                this.t = null;
                            }
                            if (z) {
                                u("finished onSizeReady in " + com.bumptech.glide.o.f.a(this.u));
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

    @Override // com.bumptech.glide.request.c
    public boolean h(c cVar) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        a<?> aVar;
        Priority priority;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        a<?> aVar2;
        Priority priority2;
        int size2;
        if (!(cVar instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.f3480d) {
            i = this.l;
            i2 = this.m;
            obj = this.i;
            cls = this.j;
            aVar = this.k;
            priority = this.n;
            List<d<R>> list = this.p;
            size = list != null ? list.size() : 0;
        }
        SingleRequest singleRequest = (SingleRequest) cVar;
        synchronized (singleRequest.f3480d) {
            i3 = singleRequest.l;
            i4 = singleRequest.m;
            obj2 = singleRequest.i;
            cls2 = singleRequest.j;
            aVar2 = singleRequest.k;
            priority2 = singleRequest.n;
            List<d<R>> list2 = singleRequest.p;
            size2 = list2 != null ? list2.size() : 0;
        }
        return i == i3 && i2 == i4 && k.b(obj, obj2) && cls.equals(cls2) && aVar.equals(aVar2) && priority == priority2 && size == size2;
    }

    @Override // com.bumptech.glide.request.c
    public boolean i() {
        boolean z;
        synchronized (this.f3480d) {
            z = this.w == Status.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3480d) {
            Status status = this.w;
            z = status == Status.RUNNING || status == Status.WAITING_FOR_SIZE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean j() {
        boolean z;
        synchronized (this.f3480d) {
            z = this.w == Status.CLEARED;
        }
        return z;
    }
}
