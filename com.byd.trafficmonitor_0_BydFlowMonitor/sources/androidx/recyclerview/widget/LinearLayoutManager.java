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
import b.r.d.g;
import b.r.d.j;
import b.r.d.m;
import java.util.List;

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
    public int s;
    public c t;
    public j u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1145b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1146c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1147d;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean j() {
            return this.f1145b >= 0;
        }

        public void k() {
            this.f1145b = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1145b);
            parcel.writeInt(this.f1146c);
            parcel.writeInt(this.f1147d ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.f1145b = parcel.readInt();
            this.f1146c = parcel.readInt();
            this.f1147d = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1145b = savedState.f1145b;
            this.f1146c = savedState.f1146c;
            this.f1147d = savedState.f1147d;
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public j f1148a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1149b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1150c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1151d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1152e;

        public a() {
            e();
        }

        public void a() {
            this.f1150c = this.f1151d ? this.f1148a.i() : this.f1148a.m();
        }

        public void b(View view, int i2) {
            if (this.f1151d) {
                this.f1150c = this.f1148a.d(view) + this.f1148a.o();
            } else {
                this.f1150c = this.f1148a.g(view);
            }
            this.f1149b = i2;
        }

        public void c(View view, int i2) {
            int iO = this.f1148a.o();
            if (iO >= 0) {
                b(view, i2);
                return;
            }
            this.f1149b = i2;
            if (this.f1151d) {
                int i3 = (this.f1148a.i() - iO) - this.f1148a.d(view);
                this.f1150c = this.f1148a.i() - i3;
                if (i3 > 0) {
                    int iE = this.f1150c - this.f1148a.e(view);
                    int iM = this.f1148a.m();
                    int iMin = iE - (iM + Math.min(this.f1148a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1150c += Math.min(i3, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1148a.g(view);
            int iM2 = iG - this.f1148a.m();
            this.f1150c = iG;
            if (iM2 > 0) {
                int i4 = (this.f1148a.i() - Math.min(0, (this.f1148a.i() - iO) - this.f1148a.d(view))) - (iG + this.f1148a.e(view));
                if (i4 < 0) {
                    this.f1150c -= Math.min(iM2, -i4);
                }
            }
        }

        public boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < zVar.b();
        }

        public void e() {
            this.f1149b = -1;
            this.f1150c = Integer.MIN_VALUE;
            this.f1151d = false;
            this.f1152e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1149b + ", mCoordinate=" + this.f1150c + ", mLayoutFromEnd=" + this.f1151d + ", mValid=" + this.f1152e + '}';
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1153a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1154b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1155c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1156d;

        public void a() {
            this.f1153a = 0;
            this.f1154b = false;
            this.f1155c = false;
            this.f1156d = false;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1158b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1159c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1160d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1161e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1162f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1163g;
        public int k;
        public boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1157a = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1164h = 0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1165i = 0;
        public boolean j = false;
        public List<RecyclerView.c0> l = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View viewF = f(view);
            if (viewF == null) {
                this.f1160d = -1;
            } else {
                this.f1160d = ((RecyclerView.p) viewF.getLayoutParams()).a();
            }
        }

        public boolean c(RecyclerView.z zVar) {
            int i2 = this.f1160d;
            return i2 >= 0 && i2 < zVar.b();
        }

        public View d(RecyclerView.v vVar) {
            if (this.l != null) {
                return e();
            }
            View viewO = vVar.o(this.f1160d);
            this.f1160d += this.f1161e;
            return viewO;
        }

        public final View e() {
            int size = this.l.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = this.l.get(i2).f1185b;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f1160d == pVar.a()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public View f(View view) {
            int iA;
            int size = this.l.size();
            View view2 = null;
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < size; i3++) {
                View view3 = this.l.get(i3).f1185b;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (iA = (pVar.a() - this.f1160d) * this.f1161e) >= 0 && iA < i2) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i2 = iA;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public final void A2() {
        if (this.s == 1 || !q2()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    public int B2(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i2 == 0) {
            return 0;
        }
        T1();
        this.t.f1157a = true;
        int i3 = i2 > 0 ? 1 : -1;
        int iAbs = Math.abs(i2);
        I2(i3, iAbs, true, zVar);
        c cVar = this.t;
        int iU1 = cVar.f1163g + U1(vVar, cVar, zVar, false);
        if (iU1 < 0) {
            return 0;
        }
        if (iAbs > iU1) {
            i2 = i3 * iU1;
        }
        this.u.r(-i2);
        this.t.k = i2;
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View C(int i2) {
        int iJ = J();
        if (iJ == 0) {
            return null;
        }
        int iH0 = i2 - h0(I(0));
        if (iH0 >= 0 && iH0 < iJ) {
            View viewI = I(iH0);
            if (h0(viewI) == i2) {
                return viewI;
            }
        }
        return super.C(i2);
    }

    public void C2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        g(null);
        if (i2 != this.s || this.u == null) {
            j jVarB = j.b(this, i2);
            this.u = jVarB;
            this.E.f1148a = jVarB;
            this.s = i2;
            t1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return new RecyclerView.p(-2, -2);
    }

    public void D2(boolean z) {
        g(null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        t1();
    }

    public void E2(boolean z) {
        g(null);
        if (this.y == z) {
            return;
        }
        this.y = z;
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
        if (this.v != this.y) {
            return false;
        }
        View viewI2 = aVar.f1151d ? i2(vVar, zVar) : j2(vVar, zVar);
        if (viewI2 == null) {
            return false;
        }
        aVar.b(viewI2, h0(viewI2));
        if (!zVar.e() && L1()) {
            if (this.u.g(viewI2) >= this.u.i() || this.u.d(viewI2) < this.u.m()) {
                aVar.f1150c = aVar.f1151d ? this.u.i() : this.u.m();
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean G1() {
        return (X() == 1073741824 || p0() == 1073741824 || !q0()) ? false : true;
    }

    public final boolean G2(RecyclerView.z zVar, a aVar) {
        int i2;
        if (!zVar.e() && (i2 = this.A) != -1) {
            if (i2 >= 0 && i2 < zVar.b()) {
                aVar.f1149b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.j()) {
                    boolean z = this.D.f1147d;
                    aVar.f1151d = z;
                    if (z) {
                        aVar.f1150c = this.u.i() - this.D.f1146c;
                    } else {
                        aVar.f1150c = this.u.m() + this.D.f1146c;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z2 = this.x;
                    aVar.f1151d = z2;
                    if (z2) {
                        aVar.f1150c = this.u.i() - this.B;
                    } else {
                        aVar.f1150c = this.u.m() + this.B;
                    }
                    return true;
                }
                View viewC = C(this.A);
                if (viewC == null) {
                    if (J() > 0) {
                        aVar.f1151d = (this.A < h0(I(0))) == this.x;
                    }
                    aVar.a();
                } else {
                    if (this.u.e(viewC) > this.u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.u.g(viewC) - this.u.m() < 0) {
                        aVar.f1150c = this.u.m();
                        aVar.f1151d = false;
                        return true;
                    }
                    if (this.u.i() - this.u.d(viewC) < 0) {
                        aVar.f1150c = this.u.i();
                        aVar.f1151d = true;
                        return true;
                    }
                    aVar.f1150c = aVar.f1151d ? this.u.d(viewC) + this.u.o() : this.u.g(viewC);
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
        aVar.f1149b = this.y ? zVar.b() - 1 : 0;
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
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i2) {
        g gVar = new g(recyclerView.getContext());
        gVar.p(i2);
        J1(gVar);
    }

    public final void I2(int i2, int i3, boolean z, RecyclerView.z zVar) {
        int iM;
        this.t.m = z2();
        this.t.f1162f = i2;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]);
        int iMax2 = Math.max(0, this.H[1]);
        boolean z2 = i2 == 1;
        c cVar = this.t;
        int i4 = z2 ? iMax2 : iMax;
        cVar.f1164h = i4;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.f1165i = iMax;
        if (z2) {
            cVar.f1164h = i4 + this.u.j();
            View viewM2 = m2();
            c cVar2 = this.t;
            cVar2.f1161e = this.x ? -1 : 1;
            int iH0 = h0(viewM2);
            c cVar3 = this.t;
            cVar2.f1160d = iH0 + cVar3.f1161e;
            cVar3.f1158b = this.u.d(viewM2);
            iM = this.u.d(viewM2) - this.u.i();
        } else {
            View viewN2 = n2();
            this.t.f1164h += this.u.m();
            c cVar4 = this.t;
            cVar4.f1161e = this.x ? 1 : -1;
            int iH02 = h0(viewN2);
            c cVar5 = this.t;
            cVar4.f1160d = iH02 + cVar5.f1161e;
            cVar5.f1158b = this.u.g(viewN2);
            iM = (-this.u.g(viewN2)) + this.u.m();
        }
        c cVar6 = this.t;
        cVar6.f1159c = i3;
        if (z) {
            cVar6.f1159c = i3 - iM;
        }
        cVar6.f1163g = iM;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iR1;
        A2();
        if (J() == 0 || (iR1 = R1(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        T1();
        I2(iR1, (int) (this.u.n() * 0.33333334f), false, zVar);
        c cVar = this.t;
        cVar.f1163g = Integer.MIN_VALUE;
        cVar.f1157a = false;
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

    public final void J2(int i2, int i3) {
        this.t.f1159c = this.u.i() - i3;
        c cVar = this.t;
        cVar.f1161e = this.x ? -1 : 1;
        cVar.f1160d = i2;
        cVar.f1162f = 1;
        cVar.f1158b = i3;
        cVar.f1163g = Integer.MIN_VALUE;
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
        J2(aVar.f1149b, aVar.f1150c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean L1() {
        return this.D == null && this.v == this.y;
    }

    public final void L2(int i2, int i3) {
        this.t.f1159c = i3 - this.u.m();
        c cVar = this.t;
        cVar.f1160d = i2;
        cVar.f1161e = this.x ? 1 : -1;
        cVar.f1162f = -1;
        cVar.f1158b = i3;
        cVar.f1163g = Integer.MIN_VALUE;
    }

    public void M1(RecyclerView.z zVar, int[] iArr) {
        int i2;
        int iO2 = o2(zVar);
        if (this.t.f1162f == -1) {
            i2 = 0;
        } else {
            i2 = iO2;
            iO2 = 0;
        }
        iArr[0] = iO2;
        iArr[1] = i2;
    }

    public final void M2(a aVar) {
        L2(aVar.f1149b, aVar.f1150c);
    }

    public void N1(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i2 = cVar.f1160d;
        if (i2 < 0 || i2 >= zVar.b()) {
            return;
        }
        cVar2.a(i2, Math.max(0, cVar.f1163g));
    }

    public final int O1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.a(zVar, this.u, Y1(!this.z, true), X1(!this.z, true), this, this.z);
    }

    public final int P1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.b(zVar, this.u, Y1(!this.z, true), X1(!this.z, true), this, this.z, this.x);
    }

    public final int Q1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.c(zVar, this.u, Y1(!this.z, true), X1(!this.z, true), this, this.z);
    }

    public int R1(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && q2()) ? -1 : 1 : (this.s != 1 && q2()) ? 1 : -1;
    }

    public c S1() {
        return new c();
    }

    public void T1() {
        if (this.t == null) {
            this.t = S1();
        }
    }

    public int U1(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z) {
        int i2 = cVar.f1159c;
        int i3 = cVar.f1163g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                cVar.f1163g = i3 + i2;
            }
            v2(vVar, cVar);
        }
        int i4 = cVar.f1159c + cVar.f1164h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.m && i4 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            s2(vVar, zVar, cVar, bVar);
            if (!bVar.f1154b) {
                cVar.f1158b += bVar.f1153a * cVar.f1162f;
                if (!bVar.f1155c || cVar.l != null || !zVar.e()) {
                    int i5 = cVar.f1159c;
                    int i6 = bVar.f1153a;
                    cVar.f1159c = i5 - i6;
                    i4 -= i6;
                }
                int i7 = cVar.f1163g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + bVar.f1153a;
                    cVar.f1163g = i8;
                    int i9 = cVar.f1159c;
                    if (i9 < 0) {
                        cVar.f1163g = i8 + i9;
                    }
                    v2(vVar, cVar);
                }
                if (z && bVar.f1156d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - cVar.f1159c;
    }

    public final View V1() {
        return d2(0, J());
    }

    public final View W1(RecyclerView.v vVar, RecyclerView.z zVar) {
        return h2(vVar, zVar, 0, J(), zVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int iK2;
        int i6;
        View viewC;
        int iG;
        int i7;
        int i8 = -1;
        if (!(this.D == null && this.A == -1) && zVar.b() == 0) {
            k1(vVar);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.j()) {
            this.A = this.D.f1145b;
        }
        T1();
        this.t.f1157a = false;
        A2();
        View viewV = V();
        a aVar = this.E;
        if (!aVar.f1152e || this.A != -1 || this.D != null) {
            aVar.e();
            a aVar2 = this.E;
            aVar2.f1151d = this.x ^ this.y;
            H2(vVar, zVar, aVar2);
            this.E.f1152e = true;
        } else if (viewV != null && (this.u.g(viewV) >= this.u.i() || this.u.d(viewV) <= this.u.m())) {
            this.E.c(viewV, h0(viewV));
        }
        c cVar = this.t;
        cVar.f1162f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]) + this.u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.u.j();
        if (zVar.e() && (i6 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewC = C(i6)) != null) {
            if (this.x) {
                i7 = this.u.i() - this.u.d(viewC);
                iG = this.B;
            } else {
                iG = this.u.g(viewC) - this.u.m();
                i7 = this.B;
            }
            int i9 = i7 - iG;
            if (i9 > 0) {
                iMax += i9;
            } else {
                iMax2 -= i9;
            }
        }
        a aVar3 = this.E;
        if (!aVar3.f1151d ? !this.x : this.x) {
            i8 = 1;
        }
        u2(vVar, zVar, aVar3, i8);
        w(vVar);
        this.t.m = z2();
        this.t.j = zVar.e();
        this.t.f1165i = 0;
        a aVar4 = this.E;
        if (aVar4.f1151d) {
            M2(aVar4);
            c cVar2 = this.t;
            cVar2.f1164h = iMax;
            U1(vVar, cVar2, zVar, false);
            c cVar3 = this.t;
            i3 = cVar3.f1158b;
            int i10 = cVar3.f1160d;
            int i11 = cVar3.f1159c;
            if (i11 > 0) {
                iMax2 += i11;
            }
            K2(this.E);
            c cVar4 = this.t;
            cVar4.f1164h = iMax2;
            cVar4.f1160d += cVar4.f1161e;
            U1(vVar, cVar4, zVar, false);
            c cVar5 = this.t;
            i2 = cVar5.f1158b;
            int i12 = cVar5.f1159c;
            if (i12 > 0) {
                L2(i10, i3);
                c cVar6 = this.t;
                cVar6.f1164h = i12;
                U1(vVar, cVar6, zVar, false);
                i3 = this.t.f1158b;
            }
        } else {
            K2(aVar4);
            c cVar7 = this.t;
            cVar7.f1164h = iMax2;
            U1(vVar, cVar7, zVar, false);
            c cVar8 = this.t;
            i2 = cVar8.f1158b;
            int i13 = cVar8.f1160d;
            int i14 = cVar8.f1159c;
            if (i14 > 0) {
                iMax += i14;
            }
            M2(this.E);
            c cVar9 = this.t;
            cVar9.f1164h = iMax;
            cVar9.f1160d += cVar9.f1161e;
            U1(vVar, cVar9, zVar, false);
            c cVar10 = this.t;
            i3 = cVar10.f1158b;
            int i15 = cVar10.f1159c;
            if (i15 > 0) {
                J2(i13, i2);
                c cVar11 = this.t;
                cVar11.f1164h = i15;
                U1(vVar, cVar11, zVar, false);
                i2 = this.t.f1158b;
            }
        }
        if (J() > 0) {
            if (this.x ^ this.y) {
                int iK22 = k2(i2, vVar, zVar, true);
                i4 = i3 + iK22;
                i5 = i2 + iK22;
                iK2 = l2(i4, vVar, zVar, false);
            } else {
                int iL2 = l2(i3, vVar, zVar, true);
                i4 = i3 + iL2;
                i5 = i2 + iL2;
                iK2 = k2(i5, vVar, zVar, false);
            }
            i3 = i4 + iK2;
            i2 = i5 + iK2;
        }
        t2(vVar, zVar, i3, i2);
        if (zVar.e()) {
            this.E.e();
        } else {
            this.u.s();
        }
        this.v = this.y;
    }

    public View X1(boolean z, boolean z2) {
        return this.x ? e2(0, J(), z, z2) : e2(J() - 1, -1, z, z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    public View Y1(boolean z, boolean z2) {
        return this.x ? e2(J() - 1, -1, z, z2) : e2(0, J(), z, z2);
    }

    public int Z1() {
        View viewE2 = e2(0, J(), false, true);
        if (viewE2 == null) {
            return -1;
        }
        return h0(viewE2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF a(int i2) {
        if (J() == 0) {
            return null;
        }
        int i3 = (i2 < h0(I(0))) != this.x ? -1 : 1;
        return this.s == 0 ? new PointF(i3, 0.0f) : new PointF(0.0f, i3);
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
            boolean z = this.v ^ this.x;
            savedState.f1147d = z;
            if (z) {
                View viewM2 = m2();
                savedState.f1146c = this.u.i() - this.u.d(viewM2);
                savedState.f1145b = h0(viewM2);
            } else {
                View viewN2 = n2();
                savedState.f1145b = h0(viewN2);
                savedState.f1146c = this.u.g(viewN2) - this.u.m();
            }
        } else {
            savedState.k();
        }
        return savedState;
    }

    public View d2(int i2, int i3) {
        int i4;
        int i5;
        T1();
        if ((i3 > i2 ? (byte) 1 : i3 < i2 ? (byte) -1 : (byte) 0) == 0) {
            return I(i2);
        }
        if (this.u.g(I(i2)) < this.u.m()) {
            i4 = 16644;
            i5 = 16388;
        } else {
            i4 = 4161;
            i5 = 4097;
        }
        return this.s == 0 ? this.f1213e.a(i2, i3, i4, i5) : this.f1214f.a(i2, i3, i4, i5);
    }

    public View e2(int i2, int i3, boolean z, boolean z2) {
        T1();
        int i4 = z ? 24579 : 320;
        int i5 = z2 ? 320 : 0;
        return this.s == 0 ? this.f1213e.a(i2, i3, i4, i5) : this.f1214f.a(i2, i3, i4, i5);
    }

    public final View f2() {
        return this.x ? V1() : a2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(String str) {
        if (this.D == null) {
            super.g(str);
        }
    }

    public final View g2() {
        return this.x ? a2() : V1();
    }

    public View h2(RecyclerView.v vVar, RecyclerView.z zVar, int i2, int i3, int i4) {
        T1();
        int iM = this.u.m();
        int i5 = this.u.i();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View viewI = I(i2);
            int iH0 = h0(viewI);
            if (iH0 >= 0 && iH0 < i4) {
                if (((RecyclerView.p) viewI.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewI;
                    }
                } else {
                    if (this.u.g(viewI) < i5 && this.u.d(viewI) >= iM) {
                        return viewI;
                    }
                    if (view == null) {
                        view = viewI;
                    }
                }
            }
            i2 += i6;
        }
        return view != null ? view : view2;
    }

    public final View i2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x ? W1(vVar, zVar) : b2(vVar, zVar);
    }

    public final View j2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x ? b2(vVar, zVar) : W1(vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.s == 0;
    }

    public final int k2(int i2, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i3;
        int i4 = this.u.i() - i2;
        if (i4 <= 0) {
            return 0;
        }
        int i5 = -B2(-i4, vVar, zVar);
        int i6 = i2 + i5;
        if (!z || (i3 = this.u.i() - i6) <= 0) {
            return i5;
        }
        this.u.r(i3);
        return i3 + i5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l() {
        return this.s == 1;
    }

    public final int l2(int i2, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iM2 = i2 - this.u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i3 = -B2(iM2, vVar, zVar);
        int i4 = i2 + i3;
        if (!z || (iM = i4 - this.u.m()) <= 0) {
            return i3;
        }
        this.u.r(-iM);
        return i3 - iM;
    }

    public final View m2() {
        return I(this.x ? 0 : J() - 1);
    }

    public final View n2() {
        return I(this.x ? J() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i2, int i3, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.s != 0) {
            i2 = i3;
        }
        if (J() == 0 || i2 == 0) {
            return;
        }
        T1();
        I2(i2 > 0 ? 1 : -1, Math.abs(i2), true, zVar);
        N1(zVar, this.t, cVar);
    }

    @Deprecated
    public int o2(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void p(int i2, RecyclerView.o.c cVar) {
        boolean z;
        int i3;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.j()) {
            A2();
            z = this.x;
            i3 = this.A;
            if (i3 == -1) {
                i3 = z ? i2 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z = savedState2.f1147d;
            i3 = savedState2.f1145b;
        }
        int i4 = z ? -1 : 1;
        for (int i5 = 0; i5 < this.G && i3 >= 0 && i3 < i2; i5++) {
            cVar.a(i3, 0);
            i3 += i4;
        }
    }

    public int p2() {
        return this.s;
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
        return this.z;
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
        int i2;
        int i3;
        int i4;
        int iE0;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f1154b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f1162f == -1)) {
                d(viewD);
            } else {
                e(viewD, 0);
            }
        } else {
            if (this.x == (cVar.f1162f == -1)) {
                b(viewD);
            } else {
                c(viewD, 0);
            }
        }
        A0(viewD, 0, 0);
        bVar.f1153a = this.u.e(viewD);
        if (this.s == 1) {
            if (q2()) {
                iF = o0() - f0();
                iE0 = iF - this.u.f(viewD);
            } else {
                iE0 = e0();
                iF = this.u.f(viewD) + iE0;
            }
            if (cVar.f1162f == -1) {
                int i5 = cVar.f1158b;
                i4 = i5;
                i3 = iF;
                i2 = i5 - bVar.f1153a;
            } else {
                int i6 = cVar.f1158b;
                i2 = i6;
                i3 = iF;
                i4 = bVar.f1153a + i6;
            }
        } else {
            int iG0 = g0();
            int iF2 = this.u.f(viewD) + iG0;
            if (cVar.f1162f == -1) {
                int i7 = cVar.f1158b;
                i3 = i7;
                i2 = iG0;
                i4 = iF2;
                iE0 = i7 - bVar.f1153a;
            } else {
                int i8 = cVar.f1158b;
                i2 = iG0;
                i3 = bVar.f1153a + i8;
                i4 = iF2;
                iE0 = i8;
            }
        }
        z0(viewD, iE0, i2, i3, i4);
        if (pVar.c() || pVar.b()) {
            bVar.f1155c = true;
        }
        bVar.f1156d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return O1(zVar);
    }

    public final void t2(RecyclerView.v vVar, RecyclerView.z zVar, int i2, int i3) {
        if (!zVar.g() || J() == 0 || zVar.e() || !L1()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int iH0 = h0(I(0));
        int iE = 0;
        int iE2 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView.c0 c0Var = listK.get(i4);
            if (!c0Var.v()) {
                if (((c0Var.m() < iH0) != this.x ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.u.e(c0Var.f1185b);
                } else {
                    iE2 += this.u.e(c0Var.f1185b);
                }
            }
        }
        this.t.l = listK;
        if (iE > 0) {
            L2(h0(n2()), i2);
            c cVar = this.t;
            cVar.f1164h = iE;
            cVar.f1159c = 0;
            cVar.a();
            U1(vVar, this.t, zVar, false);
        }
        if (iE2 > 0) {
            J2(h0(m2()), i3);
            c cVar2 = this.t;
            cVar2.f1164h = iE2;
            cVar2.f1159c = 0;
            cVar2.a();
            U1(vVar, this.t, zVar, false);
        }
        this.t.l = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return P1(zVar);
    }

    public void u2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    public final void v2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1157a || cVar.m) {
            return;
        }
        int i2 = cVar.f1163g;
        int i3 = cVar.f1165i;
        if (cVar.f1162f == -1) {
            x2(vVar, i2, i3);
        } else {
            y2(vVar, i2, i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 1) {
            return 0;
        }
        return B2(i2, vVar, zVar);
    }

    public final void w2(RecyclerView.v vVar, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        if (i3 <= i2) {
            while (i2 > i3) {
                n1(i2, vVar);
                i2--;
            }
        } else {
            for (int i4 = i3 - 1; i4 >= i2; i4--) {
                n1(i4, vVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i2) {
        this.A = i2;
        this.B = Integer.MIN_VALUE;
        SavedState savedState = this.D;
        if (savedState != null) {
            savedState.k();
        }
        t1();
    }

    public final void x2(RecyclerView.v vVar, int i2, int i3) {
        int iJ = J();
        if (i2 < 0) {
            return;
        }
        int iH = (this.u.h() - i2) + i3;
        if (this.x) {
            for (int i4 = 0; i4 < iJ; i4++) {
                View viewI = I(i4);
                if (this.u.g(viewI) < iH || this.u.q(viewI) < iH) {
                    w2(vVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = iJ - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View viewI2 = I(i6);
            if (this.u.g(viewI2) < iH || this.u.q(viewI2) < iH) {
                w2(vVar, i5, i6);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 0) {
            return 0;
        }
        return B2(i2, vVar, zVar);
    }

    public final void y2(RecyclerView.v vVar, int i2, int i3) {
        if (i2 < 0) {
            return;
        }
        int i4 = i2 - i3;
        int iJ = J();
        if (!this.x) {
            for (int i5 = 0; i5 < iJ; i5++) {
                View viewI = I(i5);
                if (this.u.d(viewI) > i4 || this.u.p(viewI) > i4) {
                    w2(vVar, 0, i5);
                    return;
                }
            }
            return;
        }
        int i6 = iJ - 1;
        for (int i7 = i6; i7 >= 0; i7--) {
            View viewI2 = I(i7);
            if (this.u.d(viewI2) > i4 || this.u.p(viewI2) > i4) {
                w2(vVar, i6, i7);
                return;
            }
        }
    }

    public boolean z2() {
        return this.u.k() == 0 && this.u.h() == 0;
    }

    public LinearLayoutManager(Context context, int i2, boolean z) {
        this.s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        C2(i2);
        D2(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i2, i3);
        C2(dVarI0.f1220a);
        D2(dVarI0.f1222c);
        E2(dVarI0.f1223d);
    }
}
