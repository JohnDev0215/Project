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
import b.h.m.c0.c;
import b.r.d.f;
import b.r.d.g;
import b.r.d.j;
import b.r.d.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.y.b {
    public BitSet B;
    public boolean G;
    public boolean H;
    public SavedState I;
    public int J;
    public int[] O;
    public d[] t;
    public j u;
    public j v;
    public int w;
    public int x;
    public final f y;
    public int s = -1;
    public boolean z = false;
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
        public int f1274b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1275c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1276d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f1277e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1278f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int[] f1279g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public List<LazySpanLookup.FullSpanItem> f1280h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1281i;
        public boolean j;
        public boolean k;

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

        public void j() {
            this.f1277e = null;
            this.f1276d = 0;
            this.f1274b = -1;
            this.f1275c = -1;
        }

        public void k() {
            this.f1277e = null;
            this.f1276d = 0;
            this.f1278f = 0;
            this.f1279g = null;
            this.f1280h = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1274b);
            parcel.writeInt(this.f1275c);
            parcel.writeInt(this.f1276d);
            if (this.f1276d > 0) {
                parcel.writeIntArray(this.f1277e);
            }
            parcel.writeInt(this.f1278f);
            if (this.f1278f > 0) {
                parcel.writeIntArray(this.f1279g);
            }
            parcel.writeInt(this.f1281i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeList(this.f1280h);
        }

        public SavedState(Parcel parcel) {
            this.f1274b = parcel.readInt();
            this.f1275c = parcel.readInt();
            int i2 = parcel.readInt();
            this.f1276d = i2;
            if (i2 > 0) {
                int[] iArr = new int[i2];
                this.f1277e = iArr;
                parcel.readIntArray(iArr);
            }
            int i3 = parcel.readInt();
            this.f1278f = i3;
            if (i3 > 0) {
                int[] iArr2 = new int[i3];
                this.f1279g = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f1281i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.k = parcel.readInt() == 1;
            this.f1280h = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f1276d = savedState.f1276d;
            this.f1274b = savedState.f1274b;
            this.f1275c = savedState.f1275c;
            this.f1277e = savedState.f1277e;
            this.f1278f = savedState.f1278f;
            this.f1279g = savedState.f1279g;
            this.f1281i = savedState.f1281i;
            this.j = savedState.j;
            this.k = savedState.k;
            this.f1280h = savedState.f1280h;
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
        public int f1283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1284b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1285c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1286d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1287e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f1288f;

        public b() {
            c();
        }

        public void a() {
            this.f1284b = this.f1285c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        public void b(int i2) {
            if (this.f1285c) {
                this.f1284b = StaggeredGridLayoutManager.this.u.i() - i2;
            } else {
                this.f1284b = StaggeredGridLayoutManager.this.u.m() + i2;
            }
        }

        public void c() {
            this.f1283a = -1;
            this.f1284b = Integer.MIN_VALUE;
            this.f1285c = false;
            this.f1286d = false;
            this.f1287e = false;
            int[] iArr = this.f1288f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f1288f;
            if (iArr == null || iArr.length < length) {
                this.f1288f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.f1288f[i2] = dVarArr[i2].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d f1290e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1291f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f1290e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f1296e;
        }

        public boolean f() {
            return this.f1291f;
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

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList<View> f1292a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1293b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1294c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1295d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1296e;

        public d(int i2) {
            this.f1296e = i2;
        }

        public void a(View view) {
            c cVarN = n(view);
            cVarN.f1290e = this;
            this.f1292a.add(view);
            this.f1294c = Integer.MIN_VALUE;
            if (this.f1292a.size() == 1) {
                this.f1293b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1295d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        public void b(boolean z, int i2) {
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
                    this.f1294c = iL;
                    this.f1293b = iL;
                }
            }
        }

        public void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1292a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1294c = StaggeredGridLayoutManager.this.u.d(view);
            if (cVarN.f1291f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1271c == 1) {
                this.f1294c += fullSpanItemF.j(this.f1296e);
            }
        }

        public void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1292a.get(0);
            c cVarN = n(view);
            this.f1293b = StaggeredGridLayoutManager.this.u.g(view);
            if (cVarN.f1291f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f1271c == -1) {
                this.f1293b -= fullSpanItemF.j(this.f1296e);
            }
        }

        public void e() {
            this.f1292a.clear();
            q();
            this.f1295d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.z ? i(this.f1292a.size() - 1, -1, true) : i(0, this.f1292a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.z ? i(0, this.f1292a.size(), true) : i(this.f1292a.size() - 1, -1, true);
        }

        public int h(int i2, int i3, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.u.m();
            int i4 = StaggeredGridLayoutManager.this.u.i();
            int i5 = i3 > i2 ? 1 : -1;
            while (i2 != i3) {
                View view = this.f1292a.get(i2);
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

        public int i(int i2, int i3, boolean z) {
            return h(i2, i3, false, false, z);
        }

        public int j() {
            return this.f1295d;
        }

        public int k() {
            int i2 = this.f1294c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f1294c;
        }

        public int l(int i2) {
            int i3 = this.f1294c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f1292a.size() == 0) {
                return i2;
            }
            c();
            return this.f1294c;
        }

        public View m(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.f1292a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1292a.get(size);
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
                int size2 = this.f1292a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = this.f1292a.get(i4);
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

        public c n(View view) {
            return (c) view.getLayoutParams();
        }

        public int o() {
            int i2 = this.f1293b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.f1293b;
        }

        public int p(int i2) {
            int i3 = this.f1293b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f1292a.size() == 0) {
                return i2;
            }
            d();
            return this.f1293b;
        }

        public void q() {
            this.f1293b = Integer.MIN_VALUE;
            this.f1294c = Integer.MIN_VALUE;
        }

        public void r(int i2) {
            int i3 = this.f1293b;
            if (i3 != Integer.MIN_VALUE) {
                this.f1293b = i3 + i2;
            }
            int i4 = this.f1294c;
            if (i4 != Integer.MIN_VALUE) {
                this.f1294c = i4 + i2;
            }
        }

        public void s() {
            int size = this.f1292a.size();
            View viewRemove = this.f1292a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f1290e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f1295d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            if (size == 1) {
                this.f1293b = Integer.MIN_VALUE;
            }
            this.f1294c = Integer.MIN_VALUE;
        }

        public void t() {
            View viewRemove = this.f1292a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f1290e = null;
            if (this.f1292a.size() == 0) {
                this.f1294c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1295d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            this.f1293b = Integer.MIN_VALUE;
        }

        public void u(View view) {
            c cVarN = n(view);
            cVarN.f1290e = this;
            this.f1292a.add(0, view);
            this.f1293b = Integer.MIN_VALUE;
            if (this.f1292a.size() == 1) {
                this.f1294c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1295d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        public void v(int i2) {
            this.f1293b = i2;
            this.f1294c = i2;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i2, i3);
        I2(dVarI0.f1220a);
        K2(dVarI0.f1221b);
        J2(dVarI0.f1222c);
        this.y = new f();
        a2();
    }

    public final void A2(View view) {
        for (int i2 = this.s - 1; i2 >= 0; i2--) {
            this.t[i2].u(view);
        }
    }

    public final void B2(RecyclerView.v vVar, f fVar) {
        if (!fVar.f3102a || fVar.f3110i) {
            return;
        }
        if (fVar.f3103b == 0) {
            if (fVar.f3106e == -1) {
                C2(vVar, fVar.f3108g);
                return;
            } else {
                D2(vVar, fVar.f3107f);
                return;
            }
        }
        if (fVar.f3106e != -1) {
            int iN2 = n2(fVar.f3108g) - fVar.f3108g;
            D2(vVar, iN2 < 0 ? fVar.f3107f : Math.min(iN2, fVar.f3103b) + fVar.f3107f);
        } else {
            int i2 = fVar.f3107f;
            int iM2 = i2 - m2(i2);
            C2(vVar, iM2 < 0 ? fVar.f3108g : fVar.f3108g - Math.min(iM2, fVar.f3103b));
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

    public final void C2(RecyclerView.v vVar, int i2) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            if (this.u.g(viewI) < i2 || this.u.q(viewI) < i2) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f1291f) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    if (this.t[i3].f1292a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.s; i4++) {
                    this.t[i4].s();
                }
            } else if (cVar.f1290e.f1292a.size() == 1) {
                return;
            } else {
                cVar.f1290e.s();
            }
            m1(viewI, vVar);
        }
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

    public final void D2(RecyclerView.v vVar, int i2) {
        while (J() > 0) {
            View viewI = I(0);
            if (this.u.d(viewI) > i2 || this.u.p(viewI) > i2) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f1291f) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    if (this.t[i3].f1292a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.s; i4++) {
                    this.t[i4].t();
                }
            } else if (cVar.f1290e.f1292a.size() == 1) {
                return;
            } else {
                cVar.f1290e.t();
            }
            m1(viewI, vVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    public final void E2() {
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
        Q2(iRound);
        if (this.x == i3) {
            return;
        }
        for (int i4 = 0; i4 < iJ; i4++) {
            View viewI2 = I(i4);
            c cVar = (c) viewI2.getLayoutParams();
            if (!cVar.f1291f) {
                if (u2() && this.w == 1) {
                    int i5 = this.s;
                    int i6 = cVar.f1290e.f1296e;
                    viewI2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.x) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = cVar.f1290e.f1296e;
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p F(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public final void F2() {
        if (this.w == 1 || !u2()) {
            this.A = this.z;
        } else {
            this.A = !this.z;
        }
    }

    public int G2(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i2 == 0) {
            return 0;
        }
        z2(i2, zVar);
        int iB2 = b2(vVar, this.y, zVar);
        if (this.y.f3103b >= iB2) {
            i2 = i2 < 0 ? -iB2 : iB2;
        }
        this.u.r(-i2);
        this.G = this.A;
        f fVar = this.y;
        fVar.f3103b = 0;
        B2(vVar, fVar);
        return i2;
    }

    public final void H2(int i2) {
        f fVar = this.y;
        fVar.f3106e = i2;
        fVar.f3105d = this.A != (i2 == -1) ? -1 : 1;
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

    public void I2(int i2) {
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
    public View J0(View view, int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewB;
        View viewM;
        if (J() == 0 || (viewB = B(view)) == null) {
            return null;
        }
        F2();
        int iX1 = X1(i2);
        if (iX1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewB.getLayoutParams();
        boolean z = cVar.f1291f;
        d dVar = cVar.f1290e;
        int iK2 = iX1 == 1 ? k2() : j2();
        P2(iK2, zVar);
        H2(iX1);
        f fVar = this.y;
        fVar.f3104c = fVar.f3105d + iK2;
        fVar.f3103b = (int) (this.u.n() * 0.33333334f);
        f fVar2 = this.y;
        fVar2.f3109h = true;
        fVar2.f3102a = false;
        b2(vVar, fVar2, zVar);
        this.G = this.A;
        if (!z && (viewM = dVar.m(iK2, iX1)) != null && viewM != viewB) {
            return viewM;
        }
        if (y2(iX1)) {
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
        if (y2(iX1)) {
            for (int i5 = this.s - 1; i5 >= 0; i5--) {
                if (i5 != dVar.f1296e) {
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

    public void J2(boolean z) {
        g(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1281i != z) {
            savedState.f1281i = z;
        }
        this.z = z;
        t1();
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

    public void K2(int i2) {
        g(null);
        if (i2 != this.s) {
            t2();
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
    public boolean L1() {
        return this.I == null;
    }

    public final void L2(int i2, int i3) {
        for (int i4 = 0; i4 < this.s; i4++) {
            if (!this.t[i4].f1292a.isEmpty()) {
                R2(this.t[i4], i2, i3);
            }
        }
    }

    public final void M1(View view) {
        for (int i2 = this.s - 1; i2 >= 0; i2--) {
            this.t[i2].a(view);
        }
    }

    public final boolean M2(RecyclerView.z zVar, b bVar) {
        bVar.f1283a = this.G ? g2(zVar.b()) : c2(zVar.b());
        bVar.f1284b = Integer.MIN_VALUE;
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.w == 1 ? this.s : super.N(vVar, zVar);
    }

    public final void N1(b bVar) {
        SavedState savedState = this.I;
        int i2 = savedState.f1276d;
        if (i2 > 0) {
            if (i2 == this.s) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].e();
                    SavedState savedState2 = this.I;
                    int i4 = savedState2.f1277e[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += savedState2.j ? this.u.i() : this.u.m();
                    }
                    this.t[i3].v(i4);
                }
            } else {
                savedState.k();
                SavedState savedState3 = this.I;
                savedState3.f1274b = savedState3.f1275c;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.k;
        J2(savedState4.f1281i);
        F2();
        SavedState savedState5 = this.I;
        int i5 = savedState5.f1274b;
        if (i5 != -1) {
            this.C = i5;
            bVar.f1285c = savedState5.j;
        } else {
            bVar.f1285c = this.A;
        }
        if (savedState5.f1278f > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f1268a = savedState5.f1279g;
            lazySpanLookup.f1269b = savedState5.f1280h;
        }
    }

    public boolean N2(RecyclerView.z zVar, b bVar) {
        int i2;
        if (!zVar.e() && (i2 = this.C) != -1) {
            if (i2 >= 0 && i2 < zVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f1274b == -1 || savedState.f1276d < 1) {
                    View viewC = C(this.C);
                    if (viewC != null) {
                        bVar.f1283a = this.A ? k2() : j2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1285c) {
                                bVar.f1284b = (this.u.i() - this.D) - this.u.d(viewC);
                            } else {
                                bVar.f1284b = (this.u.m() + this.D) - this.u.g(viewC);
                            }
                            return true;
                        }
                        if (this.u.e(viewC) > this.u.n()) {
                            bVar.f1284b = bVar.f1285c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int iG = this.u.g(viewC) - this.u.m();
                        if (iG < 0) {
                            bVar.f1284b = -iG;
                            return true;
                        }
                        int i3 = this.u.i() - this.u.d(viewC);
                        if (i3 < 0) {
                            bVar.f1284b = i3;
                            return true;
                        }
                        bVar.f1284b = Integer.MIN_VALUE;
                    } else {
                        int i4 = this.C;
                        bVar.f1283a = i4;
                        int i5 = this.D;
                        if (i5 == Integer.MIN_VALUE) {
                            bVar.f1285c = R1(i4) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i5);
                        }
                        bVar.f1286d = true;
                    }
                } else {
                    bVar.f1284b = Integer.MIN_VALUE;
                    bVar.f1283a = this.C;
                }
                return true;
            }
            this.C = -1;
            this.D = Integer.MIN_VALUE;
        }
        return false;
    }

    public boolean O1() {
        int iL = this.t[0].l(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.s; i2++) {
            if (this.t[i2].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    public void O2(RecyclerView.z zVar, b bVar) {
        if (N2(zVar, bVar) || M2(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1283a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView.v vVar, RecyclerView.z zVar, View view, b.h.m.c0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.O0(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.w == 0) {
            cVar.f0(c.C0044c.a(cVar2.e(), cVar2.f1291f ? this.s : 1, -1, -1, false, false));
        } else {
            cVar.f0(c.C0044c.a(-1, -1, cVar2.e(), cVar2.f1291f ? this.s : 1, false, false));
        }
    }

    public boolean P1() {
        int iP = this.t[0].p(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.s; i2++) {
            if (this.t[i2].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    public final void P2(int i2, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        f fVar = this.y;
        boolean z = false;
        fVar.f3103b = 0;
        fVar.f3104c = i2;
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
            this.y.f3107f = this.u.m() - iN2;
            this.y.f3108g = this.u.i() + iN;
        } else {
            this.y.f3108g = this.u.h() + iN;
            this.y.f3107f = -iN2;
        }
        f fVar2 = this.y;
        fVar2.f3109h = false;
        fVar2.f3102a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        fVar2.f3110i = z;
    }

    public final void Q1(View view, c cVar, f fVar) {
        if (fVar.f3106e == 1) {
            if (cVar.f1291f) {
                M1(view);
                return;
            } else {
                cVar.f1290e.a(view);
                return;
            }
        }
        if (cVar.f1291f) {
            A2(view);
        } else {
            cVar.f1290e.u(view);
        }
    }

    public void Q2(int i2) {
        this.x = i2 / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i2, this.v.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i2, int i3) {
        r2(i2, i3, 1);
    }

    public final int R1(int i2) {
        if (J() == 0) {
            return this.A ? 1 : -1;
        }
        return (i2 < j2()) != this.A ? -1 : 1;
    }

    public final void R2(d dVar, int i2, int i3) {
        int iJ = dVar.j();
        if (i2 == -1) {
            if (dVar.o() + iJ <= i3) {
                this.B.set(dVar.f1296e, false);
            }
        } else if (dVar.k() - iJ >= i3) {
            this.B.set(dVar.f1296e, false);
        }
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
        if (iJ2 == 0 && s2() != null) {
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
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iJ2, fullSpanItemE.f1270b, i2 * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f1270b);
        } else {
            this.E.d(fullSpanItemE2.f1270b + 1);
        }
        u1();
        t1();
        return true;
    }

    public final int S2(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i2, int i3, int i4) {
        r2(i2, i3, 8);
    }

    public final boolean T1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.u.i()) {
                ArrayList<View> arrayList = dVar.f1292a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f1291f;
            }
        } else if (dVar.o() > this.u.m()) {
            return !dVar.n(dVar.f1292a.get(0)).f1291f;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i2, int i3) {
        r2(i2, i3, 2);
    }

    public final int U1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.a(zVar, this.u, e2(!this.N), d2(!this.N), this, this.N);
    }

    public final int V1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.b(zVar, this.u, e2(!this.N), d2(!this.N), this, this.N, this.A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        r2(i2, i3, 4);
    }

    public final int W1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return m.c(zVar, this.u, e2(!this.N), d2(!this.N), this, this.N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        x2(vVar, zVar, true);
    }

    public final int X1(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && u2()) ? -1 : 1 : (this.w != 1 && u2()) ? 1 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    public final LazySpanLookup.FullSpanItem Y1(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1272d = new int[this.s];
        for (int i3 = 0; i3 < this.s; i3++) {
            fullSpanItem.f1272d[i3] = i2 - this.t[i3].l(i2);
        }
        return fullSpanItem;
    }

    public final LazySpanLookup.FullSpanItem Z1(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1272d = new int[this.s];
        for (int i3 = 0; i3 < this.s; i3++) {
            fullSpanItem.f1272d[i3] = this.t[i3].p(i2) - i2;
        }
        return fullSpanItem;
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

    public final void a2() {
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
    public final int b2(RecyclerView.v vVar, f fVar, RecyclerView.z zVar) {
        d dVarP2;
        int iE;
        int i2;
        int iE2;
        int iE3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        int i3 = this.y.f3110i ? fVar.f3106e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : fVar.f3106e == 1 ? fVar.f3108g + fVar.f3103b : fVar.f3107f - fVar.f3103b;
        L2(fVar.f3106e, i3);
        int i4 = this.A ? this.u.i() : this.u.m();
        ?? r0 = false;
        while (fVar.a(zVar) && (this.y.f3110i || !this.B.isEmpty())) {
            View viewB = fVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarP2 = cVar.f1291f ? this.t[r9] : p2(fVar);
                this.E.n(iA, dVarP2);
            } else {
                dVarP2 = this.t[iG];
            }
            d dVar = dVarP2;
            cVar.f1290e = dVar;
            if (fVar.f3106e == 1) {
                d(viewB);
            } else {
                e(viewB, r9);
            }
            w2(viewB, cVar, r9);
            if (fVar.f3106e == 1) {
                int iL2 = cVar.f1291f ? l2(i4) : dVar.l(i4);
                int iE4 = this.u.e(viewB) + iL2;
                if (r3 != 0 && cVar.f1291f) {
                    LazySpanLookup.FullSpanItem fullSpanItemY1 = Y1(iL2);
                    fullSpanItemY1.f1271c = -1;
                    fullSpanItemY1.f1270b = iA;
                    this.E.a(fullSpanItemY1);
                }
                i2 = iE4;
                iE = iL2;
            } else {
                int iO2 = cVar.f1291f ? o2(i4) : dVar.p(i4);
                iE = iO2 - this.u.e(viewB);
                if (r3 != 0 && cVar.f1291f) {
                    LazySpanLookup.FullSpanItem fullSpanItemZ1 = Z1(iO2);
                    fullSpanItemZ1.f1271c = 1;
                    fullSpanItemZ1.f1270b = iA;
                    this.E.a(fullSpanItemZ1);
                }
                i2 = iO2;
            }
            if (cVar.f1291f && fVar.f3105d == -1) {
                if (r3 != 0) {
                    this.M = true;
                } else {
                    if (!(fVar.f3106e == 1 ? O1() : P1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f1273e = true;
                        }
                        this.M = true;
                    }
                }
            }
            Q1(viewB, cVar, fVar);
            if (u2() && this.w == 1) {
                int i5 = cVar.f1291f ? this.v.i() : this.v.i() - (((this.s - 1) - dVar.f1296e) * this.x);
                iE3 = i5;
                iE2 = i5 - this.v.e(viewB);
            } else {
                int iM = cVar.f1291f ? this.v.m() : (dVar.f1296e * this.x) + this.v.m();
                iE2 = iM;
                iE3 = this.v.e(viewB) + iM;
            }
            if (this.w == 1) {
                z0(viewB, iE2, iE, iE3, i2);
            } else {
                z0(viewB, iE, iE2, i2, iE3);
            }
            if (cVar.f1291f) {
                L2(this.y.f3106e, i3);
            } else {
                R2(dVar, this.y.f3106e, i3);
            }
            B2(vVar, this.y);
            if (!this.y.f3109h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.f1291f) {
                this.B.clear();
                z = false;
            } else {
                z = false;
                this.B.set(dVar.f1296e, false);
            }
            r9 = z;
            r0 = true;
        }
        ?? r32 = r9;
        if (r0 == false) {
            B2(vVar, this.y);
        }
        int iM2 = this.y.f3106e == -1 ? this.u.m() - o2(this.u.m()) : l2(this.u.i()) - this.u.i();
        return iM2 > 0 ? Math.min(fVar.f3103b, iM2) : r32 == true ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.I = (SavedState) parcelable;
            t1();
        }
    }

    public final int c2(int i2) {
        int iJ = J();
        for (int i3 = 0; i3 < iJ; i3++) {
            int iH0 = h0(I(i3));
            if (iH0 >= 0 && iH0 < i2) {
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
        savedState.f1281i = this.z;
        savedState.j = this.G;
        savedState.k = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1268a) == null) {
            savedState.f1278f = 0;
        } else {
            savedState.f1279g = iArr;
            savedState.f1278f = iArr.length;
            savedState.f1280h = lazySpanLookup.f1269b;
        }
        if (J() > 0) {
            savedState.f1274b = this.G ? k2() : j2();
            savedState.f1275c = f2();
            int i2 = this.s;
            savedState.f1276d = i2;
            savedState.f1277e = new int[i2];
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
                savedState.f1277e[i3] = iP;
            }
        } else {
            savedState.f1274b = -1;
            savedState.f1275c = -1;
            savedState.f1276d = 0;
        }
        return savedState;
    }

    public View d2(boolean z) {
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

    public View e2(boolean z) {
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

    public final int g2(int i2) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            int iH0 = h0(I(iJ));
            if (iH0 >= 0 && iH0 < i2) {
                return iH0;
            }
        }
        return 0;
    }

    public final void h2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i2;
        int iL2 = l2(Integer.MIN_VALUE);
        if (iL2 != Integer.MIN_VALUE && (i2 = this.u.i() - iL2) > 0) {
            int i3 = i2 - (-G2(-i2, vVar, zVar));
            if (!z || i3 <= 0) {
                return;
            }
            this.u.r(i3);
        }
    }

    public final void i2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iO2 = o2(Integer.MAX_VALUE);
        if (iO2 != Integer.MAX_VALUE && (iM = iO2 - this.u.m()) > 0) {
            int iG2 = iM - G2(iM, vVar, zVar);
            if (!z || iG2 <= 0) {
                return;
            }
            this.u.r(-iG2);
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
        return this.w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int k0(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.w == 0 ? this.s : super.k0(vVar, zVar);
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
        return this.w == 1;
    }

    public final int l2(int i2) {
        int iL = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iL2 = this.t[i3].l(i2);
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

    public final int m2(int i2) {
        int iP = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iP2 = this.t[i3].p(i2);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final int n2(int i2) {
        int iL = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iL2 = this.t[i3].l(i2);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
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
        z2(i2, zVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.s; i5++) {
            f fVar = this.y;
            if (fVar.f3105d == -1) {
                iL = fVar.f3107f;
                iP = this.t[i5].p(iL);
            } else {
                iL = this.t[i5].l(fVar.f3108g);
                iP = this.y.f3108g;
            }
            int i6 = iL - iP;
            if (i6 >= 0) {
                this.O[i4] = i6;
                i4++;
            }
        }
        Arrays.sort(this.O, 0, i4);
        for (int i7 = 0; i7 < i4 && this.y.a(zVar); i7++) {
            cVar.a(this.y.f3104c, this.O[i7]);
            f fVar2 = this.y;
            fVar2.f3104c += fVar2.f3105d;
        }
    }

    public final int o2(int i2) {
        int iP = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iP2 = this.t[i3].p(i2);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final d p2(f fVar) {
        int i2;
        int i3;
        int i4 = -1;
        if (y2(fVar.f3106e)) {
            i2 = this.s - 1;
            i3 = -1;
        } else {
            i2 = 0;
            i4 = this.s;
            i3 = 1;
        }
        d dVar = null;
        if (fVar.f3106e == 1) {
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return U1(zVar);
    }

    public int q2() {
        return this.w;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return V1(zVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r2(int r7, int r8, int r9) {
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.r2(int, int, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int s(RecyclerView.z zVar) {
        return W1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean s0() {
        return this.F != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View s2() {
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
            boolean r3 = r12.u2()
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1290e
            int r9 = r9.f1296e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1290e
            boolean r9 = r12.T1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1290e
            int r9 = r9.f1296e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f1291f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.I(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            b.r.d.j r10 = r12.u
            int r10 = r10.d(r7)
            b.r.d.j r11 = r12.u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            b.r.d.j r10 = r12.u
            int r10 = r10.g(r7)
            b.r.d.j r11 = r12.u
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f1290e
            int r8 = r8.f1296e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f1290e
            int r9 = r9.f1296e
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.s2():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return U1(zVar);
    }

    public void t2() {
        this.E.b();
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return V1(zVar);
    }

    public boolean u2() {
        return Z() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return W1(zVar);
    }

    public final void v2(View view, int i2, int i3, boolean z) {
        j(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iS2 = S2(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iS22 = S2(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? H1(view, iS2, iS22, cVar) : F1(view, iS2, iS22, cVar)) {
            view.measure(iS2, iS22);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        return G2(i2, vVar, zVar);
    }

    public final void w2(View view, c cVar, boolean z) {
        if (cVar.f1291f) {
            if (this.w == 1) {
                v2(view, this.J, RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
                return;
            } else {
                v2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z);
                return;
            }
        }
        if (this.w == 1) {
            v2(view, RecyclerView.o.K(this.x, p0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            v2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.K(this.x, X(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i2) {
        SavedState savedState = this.I;
        if (savedState != null && savedState.f1274b != i2) {
            savedState.j();
        }
        this.C = i2;
        this.D = Integer.MIN_VALUE;
        t1();
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void x2(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.z r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.x2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, boolean):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        return G2(i2, vVar, zVar);
    }

    public final boolean y2(int i2) {
        if (this.w == 0) {
            return (i2 == -1) != this.A;
        }
        return ((i2 == -1) == this.A) == u2();
    }

    public void z2(int i2, RecyclerView.z zVar) {
        int i3;
        int iJ2;
        if (i2 > 0) {
            iJ2 = k2();
            i3 = 1;
        } else {
            i3 = -1;
            iJ2 = j2();
        }
        this.y.f3102a = true;
        P2(iJ2, zVar);
        H2(i3);
        f fVar = this.y;
        fVar.f3104c = iJ2 + fVar.f3105d;
        fVar.f3103b = Math.abs(i2);
    }

    public static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f1268a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<FullSpanItem> f1269b;

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1269b == null) {
                this.f1269b = new ArrayList();
            }
            int size = this.f1269b.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = this.f1269b.get(i2);
                if (fullSpanItem2.f1270b == fullSpanItem.f1270b) {
                    this.f1269b.remove(i2);
                }
                if (fullSpanItem2.f1270b >= fullSpanItem.f1270b) {
                    this.f1269b.add(i2, fullSpanItem);
                    return;
                }
            }
            this.f1269b.add(fullSpanItem);
        }

        public void b() {
            int[] iArr = this.f1268a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1269b = null;
        }

        public void c(int i2) {
            int[] iArr = this.f1268a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.f1268a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[o(i2)];
                this.f1268a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1268a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i2) {
            List<FullSpanItem> list = this.f1269b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1269b.get(size).f1270b >= i2) {
                        this.f1269b.remove(size);
                    }
                }
            }
            return h(i2);
        }

        public FullSpanItem e(int i2, int i3, int i4, boolean z) {
            List<FullSpanItem> list = this.f1269b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = this.f1269b.get(i5);
                int i6 = fullSpanItem.f1270b;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || fullSpanItem.f1271c == i4 || (z && fullSpanItem.f1273e))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i2) {
            List<FullSpanItem> list = this.f1269b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1269b.get(size);
                if (fullSpanItem.f1270b == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int g(int i2) {
            int[] iArr = this.f1268a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        public int h(int i2) {
            int[] iArr = this.f1268a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int i3 = i(i2);
            if (i3 == -1) {
                int[] iArr2 = this.f1268a;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.f1268a.length;
            }
            int i4 = i3 + 1;
            Arrays.fill(this.f1268a, i2, i4, -1);
            return i4;
        }

        public final int i(int i2) {
            if (this.f1269b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i2);
            if (fullSpanItemF != null) {
                this.f1269b.remove(fullSpanItemF);
            }
            int size = this.f1269b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (this.f1269b.get(i3).f1270b >= i2) {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1269b.get(i3);
            this.f1269b.remove(i3);
            return fullSpanItem.f1270b;
        }

        public void j(int i2, int i3) {
            int[] iArr = this.f1268a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f1268a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.f1268a, i2, i4, -1);
            l(i2, i3);
        }

        public void k(int i2, int i3) {
            int[] iArr = this.f1268a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f1268a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.f1268a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            m(i2, i3);
        }

        public final void l(int i2, int i3) {
            List<FullSpanItem> list = this.f1269b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1269b.get(size);
                int i4 = fullSpanItem.f1270b;
                if (i4 >= i2) {
                    fullSpanItem.f1270b = i4 + i3;
                }
            }
        }

        public final void m(int i2, int i3) {
            List<FullSpanItem> list = this.f1269b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1269b.get(size);
                int i5 = fullSpanItem.f1270b;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.f1269b.remove(size);
                    } else {
                        fullSpanItem.f1270b = i5 - i3;
                    }
                }
            }
        }

        public void n(int i2, d dVar) {
            c(i2);
            this.f1268a[i2] = dVar.f1296e;
        }

        public int o(int i2) {
            int length = this.f1268a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1270b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1271c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int[] f1272d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public boolean f1273e;

            public static class a implements Parcelable.Creator<FullSpanItem> {
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

            public FullSpanItem(Parcel parcel) {
                this.f1270b = parcel.readInt();
                this.f1271c = parcel.readInt();
                this.f1273e = parcel.readInt() == 1;
                int i2 = parcel.readInt();
                if (i2 > 0) {
                    int[] iArr = new int[i2];
                    this.f1272d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public int j(int i2) {
                int[] iArr = this.f1272d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i2];
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1270b + ", mGapDir=" + this.f1271c + ", mHasUnwantedGapAfter=" + this.f1273e + ", mGapPerSpan=" + Arrays.toString(this.f1272d) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f1270b);
                parcel.writeInt(this.f1271c);
                parcel.writeInt(this.f1273e ? 1 : 0);
                int[] iArr = this.f1272d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1272d);
                }
            }

            public FullSpanItem() {
            }
        }
    }
}
