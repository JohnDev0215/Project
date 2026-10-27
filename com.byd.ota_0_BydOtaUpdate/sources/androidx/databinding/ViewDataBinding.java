package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import b.i.c;
import b.l.m;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends b.i.a {
    public static final g E3;
    public static final g F3;
    public static final c.a<b.i.f, ViewDataBinding, Void> G3;
    public static final ReferenceQueue<ViewDataBinding> H3;
    public static final View.OnAttachStateChangeListener I3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f474d;
    public static final boolean s;
    public static final g x;
    public static final g y;
    public final Runnable J3;
    public boolean K3;
    public boolean L3;
    public b.i.c<b.i.f, ViewDataBinding, Void> M3;
    public boolean N3;
    public Choreographer O3;
    public final Choreographer.FrameCallback P3;
    public Handler Q3;
    public ViewDataBinding R3;
    public b.l.f S3;

    public static class OnStartListener implements b.l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<ViewDataBinding> f475a;

        @m(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f475a.get();
            if (viewDataBinding != null) {
                viewDataBinding.e();
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

    public static class e extends c.a<b.i.f, ViewDataBinding, Void> {
        @Override // b.i.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(b.i.f fVar, ViewDataBinding viewDataBinding, int i2, Void r4) {
            if (i2 == 1) {
                if (fVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.L3 = true;
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
            ViewDataBinding.f(view).J3.run();
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
        f474d = i2;
        s = i2 >= 16;
        x = new a();
        y = new b();
        E3 = new c();
        F3 = new d();
        G3 = new e();
        H3 = new ReferenceQueue<>();
        if (i2 < 19) {
            I3 = null;
        } else {
            I3 = new f();
        }
    }

    public static ViewDataBinding f(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(b.i.h.a.f1865a);
        }
        return null;
    }

    public abstract void c();

    public final void d() {
        if (this.N3) {
            h();
            return;
        }
        if (g()) {
            this.N3 = true;
            this.L3 = false;
            b.i.c<b.i.f, ViewDataBinding, Void> cVar = this.M3;
            if (cVar != null) {
                cVar.f(this, 1, null);
                if (this.L3) {
                    this.M3.f(this, 2, null);
                }
            }
            if (!this.L3) {
                c();
                b.i.c<b.i.f, ViewDataBinding, Void> cVar2 = this.M3;
                if (cVar2 != null) {
                    cVar2.f(this, 3, null);
                }
            }
            this.N3 = false;
        }
    }

    public void e() {
        ViewDataBinding viewDataBinding = this.R3;
        if (viewDataBinding == null) {
            d();
        } else {
            viewDataBinding.e();
        }
    }

    public abstract boolean g();

    public void h() {
        ViewDataBinding viewDataBinding = this.R3;
        if (viewDataBinding != null) {
            viewDataBinding.h();
            return;
        }
        b.l.f fVar = this.S3;
        if (fVar == null || fVar.a().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.K3) {
                    return;
                }
                this.K3 = true;
                if (s) {
                    this.O3.postFrameCallback(this.P3);
                } else {
                    this.Q3.post(this.J3);
                }
            }
        }
    }
}
