package L1;

import B5.RunnableC0079c;
import F1.H;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import h6.n;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final c f3980v = new c(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3981a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3982b;
    public float[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f3983e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f3984f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f3985g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f3986h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f3987i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f3988j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3989k;
    public VelocityTracker l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f3990m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f3991n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final OverScroller f3992p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final com.bumptech.glide.c f3993q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f3994r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3995s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final CoordinatorLayout f3996t;
    public int c = -1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final RunnableC0079c f3997u = new RunnableC0079c(this, 5);

    public d(Context context, CoordinatorLayout coordinatorLayout, com.bumptech.glide.c cVar) {
        if (coordinatorLayout == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.f3996t = coordinatorLayout;
        this.f3993q = cVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f3982b = viewConfiguration.getScaledTouchSlop();
        this.f3990m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f3991n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f3992p = new OverScroller(context, f3980v);
    }

    public final void a() {
        this.c = -1;
        float[] fArr = this.d;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.f3983e, 0.0f);
            Arrays.fill(this.f3984f, 0.0f);
            Arrays.fill(this.f3985g, 0.0f);
            Arrays.fill(this.f3986h, 0);
            Arrays.fill(this.f3987i, 0);
            Arrays.fill(this.f3988j, 0);
            this.f3989k = 0;
        }
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public final void b(View view, int i7) {
        ViewParent parent = view.getParent();
        CoordinatorLayout coordinatorLayout = this.f3996t;
        if (parent != coordinatorLayout) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + coordinatorLayout + ")");
        }
        this.f3994r = view;
        this.c = i7;
        this.f3993q.s0(view, i7);
        n(1);
    }

    public final boolean c(View view, float f10, float f11) {
        if (view == null) {
            return false;
        }
        com.bumptech.glide.c cVar = this.f3993q;
        boolean z6 = cVar.k0(view) > 0;
        boolean z10 = cVar.l0() > 0;
        if (!z6 || !z10) {
            return z6 ? Math.abs(f10) > ((float) this.f3982b) : z10 && Math.abs(f11) > ((float) this.f3982b);
        }
        float f12 = (f11 * f11) + (f10 * f10);
        int i7 = this.f3982b;
        return f12 > ((float) (i7 * i7));
    }

    public final void d(int i7) {
        float[] fArr = this.d;
        if (fArr != null) {
            int i10 = this.f3989k;
            int i11 = 1 << i7;
            if ((i10 & i11) != 0) {
                fArr[i7] = 0.0f;
                this.f3983e[i7] = 0.0f;
                this.f3984f[i7] = 0.0f;
                this.f3985g[i7] = 0.0f;
                this.f3986h[i7] = 0;
                this.f3987i[i7] = 0;
                this.f3988j[i7] = 0;
                this.f3989k = (~i11) & i10;
            }
        }
    }

    public final int e(int i7, int i10, int i11) {
        if (i7 == 0) {
            return 0;
        }
        float width = this.f3996t.getWidth() / 2;
        float fSin = (((float) Math.sin((Math.min(1.0f, Math.abs(i7) / r3) - 0.5f) * 0.47123894f)) * width) + width;
        int iAbs = Math.abs(i10);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fSin / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i7) / i11) + 1.0f) * 256.0f), 600);
    }

    public final boolean f() {
        if (this.f3981a == 2) {
            OverScroller overScroller = this.f3992p;
            boolean zComputeScrollOffset = overScroller.computeScrollOffset();
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int left = currX - this.f3994r.getLeft();
            int top = currY - this.f3994r.getTop();
            if (left != 0) {
                View view = this.f3994r;
                int[] iArr = H.f2303a;
                view.offsetLeftAndRight(left);
            }
            if (top != 0) {
                View view2 = this.f3994r;
                int[] iArr2 = H.f2303a;
                view2.offsetTopAndBottom(top);
            }
            if (left != 0 || top != 0) {
                this.f3993q.u0(this.f3994r, currX, currY);
            }
            if (zComputeScrollOffset && currX == overScroller.getFinalX() && currY == overScroller.getFinalY()) {
                overScroller.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                this.f3996t.post(this.f3997u);
            }
        }
        return this.f3981a == 2;
    }

    public final View g(int i7, int i10) {
        CoordinatorLayout coordinatorLayout = this.f3996t;
        for (int childCount = coordinatorLayout.getChildCount() - 1; childCount >= 0; childCount--) {
            this.f3993q.getClass();
            View childAt = coordinatorLayout.getChildAt(childCount);
            if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && i10 >= childAt.getTop() && i10 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean h(int i7, int i10, int i11, int i12) {
        float f10;
        float f11;
        float f12;
        float f13;
        int left = this.f3994r.getLeft();
        int top = this.f3994r.getTop();
        int i13 = i7 - left;
        int i14 = i10 - top;
        OverScroller overScroller = this.f3992p;
        int i15 = 0;
        if (i13 == 0 && i14 == 0) {
            overScroller.abortAnimation();
            n(0);
            return false;
        }
        View view = this.f3994r;
        int i16 = (int) this.f3991n;
        int i17 = (int) this.f3990m;
        int iAbs = Math.abs(i11);
        if (iAbs < i16) {
            i11 = 0;
        } else if (iAbs > i17) {
            i11 = i11 > 0 ? i17 : -i17;
        }
        int iAbs2 = Math.abs(i12);
        if (iAbs2 < i16) {
            i12 = i15;
        } else if (iAbs2 > i17) {
            if (i12 > 0) {
                i12 = i17;
            } else {
                i15 = -i17;
                i12 = i15;
            }
        }
        int iAbs3 = Math.abs(i13);
        int iAbs4 = Math.abs(i14);
        int iAbs5 = Math.abs(i11);
        int iAbs6 = Math.abs(i12);
        int i18 = iAbs5 + iAbs6;
        int i19 = iAbs3 + iAbs4;
        if (i11 != 0) {
            f10 = iAbs5;
            f11 = i18;
        } else {
            f10 = iAbs3;
            f11 = i19;
        }
        float f14 = f10 / f11;
        if (i12 != 0) {
            f12 = iAbs6;
            f13 = i18;
        } else {
            f12 = iAbs4;
            f13 = i19;
        }
        float f15 = f12 / f13;
        com.bumptech.glide.c cVar = this.f3993q;
        overScroller.startScroll(left, top, i13, i14, (int) ((e(i14, i12, cVar.l0()) * f15) + (e(i13, i11, cVar.k0(view)) * f14)));
        n(2);
        return true;
    }

    public final boolean i(int i7) {
        if ((this.f3989k & (1 << i7)) != 0) {
            return true;
        }
        n.j("ViewDragHelper", "Ignoring pointerId=" + i7 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public final void j(MotionEvent motionEvent) {
        int i7;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i10 = 0;
        if (actionMasked == 0) {
            float x8 = motionEvent.getX();
            float y10 = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewG = g((int) x8, (int) y10);
            l(pointerId, x8, y10);
            q(viewG, pointerId);
            int i11 = this.f3986h[pointerId];
            return;
        }
        if (actionMasked == 1) {
            if (this.f3981a == 1) {
                k();
            }
            a();
            return;
        }
        com.bumptech.glide.c cVar = this.f3993q;
        if (actionMasked != 2) {
            if (actionMasked == 3) {
                if (this.f3981a == 1) {
                    this.f3995s = true;
                    cVar.v0(this.f3994r, 0.0f, 0.0f);
                    this.f3995s = false;
                    if (this.f3981a == 1) {
                        n(0);
                    }
                }
                a();
                return;
            }
            if (actionMasked == 5) {
                int pointerId2 = motionEvent.getPointerId(actionIndex);
                float x9 = motionEvent.getX(actionIndex);
                float y11 = motionEvent.getY(actionIndex);
                l(pointerId2, x9, y11);
                if (this.f3981a == 0) {
                    q(g((int) x9, (int) y11), pointerId2);
                    int i12 = this.f3986h[pointerId2];
                    return;
                }
                int i13 = (int) x9;
                int i14 = (int) y11;
                View view = this.f3994r;
                if (view != null) {
                    i10 = (i13 < view.getLeft() || i13 >= view.getRight() || i14 < view.getTop() || i14 >= view.getBottom()) ? 0 : 1;
                }
                if (i10 != 0) {
                    q(this.f3994r, pointerId2);
                    return;
                }
                return;
            }
            if (actionMasked != 6) {
                return;
            }
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            if (this.f3981a == 1 && pointerId3 == this.c) {
                int pointerCount = motionEvent.getPointerCount();
                while (true) {
                    if (i10 >= pointerCount) {
                        i7 = -1;
                        break;
                    }
                    int pointerId4 = motionEvent.getPointerId(i10);
                    if (pointerId4 != this.c) {
                        View viewG2 = g((int) motionEvent.getX(i10), (int) motionEvent.getY(i10));
                        View view2 = this.f3994r;
                        if (viewG2 == view2 && q(view2, pointerId4)) {
                            i7 = this.c;
                            break;
                        }
                    }
                    i10++;
                }
                if (i7 == -1) {
                    k();
                }
            }
            d(pointerId3);
            return;
        }
        if (this.f3981a == 1) {
            if (i(this.c)) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.c);
                float x10 = motionEvent.getX(iFindPointerIndex);
                float y12 = motionEvent.getY(iFindPointerIndex);
                float[] fArr = this.f3984f;
                int i15 = this.c;
                int i16 = (int) (x10 - fArr[i15]);
                int i17 = (int) (y12 - this.f3985g[i15]);
                int left = this.f3994r.getLeft() + i16;
                int top = this.f3994r.getTop() + i17;
                int left2 = this.f3994r.getLeft();
                int top2 = this.f3994r.getTop();
                if (i16 != 0) {
                    left = cVar.M(this.f3994r, left);
                    int[] iArr = H.f2303a;
                    this.f3994r.offsetLeftAndRight(left - left2);
                }
                if (i17 != 0) {
                    top = cVar.N(this.f3994r, top);
                    int[] iArr2 = H.f2303a;
                    this.f3994r.offsetTopAndBottom(top - top2);
                }
                if (i16 != 0 || i17 != 0) {
                    cVar.u0(this.f3994r, left, top);
                }
                m(motionEvent);
                return;
            }
            return;
        }
        int pointerCount2 = motionEvent.getPointerCount();
        while (i10 < pointerCount2) {
            int pointerId5 = motionEvent.getPointerId(i10);
            if (i(pointerId5)) {
                float x11 = motionEvent.getX(i10);
                float y13 = motionEvent.getY(i10);
                float f10 = x11 - this.d[pointerId5];
                float f11 = y13 - this.f3983e[pointerId5];
                Math.abs(f10);
                Math.abs(f11);
                int i18 = this.f3986h[pointerId5];
                Math.abs(f11);
                Math.abs(f10);
                int i19 = this.f3986h[pointerId5];
                Math.abs(f10);
                Math.abs(f11);
                int i20 = this.f3986h[pointerId5];
                Math.abs(f11);
                Math.abs(f10);
                int i21 = this.f3986h[pointerId5];
                if (this.f3981a != 1) {
                    View viewG3 = g((int) x11, (int) y13);
                    if (c(viewG3, f10, f11) && q(viewG3, pointerId5)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            i10++;
        }
        m(motionEvent);
    }

    public final void k() {
        VelocityTracker velocityTracker = this.l;
        float f10 = this.f3990m;
        velocityTracker.computeCurrentVelocity(1000, f10);
        float xVelocity = this.l.getXVelocity(this.c);
        float f11 = this.f3991n;
        float fAbs = Math.abs(xVelocity);
        float f12 = 0.0f;
        if (fAbs < f11) {
            xVelocity = 0.0f;
        } else if (fAbs > f10) {
            xVelocity = xVelocity > 0.0f ? f10 : -f10;
        }
        float yVelocity = this.l.getYVelocity(this.c);
        float fAbs2 = Math.abs(yVelocity);
        if (fAbs2 >= f11) {
            if (fAbs2 > f10) {
                if (yVelocity <= 0.0f) {
                    f10 = -f10;
                }
                f12 = f10;
            } else {
                f12 = yVelocity;
            }
        }
        this.f3995s = true;
        this.f3993q.v0(this.f3994r, xVelocity, f12);
        this.f3995s = false;
        if (this.f3981a == 1) {
            n(0);
        }
    }

    public final void l(int i7, float f10, float f11) {
        float[] fArr = this.d;
        if (fArr == null || fArr.length <= i7) {
            int i10 = i7 + 1;
            float[] fArr2 = new float[i10];
            float[] fArr3 = new float[i10];
            float[] fArr4 = new float[i10];
            float[] fArr5 = new float[i10];
            int[] iArr = new int[i10];
            int[] iArr2 = new int[i10];
            int[] iArr3 = new int[i10];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f3983e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f3984f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f3985g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f3986h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f3987i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f3988j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.d = fArr2;
            this.f3983e = fArr3;
            this.f3984f = fArr4;
            this.f3985g = fArr5;
            this.f3986h = iArr;
            this.f3987i = iArr2;
            this.f3988j = iArr3;
        }
        float[] fArr9 = this.d;
        this.f3984f[i7] = f10;
        fArr9[i7] = f10;
        float[] fArr10 = this.f3983e;
        this.f3985g[i7] = f11;
        fArr10[i7] = f11;
        int[] iArr7 = this.f3986h;
        int i11 = (int) f10;
        int i12 = (int) f11;
        CoordinatorLayout coordinatorLayout = this.f3996t;
        int left = coordinatorLayout.getLeft();
        int i13 = this.o;
        int i14 = i11 < left + i13 ? 1 : 0;
        if (i12 < coordinatorLayout.getTop() + i13) {
            i14 |= 4;
        }
        if (i11 > coordinatorLayout.getRight() - i13) {
            i14 |= 2;
        }
        if (i12 > coordinatorLayout.getBottom() - i13) {
            i14 |= 8;
        }
        iArr7[i7] = i14;
        this.f3989k = (1 << i7) | this.f3989k;
    }

    public final void m(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i7 = 0; i7 < pointerCount; i7++) {
            int pointerId = motionEvent.getPointerId(i7);
            if (i(pointerId)) {
                float x8 = motionEvent.getX(i7);
                float y10 = motionEvent.getY(i7);
                this.f3984f[pointerId] = x8;
                this.f3985g[pointerId] = y10;
            }
        }
    }

    public final void n(int i7) {
        this.f3996t.removeCallbacks(this.f3997u);
        if (this.f3981a != i7) {
            this.f3981a = i7;
            this.f3993q.t0(i7);
            if (this.f3981a == 0) {
                this.f3994r = null;
            }
        }
    }

    public final boolean o(int i7, int i10) {
        if (this.f3995s) {
            return h(i7, i10, (int) this.l.getXVelocity(this.c), (int) this.l.getYVelocity(this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean p(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L1.d.p(android.view.MotionEvent):boolean");
    }

    public final boolean q(View view, int i7) {
        if (view == this.f3994r && this.c == i7) {
            return true;
        }
        if (view == null || !this.f3993q.E0(view, i7)) {
            return false;
        }
        this.c = i7;
        b(view, i7);
        return true;
    }
}
