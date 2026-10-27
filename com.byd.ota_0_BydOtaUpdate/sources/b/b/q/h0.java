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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h0 f1042c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static h0 f1043d;
    public final Runnable E3 = new a();
    public final Runnable F3 = new b();
    public int G3;
    public int H3;
    public i0 I3;
    public boolean J3;
    public final View s;
    public final CharSequence x;
    public final int y;

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
        this.s = view;
        this.x = charSequence;
        this.y = b.f.m.t.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(h0 h0Var) {
        h0 h0Var2 = f1042c;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f1042c = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f1042c;
        if (h0Var != null && h0Var.s == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f1043d;
        if (h0Var2 != null && h0Var2.s == view) {
            h0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void a() {
        this.s.removeCallbacks(this.E3);
    }

    public final void b() {
        this.G3 = Integer.MAX_VALUE;
        this.H3 = Integer.MAX_VALUE;
    }

    public void c() {
        if (f1043d == this) {
            f1043d = null;
            i0 i0Var = this.I3;
            if (i0Var != null) {
                i0Var.c();
                this.I3 = null;
                b();
                this.s.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1042c == this) {
            e(null);
        }
        this.s.removeCallbacks(this.F3);
    }

    public final void d() {
        this.s.postDelayed(this.E3, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (b.f.m.s.F(this.s)) {
            e(null);
            h0 h0Var = f1043d;
            if (h0Var != null) {
                h0Var.c();
            }
            f1043d = this;
            this.J3 = z;
            i0 i0Var = new i0(this.s.getContext());
            this.I3 = i0Var;
            i0Var.e(this.s, this.G3, this.H3, this.J3, this.x);
            this.s.addOnAttachStateChangeListener(this);
            if (this.J3) {
                j2 = 2500;
            } else {
                if ((b.f.m.s.B(this.s) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.s.removeCallbacks(this.F3);
            this.s.postDelayed(this.F3, j2);
        }
    }

    public final boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.G3) <= this.y && Math.abs(y - this.H3) <= this.y) {
            return false;
        }
        this.G3 = x;
        this.H3 = y;
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.I3 != null && this.J3) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.s.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.s.isEnabled() && this.I3 == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.G3 = view.getWidth() / 2;
        this.H3 = view.getHeight() / 2;
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
