package r;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static h0 f7549d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static h0 f7550e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View f7551f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CharSequence f7552g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f7553h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Runnable f7554i = new a();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Runnable f7555j = new b();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7556k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7557l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public i0 f7558m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f7559n;

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
        this.f7551f = view;
        this.f7552g = charSequence;
        this.f7553h = o0.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void e(h0 h0Var) {
        h0 h0Var2 = f7549d;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f7549d = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f7549d;
        if (h0Var != null && h0Var.f7551f == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f7550e;
        if (h0Var2 != null && h0Var2.f7551f == view) {
            h0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void a() {
        this.f7551f.removeCallbacks(this.f7554i);
    }

    public final void b() {
        this.f7556k = Integer.MAX_VALUE;
        this.f7557l = Integer.MAX_VALUE;
    }

    public void c() {
        if (f7550e == this) {
            f7550e = null;
            i0 i0Var = this.f7558m;
            if (i0Var != null) {
                i0Var.c();
                this.f7558m = null;
                b();
                this.f7551f.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f7549d == this) {
            e(null);
        }
        this.f7551f.removeCallbacks(this.f7555j);
    }

    public final void d() {
        this.f7551f.postDelayed(this.f7554i, ViewConfiguration.getLongPressTimeout());
    }

    public void g(boolean z7) {
        long j8;
        int longPressTimeout;
        long j9;
        if (o0.t.P(this.f7551f)) {
            e(null);
            h0 h0Var = f7550e;
            if (h0Var != null) {
                h0Var.c();
            }
            f7550e = this;
            this.f7559n = z7;
            i0 i0Var = new i0(this.f7551f.getContext());
            this.f7558m = i0Var;
            i0Var.e(this.f7551f, this.f7556k, this.f7557l, this.f7559n, this.f7552g);
            this.f7551f.addOnAttachStateChangeListener(this);
            if (this.f7559n) {
                j9 = 2500;
            } else {
                if ((o0.t.J(this.f7551f) & 1) == 1) {
                    j8 = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j8 = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j9 = j8 - ((long) longPressTimeout);
            }
            this.f7551f.removeCallbacks(this.f7555j);
            this.f7551f.postDelayed(this.f7555j, j9);
        }
    }

    public final boolean h(MotionEvent motionEvent) {
        int x7 = (int) motionEvent.getX();
        int y7 = (int) motionEvent.getY();
        if (Math.abs(x7 - this.f7556k) <= this.f7553h && Math.abs(y7 - this.f7557l) <= this.f7553h) {
            return false;
        }
        this.f7556k = x7;
        this.f7557l = y7;
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.f7558m != null && this.f7559n) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f7551f.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f7551f.isEnabled() && this.f7558m == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f7556k = view.getWidth() / 2;
        this.f7557l = view.getHeight() / 2;
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
