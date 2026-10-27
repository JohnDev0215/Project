package defpackage;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class j3 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static j3 b;
    public static j3 c;
    public final View d;
    public final CharSequence e;
    public final int f;
    public final Runnable g = new a();
    public final Runnable h = new b();
    public int i;
    public int j;
    public k3 k;
    public boolean l;

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j3.this.g(false);
        }
    }

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j3.this.c();
        }
    }

    public j3(View view, CharSequence charSequence) {
        this.d = view;
        this.e = charSequence;
        this.f = u9.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(j3 j3Var) {
        j3 j3Var2 = b;
        if (j3Var2 != null) {
            j3Var2.a();
        }
        b = j3Var;
        if (j3Var != null) {
            j3Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        j3 j3Var = b;
        if (j3Var != null && j3Var.d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new j3(view, charSequence);
            return;
        }
        j3 j3Var2 = c;
        if (j3Var2 != null && j3Var2.d == view) {
            j3Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void a() {
        this.d.removeCallbacks(this.g);
    }

    public final void b() {
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    public void c() {
        if (c == this) {
            c = null;
            k3 k3Var = this.k;
            if (k3Var != null) {
                k3Var.c();
                this.k = null;
                b();
                this.d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (b == this) {
            e(null);
        }
        this.d.removeCallbacks(this.h);
    }

    public final void d() {
        this.d.postDelayed(this.g, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (t9.O(this.d)) {
            e(null);
            j3 j3Var = c;
            if (j3Var != null) {
                j3Var.c();
            }
            c = this;
            this.l = z;
            k3 k3Var = new k3(this.d.getContext());
            this.k = k3Var;
            k3Var.e(this.d, this.i, this.j, this.l, this.e);
            this.d.addOnAttachStateChangeListener(this);
            if (this.l) {
                j2 = 2500;
            } else {
                if ((t9.I(this.d) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.d.removeCallbacks(this.h);
            this.d.postDelayed(this.h, j2);
        }
    }

    public final boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.i) <= this.f && Math.abs(y - this.j) <= this.f) {
            return false;
        }
        this.i = x;
        this.j = y;
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.k != null && this.l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.d.isEnabled() && this.k == null && h(motionEvent)) {
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
