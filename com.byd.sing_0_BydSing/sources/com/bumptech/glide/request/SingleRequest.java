package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.volley.BuildConfig;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.m.k;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class SingleRequest<R> implements c, com.bumptech.glide.request.h.c, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3215a = Log.isLoggable("Request", 2);
    private int A;
    private int B;
    private boolean C;
    private RuntimeException D;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.m.l.c f3217c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Object f3218d;
    private final d<R> e;
    private final RequestCoordinator f;
    private final Context g;
    private final com.bumptech.glide.d h;
    private final Object i;
    private final Class<R> j;
    private final a<?> k;
    private final int l;
    private final int m;
    private final Priority n;
    private final com.bumptech.glide.request.h.d<R> o;
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

    private SingleRequest(Context context, com.bumptech.glide.d dVar, Object obj, Object obj2, Class<R> cls, a<?> aVar, int i, int i2, Priority priority, com.bumptech.glide.request.h.d<R> dVar2, d<R> dVar3, List<d<R>> list, RequestCoordinator requestCoordinator, i iVar, com.bumptech.glide.request.i.c<? super R> cVar, Executor executor) {
        this.f3216b = f3215a ? String.valueOf(super.hashCode()) : null;
        this.f3217c = com.bumptech.glide.m.l.c.a();
        this.f3218d = obj;
        this.g = context;
        this.h = dVar;
        this.i = obj2;
        this.j = cls;
        this.k = aVar;
        this.l = i;
        this.m = i2;
        this.n = priority;
        this.o = dVar2;
        this.e = dVar3;
        this.p = list;
        this.f = requestCoordinator;
        this.v = iVar;
        this.q = cVar;
        this.r = executor;
        this.w = Status.PENDING;
        if (this.D == null && dVar.h()) {
            this.D = new RuntimeException("Glide request origin trace");
        }
    }

    private void A() {
        if (l()) {
            Drawable drawableP = this.i == null ? p() : null;
            if (drawableP == null) {
                drawableP = o();
            }
            if (drawableP == null) {
                drawableP = q();
            }
            this.o.d(drawableP);
        }
    }

    private void i() {
        if (this.C) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    private boolean k() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.k(this);
    }

    private boolean l() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.c(this);
    }

    private boolean m() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.f(this);
    }

    private void n() {
        i();
        this.f3217c.c();
        this.o.b(this);
        i.d dVar = this.t;
        if (dVar != null) {
            dVar.a();
            this.t = null;
        }
    }

    private Drawable o() {
        if (this.x == null) {
            Drawable drawableH = this.k.h();
            this.x = drawableH;
            if (drawableH == null && this.k.g() > 0) {
                this.x = s(this.k.g());
            }
        }
        return this.x;
    }

    private Drawable p() {
        if (this.z == null) {
            Drawable drawableI = this.k.i();
            this.z = drawableI;
            if (drawableI == null && this.k.j() > 0) {
                this.z = s(this.k.j());
            }
        }
        return this.z;
    }

    private Drawable q() {
        if (this.y == null) {
            Drawable drawableO = this.k.o();
            this.y = drawableO;
            if (drawableO == null && this.k.p() > 0) {
                this.y = s(this.k.p());
            }
        }
        return this.y;
    }

    private boolean r() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || !requestCoordinator.g().b();
    }

    private Drawable s(int i) {
        return com.bumptech.glide.load.k.e.a.a(this.h, i, this.k.u() != null ? this.k.u() : this.g.getTheme());
    }

    private void t(String str) {
        Log.v("Request", str + " this: " + this.f3216b);
    }

    private static int u(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    private void v() {
        RequestCoordinator requestCoordinator = this.f;
        if (requestCoordinator != null) {
            requestCoordinator.a(this);
        }
    }

    private void w() {
        RequestCoordinator requestCoordinator = this.f;
        if (requestCoordinator != null) {
            requestCoordinator.i(this);
        }
    }

    public static <R> SingleRequest<R> x(Context context, com.bumptech.glide.d dVar, Object obj, Object obj2, Class<R> cls, a<?> aVar, int i, int i2, Priority priority, com.bumptech.glide.request.h.d<R> dVar2, d<R> dVar3, List<d<R>> list, RequestCoordinator requestCoordinator, i iVar, com.bumptech.glide.request.i.c<? super R> cVar, Executor executor) {
        return new SingleRequest<>(context, dVar, obj, obj2, cls, aVar, i, i2, priority, dVar2, dVar3, list, requestCoordinator, iVar, cVar, executor);
    }

    private void y(GlideException glideException, int i) {
        boolean zB;
        this.f3217c.c();
        synchronized (this.f3218d) {
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
                        zB |= it.next().b(glideException, this.i, this.o, r());
                    }
                } else {
                    zB = false;
                }
                d<R> dVar = this.e;
                if (dVar == null || !dVar.b(glideException, this.i, this.o, r())) {
                    z = false;
                }
                if (!(zB | z)) {
                    A();
                }
                this.C = false;
                v();
            } catch (Throwable th) {
                this.C = false;
                throw th;
            }
        }
    }

    private void z(s<R> sVar, R r, DataSource dataSource) {
        boolean zA;
        boolean zR = r();
        this.w = Status.COMPLETE;
        this.s = sVar;
        if (this.h.f() <= 3) {
            Log.d("Glide", "Finished loading " + r.getClass().getSimpleName() + " from " + dataSource + " for " + this.i + " with size [" + this.A + "x" + this.B + "] in " + com.bumptech.glide.m.f.a(this.u) + " ms");
        }
        boolean z = true;
        this.C = true;
        try {
            List<d<R>> list = this.p;
            if (list != null) {
                Iterator<d<R>> it = list.iterator();
                zA = false;
                while (it.hasNext()) {
                    zA |= it.next().a(r, this.i, this.o, dataSource, zR);
                }
            } else {
                zA = false;
            }
            d<R> dVar = this.e;
            if (dVar == null || !dVar.a(r, this.i, this.o, dataSource, zR)) {
                z = false;
            }
            if (!(z | zA)) {
                this.o.c(r, this.q.a(dataSource, zR));
            }
            this.C = false;
            w();
        } catch (Throwable th) {
            this.C = false;
            throw th;
        }
    }

    @Override // com.bumptech.glide.request.f
    public void a(GlideException glideException) {
        y(glideException, 5);
    }

    @Override // com.bumptech.glide.request.c
    public boolean b() {
        boolean z;
        synchronized (this.f3218d) {
            z = this.w == Status.COMPLETE;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.f
    public void c(s<?> sVar, DataSource dataSource) {
        this.f3217c.c();
        s<?> sVar2 = null;
        try {
            synchronized (this.f3218d) {
                try {
                    this.t = null;
                    if (sVar == null) {
                        a(new GlideException("Expected to receive a Resource<R> with an object of " + this.j + " inside, but instead got null."));
                        return;
                    }
                    Object obj = sVar.get();
                    try {
                        if (obj != null && this.j.isAssignableFrom(obj.getClass())) {
                            if (m()) {
                                z(sVar, obj, dataSource);
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
                        sb.append(obj != null ? obj.getClass() : BuildConfig.FLAVOR);
                        sb.append("{");
                        sb.append(obj);
                        sb.append("} inside Resource{");
                        sb.append(sVar);
                        sb.append("}.");
                        sb.append(obj != null ? BuildConfig.FLAVOR : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
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
        synchronized (this.f3218d) {
            i();
            this.f3217c.c();
            Status status = this.w;
            Status status2 = Status.CLEARED;
            if (status == status2) {
                return;
            }
            n();
            s<R> sVar = this.s;
            if (sVar != null) {
                this.s = null;
            } else {
                sVar = null;
            }
            if (k()) {
                this.o.h(q());
            }
            this.w = status2;
            if (sVar != null) {
                this.v.k(sVar);
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean d(c cVar) {
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
        synchronized (this.f3218d) {
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
        synchronized (singleRequest.f3218d) {
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
    public boolean e() {
        boolean z;
        synchronized (this.f3218d) {
            z = this.w == Status.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.f
    public Object f() {
        this.f3217c.c();
        return this.f3218d;
    }

    @Override // com.bumptech.glide.request.h.c
    public void g(int i, int i2) throws Throwable {
        Object obj;
        this.f3217c.c();
        Object obj2 = this.f3218d;
        synchronized (obj2) {
            try {
                try {
                    boolean z = f3215a;
                    if (z) {
                        t("Got onSizeReady in " + com.bumptech.glide.m.f.a(this.u));
                    }
                    if (this.w == Status.WAITING_FOR_SIZE) {
                        Status status = Status.RUNNING;
                        this.w = status;
                        float fT = this.k.t();
                        this.A = u(i, fT);
                        this.B = u(i2, fT);
                        if (z) {
                            t("finished setup for calling load in " + com.bumptech.glide.m.f.a(this.u));
                        }
                        obj = obj2;
                        try {
                            this.t = this.v.f(this.h, this.i, this.k.s(), this.A, this.B, this.k.r(), this.j, this.n, this.k.f(), this.k.v(), this.k.D(), this.k.A(), this.k.l(), this.k.y(), this.k.x(), this.k.w(), this.k.k(), this, this.r);
                            if (this.w != status) {
                                this.t = null;
                            }
                            if (z) {
                                t("finished onSizeReady in " + com.bumptech.glide.m.f.a(this.u));
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
    public void h() {
        synchronized (this.f3218d) {
            i();
            this.f3217c.c();
            this.u = com.bumptech.glide.m.f.b();
            if (this.i == null) {
                if (k.r(this.l, this.m)) {
                    this.A = this.l;
                    this.B = this.m;
                }
                y(new GlideException("Received null model"), p() == null ? 5 : 3);
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
            if ((status4 == status2 || status4 == status3) && l()) {
                this.o.f(q());
            }
            if (f3215a) {
                t("finished run method in " + com.bumptech.glide.m.f.a(this.u));
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3218d) {
            Status status = this.w;
            z = status == Status.RUNNING || status == Status.WAITING_FOR_SIZE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean j() {
        boolean z;
        synchronized (this.f3218d) {
            z = this.w == Status.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public void pause() {
        synchronized (this.f3218d) {
            if (isRunning()) {
                clear();
            }
        }
    }
}
