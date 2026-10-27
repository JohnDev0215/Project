package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.c;
import androidx.lifecycle.d;
import androidx.lifecycle.n;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends androidx.databinding.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f1316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final boolean f1317b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final g f1318c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final g f1319d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final g f1320e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final g f1321f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final c.a<?, ViewDataBinding, Void> f1322g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final ReferenceQueue<ViewDataBinding> f1323h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final View.OnAttachStateChangeListener f1324i;
    private final Runnable j;
    private boolean k;
    private boolean l;
    private androidx.databinding.c<?, ViewDataBinding, Void> m;
    private boolean n;
    private Choreographer o;
    private final Choreographer.FrameCallback p;
    private Handler q;
    private ViewDataBinding r;
    private androidx.lifecycle.g s;

    static class OnStartListener implements androidx.lifecycle.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<ViewDataBinding> f1325a;

        @n(d.a.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1325a.get();
            if (viewDataBinding != null) {
                viewDataBinding.p();
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

    static class e extends c.a<?, ViewDataBinding, Void> {
        e() {
        }
    }

    static class f implements View.OnAttachStateChangeListener {
        f() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.q(view).j.run();
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
        f1316a = i2;
        f1317b = i2 >= 16;
        f1318c = new a();
        f1319d = new b();
        f1320e = new c();
        f1321f = new d();
        f1322g = new e();
        f1323h = new ReferenceQueue<>();
        if (i2 < 19) {
            f1324i = null;
        } else {
            f1324i = new f();
        }
    }

    private void j() {
        if (this.n) {
            s();
            return;
        }
        if (r()) {
            this.n = true;
            this.l = false;
            if (this.m != null) {
                throw null;
            }
            d();
            if (this.m != null) {
                throw null;
            }
            this.n = false;
        }
    }

    static ViewDataBinding q(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(androidx.databinding.f.a.dataBinding);
        }
        return null;
    }

    protected abstract void d();

    public void p() {
        ViewDataBinding viewDataBinding = this.r;
        if (viewDataBinding == null) {
            j();
        } else {
            viewDataBinding.p();
        }
    }

    public abstract boolean r();

    protected void s() {
        ViewDataBinding viewDataBinding = this.r;
        if (viewDataBinding != null) {
            viewDataBinding.s();
            return;
        }
        androidx.lifecycle.g gVar = this.s;
        if (gVar == null || gVar.a().b().a(d.b.STARTED)) {
            synchronized (this) {
                if (this.k) {
                    return;
                }
                this.k = true;
                if (f1317b) {
                    this.o.postFrameCallback(this.p);
                } else {
                    this.q.post(this.j);
                }
            }
        }
    }
}
