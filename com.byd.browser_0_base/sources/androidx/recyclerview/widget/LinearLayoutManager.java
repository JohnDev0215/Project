package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import androidx.recyclerview.widget.l;
import defpackage.gq2;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.m implements l.h, RecyclerView.v.b {
    public final a A;
    public final b B;
    public final int C;
    public final int[] D;
    public int p;
    public c q;
    public s r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public final boolean w;
    public int x;
    public int y;
    public SavedState z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int e;
        public int f;
        public boolean g;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt() == 1;
        }

        @SuppressLint({"UnknownNullness"})
        public SavedState(SavedState savedState) {
            this.e = savedState.e;
            this.f = savedState.f;
            this.g = savedState.g;
        }
    }

    public static class a {
        public s a;
        public int b;
        public int c;
        public boolean d;
        public boolean e;

        public a() {
            d();
        }

        public final void a() {
            this.c = this.d ? this.a.g() : this.a.k();
        }

        public final void b(View view, int i) {
            if (this.d) {
                this.c = this.a.m() + this.a.b(view);
            } else {
                this.c = this.a.e(view);
            }
            this.b = i;
        }

        public final void c(View view, int i) {
            int iM = this.a.m();
            if (iM >= 0) {
                b(view, i);
                return;
            }
            this.b = i;
            if (!this.d) {
                int iE = this.a.e(view);
                int iK = iE - this.a.k();
                this.c = iE;
                if (iK > 0) {
                    int iG = (this.a.g() - Math.min(0, (this.a.g() - iM) - this.a.b(view))) - (this.a.c(view) + iE);
                    if (iG < 0) {
                        this.c -= Math.min(iK, -iG);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG2 = (this.a.g() - iM) - this.a.b(view);
            this.c = this.a.g() - iG2;
            if (iG2 > 0) {
                int iC = this.c - this.a.c(view);
                int iK2 = this.a.k();
                int iMin = iC - (Math.min(this.a.e(view) - iK2, 0) + iK2);
                if (iMin < 0) {
                    this.c = Math.min(iG2, -iMin) + this.c;
                }
            }
        }

        public final void d() {
            this.b = -1;
            this.c = Integer.MIN_VALUE;
            this.d = false;
            this.e = false;
        }

        public final String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }
    }

    public static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;
    }

    public static class c {
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int j;
        public boolean l;
        public boolean a = true;
        public int h = 0;
        public int i = 0;
        public List<RecyclerView.a0> k = null;

        public final void a(View view) {
            int iA;
            int size = this.k.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.k.get(i2).e;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.c() && (iA = (layoutParams.a() - this.d) * this.e) >= 0 && iA < i) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    } else {
                        i = iA;
                    }
                }
            }
            if (view2 == null) {
                this.d = -1;
            } else {
                this.d = ((RecyclerView.LayoutParams) view2.getLayoutParams()).a();
            }
        }

        public final View b(RecyclerView.s sVar) {
            List<RecyclerView.a0> list = this.k;
            if (list == null) {
                View view = sVar.k(Long.MAX_VALUE, this.d).e;
                this.d += this.e;
                return view;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view2 = this.k.get(i).e;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view2.getLayoutParams();
                if (!layoutParams.c() && this.d == layoutParams.a()) {
                    a(view2);
                    return view2;
                }
            }
            return null;
        }
    }

    public LinearLayoutManager(@SuppressLint({"UnknownNullness"}) Context context) {
        this(1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean D0() {
        boolean z;
        if (this.m == 1073741824 || this.l == 1073741824) {
            return false;
        }
        int iA = A();
        int i = 0;
        while (true) {
            if (i >= iA) {
                z = false;
                break;
            }
            ViewGroup.LayoutParams layoutParams = z(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                z = true;
                break;
            }
            i++;
        }
        return z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public void F0(RecyclerView recyclerView, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.a = i;
        G0(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean H0() {
        return this.z == null && this.s == this.v;
    }

    public void I0(RecyclerView.w wVar, int[] iArr) {
        int i;
        int iL = wVar.a != -1 ? this.r.l() : 0;
        if (this.q.f == -1) {
            i = 0;
        } else {
            i = iL;
            iL = 0;
        }
        iArr[0] = iL;
        iArr[1] = i;
    }

    public void J0(RecyclerView.w wVar, c cVar, RecyclerView.m.c cVar2) {
        int i = cVar.d;
        if (i < 0 || i >= wVar.b()) {
            return;
        }
        ((k.b) cVar2).a(i, Math.max(0, cVar.g));
    }

    public final int K0(RecyclerView.w wVar) {
        if (A() == 0) {
            return 0;
        }
        O0();
        s sVar = this.r;
        boolean z = !this.w;
        return y.a(wVar, sVar, R0(z), Q0(z), this, this.w);
    }

    public final int L0(RecyclerView.w wVar) {
        if (A() == 0) {
            return 0;
        }
        O0();
        s sVar = this.r;
        boolean z = !this.w;
        return y.b(wVar, sVar, R0(z), Q0(z), this, this.w, this.u);
    }

    public final int M0(RecyclerView.w wVar) {
        if (A() == 0) {
            return 0;
        }
        O0();
        s sVar = this.r;
        boolean z = !this.w;
        return y.c(wVar, sVar, R0(z), Q0(z), this, this.w);
    }

    public final int N0(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.p == 1) ? 1 : Integer.MIN_VALUE : this.p == 0 ? 1 : Integer.MIN_VALUE : this.p == 1 ? -1 : Integer.MIN_VALUE : this.p == 0 ? -1 : Integer.MIN_VALUE : (this.p != 1 && Z0()) ? -1 : 1 : (this.p != 1 && Z0()) ? 1 : -1;
    }

    public final void O0() {
        if (this.q == null) {
            this.q = new c();
        }
    }

    public final int P0(RecyclerView.s sVar, c cVar, RecyclerView.w wVar, boolean z) {
        int i = cVar.c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            c1(sVar, cVar);
        }
        int i3 = cVar.c + cVar.h;
        while (true) {
            if (!cVar.l && i3 <= 0) {
                break;
            }
            int i4 = cVar.d;
            if (!(i4 >= 0 && i4 < wVar.b())) {
                break;
            }
            b bVar = this.B;
            bVar.a = 0;
            bVar.b = false;
            bVar.c = false;
            bVar.d = false;
            a1(sVar, wVar, cVar, bVar);
            if (!bVar.b) {
                int i5 = cVar.b;
                int i6 = bVar.a;
                cVar.b = (cVar.f * i6) + i5;
                if (!bVar.c || cVar.k != null || !wVar.g) {
                    cVar.c -= i6;
                    i3 -= i6;
                }
                int i7 = cVar.g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + i6;
                    cVar.g = i8;
                    int i9 = cVar.c;
                    if (i9 < 0) {
                        cVar.g = i8 + i9;
                    }
                    c1(sVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean Q() {
        return true;
    }

    public final View Q0(boolean z) {
        return this.u ? T0(0, A(), z, true) : T0(A() - 1, -1, z, true);
    }

    public final View R0(boolean z) {
        return this.u ? T0(A() - 1, -1, z, true) : T0(0, A(), z, true);
    }

    public final View S0(int i, int i2) {
        int i3;
        int i4;
        O0();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return z(i);
        }
        if (this.r.e(z(i)) < this.r.k()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.p == 0 ? this.c.a(i, i2, i3, i4) : this.d.a(i, i2, i3, i4);
    }

    public final View T0(int i, int i2, boolean z, boolean z2) {
        O0();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        return this.p == 0 ? this.c.a(i, i2, i3, i4) : this.d.a(i, i2, i3, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View U0(androidx.recyclerview.widget.RecyclerView.s r17, androidx.recyclerview.widget.RecyclerView.w r18, boolean r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r16.O0()
            int r1 = r16.A()
            r2 = 0
            r3 = 1
            if (r20 == 0) goto L15
            int r1 = r16.A()
            int r1 = r1 - r3
            r4 = -1
            r5 = r4
            goto L18
        L15:
            r4 = r1
            r1 = r2
            r5 = r3
        L18:
            int r6 = r18.b()
            androidx.recyclerview.widget.s r7 = r0.r
            int r7 = r7.k()
            androidx.recyclerview.widget.s r8 = r0.r
            int r8 = r8.g()
            r9 = 0
            r10 = r9
            r11 = r10
        L2b:
            if (r1 == r4) goto L7a
            android.view.View r12 = r0.z(r1)
            int r13 = androidx.recyclerview.widget.RecyclerView.m.K(r12)
            androidx.recyclerview.widget.s r14 = r0.r
            int r14 = r14.e(r12)
            androidx.recyclerview.widget.s r15 = r0.r
            int r15 = r15.b(r12)
            if (r13 < 0) goto L78
            if (r13 >= r6) goto L78
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            androidx.recyclerview.widget.RecyclerView$LayoutParams r13 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r13
            boolean r13 = r13.c()
            if (r13 == 0) goto L55
            if (r11 != 0) goto L78
            r11 = r12
            goto L78
        L55:
            if (r15 > r7) goto L5b
            if (r14 >= r7) goto L5b
            r13 = r3
            goto L5c
        L5b:
            r13 = r2
        L5c:
            if (r14 < r8) goto L62
            if (r15 <= r8) goto L62
            r14 = r3
            goto L63
        L62:
            r14 = r2
        L63:
            if (r13 != 0) goto L69
            if (r14 == 0) goto L68
            goto L69
        L68:
            return r12
        L69:
            if (r19 == 0) goto L71
            if (r14 == 0) goto L6e
            goto L73
        L6e:
            if (r9 != 0) goto L78
            goto L77
        L71:
            if (r13 == 0) goto L75
        L73:
            r10 = r12
            goto L78
        L75:
            if (r9 != 0) goto L78
        L77:
            r9 = r12
        L78:
            int r1 = r1 + r5
            goto L2b
        L7a:
            if (r9 == 0) goto L7d
            goto L82
        L7d:
            if (r10 == 0) goto L81
            r9 = r10
            goto L82
        L81:
            r9 = r11
        L82:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.U0(androidx.recyclerview.widget.RecyclerView$s, androidx.recyclerview.widget.RecyclerView$w, boolean, boolean):android.view.View");
    }

    public final int V0(int i, RecyclerView.s sVar, RecyclerView.w wVar, boolean z) {
        int iG;
        int iG2 = this.r.g() - i;
        if (iG2 <= 0) {
            return 0;
        }
        int i2 = -f1(-iG2, sVar, wVar);
        int i3 = i + i2;
        if (!z || (iG = this.r.g() - i3) <= 0) {
            return i2;
        }
        this.r.p(iG);
        return iG + i2;
    }

    public final int W0(int i, RecyclerView.s sVar, RecyclerView.w wVar, boolean z) {
        int iK;
        int iK2 = i - this.r.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i2 = -f1(iK2, sVar, wVar);
        int i3 = i + i2;
        if (!z || (iK = i3 - this.r.k()) <= 0) {
            return i2;
        }
        this.r.p(-iK);
        return i2 - iK;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public View X(View view, int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        int iN0;
        e1();
        if (A() == 0 || (iN0 = N0(i)) == Integer.MIN_VALUE) {
            return null;
        }
        O0();
        j1(iN0, (int) (this.r.l() * 0.33333334f), false, wVar);
        c cVar = this.q;
        cVar.g = Integer.MIN_VALUE;
        cVar.a = false;
        P0(sVar, cVar, wVar, true);
        View viewS0 = iN0 == -1 ? this.u ? S0(A() - 1, -1) : S0(0, A()) : this.u ? S0(0, A()) : S0(A() - 1, -1);
        View viewY0 = iN0 == -1 ? Y0() : X0();
        if (!viewY0.hasFocusable()) {
            return viewS0;
        }
        if (viewS0 == null) {
            return null;
        }
        return viewY0;
    }

    public final View X0() {
        return z(this.u ? 0 : A() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final void Y(AccessibilityEvent accessibilityEvent) {
        super.Y(accessibilityEvent);
        if (A() > 0) {
            accessibilityEvent.setFromIndex(b());
            accessibilityEvent.setToIndex(d());
        }
    }

    public final View Y0() {
        return z(this.u ? A() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void Z(RecyclerView.s sVar, RecyclerView.w wVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.Z(sVar, wVar, accessibilityNodeInfoCompat);
        RecyclerView.e eVar = this.b.q;
        if (eVar == null || eVar.k() <= 0) {
            return;
        }
        accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.z);
    }

    public final boolean Z0() {
        return E() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.v.b
    @SuppressLint({"UnknownNullness"})
    public final PointF a(int i) {
        if (A() == 0) {
            return null;
        }
        int i2 = (i < RecyclerView.m.K(z(0))) != this.u ? -1 : 1;
        return this.p == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    public void a1(RecyclerView.s sVar, RecyclerView.w wVar, c cVar, b bVar) {
        int iJ;
        int i;
        int iD;
        int i2;
        int iD2;
        View viewB = cVar.b(sVar);
        if (viewB == null) {
            bVar.b = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewB.getLayoutParams();
        if (cVar.k == null) {
            if (this.u == (cVar.f == -1)) {
                e(viewB, -1, false);
            } else {
                e(viewB, 0, false);
            }
        } else {
            if (this.u == (cVar.f == -1)) {
                e(viewB, -1, true);
            } else {
                e(viewB, 0, true);
            }
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) viewB.getLayoutParams();
        Rect rectM = this.b.M(viewB);
        int i3 = rectM.left + rectM.right + 0;
        int i4 = rectM.top + rectM.bottom + 0;
        int iB = RecyclerView.m.B(h(), this.n, this.l, I() + H() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin + i3, ((ViewGroup.MarginLayoutParams) layoutParams2).width);
        int iB2 = RecyclerView.m.B(i(), this.o, this.m, G() + J() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) layoutParams2).height);
        if (C0(viewB, iB, iB2, layoutParams2)) {
            viewB.measure(iB, iB2);
        }
        bVar.a = this.r.c(viewB);
        if (this.p == 1) {
            if (Z0()) {
                iD = this.n - I();
                iD2 = iD - this.r.d(viewB);
            } else {
                int iH = H();
                iD = this.r.d(viewB) + iH;
                iD2 = iH;
            }
            if (cVar.f == -1) {
                i2 = cVar.b;
                iJ = i2 - bVar.a;
            } else {
                iJ = cVar.b;
                i2 = bVar.a + iJ;
            }
        } else {
            iJ = J();
            int iD3 = this.r.d(viewB) + iJ;
            if (cVar.f == -1) {
                iD = cVar.b;
                i = iD - bVar.a;
            } else {
                i = cVar.b;
                iD = bVar.a + i;
            }
            int i5 = i;
            i2 = iD3;
            iD2 = i5;
        }
        RecyclerView.m.S(viewB, iD2, iJ, iD, i2);
        if (layoutParams.c() || layoutParams.b()) {
            bVar.c = true;
        }
        bVar.d = viewB.hasFocusable();
    }

    public int b() {
        View viewT0 = T0(0, A(), false, true);
        if (viewT0 == null) {
            return -1;
        }
        return RecyclerView.m.K(viewT0);
    }

    @Override // androidx.recyclerview.widget.l.h
    public final void c(View view, View view2) {
        f("Cannot drop a view during a scroll or layout calculation");
        O0();
        e1();
        int iK = RecyclerView.m.K(view);
        int iK2 = RecyclerView.m.K(view2);
        byte b2 = iK < iK2 ? (byte) 1 : (byte) -1;
        if (this.u) {
            if (b2 == 1) {
                g1(iK2, this.r.g() - (this.r.c(view) + this.r.e(view2)));
                return;
            } else {
                g1(iK2, this.r.g() - this.r.b(view2));
                return;
            }
        }
        if (b2 == -1) {
            g1(iK2, this.r.e(view2));
        } else {
            g1(iK2, this.r.b(view2) - this.r.c(view));
        }
    }

    public final void c1(RecyclerView.s sVar, c cVar) {
        if (!cVar.a || cVar.l) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f == -1) {
            int iA = A();
            if (i < 0) {
                return;
            }
            int iF = (this.r.f() - i) + i2;
            if (this.u) {
                for (int i3 = 0; i3 < iA; i3++) {
                    View viewZ = z(i3);
                    if (this.r.e(viewZ) < iF || this.r.o(viewZ) < iF) {
                        d1(sVar, 0, i3);
                        return;
                    }
                }
                return;
            }
            int i4 = iA - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View viewZ2 = z(i5);
                if (this.r.e(viewZ2) < iF || this.r.o(viewZ2) < iF) {
                    d1(sVar, i4, i5);
                    return;
                }
            }
            return;
        }
        if (i < 0) {
            return;
        }
        int i6 = i - i2;
        int iA2 = A();
        if (!this.u) {
            for (int i7 = 0; i7 < iA2; i7++) {
                View viewZ3 = z(i7);
                if (this.r.b(viewZ3) > i6 || this.r.n(viewZ3) > i6) {
                    d1(sVar, 0, i7);
                    return;
                }
            }
            return;
        }
        int i8 = iA2 - 1;
        for (int i9 = i8; i9 >= 0; i9--) {
            View viewZ4 = z(i9);
            if (this.r.b(viewZ4) > i6 || this.r.n(viewZ4) > i6) {
                d1(sVar, i8, i9);
                return;
            }
        }
    }

    public int d() {
        View viewT0 = T0(A() - 1, -1, false, true);
        if (viewT0 == null) {
            return -1;
        }
        return RecyclerView.m.K(viewT0);
    }

    public final void d1(RecyclerView.s sVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                View viewZ = z(i);
                r0(i);
                sVar.h(viewZ);
                i--;
            }
            return;
        }
        while (true) {
            i2--;
            if (i2 < i) {
                return;
            }
            View viewZ2 = z(i2);
            r0(i2);
            sVar.h(viewZ2);
        }
    }

    public final void e1() {
        if (this.p == 1 || !Z0()) {
            this.u = this.t;
        } else {
            this.u = !this.t;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final void f(String str) {
        if (this.z == null) {
            super.f(str);
        }
    }

    public final int f1(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (A() == 0 || i == 0) {
            return 0;
        }
        O0();
        this.q.a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        j1(i2, iAbs, true, wVar);
        c cVar = this.q;
        int iP0 = P0(sVar, cVar, wVar, false) + cVar.g;
        if (iP0 < 0) {
            return 0;
        }
        if (iAbs > iP0) {
            i = i2 * iP0;
        }
        this.r.p(-i);
        this.q.j = i;
        return i;
    }

    public final void g1(int i, int i2) {
        this.x = i;
        this.y = i2;
        SavedState savedState = this.z;
        if (savedState != null) {
            savedState.e = -1;
        }
        t0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean h() {
        return this.p == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x018b  */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // androidx.recyclerview.widget.RecyclerView.m
    @android.annotation.SuppressLint({"UnknownNullness"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h0(androidx.recyclerview.widget.RecyclerView.s r18, androidx.recyclerview.widget.RecyclerView.w r19) {
        /*
            Method dump skipped, instruction units count: 1096
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.h0(androidx.recyclerview.widget.RecyclerView$s, androidx.recyclerview.widget.RecyclerView$w):void");
    }

    public final void h1(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(gq2.e("invalid orientation:", i));
        }
        f(null);
        if (i != this.p || this.r == null) {
            s sVarA = s.a(this, i);
            this.r = sVarA;
            this.A.a = sVarA;
            this.p = i;
            t0();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean i() {
        return this.p == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public void i0(RecyclerView.w wVar) {
        this.z = null;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.A.d();
    }

    public void i1(boolean z) {
        f(null);
        if (this.v == z) {
            return;
        }
        this.v = z;
        t0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final void j0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.z = savedState;
            if (this.x != -1) {
                savedState.e = -1;
            }
            t0();
        }
    }

    public final void j1(int i, int i2, boolean z, RecyclerView.w wVar) {
        int iK;
        this.q.l = this.r.i() == 0 && this.r.f() == 0;
        this.q.f = i;
        int[] iArr = this.D;
        iArr[0] = 0;
        iArr[1] = 0;
        I0(wVar, iArr);
        int iMax = Math.max(0, iArr[0]);
        int iMax2 = Math.max(0, iArr[1]);
        boolean z2 = i == 1;
        c cVar = this.q;
        int i3 = z2 ? iMax2 : iMax;
        cVar.h = i3;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.i = iMax;
        if (z2) {
            cVar.h = this.r.h() + i3;
            View viewX0 = X0();
            c cVar2 = this.q;
            cVar2.e = this.u ? -1 : 1;
            int iK2 = RecyclerView.m.K(viewX0);
            c cVar3 = this.q;
            cVar2.d = iK2 + cVar3.e;
            cVar3.b = this.r.b(viewX0);
            iK = this.r.b(viewX0) - this.r.g();
        } else {
            View viewY0 = Y0();
            c cVar4 = this.q;
            cVar4.h = this.r.k() + cVar4.h;
            c cVar5 = this.q;
            cVar5.e = this.u ? 1 : -1;
            int iK3 = RecyclerView.m.K(viewY0);
            c cVar6 = this.q;
            cVar5.d = iK3 + cVar6.e;
            cVar6.b = this.r.e(viewY0);
            iK = (-this.r.e(viewY0)) + this.r.k();
        }
        c cVar7 = this.q;
        cVar7.c = i2;
        if (z) {
            cVar7.c = i2 - iK;
        }
        cVar7.g = iK;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final Parcelable k0() {
        SavedState savedState = this.z;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (A() > 0) {
            O0();
            boolean z = this.s ^ this.u;
            savedState2.g = z;
            if (z) {
                View viewX0 = X0();
                savedState2.f = this.r.g() - this.r.b(viewX0);
                savedState2.e = RecyclerView.m.K(viewX0);
            } else {
                View viewY0 = Y0();
                savedState2.e = RecyclerView.m.K(viewY0);
                savedState2.f = this.r.e(viewY0) - this.r.k();
            }
        } else {
            savedState2.e = -1;
        }
        return savedState2;
    }

    public final void k1(int i, int i2) {
        this.q.c = this.r.g() - i2;
        c cVar = this.q;
        cVar.e = this.u ? -1 : 1;
        cVar.d = i;
        cVar.f = 1;
        cVar.b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final void l(int i, int i2, RecyclerView.w wVar, RecyclerView.m.c cVar) {
        if (this.p != 0) {
            i = i2;
        }
        if (A() == 0 || i == 0) {
            return;
        }
        O0();
        j1(i > 0 ? 1 : -1, Math.abs(i), true, wVar);
        J0(wVar, this.q, cVar);
    }

    public final void l1(int i, int i2) {
        this.q.c = i2 - this.r.k();
        c cVar = this.q;
        cVar.d = i;
        cVar.e = this.u ? 1 : -1;
        cVar.f = -1;
        cVar.b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    @Override // androidx.recyclerview.widget.RecyclerView.m
    @android.annotation.SuppressLint({"UnknownNullness"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(int r7, androidx.recyclerview.widget.RecyclerView.m.c r8) {
        /*
            r6 = this;
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r0 = r6.z
            r1 = 1
            r2 = -1
            r3 = 0
            if (r0 == 0) goto L13
            int r4 = r0.e
            if (r4 < 0) goto Ld
            r5 = r1
            goto Le
        Ld:
            r5 = r3
        Le:
            if (r5 == 0) goto L13
            boolean r0 = r0.g
            goto L22
        L13:
            r6.e1()
            boolean r0 = r6.u
            int r4 = r6.x
            if (r4 != r2) goto L22
            if (r0 == 0) goto L21
            int r4 = r7 + (-1)
            goto L22
        L21:
            r4 = r3
        L22:
            if (r0 == 0) goto L25
            r1 = r2
        L25:
            r0 = r3
        L26:
            int r2 = r6.C
            if (r0 >= r2) goto L38
            if (r4 < 0) goto L38
            if (r4 >= r7) goto L38
            r2 = r8
            androidx.recyclerview.widget.k$b r2 = (androidx.recyclerview.widget.k.b) r2
            r2.a(r4, r3)
            int r4 = r4 + r1
            int r0 = r0 + 1
            goto L26
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.m(int, androidx.recyclerview.widget.RecyclerView$m$c):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean m0(int i, Bundle bundle) {
        int iMin;
        if (super.m0(i, bundle)) {
            return true;
        }
        if (i == 16908343 && bundle != null) {
            if (this.p == 1) {
                int i2 = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
                if (i2 < 0) {
                    return false;
                }
                RecyclerView recyclerView = this.b;
                iMin = Math.min(i2, N(recyclerView.g, recyclerView.m0) - 1);
            } else {
                int i3 = bundle.getInt("android.view.accessibility.action.ARGUMENT_COLUMN_INT", -1);
                if (i3 < 0) {
                    return false;
                }
                RecyclerView recyclerView2 = this.b;
                iMin = Math.min(i3, C(recyclerView2.g, recyclerView2.m0) - 1);
            }
            if (iMin >= 0) {
                g1(iMin, 0);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final int n(RecyclerView.w wVar) {
        return K0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public int o(RecyclerView.w wVar) {
        return L0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public int p(RecyclerView.w wVar) {
        return M0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final int q(RecyclerView.w wVar) {
        return K0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public int r(RecyclerView.w wVar) {
        return L0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public int s(RecyclerView.w wVar) {
        return M0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public View u(int i) {
        int iA = A();
        if (iA == 0) {
            return null;
        }
        int iK = i - RecyclerView.m.K(z(0));
        if (iK >= 0 && iK < iA) {
            View viewZ = z(iK);
            if (RecyclerView.m.K(viewZ) == i) {
                return viewZ;
            }
        }
        return super.u(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public int u0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (this.p == 1) {
            return 0;
        }
        return f1(i, sVar, wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public RecyclerView.LayoutParams v() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void v0(int i) {
        this.x = i;
        this.y = Integer.MIN_VALUE;
        SavedState savedState = this.z;
        if (savedState != null) {
            savedState.e = -1;
        }
        t0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public int w0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (this.p == 0) {
            return 0;
        }
        return f1(i, sVar, wVar);
    }

    public LinearLayoutManager(int i) {
        this.p = 1;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new a();
        this.B = new b();
        this.C = 2;
        this.D = new int[2];
        h1(i);
        f(null);
        if (this.t) {
            this.t = false;
            t0();
        }
    }

    @SuppressLint({"UnknownNullness"})
    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.p = 1;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new a();
        this.B = new b();
        this.C = 2;
        this.D = new int[2];
        RecyclerView.m.d dVarL = RecyclerView.m.L(context, attributeSet, i, i2);
        h1(dVarL.a);
        boolean z = dVarL.c;
        f(null);
        if (z != this.t) {
            this.t = z;
            t0();
        }
        i1(dVarL.d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @SuppressLint({"UnknownNullness"})
    public final void W(RecyclerView recyclerView) {
    }

    public void b1(RecyclerView.s sVar, RecyclerView.w wVar, a aVar, int i) {
    }
}
