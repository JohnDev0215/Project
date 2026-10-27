package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.library.R$id;
import androidx.lifecycle.Lifecycle;
import defpackage.ab;
import defpackage.cb;
import defpackage.dc;
import defpackage.ec;
import defpackage.fb;
import defpackage.lc;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends ab {
    public static int c;
    public static final boolean d;
    public static final g e;
    public static final g f;
    public static final g g;
    public static final g h;
    public static final cb.a<fb, ViewDataBinding, Void> i;
    public static final ReferenceQueue<ViewDataBinding> j;
    public static final View.OnAttachStateChangeListener k;
    public final Runnable l;
    public boolean m;
    public boolean n;
    public cb<fb, ViewDataBinding, Void> o;
    public boolean p;
    public Choreographer q;
    public final Choreographer.FrameCallback r;
    public Handler s;
    public ViewDataBinding t;
    public ec u;

    public static class OnStartListener implements dc {
        public final WeakReference<ViewDataBinding> a;

        @lc(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.a.get();
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

    public static class e extends cb.a<fb, ViewDataBinding, Void> {
        @Override // cb.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(fb fbVar, ViewDataBinding viewDataBinding, int i, Void r4) {
            if (i == 1) {
                if (fbVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.n = true;
            } else if (i == 2) {
                fbVar.b(viewDataBinding);
            } else {
                if (i != 3) {
                    return;
                }
                fbVar.a(viewDataBinding);
            }
        }
    }

    public static class f implements View.OnAttachStateChangeListener {
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

    public interface g {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        c = i2;
        d = i2 >= 16;
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

    public static ViewDataBinding k(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R$id.dataBinding);
        }
        return null;
    }

    public abstract void h();

    public final void i() {
        if (this.p) {
            m();
            return;
        }
        if (l()) {
            this.p = true;
            this.n = false;
            cb<fb, ViewDataBinding, Void> cbVar = this.o;
            if (cbVar != null) {
                cbVar.d(this, 1, null);
                if (this.n) {
                    this.o.d(this, 2, null);
                }
            }
            if (!this.n) {
                h();
                cb<fb, ViewDataBinding, Void> cbVar2 = this.o;
                if (cbVar2 != null) {
                    cbVar2.d(this, 3, null);
                }
            }
            this.p = false;
        }
    }

    public void j() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding == null) {
            i();
        } else {
            viewDataBinding.j();
        }
    }

    public abstract boolean l();

    public void m() {
        ViewDataBinding viewDataBinding = this.t;
        if (viewDataBinding != null) {
            viewDataBinding.m();
            return;
        }
        ec ecVar = this.u;
        if (ecVar == null || ecVar.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.m) {
                    return;
                }
                this.m = true;
                if (d) {
                    this.q.postFrameCallback(this.r);
                } else {
                    this.s.post(this.l);
                }
            }
        }
    }
}
