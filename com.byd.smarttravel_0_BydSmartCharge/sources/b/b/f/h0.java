package b.b.f;

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
    public static h0 f784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h0 f785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f786d;
    public final CharSequence e;
    public final int f;
    public final Runnable g = new a();
    public final Runnable h = new b();
    public int i;
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
        this.f786d = view;
        this.e = charSequence;
        this.f = b.g.j.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(h0 h0Var) {
        h0 h0Var2 = f784b;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f784b = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f784b;
        if (h0Var != null && h0Var.f786d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f785c;
        if (h0Var2 != null && h0Var2.f786d == view) {
            h0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void a() {
        this.f786d.removeCallbacks(this.g);
    }

    public final void b() {
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    public void c() {
        if (f785c == this) {
            f785c = null;
            i0 i0Var = this.k;
            if (i0Var != null) {
                i0Var.c();
                this.k = null;
                b();
                this.f786d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f784b == this) {
            e(null);
        }
        this.f786d.removeCallbacks(this.h);
    }

    public final void d() {
        this.f786d.postDelayed(this.g, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (b.g.j.t.O(this.f786d)) {
            e(null);
            h0 h0Var = f785c;
            if (h0Var != null) {
                h0Var.c();
            }
            f785c = this;
            this.l = z;
            i0 i0Var = new i0(this.f786d.getContext());
            this.k = i0Var;
            i0Var.e(this.f786d, this.i, this.j, this.l, this.e);
            this.f786d.addOnAttachStateChangeListener(this);
            if (this.l) {
                j2 = 2500;
            } else {
                if ((b.g.j.t.I(this.f786d) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.f786d.removeCallbacks(this.h);
            this.f786d.postDelayed(this.h, j2);
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
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f786d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f786d.isEnabled() && this.k == null && h(motionEvent)) {
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
