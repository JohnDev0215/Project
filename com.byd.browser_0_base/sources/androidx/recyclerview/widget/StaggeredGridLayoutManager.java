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
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import defpackage.in3;
import defpackage.om3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.m implements RecyclerView.v.b {
    public final LazySpanLookup B;
    public final int C;
    public boolean D;
    public boolean E;
    public SavedState F;
    public final Rect G;
    public final b H;
    public final boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int[] f20J;
    public final a K;
    public int p;
    public c[] q;
    public s r;
    public s s;
    public int t;
    public int u;
    public final o v;
    public boolean w;
    public BitSet y;
    public boolean x = false;
    public int z = -1;
    public int A = Integer.MIN_VALUE;

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public c e;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int e;
        public int f;
        public int g;
        public int[] h;
        public int i;
        public int[] j;
        public List<LazySpanLookup.FullSpanItem> k;
        public boolean l;
        public boolean m;
        public boolean n;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            if (this.g > 0) {
                parcel.writeIntArray(this.h);
            }
            parcel.writeInt(this.i);
            if (this.i > 0) {
                parcel.writeIntArray(this.j);
            }
            parcel.writeInt(this.l ? 1 : 0);
            parcel.writeInt(this.m ? 1 : 0);
            parcel.writeInt(this.n ? 1 : 0);
            parcel.writeList(this.k);
        }

        public SavedState(Parcel parcel) {
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            int i = parcel.readInt();
            this.g = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.h = iArr;
                parcel.readIntArray(iArr);
            }
            int i2 = parcel.readInt();
            this.i = i2;
            if (i2 > 0) {
                int[] iArr2 = new int[i2];
                this.j = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.l = parcel.readInt() == 1;
            this.m = parcel.readInt() == 1;
            this.n = parcel.readInt() == 1;
            this.k = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.g = savedState.g;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.l = savedState.l;
            this.m = savedState.m;
            this.n = savedState.n;
            this.k = savedState.k;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            StaggeredGridLayoutManager.this.J0();
        }
    }

    public class b {
        public int a;
        public int b;
        public boolean c;
        public boolean d;
        public boolean e;
        public int[] f;

        public b() {
            a();
        }

        public final void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }
    }

    public class c {
        public final ArrayList<View> a = new ArrayList<>();
        public int b = Integer.MIN_VALUE;
        public int c = Integer.MIN_VALUE;
        public int d = 0;
        public final int e;

        public c(int i) {
            this.e = i;
        }

        public static LayoutParams h(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        public final void a() {
            View view = this.a.get(r0.size() - 1);
            LayoutParams layoutParamsH = h(view);
            this.c = StaggeredGridLayoutManager.this.r.b(view);
            layoutParamsH.getClass();
        }

        public final void b() {
            this.a.clear();
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
            this.d = 0;
        }

        public final int c() {
            return StaggeredGridLayoutManager.this.w ? e(r1.size() - 1, -1) : e(0, this.a.size());
        }

        public final int d() {
            return StaggeredGridLayoutManager.this.w ? e(0, this.a.size()) : e(r1.size() - 1, -1);
        }

        public final int e(int i, int i2) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
            int iK = staggeredGridLayoutManager.r.k();
            int iG = staggeredGridLayoutManager.r.g();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.a.get(i);
                int iE = staggeredGridLayoutManager.r.e(view);
                int iB = staggeredGridLayoutManager.r.b(view);
                boolean z = iE <= iG;
                boolean z2 = iB >= iK;
                if (z && z2 && (iE < iK || iB > iG)) {
                    return RecyclerView.m.K(view);
                }
                i += i3;
            }
            return -1;
        }

        public final int f(int i) {
            int i2 = this.c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.a.size() == 0) {
                return i;
            }
            a();
            return this.c;
        }

        public final View g(int i, int i2) {
            ArrayList<View> arrayList = this.a;
            StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
            View view = null;
            if (i2 != -1) {
                int size = arrayList.size() - 1;
                while (size >= 0) {
                    View view2 = arrayList.get(size);
                    if ((staggeredGridLayoutManager.w && RecyclerView.m.K(view2) >= i) || ((!staggeredGridLayoutManager.w && RecyclerView.m.K(view2) <= i) || !view2.hasFocusable())) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = arrayList.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = arrayList.get(i3);
                    if ((staggeredGridLayoutManager.w && RecyclerView.m.K(view3) <= i) || ((!staggeredGridLayoutManager.w && RecyclerView.m.K(view3) >= i) || !view3.hasFocusable())) {
                        break;
                    }
                    i3++;
                    view = view3;
                }
            }
            return view;
        }

        public final int i(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            ArrayList<View> arrayList = this.a;
            if (arrayList.size() == 0) {
                return i;
            }
            View view = arrayList.get(0);
            LayoutParams layoutParamsH = h(view);
            this.b = StaggeredGridLayoutManager.this.r.e(view);
            layoutParamsH.getClass();
            return this.b;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.p = -1;
        this.w = false;
        LazySpanLookup lazySpanLookup = new LazySpanLookup();
        this.B = lazySpanLookup;
        this.C = 2;
        this.G = new Rect();
        this.H = new b();
        this.I = true;
        this.K = new a();
        RecyclerView.m.d dVarL = RecyclerView.m.L(context, attributeSet, i, i2);
        int i3 = dVarL.a;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        f(null);
        if (i3 != this.t) {
            this.t = i3;
            s sVar = this.r;
            this.r = this.s;
            this.s = sVar;
            t0();
        }
        int i4 = dVarL.b;
        f(null);
        if (i4 != this.p) {
            lazySpanLookup.a();
            t0();
            this.p = i4;
            this.y = new BitSet(this.p);
            this.q = new c[this.p];
            for (int i5 = 0; i5 < this.p; i5++) {
                this.q[i5] = new c(i5);
            }
            t0();
        }
        boolean z = dVarL.c;
        f(null);
        SavedState savedState = this.F;
        if (savedState != null && savedState.l != z) {
            savedState.l = z;
        }
        this.w = z;
        t0();
        this.v = new o();
        this.r = s.a(this, this.t);
        this.s = s.a(this, 1 - this.t);
    }

    public static int l1(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int C(RecyclerView.s sVar, RecyclerView.w wVar) {
        if (this.t == 1) {
            return Math.min(this.p, wVar.b());
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void F0(RecyclerView recyclerView, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.a = i;
        G0(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean H0() {
        return this.F == null;
    }

    public final int I0(int i) {
        if (A() == 0) {
            return this.x ? 1 : -1;
        }
        return (i < S0()) != this.x ? -1 : 1;
    }

    public final boolean J0() {
        int iS0;
        if (A() != 0 && this.C != 0 && this.g) {
            if (this.x) {
                iS0 = T0();
                S0();
            } else {
                iS0 = S0();
                T0();
            }
            if (iS0 == 0 && X0() != null) {
                this.B.a();
                this.f = true;
                t0();
                return true;
            }
        }
        return false;
    }

    public final int K0(RecyclerView.w wVar) {
        if (A() == 0) {
            return 0;
        }
        s sVar = this.r;
        boolean z = this.I;
        return y.a(wVar, sVar, P0(!z), O0(!z), this, this.I);
    }

    public final int L0(RecyclerView.w wVar) {
        if (A() == 0) {
            return 0;
        }
        s sVar = this.r;
        boolean z = this.I;
        return y.b(wVar, sVar, P0(!z), O0(!z), this, this.I, this.x);
    }

    public final int M0(RecyclerView.w wVar) {
        if (A() == 0) {
            return 0;
        }
        s sVar = this.r;
        boolean z = this.I;
        return y.c(wVar, sVar, P0(!z), O0(!z), this, this.I);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int N(RecyclerView.s sVar, RecyclerView.w wVar) {
        if (this.t == 0) {
            return Math.min(this.p, wVar.b());
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v19 */
    public final int N0(RecyclerView.s sVar, o oVar, RecyclerView.w wVar) {
        c cVar;
        ?? r8;
        int i;
        int iC;
        int iK;
        int iC2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        int i7 = 1;
        this.y.set(0, this.p, true);
        o oVar2 = this.v;
        int i8 = oVar2.i ? oVar.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : oVar.e == 1 ? oVar.g + oVar.b : oVar.f - oVar.b;
        int i9 = oVar.e;
        for (int i10 = 0; i10 < this.p; i10++) {
            if (!this.q[i10].a.isEmpty()) {
                k1(this.q[i10], i9, i8);
            }
        }
        int iG = this.x ? this.r.g() : this.r.k();
        boolean z = false;
        while (true) {
            int i11 = oVar.c;
            if (((i11 < 0 || i11 >= wVar.b()) ? i6 : i7) == 0 || (!oVar2.i && this.y.isEmpty())) {
                break;
            }
            View view = sVar.k(Long.MAX_VALUE, oVar.c).e;
            oVar.c += oVar.d;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int iA = layoutParams.a();
            LazySpanLookup lazySpanLookup = this.B;
            int[] iArr = lazySpanLookup.a;
            int i12 = (iArr == null || iA >= iArr.length) ? -1 : iArr[iA];
            if ((i12 == -1 ? i7 : i6) != 0) {
                if (b1(oVar.e)) {
                    i5 = this.p - i7;
                    i4 = -1;
                    i3 = -1;
                } else {
                    i3 = i7;
                    i4 = this.p;
                    i5 = i6;
                }
                c cVar2 = null;
                if (oVar.e == i7) {
                    int iK2 = this.r.k();
                    int i13 = Integer.MAX_VALUE;
                    while (i5 != i4) {
                        c cVar3 = this.q[i5];
                        int iF = cVar3.f(iK2);
                        if (iF < i13) {
                            i13 = iF;
                            cVar2 = cVar3;
                        }
                        i5 += i3;
                    }
                } else {
                    int iG2 = this.r.g();
                    int i14 = Integer.MIN_VALUE;
                    while (i5 != i4) {
                        c cVar4 = this.q[i5];
                        int i15 = cVar4.i(iG2);
                        if (i15 > i14) {
                            cVar2 = cVar4;
                            i14 = i15;
                        }
                        i5 += i3;
                    }
                }
                cVar = cVar2;
                lazySpanLookup.b(iA);
                lazySpanLookup.a[iA] = cVar.e;
            } else {
                cVar = this.q[i12];
            }
            layoutParams.e = cVar;
            if (oVar.e == 1) {
                r8 = 0;
                e(view, -1, false);
            } else {
                r8 = 0;
                e(view, 0, false);
            }
            if (this.t == 1) {
                Z0(view, RecyclerView.m.B(r8, this.u, this.l, r8, ((ViewGroup.MarginLayoutParams) layoutParams).width), RecyclerView.m.B(true, this.o, this.m, G() + J(), ((ViewGroup.MarginLayoutParams) layoutParams).height), r8);
            } else {
                Z0(view, RecyclerView.m.B(true, this.n, this.l, I() + H(), ((ViewGroup.MarginLayoutParams) layoutParams).width), RecyclerView.m.B(false, this.u, this.m, 0, ((ViewGroup.MarginLayoutParams) layoutParams).height), false);
            }
            if (oVar.e == 1) {
                iC = cVar.f(iG);
                i = this.r.c(view) + iC;
            } else {
                i = cVar.i(iG);
                iC = i - this.r.c(view);
            }
            if (oVar.e == 1) {
                c cVar5 = layoutParams.e;
                cVar5.getClass();
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                layoutParams2.e = cVar5;
                ArrayList<View> arrayList = cVar5.a;
                arrayList.add(view);
                cVar5.c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    cVar5.b = Integer.MIN_VALUE;
                }
                if (layoutParams2.c() || layoutParams2.b()) {
                    cVar5.d = StaggeredGridLayoutManager.this.r.c(view) + cVar5.d;
                }
            } else {
                c cVar6 = layoutParams.e;
                cVar6.getClass();
                LayoutParams layoutParams3 = (LayoutParams) view.getLayoutParams();
                layoutParams3.e = cVar6;
                ArrayList<View> arrayList2 = cVar6.a;
                arrayList2.add(0, view);
                cVar6.b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    cVar6.c = Integer.MIN_VALUE;
                }
                if (layoutParams3.c() || layoutParams3.b()) {
                    cVar6.d = StaggeredGridLayoutManager.this.r.c(view) + cVar6.d;
                }
            }
            if (Y0() && this.t == 1) {
                iC2 = this.s.g() - (((this.p - 1) - cVar.e) * this.u);
                iK = iC2 - this.s.c(view);
            } else {
                iK = this.s.k() + (cVar.e * this.u);
                iC2 = this.s.c(view) + iK;
            }
            if (this.t == 1) {
                RecyclerView.m.S(view, iK, iC, iC2, i);
            } else {
                RecyclerView.m.S(view, iC, iK, i, iC2);
            }
            k1(cVar, oVar2.e, i8);
            d1(sVar, oVar2);
            if (oVar2.h && view.hasFocusable()) {
                i2 = 0;
                this.y.set(cVar.e, false);
            } else {
                i2 = 0;
            }
            i6 = i2;
            i7 = 1;
            z = true;
        }
        int i16 = i6;
        if (!z) {
            d1(sVar, oVar2);
        }
        int iK3 = oVar2.e == -1 ? this.r.k() - V0(this.r.k()) : U0(this.r.g()) - this.r.g();
        return iK3 > 0 ? Math.min(oVar.b, iK3) : i16;
    }

    public final View O0(boolean z) {
        int iK = this.r.k();
        int iG = this.r.g();
        View view = null;
        for (int iA = A() - 1; iA >= 0; iA--) {
            View viewZ = z(iA);
            int iE = this.r.e(viewZ);
            int iB = this.r.b(viewZ);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z) {
                    return viewZ;
                }
                if (view == null) {
                    view = viewZ;
                }
            }
        }
        return view;
    }

    public final View P0(boolean z) {
        int iK = this.r.k();
        int iG = this.r.g();
        int iA = A();
        View view = null;
        for (int i = 0; i < iA; i++) {
            View viewZ = z(i);
            int iE = this.r.e(viewZ);
            if (this.r.b(viewZ) > iK && iE < iG) {
                if (iE >= iK || !z) {
                    return viewZ;
                }
                if (view == null) {
                    view = viewZ;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean Q() {
        return this.C != 0;
    }

    public final void Q0(RecyclerView.s sVar, RecyclerView.w wVar, boolean z) {
        int iG;
        int iU0 = U0(Integer.MIN_VALUE);
        if (iU0 != Integer.MIN_VALUE && (iG = this.r.g() - iU0) > 0) {
            int i = iG - (-h1(-iG, sVar, wVar));
            if (!z || i <= 0) {
                return;
            }
            this.r.p(i);
        }
    }

    public final void R0(RecyclerView.s sVar, RecyclerView.w wVar, boolean z) {
        int iK;
        int iV0 = V0(Integer.MAX_VALUE);
        if (iV0 != Integer.MAX_VALUE && (iK = iV0 - this.r.k()) > 0) {
            int iH1 = iK - h1(iK, sVar, wVar);
            if (!z || iH1 <= 0) {
                return;
            }
            this.r.p(-iH1);
        }
    }

    public final int S0() {
        if (A() == 0) {
            return 0;
        }
        return RecyclerView.m.K(z(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void T(int i) {
        super.T(i);
        for (int i2 = 0; i2 < this.p; i2++) {
            c cVar = this.q[i2];
            int i3 = cVar.b;
            if (i3 != Integer.MIN_VALUE) {
                cVar.b = i3 + i;
            }
            int i4 = cVar.c;
            if (i4 != Integer.MIN_VALUE) {
                cVar.c = i4 + i;
            }
        }
    }

    public final int T0() {
        int iA = A();
        if (iA == 0) {
            return 0;
        }
        return RecyclerView.m.K(z(iA - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void U(int i) {
        super.U(i);
        for (int i2 = 0; i2 < this.p; i2++) {
            c cVar = this.q[i2];
            int i3 = cVar.b;
            if (i3 != Integer.MIN_VALUE) {
                cVar.b = i3 + i;
            }
            int i4 = cVar.c;
            if (i4 != Integer.MIN_VALUE) {
                cVar.c = i4 + i;
            }
        }
    }

    public final int U0(int i) {
        int iF = this.q[0].f(i);
        for (int i2 = 1; i2 < this.p; i2++) {
            int iF2 = this.q[i2].f(i);
            if (iF2 > iF) {
                iF = iF2;
            }
        }
        return iF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void V() {
        this.B.a();
        for (int i = 0; i < this.p; i++) {
            this.q[i].b();
        }
    }

    public final int V0(int i) {
        int i2 = this.q[0].i(i);
        for (int i3 = 1; i3 < this.p; i3++) {
            int i4 = this.q[i3].i(i);
            if (i4 < i2) {
                i2 = i4;
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void W(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.K);
        }
        for (int i = 0; i < this.p; i++) {
            this.q[i].b();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void W0(int r8, int r9, int r10) {
        /*
            r7 = this;
            boolean r0 = r7.x
            if (r0 == 0) goto L9
            int r0 = r7.T0()
            goto Ld
        L9:
            int r0 = r7.S0()
        Ld:
            r1 = 8
            if (r10 != r1) goto L1a
            if (r8 >= r9) goto L16
            int r2 = r9 + 1
            goto L1c
        L16:
            int r2 = r8 + 1
            r3 = r9
            goto L1d
        L1a:
            int r2 = r8 + r9
        L1c:
            r3 = r8
        L1d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r7.B
            r4.c(r3)
            r5 = 1
            if (r10 == r5) goto L36
            r6 = 2
            if (r10 == r6) goto L32
            if (r10 == r1) goto L2b
            goto L39
        L2b:
            r4.e(r8, r5)
            r4.d(r9, r5)
            goto L39
        L32:
            r4.e(r8, r9)
            goto L39
        L36:
            r4.d(r8, r9)
        L39:
            if (r2 > r0) goto L3c
            return
        L3c:
            boolean r8 = r7.x
            if (r8 == 0) goto L45
            int r8 = r7.S0()
            goto L49
        L45:
            int r8 = r7.T0()
        L49:
            if (r3 > r8) goto L4e
            r7.t0()
        L4e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.W0(int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x006d  */
    @Override // androidx.recyclerview.widget.RecyclerView.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View X(android.view.View r9, int r10, androidx.recyclerview.widget.RecyclerView.s r11, androidx.recyclerview.widget.RecyclerView.w r12) {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.X(android.view.View, int, androidx.recyclerview.widget.RecyclerView$s, androidx.recyclerview.widget.RecyclerView$w):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View X0() {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.X0():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void Y(AccessibilityEvent accessibilityEvent) {
        super.Y(accessibilityEvent);
        if (A() > 0) {
            View viewP0 = P0(false);
            View viewO0 = O0(false);
            if (viewP0 == null || viewO0 == null) {
                return;
            }
            int iK = RecyclerView.m.K(viewP0);
            int iK2 = RecyclerView.m.K(viewO0);
            if (iK < iK2) {
                accessibilityEvent.setFromIndex(iK);
                accessibilityEvent.setToIndex(iK2);
            } else {
                accessibilityEvent.setFromIndex(iK2);
                accessibilityEvent.setToIndex(iK);
            }
        }
    }

    public final boolean Y0() {
        return E() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void Z(RecyclerView.s sVar, RecyclerView.w wVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.Z(sVar, wVar, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.n("androidx.recyclerview.widget.StaggeredGridLayoutManager");
    }

    public final void Z0(View view, int i, int i2, boolean z) {
        Rect rect = this.G;
        g(view, rect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int iL1 = l1(i, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int iL12 = l1(i2, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect.bottom);
        if (C0(view, iL1, iL12, layoutParams)) {
            view.measure(iL1, iL12);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.v.b
    public final PointF a(int i) {
        int iI0 = I0(i);
        PointF pointF = new PointF();
        if (iI0 == 0) {
            return null;
        }
        if (this.t == 0) {
            pointF.x = iI0;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iI0;
        }
        return pointF;
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x040b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a1(androidx.recyclerview.widget.RecyclerView.s r17, androidx.recyclerview.widget.RecyclerView.w r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 1062
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.a1(androidx.recyclerview.widget.RecyclerView$s, androidx.recyclerview.widget.RecyclerView$w, boolean):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void b0(RecyclerView.s sVar, RecyclerView.w wVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            a0(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.t == 0) {
            c cVar = layoutParams2.e;
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(cVar != null ? cVar.e : -1, 1, -1, -1, false, false));
        } else {
            c cVar2 = layoutParams2.e;
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(-1, -1, cVar2 != null ? cVar2.e : -1, 1, false, false));
        }
    }

    public final boolean b1(int i) {
        if (this.t == 0) {
            return (i == -1) != this.x;
        }
        return ((i == -1) == this.x) == Y0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void c0(int i, int i2) {
        W0(i, i2, 1);
    }

    public final void c1(int i, RecyclerView.w wVar) {
        int iS0;
        int i2;
        if (i > 0) {
            iS0 = T0();
            i2 = 1;
        } else {
            iS0 = S0();
            i2 = -1;
        }
        o oVar = this.v;
        oVar.a = true;
        j1(iS0, wVar);
        i1(i2);
        oVar.c = iS0 + oVar.d;
        oVar.b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void d0() {
        this.B.a();
        t0();
    }

    public final void d1(RecyclerView.s sVar, o oVar) {
        if (!oVar.a || oVar.i) {
            return;
        }
        if (oVar.b == 0) {
            if (oVar.e == -1) {
                e1(oVar.g, sVar);
                return;
            } else {
                f1(oVar.f, sVar);
                return;
            }
        }
        int i = 1;
        if (oVar.e == -1) {
            int i2 = oVar.f;
            int i3 = this.q[0].i(i2);
            while (i < this.p) {
                int i4 = this.q[i].i(i2);
                if (i4 > i3) {
                    i3 = i4;
                }
                i++;
            }
            int i5 = i2 - i3;
            e1(i5 < 0 ? oVar.g : oVar.g - Math.min(i5, oVar.b), sVar);
            return;
        }
        int i6 = oVar.g;
        int iF = this.q[0].f(i6);
        while (i < this.p) {
            int iF2 = this.q[i].f(i6);
            if (iF2 < iF) {
                iF = iF2;
            }
            i++;
        }
        int i7 = iF - oVar.g;
        f1(i7 < 0 ? oVar.f : Math.min(i7, oVar.b) + oVar.f, sVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void e0(int i, int i2) {
        W0(i, i2, 8);
    }

    public final void e1(int i, RecyclerView.s sVar) {
        for (int iA = A() - 1; iA >= 0; iA--) {
            View viewZ = z(iA);
            if (this.r.e(viewZ) < i || this.r.o(viewZ) < i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) viewZ.getLayoutParams();
            layoutParams.getClass();
            if (layoutParams.e.a.size() == 1) {
                return;
            }
            c cVar = layoutParams.e;
            ArrayList<View> arrayList = cVar.a;
            int size = arrayList.size();
            View viewRemove = arrayList.remove(size - 1);
            LayoutParams layoutParamsH = c.h(viewRemove);
            layoutParamsH.e = null;
            if (layoutParamsH.c() || layoutParamsH.b()) {
                cVar.d -= StaggeredGridLayoutManager.this.r.c(viewRemove);
            }
            if (size == 1) {
                cVar.b = Integer.MIN_VALUE;
            }
            cVar.c = Integer.MIN_VALUE;
            q0(viewZ);
            sVar.h(viewZ);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void f(String str) {
        if (this.F == null) {
            super.f(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void f0(int i, int i2) {
        W0(i, i2, 2);
    }

    public final void f1(int i, RecyclerView.s sVar) {
        while (A() > 0) {
            View viewZ = z(0);
            if (this.r.b(viewZ) > i || this.r.n(viewZ) > i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) viewZ.getLayoutParams();
            layoutParams.getClass();
            if (layoutParams.e.a.size() == 1) {
                return;
            }
            c cVar = layoutParams.e;
            ArrayList<View> arrayList = cVar.a;
            View viewRemove = arrayList.remove(0);
            LayoutParams layoutParamsH = c.h(viewRemove);
            layoutParamsH.e = null;
            if (arrayList.size() == 0) {
                cVar.c = Integer.MIN_VALUE;
            }
            if (layoutParamsH.c() || layoutParamsH.b()) {
                cVar.d -= StaggeredGridLayoutManager.this.r.c(viewRemove);
            }
            cVar.b = Integer.MIN_VALUE;
            q0(viewZ);
            sVar.h(viewZ);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void g0(int i, int i2) {
        W0(i, i2, 4);
    }

    public final void g1() {
        if (this.t == 1 || !Y0()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean h() {
        return this.t == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void h0(RecyclerView.s sVar, RecyclerView.w wVar) {
        a1(sVar, wVar, true);
    }

    public final int h1(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (A() == 0 || i == 0) {
            return 0;
        }
        c1(i, wVar);
        o oVar = this.v;
        int iN0 = N0(sVar, oVar, wVar);
        if (oVar.b >= iN0) {
            i = i < 0 ? -iN0 : iN0;
        }
        this.r.p(-i);
        this.D = this.x;
        oVar.b = 0;
        d1(sVar, oVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean i() {
        return this.t == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void i0(RecyclerView.w wVar) {
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.F = null;
        this.H.a();
    }

    public final void i1(int i) {
        o oVar = this.v;
        oVar.e = i;
        oVar.d = this.x != (i == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean j(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void j0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.F = savedState;
            if (this.z != -1) {
                savedState.h = null;
                savedState.g = 0;
                savedState.e = -1;
                savedState.f = -1;
                savedState.h = null;
                savedState.g = 0;
                savedState.i = 0;
                savedState.j = null;
                savedState.k = null;
            }
            t0();
        }
    }

    public final void j1(int i, RecyclerView.w wVar) {
        int iL;
        int iL2;
        int i2;
        o oVar = this.v;
        boolean z = false;
        oVar.b = 0;
        oVar.c = i;
        RecyclerView.v vVar = this.e;
        if (!(vVar != null && vVar.e) || (i2 = wVar.a) == -1) {
            iL = 0;
            iL2 = 0;
        } else {
            if (this.x == (i2 < i)) {
                iL = this.r.l();
                iL2 = 0;
            } else {
                iL2 = this.r.l();
                iL = 0;
            }
        }
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && recyclerView.l) {
            oVar.f = this.r.k() - iL2;
            oVar.g = this.r.g() + iL;
        } else {
            oVar.g = this.r.f() + iL;
            oVar.f = -iL2;
        }
        oVar.h = false;
        oVar.a = true;
        if (this.r.i() == 0 && this.r.f() == 0) {
            z = true;
        }
        oVar.i = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final Parcelable k0() {
        int i;
        int iK;
        int[] iArr;
        SavedState savedState = this.F;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.l = this.w;
        savedState2.m = this.D;
        savedState2.n = this.E;
        LazySpanLookup lazySpanLookup = this.B;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.a) == null) {
            savedState2.i = 0;
        } else {
            savedState2.j = iArr;
            savedState2.i = iArr.length;
            savedState2.k = lazySpanLookup.b;
        }
        if (A() > 0) {
            savedState2.e = this.D ? T0() : S0();
            View viewO0 = this.x ? O0(true) : P0(true);
            savedState2.f = viewO0 != null ? RecyclerView.m.K(viewO0) : -1;
            int i2 = this.p;
            savedState2.g = i2;
            savedState2.h = new int[i2];
            for (int i3 = 0; i3 < this.p; i3++) {
                if (this.D) {
                    i = this.q[i3].f(Integer.MIN_VALUE);
                    if (i != Integer.MIN_VALUE) {
                        iK = this.r.g();
                        i -= iK;
                    }
                } else {
                    i = this.q[i3].i(Integer.MIN_VALUE);
                    if (i != Integer.MIN_VALUE) {
                        iK = this.r.k();
                        i -= iK;
                    }
                }
                savedState2.h[i3] = i;
            }
        } else {
            savedState2.e = -1;
            savedState2.f = -1;
            savedState2.g = 0;
        }
        return savedState2;
    }

    public final void k1(c cVar, int i, int i2) {
        int i3 = cVar.d;
        int i4 = cVar.e;
        if (i != -1) {
            int i5 = cVar.c;
            if (i5 == Integer.MIN_VALUE) {
                cVar.a();
                i5 = cVar.c;
            }
            if (i5 - i3 >= i2) {
                this.y.set(i4, false);
                return;
            }
            return;
        }
        int i6 = cVar.b;
        if (i6 == Integer.MIN_VALUE) {
            View view = cVar.a.get(0);
            LayoutParams layoutParamsH = c.h(view);
            cVar.b = StaggeredGridLayoutManager.this.r.e(view);
            layoutParamsH.getClass();
            i6 = cVar.b;
        }
        if (i6 + i3 <= i2) {
            this.y.set(i4, false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void l(int i, int i2, RecyclerView.w wVar, RecyclerView.m.c cVar) {
        o oVar;
        int iF;
        int i3;
        if (this.t != 0) {
            i = i2;
        }
        if (A() == 0 || i == 0) {
            return;
        }
        c1(i, wVar);
        int[] iArr = this.f20J;
        if (iArr == null || iArr.length < this.p) {
            this.f20J = new int[this.p];
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = this.p;
            oVar = this.v;
            if (i4 >= i6) {
                break;
            }
            if (oVar.d == -1) {
                iF = oVar.f;
                i3 = this.q[i4].i(iF);
            } else {
                iF = this.q[i4].f(oVar.g);
                i3 = oVar.g;
            }
            int i7 = iF - i3;
            if (i7 >= 0) {
                this.f20J[i5] = i7;
                i5++;
            }
            i4++;
        }
        Arrays.sort(this.f20J, 0, i5);
        for (int i8 = 0; i8 < i5; i8++) {
            int i9 = oVar.c;
            if (!(i9 >= 0 && i9 < wVar.b())) {
                return;
            }
            ((k.b) cVar).a(oVar.c, this.f20J[i8]);
            oVar.c += oVar.d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void l0(int i) {
        if (i == 0) {
            J0();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int n(RecyclerView.w wVar) {
        return K0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int o(RecyclerView.w wVar) {
        return L0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int p(RecyclerView.w wVar) {
        return M0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int q(RecyclerView.w wVar) {
        return K0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int r(RecyclerView.w wVar) {
        return L0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int s(RecyclerView.w wVar) {
        return M0(wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int u0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        return h1(i, sVar, wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final RecyclerView.LayoutParams v() {
        return this.t == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void v0(int i) {
        SavedState savedState = this.F;
        if (savedState != null && savedState.e != i) {
            savedState.h = null;
            savedState.g = 0;
            savedState.e = -1;
            savedState.f = -1;
        }
        this.z = i;
        this.A = Integer.MIN_VALUE;
        t0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final RecyclerView.LayoutParams w(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int w0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        return h1(i, sVar, wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final RecyclerView.LayoutParams x(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void z0(Rect rect, int i, int i2) {
        int iK;
        int iK2;
        int I = I() + H();
        int iG = G() + J();
        if (this.t == 1) {
            int iHeight = rect.height() + iG;
            RecyclerView recyclerView = this.b;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            iK2 = RecyclerView.m.k(i2, iHeight, om3.d.c(recyclerView));
            iK = RecyclerView.m.k(i, (this.u * this.p) + I, om3.d.d(this.b));
        } else {
            int iWidth = rect.width() + I;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            iK = RecyclerView.m.k(i, iWidth, om3.d.d(recyclerView2));
            iK2 = RecyclerView.m.k(i2, (this.u * this.p) + iG, om3.d.c(this.b));
        }
        this.b.setMeasuredDimension(iK, iK2);
    }

    public static class LazySpanLookup {
        public int[] a;
        public List<FullSpanItem> b;

        public final void a() {
            int[] iArr = this.a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.b = null;
        }

        public final void b(int i) {
            int[] iArr = this.a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int length = iArr.length;
                while (length <= i) {
                    length *= 2;
                }
                int[] iArr3 = new int[length];
                this.a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x005e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int c(int r6) {
            /*
                r5 = this;
                int[] r0 = r5.a
                r1 = -1
                if (r0 != 0) goto L6
                return r1
            L6:
                int r0 = r0.length
                if (r6 < r0) goto La
                return r1
            La:
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r5.b
                if (r0 != 0) goto Lf
                goto L5e
            Lf:
                r2 = 0
                if (r0 != 0) goto L13
                goto L2b
            L13:
                int r0 = r0.size()
                int r0 = r0 + r1
            L18:
                if (r0 < 0) goto L2b
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r3 = r5.b
                java.lang.Object r3 = r3.get(r0)
                androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r3
                int r4 = r3.e
                if (r4 != r6) goto L28
                r2 = r3
                goto L2b
            L28:
                int r0 = r0 + (-1)
                goto L18
            L2b:
                if (r2 == 0) goto L32
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r5.b
                r0.remove(r2)
            L32:
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r5.b
                int r0 = r0.size()
                r2 = 0
            L39:
                if (r2 >= r0) goto L4b
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r3 = r5.b
                java.lang.Object r3 = r3.get(r2)
                androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r3
                int r3 = r3.e
                if (r3 < r6) goto L48
                goto L4c
            L48:
                int r2 = r2 + 1
                goto L39
            L4b:
                r2 = r1
            L4c:
                if (r2 == r1) goto L5e
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r5.b
                java.lang.Object r0 = r0.get(r2)
                androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r0
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r3 = r5.b
                r3.remove(r2)
                int r0 = r0.e
                goto L5f
            L5e:
                r0 = r1
            L5f:
                if (r0 != r1) goto L6b
                int[] r0 = r5.a
                int r2 = r0.length
                java.util.Arrays.fill(r0, r6, r2, r1)
                int[] r5 = r5.a
                int r5 = r5.length
                return r5
            L6b:
                int r0 = r0 + 1
                int[] r2 = r5.a
                int r2 = r2.length
                int r0 = java.lang.Math.min(r0, r2)
                int[] r5 = r5.a
                java.util.Arrays.fill(r5, r6, r0, r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.c(int):int");
        }

        public final void d(int i, int i2) {
            int[] iArr = this.a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            b(i3);
            int[] iArr2 = this.a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.a, i, i3, -1);
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i4 = fullSpanItem.e;
                if (i4 >= i) {
                    fullSpanItem.e = i4 + i2;
                }
            }
        }

        public final void e(int i, int i2) {
            int[] iArr = this.a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            b(i3);
            int[] iArr2 = this.a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i4 = fullSpanItem.e;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.b.remove(size);
                    } else {
                        fullSpanItem.e = i4 - i2;
                    }
                }
            }
        }

        @SuppressLint({"BanParcelableUsage"})
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();
            public int e;
            public int f;
            public int[] g;
            public boolean h;

            public class a implements Parcelable.Creator<FullSpanItem> {
                @Override // android.os.Parcelable.Creator
                public final FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                public final FullSpanItem[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            }

            public FullSpanItem(Parcel parcel) {
                this.e = parcel.readInt();
                this.f = parcel.readInt();
                this.h = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.g = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public final String toString() {
                return "FullSpanItem{mPosition=" + this.e + ", mGapDir=" + this.f + ", mHasUnwantedGapAfter=" + this.h + ", mGapPerSpan=" + Arrays.toString(this.g) + '}';
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.e);
                parcel.writeInt(this.f);
                parcel.writeInt(this.h ? 1 : 0);
                int[] iArr = this.g;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.g);
                }
            }

            public FullSpanItem() {
            }
        }
    }
}
