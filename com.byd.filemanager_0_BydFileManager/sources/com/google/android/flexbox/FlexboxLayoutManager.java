package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h;
import androidx.recyclerview.widget.k;
import com.google.android.flexbox.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FlexboxLayoutManager extends RecyclerView.o implements com.google.android.flexbox.a, RecyclerView.y.b {
    private static final Rect t = new Rect();
    private boolean A;
    private RecyclerView.v D;
    private RecyclerView.z E;
    private c F;
    private k H;
    private k I;
    private SavedState J;
    private boolean O;
    private final Context Q;
    private View R;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean z;
    private int y = -1;
    private List<com.google.android.flexbox.b> B = new ArrayList();
    private final com.google.android.flexbox.c C = new com.google.android.flexbox.c(this);
    private b G = new b();
    private int K = -1;
    private int L = Integer.MIN_VALUE;
    private int M = Integer.MIN_VALUE;
    private int N = Integer.MIN_VALUE;
    private SparseArray<View> P = new SparseArray<>();
    private int S = -1;
    private c.b T = new c.b();

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3933a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3934b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3935c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3936d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f3937e;
        private boolean f;
        private boolean g;

        private b() {
            this.f3936d = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void q() {
            if (FlexboxLayoutManager.this.j() || !FlexboxLayoutManager.this.z) {
                this.f3935c = this.f3937e ? FlexboxLayoutManager.this.H.i() : FlexboxLayoutManager.this.H.m();
            } else {
                this.f3935c = this.f3937e ? FlexboxLayoutManager.this.H.i() : FlexboxLayoutManager.this.u0() - FlexboxLayoutManager.this.H.m();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void r(View view) {
            if (FlexboxLayoutManager.this.j() || !FlexboxLayoutManager.this.z) {
                if (this.f3937e) {
                    this.f3935c = FlexboxLayoutManager.this.H.d(view) + FlexboxLayoutManager.this.H.o();
                } else {
                    this.f3935c = FlexboxLayoutManager.this.H.g(view);
                }
            } else if (this.f3937e) {
                this.f3935c = FlexboxLayoutManager.this.H.g(view) + FlexboxLayoutManager.this.H.o();
            } else {
                this.f3935c = FlexboxLayoutManager.this.H.d(view);
            }
            this.f3933a = FlexboxLayoutManager.this.n0(view);
            this.g = false;
            int[] iArr = FlexboxLayoutManager.this.C.f3950c;
            int i = this.f3933a;
            if (i == -1) {
                i = 0;
            }
            int i2 = iArr[i];
            this.f3934b = i2 != -1 ? i2 : 0;
            if (FlexboxLayoutManager.this.B.size() > this.f3934b) {
                this.f3933a = ((com.google.android.flexbox.b) FlexboxLayoutManager.this.B.get(this.f3934b)).o;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s() {
            this.f3933a = -1;
            this.f3934b = -1;
            this.f3935c = Integer.MIN_VALUE;
            this.f = false;
            this.g = false;
            if (FlexboxLayoutManager.this.j()) {
                if (FlexboxLayoutManager.this.v == 0) {
                    this.f3937e = FlexboxLayoutManager.this.u == 1;
                    return;
                } else {
                    this.f3937e = FlexboxLayoutManager.this.v == 2;
                    return;
                }
            }
            if (FlexboxLayoutManager.this.v == 0) {
                this.f3937e = FlexboxLayoutManager.this.u == 3;
            } else {
                this.f3937e = FlexboxLayoutManager.this.v == 2;
            }
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f3933a + ", mFlexLinePosition=" + this.f3934b + ", mCoordinate=" + this.f3935c + ", mPerpendicularCoordinate=" + this.f3936d + ", mLayoutFromEnd=" + this.f3937e + ", mValid=" + this.f + ", mAssignedFromSavedState=" + this.g + '}';
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3938a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f3939b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3940c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3941d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3942e;
        private int f;
        private int g;
        private int h;
        private int i;
        private boolean j;

        private c() {
            this.h = 1;
            this.i = 1;
        }

        static /* synthetic */ int i(c cVar) {
            int i = cVar.f3940c;
            cVar.f3940c = i + 1;
            return i;
        }

        static /* synthetic */ int j(c cVar) {
            int i = cVar.f3940c;
            cVar.f3940c = i - 1;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean w(RecyclerView.z zVar, List<com.google.android.flexbox.b> list) {
            int i;
            int i2 = this.f3941d;
            return i2 >= 0 && i2 < zVar.b() && (i = this.f3940c) >= 0 && i < list.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.f3938a + ", mFlexLinePosition=" + this.f3940c + ", mPosition=" + this.f3941d + ", mOffset=" + this.f3942e + ", mScrollingOffset=" + this.f + ", mLastScrollDelta=" + this.g + ", mItemDirection=" + this.h + ", mLayoutDirection=" + this.i + '}';
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d dVarO0 = RecyclerView.o.o0(context, attributeSet, i, i2);
        int i3 = dVarO0.f1325a;
        if (i3 != 0) {
            if (i3 == 1) {
                if (dVarO0.f1327c) {
                    N2(3);
                } else {
                    N2(2);
                }
            }
        } else if (dVarO0.f1327c) {
            N2(1);
        } else {
            N2(0);
        }
        O2(1);
        M2(4);
        I1(true);
        this.Q = context;
    }

    private int A2(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (T() == 0 || i == 0) {
            return 0;
        }
        j2();
        int i2 = 1;
        this.F.j = true;
        boolean z = !j() && this.z;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int iAbs = Math.abs(i);
        U2(i2, iAbs);
        int iK2 = this.F.f + k2(vVar, zVar, this.F);
        if (iK2 < 0) {
            return 0;
        }
        if (z) {
            if (iAbs > iK2) {
                i = (-i2) * iK2;
            }
        } else if (iAbs > iK2) {
            i = i2 * iK2;
        }
        this.H.r(-i);
        this.F.g = i;
        return i;
    }

    private int B2(int i) {
        int iMin;
        if (T() == 0 || i == 0) {
            return 0;
        }
        j2();
        boolean zJ = j();
        View view = this.R;
        int width = zJ ? view.getWidth() : view.getHeight();
        int iU0 = zJ ? u0() : g0();
        if (j0() == 1) {
            int iAbs = Math.abs(i);
            if (i < 0) {
                iMin = Math.min((iU0 + this.G.f3936d) - width, iAbs);
            } else {
                if (this.G.f3936d + i <= 0) {
                    return i;
                }
                iMin = this.G.f3936d;
            }
        } else {
            if (i > 0) {
                return Math.min((iU0 - this.G.f3936d) - width, i);
            }
            if (this.G.f3936d + i >= 0) {
                return i;
            }
            iMin = this.G.f3936d;
        }
        return -iMin;
    }

    private boolean C2(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int iU0 = u0() - getPaddingRight();
        int iG0 = g0() - getPaddingBottom();
        int iX2 = x2(view);
        int iZ2 = z2(view);
        int iY2 = y2(view);
        int iV2 = v2(view);
        return z ? (paddingLeft <= iX2 && iU0 >= iY2) && (paddingTop <= iZ2 && iG0 >= iV2) : (iX2 >= iU0 || iY2 >= paddingLeft) && (iZ2 >= iG0 || iV2 >= paddingTop);
    }

    private static boolean D0(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    private int D2(com.google.android.flexbox.b bVar, c cVar) {
        return j() ? E2(bVar, cVar) : F2(bVar, cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int E2(com.google.android.flexbox.b r22, com.google.android.flexbox.FlexboxLayoutManager.c r23) {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.E2(com.google.android.flexbox.b, com.google.android.flexbox.FlexboxLayoutManager$c):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int F2(com.google.android.flexbox.b r26, com.google.android.flexbox.FlexboxLayoutManager.c r27) {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.F2(com.google.android.flexbox.b, com.google.android.flexbox.FlexboxLayoutManager$c):int");
    }

    private void G2(RecyclerView.v vVar, c cVar) {
        if (cVar.j) {
            if (cVar.i == -1) {
                I2(vVar, cVar);
            } else {
                J2(vVar, cVar);
            }
        }
    }

    private void H2(RecyclerView.v vVar, int i, int i2) {
        while (i2 >= i) {
            w1(i2, vVar);
            i2--;
        }
    }

    private void I2(RecyclerView.v vVar, c cVar) {
        if (cVar.f < 0) {
            return;
        }
        this.H.h();
        int unused = cVar.f;
        int iT = T();
        if (iT == 0) {
            return;
        }
        int i = iT - 1;
        int i2 = this.C.f3950c[n0(S(i))];
        if (i2 == -1) {
            return;
        }
        com.google.android.flexbox.b bVar = this.B.get(i2);
        int i3 = i;
        while (true) {
            if (i3 < 0) {
                break;
            }
            View viewS = S(i3);
            if (!c2(viewS, cVar.f)) {
                break;
            }
            if (bVar.o == n0(viewS)) {
                if (i2 <= 0) {
                    iT = i3;
                    break;
                } else {
                    i2 += cVar.i;
                    bVar = this.B.get(i2);
                    iT = i3;
                }
            }
            i3--;
        }
        H2(vVar, iT, i);
    }

    private void J2(RecyclerView.v vVar, c cVar) {
        int iT;
        if (cVar.f >= 0 && (iT = T()) != 0) {
            int i = this.C.f3950c[n0(S(0))];
            int i2 = -1;
            if (i == -1) {
                return;
            }
            com.google.android.flexbox.b bVar = this.B.get(i);
            int i3 = 0;
            while (true) {
                if (i3 >= iT) {
                    break;
                }
                View viewS = S(i3);
                if (!d2(viewS, cVar.f)) {
                    break;
                }
                if (bVar.p == n0(viewS)) {
                    if (i >= this.B.size() - 1) {
                        i2 = i3;
                        break;
                    } else {
                        i += cVar.i;
                        bVar = this.B.get(i);
                        i2 = i3;
                    }
                }
                i3++;
            }
            H2(vVar, 0, i2);
        }
    }

    private void K2() {
        int iH0 = j() ? h0() : v0();
        this.F.f3939b = iH0 == 0 || iH0 == Integer.MIN_VALUE;
    }

    private void L2() {
        int iJ0 = j0();
        int i = this.u;
        if (i == 0) {
            this.z = iJ0 == 1;
            this.A = this.v == 2;
            return;
        }
        if (i == 1) {
            this.z = iJ0 != 1;
            this.A = this.v == 2;
            return;
        }
        if (i == 2) {
            boolean z = iJ0 == 1;
            this.z = z;
            if (this.v == 2) {
                this.z = !z;
            }
            this.A = false;
            return;
        }
        if (i != 3) {
            this.z = false;
            this.A = false;
            return;
        }
        boolean z2 = iJ0 == 1;
        this.z = z2;
        if (this.v == 2) {
            this.z = !z2;
        }
        this.A = true;
    }

    private boolean P1(View view, int i, int i2, RecyclerView.p pVar) {
        return (!view.isLayoutRequested() && C0() && D0(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && D0(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
    }

    private boolean P2(RecyclerView.z zVar, b bVar) {
        if (T() == 0) {
            return false;
        }
        View viewO2 = bVar.f3937e ? o2(zVar.b()) : l2(zVar.b());
        if (viewO2 == null) {
            return false;
        }
        bVar.r(viewO2);
        if (!zVar.e() && V1()) {
            if (this.H.g(viewO2) >= this.H.i() || this.H.d(viewO2) < this.H.m()) {
                bVar.f3935c = bVar.f3937e ? this.H.i() : this.H.m();
            }
        }
        return true;
    }

    private boolean Q2(RecyclerView.z zVar, b bVar, SavedState savedState) {
        int i;
        if (!zVar.e() && (i = this.K) != -1) {
            if (i >= 0 && i < zVar.b()) {
                bVar.f3933a = this.K;
                bVar.f3934b = this.C.f3950c[bVar.f3933a];
                SavedState savedState2 = this.J;
                if (savedState2 != null && savedState2.A(zVar.b())) {
                    bVar.f3935c = this.H.m() + savedState.f3932c;
                    bVar.g = true;
                    bVar.f3934b = -1;
                    return true;
                }
                if (this.L != Integer.MIN_VALUE) {
                    if (j() || !this.z) {
                        bVar.f3935c = this.H.m() + this.L;
                    } else {
                        bVar.f3935c = this.L - this.H.j();
                    }
                    return true;
                }
                View viewM = M(this.K);
                if (viewM == null) {
                    if (T() > 0) {
                        bVar.f3937e = this.K < n0(S(0));
                    }
                    bVar.q();
                } else {
                    if (this.H.e(viewM) > this.H.n()) {
                        bVar.q();
                        return true;
                    }
                    if (this.H.g(viewM) - this.H.m() < 0) {
                        bVar.f3935c = this.H.m();
                        bVar.f3937e = false;
                        return true;
                    }
                    if (this.H.i() - this.H.d(viewM) < 0) {
                        bVar.f3935c = this.H.i();
                        bVar.f3937e = true;
                        return true;
                    }
                    bVar.f3935c = bVar.f3937e ? this.H.d(viewM) + this.H.o() : this.H.g(viewM);
                }
                return true;
            }
            this.K = -1;
            this.L = Integer.MIN_VALUE;
        }
        return false;
    }

    private void R2(RecyclerView.z zVar, b bVar) {
        if (Q2(zVar, bVar, this.J) || P2(zVar, bVar)) {
            return;
        }
        bVar.q();
        bVar.f3933a = 0;
        bVar.f3934b = 0;
    }

    private void S2(int i) {
        int iN2 = n2();
        int iQ2 = q2();
        if (i >= iQ2) {
            return;
        }
        int iT = T();
        this.C.t(iT);
        this.C.u(iT);
        this.C.s(iT);
        if (i >= this.C.f3950c.length) {
            return;
        }
        this.S = i;
        View viewW2 = w2();
        if (viewW2 == null) {
            return;
        }
        if (iN2 > i || i > iQ2) {
            this.K = n0(viewW2);
            if (j() || !this.z) {
                this.L = this.H.g(viewW2) - this.H.m();
            } else {
                this.L = this.H.d(viewW2) + this.H.j();
            }
        }
    }

    private void T2(int i) {
        boolean z;
        int i2;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(u0(), v0());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(g0(), h0());
        int iU0 = u0();
        int iG0 = g0();
        if (j()) {
            int i3 = this.M;
            z = (i3 == Integer.MIN_VALUE || i3 == iU0) ? false : true;
            i2 = this.F.f3939b ? this.Q.getResources().getDisplayMetrics().heightPixels : this.F.f3938a;
        } else {
            int i4 = this.N;
            z = (i4 == Integer.MIN_VALUE || i4 == iG0) ? false : true;
            i2 = this.F.f3939b ? this.Q.getResources().getDisplayMetrics().widthPixels : this.F.f3938a;
        }
        int i5 = i2;
        this.M = iU0;
        this.N = iG0;
        int i6 = this.S;
        if (i6 == -1 && (this.K != -1 || z)) {
            if (this.G.f3937e) {
                return;
            }
            this.B.clear();
            this.T.a();
            if (j()) {
                this.C.e(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i5, this.G.f3933a, this.B);
            } else {
                this.C.h(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i5, this.G.f3933a, this.B);
            }
            this.B = this.T.f3953a;
            this.C.p(iMakeMeasureSpec, iMakeMeasureSpec2);
            this.C.W();
            b bVar = this.G;
            bVar.f3934b = this.C.f3950c[bVar.f3933a];
            this.F.f3940c = this.G.f3934b;
            return;
        }
        int iMin = i6 != -1 ? Math.min(i6, this.G.f3933a) : this.G.f3933a;
        this.T.a();
        if (j()) {
            if (this.B.size() > 0) {
                this.C.j(this.B, iMin);
                this.C.b(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i5, iMin, this.G.f3933a, this.B);
            } else {
                this.C.s(i);
                this.C.d(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i5, 0, this.B);
            }
        } else if (this.B.size() > 0) {
            this.C.j(this.B, iMin);
            this.C.b(this.T, iMakeMeasureSpec2, iMakeMeasureSpec, i5, iMin, this.G.f3933a, this.B);
        } else {
            this.C.s(i);
            this.C.g(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i5, 0, this.B);
        }
        this.B = this.T.f3953a;
        this.C.q(iMakeMeasureSpec, iMakeMeasureSpec2, iMin);
        this.C.X(iMin);
    }

    private void U2(int i, int i2) {
        this.F.i = i;
        boolean zJ = j();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(u0(), v0());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(g0(), h0());
        boolean z = !zJ && this.z;
        if (i == 1) {
            View viewS = S(T() - 1);
            this.F.f3942e = this.H.d(viewS);
            int iN0 = n0(viewS);
            View viewP2 = p2(viewS, this.B.get(this.C.f3950c[iN0]));
            this.F.h = 1;
            c cVar = this.F;
            cVar.f3941d = iN0 + cVar.h;
            if (this.C.f3950c.length <= this.F.f3941d) {
                this.F.f3940c = -1;
            } else {
                c cVar2 = this.F;
                cVar2.f3940c = this.C.f3950c[cVar2.f3941d];
            }
            if (z) {
                this.F.f3942e = this.H.g(viewP2);
                this.F.f = (-this.H.g(viewP2)) + this.H.m();
                c cVar3 = this.F;
                cVar3.f = cVar3.f >= 0 ? this.F.f : 0;
            } else {
                this.F.f3942e = this.H.d(viewP2);
                this.F.f = this.H.d(viewP2) - this.H.i();
            }
            if ((this.F.f3940c == -1 || this.F.f3940c > this.B.size() - 1) && this.F.f3941d <= getFlexItemCount()) {
                int i3 = i2 - this.F.f;
                this.T.a();
                if (i3 > 0) {
                    if (zJ) {
                        this.C.d(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i3, this.F.f3941d, this.B);
                    } else {
                        this.C.g(this.T, iMakeMeasureSpec, iMakeMeasureSpec2, i3, this.F.f3941d, this.B);
                    }
                    this.C.q(iMakeMeasureSpec, iMakeMeasureSpec2, this.F.f3941d);
                    this.C.X(this.F.f3941d);
                }
            }
        } else {
            View viewS2 = S(0);
            this.F.f3942e = this.H.g(viewS2);
            int iN02 = n0(viewS2);
            View viewM2 = m2(viewS2, this.B.get(this.C.f3950c[iN02]));
            this.F.h = 1;
            int i4 = this.C.f3950c[iN02];
            if (i4 == -1) {
                i4 = 0;
            }
            if (i4 > 0) {
                this.F.f3941d = iN02 - this.B.get(i4 - 1).b();
            } else {
                this.F.f3941d = -1;
            }
            this.F.f3940c = i4 > 0 ? i4 - 1 : 0;
            if (z) {
                this.F.f3942e = this.H.d(viewM2);
                this.F.f = this.H.d(viewM2) - this.H.i();
                c cVar4 = this.F;
                cVar4.f = cVar4.f >= 0 ? this.F.f : 0;
            } else {
                this.F.f3942e = this.H.g(viewM2);
                this.F.f = (-this.H.g(viewM2)) + this.H.m();
            }
        }
        c cVar5 = this.F;
        cVar5.f3938a = i2 - cVar5.f;
    }

    private void V2(b bVar, boolean z, boolean z2) {
        if (z2) {
            K2();
        } else {
            this.F.f3939b = false;
        }
        if (j() || !this.z) {
            this.F.f3938a = this.H.i() - bVar.f3935c;
        } else {
            this.F.f3938a = bVar.f3935c - getPaddingRight();
        }
        this.F.f3941d = bVar.f3933a;
        this.F.h = 1;
        this.F.i = 1;
        this.F.f3942e = bVar.f3935c;
        this.F.f = Integer.MIN_VALUE;
        this.F.f3940c = bVar.f3934b;
        if (!z || this.B.size() <= 1 || bVar.f3934b < 0 || bVar.f3934b >= this.B.size() - 1) {
            return;
        }
        com.google.android.flexbox.b bVar2 = this.B.get(bVar.f3934b);
        c.i(this.F);
        this.F.f3941d += bVar2.b();
    }

    private void W2(b bVar, boolean z, boolean z2) {
        if (z2) {
            K2();
        } else {
            this.F.f3939b = false;
        }
        if (j() || !this.z) {
            this.F.f3938a = bVar.f3935c - this.H.m();
        } else {
            this.F.f3938a = (this.R.getWidth() - bVar.f3935c) - this.H.m();
        }
        this.F.f3941d = bVar.f3933a;
        this.F.h = 1;
        this.F.i = -1;
        this.F.f3942e = bVar.f3935c;
        this.F.f = Integer.MIN_VALUE;
        this.F.f3940c = bVar.f3934b;
        if (!z || bVar.f3934b <= 0 || this.B.size() <= bVar.f3934b) {
            return;
        }
        com.google.android.flexbox.b bVar2 = this.B.get(bVar.f3934b);
        c.j(this.F);
        this.F.f3941d -= bVar2.b();
    }

    private boolean c2(View view, int i) {
        return (j() || !this.z) ? this.H.g(view) >= this.H.h() - i : this.H.d(view) <= i;
    }

    private boolean d2(View view, int i) {
        return (j() || !this.z) ? this.H.d(view) <= i : this.H.h() - this.H.g(view) <= i;
    }

    private void e2() {
        this.B.clear();
        this.G.s();
        this.G.f3936d = 0;
    }

    private int f2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        int iB = zVar.b();
        j2();
        View viewL2 = l2(iB);
        View viewO2 = o2(iB);
        if (zVar.b() == 0 || viewL2 == null || viewO2 == null) {
            return 0;
        }
        return Math.min(this.H.n(), this.H.d(viewO2) - this.H.g(viewL2));
    }

    private int g2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        int iB = zVar.b();
        View viewL2 = l2(iB);
        View viewO2 = o2(iB);
        if (zVar.b() != 0 && viewL2 != null && viewO2 != null) {
            int iN0 = n0(viewL2);
            int iN02 = n0(viewO2);
            int iAbs = Math.abs(this.H.d(viewO2) - this.H.g(viewL2));
            int i = this.C.f3950c[iN0];
            if (i != 0 && i != -1) {
                return Math.round((i * (iAbs / ((r4[iN02] - i) + 1))) + (this.H.m() - this.H.g(viewL2)));
            }
        }
        return 0;
    }

    private int h2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        int iB = zVar.b();
        View viewL2 = l2(iB);
        View viewO2 = o2(iB);
        if (zVar.b() == 0 || viewL2 == null || viewO2 == null) {
            return 0;
        }
        int iN2 = n2();
        return (int) ((Math.abs(this.H.d(viewO2) - this.H.g(viewL2)) / ((q2() - iN2) + 1)) * zVar.b());
    }

    private void i2() {
        if (this.F == null) {
            this.F = new c();
        }
    }

    private void j2() {
        if (this.H != null) {
            return;
        }
        if (j()) {
            if (this.v == 0) {
                this.H = k.a(this);
                this.I = k.c(this);
                return;
            } else {
                this.H = k.c(this);
                this.I = k.a(this);
                return;
            }
        }
        if (this.v == 0) {
            this.H = k.c(this);
            this.I = k.a(this);
        } else {
            this.H = k.a(this);
            this.I = k.c(this);
        }
    }

    private int k2(RecyclerView.v vVar, RecyclerView.z zVar, c cVar) {
        if (cVar.f != Integer.MIN_VALUE) {
            if (cVar.f3938a < 0) {
                cVar.f += cVar.f3938a;
            }
            G2(vVar, cVar);
        }
        int i = cVar.f3938a;
        int iA = cVar.f3938a;
        int iD2 = 0;
        boolean zJ = j();
        while (true) {
            if ((iA <= 0 && !this.F.f3939b) || !cVar.w(zVar, this.B)) {
                break;
            }
            com.google.android.flexbox.b bVar = this.B.get(cVar.f3940c);
            cVar.f3941d = bVar.o;
            iD2 += D2(bVar, cVar);
            if (zJ || !this.z) {
                cVar.f3942e += bVar.a() * cVar.i;
            } else {
                cVar.f3942e -= bVar.a() * cVar.i;
            }
            iA -= bVar.a();
        }
        cVar.f3938a -= iD2;
        if (cVar.f != Integer.MIN_VALUE) {
            cVar.f += iD2;
            if (cVar.f3938a < 0) {
                cVar.f += cVar.f3938a;
            }
            G2(vVar, cVar);
        }
        return i - cVar.f3938a;
    }

    private View l2(int i) {
        View viewS2 = s2(0, T(), i);
        if (viewS2 == null) {
            return null;
        }
        int i2 = this.C.f3950c[n0(viewS2)];
        if (i2 == -1) {
            return null;
        }
        return m2(viewS2, this.B.get(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View m2(android.view.View r6, com.google.android.flexbox.b r7) {
        /*
            r5 = this;
            boolean r0 = r5.j()
            int r7 = r7.h
            r1 = 1
        L7:
            if (r1 >= r7) goto L3f
            android.view.View r2 = r5.S(r1)
            if (r2 == 0) goto L3c
            int r3 = r2.getVisibility()
            r4 = 8
            if (r3 != r4) goto L18
            goto L3c
        L18:
            boolean r3 = r5.z
            if (r3 == 0) goto L2d
            if (r0 != 0) goto L2d
            androidx.recyclerview.widget.k r3 = r5.H
            int r3 = r3.d(r6)
            androidx.recyclerview.widget.k r4 = r5.H
            int r4 = r4.d(r2)
            if (r3 >= r4) goto L3c
            goto L3b
        L2d:
            androidx.recyclerview.widget.k r3 = r5.H
            int r3 = r3.g(r6)
            androidx.recyclerview.widget.k r4 = r5.H
            int r4 = r4.g(r2)
            if (r3 <= r4) goto L3c
        L3b:
            r6 = r2
        L3c:
            int r1 = r1 + 1
            goto L7
        L3f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.m2(android.view.View, com.google.android.flexbox.b):android.view.View");
    }

    private View o2(int i) {
        View viewS2 = s2(T() - 1, -1, i);
        if (viewS2 == null) {
            return null;
        }
        return p2(viewS2, this.B.get(this.C.f3950c[n0(viewS2)]));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View p2(android.view.View r6, com.google.android.flexbox.b r7) {
        /*
            r5 = this;
            boolean r0 = r5.j()
            int r1 = r5.T()
            int r1 = r1 + (-2)
            int r2 = r5.T()
            int r7 = r7.h
            int r2 = r2 - r7
            int r2 = r2 + (-1)
        L13:
            if (r1 <= r2) goto L4b
            android.view.View r7 = r5.S(r1)
            if (r7 == 0) goto L48
            int r3 = r7.getVisibility()
            r4 = 8
            if (r3 != r4) goto L24
            goto L48
        L24:
            boolean r3 = r5.z
            if (r3 == 0) goto L39
            if (r0 != 0) goto L39
            androidx.recyclerview.widget.k r3 = r5.H
            int r3 = r3.g(r6)
            androidx.recyclerview.widget.k r4 = r5.H
            int r4 = r4.g(r7)
            if (r3 <= r4) goto L48
            goto L47
        L39:
            androidx.recyclerview.widget.k r3 = r5.H
            int r3 = r3.d(r6)
            androidx.recyclerview.widget.k r4 = r5.H
            int r4 = r4.d(r7)
            if (r3 >= r4) goto L48
        L47:
            r6 = r7
        L48:
            int r1 = r1 + (-1)
            goto L13
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.p2(android.view.View, com.google.android.flexbox.b):android.view.View");
    }

    private View r2(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View viewS = S(i);
            if (C2(viewS, z)) {
                return viewS;
            }
            i += i3;
        }
        return null;
    }

    private View s2(int i, int i2, int i3) {
        j2();
        i2();
        int iM = this.H.m();
        int i4 = this.H.i();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewS = S(i);
            int iN0 = n0(viewS);
            if (iN0 >= 0 && iN0 < i3) {
                if (((RecyclerView.p) viewS.getLayoutParams()).w()) {
                    if (view2 == null) {
                        view2 = viewS;
                    }
                } else {
                    if (this.H.g(viewS) >= iM && this.H.d(viewS) <= i4) {
                        return viewS;
                    }
                    if (view == null) {
                        view = viewS;
                    }
                }
            }
            i += i5;
        }
        return view != null ? view : view2;
    }

    private int t2(int i, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iA2;
        int i2;
        if (!j() && this.z) {
            int iM = i - this.H.m();
            if (iM <= 0) {
                return 0;
            }
            iA2 = A2(iM, vVar, zVar);
        } else {
            int i3 = this.H.i() - i;
            if (i3 <= 0) {
                return 0;
            }
            iA2 = -A2(-i3, vVar, zVar);
        }
        int i4 = i + iA2;
        if (!z || (i2 = this.H.i() - i4) <= 0) {
            return iA2;
        }
        this.H.r(i2);
        return i2 + iA2;
    }

    private int u2(int i, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iA2;
        int iM;
        if (j() || !this.z) {
            int iM2 = i - this.H.m();
            if (iM2 <= 0) {
                return 0;
            }
            iA2 = -A2(iM2, vVar, zVar);
        } else {
            int i2 = this.H.i() - i;
            if (i2 <= 0) {
                return 0;
            }
            iA2 = A2(-i2, vVar, zVar);
        }
        int i3 = i + iA2;
        if (!z || (iM = i3 - this.H.m()) <= 0) {
            return iA2;
        }
        this.H.r(-iM);
        return iA2 - iM;
    }

    private int v2(View view) {
        return Y(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
    }

    private View w2() {
        return S(0);
    }

    private int x2(View view) {
        return a0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
    }

    private int y2(View view) {
        return d0(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
    }

    private int z2(View view) {
        return e0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int A(RecyclerView.z zVar) {
        return f2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int B(RecyclerView.z zVar) {
        g2(zVar);
        return g2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int C(RecyclerView.z zVar) {
        return h2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int D(RecyclerView.z zVar) {
        return f2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int E(RecyclerView.z zVar) {
        return g2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int F(RecyclerView.z zVar) {
        return h2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int F1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (!j()) {
            int iA2 = A2(i, vVar, zVar);
            this.P.clear();
            return iA2;
        }
        int iB2 = B2(i);
        this.G.f3936d += iB2;
        this.I.r(-iB2);
        return iB2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void G1(int i) {
        this.K = i;
        this.L = Integer.MIN_VALUE;
        SavedState savedState = this.J;
        if (savedState != null) {
            savedState.B();
        }
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int H1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (j()) {
            int iA2 = A2(i, vVar, zVar);
            this.P.clear();
            return iA2;
        }
        int iB2 = B2(i);
        this.G.f3936d += iB2;
        this.I.r(-iB2);
        return iB2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void M0(RecyclerView.g gVar, RecyclerView.g gVar2) {
        s1();
    }

    public void M2(int i) {
        int i2 = this.x;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                s1();
                e2();
            }
            this.x = i;
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p N() {
        return new LayoutParams(-2, -2);
    }

    public void N2(int i) {
        if (this.u != i) {
            s1();
            this.u = i;
            this.H = null;
            this.I = null;
            e2();
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p O(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void O0(RecyclerView recyclerView) {
        super.O0(recyclerView);
        this.R = (View) recyclerView.getParent();
    }

    public void O2(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        int i2 = this.v;
        if (i2 != i) {
            if (i2 == 0 || i == 0) {
                s1();
                e2();
            }
            this.v = i;
            this.H = null;
            this.I = null;
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Q0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.Q0(recyclerView, vVar);
        if (this.O) {
            t1(vVar);
            vVar.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S1(RecyclerView recyclerView, RecyclerView.z zVar, int i) {
        h hVar = new h(recyclerView.getContext());
        hVar.p(i);
        T1(hVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Z0(RecyclerView recyclerView, int i, int i2) {
        super.Z0(recyclerView, i, i2);
        S2(i);
    }

    @Override // com.google.android.flexbox.a
    public View a(int i) {
        View view = this.P.get(i);
        return view != null ? view : this.D.o(i);
    }

    @Override // com.google.android.flexbox.a
    public int b(View view, int i, int i2) {
        int iS0;
        int iR;
        if (j()) {
            iS0 = k0(view);
            iR = p0(view);
        } else {
            iS0 = s0(view);
            iR = R(view);
        }
        return iS0 + iR;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b1(RecyclerView recyclerView, int i, int i2, int i3) {
        super.b1(recyclerView, i, i2, i3);
        S2(Math.min(i, i2));
    }

    @Override // com.google.android.flexbox.a
    public void c(com.google.android.flexbox.b bVar) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(RecyclerView recyclerView, int i, int i2) {
        super.c1(recyclerView, i, i2);
        S2(i);
    }

    @Override // com.google.android.flexbox.a
    public int d(int i, int i2, int i3) {
        return RecyclerView.o.U(g0(), h0(), i2, i3, v());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void d1(RecyclerView recyclerView, int i, int i2) {
        super.d1(recyclerView, i, i2);
        S2(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF e(int i) {
        if (T() == 0) {
            return null;
        }
        int i2 = i < n0(S(0)) ? -1 : 1;
        return j() ? new PointF(0.0f, i2) : new PointF(i2, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e1(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.e1(recyclerView, i, i2, obj);
        S2(i);
    }

    @Override // com.google.android.flexbox.a
    public View f(int i) {
        return a(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f1(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i;
        int i2;
        this.D = vVar;
        this.E = zVar;
        int iB = zVar.b();
        if (iB == 0 && zVar.e()) {
            return;
        }
        L2();
        j2();
        i2();
        this.C.t(iB);
        this.C.u(iB);
        this.C.s(iB);
        this.F.j = false;
        SavedState savedState = this.J;
        if (savedState != null && savedState.A(iB)) {
            this.K = this.J.f3931b;
        }
        if (!this.G.f || this.K != -1 || this.J != null) {
            this.G.s();
            R2(zVar, this.G);
            this.G.f = true;
        }
        G(vVar);
        if (this.G.f3937e) {
            W2(this.G, false, true);
        } else {
            V2(this.G, false, true);
        }
        T2(iB);
        if (this.G.f3937e) {
            k2(vVar, zVar, this.F);
            i2 = this.F.f3942e;
            V2(this.G, true, false);
            k2(vVar, zVar, this.F);
            i = this.F.f3942e;
        } else {
            k2(vVar, zVar, this.F);
            i = this.F.f3942e;
            W2(this.G, true, false);
            k2(vVar, zVar, this.F);
            i2 = this.F.f3942e;
        }
        if (T() > 0) {
            if (this.G.f3937e) {
                u2(i2 + t2(i, vVar, zVar, true), vVar, zVar, false);
            } else {
                t2(i + u2(i2, vVar, zVar, true), vVar, zVar, false);
            }
        }
    }

    @Override // com.google.android.flexbox.a
    public void g(View view, int i, int i2, com.google.android.flexbox.b bVar) {
        t(view, t);
        if (j()) {
            int iK0 = k0(view) + p0(view);
            bVar.f3947e += iK0;
            bVar.f += iK0;
        } else {
            int iS0 = s0(view) + R(view);
            bVar.f3947e += iS0;
            bVar.f += iS0;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g1(RecyclerView.z zVar) {
        super.g1(zVar);
        this.J = null;
        this.K = -1;
        this.L = Integer.MIN_VALUE;
        this.S = -1;
        this.G.s();
        this.P.clear();
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.x;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.u;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return this.E.b();
    }

    @Override // com.google.android.flexbox.a
    public List<com.google.android.flexbox.b> getFlexLinesInternal() {
        return this.B;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.v;
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        if (this.B.size() == 0) {
            return 0;
        }
        int iMax = Integer.MIN_VALUE;
        int size = this.B.size();
        for (int i = 0; i < size; i++) {
            iMax = Math.max(iMax, this.B.get(i).f3947e);
        }
        return iMax;
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.y;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int size = this.B.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.B.get(i2).g;
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int h(int i, int i2, int i3) {
        return RecyclerView.o.U(u0(), v0(), i2, i3, u());
    }

    @Override // com.google.android.flexbox.a
    public void i(int i, View view) {
        this.P.put(i, view);
    }

    @Override // com.google.android.flexbox.a
    public boolean j() {
        int i = this.u;
        return i == 0 || i == 1;
    }

    @Override // com.google.android.flexbox.a
    public int k(View view) {
        int iK0;
        int iP0;
        if (j()) {
            iK0 = s0(view);
            iP0 = R(view);
        } else {
            iK0 = k0(view);
            iP0 = p0(view);
        }
        return iK0 + iP0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void k1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.J = (SavedState) parcelable;
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable l1() {
        if (this.J != null) {
            return new SavedState(this.J);
        }
        SavedState savedState = new SavedState();
        if (T() > 0) {
            View viewW2 = w2();
            savedState.f3931b = n0(viewW2);
            savedState.f3932c = this.H.g(viewW2) - this.H.m();
        } else {
            savedState.B();
        }
        return savedState;
    }

    public int n2() {
        View viewR2 = r2(0, T(), false);
        if (viewR2 == null) {
            return -1;
        }
        return n0(viewR2);
    }

    public int q2() {
        View viewR2 = r2(T() - 1, -1, false);
        if (viewR2 == null) {
            return -1;
        }
        return n0(viewR2);
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<com.google.android.flexbox.b> list) {
        this.B = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean u() {
        return !j() || u0() > this.R.getWidth();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean v() {
        return j() || g0() > this.R.getHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean w(RecyclerView.p pVar) {
        return pVar instanceof LayoutParams;
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3931b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3932c;

        static class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean A(int i) {
            int i2 = this.f3931b;
            return i2 >= 0 && i2 < i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void B() {
            this.f3931b = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.f3931b + ", mAnchorOffset=" + this.f3932c + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f3931b);
            parcel.writeInt(this.f3932c);
        }

        SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.f3931b = parcel.readInt();
            this.f3932c = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.f3931b = savedState.f3931b;
            this.f3932c = savedState.f3932c;
        }
    }

    public static class LayoutParams extends RecyclerView.p implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new a();
        private float f;
        private float g;
        private int h;
        private float i;
        private int j;
        private int k;
        private int l;
        private int m;
        private boolean n;

        static class a implements Parcelable.Creator<LayoutParams> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = -1;
            this.i = -1.0f;
            this.l = 16777215;
            this.m = 16777215;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int a() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int b() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int c() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int e() {
            return this.k;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int f() {
            return this.j;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean g() {
            return this.n;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float h() {
            return this.f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int k() {
            return this.m;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int l() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float m() {
            return this.i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int n() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int o() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float q() {
            return this.g;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int r() {
            return this.l;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int t() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.f);
            parcel.writeFloat(this.g);
            parcel.writeInt(this.h);
            parcel.writeFloat(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
            parcel.writeInt(this.m);
            parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = -1;
            this.i = -1.0f;
            this.l = 16777215;
            this.m = 16777215;
        }

        protected LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = -1;
            this.i = -1.0f;
            this.l = 16777215;
            this.m = 16777215;
            this.f = parcel.readFloat();
            this.g = parcel.readFloat();
            this.h = parcel.readInt();
            this.i = parcel.readFloat();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
            this.m = parcel.readInt();
            this.n = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }
}
