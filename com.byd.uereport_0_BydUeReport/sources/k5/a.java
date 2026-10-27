package k5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import o0.t;

/* JADX INFO: compiled from: HeaderBehavior.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<V extends View> extends c<V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f5877d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OverScroller f5878e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5879f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5880g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5881h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5882i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public VelocityTracker f5883j;

    /* JADX INFO: renamed from: k5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeaderBehavior.java */
    public class RunnableC0074a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final CoordinatorLayout f5884d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final V f5885e;

        public RunnableC0074a(CoordinatorLayout coordinatorLayout, V v7) {
            this.f5884d = coordinatorLayout;
            this.f5885e = v7;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f5885e == null || (overScroller = a.this.f5878e) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                a.this.N(this.f5884d, this.f5885e);
                return;
            }
            a aVar = a.this;
            aVar.P(this.f5884d, this.f5885e, aVar.f5878e.getCurrY());
            t.d0(this.f5885e, this);
        }
    }

    public a() {
        this.f5880g = -1;
        this.f5882i = -1;
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
            r11.f5880g = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.f5881h = r12
            goto L4c
        L2d:
            int r0 = r11.f5880g
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.f5881h
            int r7 = r1 - r0
            r11.f5881h = r0
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
            android.view.VelocityTracker r0 = r11.f5883j
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.f5883j
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.f5883j
            int r4 = r11.f5880g
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
            r11.f5879f = r3
            r11.f5880g = r1
            android.view.VelocityTracker r13 = r11.f5883j
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.f5883j = r13
        L81:
            android.view.VelocityTracker r13 = r11.f5883j
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r13 = r11.f5879f
            if (r13 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = r3
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: k5.a.D(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract boolean H(V v7);

    public final void I() {
        if (this.f5883j == null) {
            this.f5883j = VelocityTracker.obtain();
        }
    }

    public final boolean J(CoordinatorLayout coordinatorLayout, V v7, int i8, int i9, float f8) {
        Runnable runnable = this.f5877d;
        if (runnable != null) {
            v7.removeCallbacks(runnable);
            this.f5877d = null;
        }
        if (this.f5878e == null) {
            this.f5878e = new OverScroller(v7.getContext());
        }
        this.f5878e.fling(0, E(), 0, Math.round(f8), 0, 0, i8, i9);
        if (!this.f5878e.computeScrollOffset()) {
            N(coordinatorLayout, v7);
            return false;
        }
        RunnableC0074a runnableC0074a = new RunnableC0074a(coordinatorLayout, v7);
        this.f5877d = runnableC0074a;
        t.d0(v7, runnableC0074a);
        return true;
    }

    public abstract int K(V v7);

    public abstract int L(V v7);

    public abstract int M();

    public abstract void N(CoordinatorLayout coordinatorLayout, V v7);

    public final int O(CoordinatorLayout coordinatorLayout, V v7, int i8, int i9, int i10) {
        return Q(coordinatorLayout, v7, M() - i8, i9, i10);
    }

    public int P(CoordinatorLayout coordinatorLayout, V v7, int i8) {
        return Q(coordinatorLayout, v7, i8, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public abstract int Q(CoordinatorLayout coordinatorLayout, V v7, int i8, int i9, int i10);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.f5882i < 0) {
            this.f5882i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f5879f) {
            int i8 = this.f5880g;
            if (i8 == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i8)) == -1) {
                return false;
            }
            int y7 = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y7 - this.f5881h) > this.f5882i) {
                this.f5881h = y7;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f5880g = -1;
            int x7 = (int) motionEvent.getX();
            int y8 = (int) motionEvent.getY();
            boolean z7 = H(v7) && coordinatorLayout.F(v7, x7, y8);
            this.f5879f = z7;
            if (z7) {
                this.f5881h = y8;
                this.f5880g = motionEvent.getPointerId(0);
                I();
                OverScroller overScroller = this.f5878e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f5878e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f5883j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5880g = -1;
        this.f5882i = -1;
    }
}
