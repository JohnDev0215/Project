package p;

import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public abstract class nw0 implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float c;
    public final int e;
    public final int k;
    public final View l;
    public mw0 m;
    public mw0 n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f160p;
    public final int[] q = new int[2];

    public nw0(View view) {
        this.l = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.c = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.e = tapTimeout;
        this.k = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        mw0 mw0Var = this.n;
        View view = this.l;
        if (mw0Var != null) {
            view.removeCallbacks(mw0Var);
        }
        mw0 mw0Var2 = this.m;
        if (mw0Var2 != null) {
            view.removeCallbacks(mw0Var2);
        }
    }

    public abstract jd2 b();

    public abstract boolean c();

    public boolean d() {
        jd2 jd2VarB = b();
        if (jd2VarB == null || !jd2VarB.a()) {
            return true;
        }
        jd2VarB.dismiss();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fe  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.nw0.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.o = false;
        this.f160p = -1;
        mw0 mw0Var = this.m;
        if (mw0Var != null) {
            this.l.removeCallbacks(mw0Var);
        }
    }
}
