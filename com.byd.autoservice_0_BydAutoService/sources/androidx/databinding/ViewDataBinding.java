package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.library.R$id;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import q0.c;
import t0.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends q0.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f1212c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1213d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g f1214e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g f1215f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g f1216g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final g f1217h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c.a<q0.f, ViewDataBinding, Void> f1218i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final ReferenceQueue<ViewDataBinding> f1219j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final View.OnAttachStateChangeListener f1220k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Runnable f1221l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1222m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1223n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public q0.c<q0.f, ViewDataBinding, Void> f1224o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1225p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Choreographer f1226q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Choreographer.FrameCallback f1227r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Handler f1228s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ViewDataBinding f1229t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public t0.f f1230u;

    public static class OnStartListener implements t0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<ViewDataBinding> f1231a;

        @m(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1231a.get();
            if (viewDataBinding != null) {
                viewDataBinding.j();
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

    public static class e extends c.a<q0.f, ViewDataBinding, Void> {
        @Override // q0.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(q0.f fVar, ViewDataBinding viewDataBinding, int i3, Void r4) {
            if (i3 == 1) {
                if (fVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.f1223n = true;
            } else if (i3 == 2) {
                fVar.b(viewDataBinding);
            } else {
                if (i3 != 3) {
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
            ViewDataBinding.k(view).f1221l.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public interface g {
    }

    static {
        int i3 = Build.VERSION.SDK_INT;
        f1212c = i3;
        f1213d = i3 >= 16;
        f1214e = new a();
        f1215f = new b();
        f1216g = new c();
        f1217h = new d();
        f1218i = new e();
        f1219j = new ReferenceQueue<>();
        if (i3 < 19) {
            f1220k = null;
        } else {
            f1220k = new f();
        }
    }

    public static ViewDataBinding k(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R$id.dataBinding);
        }
        return null;
    }

    public abstract void h();

    public final void i() {
        if (this.f1225p) {
            m();
            return;
        }
        if (l()) {
            this.f1225p = true;
            this.f1223n = false;
            q0.c<q0.f, ViewDataBinding, Void> cVar = this.f1224o;
            if (cVar != null) {
                cVar.d(this, 1, null);
                if (this.f1223n) {
                    this.f1224o.d(this, 2, null);
                }
            }
            if (!this.f1223n) {
                h();
                q0.c<q0.f, ViewDataBinding, Void> cVar2 = this.f1224o;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.f1225p = false;
        }
    }

    public void j() {
        ViewDataBinding viewDataBinding = this.f1229t;
        if (viewDataBinding == null) {
            i();
        } else {
            viewDataBinding.j();
        }
    }

    public abstract boolean l();

    public void m() {
        ViewDataBinding viewDataBinding = this.f1229t;
        if (viewDataBinding != null) {
            viewDataBinding.m();
            return;
        }
        t0.f fVar = this.f1230u;
        if (fVar == null || fVar.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.f1222m) {
                    return;
                }
                this.f1222m = true;
                if (f1213d) {
                    this.f1226q.postFrameCallback(this.f1227r);
                } else {
                    this.f1228s.post(this.f1221l);
                }
            }
        }
    }
}
