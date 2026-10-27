package androidx.recyclerview.widget;

import a.b.k.l;
import a.p.e.n;
import a.p.e.s;
import a.p.e.w;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.m implements RecyclerView.x.b {
    public int A;
    public int B;
    public d C;
    public final a D;
    public final b E;
    public int F;
    public int[] G;
    public int s;
    public c t;
    public w u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w f1597a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1598b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1599c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1600d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1601e;

        public a() {
            d();
        }

        public void a() {
            this.f1599c = this.f1600d ? this.f1597a.g() : this.f1597a.k();
        }

        public void b(View view, int i) {
            if (this.f1600d) {
                this.f1599c = this.f1597a.m() + this.f1597a.b(view);
            } else {
                this.f1599c = this.f1597a.e(view);
            }
            this.f1598b = i;
        }

        public void c(View view, int i) {
            int iMin;
            int iM = this.f1597a.m();
            if (iM >= 0) {
                b(view, i);
                return;
            }
            this.f1598b = i;
            if (this.f1600d) {
                int iG = (this.f1597a.g() - iM) - this.f1597a.b(view);
                this.f1599c = this.f1597a.g() - iG;
                if (iG <= 0) {
                    return;
                }
                int iC = this.f1599c - this.f1597a.c(view);
                int iK = this.f1597a.k();
                int iMin2 = iC - (Math.min(this.f1597a.e(view) - iK, 0) + iK);
                if (iMin2 >= 0) {
                    return;
                }
                iMin = Math.min(iG, -iMin2) + this.f1599c;
            } else {
                int iE = this.f1597a.e(view);
                int iK2 = iE - this.f1597a.k();
                this.f1599c = iE;
                if (iK2 <= 0) {
                    return;
                }
                int iG2 = (this.f1597a.g() - Math.min(0, (this.f1597a.g() - iM) - this.f1597a.b(view))) - (this.f1597a.c(view) + iE);
                if (iG2 >= 0) {
                    return;
                } else {
                    iMin = this.f1599c - Math.min(iK2, -iG2);
                }
            }
            this.f1599c = iMin;
        }

        public void d() {
            this.f1598b = -1;
            this.f1599c = Integer.MIN_VALUE;
            this.f1600d = false;
            this.f1601e = false;
        }

        public String toString() {
            StringBuilder sbD = b.a.a.a.a.d("AnchorInfo{mPosition=");
            sbD.append(this.f1598b);
            sbD.append(", mCoordinate=");
            sbD.append(this.f1599c);
            sbD.append(", mLayoutFromEnd=");
            sbD.append(this.f1600d);
            sbD.append(", mValid=");
            sbD.append(this.f1601e);
            sbD.append('}');
            return sbD.toString();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1602a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1603b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1604c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1605d;
    }

    public static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1607b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1608c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1609d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1610e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1611f;
        public int g;
        public boolean j;
        public int k;
        public boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1606a = true;
        public int h = 0;
        public int i = 0;
        public List<RecyclerView.b0> l = null;

        public void a(View view) {
            int iA;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).f1629a;
                RecyclerView.n nVar = (RecyclerView.n) view3.getLayoutParams();
                if (view3 != view && !nVar.c() && (iA = (nVar.a() - this.f1609d) * this.f1610e) >= 0 && iA < i) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    } else {
                        i = iA;
                    }
                }
            }
            this.f1609d = view2 == null ? -1 : ((RecyclerView.n) view2.getLayoutParams()).a();
        }

        public boolean b(RecyclerView.y yVar) {
            int i = this.f1609d;
            return i >= 0 && i < yVar.b();
        }

        public View c(RecyclerView.t tVar) {
            List<RecyclerView.b0> list = this.l;
            if (list == null) {
                View view = tVar.j(this.f1609d, false, Long.MAX_VALUE).f1629a;
                this.f1609d += this.f1610e;
                return view;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view2 = this.l.get(i).f1629a;
                RecyclerView.n nVar = (RecyclerView.n) view2.getLayoutParams();
                if (!nVar.c() && this.f1609d == nVar.a()) {
                    a(view2);
                    return view2;
                }
            }
            return null;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class d implements Parcelable {
        public static final Parcelable.Creator<d> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1612a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1613b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1614c;

        public static class a implements Parcelable.Creator<d> {
            @Override // android.os.Parcelable.Creator
            public d createFromParcel(Parcel parcel) {
                return new d(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public d[] newArray(int i) {
                return new d[i];
            }
        }

        public d() {
        }

        public d(Parcel parcel) {
            this.f1612a = parcel.readInt();
            this.f1613b = parcel.readInt();
            this.f1614c = parcel.readInt() == 1;
        }

        public d(d dVar) {
            this.f1612a = dVar.f1612a;
            this.f1613b = dVar.f1613b;
            this.f1614c = dVar.f1614c;
        }

        public boolean b() {
            return this.f1612a >= 0;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1612a);
            parcel.writeInt(this.f1613b);
            parcel.writeInt(this.f1614c ? 1 : 0);
        }
    }

    public LinearLayoutManager(int i, boolean z) {
        this.s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.C = null;
        this.D = new a();
        this.E = new b();
        this.F = 2;
        this.G = new int[2];
        C1(i);
        d(null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        K0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public Parcelable A0() {
        d dVar = this.C;
        if (dVar != null) {
            return new d(dVar);
        }
        d dVar2 = new d();
        if (z() > 0) {
            f1();
            boolean z = this.v ^ this.x;
            dVar2.f1614c = z;
            if (z) {
                View viewS1 = s1();
                dVar2.f1613b = this.u.g() - this.u.b(viewS1);
                dVar2.f1612a = R(viewS1);
            } else {
                View viewT1 = t1();
                dVar2.f1612a = R(viewT1);
                dVar2.f1613b = this.u.e(viewT1) - this.u.k();
            }
        } else {
            dVar2.f1612a = -1;
        }
        return dVar2;
    }

    public final void A1() {
        this.x = (this.s == 1 || !u1()) ? this.w : !this.w;
    }

    public int B1(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        if (z() == 0 || i == 0) {
            return 0;
        }
        f1();
        this.t.f1606a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        E1(i2, iAbs, true, yVar);
        c cVar = this.t;
        int iG1 = g1(tVar, cVar, yVar, false) + cVar.g;
        if (iG1 < 0) {
            return 0;
        }
        if (iAbs > iG1) {
            i = i2 * iG1;
        }
        this.u.p(-i);
        this.t.k = i;
        return i;
    }

    public void C1(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        d(null);
        if (i != this.s || this.u == null) {
            w wVarA = w.a(this, i);
            this.u = wVarA;
            this.D.f1597a = wVarA;
            this.s = i;
            K0();
        }
    }

    public void D1(boolean z) {
        d(null);
        if (this.y == z) {
            return;
        }
        this.y = z;
        K0();
    }

    public final void E1(int i, int i2, boolean z, RecyclerView.y yVar) {
        int iK;
        this.t.m = z1();
        this.t.f1611f = i;
        int[] iArr = this.G;
        iArr[0] = 0;
        iArr[1] = 0;
        Z0(yVar, iArr);
        int iMax = Math.max(0, this.G[0]);
        int iMax2 = Math.max(0, this.G[1]);
        boolean z2 = i == 1;
        this.t.h = z2 ? iMax2 : iMax;
        c cVar = this.t;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.i = iMax;
        if (z2) {
            c cVar2 = this.t;
            cVar2.h = this.u.h() + cVar2.h;
            View viewS1 = s1();
            this.t.f1610e = this.x ? -1 : 1;
            c cVar3 = this.t;
            int iR = R(viewS1);
            c cVar4 = this.t;
            cVar3.f1609d = iR + cVar4.f1610e;
            cVar4.f1607b = this.u.b(viewS1);
            iK = this.u.b(viewS1) - this.u.g();
        } else {
            View viewT1 = t1();
            c cVar5 = this.t;
            cVar5.h = this.u.k() + cVar5.h;
            this.t.f1610e = this.x ? 1 : -1;
            c cVar6 = this.t;
            int iR2 = R(viewT1);
            c cVar7 = this.t;
            cVar6.f1609d = iR2 + cVar7.f1610e;
            cVar7.f1607b = this.u.e(viewT1);
            iK = (-this.u.e(viewT1)) + this.u.k();
        }
        c cVar8 = this.t;
        cVar8.f1608c = i2;
        if (z) {
            cVar8.f1608c = i2 - iK;
        }
        this.t.g = iK;
    }

    public final void F1(int i, int i2) {
        this.t.f1608c = this.u.g() - i2;
        this.t.f1610e = this.x ? -1 : 1;
        c cVar = this.t;
        cVar.f1609d = i;
        cVar.f1611f = 1;
        cVar.f1607b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    public final void G1(int i, int i2) {
        this.t.f1608c = i2 - this.u.k();
        c cVar = this.t;
        cVar.f1609d = i;
        cVar.f1610e = this.x ? 1 : -1;
        c cVar2 = this.t;
        cVar2.f1611f = -1;
        cVar2.f1607b = i2;
        cVar2.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int L0(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        if (this.s == 1) {
            return 0;
        }
        return B1(i, tVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void M0(int i) {
        this.A = i;
        this.B = Integer.MIN_VALUE;
        d dVar = this.C;
        if (dVar != null) {
            dVar.f1612a = -1;
        }
        K0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int N0(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        if (this.s == 0) {
            return 0;
        }
        return B1(i, tVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean U0() {
        boolean z;
        if (this.p == 1073741824 || this.o == 1073741824) {
            return false;
        }
        int iZ = z();
        int i = 0;
        while (true) {
            if (i >= iZ) {
                z = false;
                break;
            }
            ViewGroup.LayoutParams layoutParams = y(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                z = true;
                break;
            }
            i++;
        }
        return z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean W() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView recyclerView, RecyclerView.y yVar, int i) {
        s sVar = new s(recyclerView.getContext());
        sVar.f1677a = i;
        X0(sVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean Y0() {
        return this.C == null && this.v == this.y;
    }

    public void Z0(RecyclerView.y yVar, int[] iArr) {
        int i;
        int iL = yVar.f1689a != -1 ? this.u.l() : 0;
        if (this.t.f1611f == -1) {
            i = 0;
        } else {
            i = iL;
            iL = 0;
        }
        iArr[0] = iL;
        iArr[1] = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.x.b
    public PointF a(int i) {
        if (z() == 0) {
            return null;
        }
        int i2 = (i < R(y(0))) != this.x ? -1 : 1;
        return this.s == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    public void a1(RecyclerView.y yVar, c cVar, RecyclerView.m.c cVar2) {
        int i = cVar.f1609d;
        if (i < 0 || i >= yVar.b()) {
            return;
        }
        ((n.b) cVar2).a(i, Math.max(0, cVar.g));
    }

    public final int b1(RecyclerView.y yVar) {
        if (z() == 0) {
            return 0;
        }
        f1();
        return l.i.l(yVar, this.u, j1(!this.z, true), i1(!this.z, true), this, this.z);
    }

    public final int c1(RecyclerView.y yVar) {
        if (z() == 0) {
            return 0;
        }
        f1();
        return l.i.m(yVar, this.u, j1(!this.z, true), i1(!this.z, true), this, this.z, this.x);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void d(String str) {
        RecyclerView recyclerView;
        if (this.C != null || (recyclerView = this.f1648b) == null) {
            return;
        }
        recyclerView.i(str);
    }

    public final int d1(RecyclerView.y yVar) {
        if (z() == 0) {
            return 0;
        }
        f1();
        return l.i.n(yVar, this.u, j1(!this.z, true), i1(!this.z, true), this, this.z);
    }

    public int e1(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && u1()) ? -1 : 1 : (this.s != 1 && u1()) ? 1 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean f() {
        return this.s == 0;
    }

    public void f1() {
        if (this.t == null) {
            this.t = new c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean g() {
        return this.s == 1;
    }

    public int g1(RecyclerView.t tVar, c cVar, RecyclerView.y yVar, boolean z) {
        int i = cVar.f1608c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            x1(tVar, cVar);
        }
        int i3 = cVar.f1608c + cVar.h;
        b bVar = this.E;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.b(yVar)) {
                break;
            }
            bVar.f1602a = 0;
            bVar.f1603b = false;
            bVar.f1604c = false;
            bVar.f1605d = false;
            v1(tVar, yVar, cVar, bVar);
            if (!bVar.f1603b) {
                cVar.f1607b = (bVar.f1602a * cVar.f1611f) + cVar.f1607b;
                if (!bVar.f1604c || cVar.l != null || !yVar.g) {
                    int i4 = cVar.f1608c;
                    int i5 = bVar.f1602a;
                    cVar.f1608c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.f1602a;
                    cVar.g = i7;
                    int i8 = cVar.f1608c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    x1(tVar, cVar);
                }
                if (z && bVar.f1605d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.f1608c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void h0(RecyclerView recyclerView, RecyclerView.t tVar) {
        g0();
    }

    public final View h1(RecyclerView.t tVar, RecyclerView.y yVar) {
        return p1(tVar, yVar, 0, z(), yVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public View i0(View view, int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        int iE1;
        A1();
        if (z() == 0 || (iE1 = e1(i)) == Integer.MIN_VALUE) {
            return null;
        }
        f1();
        E1(iE1, (int) (this.u.l() * 0.33333334f), false, yVar);
        c cVar = this.t;
        cVar.g = Integer.MIN_VALUE;
        cVar.f1606a = false;
        g1(tVar, cVar, yVar, true);
        View viewN1 = iE1 == -1 ? this.x ? n1(z() - 1, -1) : n1(0, z()) : this.x ? n1(0, z()) : n1(z() - 1, -1);
        View viewT1 = iE1 == -1 ? t1() : s1();
        if (!viewT1.hasFocusable()) {
            return viewN1;
        }
        if (viewN1 == null) {
            return null;
        }
        return viewT1;
    }

    public View i1(boolean z, boolean z2) {
        int iZ;
        int iZ2;
        if (this.x) {
            iZ = 0;
            iZ2 = z();
        } else {
            iZ = z() - 1;
            iZ2 = -1;
        }
        return o1(iZ, iZ2, z, z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void j(int i, int i2, RecyclerView.y yVar, RecyclerView.m.c cVar) {
        if (this.s != 0) {
            i = i2;
        }
        if (z() == 0 || i == 0) {
            return;
        }
        f1();
        E1(i > 0 ? 1 : -1, Math.abs(i), true, yVar);
        a1(yVar, this.t, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void j0(AccessibilityEvent accessibilityEvent) {
        RecyclerView.t tVar = this.f1648b.f1616b;
        k0(accessibilityEvent);
        if (z() > 0) {
            accessibilityEvent.setFromIndex(k1());
            accessibilityEvent.setToIndex(m1());
        }
    }

    public View j1(boolean z, boolean z2) {
        int iZ;
        int iZ2;
        if (this.x) {
            iZ = z() - 1;
            iZ2 = -1;
        } else {
            iZ = 0;
            iZ2 = z();
        }
        return o1(iZ, iZ2, z, z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void k(int i, RecyclerView.m.c cVar) {
        boolean z;
        int i2;
        d dVar = this.C;
        if (dVar == null || !dVar.b()) {
            A1();
            z = this.x;
            i2 = this.A;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            d dVar2 = this.C;
            z = dVar2.f1614c;
            i2 = dVar2.f1612a;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.F && i2 >= 0 && i2 < i; i4++) {
            ((n.b) cVar).a(i2, 0);
            i2 += i3;
        }
    }

    public int k1() {
        View viewO1 = o1(0, z(), false, true);
        if (viewO1 == null) {
            return -1;
        }
        return R(viewO1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int l(RecyclerView.y yVar) {
        return b1(yVar);
    }

    public final View l1(RecyclerView.t tVar, RecyclerView.y yVar) {
        return p1(tVar, yVar, z() - 1, -1, yVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int m(RecyclerView.y yVar) {
        return c1(yVar);
    }

    public int m1() {
        View viewO1 = o1(z() - 1, -1, false, true);
        if (viewO1 == null) {
            return -1;
        }
        return R(viewO1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int n(RecyclerView.y yVar) {
        return d1(yVar);
    }

    public View n1(int i, int i2) {
        int i3;
        int i4;
        f1();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return y(i);
        }
        if (this.u.e(y(i)) < this.u.k()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return (this.s == 0 ? this.f1651e : this.f1652f).a(i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int o(RecyclerView.y yVar) {
        return b1(yVar);
    }

    public View o1(int i, int i2, boolean z, boolean z2) {
        f1();
        return (this.s == 0 ? this.f1651e : this.f1652f).a(i, i2, z ? 24579 : 320, z2 ? 320 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int p(RecyclerView.y yVar) {
        return c1(yVar);
    }

    public View p1(RecyclerView.t tVar, RecyclerView.y yVar, int i, int i2, int i3) {
        f1();
        int iK = this.u.k();
        int iG = this.u.g();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewY = y(i);
            int iR = R(viewY);
            if (iR >= 0 && iR < i3) {
                if (((RecyclerView.n) viewY.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewY;
                    }
                } else {
                    if (this.u.e(viewY) < iG && this.u.b(viewY) >= iK) {
                        return viewY;
                    }
                    if (view == null) {
                        view = viewY;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int q(RecyclerView.y yVar) {
        return d1(yVar);
    }

    public final int q1(int i, RecyclerView.t tVar, RecyclerView.y yVar, boolean z) {
        int iG;
        int iG2 = this.u.g() - i;
        if (iG2 <= 0) {
            return 0;
        }
        int i2 = -B1(-iG2, tVar, yVar);
        int i3 = i + i2;
        if (!z || (iG = this.u.g() - i3) <= 0) {
            return i2;
        }
        this.u.p(iG);
        return iG + i2;
    }

    public final int r1(int i, RecyclerView.t tVar, RecyclerView.y yVar, boolean z) {
        int iK;
        int iK2 = i - this.u.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i2 = -B1(iK2, tVar, yVar);
        int i3 = i + i2;
        if (!z || (iK = i3 - this.u.k()) <= 0) {
            return i2;
        }
        this.u.p(-iK);
        return i2 - iK;
    }

    public final View s1() {
        return y(this.x ? 0 : z() - 1);
    }

    public final View t1() {
        return y(this.x ? z() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public View u(int i) {
        int iZ = z();
        if (iZ == 0) {
            return null;
        }
        int iR = i - R(y(0));
        if (iR >= 0 && iR < iZ) {
            View viewY = y(iR);
            if (R(viewY) == i) {
                return viewY;
            }
        }
        return super.u(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0177  */
    @Override // androidx.recyclerview.widget.RecyclerView.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void u0(androidx.recyclerview.widget.RecyclerView.t r17, androidx.recyclerview.widget.RecyclerView.y r18) {
        /*
            Method dump skipped, instruction units count: 1073
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.u0(androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$y):void");
    }

    public boolean u1() {
        return K() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n v() {
        return new RecyclerView.n(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void v0(RecyclerView.y yVar) {
        this.C = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D.d();
    }

    public void v1(RecyclerView.t tVar, RecyclerView.y yVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int iO;
        int iD;
        View viewC = cVar.c(tVar);
        if (viewC == null) {
            bVar.f1603b = true;
            return;
        }
        RecyclerView.n nVar = (RecyclerView.n) viewC.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f1611f == -1)) {
                c(viewC, -1, false);
            } else {
                c(viewC, 0, false);
            }
        } else {
            if (this.x == (cVar.f1611f == -1)) {
                c(viewC, -1, true);
            } else {
                c(viewC, 0, true);
            }
        }
        RecyclerView.n nVar2 = (RecyclerView.n) viewC.getLayoutParams();
        Rect rectM = this.f1648b.M(viewC);
        int i4 = rectM.left + rectM.right + 0;
        int i5 = rectM.top + rectM.bottom + 0;
        int iA = RecyclerView.m.A(this.q, this.o, P() + O() + ((ViewGroup.MarginLayoutParams) nVar2).leftMargin + ((ViewGroup.MarginLayoutParams) nVar2).rightMargin + i4, ((ViewGroup.MarginLayoutParams) nVar2).width, f());
        int iA2 = RecyclerView.m.A(this.r, this.p, N() + Q() + ((ViewGroup.MarginLayoutParams) nVar2).topMargin + ((ViewGroup.MarginLayoutParams) nVar2).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) nVar2).height, g());
        if (T0(viewC, iA, iA2, nVar2)) {
            viewC.measure(iA, iA2);
        }
        bVar.f1602a = this.u.c(viewC);
        if (this.s == 1) {
            if (u1()) {
                iD = this.q - P();
                iO = iD - this.u.d(viewC);
            } else {
                iO = O();
                iD = this.u.d(viewC) + iO;
            }
            int i6 = cVar.f1611f;
            int i7 = cVar.f1607b;
            if (i6 == -1) {
                i3 = i7;
                i2 = iD;
                i = i7 - bVar.f1602a;
            } else {
                i = i7;
                i2 = iD;
                i3 = bVar.f1602a + i7;
            }
        } else {
            int iQ = Q();
            int iD2 = this.u.d(viewC) + iQ;
            int i8 = cVar.f1611f;
            int i9 = cVar.f1607b;
            if (i8 == -1) {
                i2 = i9;
                i = iQ;
                i3 = iD2;
                iO = i9 - bVar.f1602a;
            } else {
                i = iQ;
                i2 = bVar.f1602a + i9;
                i3 = iD2;
                iO = i9;
            }
        }
        a0(viewC, iO, i, i2, i3);
        if (nVar.c() || nVar.b()) {
            bVar.f1604c = true;
        }
        bVar.f1605d = viewC.hasFocusable();
    }

    public void w1(RecyclerView.t tVar, RecyclerView.y yVar, a aVar, int i) {
    }

    public final void x1(RecyclerView.t tVar, c cVar) {
        if (!cVar.f1606a || cVar.m) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f1611f == -1) {
            int iZ = z();
            if (i < 0) {
                return;
            }
            int iF = (this.u.f() - i) + i2;
            if (this.x) {
                for (int i3 = 0; i3 < iZ; i3++) {
                    View viewY = y(i3);
                    if (this.u.e(viewY) < iF || this.u.o(viewY) < iF) {
                        y1(tVar, 0, i3);
                        return;
                    }
                }
                return;
            }
            int i4 = iZ - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View viewY2 = y(i5);
                if (this.u.e(viewY2) < iF || this.u.o(viewY2) < iF) {
                    y1(tVar, i4, i5);
                    return;
                }
            }
            return;
        }
        if (i < 0) {
            return;
        }
        int i6 = i - i2;
        int iZ2 = z();
        if (!this.x) {
            for (int i7 = 0; i7 < iZ2; i7++) {
                View viewY3 = y(i7);
                if (this.u.b(viewY3) > i6 || this.u.n(viewY3) > i6) {
                    y1(tVar, 0, i7);
                    return;
                }
            }
            return;
        }
        int i8 = iZ2 - 1;
        for (int i9 = i8; i9 >= 0; i9--) {
            View viewY4 = y(i9);
            if (this.u.b(viewY4) > i6 || this.u.n(viewY4) > i6) {
                y1(tVar, i8, i9);
                return;
            }
        }
    }

    public final void y1(RecyclerView.t tVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                H0(i, tVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                H0(i3, tVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void z0(Parcelable parcelable) {
        if (parcelable instanceof d) {
            this.C = (d) parcelable;
            K0();
        }
    }

    public boolean z1() {
        return this.u.i() == 0 && this.u.f() == 0;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.C = null;
        this.D = new a();
        this.E = new b();
        this.F = 2;
        this.G = new int[2];
        RecyclerView.m.d dVarS = RecyclerView.m.S(context, attributeSet, i, i2);
        C1(dVarS.f1655a);
        boolean z = dVarS.f1657c;
        d(null);
        if (z != this.w) {
            this.w = z;
            K0();
        }
        D1(dVarS.f1658d);
    }
}
