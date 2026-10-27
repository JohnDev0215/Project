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
public class LinearLayoutManager extends RecyclerView.o implements RecyclerView.y.b {
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
    j u;
    private boolean v;
    private boolean w;
    boolean x;
    private boolean y;
    private boolean z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1128b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1129c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1130d;

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

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        boolean f() {
            return this.f1128b >= 0;
        }

        void g() {
            this.f1128b = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1128b);
            parcel.writeInt(this.f1129c);
            parcel.writeInt(this.f1130d ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f1128b = parcel.readInt();
            this.f1129c = parcel.readInt();
            this.f1130d = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1128b = savedState.f1128b;
            this.f1129c = savedState.f1129c;
            this.f1130d = savedState.f1130d;
        }
    }

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        j f1131a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1132b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1133c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1134d;
        boolean e;

        a() {
            e();
        }

        void a() {
            this.f1133c = this.f1134d ? this.f1131a.i() : this.f1131a.m();
        }

        public void b(View view, int i) {
            if (this.f1134d) {
                this.f1133c = this.f1131a.d(view) + this.f1131a.o();
            } else {
                this.f1133c = this.f1131a.g(view);
            }
            this.f1132b = i;
        }

        public void c(View view, int i) {
            int iO = this.f1131a.o();
            if (iO >= 0) {
                b(view, i);
                return;
            }
            this.f1132b = i;
            if (this.f1134d) {
                int i2 = (this.f1131a.i() - iO) - this.f1131a.d(view);
                this.f1133c = this.f1131a.i() - i2;
                if (i2 > 0) {
                    int iE = this.f1133c - this.f1131a.e(view);
                    int iM = this.f1131a.m();
                    int iMin = iE - (iM + Math.min(this.f1131a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1133c += Math.min(i2, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1131a.g(view);
            int iM2 = iG - this.f1131a.m();
            this.f1133c = iG;
            if (iM2 > 0) {
                int i3 = (this.f1131a.i() - Math.min(0, (this.f1131a.i() - iO) - this.f1131a.d(view))) - (iG + this.f1131a.e(view));
                if (i3 < 0) {
                    this.f1133c -= Math.min(iM2, -i3);
                }
            }
        }

        boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < zVar.b();
        }

        void e() {
            this.f1132b = -1;
            this.f1133c = Integer.MIN_VALUE;
            this.f1134d = false;
            this.e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1132b + ", mCoordinate=" + this.f1133c + ", mLayoutFromEnd=" + this.f1134d + ", mValid=" + this.e + '}';
        }
    }

    protected static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1138d;

        protected b() {
        }

        void a() {
            this.f1135a = 0;
            this.f1136b = false;
            this.f1137c = false;
            this.f1138d = false;
        }
    }

    static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1140b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1141c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1142d;
        int e;
        int f;
        int g;
        int k;
        boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1139a = true;
        int h = 0;
        int i = 0;
        boolean j = false;
        List<RecyclerView.c0> l = null;

        c() {
        }

        private View e() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = this.l.get(i).f1153b;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f1142d == pVar.a()) {
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
                this.f1142d = -1;
            } else {
                this.f1142d = ((RecyclerView.p) viewF.getLayoutParams()).a();
            }
        }

        boolean c(RecyclerView.z zVar) {
            int i = this.f1142d;
            return i >= 0 && i < zVar.b();
        }

        View d(RecyclerView.v vVar) {
            if (this.l != null) {
                return e();
            }
            View viewO = vVar.o(this.f1142d);
            this.f1142d += this.e;
            return viewO;
        }

        public View f(View view) {
            int iA;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).f1153b;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (iA = (pVar.a() - this.f1142d) * this.e) >= 0 && iA < i) {
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

    private void A2() {
        if (this.s == 1 || !q2()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    private boolean F2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
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
        View viewI2 = aVar.f1134d ? i2(vVar, zVar) : j2(vVar, zVar);
        if (viewI2 == null) {
            return false;
        }
        aVar.b(viewI2, h0(viewI2));
        if (!zVar.e() && L1()) {
            if (this.u.g(viewI2) >= this.u.i() || this.u.d(viewI2) < this.u.m()) {
                aVar.f1133c = aVar.f1134d ? this.u.i() : this.u.m();
            }
        }
        return true;
    }

    private boolean G2(RecyclerView.z zVar, a aVar) {
        int i;
        if (!zVar.e() && (i = this.A) != -1) {
            if (i >= 0 && i < zVar.b()) {
                aVar.f1132b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.f()) {
                    boolean z = this.D.f1130d;
                    aVar.f1134d = z;
                    if (z) {
                        aVar.f1133c = this.u.i() - this.D.f1129c;
                    } else {
                        aVar.f1133c = this.u.m() + this.D.f1129c;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z2 = this.x;
                    aVar.f1134d = z2;
                    if (z2) {
                        aVar.f1133c = this.u.i() - this.B;
                    } else {
                        aVar.f1133c = this.u.m() + this.B;
                    }
                    return true;
                }
                View viewC = C(this.A);
                if (viewC == null) {
                    if (J() > 0) {
                        aVar.f1134d = (this.A < h0(I(0))) == this.x;
                    }
                    aVar.a();
                } else {
                    if (this.u.e(viewC) > this.u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.u.g(viewC) - this.u.m() < 0) {
                        aVar.f1133c = this.u.m();
                        aVar.f1134d = false;
                        return true;
                    }
                    if (this.u.i() - this.u.d(viewC) < 0) {
                        aVar.f1133c = this.u.i();
                        aVar.f1134d = true;
                        return true;
                    }
                    aVar.f1133c = aVar.f1134d ? this.u.d(viewC) + this.u.o() : this.u.g(viewC);
                }
                return true;
            }
            this.A = -1;
            this.B = Integer.MIN_VALUE;
        }
        return false;
    }

    private void H2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (G2(zVar, aVar) || F2(vVar, zVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1132b = this.y ? zVar.b() - 1 : 0;
    }

    private void I2(int i, int i2, boolean z, RecyclerView.z zVar) {
        int iM;
        this.t.m = z2();
        this.t.f = i;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
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
            View viewM2 = m2();
            c cVar2 = this.t;
            cVar2.e = this.x ? -1 : 1;
            int iH0 = h0(viewM2);
            c cVar3 = this.t;
            cVar2.f1142d = iH0 + cVar3.e;
            cVar3.f1140b = this.u.d(viewM2);
            iM = this.u.d(viewM2) - this.u.i();
        } else {
            View viewN2 = n2();
            this.t.h += this.u.m();
            c cVar4 = this.t;
            cVar4.e = this.x ? 1 : -1;
            int iH02 = h0(viewN2);
            c cVar5 = this.t;
            cVar4.f1142d = iH02 + cVar5.e;
            cVar5.f1140b = this.u.g(viewN2);
            iM = (-this.u.g(viewN2)) + this.u.m();
        }
        c cVar6 = this.t;
        cVar6.f1141c = i2;
        if (z) {
            cVar6.f1141c = i2 - iM;
        }
        cVar6.g = iM;
    }

    private void J2(int i, int i2) {
        this.t.f1141c = this.u.i() - i2;
        c cVar = this.t;
        cVar.e = this.x ? -1 : 1;
        cVar.f1142d = i;
        cVar.f = 1;
        cVar.f1140b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private void K2(a aVar) {
        J2(aVar.f1132b, aVar.f1133c);
    }

    private void L2(int i, int i2) {
        this.t.f1141c = i2 - this.u.m();
        c cVar = this.t;
        cVar.f1142d = i;
        cVar.e = this.x ? 1 : -1;
        cVar.f = -1;
        cVar.f1140b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private void M2(a aVar) {
        L2(aVar.f1132b, aVar.f1133c);
    }

    private int O1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.a(zVar, this.u, Y1(!this.z, true), X1(!this.z, true), this, this.z);
    }

    private int P1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.b(zVar, this.u, Y1(!this.z, true), X1(!this.z, true), this, this.z, this.x);
    }

    private int Q1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return m.c(zVar, this.u, Y1(!this.z, true), X1(!this.z, true), this, this.z);
    }

    private View V1() {
        return d2(0, J());
    }

    private View W1(RecyclerView.v vVar, RecyclerView.z zVar) {
        return h2(vVar, zVar, 0, J(), zVar.b());
    }

    private View a2() {
        return d2(J() - 1, -1);
    }

    private View b2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return h2(vVar, zVar, J() - 1, -1, zVar.b());
    }

    private View f2() {
        return this.x ? V1() : a2();
    }

    private View g2() {
        return this.x ? a2() : V1();
    }

    private View i2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x ? W1(vVar, zVar) : b2(vVar, zVar);
    }

    private View j2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x ? b2(vVar, zVar) : W1(vVar, zVar);
    }

    private int k2(int i, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i2;
        int i3 = this.u.i() - i;
        if (i3 <= 0) {
            return 0;
        }
        int i4 = -B2(-i3, vVar, zVar);
        int i5 = i + i4;
        if (!z || (i2 = this.u.i() - i5) <= 0) {
            return i4;
        }
        this.u.r(i2);
        return i2 + i4;
    }

    private int l2(int i, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iM2 = i - this.u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i2 = -B2(iM2, vVar, zVar);
        int i3 = i + i2;
        if (!z || (iM = i3 - this.u.m()) <= 0) {
            return i2;
        }
        this.u.r(-iM);
        return i2 - iM;
    }

    private View m2() {
        return I(this.x ? 0 : J() - 1);
    }

    private View n2() {
        return I(this.x ? J() - 1 : 0);
    }

    private void t2(RecyclerView.v vVar, RecyclerView.z zVar, int i, int i2) {
        if (!zVar.g() || J() == 0 || zVar.e() || !L1()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int iH0 = h0(I(0));
        int iE = 0;
        int iE2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView.c0 c0Var = listK.get(i3);
            if (!c0Var.v()) {
                if (((c0Var.m() < iH0) != this.x ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.u.e(c0Var.f1153b);
                } else {
                    iE2 += this.u.e(c0Var.f1153b);
                }
            }
        }
        this.t.l = listK;
        if (iE > 0) {
            L2(h0(n2()), i);
            c cVar = this.t;
            cVar.h = iE;
            cVar.f1141c = 0;
            cVar.a();
            U1(vVar, this.t, zVar, false);
        }
        if (iE2 > 0) {
            J2(h0(m2()), i2);
            c cVar2 = this.t;
            cVar2.h = iE2;
            cVar2.f1141c = 0;
            cVar2.a();
            U1(vVar, this.t, zVar, false);
        }
        this.t.l = null;
    }

    private void v2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1139a || cVar.m) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f == -1) {
            x2(vVar, i, i2);
        } else {
            y2(vVar, i, i2);
        }
    }

    private void w2(RecyclerView.v vVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                n1(i, vVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                n1(i3, vVar);
            }
        }
    }

    private void x2(RecyclerView.v vVar, int i, int i2) {
        int iJ = J();
        if (i < 0) {
            return;
        }
        int iH = (this.u.h() - i) + i2;
        if (this.x) {
            for (int i3 = 0; i3 < iJ; i3++) {
                View viewI = I(i3);
                if (this.u.g(viewI) < iH || this.u.q(viewI) < iH) {
                    w2(vVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = iJ - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View viewI2 = I(i5);
            if (this.u.g(viewI2) < iH || this.u.q(viewI2) < iH) {
                w2(vVar, i4, i5);
                return;
            }
        }
    }

    private void y2(RecyclerView.v vVar, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int iJ = J();
        if (!this.x) {
            for (int i4 = 0; i4 < iJ; i4++) {
                View viewI = I(i4);
                if (this.u.d(viewI) > i3 || this.u.p(viewI) > i3) {
                    w2(vVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = iJ - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View viewI2 = I(i6);
            if (this.u.d(viewI2) > i3 || this.u.p(viewI2) > i3) {
                w2(vVar, i5, i6);
                return;
            }
        }
    }

    int B2(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i == 0) {
            return 0;
        }
        T1();
        this.t.f1139a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        I2(i2, iAbs, true, zVar);
        c cVar = this.t;
        int iU1 = cVar.g + U1(vVar, cVar, zVar, false);
        if (iU1 < 0) {
            return 0;
        }
        if (iAbs > iU1) {
            i = i2 * iU1;
        }
        this.u.r(-i);
        this.t.k = i;
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View C(int i) {
        int iJ = J();
        if (iJ == 0) {
            return null;
        }
        int iH0 = i - h0(I(0));
        if (iH0 >= 0 && iH0 < iJ) {
            View viewI = I(iH0);
            if (h0(viewI) == i) {
                return viewI;
            }
        }
        return super.C(i);
    }

    public void C2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        g(null);
        if (i != this.s || this.u == null) {
            j jVarB = j.b(this, i);
            this.u = jVarB;
            this.E.f1131a = jVarB;
            this.s = i;
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    boolean G1() {
        return (X() == 1073741824 || p0() == 1073741824 || !q0()) ? false : true;
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
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i) {
        g gVar = new g(recyclerView.getContext());
        gVar.p(i);
        J1(gVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iR1;
        A2();
        if (J() == 0 || (iR1 = R1(i)) == Integer.MIN_VALUE) {
            return null;
        }
        T1();
        I2(iR1, (int) (this.u.n() * 0.33333334f), false, zVar);
        c cVar = this.t;
        cVar.g = Integer.MIN_VALUE;
        cVar.f1139a = false;
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void K0(AccessibilityEvent accessibilityEvent) {
        super.K0(accessibilityEvent);
        if (J() > 0) {
            accessibilityEvent.setFromIndex(Z1());
            accessibilityEvent.setToIndex(c2());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean L1() {
        return this.D == null && this.v == this.y;
    }

    protected void M1(RecyclerView.z zVar, int[] iArr) {
        int i;
        int iO2 = o2(zVar);
        if (this.t.f == -1) {
            i = 0;
        } else {
            i = iO2;
            iO2 = 0;
        }
        iArr[0] = iO2;
        iArr[1] = i;
    }

    void N1(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i = cVar.f1142d;
        if (i < 0 || i >= zVar.b()) {
            return;
        }
        cVar2.a(i, Math.max(0, cVar.g));
    }

    int R1(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.s == 1) ? 1 : Integer.MIN_VALUE : this.s == 0 ? 1 : Integer.MIN_VALUE : this.s == 1 ? -1 : Integer.MIN_VALUE : this.s == 0 ? -1 : Integer.MIN_VALUE : (this.s != 1 && q2()) ? -1 : 1 : (this.s != 1 && q2()) ? 1 : -1;
    }

    c S1() {
        return new c();
    }

    void T1() {
        if (this.t == null) {
            this.t = S1();
        }
    }

    int U1(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z) {
        int i = cVar.f1141c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            v2(vVar, cVar);
        }
        int i3 = cVar.f1141c + cVar.h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            s2(vVar, zVar, cVar, bVar);
            if (!bVar.f1136b) {
                cVar.f1140b += bVar.f1135a * cVar.f;
                if (!bVar.f1137c || cVar.l != null || !zVar.e()) {
                    int i4 = cVar.f1141c;
                    int i5 = bVar.f1135a;
                    cVar.f1141c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.f1135a;
                    cVar.g = i7;
                    int i8 = cVar.f1141c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    v2(vVar, cVar);
                }
                if (z && bVar.f1138d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.f1141c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int iK2;
        int i5;
        View viewC;
        int iG;
        int i6;
        int i7 = -1;
        if (!(this.D == null && this.A == -1) && zVar.b() == 0) {
            k1(vVar);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.f()) {
            this.A = this.D.f1128b;
        }
        T1();
        this.t.f1139a = false;
        A2();
        View viewV = V();
        a aVar = this.E;
        if (!aVar.e || this.A != -1 || this.D != null) {
            aVar.e();
            a aVar2 = this.E;
            aVar2.f1134d = this.x ^ this.y;
            H2(vVar, zVar, aVar2);
            this.E.e = true;
        } else if (viewV != null && (this.u.g(viewV) >= this.u.i() || this.u.d(viewV) <= this.u.m())) {
            this.E.c(viewV, h0(viewV));
        }
        c cVar = this.t;
        cVar.f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]) + this.u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.u.j();
        if (zVar.e() && (i5 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewC = C(i5)) != null) {
            if (this.x) {
                i6 = this.u.i() - this.u.d(viewC);
                iG = this.B;
            } else {
                iG = this.u.g(viewC) - this.u.m();
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
        if (!aVar3.f1134d ? !this.x : this.x) {
            i7 = 1;
        }
        u2(vVar, zVar, aVar3, i7);
        w(vVar);
        this.t.m = z2();
        this.t.j = zVar.e();
        this.t.i = 0;
        a aVar4 = this.E;
        if (aVar4.f1134d) {
            M2(aVar4);
            c cVar2 = this.t;
            cVar2.h = iMax;
            U1(vVar, cVar2, zVar, false);
            c cVar3 = this.t;
            i2 = cVar3.f1140b;
            int i9 = cVar3.f1142d;
            int i10 = cVar3.f1141c;
            if (i10 > 0) {
                iMax2 += i10;
            }
            K2(this.E);
            c cVar4 = this.t;
            cVar4.h = iMax2;
            cVar4.f1142d += cVar4.e;
            U1(vVar, cVar4, zVar, false);
            c cVar5 = this.t;
            i = cVar5.f1140b;
            int i11 = cVar5.f1141c;
            if (i11 > 0) {
                L2(i9, i2);
                c cVar6 = this.t;
                cVar6.h = i11;
                U1(vVar, cVar6, zVar, false);
                i2 = this.t.f1140b;
            }
        } else {
            K2(aVar4);
            c cVar7 = this.t;
            cVar7.h = iMax2;
            U1(vVar, cVar7, zVar, false);
            c cVar8 = this.t;
            i = cVar8.f1140b;
            int i12 = cVar8.f1142d;
            int i13 = cVar8.f1141c;
            if (i13 > 0) {
                iMax += i13;
            }
            M2(this.E);
            c cVar9 = this.t;
            cVar9.h = iMax;
            cVar9.f1142d += cVar9.e;
            U1(vVar, cVar9, zVar, false);
            c cVar10 = this.t;
            i2 = cVar10.f1140b;
            int i14 = cVar10.f1141c;
            if (i14 > 0) {
                J2(i12, i);
                c cVar11 = this.t;
                cVar11.h = i14;
                U1(vVar, cVar11, zVar, false);
                i = this.t.f1140b;
            }
        }
        if (J() > 0) {
            if (this.x ^ this.y) {
                int iK22 = k2(i, vVar, zVar, true);
                i3 = i2 + iK22;
                i4 = i + iK22;
                iK2 = l2(i3, vVar, zVar, false);
            } else {
                int iL2 = l2(i2, vVar, zVar, true);
                i3 = i2 + iL2;
                i4 = i + iL2;
                iK2 = k2(i4, vVar, zVar, false);
            }
            i2 = i3 + iK2;
            i = i4 + iK2;
        }
        t2(vVar, zVar, i2, i);
        if (zVar.e()) {
            this.E.e();
        } else {
            this.u.s();
        }
        this.v = this.y;
    }

    View X1(boolean z, boolean z2) {
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

    View Y1(boolean z, boolean z2) {
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
    public PointF a(int i) {
        if (J() == 0) {
            return null;
        }
        int i2 = (i < h0(I(0))) != this.x ? -1 : 1;
        return this.s == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
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
            savedState.f1130d = z;
            if (z) {
                View viewM2 = m2();
                savedState.f1129c = this.u.i() - this.u.d(viewM2);
                savedState.f1128b = h0(viewM2);
            } else {
                View viewN2 = n2();
                savedState.f1128b = h0(viewN2);
                savedState.f1129c = this.u.g(viewN2) - this.u.m();
            }
        } else {
            savedState.g();
        }
        return savedState;
    }

    View d2(int i, int i2) {
        int i3;
        int i4;
        T1();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return I(i);
        }
        if (this.u.g(I(i)) < this.u.m()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.s == 0 ? this.e.a(i, i2, i3, i4) : this.f.a(i, i2, i3, i4);
    }

    View e2(int i, int i2, boolean z, boolean z2) {
        T1();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        return this.s == 0 ? this.e.a(i, i2, i3, i4) : this.f.a(i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(String str) {
        if (this.D == null) {
            super.g(str);
        }
    }

    View h2(RecyclerView.v vVar, RecyclerView.z zVar, int i, int i2, int i3) {
        T1();
        int iM = this.u.m();
        int i4 = this.u.i();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewI = I(i);
            int iH0 = h0(viewI);
            if (iH0 >= 0 && iH0 < i3) {
                if (((RecyclerView.p) viewI.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewI;
                    }
                } else {
                    if (this.u.g(viewI) < i4 && this.u.d(viewI) >= iM) {
                        return viewI;
                    }
                    if (view == null) {
                        view = viewI;
                    }
                }
            }
            i += i5;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.s == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l() {
        return this.s == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i, int i2, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.s != 0) {
            i = i2;
        }
        if (J() == 0 || i == 0) {
            return;
        }
        T1();
        I2(i > 0 ? 1 : -1, Math.abs(i), true, zVar);
        N1(zVar, this.t, cVar);
    }

    @Deprecated
    protected int o2(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void p(int i, RecyclerView.o.c cVar) {
        boolean z;
        int i2;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.f()) {
            A2();
            z = this.x;
            i2 = this.A;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z = savedState2.f1130d;
            i2 = savedState2.f1128b;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.G && i2 >= 0 && i2 < i; i4++) {
            cVar.a(i2, 0);
            i2 += i3;
        }
    }

    public int p2() {
        return this.s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return O1(zVar);
    }

    protected boolean q2() {
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

    void s2(RecyclerView.v vVar, RecyclerView.z zVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int iE0;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f1136b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f == -1)) {
                d(viewD);
            } else {
                e(viewD, 0);
            }
        } else {
            if (this.x == (cVar.f == -1)) {
                b(viewD);
            } else {
                c(viewD, 0);
            }
        }
        A0(viewD, 0, 0);
        bVar.f1135a = this.u.e(viewD);
        if (this.s == 1) {
            if (q2()) {
                iF = o0() - f0();
                iE0 = iF - this.u.f(viewD);
            } else {
                iE0 = e0();
                iF = this.u.f(viewD) + iE0;
            }
            if (cVar.f == -1) {
                int i4 = cVar.f1140b;
                i3 = i4;
                i2 = iF;
                i = i4 - bVar.f1135a;
            } else {
                int i5 = cVar.f1140b;
                i = i5;
                i2 = iF;
                i3 = bVar.f1135a + i5;
            }
        } else {
            int iG0 = g0();
            int iF2 = this.u.f(viewD) + iG0;
            if (cVar.f == -1) {
                int i6 = cVar.f1140b;
                i2 = i6;
                i = iG0;
                i3 = iF2;
                iE0 = i6 - bVar.f1135a;
            } else {
                int i7 = cVar.f1140b;
                i = iG0;
                i2 = bVar.f1135a + i7;
                i3 = iF2;
                iE0 = i7;
            }
        }
        z0(viewD, iE0, i, i2, i3);
        if (pVar.c() || pVar.b()) {
            bVar.f1137c = true;
        }
        bVar.f1138d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return O1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return P1(zVar);
    }

    void u2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 1) {
            return 0;
        }
        return B2(i, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i) {
        this.A = i;
        this.B = Integer.MIN_VALUE;
        SavedState savedState = this.D;
        if (savedState != null) {
            savedState.g();
        }
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 0) {
            return 0;
        }
        return B2(i, vVar, zVar);
    }

    boolean z2() {
        return this.u.k() == 0 && this.u.h() == 0;
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
        C2(i);
        D2(z);
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
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i, i2);
        C2(dVarI0.f1176a);
        D2(dVarI0.f1178c);
        E2(dVarI0.f1179d);
    }
}
