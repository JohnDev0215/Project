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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import m0.c;
import w0.f;
import w0.g;
import w0.j;
import w0.m;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.y.b {
    public BitSet B;
    public boolean G;
    public boolean H;
    public SavedState I;
    public int J;
    public int[] O;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public d[] f1585t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public j f1586u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public j f1587v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1588w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1589x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final f f1590y;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1584s = -1;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1591z = false;
    public boolean A = false;
    public int C = -1;
    public int D = Integer.MIN_VALUE;
    public LazySpanLookup E = new LazySpanLookup();
    public int F = 2;
    public final Rect K = new Rect();
    public final b L = new b();
    public boolean M = false;
    public boolean N = true;
    public final Runnable P = new a();

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1598b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1599c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1600d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f1601e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1602f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int[] f1603g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public List<LazySpanLookup.FullSpanItem> f1604h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1605i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1606j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1607k;

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

        public void f() {
            this.f1601e = null;
            this.f1600d = 0;
            this.f1598b = -1;
            this.f1599c = -1;
        }

        public void g() {
            this.f1601e = null;
            this.f1600d = 0;
            this.f1602f = 0;
            this.f1603g = null;
            this.f1604h = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            parcel.writeInt(this.f1598b);
            parcel.writeInt(this.f1599c);
            parcel.writeInt(this.f1600d);
            if (this.f1600d > 0) {
                parcel.writeIntArray(this.f1601e);
            }
            parcel.writeInt(this.f1602f);
            if (this.f1602f > 0) {
                parcel.writeIntArray(this.f1603g);
            }
            parcel.writeInt(this.f1605i ? 1 : 0);
            parcel.writeInt(this.f1606j ? 1 : 0);
            parcel.writeInt(this.f1607k ? 1 : 0);
            parcel.writeList(this.f1604h);
        }

        public SavedState(Parcel parcel) {
            this.f1598b = parcel.readInt();
            this.f1599c = parcel.readInt();
            int i3 = parcel.readInt();
            this.f1600d = i3;
            if (i3 > 0) {
                int[] iArr = new int[i3];
                this.f1601e = iArr;
                parcel.readIntArray(iArr);
            }
            int i4 = parcel.readInt();
            this.f1602f = i4;
            if (i4 > 0) {
                int[] iArr2 = new int[i4];
                this.f1603g = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f1605i = parcel.readInt() == 1;
            this.f1606j = parcel.readInt() == 1;
            this.f1607k = parcel.readInt() == 1;
            this.f1604h = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f1600d = savedState.f1600d;
            this.f1598b = savedState.f1598b;
            this.f1599c = savedState.f1599c;
            this.f1601e = savedState.f1601e;
            this.f1602f = savedState.f1602f;
            this.f1603g = savedState.f1603g;
            this.f1605i = savedState.f1605i;
            this.f1606j = savedState.f1606j;
            this.f1607k = savedState.f1607k;
            this.f1604h = savedState.f1604h;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.S1();
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1609a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1610b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1611c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1612d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1613e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f1614f;

        public b() {
            c();
        }

        public void a() {
            this.f1610b = this.f1611c ? StaggeredGridLayoutManager.this.f1586u.i() : StaggeredGridLayoutManager.this.f1586u.m();
        }

        public void b(int i3) {
            if (this.f1611c) {
                this.f1610b = StaggeredGridLayoutManager.this.f1586u.i() - i3;
            } else {
                this.f1610b = StaggeredGridLayoutManager.this.f1586u.m() + i3;
            }
        }

        public void c() {
            this.f1609a = -1;
            this.f1610b = Integer.MIN_VALUE;
            this.f1611c = false;
            this.f1612d = false;
            this.f1613e = false;
            int[] iArr = this.f1614f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f1614f;
            if (iArr == null || iArr.length < length) {
                this.f1614f = new int[StaggeredGridLayoutManager.this.f1585t.length];
            }
            for (int i3 = 0; i3 < length; i3++) {
                this.f1614f[i3] = dVarArr[i3].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d f1616e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1617f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f1616e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f1622e;
        }

        public boolean f() {
            return this.f1617f;
        }

        public c(int i3, int i4) {
            super(i3, i4);
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList<View> f1618a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1619b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1620c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1621d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1622e;

        public d(int i3) {
            this.f1622e = i3;
        }

        public void a(View view) {
            c cVarN = n(view);
            cVarN.f1616e = this;
            this.f1618a.add(view);
            this.f1620c = Integer.MIN_VALUE;
            if (this.f1618a.size() == 1) {
                this.f1619b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1621d += StaggeredGridLayoutManager.this.f1586u.e(view);
            }
        }

        public void b(boolean z3, int i3) {
            int iL = z3 ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (iL == Integer.MIN_VALUE) {
                return;
            }
            if (!z3 || iL >= StaggeredGridLayoutManager.this.f1586u.i()) {
                if (z3 || iL <= StaggeredGridLayoutManager.this.f1586u.m()) {
                    if (i3 != Integer.MIN_VALUE) {
                        iL += i3;
                    }
                    this.f1620c = iL;
                    this.f1619b = iL;
                }
            }
        }

        public void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1618a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1620c = StaggeredGridLayoutManager.this.f1586u.d(view);
            if (cVarN.f1617f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1595c == 1) {
                this.f1620c += fullSpanItemF.f(this.f1622e);
            }
        }

        public void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1618a.get(0);
            c cVarN = n(view);
            this.f1619b = StaggeredGridLayoutManager.this.f1586u.g(view);
            if (cVarN.f1617f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1595c == -1) {
                this.f1619b -= fullSpanItemF.f(this.f1622e);
            }
        }

        public void e() {
            this.f1618a.clear();
            q();
            this.f1621d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.f1591z ? i(this.f1618a.size() - 1, -1, true) : i(0, this.f1618a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.f1591z ? i(0, this.f1618a.size(), true) : i(this.f1618a.size() - 1, -1, true);
        }

        public int h(int i3, int i4, boolean z3, boolean z4, boolean z5) {
            int iM = StaggeredGridLayoutManager.this.f1586u.m();
            int i5 = StaggeredGridLayoutManager.this.f1586u.i();
            int i6 = i4 > i3 ? 1 : -1;
            while (i3 != i4) {
                View view = this.f1618a.get(i3);
                int iG = StaggeredGridLayoutManager.this.f1586u.g(view);
                int iD = StaggeredGridLayoutManager.this.f1586u.d(view);
                boolean z6 = false;
                boolean z7 = !z5 ? iG >= i5 : iG > i5;
                if (!z5 ? iD > iM : iD >= iM) {
                    z6 = true;
                }
                if (z7 && z6) {
                    if (z3 && z4) {
                        if (iG >= iM && iD <= i5) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    } else {
                        if (z4) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                        if (iG < iM || iD > i5) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    }
                }
                i3 += i6;
            }
            return -1;
        }

        public int i(int i3, int i4, boolean z3) {
            return h(i3, i4, false, false, z3);
        }

        public int j() {
            return this.f1621d;
        }

        public int k() {
            int i3 = this.f1620c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            c();
            return this.f1620c;
        }

        public int l(int i3) {
            int i4 = this.f1620c;
            if (i4 != Integer.MIN_VALUE) {
                return i4;
            }
            if (this.f1618a.size() == 0) {
                return i3;
            }
            c();
            return this.f1620c;
        }

        public View m(int i3, int i4) {
            View view = null;
            if (i4 != -1) {
                int size = this.f1618a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1618a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.f1591z && staggeredGridLayoutManager.h0(view2) >= i3) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.f1591z && staggeredGridLayoutManager2.h0(view2) <= i3) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1618a.size();
                int i5 = 0;
                while (i5 < size2) {
                    View view3 = this.f1618a.get(i5);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.f1591z && staggeredGridLayoutManager3.h0(view3) <= i3) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.f1591z && staggeredGridLayoutManager4.h0(view3) >= i3) || !view3.hasFocusable()) {
                        break;
                    }
                    i5++;
                    view = view3;
                }
            }
            return view;
        }

        public c n(View view) {
            return (c) view.getLayoutParams();
        }

        public int o() {
            int i3 = this.f1619b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            d();
            return this.f1619b;
        }

        public int p(int i3) {
            int i4 = this.f1619b;
            if (i4 != Integer.MIN_VALUE) {
                return i4;
            }
            if (this.f1618a.size() == 0) {
                return i3;
            }
            d();
            return this.f1619b;
        }

        public void q() {
            this.f1619b = Integer.MIN_VALUE;
            this.f1620c = Integer.MIN_VALUE;
        }

        public void r(int i3) {
            int i4 = this.f1619b;
            if (i4 != Integer.MIN_VALUE) {
                this.f1619b = i4 + i3;
            }
            int i5 = this.f1620c;
            if (i5 != Integer.MIN_VALUE) {
                this.f1620c = i5 + i3;
            }
        }

        public void s() {
            int size = this.f1618a.size();
            View viewRemove = this.f1618a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f1616e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f1621d -= StaggeredGridLayoutManager.this.f1586u.e(viewRemove);
            }
            if (size == 1) {
                this.f1619b = Integer.MIN_VALUE;
            }
            this.f1620c = Integer.MIN_VALUE;
        }

        public void t() {
            View viewRemove = this.f1618a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f1616e = null;
            if (this.f1618a.size() == 0) {
                this.f1620c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1621d -= StaggeredGridLayoutManager.this.f1586u.e(viewRemove);
            }
            this.f1619b = Integer.MIN_VALUE;
        }

        public void u(View view) {
            c cVarN = n(view);
            cVarN.f1616e = this;
            this.f1618a.add(0, view);
            this.f1619b = Integer.MIN_VALUE;
            if (this.f1618a.size() == 1) {
                this.f1620c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1621d += StaggeredGridLayoutManager.this.f1586u.e(view);
            }
        }

        public void v(int i3) {
            this.f1619b = i3;
            this.f1620c = i3;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i3, int i4) {
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i3, i4);
        H2(dVarI0.f1528a);
        J2(dVarI0.f1529b);
        I2(dVarI0.f1530c);
        this.f1590y = new f();
        a2();
    }

    public final void A2(RecyclerView.v vVar, f fVar) {
        if (!fVar.f5631a || fVar.f5639i) {
            return;
        }
        if (fVar.f5632b == 0) {
            if (fVar.f5635e == -1) {
                B2(vVar, fVar.f5637g);
                return;
            } else {
                C2(vVar, fVar.f5636f);
                return;
            }
        }
        if (fVar.f5635e != -1) {
            int iN2 = n2(fVar.f5637g) - fVar.f5637g;
            C2(vVar, iN2 < 0 ? fVar.f5636f : Math.min(iN2, fVar.f5632b) + fVar.f5636f);
        } else {
            int i3 = fVar.f5636f;
            int iM2 = i3 - m2(i3);
            B2(vVar, iM2 < 0 ? fVar.f5637g : fVar.f5637g - Math.min(iM2, fVar.f5632b));
        }
    }

    public final void B2(RecyclerView.v vVar, int i3) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            if (this.f1586u.g(viewI) < i3 || this.f1586u.q(viewI) < i3) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f1617f) {
                for (int i4 = 0; i4 < this.f1584s; i4++) {
                    if (this.f1585t[i4].f1618a.size() == 1) {
                        return;
                    }
                }
                for (int i5 = 0; i5 < this.f1584s; i5++) {
                    this.f1585t[i5].s();
                }
            } else if (cVar.f1616e.f1618a.size() == 1) {
                return;
            } else {
                cVar.f1616e.s();
            }
            m1(viewI, vVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C0(int i3) {
        super.C0(i3);
        for (int i4 = 0; i4 < this.f1584s; i4++) {
            this.f1585t[i4].r(i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C1(Rect rect, int i3, int i4) {
        int iN;
        int iN2;
        int iE0 = e0() + f0();
        int iG0 = g0() + d0();
        if (this.f1588w == 1) {
            iN2 = RecyclerView.o.n(i4, rect.height() + iG0, b0());
            iN = RecyclerView.o.n(i3, (this.f1589x * this.f1584s) + iE0, c0());
        } else {
            iN = RecyclerView.o.n(i3, rect.width() + iE0, c0());
            iN2 = RecyclerView.o.n(i4, (this.f1589x * this.f1584s) + iG0, b0());
        }
        B1(iN, iN2);
    }

    public final void C2(RecyclerView.v vVar, int i3) {
        while (J() > 0) {
            View viewI = I(0);
            if (this.f1586u.d(viewI) > i3 || this.f1586u.p(viewI) > i3) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f1617f) {
                for (int i4 = 0; i4 < this.f1584s; i4++) {
                    if (this.f1585t[i4].f1618a.size() == 1) {
                        return;
                    }
                }
                for (int i5 = 0; i5 < this.f1584s; i5++) {
                    this.f1585t[i5].t();
                }
            } else if (cVar.f1616e.f1618a.size() == 1) {
                return;
            } else {
                cVar.f1616e.t();
            }
            m1(viewI, vVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return this.f1588w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void D0(int i3) {
        super.D0(i3);
        for (int i4 = 0; i4 < this.f1584s; i4++) {
            this.f1585t[i4].r(i3);
        }
    }

    public final void D2() {
        if (this.f1587v.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int iJ = J();
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI = I(i3);
            float fE = this.f1587v.e(viewI);
            if (fE >= fMax) {
                if (((c) viewI.getLayoutParams()).f()) {
                    fE = (fE * 1.0f) / this.f1584s;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i4 = this.f1589x;
        int iRound = Math.round(fMax * this.f1584s);
        if (this.f1587v.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.f1587v.n());
        }
        P2(iRound);
        if (this.f1589x == i4) {
            return;
        }
        for (int i5 = 0; i5 < iJ; i5++) {
            View viewI2 = I(i5);
            c cVar = (c) viewI2.getLayoutParams();
            if (!cVar.f1617f) {
                if (t2() && this.f1588w == 1) {
                    int i6 = this.f1584s;
                    int i7 = cVar.f1616e.f1622e;
                    viewI2.offsetLeftAndRight(((-((i6 - 1) - i7)) * this.f1589x) - ((-((i6 - 1) - i7)) * i4));
                } else {
                    int i8 = cVar.f1616e.f1622e;
                    int i9 = this.f1589x * i8;
                    int i10 = i8 * i4;
                    if (this.f1588w == 1) {
                        viewI2.offsetLeftAndRight(i9 - i10);
                    } else {
                        viewI2.offsetTopAndBottom(i9 - i10);
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    public final void E2() {
        if (this.f1588w == 1 || !t2()) {
            this.A = this.f1591z;
        } else {
            this.A = !this.f1591z;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p F(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public int F2(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i3 == 0) {
            return 0;
        }
        y2(i3, zVar);
        int iB2 = b2(vVar, this.f1590y, zVar);
        if (this.f1590y.f5632b >= iB2) {
            i3 = i3 < 0 ? -iB2 : iB2;
        }
        this.f1586u.r(-i3);
        this.G = this.A;
        f fVar = this.f1590y;
        fVar.f5632b = 0;
        A2(vVar, fVar);
        return i3;
    }

    public final void G2(int i3) {
        f fVar = this.f1590y;
        fVar.f5635e = i3;
        fVar.f5634d = this.A != (i3 == -1) ? -1 : 1;
    }

    public void H2(int i3) {
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        g(null);
        if (i3 == this.f1588w) {
            return;
        }
        this.f1588w = i3;
        j jVar = this.f1586u;
        this.f1586u = this.f1587v;
        this.f1587v = jVar;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.I0(recyclerView, vVar);
        o1(this.P);
        for (int i3 = 0; i3 < this.f1584s; i3++) {
            this.f1585t[i3].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i3) {
        g gVar = new g(recyclerView.getContext());
        gVar.p(i3);
        J1(gVar);
    }

    public void I2(boolean z3) {
        g(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1605i != z3) {
            savedState.f1605i = z3;
        }
        this.f1591z = z3;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewB;
        View viewM;
        if (J() == 0 || (viewB = B(view)) == null) {
            return null;
        }
        E2();
        int iX1 = X1(i3);
        if (iX1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewB.getLayoutParams();
        boolean z3 = cVar.f1617f;
        d dVar = cVar.f1616e;
        int iK2 = iX1 == 1 ? k2() : j2();
        O2(iK2, zVar);
        G2(iX1);
        f fVar = this.f1590y;
        fVar.f5633c = fVar.f5634d + iK2;
        fVar.f5632b = (int) (this.f1586u.n() * 0.33333334f);
        f fVar2 = this.f1590y;
        fVar2.f5638h = true;
        fVar2.f5631a = false;
        b2(vVar, fVar2, zVar);
        this.G = this.A;
        if (!z3 && (viewM = dVar.m(iK2, iX1)) != null && viewM != viewB) {
            return viewM;
        }
        if (x2(iX1)) {
            for (int i4 = this.f1584s - 1; i4 >= 0; i4--) {
                View viewM2 = this.f1585t[i4].m(iK2, iX1);
                if (viewM2 != null && viewM2 != viewB) {
                    return viewM2;
                }
            }
        } else {
            for (int i5 = 0; i5 < this.f1584s; i5++) {
                View viewM3 = this.f1585t[i5].m(iK2, iX1);
                if (viewM3 != null && viewM3 != viewB) {
                    return viewM3;
                }
            }
        }
        boolean z4 = (this.f1591z ^ true) == (iX1 == -1);
        if (!z3) {
            View viewC = C(z4 ? dVar.f() : dVar.g());
            if (viewC != null && viewC != viewB) {
                return viewC;
            }
        }
        if (x2(iX1)) {
            for (int i6 = this.f1584s - 1; i6 >= 0; i6--) {
                if (i6 != dVar.f1622e) {
                    View viewC2 = C(z4 ? this.f1585t[i6].f() : this.f1585t[i6].g());
                    if (viewC2 != null && viewC2 != viewB) {
                        return viewC2;
                    }
                }
            }
        } else {
            for (int i7 = 0; i7 < this.f1584s; i7++) {
                View viewC3 = C(z4 ? this.f1585t[i7].f() : this.f1585t[i7].g());
                if (viewC3 != null && viewC3 != viewB) {
                    return viewC3;
                }
            }
        }
        return null;
    }

    public void J2(int i3) {
        g(null);
        if (i3 != this.f1584s) {
            s2();
            this.f1584s = i3;
            this.B = new BitSet(this.f1584s);
            this.f1585t = new d[this.f1584s];
            for (int i4 = 0; i4 < this.f1584s; i4++) {
                this.f1585t[i4] = new d(i4);
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

    public final void K2(int i3, int i4) {
        for (int i5 = 0; i5 < this.f1584s; i5++) {
            if (!this.f1585t[i5].f1618a.isEmpty()) {
                Q2(this.f1585t[i5], i3, i4);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean L1() {
        return this.I == null;
    }

    public final boolean L2(RecyclerView.z zVar, b bVar) {
        bVar.f1609a = this.G ? g2(zVar.b()) : c2(zVar.b());
        bVar.f1610b = Integer.MIN_VALUE;
        return true;
    }

    public final void M1(View view) {
        for (int i3 = this.f1584s - 1; i3 >= 0; i3--) {
            this.f1585t[i3].a(view);
        }
    }

    public boolean M2(RecyclerView.z zVar, b bVar) {
        int i3;
        if (!zVar.e() && (i3 = this.C) != -1) {
            if (i3 >= 0 && i3 < zVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f1598b == -1 || savedState.f1600d < 1) {
                    View viewC = C(this.C);
                    if (viewC != null) {
                        bVar.f1609a = this.A ? k2() : j2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1611c) {
                                bVar.f1610b = (this.f1586u.i() - this.D) - this.f1586u.d(viewC);
                            } else {
                                bVar.f1610b = (this.f1586u.m() + this.D) - this.f1586u.g(viewC);
                            }
                            return true;
                        }
                        if (this.f1586u.e(viewC) > this.f1586u.n()) {
                            bVar.f1610b = bVar.f1611c ? this.f1586u.i() : this.f1586u.m();
                            return true;
                        }
                        int iG = this.f1586u.g(viewC) - this.f1586u.m();
                        if (iG < 0) {
                            bVar.f1610b = -iG;
                            return true;
                        }
                        int i4 = this.f1586u.i() - this.f1586u.d(viewC);
                        if (i4 < 0) {
                            bVar.f1610b = i4;
                            return true;
                        }
                        bVar.f1610b = Integer.MIN_VALUE;
                    } else {
                        int i5 = this.C;
                        bVar.f1609a = i5;
                        int i6 = this.D;
                        if (i6 == Integer.MIN_VALUE) {
                            bVar.f1611c = R1(i5) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i6);
                        }
                        bVar.f1612d = true;
                    }
                } else {
                    bVar.f1610b = Integer.MIN_VALUE;
                    bVar.f1609a = this.C;
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
        return this.f1588w == 1 ? this.f1584s : super.N(vVar, zVar);
    }

    public final void N1(b bVar) {
        SavedState savedState = this.I;
        int i3 = savedState.f1600d;
        if (i3 > 0) {
            if (i3 == this.f1584s) {
                for (int i4 = 0; i4 < this.f1584s; i4++) {
                    this.f1585t[i4].e();
                    SavedState savedState2 = this.I;
                    int i5 = savedState2.f1601e[i4];
                    if (i5 != Integer.MIN_VALUE) {
                        i5 += savedState2.f1606j ? this.f1586u.i() : this.f1586u.m();
                    }
                    this.f1585t[i4].v(i5);
                }
            } else {
                savedState.g();
                SavedState savedState3 = this.I;
                savedState3.f1598b = savedState3.f1599c;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.f1607k;
        I2(savedState4.f1605i);
        E2();
        SavedState savedState5 = this.I;
        int i6 = savedState5.f1598b;
        if (i6 != -1) {
            this.C = i6;
            bVar.f1611c = savedState5.f1606j;
        } else {
            bVar.f1611c = this.A;
        }
        if (savedState5.f1602f > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f1592a = savedState5.f1603g;
            lazySpanLookup.f1593b = savedState5.f1604h;
        }
    }

    public void N2(RecyclerView.z zVar, b bVar) {
        if (M2(zVar, bVar) || L2(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1609a = 0;
    }

    public boolean O1() {
        int iL = this.f1585t[0].l(Integer.MIN_VALUE);
        for (int i3 = 1; i3 < this.f1584s; i3++) {
            if (this.f1585t[i3].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    public final void O2(int i3, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        f fVar = this.f1590y;
        boolean z3 = false;
        fVar.f5632b = 0;
        fVar.f5633c = i3;
        if (!x0() || (iC = zVar.c()) == -1) {
            iN = 0;
            iN2 = 0;
        } else {
            if (this.A == (iC < i3)) {
                iN = this.f1586u.n();
                iN2 = 0;
            } else {
                iN2 = this.f1586u.n();
                iN = 0;
            }
        }
        if (M()) {
            this.f1590y.f5636f = this.f1586u.m() - iN2;
            this.f1590y.f5637g = this.f1586u.i() + iN;
        } else {
            this.f1590y.f5637g = this.f1586u.h() + iN;
            this.f1590y.f5636f = -iN2;
        }
        f fVar2 = this.f1590y;
        fVar2.f5638h = false;
        fVar2.f5631a = true;
        if (this.f1586u.k() == 0 && this.f1586u.h() == 0) {
            z3 = true;
        }
        fVar2.f5639i = z3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView.v vVar, RecyclerView.z zVar, View view, m0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.O0(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.f1588w == 0) {
            cVar.e0(c.C0036c.a(cVar2.e(), cVar2.f1617f ? this.f1584s : 1, -1, -1, false, false));
        } else {
            cVar.e0(c.C0036c.a(-1, -1, cVar2.e(), cVar2.f1617f ? this.f1584s : 1, false, false));
        }
    }

    public boolean P1() {
        int iP = this.f1585t[0].p(Integer.MIN_VALUE);
        for (int i3 = 1; i3 < this.f1584s; i3++) {
            if (this.f1585t[i3].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    public void P2(int i3) {
        this.f1589x = i3 / this.f1584s;
        this.J = View.MeasureSpec.makeMeasureSpec(i3, this.f1587v.k());
    }

    public final void Q1(View view, c cVar, f fVar) {
        if (fVar.f5635e == 1) {
            if (cVar.f1617f) {
                M1(view);
                return;
            } else {
                cVar.f1616e.a(view);
                return;
            }
        }
        if (cVar.f1617f) {
            z2(view);
        } else {
            cVar.f1616e.u(view);
        }
    }

    public final void Q2(d dVar, int i3, int i4) {
        int iJ = dVar.j();
        if (i3 == -1) {
            if (dVar.o() + iJ <= i4) {
                this.B.set(dVar.f1622e, false);
            }
        } else if (dVar.k() - iJ >= i4) {
            this.B.set(dVar.f1622e, false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i3, int i4) {
        q2(i3, i4, 1);
    }

    public final int R1(int i3) {
        if (J() == 0) {
            return this.A ? 1 : -1;
        }
        return (i3 < j2()) != this.A ? -1 : 1;
    }

    public final int R2(int i3, int i4, int i5) {
        if (i4 == 0 && i5 == 0) {
            return i3;
        }
        int mode = View.MeasureSpec.getMode(i3);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i3) - i4) - i5), mode) : i3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(RecyclerView recyclerView) {
        this.E.b();
        t1();
    }

    public boolean S1() {
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
        int i3 = this.A ? -1 : 1;
        int i4 = iK2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.E.e(iJ2, i4, i3, true);
        if (fullSpanItemE == null) {
            this.M = false;
            this.E.d(i4);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iJ2, fullSpanItemE.f1594b, i3 * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f1594b);
        } else {
            this.E.d(fullSpanItemE2.f1594b + 1);
        }
        u1();
        t1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i3, int i4, int i5) {
        q2(i3, i4, 8);
    }

    public final boolean T1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.f1586u.i()) {
                ArrayList<View> arrayList = dVar.f1618a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f1617f;
            }
        } else if (dVar.o() > this.f1586u.m()) {
            return !dVar.n(dVar.f1618a.get(0)).f1617f;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i3, int i4) {
        q2(i3, i4, 2);
    }

    public final int U1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.a(zVar, this.f1586u, e2(!this.N), d2(!this.N), this, this.N);
    }

    public final int V1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.b(zVar, this.f1586u, e2(!this.N), d2(!this.N), this, this.N, this.A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i3, int i4, Object obj) {
        q2(i3, i4, 4);
    }

    public final int W1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.c(zVar, this.f1586u, e2(!this.N), d2(!this.N), this, this.N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        w2(vVar, zVar, true);
    }

    public final int X1(int i3) {
        return i3 != 1 ? i3 != 2 ? i3 != 17 ? i3 != 33 ? i3 != 66 ? (i3 == 130 && this.f1588w == 1) ? 1 : Integer.MIN_VALUE : this.f1588w == 0 ? 1 : Integer.MIN_VALUE : this.f1588w == 1 ? -1 : Integer.MIN_VALUE : this.f1588w == 0 ? -1 : Integer.MIN_VALUE : (this.f1588w != 1 && t2()) ? -1 : 1 : (this.f1588w != 1 && t2()) ? 1 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    public final LazySpanLookup.FullSpanItem Y1(int i3) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1596d = new int[this.f1584s];
        for (int i4 = 0; i4 < this.f1584s; i4++) {
            fullSpanItem.f1596d[i4] = i3 - this.f1585t[i4].l(i3);
        }
        return fullSpanItem;
    }

    public final LazySpanLookup.FullSpanItem Z1(int i3) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1596d = new int[this.f1584s];
        for (int i4 = 0; i4 < this.f1584s; i4++) {
            fullSpanItem.f1596d[i4] = this.f1585t[i4].p(i3) - i3;
        }
        return fullSpanItem;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF a(int i3) {
        int iR1 = R1(i3);
        PointF pointF = new PointF();
        if (iR1 == 0) {
            return null;
        }
        if (this.f1588w == 0) {
            pointF.x = iR1;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iR1;
        }
        return pointF;
    }

    public final void a2() {
        this.f1586u = j.b(this, this.f1588w);
        this.f1587v = j.b(this, 1 - this.f1588w);
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
    public final int b2(RecyclerView.v vVar, f fVar, RecyclerView.z zVar) {
        d dVarP2;
        int iE;
        int i3;
        int iE2;
        int iE3;
        boolean z3;
        ?? r9 = 0;
        this.B.set(0, this.f1584s, true);
        int i4 = this.f1590y.f5639i ? fVar.f5635e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : fVar.f5635e == 1 ? fVar.f5637g + fVar.f5632b : fVar.f5636f - fVar.f5632b;
        K2(fVar.f5635e, i4);
        int i5 = this.A ? this.f1586u.i() : this.f1586u.m();
        ?? r02 = false;
        while (fVar.a(zVar) && (this.f1590y.f5639i || !this.B.isEmpty())) {
            View viewB = fVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarP2 = cVar.f1617f ? this.f1585t[r9] : p2(fVar);
                this.E.n(iA, dVarP2);
            } else {
                dVarP2 = this.f1585t[iG];
            }
            d dVar = dVarP2;
            cVar.f1616e = dVar;
            if (fVar.f5635e == 1) {
                d(viewB);
            } else {
                e(viewB, r9);
            }
            v2(viewB, cVar, r9);
            if (fVar.f5635e == 1) {
                int iL2 = cVar.f1617f ? l2(i5) : dVar.l(i5);
                int iE4 = this.f1586u.e(viewB) + iL2;
                if (r3 != 0 && cVar.f1617f) {
                    LazySpanLookup.FullSpanItem fullSpanItemY1 = Y1(iL2);
                    fullSpanItemY1.f1595c = -1;
                    fullSpanItemY1.f1594b = iA;
                    this.E.a(fullSpanItemY1);
                }
                i3 = iE4;
                iE = iL2;
            } else {
                int iO2 = cVar.f1617f ? o2(i5) : dVar.p(i5);
                iE = iO2 - this.f1586u.e(viewB);
                if (r3 != 0 && cVar.f1617f) {
                    LazySpanLookup.FullSpanItem fullSpanItemZ1 = Z1(iO2);
                    fullSpanItemZ1.f1595c = 1;
                    fullSpanItemZ1.f1594b = iA;
                    this.E.a(fullSpanItemZ1);
                }
                i3 = iO2;
            }
            if (cVar.f1617f && fVar.f5634d == -1) {
                if (r3 != 0) {
                    this.M = true;
                } else {
                    if (!(fVar.f5635e == 1 ? O1() : P1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f1597e = true;
                        }
                        this.M = true;
                    }
                }
            }
            Q1(viewB, cVar, fVar);
            if (t2() && this.f1588w == 1) {
                int i6 = cVar.f1617f ? this.f1587v.i() : this.f1587v.i() - (((this.f1584s - 1) - dVar.f1622e) * this.f1589x);
                iE3 = i6;
                iE2 = i6 - this.f1587v.e(viewB);
            } else {
                int iM = cVar.f1617f ? this.f1587v.m() : (dVar.f1622e * this.f1589x) + this.f1587v.m();
                iE2 = iM;
                iE3 = this.f1587v.e(viewB) + iM;
            }
            if (this.f1588w == 1) {
                z0(viewB, iE2, iE, iE3, i3);
            } else {
                z0(viewB, iE, iE2, i3, iE3);
            }
            if (cVar.f1617f) {
                K2(this.f1590y.f5635e, i4);
            } else {
                Q2(dVar, this.f1590y.f5635e, i4);
            }
            A2(vVar, this.f1590y);
            if (!this.f1590y.f5638h || !viewB.hasFocusable()) {
                z3 = false;
            } else if (cVar.f1617f) {
                this.B.clear();
                z3 = false;
            } else {
                z3 = false;
                this.B.set(dVar.f1622e, false);
            }
            r9 = z3;
            r02 = true;
        }
        ?? r32 = r9;
        if (r02 == false) {
            A2(vVar, this.f1590y);
        }
        int iM2 = this.f1590y.f5635e == -1 ? this.f1586u.m() - o2(this.f1586u.m()) : l2(this.f1586u.i()) - this.f1586u.i();
        return iM2 > 0 ? Math.min(fVar.f5632b, iM2) : r32 == true ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.I = (SavedState) parcelable;
            t1();
        }
    }

    public final int c2(int i3) {
        int iJ = J();
        for (int i4 = 0; i4 < iJ; i4++) {
            int iH0 = h0(I(i4));
            if (iH0 >= 0 && iH0 < i3) {
                return iH0;
            }
        }
        return 0;
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
        savedState.f1605i = this.f1591z;
        savedState.f1606j = this.G;
        savedState.f1607k = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1592a) == null) {
            savedState.f1602f = 0;
        } else {
            savedState.f1603g = iArr;
            savedState.f1602f = iArr.length;
            savedState.f1604h = lazySpanLookup.f1593b;
        }
        if (J() > 0) {
            savedState.f1598b = this.G ? k2() : j2();
            savedState.f1599c = f2();
            int i3 = this.f1584s;
            savedState.f1600d = i3;
            savedState.f1601e = new int[i3];
            for (int i4 = 0; i4 < this.f1584s; i4++) {
                if (this.G) {
                    iP = this.f1585t[i4].l(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.f1586u.i();
                        iP -= iM;
                    }
                } else {
                    iP = this.f1585t[i4].p(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.f1586u.m();
                        iP -= iM;
                    }
                }
                savedState.f1601e[i4] = iP;
            }
        } else {
            savedState.f1598b = -1;
            savedState.f1599c = -1;
            savedState.f1600d = 0;
        }
        return savedState;
    }

    public View d2(boolean z3) {
        int iM = this.f1586u.m();
        int i3 = this.f1586u.i();
        View view = null;
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            int iG = this.f1586u.g(viewI);
            int iD = this.f1586u.d(viewI);
            if (iD > iM && iG < i3) {
                if (iD <= i3 || !z3) {
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
    public void e1(int i3) {
        if (i3 == 0) {
            S1();
        }
    }

    public View e2(boolean z3) {
        int iM = this.f1586u.m();
        int i3 = this.f1586u.i();
        int iJ = J();
        View view = null;
        for (int i4 = 0; i4 < iJ; i4++) {
            View viewI = I(i4);
            int iG = this.f1586u.g(viewI);
            if (this.f1586u.d(viewI) > iM && iG < i3) {
                if (iG >= iM || !z3) {
                    return viewI;
                }
                if (view == null) {
                    view = viewI;
                }
            }
        }
        return view;
    }

    public int f2() {
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

    public final int g2(int i3) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            int iH0 = h0(I(iJ));
            if (iH0 >= 0 && iH0 < i3) {
                return iH0;
            }
        }
        return 0;
    }

    public final void h2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z3) {
        int i3;
        int iL2 = l2(Integer.MIN_VALUE);
        if (iL2 != Integer.MIN_VALUE && (i3 = this.f1586u.i() - iL2) > 0) {
            int i4 = i3 - (-F2(-i3, vVar, zVar));
            if (!z3 || i4 <= 0) {
                return;
            }
            this.f1586u.r(i4);
        }
    }

    public final void i2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z3) {
        int iM;
        int iO2 = o2(Integer.MAX_VALUE);
        if (iO2 != Integer.MAX_VALUE && (iM = iO2 - this.f1586u.m()) > 0) {
            int iF2 = iM - F2(iM, vVar, zVar);
            if (!z3 || iF2 <= 0) {
                return;
            }
            this.f1586u.r(-iF2);
        }
    }

    public int j2() {
        if (J() == 0) {
            return 0;
        }
        return h0(I(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean k() {
        return this.f1588w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int k0(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f1588w == 0 ? this.f1584s : super.k0(vVar, zVar);
    }

    public int k2() {
        int iJ = J();
        if (iJ == 0) {
            return 0;
        }
        return h0(I(iJ - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l() {
        return this.f1588w == 1;
    }

    public final int l2(int i3) {
        int iL = this.f1585t[0].l(i3);
        for (int i4 = 1; i4 < this.f1584s; i4++) {
            int iL2 = this.f1585t[i4].l(i3);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean m(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    public final int m2(int i3) {
        int iP = this.f1585t[0].p(i3);
        for (int i4 = 1; i4 < this.f1584s; i4++) {
            int iP2 = this.f1585t[i4].p(i3);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final int n2(int i3) {
        int iL = this.f1585t[0].l(i3);
        for (int i4 = 1; i4 < this.f1584s; i4++) {
            int iL2 = this.f1585t[i4].l(i3);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i3, int i4, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.f1588w != 0) {
            i3 = i4;
        }
        if (J() == 0 || i3 == 0) {
            return;
        }
        y2(i3, zVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.f1584s) {
            this.O = new int[this.f1584s];
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.f1584s; i6++) {
            f fVar = this.f1590y;
            if (fVar.f5634d == -1) {
                iL = fVar.f5636f;
                iP = this.f1585t[i6].p(iL);
            } else {
                iL = this.f1585t[i6].l(fVar.f5637g);
                iP = this.f1590y.f5637g;
            }
            int i7 = iL - iP;
            if (i7 >= 0) {
                this.O[i5] = i7;
                i5++;
            }
        }
        Arrays.sort(this.O, 0, i5);
        for (int i8 = 0; i8 < i5 && this.f1590y.a(zVar); i8++) {
            cVar.a(this.f1590y.f5633c, this.O[i8]);
            f fVar2 = this.f1590y;
            fVar2.f5633c += fVar2.f5634d;
        }
    }

    public final int o2(int i3) {
        int iP = this.f1585t[0].p(i3);
        for (int i4 = 1; i4 < this.f1584s; i4++) {
            int iP2 = this.f1585t[i4].p(i3);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final d p2(f fVar) {
        int i3;
        int i4;
        int i5 = -1;
        if (x2(fVar.f5635e)) {
            i3 = this.f1584s - 1;
            i4 = -1;
        } else {
            i3 = 0;
            i5 = this.f1584s;
            i4 = 1;
        }
        d dVar = null;
        if (fVar.f5635e == 1) {
            int i6 = Integer.MAX_VALUE;
            int iM = this.f1586u.m();
            while (i3 != i5) {
                d dVar2 = this.f1585t[i3];
                int iL = dVar2.l(iM);
                if (iL < i6) {
                    dVar = dVar2;
                    i6 = iL;
                }
                i3 += i4;
            }
            return dVar;
        }
        int i7 = Integer.MIN_VALUE;
        int i8 = this.f1586u.i();
        while (i3 != i5) {
            d dVar3 = this.f1585t[i3];
            int iP = dVar3.p(i8);
            if (iP > i7) {
                dVar = dVar3;
                i7 = iP;
            }
            i3 += i4;
        }
        return dVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return U1(zVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q2(int r7, int r8, int r9) {
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
    public android.view.View r2() {
        /*
            r12 = this;
            int r0 = r12.J()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.f1584s
            r2.<init>(r3)
            int r3 = r12.f1584s
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.f1588w
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1616e
            int r9 = r9.f1622e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1616e
            boolean r9 = r12.T1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1616e
            int r9 = r9.f1622e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f1617f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.I(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            w0.j r10 = r12.f1586u
            int r10 = r10.d(r7)
            w0.j r11 = r12.f1586u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            w0.j r10 = r12.f1586u
            int r10 = r10.g(r7)
            w0.j r11 = r12.f1586u
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f1616e
            int r8 = r8.f1622e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f1616e
            int r9 = r9.f1622e
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

    public boolean t2() {
        return Z() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return V1(zVar);
    }

    public final void u2(View view, int i3, int i4, boolean z3) {
        j(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i5 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iR2 = R2(i3, i5 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i6 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iR22 = R2(i4, i6 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z3 ? H1(view, iR2, iR22, cVar) : F1(view, iR2, iR22, cVar)) {
            view.measure(iR2, iR22);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return W1(zVar);
    }

    public final void v2(View view, c cVar, boolean z3) {
        if (cVar.f1617f) {
            if (this.f1588w == 1) {
                u2(view, this.J, RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z3);
                return;
            } else {
                u2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z3);
                return;
            }
        }
        if (this.f1588w == 1) {
            u2(view, RecyclerView.o.K(this.f1589x, p0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z3);
        } else {
            u2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.K(this.f1589x, X(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i3, vVar, zVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w2(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.z r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.w2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, boolean):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i3) {
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1598b != i3) {
            savedState.f();
        }
        this.C = i3;
        this.D = Integer.MIN_VALUE;
        t1();
    }

    public final boolean x2(int i3) {
        if (this.f1588w == 0) {
            return (i3 == -1) != this.A;
        }
        return ((i3 == -1) == this.A) == t2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i3, vVar, zVar);
    }

    public void y2(int i3, RecyclerView.z zVar) {
        int i4;
        int iJ2;
        if (i3 > 0) {
            iJ2 = k2();
            i4 = 1;
        } else {
            i4 = -1;
            iJ2 = j2();
        }
        this.f1590y.f5631a = true;
        O2(iJ2, zVar);
        G2(i4);
        f fVar = this.f1590y;
        fVar.f5633c = iJ2 + fVar.f5634d;
        fVar.f5632b = Math.abs(i3);
    }

    public final void z2(View view) {
        for (int i3 = this.f1584s - 1; i3 >= 0; i3--) {
            this.f1585t[i3].u(view);
        }
    }

    public static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f1592a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<FullSpanItem> f1593b;

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1593b == null) {
                this.f1593b = new ArrayList();
            }
            int size = this.f1593b.size();
            for (int i3 = 0; i3 < size; i3++) {
                FullSpanItem fullSpanItem2 = this.f1593b.get(i3);
                if (fullSpanItem2.f1594b == fullSpanItem.f1594b) {
                    this.f1593b.remove(i3);
                }
                if (fullSpanItem2.f1594b >= fullSpanItem.f1594b) {
                    this.f1593b.add(i3, fullSpanItem);
                    return;
                }
            }
            this.f1593b.add(fullSpanItem);
        }

        public void b() {
            int[] iArr = this.f1592a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1593b = null;
        }

        public void c(int i3) {
            int[] iArr = this.f1592a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i3, 10) + 1];
                this.f1592a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[o(i3)];
                this.f1592a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1592a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i3) {
            List<FullSpanItem> list = this.f1593b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1593b.get(size).f1594b >= i3) {
                        this.f1593b.remove(size);
                    }
                }
            }
            return h(i3);
        }

        public FullSpanItem e(int i3, int i4, int i5, boolean z3) {
            List<FullSpanItem> list = this.f1593b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i6 = 0; i6 < size; i6++) {
                FullSpanItem fullSpanItem = this.f1593b.get(i6);
                int i7 = fullSpanItem.f1594b;
                if (i7 >= i4) {
                    return null;
                }
                if (i7 >= i3 && (i5 == 0 || fullSpanItem.f1595c == i5 || (z3 && fullSpanItem.f1597e))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i3) {
            List<FullSpanItem> list = this.f1593b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1593b.get(size);
                if (fullSpanItem.f1594b == i3) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int g(int i3) {
            int[] iArr = this.f1592a;
            if (iArr == null || i3 >= iArr.length) {
                return -1;
            }
            return iArr[i3];
        }

        public int h(int i3) {
            int[] iArr = this.f1592a;
            if (iArr == null || i3 >= iArr.length) {
                return -1;
            }
            int i4 = i(i3);
            if (i4 == -1) {
                int[] iArr2 = this.f1592a;
                Arrays.fill(iArr2, i3, iArr2.length, -1);
                return this.f1592a.length;
            }
            int i5 = i4 + 1;
            Arrays.fill(this.f1592a, i3, i5, -1);
            return i5;
        }

        public final int i(int i3) {
            if (this.f1593b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i3);
            if (fullSpanItemF != null) {
                this.f1593b.remove(fullSpanItemF);
            }
            int size = this.f1593b.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    i4 = -1;
                    break;
                }
                if (this.f1593b.get(i4).f1594b >= i3) {
                    break;
                }
                i4++;
            }
            if (i4 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1593b.get(i4);
            this.f1593b.remove(i4);
            return fullSpanItem.f1594b;
        }

        public void j(int i3, int i4) {
            int[] iArr = this.f1592a;
            if (iArr == null || i3 >= iArr.length) {
                return;
            }
            int i5 = i3 + i4;
            c(i5);
            int[] iArr2 = this.f1592a;
            System.arraycopy(iArr2, i3, iArr2, i5, (iArr2.length - i3) - i4);
            Arrays.fill(this.f1592a, i3, i5, -1);
            l(i3, i4);
        }

        public void k(int i3, int i4) {
            int[] iArr = this.f1592a;
            if (iArr == null || i3 >= iArr.length) {
                return;
            }
            int i5 = i3 + i4;
            c(i5);
            int[] iArr2 = this.f1592a;
            System.arraycopy(iArr2, i5, iArr2, i3, (iArr2.length - i3) - i4);
            int[] iArr3 = this.f1592a;
            Arrays.fill(iArr3, iArr3.length - i4, iArr3.length, -1);
            m(i3, i4);
        }

        public final void l(int i3, int i4) {
            List<FullSpanItem> list = this.f1593b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1593b.get(size);
                int i5 = fullSpanItem.f1594b;
                if (i5 >= i3) {
                    fullSpanItem.f1594b = i5 + i4;
                }
            }
        }

        public final void m(int i3, int i4) {
            List<FullSpanItem> list = this.f1593b;
            if (list == null) {
                return;
            }
            int i5 = i3 + i4;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1593b.get(size);
                int i6 = fullSpanItem.f1594b;
                if (i6 >= i3) {
                    if (i6 < i5) {
                        this.f1593b.remove(size);
                    } else {
                        fullSpanItem.f1594b = i6 - i4;
                    }
                }
            }
        }

        public void n(int i3, d dVar) {
            c(i3);
            this.f1592a[i3] = dVar.f1622e;
        }

        public int o(int i3) {
            int length = this.f1592a.length;
            while (length <= i3) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1594b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1595c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int[] f1596d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public boolean f1597e;

            public static class a implements Parcelable.Creator<FullSpanItem> {
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public FullSpanItem[] newArray(int i3) {
                    return new FullSpanItem[i3];
                }
            }

            public FullSpanItem(Parcel parcel) {
                this.f1594b = parcel.readInt();
                this.f1595c = parcel.readInt();
                this.f1597e = parcel.readInt() == 1;
                int i3 = parcel.readInt();
                if (i3 > 0) {
                    int[] iArr = new int[i3];
                    this.f1596d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public int f(int i3) {
                int[] iArr = this.f1596d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i3];
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1594b + ", mGapDir=" + this.f1595c + ", mHasUnwantedGapAfter=" + this.f1597e + ", mGapPerSpan=" + Arrays.toString(this.f1596d) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i3) {
                parcel.writeInt(this.f1594b);
                parcel.writeInt(this.f1595c);
                parcel.writeInt(this.f1597e ? 1 : 0);
                int[] iArr = this.f1596d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1596d);
                }
            }

            public FullSpanItem() {
            }
        }
    }
}
