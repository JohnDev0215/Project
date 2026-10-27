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

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o implements RecyclerView.z.b {
    int A;
    int B;
    private boolean C;
    d D;
    final a E;
    private final b F;
    private int G;
    private int[] H;
    int s;
    private c t;
    k u;
    private boolean v;
    private boolean w;
    boolean x;
    private boolean y;
    private boolean z;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        k f1425a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1427c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1428d;
        boolean e;

        a() {
            e();
        }

        void a() {
            this.f1427c = this.f1428d ? this.f1425a.i() : this.f1425a.m();
        }

        public void b(View view, int i) {
            this.f1427c = this.f1428d ? this.f1425a.d(view) + this.f1425a.o() : this.f1425a.g(view);
            this.f1426b = i;
        }

        public void c(View view, int i) {
            int iO = this.f1425a.o();
            if (iO >= 0) {
                b(view, i);
                return;
            }
            this.f1426b = i;
            if (this.f1428d) {
                int i2 = (this.f1425a.i() - iO) - this.f1425a.d(view);
                this.f1427c = this.f1425a.i() - i2;
                if (i2 > 0) {
                    int iE = this.f1427c - this.f1425a.e(view);
                    int iM = this.f1425a.m();
                    int iMin = iE - (iM + Math.min(this.f1425a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1427c += Math.min(i2, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1425a.g(view);
            int iM2 = iG - this.f1425a.m();
            this.f1427c = iG;
            if (iM2 > 0) {
                int i3 = (this.f1425a.i() - Math.min(0, (this.f1425a.i() - iO) - this.f1425a.d(view))) - (iG + this.f1425a.e(view));
                if (i3 < 0) {
                    this.f1427c -= Math.min(iM2, -i3);
                }
            }
        }

        boolean d(View view, RecyclerView.a0 a0Var) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.w() && pVar.u() >= 0 && pVar.u() < a0Var.b();
        }

        void e() {
            this.f1426b = -1;
            this.f1427c = Integer.MIN_VALUE;
            this.f1428d = false;
            this.e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1426b + ", mCoordinate=" + this.f1427c + ", mLayoutFromEnd=" + this.f1428d + ", mValid=" + this.e + '}';
        }
    }

    protected static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1430b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1431c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1432d;

        protected b() {
        }

        void a() {
            this.f1429a = 0;
            this.f1430b = false;
            this.f1431c = false;
            this.f1432d = false;
        }
    }

    static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1434b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1435c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1436d;
        int e;
        int f;
        int g;
        int k;
        boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1433a = true;
        int h = 0;
        int i = 0;
        boolean j = false;
        List<RecyclerView.d0> l = null;

        c() {
        }

        private View e() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = this.l.get(i).f1454b;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.w() && this.f1436d == pVar.u()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public void a() {
            b(null);
        }

        public void b(View view) {
            View viewF = f(view);
            this.f1436d = viewF == null ? -1 : ((RecyclerView.p) viewF.getLayoutParams()).u();
        }

        boolean c(RecyclerView.a0 a0Var) {
            int i = this.f1436d;
            return i >= 0 && i < a0Var.b();
        }

        View d(RecyclerView.v vVar) {
            if (this.l != null) {
                return e();
            }
            View viewO = vVar.o(this.f1436d);
            this.f1436d += this.e;
            return viewO;
        }

        public View f(View view) {
            int iU;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).f1454b;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.w() && (iU = (pVar.u() - this.f1436d) * this.e) >= 0 && iU < i) {
                    view2 = view3;
                    if (iU == 0) {
                        break;
                    }
                    i = iU;
                }
            }
            return view2;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class d implements Parcelable {
        public static final Parcelable.Creator<d> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1437b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1438c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1439d;

        static class a implements Parcelable.Creator<d> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public d createFromParcel(Parcel parcel) {
                return new d(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public d[] newArray(int i) {
                return new d[i];
            }
        }

        public d() {
        }

        d(Parcel parcel) {
            this.f1437b = parcel.readInt();
            this.f1438c = parcel.readInt();
            this.f1439d = parcel.readInt() == 1;
        }

        public d(d dVar) {
            this.f1437b = dVar.f1437b;
            this.f1438c = dVar.f1438c;
            this.f1439d = dVar.f1439d;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        boolean u() {
            return this.f1437b >= 0;
        }

        void v() {
            this.f1437b = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1437b);
            parcel.writeInt(this.f1438c);
            parcel.writeInt(this.f1439d ? 1 : 0);
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
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
        Y2(i);
        Z2(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
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
        RecyclerView.o.d dVarA0 = RecyclerView.o.A0(context, attributeSet, i, i2);
        Y2(dVarA0.f1476a);
        Z2(dVarA0.f1478c);
        a3(dVarA0.f1479d);
    }

    private View B2() {
        return this.x ? r2() : w2();
    }

    private View C2() {
        return this.x ? w2() : r2();
    }

    private View E2(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return this.x ? s2(vVar, a0Var) : x2(vVar, a0Var);
    }

    private View F2(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return this.x ? x2(vVar, a0Var) : s2(vVar, a0Var);
    }

    private int G2(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int i2;
        int i3 = this.u.i() - i;
        if (i3 <= 0) {
            return 0;
        }
        int i4 = -X2(-i3, vVar, a0Var);
        int i5 = i + i4;
        if (!z || (i2 = this.u.i() - i5) <= 0) {
            return i4;
        }
        this.u.r(i2);
        return i2 + i4;
    }

    private int H2(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int iM;
        int iM2 = i - this.u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i2 = -X2(iM2, vVar, a0Var);
        int i3 = i + i2;
        if (!z || (iM = i3 - this.u.m()) <= 0) {
            return i2;
        }
        this.u.r(-iM);
        return i2 - iM;
    }

    private View I2() {
        return e0(this.x ? 0 : f0() - 1);
    }

    private View J2() {
        return e0(this.x ? f0() - 1 : 0);
    }

    private void P2(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2) {
        if (!a0Var.g() || f0() == 0 || a0Var.e() || !h2()) {
            return;
        }
        List<RecyclerView.d0> listK = vVar.k();
        int size = listK.size();
        int iZ0 = z0(e0(0));
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView.d0 d0Var = listK.get(i5);
            if (!d0Var.v()) {
                byte b2 = (d0Var.m() < iZ0) != this.x ? (byte) -1 : (byte) 1;
                int iE = this.u.e(d0Var.f1454b);
                if (b2 == -1) {
                    i3 += iE;
                } else {
                    i4 += iE;
                }
            }
        }
        this.t.l = listK;
        if (i3 > 0) {
            h3(z0(J2()), i);
            c cVar = this.t;
            cVar.h = i3;
            cVar.f1435c = 0;
            cVar.a();
            q2(vVar, this.t, a0Var, false);
        }
        if (i4 > 0) {
            f3(z0(I2()), i2);
            c cVar2 = this.t;
            cVar2.h = i4;
            cVar2.f1435c = 0;
            cVar2.a();
            q2(vVar, this.t, a0Var, false);
        }
        this.t.l = null;
    }

    private void R2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1433a || cVar.m) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f == -1) {
            T2(vVar, i, i2);
        } else {
            U2(vVar, i, i2);
        }
    }

    private void S2(RecyclerView.v vVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                I1(i, vVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                I1(i3, vVar);
            }
        }
    }

    private void T2(RecyclerView.v vVar, int i, int i2) {
        int iF0 = f0();
        if (i < 0) {
            return;
        }
        int iH = (this.u.h() - i) + i2;
        if (this.x) {
            for (int i3 = 0; i3 < iF0; i3++) {
                View viewE0 = e0(i3);
                if (this.u.g(viewE0) < iH || this.u.q(viewE0) < iH) {
                    S2(vVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = iF0 - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View viewE02 = e0(i5);
            if (this.u.g(viewE02) < iH || this.u.q(viewE02) < iH) {
                S2(vVar, i4, i5);
                return;
            }
        }
    }

    private void U2(RecyclerView.v vVar, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int iF0 = f0();
        if (!this.x) {
            for (int i4 = 0; i4 < iF0; i4++) {
                View viewE0 = e0(i4);
                if (this.u.d(viewE0) > i3 || this.u.p(viewE0) > i3) {
                    S2(vVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = iF0 - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View viewE02 = e0(i6);
            if (this.u.d(viewE02) > i3 || this.u.p(viewE02) > i3) {
                S2(vVar, i5, i6);
                return;
            }
        }
    }

    private void W2() {
        this.x = (this.s == 1 || !M2()) ? this.w : !this.w;
    }

    private boolean b3(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar) {
        if (f0() == 0) {
            return false;
        }
        View viewR0 = r0();
        if (viewR0 != null && aVar.d(viewR0, a0Var)) {
            aVar.c(viewR0, z0(viewR0));
            return true;
        }
        if (this.v != this.y) {
            return false;
        }
        View viewE2 = aVar.f1428d ? E2(vVar, a0Var) : F2(vVar, a0Var);
        if (viewE2 == null) {
            return false;
        }
        aVar.b(viewE2, z0(viewE2));
        if (!a0Var.e() && h2()) {
            if (this.u.g(viewE2) >= this.u.i() || this.u.d(viewE2) < this.u.m()) {
                aVar.f1427c = aVar.f1428d ? this.u.i() : this.u.m();
            }
        }
        return true;
    }

    private boolean c3(RecyclerView.a0 a0Var, a aVar) {
        int i;
        if (!a0Var.e() && (i = this.A) != -1) {
            if (i >= 0 && i < a0Var.b()) {
                aVar.f1426b = this.A;
                d dVar = this.D;
                if (dVar != null && dVar.u()) {
                    boolean z = this.D.f1439d;
                    aVar.f1428d = z;
                    aVar.f1427c = z ? this.u.i() - this.D.f1438c : this.u.m() + this.D.f1438c;
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z2 = this.x;
                    aVar.f1428d = z2;
                    aVar.f1427c = z2 ? this.u.i() - this.B : this.u.m() + this.B;
                    return true;
                }
                View viewY = Y(this.A);
                if (viewY == null) {
                    if (f0() > 0) {
                        aVar.f1428d = (this.A < z0(e0(0))) == this.x;
                    }
                    aVar.a();
                } else {
                    if (this.u.e(viewY) > this.u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.u.g(viewY) - this.u.m() < 0) {
                        aVar.f1427c = this.u.m();
                        aVar.f1428d = false;
                        return true;
                    }
                    if (this.u.i() - this.u.d(viewY) < 0) {
                        aVar.f1427c = this.u.i();
                        aVar.f1428d = true;
                        return true;
                    }
                    aVar.f1427c = aVar.f1428d ? this.u.d(viewY) + this.u.o() : this.u.g(viewY);
                }
                return true;
            }
            this.A = -1;
            this.B = Integer.MIN_VALUE;
        }
        return false;
    }

    private void d3(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar) {
        if (c3(a0Var, aVar) || b3(vVar, a0Var, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1426b = this.y ? a0Var.b() - 1 : 0;
    }

    private void e3(int i, int i2, boolean z, RecyclerView.a0 a0Var) {
        int iM;
        this.t.m = V2();
        this.t.f = i;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        i2(a0Var, iArr);
        int iMax = Math.max(0, this.H[0]);
        int iMax2 = Math.max(0, this.H[1]);
        boolean z2 = i == 1;
        c cVar = this.t;
        int i3 = z2 ? iMax2 : iMax;
        cVar.h = i3;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.i = iMax;
        if (z2) {
            cVar.h = i3 + this.u.j();
            View viewI2 = I2();
            c cVar2 = this.t;
            cVar2.e = this.x ? -1 : 1;
            int iZ0 = z0(viewI2);
            c cVar3 = this.t;
            cVar2.f1436d = iZ0 + cVar3.e;
            cVar3.f1434b = this.u.d(viewI2);
            iM = this.u.d(viewI2) - this.u.i();
        } else {
            View viewJ2 = J2();
            this.t.h += this.u.m();
            c cVar4 = this.t;
            cVar4.e = this.x ? 1 : -1;
            int iZ02 = z0(viewJ2);
            c cVar5 = this.t;
            cVar4.f1436d = iZ02 + cVar5.e;
            cVar5.f1434b = this.u.g(viewJ2);
            iM = (-this.u.g(viewJ2)) + this.u.m();
        }
        c cVar6 = this.t;
        cVar6.f1435c = i2;
        if (z) {
            cVar6.f1435c = i2 - iM;
        }
        cVar6.g = iM;
    }

    private void f3(int i, int i2) {
        this.t.f1435c = this.u.i() - i2;
        c cVar = this.t;
        cVar.e = this.x ? -1 : 1;
        cVar.f1436d = i;
        cVar.f = 1;
        cVar.f1434b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private void g3(a aVar) {
        f3(aVar.f1426b, aVar.f1427c);
    }

    private void h3(int i, int i2) {
        this.t.f1435c = i2 - this.u.m();
        c cVar = this.t;
        cVar.f1436d = i;
        cVar.e = this.x ? 1 : -1;
        cVar.f = -1;
        cVar.f1434b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private void i3(a aVar) {
        h3(aVar.f1426b, aVar.f1427c);
    }

    private int k2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        p2();
        return m.a(a0Var, this.u, u2(!this.z, true), t2(!this.z, true), this, this.z);
    }

    private int l2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        p2();
        return m.b(a0Var, this.u, u2(!this.z, true), t2(!this.z, true), this, this.z, this.x);
    }

    private int m2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        p2();
        return m.c(a0Var, this.u, u2(!this.z, true), t2(!this.z, true), this, this.z);
    }

    private View r2() {
        return z2(0, f0());
    }

    private View s2(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return D2(vVar, a0Var, 0, f0(), a0Var.b());
    }

    private View w2() {
        return z2(f0() - 1, -1);
    }

    private View x2(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return D2(vVar, a0Var, f0() - 1, -1, a0Var.b());
    }

    View A2(int i, int i2, boolean z, boolean z2) {
        p2();
        return (this.s == 0 ? this.e : this.f).a(i, i2, z ? 24579 : 320, z2 ? 320 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C(String str) {
        if (this.D == null) {
            super.C(str);
        }
    }

    View D2(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2, int i3) {
        p2();
        int iM = this.u.m();
        int i4 = this.u.i();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewE0 = e0(i);
            int iZ0 = z0(viewE0);
            if (iZ0 >= 0 && iZ0 < i3) {
                if (((RecyclerView.p) viewE0.getLayoutParams()).w()) {
                    if (view2 == null) {
                        view2 = viewE0;
                    }
                } else {
                    if (this.u.g(viewE0) < i4 && this.u.d(viewE0) >= iM) {
                        return viewE0;
                    }
                    if (view == null) {
                        view = viewE0;
                    }
                }
            }
            i += i5;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean G() {
        return this.s == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean H() {
        return this.s == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void K(int i, int i2, RecyclerView.a0 a0Var, RecyclerView.o.c cVar) {
        if (this.s != 0) {
            i = i2;
        }
        if (f0() == 0 || i == 0) {
            return;
        }
        p2();
        e3(i > 0 ? 1 : -1, Math.abs(i), true, a0Var);
        j2(a0Var, this.t, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean K0() {
        return true;
    }

    @Deprecated
    protected int K2(RecyclerView.a0 a0Var) {
        if (a0Var.d()) {
            return this.u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void L(int i, RecyclerView.o.c cVar) {
        boolean z;
        int i2;
        d dVar = this.D;
        if (dVar == null || !dVar.u()) {
            W2();
            z = this.x;
            i2 = this.A;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            d dVar2 = this.D;
            z = dVar2.f1439d;
            i2 = dVar2.f1437b;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.G && i2 >= 0 && i2 < i; i4++) {
            cVar.a(i2, 0);
            i2 += i3;
        }
    }

    public int L2() {
        return this.s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int M(RecyclerView.a0 a0Var) {
        return k2(a0Var);
    }

    protected boolean M2() {
        return v0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.a0 a0Var) {
        return l2(a0Var);
    }

    public boolean N2() {
        return this.z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int O(RecyclerView.a0 a0Var) {
        return m2(a0Var);
    }

    void O2(RecyclerView.v vVar, RecyclerView.a0 a0Var, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int iL;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f1430b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f == -1)) {
                z(viewD);
            } else {
                A(viewD, 0);
            }
        } else {
            if (this.x == (cVar.f == -1)) {
                x(viewD);
            } else {
                y(viewD, 0);
            }
        }
        U0(viewD, 0, 0);
        bVar.f1429a = this.u.e(viewD);
        if (this.s == 1) {
            if (M2()) {
                iF = G0() - b();
                iL = iF - this.u.f(viewD);
            } else {
                iL = l();
                iF = this.u.f(viewD) + iL;
            }
            int i4 = cVar.f;
            int i5 = cVar.f1434b;
            if (i4 == -1) {
                i3 = i5;
                i2 = iF;
                i = i5 - bVar.f1429a;
            } else {
                i = i5;
                i2 = iF;
                i3 = bVar.f1429a + i5;
            }
        } else {
            int iJ = j();
            int iF2 = this.u.f(viewD) + iJ;
            int i6 = cVar.f;
            int i7 = cVar.f1434b;
            if (i6 == -1) {
                i2 = i7;
                i = iJ;
                i3 = iF2;
                iL = i7 - bVar.f1429a;
            } else {
                i = iJ;
                i2 = bVar.f1429a + i7;
                i3 = iF2;
                iL = i7;
            }
        }
        T0(viewD, iL, i, i2, i3);
        if (pVar.w() || pVar.v()) {
            bVar.f1431c = true;
        }
        bVar.f1432d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int P(RecyclerView.a0 a0Var) {
        return k2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int Q(RecyclerView.a0 a0Var) {
        return l2(a0Var);
    }

    void Q2(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int R(RecyclerView.a0 a0Var) {
        return m2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int R1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 1) {
            return 0;
        }
        return X2(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S1(int i) {
        this.A = i;
        this.B = Integer.MIN_VALUE;
        d dVar = this.D;
        if (dVar != null) {
            dVar.v();
        }
        O1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int T1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 0) {
            return 0;
        }
        return X2(i, vVar, a0Var);
    }

    boolean V2() {
        return this.u.k() == 0 && this.u.h() == 0;
    }

    int X2(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (f0() == 0 || i == 0) {
            return 0;
        }
        p2();
        this.t.f1433a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        e3(i2, iAbs, true, a0Var);
        c cVar = this.t;
        int iQ2 = cVar.g + q2(vVar, cVar, a0Var, false);
        if (iQ2 < 0) {
            return 0;
        }
        if (iAbs > iQ2) {
            i = i2 * iQ2;
        }
        this.u.r(-i);
        this.t.k = i;
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View Y(int i) {
        int iF0 = f0();
        if (iF0 == 0) {
            return null;
        }
        int iZ0 = i - z0(e0(0));
        if (iZ0 >= 0 && iZ0 < iF0) {
            View viewE0 = e0(iZ0);
            if (z0(viewE0) == i) {
                return viewE0;
            }
        }
        return super.Y(i);
    }

    public void Y2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        C(null);
        if (i != this.s || this.u == null) {
            k kVarB = k.b(this, i);
            this.u = kVarB;
            this.E.f1425a = kVarB;
            this.s = i;
            O1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p Z() {
        return new RecyclerView.p(-2, -2);
    }

    public void Z2(boolean z) {
        C(null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        O1();
    }

    public void a3(boolean z) {
        C(null);
        if (this.y == z) {
            return;
        }
        this.y = z;
        O1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.c1(recyclerView, vVar);
        if (this.C) {
            F1(vVar);
            vVar.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    boolean c2() {
        return (t0() == 1073741824 || H0() == 1073741824 || !I0()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View d1(View view, int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int iN2;
        W2();
        if (f0() == 0 || (iN2 = n2(i)) == Integer.MIN_VALUE) {
            return null;
        }
        p2();
        e3(iN2, (int) (this.u.n() * 0.33333334f), false, a0Var);
        c cVar = this.t;
        cVar.g = Integer.MIN_VALUE;
        cVar.f1433a = false;
        q2(vVar, cVar, a0Var, true);
        View viewC2 = iN2 == -1 ? C2() : B2();
        View viewJ2 = iN2 == -1 ? J2() : I2();
        if (!viewJ2.hasFocusable()) {
            return viewC2;
        }
        if (viewC2 == null) {
            return null;
        }
        return viewJ2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e1(AccessibilityEvent accessibilityEvent) {
        super.e1(accessibilityEvent);
        if (f0() > 0) {
            accessibilityEvent.setFromIndex(v2());
            accessibilityEvent.setToIndex(y2());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e2(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        h hVar = new h(recyclerView.getContext());
        hVar.p(i);
        f2(hVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean h2() {
        return this.D == null && this.v == this.y;
    }

    protected void i2(RecyclerView.a0 a0Var, int[] iArr) {
        int i;
        int iK2 = K2(a0Var);
        if (this.t.f == -1) {
            i = 0;
        } else {
            i = iK2;
            iK2 = 0;
        }
        iArr[0] = iK2;
        iArr[1] = i;
    }

    void j2(RecyclerView.a0 a0Var, c cVar, RecyclerView.o.c cVar2) {
        int i = cVar.f1436d;
        if (i < 0 || i >= a0Var.b()) {
            return;
        }
        cVar2.a(i, Math.max(0, cVar.g));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF n(int i) {
        if (f0() == 0) {
            return null;
        }
        int i2 = (i < z0(e0(0))) != this.x ? -1 : 1;
        return this.s == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    int n2(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && M2()) ? -1 : 1 : (this.s != 1 && M2()) ? 1 : -1;
    }

    c o2() {
        return new c();
    }

    void p2() {
        if (this.t == null) {
            this.t = o2();
        }
    }

    int q2(RecyclerView.v vVar, c cVar, RecyclerView.a0 a0Var, boolean z) {
        int i = cVar.f1435c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            R2(vVar, cVar);
        }
        int i3 = cVar.f1435c + cVar.h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.c(a0Var)) {
                break;
            }
            bVar.a();
            O2(vVar, a0Var, cVar, bVar);
            if (!bVar.f1430b) {
                cVar.f1434b += bVar.f1429a * cVar.f;
                if (!bVar.f1431c || cVar.l != null || !a0Var.e()) {
                    int i4 = cVar.f1435c;
                    int i5 = bVar.f1429a;
                    cVar.f1435c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.f1429a;
                    cVar.g = i7;
                    int i8 = cVar.f1435c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    R2(vVar, cVar);
                }
                if (z && bVar.f1432d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.f1435c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void r1(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int i;
        int i2;
        int i3;
        int i4;
        int iG2;
        int i5;
        View viewY;
        int iG;
        int i6;
        int i7 = -1;
        if (!(this.D == null && this.A == -1) && a0Var.b() == 0) {
            F1(vVar);
            return;
        }
        d dVar = this.D;
        if (dVar != null && dVar.u()) {
            this.A = this.D.f1437b;
        }
        p2();
        this.t.f1433a = false;
        W2();
        View viewR0 = r0();
        a aVar = this.E;
        if (!aVar.e || this.A != -1 || this.D != null) {
            aVar.e();
            a aVar2 = this.E;
            aVar2.f1428d = this.x ^ this.y;
            d3(vVar, a0Var, aVar2);
            this.E.e = true;
        } else if (viewR0 != null && (this.u.g(viewR0) >= this.u.i() || this.u.d(viewR0) <= this.u.m())) {
            this.E.c(viewR0, z0(viewR0));
        }
        c cVar = this.t;
        cVar.f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        i2(a0Var, iArr);
        int iMax = Math.max(0, this.H[0]) + this.u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.u.j();
        if (a0Var.e() && (i5 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewY = Y(i5)) != null) {
            if (this.x) {
                i6 = this.u.i() - this.u.d(viewY);
                iG = this.B;
            } else {
                iG = this.u.g(viewY) - this.u.m();
                i6 = this.B;
            }
            int i8 = i6 - iG;
            if (i8 > 0) {
                iMax += i8;
            } else {
                iMax2 -= i8;
            }
        }
        a aVar3 = this.E;
        if (!aVar3.f1428d ? !this.x : this.x) {
            i7 = 1;
        }
        Q2(vVar, a0Var, aVar3, i7);
        S(vVar);
        this.t.m = V2();
        this.t.j = a0Var.e();
        this.t.i = 0;
        a aVar4 = this.E;
        if (aVar4.f1428d) {
            i3(aVar4);
            c cVar2 = this.t;
            cVar2.h = iMax;
            q2(vVar, cVar2, a0Var, false);
            c cVar3 = this.t;
            i2 = cVar3.f1434b;
            int i9 = cVar3.f1436d;
            int i10 = cVar3.f1435c;
            if (i10 > 0) {
                iMax2 += i10;
            }
            g3(this.E);
            c cVar4 = this.t;
            cVar4.h = iMax2;
            cVar4.f1436d += cVar4.e;
            q2(vVar, cVar4, a0Var, false);
            c cVar5 = this.t;
            i = cVar5.f1434b;
            int i11 = cVar5.f1435c;
            if (i11 > 0) {
                h3(i9, i2);
                c cVar6 = this.t;
                cVar6.h = i11;
                q2(vVar, cVar6, a0Var, false);
                i2 = this.t.f1434b;
            }
        } else {
            g3(aVar4);
            c cVar7 = this.t;
            cVar7.h = iMax2;
            q2(vVar, cVar7, a0Var, false);
            c cVar8 = this.t;
            i = cVar8.f1434b;
            int i12 = cVar8.f1436d;
            int i13 = cVar8.f1435c;
            if (i13 > 0) {
                iMax += i13;
            }
            i3(this.E);
            c cVar9 = this.t;
            cVar9.h = iMax;
            cVar9.f1436d += cVar9.e;
            q2(vVar, cVar9, a0Var, false);
            c cVar10 = this.t;
            i2 = cVar10.f1434b;
            int i14 = cVar10.f1435c;
            if (i14 > 0) {
                f3(i12, i);
                c cVar11 = this.t;
                cVar11.h = i14;
                q2(vVar, cVar11, a0Var, false);
                i = this.t.f1434b;
            }
        }
        if (f0() > 0) {
            if (this.x ^ this.y) {
                int iG22 = G2(i, vVar, a0Var, true);
                i3 = i2 + iG22;
                i4 = i + iG22;
                iG2 = H2(i3, vVar, a0Var, false);
            } else {
                int iH2 = H2(i2, vVar, a0Var, true);
                i3 = i2 + iH2;
                i4 = i + iH2;
                iG2 = G2(i4, vVar, a0Var, false);
            }
            i2 = i3 + iG2;
            i = i4 + iG2;
        }
        P2(vVar, a0Var, i2, i);
        if (a0Var.e()) {
            this.E.e();
        } else {
            this.u.s();
        }
        this.v = this.y;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void s1(RecyclerView.a0 a0Var) {
        super.s1(a0Var);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    View t2(boolean z, boolean z2) {
        int iF0;
        int iF02;
        if (this.x) {
            iF0 = 0;
            iF02 = f0();
        } else {
            iF0 = f0() - 1;
            iF02 = -1;
        }
        return A2(iF0, iF02, z, z2);
    }

    View u2(boolean z, boolean z2) {
        int iF0;
        int iF02;
        if (this.x) {
            iF0 = f0() - 1;
            iF02 = -1;
        } else {
            iF0 = 0;
            iF02 = f0();
        }
        return A2(iF0, iF02, z, z2);
    }

    public int v2() {
        View viewA2 = A2(0, f0(), false, true);
        if (viewA2 == null) {
            return -1;
        }
        return z0(viewA2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void w1(Parcelable parcelable) {
        if (parcelable instanceof d) {
            this.D = (d) parcelable;
            O1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable x1() {
        if (this.D != null) {
            return new d(this.D);
        }
        d dVar = new d();
        if (f0() > 0) {
            p2();
            boolean z = this.v ^ this.x;
            dVar.f1439d = z;
            if (z) {
                View viewI2 = I2();
                dVar.f1438c = this.u.i() - this.u.d(viewI2);
                dVar.f1437b = z0(viewI2);
            } else {
                View viewJ2 = J2();
                dVar.f1437b = z0(viewJ2);
                dVar.f1438c = this.u.g(viewJ2) - this.u.m();
            }
        } else {
            dVar.v();
        }
        return dVar;
    }

    public int y2() {
        View viewA2 = A2(f0() - 1, -1, false, true);
        if (viewA2 == null) {
            return -1;
        }
        return z0(viewA2);
    }

    View z2(int i, int i2) {
        int i3;
        int i4;
        p2();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return e0(i);
        }
        if (this.u.g(e0(i)) < this.u.m()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return (this.s == 0 ? this.e : this.f).a(i, i2, i3, i4);
    }
}
