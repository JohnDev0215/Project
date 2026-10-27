package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: loaded from: classes.dex */
class y0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static y0 f1068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static y0 f1069c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final View f1070d;
    private final CharSequence e;
    private final int f;
    private final Runnable g = new a();
    private final Runnable h = new b();
    private int i;
    private int j;
    private z0 k;
    private boolean l;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y0.this.g(false);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y0.this.c();
        }
    }

    private y0(View view, CharSequence charSequence) {
        this.f1070d = view;
        this.e = charSequence;
        this.f = a.g.k.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void a() {
        this.f1070d.removeCallbacks(this.g);
    }

    private void b() {
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    private void d() {
        this.f1070d.postDelayed(this.g, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(y0 y0Var) {
        y0 y0Var2 = f1068b;
        if (y0Var2 != null) {
            y0Var2.a();
        }
        f1068b = y0Var;
        if (y0Var != null) {
            y0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        y0 y0Var = f1068b;
        if (y0Var != null && y0Var.f1070d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new y0(view, charSequence);
            return;
        }
        y0 y0Var2 = f1069c;
        if (y0Var2 != null && y0Var2.f1070d == view) {
            y0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.i) <= this.f && Math.abs(y - this.j) <= this.f) {
            return false;
        }
        this.i = x;
        this.j = y;
        return true;
    }

    void c() {
        if (f1069c == this) {
            f1069c = null;
            z0 z0Var = this.k;
            if (z0Var != null) {
                z0Var.c();
                this.k = null;
                b();
                this.f1070d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1068b == this) {
            e(null);
        }
        this.f1070d.removeCallbacks(this.h);
    }

    void g(boolean z) {
        long longPressTimeout;
        if (a.g.k.t.M(this.f1070d)) {
            e(null);
            y0 y0Var = f1069c;
            if (y0Var != null) {
                y0Var.c();
            }
            f1069c = this;
            this.l = z;
            z0 z0Var = new z0(this.f1070d.getContext());
            this.k = z0Var;
            z0Var.e(this.f1070d, this.i, this.j, this.l, this.e);
            this.f1070d.addOnAttachStateChangeListener(this);
            if (this.l) {
                longPressTimeout = 2500;
            } else {
                longPressTimeout = ((a.g.k.t.G(this.f1070d) & 1) == 1 ? 3000L : 15000L) - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.f1070d.removeCallbacks(this.h);
            this.f1070d.postDelayed(this.h, longPressTimeout);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.k != null && this.l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f1070d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f1070d.isEnabled() && this.k == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.i = view.getWidth() / 2;
        this.j = view.getHeight() / 2;
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
