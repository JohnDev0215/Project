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
    private static h0 f452b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static h0 f453c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final View f454d;
    private final CharSequence e;
    private final int f;
    private final Runnable g = new a();
    private final Runnable h = new b();
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
        this.f454d = view;
        this.e = charSequence;
        this.f = b.g.k.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void a() {
        this.f454d.removeCallbacks(this.g);
    }

    private void b() {
        this.i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    private void d() {
        this.f454d.postDelayed(this.g, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(h0 h0Var) {
        h0 h0Var2 = f452b;
        if (h0Var2 != null) {
            h0Var2.a();
        }
        f452b = h0Var;
        if (h0Var != null) {
            h0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        h0 h0Var = f452b;
        if (h0Var != null && h0Var.f454d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new h0(view, charSequence);
            return;
        }
        h0 h0Var2 = f453c;
        if (h0Var2 != null && h0Var2.f454d == view) {
            h0Var2.c();
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
        if (f453c == this) {
            f453c = null;
            i0 i0Var = this.k;
            if (i0Var != null) {
                i0Var.c();
                this.k = null;
                b();
                this.f454d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f452b == this) {
            e(null);
        }
        this.f454d.removeCallbacks(this.h);
    }

    void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (b.g.k.t.O(this.f454d)) {
            e(null);
            h0 h0Var = f453c;
            if (h0Var != null) {
                h0Var.c();
            }
            f453c = this;
            this.l = z;
            i0 i0Var = new i0(this.f454d.getContext());
            this.k = i0Var;
            i0Var.e(this.f454d, this.i, this.j, this.l, this.e);
            this.f454d.addOnAttachStateChangeListener(this);
            if (this.l) {
                j2 = 2500;
            } else {
                if ((b.g.k.t.I(this.f454d) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.f454d.removeCallbacks(this.h);
            this.f454d.postDelayed(this.h, j2);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.k != null && this.l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f454d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f454d.isEnabled() && this.k == null && h(motionEvent)) {
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
