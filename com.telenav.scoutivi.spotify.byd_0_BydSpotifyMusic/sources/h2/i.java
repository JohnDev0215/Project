package h2;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import k2.j;

/* JADX INFO: compiled from: ViewTarget.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class i<T extends View, Z> extends h2.a<Z> {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static boolean f6598k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static int f6599l = com.bumptech.glide.h.glide_custom_view_target_tag;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final T f6600e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final a f6601g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View.OnAttachStateChangeListener f6602h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f6603i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f6604j;

    /* JADX INFO: compiled from: ViewTarget.java */
    static final class a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        static Integer f6605e;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f6606a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<g> f6607b = new ArrayList();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f6608c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ViewTreeObserverOnPreDrawListenerC0079a f6609d;

        /* JADX INFO: renamed from: h2.i$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ViewTarget.java */
        private static final class ViewTreeObserverOnPreDrawListenerC0079a implements ViewTreeObserver.OnPreDrawListener {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private final WeakReference<a> f6610e;

            ViewTreeObserverOnPreDrawListenerC0079a(a aVar) {
                this.f6610e = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    Log.v("ViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                a aVar = this.f6610e.get();
                if (aVar == null) {
                    return true;
                }
                aVar.a();
                return true;
            }
        }

        a(View view) {
            this.f6606a = view;
        }

        private static int c(Context context) {
            if (f6605e == null) {
                Display defaultDisplay = ((WindowManager) j.d((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                f6605e = Integer.valueOf(Math.max(point.x, point.y));
            }
            return f6605e.intValue();
        }

        private int e(int i7, int i8, int i9) {
            int i10 = i8 - i9;
            if (i10 > 0) {
                return i10;
            }
            if (this.f6608c && this.f6606a.isLayoutRequested()) {
                return 0;
            }
            int i11 = i7 - i9;
            if (i11 > 0) {
                return i11;
            }
            if (this.f6606a.isLayoutRequested() || i8 != -2) {
                return 0;
            }
            if (Log.isLoggable("ViewTarget", 4)) {
                Log.i("ViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return c(this.f6606a.getContext());
        }

        private int f() {
            int paddingTop = this.f6606a.getPaddingTop() + this.f6606a.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.f6606a.getLayoutParams();
            return e(this.f6606a.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int g() {
            int paddingLeft = this.f6606a.getPaddingLeft() + this.f6606a.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.f6606a.getLayoutParams();
            return e(this.f6606a.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private boolean h(int i7) {
            return i7 > 0 || i7 == Integer.MIN_VALUE;
        }

        private boolean i(int i7, int i8) {
            return h(i7) && h(i8);
        }

        private void j(int i7, int i8) {
            Iterator it = new ArrayList(this.f6607b).iterator();
            while (it.hasNext()) {
                ((g) it.next()).f(i7, i8);
            }
        }

        void a() {
            if (this.f6607b.isEmpty()) {
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
            ViewTreeObserver viewTreeObserver = this.f6606a.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f6609d);
            }
            this.f6609d = null;
            this.f6607b.clear();
        }

        void d(g gVar) {
            int iG = g();
            int iF = f();
            if (i(iG, iF)) {
                gVar.f(iG, iF);
                return;
            }
            if (!this.f6607b.contains(gVar)) {
                this.f6607b.add(gVar);
            }
            if (this.f6609d == null) {
                ViewTreeObserver viewTreeObserver = this.f6606a.getViewTreeObserver();
                ViewTreeObserverOnPreDrawListenerC0079a viewTreeObserverOnPreDrawListenerC0079a = new ViewTreeObserverOnPreDrawListenerC0079a(this);
                this.f6609d = viewTreeObserverOnPreDrawListenerC0079a;
                viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0079a);
            }
        }

        void k(g gVar) {
            this.f6607b.remove(gVar);
        }
    }

    public i(T t6) {
        this.f6600e = (T) j.d(t6);
        this.f6601g = new a(t6);
    }

    private Object m() {
        return this.f6600e.getTag(f6599l);
    }

    private void n() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.f6602h;
        if (onAttachStateChangeListener == null || this.f6604j) {
            return;
        }
        this.f6600e.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.f6604j = true;
    }

    private void o() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.f6602h;
        if (onAttachStateChangeListener == null || !this.f6604j) {
            return;
        }
        this.f6600e.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        this.f6604j = false;
    }

    private void p(Object obj) {
        f6598k = true;
        this.f6600e.setTag(f6599l, obj);
    }

    @Override // h2.a, h2.h
    public void d(Drawable drawable) {
        super.d(drawable);
        n();
    }

    @Override // h2.h
    public void e(g gVar) {
        this.f6601g.d(gVar);
    }

    @Override // h2.h
    public g2.c h() {
        Object objM = m();
        if (objM == null) {
            return null;
        }
        if (objM instanceof g2.c) {
            return (g2.c) objM;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    @Override // h2.a, h2.h
    public void i(Drawable drawable) {
        super.i(drawable);
        this.f6601g.b();
        if (this.f6603i) {
            return;
        }
        o();
    }

    @Override // h2.h
    public void j(g gVar) {
        this.f6601g.k(gVar);
    }

    @Override // h2.h
    public void l(g2.c cVar) {
        p(cVar);
    }

    public String toString() {
        return "Target for: " + this.f6600e;
    }
}
