package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.n.d.h;
import b.n.d.j;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o {
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
    public h u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f553c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f554d;
        public boolean s;

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

        public boolean a() {
            return this.f553c >= 0;
        }

        public void b() {
            this.f553c = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f553c);
            parcel.writeInt(this.f554d);
            parcel.writeInt(this.s ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.f553c = parcel.readInt();
            this.f554d = parcel.readInt();
            this.s = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f553c = savedState.f553c;
            this.f554d = savedState.f554d;
            this.s = savedState.s;
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f555a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f556b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f557c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f558d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f559e;

        public a() {
            e();
        }

        public void a() {
            this.f557c = this.f558d ? this.f555a.i() : this.f555a.m();
        }

        public void b(View view, int i2) {
            if (this.f558d) {
                this.f557c = this.f555a.d(view) + this.f555a.o();
            } else {
                this.f557c = this.f555a.g(view);
            }
            this.f556b = i2;
        }

        public void c(View view, int i2) {
            int iO = this.f555a.o();
            if (iO >= 0) {
                b(view, i2);
                return;
            }
            this.f556b = i2;
            if (this.f558d) {
                int i3 = (this.f555a.i() - iO) - this.f555a.d(view);
                this.f557c = this.f555a.i() - i3;
                if (i3 > 0) {
                    int iE = this.f557c - this.f555a.e(view);
                    int iM = this.f555a.m();
                    int iMin = iE - (iM + Math.min(this.f555a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f557c += Math.min(i3, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f555a.g(view);
            int iM2 = iG - this.f555a.m();
            this.f557c = iG;
            if (iM2 > 0) {
                int i4 = (this.f555a.i() - Math.min(0, (this.f555a.i() - iO) - this.f555a.d(view))) - (iG + this.f555a.e(view));
                if (i4 < 0) {
                    this.f557c -= Math.min(iM2, -i4);
                }
            }
        }

        public boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < zVar.b();
        }

        public void e() {
            this.f556b = -1;
            this.f557c = Integer.MIN_VALUE;
            this.f558d = false;
            this.f559e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f556b + ", mCoordinate=" + this.f557c + ", mLayoutFromEnd=" + this.f558d + ", mValid=" + this.f559e + '}';
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f560a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f561b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f562c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f563d;

        public void a() {
            this.f560a = 0;
            this.f561b = false;
            this.f562c = false;
            this.f563d = false;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f565b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f566c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f567d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f568e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f569f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f570g;
        public int k;
        public boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f564a = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f571h = 0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f572i = 0;
        public boolean j = false;
        public List<RecyclerView.c0> l = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View viewF = f(view);
            if (viewF == null) {
                this.f567d = -1;
            } else {
                this.f567d = ((RecyclerView.p) viewF.getLayoutParams()).a();
            }
        }

        public boolean c(RecyclerView.z zVar) {
            int i2 = this.f567d;
            return i2 >= 0 && i2 < zVar.b();
        }

        public View d(RecyclerView.v vVar) {
            if (this.l != null) {
                return e();
            }
            View viewO = vVar.o(this.f567d);
            this.f567d += this.f568e;
            return viewO;
        }

        public final View e() {
            int size = this.l.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = this.l.get(i2).f580b;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f567d == pVar.a()) {
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
                View view3 = this.l.get(i3).f580b;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (iA = (pVar.a() - this.f567d) * this.f568e) >= 0 && iA < i2) {
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
        w2(i2);
        x2(z);
    }

    public final boolean A2(RecyclerView.z zVar, a aVar) {
        int i2;
        if (!zVar.e() && (i2 = this.A) != -1) {
            if (i2 >= 0 && i2 < zVar.b()) {
                aVar.f556b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.a()) {
                    boolean z = this.D.s;
                    aVar.f558d = z;
                    if (z) {
                        aVar.f557c = this.u.i() - this.D.f554d;
                    } else {
                        aVar.f557c = this.u.m() + this.D.f554d;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z2 = this.x;
                    aVar.f558d = z2;
                    if (z2) {
                        aVar.f557c = this.u.i() - this.B;
                    } else {
                        aVar.f557c = this.u.m() + this.B;
                    }
                    return true;
                }
                View viewB = B(this.A);
                if (viewB == null) {
                    if (I() > 0) {
                        aVar.f558d = (this.A < f0(H(0))) == this.x;
                    }
                    aVar.a();
                } else {
                    if (this.u.e(viewB) > this.u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.u.g(viewB) - this.u.m() < 0) {
                        aVar.f557c = this.u.m();
                        aVar.f558d = false;
                        return true;
                    }
                    if (this.u.i() - this.u.d(viewB) < 0) {
                        aVar.f557c = this.u.i();
                        aVar.f558d = true;
                        return true;
                    }
                    aVar.f557c = aVar.f558d ? this.u.d(viewB) + this.u.o() : this.u.g(viewB);
                }
                return true;
            }
            this.A = -1;
            this.B = Integer.MIN_VALUE;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View B(int i2) {
        int I = I();
        if (I == 0) {
            return null;
        }
        int iF0 = i2 - f0(H(0));
        if (iF0 >= 0 && iF0 < I) {
            View viewH = H(iF0);
            if (f0(viewH) == i2) {
                return viewH;
            }
        }
        return super.B(i2);
    }

    public final void B2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (A2(zVar, aVar) || z2(vVar, zVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f556b = this.y ? zVar.b() - 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p C() {
        return new RecyclerView.p(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean C1() {
        return (W() == 1073741824 || n0() == 1073741824 || !o0()) ? false : true;
    }

    public final void C2(int i2, int i3, boolean z, RecyclerView.z zVar) {
        int iM;
        this.t.m = t2();
        this.t.f569f = i2;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        G1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]);
        int iMax2 = Math.max(0, this.H[1]);
        boolean z2 = i2 == 1;
        c cVar = this.t;
        int i4 = z2 ? iMax2 : iMax;
        cVar.f571h = i4;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.f572i = iMax;
        if (z2) {
            cVar.f571h = i4 + this.u.j();
            View viewG2 = g2();
            c cVar2 = this.t;
            cVar2.f568e = this.x ? -1 : 1;
            int iF0 = f0(viewG2);
            c cVar3 = this.t;
            cVar2.f567d = iF0 + cVar3.f568e;
            cVar3.f565b = this.u.d(viewG2);
            iM = this.u.d(viewG2) - this.u.i();
        } else {
            View viewH2 = h2();
            this.t.f571h += this.u.m();
            c cVar4 = this.t;
            cVar4.f568e = this.x ? 1 : -1;
            int iF02 = f0(viewH2);
            c cVar5 = this.t;
            cVar4.f567d = iF02 + cVar5.f568e;
            cVar5.f565b = this.u.g(viewH2);
            iM = (-this.u.g(viewH2)) + this.u.m();
        }
        c cVar6 = this.t;
        cVar6.f566c = i3;
        if (z) {
            cVar6.f566c = i3 - iM;
        }
        cVar6.f570g = iM;
    }

    public final void D2(int i2, int i3) {
        this.t.f566c = this.u.i() - i3;
        c cVar = this.t;
        cVar.f568e = this.x ? -1 : 1;
        cVar.f567d = i2;
        cVar.f569f = 1;
        cVar.f565b = i3;
        cVar.f570g = Integer.MIN_VALUE;
    }

    public final void E2(a aVar) {
        D2(aVar.f556b, aVar.f557c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean F1() {
        return this.D == null && this.v == this.y;
    }

    public final void F2(int i2, int i3) {
        this.t.f566c = i3 - this.u.m();
        c cVar = this.t;
        cVar.f567d = i2;
        cVar.f568e = this.x ? 1 : -1;
        cVar.f569f = -1;
        cVar.f565b = i3;
        cVar.f570g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void G0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.G0(recyclerView, vVar);
        if (this.C) {
            h1(vVar);
            vVar.c();
        }
    }

    public void G1(RecyclerView.z zVar, int[] iArr) {
        int i2;
        int iI2 = i2(zVar);
        if (this.t.f569f == -1) {
            i2 = 0;
        } else {
            i2 = iI2;
            iI2 = 0;
        }
        iArr[0] = iI2;
        iArr[1] = i2;
    }

    public final void G2(a aVar) {
        F2(aVar.f556b, aVar.f557c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View H0(View view, int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iL1;
        u2();
        if (I() == 0 || (iL1 = L1(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        N1();
        C2(iL1, (int) (this.u.n() * 0.33333334f), false, zVar);
        c cVar = this.t;
        cVar.f570g = Integer.MIN_VALUE;
        cVar.f564a = false;
        O1(vVar, cVar, zVar, true);
        View viewA2 = iL1 == -1 ? a2() : Z1();
        View viewH2 = iL1 == -1 ? h2() : g2();
        if (!viewH2.hasFocusable()) {
            return viewA2;
        }
        if (viewA2 == null) {
            return null;
        }
        return viewH2;
    }

    public void H1(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i2 = cVar.f567d;
        if (i2 < 0 || i2 >= zVar.b()) {
            return;
        }
        cVar2.a(i2, Math.max(0, cVar.f570g));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(AccessibilityEvent accessibilityEvent) {
        super.I0(accessibilityEvent);
        if (I() > 0) {
            accessibilityEvent.setFromIndex(T1());
            accessibilityEvent.setToIndex(W1());
        }
    }

    public final int I1(RecyclerView.z zVar) {
        if (I() == 0) {
            return 0;
        }
        N1();
        return j.a(zVar, this.u, S1(!this.z, true), R1(!this.z, true), this, this.z);
    }

    public final int J1(RecyclerView.z zVar) {
        if (I() == 0) {
            return 0;
        }
        N1();
        return j.b(zVar, this.u, S1(!this.z, true), R1(!this.z, true), this, this.z, this.x);
    }

    public final int K1(RecyclerView.z zVar) {
        if (I() == 0) {
            return 0;
        }
        N1();
        return j.c(zVar, this.u, S1(!this.z, true), R1(!this.z, true), this, this.z);
    }

    public int L1(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && k2()) ? -1 : 1 : (this.s != 1 && k2()) ? 1 : -1;
    }

    public c M1() {
        return new c();
    }

    public void N1() {
        if (this.t == null) {
            this.t = M1();
        }
    }

    public int O1(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z) {
        int i2 = cVar.f566c;
        int i3 = cVar.f570g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                cVar.f570g = i3 + i2;
            }
            p2(vVar, cVar);
        }
        int i4 = cVar.f566c + cVar.f571h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.m && i4 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            m2(vVar, zVar, cVar, bVar);
            if (!bVar.f561b) {
                cVar.f565b += bVar.f560a * cVar.f569f;
                if (!bVar.f562c || cVar.l != null || !zVar.e()) {
                    int i5 = cVar.f566c;
                    int i6 = bVar.f560a;
                    cVar.f566c = i5 - i6;
                    i4 -= i6;
                }
                int i7 = cVar.f570g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + bVar.f560a;
                    cVar.f570g = i8;
                    int i9 = cVar.f566c;
                    if (i9 < 0) {
                        cVar.f570g = i8 + i9;
                    }
                    p2(vVar, cVar);
                }
                if (z && bVar.f563d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - cVar.f566c;
    }

    public final View P1() {
        return X1(0, I());
    }

    public final View Q1(RecyclerView.v vVar, RecyclerView.z zVar) {
        return b2(vVar, zVar, 0, I(), zVar.b());
    }

    public View R1(boolean z, boolean z2) {
        return this.x ? Y1(0, I(), z, z2) : Y1(I() - 1, -1, z, z2);
    }

    public View S1(boolean z, boolean z2) {
        return this.x ? Y1(I() - 1, -1, z, z2) : Y1(0, I(), z, z2);
    }

    public int T1() {
        View viewY1 = Y1(0, I(), false, true);
        if (viewY1 == null) {
            return -1;
        }
        return f0(viewY1);
    }

    public final View U1() {
        return X1(I() - 1, -1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void V0(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int iE2;
        int i6;
        View viewB;
        int iG;
        int i7;
        int i8 = -1;
        if (!(this.D == null && this.A == -1) && zVar.b() == 0) {
            h1(vVar);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.a()) {
            this.A = this.D.f553c;
        }
        N1();
        this.t.f564a = false;
        u2();
        View viewU = U();
        a aVar = this.E;
        if (!aVar.f559e || this.A != -1 || this.D != null) {
            aVar.e();
            a aVar2 = this.E;
            aVar2.f558d = this.x ^ this.y;
            B2(vVar, zVar, aVar2);
            this.E.f559e = true;
        } else if (viewU != null && (this.u.g(viewU) >= this.u.i() || this.u.d(viewU) <= this.u.m())) {
            this.E.c(viewU, f0(viewU));
        }
        c cVar = this.t;
        cVar.f569f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        G1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]) + this.u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.u.j();
        if (zVar.e() && (i6 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewB = B(i6)) != null) {
            if (this.x) {
                i7 = this.u.i() - this.u.d(viewB);
                iG = this.B;
            } else {
                iG = this.u.g(viewB) - this.u.m();
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
        if (!aVar3.f558d ? !this.x : this.x) {
            i8 = 1;
        }
        o2(vVar, zVar, aVar3, i8);
        v(vVar);
        this.t.m = t2();
        this.t.j = zVar.e();
        this.t.f572i = 0;
        a aVar4 = this.E;
        if (aVar4.f558d) {
            G2(aVar4);
            c cVar2 = this.t;
            cVar2.f571h = iMax;
            O1(vVar, cVar2, zVar, false);
            c cVar3 = this.t;
            i3 = cVar3.f565b;
            int i10 = cVar3.f567d;
            int i11 = cVar3.f566c;
            if (i11 > 0) {
                iMax2 += i11;
            }
            E2(this.E);
            c cVar4 = this.t;
            cVar4.f571h = iMax2;
            cVar4.f567d += cVar4.f568e;
            O1(vVar, cVar4, zVar, false);
            c cVar5 = this.t;
            i2 = cVar5.f565b;
            int i12 = cVar5.f566c;
            if (i12 > 0) {
                F2(i10, i3);
                c cVar6 = this.t;
                cVar6.f571h = i12;
                O1(vVar, cVar6, zVar, false);
                i3 = this.t.f565b;
            }
        } else {
            E2(aVar4);
            c cVar7 = this.t;
            cVar7.f571h = iMax2;
            O1(vVar, cVar7, zVar, false);
            c cVar8 = this.t;
            i2 = cVar8.f565b;
            int i13 = cVar8.f567d;
            int i14 = cVar8.f566c;
            if (i14 > 0) {
                iMax += i14;
            }
            G2(this.E);
            c cVar9 = this.t;
            cVar9.f571h = iMax;
            cVar9.f567d += cVar9.f568e;
            O1(vVar, cVar9, zVar, false);
            c cVar10 = this.t;
            i3 = cVar10.f565b;
            int i15 = cVar10.f566c;
            if (i15 > 0) {
                D2(i13, i2);
                c cVar11 = this.t;
                cVar11.f571h = i15;
                O1(vVar, cVar11, zVar, false);
                i2 = this.t.f565b;
            }
        }
        if (I() > 0) {
            if (this.x ^ this.y) {
                int iE22 = e2(i2, vVar, zVar, true);
                i4 = i3 + iE22;
                i5 = i2 + iE22;
                iE2 = f2(i4, vVar, zVar, false);
            } else {
                int iF2 = f2(i3, vVar, zVar, true);
                i4 = i3 + iF2;
                i5 = i2 + iF2;
                iE2 = e2(i5, vVar, zVar, false);
            }
            i3 = i4 + iE2;
            i2 = i5 + iE2;
        }
        n2(vVar, zVar, i3, i2);
        if (zVar.e()) {
            this.E.e();
        } else {
            this.u.s();
        }
        this.v = this.y;
    }

    public final View V1(RecyclerView.v vVar, RecyclerView.z zVar) {
        return b2(vVar, zVar, I() - 1, -1, zVar.b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView.z zVar) {
        super.W0(zVar);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    public int W1() {
        View viewY1 = Y1(I() - 1, -1, false, true);
        if (viewY1 == null) {
            return -1;
        }
        return f0(viewY1);
    }

    public View X1(int i2, int i3) {
        int i4;
        int i5;
        N1();
        if ((i3 > i2 ? (byte) 1 : i3 < i2 ? (byte) -1 : (byte) 0) == 0) {
            return H(i2);
        }
        if (this.u.g(H(i2)) < this.u.m()) {
            i4 = 16644;
            i5 = 16388;
        } else {
            i4 = 4161;
            i5 = 4097;
        }
        return this.s == 0 ? this.f608e.a(i2, i3, i4, i5) : this.f609f.a(i2, i3, i4, i5);
    }

    public View Y1(int i2, int i3, boolean z, boolean z2) {
        N1();
        int i4 = z ? 24579 : 320;
        int i5 = z2 ? 320 : 0;
        return this.s == 0 ? this.f608e.a(i2, i3, i4, i5) : this.f609f.a(i2, i3, i4, i5);
    }

    public final View Z1() {
        return this.x ? P1() : U1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.D = (SavedState) parcelable;
            q1();
        }
    }

    public final View a2() {
        return this.x ? U1() : P1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable b1() {
        if (this.D != null) {
            return new SavedState(this.D);
        }
        SavedState savedState = new SavedState();
        if (I() > 0) {
            N1();
            boolean z = this.v ^ this.x;
            savedState.s = z;
            if (z) {
                View viewG2 = g2();
                savedState.f554d = this.u.i() - this.u.d(viewG2);
                savedState.f553c = f0(viewG2);
            } else {
                View viewH2 = h2();
                savedState.f553c = f0(viewH2);
                savedState.f554d = this.u.g(viewH2) - this.u.m();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    public View b2(RecyclerView.v vVar, RecyclerView.z zVar, int i2, int i3, int i4) {
        N1();
        int iM = this.u.m();
        int i5 = this.u.i();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View viewH = H(i2);
            int iF0 = f0(viewH);
            if (iF0 >= 0 && iF0 < i4) {
                if (((RecyclerView.p) viewH.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewH;
                    }
                } else {
                    if (this.u.g(viewH) < i5 && this.u.d(viewH) >= iM) {
                        return viewH;
                    }
                    if (view == null) {
                        view = viewH;
                    }
                }
            }
            i2 += i6;
        }
        return view != null ? view : view2;
    }

    public final View c2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x ? Q1(vVar, zVar) : V1(vVar, zVar);
    }

    public final View d2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x ? V1(vVar, zVar) : Q1(vVar, zVar);
    }

    public final int e2(int i2, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i3;
        int i4 = this.u.i() - i2;
        if (i4 <= 0) {
            return 0;
        }
        int i5 = -v2(-i4, vVar, zVar);
        int i6 = i2 + i5;
        if (!z || (i3 = this.u.i() - i6) <= 0) {
            return i5;
        }
        this.u.r(i3);
        return i3 + i5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f(String str) {
        if (this.D == null) {
            super.f(str);
        }
    }

    public final int f2(int i2, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iM2 = i2 - this.u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i3 = -v2(iM2, vVar, zVar);
        int i4 = i2 + i3;
        if (!z || (iM = i4 - this.u.m()) <= 0) {
            return i3;
        }
        this.u.r(-iM);
        return i3 - iM;
    }

    public final View g2() {
        return H(this.x ? 0 : I() - 1);
    }

    public final View h2() {
        return H(this.x ? I() - 1 : 0);
    }

    @Deprecated
    public int i2(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean j() {
        return this.s == 0;
    }

    public int j2() {
        return this.s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.s == 1;
    }

    public boolean k2() {
        return X() == 1;
    }

    public boolean l2() {
        return this.z;
    }

    public void m2(RecyclerView.v vVar, RecyclerView.z zVar, c cVar, b bVar) {
        int i2;
        int i3;
        int i4;
        int iC0;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f561b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f569f == -1)) {
                c(viewD);
            } else {
                d(viewD, 0);
            }
        } else {
            if (this.x == (cVar.f569f == -1)) {
                a(viewD);
            } else {
                b(viewD, 0);
            }
        }
        y0(viewD, 0, 0);
        bVar.f560a = this.u.e(viewD);
        if (this.s == 1) {
            if (k2()) {
                iF = m0() - d0();
                iC0 = iF - this.u.f(viewD);
            } else {
                iC0 = c0();
                iF = this.u.f(viewD) + iC0;
            }
            if (cVar.f569f == -1) {
                int i5 = cVar.f565b;
                i4 = i5;
                i3 = iF;
                i2 = i5 - bVar.f560a;
            } else {
                int i6 = cVar.f565b;
                i2 = i6;
                i3 = iF;
                i4 = bVar.f560a + i6;
            }
        } else {
            int iE0 = e0();
            int iF2 = this.u.f(viewD) + iE0;
            if (cVar.f569f == -1) {
                int i7 = cVar.f565b;
                i3 = i7;
                i2 = iE0;
                i4 = iF2;
                iC0 = i7 - bVar.f560a;
            } else {
                int i8 = cVar.f565b;
                i2 = iE0;
                i3 = bVar.f560a + i8;
                i4 = iF2;
                iC0 = i8;
            }
        }
        x0(viewD, iC0, i2, i3, i4);
        if (pVar.c() || pVar.b()) {
            bVar.f562c = true;
        }
        bVar.f563d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void n(int i2, int i3, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.s != 0) {
            i2 = i3;
        }
        if (I() == 0 || i2 == 0) {
            return;
        }
        N1();
        C2(i2 > 0 ? 1 : -1, Math.abs(i2), true, zVar);
        H1(zVar, this.t, cVar);
    }

    public final void n2(RecyclerView.v vVar, RecyclerView.z zVar, int i2, int i3) {
        if (!zVar.g() || I() == 0 || zVar.e() || !F1()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int iF0 = f0(H(0));
        int iE = 0;
        int iE2 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView.c0 c0Var = listK.get(i4);
            if (!c0Var.v()) {
                if (((c0Var.m() < iF0) != this.x ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.u.e(c0Var.f580b);
                } else {
                    iE2 += this.u.e(c0Var.f580b);
                }
            }
        }
        this.t.l = listK;
        if (iE > 0) {
            F2(f0(h2()), i2);
            c cVar = this.t;
            cVar.f571h = iE;
            cVar.f566c = 0;
            cVar.a();
            O1(vVar, this.t, zVar, false);
        }
        if (iE2 > 0) {
            D2(f0(g2()), i3);
            c cVar2 = this.t;
            cVar2.f571h = iE2;
            cVar2.f566c = 0;
            cVar2.a();
            O1(vVar, this.t, zVar, false);
        }
        this.t.l = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i2, RecyclerView.o.c cVar) {
        boolean z;
        int i3;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.a()) {
            u2();
            z = this.x;
            i3 = this.A;
            if (i3 == -1) {
                i3 = z ? i2 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z = savedState2.s;
            i3 = savedState2.f553c;
        }
        int i4 = z ? -1 : 1;
        for (int i5 = 0; i5 < this.G && i3 >= 0 && i3 < i2; i5++) {
            cVar.a(i3, 0);
            i3 += i4;
        }
    }

    public void o2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int p(RecyclerView.z zVar) {
        return I1(zVar);
    }

    public final void p2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f564a || cVar.m) {
            return;
        }
        int i2 = cVar.f570g;
        int i3 = cVar.f572i;
        if (cVar.f569f == -1) {
            r2(vVar, i2, i3);
        } else {
            s2(vVar, i2, i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return J1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean q0() {
        return true;
    }

    public final void q2(RecyclerView.v vVar, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        if (i3 <= i2) {
            while (i2 > i3) {
                k1(i2, vVar);
                i2--;
            }
        } else {
            for (int i4 = i3 - 1; i4 >= i2; i4--) {
                k1(i4, vVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return K1(zVar);
    }

    public final void r2(RecyclerView.v vVar, int i2, int i3) {
        int I = I();
        if (i2 < 0) {
            return;
        }
        int iH = (this.u.h() - i2) + i3;
        if (this.x) {
            for (int i4 = 0; i4 < I; i4++) {
                View viewH = H(i4);
                if (this.u.g(viewH) < iH || this.u.q(viewH) < iH) {
                    q2(vVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = I - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View viewH2 = H(i6);
            if (this.u.g(viewH2) < iH || this.u.q(viewH2) < iH) {
                q2(vVar, i5, i6);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int s(RecyclerView.z zVar) {
        return I1(zVar);
    }

    public final void s2(RecyclerView.v vVar, int i2, int i3) {
        if (i2 < 0) {
            return;
        }
        int i4 = i2 - i3;
        int I = I();
        if (!this.x) {
            for (int i5 = 0; i5 < I; i5++) {
                View viewH = H(i5);
                if (this.u.d(viewH) > i4 || this.u.p(viewH) > i4) {
                    q2(vVar, 0, i5);
                    return;
                }
            }
            return;
        }
        int i6 = I - 1;
        for (int i7 = i6; i7 >= 0; i7--) {
            View viewH2 = H(i7);
            if (this.u.d(viewH2) > i4 || this.u.p(viewH2) > i4) {
                q2(vVar, i6, i7);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return J1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 1) {
            return 0;
        }
        return v2(i2, vVar, zVar);
    }

    public boolean t2() {
        return this.u.k() == 0 && this.u.h() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return K1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 0) {
            return 0;
        }
        return v2(i2, vVar, zVar);
    }

    public final void u2() {
        if (this.s == 1 || !k2()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    public int v2(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (I() == 0 || i2 == 0) {
            return 0;
        }
        N1();
        this.t.f564a = true;
        int i3 = i2 > 0 ? 1 : -1;
        int iAbs = Math.abs(i2);
        C2(i3, iAbs, true, zVar);
        c cVar = this.t;
        int iO1 = cVar.f570g + O1(vVar, cVar, zVar, false);
        if (iO1 < 0) {
            return 0;
        }
        if (iAbs > iO1) {
            i2 = i3 * iO1;
        }
        this.u.r(-i2);
        this.t.k = i2;
        return i2;
    }

    public void w2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        f(null);
        if (i2 != this.s || this.u == null) {
            h hVarB = h.b(this, i2);
            this.u = hVarB;
            this.E.f555a = hVarB;
            this.s = i2;
            q1();
        }
    }

    public void x2(boolean z) {
        f(null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        q1();
    }

    public void y2(boolean z) {
        f(null);
        if (this.y == z) {
            return;
        }
        this.y = z;
        q1();
    }

    public final boolean z2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (I() == 0) {
            return false;
        }
        View viewU = U();
        if (viewU != null && aVar.d(viewU, zVar)) {
            aVar.c(viewU, f0(viewU));
            return true;
        }
        if (this.v != this.y) {
            return false;
        }
        View viewC2 = aVar.f558d ? c2(vVar, zVar) : d2(vVar, zVar);
        if (viewC2 == null) {
            return false;
        }
        aVar.b(viewC2, f0(viewC2));
        if (!zVar.e() && F1()) {
            if (this.u.g(viewC2) >= this.u.i() || this.u.d(viewC2) < this.u.m()) {
                aVar.f557c = aVar.f558d ? this.u.i() : this.u.m();
            }
        }
        return true;
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
        RecyclerView.o.d dVarG0 = RecyclerView.o.g0(context, attributeSet, i2, i3);
        w2(dVarG0.f615a);
        x2(dVarG0.f617c);
        y2(dVarG0.f618d);
    }
}
