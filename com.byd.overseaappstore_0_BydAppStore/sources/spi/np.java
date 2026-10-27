package spi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.b;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestCoordinator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import spi.es;
import spi.i8;
import spi.sj;

/* JADX INFO: loaded from: classes.dex */
public final class np<R> implements jm, tp, an {
    public static final boolean D = Log.isLoggable("GlideRequest", 2);

    @GuardedBy("requestLock")
    public int A;

    @GuardedBy("requestLock")
    public boolean B;

    @Nullable
    public RuntimeException C;

    @Nullable
    public final String a;
    public final es.b b;
    public final Object c;

    @Nullable
    public final pm<R> d;
    public final RequestCoordinator e;
    public final Context f;
    public final com.bumptech.glide.c g;

    @Nullable
    public final Object h;
    public final Class<R> i;
    public final i1<?> j;
    public final int k;
    public final int l;
    public final Priority m;
    public final rs<R> n;

    @Nullable
    public final List<pm<R>> o;
    public final wt<? super R> p;
    public final Executor q;

    @GuardedBy("requestLock")
    public wm<R> r;

    @GuardedBy("requestLock")
    public i8.d s;

    @GuardedBy("requestLock")
    public long t;
    public volatile i8 u;

    @GuardedBy("requestLock")
    public a v;

    @Nullable
    @GuardedBy("requestLock")
    public Drawable w;

    @Nullable
    @GuardedBy("requestLock")
    public Drawable x;

    @Nullable
    @GuardedBy("requestLock")
    public Drawable y;

    @GuardedBy("requestLock")
    public int z;

    public enum a {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    private np(Context context, com.bumptech.glide.c cVar, @NonNull Object obj, @Nullable Object obj2, Class<R> cls, i1<?> i1Var, int i, int i2, Priority priority, rs<R> rsVar, @Nullable pm<R> pmVar, @Nullable List<pm<R>> list, RequestCoordinator requestCoordinator, i8 i8Var, wt<? super R> wtVar, Executor executor) {
        this.a = D ? String.valueOf(hashCode()) : null;
        this.b = new es.b();
        this.c = obj;
        this.f = context;
        this.g = cVar;
        this.h = obj2;
        this.i = cls;
        this.j = i1Var;
        this.k = i;
        this.l = i2;
        this.m = priority;
        this.n = rsVar;
        this.d = pmVar;
        this.o = list;
        this.e = requestCoordinator;
        this.u = i8Var;
        this.p = wtVar;
        this.q = executor;
        this.v = a.PENDING;
        if (this.C == null && cVar.h.a(b.d.class)) {
            this.C = new RuntimeException("Glide request origin trace");
        }
    }

    public static np n(Context context, com.bumptech.glide.c cVar, Object obj, Object obj2, Class cls, i1 i1Var, int i, int i2, Priority priority, rs rsVar, @Nullable List list, RequestCoordinator requestCoordinator, i8 i8Var, wt wtVar, Executor executor) {
        return new np(context, cVar, obj, obj2, cls, i1Var, i, i2, priority, rsVar, null, list, requestCoordinator, i8Var, wtVar, executor);
    }

    @Override // spi.jm
    public final boolean a() {
        boolean z;
        synchronized (this.c) {
            z = this.v == a.COMPLETE;
        }
        return z;
    }

