package com.bumptech.glide.request.h;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.f;
import com.bumptech.glide.o.j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CustomViewTarget.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends View, Z> implements e<Z> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3500b = f.f2857a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f3501c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final T f3502d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View.OnAttachStateChangeListener f3503e;
    private boolean f;
    private boolean g;

    /* JADX INFO: compiled from: CustomViewTarget.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static Integer f3504a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f3505b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<d> f3506c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3507d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ViewTreeObserverOnPreDrawListenerC0082a f3508e;

        /* JADX INFO: renamed from: com.bumptech.glide.request.h.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CustomViewTarget.java */
        private static final class ViewTreeObserverOnPreDrawListenerC0082a implements ViewTreeObserver.OnPreDrawListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final WeakReference<a> f3509b;

            ViewTreeObserverOnPreDrawListenerC0082a(a aVar) {
                this.f3509b = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable("CustomViewTarget", 2)) {
                    Log.v("CustomViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                a aVar = this.f3509b.get();
                if (aVar == null) {
                    return true;
                }
                aVar.a();
                return true;
            }
        }

        a(View view) {
            this.f3505b = view;
        }

        private static int c(Context context) {
            if (f3504a == null) {
                Display defaultDisplay = ((WindowManager) j.d((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                f3504a = Integer.valueOf(Math.max(point.x, point.y));
            }
            return f3504a.intValue();
        }

        private int e(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.f3507d && this.f3505b.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.f3505b.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable("CustomViewTarget", 4)) {
                Log.i("CustomViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return c(this.f3505b.getContext());
        }

        private int f() {
            int paddingTop = this.f3505b.getPaddingTop() + this.f3505b.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.f3505b.getLayoutParams();
            return e(this.f3505b.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int g() {
            int paddingLeft = this.f3505b.getPaddingLeft() + this.f3505b.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.f3505b.getLayoutParams();
            return e(this.f3505b.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private boolean h(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        private boolean i(int i, int i2) {
            return h(i) && h(i2);
        }

        private void j(int i, int i2) {
            Iterator it = new ArrayList(this.f3506c).iterator();
            while (it.hasNext()) {
                ((d) it.next()).g(i, i2);
            }
        }

        void a() {
            if (this.f3506c.isEmpty()) {
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
            ViewTreeObserver viewTreeObserver = this.f3505b.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f3508e);
            }
            this.f3508e = null;
            this.f3506c.clear();
        }

        void d(d dVar) {
            int iG = g();
            int iF = f();
            if (i(iG, iF)) {
                dVar.g(iG, iF);
                return;
            }
            if (!this.f3506c.contains(dVar)) {
                this.f3506c.add(dVar);
            }
            if (this.f3508e == null) {
                ViewTreeObserver viewTreeObserver = this.f3505b.getViewTreeObserver();
                ViewTreeObserverOnPreDrawListenerC0082a viewTreeObserverOnPreDrawListenerC0082a = new ViewTreeObserverOnPreDrawListenerC0082a(this);
                this.f3508e = viewTreeObserverOnPreDrawListenerC0082a;
                viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0082a);
            }
        }

        void k(d dVar) {
            this.f3506c.remove(dVar);
        }
    }

    public b(T t) {
        this.f3502d = (T) j.d(t);
        this.f3501c = new a(t);
    }

    private Object l() {
        return this.f3502d.getTag(f3500b);
    }

    private void m() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.f3503e;
        if (onAttachStateChangeListener == null || this.g) {
            return;
        }
        this.f3502d.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.g = true;
    }

    private void n() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.f3503e;
        if (onAttachStateChangeListener == null || !this.g) {
            return;
        }
        this.f3502d.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        this.g = false;
    }

    private void q(Object obj) {
        this.f3502d.setTag(f3500b, obj);
    }

    @Override // com.bumptech.glide.manager.i
    public void a() {
    }

    @Override // com.bumptech.glide.request.h.e
    public final void b(d dVar) {
        this.f3501c.k(dVar);
    }

    @Override // com.bumptech.glide.request.h.e
    public final void c(Drawable drawable) {
        m();
        p(drawable);
    }

    @Override // com.bumptech.glide.manager.i
    public void f() {
    }

    @Override // com.bumptech.glide.request.h.e
    public final com.bumptech.glide.request.c g() {
        Object objL = l();
        if (objL == null) {
            return null;
        }
        if (objL instanceof com.bumptech.glide.request.c) {
            return (com.bumptech.glide.request.c) objL;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    @Override // com.bumptech.glide.request.h.e
    public final void h(Drawable drawable) {
        this.f3501c.b();
        o(drawable);
        if (this.f) {
            return;
        }
        n();
    }

    @Override // com.bumptech.glide.request.h.e
    public final void i(d dVar) {
        this.f3501c.d(dVar);
    }

    @Override // com.bumptech.glide.request.h.e
    public final void j(com.bumptech.glide.request.c cVar) {
        q(cVar);
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
    }

    protected abstract void o(Drawable drawable);

    protected void p(Drawable drawable) {
    }

    public String toString() {
        return "Target for: " + this.f3502d;
    }
}
