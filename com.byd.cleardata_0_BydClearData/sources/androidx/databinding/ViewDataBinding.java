package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.b;
import androidx.lifecycle.d;
import androidx.lifecycle.n;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends androidx.databinding.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f1264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final boolean f1265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final g f1266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final g f1267d;
    private static final g e;
    private static final g f;
    private static final b.a<?, ViewDataBinding, Void> g;
    private static final ReferenceQueue<ViewDataBinding> h;
    private static final View.OnAttachStateChangeListener i;
    private final Runnable j;
    private boolean k;
    private boolean l;
    private androidx.databinding.b<?, ViewDataBinding, Void> m;
    private boolean n;
    private Choreographer o;
    private final Choreographer.FrameCallback p;
    private Handler q;
    private ViewDataBinding r;
    private androidx.lifecycle.g s;

    static class OnStartListener implements androidx.lifecycle.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<ViewDataBinding> f1268a;

        @n(d.a.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1268a.get();
            if (viewDataBinding != null) {
                viewDataBinding.d();
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

    static class e extends b.a<?, ViewDataBinding, Void> {
        e() {
        }
    }

    static class f implements View.OnAttachStateChangeListener {
        f() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.e(view).j.run();
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
        f1264a = i2;
        f1265b = i2 >= 16;
        f1266c = new a();
        f1267d = new b();
        e = new c();
        f = new d();
        g = new e();
        h = new ReferenceQueue<>();
        i = i2 < 19 ? null : new f();
    }

    private void c() {
        if (this.n) {
            g();
            return;
        }
        if (f()) {
            this.n = true;
            this.l = false;
            if (this.m != null) {
                throw null;
            }
            b();
            if (this.m != null) {
                throw null;
            }
            this.n = false;
        }
    }

    static ViewDataBinding e(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(androidx.databinding.d.a.f1269a);
        }
        return null;
    }

    protected abstract void b();

    public void d() {
        ViewDataBinding viewDataBinding = this.r;
        if (viewDataBinding == null) {
            c();
        } else {
            viewDataBinding.d();
        }
    }

    public abstract boolean f();

    protected void g() {
        ViewDataBinding viewDataBinding = this.r;
        if (viewDataBinding != null) {
            viewDataBinding.g();
            return;
        }
        androidx.lifecycle.g gVar = this.s;
        if (gVar == null || gVar.a().b().a(d.b.STARTED)) {
            synchronized (this) {
                if (this.k) {
                    return;
                }
                this.k = true;
                if (f1265b) {
                    this.o.postFrameCallback(this.p);
                } else {
                    this.q.post(this.j);
                }
            }
        }
    }
}
