package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class LinearLayoutManager extends RecyclerView.m {
    int A;
    int B;
    private boolean C;
    SavedState D;
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

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1081c;
        int d;
        boolean e;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Parcelable.Creator<SavedState> {
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

        public SavedState() {
        }

        boolean a() {
            return this.f1081c >= 0;
        }

        void b() {
            this.f1081c = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1081c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f1081c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1081c = savedState.f1081c;
            this.d = savedState.d;
            this.e = savedState.e;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        k f1082a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1083b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1084c;
        boolean d;
        boolean e;

        a() {
            e();
        }

        void a() {
            this.f1084c = this.d ? this.f1082a.i() : this.f1082a.m();
        }

        public void b(View view, int i) {
            if (this.d) {
                this.f1084c = this.f1082a.d(view) + this.f1082a.o();
            } else {
                this.f1084c = this.f1082a.g(view);
            }
            this.f1083b = i;
        }

        public void c(View view, int i) {
            int iO = this.f1082a.o();
            if (iO >= 0) {
                b(view, i);
                return;
            }
            this.f1083b = i;
            if (this.d) {
                int i2 = (this.f1082a.i() - iO) - this.f1082a.d(view);
                this.f1084c = this.f1082a.i() - i2;
                if (i2 > 0) {
                    int iE = this.f1084c - this.f1082a.e(view);
                    int iM = this.f1082a.m();
                    int iMin = iE - (iM + Math.min(this.f1082a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1084c += Math.min(i2, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1082a.g(view);
            int iM2 = iG - this.f1082a.m();
            this.f1084c = iG;
            if (iM2 > 0) {
                int i3 = (this.f1082a.i() - Math.min(0, (this.f1082a.i() - iO) - this.f1082a.d(view))) - (iG + this.f1082a.e(view));
                if (i3 < 0) {
                    this.f1084c -= Math.min(iM2, -i3);
                }
            }
        }

        boolean d(View view, RecyclerView.x xVar) {
            RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
            return !nVar.c() && nVar.a() >= 0 && nVar.a() < xVar.b();
        }

        void e() {
            this.f1083b = -1;
            this.f1084c = Integer.MIN_VALUE;
            this.d = false;
            this.e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1083b + ", mCoordinate=" + this.f1084c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    protected static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1085a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1086b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1087c;
        public boolean d;

        protected b() {
        }

        void a() {
            this.f1085a = 0;
            this.f1086b = false;
            this.f1087c = false;
            this.d = false;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1089b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1090c;
        int d;
        int e;
        int f;
        int g;
        boolean j;
        int k;
        boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1088a = true;
        int h = 0;
        int i = 0;
        List<RecyclerView.A> l = null;

        c() {
        }

        private View e() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = this.l.get(i).f1092a;
                RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
                if (!nVar.c() && this.d == nVar.a()) {
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
            if (viewF == null) {
                this.d = -1;
            } else {
                this.d = ((RecyclerView.n) viewF.getLayoutParams()).a();
            }
        }

        boolean c(RecyclerView.x xVar) {
            int i = this.d;
            return i >= 0 && i < xVar.b();
        }

        View d(RecyclerView.t tVar) {
            if (this.l != null) {
                return e();
            }
            View viewO = tVar.o(this.d);
            this.d += this.e;
            return viewO;
        }

        public View f(View view) {
            int iA;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).f1092a;
                RecyclerView.n nVar = (RecyclerView.n) view3.getLayoutParams();
                if (view3 != view && !nVar.c() && (iA = (nVar.a() - this.d) * this.e) >= 0 && iA < i) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i = iA;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private void A2(a aVar) {
        z2(aVar.f1083b, aVar.f1084c);
    }

    private void B2(int i, int i2) {
        this.t.f1090c = i2 - this.u.m();
        c cVar = this.t;
        cVar.d = i;
        cVar.e = this.x ? 1 : -1;
        c cVar2 = this.t;
        cVar2.f = -1;
        cVar2.f1089b = i2;
        cVar2.g = Integer.MIN_VALUE;
    }

    private void C2(a aVar) {
        B2(aVar.f1083b, aVar.f1084c);
    }

    private int I1(RecyclerView.x xVar) {
        if (I() == 0) {
            return 0;
        }
        N1();
        return m.a(xVar, this.u, R1(!this.z, true), Q1(!this.z, true), this, this.z);
    }

    private int J1(RecyclerView.x xVar) {
        if (I() == 0) {
            return 0;
        }
        N1();
        return m.b(xVar, this.u, R1(!this.z, true), Q1(!this.z, true), this, this.z, this.x);
    }

    private int K1(RecyclerView.x xVar) {
        if (I() == 0) {
            return 0;
        }
        N1();
        return m.c(xVar, this.u, R1(!this.z, true), Q1(!this.z, true), this, this.z);
    }

    private View P1() {
        return V1(0, I());
    }

    private View T1() {
        return V1(I() - 1, -1);
    }

    private View X1() {
        return this.x ? P1() : T1();
    }

    private View Y1() {
        return this.x ? T1() : P1();
    }

    private int a2(int i, RecyclerView.t tVar, RecyclerView.x xVar, boolean z) {
        int i2;
        int i3 = this.u.i() - i;
        if (i3 <= 0) {
            return 0;
        }
        int i4 = -r2(-i3, tVar, xVar);
        int i5 = i + i4;
        if (!z || (i2 = this.u.i() - i5) <= 0) {
            return i4;
        }
        this.u.r(i2);
        return i2 + i4;
    }

    private int b2(int i, RecyclerView.t tVar, RecyclerView.x xVar, boolean z) {
        int iM;
        int iM2 = i - this.u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i2 = -r2(iM2, tVar, xVar);
        int i3 = i + i2;
        if (!z || (iM = i3 - this.u.m()) <= 0) {
            return i2;
        }
        this.u.r(-iM);
        return i2 - iM;
    }

    private View c2() {
        return H(this.x ? 0 : I() - 1);
    }

    private View d2() {
        return H(this.x ? I() - 1 : 0);
    }

    private void j2(RecyclerView.t tVar, RecyclerView.x xVar, int i, int i2) {
        if (!xVar.g() || I() == 0 || xVar.e() || !F1()) {
            return;
        }
        List<RecyclerView.A> listK = tVar.k();
        int size = listK.size();
        int iF0 = f0(H(0));
        int iE = 0;
        int iE2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView.A a2 = listK.get(i3);
            if (!a2.v()) {
                if (((a2.m() < iF0) != this.x ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.u.e(a2.f1092a);
                } else {
                    iE2 += this.u.e(a2.f1092a);
                }
            }
        }
        this.t.l = listK;
        if (iE > 0) {
            B2(f0(d2()), i);
            c cVar = this.t;
            cVar.h = iE;
            cVar.f1090c = 0;
            cVar.a();
            O1(tVar, this.t, xVar, false);
        }
        if (iE2 > 0) {
            z2(f0(c2()), i2);
            c cVar2 = this.t;
            cVar2.h = iE2;
            cVar2.f1090c = 0;
            cVar2.a();
            O1(tVar, this.t, xVar, false);
        }
        this.t.l = null;
    }

    private void l2(RecyclerView.t tVar, c cVar) {
        if (!cVar.f1088a || cVar.m) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f == -1) {
            n2(tVar, i, i2);
        } else {
            o2(tVar, i, i2);
        }
    }

    private void m2(RecyclerView.t tVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                k1(i, tVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                k1(i3, tVar);
            }
        }
    }

    private void n2(RecyclerView.t tVar, int i, int i2) {
        int I = I();
        if (i < 0) {
            return;
        }
        int iH = (this.u.h() - i) + i2;
        if (this.x) {
            for (int i3 = 0; i3 < I; i3++) {
                View viewH = H(i3);
                if (this.u.g(viewH) < iH || this.u.q(viewH) < iH) {
                    m2(tVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = I - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View viewH2 = H(i5);
            if (this.u.g(viewH2) < iH || this.u.q(viewH2) < iH) {
                m2(tVar, i4, i5);
                return;
            }
        }
    }

    private void o2(RecyclerView.t tVar, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int I = I();
        if (!this.x) {
            for (int i4 = 0; i4 < I; i4++) {
                View viewH = H(i4);
                if (this.u.d(viewH) > i3 || this.u.p(viewH) > i3) {
                    m2(tVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = I - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View viewH2 = H(i6);
            if (this.u.d(viewH2) > i3 || this.u.p(viewH2) > i3) {
                m2(tVar, i5, i6);
                return;
            }
        }
    }

    private void q2() {
        if (this.s == 1 || !g2()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    private boolean v2(RecyclerView.t tVar, RecyclerView.x xVar, a aVar) {
        View viewZ1;
        boolean z = false;
        if (I() == 0) {
            return false;
        }
        View viewU = U();
        if (viewU != null && aVar.d(viewU, xVar)) {
            aVar.c(viewU, f0(viewU));
            return true;
        }
        boolean z2 = this.v;
        boolean z3 = this.y;
        if (z2 != z3 || (viewZ1 = Z1(tVar, xVar, aVar.d, z3)) == null) {
            return false;
        }
        aVar.b(viewZ1, f0(viewZ1));
        if (!xVar.e() && F1()) {
            int iG = this.u.g(viewZ1);
            int iD = this.u.d(viewZ1);
            int iM = this.u.m();
            int i = this.u.i();
            boolean z4 = iD <= iM && iG < iM;
            if (iG >= i && iD > i) {
                z = true;
            }
            if (z4 || z) {
                if (aVar.d) {
                    iM = i;
                }
                aVar.f1084c = iM;
            }
        }
        return true;
    }

    private boolean w2(RecyclerView.x xVar, a aVar) {
        int i;
        if (!xVar.e() && (i = this.A) != -1) {
            if (i >= 0 && i < xVar.b()) {
                aVar.f1083b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.a()) {
                    boolean z = this.D.e;
                    aVar.d = z;
                    if (z) {
                        aVar.f1084c = this.u.i() - this.D.d;
                    } else {
                        aVar.f1084c = this.u.m() + this.D.d;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z2 = this.x;
                    aVar.d = z2;
                    if (z2) {
                        aVar.f1084c = this.u.i() - this.B;
                    } else {
                        aVar.f1084c = this.u.m() + this.B;
                    }
                    return true;
                }
                View viewB = B(this.A);
                if (viewB == null) {
                    if (I() > 0) {
                        aVar.d = (this.A < f0(H(0))) == this.x;
                    }
                    aVar.a();
                } else {
                    if (this.u.e(viewB) > this.u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.u.g(viewB) - this.u.m() < 0) {
                        aVar.f1084c = this.u.m();
                        aVar.d = false;
                        return true;
                    }
                    if (this.u.i() - this.u.d(viewB) < 0) {
                        aVar.f1084c = this.u.i();
                        aVar.d = true;
                        return true;
                    }
                    aVar.f1084c = aVar.d ? this.u.d(viewB) + this.u.o() : this.u.g(viewB);
                }
                return true;
            }
            this.A = -1;
            this.B = Integer.MIN_VALUE;
        }
        return false;
    }

    private void x2(RecyclerView.t tVar, RecyclerView.x xVar, a aVar) {
        if (w2(xVar, aVar) || v2(tVar, xVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1083b = this.y ? xVar.b() - 1 : 0;
    }

    private void y2(int i, int i2, boolean z, RecyclerView.x xVar) {
        int iM;
        this.t.m = p2();
        this.t.f = i;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        G1(xVar, iArr);
        int iMax = Math.max(0, this.H[0]);
        int iMax2 = Math.max(0, this.H[1]);
        boolean z2 = i == 1;
        this.t.h = z2 ? iMax2 : iMax;
        c cVar = this.t;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.i = iMax;
        if (z2) {
            this.t.h += this.u.j();
            View viewC2 = c2();
            this.t.e = this.x ? -1 : 1;
            c cVar2 = this.t;
            int iF0 = f0(viewC2);
            c cVar3 = this.t;
            cVar2.d = iF0 + cVar3.e;
            cVar3.f1089b = this.u.d(viewC2);
            iM = this.u.d(viewC2) - this.u.i();
        } else {
            View viewD2 = d2();
            this.t.h += this.u.m();
            this.t.e = this.x ? 1 : -1;
            c cVar4 = this.t;
            int iF02 = f0(viewD2);
            c cVar5 = this.t;
            cVar4.d = iF02 + cVar5.e;
            cVar5.f1089b = this.u.g(viewD2);
            iM = (-this.u.g(viewD2)) + this.u.m();
        }
        c cVar6 = this.t;
        cVar6.f1090c = i2;
        if (z) {
            cVar6.f1090c = i2 - iM;
        }
        this.t.g = iM;
    }

    private void z2(int i, int i2) {
        this.t.f1090c = this.u.i() - i2;
        this.t.e = this.x ? -1 : 1;
        c cVar = this.t;
        cVar.d = i;
        cVar.f = 1;
        cVar.f1089b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public View B(int i) {
        int I = I();
        if (I == 0) {
            return null;
        }
        int iF0 = i - f0(H(0));
        if (iF0 >= 0 && iF0 < I) {
            View viewH = H(iF0);
            if (f0(viewH) == i) {
                return viewH;
            }
        }
        return super.B(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n C() {
        return new RecyclerView.n(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    boolean C1() {
        return (W() == 1073741824 || n0() == 1073741824 || !o0()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean F1() {
        return this.D == null && this.v == this.y;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void G0(RecyclerView recyclerView, RecyclerView.t tVar) {
        super.G0(recyclerView, tVar);
        if (this.C) {
            h1(tVar);
            tVar.c();
        }
    }

    protected void G1(@NonNull RecyclerView.x xVar, @NonNull int[] iArr) {
        int i;
        int iE2 = e2(xVar);
        if (this.t.f == -1) {
            i = 0;
        } else {
            i = iE2;
            iE2 = 0;
        }
        iArr[0] = iE2;
        iArr[1] = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public View H0(View view, int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        int iL1;
        q2();
        if (I() == 0 || (iL1 = L1(i)) == Integer.MIN_VALUE) {
            return null;
        }
        N1();
        y2(iL1, (int) (this.u.n() * 0.33333334f), false, xVar);
        c cVar = this.t;
        cVar.g = Integer.MIN_VALUE;
        cVar.f1088a = false;
        O1(tVar, cVar, xVar, true);
        View viewY1 = iL1 == -1 ? Y1() : X1();
        View viewD2 = iL1 == -1 ? d2() : c2();
        if (!viewD2.hasFocusable()) {
            return viewY1;
        }
        if (viewY1 == null) {
            return null;
        }
        return viewD2;
    }

    void H1(RecyclerView.x xVar, c cVar, RecyclerView.m.c cVar2) {
        int i = cVar.d;
        if (i < 0 || i >= xVar.b()) {
            return;
        }
        cVar2.a(i, Math.max(0, cVar.g));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void I0(AccessibilityEvent accessibilityEvent) {
        super.I0(accessibilityEvent);
        if (I() > 0) {
            accessibilityEvent.setFromIndex(S1());
            accessibilityEvent.setToIndex(U1());
        }
    }

    int L1(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && g2()) ? -1 : 1 : (this.s != 1 && g2()) ? 1 : -1;
    }

    c M1() {
        return new c();
    }

    void N1() {
        if (this.t == null) {
            this.t = M1();
        }
    }

    int O1(RecyclerView.t tVar, c cVar, RecyclerView.x xVar, boolean z) {
        int i = cVar.f1090c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            l2(tVar, cVar);
        }
        int i3 = cVar.f1090c + cVar.h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.c(xVar)) {
                break;
            }
            bVar.a();
            i2(tVar, xVar, cVar, bVar);
            if (!bVar.f1086b) {
                cVar.f1089b += bVar.f1085a * cVar.f;
                if (!bVar.f1087c || cVar.l != null || !xVar.e()) {
                    int i4 = cVar.f1090c;
                    int i5 = bVar.f1085a;
                    cVar.f1090c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.f1085a;
                    cVar.g = i7;
                    int i8 = cVar.f1090c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    l2(tVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.f1090c;
    }

    View Q1(boolean z, boolean z2) {
        return this.x ? W1(0, I(), z, z2) : W1(I() - 1, -1, z, z2);
    }

    View R1(boolean z, boolean z2) {
        return this.x ? W1(I() - 1, -1, z, z2) : W1(0, I(), z, z2);
    }

    public int S1() {
        View viewW1 = W1(0, I(), false, true);
        if (viewW1 == null) {
            return -1;
        }
        return f0(viewW1);
    }

    public int U1() {
        View viewW1 = W1(I() - 1, -1, false, true);
        if (viewW1 == null) {
            return -1;
        }
        return f0(viewW1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void V0(RecyclerView.t tVar, RecyclerView.x xVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int iA2;
        int i5;
        View viewB;
        int iG;
        int i6;
        int i7 = -1;
        if (!(this.D == null && this.A == -1) && xVar.b() == 0) {
            h1(tVar);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.a()) {
            this.A = this.D.f1081c;
        }
        N1();
        this.t.f1088a = false;
        q2();
        View viewU = U();
        if (!this.E.e || this.A != -1 || this.D != null) {
            this.E.e();
            a aVar = this.E;
            aVar.d = this.x ^ this.y;
            x2(tVar, xVar, aVar);
            this.E.e = true;
        } else if (viewU != null && (this.u.g(viewU) >= this.u.i() || this.u.d(viewU) <= this.u.m())) {
            this.E.c(viewU, f0(viewU));
        }
        c cVar = this.t;
        cVar.f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        G1(xVar, iArr);
        int iMax = Math.max(0, this.H[0]) + this.u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.u.j();
        if (xVar.e() && (i5 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewB = B(i5)) != null) {
            if (this.x) {
                i6 = this.u.i() - this.u.d(viewB);
                iG = this.B;
            } else {
                iG = this.u.g(viewB) - this.u.m();
                i6 = this.B;
            }
            int i8 = i6 - iG;
            if (i8 > 0) {
                iMax += i8;
            } else {
                iMax2 -= i8;
            }
        }
        if (!this.E.d ? !this.x : this.x) {
            i7 = 1;
        }
        k2(tVar, xVar, this.E, i7);
        v(tVar);
        this.t.m = p2();
        this.t.j = xVar.e();
        this.t.i = 0;
        a aVar2 = this.E;
        if (aVar2.d) {
            C2(aVar2);
            c cVar2 = this.t;
            cVar2.h = iMax;
            O1(tVar, cVar2, xVar, false);
            c cVar3 = this.t;
            i2 = cVar3.f1089b;
            int i9 = cVar3.d;
            int i10 = cVar3.f1090c;
            if (i10 > 0) {
                iMax2 += i10;
            }
            A2(this.E);
            c cVar4 = this.t;
            cVar4.h = iMax2;
            cVar4.d += cVar4.e;
            O1(tVar, cVar4, xVar, false);
            c cVar5 = this.t;
            i = cVar5.f1089b;
            int i11 = cVar5.f1090c;
            if (i11 > 0) {
                B2(i9, i2);
                c cVar6 = this.t;
                cVar6.h = i11;
                O1(tVar, cVar6, xVar, false);
                i2 = this.t.f1089b;
            }
        } else {
            A2(aVar2);
            c cVar7 = this.t;
            cVar7.h = iMax2;
            O1(tVar, cVar7, xVar, false);
            c cVar8 = this.t;
            i = cVar8.f1089b;
            int i12 = cVar8.d;
            int i13 = cVar8.f1090c;
            if (i13 > 0) {
                iMax += i13;
            }
            C2(this.E);
            c cVar9 = this.t;
            cVar9.h = iMax;
            cVar9.d += cVar9.e;
            O1(tVar, cVar9, xVar, false);
            c cVar10 = this.t;
            i2 = cVar10.f1089b;
            int i14 = cVar10.f1090c;
            if (i14 > 0) {
                z2(i12, i);
                c cVar11 = this.t;
                cVar11.h = i14;
                O1(tVar, cVar11, xVar, false);
                i = this.t.f1089b;
            }
        }
        if (I() > 0) {
            if (this.x ^ this.y) {
                int iA22 = a2(i, tVar, xVar, true);
                i3 = i2 + iA22;
                i4 = i + iA22;
                iA2 = b2(i3, tVar, xVar, false);
            } else {
                int iB2 = b2(i2, tVar, xVar, true);
                i3 = i2 + iB2;
                i4 = i + iB2;
                iA2 = a2(i4, tVar, xVar, false);
            }
            i2 = i3 + iA2;
            i = i4 + iA2;
        }
        j2(tVar, xVar, i2, i);
        if (xVar.e()) {
            this.E.e();
        } else {
            this.u.s();
        }
        this.v = this.y;
    }

    View V1(int i, int i2) {
        int i3;
        int i4;
        N1();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return H(i);
        }
        if (this.u.g(H(i)) < this.u.m()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.s == 0 ? this.e.a(i, i2, i3, i4) : this.f.a(i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView.x xVar) {
        super.W0(xVar);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    View W1(int i, int i2, boolean z, boolean z2) {
        N1();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        return this.s == 0 ? this.e.a(i, i2, i3, i4) : this.f.a(i, i2, i3, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.View Z1(androidx.recyclerview.widget.RecyclerView.t r17, androidx.recyclerview.widget.RecyclerView.x r18, boolean r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r16.N1()
            int r1 = r16.I()
            r2 = -1
            r3 = 0
            r4 = 1
            if (r20 == 0) goto L15
            int r1 = r16.I()
            int r1 = r1 - r4
            r5 = r2
            goto L18
        L15:
            r2 = r1
            r1 = r3
            r5 = r4
        L18:
            int r6 = r18.b()
            androidx.recyclerview.widget.k r7 = r0.u
            int r7 = r7.m()
            androidx.recyclerview.widget.k r8 = r0.u
            int r8 = r8.i()
            r9 = 0
            r10 = r9
            r11 = r10
        L2b:
            if (r1 == r2) goto L7a
            android.view.View r12 = r0.H(r1)
            int r13 = r0.f0(r12)
            androidx.recyclerview.widget.k r14 = r0.u
            int r14 = r14.g(r12)
            androidx.recyclerview.widget.k r15 = r0.u
            int r15 = r15.d(r12)
            if (r13 < 0) goto L78
            if (r13 >= r6) goto L78
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            androidx.recyclerview.widget.RecyclerView$n r13 = (androidx.recyclerview.widget.RecyclerView.n) r13
            boolean r13 = r13.c()
            if (r13 == 0) goto L55
            if (r11 != 0) goto L78
            r11 = r12
            goto L78
        L55:
            if (r15 > r7) goto L5b
            if (r14 >= r7) goto L5b
            r13 = r4
            goto L5c
        L5b:
            r13 = r3
        L5c:
            if (r14 < r8) goto L62
            if (r15 <= r8) goto L62
            r14 = r4
            goto L63
        L62:
            r14 = r3
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.Z1(androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$x, boolean, boolean):android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void a1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.D = savedState;
            if (this.A != -1) {
                savedState.b();
            }
            q1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public Parcelable b1() {
        if (this.D != null) {
            return new SavedState(this.D);
        }
        SavedState savedState = new SavedState();
        if (I() > 0) {
            N1();
            boolean z = this.v ^ this.x;
            savedState.e = z;
            if (z) {
                View viewC2 = c2();
                savedState.d = this.u.i() - this.u.d(viewC2);
                savedState.f1081c = f0(viewC2);
            } else {
                View viewD2 = d2();
                savedState.f1081c = f0(viewD2);
                savedState.d = this.u.g(viewD2) - this.u.m();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    @Deprecated
    protected int e2(RecyclerView.x xVar) {
        if (xVar.d()) {
            return this.u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void f(String str) {
        if (this.D == null) {
            super.f(str);
        }
    }

    public int f2() {
        return this.s;
    }

    protected boolean g2() {
        return X() == 1;
    }

    public boolean h2() {
        return this.z;
    }

    void i2(RecyclerView.t tVar, RecyclerView.x xVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int iC0;
        int iF;
        View viewD = cVar.d(tVar);
        if (viewD == null) {
            bVar.f1086b = true;
            return;
        }
        RecyclerView.n nVar = (RecyclerView.n) viewD.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f == -1)) {
                c(viewD);
            } else {
                d(viewD, 0);
            }
        } else {
            if (this.x == (cVar.f == -1)) {
                a(viewD);
            } else {
                b(viewD, 0);
            }
        }
        y0(viewD, 0, 0);
        bVar.f1085a = this.u.e(viewD);
        if (this.s == 1) {
            if (g2()) {
                iF = m0() - d0();
                iC0 = iF - this.u.f(viewD);
            } else {
                iC0 = c0();
                iF = this.u.f(viewD) + iC0;
            }
            if (cVar.f == -1) {
                int i4 = cVar.f1089b;
                i3 = i4;
                i2 = iF;
                i = i4 - bVar.f1085a;
            } else {
                int i5 = cVar.f1089b;
                i = i5;
                i2 = iF;
                i3 = bVar.f1085a + i5;
            }
        } else {
            int iE0 = e0();
            int iF2 = this.u.f(viewD) + iE0;
            if (cVar.f == -1) {
                int i6 = cVar.f1089b;
                i2 = i6;
                i = iE0;
                i3 = iF2;
                iC0 = i6 - bVar.f1085a;
            } else {
                int i7 = cVar.f1089b;
                i = iE0;
                i2 = bVar.f1085a + i7;
                i3 = iF2;
                iC0 = i7;
            }
        }
        x0(viewD, iC0, i, i2, i3);
        if (nVar.c() || nVar.b()) {
            bVar.f1087c = true;
        }
        bVar.d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean j() {
        return this.s == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean k() {
        return this.s == 1;
    }

    void k2(RecyclerView.t tVar, RecyclerView.x xVar, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void n(int i, int i2, RecyclerView.x xVar, RecyclerView.m.c cVar) {
        if (this.s != 0) {
            i = i2;
        }
        if (I() == 0 || i == 0) {
            return;
        }
        N1();
        y2(i > 0 ? 1 : -1, Math.abs(i), true, xVar);
        H1(xVar, this.t, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void o(int i, RecyclerView.m.c cVar) {
        boolean z;
        int i2;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.a()) {
            q2();
            z = this.x;
            i2 = this.A;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z = savedState2.e;
            i2 = savedState2.f1081c;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.G && i2 >= 0 && i2 < i; i4++) {
            cVar.a(i2, 0);
            i2 += i3;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int p(RecyclerView.x xVar) {
        return I1(xVar);
    }

    boolean p2() {
        return this.u.k() == 0 && this.u.h() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int q(RecyclerView.x xVar) {
        return J1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean q0() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int r(RecyclerView.x xVar) {
        return K1(xVar);
    }

    int r2(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        if (I() == 0 || i == 0) {
            return 0;
        }
        N1();
        this.t.f1088a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        y2(i2, iAbs, true, xVar);
        c cVar = this.t;
        int iO1 = cVar.g + O1(tVar, cVar, xVar, false);
        if (iO1 < 0) {
            return 0;
        }
        if (iAbs > iO1) {
            i = i2 * iO1;
        }
        this.u.r(-i);
        this.t.k = i;
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int s(RecyclerView.x xVar) {
        return I1(xVar);
    }

    public void s2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        f(null);
        if (i != this.s || this.u == null) {
            k kVarB = k.b(this, i);
            this.u = kVarB;
            this.E.f1082a = kVarB;
            this.s = i;
            q1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int t(RecyclerView.x xVar) {
        return J1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int t1(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        if (this.s == 1) {
            return 0;
        }
        return r2(i, tVar, xVar);
    }

    public void t2(boolean z) {
        f(null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int u(RecyclerView.x xVar) {
        return K1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int u1(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        if (this.s == 0) {
            return 0;
        }
        return r2(i, tVar, xVar);
    }

    public void u2(boolean z) {
        f(null);
        if (this.y == z) {
            return;
        }
        this.y = z;
        q1();
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
        s2(i);
        t2(z);
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
        RecyclerView.m.d dVarG0 = RecyclerView.m.g0(context, attributeSet, i, i2);
        s2(dVarG0.f1115a);
        t2(dVarG0.f1117c);
        u2(dVarG0.d);
    }
}
