package androidx.recyclerview.widget;

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
import b.g.k.c0.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.y.b {
    private BitSet B;
    private boolean G;
    private boolean H;
    private SavedState I;
    private int J;
    private int[] O;
    d[] t;
    j u;
    j v;
    private int w;
    private int x;
    private final f y;
    private int s = -1;
    boolean z = false;
    boolean A = false;
    int C = -1;
    int D = Integer.MIN_VALUE;
    LazySpanLookup E = new LazySpanLookup();
    private int F = 2;
    private final Rect K = new Rect();
    private final b L = new b();
    private boolean M = false;
    private boolean N = true;
    private final Runnable P = new a();

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1879b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1880c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1881d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int[] f1882e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1883f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int[] f1884g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        List<LazySpanLookup.FullSpanItem> f1885h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f1886i;
        boolean j;
        boolean k;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState() {
        }

        void a() {
            this.f1882e = null;
            this.f1881d = 0;
            this.f1879b = -1;
            this.f1880c = -1;
        }

        void c() {
            this.f1882e = null;
            this.f1881d = 0;
            this.f1883f = 0;
            this.f1884g = null;
            this.f1885h = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1879b);
            parcel.writeInt(this.f1880c);
            parcel.writeInt(this.f1881d);
            if (this.f1881d > 0) {
                parcel.writeIntArray(this.f1882e);
            }
            parcel.writeInt(this.f1883f);
            if (this.f1883f > 0) {
                parcel.writeIntArray(this.f1884g);
            }
            parcel.writeInt(this.f1886i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeList(this.f1885h);
        }

        SavedState(Parcel parcel) {
            this.f1879b = parcel.readInt();
            this.f1880c = parcel.readInt();
            int i2 = parcel.readInt();
            this.f1881d = i2;
            if (i2 > 0) {
                int[] iArr = new int[i2];
                this.f1882e = iArr;
                parcel.readIntArray(iArr);
            }
            int i3 = parcel.readInt();
            this.f1883f = i3;
            if (i3 > 0) {
                int[] iArr2 = new int[i3];
                this.f1884g = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f1886i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.k = parcel.readInt() == 1;
            this.f1885h = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f1881d = savedState.f1881d;
            this.f1879b = savedState.f1879b;
            this.f1880c = savedState.f1880c;
            this.f1882e = savedState.f1882e;
            this.f1883f = savedState.f1883f;
            this.f1884g = savedState.f1884g;
            this.f1886i = savedState.f1886i;
            this.j = savedState.j;
            this.k = savedState.k;
            this.f1885h = savedState.f1885h;
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.S1();
        }
    }

    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1888a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1889b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1890c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1891d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1892e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int[] f1893f;

        b() {
            c();
        }

        void a() {
            this.f1889b = this.f1890c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        void b(int i2) {
            if (this.f1890c) {
                this.f1889b = StaggeredGridLayoutManager.this.u.i() - i2;
            } else {
                this.f1889b = StaggeredGridLayoutManager.this.u.m() + i2;
            }
        }

        void c() {
            this.f1888a = -1;
            this.f1889b = Integer.MIN_VALUE;
            this.f1890c = false;
            this.f1891d = false;
            this.f1892e = false;
            int[] iArr = this.f1893f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f1893f;
            if (iArr == null || iArr.length < length) {
                this.f1893f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.f1893f[i2] = dVarArr[i2].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        d f1895e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f1896f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f1895e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f1901e;
        }

        public boolean f() {
            return this.f1896f;
        }

        public c(int i2, int i3) {
            super(i2, i3);
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ArrayList<View> f1897a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1898b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1899c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1900d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f1901e;

        d(int i2) {
            this.f1901e = i2;
        }

        void a(View view) {
            c cVarN = n(view);
            cVarN.f1895e = this;
            this.f1897a.add(view);
            this.f1899c = Integer.MIN_VALUE;
            if (this.f1897a.size() == 1) {
                this.f1898b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1900d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void b(boolean z, int i2) {
            int iL = z ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (iL == Integer.MIN_VALUE) {
                return;
            }
            if (!z || iL >= StaggeredGridLayoutManager.this.u.i()) {
                if (z || iL <= StaggeredGridLayoutManager.this.u.m()) {
                    if (i2 != Integer.MIN_VALUE) {
                        iL += i2;
                    }
                    this.f1899c = iL;
                    this.f1898b = iL;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1897a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1899c = StaggeredGridLayoutManager.this.u.d(view);
            if (cVarN.f1896f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1876c == 1) {
                this.f1899c += fullSpanItemF.a(this.f1901e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1897a.get(0);
            c cVarN = n(view);
            this.f1898b = StaggeredGridLayoutManager.this.u.g(view);
            if (cVarN.f1896f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1876c == -1) {
                this.f1898b -= fullSpanItemF.a(this.f1901e);
            }
        }

        void e() {
            this.f1897a.clear();
            q();
            this.f1900d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.z ? i(this.f1897a.size() - 1, -1, true) : i(0, this.f1897a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.z ? i(0, this.f1897a.size(), true) : i(this.f1897a.size() - 1, -1, true);
        }

        int h(int i2, int i3, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.u.m();
            int i4 = StaggeredGridLayoutManager.this.u.i();
            int i5 = i3 > i2 ? 1 : -1;
            while (i2 != i3) {
                View view = this.f1897a.get(i2);
                int iG = StaggeredGridLayoutManager.this.u.g(view);
                int iD = StaggeredGridLayoutManager.this.u.d(view);
                boolean z4 = false;
                boolean z5 = !z3 ? iG >= i4 : iG > i4;
                if (!z3 ? iD > iM : iD >= iM) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (iG >= iM && iD <= i4) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                        if (iG < iM || iD > i4) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    }
                }
                i2 += i5;
            }
            return -1;
        }

        int i(int i2, int i3, boolean z) {
            return h(i2, i3, false, false, z);
        }

        public int j() {
            return this.f1900d;
        }

        int k() {
            int i2 = this.f1899c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f1899c;
        }

        int l(int i2) {
            int i3 = this.f1899c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f1897a.size() == 0) {
                return i2;
            }
            c();
            return this.f1899c;
        }

        public View m(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.f1897a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1897a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.h0(view2) >= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.h0(view2) <= i2) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1897a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = this.f1897a.get(i4);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.h0(view3) <= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.h0(view3) >= i2) || !view3.hasFocusable()) {
                        break;
                    }
                    i4++;
                    view = view3;
                }
            }
            return view;
        }

        c n(View view) {
            return (c) view.getLayoutParams();
        }

        int o() {
            int i2 = this.f1898b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.f1898b;
        }

        int p(int i2) {
            int i3 = this.f1898b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f1897a.size() == 0) {
                return i2;
            }
            d();
            return this.f1898b;
        }

        void q() {
            this.f1898b = Integer.MIN_VALUE;
            this.f1899c = Integer.MIN_VALUE;
        }

        void r(int i2) {
            int i3 = this.f1898b;
            if (i3 != Integer.MIN_VALUE) {
                this.f1898b = i3 + i2;
            }
            int i4 = this.f1899c;
            if (i4 != Integer.MIN_VALUE) {
                this.f1899c = i4 + i2;
            }
        }

        void s() {
            int size = this.f1897a.size();
            View viewRemove = this.f1897a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f1895e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f1900d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            if (size == 1) {
                this.f1898b = Integer.MIN_VALUE;
            }
            this.f1899c = Integer.MIN_VALUE;
        }

        void t() {
            View viewRemove = this.f1897a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f1895e = null;
            if (this.f1897a.size() == 0) {
                this.f1899c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1900d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            this.f1898b = Integer.MIN_VALUE;
        }

        void u(View view) {
            c cVarN = n(view);
            cVarN.f1895e = this;
            this.f1897a.add(0, view);
            this.f1898b = Integer.MIN_VALUE;
            if (this.f1897a.size() == 1) {
                this.f1899c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1900d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void v(int i2) {
            this.f1898b = i2;
            this.f1899c = i2;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i2, i3);
        H2(dVarI0.f1825a);
        J2(dVarI0.f1826b);
        I2(dVarI0.f1827c);
        this.y = new f();
        a2();
    }

    private void A2(RecyclerView.v vVar, f fVar) {
        if (!fVar.f1990a || fVar.f1998i) {
            return;
        }
        if (fVar.f1991b == 0) {
            if (fVar.f1994e == -1) {
                B2(vVar, fVar.f1996g);
                return;
            } else {
                C2(vVar, fVar.f1995f);
                return;
            }
        }
        if (fVar.f1994e != -1) {
            int iN2 = n2(fVar.f1996g) - fVar.f1996g;
            C2(vVar, iN2 < 0 ? fVar.f1995f : Math.min(iN2, fVar.f1991b) + fVar.f1995f);
        } else {
            int i2 = fVar.f1995f;
            int iM2 = i2 - m2(i2);
            B2(vVar, iM2 < 0 ? fVar.f1996g : fVar.f1996g - Math.min(iM2, fVar.f1991b));
        }
    }

    private void B2(RecyclerView.v vVar, int i2) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            if (this.u.g(viewI) < i2 || this.u.q(viewI) < i2) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f1896f) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    if (this.t[i3].f1897a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.s; i4++) {
                    this.t[i4].s();
                }
            } else if (cVar.f1895e.f1897a.size() == 1) {
                return;
            } else {
                cVar.f1895e.s();
            }
            m1(viewI, vVar);
        }
    }

    private void C2(RecyclerView.v vVar, int i2) {
        while (J() > 0) {
            View viewI = I(0);
            if (this.u.d(viewI) > i2 || this.u.p(viewI) > i2) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f1896f) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    if (this.t[i3].f1897a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.s; i4++) {
                    this.t[i4].t();
                }
            } else if (cVar.f1895e.f1897a.size() == 1) {
                return;
            } else {
                cVar.f1895e.t();
            }
            m1(viewI, vVar);
        }
    }

    private void D2() {
        if (this.v.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int iJ = J();
        for (int i2 = 0; i2 < iJ; i2++) {
            View viewI = I(i2);
            float fE = this.v.e(viewI);
            if (fE >= fMax) {
                if (((c) viewI.getLayoutParams()).f()) {
                    fE = (fE * 1.0f) / this.s;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i3 = this.x;
        int iRound = Math.round(fMax * this.s);
        if (this.v.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.v.n());
        }
        P2(iRound);
        if (this.x == i3) {
            return;
        }
        for (int i4 = 0; i4 < iJ; i4++) {
            View viewI2 = I(i4);
            c cVar = (c) viewI2.getLayoutParams();
            if (!cVar.f1896f) {
                if (t2() && this.w == 1) {
                    int i5 = this.s;
                    int i6 = cVar.f1895e.f1901e;
                    viewI2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.x) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = cVar.f1895e.f1901e;
                    int i8 = this.x * i7;
                    int i9 = i7 * i3;
                    if (this.w == 1) {
                        viewI2.offsetLeftAndRight(i8 - i9);
                    } else {
                        viewI2.offsetTopAndBottom(i8 - i9);
                    }
                }
            }
        }
    }

    private void E2() {
        if (this.w == 1 || !t2()) {
            this.A = this.z;
        } else {
            this.A = !this.z;
        }
    }

    private void G2(int i2) {
        f fVar = this.y;
        fVar.f1994e = i2;
        fVar.f1993d = this.A != (i2 == -1) ? -1 : 1;
    }

    private void K2(int i2, int i3) {
        for (int i4 = 0; i4 < this.s; i4++) {
            if (!this.t[i4].f1897a.isEmpty()) {
                Q2(this.t[i4], i2, i3);
            }
        }
    }

    private boolean L2(RecyclerView.z zVar, b bVar) {
        bVar.f1888a = this.G ? g2(zVar.b()) : c2(zVar.b());
        bVar.f1889b = Integer.MIN_VALUE;
        return true;
    }

    private void M1(View view) {
        for (int i2 = this.s - 1; i2 >= 0; i2--) {
            this.t[i2].a(view);
        }
    }

    private void N1(b bVar) {
        SavedState savedState = this.I;
        int i2 = savedState.f1881d;
        if (i2 > 0) {
            if (i2 == this.s) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].e();
                    SavedState savedState2 = this.I;
                    int i4 = savedState2.f1882e[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += savedState2.j ? this.u.i() : this.u.m();
                    }
                    this.t[i3].v(i4);
                }
            } else {
                savedState.c();
                SavedState savedState3 = this.I;
                savedState3.f1879b = savedState3.f1880c;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.k;
        I2(savedState4.f1886i);
        E2();
        SavedState savedState5 = this.I;
        int i5 = savedState5.f1879b;
        if (i5 != -1) {
            this.C = i5;
            bVar.f1890c = savedState5.j;
        } else {
            bVar.f1890c = this.A;
        }
        if (savedState5.f1883f > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f1873a = savedState5.f1884g;
            lazySpanLookup.f1874b = savedState5.f1885h;
        }
    }

    private void O2(int i2, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        f fVar = this.y;
        boolean z = false;
        fVar.f1991b = 0;
        fVar.f1992c = i2;
        if (!x0() || (iC = zVar.c()) == -1) {
            iN = 0;
            iN2 = 0;
        } else {
            if (this.A == (iC < i2)) {
                iN = this.u.n();
                iN2 = 0;
            } else {
                iN2 = this.u.n();
                iN = 0;
            }
        }
        if (M()) {
            this.y.f1995f = this.u.m() - iN2;
            this.y.f1996g = this.u.i() + iN;
        } else {
            this.y.f1996g = this.u.h() + iN;
            this.y.f1995f = -iN2;
        }
        f fVar2 = this.y;
        fVar2.f1997h = false;
        fVar2.f1990a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        fVar2.f1998i = z;
    }

    private void Q1(View view, c cVar, f fVar) {
        if (fVar.f1994e == 1) {
            if (cVar.f1896f) {
                M1(view);
                return;
            } else {
                cVar.f1895e.a(view);
                return;
            }
        }
        if (cVar.f1896f) {
            z2(view);
        } else {
            cVar.f1895e.u(view);
        }
    }

    private void Q2(d dVar, int i2, int i3) {
        int iJ = dVar.j();
        if (i2 == -1) {
            if (dVar.o() + iJ <= i3) {
                this.B.set(dVar.f1901e, false);
            }
        } else if (dVar.k() - iJ >= i3) {
            this.B.set(dVar.f1901e, false);
        }
    }

    private int R1(int i2) {
        if (J() == 0) {
            return this.A ? 1 : -1;
        }
        return (i2 < j2()) != this.A ? -1 : 1;
    }

    private int R2(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    private boolean T1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.u.i()) {
                ArrayList<View> arrayList = dVar.f1897a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f1896f;
            }
        } else if (dVar.o() > this.u.m()) {
            return !dVar.n(dVar.f1897a.get(0)).f1896f;
        }
        return false;
    }

    private int U1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.a(zVar, this.u, e2(!this.N), d2(!this.N), this, this.N);
    }

    private int V1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.b(zVar, this.u, e2(!this.N), d2(!this.N), this, this.N, this.A);
    }

    private int W1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.c(zVar, this.u, e2(!this.N), d2(!this.N), this, this.N);
    }

    private int X1(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && t2()) ? -1 : 1 : (this.w != 1 && t2()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem Y1(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1877d = new int[this.s];
        for (int i3 = 0; i3 < this.s; i3++) {
            fullSpanItem.f1877d[i3] = i2 - this.t[i3].l(i2);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem Z1(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1877d = new int[this.s];
        for (int i3 = 0; i3 < this.s; i3++) {
            fullSpanItem.f1877d[i3] = this.t[i3].p(i2) - i2;
        }
        return fullSpanItem;
    }

    private void a2() {
        this.u = j.b(this, this.w);
        this.v = j.b(this, 1 - this.w);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r16v0, types: [androidx.recyclerview.widget.RecyclerView$o, androidx.recyclerview.widget.StaggeredGridLayoutManager] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int b2(RecyclerView.v vVar, f fVar, RecyclerView.z zVar) {
        d dVarP2;
        int iE;
        int i2;
        int iE2;
        int iE3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        int i3 = this.y.f1998i ? fVar.f1994e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : fVar.f1994e == 1 ? fVar.f1996g + fVar.f1991b : fVar.f1995f - fVar.f1991b;
        K2(fVar.f1994e, i3);
        int i4 = this.A ? this.u.i() : this.u.m();
        ?? r0 = false;
        while (fVar.a(zVar) && (this.y.f1998i || !this.B.isEmpty())) {
            View viewB = fVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarP2 = cVar.f1896f ? this.t[r9] : p2(fVar);
                this.E.n(iA, dVarP2);
            } else {
                dVarP2 = this.t[iG];
            }
            d dVar = dVarP2;
            cVar.f1895e = dVar;
            if (fVar.f1994e == 1) {
                d(viewB);
            } else {
                e(viewB, r9);
            }
            v2(viewB, cVar, r9);
            if (fVar.f1994e == 1) {
                int iL2 = cVar.f1896f ? l2(i4) : dVar.l(i4);
                int iE4 = this.u.e(viewB) + iL2;
                if (r3 != 0 && cVar.f1896f) {
                    LazySpanLookup.FullSpanItem fullSpanItemY1 = Y1(iL2);
                    fullSpanItemY1.f1876c = -1;
                    fullSpanItemY1.f1875b = iA;
                    this.E.a(fullSpanItemY1);
                }
                i2 = iE4;
                iE = iL2;
            } else {
                int iO2 = cVar.f1896f ? o2(i4) : dVar.p(i4);
                iE = iO2 - this.u.e(viewB);
                if (r3 != 0 && cVar.f1896f) {
                    LazySpanLookup.FullSpanItem fullSpanItemZ1 = Z1(iO2);
                    fullSpanItemZ1.f1876c = 1;
                    fullSpanItemZ1.f1875b = iA;
                    this.E.a(fullSpanItemZ1);
                }
                i2 = iO2;
            }
            if (cVar.f1896f && fVar.f1993d == -1) {
                if (r3 != 0) {
                    this.M = true;
                } else {
                    if (!(fVar.f1994e == 1 ? O1() : P1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f1878e = true;
                        }
                        this.M = true;
                    }
                }
            }
            Q1(viewB, cVar, fVar);
            if (t2() && this.w == 1) {
                int i5 = cVar.f1896f ? this.v.i() : this.v.i() - (((this.s - 1) - dVar.f1901e) * this.x);
                iE3 = i5;
                iE2 = i5 - this.v.e(viewB);
            } else {
                int iM = cVar.f1896f ? this.v.m() : (dVar.f1901e * this.x) + this.v.m();
                iE2 = iM;
                iE3 = this.v.e(viewB) + iM;
            }
            if (this.w == 1) {
                z0(viewB, iE2, iE, iE3, i2);
            } else {
                z0(viewB, iE, iE2, i2, iE3);
            }
            if (cVar.f1896f) {
                K2(this.y.f1994e, i3);
            } else {
                Q2(dVar, this.y.f1994e, i3);
            }
            A2(vVar, this.y);
            if (!this.y.f1997h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.f1896f) {
                this.B.clear();
                z = false;
            } else {
                z = false;
                this.B.set(dVar.f1901e, false);
            }
            r9 = z;
            r0 = true;
        }
        ?? r32 = r9;
        if (r0 == false) {
            A2(vVar, this.y);
        }
        int iM2 = this.y.f1994e == -1 ? this.u.m() - o2(this.u.m()) : l2(this.u.i()) - this.u.i();
        return iM2 > 0 ? Math.min(fVar.f1991b, iM2) : r32 == true ? 1 : 0;
    }

    private int c2(int i2) {
        int iJ = J();
        for (int i3 = 0; i3 < iJ; i3++) {
            int iH0 = h0(I(i3));
            if (iH0 >= 0 && iH0 < i2) {
                return iH0;
            }
        }
        return 0;
    }

    private int g2(int i2) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            int iH0 = h0(I(iJ));
            if (iH0 >= 0 && iH0 < i2) {
                return iH0;
            }
        }
        return 0;
    }

    private void h2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i2;
        int iL2 = l2(Integer.MIN_VALUE);
        if (iL2 != Integer.MIN_VALUE && (i2 = this.u.i() - iL2) > 0) {
            int i3 = i2 - (-F2(-i2, vVar, zVar));
            if (!z || i3 <= 0) {
                return;
            }
            this.u.r(i3);
        }
    }

    private void i2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iO2 = o2(Integer.MAX_VALUE);
        if (iO2 != Integer.MAX_VALUE && (iM = iO2 - this.u.m()) > 0) {
            int iF2 = iM - F2(iM, vVar, zVar);
            if (!z || iF2 <= 0) {
                return;
            }
            this.u.r(-iF2);
        }
    }

    private int l2(int i2) {
        int iL = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iL2 = this.t[i3].l(i2);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int m2(int i2) {
        int iP = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iP2 = this.t[i3].p(i2);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private int n2(int i2) {
        int iL = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iL2 = this.t[i3].l(i2);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int o2(int i2) {
        int iP = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iP2 = this.t[i3].p(i2);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private d p2(f fVar) {
        int i2;
        int i3;
        int i4 = -1;
        if (x2(fVar.f1994e)) {
            i2 = this.s - 1;
            i3 = -1;
        } else {
            i2 = 0;
            i4 = this.s;
            i3 = 1;
        }
        d dVar = null;
        if (fVar.f1994e == 1) {
            int i5 = Integer.MAX_VALUE;
            int iM = this.u.m();
            while (i2 != i4) {
                d dVar2 = this.t[i2];
                int iL = dVar2.l(iM);
                if (iL < i5) {
                    dVar = dVar2;
                    i5 = iL;
                }
                i2 += i3;
            }
            return dVar;
        }
        int i6 = Integer.MIN_VALUE;
        int i7 = this.u.i();
        while (i2 != i4) {
            d dVar3 = this.t[i2];
            int iP = dVar3.p(i7);
            if (iP > i6) {
                dVar = dVar3;
                i6 = iP;
            }
            i2 += i3;
        }
        return dVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void q2(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.A
            if (r0 == 0) goto L9
            int r0 = r6.k2()
            goto Ld
        L9:
            int r0 = r6.j2()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.E
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.E
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.E
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.E
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.E
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.A
            if (r7 == 0) goto L4d
            int r7 = r6.j2()
            goto L51
        L4d:
            int r7 = r6.k2()
        L51:
            if (r3 > r7) goto L56
            r6.t1()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.q2(int, int, int):void");
    }

    private void u2(View view, int i2, int i3, boolean z) {
        j(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iR2 = R2(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iR22 = R2(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? H1(view, iR2, iR22, cVar) : F1(view, iR2, iR22, cVar)) {
            view.measure(iR2, iR22);
        }
    }

    private void v2(View view, c cVar, boolean z) {
        if (cVar.f1896f) {
            if (this.w == 1) {
                u2(view, this.J, RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
                return;
            } else {
                u2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z);
                return;
            }
        }
        if (this.w == 1) {
            u2(view, RecyclerView.o.K(this.x, p0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            u2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.K(this.x, X(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void w2(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.z r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.w2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, boolean):void");
    }

    private boolean x2(int i2) {
        if (this.w == 0) {
            return (i2 == -1) != this.A;
        }
        return ((i2 == -1) == this.A) == t2();
    }

    private void z2(View view) {
        for (int i2 = this.s - 1; i2 >= 0; i2--) {
            this.t[i2].u(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C0(int i2) {
        super.C0(i2);
        for (int i3 = 0; i3 < this.s; i3++) {
            this.t[i3].r(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C1(Rect rect, int i2, int i3) {
        int iN;
        int iN2;
        int iE0 = e0() + f0();
        int iG0 = g0() + d0();
        if (this.w == 1) {
            iN2 = RecyclerView.o.n(i3, rect.height() + iG0, b0());
            iN = RecyclerView.o.n(i2, (this.x * this.s) + iE0, c0());
        } else {
            iN = RecyclerView.o.n(i2, rect.width() + iE0, c0());
            iN2 = RecyclerView.o.n(i3, (this.x * this.s) + iG0, b0());
        }
        B1(iN, iN2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return this.w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void D0(int i2) {
        super.D0(i2);
        for (int i3 = 0; i3 < this.s; i3++) {
            this.t[i3].r(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p F(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    int F2(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i2 == 0) {
            return 0;
        }
        y2(i2, zVar);
        int iB2 = b2(vVar, this.y, zVar);
        if (this.y.f1991b >= iB2) {
            i2 = i2 < 0 ? -iB2 : iB2;
        }
        this.u.r(-i2);
        this.G = this.A;
        f fVar = this.y;
        fVar.f1991b = 0;
        A2(vVar, fVar);
        return i2;
    }

    public void H2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        g(null);
        if (i2 == this.w) {
            return;
        }
        this.w = i2;
        j jVar = this.u;
        this.u = this.v;
        this.v = jVar;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.I0(recyclerView, vVar);
        o1(this.P);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i2) {
        g gVar = new g(recyclerView.getContext());
        gVar.p(i2);
        J1(gVar);
    }

    public void I2(boolean z) {
        g(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1886i != z) {
            savedState.f1886i = z;
        }
        this.z = z;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewB;
        View viewM;
        if (J() == 0 || (viewB = B(view)) == null) {
            return null;
        }
        E2();
        int iX1 = X1(i2);
        if (iX1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewB.getLayoutParams();
        boolean z = cVar.f1896f;
        d dVar = cVar.f1895e;
        int iK2 = iX1 == 1 ? k2() : j2();
        O2(iK2, zVar);
        G2(iX1);
        f fVar = this.y;
        fVar.f1992c = fVar.f1993d + iK2;
        fVar.f1991b = (int) (this.u.n() * 0.33333334f);
        f fVar2 = this.y;
        fVar2.f1997h = true;
        fVar2.f1990a = false;
        b2(vVar, fVar2, zVar);
        this.G = this.A;
        if (!z && (viewM = dVar.m(iK2, iX1)) != null && viewM != viewB) {
            return viewM;
        }
        if (x2(iX1)) {
            for (int i3 = this.s - 1; i3 >= 0; i3--) {
                View viewM2 = this.t[i3].m(iK2, iX1);
                if (viewM2 != null && viewM2 != viewB) {
                    return viewM2;
                }
            }
        } else {
            for (int i4 = 0; i4 < this.s; i4++) {
                View viewM3 = this.t[i4].m(iK2, iX1);
                if (viewM3 != null && viewM3 != viewB) {
                    return viewM3;
                }
            }
        }
        boolean z2 = (this.z ^ true) == (iX1 == -1);
        if (!z) {
            View viewC = C(z2 ? dVar.f() : dVar.g());
            if (viewC != null && viewC != viewB) {
                return viewC;
            }
        }
        if (x2(iX1)) {
            for (int i5 = this.s - 1; i5 >= 0; i5--) {
                if (i5 != dVar.f1901e) {
                    View viewC2 = C(z2 ? this.t[i5].f() : this.t[i5].g());
                    if (viewC2 != null && viewC2 != viewB) {
                        return viewC2;
                    }
                }
            }
        } else {
            for (int i6 = 0; i6 < this.s; i6++) {
                View viewC3 = C(z2 ? this.t[i6].f() : this.t[i6].g());
                if (viewC3 != null && viewC3 != viewB) {
                    return viewC3;
                }
            }
        }
        return null;
    }

    public void J2(int i2) {
        g(null);
        if (i2 != this.s) {
            s2();
            this.s = i2;
            this.B = new BitSet(this.s);
            this.t = new d[this.s];
            for (int i3 = 0; i3 < this.s; i3++) {
                this.t[i3] = new d(i3);
            }
            t1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void K0(AccessibilityEvent accessibilityEvent) {
        super.K0(accessibilityEvent);
        if (J() > 0) {
            View viewE2 = e2(false);
            View viewD2 = d2(false);
            if (viewE2 == null || viewD2 == null) {
                return;
            }
            int iH0 = h0(viewE2);
            int iH02 = h0(viewD2);
            if (iH0 < iH02) {
                accessibilityEvent.setFromIndex(iH0);
                accessibilityEvent.setToIndex(iH02);
            } else {
                accessibilityEvent.setFromIndex(iH02);
                accessibilityEvent.setToIndex(iH0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean L1() {
        return this.I == null;
    }

    boolean M2(RecyclerView.z zVar, b bVar) {
        int i2;
        if (!zVar.e() && (i2 = this.C) != -1) {
            if (i2 >= 0 && i2 < zVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f1879b == -1 || savedState.f1881d < 1) {
                    View viewC = C(this.C);
                    if (viewC != null) {
                        bVar.f1888a = this.A ? k2() : j2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1890c) {
                                bVar.f1889b = (this.u.i() - this.D) - this.u.d(viewC);
                            } else {
                                bVar.f1889b = (this.u.m() + this.D) - this.u.g(viewC);
                            }
                            return true;
                        }
                        if (this.u.e(viewC) > this.u.n()) {
                            bVar.f1889b = bVar.f1890c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int iG = this.u.g(viewC) - this.u.m();
                        if (iG < 0) {
                            bVar.f1889b = -iG;
                            return true;
                        }
                        int i3 = this.u.i() - this.u.d(viewC);
                        if (i3 < 0) {
                            bVar.f1889b = i3;
                            return true;
                        }
                        bVar.f1889b = Integer.MIN_VALUE;
                    } else {
                        int i4 = this.C;
                        bVar.f1888a = i4;
                        int i5 = this.D;
                        if (i5 == Integer.MIN_VALUE) {
                            bVar.f1890c = R1(i4) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i5);
                        }
                        bVar.f1891d = true;
                    }
                } else {
                    bVar.f1889b = Integer.MIN_VALUE;
                    bVar.f1888a = this.C;
                }
                return true;
            }
            this.C = -1;
            this.D = Integer.MIN_VALUE;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.w == 1 ? this.s : super.N(vVar, zVar);
    }

    void N2(RecyclerView.z zVar, b bVar) {
        if (M2(zVar, bVar) || L2(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1888a = 0;
    }

    boolean O1() {
        int iL = this.t[0].l(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.s; i2++) {
            if (this.t[i2].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView.v vVar, RecyclerView.z zVar, View view, b.g.k.c0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.O0(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.w == 0) {
            cVar.e0(c.C0047c.a(cVar2.e(), cVar2.f1896f ? this.s : 1, -1, -1, false, false));
        } else {
            cVar.e0(c.C0047c.a(-1, -1, cVar2.e(), cVar2.f1896f ? this.s : 1, false, false));
        }
    }

    boolean P1() {
        int iP = this.t[0].p(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.s; i2++) {
            if (this.t[i2].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    void P2(int i2) {
        this.x = i2 / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i2, this.v.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i2, int i3) {
        q2(i2, i3, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(RecyclerView recyclerView) {
        this.E.b();
        t1();
    }

    boolean S1() {
        int iJ2;
        int iK2;
        if (J() == 0 || this.F == 0 || !r0()) {
            return false;
        }
        if (this.A) {
            iJ2 = k2();
            iK2 = j2();
        } else {
            iJ2 = j2();
            iK2 = k2();
        }
        if (iJ2 == 0 && r2() != null) {
            this.E.b();
            u1();
            t1();
            return true;
        }
        if (!this.M) {
            return false;
        }
        int i2 = this.A ? -1 : 1;
        int i3 = iK2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.E.e(iJ2, i3, i2, true);
        if (fullSpanItemE == null) {
            this.M = false;
            this.E.d(i3);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iJ2, fullSpanItemE.f1875b, i2 * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f1875b);
        } else {
            this.E.d(fullSpanItemE2.f1875b + 1);
        }
        u1();
        t1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i2, int i3, int i4) {
        q2(i2, i3, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i2, int i3) {
        q2(i2, i3, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        q2(i2, i3, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        w2(vVar, zVar, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF a(int i2) {
        int iR1 = R1(i2);
        PointF pointF = new PointF();
        if (iR1 == 0) {
            return null;
        }
        if (this.w == 0) {
            pointF.x = iR1;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iR1;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.I = (SavedState) parcelable;
            t1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable d1() {
        int iP;
        int iM;
        int[] iArr;
        if (this.I != null) {
            return new SavedState(this.I);
        }
        SavedState savedState = new SavedState();
        savedState.f1886i = this.z;
        savedState.j = this.G;
        savedState.k = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1873a) == null) {
            savedState.f1883f = 0;
        } else {
            savedState.f1884g = iArr;
            savedState.f1883f = iArr.length;
            savedState.f1885h = lazySpanLookup.f1874b;
        }
        if (J() > 0) {
            savedState.f1879b = this.G ? k2() : j2();
            savedState.f1880c = f2();
            int i2 = this.s;
            savedState.f1881d = i2;
            savedState.f1882e = new int[i2];
            for (int i3 = 0; i3 < this.s; i3++) {
                if (this.G) {
                    iP = this.t[i3].l(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.u.i();
                        iP -= iM;
                    }
                } else {
                    iP = this.t[i3].p(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.u.m();
                        iP -= iM;
                    }
                }
                savedState.f1882e[i3] = iP;
            }
        } else {
            savedState.f1879b = -1;
            savedState.f1880c = -1;
            savedState.f1881d = 0;
        }
        return savedState;
    }

    View d2(boolean z) {
        int iM = this.u.m();
        int i2 = this.u.i();
        View view = null;
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            int iG = this.u.g(viewI);
            int iD = this.u.d(viewI);
            if (iD > iM && iG < i2) {
                if (iD <= i2 || !z) {
                    return viewI;
                }
                if (view == null) {
                    view = viewI;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e1(int i2) {
        if (i2 == 0) {
            S1();
        }
    }

    View e2(boolean z) {
        int iM = this.u.m();
        int i2 = this.u.i();
        int iJ = J();
        View view = null;
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI = I(i3);
            int iG = this.u.g(viewI);
            if (this.u.d(viewI) > iM && iG < i2) {
                if (iG >= iM || !z) {
                    return viewI;
                }
                if (view == null) {
                    view = viewI;
                }
            }
        }
        return view;
    }

    int f2() {
        View viewD2 = this.A ? d2(true) : e2(true);
        if (viewD2 == null) {
            return -1;
        }
        return h0(viewD2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(String str) {
        if (this.I == null) {
            super.g(str);
        }
    }

    int j2() {
        if (J() == 0) {
            return 0;
        }
        return h0(I(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int k0(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.w == 0 ? this.s : super.k0(vVar, zVar);
    }

    int k2() {
        int iJ = J();
        if (iJ == 0) {
            return 0;
        }
        return h0(I(iJ - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l() {
        return this.w == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean m(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i2, int i3, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.w != 0) {
            i2 = i3;
        }
        if (J() == 0 || i2 == 0) {
            return;
        }
        y2(i2, zVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.s; i5++) {
            f fVar = this.y;
            if (fVar.f1993d == -1) {
                iL = fVar.f1995f;
                iP = this.t[i5].p(iL);
            } else {
                iL = this.t[i5].l(fVar.f1996g);
                iP = this.y.f1996g;
            }
            int i6 = iL - iP;
            if (i6 >= 0) {
                this.O[i4] = i6;
                i4++;
            }
        }
        Arrays.sort(this.O, 0, i4);
        for (int i7 = 0; i7 < i4 && this.y.a(zVar); i7++) {
            cVar.a(this.y.f1992c, this.O[i7]);
            f fVar2 = this.y;
            fVar2.f1992c += fVar2.f1993d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return U1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return V1(zVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.View r2() {
        /*
            r12 = this;
            int r0 = r12.J()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.s
            r2.<init>(r3)
            int r3 = r12.s
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.w
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.t2()
            if (r3 == 0) goto L20
            r3 = r1
            goto L21
        L20:
            r3 = r5
        L21:
            boolean r6 = r12.A
            if (r6 == 0) goto L27
            r6 = r5
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = r4
        L2b:
            if (r0 >= r6) goto L2e
            r5 = r1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.I(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1895e
            int r9 = r9.f1901e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1895e
            boolean r9 = r12.T1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1895e
            int r9 = r9.f1901e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f1896f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.I(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.j r10 = r12.u
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.j r11 = r12.u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.j r10 = r12.u
            int r10 = r10.g(r7)
            androidx.recyclerview.widget.j r11 = r12.u
            int r11 = r11.g(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = r1
            goto L8b
        L8a:
            r10 = r4
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f1895e
            int r8 = r8.f1901e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f1895e
            int r9 = r9.f1901e
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = r1
            goto La1
        La0:
            r8 = r4
        La1:
            if (r3 >= 0) goto La5
            r9 = r1
            goto La6
        La5:
            r9 = r4
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.r2():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int s(RecyclerView.z zVar) {
        return W1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean s0() {
        return this.F != 0;
    }

    public void s2() {
        this.E.b();
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return U1(zVar);
    }

    boolean t2() {
        return Z() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return V1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return W1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i2, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i2) {
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1879b != i2) {
            savedState.a();
        }
        this.C = i2;
        this.D = Integer.MIN_VALUE;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i2, vVar, zVar);
    }

    void y2(int i2, RecyclerView.z zVar) {
        int i3;
        int iJ2;
        if (i2 > 0) {
            iJ2 = k2();
            i3 = 1;
        } else {
            i3 = -1;
            iJ2 = j2();
        }
        this.y.f1990a = true;
        O2(iJ2, zVar);
        G2(i3);
        f fVar = this.y;
        fVar.f1992c = iJ2 + fVar.f1993d;
        fVar.f1991b = Math.abs(i2);
    }

    static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int[] f1873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        List<FullSpanItem> f1874b;

        LazySpanLookup() {
        }

        private int i(int i2) {
            if (this.f1874b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i2);
            if (fullSpanItemF != null) {
                this.f1874b.remove(fullSpanItemF);
            }
            int size = this.f1874b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (this.f1874b.get(i3).f1875b >= i2) {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1874b.get(i3);
            this.f1874b.remove(i3);
            return fullSpanItem.f1875b;
        }

        private void l(int i2, int i3) {
            List<FullSpanItem> list = this.f1874b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1874b.get(size);
                int i4 = fullSpanItem.f1875b;
                if (i4 >= i2) {
                    fullSpanItem.f1875b = i4 + i3;
                }
            }
        }

        private void m(int i2, int i3) {
            List<FullSpanItem> list = this.f1874b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1874b.get(size);
                int i5 = fullSpanItem.f1875b;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.f1874b.remove(size);
                    } else {
                        fullSpanItem.f1875b = i5 - i3;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1874b == null) {
                this.f1874b = new ArrayList();
            }
            int size = this.f1874b.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = this.f1874b.get(i2);
                if (fullSpanItem2.f1875b == fullSpanItem.f1875b) {
                    this.f1874b.remove(i2);
                }
                if (fullSpanItem2.f1875b >= fullSpanItem.f1875b) {
                    this.f1874b.add(i2, fullSpanItem);
                    return;
                }
            }
            this.f1874b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.f1873a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1874b = null;
        }

        void c(int i2) {
            int[] iArr = this.f1873a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.f1873a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[o(i2)];
                this.f1873a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1873a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i2) {
            List<FullSpanItem> list = this.f1874b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1874b.get(size).f1875b >= i2) {
                        this.f1874b.remove(size);
                    }
                }
            }
            return h(i2);
        }

        public FullSpanItem e(int i2, int i3, int i4, boolean z) {
            List<FullSpanItem> list = this.f1874b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = this.f1874b.get(i5);
                int i6 = fullSpanItem.f1875b;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || fullSpanItem.f1876c == i4 || (z && fullSpanItem.f1878e))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i2) {
            List<FullSpanItem> list = this.f1874b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1874b.get(size);
                if (fullSpanItem.f1875b == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i2) {
            int[] iArr = this.f1873a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        int h(int i2) {
            int[] iArr = this.f1873a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int i3 = i(i2);
            if (i3 == -1) {
                int[] iArr2 = this.f1873a;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.f1873a.length;
            }
            int i4 = i3 + 1;
            Arrays.fill(this.f1873a, i2, i4, -1);
            return i4;
        }

        void j(int i2, int i3) {
            int[] iArr = this.f1873a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f1873a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.f1873a, i2, i4, -1);
            l(i2, i3);
        }

        void k(int i2, int i3) {
            int[] iArr = this.f1873a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f1873a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.f1873a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            m(i2, i3);
        }

        void n(int i2, d dVar) {
            c(i2);
            this.f1873a[i2] = dVar.f1901e;
        }

        int o(int i2) {
            int length = this.f1873a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f1875b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1876c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int[] f1877d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            boolean f1878e;

            static class a implements Parcelable.Creator<FullSpanItem> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public FullSpanItem[] newArray(int i2) {
                    return new FullSpanItem[i2];
                }
            }

            FullSpanItem(Parcel parcel) {
                this.f1875b = parcel.readInt();
                this.f1876c = parcel.readInt();
                this.f1878e = parcel.readInt() == 1;
                int i2 = parcel.readInt();
                if (i2 > 0) {
                    int[] iArr = new int[i2];
                    this.f1877d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int a(int i2) {
                int[] iArr = this.f1877d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i2];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1875b + ", mGapDir=" + this.f1876c + ", mHasUnwantedGapAfter=" + this.f1878e + ", mGapPerSpan=" + Arrays.toString(this.f1877d) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f1875b);
                parcel.writeInt(this.f1876c);
                parcel.writeInt(this.f1878e ? 1 : 0);
                int[] iArr = this.f1877d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1877d);
                }
            }

            FullSpanItem() {
            }
        }
    }
}
