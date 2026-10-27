package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import b.k.c;
import b.n.n;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends b.k.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f831c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f832d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g f833e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g f834f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g f835g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final g f836h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c.a<b.k.f, ViewDataBinding, Void> f837i;
    public static final ReferenceQueue<ViewDataBinding> j;
    public static final View.OnAttachStateChangeListener k;
    public final Runnable l;
    public boolean m;
    public boolean n;
    public b.k.c<b.k.f, ViewDataBinding, Void> o;
    public boolean p;
    public Choreographer q;
    public final Choreographer.FrameCallback r;
    public Handler s;
    public ViewDataBinding t;
    public b.n.g u;

    public static class OnStartListener implements b.n.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<ViewDataBinding> f838a;

        @n(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f838a.get();
            if (viewDataBinding != null) {
                viewDataBinding.n();
            }
        }
    }

    public static class a implements g {
    }

    public static class b implements g {
    }

    public static class c implements g {
    }

    public static class d implements g {
    }

    public static class e extends c.a<b.k.f, ViewDataBinding, Void> {
        @Override // b.k.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(b.k.f fVar, ViewDataBinding viewDataBinding, int i2, Void r4) {
            if (i2 == 1) {
                if (fVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.n = true;
            } else if (i2 == 2) {
                fVar.b(viewDataBinding);
            } else {
                if (i2 != 3) {
                    return;
                }
                fVar.a(viewDataBinding);
            }
        }
    }

    public static class f implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.o(view).l.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public interface g {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f831c = i2;
        f832d = i2 >= 16;
        f833e = new a();
        f834f = new b();
        f835g = new c();
        f836h = new d();
        f837i = new e();
        j = new ReferenceQueue<>();
        if (i2 < 19) {
            k = null;
        } else {
            k = new f();
        }
    }

    public static ViewDataBinding o(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(b.k.h.a.dataBinding);
        }
        return null;
    }

    public abstract void l();

    public final void m() {
        if (this.p) {
            q();
            return;
        }
        if (p()) {
            this.p = true;
            this.n = false;
            b.k.c<b.k.f, ViewDataBinding, Void> cVar = this.o;
            if (cVar != null) {
                cVar.d(this, 1, null);
                if (this.n) {
                    this.o.d(this, 2, null);
                }
            }
            if (!this.n) {
                l();
                b.k.c<b.k.f, ViewDataBinding, Void> cVar2 = this.o;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.p = false;
        }
    }

    public void n() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding == null) {
            m();
        } else {
            viewDataBinding.n();
        }
    }

    public abstract boolean p();

    public void q() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding != null) {
            viewDataBinding.q();
            return;
        }
        b.n.g gVar = this.u;
        if (gVar == null || gVar.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.m) {
                    return;
                }
                this.m = true;
                if (f832d) {
                    this.q.postFrameCallback(this.r);
                } else {
                    this.s.post(this.l);
                }
            }
        }
    }
}
