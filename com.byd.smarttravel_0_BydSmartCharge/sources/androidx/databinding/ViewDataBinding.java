package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.View;
import androidx.databinding.library.R$id;
import androidx.lifecycle.Lifecycle;
import b.j.c;
import b.m.m;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends b.j.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f348c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f349d = 8;
    public static final boolean e;
    public static final i f;
    public static final i g;
    public static final i h;
    public static final i i;
    public static final c.a<b.j.h, ViewDataBinding, Void> j;
    public static final ReferenceQueue<ViewDataBinding> k;
    public static final View.OnAttachStateChangeListener l;
    public final Runnable m;
    public boolean n;
    public boolean o;
    public l[] p;
    public final View q;
    public b.j.c<b.j.h, ViewDataBinding, Void> r;
    public boolean s;
    public Choreographer t;
    public final Choreographer.FrameCallback u;
    public Handler v;
    public final b.j.e w;
    public ViewDataBinding x;
    public b.m.f y;

    public static class OnStartListener implements b.m.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<ViewDataBinding> f350a;

        @m(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f350a.get();
            if (viewDataBinding != null) {
                viewDataBinding.o();
            }
        }
    }

    public static class a implements i {
    }

    public static class b implements i {
    }

    public static class c implements i {
    }

    public static class d implements i {
    }

    public static class e extends c.a<b.j.h, ViewDataBinding, Void> {
        @Override // b.j.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(b.j.h hVar, ViewDataBinding viewDataBinding, int i, Void r4) {
            if (i == 1) {
                if (hVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.o = true;
            } else if (i == 2) {
                hVar.b(viewDataBinding);
            } else {
                if (i != 3) {
                    return;
                }
                hVar.a(viewDataBinding);
            }
        }
    }

    public static class f implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.q(view).m.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.n = false;
            }
            ViewDataBinding.w();
            if (Build.VERSION.SDK_INT < 19 || ViewDataBinding.this.q.isAttachedToWindow()) {
                ViewDataBinding.this.o();
            } else {
                ViewDataBinding.this.q.removeOnAttachStateChangeListener(ViewDataBinding.l);
                ViewDataBinding.this.q.addOnAttachStateChangeListener(ViewDataBinding.l);
            }
        }
    }

    public class h implements Choreographer.FrameCallback {
        public h() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            ViewDataBinding.this.m.run();
        }
    }

    public interface i {
    }

    public static class j {
    }

    public interface k<T> {
        void a(T t);
    }

    public static class l<T> extends WeakReference<ViewDataBinding> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k<T> f353a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public T f354b;

        public boolean a() {
            boolean z;
            T t = this.f354b;
            if (t != null) {
                this.f353a.a(t);
                z = true;
            } else {
                z = false;
            }
            this.f354b = null;
            return z;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f348c = i2;
        e = i2 >= 16;
        f = new a();
        g = new b();
        h = new c();
        i = new d();
        j = new e();
        k = new ReferenceQueue<>();
        if (i2 < 19) {
            l = null;
        } else {
            l = new f();
        }
    }

    public ViewDataBinding(b.j.e eVar, View view, int i2) {
        this.m = new g();
        this.n = false;
        this.o = false;
        this.w = eVar;
        this.p = new l[i2];
        this.q = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        }
        if (e) {
            this.t = Choreographer.getInstance();
            this.u = new h();
        } else {
            this.u = null;
            this.v = new Handler(Looper.myLooper());
        }
    }

    public static b.j.e l(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof b.j.e) {
            return (b.j.e) obj;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
    }

    public static int p(String str, int i2, j jVar, int i3) {
        str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        throw null;
    }

    public static ViewDataBinding q(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R$id.dataBinding);
        }
        return null;
    }

    public static boolean s(String str, int i2) {
        int length = str.length();
        if (length == i2) {
            return false;
        }
        while (i2 < length) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
            i2++;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void t(b.j.e r15, android.view.View r16, java.lang.Object[] r17, androidx.databinding.ViewDataBinding.j r18, android.util.SparseIntArray r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.ViewDataBinding.t(b.j.e, android.view.View, java.lang.Object[], androidx.databinding.ViewDataBinding$j, android.util.SparseIntArray, boolean):void");
    }

    public static Object[] u(b.j.e eVar, View view, int i2, j jVar, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i2];
        t(eVar, view, objArr, jVar, sparseIntArray, true);
        return objArr;
    }

    public static int v(String str, int i2) {
        int iCharAt = 0;
        while (i2 < str.length()) {
            iCharAt = (iCharAt * 10) + (str.charAt(i2) - '0');
            i2++;
        }
        return iCharAt;
    }

    public static void w() {
        while (true) {
            Reference<? extends ViewDataBinding> referencePoll = k.poll();
            if (referencePoll == null) {
                return;
            }
            if (referencePoll instanceof l) {
                ((l) referencePoll).a();
            }
        }
    }

    public abstract void m();

    public final void n() {
        if (this.s) {
            x();
            return;
        }
        if (r()) {
            this.s = true;
            this.o = false;
            b.j.c<b.j.h, ViewDataBinding, Void> cVar = this.r;
            if (cVar != null) {
                cVar.d(this, 1, null);
                if (this.o) {
                    this.r.d(this, 2, null);
                }
            }
            if (!this.o) {
                m();
                b.j.c<b.j.h, ViewDataBinding, Void> cVar2 = this.r;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.s = false;
        }
    }

    public void o() {
        ViewDataBinding viewDataBinding = this.x;
        if (viewDataBinding == null) {
            n();
        } else {
            viewDataBinding.o();
        }
    }

    public abstract boolean r();

    public void x() {
        ViewDataBinding viewDataBinding = this.x;
        if (viewDataBinding != null) {
            viewDataBinding.x();
            return;
        }
        b.m.f fVar = this.y;
        if (fVar == null || fVar.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.n) {
                    return;
                }
                this.n = true;
                if (e) {
                    this.t.postFrameCallback(this.u);
                } else {
                    this.v.post(this.m);
                }
            }
        }
    }

    public void y(View view) {
        view.setTag(R$id.dataBinding, this);
    }

    public ViewDataBinding(Object obj, View view, int i2) {
        this(l(obj), view, i2);
    }
}
