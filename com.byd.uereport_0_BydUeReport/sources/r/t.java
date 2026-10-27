package r;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: compiled from: ForwardingListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f7636d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7637e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f7638f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f7639g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Runnable f7640h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Runnable f7641i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f7642j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7643k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int[] f7644l = new int[2];

    /* JADX INFO: compiled from: ForwardingListener.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = t.this.f7639g.getParent();
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
        this.f7639g = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f7636d = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f7637e = tapTimeout;
        this.f7638f = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public static boolean h(View view, float f8, float f9, float f10) {
        float f11 = -f10;
        return f8 >= f11 && f9 >= f11 && f8 < ((float) (view.getRight() - view.getLeft())) + f10 && f9 < ((float) (view.getBottom() - view.getTop())) + f10;
    }

    public final void a() {
        Runnable runnable = this.f7641i;
        if (runnable != null) {
            this.f7639g.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f7640h;
        if (runnable2 != null) {
            this.f7639g.removeCallbacks(runnable2);
        }
    }

    public abstract q.p b();

    public abstract boolean c();

    public boolean d() {
        q.p pVarB = b();
        if (pVarB == null || !pVarB.c()) {
            return true;
        }
        pVarB.dismiss();
        return true;
    }

    public void e() {
        a();
        View view = this.f7639g;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.f7642j = true;
        }
    }

    public final boolean f(MotionEvent motionEvent) {
        r rVar;
        View view = this.f7639g;
        q.p pVarB = b();
        if (pVarB == null || !pVarB.c() || (rVar = (r) pVarB.h()) == null || !rVar.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, motionEventObtainNoHistory);
        j(rVar, motionEventObtainNoHistory);
        boolean zE = rVar.e(motionEventObtainNoHistory, this.f7643k);
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
            android.view.View r0 = r5.f7639g
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
            int r1 = r5.f7643k
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f7636d
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
            r5.f7643k = r6
            java.lang.Runnable r6 = r5.f7640h
            if (r6 != 0) goto L52
            r.t$a r6 = new r.t$a
            r6.<init>()
            r5.f7640h = r6
        L52:
            java.lang.Runnable r6 = r5.f7640h
            int r1 = r5.f7637e
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f7641i
            if (r6 != 0) goto L65
            r.t$b r6 = new r.t$b
            r6.<init>()
            r5.f7641i = r6
        L65:
            java.lang.Runnable r6 = r5.f7641i
            int r1 = r5.f7638f
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: r.t.g(android.view.MotionEvent):boolean");
    }

    public final boolean i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f7644l);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    public final boolean j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f7644l);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z7;
        boolean z8 = this.f7642j;
        if (z8) {
            z7 = f(motionEvent) || !d();
        } else {
            z7 = g(motionEvent) && c();
            if (z7) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f7639g.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.f7642j = z7;
        return z7 || z8;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f7642j = false;
        this.f7643k = -1;
        Runnable runnable = this.f7640h;
        if (runnable != null) {
            this.f7639g.removeCallbacks(runnable);
        }
    }
}
