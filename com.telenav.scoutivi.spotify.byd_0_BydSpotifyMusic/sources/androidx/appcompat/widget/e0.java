package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: compiled from: ForwardingListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e0 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final float f1026e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f1027g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f1028h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final View f1029i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Runnable f1030j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Runnable f1031k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f1032l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1033m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final int[] f1034n = new int[2];

    /* JADX INFO: compiled from: ForwardingListener.java */
    private class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = e0.this.f1029i.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: compiled from: ForwardingListener.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e0.this.e();
        }
    }

    public e0(View view) {
        this.f1029i = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1026e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1027g = tapTimeout;
        this.f1028h = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private void a() {
        Runnable runnable = this.f1031k;
        if (runnable != null) {
            this.f1029i.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f1030j;
        if (runnable2 != null) {
            this.f1029i.removeCallbacks(runnable2);
        }
    }

    private boolean f(MotionEvent motionEvent) {
        c0 c0Var;
        View view = this.f1029i;
        j.e eVarB = b();
        if (eVarB == null || !eVarB.b() || (c0Var = (c0) eVarB.l()) == null || !c0Var.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, motionEventObtainNoHistory);
        j(c0Var, motionEventObtainNoHistory);
        boolean zE = c0Var.e(motionEventObtainNoHistory, this.f1033m);
        motionEventObtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return zE && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean g(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.f1029i
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L41
            r3 = 1
            if (r1 == r3) goto L3d
            r4 = 2
            if (r1 == r4) goto L1a
            r6 = 3
            if (r1 == r6) goto L3d
            goto L6d
        L1a:
            int r1 = r5.f1033m
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f1026e
            boolean r6 = h(r0, r4, r6, r1)
            if (r6 != 0) goto L6d
            r5.a()
            android.view.ViewParent r6 = r0.getParent()
            r6.requestDisallowInterceptTouchEvent(r3)
            return r3
        L3d:
            r5.a()
            goto L6d
        L41:
            int r6 = r6.getPointerId(r2)
            r5.f1033m = r6
            java.lang.Runnable r6 = r5.f1030j
            if (r6 != 0) goto L52
            androidx.appcompat.widget.e0$a r6 = new androidx.appcompat.widget.e0$a
            r6.<init>()
            r5.f1030j = r6
        L52:
            java.lang.Runnable r6 = r5.f1030j
            int r1 = r5.f1027g
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f1031k
            if (r6 != 0) goto L65
            androidx.appcompat.widget.e0$b r6 = new androidx.appcompat.widget.e0$b
            r6.<init>()
            r5.f1031k = r6
        L65:
            java.lang.Runnable r6 = r5.f1031k
            int r1 = r5.f1028h
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.e0.g(android.view.MotionEvent):boolean");
    }

    private static boolean h(View view, float f7, float f8, float f9) {
        float f10 = -f9;
        return f7 >= f10 && f8 >= f10 && f7 < ((float) (view.getRight() - view.getLeft())) + f9 && f8 < ((float) (view.getBottom() - view.getTop())) + f9;
    }

    private boolean i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f1034n);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    private boolean j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f1034n);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }

    public abstract j.e b();

    protected abstract boolean c();

    protected boolean d() {
        j.e eVarB = b();
        if (eVarB == null || !eVarB.b()) {
            return true;
        }
        eVarB.dismiss();
        return true;
    }

    void e() {
        a();
        View view = this.f1029i;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.f1032l = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z6;
        boolean z7 = this.f1032l;
        if (z7) {
            z6 = f(motionEvent) || !d();
        } else {
            z6 = g(motionEvent) && c();
            if (z6) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f1029i.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.f1032l = z6;
        return z6 || z7;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f1032l = false;
        this.f1033m = -1;
        Runnable runnable = this.f1030j;
        if (runnable != null) {
            this.f1029i.removeCallbacks(runnable);
        }
    }
}
