package com.bumptech.glide.r.j;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.h;
import com.bumptech.glide.t.j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends View, Z> implements e<Z> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2519b = h.f1945a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f2520c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final T f2521d;
    private View.OnAttachStateChangeListener e;
    private boolean f;
    private boolean g;

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static Integer f2522a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f2523b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<d> f2524c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2525d;
        private ViewTreeObserverOnPreDrawListenerC0086a e;

        /* JADX INFO: renamed from: com.bumptech.glide.r.j.b$a$a, reason: collision with other inner class name */
        private static final class ViewTreeObserverOnPreDrawListenerC0086a implements ViewTreeObserver.OnPreDrawListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final WeakReference<a> f2526b;

            ViewTreeObserverOnPreDrawListenerC0086a(a aVar) {
                this.f2526b = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable("CustomViewTarget", 2)) {
                    Log.v("CustomViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                a aVar = this.f2526b.get();
                if (aVar == null) {
                    return true;
                }
                aVar.a();
                return true;
            }
        }

        a(View view) {
            this.f2523b = view;
        }

        private static int c(Context context) {
            if (f2522a == null) {
                Display defaultDisplay = ((WindowManager) j.d((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                f2522a = Integer.valueOf(Math.max(point.x, point.y));
            }
            return f2522a.intValue();
        }

        private int e(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.f2525d && this.f2523b.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.f2523b.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable("CustomViewTarget", 4)) {
                Log.i("CustomViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return c(this.f2523b.getContext());
        }

        private int f() {
            int paddingTop = this.f2523b.getPaddingTop() + this.f2523b.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.f2523b.getLayoutParams();
            return e(this.f2523b.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int g() {
            int paddingLeft = this.f2523b.getPaddingLeft() + this.f2523b.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.f2523b.getLayoutParams();
            return e(this.f2523b.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private boolean h(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        private boolean i(int i, int i2) {
            return h(i) && h(i2);
        }

        private void j(int i, int i2) {
            Iterator it = new ArrayList(this.f2524c).iterator();
            while (it.hasNext()) {
                ((d) it.next()).g(i, i2);
            }
        }

        void a() {
            if (this.f2524c.isEmpty()) {
                return;
            }
            int iG = g();
            int iF = f();
            if (i(iG, iF)) {
                j(iG, iF);
                b();
            }
        }

        void b() {
            ViewTreeObserver viewTreeObserver = this.f2523b.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.e);
            }
            this.e = null;
            this.f2524c.clear();
        }

        void d(d dVar) {
            int iG = g();
            int iF = f();
            if (i(iG, iF)) {
                dVar.g(iG, iF);
                return;
            }
            if (!this.f2524c.contains(dVar)) {
                this.f2524c.add(dVar);
            }
            if (this.e == null) {
                ViewTreeObserver viewTreeObserver = this.f2523b.getViewTreeObserver();
                ViewTreeObserverOnPreDrawListenerC0086a viewTreeObserverOnPreDrawListenerC0086a = new ViewTreeObserverOnPreDrawListenerC0086a(this);
                this.e = viewTreeObserverOnPreDrawListenerC0086a;
                viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0086a);
            }
        }

        void k(d dVar) {
            this.f2524c.remove(dVar);
        }
    }

    public b(T t) {
        this.f2521d = (T) j.d(t);
        this.f2520c = new a(t);
    }

    private Object l() {
        return this.f2521d.getTag(f2519b);
    }

    private void m() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.e;
        if (onAttachStateChangeListener == null || this.g) {
            return;
        }
        this.f2521d.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.g = true;
    }

    private void n() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.e;
        if (onAttachStateChangeListener == null || !this.g) {
            return;
        }
        this.f2521d.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        this.g = false;
    }

    private void q(Object obj) {
        this.f2521d.setTag(f2519b, obj);
    }

    @Override // com.bumptech.glide.o.i
    public void a() {
    }

    @Override // com.bumptech.glide.r.j.e
    public final void b(d dVar) {
        this.f2520c.k(dVar);
    }

    @Override // com.bumptech.glide.r.j.e
    public final void c(Drawable drawable) {
        m();
        p(drawable);
    }

    @Override // com.bumptech.glide.o.i
    public void f() {
    }

    @Override // com.bumptech.glide.r.j.e
    public final com.bumptech.glide.r.c g() {
        Object objL = l();
        if (objL == null) {
            return null;
        }
        if (objL instanceof com.bumptech.glide.r.c) {
            return (com.bumptech.glide.r.c) objL;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    @Override // com.bumptech.glide.r.j.e
    public final void h(Drawable drawable) {
        this.f2520c.b();
        o(drawable);
        if (this.f) {
            return;
        }
        n();
    }

    @Override // com.bumptech.glide.r.j.e
    public final void i(d dVar) {
        this.f2520c.d(dVar);
    }

    @Override // com.bumptech.glide.r.j.e
    public final void j(com.bumptech.glide.r.c cVar) {
        q(cVar);
    }

    @Override // com.bumptech.glide.o.i
    public void k() {
    }

    protected abstract void o(Drawable drawable);

    protected void p(Drawable drawable) {
    }

    public String toString() {
        return "Target for: " + this.f2521d;
    }
}
