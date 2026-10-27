package t1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import l0.t;

/* JADX INFO: compiled from: HeaderBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<V extends View> extends c<V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f5016d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OverScroller f5017e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5018f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5019g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5020h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5021i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public VelocityTracker f5022j;

    /* JADX INFO: renamed from: t1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeaderBehavior.java */
    public class RunnableC0062a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CoordinatorLayout f5023b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final V f5024c;

        public RunnableC0062a(CoordinatorLayout coordinatorLayout, V v3) {
            this.f5023b = coordinatorLayout;
            this.f5024c = v3;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f5024c == null || (overScroller = a.this.f5017e) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                a.this.N(this.f5023b, this.f5024c);
                return;
            }
            a aVar = a.this;
            aVar.P(this.f5023b, this.f5024c, aVar.f5017e.getCurrY());
            t.b0(this.f5024c, this);
        }
    }

    public a() {
        this.f5019g = -1;
        this.f5021i = -1;
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
            r11.f5019g = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.f5020h = r12
            goto L4c
        L2d:
            int r0 = r11.f5019g
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.f5020h
            int r7 = r1 - r0
            r11.f5020h = r0
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
            android.view.VelocityTracker r0 = r11.f5022j
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.f5022j
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.f5022j
            int r4 = r11.f5019g
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
            r11.f5018f = r3
            r11.f5019g = r1
            android.view.VelocityTracker r13 = r11.f5022j
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.f5022j = r13
        L81:
            android.view.VelocityTracker r13 = r11.f5022j
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r13 = r11.f5018f
            if (r13 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = r3
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.a.D(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract boolean H(V v3);

    public final void I() {
        if (this.f5022j == null) {
            this.f5022j = VelocityTracker.obtain();
        }
    }

    public final boolean J(CoordinatorLayout coordinatorLayout, V v3, int i3, int i4, float f3) {
        Runnable runnable = this.f5016d;
        if (runnable != null) {
            v3.removeCallbacks(runnable);
            this.f5016d = null;
        }
        if (this.f5017e == null) {
            this.f5017e = new OverScroller(v3.getContext());
        }
        this.f5017e.fling(0, E(), 0, Math.round(f3), 0, 0, i3, i4);
        if (!this.f5017e.computeScrollOffset()) {
            N(coordinatorLayout, v3);
            return false;
        }
        RunnableC0062a runnableC0062a = new RunnableC0062a(coordinatorLayout, v3);
        this.f5016d = runnableC0062a;
        t.b0(v3, runnableC0062a);
        return true;
    }

    public abstract int K(V v3);

    public abstract int L(V v3);

    public abstract int M();

    public abstract void N(CoordinatorLayout coordinatorLayout, V v3);

    public final int O(CoordinatorLayout coordinatorLayout, V v3, int i3, int i4, int i5) {
        return Q(coordinatorLayout, v3, M() - i3, i4, i5);
    }

    public int P(CoordinatorLayout coordinatorLayout, V v3, int i3) {
        return Q(coordinatorLayout, v3, i3, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public abstract int Q(CoordinatorLayout coordinatorLayout, V v3, int i3, int i4, int i5);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.f5021i < 0) {
            this.f5021i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f5018f) {
            int i3 = this.f5019g;
            if (i3 == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i3)) == -1) {
                return false;
            }
            int y3 = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y3 - this.f5020h) > this.f5021i) {
                this.f5020h = y3;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f5019g = -1;
            int x3 = (int) motionEvent.getX();
            int y4 = (int) motionEvent.getY();
            boolean z3 = H(v3) && coordinatorLayout.F(v3, x3, y4);
            this.f5018f = z3;
            if (z3) {
                this.f5020h = y4;
                this.f5019g = motionEvent.getPointerId(0);
                I();
                OverScroller overScroller = this.f5017e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f5017e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f5022j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5019g = -1;
        this.f5021i = -1;
    }
}
