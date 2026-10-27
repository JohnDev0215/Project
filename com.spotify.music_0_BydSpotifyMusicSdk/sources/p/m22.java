package p;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class m22 implements Runnable {
    public int c;
    public int e;
    public OverScroller k;
    public Interpolator l;
    public boolean m;
    public boolean n;
    public final /* synthetic */ RecyclerView o;

    public m22(RecyclerView recyclerView) {
        this.o = recyclerView;
        cu2 cu2Var = RecyclerView.C0;
        this.l = cu2Var;
        this.m = false;
        this.n = false;
        this.k = new OverScroller(recyclerView.getContext(), cu2Var);
    }

    public final void a() {
        if (this.m) {
            this.n = true;
            return;
        }
        RecyclerView recyclerView = this.o;
        recyclerView.removeCallbacks(this);
        WeakHashMap weakHashMap = zt2.a;
        it2.m(recyclerView, this);
    }

    public final void b(int i, int i2, int i3, Interpolator interpolator) {
        int iRound;
        RecyclerView recyclerView = this.o;
        if (i3 == Integer.MIN_VALUE) {
            int iAbs = Math.abs(i);
            int iAbs2 = Math.abs(i2);
            boolean z = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt(0);
            int iSqrt2 = (int) Math.sqrt((i2 * i2) + (i * i));
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i4 = width / 2;
            float f = width;
            float f2 = i4;
            float fSin = (((float) Math.sin((Math.min(1.0f, (iSqrt2 * 1.0f) / f) - 0.5f) * 0.47123894f)) * f2) + f2;
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fSin / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f) + 1.0f) * 300.0f);
            }
            i3 = Math.min(iRound, 2000);
        }
        int i5 = i3;
        if (interpolator == null) {
            interpolator = RecyclerView.C0;
        }
        if (this.l != interpolator) {
            this.l = interpolator;
            this.k = new OverScroller(recyclerView.getContext(), interpolator);
        }
        this.e = 0;
        this.c = 0;
        recyclerView.setScrollState(2);
        this.k.startScroll(0, 0, i, i2, i5);
        a();
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        int i4;
        RecyclerView recyclerView = this.o;
        if (recyclerView.t == null) {
            recyclerView.removeCallbacks(this);
            this.k.abortAnimation();
            return;
        }
        this.n = false;
        this.m = true;
        recyclerView.m();
        OverScroller overScroller = this.k;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i5 = currX - this.c;
            int i6 = currY - this.e;
            this.c = currX;
            this.e = currY;
            int[] iArr = recyclerView.w0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean zR = recyclerView.r(i5, i6, 1, iArr, null);
            int[] iArr2 = recyclerView.w0;
            if (zR) {
                i5 -= iArr2[0];
                i6 -= iArr2[1];
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.l(i5, i6);
            }
            if (recyclerView.s != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
                recyclerView.Z(i5, i6, iArr2);
                int i7 = iArr2[0];
                int i8 = iArr2[1];
                int i9 = i5 - i7;
                int i10 = i6 - i8;
                qa1 qa1Var = recyclerView.t.e;
                if (qa1Var != null && !qa1Var.d && qa1Var.e) {
                    int iB = recyclerView.k0.b();
                    if (iB == 0) {
                        qa1Var.f();
                    } else if (qa1Var.a >= iB) {
                        qa1Var.a = iB - 1;
                        qa1Var.d(i7, i8);
                    } else {
                        qa1Var.d(i7, i8);
                    }
                }
                i4 = i7;
                i = i9;
                i2 = i10;
                i3 = i8;
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
                i4 = 0;
            }
            if (!recyclerView.u.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.w0;
            iArr3[0] = 0;
            iArr3[1] = 0;
            int i11 = i3;
            recyclerView.s(i4, i3, i, i2, null, 1, iArr3);
            int i12 = i - iArr2[0];
            int i13 = i2 - iArr2[1];
            if (i4 != 0 || i11 != 0) {
                recyclerView.t(i4, i11);
            }
            if (!recyclerView.awakenScrollBars()) {
                recyclerView.invalidate();
            }
            boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i12 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i13 != 0));
            qa1 qa1Var2 = recyclerView.t.e;
            if ((qa1Var2 == null || !qa1Var2.d) && z) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i14 = i12 < 0 ? -currVelocity : i12 > 0 ? currVelocity : 0;
                    if (i13 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i13 <= 0) {
                        currVelocity = 0;
                    }
                    if (i14 < 0) {
                        recyclerView.v();
                        if (recyclerView.L.isFinished()) {
                            recyclerView.L.onAbsorb(-i14);
                        }
                    } else if (i14 > 0) {
                        recyclerView.w();
                        if (recyclerView.N.isFinished()) {
                            recyclerView.N.onAbsorb(i14);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.x();
                        if (recyclerView.M.isFinished()) {
                            recyclerView.M.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.u();
                        if (recyclerView.O.isFinished()) {
                            recyclerView.O.onAbsorb(currVelocity);
                        }
                    }
                    if (i14 != 0 || currVelocity != 0) {
                        WeakHashMap weakHashMap = zt2.a;
                        it2.k(recyclerView);
                    }
                }
                b01 b01Var = recyclerView.j0;
                int[] iArr4 = b01Var.c;
                if (iArr4 != null) {
                    Arrays.fill(iArr4, -1);
                }
                b01Var.d = 0;
            } else {
                a();
                d01 d01Var = recyclerView.i0;
                if (d01Var != null) {
                    d01Var.a(recyclerView, i4, i11);
                }
            }
        }
        qa1 qa1Var3 = recyclerView.t.e;
        if (qa1Var3 != null && qa1Var3.d) {
            qa1Var3.d(0, 0);
        }
        this.m = false;
        if (!this.n) {
            recyclerView.setScrollState(0);
            recyclerView.e0(1);
        } else {
            recyclerView.removeCallbacks(this);
            WeakHashMap weakHashMap2 = zt2.a;
            it2.m(recyclerView, this);
        }
    }
}
