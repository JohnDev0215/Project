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

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    int f2766s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private c f2767t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    m f2768u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f2769v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f2770w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    boolean f2771x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f2772y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f2773z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2774e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2775g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f2776h;

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState() {
        }

        boolean b() {
            return this.f2774e >= 0;
        }

        void c() {
            this.f2774e = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f2774e);
            parcel.writeInt(this.f2775g);
            parcel.writeInt(this.f2776h ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f2774e = parcel.readInt();
            this.f2775g = parcel.readInt();
            this.f2776h = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f2774e = savedState.f2774e;
            this.f2775g = savedState.f2775g;
            this.f2776h = savedState.f2776h;
        }
    }

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        m f2777a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2778b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2779c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2780d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2781e;

        a() {
            e();
        }

        void a() {
            this.f2779c = this.f2780d ? this.f2777a.i() : this.f2777a.m();
        }

        public void b(View view, int i7) {
            if (this.f2780d) {
                this.f2779c = this.f2777a.d(view) + this.f2777a.o();
            } else {
                this.f2779c = this.f2777a.g(view);
            }
            this.f2778b = i7;
        }

        public void c(View view, int i7) {
            int iO = this.f2777a.o();
            if (iO >= 0) {
                b(view, i7);
                return;
            }
            this.f2778b = i7;
            if (this.f2780d) {
                int i8 = (this.f2777a.i() - iO) - this.f2777a.d(view);
                this.f2779c = this.f2777a.i() - i8;
                if (i8 > 0) {
                    int iE = this.f2779c - this.f2777a.e(view);
                    int iM = this.f2777a.m();
                    int iMin = iE - (iM + Math.min(this.f2777a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f2779c += Math.min(i8, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f2777a.g(view);
            int iM2 = iG - this.f2777a.m();
            this.f2779c = iG;
            if (iM2 > 0) {
                int i9 = (this.f2777a.i() - Math.min(0, (this.f2777a.i() - iO) - this.f2777a.d(view))) - (iG + this.f2777a.e(view));
                if (i9 < 0) {
                    this.f2779c -= Math.min(iM2, -i9);
                }
            }
        }

        boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < zVar.b();
        }

        void e() {
            this.f2778b = -1;
            this.f2779c = Integer.MIN_VALUE;
            this.f2780d = false;
            this.f2781e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f2778b + ", mCoordinate=" + this.f2779c + ", mLayoutFromEnd=" + this.f2780d + ", mValid=" + this.f2781e + '}';
        }
    }

    protected static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2782a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2783b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2784c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2785d;

        protected b() {
        }

        void a() {
            this.f2782a = 0;
            this.f2783b = false;
            this.f2784c = false;
            this.f2785d = false;
        }
    }

    static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2787b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2788c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2789d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2790e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2791f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2792g;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        int f2796k;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        boolean f2798m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f2786a = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f2793h = 0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f2794i = 0;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f2795j = false;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        List<RecyclerView.c0> f2797l = null;

        c() {
        }

        private View e() {
            int size = this.f2797l.size();
            for (int i7 = 0; i7 < size; i7++) {
                View view = this.f2797l.get(i7).f2857a;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f2789d == pVar.a()) {
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
                this.f2789d = -1;
            } else {
                this.f2789d = ((RecyclerView.p) viewF.getLayoutParams()).a();
            }
        }

        boolean c(RecyclerView.z zVar) {
            int i7 = this.f2789d;
            return i7 >= 0 && i7 < zVar.b();
        }

        View d(RecyclerView.v vVar) {
            if (this.f2797l != null) {
                return e();
            }
            View viewO = vVar.o(this.f2789d);
            this.f2789d += this.f2790e;
            return viewO;
        }

        public View f(View view) {
            int iA;
            int size = this.f2797l.size();
            View view2 = null;
            int i7 = Integer.MAX_VALUE;
            for (int i8 = 0; i8 < size; i8++) {
                View view3 = this.f2797l.get(i8).f2857a;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (iA = (pVar.a() - this.f2789d) * this.f2790e) >= 0 && iA < i7) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i7 = iA;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private void A2() {
        if (this.f2766s == 1 || !q2()) {
            this.f2771x = this.f2770w;
        } else {
            this.f2771x = !this.f2770w;
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
        if (this.f2769v != this.f2772y) {
            return false;
        }
        View viewI2 = aVar.f2780d ? i2(vVar, zVar) : j2(vVar, zVar);
        if (viewI2 == null) {
            return false;
        }
        aVar.b(viewI2, h0(viewI2));
        if (!zVar.e() && L1()) {
            if (this.f2768u.g(viewI2) >= this.f2768u.i() || this.f2768u.d(viewI2) < this.f2768u.m()) {
                aVar.f2779c = aVar.f2780d ? this.f2768u.i() : this.f2768u.m();
            }
        }
        return true;
    }

    private boolean G2(RecyclerView.z zVar, a aVar) {
        int i7;
        if (!zVar.e() && (i7 = this.A) != -1) {
            if (i7 >= 0 && i7 < zVar.b()) {
                aVar.f2778b = this.A;
                SavedState savedState = this.D;
                if (savedState != null && savedState.b()) {
                    boolean z6 = this.D.f2776h;
                    aVar.f2780d = z6;
                    if (z6) {
                        aVar.f2779c = this.f2768u.i() - this.D.f2775g;
                    } else {
                        aVar.f2779c = this.f2768u.m() + this.D.f2775g;
                    }
                    return true;
                }
                if (this.B != Integer.MIN_VALUE) {
                    boolean z7 = this.f2771x;
                    aVar.f2780d = z7;
                    if (z7) {
                        aVar.f2779c = this.f2768u.i() - this.B;
                    } else {
                        aVar.f2779c = this.f2768u.m() + this.B;
                    }
                    return true;
                }
                View viewC = C(this.A);
                if (viewC == null) {
                    if (J() > 0) {
                        aVar.f2780d = (this.A < h0(I(0))) == this.f2771x;
                    }
                    aVar.a();
                } else {
                    if (this.f2768u.e(viewC) > this.f2768u.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.f2768u.g(viewC) - this.f2768u.m() < 0) {
                        aVar.f2779c = this.f2768u.m();
                        aVar.f2780d = false;
                        return true;
                    }
                    if (this.f2768u.i() - this.f2768u.d(viewC) < 0) {
                        aVar.f2779c = this.f2768u.i();
                        aVar.f2780d = true;
                        return true;
                    }
                    aVar.f2779c = aVar.f2780d ? this.f2768u.d(viewC) + this.f2768u.o() : this.f2768u.g(viewC);
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
        aVar.f2778b = this.f2772y ? zVar.b() - 1 : 0;
    }

    private void I2(int i7, int i8, boolean z6, RecyclerView.z zVar) {
        int iM;
        this.f2767t.f2798m = z2();
        this.f2767t.f2791f = i7;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]);
        int iMax2 = Math.max(0, this.H[1]);
        boolean z7 = i7 == 1;
        c cVar = this.f2767t;
        int i9 = z7 ? iMax2 : iMax;
        cVar.f2793h = i9;
        if (!z7) {
            iMax = iMax2;
        }
        cVar.f2794i = iMax;
        if (z7) {
            cVar.f2793h = i9 + this.f2768u.j();
            View viewM2 = m2();
            c cVar2 = this.f2767t;
            cVar2.f2790e = this.f2771x ? -1 : 1;
            int iH0 = h0(viewM2);
            c cVar3 = this.f2767t;
            cVar2.f2789d = iH0 + cVar3.f2790e;
            cVar3.f2787b = this.f2768u.d(viewM2);
            iM = this.f2768u.d(viewM2) - this.f2768u.i();
        } else {
            View viewN2 = n2();
            this.f2767t.f2793h += this.f2768u.m();
            c cVar4 = this.f2767t;
            cVar4.f2790e = this.f2771x ? 1 : -1;
            int iH02 = h0(viewN2);
            c cVar5 = this.f2767t;
            cVar4.f2789d = iH02 + cVar5.f2790e;
            cVar5.f2787b = this.f2768u.g(viewN2);
            iM = (-this.f2768u.g(viewN2)) + this.f2768u.m();
        }
        c cVar6 = this.f2767t;
        cVar6.f2788c = i8;
        if (z6) {
            cVar6.f2788c = i8 - iM;
        }
        cVar6.f2792g = iM;
    }

    private void J2(int i7, int i8) {
        this.f2767t.f2788c = this.f2768u.i() - i8;
        c cVar = this.f2767t;
        cVar.f2790e = this.f2771x ? -1 : 1;
        cVar.f2789d = i7;
        cVar.f2791f = 1;
        cVar.f2787b = i8;
        cVar.f2792g = Integer.MIN_VALUE;
    }

    private void K2(a aVar) {
        J2(aVar.f2778b, aVar.f2779c);
    }

    private void L2(int i7, int i8) {
        this.f2767t.f2788c = i8 - this.f2768u.m();
        c cVar = this.f2767t;
        cVar.f2789d = i7;
        cVar.f2790e = this.f2771x ? 1 : -1;
        cVar.f2791f = -1;
        cVar.f2787b = i8;
        cVar.f2792g = Integer.MIN_VALUE;
    }

    private void M2(a aVar) {
        L2(aVar.f2778b, aVar.f2779c);
    }

    private int O1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return p.a(zVar, this.f2768u, Y1(!this.f2773z, true), X1(!this.f2773z, true), this, this.f2773z);
    }

    private int P1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return p.b(zVar, this.f2768u, Y1(!this.f2773z, true), X1(!this.f2773z, true), this, this.f2773z, this.f2771x);
    }

    private int Q1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        T1();
        return p.c(zVar, this.f2768u, Y1(!this.f2773z, true), X1(!this.f2773z, true), this, this.f2773z);
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
        return this.f2771x ? V1() : a2();
    }

    private View g2() {
        return this.f2771x ? a2() : V1();
    }

    private View i2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f2771x ? W1(vVar, zVar) : b2(vVar, zVar);
    }

    private View j2(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f2771x ? b2(vVar, zVar) : W1(vVar, zVar);
    }

    private int k2(int i7, RecyclerView.v vVar, RecyclerView.z zVar, boolean z6) {
        int i8;
        int i9 = this.f2768u.i() - i7;
        if (i9 <= 0) {
            return 0;
        }
        int i10 = -B2(-i9, vVar, zVar);
        int i11 = i7 + i10;
        if (!z6 || (i8 = this.f2768u.i() - i11) <= 0) {
            return i10;
        }
        this.f2768u.r(i8);
        return i8 + i10;
    }

    private int l2(int i7, RecyclerView.v vVar, RecyclerView.z zVar, boolean z6) {
        int iM;
        int iM2 = i7 - this.f2768u.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i8 = -B2(iM2, vVar, zVar);
        int i9 = i7 + i8;
        if (!z6 || (iM = i9 - this.f2768u.m()) <= 0) {
            return i8;
        }
        this.f2768u.r(-iM);
        return i8 - iM;
    }

    private View m2() {
        return I(this.f2771x ? 0 : J() - 1);
    }

    private View n2() {
        return I(this.f2771x ? J() - 1 : 0);
    }

    private void t2(RecyclerView.v vVar, RecyclerView.z zVar, int i7, int i8) {
        if (!zVar.g() || J() == 0 || zVar.e() || !L1()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int iH0 = h0(I(0));
        int iE = 0;
        int iE2 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            RecyclerView.c0 c0Var = listK.get(i9);
            if (!c0Var.v()) {
                if (((c0Var.m() < iH0) != this.f2771x ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.f2768u.e(c0Var.f2857a);
                } else {
                    iE2 += this.f2768u.e(c0Var.f2857a);
                }
            }
        }
        this.f2767t.f2797l = listK;
        if (iE > 0) {
            L2(h0(n2()), i7);
            c cVar = this.f2767t;
            cVar.f2793h = iE;
            cVar.f2788c = 0;
            cVar.a();
            U1(vVar, this.f2767t, zVar, false);
        }
        if (iE2 > 0) {
            J2(h0(m2()), i8);
            c cVar2 = this.f2767t;
            cVar2.f2793h = iE2;
            cVar2.f2788c = 0;
            cVar2.a();
            U1(vVar, this.f2767t, zVar, false);
        }
        this.f2767t.f2797l = null;
    }

    private void v2(RecyclerView.v vVar, c cVar) {
        if (!cVar.f2786a || cVar.f2798m) {
            return;
        }
        int i7 = cVar.f2792g;
        int i8 = cVar.f2794i;
        if (cVar.f2791f == -1) {
            x2(vVar, i7, i8);
        } else {
            y2(vVar, i7, i8);
        }
    }

    private void w2(RecyclerView.v vVar, int i7, int i8) {
        if (i7 == i8) {
            return;
        }
        if (i8 <= i7) {
            while (i7 > i8) {
                n1(i7, vVar);
                i7--;
            }
        } else {
            for (int i9 = i8 - 1; i9 >= i7; i9--) {
                n1(i9, vVar);
            }
        }
    }

    private void x2(RecyclerView.v vVar, int i7, int i8) {
        int iJ = J();
        if (i7 < 0) {
            return;
        }
        int iH = (this.f2768u.h() - i7) + i8;
        if (this.f2771x) {
            for (int i9 = 0; i9 < iJ; i9++) {
                View viewI = I(i9);
                if (this.f2768u.g(viewI) < iH || this.f2768u.q(viewI) < iH) {
                    w2(vVar, 0, i9);
                    return;
                }
            }
            return;
        }
        int i10 = iJ - 1;
        for (int i11 = i10; i11 >= 0; i11--) {
            View viewI2 = I(i11);
            if (this.f2768u.g(viewI2) < iH || this.f2768u.q(viewI2) < iH) {
                w2(vVar, i10, i11);
                return;
            }
        }
    }

    private void y2(RecyclerView.v vVar, int i7, int i8) {
        if (i7 < 0) {
            return;
        }
        int i9 = i7 - i8;
        int iJ = J();
        if (!this.f2771x) {
            for (int i10 = 0; i10 < iJ; i10++) {
                View viewI = I(i10);
                if (this.f2768u.d(viewI) > i9 || this.f2768u.p(viewI) > i9) {
                    w2(vVar, 0, i10);
                    return;
                }
            }
            return;
        }
        int i11 = iJ - 1;
        for (int i12 = i11; i12 >= 0; i12--) {
            View viewI2 = I(i12);
            if (this.f2768u.d(viewI2) > i9 || this.f2768u.p(viewI2) > i9) {
                w2(vVar, i11, i12);
                return;
            }
        }
    }

    int B2(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i7 == 0) {
            return 0;
        }
        T1();
        this.f2767t.f2786a = true;
        int i8 = i7 > 0 ? 1 : -1;
        int iAbs = Math.abs(i7);
        I2(i8, iAbs, true, zVar);
        c cVar = this.f2767t;
        int iU1 = cVar.f2792g + U1(vVar, cVar, zVar, false);
        if (iU1 < 0) {
            return 0;
        }
        if (iAbs > iU1) {
            i7 = i8 * iU1;
        }
        this.f2768u.r(-i7);
        this.f2767t.f2796k = i7;
        return i7;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View C(int i7) {
        int iJ = J();
        if (iJ == 0) {
            return null;
        }
        int iH0 = i7 - h0(I(0));
        if (iH0 >= 0 && iH0 < iJ) {
            View viewI = I(iH0);
            if (h0(viewI) == i7) {
                return viewI;
            }
        }
        return super.C(i7);
    }

    public void C2(int i7) {
        if (i7 != 0 && i7 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i7);
        }
        g(null);
        if (i7 != this.f2766s || this.f2768u == null) {
            m mVarB = m.b(this, i7);
            this.f2768u = mVarB;
            this.E.f2777a = mVarB;
            this.f2766s = i7;
            t1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return new RecyclerView.p(-2, -2);
    }

    public void D2(boolean z6) {
        g(null);
        if (z6 == this.f2770w) {
            return;
        }
        this.f2770w = z6;
        t1();
    }

    public void E2(boolean z6) {
        g(null);
        if (this.f2772y == z6) {
            return;
        }
        this.f2772y = z6;
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
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i7) {
        j jVar = new j(recyclerView.getContext());
        jVar.p(i7);
        J1(jVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iR1;
        A2();
        if (J() == 0 || (iR1 = R1(i7)) == Integer.MIN_VALUE) {
            return null;
        }
        T1();
        I2(iR1, (int) (this.f2768u.n() * 0.33333334f), false, zVar);
        c cVar = this.f2767t;
        cVar.f2792g = Integer.MIN_VALUE;
        cVar.f2786a = false;
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
        return this.D == null && this.f2769v == this.f2772y;
    }

    protected void M1(RecyclerView.z zVar, int[] iArr) {
        int i7;
        int iO2 = o2(zVar);
        if (this.f2767t.f2791f == -1) {
            i7 = 0;
        } else {
            i7 = iO2;
            iO2 = 0;
        }
        iArr[0] = iO2;
        iArr[1] = i7;
    }

    void N1(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i7 = cVar.f2789d;
        if (i7 < 0 || i7 >= zVar.b()) {
            return;
        }
        cVar2.a(i7, Math.max(0, cVar.f2792g));
    }

    int R1(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 17 ? i7 != 33 ? i7 != 66 ? (i7 == 130 && this.f2766s == 1) ? 1 : Integer.MIN_VALUE : this.f2766s == 0 ? 1 : Integer.MIN_VALUE : this.f2766s == 1 ? -1 : Integer.MIN_VALUE : this.f2766s == 0 ? -1 : Integer.MIN_VALUE : (this.f2766s != 1 && q2()) ? -1 : 1 : (this.f2766s != 1 && q2()) ? 1 : -1;
    }

    c S1() {
        return new c();
    }

    void T1() {
        if (this.f2767t == null) {
            this.f2767t = S1();
        }
    }

    int U1(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z6) {
        int i7 = cVar.f2788c;
        int i8 = cVar.f2792g;
        if (i8 != Integer.MIN_VALUE) {
            if (i7 < 0) {
                cVar.f2792g = i8 + i7;
            }
            v2(vVar, cVar);
        }
        int i9 = cVar.f2788c + cVar.f2793h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.f2798m && i9 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            s2(vVar, zVar, cVar, bVar);
            if (!bVar.f2783b) {
                cVar.f2787b += bVar.f2782a * cVar.f2791f;
                if (!bVar.f2784c || cVar.f2797l != null || !zVar.e()) {
                    int i10 = cVar.f2788c;
                    int i11 = bVar.f2782a;
                    cVar.f2788c = i10 - i11;
                    i9 -= i11;
                }
                int i12 = cVar.f2792g;
                if (i12 != Integer.MIN_VALUE) {
                    int i13 = i12 + bVar.f2782a;
                    cVar.f2792g = i13;
                    int i14 = cVar.f2788c;
                    if (i14 < 0) {
                        cVar.f2792g = i13 + i14;
                    }
                    v2(vVar, cVar);
                }
                if (z6 && bVar.f2785d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i7 - cVar.f2788c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i7;
        int i8;
        int i9;
        int i10;
        int iK2;
        int i11;
        View viewC;
        int iG;
        int i12;
        int i13 = -1;
        if (!(this.D == null && this.A == -1) && zVar.b() == 0) {
            k1(vVar);
            return;
        }
        SavedState savedState = this.D;
        if (savedState != null && savedState.b()) {
            this.A = this.D.f2774e;
        }
        T1();
        this.f2767t.f2786a = false;
        A2();
        View viewV = V();
        a aVar = this.E;
        if (!aVar.f2781e || this.A != -1 || this.D != null) {
            aVar.e();
            a aVar2 = this.E;
            aVar2.f2780d = this.f2771x ^ this.f2772y;
            H2(vVar, zVar, aVar2);
            this.E.f2781e = true;
        } else if (viewV != null && (this.f2768u.g(viewV) >= this.f2768u.i() || this.f2768u.d(viewV) <= this.f2768u.m())) {
            this.E.c(viewV, h0(viewV));
        }
        c cVar = this.f2767t;
        cVar.f2791f = cVar.f2796k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        M1(zVar, iArr);
        int iMax = Math.max(0, this.H[0]) + this.f2768u.m();
        int iMax2 = Math.max(0, this.H[1]) + this.f2768u.j();
        if (zVar.e() && (i11 = this.A) != -1 && this.B != Integer.MIN_VALUE && (viewC = C(i11)) != null) {
            if (this.f2771x) {
                i12 = this.f2768u.i() - this.f2768u.d(viewC);
                iG = this.B;
            } else {
                iG = this.f2768u.g(viewC) - this.f2768u.m();
                i12 = this.B;
            }
            int i14 = i12 - iG;
            if (i14 > 0) {
                iMax += i14;
            } else {
                iMax2 -= i14;
            }
        }
        a aVar3 = this.E;
        if (!aVar3.f2780d ? !this.f2771x : this.f2771x) {
            i13 = 1;
        }
        u2(vVar, zVar, aVar3, i13);
        w(vVar);
        this.f2767t.f2798m = z2();
        this.f2767t.f2795j = zVar.e();
        this.f2767t.f2794i = 0;
        a aVar4 = this.E;
        if (aVar4.f2780d) {
            M2(aVar4);
            c cVar2 = this.f2767t;
            cVar2.f2793h = iMax;
            U1(vVar, cVar2, zVar, false);
            c cVar3 = this.f2767t;
            i8 = cVar3.f2787b;
            int i15 = cVar3.f2789d;
            int i16 = cVar3.f2788c;
            if (i16 > 0) {
                iMax2 += i16;
            }
            K2(this.E);
            c cVar4 = this.f2767t;
            cVar4.f2793h = iMax2;
            cVar4.f2789d += cVar4.f2790e;
            U1(vVar, cVar4, zVar, false);
            c cVar5 = this.f2767t;
            i7 = cVar5.f2787b;
            int i17 = cVar5.f2788c;
            if (i17 > 0) {
                L2(i15, i8);
                c cVar6 = this.f2767t;
                cVar6.f2793h = i17;
                U1(vVar, cVar6, zVar, false);
                i8 = this.f2767t.f2787b;
            }
        } else {
            K2(aVar4);
            c cVar7 = this.f2767t;
            cVar7.f2793h = iMax2;
            U1(vVar, cVar7, zVar, false);
            c cVar8 = this.f2767t;
            i7 = cVar8.f2787b;
            int i18 = cVar8.f2789d;
            int i19 = cVar8.f2788c;
            if (i19 > 0) {
                iMax += i19;
            }
            M2(this.E);
            c cVar9 = this.f2767t;
            cVar9.f2793h = iMax;
            cVar9.f2789d += cVar9.f2790e;
            U1(vVar, cVar9, zVar, false);
            c cVar10 = this.f2767t;
            i8 = cVar10.f2787b;
            int i20 = cVar10.f2788c;
            if (i20 > 0) {
                J2(i18, i7);
                c cVar11 = this.f2767t;
                cVar11.f2793h = i20;
                U1(vVar, cVar11, zVar, false);
                i7 = this.f2767t.f2787b;
            }
        }
        if (J() > 0) {
            if (this.f2771x ^ this.f2772y) {
                int iK22 = k2(i7, vVar, zVar, true);
                i9 = i8 + iK22;
                i10 = i7 + iK22;
                iK2 = l2(i9, vVar, zVar, false);
            } else {
                int iL2 = l2(i8, vVar, zVar, true);
                i9 = i8 + iL2;
                i10 = i7 + iL2;
                iK2 = k2(i10, vVar, zVar, false);
            }
            i8 = i9 + iK2;
            i7 = i10 + iK2;
        }
        t2(vVar, zVar, i8, i7);
        if (zVar.e()) {
            this.E.e();
        } else {
            this.f2768u.s();
        }
        this.f2769v = this.f2772y;
    }

    View X1(boolean z6, boolean z7) {
        return this.f2771x ? e2(0, J(), z6, z7) : e2(J() - 1, -1, z6, z7);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.D = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.E.e();
    }

    View Y1(boolean z6, boolean z7) {
        return this.f2771x ? e2(J() - 1, -1, z6, z7) : e2(0, J(), z6, z7);
    }

    public int Z1() {
        View viewE2 = e2(0, J(), false, true);
        if (viewE2 == null) {
            return -1;
        }
        return h0(viewE2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF a(int i7) {
        if (J() == 0) {
            return null;
        }
        int i8 = (i7 < h0(I(0))) != this.f2771x ? -1 : 1;
        return this.f2766s == 0 ? new PointF(i8, 0.0f) : new PointF(0.0f, i8);
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
            boolean z6 = this.f2769v ^ this.f2771x;
            savedState.f2776h = z6;
            if (z6) {
                View viewM2 = m2();
                savedState.f2775g = this.f2768u.i() - this.f2768u.d(viewM2);
                savedState.f2774e = h0(viewM2);
            } else {
                View viewN2 = n2();
                savedState.f2774e = h0(viewN2);
                savedState.f2775g = this.f2768u.g(viewN2) - this.f2768u.m();
            }
        } else {
            savedState.c();
        }
        return savedState;
    }

    View d2(int i7, int i8) {
        int i9;
        int i10;
        T1();
        if ((i8 > i7 ? (byte) 1 : i8 < i7 ? (byte) -1 : (byte) 0) == 0) {
            return I(i7);
        }
        if (this.f2768u.g(I(i7)) < this.f2768u.m()) {
            i9 = 16644;
            i10 = 16388;
        } else {
            i9 = 4161;
            i10 = 4097;
        }
        return this.f2766s == 0 ? this.f2895e.a(i7, i8, i9, i10) : this.f2896f.a(i7, i8, i9, i10);
    }

    View e2(int i7, int i8, boolean z6, boolean z7) {
        T1();
        int i9 = z6 ? 24579 : 320;
        int i10 = z7 ? 320 : 0;
        return this.f2766s == 0 ? this.f2895e.a(i7, i8, i9, i10) : this.f2896f.a(i7, i8, i9, i10);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(String str) {
        if (this.D == null) {
            super.g(str);
        }
    }

    View h2(RecyclerView.v vVar, RecyclerView.z zVar, int i7, int i8, int i9) {
        T1();
        int iM = this.f2768u.m();
        int i10 = this.f2768u.i();
        int i11 = i8 > i7 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i7 != i8) {
            View viewI = I(i7);
            int iH0 = h0(viewI);
            if (iH0 >= 0 && iH0 < i9) {
                if (((RecyclerView.p) viewI.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewI;
                    }
                } else {
                    if (this.f2768u.g(viewI) < i10 && this.f2768u.d(viewI) >= iM) {
                        return viewI;
                    }
                    if (view == null) {
                        view = viewI;
                    }
                }
            }
            i7 += i11;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.f2766s == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l() {
        return this.f2766s == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i7, int i8, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.f2766s != 0) {
            i7 = i8;
        }
        if (J() == 0 || i7 == 0) {
            return;
        }
        T1();
        I2(i7 > 0 ? 1 : -1, Math.abs(i7), true, zVar);
        N1(zVar, this.f2767t, cVar);
    }

    @Deprecated
    protected int o2(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.f2768u.n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void p(int i7, RecyclerView.o.c cVar) {
        boolean z6;
        int i8;
        SavedState savedState = this.D;
        if (savedState == null || !savedState.b()) {
            A2();
            z6 = this.f2771x;
            i8 = this.A;
            if (i8 == -1) {
                i8 = z6 ? i7 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.D;
            z6 = savedState2.f2776h;
            i8 = savedState2.f2774e;
        }
        int i9 = z6 ? -1 : 1;
        for (int i10 = 0; i10 < this.G && i8 >= 0 && i8 < i7; i10++) {
            cVar.a(i8, 0);
            i8 += i9;
        }
    }

    public int p2() {
        return this.f2766s;
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
        return this.f2773z;
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
        int i7;
        int i8;
        int i9;
        int iE0;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f2783b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.f2797l == null) {
            if (this.f2771x == (cVar.f2791f == -1)) {
                d(viewD);
            } else {
                e(viewD, 0);
            }
        } else {
            if (this.f2771x == (cVar.f2791f == -1)) {
                b(viewD);
            } else {
                c(viewD, 0);
            }
        }
        A0(viewD, 0, 0);
        bVar.f2782a = this.f2768u.e(viewD);
        if (this.f2766s == 1) {
            if (q2()) {
                iF = o0() - f0();
                iE0 = iF - this.f2768u.f(viewD);
            } else {
                iE0 = e0();
                iF = this.f2768u.f(viewD) + iE0;
            }
            if (cVar.f2791f == -1) {
                int i10 = cVar.f2787b;
                i9 = i10;
                i8 = iF;
                i7 = i10 - bVar.f2782a;
            } else {
                int i11 = cVar.f2787b;
                i7 = i11;
                i8 = iF;
                i9 = bVar.f2782a + i11;
            }
        } else {
            int iG0 = g0();
            int iF2 = this.f2768u.f(viewD) + iG0;
            if (cVar.f2791f == -1) {
                int i12 = cVar.f2787b;
                i8 = i12;
                i7 = iG0;
                i9 = iF2;
                iE0 = i12 - bVar.f2782a;
            } else {
                int i13 = cVar.f2787b;
                i7 = iG0;
                i8 = bVar.f2782a + i13;
                i9 = iF2;
                iE0 = i13;
            }
        }
        z0(viewD, iE0, i7, i8, i9);
        if (pVar.c() || pVar.b()) {
            bVar.f2784c = true;
        }
        bVar.f2785d = viewD.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return O1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return P1(zVar);
    }

    void u2(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i7) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f2766s == 1) {
            return 0;
        }
        return B2(i7, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i7) {
        this.A = i7;
        this.B = Integer.MIN_VALUE;
        SavedState savedState = this.D;
        if (savedState != null) {
            savedState.c();
        }
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f2766s == 0) {
            return 0;
        }
        return B2(i7, vVar, zVar);
    }

    boolean z2() {
        return this.f2768u.k() == 0 && this.f2768u.h() == 0;
    }

    public LinearLayoutManager(Context context, int i7, boolean z6) {
        this.f2766s = 1;
        this.f2770w = false;
        this.f2771x = false;
        this.f2772y = false;
        this.f2773z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        C2(i7);
        D2(z6);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        this.f2766s = 1;
        this.f2770w = false;
        this.f2771x = false;
        this.f2772y = false;
        this.f2773z = true;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i7, i8);
        C2(dVarI0.f2911a);
        D2(dVarI0.f2913c);
        E2(dVarI0.f2914d);
    }
}
