package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.c;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.n;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends androidx.databinding.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static int f1057c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final boolean f1058d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final g f1059e;
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
    private androidx.lifecycle.g u;

    static class OnStartListener implements androidx.lifecycle.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<ViewDataBinding> f1060a;

        @n(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1060a.get();
            if (viewDataBinding != null) {
                viewDataBinding.y();
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
            ViewDataBinding.z(view).l.run();
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
        f1057c = i2;
        f1058d = i2 >= 16;
        f1059e = new a();
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

    private void x() {
        if (this.p) {
            B();
            return;
        }
        if (A()) {
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
                w();
                androidx.databinding.c<androidx.databinding.f, ViewDataBinding, Void> cVar2 = this.o;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.p = false;
        }
    }

    static ViewDataBinding z(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(androidx.databinding.h.a.f1067a);
        }
        return null;
    }

    public abstract boolean A();

    protected void B() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding != null) {
            viewDataBinding.B();
            return;
        }
        androidx.lifecycle.g gVar = this.u;
        if (gVar == null || gVar.a().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.m) {
                    return;
                }
                this.m = true;
                if (f1058d) {
                    this.q.postFrameCallback(this.r);
                } else {
                    this.s.post(this.l);
                }
            }
        }
    }

    protected abstract void w();

    public void y() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding == null) {
            x();
        } else {
            viewDataBinding.y();
        }
    }
}