    @Override // spi.tp
    public final void b(int i, int i2) throws Throwable {
        Object obj;
        int iRound = i;
        this.b.b();
        Object obj2 = this.c;
        synchronized (obj2) {
            try {
                boolean z = D;
                if (z) {
                    m("Got onSizeReady in " + ch.a(this.t));
                }
                if (this.v == a.WAITING_FOR_SIZE) {
                    a aVar = a.RUNNING;
                    this.v = aVar;
                    float f = this.j.b;
                    if (iRound != Integer.MIN_VALUE) {
                        iRound = Math.round(iRound * f);
                    }
                    this.z = iRound;
                    this.A = i2 == Integer.MIN_VALUE ? i2 : Math.round(f * i2);
                    if (z) {
                        m("finished setup for calling load in " + ch.a(this.t));
                    }
                    i8 i8Var = this.u;
                    com.bumptech.glide.c cVar = this.g;
                    Object obj3 = this.h;
                    i1<?> i1Var = this.j;
                    try {
                        obj = obj2;
                        try {
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        obj = obj2;
                    }
                    try {
                        this.s = i8Var.b(cVar, obj3, i1Var.l, this.z, this.A, i1Var.s, this.i, this.m, i1Var.c, i1Var.r, i1Var.m, i1Var.y, i1Var.q, i1Var.i, i1Var.w, i1Var.z, i1Var.x, this, this.q);
                        if (this.v != aVar) {
                            this.s = null;
                        }
                        if (z) {
                            m("finished onSizeReady in " + ch.a(this.t));
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        while (true) {
                            try {
                                throw th;
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                obj = obj2;
            }
        }
    }

    @Override // spi.jm
    public final boolean c(jm jmVar) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        i1<?> i1Var;
        Priority priority;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        i1<?> i1Var2;
        Priority priority2;
        int size2;
        if (!(jmVar instanceof np)) {
            return false;
        }
        synchronized (this.c) {
            i = this.k;
            i2 = this.l;
            obj = this.h;
            cls = this.i;
            i1Var = this.j;
            priority = this.m;
            List<pm<R>> list = this.o;
            size = list != null ? list.size() : 0;
        }
        np npVar = (np) jmVar;
        synchronized (npVar.c) {
            i3 = npVar.k;
            i4 = npVar.l;
            obj2 = npVar.h;
            cls2 = npVar.i;
            i1Var2 = npVar.j;
            priority2 = npVar.m;
            List<pm<R>> list2 = npVar.o;
            size2 = list2 != null ? list2.size() : 0;
        }
        if (i == i3 && i2 == i4) {
            char[] cArr = tu.a;
            if ((obj == null ? obj2 == null : obj instanceof ri ? ((ri) obj).a() : obj.equals(obj2)) && cls.equals(cls2) && i1Var.equals(i1Var2) && priority == priority2 && size == size2) {
                return true;
            }
        }
        return false;
    }

    @Override // spi.jm
    public final void clear() {
        synchronized (this.c) {
            d();
            this.b.b();
            a aVar = this.v;
            a aVar2 = a.CLEARED;
            if (aVar == aVar2) {
                return;
            }
            e();
            wm<R> wmVar = this.r;
            if (wmVar != null) {
                this.r = null;
            } else {
                wmVar = null;
            }
            RequestCoordinator requestCoordinator = this.e;
            if (requestCoordinator == null || requestCoordinator.g(this)) {
                this.n.i(h());
            }
            this.v = aVar2;
            if (wmVar != null) {
                this.u.f(wmVar);
            }
        }
    }

    @GuardedBy("requestLock")
    public final void d() {
        if (this.B) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @GuardedBy("requestLock")
    public final void e() {
        d();
        this.b.b();
        this.n.f(this);
        i8.d dVar = this.s;
        if (dVar != null) {
            synchronized (i8.this) {
                dVar.a.h(dVar.b);
            }
            this.s = null;
        }
    }

    @Override // spi.jm
    public final boolean f() {
        boolean z;
        synchronized (this.c) {
            z = this.v == a.CLEARED;
        }
        return z;
    }

    @GuardedBy("requestLock")
    public final Drawable g() {
        int i;
        if (this.y == null) {
            i1<?> i1Var = this.j;
            Drawable drawable = i1Var.o;
            this.y = drawable;
            if (drawable == null && (i = i1Var.p) > 0) {
                this.y = l(i);
            }
        }
        return this.y;
    }

    @GuardedBy("requestLock")
    public final Drawable h() {
        int i;
        if (this.x == null) {
            i1<?> i1Var = this.j;
            Drawable drawable = i1Var.g;
            this.x = drawable;
            if (drawable == null && (i = i1Var.h) > 0) {
                this.x = l(i);
            }
        }
        return this.x;
    }

    @Override // spi.jm
    public final void i() {
        synchronized (this.c) {
            d();
            this.b.b();
            int i = ch.b;
            this.t = SystemClock.elapsedRealtimeNanos();
            if (this.h == null) {
                if (tu.j(this.k, this.l)) {
                    this.z = this.k;
                    this.A = this.l;
                }
                o(new GlideException("Received null model"), g() == null ? 5 : 3);
                return;
            }
            a aVar = this.v;
            if (aVar == a.RUNNING) {
                throw new IllegalArgumentException("Cannot restart a running request");
            }
            if (aVar == a.COMPLETE) {
                p(this.r, DataSource.MEMORY_CACHE, false);
                return;
            }
            List<pm<R>> list = this.o;
            if (list != null) {
                for (pm<R> pmVar : list) {
                    if (pmVar instanceof sa) {
                        Objects.requireNonNull((sa) pmVar);
                    }
                }
            }
            a aVar2 = a.WAITING_FOR_SIZE;
            this.v = aVar2;
            if (tu.j(this.k, this.l)) {
                b(this.k, this.l);
            } else {
                this.n.a(this);
            }
            a aVar3 = this.v;
            if (aVar3 == a.RUNNING || aVar3 == aVar2) {
                RequestCoordinator requestCoordinator = this.e;
                if (requestCoordinator == null || requestCoordinator.e(this)) {
                    this.n.g(h());
                }
            }
            if (D) {
                m("finished run method in " + ch.a(this.t));
            }
        }
    }

    @Override // spi.jm
    public final boolean isRunning() {
        boolean z;
        synchronized (this.c) {
            a aVar = this.v;
            z = aVar == a.RUNNING || aVar == a.WAITING_FOR_SIZE;
        }
        return z;
    }

    @Override // spi.jm
    public final boolean j() {
        boolean z;
        synchronized (this.c) {
            z = this.v == a.COMPLETE;
        }
        return z;
    }

    @GuardedBy("requestLock")
    public final boolean k() {
        RequestCoordinator requestCoordinator = this.e;
        return requestCoordinator == null || !requestCoordinator.getRoot().a();
    }

    @GuardedBy("requestLock")
    public final Drawable l(@DrawableRes int i) {
        Resources.Theme theme = this.j.u;
        if (theme == null) {
            theme = this.f.getTheme();
        }
        com.bumptech.glide.c cVar = this.g;
        return s7.a(cVar, cVar, i, theme);
    }

    public final void m(String str) {
        StringBuilder sbM = e.m(str, " this: ");
        sbM.append(this.a);
        Log.v("GlideRequest", sbM.toString());
    }

    public final void o(GlideException glideException, int i) {
        this.b.b();
        synchronized (this.c) {
            glideException.setOrigin(this.C);
            int i2 = this.g.i;
            if (i2 <= i) {
                Log.w("Glide", "Load failed for " + this.h + " with size [" + this.z + "x" + this.A + "]", glideException);
                if (i2 <= 4) {
                    glideException.logRootCauses("Glide");
                }
            }
            this.s = null;
            this.v = a.FAILED;
            this.B = true;
            try {
                List<pm<R>> list = this.o;
                if (list != null) {
                    for (pm<R> pmVar : list) {
                        rs<R> rsVar = this.n;
                        k();
                        pmVar.a(glideException, rsVar);
                    }
                }
                pm<R> pmVar2 = this.d;
                if (pmVar2 != null) {
                    rs<R> rsVar2 = this.n;
                    k();
                    pmVar2.a(glideException, rsVar2);
                }
                r();
                this.B = false;
                RequestCoordinator requestCoordinator = this.e;
                if (requestCoordinator != null) {
                    requestCoordinator.d(this);
                }
            } catch (Throwable th) {
                this.B = false;
                throw th;
            }
        }
    }

    public final void p(wm<?> wmVar, DataSource dataSource, boolean z) {
        np<R> npVar;
        Throwable th;
        this.b.b();
        wm<?> wmVar2 = null;
        try {
            synchronized (this.c) {
                try {
                    this.s = null;
                    if (wmVar == null) {
                        o(new GlideException("Expected to receive a Resource<R> with an object of " + this.i + " inside, but instead got null."), 5);
                        return;
                    }
                    Object obj = wmVar.get();
                    try {
                        if (obj == null || !this.i.isAssignableFrom(obj.getClass())) {
                            this.r = null;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Expected to receive an object of ");
                            sb.append(this.i);
                            sb.append(" but instead got ");
                            sb.append(obj != null ? obj.getClass() : "");
                            sb.append("{");
                            sb.append(obj);
                            sb.append("} inside Resource{");
                            sb.append(wmVar);
                            sb.append("}.");
                            sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                            o(new GlideException(sb.toString()), 5);
                        } else {
                            RequestCoordinator requestCoordinator = this.e;
                            if (requestCoordinator == null || requestCoordinator.h(this)) {
                                q(wmVar, obj, dataSource);
                                return;
                            } else {
                                this.r = null;
                                this.v = a.COMPLETE;
                            }
                        }
                        this.u.f(wmVar);
                    } catch (Throwable th2) {
                        th = th2;
                        wmVar2 = wmVar;
                        npVar = this;
                        while (true) {
                            try {
                                try {
                                    throw th;
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (wmVar2 != null) {
                                        npVar.u.f(wmVar2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                npVar = npVar;
                            }
                            th = th4;
                            npVar = npVar;
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    npVar = this;
                }
            }
        } catch (Throwable th6) {
            th = th6;
            npVar = this;
        }
    }

    @Override // spi.jm
    public final void pause() {
        synchronized (this.c) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @GuardedBy("requestLock")
    public final void q(wm wmVar, Object obj, DataSource dataSource) {
        k();
        this.v = a.COMPLETE;
        this.r = wmVar;
        if (this.g.i <= 3) {
            StringBuilder sbK = e.k("Finished loading ");
            sbK.append(obj.getClass().getSimpleName());
            sbK.append(" from ");
            sbK.append(dataSource);
            sbK.append(" for ");
            sbK.append(this.h);
            sbK.append(" with size [");
            sbK.append(this.z);
            sbK.append("x");
            sbK.append(this.A);
            sbK.append("] in ");
            sbK.append(ch.a(this.t));
            sbK.append(" ms");
            Log.d("Glide", sbK.toString());
        }
        this.B = true;
        try {
            List<pm<R>> list = this.o;
            if (list != null) {
                Iterator<pm<R>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().b(obj, this.n);
                }
            }
            pm<R> pmVar = this.d;
            if (pmVar != null) {
                pmVar.b(obj, this.n);
            }
            Objects.requireNonNull((sj.a) this.p);
            this.n.c(obj, sj.a);
            this.B = false;
            RequestCoordinator requestCoordinator = this.e;
            if (requestCoordinator != null) {
                requestCoordinator.b(this);
            }
        } catch (Throwable th) {
            this.B = false;
            throw th;
        }
    }

    @GuardedBy("requestLock")
    public final void r() {
        int i;
        RequestCoordinator requestCoordinator = this.e;
        if (requestCoordinator == null || requestCoordinator.e(this)) {
            Drawable drawableG = this.h == null ? g() : null;
            if (drawableG == null) {
                if (this.w == null) {
                    i1<?> i1Var = this.j;
                    Drawable drawable = i1Var.e;
                    this.w = drawable;
                    if (drawable == null && (i = i1Var.f) > 0) {
                        this.w = l(i);
                    }
                }
                drawableG = this.w;
            }
            if (drawableG == null) {
                drawableG = h();
            }
            this.n.d(drawableG);
        }
    }

    public final String toString() {
        Object obj;
        Class<R> cls;
        synchronized (this.c) {
            obj = this.h;
            cls = this.i;
        }
        return super.toString() + "[model=" + obj + ", transcodeClass=" + cls + "]";
    }
}
