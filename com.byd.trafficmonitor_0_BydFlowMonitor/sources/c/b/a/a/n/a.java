package c.b.a.a.n;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.h.m.t;

/* JADX INFO: compiled from: HeaderBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<V extends View> extends c<V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f4033d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OverScroller f4034e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4035f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4036g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4037h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4038i;
    public VelocityTracker j;

    /* JADX INFO: renamed from: c.b.a.a.n.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeaderBehavior.java */
    public class RunnableC0088a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CoordinatorLayout f4039b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final V f4040c;

        public RunnableC0088a(CoordinatorLayout coordinatorLayout, V v) {
            this.f4039b = coordinatorLayout;
            this.f4040c = v;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f4040c == null || (overScroller = a.this.f4034e) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                a.this.N(this.f4039b, this.f4040c);
                return;
            }
            a aVar = a.this;
            aVar.P(this.f4039b, this.f4040c, aVar.f4034e.getCurrY());
            t.c0(this.f4040c, this);
        }
    }

    public a() {
        this.f4036g = -1;
        this.f4038i = -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean D(androidx.coordinatorlayout.widget.CoordinatorLayout r12, V r13, android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r14.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L4e
            r4 = 2
            if (r0 == r4) goto L2d
            r12 = 3
            if (r0 == r12) goto L72
            r12 = 6
            if (r0 == r12) goto L13
            goto L4c
        L13:
            int r12 = r14.getActionIndex()
            if (r12 != 0) goto L1b
            r12 = r2
            goto L1c
        L1b:
            r12 = r3
        L1c:
            int r13 = r14.getPointerId(r12)
            r11.f4036g = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.f4037h = r12
            goto L4c
        L2d:
            int r0 = r11.f4036g
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.f4037h
            int r7 = r1 - r0
            r11.f4037h = r0
            int r8 = r11.K(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r4.O(r5, r6, r7, r8, r9)
        L4c:
            r12 = r3
            goto L81
        L4e:
            android.view.VelocityTracker r0 = r11.j
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.j
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.j
            int r4 = r11.f4036g
            float r10 = r0.getYVelocity(r4)
            int r0 = r11.L(r13)
            int r8 = -r0
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r5.J(r6, r7, r8, r9, r10)
            r12 = r2
            goto L73
        L72:
            r12 = r3
        L73:
            r11.f4035f = r3
            r11.f4036g = r1
            android.view.VelocityTracker r13 = r11.j
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.j = r13
        L81:
            android.view.VelocityTracker r13 = r11.j
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r13 = r11.f4035f
            if (r13 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = r3
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.n.a.D(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract boolean H(V v);

    public final void I() {
        if (this.j == null) {
            this.j = VelocityTracker.obtain();
        }
    }

    public final boolean J(CoordinatorLayout coordinatorLayout, V v, int i2, int i3, float f2) {
        Runnable runnable = this.f4033d;
        if (runnable != null) {
            v.removeCallbacks(runnable);
            this.f4033d = null;
        }
        if (this.f4034e == null) {
            this.f4034e = new OverScroller(v.getContext());
        }
        this.f4034e.fling(0, E(), 0, Math.round(f2), 0, 0, i2, i3);
        if (!this.f4034e.computeScrollOffset()) {
            N(coordinatorLayout, v);
            return false;
        }
        RunnableC0088a runnableC0088a = new RunnableC0088a(coordinatorLayout, v);
        this.f4033d = runnableC0088a;
        t.c0(v, runnableC0088a);
        return true;
    }

    public abstract int K(V v);

    public abstract int L(V v);

    public abstract int M();

    public abstract void N(CoordinatorLayout coordinatorLayout, V v);

    public final int O(CoordinatorLayout coordinatorLayout, V v, int i2, int i3, int i4) {
        return Q(coordinatorLayout, v, M() - i2, i3, i4);
    }

    public int P(CoordinatorLayout coordinatorLayout, V v, int i2) {
        return Q(coordinatorLayout, v, i2, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public abstract int Q(CoordinatorLayout coordinatorLayout, V v, int i2, int i3, int i4);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.f4038i < 0) {
            this.f4038i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f4035f) {
            int i2 = this.f4036g;
            if (i2 == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i2)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y - this.f4037h) > this.f4038i) {
                this.f4037h = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f4036g = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            boolean z = H(v) && coordinatorLayout.F(v, x, y2);
            this.f4035f = z;
            if (z) {
                this.f4037h = y2;
                this.f4036g = motionEvent.getPointerId(0);
                I();
                OverScroller overScroller = this.f4034e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f4034e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4036g = -1;
        this.f4038i = -1;
    }
}
