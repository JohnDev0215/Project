package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import defpackage.AbstractC0865h6;
import defpackage.C0474a6;
import defpackage.C0586c6;
import defpackage.C1032k6;
import defpackage.C1088l6;
import defpackage.C1199n6;
import defpackage.C1254o6;
import defpackage.C1309p6;
import defpackage.C1802y5;
import defpackage.H5;
import defpackage.RunnableC0976j6;
import defpackage.V5;
import defpackage.W5;
import defpackage.X5;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends W5 {
    public boolean C;
    public boolean D;
    public SavedState E;
    public int p;
    public C1309p6[] q;
    public H5 r;
    public H5 s;
    public int t;
    public final C1802y5 u;
    public boolean v;
    public BitSet x;
    public boolean w = false;
    public int y = -1;
    public int z = Integer.MIN_VALUE;
    public C1199n6 A = new C1199n6();
    public int B = 2;
    public final Rect F = new Rect();
    public final C1032k6 G = new C1032k6(this);
    public boolean H = false;
    public boolean I = true;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final Runnable f20J = new RunnableC0976j6(this);

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C1254o6();
        public boolean A;
        public boolean B;
        public int s;
        public int t;
        public int u;
        public int[] v;
        public int w;
        public int[] x;
        public List y;
        public boolean z;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.s);
            parcel.writeInt(this.t);
            parcel.writeInt(this.u);
            if (this.u > 0) {
                parcel.writeIntArray(this.v);
            }
            parcel.writeInt(this.w);
            if (this.w > 0) {
                parcel.writeIntArray(this.x);
            }
            parcel.writeInt(this.z ? 1 : 0);
            parcel.writeInt(this.A ? 1 : 0);
            parcel.writeInt(this.B ? 1 : 0);
            parcel.writeList(this.y);
        }

        public SavedState(Parcel parcel) {
            this.s = parcel.readInt();
            this.t = parcel.readInt();
            int i = parcel.readInt();
            this.u = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.v = iArr;
                parcel.readIntArray(iArr);
            }
            int i2 = parcel.readInt();
            this.w = i2;
            if (i2 > 0) {
                int[] iArr2 = new int[i2];
                this.x = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.z = parcel.readInt() == 1;
            this.A = parcel.readInt() == 1;
            this.B = parcel.readInt() == 1;
            this.y = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.u = savedState.u;
            this.s = savedState.s;
            this.t = savedState.t;
            this.v = savedState.v;
            this.w = savedState.w;
            this.x = savedState.x;
            this.z = savedState.z;
            this.A = savedState.A;
            this.B = savedState.B;
            this.y = savedState.y;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.p = -1;
        this.v = false;
        V5 v5Y = W5.y(context, attributeSet, i, i2);
        int i3 = v5Y.a;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        a(null);
        if (i3 != this.t) {
            this.t = i3;
            H5 h5 = this.r;
            this.r = this.s;
            this.s = h5;
            a0();
        }
        int i4 = v5Y.b;
        a(null);
        if (i4 != this.p) {
            this.A.a();
            a0();
            this.p = i4;
            this.x = new BitSet(this.p);
            this.q = new C1309p6[this.p];
            for (int i5 = 0; i5 < this.p; i5++) {
                this.q[i5] = new C1309p6(this, i5);
            }
            a0();
        }
        boolean z = v5Y.c;
        a(null);
        SavedState savedState = this.E;
        if (savedState != null && savedState.z != z) {
            savedState.z = z;
        }
        this.v = z;
        a0();
        this.u = new C1802y5();
        this.r = H5.a(this, this.t);
        this.s = H5.a(this, 1 - this.t);
    }

    @Override // defpackage.W5
    public boolean B() {
        return this.B != 0;
    }

    @Override // defpackage.W5
    public void G(RecyclerView recyclerView, C0474a6 c0474a6) {
        F();
        Runnable runnable = this.f20J;
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(runnable);
        }
        for (int i = 0; i < this.p; i++) {
            this.q[i].c();
        }
        recyclerView.requestLayout();
    }

    @Override // defpackage.W5
    public void H(AccessibilityEvent accessibilityEvent) {
        C0474a6 c0474a6 = this.b.s;
        I(accessibilityEvent);
        if (p() > 0) {
            View viewI0 = i0(false);
            View viewH0 = h0(false);
            if (viewI0 == null || viewH0 == null) {
                return;
            }
            x(viewI0);
            throw null;
        }
    }

    @Override // defpackage.W5
    public void K(RecyclerView recyclerView, int i, int i2) {
        l0(i, i2, 1);
    }

    @Override // defpackage.W5
    public void L(RecyclerView recyclerView, int i, int i2, int i3) {
        l0(i, i2, 8);
    }

    @Override // defpackage.W5
    public void M(RecyclerView recyclerView, int i, int i2) {
        l0(i, i2, 2);
    }

    @Override // defpackage.W5
    public void O(RecyclerView recyclerView, int i, int i2, Object obj) {
        l0(i, i2, 4);
    }

    @Override // defpackage.W5
    public void S(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.E = (SavedState) parcelable;
            a0();
        }
    }

    @Override // defpackage.W5
    public Parcelable T() {
        int iF;
        int iE;
        int[] iArr;
        SavedState savedState = this.E;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.z = this.v;
        savedState2.A = this.C;
        savedState2.B = this.D;
        C1199n6 c1199n6 = this.A;
        if (c1199n6 == null || (iArr = c1199n6.a) == null) {
            savedState2.w = 0;
        } else {
            savedState2.x = iArr;
            savedState2.w = iArr.length;
            savedState2.y = c1199n6.b;
        }
        if (p() > 0) {
            if (this.C) {
                k0();
            } else {
                j0();
            }
            savedState2.s = 0;
            View viewH0 = this.w ? h0(true) : i0(true);
            if (viewH0 != null) {
                x(viewH0);
                throw null;
            }
            savedState2.t = -1;
            int i = this.p;
            savedState2.u = i;
            savedState2.v = new int[i];
            for (int i2 = 0; i2 < this.p; i2++) {
                if (this.C) {
                    iF = this.q[i2].d(Integer.MIN_VALUE);
                    if (iF != Integer.MIN_VALUE) {
                        iE = this.r.d();
                        iF -= iE;
                    }
                } else {
                    iF = this.q[i2].f(Integer.MIN_VALUE);
                    if (iF != Integer.MIN_VALUE) {
                        iE = this.r.e();
                        iF -= iE;
                    }
                }
                savedState2.v[i2] = iF;
            }
        } else {
            savedState2.s = -1;
            savedState2.t = -1;
            savedState2.u = 0;
        }
        return savedState2;
    }

    @Override // defpackage.W5
    public void U(int i) {
        if (i == 0) {
            d0();
        }
    }

    @Override // defpackage.W5
    public void a(String str) {
        if (this.E == null) {
            super.a(str);
        }
    }

    @Override // defpackage.W5
    public boolean b() {
        return this.t == 0;
    }

    @Override // defpackage.W5
    public boolean c() {
        return this.t == 1;
    }

    @Override // defpackage.W5
    public boolean c0() {
        return this.E == null;
    }

    @Override // defpackage.W5
    public boolean d(X5 x5) {
        return x5 instanceof C1088l6;
    }

    public boolean d0() {
        if (p() != 0 && this.B != 0 && this.h) {
            if (this.w) {
                k0();
                j0();
            } else {
                j0();
                k0();
            }
            if (m0() != null) {
                this.A.a();
                this.g = true;
                a0();
                return true;
            }
        }
        return false;
    }

    public final int e0(C0586c6 c0586c6) {
        if (p() == 0) {
            return 0;
        }
        return AbstractC0865h6.a(c0586c6, this.r, i0(!this.I), h0(!this.I), this, this.I);
    }

    @Override // defpackage.W5
    public int f(C0586c6 c0586c6) {
        return e0(c0586c6);
    }

    public final int f0(C0586c6 c0586c6) {
        if (p() == 0) {
            return 0;
        }
        AbstractC0865h6.b(c0586c6, this.r, i0(!this.I), h0(!this.I), this, this.I, this.w);
        return 0;
    }

    @Override // defpackage.W5
    public int g(C0586c6 c0586c6) {
        f0(c0586c6);
        return 0;
    }

    public final int g0(C0586c6 c0586c6) {
        if (p() == 0) {
            return 0;
        }
        return AbstractC0865h6.c(c0586c6, this.r, i0(!this.I), h0(!this.I), this, this.I);
    }

    @Override // defpackage.W5
    public int h(C0586c6 c0586c6) {
        return g0(c0586c6);
    }

    public View h0(boolean z) {
        int iE = this.r.e();
        int iD = this.r.d();
        View view = null;
        for (int iP = p() - 1; iP >= 0; iP--) {
            View viewO = o(iP);
            int iC = this.r.c(viewO);
            int iB = this.r.b(viewO);
            if (iB > iE && iC < iD) {
                if (iB <= iD || !z) {
                    return viewO;
                }
                if (view == null) {
                    view = viewO;
                }
            }
        }
        return view;
    }

    @Override // defpackage.W5
    public int i(C0586c6 c0586c6) {
        return e0(c0586c6);
    }

    public View i0(boolean z) {
        int iE = this.r.e();
        int iD = this.r.d();
        int iP = p();
        View view = null;
        for (int i = 0; i < iP; i++) {
            View viewO = o(i);
            int iC = this.r.c(viewO);
            if (this.r.b(viewO) > iE && iC < iD) {
                if (iC >= iE || !z) {
                    return viewO;
                }
                if (view == null) {
                    view = viewO;
                }
            }
        }
        return view;
    }

    @Override // defpackage.W5
    public int j(C0586c6 c0586c6) {
        f0(c0586c6);
        return 0;
    }

    public int j0() {
        if (p() == 0) {
            return 0;
        }
        x(o(0));
        throw null;
    }

    @Override // defpackage.W5
    public int k(C0586c6 c0586c6) {
        return g0(c0586c6);
    }

    public int k0() {
        int iP = p();
        if (iP == 0) {
            return 0;
        }
        x(o(iP - 1));
        throw null;
    }

    @Override // defpackage.W5
    public X5 l() {
        return this.t == 0 ? new C1088l6(-2, -1) : new C1088l6(-1, -2);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l0(int r6, int r7, int r8) {
        /*
            r5 = this;
            boolean r0 = r5.w
            if (r0 == 0) goto L8
            r5.k0()
            goto Lb
        L8:
            r5.j0()
        Lb:
            r0 = 8
            if (r8 != r0) goto L18
            if (r6 >= r7) goto L14
            int r1 = r7 + 1
            goto L1a
        L14:
            int r1 = r6 + 1
            r2 = r7
            goto L1b
        L18:
            int r1 = r6 + r7
        L1a:
            r2 = r6
        L1b:
            n6 r3 = r5.A
            r3.d(r2)
            r3 = 1
            if (r8 == r3) goto L3a
            r4 = 2
            if (r8 == r4) goto L34
            if (r8 == r0) goto L29
            goto L3f
        L29:
            n6 r8 = r5.A
            r8.f(r6, r3)
            n6 r6 = r5.A
            r6.e(r7, r3)
            goto L3f
        L34:
            n6 r8 = r5.A
            r8.f(r6, r7)
            goto L3f
        L3a:
            n6 r8 = r5.A
            r8.e(r6, r7)
        L3f:
            if (r1 > 0) goto L42
            return
        L42:
            boolean r6 = r5.w
            if (r6 == 0) goto L4a
            r5.j0()
            goto L4d
        L4a:
            r5.k0()
        L4d:
            if (r2 > 0) goto L52
            r5.a0()
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.l0(int, int, int):void");
    }

    @Override // defpackage.W5
    public X5 m(Context context, AttributeSet attributeSet) {
        return new C1088l6(context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View m0() {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.m0():android.view.View");
    }

    @Override // defpackage.W5
    public X5 n(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new C1088l6((ViewGroup.MarginLayoutParams) layoutParams) : new C1088l6(layoutParams);
    }

    public boolean n0() {
        return s() == 1;
    }

    @Override // defpackage.W5
    public int r(C0474a6 c0474a6, C0586c6 c0586c6) {
        if (this.t == 1) {
            return this.p;
        }
        RecyclerView recyclerView = this.b;
        return 1;
    }

    @Override // defpackage.W5
    public int z(C0474a6 c0474a6, C0586c6 c0586c6) {
        if (this.t == 0) {
            return this.p;
        }
        RecyclerView recyclerView = this.b;
        return 1;
    }
}
