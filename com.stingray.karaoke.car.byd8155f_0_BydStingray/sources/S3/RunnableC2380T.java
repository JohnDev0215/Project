package s3;

import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

/* JADX INFO: renamed from: s3.T, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2380T implements Runnable {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22213m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public OverScroller f22214n;
    public Interpolator o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f22215p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f22216q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f22217r;

    public RunnableC2380T(RecyclerView recyclerView) {
        this.f22217r = recyclerView;
        L1.c cVar = RecyclerView.f12095M0;
        this.o = cVar;
        this.f22215p = false;
        this.f22216q = false;
        this.f22214n = new OverScroller(recyclerView.getContext(), cVar);
    }

    public final void a(int i7, int i10) {
        RecyclerView recyclerView = this.f22217r;
        recyclerView.setScrollState(2);
        this.f22213m = 0;
        this.l = 0;
        Interpolator interpolator = this.o;
        L1.c cVar = RecyclerView.f12095M0;
        if (interpolator != cVar) {
            this.o = cVar;
            this.f22214n = new OverScroller(recyclerView.getContext(), cVar);
        }
        this.f22214n.fling(0, 0, i7, i10, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        b();
    }

    public final void b() {
        if (this.f22215p) {
            this.f22216q = true;
            return;
        }
        RecyclerView recyclerView = this.f22217r;
        recyclerView.removeCallbacks(this);
        int[] iArr = F1.H.f2303a;
        recyclerView.postOnAnimation(this);
    }

    public final void c(int i7, int i10, int i11, BaseInterpolator baseInterpolator) {
        RecyclerView recyclerView = this.f22217r;
        if (i11 == Integer.MIN_VALUE) {
            int iAbs = Math.abs(i7);
            int iAbs2 = Math.abs(i10);
            boolean z6 = iAbs > iAbs2;
            int width = z6 ? recyclerView.getWidth() : recyclerView.getHeight();
            if (!z6) {
                iAbs = iAbs2;
            }
            i11 = Math.min((int) (((iAbs / width) + 1.0f) * 300.0f), 2000);
        }
        int i12 = i11;
        Interpolator interpolator = baseInterpolator;
        if (baseInterpolator == null) {
            interpolator = RecyclerView.f12095M0;
        }
        if (this.o != interpolator) {
            this.o = interpolator;
            this.f22214n = new OverScroller(recyclerView.getContext(), interpolator);
        }
        this.f22213m = 0;
        this.l = 0;
        recyclerView.setScrollState(2);
        this.f22214n.startScroll(0, 0, i7, i10, i12);
        b();
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7;
        int i10;
        int i11;
        int i12;
        RecyclerView recyclerView = this.f22217r;
        if (recyclerView.f12157x == null) {
            recyclerView.removeCallbacks(this);
            this.f22214n.abortAnimation();
            return;
        }
        this.f22216q = false;
        this.f22215p = true;
        recyclerView.k();
        OverScroller overScroller = this.f22214n;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i13 = currX - this.l;
            int i14 = currY - this.f22213m;
            this.l = currX;
            this.f22213m = currY;
            int iJ = RecyclerView.j(i13, recyclerView.f12117Q, recyclerView.f12119S, recyclerView.getWidth());
            int iJ2 = RecyclerView.j(i14, recyclerView.f12118R, recyclerView.f12120T, recyclerView.getHeight());
            int[] iArr = recyclerView.f12098A0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean zP = recyclerView.p(iJ, iJ2, 1, iArr, null);
            int[] iArr2 = recyclerView.f12098A0;
            if (zP) {
                iJ -= iArr2[0];
                iJ2 -= iArr2[1];
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.i(iJ, iJ2);
            }
            if (recyclerView.f12155w != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
                recyclerView.X(iJ, iJ2, iArr2);
                int i15 = iArr2[0];
                int i16 = iArr2[1];
                int i17 = iJ - i15;
                int i18 = iJ2 - i16;
                C2402v c2402v = recyclerView.f12157x.f22181e;
                if (c2402v != null && !c2402v.d && c2402v.f22353e) {
                    int iB = recyclerView.f12140o0.b();
                    if (iB == 0) {
                        c2402v.c();
                    } else if (c2402v.f22351a >= iB) {
                        c2402v.f22351a = iB - 1;
                        c2402v.b(i15, i16);
                    } else {
                        c2402v.b(i15, i16);
                    }
                }
                i12 = i15;
                i7 = i17;
                i10 = i18;
                i11 = i16;
            } else {
                i7 = iJ;
                i10 = iJ2;
                i11 = 0;
                i12 = 0;
            }
            if (!recyclerView.f12161z.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.f12098A0;
            iArr3[0] = 0;
            iArr3[1] = 0;
            int i19 = i11;
            recyclerView.q(i12, i11, i7, i10, null, 1, iArr3);
            int i20 = i7 - iArr2[0];
            int i21 = i10 - iArr2[1];
            if (i12 != 0 || i19 != 0) {
                recyclerView.r(i12, i19);
            }
            if (!recyclerView.awakenScrollBars()) {
                recyclerView.invalidate();
            }
            boolean z6 = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i20 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i21 != 0));
            C2402v c2402v2 = recyclerView.f12157x.f22181e;
            if ((c2402v2 == null || !c2402v2.d) && z6) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i22 = i20 < 0 ? -currVelocity : i20 > 0 ? currVelocity : 0;
                    if (i21 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i21 <= 0) {
                        currVelocity = 0;
                    }
                    if (i22 < 0) {
                        recyclerView.t();
                        if (recyclerView.f12117Q.isFinished()) {
                            recyclerView.f12117Q.onAbsorb(-i22);
                        }
                    } else if (i22 > 0) {
                        recyclerView.u();
                        if (recyclerView.f12119S.isFinished()) {
                            recyclerView.f12119S.onAbsorb(i22);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.v();
                        if (recyclerView.f12118R.isFinished()) {
                            recyclerView.f12118R.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.s();
                        if (recyclerView.f12120T.isFinished()) {
                            recyclerView.f12120T.onAbsorb(currVelocity);
                        }
                    }
                    if (i22 != 0 || currVelocity != 0) {
                        int[] iArr4 = F1.H.f2303a;
                        recyclerView.postInvalidateOnAnimation();
                    }
                }
                if (RecyclerView.f12093K0) {
                    C2393m c2393m = recyclerView.f12139n0;
                    int[] iArr5 = c2393m.f22320a;
                    if (iArr5 != null) {
                        Arrays.fill(iArr5, -1);
                    }
                    c2393m.d = 0;
                }
            } else {
                b();
                RunnableC2395o runnableC2395o = recyclerView.f12137m0;
                if (runnableC2395o != null) {
                    runnableC2395o.a(recyclerView, i12, i19);
                }
            }
        }
        C2402v c2402v3 = recyclerView.f12157x.f22181e;
        if (c2402v3 != null && c2402v3.d) {
            c2402v3.b(0, 0);
        }
        this.f22215p = false;
        if (!this.f22216q) {
            recyclerView.setScrollState(0);
            recyclerView.c0(1);
        } else {
            recyclerView.removeCallbacks(this);
            int[] iArr6 = F1.H.f2303a;
            recyclerView.postOnAnimation(this);
        }
    }
}
