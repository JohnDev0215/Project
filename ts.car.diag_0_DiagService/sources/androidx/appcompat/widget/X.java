package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
class X implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static X l;
    private static X m;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f340c;
    private final CharSequence d;
    private final int e;
    private final Runnable f = new a();
    private final Runnable g = new b();
    private int h;
    private int i;
    private Y j;
    private boolean k;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            X.this.g(false);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            X.this.c();
        }
    }

    private X(View view, CharSequence charSequence) {
        this.f340c = view;
        this.d = charSequence;
        this.e = androidx.core.view.s.c(ViewConfiguration.get(view.getContext()));
        b();
        this.f340c.setOnLongClickListener(this);
        this.f340c.setOnHoverListener(this);
    }

    private void a() {
        this.f340c.removeCallbacks(this.f);
    }

    private void b() {
        this.h = Integer.MAX_VALUE;
        this.i = Integer.MAX_VALUE;
    }

    private void d() {
        this.f340c.postDelayed(this.f, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(X x) {
        X x2 = l;
        if (x2 != null) {
            x2.a();
        }
        l = x;
        if (x != null) {
            x.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        X x = l;
        if (x != null && x.f340c == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new X(view, charSequence);
            return;
        }
        X x2 = m;
        if (x2 != null && x2.f340c == view) {
            x2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.h) <= this.e && Math.abs(y - this.i) <= this.e) {
            return false;
        }
        this.h = x;
        this.i = y;
        return true;
    }

    void c() {
        if (m == this) {
            m = null;
            Y y = this.j;
            if (y != null) {
                y.c();
                this.j = null;
                b();
                this.f340c.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (l == this) {
            e(null);
        }
        this.f340c.removeCallbacks(this.g);
    }

    void g(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (ViewCompat.A(this.f340c)) {
            e(null);
            X x = m;
            if (x != null) {
                x.c();
            }
            m = this;
            this.k = z;
            Y y = new Y(this.f340c.getContext());
            this.j = y;
            y.e(this.f340c, this.h, this.i, this.k, this.d);
            this.f340c.addOnAttachStateChangeListener(this);
            if (this.k) {
                j2 = 2500;
            } else {
                if ((ViewCompat.w(this.f340c) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.f340c.removeCallbacks(this.g);
            this.f340c.postDelayed(this.g, j2);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.j != null && this.k) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f340c.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f340c.isEnabled() && this.j == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.h = view.getWidth() / 2;
        this.i = view.getHeight() / 2;
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
