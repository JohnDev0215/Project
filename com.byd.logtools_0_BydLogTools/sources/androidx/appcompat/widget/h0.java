package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
class h0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static h0 f618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static h0 f619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final View f620d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final CharSequence f621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Runnable f623g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Runnable f624h = new b();
    private int i;
    private int j;
    private i0 k;
    private boolean l;

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h0.this.g(false);
        }
    }

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h0.this.c();
        }
    }

    private h0(View view, CharSequence charSequence) {
        this.f620d = view;
        this.f621e = charSequence;
        this.f622f = b.g.k.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void a() {
        this.f620d.removeCallbacks(this.f623g);
    }

    private void b() {
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    private void d() {
        this.f620d.postDelayed(this.f623g, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(h0 h0Var) {
        h0 h0Var2 = f618b;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f618b = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f618b;
        if (h0Var != null && h0Var.f620d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f619c;
        if (h0Var2 != null && h0Var2.f620d == view) {
            h0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.i) <= this.f622f && Math.abs(y - this.j) <= this.f622f) {
            return false;
        }
        this.i = x;
        this.j = y;
        return true;
    }

    void c() {
        if (f619c == this) {
            f619c = null;
            i0 i0Var = this.k;
            if (i0Var != null) {
                i0Var.c();
                this.k = null;
                b();
                this.f620d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f618b == this) {
            e(null);
        }
        this.f620d.removeCallbacks(this.f624h);
    }

    void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (b.g.k.t.O(this.f620d)) {
            e(null);
            h0 h0Var = f619c;
            if (h0Var != null) {
                h0Var.c();
            }
            f619c = this;
            this.l = z;
            i0 i0Var = new i0(this.f620d.getContext());
            this.k = i0Var;
            i0Var.e(this.f620d, this.i, this.j, this.l, this.f621e);
            this.f620d.addOnAttachStateChangeListener(this);
            if (this.l) {
                j2 = 2500;
            } else {
                if ((b.g.k.t.I(this.f620d) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.f620d.removeCallbacks(this.f624h);
            this.f620d.postDelayed(this.f624h, j2);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.k != null && this.l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f620d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f620d.isEnabled() && this.k == null && h(motionEvent)) {
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
