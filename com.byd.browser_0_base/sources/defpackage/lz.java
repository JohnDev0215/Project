package defpackage;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import org.chromium.components.browser_ui.widget.R$bool;

/* JADX INFO: compiled from: ContextMenuDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lz extends o4 {
    public final Activity e;
    public final View f;
    public final boolean g;
    public final boolean h;
    public float i;
    public float j;
    public int k;
    public p4 l;
    public final View m;
    public View.OnLayoutChangeListener n;
    public b o;
    public final Rect p;
    public final int q;
    public final int r;
    public final Integer s;
    public final Integer t;
    public final View u;
    public final C0188h0 v;

    /* JADX INFO: compiled from: ContextMenuDialog.java */
    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (i == i5 && i3 == i7 && i2 == i6 && i4 == i8) {
                return;
            }
            final lz lzVar = lz.this;
            boolean z = lzVar.g;
            Rect rect = lzVar.p;
            if (!z) {
                if (view.getMeasuredHeight() == 0) {
                    return;
                }
                Rect rect2 = new Rect();
                lzVar.e.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
                float f = rect2.left;
                float f2 = rect2.top;
                int[] iArr = new int[2];
                View view2 = lzVar.f;
                view2.getLocationOnScreen(iArr);
                lzVar.k = iArr[1];
                lzVar.i = (rect.centerX() - iArr[0]) + f;
                float fCenterY = (rect.centerY() - iArr[1]) + f2;
                lzVar.j = fCenterY;
                view2.startAnimation(lz.b(lzVar.i, fCenterY, true));
                view.removeOnLayoutChangeListener(this);
                lzVar.n = null;
                return;
            }
            if (view.getMeasuredHeight() == 0) {
                return;
            }
            p4 p4Var = lzVar.l;
            if (p4Var != null && p4Var.c()) {
                lzVar.dismiss();
                return;
            }
            p4 p4Var2 = new p4(lzVar.e, lzVar.m, new ColorDrawable(0), lzVar.f, new z92(rect));
            lzVar.l = p4Var2;
            Integer num = lzVar.s;
            if (num != null) {
                p4Var2.q = num.intValue();
            }
            Integer num2 = lzVar.t;
            if (num2 != null) {
                lzVar.l.s = num2.intValue();
            }
            p4 p4Var3 = lzVar.l;
            p4Var3.z = true;
            p4Var3.w = true;
            p4Var3.j.setOutsideTouchable(false);
            lzVar.l.B = true;
            C0188h0 c0188h0 = lzVar.v;
            if (c0188h0 != null && c0188h0.d()) {
                lzVar.l.j.setFocusable(true);
            }
            lzVar.l.addOnDismissListener(new PopupWindow.OnDismissListener() { // from class: kz
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    lzVar.dismiss();
                }
            });
            lzVar.l.d();
        }
    }

    /* JADX INFO: compiled from: ContextMenuDialog.java */
    public class b implements View.OnDragListener {
        public b() {
        }

        @Override // android.view.View.OnDragListener
        public final boolean onDrag(View view, DragEvent dragEvent) {
            lz lzVar = lz.this;
            View view2 = lzVar.u;
            if (view2 == null || !view2.isAttachedToWindow()) {
                return false;
            }
            return lzVar.u.dispatchDragEvent(dragEvent);
        }
    }

    public lz(Activity activity, int i, int i2, int i3, View view, View view2, boolean z, boolean z2, Integer num, Integer num2, View view3, Rect rect, qo qoVar) {
        super(activity, i);
        this.e = activity;
        this.q = i2;
        this.r = i3;
        this.f = view2;
        this.m = view;
        this.g = z;
        this.h = z2;
        this.s = num;
        this.t = num2;
        this.u = view3;
        this.p = rect;
        this.v = qoVar;
    }

    public static ScaleAnimation b(float f, float f2, boolean z) {
        float f3 = z ? 0.0f : 1.0f;
        float f4 = z ? 1.0f : 0.0f;
        ScaleAnimation scaleAnimation = new ScaleAnimation(f3, f4, f3, f4, 0, f, 0, f2);
        scaleAnimation.setDuration((long) ((z ? 250L : 150L) * Settings.Global.getFloat(xz.a.getContentResolver(), "animator_duration_scale", 1.0f)));
        scaleAnimation.setInterpolator(h21.g);
        return scaleAnimation;
    }

    @Override // defpackage.o4, android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        if (!this.g) {
            View.OnLayoutChangeListener onLayoutChangeListener = this.n;
            View view = this.f;
            if (onLayoutChangeListener != null) {
                view.removeOnLayoutChangeListener(onLayoutChangeListener);
                this.n = null;
            }
            view.getLocationOnScreen(new int[2]);
            ScaleAnimation scaleAnimationB = b(this.i, this.j + (this.k - r0[1]), false);
            scaleAnimationB.setAnimationListener(new c());
            view.startAnimation(scaleAnimationB);
            return;
        }
        p4 p4Var = this.l;
        if (p4Var != null) {
            p4Var.a();
            this.l = null;
        }
        View.OnLayoutChangeListener onLayoutChangeListener2 = this.n;
        View view2 = this.m;
        if (onLayoutChangeListener2 != null) {
            view2.removeOnLayoutChangeListener(onLayoutChangeListener2);
            this.n = null;
        }
        if (this.o != null) {
            view2.setOnDragListener(null);
            this.o = null;
        }
        super.dismiss();
    }

    @Override // android.app.Dialog
    public final void onStart() {
        int i;
        super.onStart();
        Window window = getWindow();
        boolean z = false;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setLayout(-1, -1);
        boolean z2 = this.h;
        if (z2) {
            window.clearFlags(2);
            window.addFlags(32);
            window.addFlags(Integer.MIN_VALUE);
            Activity activity = this.e;
            window.setNavigationBarColor(activity.getWindow().getNavigationBarColor());
            uh3.m(window.getDecorView(), activity.getResources().getBoolean(R$bool.window_light_navigation_bar));
            int statusBarColor = activity.getWindow().getStatusBarColor();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(statusBarColor);
            View rootView = window.getDecorView().getRootView();
            boolean z3 = !z7.h0(activity.getWindow().getStatusBarColor());
            int systemUiVisibility = rootView.getSystemUiVisibility();
            rootView.setSystemUiVisibility(z3 ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        }
        View view = this.f;
        int i2 = this.q;
        if (i2 != -1 && (i = this.r) != -1) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.bottomMargin = i;
            layoutParams.topMargin = i2;
        }
        a aVar = new a();
        this.n = aVar;
        boolean z4 = this.g;
        View view2 = this.m;
        if (z4) {
            view = view2;
        }
        view.addOnLayoutChangeListener(aVar);
        if (z4 && z2 && this.u != null) {
            z = true;
        }
        if (z) {
            b bVar = new b();
            this.o = bVar;
            view2.setOnDragListener(bVar);
        }
    }

    @Override // android.app.Dialog
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            dismiss();
            return true;
        }
        boolean z = this.g;
        View view = this.u;
        if ((z && this.h && view != null) && view.isAttachedToWindow()) {
            return view.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    /* JADX INFO: compiled from: ContextMenuDialog.java */
    public class c implements Animation.AnimationListener {
        public c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            lz.super.dismiss();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }
}
