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
        int f1704b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1705c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1706d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int[] f1707e;
        int f;
        int[] g;
        List<LazySpanLookup.FullSpanItem> h;
        boolean i;
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        void a() {
            this.f1707e = null;
            this.f1706d = 0;
            this.f1704b = -1;
            this.f1705c = -1;
        }

        void c() {
            this.f1707e = null;
            this.f1706d = 0;
            this.f = 0;
            this.g = null;
            this.h = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1704b);
            parcel.writeInt(this.f1705c);
            parcel.writeInt(this.f1706d);
            if (this.f1706d > 0) {
                parcel.writeIntArray(this.f1707e);
            }
            parcel.writeInt(this.f);
            if (this.f > 0) {
                parcel.writeIntArray(this.g);
            }
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeList(this.h);
        }

        SavedState(Parcel parcel) {
            this.f1704b = parcel.readInt();
            this.f1705c = parcel.readInt();
            int i = parcel.readInt();
            this.f1706d = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.f1707e = iArr;
                parcel.readIntArray(iArr);
            }
            int i2 = parcel.readInt();
            this.f = i2;
            if (i2 > 0) {
                int[] iArr2 = new int[i2];
                this.g = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.k = parcel.readInt() == 1;
            this.h = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f1706d = savedState.f1706d;
            this.f1704b = savedState.f1704b;
            this.f1705c = savedState.f1705c;
            this.f1707e = savedState.f1707e;
            this.f = savedState.f;
            this.g = savedState.g;
            this.i = savedState.i;
            this.j = savedState.j;
            this.k = savedState.k;
            this.h = savedState.h;
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
        int f1709a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1710b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1711c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1712d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1713e;
        int[] f;

        b() {
            c();
        }

        void a() {
            this.f1710b = this.f1711c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        void b(int i) {
            if (this.f1711c) {
                this.f1710b = StaggeredGridLayoutManager.this.u.i() - i;
            } else {
                this.f1710b = StaggeredGridLayoutManager.this.u.m() + i;
            }
        }

        void c() {
            this.f1709a = -1;
            this.f1710b = Integer.MIN_VALUE;
            this.f1711c = false;
            this.f1712d = false;
            this.f1713e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = dVarArr[i].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        d f1714e;
        boolean f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f1714e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f1719e;
        }

        public boolean f() {
            return this.f;
        }

        public c(int i, int i2) {
            super(i, i2);
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
        ArrayList<View> f1715a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1716b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1717c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1718d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f1719e;

        d(int i) {
            this.f1719e = i;
        }

        void a(View view) {
            c cVarN = n(view);
            cVarN.f1714e = this;
            this.f1715a.add(view);
            this.f1717c = Integer.MIN_VALUE;
            if (this.f1715a.size() == 1) {
                this.f1716b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1718d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void b(boolean z, int i) {
            int iL = z ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (iL == Integer.MIN_VALUE) {
                return;
            }
            if (!z || iL >= StaggeredGridLayoutManager.this.u.i()) {
                if (z || iL <= StaggeredGridLayoutManager.this.u.m()) {
                    if (i != Integer.MIN_VALUE) {
                        iL += i;
                    }
                    this.f1717c = iL;
                    this.f1716b = iL;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1715a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1717c = StaggeredGridLayoutManager.this.u.d(view);
            if (cVarN.f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1701c == 1) {
                this.f1717c += fullSpanItemF.a(this.f1719e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1715a.get(0);
            c cVarN = n(view);
            this.f1716b = StaggeredGridLayoutManager.this.u.g(view);
            if (cVarN.f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1701c == -1) {
                this.f1716b -= fullSpanItemF.a(this.f1719e);
            }
        }

        void e() {
            this.f1715a.clear();
            q();
            this.f1718d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.z ? i(this.f1715a.size() - 1, -1, true) : i(0, this.f1715a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.z ? i(0, this.f1715a.size(), true) : i(this.f1715a.size() - 1, -1, true);
        }

        int h(int i, int i2, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.u.m();
            int i3 = StaggeredGridLayoutManager.this.u.i();
            int i4 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1715a.get(i);
                int iG = StaggeredGridLayoutManager.this.u.g(view);
                int iD = StaggeredGridLayoutManager.this.u.d(view);
                boolean z4 = false;
                boolean z5 = !z3 ? iG >= i3 : iG > i3;
                if (!z3 ? iD > iM : iD >= iM) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (iG >= iM && iD <= i3) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                        if (iG < iM || iD > i3) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    }
                }
                i += i4;
            }
            return -1;
        }

        int i(int i, int i2, boolean z) {
            return h(i, i2, false, false, z);
        }

        public int j() {
            return this.f1718d;
        }

        int k() {
            int i = this.f1717c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.f1717c;
        }

        int l(int i) {
            int i2 = this.f1717c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1715a.size() == 0) {
                return i;
            }
            c();
            return this.f1717c;
        }

        public View m(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.f1715a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1715a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.h0(view2) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.h0(view2) <= i) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1715a.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.f1715a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.h0(view3) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.h0(view3) >= i) || !view3.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view3;
                }
            }
            return view;
        }

        c n(View view) {
            return (c) view.getLayoutParams();
        }

        int o() {
            int i = this.f1716b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            d();
            return this.f1716b;
        }

        int p(int i) {
            int i2 = this.f1716b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1715a.size() == 0) {
                return i;
            }
            d();
            return this.f1716b;
        }

        void q() {
            this.f1716b = Integer.MIN_VALUE;
            this.f1717c = Integer.MIN_VALUE;
        }

        void r(int i) {
            int i2 = this.f1716b;
            if (i2 != Integer.MIN_VALUE) {
                this.f1716b = i2 + i;
            }
            int i3 = this.f1717c;
            if (i3 != Integer.MIN_VALUE) {
                this.f1717c = i3 + i;
            }
        }

        void s() {
            int size = this.f1715a.size();
            View viewRemove = this.f1715a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f1714e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f1718d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            if (size == 1) {
                this.f1716b = Integer.MIN_VALUE;
            }
            this.f1717c = Integer.MIN_VALUE;
        }

        void t() {
            View viewRemove = this.f1715a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f1714e = null;
            if (this.f1715a.size() == 0) {
                this.f1717c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1718d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            this.f1716b = Integer.MIN_VALUE;
        }

        void u(View view) {
            c cVarN = n(view);
            cVarN.f1714e = this;
            this.f1715a.add(0, view);
            this.f1716b = Integer.MIN_VALUE;
            if (this.f1715a.size() == 1) {
                this.f1717c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1718d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void v(int i) {
            this.f1716b = i;
            this.f1717c = i;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i, i2);
        H2(dVarI0.f1663a);
        J2(dVarI0.f1664b);
        I2(dVarI0.f1665c);
        this.y = new f();
        a2();
    }

    private void A2(RecyclerView.v vVar, f fVar) {
        if (!fVar.f1794a || fVar.i) {
            return;
        }
        if (fVar.f1795b == 0) {
            if (fVar.f1798e == -1) {
                B2(vVar, fVar.g);
                return;
            } else {
                C2(vVar, fVar.f);
                return;
            }
        }
        if (fVar.f1798e != -1) {
            int iN2 = n2(fVar.g) - fVar.g;
            C2(vVar, iN2 < 0 ? fVar.f : Math.min(iN2, fVar.f1795b) + fVar.f);
        } else {
            int i = fVar.f;
            int iM2 = i - m2(i);
            B2(vVar, iM2 < 0 ? fVar.g : fVar.g - Math.min(iM2, fVar.f1795b));
        }
    }

    private void B2(RecyclerView.v vVar, int i) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            if (this.u.g(viewI) < i || this.u.q(viewI) < i) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1715a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].s();
                }
            } else if (cVar.f1714e.f1715a.size() == 1) {
                return;
            } else {
                cVar.f1714e.s();
            }
            m1(viewI, vVar);
        }
    }

    private void C2(RecyclerView.v vVar, int i) {
        while (J() > 0) {
            View viewI = I(0);
            if (this.u.d(viewI) > i || this.u.p(viewI) > i) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1715a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].t();
                }
            } else if (cVar.f1714e.f1715a.size() == 1) {
                return;
            } else {
                cVar.f1714e.t();
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
        for (int i = 0; i < iJ; i++) {
            View viewI = I(i);
            float fE = this.v.e(viewI);
            if (fE >= fMax) {
                if (((c) viewI.getLayoutParams()).f()) {
                    fE = (fE * 1.0f) / this.s;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i2 = this.x;
        int iRound = Math.round(fMax * this.s);
        if (this.v.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.v.n());
        }
        P2(iRound);
        if (this.x == i2) {
            return;
        }
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI2 = I(i3);
            c cVar = (c) viewI2.getLayoutParams();
            if (!cVar.f) {
                if (t2() && this.w == 1) {
                    int i4 = this.s;
                    int i5 = cVar.f1714e.f1719e;
                    viewI2.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.x) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = cVar.f1714e.f1719e;
                    int i7 = this.x * i6;
                    int i8 = i6 * i2;
                    if (this.w == 1) {
                        viewI2.offsetLeftAndRight(i7 - i8);
                    } else {
                        viewI2.offsetTopAndBottom(i7 - i8);
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

    private void G2(int i) {
        f fVar = this.y;
        fVar.f1798e = i;
        fVar.f1797d = this.A != (i == -1) ? -1 : 1;
    }

    private void K2(int i, int i2) {
        for (int i3 = 0; i3 < this.s; i3++) {
            if (!this.t[i3].f1715a.isEmpty()) {
                Q2(this.t[i3], i, i2);
            }
        }
    }

    private boolean L2(RecyclerView.z zVar, b bVar) {
        bVar.f1709a = this.G ? g2(zVar.b()) : c2(zVar.b());
        bVar.f1710b = Integer.MIN_VALUE;
        return true;
    }

    private void M1(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].a(view);
        }
    }

    private void N1(b bVar) {
        SavedState savedState = this.I;
        int i = savedState.f1706d;
        if (i > 0) {
            if (i == this.s) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    this.t[i2].e();
                    SavedState savedState2 = this.I;
                    int i3 = savedState2.f1707e[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        i3 += savedState2.j ? this.u.i() : this.u.m();
                    }
                    this.t[i2].v(i3);
                }
            } else {
                savedState.c();
                SavedState savedState3 = this.I;
                savedState3.f1704b = savedState3.f1705c;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.k;
        I2(savedState4.i);
        E2();
        SavedState savedState5 = this.I;
        int i4 = savedState5.f1704b;
        if (i4 != -1) {
            this.C = i4;
            bVar.f1711c = savedState5.j;
        } else {
            bVar.f1711c = this.A;
        }
        if (savedState5.f > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f1698a = savedState5.g;
            lazySpanLookup.f1699b = savedState5.h;
        }
    }

    private void O2(int i, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        f fVar = this.y;
        boolean z = false;
        fVar.f1795b = 0;
        fVar.f1796c = i;
        if (!x0() || (iC = zVar.c()) == -1) {
            iN = 0;
            iN2 = 0;
        } else {
            if (this.A == (iC < i)) {
                iN = this.u.n();
                iN2 = 0;
            } else {
                iN2 = this.u.n();
                iN = 0;
            }
        }
        if (M()) {
            this.y.f = this.u.m() - iN2;
            this.y.g = this.u.i() + iN;
        } else {
            this.y.g = this.u.h() + iN;
            this.y.f = -iN2;
        }
        f fVar2 = this.y;
        fVar2.h = false;
        fVar2.f1794a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        fVar2.i = z;
    }

    private void Q1(View view, c cVar, f fVar) {
        if (fVar.f1798e == 1) {
            if (cVar.f) {
                M1(view);
                return;
            } else {
                cVar.f1714e.a(view);
                return;
            }
        }
        if (cVar.f) {
            z2(view);
        } else {
            cVar.f1714e.u(view);
        }
    }

    private void Q2(d dVar, int i, int i2) {
        int iJ = dVar.j();
        if (i == -1) {
            if (dVar.o() + iJ <= i2) {
                this.B.set(dVar.f1719e, false);
            }
        } else if (dVar.k() - iJ >= i2) {
            this.B.set(dVar.f1719e, false);
        }
    }

    private int R1(int i) {
        if (J() == 0) {
            return this.A ? 1 : -1;
        }
        return (i < j2()) != this.A ? -1 : 1;
    }

    private int R2(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    private boolean T1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.u.i()) {
                ArrayList<View> arrayList = dVar.f1715a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f;
            }
        } else if (dVar.o() > this.u.m()) {
            return !dVar.n(dVar.f1715a.get(0)).f;
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

    private int X1(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && t2()) ? -1 : 1 : (this.w != 1 && t2()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem Y1(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1702d = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            fullSpanItem.f1702d[i2] = i - this.t[i2].l(i);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem Z1(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1702d = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            fullSpanItem.f1702d[i2] = this.t[i2].p(i) - i;
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
        int i;
        int iE2;
        int iE3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        int i2 = this.y.i ? fVar.f1798e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : fVar.f1798e == 1 ? fVar.g + fVar.f1795b : fVar.f - fVar.f1795b;
        K2(fVar.f1798e, i2);
        int i3 = this.A ? this.u.i() : this.u.m();
        ?? r0 = false;
        while (fVar.a(zVar) && (this.y.i || !this.B.isEmpty())) {
            View viewB = fVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarP2 = cVar.f ? this.t[r9] : p2(fVar);
                this.E.n(iA, dVarP2);
            } else {
                dVarP2 = this.t[iG];
            }
            d dVar = dVarP2;
            cVar.f1714e = dVar;
            if (fVar.f1798e == 1) {
                d(viewB);
            } else {
                e(viewB, r9);
            }
            v2(viewB, cVar, r9);
            if (fVar.f1798e == 1) {
                int iL2 = cVar.f ? l2(i3) : dVar.l(i3);
                int iE4 = this.u.e(viewB) + iL2;
                if (r3 != 0 && cVar.f) {
                    LazySpanLookup.FullSpanItem fullSpanItemY1 = Y1(iL2);
                    fullSpanItemY1.f1701c = -1;
                    fullSpanItemY1.f1700b = iA;
                    this.E.a(fullSpanItemY1);
                }
                i = iE4;
                iE = iL2;
            } else {
                int iO2 = cVar.f ? o2(i3) : dVar.p(i3);
                iE = iO2 - this.u.e(viewB);
                if (r3 != 0 && cVar.f) {
                    LazySpanLookup.FullSpanItem fullSpanItemZ1 = Z1(iO2);
                    fullSpanItemZ1.f1701c = 1;
                    fullSpanItemZ1.f1700b = iA;
                    this.E.a(fullSpanItemZ1);
                }
                i = iO2;
            }
            if (cVar.f && fVar.f1797d == -1) {
                if (r3 != 0) {
                    this.M = true;
                } else {
                    if (!(fVar.f1798e == 1 ? O1() : P1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f1703e = true;
                        }
                        this.M = true;
                    }
                }
            }
            Q1(viewB, cVar, fVar);
            if (t2() && this.w == 1) {
                int i4 = cVar.f ? this.v.i() : this.v.i() - (((this.s - 1) - dVar.f1719e) * this.x);
                iE3 = i4;
                iE2 = i4 - this.v.e(viewB);
            } else {
                int iM = cVar.f ? this.v.m() : (dVar.f1719e * this.x) + this.v.m();
                iE2 = iM;
                iE3 = this.v.e(viewB) + iM;
            }
            if (this.w == 1) {
                z0(viewB, iE2, iE, iE3, i);
            } else {
                z0(viewB, iE, iE2, i, iE3);
            }
            if (cVar.f) {
                K2(this.y.f1798e, i2);
            } else {
                Q2(dVar, this.y.f1798e, i2);
            }
            A2(vVar, this.y);
            if (!this.y.h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.f) {
                this.B.clear();
                z = false;
            } else {
                z = false;
                this.B.set(dVar.f1719e, false);
            }
            r9 = z;
            r0 = true;
        }
        ?? r32 = r9;
        if (r0 == false) {
            A2(vVar, this.y);
        }
        int iM2 = this.y.f1798e == -1 ? this.u.m() - o2(this.u.m()) : l2(this.u.i()) - this.u.i();
        return iM2 > 0 ? Math.min(fVar.f1795b, iM2) : r32 == true ? 1 : 0;
    }

    private int c2(int i) {
        int iJ = J();
        for (int i2 = 0; i2 < iJ; i2++) {
            int iH0 = h0(I(i2));
            if (iH0 >= 0 && iH0 < i) {
                return iH0;
            }
        }
        return 0;
    }

    private int g2(int i) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            int iH0 = h0(I(iJ));
            if (iH0 >= 0 && iH0 < i) {
                return iH0;
            }
        }
        return 0;
    }

    private void h2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i;
        int iL2 = l2(Integer.MIN_VALUE);
        if (iL2 != Integer.MIN_VALUE && (i = this.u.i() - iL2) > 0) {
            int i2 = i - (-F2(-i, vVar, zVar));
            if (!z || i2 <= 0) {
                return;
            }
            this.u.r(i2);
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

    private int l2(int i) {
        int iL = this.t[0].l(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iL2 = this.t[i2].l(i);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int m2(int i) {
        int iP = this.t[0].p(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iP2 = this.t[i2].p(i);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private int n2(int i) {
        int iL = this.t[0].l(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iL2 = this.t[i2].l(i);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int o2(int i) {
        int iP = this.t[0].p(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iP2 = this.t[i2].p(i);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private d p2(f fVar) {
        int i;
        int i2;
        int i3 = -1;
        if (x2(fVar.f1798e)) {
            i = this.s - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.s;
            i2 = 1;
        }
        d dVar = null;
        if (fVar.f1798e == 1) {
            int i4 = Integer.MAX_VALUE;
            int iM = this.u.m();
            while (i != i3) {
                d dVar2 = this.t[i];
                int iL = dVar2.l(iM);
                if (iL < i4) {
                    dVar = dVar2;
                    i4 = iL;
                }
                i += i2;
            }
            return dVar;
        }
        int i5 = Integer.MIN_VALUE;
        int i6 = this.u.i();
        while (i != i3) {
            d dVar3 = this.t[i];
            int iP = dVar3.p(i6);
            if (iP > i5) {
                dVar = dVar3;
                i5 = iP;
            }
            i += i2;
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

    private void u2(View view, int i, int i2, boolean z) {
        j(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iR2 = R2(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iR22 = R2(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? H1(view, iR2, iR22, cVar) : F1(view, iR2, iR22, cVar)) {
            view.measure(iR2, iR22);
        }
    }

    private void v2(View view, c cVar, boolean z) {
        if (cVar.f) {
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

    private boolean x2(int i) {
        if (this.w == 0) {
            return (i == -1) != this.A;
        }
        return ((i == -1) == this.A) == t2();
    }

    private void z2(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].u(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C0(int i) {
        super.C0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].r(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C1(Rect rect, int i, int i2) {
        int iN;
        int iN2;
        int iE0 = e0() + f0();
        int iG0 = g0() + d0();
        if (this.w == 1) {
            iN2 = RecyclerView.o.n(i2, rect.height() + iG0, b0());
            iN = RecyclerView.o.n(i, (this.x * this.s) + iE0, c0());
        } else {
            iN = RecyclerView.o.n(i, rect.width() + iE0, c0());
            iN2 = RecyclerView.o.n(i2, (this.x * this.s) + iG0, b0());
        }
        B1(iN, iN2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return this.w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void D0(int i) {
        super.D0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].r(i);
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

    int F2(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i == 0) {
            return 0;
        }
        y2(i, zVar);
        int iB2 = b2(vVar, this.y, zVar);
        if (this.y.f1795b >= iB2) {
            i = i < 0 ? -iB2 : iB2;
        }
        this.u.r(-i);
        this.G = this.A;
        f fVar = this.y;
        fVar.f1795b = 0;
        A2(vVar, fVar);
        return i;
    }

    public void H2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        g(null);
        if (i == this.w) {
            return;
        }
        this.w = i;
        j jVar = this.u;
        this.u = this.v;
        this.v = jVar;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.I0(recyclerView, vVar);
        o1(this.P);
        for (int i = 0; i < this.s; i++) {
            this.t[i].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i) {
        g gVar = new g(recyclerView.getContext());
        gVar.p(i);
        J1(gVar);
    }

    public void I2(boolean z) {
        g(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.i != z) {
            savedState.i = z;
        }
        this.z = z;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewB;
        View viewM;
        if (J() == 0 || (viewB = B(view)) == null) {
            return null;
        }
        E2();
        int iX1 = X1(i);
        if (iX1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewB.getLayoutParams();
        boolean z = cVar.f;
        d dVar = cVar.f1714e;
        int iK2 = iX1 == 1 ? k2() : j2();
        O2(iK2, zVar);
        G2(iX1);
        f fVar = this.y;
        fVar.f1796c = fVar.f1797d + iK2;
        fVar.f1795b = (int) (this.u.n() * 0.33333334f);
        f fVar2 = this.y;
        fVar2.h = true;
        fVar2.f1794a = false;
        b2(vVar, fVar2, zVar);
        this.G = this.A;
        if (!z && (viewM = dVar.m(iK2, iX1)) != null && viewM != viewB) {
            return viewM;
        }
        if (x2(iX1)) {
            for (int i2 = this.s - 1; i2 >= 0; i2--) {
                View viewM2 = this.t[i2].m(iK2, iX1);
                if (viewM2 != null && viewM2 != viewB) {
                    return viewM2;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.s; i3++) {
                View viewM3 = this.t[i3].m(iK2, iX1);
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
            for (int i4 = this.s - 1; i4 >= 0; i4--) {
                if (i4 != dVar.f1719e) {
                    View viewC2 = C(z2 ? this.t[i4].f() : this.t[i4].g());
                    if (viewC2 != null && viewC2 != viewB) {
                        return viewC2;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.s; i5++) {
                View viewC3 = C(z2 ? this.t[i5].f() : this.t[i5].g());
                if (viewC3 != null && viewC3 != viewB) {
                    return viewC3;
                }
            }
        }
        return null;
    }

    public void J2(int i) {
        g(null);
        if (i != this.s) {
            s2();
            this.s = i;
            this.B = new BitSet(this.s);
            this.t = new d[this.s];
            for (int i2 = 0; i2 < this.s; i2++) {
                this.t[i2] = new d(i2);
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
        int i;
        if (!zVar.e() && (i = this.C) != -1) {
            if (i >= 0 && i < zVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f1704b == -1 || savedState.f1706d < 1) {
                    View viewC = C(this.C);
                    if (viewC != null) {
                        bVar.f1709a = this.A ? k2() : j2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1711c) {
                                bVar.f1710b = (this.u.i() - this.D) - this.u.d(viewC);
                            } else {
                                bVar.f1710b = (this.u.m() + this.D) - this.u.g(viewC);
                            }
                            return true;
                        }
                        if (this.u.e(viewC) > this.u.n()) {
                            bVar.f1710b = bVar.f1711c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int iG = this.u.g(viewC) - this.u.m();
                        if (iG < 0) {
                            bVar.f1710b = -iG;
                            return true;
                        }
                        int i2 = this.u.i() - this.u.d(viewC);
                        if (i2 < 0) {
                            bVar.f1710b = i2;
                            return true;
                        }
                        bVar.f1710b = Integer.MIN_VALUE;
                    } else {
                        int i3 = this.C;
                        bVar.f1709a = i3;
                        int i4 = this.D;
                        if (i4 == Integer.MIN_VALUE) {
                            bVar.f1711c = R1(i3) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i4);
                        }
                        bVar.f1712d = true;
                    }
                } else {
                    bVar.f1710b = Integer.MIN_VALUE;
                    bVar.f1709a = this.C;
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
        bVar.f1709a = 0;
    }

    boolean O1() {
        int iL = this.t[0].l(Integer.MIN_VALUE);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].l(Integer.MIN_VALUE) != iL) {
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
            cVar.f0(c.C0054c.a(cVar2.e(), cVar2.f ? this.s : 1, -1, -1, false, false));
        } else {
            cVar.f0(c.C0054c.a(-1, -1, cVar2.e(), cVar2.f ? this.s : 1, false, false));
        }
    }

    boolean P1() {
        int iP = this.t[0].p(Integer.MIN_VALUE);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    void P2(int i) {
        this.x = i / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i, this.v.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i, int i2) {
        q2(i, i2, 1);
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
        int i = this.A ? -1 : 1;
        int i2 = iK2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.E.e(iJ2, i2, i, true);
        if (fullSpanItemE == null) {
            this.M = false;
            this.E.d(i2);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iJ2, fullSpanItemE.f1700b, i * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f1700b);
        } else {
            this.E.d(fullSpanItemE2.f1700b + 1);
        }
        u1();
        t1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i, int i2, int i3) {
        q2(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i, int i2) {
        q2(i, i2, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i, int i2, Object obj) {
        q2(i, i2, 4);
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
    public PointF a(int i) {
        int iR1 = R1(i);
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
        savedState.i = this.z;
        savedState.j = this.G;
        savedState.k = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1698a) == null) {
            savedState.f = 0;
        } else {
            savedState.g = iArr;
            savedState.f = iArr.length;
            savedState.h = lazySpanLookup.f1699b;
        }
        if (J() > 0) {
            savedState.f1704b = this.G ? k2() : j2();
            savedState.f1705c = f2();
            int i = this.s;
            savedState.f1706d = i;
            savedState.f1707e = new int[i];
            for (int i2 = 0; i2 < this.s; i2++) {
                if (this.G) {
                    iP = this.t[i2].l(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.u.i();
                        iP -= iM;
                    }
                } else {
                    iP = this.t[i2].p(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.u.m();
                        iP -= iM;
                    }
                }
                savedState.f1707e[i2] = iP;
            }
        } else {
            savedState.f1704b = -1;
            savedState.f1705c = -1;
            savedState.f1706d = 0;
        }
        return savedState;
    }

    View d2(boolean z) {
        int iM = this.u.m();
        int i = this.u.i();
        View view = null;
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            int iG = this.u.g(viewI);
            int iD = this.u.d(viewI);
            if (iD > iM && iG < i) {
                if (iD <= i || !z) {
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
    public void e1(int i) {
        if (i == 0) {
            S1();
        }
    }

    View e2(boolean z) {
        int iM = this.u.m();
        int i = this.u.i();
        int iJ = J();
        View view = null;
        for (int i2 = 0; i2 < iJ; i2++) {
            View viewI = I(i2);
            int iG = this.u.g(viewI);
            if (this.u.d(viewI) > iM && iG < i) {
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
    public void o(int i, int i2, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.w != 0) {
            i = i2;
        }
        if (J() == 0 || i == 0) {
            return;
        }
        y2(i, zVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.s; i4++) {
            f fVar = this.y;
            if (fVar.f1797d == -1) {
                iL = fVar.f;
                iP = this.t[i4].p(iL);
            } else {
                iL = this.t[i4].l(fVar.g);
                iP = this.y.g;
            }
            int i5 = iL - iP;
            if (i5 >= 0) {
                this.O[i3] = i5;
                i3++;
            }
        }
        Arrays.sort(this.O, 0, i3);
        for (int i6 = 0; i6 < i3 && this.y.a(zVar); i6++) {
            cVar.a(this.y.f1796c, this.O[i6]);
            f fVar2 = this.y;
            fVar2.f1796c += fVar2.f1797d;
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1714e
            int r9 = r9.f1719e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1714e
            boolean r9 = r12.T1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1714e
            int r9 = r9.f1719e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f1714e
            int r8 = r8.f1719e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f1714e
            int r9 = r9.f1719e
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
    public int w1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i) {
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1704b != i) {
            savedState.a();
        }
        this.C = i;
        this.D = Integer.MIN_VALUE;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i, vVar, zVar);
    }

    void y2(int i, RecyclerView.z zVar) {
        int i2;
        int iJ2;
        if (i > 0) {
            iJ2 = k2();
            i2 = 1;
        } else {
            i2 = -1;
            iJ2 = j2();
        }
        this.y.f1794a = true;
        O2(iJ2, zVar);
        G2(i2);
        f fVar = this.y;
        fVar.f1796c = iJ2 + fVar.f1797d;
        fVar.f1795b = Math.abs(i);
    }

    static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int[] f1698a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        List<FullSpanItem> f1699b;

        LazySpanLookup() {
        }

        private int i(int i) {
            if (this.f1699b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i);
            if (fullSpanItemF != null) {
                this.f1699b.remove(fullSpanItemF);
            }
            int size = this.f1699b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                if (this.f1699b.get(i2).f1700b >= i) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1699b.get(i2);
            this.f1699b.remove(i2);
            return fullSpanItem.f1700b;
        }

        private void l(int i, int i2) {
            List<FullSpanItem> list = this.f1699b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1699b.get(size);
                int i3 = fullSpanItem.f1700b;
                if (i3 >= i) {
                    fullSpanItem.f1700b = i3 + i2;
                }
            }
        }

        private void m(int i, int i2) {
            List<FullSpanItem> list = this.f1699b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1699b.get(size);
                int i4 = fullSpanItem.f1700b;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.f1699b.remove(size);
                    } else {
                        fullSpanItem.f1700b = i4 - i2;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1699b == null) {
                this.f1699b = new ArrayList();
            }
            int size = this.f1699b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.f1699b.get(i);
                if (fullSpanItem2.f1700b == fullSpanItem.f1700b) {
                    this.f1699b.remove(i);
                }
                if (fullSpanItem2.f1700b >= fullSpanItem.f1700b) {
                    this.f1699b.add(i, fullSpanItem);
                    return;
                }
            }
            this.f1699b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.f1698a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1699b = null;
        }

        void c(int i) {
            int[] iArr = this.f1698a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1698a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[o(i)];
                this.f1698a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1698a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i) {
            List<FullSpanItem> list = this.f1699b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1699b.get(size).f1700b >= i) {
                        this.f1699b.remove(size);
                    }
                }
            }
            return h(i);
        }

        public FullSpanItem e(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.f1699b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.f1699b.get(i4);
                int i5 = fullSpanItem.f1700b;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || fullSpanItem.f1701c == i3 || (z && fullSpanItem.f1703e))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i) {
            List<FullSpanItem> list = this.f1699b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1699b.get(size);
                if (fullSpanItem.f1700b == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i) {
            int[] iArr = this.f1698a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        int h(int i) {
            int[] iArr = this.f1698a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int i2 = i(i);
            if (i2 == -1) {
                int[] iArr2 = this.f1698a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.f1698a.length;
            }
            int i3 = i2 + 1;
            Arrays.fill(this.f1698a, i, i3, -1);
            return i3;
        }

        void j(int i, int i2) {
            int[] iArr = this.f1698a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1698a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1698a, i, i3, -1);
            l(i, i2);
        }

        void k(int i, int i2) {
            int[] iArr = this.f1698a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1698a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1698a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            m(i, i2);
        }

        void n(int i, d dVar) {
            c(i);
            this.f1698a[i] = dVar.f1719e;
        }

        int o(int i) {
            int length = this.f1698a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f1700b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1701c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int[] f1702d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            boolean f1703e;

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
                public FullSpanItem[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            }

            FullSpanItem(Parcel parcel) {
                this.f1700b = parcel.readInt();
                this.f1701c = parcel.readInt();
                this.f1703e = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.f1702d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int a(int i) {
                int[] iArr = this.f1702d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1700b + ", mGapDir=" + this.f1701c + ", mHasUnwantedGapAfter=" + this.f1703e + ", mGapPerSpan=" + Arrays.toString(this.f1702d) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1700b);
                parcel.writeInt(this.f1701c);
                parcel.writeInt(this.f1703e ? 1 : 0);
                int[] iArr = this.f1702d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1702d);
                }
            }

            FullSpanItem() {
            }
        }
    }
}
