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
import u0.c;
import x0.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends u0.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f1299e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f1300f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g f1301g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final g f1302h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g f1303i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final g f1304j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final c.a<u0.f, ViewDataBinding, Void> f1305k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final ReferenceQueue<ViewDataBinding> f1306l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final View.OnAttachStateChangeListener f1307m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Runnable f1308n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1309o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1310p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public u0.c<u0.f, ViewDataBinding, Void> f1311q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1312r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Choreographer f1313s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Choreographer.FrameCallback f1314t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Handler f1315u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ViewDataBinding f1316v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public x0.f f1317w;

    public static class OnStartListener implements x0.e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final WeakReference<ViewDataBinding> f1318d;

        @m(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1318d.get();
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

    public static class e extends c.a<u0.f, ViewDataBinding, Void> {
        @Override // u0.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(u0.f fVar, ViewDataBinding viewDataBinding, int i8, Void r42) {
            if (i8 == 1) {
                if (fVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.f1310p = true;
            } else if (i8 == 2) {
                fVar.b(viewDataBinding);
            } else {
                if (i8 != 3) {
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
            ViewDataBinding.k(view).f1308n.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public interface g {
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        f1299e = i8;
        f1300f = i8 >= 16;
        f1301g = new a();
        f1302h = new b();
        f1303i = new c();
        f1304j = new d();
        f1305k = new e();
        f1306l = new ReferenceQueue<>();
        if (i8 < 19) {
            f1307m = null;
        } else {
            f1307m = new f();
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
        if (this.f1312r) {
            m();
            return;
        }
        if (l()) {
            this.f1312r = true;
            this.f1310p = false;
            u0.c<u0.f, ViewDataBinding, Void> cVar = this.f1311q;
            if (cVar != null) {
                cVar.d(this, 1, null);
                if (this.f1310p) {
                    this.f1311q.d(this, 2, null);
                }
            }
            if (!this.f1310p) {
                h();
                u0.c<u0.f, ViewDataBinding, Void> cVar2 = this.f1311q;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.f1312r = false;
        }
    }

    public void j() {
        ViewDataBinding viewDataBinding = this.f1316v;
        if (viewDataBinding == null) {
            i();
        } else {
            viewDataBinding.j();
        }
    }

    public abstract boolean l();

    public void m() {
        ViewDataBinding viewDataBinding = this.f1316v;
        if (viewDataBinding != null) {
            viewDataBinding.m();
            return;
        }
        x0.f fVar = this.f1317w;
        if (fVar == null || fVar.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.f1309o) {
                    return;
                }
                this.f1309o = true;
                if (f1300f) {
                    this.f1313s.postFrameCallback(this.f1314t);
                } else {
                    this.f1315u.post(this.f1308n);
                }
            }
        }
    }
}
