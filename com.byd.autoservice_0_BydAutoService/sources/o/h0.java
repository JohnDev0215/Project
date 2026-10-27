package o;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static h0 f4290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h0 f4291c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f4292d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CharSequence f4293e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4294f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f4295g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f4296h = new b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4297i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4298j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public i0 f4299k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4300l;

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h0.this.g(false);
        }
    }

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h0.this.c();
        }
    }

    public h0(View view, CharSequence charSequence) {
        this.f4292d = view;
        this.f4293e = charSequence;
        this.f4294f = l0.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(h0 h0Var) {
        h0 h0Var2 = f4290b;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f4290b = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f4290b;
        if (h0Var != null && h0Var.f4292d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f4291c;
        if (h0Var2 != null && h0Var2.f4292d == view) {
            h0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void a() {
        this.f4292d.removeCallbacks(this.f4295g);
    }

    public final void b() {
        this.f4297i = Integer.MAX_VALUE;
        this.f4298j = Integer.MAX_VALUE;
    }

    public void c() {
        if (f4291c == this) {
            f4291c = null;
            i0 i0Var = this.f4299k;
            if (i0Var != null) {
                i0Var.c();
                this.f4299k = null;
                b();
                this.f4292d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f4290b == this) {
            e(null);
        }
        this.f4292d.removeCallbacks(this.f4296h);
    }

    public final void d() {
        this.f4292d.postDelayed(this.f4295g, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z3) {
        long j3;
        int longPressTimeout;
        long j4;
        if (l0.t.O(this.f4292d)) {
            e(null);
            h0 h0Var = f4291c;
            if (h0Var != null) {
                h0Var.c();
            }
            f4291c = this;
            this.f4300l = z3;
            i0 i0Var = new i0(this.f4292d.getContext());
            this.f4299k = i0Var;
            i0Var.e(this.f4292d, this.f4297i, this.f4298j, this.f4300l, this.f4293e);
            this.f4292d.addOnAttachStateChangeListener(this);
            if (this.f4300l) {
                j4 = 2500;
            } else {
                if ((l0.t.I(this.f4292d) & 1) == 1) {
                    j3 = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j3 = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j4 = j3 - ((long) longPressTimeout);
            }
            this.f4292d.removeCallbacks(this.f4296h);
            this.f4292d.postDelayed(this.f4296h, j4);
        }
    }

    public final boolean h(MotionEvent motionEvent) {
        int x3 = (int) motionEvent.getX();
        int y3 = (int) motionEvent.getY();
        if (Math.abs(x3 - this.f4297i) <= this.f4294f && Math.abs(y3 - this.f4298j) <= this.f4294f) {
            return false;
        }
        this.f4297i = x3;
        this.f4298j = y3;
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.f4299k != null && this.f4300l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f4292d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f4292d.isEnabled() && this.f4299k == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f4297i = view.getWidth() / 2;
        this.f4298j = view.getHeight() / 2;
        g(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        c();
    }
}
