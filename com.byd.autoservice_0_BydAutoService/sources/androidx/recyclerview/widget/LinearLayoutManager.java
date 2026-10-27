package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import w0.g;
import w0.j;
import w0.m;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o implements RecyclerView.y.b {
    public int A;
    public int B;
    public boolean C;
    public SavedState D;
    public final a E;
    public final b F;
    public int G;
    public int[] H;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1379s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public c f1380t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public j f1381u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1382v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1383w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1384x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1385y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1386z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1388c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1389d;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean f() {
            return this.f1387b >= 0;
        }

        public void g() {
            this.f1387b = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            parcel.writeInt(this.f1387b);
            parcel.writeInt(this.f1388c);
            parcel.writeInt(this.f1389d ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.f1387b = parcel.readInt();
            this.f1388c = parcel.readInt();
            this.f1389d = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1387b = savedState.f1387b;
            this.f1388c = savedState.f1388c;
            this.f1389d = savedState.f1389d;
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public j f1390a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1391b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1392c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1393d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1394e;

        public a() {
            e();
        }

        public void a() {
            this.f1392c = this.f1393d ? this.f1390a.i() : this.f1390a.m();
        }

        public void b(View view, int i3) {
            if (this.f1393d) {
                this.f1392c = this.f1390a.d(view) + this.f1390a.o();
            } else {
                this.f1392c = this.f1390a.g(view);
            }
            this.f1391b = i3;
        }

        public void c(View view, int i3) {
            int iO = this.f1390a.o();
            if (iO >= 0) {
                b(view, i3);
                return;
            }
            this.f1391b = i3;
            if (this.f1393d) {
                int i4 = (this.f1390a.i() - iO) - this.f1390a.d(view);
                this.f1392c = this.f1390a.i() - i4;
                if (i4 > 0) {
                    int iE = this.f1392c - this.f1390a.e(view);
                    int iM = this.f1390a.m();
                    int iMin = iE - (iM + Math.min(this.f1390a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1392c += Math.min(i4, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1390a.g(view);
            int iM2 = iG - this.f1390a.m();
            this.f1392c = iG;
            if (iM2 > 0) {
                int i5 = (this.f1390a.i() - Math.min(0, (this.f1390a.i() - iO) - this.f1390a.d(view))) - (iG + this.f1390a.e(view));
                if (i5 < 0) {
                    this.f1392c -= Math.min(iM2, -i5);
                }
            }
        }

        public boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < zVar.b();
        }

        public void e() {
            this.f1391b = -1;
            this.f1392c = Integer.MIN_VALUE;
            this.f1393d = false;
            this.f1394e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1391b + ", mCoordinate=" + this.f1392c + ", mLayoutFromEnd=" + this.f1393d + ", mValid=" + this.f1394e + '}';
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1395a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1396b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1397c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1398d;

        public void a() {
            this.f1395a = 0;
            this.f1396b = false;
            this.f1397c = false;
            this.f1398d = false;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1400b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1401c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1402d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1403e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1404f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1405g;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1409k;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f1411m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1399a = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1406h = 0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1407i = 0;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1408j = false;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public List<RecyclerView.c0> f1410l = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View viewF = f(view);
            if (viewF == null) {
                this.f1402d = -1;
            } else {
                this.f1402d = ((RecyclerView.p) viewF.getLayoutParams()).a();
            }
        }

        public boolean c(RecyclerView.z zVar) {
            int i3 = this.f1402d;
            return i3 >= 0 && i3 < zVar.b();
        }

        public View d(RecyclerView.v vVar) {
            if (this.f1410l != null) {
                return e();
            }
            View viewO = vVar.o(this.f1402d);
            this.f1402d += this.f1403e;
            return viewO;
        }

        public final View e() {
            int size = this.f1410l.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = this.f1410l.get(i3).f1474b;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f1402d == pVar.a()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public View f(View view) {
            int iA;
            int size = this.f1410l.size();
            View view2 = null;
            int i3 = Integer.MAX_VALUE;
            for (int i4 = 0; i4 < size; i4++) {
                View view3 = this.f1410l.get(i4).f1474b;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (iA = (pVar.a() - this.f1402d) * this.f1403e) >= 0 && iA < i3) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i3 = iA;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public final void A2() {
        if (this.f1379s == 1 || !q2()) {
            this.f1384x = this.f1383w;
        } else {
            this.f1384x = !this.f1383w;
        }
    }

    public int B2(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i3 == 0) {
            return 0;
        }
        T1();
        this.f1380t.f1399a = true;
        int i4 = i3 > 0 ? 1 : -1;
        int iAbs = Math.abs(i3);
        I2(i4, iAbs, true, zVar);
        c cVar = this.f1380t;
        int iU1 = cVar.f1405g + U1(vVar, cVar, zVar, false);
        if (iU1 < 0) {
            return 0;
        }
        if (iAbs > iU1) {
            i3 = i4 * iU1;
        }
        this.f1381u.r(-i3);
        this.f1380t.f1409k = i3;
        return i3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View C(int i3) {
        int iJ = J();
        if (iJ == 0) {
            return null;
        }
        int iH0 = i3 - h0(I(0));
        if (iH0 >= 0 && iH0 < iJ) {
            View viewI = I(iH0);
            if (h0(viewI) == i3) {
                return viewI;
            }
        }
        return super.C(i3);
    }

    public void C2(int i3) {
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i3);
        }
        g(null);
        if (i3 != this.f1379s || this.f1381u == null) {
            j jVarB = j.b(this, i3);
            this.f1381u = jVarB;
            this.E.f1390a = jVarB;
            this.f1379s = i3;
            t1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return new RecyclerView.p(-2, -2);
    }

    public void D2(boolean z3) {
        g(null);
        if (z3 == this.f1383w) {
            return;
        }
        this.f1383w = z3;
        t1();
    }

    public void E2(boolean z3) {
        g(null);
        if (this.f1385y == z3) {
            return;
        }
        this.f1385y = z3;
        t1();
    }

    public final boolean F2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (J() == 0) {
            return false;
        }
        View viewV = V();
        if (viewV != null && aVar.d(viewV, zVar)) {
            aVar.c(viewV, h0(viewV));
            return true;
        }
        if (this.f1382v != this.f1385y) {
            return false;
        }
        View viewI2 = aVar.f1393d ? i2(vVar, zVar) : j2(vVar, zVar);
        if (viewI2 == null) {
            return false;
        }
        aVar.b(viewI2, h0(viewI2));
        if (!zVar.e() && L1()) {
            if (this.f1381u.g(viewI2) >= this.f1381u.i() || this.f1381u.d(viewI2) < this.f1381u.m()) {
                aVar.f1392c = aVar.f1393d ? this.f1381u.i() : this.f1381u.m();
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean G1() {
        return (X() == 1073741824 || p0() == 1073741824 || !q0()) ? false : true;
    }

    public final boolean G2(RecyclerView.z zVar, a aVar) {
        int i3;
        if (!zVar.e() && (i3 = this.A) != -1) {
            if (i3 >= 0 && i3 < zVar.b()) {
                aVar.f1391b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.f()) {
                    boolean z3 = this.D.f1389d;
                    aVar.f1393d = z3;
                    if (z3) {
                        aVar.f1392c = this.f1381u.i() - this.D.f1388c;
                    } else {
                        aVar.f1392c = this.f1381u.m() + this.D.f1388c;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z4 = this.f1384x;
                    aVar.f1393d = z4;
                    if (z4) {
                        aVar.f1392c = this.f1381u.i() - this.B;
                    } else {
                        aVar.f1392c = this.f1381u.m() + this.B;
                    }
                    return true;
                }
                View viewC = C(this.A);
                if (viewC == null) {
                    if (J() > 0) {
                        aVar.f1393d = (this.A < h0(I(0))) == this.f1384x;
                    }
                    aVar.a();
                } else {
                    if (this.f1381u.e(viewC) > this.f1381u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.f1381u.g(viewC) - this.f1381u.m() < 0) {
                        aVar.f1392c = this.f1381u.m();
                        aVar.f1393d = false;
                        return true;
                    }
                    if (this.f1381u.i() - this.f1381u.d(viewC) < 0) {
                        aVar.f1392c = this.f1381u.i();
                        aVar.f1393d = true;
                        return true;
                    }
                    aVar.f1392c = aVar.f1393d ? this.f1381u.d(viewC) + this.f1381u.o() : this.f1381u.g(viewC);
                }
                return true;
            }
            this.A = -1;
            this.B = Integer.MIN_VALUE;
        }
        return false;
    }

    public final void H2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (G2(zVar, aVar) || F2(vVar, zVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1391b = this.f1385y ? zVar.b() - 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.I0(recyclerView, vVar);
        if (this.C) {
            k1(vVar);
            vVar.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i3) {
        g gVar = new g(recyclerView.getContext());
        gVar.p(i3);
        J1(gVar);
    }

    public final void I2(int i3, int i4, boolean z3, RecyclerView.z zVar) {
        int iM;
        this.f1380t.f1411m = z2();
        this.f1380t.f1404f = i3;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]);
        int iMax2 = Math.max(0, this.H[1]);
        boolean z4 = i3 == 1;
        c cVar = this.f1380t;
        int i5 = z4 ? iMax2 : iMax;
        cVar.f1406h = i5;
        if (!z4) {
            iMax = iMax2;
        }
        cVar.f1407i = iMax;
        if (z4) {
            cVar.f1406h = i5 + this.f1381u.j();
            View viewM2 = m2();
            c cVar2 = this.f1380t;
            cVar2.f1403e = this.f1384x ? -1 : 1;
            int iH0 = h0(viewM2);
            c cVar3 = this.f1380t;
            cVar2.f1402d = iH0 + cVar3.f1403e;
            cVar3.f1400b = this.f1381u.d(viewM2);
            iM = this.f1381u.d(viewM2) - this.f1381u.i();
        } else {
            View viewN2 = n2();
            this.f1380t.f1406h += this.f1381u.m();
            c cVar4 = this.f1380t;
            cVar4.f1403e = this.f1384x ? 1 : -1;
            int iH02 = h0(viewN2);
            c cVar5 = this.f1380t;
            cVar4.f1402d = iH02 + cVar5.f1403e;
            cVar5.f1400b = this.f1381u.g(viewN2);
            iM = (-this.f1381u.g(viewN2)) + this.f1381u.m();
        }
        c cVar6 = this.f1380t;
        cVar6.f1401c = i4;
        if (z3) {
            cVar6.f1401c = i4 - iM;
        }
        cVar6.f1405g = iM;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iR1;
        A2();
        if (J() == 0 || (iR1 = R1(i3)) == Integer.MIN_VALUE) {
            return null;
        }
        T1();
        I2(iR1, (int) (this.f1381u.n() * 0.33333334f), false, zVar);
        c cVar = this.f1380t;
        cVar.f1405g = Integer.MIN_VALUE;
        cVar.f1399a = false;
        U1(vVar, cVar, zVar, true);
        View viewG2 = iR1 == -1 ? g2() : f2();
        View viewN2 = iR1 == -1 ? n2() : m2();
        if (!viewN2.hasFocusable()) {
            return viewG2;
        }
        if (viewG2 == null) {
            return null;
        }
        return viewN2;
    }

    public final void J2(int i3, int i4) {
        this.f1380t.f1401c = this.f1381u.i() - i4;
        c cVar = this.f1380t;
        cVar.f1403e = this.f1384x ? -1 : 1;
        cVar.f1402d = i3;
        cVar.f1404f = 1;
        cVar.f1400b = i4;
        cVar.f1405g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void K0(AccessibilityEvent accessibilityEvent) {
        super.K0(accessibilityEvent);
        if (J() > 0) {
            accessibilityEvent.setFromIndex(Z1());
            accessibilityEvent.setToIndex(c2());
        }
    }

    public final void K2(a aVar) {
        J2(aVar.f1391b, aVar.f1392c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean L1() {
        return this.D == null && this.f1382v == this.f1385y;
    }

    public final void L2(int i3, int i4) {
        this.f1380t.f1401c = i4 - this.f1381u.m();
        c cVar = this.f1380t;
        cVar.f1402d = i3;
        cVar.f1403e = this.f1384x ? 1 : -1;
        cVar.f1404f = -1;
        cVar.f1400b = i4;
        cVar.f1405g = Integer.MIN_VALUE;
    }

    public void M1(RecyclerView.z zVar, int[] iArr) {
        int i3;
        int iO2 = o2(zVar);
        if (this.f1380t.f1404f == -1) {
            i3 = 0;
        } else {
            i3 = iO2;
            iO2 = 0;
        }
        iArr[0] = iO2;
        iArr[1] = i3;
    }

    public final void M2(a aVar) {
        L2(aVar.f1391b, aVar.f1392c);
    }

    public void N1(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i3 = cVar.f1402d;
        if (i3 < 0 || i3 >= zVar.b()) {
            return;
        }
        cVar2.a(i3, Math.max(0, cVar.f1405g));
    }

    public final int O1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.a(zVar, this.f1381u, Y1(!this.f1386z, true), X1(!this.f1386z, true), this, this.f1386z);
    }

    public final int P1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.b(zVar, this.f1381u, Y1(!this.f1386z, true), X1(!this.f1386z, true), this, this.f1386z, this.f1384x);
    }

    public final int Q1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.c(zVar, this.f1381u, Y1(!this.f1386z, true), X1(!this.f1386z, true), this, this.f1386z);
    }

    public int R1(int i3) {
        return i3 != 1 ? i3 != 2 ? i3 != 17 ? i3 != 33 ? i3 != 66 ? (i3 == 130 && this.f1379s == 1) ? 1 : Integer.MIN_VALUE : this.f1379s == 0 ? 1 : Integer.MIN_VALUE : this.f1379s == 1 ? -1 : Integer.MIN_VALUE : this.f1379s == 0 ? -1 : Integer.MIN_VALUE : (this.f1379s != 1 && q2()) ? -1 : 1 : (this.f1379s != 1 && q2()) ? 1 : -1;
    }

    public c S1() {
        return new c();
    }

    public void T1() {
        if (this.f1380t == null) {
            this.f1380t = S1();
        }
    }

    public int U1(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z3) {
        int i3 = cVar.f1401c;
        int i4 = cVar.f1405g;
        if (i4 != Integer.MIN_VALUE) {
            if (i3 < 0) {
                cVar.f1405g = i4 + i3;
            }
            v2(vVar, cVar);
        }
        int i5 = cVar.f1401c + cVar.f1406h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.f1411m && i5 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            s2(vVar, zVar, cVar, bVar);
            if (!bVar.f1396b) {
                cVar.f1400b += bVar.f1395a * cVar.f1404f;
                if (!bVar.f1397c || cVar.f1410l != null || !zVar.e()) {
                    int i6 = cVar.f1401c;
                    int i7 = bVar.f1395a;
                    cVar.f1401c = i6 - i7;
                    i5 -= i7;
                }
                int i8 = cVar.f1405g;
                if (i8 != Integer.MIN_VALUE) {
                    int i9 = i8 + bVar.f1395a;
                    cVar.f1405g = i9;
                    int i10 = cVar.f1401c;
                    if (i10 < 0) {
                        cVar.f1405g = i9 + i10;
                    }
                    v2(vVar, cVar);
                }
                if (z3 && bVar.f1398d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i3 - cVar.f1401c;
    }

    public final View V1() {
        return d2(0, J());
    }

    public final View W1(RecyclerView.v vVar, RecyclerView.z zVar) {
        return h2(vVar, zVar, 0, J(), zVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i3;
        int i4;
        int i5;
        int i6;
        int iK2;
        int i7;
        View viewC;
        int iG;
        int i8;
        int i9 = -1;
        if (!(this.D == null && this.A == -1) && zVar.b() == 0) {
            k1(vVar);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.f()) {
            this.A = this.D.f1387b;
        }
        T1();
        this.f1380t.f1399a = false;
        A2();
        View viewV = V();
        a aVar = this.E;
        if (!aVar.f1394e || this.A != -1 || this.D != null) {
            aVar.e();
            a aVar2 = this.E;
            aVar2.f1393d = this.f1384x ^ this.f1385y;
            H2(vVar, zVar, aVar2);
            this.E.f1394e = true;
        } else if (viewV != null && (this.f1381u.g(viewV) >= this.f1381u.i() || this.f1381u.d(viewV) <= this.f1381u.m())) {
            this.E.c(viewV, h0(viewV));
        }
        c cVar = this.f1380t;
        cVar.f1404f = cVar.f1409k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]) + this.f1381u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.f1381u.j();
        if (zVar.e() && (i7 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewC = C(i7)) != null) {
            if (this.f1384x) {
                i8 = this.f1381u.i() - this.f1381u.d(viewC);
                iG = this.B;
            } else {
                iG = this.f1381u.g(viewC) - this.f1381u.m();
                i8 = this.B;
            }
            int i10 = i8 - iG;
            if (i10 > 0) {
                iMax += i10;
            } else {
                iMax2 -= i10;
            }
        }
        a aVar3 = this.E;
        if (!aVar3.f1393d ? !this.f1384x : this.f1384x) {
            i9 = 1;
        }
        u2(vVar, zVar, aVar3, i9);
        w(vVar);
        this.f1380t.f1411m = z2();
        this.f1380t.f1408j = zVar.e();
        this.f1380t.f1407i = 0;
        a aVar4 = this.E;
        if (aVar4.f1393d) {
            M2(aVar4);
            c cVar2 = this.f1380t;
            cVar2.f1406h = iMax;
            U1(vVar, cVar2, zVar, false);
            c cVar3 = this.f1380t;
            i4 = cVar3.f1400b;
            int i11 = cVar3.f1402d;
            int i12 = cVar3.f1401c;
            if (i12 > 0) {
                iMax2 += i12;
            }
            K2(this.E);
            c cVar4 = this.f1380t;
            cVar4.f1406h = iMax2;
            cVar4.f1402d += cVar4.f1403e;
            U1(vVar, cVar4, zVar, false);
            c cVar5 = this.f1380t;
            i3 = cVar5.f1400b;
            int i13 = cVar5.f1401c;
            if (i13 > 0) {
                L2(i11, i4);
                c cVar6 = this.f1380t;
                cVar6.f1406h = i13;
                U1(vVar, cVar6, zVar, false);
                i4 = this.f1380t.f1400b;
            }
        } else {
            K2(aVar4);
            c cVar7 = this.f1380t;
            cVar7.f1406h = iMax2;
            U1(vVar, cVar7, zVar, false);
            c cVar8 = this.f1380t;
            i3 = cVar8.f1400b;
            int i14 = cVar8.f1402d;
            int i15 = cVar8.f1401c;
            if (i15 > 0) {
                iMax += i15;
            }
            M2(this.E);
            c cVar9 = this.f1380t;
            cVar9.f1406h = iMax;
            cVar9.f1402d += cVar9.f1403e;
            U1(vVar, cVar9, zVar, false);
            c cVar10 = this.f1380t;
            i4 = cVar10.f1400b;
            int i16 = cVar10.f1401c;
            if (i16 > 0) {
                J2(i14, i3);
                c cVar11 = this.f1380t;
                cVar11.f1406h = i16;
                U1(vVar, cVar11, zVar, false);
                i3 = this.f1380t.f1400b;
            }
        }
        if (J() > 0) {
            if (this.f1384x ^ this.f1385y) {
                int iK22 = k2(i3, vVar, zVar, true);
                i5 = i4 + iK22;
                i6 = i3 + iK22;
                iK2 = l2(i5, vVar, zVar, false);
            } else {
                int iL2 = l2(i4, vVar, zVar, true);
                i5 = i4 + iL2;
                i6 = i3 + iL2;
                iK2 = k2(i6, vVar, zVar, false);
            }
            i4 = i5 + iK2;
            i3 = i6 + iK2;
        }
        t2(vVar, zVar, i4, i3);
        if (zVar.e()) {
            this.E.e();
        } else {
            this.f1381u.s();
        }
        this.f1382v = this.f1385y;
    }

    public View X1(boolean z3, boolean z4) {
        return this.f1384x ? e2(0, J(), z3, z4) : e2(J() - 1, -1, z3, z4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    public View Y1(boolean z3, boolean z4) {
        return this.f1384x ? e2(J() - 1, -1, z3, z4) : e2(0, J(), z3, z4);
    }

    public int Z1() {
        View viewE2 = e2(0, J(), false, true);
        if (viewE2 == null) {
            return -1;
        }
        return h0(viewE2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF a(int i3) {
        if (J() == 0) {
            return null;
        }
        int i4 = (i3 < h0(I(0))) != this.f1384x ? -1 : 1;
        return this.f1379s == 0 ? new PointF(i4, 0.0f) : new PointF(0.0f, i4);
    }

    public final View a2() {
        return d2(J() - 1, -1);
    }

    public final View b2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return h2(vVar, zVar, J() - 1, -1, zVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.D = (SavedState) parcelable;
            t1();
        }
    }

    public int c2() {
        View viewE2 = e2(J() - 1, -1, false, true);
        if (viewE2 == null) {
            return -1;
        }
        return h0(viewE2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable d1() {
        if (this.D != null) {
            return new SavedState(this.D);
        }
        SavedState savedState = new SavedState();
        if (J() > 0) {
            T1();
            boolean z3 = this.f1382v ^ this.f1384x;
            savedState.f1389d = z3;
            if (z3) {
                View viewM2 = m2();
                savedState.f1388c = this.f1381u.i() - this.f1381u.d(viewM2);
                savedState.f1387b = h0(viewM2);
            } else {
                View viewN2 = n2();
                savedState.f1387b = h0(viewN2);
                savedState.f1388c = this.f1381u.g(viewN2) - this.f1381u.m();
            }
        } else {
            savedState.g();
        }
        return savedState;
    }

    public View d2(int i3, int i4) {
        int i5;
        int i6;
        T1();
        if ((i4 > i3 ? (byte) 1 : i4 < i3 ? (byte) -1 : (byte) 0) == 0) {
            return I(i3);
        }
        if (this.f1381u.g(I(i3)) < this.f1381u.m()) {
            i5 = 16644;
            i6 = 16388;
        } else {
            i5 = 4161;
            i6 = 4097;
        }
        return this.f1379s == 0 ? this.f1512e.a(i3, i4, i5, i6) : this.f1513f.a(i3, i4, i5, i6);
    }

    public View e2(int i3, int i4, boolean z3, boolean z4) {
        T1();
        int i5 = z3 ? 24579 : 320;
        int i6 = z4 ? 320 : 0;
        return this.f1379s == 0 ? this.f1512e.a(i3, i4, i5, i6) : this.f1513f.a(i3, i4, i5, i6);
    }

    public final View f2() {
        return this.f1384x ? V1() : a2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(String str) {
        if (this.D == null) {
            super.g(str);
        }
    }

    public final View g2() {
        return this.f1384x ? a2() : V1();
    }

    public View h2(RecyclerView.v vVar, RecyclerView.z zVar, int i3, int i4, int i5) {
        T1();
        int iM = this.f1381u.m();
        int i6 = this.f1381u.i();
        int i7 = i4 > i3 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i3 != i4) {
            View viewI = I(i3);
            int iH0 = h0(viewI);
            if (iH0 >= 0 && iH0 < i5) {
                if (((RecyclerView.p) viewI.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewI;
                    }
                } else {
                    if (this.f1381u.g(viewI) < i6 && this.f1381u.d(viewI) >= iM) {
                        return viewI;
                    }
                    if (view == null) {
                        view = viewI;
                    }
                }
            }
            i3 += i7;
        }
        return view != null ? view : view2;
    }

    public final View i2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f1384x ? W1(vVar, zVar) : b2(vVar, zVar);
    }

    public final View j2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f1384x ? b2(vVar, zVar) : W1(vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.f1379s == 0;
    }

    public final int k2(int i3, RecyclerView.v vVar, RecyclerView.z zVar, boolean z3) {
        int i4;
        int i5 = this.f1381u.i() - i3;
        if (i5 <= 0) {
            return 0;
        }
        int i6 = -B2(-i5, vVar, zVar);
        int i7 = i3 + i6;
        if (!z3 || (i4 = this.f1381u.i() - i7) <= 0) {
            return i6;
        }
        this.f1381u.r(i4);
        return i4 + i6;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l() {
        return this.f1379s == 1;
    }

    public final int l2(int i3, RecyclerView.v vVar, RecyclerView.z zVar, boolean z3) {
        int iM;
        int iM2 = i3 - this.f1381u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i4 = -B2(iM2, vVar, zVar);
        int i5 = i3 + i4;
        if (!z3 || (iM = i5 - this.f1381u.m()) <= 0) {
            return i4;
        }
        this.f1381u.r(-iM);
        return i4 - iM;
    }

    public final View m2() {
        return I(this.f1384x ? 0 : J() - 1);
    }

    public final View n2() {
        return I(this.f1384x ? J() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i3, int i4, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.f1379s != 0) {
            i3 = i4;
        }
        if (J() == 0 || i3 == 0) {
            return;
        }
        T1();
        I2(i3 > 0 ? 1 : -1, Math.abs(i3), true, zVar);
        N1(zVar, this.f1380t, cVar);
    }

    @Deprecated
    public int o2(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.f1381u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void p(int i3, RecyclerView.o.c cVar) {
        boolean z3;
        int i4;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.f()) {
            A2();
            z3 = this.f1384x;
            i4 = this.A;
            if (i4 == -1) {
                i4 = z3 ? i3 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z3 = savedState2.f1389d;
            i4 = savedState2.f1387b;
        }
        int i5 = z3 ? -1 : 1;
        for (int i6 = 0; i6 < this.G && i4 >= 0 && i4 < i3; i6++) {
            cVar.a(i4, 0);
            i4 += i5;
        }
    }

    public int p2() {
        return this.f1379s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return O1(zVar);
    }

    public boolean q2() {
        return Z() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return P1(zVar);
    }

    public boolean r2() {
        return this.f1386z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int s(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean s0() {
        return true;
    }

    public void s2(RecyclerView.v vVar, RecyclerView.z zVar, c cVar, b bVar) {
        int i3;
        int i4;
        int i5;
        int iE0;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f1396b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.f1410l == null) {
            if (this.f1384x == (cVar.f1404f == -1)) {
                d(viewD);
            } else {
                e(viewD, 0);
            }
        } else {
            if (this.f1384x == (cVar.f1404f == -1)) {
                b(viewD);
            } else {
                c(viewD, 0);
            }
        }
        A0(viewD, 0, 0);
        bVar.f1395a = this.f1381u.e(viewD);
        if (this.f1379s == 1) {
            if (q2()) {
                iF = o0() - f0();
                iE0 = iF - this.f1381u.f(viewD);
            } else {
                iE0 = e0();
                iF = this.f1381u.f(viewD) + iE0;
            }
            if (cVar.f1404f == -1) {
                int i6 = cVar.f1400b;
                i5 = i6;
                i4 = iF;
                i3 = i6 - bVar.f1395a;
            } else {
                int i7 = cVar.f1400b;
                i3 = i7;
                i4 = iF;
                i5 = bVar.f1395a + i7;
            }
        } else {
            int iG0 = g0();
            int iF2 = this.f1381u.f(viewD) + iG0;
            if (cVar.f1404f == -1) {
                int i8 = cVar.f1400b;
                i4 = i8;
                i3 = iG0;
                i5 = iF2;
                iE0 = i8 - bVar.f1395a;
            } else {
                int i9 = cVar.f1400b;
                i3 = iG0;
                i4 = bVar.f1395a + i9;
                i5 = iF2;
                iE0 = i9;
            }
        }
        z0(viewD, iE0, i3, i4, i5);
        if (pVar.c() || pVar.b()) {
            bVar.f1397c = true;
        }
        bVar.f1398d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return O1(zVar);
    }

    public final void t2(RecyclerView.v vVar, RecyclerView.z zVar, int i3, int i4) {
        if (!zVar.g() || J() == 0 || zVar.e() || !L1()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int iH0 = h0(I(0));
        int iE = 0;
        int iE2 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView.c0 c0Var = listK.get(i5);
            if (!c0Var.v()) {
                if (((c0Var.m() < iH0) != this.f1384x ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.f1381u.e(c0Var.f1474b);
                } else {
                    iE2 += this.f1381u.e(c0Var.f1474b);
                }
            }
        }
        this.f1380t.f1410l = listK;
        if (iE > 0) {
            L2(h0(n2()), i3);
            c cVar = this.f1380t;
            cVar.f1406h = iE;
            cVar.f1401c = 0;
            cVar.a();
            U1(vVar, this.f1380t, zVar, false);
        }
        if (iE2 > 0) {
            J2(h0(m2()), i4);
            c cVar2 = this.f1380t;
            cVar2.f1406h = iE2;
            cVar2.f1401c = 0;
            cVar2.a();
            U1(vVar, this.f1380t, zVar, false);
        }
        this.f1380t.f1410l = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return P1(zVar);
    }

    public void u2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i3) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    public final void v2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1399a || cVar.f1411m) {
            return;
        }
        int i3 = cVar.f1405g;
        int i4 = cVar.f1407i;
        if (cVar.f1404f == -1) {
            x2(vVar, i3, i4);
        } else {
            y2(vVar, i3, i4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f1379s == 1) {
            return 0;
        }
        return B2(i3, vVar, zVar);
    }

    public final void w2(RecyclerView.v vVar, int i3, int i4) {
        if (i3 == i4) {
            return;
        }
        if (i4 <= i3) {
            while (i3 > i4) {
                n1(i3, vVar);
                i3--;
            }
        } else {
            for (int i5 = i4 - 1; i5 >= i3; i5--) {
                n1(i5, vVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i3) {
        this.A = i3;
        this.B = Integer.MIN_VALUE;
        SavedState savedState = this.D;
        if (savedState != null) {
            savedState.g();
        }
        t1();
    }

    public final void x2(RecyclerView.v vVar, int i3, int i4) {
        int iJ = J();
        if (i3 < 0) {
            return;
        }
        int iH = (this.f1381u.h() - i3) + i4;
        if (this.f1384x) {
            for (int i5 = 0; i5 < iJ; i5++) {
                View viewI = I(i5);
                if (this.f1381u.g(viewI) < iH || this.f1381u.q(viewI) < iH) {
                    w2(vVar, 0, i5);
                    return;
                }
            }
            return;
        }
        int i6 = iJ - 1;
        for (int i7 = i6; i7 >= 0; i7--) {
            View viewI2 = I(i7);
            if (this.f1381u.g(viewI2) < iH || this.f1381u.q(viewI2) < iH) {
                w2(vVar, i6, i7);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f1379s == 0) {
            return 0;
        }
        return B2(i3, vVar, zVar);
    }

    public final void y2(RecyclerView.v vVar, int i3, int i4) {
        if (i3 < 0) {
            return;
        }
        int i5 = i3 - i4;
        int iJ = J();
        if (!this.f1384x) {
            for (int i6 = 0; i6 < iJ; i6++) {
                View viewI = I(i6);
                if (this.f1381u.d(viewI) > i5 || this.f1381u.p(viewI) > i5) {
                    w2(vVar, 0, i6);
                    return;
                }
            }
            return;
        }
        int i7 = iJ - 1;
        for (int i8 = i7; i8 >= 0; i8--) {
            View viewI2 = I(i8);
            if (this.f1381u.d(viewI2) > i5 || this.f1381u.p(viewI2) > i5) {
                w2(vVar, i7, i8);
                return;
            }
        }
    }

    public boolean z2() {
        return this.f1381u.k() == 0 && this.f1381u.h() == 0;
    }

    public LinearLayoutManager(Context context, int i3, boolean z3) {
        this.f1379s = 1;
        this.f1383w = false;
        this.f1384x = false;
        this.f1385y = false;
        this.f1386z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        C2(i3);
        D2(z3);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i3, int i4) {
        this.f1379s = 1;
        this.f1383w = false;
        this.f1384x = false;
        this.f1385y = false;
        this.f1386z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i3, i4);
        C2(dVarI0.f1528a);
        D2(dVarI0.f1530c);
        E2(dVarI0.f1531d);
    }
}
