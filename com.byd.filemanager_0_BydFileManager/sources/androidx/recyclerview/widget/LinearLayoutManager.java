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
    private boolean A;
    int B;
    int C;
    private boolean D;
    SavedState E;
    final a F;
    private final b G;
    private int H;
    private int[] I;
    int t;
    private c u;
    k v;
    private boolean w;
    private boolean x;
    boolean y;
    private boolean z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1271b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1272c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1273d;

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

        boolean u() {
            return this.f1271b >= 0;
        }

        void v() {
            this.f1271b = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1271b);
            parcel.writeInt(this.f1272c);
            parcel.writeInt(this.f1273d ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f1271b = parcel.readInt();
            this.f1272c = parcel.readInt();
            this.f1273d = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1271b = savedState.f1271b;
            this.f1272c = savedState.f1272c;
            this.f1273d = savedState.f1273d;
        }
    }

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        k f1274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1276c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1277d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1278e;

        a() {
            e();
        }

        void a() {
            this.f1276c = this.f1277d ? this.f1274a.i() : this.f1274a.m();
        }

        public void b(View view, int i) {
            if (this.f1277d) {
                this.f1276c = this.f1274a.d(view) + this.f1274a.o();
            } else {
                this.f1276c = this.f1274a.g(view);
            }
            this.f1275b = i;
        }

        public void c(View view, int i) {
            int iO = this.f1274a.o();
            if (iO >= 0) {
                b(view, i);
                return;
            }
            this.f1275b = i;
            if (this.f1277d) {
                int i2 = (this.f1274a.i() - iO) - this.f1274a.d(view);
                this.f1276c = this.f1274a.i() - i2;
                if (i2 > 0) {
                    int iE = this.f1276c - this.f1274a.e(view);
                    int iM = this.f1274a.m();
                    int iMin = iE - (iM + Math.min(this.f1274a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1276c += Math.min(i2, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1274a.g(view);
            int iM2 = iG - this.f1274a.m();
            this.f1276c = iG;
            if (iM2 > 0) {
                int i3 = (this.f1274a.i() - Math.min(0, (this.f1274a.i() - iO) - this.f1274a.d(view))) - (iG + this.f1274a.e(view));
                if (i3 < 0) {
                    this.f1276c -= Math.min(iM2, -i3);
                }
            }
        }

        boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.w() && pVar.u() >= 0 && pVar.u() < zVar.b();
        }

        void e() {
            this.f1275b = -1;
            this.f1276c = Integer.MIN_VALUE;
            this.f1277d = false;
            this.f1278e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1275b + ", mCoordinate=" + this.f1276c + ", mLayoutFromEnd=" + this.f1277d + ", mValid=" + this.f1278e + '}';
        }
    }

    protected static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1281c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1282d;

        protected b() {
        }

        void a() {
            this.f1279a = 0;
            this.f1280b = false;
            this.f1281c = false;
            this.f1282d = false;
        }
    }

    static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1284b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1285c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1286d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1287e;
        int f;
        int g;
        int k;
        boolean m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1283a = true;
        int h = 0;
        int i = 0;
        boolean j = false;
        List<RecyclerView.c0> l = null;

        c() {
        }

        private View e() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = this.l.get(i).f1300b;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.w() && this.f1286d == pVar.u()) {
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
                this.f1286d = -1;
            } else {
                this.f1286d = ((RecyclerView.p) viewF.getLayoutParams()).u();
            }
        }

        boolean c(RecyclerView.z zVar) {
            int i = this.f1286d;
            return i >= 0 && i < zVar.b();
        }

        View d(RecyclerView.v vVar) {
            if (this.l != null) {
                return e();
            }
            View viewO = vVar.o(this.f1286d);
            this.f1286d += this.f1287e;
            return viewO;
        }

        public View f(View view) {
            int iU;
            int size = this.l.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).f1300b;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.w() && (iU = (pVar.u() - this.f1286d) * this.f1287e) >= 0 && iU < i) {
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

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private void F2(RecyclerView.v vVar, RecyclerView.z zVar, int i, int i2) {
        if (!zVar.g() || T() == 0 || zVar.e() || !V1()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int iN0 = n0(S(0));
        int iE = 0;
        int iE2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView.c0 c0Var = listK.get(i3);
            if (!c0Var.v()) {
                if (((c0Var.m() < iN0) != this.y ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.v.e(c0Var.f1300b);
                } else {
                    iE2 += this.v.e(c0Var.f1300b);
                }
            }
        }
        this.u.l = listK;
        if (iE > 0) {
            X2(n0(z2()), i);
            c cVar = this.u;
            cVar.h = iE;
            cVar.f1285c = 0;
            cVar.a();
            e2(vVar, this.u, zVar, false);
        }
        if (iE2 > 0) {
            V2(n0(y2()), i2);
            c cVar2 = this.u;
            cVar2.h = iE2;
            cVar2.f1285c = 0;
            cVar2.a();
            e2(vVar, this.u, zVar, false);
        }
        this.u.l = null;
    }

    private void H2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1283a || cVar.m) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f == -1) {
            J2(vVar, i, i2);
        } else {
            K2(vVar, i, i2);
        }
    }

    private void I2(RecyclerView.v vVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                w1(i, vVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                w1(i3, vVar);
            }
        }
    }

    private void J2(RecyclerView.v vVar, int i, int i2) {
        int iT = T();
        if (i < 0) {
            return;
        }
        int iH = (this.v.h() - i) + i2;
        if (this.y) {
            for (int i3 = 0; i3 < iT; i3++) {
                View viewS = S(i3);
                if (this.v.g(viewS) < iH || this.v.q(viewS) < iH) {
                    I2(vVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = iT - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View viewS2 = S(i5);
            if (this.v.g(viewS2) < iH || this.v.q(viewS2) < iH) {
                I2(vVar, i4, i5);
                return;
            }
        }
    }

    private void K2(RecyclerView.v vVar, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int iT = T();
        if (!this.y) {
            for (int i4 = 0; i4 < iT; i4++) {
                View viewS = S(i4);
                if (this.v.d(viewS) > i3 || this.v.p(viewS) > i3) {
                    I2(vVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = iT - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View viewS2 = S(i6);
            if (this.v.d(viewS2) > i3 || this.v.p(viewS2) > i3) {
                I2(vVar, i5, i6);
                return;
            }
        }
    }

    private void M2() {
        if (this.t == 1 || !C2()) {
            this.y = this.x;
        } else {
            this.y = !this.x;
        }
    }

    private boolean R2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (T() == 0) {
            return false;
        }
        View viewF0 = f0();
        if (viewF0 != null && aVar.d(viewF0, zVar)) {
            aVar.c(viewF0, n0(viewF0));
            return true;
        }
        if (this.w != this.z) {
            return false;
        }
        View viewU2 = aVar.f1277d ? u2(vVar, zVar) : v2(vVar, zVar);
        if (viewU2 == null) {
            return false;
        }
        aVar.b(viewU2, n0(viewU2));
        if (!zVar.e() && V1()) {
            if (this.v.g(viewU2) >= this.v.i() || this.v.d(viewU2) < this.v.m()) {
                aVar.f1276c = aVar.f1277d ? this.v.i() : this.v.m();
            }
        }
        return true;
    }

    private boolean S2(RecyclerView.z zVar, a aVar) {
        int i;
        if (!zVar.e() && (i = this.B) != -1) {
            if (i >= 0 && i < zVar.b()) {
                aVar.f1275b = this.B;
                SavedState savedState = this.E;
                if (savedState != null && savedState.u()) {
                    boolean z = this.E.f1273d;
                    aVar.f1277d = z;
                    if (z) {
                        aVar.f1276c = this.v.i() - this.E.f1272c;
                    } else {
                        aVar.f1276c = this.v.m() + this.E.f1272c;
                    }
                    return true;
                }
                if (this.C != Integer.MIN_VALUE) {
                    boolean z2 = this.y;
                    aVar.f1277d = z2;
                    if (z2) {
                        aVar.f1276c = this.v.i() - this.C;
                    } else {
                        aVar.f1276c = this.v.m() + this.C;
                    }
                    return true;
                }
                View viewM = M(this.B);
                if (viewM == null) {
                    if (T() > 0) {
                        aVar.f1277d = (this.B < n0(S(0))) == this.y;
                    }
                    aVar.a();
                } else {
                    if (this.v.e(viewM) > this.v.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.v.g(viewM) - this.v.m() < 0) {
                        aVar.f1276c = this.v.m();
                        aVar.f1277d = false;
                        return true;
                    }
                    if (this.v.i() - this.v.d(viewM) < 0) {
                        aVar.f1276c = this.v.i();
                        aVar.f1277d = true;
                        return true;
                    }
                    aVar.f1276c = aVar.f1277d ? this.v.d(viewM) + this.v.o() : this.v.g(viewM);
                }
                return true;
            }
            this.B = -1;
            this.C = Integer.MIN_VALUE;
        }
        return false;
    }

    private void T2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (S2(zVar, aVar) || R2(vVar, zVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1275b = this.z ? zVar.b() - 1 : 0;
    }

    private void U2(int i, int i2, boolean z, RecyclerView.z zVar) {
        int iM;
        this.u.m = L2();
        this.u.f = i;
        int[] iArr = this.I;
        iArr[0] = 0;
        iArr[1] = 0;
        W1(zVar, iArr);
        int iMax = Math.max(0, this.I[0]);
        int iMax2 = Math.max(0, this.I[1]);
        boolean z2 = i == 1;
        c cVar = this.u;
        int i3 = z2 ? iMax2 : iMax;
        cVar.h = i3;
        if (!z2) {
            iMax = iMax2;
        }
        cVar.i = iMax;
        if (z2) {
            cVar.h = i3 + this.v.j();
            View viewY2 = y2();
            c cVar2 = this.u;
            cVar2.f1287e = this.y ? -1 : 1;
            int iN0 = n0(viewY2);
            c cVar3 = this.u;
            cVar2.f1286d = iN0 + cVar3.f1287e;
            cVar3.f1284b = this.v.d(viewY2);
            iM = this.v.d(viewY2) - this.v.i();
        } else {
            View viewZ2 = z2();
            this.u.h += this.v.m();
            c cVar4 = this.u;
            cVar4.f1287e = this.y ? 1 : -1;
            int iN02 = n0(viewZ2);
            c cVar5 = this.u;
            cVar4.f1286d = iN02 + cVar5.f1287e;
            cVar5.f1284b = this.v.g(viewZ2);
            iM = (-this.v.g(viewZ2)) + this.v.m();
        }
        c cVar6 = this.u;
        cVar6.f1285c = i2;
        if (z) {
            cVar6.f1285c = i2 - iM;
        }
        cVar6.g = iM;
    }

    private void V2(int i, int i2) {
        this.u.f1285c = this.v.i() - i2;
        c cVar = this.u;
        cVar.f1287e = this.y ? -1 : 1;
        cVar.f1286d = i;
        cVar.f = 1;
        cVar.f1284b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private void W2(a aVar) {
        V2(aVar.f1275b, aVar.f1276c);
    }

    private void X2(int i, int i2) {
        this.u.f1285c = i2 - this.v.m();
        c cVar = this.u;
        cVar.f1286d = i;
        cVar.f1287e = this.y ? 1 : -1;
        cVar.f = -1;
        cVar.f1284b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private int Y1(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        d2();
        return n.a(zVar, this.v, j2(!this.A, true), i2(!this.A, true), this, this.A);
    }

    private void Y2(a aVar) {
        X2(aVar.f1275b, aVar.f1276c);
    }

    private int Z1(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        d2();
        return n.b(zVar, this.v, j2(!this.A, true), i2(!this.A, true), this, this.A, this.y);
    }

    private int a2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        d2();
        return n.c(zVar, this.v, j2(!this.A, true), i2(!this.A, true), this, this.A);
    }

    private View g2() {
        return p2(0, T());
    }

    private View h2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return t2(vVar, zVar, 0, T(), zVar.b());
    }

    private View m2() {
        return p2(T() - 1, -1);
    }

    private View n2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return t2(vVar, zVar, T() - 1, -1, zVar.b());
    }

    private View r2() {
        return this.y ? g2() : m2();
    }

    private View s2() {
        return this.y ? m2() : g2();
    }

    private View u2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.y ? h2(vVar, zVar) : n2(vVar, zVar);
    }

    private View v2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.y ? n2(vVar, zVar) : h2(vVar, zVar);
    }

    private int w2(int i, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i2;
        int i3 = this.v.i() - i;
        if (i3 <= 0) {
            return 0;
        }
        int i4 = -N2(-i3, vVar, zVar);
        int i5 = i + i4;
        if (!z || (i2 = this.v.i() - i5) <= 0) {
            return i4;
        }
        this.v.r(i2);
        return i2 + i4;
    }

    private int x2(int i, RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iM2 = i - this.v.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i2 = -N2(iM2, vVar, zVar);
        int i3 = i + i2;
        if (!z || (iM = i3 - this.v.m()) <= 0) {
            return i2;
        }
        this.v.r(-iM);
        return i2 - iM;
    }

    private View y2() {
        return S(this.y ? 0 : T() - 1);
    }

    private View z2() {
        return S(this.y ? T() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int A(RecyclerView.z zVar) {
        return Y1(zVar);
    }

    @Deprecated
    protected int A2(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.v.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int B(RecyclerView.z zVar) {
        return Z1(zVar);
    }

    public int B2() {
        return this.t;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int C(RecyclerView.z zVar) {
        return a2(zVar);
    }

    protected boolean C2() {
        return j0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int D(RecyclerView.z zVar) {
        return Y1(zVar);
    }

    public boolean D2() {
        return this.A;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int E(RecyclerView.z zVar) {
        return Z1(zVar);
    }

    void E2(RecyclerView.v vVar, RecyclerView.z zVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int paddingLeft;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f1280b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.l == null) {
            if (this.y == (cVar.f == -1)) {
                n(viewD);
            } else {
                o(viewD, 0);
            }
        } else {
            if (this.y == (cVar.f == -1)) {
                l(viewD);
            } else {
                m(viewD, 0);
            }
        }
        I0(viewD, 0, 0);
        bVar.f1279a = this.v.e(viewD);
        if (this.t == 1) {
            if (C2()) {
                iF = u0() - getPaddingRight();
                paddingLeft = iF - this.v.f(viewD);
            } else {
                paddingLeft = getPaddingLeft();
                iF = this.v.f(viewD) + paddingLeft;
            }
            if (cVar.f == -1) {
                int i4 = cVar.f1284b;
                i3 = i4;
                i2 = iF;
                i = i4 - bVar.f1279a;
            } else {
                int i5 = cVar.f1284b;
                i = i5;
                i2 = iF;
                i3 = bVar.f1279a + i5;
            }
        } else {
            int paddingTop = getPaddingTop();
            int iF2 = this.v.f(viewD) + paddingTop;
            if (cVar.f == -1) {
                int i6 = cVar.f1284b;
                i2 = i6;
                i = paddingTop;
                i3 = iF2;
                paddingLeft = i6 - bVar.f1279a;
            } else {
                int i7 = cVar.f1284b;
                i = paddingTop;
                i2 = bVar.f1279a + i7;
                i3 = iF2;
                paddingLeft = i7;
            }
        }
        H0(viewD, paddingLeft, i, i2, i3);
        if (pVar.w() || pVar.v()) {
            bVar.f1281c = true;
        }
        bVar.f1282d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int F(RecyclerView.z zVar) {
        return a2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int F1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.t == 1) {
            return 0;
        }
        return N2(i, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void G1(int i) {
        this.B = i;
        this.C = Integer.MIN_VALUE;
        SavedState savedState = this.E;
        if (savedState != null) {
            savedState.v();
        }
        C1();
    }

    void G2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int H1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.t == 0) {
            return 0;
        }
        return N2(i, vVar, zVar);
    }

    boolean L2() {
        return this.v.k() == 0 && this.v.h() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View M(int i) {
        int iT = T();
        if (iT == 0) {
            return null;
        }
        int iN0 = i - n0(S(0));
        if (iN0 >= 0 && iN0 < iT) {
            View viewS = S(iN0);
            if (n0(viewS) == i) {
                return viewS;
            }
        }
        return super.M(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p N() {
        return new RecyclerView.p(-2, -2);
    }

    int N2(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (T() == 0 || i == 0) {
            return 0;
        }
        d2();
        this.u.f1283a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        U2(i2, iAbs, true, zVar);
        c cVar = this.u;
        int iE2 = cVar.g + e2(vVar, cVar, zVar, false);
        if (iE2 < 0) {
            return 0;
        }
        if (iAbs > iE2) {
            i = i2 * iE2;
        }
        this.v.r(-i);
        this.u.k = i;
        return i;
    }

    public void O2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        q(null);
        if (i != this.t || this.v == null) {
            k kVarB = k.b(this, i);
            this.v = kVarB;
            this.F.f1274a = kVarB;
            this.t = i;
            C1();
        }
    }

    public void P2(boolean z) {
        q(null);
        if (z == this.x) {
            return;
        }
        this.x = z;
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Q0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.Q0(recyclerView, vVar);
        if (this.D) {
            t1(vVar);
            vVar.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    boolean Q1() {
        return (h0() == 1073741824 || v0() == 1073741824 || !w0()) ? false : true;
    }

    public void Q2(boolean z) {
        q(null);
        if (this.z == z) {
            return;
        }
        this.z = z;
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View R0(View view, int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iB2;
        M2();
        if (T() == 0 || (iB2 = b2(i)) == Integer.MIN_VALUE) {
            return null;
        }
        d2();
        U2(iB2, (int) (this.v.n() * 0.33333334f), false, zVar);
        c cVar = this.u;
        cVar.g = Integer.MIN_VALUE;
        cVar.f1283a = false;
        e2(vVar, cVar, zVar, true);
        View viewS2 = iB2 == -1 ? s2() : r2();
        View viewZ2 = iB2 == -1 ? z2() : y2();
        if (!viewZ2.hasFocusable()) {
            return viewS2;
        }
        if (viewS2 == null) {
            return null;
        }
        return viewZ2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(AccessibilityEvent accessibilityEvent) {
        super.S0(accessibilityEvent);
        if (T() > 0) {
            accessibilityEvent.setFromIndex(k2());
            accessibilityEvent.setToIndex(o2());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S1(RecyclerView recyclerView, RecyclerView.z zVar, int i) {
        h hVar = new h(recyclerView.getContext());
        hVar.p(i);
        T1(hVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean V1() {
        return this.E == null && this.w == this.z;
    }

    protected void W1(RecyclerView.z zVar, int[] iArr) {
        int i;
        int iA2 = A2(zVar);
        if (this.u.f == -1) {
            i = 0;
        } else {
            i = iA2;
            iA2 = 0;
        }
        iArr[0] = iA2;
        iArr[1] = i;
    }

    void X1(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i = cVar.f1286d;
        if (i < 0 || i >= zVar.b()) {
            return;
        }
        cVar2.a(i, Math.max(0, cVar.g));
    }

    int b2(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.t == 1) ? 1 : Integer.MIN_VALUE : this.t == 0 ? 1 : Integer.MIN_VALUE : this.t == 1 ? -1 : Integer.MIN_VALUE : this.t == 0 ? -1 : Integer.MIN_VALUE : (this.t != 1 && C2()) ? -1 : 1 : (this.t != 1 && C2()) ? 1 : -1;
    }

    c c2() {
        return new c();
    }

    void d2() {
        if (this.u == null) {
            this.u = c2();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF e(int i) {
        if (T() == 0) {
            return null;
        }
        int i2 = (i < n0(S(0))) != this.y ? -1 : 1;
        return this.t == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    int e2(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z) {
        int i = cVar.f1285c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            H2(vVar, cVar);
        }
        int i3 = cVar.f1285c + cVar.h;
        b bVar = this.G;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            E2(vVar, zVar, cVar, bVar);
            if (!bVar.f1280b) {
                cVar.f1284b += bVar.f1279a * cVar.f;
                if (!bVar.f1281c || cVar.l != null || !zVar.e()) {
                    int i4 = cVar.f1285c;
                    int i5 = bVar.f1279a;
                    cVar.f1285c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.f1279a;
                    cVar.g = i7;
                    int i8 = cVar.f1285c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    H2(vVar, cVar);
                }
                if (z && bVar.f1282d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.f1285c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f1(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int iW2;
        int i5;
        View viewM;
        int iG;
        int i6;
        int i7 = -1;
        if (!(this.E == null && this.B == -1) && zVar.b() == 0) {
            t1(vVar);
            return;
        }
        SavedState savedState = this.E;
        if (savedState != null && savedState.u()) {
            this.B = this.E.f1271b;
        }
        d2();
        this.u.f1283a = false;
        M2();
        View viewF0 = f0();
        a aVar = this.F;
        if (!aVar.f1278e || this.B != -1 || this.E != null) {
            aVar.e();
            a aVar2 = this.F;
            aVar2.f1277d = this.y ^ this.z;
            T2(vVar, zVar, aVar2);
            this.F.f1278e = true;
        } else if (viewF0 != null && (this.v.g(viewF0) >= this.v.i() || this.v.d(viewF0) <= this.v.m())) {
            this.F.c(viewF0, n0(viewF0));
        }
        c cVar = this.u;
        cVar.f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.I;
        iArr[0] = 0;
        iArr[1] = 0;
        W1(zVar, iArr);
        int iMax = Math.max(0, this.I[0]) + this.v.m();
        int iMax2 = Math.max(0, this.I[1]) + this.v.j();
        if (zVar.e() && (i5 = this.B) != -1 && this.C != Integer.MIN_VALUE && (viewM = M(i5)) != null) {
            if (this.y) {
                i6 = this.v.i() - this.v.d(viewM);
                iG = this.C;
            } else {
                iG = this.v.g(viewM) - this.v.m();
                i6 = this.C;
            }
            int i8 = i6 - iG;
            if (i8 > 0) {
                iMax += i8;
            } else {
                iMax2 -= i8;
            }
        }
        a aVar3 = this.F;
        if (!aVar3.f1277d ? !this.y : this.y) {
            i7 = 1;
        }
        G2(vVar, zVar, aVar3, i7);
        G(vVar);
        this.u.m = L2();
        this.u.j = zVar.e();
        this.u.i = 0;
        a aVar4 = this.F;
        if (aVar4.f1277d) {
            Y2(aVar4);
            c cVar2 = this.u;
            cVar2.h = iMax;
            e2(vVar, cVar2, zVar, false);
            c cVar3 = this.u;
            i2 = cVar3.f1284b;
            int i9 = cVar3.f1286d;
            int i10 = cVar3.f1285c;
            if (i10 > 0) {
                iMax2 += i10;
            }
            W2(this.F);
            c cVar4 = this.u;
            cVar4.h = iMax2;
            cVar4.f1286d += cVar4.f1287e;
            e2(vVar, cVar4, zVar, false);
            c cVar5 = this.u;
            i = cVar5.f1284b;
            int i11 = cVar5.f1285c;
            if (i11 > 0) {
                X2(i9, i2);
                c cVar6 = this.u;
                cVar6.h = i11;
                e2(vVar, cVar6, zVar, false);
                i2 = this.u.f1284b;
            }
        } else {
            W2(aVar4);
            c cVar7 = this.u;
            cVar7.h = iMax2;
            e2(vVar, cVar7, zVar, false);
            c cVar8 = this.u;
            i = cVar8.f1284b;
            int i12 = cVar8.f1286d;
            int i13 = cVar8.f1285c;
            if (i13 > 0) {
                iMax += i13;
            }
            Y2(this.F);
            c cVar9 = this.u;
            cVar9.h = iMax;
            cVar9.f1286d += cVar9.f1287e;
            e2(vVar, cVar9, zVar, false);
            c cVar10 = this.u;
            i2 = cVar10.f1284b;
            int i14 = cVar10.f1285c;
            if (i14 > 0) {
                V2(i12, i);
                c cVar11 = this.u;
                cVar11.h = i14;
                e2(vVar, cVar11, zVar, false);
                i = this.u.f1284b;
            }
        }
        if (T() > 0) {
            if (this.y ^ this.z) {
                int iW22 = w2(i, vVar, zVar, true);
                i3 = i2 + iW22;
                i4 = i + iW22;
                iW2 = x2(i3, vVar, zVar, false);
            } else {
                int iX2 = x2(i2, vVar, zVar, true);
                i3 = i2 + iX2;
                i4 = i + iX2;
                iW2 = w2(i4, vVar, zVar, false);
            }
            i2 = i3 + iW2;
            i = i4 + iW2;
        }
        F2(vVar, zVar, i2, i);
        if (zVar.e()) {
            this.F.e();
        } else {
            this.v.s();
        }
        this.w = this.z;
    }

    public int f2() {
        View viewQ2 = q2(0, T(), true, false);
        if (viewQ2 == null) {
            return -1;
        }
        return n0(viewQ2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g1(RecyclerView.z zVar) {
        super.g1(zVar);
        this.E = null;
        this.B = -1;
        this.C = Integer.MIN_VALUE;
        this.F.e();
    }

    View i2(boolean z, boolean z2) {
        return this.y ? q2(0, T(), z, z2) : q2(T() - 1, -1, z, z2);
    }

    View j2(boolean z, boolean z2) {
        return this.y ? q2(T() - 1, -1, z, z2) : q2(0, T(), z, z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void k1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.E = (SavedState) parcelable;
            C1();
        }
    }

    public int k2() {
        View viewQ2 = q2(0, T(), false, true);
        if (viewQ2 == null) {
            return -1;
        }
        return n0(viewQ2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable l1() {
        if (this.E != null) {
            return new SavedState(this.E);
        }
        SavedState savedState = new SavedState();
        if (T() > 0) {
            d2();
            boolean z = this.w ^ this.y;
            savedState.f1273d = z;
            if (z) {
                View viewY2 = y2();
                savedState.f1272c = this.v.i() - this.v.d(viewY2);
                savedState.f1271b = n0(viewY2);
            } else {
                View viewZ2 = z2();
                savedState.f1271b = n0(viewZ2);
                savedState.f1272c = this.v.g(viewZ2) - this.v.m();
            }
        } else {
            savedState.v();
        }
        return savedState;
    }

    public int l2() {
        View viewQ2 = q2(T() - 1, -1, true, false);
        if (viewQ2 == null) {
            return -1;
        }
        return n0(viewQ2);
    }

    public int o2() {
        View viewQ2 = q2(T() - 1, -1, false, true);
        if (viewQ2 == null) {
            return -1;
        }
        return n0(viewQ2);
    }

    View p2(int i, int i2) {
        int i3;
        int i4;
        d2();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return S(i);
        }
        if (this.v.g(S(i)) < this.v.m()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.t == 0 ? this.f.a(i, i2, i3, i4) : this.g.a(i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void q(String str) {
        if (this.E == null) {
            super.q(str);
        }
    }

    View q2(int i, int i2, boolean z, boolean z2) {
        d2();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        return this.t == 0 ? this.f.a(i, i2, i3, i4) : this.g.a(i, i2, i3, i4);
    }

    View t2(RecyclerView.v vVar, RecyclerView.z zVar, int i, int i2, int i3) {
        d2();
        int iM = this.v.m();
        int i4 = this.v.i();
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
                    if (this.v.g(viewS) < i4 && this.v.d(viewS) >= iM) {
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean u() {
        return this.t == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean v() {
        return this.t == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void y(int i, int i2, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.t != 0) {
            i = i2;
        }
        if (T() == 0 || i == 0) {
            return;
        }
        d2();
        U2(i > 0 ? 1 : -1, Math.abs(i), true, zVar);
        X1(zVar, this.u, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean y0() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void z(int i, RecyclerView.o.c cVar) {
        boolean z;
        int i2;
        SavedState savedState = this.E;
        if (savedState == null || !savedState.u()) {
            M2();
            z = this.y;
            i2 = this.B;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.E;
            z = savedState2.f1273d;
            i2 = savedState2.f1271b;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.H && i2 >= 0 && i2 < i; i4++) {
            cVar.a(i2, 0);
            i2 += i3;
        }
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.t = 1;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = true;
        this.B = -1;
        this.C = Integer.MIN_VALUE;
        this.E = null;
        this.F = new a();
        this.G = new b();
        this.H = 2;
        this.I = new int[2];
        O2(i);
        P2(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.t = 1;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = true;
        this.B = -1;
        this.C = Integer.MIN_VALUE;
        this.E = null;
        this.F = new a();
        this.G = new b();
        this.H = 2;
        this.I = new int[2];
        RecyclerView.o.d dVarO0 = RecyclerView.o.o0(context, attributeSet, i, i2);
        O2(dVarO0.f1325a);
        P2(dVarO0.f1327c);
        Q2(dVarO0.f1328d);
    }
}
