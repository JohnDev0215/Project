package b.b.q;

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
    public static h0 f1751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h0 f1752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f1753d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CharSequence f1754e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1755f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f1756g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f1757h = new b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1758i;
    public int j;
    public i0 k;
    public boolean l;

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
        this.f1753d = view;
        this.f1754e = charSequence;
        this.f1755f = b.h.m.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(h0 h0Var) {
        h0 h0Var2 = f1751b;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f1751b = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f1751b;
        if (h0Var != null && h0Var.f1753d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f1752c;
        if (h0Var2 != null && h0Var2.f1753d == view) {
            h0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void a() {
        this.f1753d.removeCallbacks(this.f1756g);
    }

    public final void b() {
        this.f1758i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    public void c() {
        if (f1752c == this) {
            f1752c = null;
            i0 i0Var = this.k;
            if (i0Var != null) {
                i0Var.c();
                this.k = null;
                b();
                this.f1753d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1751b == this) {
            e(null);
        }
        this.f1753d.removeCallbacks(this.f1757h);
    }

    public final void d() {
        this.f1753d.postDelayed(this.f1756g, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (b.h.m.t.O(this.f1753d)) {
            e(null);
            h0 h0Var = f1752c;
            if (h0Var != null) {
                h0Var.c();
            }
            f1752c = this;
            this.l = z;
            i0 i0Var = new i0(this.f1753d.getContext());
            this.k = i0Var;
            i0Var.e(this.f1753d, this.f1758i, this.j, this.l, this.f1754e);
            this.f1753d.addOnAttachStateChangeListener(this);
            if (this.l) {
                j2 = 2500;
            } else {
                if ((b.h.m.t.I(this.f1753d) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.f1753d.removeCallbacks(this.f1757h);
            this.f1753d.postDelayed(this.f1757h, j2);
        }
    }

    public final boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f1758i) <= this.f1755f && Math.abs(y - this.j) <= this.f1755f) {
            return false;
        }
        this.f1758i = x;
        this.j = y;
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.k != null && this.l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f1753d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f1753d.isEnabled() && this.k == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f1758i = view.getWidth() / 2;
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
