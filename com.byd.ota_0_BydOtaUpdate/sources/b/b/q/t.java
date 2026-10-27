package b.b.q;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: compiled from: ForwardingListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public Runnable E3;
    public boolean F3;
    public int G3;
    public final int[] H3 = new int[2];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1099c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1100d;
    public final int s;
    public final View x;
    public Runnable y;

    /* JADX INFO: compiled from: ForwardingListener.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = t.this.x.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: compiled from: ForwardingListener.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            t.this.e();
        }
    }

    public t(View view) {
        this.x = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1099c = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1100d = tapTimeout;
        this.s = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public static boolean h(View view, float f2, float f3, float f4) {
        float f5 = -f4;
        return f2 >= f5 && f3 >= f5 && f2 < ((float) (view.getRight() - view.getLeft())) + f4 && f3 < ((float) (view.getBottom() - view.getTop())) + f4;
    }

    public final void a() {
        Runnable runnable = this.E3;
        if (runnable != null) {
            this.x.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.y;
        if (runnable2 != null) {
            this.x.removeCallbacks(runnable2);
        }
    }

    public abstract b.b.p.j.p b();

    public abstract boolean c();

    public boolean d() {
        b.b.p.j.p pVarB = b();
        if (pVarB == null || !pVarB.b()) {
            return true;
        }
        pVarB.dismiss();
        return true;
    }

    public void e() {
        a();
        View view = this.x;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.F3 = true;
        }
    }

    public final boolean f(MotionEvent motionEvent) {
        r rVar;
        View view = this.x;
        b.b.p.j.p pVarB = b();
        if (pVarB == null || !pVarB.b() || (rVar = (r) pVarB.k()) == null || !rVar.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, motionEventObtainNoHistory);
        j(rVar, motionEventObtainNoHistory);
        boolean zE = rVar.e(motionEventObtainNoHistory, this.G3);
        motionEventObtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return zE && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean g(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.x
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
            int r1 = r5.G3
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f1099c
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
            r5.G3 = r6
            java.lang.Runnable r6 = r5.y
            if (r6 != 0) goto L52
            b.b.q.t$a r6 = new b.b.q.t$a
            r6.<init>()
            r5.y = r6
        L52:
            java.lang.Runnable r6 = r5.y
            int r1 = r5.f1100d
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.E3
            if (r6 != 0) goto L65
            b.b.q.t$b r6 = new b.b.q.t$b
            r6.<init>()
            r5.E3 = r6
        L65:
            java.lang.Runnable r6 = r5.E3
            int r1 = r5.s
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.q.t.g(android.view.MotionEvent):boolean");
    }

    public final boolean i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.H3);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    public final boolean j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.H3);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.F3;
        if (z2) {
            z = f(motionEvent) || !d();
        } else {
            z = g(motionEvent) && c();
            if (z) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                this.x.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.F3 = z;
        return z || z2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.F3 = false;
        this.G3 = -1;
        Runnable runnable = this.y;
        if (runnable != null) {
            this.x.removeCallbacks(runnable);
        }
    }
}
