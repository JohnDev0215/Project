package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
class i0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static i0 f640b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static i0 f641c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final View f642d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final CharSequence f643e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f644f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Runnable f645g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Runnable f646h = new b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f647i;
    private int j;
    private j0 k;
    private boolean l;

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i0.this.g(false);
        }
    }

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i0.this.c();
        }
    }

    private i0(View view, CharSequence charSequence) {
        this.f642d = view;
        this.f643e = charSequence;
        this.f644f = b.g.k.u.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void a() {
        this.f642d.removeCallbacks(this.f645g);
    }

    private void b() {
        this.f647i = Integer.MAX_VALUE;
        this.j = Integer.MAX_VALUE;
    }

    private void d() {
        this.f642d.postDelayed(this.f645g, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(i0 i0Var) {
        i0 i0Var2 = f640b;
        if (i0Var2 != null) {
            i0Var2.a();
        }
        f640b = i0Var;
        if (i0Var != null) {
            i0Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        i0 i0Var = f640b;
        if (i0Var != null && i0Var.f642d == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new i0(view, charSequence);
            return;
        }
        i0 i0Var2 = f641c;
        if (i0Var2 != null && i0Var2.f642d == view) {
            i0Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f647i) <= this.f644f && Math.abs(y - this.j) <= this.f644f) {
            return false;
        }
        this.f647i = x;
        this.j = y;
        return true;
    }

    void c() {
        if (f641c == this) {
            f641c = null;
            j0 j0Var = this.k;
            if (j0Var != null) {
                j0Var.c();
                this.k = null;
                b();
                this.f642d.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f640b == this) {
            e(null);
        }
        this.f642d.removeCallbacks(this.f646h);
    }

    void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (b.g.k.t.O(this.f642d)) {
            e(null);
            i0 i0Var = f641c;
            if (i0Var != null) {
                i0Var.c();
            }
            f641c = this;
            this.l = z;
            j0 j0Var = new j0(this.f642d.getContext());
            this.k = j0Var;
            j0Var.e(this.f642d, this.f647i, this.j, this.l, this.f643e);
            this.f642d.addOnAttachStateChangeListener(this);
            if (this.l) {
                j2 = 2500;
            } else {
                if ((b.g.k.t.I(this.f642d) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.f642d.removeCallbacks(this.f646h);
            this.f642d.postDelayed(this.f646h, j2);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.k != null && this.l) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f642d.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f642d.isEnabled() && this.k == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f647i = view.getWidth() / 2;
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
