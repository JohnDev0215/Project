package o;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: compiled from: ForwardingListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4377b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4378c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4379d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f4380e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Runnable f4381f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Runnable f4382g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4383h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4384i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f4385j = new int[2];

    /* JADX INFO: compiled from: ForwardingListener.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = t.this.f4380e.getParent();
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
        this.f4380e = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f4377b = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f4378c = tapTimeout;
        this.f4379d = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public static boolean h(View view, float f3, float f4, float f5) {
        float f6 = -f5;
        return f3 >= f6 && f4 >= f6 && f3 < ((float) (view.getRight() - view.getLeft())) + f5 && f4 < ((float) (view.getBottom() - view.getTop())) + f5;
    }

    public final void a() {
        Runnable runnable = this.f4382g;
        if (runnable != null) {
            this.f4380e.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f4381f;
        if (runnable2 != null) {
            this.f4380e.removeCallbacks(runnable2);
        }
    }

    public abstract n.p b();

    public abstract boolean c();

    public boolean d() {
        n.p pVarB = b();
        if (pVarB == null || !pVarB.b()) {
            return true;
        }
        pVarB.dismiss();
        return true;
    }

    public void e() {
        a();
        View view = this.f4380e;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.f4383h = true;
        }
    }

    public final boolean f(MotionEvent motionEvent) {
        r rVar;
        View view = this.f4380e;
        n.p pVarB = b();
        if (pVarB == null || !pVarB.b() || (rVar = (r) pVarB.l()) == null || !rVar.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, motionEventObtainNoHistory);
        j(rVar, motionEventObtainNoHistory);
        boolean zE = rVar.e(motionEventObtainNoHistory, this.f4384i);
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
            android.view.View r0 = r5.f4380e
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
            int r1 = r5.f4384i
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f4377b
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
            r5.f4384i = r6
            java.lang.Runnable r6 = r5.f4381f
            if (r6 != 0) goto L52
            o.t$a r6 = new o.t$a
            r6.<init>()
            r5.f4381f = r6
        L52:
            java.lang.Runnable r6 = r5.f4381f
            int r1 = r5.f4378c
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f4382g
            if (r6 != 0) goto L65
            o.t$b r6 = new o.t$b
            r6.<init>()
            r5.f4382g = r6
        L65:
            java.lang.Runnable r6 = r5.f4382g
            int r1 = r5.f4379d
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: o.t.g(android.view.MotionEvent):boolean");
    }

    public final boolean i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f4385j);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    public final boolean j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f4385j);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z3;
        boolean z4 = this.f4383h;
        if (z4) {
            z3 = f(motionEvent) || !d();
        } else {
            z3 = g(motionEvent) && c();
            if (z3) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f4380e.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.f4383h = z3;
        return z3 || z4;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f4383h = false;
        this.f4384i = -1;
        Runnable runnable = this.f4381f;
        if (runnable != null) {
            this.f4380e.removeCallbacks(runnable);
        }
    }
}
