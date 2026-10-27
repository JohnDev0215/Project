package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.c;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.m;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends androidx.databinding.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static int f1472c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final boolean f1473d;
    private static final g e;
    private static final g f;
    private static final g g;
    private static final g h;
    private static final c.a<androidx.databinding.f, ViewDataBinding, Void> i;
    private static final ReferenceQueue<ViewDataBinding> j;
    private static final View.OnAttachStateChangeListener k;
    private final Runnable l;
    private boolean m;
    private boolean n;
    private androidx.databinding.c<androidx.databinding.f, ViewDataBinding, Void> o;
    private boolean p;
    private Choreographer q;
    private final Choreographer.FrameCallback r;
    private Handler s;
    private ViewDataBinding t;
    private androidx.lifecycle.f u;

    static class OnStartListener implements androidx.lifecycle.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<ViewDataBinding> f1474a;

        @m(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1474a.get();
            if (viewDataBinding != null) {
                viewDataBinding.j();
            }
        }
    }

    static class a implements g {
        a() {
        }
    }

    static class b implements g {
        b() {
        }
    }

    static class c implements g {
        c() {
        }
    }

    static class d implements g {
        d() {
        }
    }

    static class e extends c.a<androidx.databinding.f, ViewDataBinding, Void> {
        e() {
        }

        @Override // androidx.databinding.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(androidx.databinding.f fVar, ViewDataBinding viewDataBinding, int i, Void r4) {
            if (i == 1) {
                if (fVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.n = true;
            } else if (i == 2) {
                fVar.b(viewDataBinding);
            } else {
                if (i != 3) {
                    return;
                }
                fVar.a(viewDataBinding);
            }
        }
    }

    static class f implements View.OnAttachStateChangeListener {
        f() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.k(view).l.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    private interface g {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f1472c = i2;
        f1473d = i2 >= 16;
        e = new a();
        f = new b();
        g = new c();
        h = new d();
        i = new e();
        j = new ReferenceQueue<>();
        if (i2 < 19) {
            k = null;
        } else {
            k = new f();
        }
    }

    private void i() {
        if (this.p) {
            m();
            return;
        }
        if (l()) {
            this.p = true;
            this.n = false;
            androidx.databinding.c<androidx.databinding.f, ViewDataBinding, Void> cVar = this.o;
            if (cVar != null) {
                cVar.d(this, 1, null);
                if (this.n) {
                    this.o.d(this, 2, null);
                }
            }
            if (!this.n) {
                h();
                androidx.databinding.c<androidx.databinding.f, ViewDataBinding, Void> cVar2 = this.o;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.p = false;
        }
    }

    static ViewDataBinding k(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(androidx.databinding.h.a.f1480a);
        }
        return null;
    }

    protected abstract void h();

    public void j() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding == null) {
            i();
        } else {
            viewDataBinding.j();
        }
    }

    public abstract boolean l();

    protected void m() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding != null) {
            viewDataBinding.m();
            return;
        }
        androidx.lifecycle.f fVar = this.u;
        if (fVar == null || fVar.a().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.m) {
                    return;
                }
                this.m = true;
                if (f1473d) {
                    this.q.postFrameCallback(this.r);
                } else {
                    this.s.post(this.l);
                }
            }
        }
    }
}
