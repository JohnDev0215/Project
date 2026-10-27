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
    private BitSet C;
    private boolean H;
    private boolean I;
    private SavedState J;
    private int K;
    private int[] P;
    d[] u;
    k v;
    k w;
    private int x;
    private int y;
    private final g z;
    private int t = -1;
    boolean A = false;
    boolean B = false;
    int D = -1;
    int E = Integer.MIN_VALUE;
    LazySpanLookup F = new LazySpanLookup();
    private int G = 2;
    private final Rect L = new Rect();
    private final b M = new b();
    private boolean N = false;
    private boolean O = true;
    private final Runnable Q = new a();

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1366b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1367c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1368d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int[] f1369e;
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

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        void u() {
            this.f1369e = null;
            this.f1368d = 0;
            this.f1366b = -1;
            this.f1367c = -1;
        }

        void v() {
            this.f1369e = null;
            this.f1368d = 0;
            this.f = 0;
            this.g = null;
            this.h = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1366b);
            parcel.writeInt(this.f1367c);
            parcel.writeInt(this.f1368d);
            if (this.f1368d > 0) {
                parcel.writeIntArray(this.f1369e);
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
            this.f1366b = parcel.readInt();
            this.f1367c = parcel.readInt();
            int i = parcel.readInt();
            this.f1368d = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.f1369e = iArr;
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
            this.f1368d = savedState.f1368d;
            this.f1366b = savedState.f1366b;
            this.f1367c = savedState.f1367c;
            this.f1369e = savedState.f1369e;
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
            StaggeredGridLayoutManager.this.c2();
        }
    }

    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1371a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1373c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1374d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1375e;
        int[] f;

        b() {
            c();
        }

        void a() {
            this.f1372b = this.f1373c ? StaggeredGridLayoutManager.this.v.i() : StaggeredGridLayoutManager.this.v.m();
        }

        void b(int i) {
            if (this.f1373c) {
                this.f1372b = StaggeredGridLayoutManager.this.v.i() - i;
            } else {
                this.f1372b = StaggeredGridLayoutManager.this.v.m() + i;
            }
        }

        void c() {
            this.f1371a = -1;
            this.f1372b = Integer.MIN_VALUE;
            this.f1373c = false;
            this.f1374d = false;
            this.f1375e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.u.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = dVarArr[i].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {
        d f;
        boolean g;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int y() {
            d dVar = this.f;
            if (dVar == null) {
                return -1;
            }
            return dVar.f1380e;
        }

        public boolean z() {
            return this.g;
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
        ArrayList<View> f1376a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1377b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1378c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1379d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f1380e;

        d(int i) {
            this.f1380e = i;
        }

        void a(View view) {
            c cVarN = n(view);
            cVarN.f = this;
            this.f1376a.add(view);
            this.f1378c = Integer.MIN_VALUE;
            if (this.f1376a.size() == 1) {
                this.f1377b = Integer.MIN_VALUE;
            }
            if (cVarN.w() || cVarN.v()) {
                this.f1379d += StaggeredGridLayoutManager.this.v.e(view);
            }
        }

        void b(boolean z, int i) {
            int iL = z ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (iL == Integer.MIN_VALUE) {
                return;
            }
            if (!z || iL >= StaggeredGridLayoutManager.this.v.i()) {
                if (z || iL <= StaggeredGridLayoutManager.this.v.m()) {
                    if (i != Integer.MIN_VALUE) {
                        iL += i;
                    }
                    this.f1378c = iL;
                    this.f1377b = iL;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1376a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1378c = StaggeredGridLayoutManager.this.v.d(view);
            if (cVarN.g && (fullSpanItemF = StaggeredGridLayoutManager.this.F.f(cVarN.u())) != null && fullSpanItemF.f1363c == 1) {
                this.f1378c += fullSpanItemF.u(this.f1380e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1376a.get(0);
            c cVarN = n(view);
            this.f1377b = StaggeredGridLayoutManager.this.v.g(view);
            if (cVarN.g && (fullSpanItemF = StaggeredGridLayoutManager.this.F.f(cVarN.u())) != null && fullSpanItemF.f1363c == -1) {
                this.f1377b -= fullSpanItemF.u(this.f1380e);
            }
        }

        void e() {
            this.f1376a.clear();
            q();
            this.f1379d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.A ? i(this.f1376a.size() - 1, -1, true) : i(0, this.f1376a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.A ? i(0, this.f1376a.size(), true) : i(this.f1376a.size() - 1, -1, true);
        }

        int h(int i, int i2, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.v.m();
            int i3 = StaggeredGridLayoutManager.this.v.i();
            int i4 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1376a.get(i);
                int iG = StaggeredGridLayoutManager.this.v.g(view);
                int iD = StaggeredGridLayoutManager.this.v.d(view);
                boolean z4 = false;
                boolean z5 = !z3 ? iG >= i3 : iG > i3;
                if (!z3 ? iD > iM : iD >= iM) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (iG >= iM && iD <= i3) {
                            return StaggeredGridLayoutManager.this.n0(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.n0(view);
                        }
                        if (iG < iM || iD > i3) {
                            return StaggeredGridLayoutManager.this.n0(view);
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
            return this.f1379d;
        }

        int k() {
            int i = this.f1378c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.f1378c;
        }

        int l(int i) {
            int i2 = this.f1378c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1376a.size() == 0) {
                return i;
            }
            c();
            return this.f1378c;
        }

        public View m(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.f1376a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1376a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.A && staggeredGridLayoutManager.n0(view2) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.A && staggeredGridLayoutManager2.n0(view2) <= i) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1376a.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.f1376a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.A && staggeredGridLayoutManager3.n0(view3) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.A && staggeredGridLayoutManager4.n0(view3) >= i) || !view3.hasFocusable()) {
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
            int i = this.f1377b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            d();
            return this.f1377b;
        }

        int p(int i) {
            int i2 = this.f1377b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1376a.size() == 0) {
                return i;
            }
            d();
            return this.f1377b;
        }

        void q() {
            this.f1377b = Integer.MIN_VALUE;
            this.f1378c = Integer.MIN_VALUE;
        }

        void r(int i) {
            int i2 = this.f1377b;
            if (i2 != Integer.MIN_VALUE) {
                this.f1377b = i2 + i;
            }
            int i3 = this.f1378c;
            if (i3 != Integer.MIN_VALUE) {
                this.f1378c = i3 + i;
            }
        }

        void s() {
            int size = this.f1376a.size();
            View viewRemove = this.f1376a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f = null;
            if (cVarN.w() || cVarN.v()) {
                this.f1379d -= StaggeredGridLayoutManager.this.v.e(viewRemove);
            }
            if (size == 1) {
                this.f1377b = Integer.MIN_VALUE;
            }
            this.f1378c = Integer.MIN_VALUE;
        }

        void t() {
            View viewRemove = this.f1376a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f = null;
            if (this.f1376a.size() == 0) {
                this.f1378c = Integer.MIN_VALUE;
            }
            if (cVarN.w() || cVarN.v()) {
                this.f1379d -= StaggeredGridLayoutManager.this.v.e(viewRemove);
            }
            this.f1377b = Integer.MIN_VALUE;
        }

        void u(View view) {
            c cVarN = n(view);
            cVarN.f = this;
            this.f1376a.add(0, view);
            this.f1377b = Integer.MIN_VALUE;
            if (this.f1376a.size() == 1) {
                this.f1378c = Integer.MIN_VALUE;
            }
            if (cVarN.w() || cVarN.v()) {
                this.f1379d += StaggeredGridLayoutManager.this.v.e(view);
            }
        }

        void v(int i) {
            this.f1377b = i;
            this.f1378c = i;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d dVarO0 = RecyclerView.o.o0(context, attributeSet, i, i2);
        S2(dVarO0.f1325a);
        U2(dVarO0.f1326b);
        T2(dVarO0.f1327c);
        this.z = new g();
        k2();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void B2(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.B
            if (r0 == 0) goto L9
            int r0 = r6.u2()
            goto Ld
        L9:
            int r0 = r6.t2()
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.F
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.F
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.F
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.F
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.F
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.B
            if (r7 == 0) goto L4d
            int r7 = r6.t2()
            goto L51
        L4d:
            int r7 = r6.u2()
        L51:
            if (r3 > r7) goto L56
            r6.C1()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.B2(int, int, int):void");
    }

    private void F2(View view, int i, int i2, boolean z) {
        t(view, this.L);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.L;
        int iC3 = c3(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.L;
        int iC32 = c3(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? R1(view, iC3, iC32, cVar) : P1(view, iC3, iC32, cVar)) {
            view.measure(iC3, iC32);
        }
    }

    private void G2(View view, c cVar, boolean z) {
        if (cVar.g) {
            if (this.x == 1) {
                F2(view, this.K, RecyclerView.o.U(g0(), h0(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
                return;
            } else {
                F2(view, RecyclerView.o.U(u0(), v0(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.K, z);
                return;
            }
        }
        if (this.x == 1) {
            F2(view, RecyclerView.o.U(this.y, v0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.U(g0(), h0(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            F2(view, RecyclerView.o.U(u0(), v0(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.U(this.y, h0(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void H2(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.z r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.H2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, boolean):void");
    }

    private boolean I2(int i) {
        if (this.x == 0) {
            return (i == -1) != this.B;
        }
        return ((i == -1) == this.B) == E2();
    }

    private void K2(View view) {
        for (int i = this.t - 1; i >= 0; i--) {
            this.u[i].u(view);
        }
    }

    private void L2(RecyclerView.v vVar, g gVar) {
        if (!gVar.f1459a || gVar.i) {
            return;
        }
        if (gVar.f1460b == 0) {
            if (gVar.f1463e == -1) {
                M2(vVar, gVar.g);
                return;
            } else {
                N2(vVar, gVar.f);
                return;
            }
        }
        if (gVar.f1463e != -1) {
            int iX2 = x2(gVar.g) - gVar.g;
            N2(vVar, iX2 < 0 ? gVar.f : Math.min(iX2, gVar.f1460b) + gVar.f);
        } else {
            int i = gVar.f;
            int iW2 = i - w2(i);
            M2(vVar, iW2 < 0 ? gVar.g : gVar.g - Math.min(iW2, gVar.f1460b));
        }
    }

    private void M2(RecyclerView.v vVar, int i) {
        for (int iT = T() - 1; iT >= 0; iT--) {
            View viewS = S(iT);
            if (this.v.g(viewS) < i || this.v.q(viewS) < i) {
                return;
            }
            c cVar = (c) viewS.getLayoutParams();
            if (cVar.g) {
                for (int i2 = 0; i2 < this.t; i2++) {
                    if (this.u[i2].f1376a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.t; i3++) {
                    this.u[i3].s();
                }
            } else if (cVar.f.f1376a.size() == 1) {
                return;
            } else {
                cVar.f.s();
            }
            v1(viewS, vVar);
        }
    }

    private void N2(RecyclerView.v vVar, int i) {
        while (T() > 0) {
            View viewS = S(0);
            if (this.v.d(viewS) > i || this.v.p(viewS) > i) {
                return;
            }
            c cVar = (c) viewS.getLayoutParams();
            if (cVar.g) {
                for (int i2 = 0; i2 < this.t; i2++) {
                    if (this.u[i2].f1376a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.t; i3++) {
                    this.u[i3].t();
                }
            } else if (cVar.f.f1376a.size() == 1) {
                return;
            } else {
                cVar.f.t();
            }
            v1(viewS, vVar);
        }
    }

    private void O2() {
        if (this.w.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int iT = T();
        for (int i = 0; i < iT; i++) {
            View viewS = S(i);
            float fE = this.w.e(viewS);
            if (fE >= fMax) {
                if (((c) viewS.getLayoutParams()).z()) {
                    fE = (fE * 1.0f) / this.t;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i2 = this.y;
        int iRound = Math.round(fMax * this.t);
        if (this.w.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.w.n());
        }
        a3(iRound);
        if (this.y == i2) {
            return;
        }
        for (int i3 = 0; i3 < iT; i3++) {
            View viewS2 = S(i3);
            c cVar = (c) viewS2.getLayoutParams();
            if (!cVar.g) {
                if (E2() && this.x == 1) {
                    int i4 = this.t;
                    int i5 = cVar.f.f1380e;
                    viewS2.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.y) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = cVar.f.f1380e;
                    int i7 = this.y * i6;
                    int i8 = i6 * i2;
                    if (this.x == 1) {
                        viewS2.offsetLeftAndRight(i7 - i8);
                    } else {
                        viewS2.offsetTopAndBottom(i7 - i8);
                    }
                }
            }
        }
    }

    private void P2() {
        if (this.x == 1 || !E2()) {
            this.B = this.A;
        } else {
            this.B = !this.A;
        }
    }

    private void R2(int i) {
        g gVar = this.z;
        gVar.f1463e = i;
        gVar.f1462d = this.B != (i == -1) ? -1 : 1;
    }

    private void V2(int i, int i2) {
        for (int i3 = 0; i3 < this.t; i3++) {
            if (!this.u[i3].f1376a.isEmpty()) {
                b3(this.u[i3], i, i2);
            }
        }
    }

    private void W1(View view) {
        for (int i = this.t - 1; i >= 0; i--) {
            this.u[i].a(view);
        }
    }

    private boolean W2(RecyclerView.z zVar, b bVar) {
        bVar.f1371a = this.H ? q2(zVar.b()) : m2(zVar.b());
        bVar.f1372b = Integer.MIN_VALUE;
        return true;
    }

    private void X1(b bVar) {
        SavedState savedState = this.J;
        int i = savedState.f1368d;
        if (i > 0) {
            if (i == this.t) {
                for (int i2 = 0; i2 < this.t; i2++) {
                    this.u[i2].e();
                    SavedState savedState2 = this.J;
                    int i3 = savedState2.f1369e[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        i3 += savedState2.j ? this.v.i() : this.v.m();
                    }
                    this.u[i2].v(i3);
                }
            } else {
                savedState.v();
                SavedState savedState3 = this.J;
                savedState3.f1366b = savedState3.f1367c;
            }
        }
        SavedState savedState4 = this.J;
        this.I = savedState4.k;
        T2(savedState4.i);
        P2();
        SavedState savedState5 = this.J;
        int i4 = savedState5.f1366b;
        if (i4 != -1) {
            this.D = i4;
            bVar.f1373c = savedState5.j;
        } else {
            bVar.f1373c = this.B;
        }
        if (savedState5.f > 1) {
            LazySpanLookup lazySpanLookup = this.F;
            lazySpanLookup.f1360a = savedState5.g;
            lazySpanLookup.f1361b = savedState5.h;
        }
    }

    private void Z2(int i, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        g gVar = this.z;
        boolean z = false;
        gVar.f1460b = 0;
        gVar.f1461c = i;
        if (!E0() || (iC = zVar.c()) == -1) {
            iN = 0;
            iN2 = 0;
        } else {
            if (this.B == (iC < i)) {
                iN = this.v.n();
                iN2 = 0;
            } else {
                iN2 = this.v.n();
                iN = 0;
            }
        }
        if (W()) {
            this.z.f = this.v.m() - iN2;
            this.z.g = this.v.i() + iN;
        } else {
            this.z.g = this.v.h() + iN;
            this.z.f = -iN2;
        }
        g gVar2 = this.z;
        gVar2.h = false;
        gVar2.f1459a = true;
        if (this.v.k() == 0 && this.v.h() == 0) {
            z = true;
        }
        gVar2.i = z;
    }

    private void a2(View view, c cVar, g gVar) {
        if (gVar.f1463e == 1) {
            if (cVar.g) {
                W1(view);
                return;
            } else {
                cVar.f.a(view);
                return;
            }
        }
        if (cVar.g) {
            K2(view);
        } else {
            cVar.f.u(view);
        }
    }

    private int b2(int i) {
        if (T() == 0) {
            return this.B ? 1 : -1;
        }
        return (i < t2()) != this.B ? -1 : 1;
    }

    private void b3(d dVar, int i, int i2) {
        int iJ = dVar.j();
        if (i == -1) {
            if (dVar.o() + iJ <= i2) {
                this.C.set(dVar.f1380e, false);
            }
        } else if (dVar.k() - iJ >= i2) {
            this.C.set(dVar.f1380e, false);
        }
    }

    private int c3(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    private boolean d2(d dVar) {
        if (this.B) {
            if (dVar.k() < this.v.i()) {
                ArrayList<View> arrayList = dVar.f1376a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).g;
            }
        } else if (dVar.o() > this.v.m()) {
            return !dVar.n(dVar.f1376a.get(0)).g;
        }
        return false;
    }

    private int e2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        return n.a(zVar, this.v, o2(!this.O), n2(!this.O), this, this.O);
    }

    private int f2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        return n.b(zVar, this.v, o2(!this.O), n2(!this.O), this, this.O, this.B);
    }

    private int g2(RecyclerView.z zVar) {
        if (T() == 0) {
            return 0;
        }
        return n.c(zVar, this.v, o2(!this.O), n2(!this.O), this, this.O);
    }

    private int h2(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.x == 1) ? 1 : Integer.MIN_VALUE : this.x == 0 ? 1 : Integer.MIN_VALUE : this.x == 1 ? -1 : Integer.MIN_VALUE : this.x == 0 ? -1 : Integer.MIN_VALUE : (this.x != 1 && E2()) ? -1 : 1 : (this.x != 1 && E2()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem i2(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1364d = new int[this.t];
        for (int i2 = 0; i2 < this.t; i2++) {
            fullSpanItem.f1364d[i2] = i - this.u[i2].l(i);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem j2(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1364d = new int[this.t];
        for (int i2 = 0; i2 < this.t; i2++) {
            fullSpanItem.f1364d[i2] = this.u[i2].p(i) - i;
        }
        return fullSpanItem;
    }

    private void k2() {
        this.v = k.b(this, this.x);
        this.w = k.b(this, 1 - this.x);
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
    private int l2(RecyclerView.v vVar, g gVar, RecyclerView.z zVar) {
        d dVarZ2;
        int iE;
        int i;
        int iE2;
        int iE3;
        boolean z;
        ?? r9 = 0;
        this.C.set(0, this.t, true);
        int i2 = this.z.i ? gVar.f1463e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : gVar.f1463e == 1 ? gVar.g + gVar.f1460b : gVar.f - gVar.f1460b;
        V2(gVar.f1463e, i2);
        int i3 = this.B ? this.v.i() : this.v.m();
        ?? r0 = false;
        while (gVar.a(zVar) && (this.z.i || !this.C.isEmpty())) {
            View viewB = gVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iU = cVar.u();
            int iG = this.F.g(iU);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarZ2 = cVar.g ? this.u[r9] : z2(gVar);
                this.F.n(iU, dVarZ2);
            } else {
                dVarZ2 = this.u[iG];
            }
            d dVar = dVarZ2;
            cVar.f = dVar;
            if (gVar.f1463e == 1) {
                n(viewB);
            } else {
                o(viewB, r9);
            }
            G2(viewB, cVar, r9);
            if (gVar.f1463e == 1) {
                int iV2 = cVar.g ? v2(i3) : dVar.l(i3);
                int iE4 = this.v.e(viewB) + iV2;
                if (r3 != 0 && cVar.g) {
                    LazySpanLookup.FullSpanItem fullSpanItemI2 = i2(iV2);
                    fullSpanItemI2.f1363c = -1;
                    fullSpanItemI2.f1362b = iU;
                    this.F.a(fullSpanItemI2);
                }
                i = iE4;
                iE = iV2;
            } else {
                int iY2 = cVar.g ? y2(i3) : dVar.p(i3);
                iE = iY2 - this.v.e(viewB);
                if (r3 != 0 && cVar.g) {
                    LazySpanLookup.FullSpanItem fullSpanItemJ2 = j2(iY2);
                    fullSpanItemJ2.f1363c = 1;
                    fullSpanItemJ2.f1362b = iU;
                    this.F.a(fullSpanItemJ2);
                }
                i = iY2;
            }
            if (cVar.g && gVar.f1462d == -1) {
                if (r3 != 0) {
                    this.N = true;
                } else {
                    if (!(gVar.f1463e == 1 ? Y1() : Z1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.F.f(iU);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f1365e = true;
                        }
                        this.N = true;
                    }
                }
            }
            a2(viewB, cVar, gVar);
            if (E2() && this.x == 1) {
                int i4 = cVar.g ? this.w.i() : this.w.i() - (((this.t - 1) - dVar.f1380e) * this.y);
                iE3 = i4;
                iE2 = i4 - this.w.e(viewB);
            } else {
                int iM = cVar.g ? this.w.m() : (dVar.f1380e * this.y) + this.w.m();
                iE2 = iM;
                iE3 = this.w.e(viewB) + iM;
            }
            if (this.x == 1) {
                H0(viewB, iE2, iE, iE3, i);
            } else {
                H0(viewB, iE, iE2, i, iE3);
            }
            if (cVar.g) {
                V2(this.z.f1463e, i2);
            } else {
                b3(dVar, this.z.f1463e, i2);
            }
            L2(vVar, this.z);
            if (!this.z.h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.g) {
                this.C.clear();
                z = false;
            } else {
                z = false;
                this.C.set(dVar.f1380e, false);
            }
            r9 = z;
            r0 = true;
        }
        ?? r32 = r9;
        if (r0 == false) {
            L2(vVar, this.z);
        }
        int iM2 = this.z.f1463e == -1 ? this.v.m() - y2(this.v.m()) : v2(this.v.i()) - this.v.i();
        return iM2 > 0 ? Math.min(gVar.f1460b, iM2) : r32 == true ? 1 : 0;
    }

    private int m2(int i) {
        int iT = T();
        for (int i2 = 0; i2 < iT; i2++) {
            int iN0 = n0(S(i2));
            if (iN0 >= 0 && iN0 < i) {
                return iN0;
            }
        }
        return 0;
    }

    private int q2(int i) {
        for (int iT = T() - 1; iT >= 0; iT--) {
            int iN0 = n0(S(iT));
            if (iN0 >= 0 && iN0 < i) {
                return iN0;
            }
        }
        return 0;
    }

    private void r2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i;
        int iV2 = v2(Integer.MIN_VALUE);
        if (iV2 != Integer.MIN_VALUE && (i = this.v.i() - iV2) > 0) {
            int i2 = i - (-Q2(-i, vVar, zVar));
            if (!z || i2 <= 0) {
                return;
            }
            this.v.r(i2);
        }
    }

    private void s2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iY2 = y2(Integer.MAX_VALUE);
        if (iY2 != Integer.MAX_VALUE && (iM = iY2 - this.v.m()) > 0) {
            int iQ2 = iM - Q2(iM, vVar, zVar);
            if (!z || iQ2 <= 0) {
                return;
            }
            this.v.r(-iQ2);
        }
    }

    private int v2(int i) {
        int iL = this.u[0].l(i);
        for (int i2 = 1; i2 < this.t; i2++) {
            int iL2 = this.u[i2].l(i);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int w2(int i) {
        int iP = this.u[0].p(i);
        for (int i2 = 1; i2 < this.t; i2++) {
            int iP2 = this.u[i2].p(i);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private int x2(int i) {
        int iL = this.u[0].l(i);
        for (int i2 = 1; i2 < this.t; i2++) {
            int iL2 = this.u[i2].l(i);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int y2(int i) {
        int iP = this.u[0].p(i);
        for (int i2 = 1; i2 < this.t; i2++) {
            int iP2 = this.u[i2].p(i);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private d z2(g gVar) {
        int i;
        int i2;
        int i3 = -1;
        if (I2(gVar.f1463e)) {
            i = this.t - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.t;
            i2 = 1;
        }
        d dVar = null;
        if (gVar.f1463e == 1) {
            int i4 = Integer.MAX_VALUE;
            int iM = this.v.m();
            while (i != i3) {
                d dVar2 = this.u[i];
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
        int i6 = this.v.i();
        while (i != i3) {
            d dVar3 = this.u[i];
            int iP = dVar3.p(i6);
            if (iP > i5) {
                dVar = dVar3;
                i5 = iP;
            }
            i += i2;
        }
        return dVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int A(RecyclerView.z zVar) {
        return e2(zVar);
    }

    public int A2() {
        return this.x;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int B(RecyclerView.z zVar) {
        return f2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int C(RecyclerView.z zVar) {
        return g2(zVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.View C2() {
        /*
            r12 = this;
            int r0 = r12.T()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.t
            r2.<init>(r3)
            int r3 = r12.t
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.x
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.E2()
            if (r3 == 0) goto L20
            r3 = r1
            goto L21
        L20:
            r3 = r5
        L21:
            boolean r6 = r12.B
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
            android.view.View r7 = r12.S(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f
            int r9 = r9.f1380e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f
            boolean r9 = r12.d2(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f
            int r9 = r9.f1380e
            r2.clear(r9)
        L54:
            boolean r9 = r8.g
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.S(r9)
            boolean r10 = r12.B
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.k r10 = r12.v
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.k r11 = r12.v
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.k r10 = r12.v
            int r10 = r10.g(r7)
            androidx.recyclerview.widget.k r11 = r12.v
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f
            int r8 = r8.f1380e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f
            int r9 = r9.f1380e
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.C2():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int D(RecyclerView.z zVar) {
        return e2(zVar);
    }

    public void D2() {
        this.F.b();
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int E(RecyclerView.z zVar) {
        return f2(zVar);
    }

    boolean E2() {
        return j0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int F(RecyclerView.z zVar) {
        return g2(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int F1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        return Q2(i, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void G1(int i) {
        SavedState savedState = this.J;
        if (savedState != null && savedState.f1366b != i) {
            savedState.u();
        }
        this.D = i;
        this.E = Integer.MIN_VALUE;
        C1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int H1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        return Q2(i, vVar, zVar);
    }

    void J2(int i, RecyclerView.z zVar) {
        int i2;
        int iT2;
        if (i > 0) {
            iT2 = u2();
            i2 = 1;
        } else {
            i2 = -1;
            iT2 = t2();
        }
        this.z.f1459a = true;
        Z2(iT2, zVar);
        R2(i2);
        g gVar = this.z;
        gVar.f1461c = iT2 + gVar.f1462d;
        gVar.f1460b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void K0(int i) {
        super.K0(i);
        for (int i2 = 0; i2 < this.t; i2++) {
            this.u[i2].r(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void L0(int i) {
        super.L0(i);
        for (int i2 = 0; i2 < this.t; i2++) {
            this.u[i2].r(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void M1(Rect rect, int i, int i2) {
        int iX;
        int iX2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.x == 1) {
            iX2 = RecyclerView.o.x(i2, rect.height() + paddingTop, l0());
            iX = RecyclerView.o.x(i, (this.y * this.t) + paddingLeft, m0());
        } else {
            iX = RecyclerView.o.x(i, rect.width() + paddingLeft, m0());
            iX2 = RecyclerView.o.x(i2, (this.y * this.t) + paddingTop, l0());
        }
        L1(iX, iX2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p N() {
        return this.x == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p O(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p P(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Q0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.Q0(recyclerView, vVar);
        x1(this.Q);
        for (int i = 0; i < this.t; i++) {
            this.u[i].e();
        }
        recyclerView.requestLayout();
    }

    int Q2(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (T() == 0 || i == 0) {
            return 0;
        }
        J2(i, zVar);
        int iL2 = l2(vVar, this.z, zVar);
        if (this.z.f1460b >= iL2) {
            i = i < 0 ? -iL2 : iL2;
        }
        this.v.r(-i);
        this.H = this.B;
        g gVar = this.z;
        gVar.f1460b = 0;
        L2(vVar, gVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View R0(View view, int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewL;
        View viewM;
        if (T() == 0 || (viewL = L(view)) == null) {
            return null;
        }
        P2();
        int iH2 = h2(i);
        if (iH2 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewL.getLayoutParams();
        boolean z = cVar.g;
        d dVar = cVar.f;
        int iU2 = iH2 == 1 ? u2() : t2();
        Z2(iU2, zVar);
        R2(iH2);
        g gVar = this.z;
        gVar.f1461c = gVar.f1462d + iU2;
        gVar.f1460b = (int) (this.v.n() * 0.33333334f);
        g gVar2 = this.z;
        gVar2.h = true;
        gVar2.f1459a = false;
        l2(vVar, gVar2, zVar);
        this.H = this.B;
        if (!z && (viewM = dVar.m(iU2, iH2)) != null && viewM != viewL) {
            return viewM;
        }
        if (I2(iH2)) {
            for (int i2 = this.t - 1; i2 >= 0; i2--) {
                View viewM2 = this.u[i2].m(iU2, iH2);
                if (viewM2 != null && viewM2 != viewL) {
                    return viewM2;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.t; i3++) {
                View viewM3 = this.u[i3].m(iU2, iH2);
                if (viewM3 != null && viewM3 != viewL) {
                    return viewM3;
                }
            }
        }
        boolean z2 = (this.A ^ true) == (iH2 == -1);
        if (!z) {
            View viewM4 = M(z2 ? dVar.f() : dVar.g());
            if (viewM4 != null && viewM4 != viewL) {
                return viewM4;
            }
        }
        if (I2(iH2)) {
            for (int i4 = this.t - 1; i4 >= 0; i4--) {
                if (i4 != dVar.f1380e) {
                    View viewM5 = M(z2 ? this.u[i4].f() : this.u[i4].g());
                    if (viewM5 != null && viewM5 != viewL) {
                        return viewM5;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.t; i5++) {
                View viewM6 = M(z2 ? this.u[i5].f() : this.u[i5].g());
                if (viewM6 != null && viewM6 != viewL) {
                    return viewM6;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(AccessibilityEvent accessibilityEvent) {
        super.S0(accessibilityEvent);
        if (T() > 0) {
            View viewO2 = o2(false);
            View viewN2 = n2(false);
            if (viewO2 == null || viewN2 == null) {
                return;
            }
            int iN0 = n0(viewO2);
            int iN02 = n0(viewN2);
            if (iN0 < iN02) {
                accessibilityEvent.setFromIndex(iN0);
                accessibilityEvent.setToIndex(iN02);
            } else {
                accessibilityEvent.setFromIndex(iN02);
                accessibilityEvent.setToIndex(iN0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S1(RecyclerView recyclerView, RecyclerView.z zVar, int i) {
        h hVar = new h(recyclerView.getContext());
        hVar.p(i);
        T1(hVar);
    }

    public void S2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        q(null);
        if (i == this.x) {
            return;
        }
        this.x = i;
        k kVar = this.v;
        this.v = this.w;
        this.w = kVar;
        C1();
    }

    public void T2(boolean z) {
        q(null);
        SavedState savedState = this.J;
        if (savedState != null && savedState.i != z) {
            savedState.i = z;
        }
        this.A = z;
        C1();
    }

    public void U2(int i) {
        q(null);
        if (i != this.t) {
            D2();
            this.t = i;
            this.C = new BitSet(this.t);
            this.u = new d[this.t];
            for (int i2 = 0; i2 < this.t; i2++) {
                this.u[i2] = new d(i2);
            }
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean V1() {
        return this.J == null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int X(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x == 1 ? this.t : super.X(vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar, View view, b.g.k.c0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.W0(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.x == 0) {
            cVar.e0(c.C0038c.a(cVar2.y(), cVar2.g ? this.t : 1, -1, -1, false, false));
        } else {
            cVar.e0(c.C0038c.a(-1, -1, cVar2.y(), cVar2.g ? this.t : 1, false, false));
        }
    }

    boolean X2(RecyclerView.z zVar, b bVar) {
        int i;
        if (!zVar.e() && (i = this.D) != -1) {
            if (i >= 0 && i < zVar.b()) {
                SavedState savedState = this.J;
                if (savedState == null || savedState.f1366b == -1 || savedState.f1368d < 1) {
                    View viewM = M(this.D);
                    if (viewM != null) {
                        bVar.f1371a = this.B ? u2() : t2();
                        if (this.E != Integer.MIN_VALUE) {
                            if (bVar.f1373c) {
                                bVar.f1372b = (this.v.i() - this.E) - this.v.d(viewM);
                            } else {
                                bVar.f1372b = (this.v.m() + this.E) - this.v.g(viewM);
                            }
                            return true;
                        }
                        if (this.v.e(viewM) > this.v.n()) {
                            bVar.f1372b = bVar.f1373c ? this.v.i() : this.v.m();
                            return true;
                        }
                        int iG = this.v.g(viewM) - this.v.m();
                        if (iG < 0) {
                            bVar.f1372b = -iG;
                            return true;
                        }
                        int i2 = this.v.i() - this.v.d(viewM);
                        if (i2 < 0) {
                            bVar.f1372b = i2;
                            return true;
                        }
                        bVar.f1372b = Integer.MIN_VALUE;
                    } else {
                        int i3 = this.D;
                        bVar.f1371a = i3;
                        int i4 = this.E;
                        if (i4 == Integer.MIN_VALUE) {
                            bVar.f1373c = b2(i3) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i4);
                        }
                        bVar.f1374d = true;
                    }
                } else {
                    bVar.f1372b = Integer.MIN_VALUE;
                    bVar.f1371a = this.D;
                }
                return true;
            }
            this.D = -1;
            this.E = Integer.MIN_VALUE;
        }
        return false;
    }

    boolean Y1() {
        int iL = this.u[0].l(Integer.MIN_VALUE);
        for (int i = 1; i < this.t; i++) {
            if (this.u[i].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    void Y2(RecyclerView.z zVar, b bVar) {
        if (X2(zVar, bVar) || W2(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1371a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Z0(RecyclerView recyclerView, int i, int i2) {
        B2(i, i2, 1);
    }

    boolean Z1() {
        int iP = this.u[0].p(Integer.MIN_VALUE);
        for (int i = 1; i < this.t; i++) {
            if (this.u[i].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a1(RecyclerView recyclerView) {
        this.F.b();
        C1();
    }

    void a3(int i) {
        this.y = i / this.t;
        this.K = View.MeasureSpec.makeMeasureSpec(i, this.w.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b1(RecyclerView recyclerView, int i, int i2, int i3) {
        B2(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(RecyclerView recyclerView, int i, int i2) {
        B2(i, i2, 2);
    }

    boolean c2() {
        int iT2;
        int iU2;
        if (T() == 0 || this.G == 0 || !x0()) {
            return false;
        }
        if (this.B) {
            iT2 = u2();
            iU2 = t2();
        } else {
            iT2 = t2();
            iU2 = u2();
        }
        if (iT2 == 0 && C2() != null) {
            this.F.b();
            D1();
            C1();
            return true;
        }
        if (!this.N) {
            return false;
        }
        int i = this.B ? -1 : 1;
        int i2 = iU2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.F.e(iT2, i2, i, true);
        if (fullSpanItemE == null) {
            this.N = false;
            this.F.d(i2);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.F.e(iT2, fullSpanItemE.f1362b, i * (-1), true);
        if (fullSpanItemE2 == null) {
            this.F.d(fullSpanItemE.f1362b);
        } else {
            this.F.d(fullSpanItemE2.f1362b + 1);
        }
        D1();
        C1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF e(int i) {
        int iB2 = b2(i);
        PointF pointF = new PointF();
        if (iB2 == 0) {
            return null;
        }
        if (this.x == 0) {
            pointF.x = iB2;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iB2;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e1(RecyclerView recyclerView, int i, int i2, Object obj) {
        B2(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f1(RecyclerView.v vVar, RecyclerView.z zVar) {
        H2(vVar, zVar, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g1(RecyclerView.z zVar) {
        super.g1(zVar);
        this.D = -1;
        this.E = Integer.MIN_VALUE;
        this.J = null;
        this.M.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void k1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.J = (SavedState) parcelable;
            C1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable l1() {
        int iP;
        int iM;
        int[] iArr;
        if (this.J != null) {
            return new SavedState(this.J);
        }
        SavedState savedState = new SavedState();
        savedState.i = this.A;
        savedState.j = this.H;
        savedState.k = this.I;
        LazySpanLookup lazySpanLookup = this.F;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1360a) == null) {
            savedState.f = 0;
        } else {
            savedState.g = iArr;
            savedState.f = iArr.length;
            savedState.h = lazySpanLookup.f1361b;
        }
        if (T() > 0) {
            savedState.f1366b = this.H ? u2() : t2();
            savedState.f1367c = p2();
            int i = this.t;
            savedState.f1368d = i;
            savedState.f1369e = new int[i];
            for (int i2 = 0; i2 < this.t; i2++) {
                if (this.H) {
                    iP = this.u[i2].l(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.v.i();
                        iP -= iM;
                    }
                } else {
                    iP = this.u[i2].p(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.v.m();
                        iP -= iM;
                    }
                }
                savedState.f1369e[i2] = iP;
            }
        } else {
            savedState.f1366b = -1;
            savedState.f1367c = -1;
            savedState.f1368d = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void m1(int i) {
        if (i == 0) {
            c2();
        }
    }

    View n2(boolean z) {
        int iM = this.v.m();
        int i = this.v.i();
        View view = null;
        for (int iT = T() - 1; iT >= 0; iT--) {
            View viewS = S(iT);
            int iG = this.v.g(viewS);
            int iD = this.v.d(viewS);
            if (iD > iM && iG < i) {
                if (iD <= i || !z) {
                    return viewS;
                }
                if (view == null) {
                    view = viewS;
                }
            }
        }
        return view;
    }

    View o2(boolean z) {
        int iM = this.v.m();
        int i = this.v.i();
        int iT = T();
        View view = null;
        for (int i2 = 0; i2 < iT; i2++) {
            View viewS = S(i2);
            int iG = this.v.g(viewS);
            if (this.v.d(viewS) > iM && iG < i) {
                if (iG >= iM || !z) {
                    return viewS;
                }
                if (view == null) {
                    view = viewS;
                }
            }
        }
        return view;
    }

    int p2() {
        View viewN2 = this.B ? n2(true) : o2(true);
        if (viewN2 == null) {
            return -1;
        }
        return n0(viewN2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void q(String str) {
        if (this.J == null) {
            super.q(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q0(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.x == 0 ? this.t : super.q0(vVar, zVar);
    }

    int t2() {
        if (T() == 0) {
            return 0;
        }
        return n0(S(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean u() {
        return this.x == 0;
    }

    int u2() {
        int iT = T();
        if (iT == 0) {
            return 0;
        }
        return n0(S(iT - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean v() {
        return this.x == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean w(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void y(int i, int i2, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.x != 0) {
            i = i2;
        }
        if (T() == 0 || i == 0) {
            return;
        }
        J2(i, zVar);
        int[] iArr = this.P;
        if (iArr == null || iArr.length < this.t) {
            this.P = new int[this.t];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.t; i4++) {
            g gVar = this.z;
            if (gVar.f1462d == -1) {
                iL = gVar.f;
                iP = this.u[i4].p(iL);
            } else {
                iL = this.u[i4].l(gVar.g);
                iP = this.z.g;
            }
            int i5 = iL - iP;
            if (i5 >= 0) {
                this.P[i3] = i5;
                i3++;
            }
        }
        Arrays.sort(this.P, 0, i3);
        for (int i6 = 0; i6 < i3 && this.z.a(zVar); i6++) {
            cVar.a(this.z.f1461c, this.P[i6]);
            g gVar2 = this.z;
            gVar2.f1461c += gVar2.f1462d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean y0() {
        return this.G != 0;
    }

    static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int[] f1360a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        List<FullSpanItem> f1361b;

        LazySpanLookup() {
        }

        private int i(int i) {
            if (this.f1361b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i);
            if (fullSpanItemF != null) {
                this.f1361b.remove(fullSpanItemF);
            }
            int size = this.f1361b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                if (this.f1361b.get(i2).f1362b >= i) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1361b.get(i2);
            this.f1361b.remove(i2);
            return fullSpanItem.f1362b;
        }

        private void l(int i, int i2) {
            List<FullSpanItem> list = this.f1361b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1361b.get(size);
                int i3 = fullSpanItem.f1362b;
                if (i3 >= i) {
                    fullSpanItem.f1362b = i3 + i2;
                }
            }
        }

        private void m(int i, int i2) {
            List<FullSpanItem> list = this.f1361b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1361b.get(size);
                int i4 = fullSpanItem.f1362b;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.f1361b.remove(size);
                    } else {
                        fullSpanItem.f1362b = i4 - i2;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1361b == null) {
                this.f1361b = new ArrayList();
            }
            int size = this.f1361b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.f1361b.get(i);
                if (fullSpanItem2.f1362b == fullSpanItem.f1362b) {
                    this.f1361b.remove(i);
                }
                if (fullSpanItem2.f1362b >= fullSpanItem.f1362b) {
                    this.f1361b.add(i, fullSpanItem);
                    return;
                }
            }
            this.f1361b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.f1360a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1361b = null;
        }

        void c(int i) {
            int[] iArr = this.f1360a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1360a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[o(i)];
                this.f1360a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1360a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i) {
            List<FullSpanItem> list = this.f1361b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1361b.get(size).f1362b >= i) {
                        this.f1361b.remove(size);
                    }
                }
            }
            return h(i);
        }

        public FullSpanItem e(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.f1361b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.f1361b.get(i4);
                int i5 = fullSpanItem.f1362b;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || fullSpanItem.f1363c == i3 || (z && fullSpanItem.f1365e))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i) {
            List<FullSpanItem> list = this.f1361b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1361b.get(size);
                if (fullSpanItem.f1362b == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i) {
            int[] iArr = this.f1360a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        int h(int i) {
            int[] iArr = this.f1360a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int i2 = i(i);
            if (i2 == -1) {
                int[] iArr2 = this.f1360a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.f1360a.length;
            }
            int i3 = i2 + 1;
            Arrays.fill(this.f1360a, i, i3, -1);
            return i3;
        }

        void j(int i, int i2) {
            int[] iArr = this.f1360a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1360a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1360a, i, i3, -1);
            l(i, i2);
        }

        void k(int i, int i2) {
            int[] iArr = this.f1360a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1360a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1360a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            m(i, i2);
        }

        void n(int i, d dVar) {
            c(i);
            this.f1360a[i] = dVar.f1380e;
        }

        int o(int i) {
            int length = this.f1360a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f1362b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1363c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int[] f1364d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            boolean f1365e;

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
                this.f1362b = parcel.readInt();
                this.f1363c = parcel.readInt();
                this.f1365e = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.f1364d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1362b + ", mGapDir=" + this.f1363c + ", mHasUnwantedGapAfter=" + this.f1365e + ", mGapPerSpan=" + Arrays.toString(this.f1364d) + '}';
            }

            int u(int i) {
                int[] iArr = this.f1364d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1362b);
                parcel.writeInt(this.f1363c);
                parcel.writeInt(this.f1365e ? 1 : 0);
                int[] iArr = this.f1364d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1364d);
                }
            }

            FullSpanItem() {
            }
        }
    }
}
