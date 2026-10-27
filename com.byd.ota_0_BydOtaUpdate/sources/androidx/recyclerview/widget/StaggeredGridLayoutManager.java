package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.f.m.b0.c;
import b.n.d.f;
import b.n.d.h;
import b.n.d.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o {
    public BitSet B;
    public boolean G;
    public boolean H;
    public SavedState I;
    public int J;
    public int[] O;
    public d[] t;
    public h u;
    public h v;
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
        public int[] E3;
        public List<LazySpanLookup.FullSpanItem> F3;
        public boolean G3;
        public boolean H3;
        public boolean I3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f652c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f653d;
        public int s;
        public int[] x;
        public int y;

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

        public void a() {
            this.x = null;
            this.s = 0;
            this.y = 0;
            this.E3 = null;
            this.F3 = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f652c);
            parcel.writeInt(this.f653d);
            parcel.writeInt(this.s);
            if (this.s > 0) {
                parcel.writeIntArray(this.x);
            }
            parcel.writeInt(this.y);
            if (this.y > 0) {
                parcel.writeIntArray(this.E3);
            }
            parcel.writeInt(this.G3 ? 1 : 0);
            parcel.writeInt(this.H3 ? 1 : 0);
            parcel.writeInt(this.I3 ? 1 : 0);
            parcel.writeList(this.F3);
        }

        public SavedState(Parcel parcel) {
            this.f652c = parcel.readInt();
            this.f653d = parcel.readInt();
            int i2 = parcel.readInt();
            this.s = i2;
            if (i2 > 0) {
                int[] iArr = new int[i2];
                this.x = iArr;
                parcel.readIntArray(iArr);
            }
            int i3 = parcel.readInt();
            this.y = i3;
            if (i3 > 0) {
                int[] iArr2 = new int[i3];
                this.E3 = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.G3 = parcel.readInt() == 1;
            this.H3 = parcel.readInt() == 1;
            this.I3 = parcel.readInt() == 1;
            this.F3 = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.s = savedState.s;
            this.f652c = savedState.f652c;
            this.f653d = savedState.f653d;
            this.x = savedState.x;
            this.y = savedState.y;
            this.E3 = savedState.E3;
            this.G3 = savedState.G3;
            this.H3 = savedState.H3;
            this.I3 = savedState.I3;
            this.F3 = savedState.F3;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.M1();
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f656b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f657c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f658d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f659e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f660f;

        public b() {
            c();
        }

        public void a() {
            this.f656b = this.f657c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        public void b(int i2) {
            if (this.f657c) {
                this.f656b = StaggeredGridLayoutManager.this.u.i() - i2;
            } else {
                this.f656b = StaggeredGridLayoutManager.this.u.m() + i2;
            }
        }

        public void c() {
            this.f655a = -1;
            this.f656b = Integer.MIN_VALUE;
            this.f657c = false;
            this.f658d = false;
            this.f659e = false;
            int[] iArr = this.f660f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f660f;
            if (iArr == null || iArr.length < length) {
                this.f660f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.f660f[i2] = dVarArr[i2].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d f662e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f663f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f662e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f668e;
        }

        public boolean f() {
            return this.f663f;
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
        public ArrayList<View> f664a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f665b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f666c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f667d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f668e;

        public d(int i2) {
            this.f668e = i2;
        }

        public void a(View view) {
            c cVarN = n(view);
            cVarN.f662e = this;
            this.f664a.add(view);
            this.f666c = Integer.MIN_VALUE;
            if (this.f664a.size() == 1) {
                this.f665b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f667d += StaggeredGridLayoutManager.this.u.e(view);
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
                    this.f666c = iL;
                    this.f665b = iL;
                }
            }
        }

        public void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f664a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f666c = StaggeredGridLayoutManager.this.u.d(view);
            if (cVarN.f663f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f651d == 1) {
                this.f666c += fullSpanItemF.a(this.f668e);
            }
        }

        public void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f664a.get(0);
            c cVarN = n(view);
            this.f665b = StaggeredGridLayoutManager.this.u.g(view);
            if (cVarN.f663f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f651d == -1) {
                this.f665b -= fullSpanItemF.a(this.f668e);
            }
        }

        public void e() {
            this.f664a.clear();
            q();
            this.f667d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.z ? i(this.f664a.size() - 1, -1, true) : i(0, this.f664a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.z ? i(0, this.f664a.size(), true) : i(this.f664a.size() - 1, -1, true);
        }

        public int h(int i2, int i3, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.u.m();
            int i4 = StaggeredGridLayoutManager.this.u.i();
            int i5 = i3 > i2 ? 1 : -1;
            while (i2 != i3) {
                View view = this.f664a.get(i2);
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
                            return StaggeredGridLayoutManager.this.f0(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.f0(view);
                        }
                        if (iG < iM || iD > i4) {
                            return StaggeredGridLayoutManager.this.f0(view);
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
            return this.f667d;
        }

        public int k() {
            int i2 = this.f666c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f666c;
        }

        public int l(int i2) {
            int i3 = this.f666c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f664a.size() == 0) {
                return i2;
            }
            c();
            return this.f666c;
        }

        public View m(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.f664a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f664a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.f0(view2) >= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.f0(view2) <= i2) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f664a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = this.f664a.get(i4);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.f0(view3) <= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.f0(view3) >= i2) || !view3.hasFocusable()) {
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
            int i2 = this.f665b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.f665b;
        }

        public int p(int i2) {
            int i3 = this.f665b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f664a.size() == 0) {
                return i2;
            }
            d();
            return this.f665b;
        }

        public void q() {
            this.f665b = Integer.MIN_VALUE;
            this.f666c = Integer.MIN_VALUE;
        }

        public void r(int i2) {
            int i3 = this.f665b;
            if (i3 != Integer.MIN_VALUE) {
                this.f665b = i3 + i2;
            }
            int i4 = this.f666c;
            if (i4 != Integer.MIN_VALUE) {
                this.f666c = i4 + i2;
            }
        }

        public void s() {
            int size = this.f664a.size();
            View viewRemove = this.f664a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f662e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f667d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            if (size == 1) {
                this.f665b = Integer.MIN_VALUE;
            }
            this.f666c = Integer.MIN_VALUE;
        }

        public void t() {
            View viewRemove = this.f664a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f662e = null;
            if (this.f664a.size() == 0) {
                this.f666c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f667d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            this.f665b = Integer.MIN_VALUE;
        }

        public void u(View view) {
            c cVarN = n(view);
            cVarN.f662e = this;
            this.f664a.add(0, view);
            this.f665b = Integer.MIN_VALUE;
            if (this.f664a.size() == 1) {
                this.f666c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f667d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        public void v(int i2) {
            this.f665b = i2;
            this.f666c = i2;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.o.d dVarG0 = RecyclerView.o.g0(context, attributeSet, i2, i3);
        B2(dVarG0.f615a);
        D2(dVarG0.f616b);
        C2(dVarG0.f617c);
        this.y = new f();
        U1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void A0(int i2) {
        super.A0(i2);
        for (int i3 = 0; i3 < this.s; i3++) {
            this.t[i3].r(i2);
        }
    }

    public final void A2(int i2) {
        f fVar = this.y;
        fVar.f2095e = i2;
        fVar.f2094d = this.A != (i2 == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void B0(int i2) {
        super.B0(i2);
        for (int i3 = 0; i3 < this.s; i3++) {
            this.t[i3].r(i2);
        }
    }

    public void B2(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        f(null);
        if (i2 == this.w) {
            return;
        }
        this.w = i2;
        h hVar = this.u;
        this.u = this.v;
        this.v = hVar;
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p C() {
        return this.w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    public void C2(boolean z) {
        f(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.G3 != z) {
            savedState.G3 = z;
        }
        this.z = z;
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    public void D2(int i2) {
        f(null);
        if (i2 != this.s) {
            m2();
            this.s = i2;
            this.B = new BitSet(this.s);
            this.t = new d[this.s];
            for (int i3 = 0; i3 < this.s; i3++) {
                this.t[i3] = new d(i3);
            }
            q1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public final void E2(int i2, int i3) {
        for (int i4 = 0; i4 < this.s; i4++) {
            if (!this.t[i4].f664a.isEmpty()) {
                K2(this.t[i4], i2, i3);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean F1() {
        return this.I == null;
    }

    public final boolean F2(RecyclerView.z zVar, b bVar) {
        bVar.f655a = this.G ? a2(zVar.b()) : W1(zVar.b());
        bVar.f656b = Integer.MIN_VALUE;
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void G0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.G0(recyclerView, vVar);
        l1(this.P);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].e();
        }
        recyclerView.requestLayout();
    }

    public final void G1(View view) {
        for (int i2 = this.s - 1; i2 >= 0; i2--) {
            this.t[i2].a(view);
        }
    }

    public boolean G2(RecyclerView.z zVar, b bVar) {
        int i2;
        if (!zVar.e() && (i2 = this.C) != -1) {
            if (i2 >= 0 && i2 < zVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f652c == -1 || savedState.s < 1) {
                    View viewB = B(this.C);
                    if (viewB != null) {
                        bVar.f655a = this.A ? e2() : d2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f657c) {
                                bVar.f656b = (this.u.i() - this.D) - this.u.d(viewB);
                            } else {
                                bVar.f656b = (this.u.m() + this.D) - this.u.g(viewB);
                            }
                            return true;
                        }
                        if (this.u.e(viewB) > this.u.n()) {
                            bVar.f656b = bVar.f657c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int iG = this.u.g(viewB) - this.u.m();
                        if (iG < 0) {
                            bVar.f656b = -iG;
                            return true;
                        }
                        int i3 = this.u.i() - this.u.d(viewB);
                        if (i3 < 0) {
                            bVar.f656b = i3;
                            return true;
                        }
                        bVar.f656b = Integer.MIN_VALUE;
                    } else {
                        int i4 = this.C;
                        bVar.f655a = i4;
                        int i5 = this.D;
                        if (i5 == Integer.MIN_VALUE) {
                            bVar.f657c = L1(i4) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i5);
                        }
                        bVar.f658d = true;
                    }
                } else {
                    bVar.f656b = Integer.MIN_VALUE;
                    bVar.f655a = this.C;
                }
                return true;
            }
            this.C = -1;
            this.D = Integer.MIN_VALUE;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View H0(View view, int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewA;
        View viewM;
        if (I() == 0 || (viewA = A(view)) == null) {
            return null;
        }
        y2();
        int iR1 = R1(i2);
        if (iR1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewA.getLayoutParams();
        boolean z = cVar.f663f;
        d dVar = cVar.f662e;
        int iE2 = iR1 == 1 ? e2() : d2();
        I2(iE2, zVar);
        A2(iR1);
        f fVar = this.y;
        fVar.f2093c = fVar.f2094d + iE2;
        fVar.f2092b = (int) (this.u.n() * 0.33333334f);
        f fVar2 = this.y;
        fVar2.f2098h = true;
        fVar2.f2091a = false;
        V1(vVar, fVar2, zVar);
        this.G = this.A;
        if (!z && (viewM = dVar.m(iE2, iR1)) != null && viewM != viewA) {
            return viewM;
        }
        if (r2(iR1)) {
            for (int i3 = this.s - 1; i3 >= 0; i3--) {
                View viewM2 = this.t[i3].m(iE2, iR1);
                if (viewM2 != null && viewM2 != viewA) {
                    return viewM2;
                }
            }
        } else {
            for (int i4 = 0; i4 < this.s; i4++) {
                View viewM3 = this.t[i4].m(iE2, iR1);
                if (viewM3 != null && viewM3 != viewA) {
                    return viewM3;
                }
            }
        }
        boolean z2 = (this.z ^ true) == (iR1 == -1);
        if (!z) {
            View viewB = B(z2 ? dVar.f() : dVar.g());
            if (viewB != null && viewB != viewA) {
                return viewB;
            }
        }
        if (r2(iR1)) {
            for (int i5 = this.s - 1; i5 >= 0; i5--) {
                if (i5 != dVar.f668e) {
                    View viewB2 = B(z2 ? this.t[i5].f() : this.t[i5].g());
                    if (viewB2 != null && viewB2 != viewA) {
                        return viewB2;
                    }
                }
            }
        } else {
            for (int i6 = 0; i6 < this.s; i6++) {
                View viewB3 = B(z2 ? this.t[i6].f() : this.t[i6].g());
                if (viewB3 != null && viewB3 != viewA) {
                    return viewB3;
                }
            }
        }
        return null;
    }

    public final void H1(b bVar) {
        SavedState savedState = this.I;
        int i2 = savedState.s;
        if (i2 > 0) {
            if (i2 == this.s) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].e();
                    SavedState savedState2 = this.I;
                    int i4 = savedState2.x[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += savedState2.H3 ? this.u.i() : this.u.m();
                    }
                    this.t[i3].v(i4);
                }
            } else {
                savedState.a();
                SavedState savedState3 = this.I;
                savedState3.f652c = savedState3.f653d;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.I3;
        C2(savedState4.G3);
        y2();
        SavedState savedState5 = this.I;
        int i5 = savedState5.f652c;
        if (i5 != -1) {
            this.C = i5;
            bVar.f657c = savedState5.H3;
        } else {
            bVar.f657c = this.A;
        }
        if (savedState5.y > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f648a = savedState5.E3;
            lazySpanLookup.f649b = savedState5.F3;
        }
    }

    public void H2(RecyclerView.z zVar, b bVar) {
        if (G2(zVar, bVar) || F2(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f655a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(AccessibilityEvent accessibilityEvent) {
        super.I0(accessibilityEvent);
        if (I() > 0) {
            View viewY1 = Y1(false);
            View viewX1 = X1(false);
            if (viewY1 == null || viewX1 == null) {
                return;
            }
            int iF0 = f0(viewY1);
            int iF02 = f0(viewX1);
            if (iF0 < iF02) {
                accessibilityEvent.setFromIndex(iF0);
                accessibilityEvent.setToIndex(iF02);
            } else {
                accessibilityEvent.setFromIndex(iF02);
                accessibilityEvent.setToIndex(iF0);
            }
        }
    }

    public boolean I1() {
        int iL = this.t[0].l(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.s; i2++) {
            if (this.t[i2].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    public final void I2(int i2, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        f fVar = this.y;
        boolean z = false;
        fVar.f2092b = 0;
        fVar.f2093c = i2;
        if (!v0() || (iC = zVar.c()) == -1) {
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
        if (L()) {
            this.y.f2096f = this.u.m() - iN2;
            this.y.f2097g = this.u.i() + iN;
        } else {
            this.y.f2097g = this.u.h() + iN;
            this.y.f2096f = -iN2;
        }
        f fVar2 = this.y;
        fVar2.f2098h = false;
        fVar2.f2091a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        fVar2.f2099i = z;
    }

    public boolean J1() {
        int iP = this.t[0].p(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.s; i2++) {
            if (this.t[i2].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    public void J2(int i2) {
        this.x = i2 / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i2, this.v.k());
    }

    public final void K1(View view, c cVar, f fVar) {
        if (fVar.f2095e == 1) {
            if (cVar.f663f) {
                G1(view);
                return;
            } else {
                cVar.f662e.a(view);
                return;
            }
        }
        if (cVar.f663f) {
            t2(view);
        } else {
            cVar.f662e.u(view);
        }
    }

    public final void K2(d dVar, int i2, int i3) {
        int iJ = dVar.j();
        if (i2 == -1) {
            if (dVar.o() + iJ <= i3) {
                this.B.set(dVar.f668e, false);
            }
        } else if (dVar.k() - iJ >= i3) {
            this.B.set(dVar.f668e, false);
        }
    }

    public final int L1(int i2) {
        if (I() == 0) {
            return this.A ? 1 : -1;
        }
        return (i2 < d2()) != this.A ? -1 : 1;
    }

    public final int L2(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int M(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.w == 1 ? this.s : super.M(vVar, zVar);
    }

    public boolean M1() {
        int iD2;
        int iE2;
        if (I() == 0 || this.F == 0 || !p0()) {
            return false;
        }
        if (this.A) {
            iD2 = e2();
            iE2 = d2();
        } else {
            iD2 = d2();
            iE2 = e2();
        }
        if (iD2 == 0 && l2() != null) {
            this.E.b();
            r1();
            q1();
            return true;
        }
        if (!this.M) {
            return false;
        }
        int i2 = this.A ? -1 : 1;
        int i3 = iE2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.E.e(iD2, i3, i2, true);
        if (fullSpanItemE == null) {
            this.M = false;
            this.E.d(i3);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iD2, fullSpanItemE.f650c, i2 * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f650c);
        } else {
            this.E.d(fullSpanItemE2.f650c + 1);
        }
        r1();
        q1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void N0(RecyclerView.v vVar, RecyclerView.z zVar, View view, b.f.m.b0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.M0(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.w == 0) {
            cVar.W(c.C0030c.a(cVar2.e(), cVar2.f663f ? this.s : 1, -1, -1, false, false));
        } else {
            cVar.W(c.C0030c.a(-1, -1, cVar2.e(), cVar2.f663f ? this.s : 1, false, false));
        }
    }

    public final boolean N1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.u.i()) {
                ArrayList<View> arrayList = dVar.f664a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f663f;
            }
        } else if (dVar.o() > this.u.m()) {
            return !dVar.n(dVar.f664a.get(0)).f663f;
        }
        return false;
    }

    public final int O1(RecyclerView.z zVar) {
        if (I() == 0) {
            return 0;
        }
        return j.a(zVar, this.u, Y1(!this.N), X1(!this.N), this, this.N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView recyclerView, int i2, int i3) {
        k2(i2, i3, 1);
    }

    public final int P1(RecyclerView.z zVar) {
        if (I() == 0) {
            return 0;
        }
        return j.b(zVar, this.u, Y1(!this.N), X1(!this.N), this, this.N, this.A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Q0(RecyclerView recyclerView) {
        this.E.b();
        q1();
    }

    public final int Q1(RecyclerView.z zVar) {
        if (I() == 0) {
            return 0;
        }
        return j.c(zVar, this.u, Y1(!this.N), X1(!this.N), this, this.N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i2, int i3, int i4) {
        k2(i2, i3, 8);
    }

    public final int R1(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && n2()) ? -1 : 1 : (this.w != 1 && n2()) ? 1 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(RecyclerView recyclerView, int i2, int i3) {
        k2(i2, i3, 2);
    }

    public final LazySpanLookup.FullSpanItem S1(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.s = new int[this.s];
        for (int i3 = 0; i3 < this.s; i3++) {
            fullSpanItem.s[i3] = i2 - this.t[i3].l(i2);
        }
        return fullSpanItem;
    }

    public final LazySpanLookup.FullSpanItem T1(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.s = new int[this.s];
        for (int i3 = 0; i3 < this.s; i3++) {
            fullSpanItem.s[i3] = this.t[i3].p(i2) - i2;
        }
        return fullSpanItem;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        k2(i2, i3, 4);
    }

    public final void U1() {
        this.u = h.b(this, this.w);
        this.v = h.b(this, 1 - this.w);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void V0(RecyclerView.v vVar, RecyclerView.z zVar) {
        q2(vVar, zVar, true);
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
    public final int V1(RecyclerView.v vVar, f fVar, RecyclerView.z zVar) {
        d dVarJ2;
        int iE;
        int i2;
        int iE2;
        int iE3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        int i3 = this.y.f2099i ? fVar.f2095e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : fVar.f2095e == 1 ? fVar.f2097g + fVar.f2092b : fVar.f2096f - fVar.f2092b;
        E2(fVar.f2095e, i3);
        int i4 = this.A ? this.u.i() : this.u.m();
        ?? r0 = false;
        while (fVar.a(zVar) && (this.y.f2099i || !this.B.isEmpty())) {
            View viewB = fVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarJ2 = cVar.f663f ? this.t[r9] : j2(fVar);
                this.E.n(iA, dVarJ2);
            } else {
                dVarJ2 = this.t[iG];
            }
            d dVar = dVarJ2;
            cVar.f662e = dVar;
            if (fVar.f2095e == 1) {
                c(viewB);
            } else {
                d(viewB, r9);
            }
            p2(viewB, cVar, r9);
            if (fVar.f2095e == 1) {
                int iF2 = cVar.f663f ? f2(i4) : dVar.l(i4);
                int iE4 = this.u.e(viewB) + iF2;
                if (r3 != 0 && cVar.f663f) {
                    LazySpanLookup.FullSpanItem fullSpanItemS1 = S1(iF2);
                    fullSpanItemS1.f651d = -1;
                    fullSpanItemS1.f650c = iA;
                    this.E.a(fullSpanItemS1);
                }
                i2 = iE4;
                iE = iF2;
            } else {
                int iI2 = cVar.f663f ? i2(i4) : dVar.p(i4);
                iE = iI2 - this.u.e(viewB);
                if (r3 != 0 && cVar.f663f) {
                    LazySpanLookup.FullSpanItem fullSpanItemT1 = T1(iI2);
                    fullSpanItemT1.f651d = 1;
                    fullSpanItemT1.f650c = iA;
                    this.E.a(fullSpanItemT1);
                }
                i2 = iI2;
            }
            if (cVar.f663f && fVar.f2094d == -1) {
                if (r3 != 0) {
                    this.M = true;
                } else {
                    if (!(fVar.f2095e == 1 ? I1() : J1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.x = true;
                        }
                        this.M = true;
                    }
                }
            }
            K1(viewB, cVar, fVar);
            if (n2() && this.w == 1) {
                int i5 = cVar.f663f ? this.v.i() : this.v.i() - (((this.s - 1) - dVar.f668e) * this.x);
                iE3 = i5;
                iE2 = i5 - this.v.e(viewB);
            } else {
                int iM = cVar.f663f ? this.v.m() : (dVar.f668e * this.x) + this.v.m();
                iE2 = iM;
                iE3 = this.v.e(viewB) + iM;
            }
            if (this.w == 1) {
                x0(viewB, iE2, iE, iE3, i2);
            } else {
                x0(viewB, iE, iE2, i2, iE3);
            }
            if (cVar.f663f) {
                E2(this.y.f2095e, i3);
            } else {
                K2(dVar, this.y.f2095e, i3);
            }
            u2(vVar, this.y);
            if (!this.y.f2098h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.f663f) {
                this.B.clear();
                z = false;
            } else {
                z = false;
                this.B.set(dVar.f668e, false);
            }
            r9 = z;
            r0 = true;
        }
        ?? r32 = r9;
        if (r0 == false) {
            u2(vVar, this.y);
        }
        int iM2 = this.y.f2095e == -1 ? this.u.m() - i2(this.u.m()) : f2(this.u.i()) - this.u.i();
        return iM2 > 0 ? Math.min(fVar.f2092b, iM2) : r32 == true ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView.z zVar) {
        super.W0(zVar);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    public final int W1(int i2) {
        int I = I();
        for (int i3 = 0; i3 < I; i3++) {
            int iF0 = f0(H(i3));
            if (iF0 >= 0 && iF0 < i2) {
                return iF0;
            }
        }
        return 0;
    }

    public View X1(boolean z) {
        int iM = this.u.m();
        int i2 = this.u.i();
        View view = null;
        for (int I = I() - 1; I >= 0; I--) {
            View viewH = H(I);
            int iG = this.u.g(viewH);
            int iD = this.u.d(viewH);
            if (iD > iM && iG < i2) {
                if (iD <= i2 || !z) {
                    return viewH;
                }
                if (view == null) {
                    view = viewH;
                }
            }
        }
        return view;
    }

    public View Y1(boolean z) {
        int iM = this.u.m();
        int i2 = this.u.i();
        int I = I();
        View view = null;
        for (int i3 = 0; i3 < I; i3++) {
            View viewH = H(i3);
            int iG = this.u.g(viewH);
            if (this.u.d(viewH) > iM && iG < i2) {
                if (iG >= iM || !z) {
                    return viewH;
                }
                if (view == null) {
                    view = viewH;
                }
            }
        }
        return view;
    }

    public int Z1() {
        View viewX1 = this.A ? X1(true) : Y1(true);
        if (viewX1 == null) {
            return -1;
        }
        return f0(viewX1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.I = (SavedState) parcelable;
            q1();
        }
    }

    public final int a2(int i2) {
        for (int I = I() - 1; I >= 0; I--) {
            int iF0 = f0(H(I));
            if (iF0 >= 0 && iF0 < i2) {
                return iF0;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable b1() {
        int iP;
        int iM;
        int[] iArr;
        if (this.I != null) {
            return new SavedState(this.I);
        }
        SavedState savedState = new SavedState();
        savedState.G3 = this.z;
        savedState.H3 = this.G;
        savedState.I3 = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f648a) == null) {
            savedState.y = 0;
        } else {
            savedState.E3 = iArr;
            savedState.y = iArr.length;
            savedState.F3 = lazySpanLookup.f649b;
        }
        if (I() > 0) {
            savedState.f652c = this.G ? e2() : d2();
            savedState.f653d = Z1();
            int i2 = this.s;
            savedState.s = i2;
            savedState.x = new int[i2];
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
                savedState.x[i3] = iP;
            }
        } else {
            savedState.f652c = -1;
            savedState.f653d = -1;
            savedState.s = 0;
        }
        return savedState;
    }

    public final void b2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int i2;
        int iF2 = f2(Integer.MIN_VALUE);
        if (iF2 != Integer.MIN_VALUE && (i2 = this.u.i() - iF2) > 0) {
            int i3 = i2 - (-z2(-i2, vVar, zVar));
            if (!z || i3 <= 0) {
                return;
            }
            this.u.r(i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(int i2) {
        if (i2 == 0) {
            M1();
        }
    }

    public final void c2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z) {
        int iM;
        int iI2 = i2(Integer.MAX_VALUE);
        if (iI2 != Integer.MAX_VALUE && (iM = iI2 - this.u.m()) > 0) {
            int iZ2 = iM - z2(iM, vVar, zVar);
            if (!z || iZ2 <= 0) {
                return;
            }
            this.u.r(-iZ2);
        }
    }

    public int d2() {
        if (I() == 0) {
            return 0;
        }
        return f0(H(0));
    }

    public int e2() {
        int I = I();
        if (I == 0) {
            return 0;
        }
        return f0(H(I - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f(String str) {
        if (this.I == null) {
            super.f(str);
        }
    }

    public final int f2(int i2) {
        int iL = this.t[0].l(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iL2 = this.t[i3].l(i2);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    public final int g2(int i2) {
        int iP = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iP2 = this.t[i3].p(i2);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final int h2(int i2) {
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
    public int i0(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.w == 0 ? this.s : super.i0(vVar, zVar);
    }

    public final int i2(int i2) {
        int iP = this.t[0].p(i2);
        for (int i3 = 1; i3 < this.s; i3++) {
            int iP2 = this.t[i3].p(i2);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean j() {
        return this.w == 0;
    }

    public final d j2(f fVar) {
        int i2;
        int i3;
        int i4 = -1;
        if (r2(fVar.f2095e)) {
            i2 = this.s - 1;
            i3 = -1;
        } else {
            i2 = 0;
            i4 = this.s;
            i3 = 1;
        }
        d dVar = null;
        if (fVar.f2095e == 1) {
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
    public boolean k() {
        return this.w == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k2(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.A
            if (r0 == 0) goto L9
            int r0 = r6.e2()
            goto Ld
        L9:
            int r0 = r6.d2()
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
            int r7 = r6.d2()
            goto L51
        L4d:
            int r7 = r6.e2()
        L51:
            if (r3 > r7) goto L56
            r6.q1()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.k2(int, int, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View l2() {
        /*
            r12 = this;
            int r0 = r12.I()
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
            boolean r3 = r12.n2()
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
            android.view.View r7 = r12.H(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f662e
            int r9 = r9.f668e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f662e
            boolean r9 = r12.N1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f662e
            int r9 = r9.f668e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f663f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.H(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            b.n.d.h r10 = r12.u
            int r10 = r10.d(r7)
            b.n.d.h r11 = r12.u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            b.n.d.h r10 = r12.u
            int r10 = r10.g(r7)
            b.n.d.h r11 = r12.u
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f662e
            int r8 = r8.f668e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f662e
            int r9 = r9.f668e
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.l2():android.view.View");
    }

    public void m2() {
        this.E.b();
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void n(int i2, int i3, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.w != 0) {
            i2 = i3;
        }
        if (I() == 0 || i2 == 0) {
            return;
        }
        s2(i2, zVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.s; i5++) {
            f fVar = this.y;
            if (fVar.f2094d == -1) {
                iL = fVar.f2096f;
                iP = this.t[i5].p(iL);
            } else {
                iL = this.t[i5].l(fVar.f2097g);
                iP = this.y.f2097g;
            }
            int i6 = iL - iP;
            if (i6 >= 0) {
                this.O[i4] = i6;
                i4++;
            }
        }
        Arrays.sort(this.O, 0, i4);
        for (int i7 = 0; i7 < i4 && this.y.a(zVar); i7++) {
            cVar.a(this.y.f2093c, this.O[i7]);
            f fVar2 = this.y;
            fVar2.f2093c += fVar2.f2094d;
        }
    }

    public boolean n2() {
        return X() == 1;
    }

    public final void o2(View view, int i2, int i3, boolean z) {
        i(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iL2 = L2(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iL22 = L2(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? D1(view, iL2, iL22, cVar) : B1(view, iL2, iL22, cVar)) {
            view.measure(iL2, iL22);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int p(RecyclerView.z zVar) {
        return O1(zVar);
    }

    public final void p2(View view, c cVar, boolean z) {
        if (cVar.f663f) {
            if (this.w == 1) {
                o2(view, this.J, RecyclerView.o.J(V(), W(), e0() + b0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
                return;
            } else {
                o2(view, RecyclerView.o.J(m0(), n0(), c0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z);
                return;
            }
        }
        if (this.w == 1) {
            o2(view, RecyclerView.o.J(this.x, n0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.J(V(), W(), e0() + b0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            o2(view, RecyclerView.o.J(m0(), n0(), c0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.J(this.x, W(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return P1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean q0() {
        return this.F != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q2(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.z r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.q2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, boolean):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    public final boolean r2(int i2) {
        if (this.w == 0) {
            return (i2 == -1) != this.A;
        }
        return ((i2 == -1) == this.A) == n2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int s(RecyclerView.z zVar) {
        return O1(zVar);
    }

    public void s2(int i2, RecyclerView.z zVar) {
        int i3;
        int iD2;
        if (i2 > 0) {
            iD2 = e2();
            i3 = 1;
        } else {
            i3 = -1;
            iD2 = d2();
        }
        this.y.f2091a = true;
        I2(iD2, zVar);
        A2(i3);
        f fVar = this.y;
        fVar.f2093c = iD2 + fVar.f2094d;
        fVar.f2092b = Math.abs(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return P1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int t1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        return z2(i2, vVar, zVar);
    }

    public final void t2(View view) {
        for (int i2 = this.s - 1; i2 >= 0; i2--) {
            this.t[i2].u(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return Q1(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int u1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        return z2(i2, vVar, zVar);
    }

    public final void u2(RecyclerView.v vVar, f fVar) {
        if (!fVar.f2091a || fVar.f2099i) {
            return;
        }
        if (fVar.f2092b == 0) {
            if (fVar.f2095e == -1) {
                v2(vVar, fVar.f2097g);
                return;
            } else {
                w2(vVar, fVar.f2096f);
                return;
            }
        }
        if (fVar.f2095e != -1) {
            int iH2 = h2(fVar.f2097g) - fVar.f2097g;
            w2(vVar, iH2 < 0 ? fVar.f2096f : Math.min(iH2, fVar.f2092b) + fVar.f2096f);
        } else {
            int i2 = fVar.f2096f;
            int iG2 = i2 - g2(i2);
            v2(vVar, iG2 < 0 ? fVar.f2097g : fVar.f2097g - Math.min(iG2, fVar.f2092b));
        }
    }

    public final void v2(RecyclerView.v vVar, int i2) {
        for (int I = I() - 1; I >= 0; I--) {
            View viewH = H(I);
            if (this.u.g(viewH) < i2 || this.u.q(viewH) < i2) {
                return;
            }
            c cVar = (c) viewH.getLayoutParams();
            if (cVar.f663f) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    if (this.t[i3].f664a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.s; i4++) {
                    this.t[i4].s();
                }
            } else if (cVar.f662e.f664a.size() == 1) {
                return;
            } else {
                cVar.f662e.s();
            }
            j1(viewH, vVar);
        }
    }

    public final void w2(RecyclerView.v vVar, int i2) {
        while (I() > 0) {
            View viewH = H(0);
            if (this.u.d(viewH) > i2 || this.u.p(viewH) > i2) {
                return;
            }
            c cVar = (c) viewH.getLayoutParams();
            if (cVar.f663f) {
                for (int i3 = 0; i3 < this.s; i3++) {
                    if (this.t[i3].f664a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.s; i4++) {
                    this.t[i4].t();
                }
            } else if (cVar.f662e.f664a.size() == 1) {
                return;
            } else {
                cVar.f662e.t();
            }
            j1(viewH, vVar);
        }
    }

    public final void x2() {
        if (this.v.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int I = I();
        for (int i2 = 0; i2 < I; i2++) {
            View viewH = H(i2);
            float fE = this.v.e(viewH);
            if (fE >= fMax) {
                if (((c) viewH.getLayoutParams()).f()) {
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
        J2(iRound);
        if (this.x == i3) {
            return;
        }
        for (int i4 = 0; i4 < I; i4++) {
            View viewH2 = H(i4);
            c cVar = (c) viewH2.getLayoutParams();
            if (!cVar.f663f) {
                if (n2() && this.w == 1) {
                    int i5 = this.s;
                    int i6 = cVar.f662e.f668e;
                    viewH2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.x) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = cVar.f662e.f668e;
                    int i8 = this.x * i7;
                    int i9 = i7 * i3;
                    if (this.w == 1) {
                        viewH2.offsetLeftAndRight(i8 - i9);
                    } else {
                        viewH2.offsetTopAndBottom(i8 - i9);
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void y1(Rect rect, int i2, int i3) {
        int iM;
        int iM2;
        int iC0 = c0() + d0();
        int iE0 = e0() + b0();
        if (this.w == 1) {
            iM2 = RecyclerView.o.m(i3, rect.height() + iE0, Z());
            iM = RecyclerView.o.m(i2, (this.x * this.s) + iC0, a0());
        } else {
            iM = RecyclerView.o.m(i2, rect.width() + iC0, a0());
            iM2 = RecyclerView.o.m(i3, (this.x * this.s) + iE0, Z());
        }
        x1(iM, iM2);
    }

    public final void y2() {
        if (this.w == 1 || !n2()) {
            this.A = this.z;
        } else {
            this.A = !this.z;
        }
    }

    public int z2(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (I() == 0 || i2 == 0) {
            return 0;
        }
        s2(i2, zVar);
        int iV1 = V1(vVar, this.y, zVar);
        if (this.y.f2092b >= iV1) {
            i2 = i2 < 0 ? -iV1 : iV1;
        }
        this.u.r(-i2);
        this.G = this.A;
        f fVar = this.y;
        fVar.f2092b = 0;
        u2(vVar, fVar);
        return i2;
    }

    public static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f648a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<FullSpanItem> f649b;

        public void a(FullSpanItem fullSpanItem) {
            if (this.f649b == null) {
                this.f649b = new ArrayList();
            }
            int size = this.f649b.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = this.f649b.get(i2);
                if (fullSpanItem2.f650c == fullSpanItem.f650c) {
                    this.f649b.remove(i2);
                }
                if (fullSpanItem2.f650c >= fullSpanItem.f650c) {
                    this.f649b.add(i2, fullSpanItem);
                    return;
                }
            }
            this.f649b.add(fullSpanItem);
        }

        public void b() {
            int[] iArr = this.f648a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f649b = null;
        }

        public void c(int i2) {
            int[] iArr = this.f648a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.f648a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[o(i2)];
                this.f648a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f648a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i2) {
            List<FullSpanItem> list = this.f649b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f649b.get(size).f650c >= i2) {
                        this.f649b.remove(size);
                    }
                }
            }
            return h(i2);
        }

        public FullSpanItem e(int i2, int i3, int i4, boolean z) {
            List<FullSpanItem> list = this.f649b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = this.f649b.get(i5);
                int i6 = fullSpanItem.f650c;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || fullSpanItem.f651d == i4 || (z && fullSpanItem.x))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i2) {
            List<FullSpanItem> list = this.f649b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f649b.get(size);
                if (fullSpanItem.f650c == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int g(int i2) {
            int[] iArr = this.f648a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        public int h(int i2) {
            int[] iArr = this.f648a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int i3 = i(i2);
            if (i3 == -1) {
                int[] iArr2 = this.f648a;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.f648a.length;
            }
            int i4 = i3 + 1;
            Arrays.fill(this.f648a, i2, i4, -1);
            return i4;
        }

        public final int i(int i2) {
            if (this.f649b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i2);
            if (fullSpanItemF != null) {
                this.f649b.remove(fullSpanItemF);
            }
            int size = this.f649b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (this.f649b.get(i3).f650c >= i2) {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f649b.get(i3);
            this.f649b.remove(i3);
            return fullSpanItem.f650c;
        }

        public void j(int i2, int i3) {
            int[] iArr = this.f648a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f648a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.f648a, i2, i4, -1);
            l(i2, i3);
        }

        public void k(int i2, int i3) {
            int[] iArr = this.f648a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.f648a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.f648a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            m(i2, i3);
        }

        public final void l(int i2, int i3) {
            List<FullSpanItem> list = this.f649b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f649b.get(size);
                int i4 = fullSpanItem.f650c;
                if (i4 >= i2) {
                    fullSpanItem.f650c = i4 + i3;
                }
            }
        }

        public final void m(int i2, int i3) {
            List<FullSpanItem> list = this.f649b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f649b.get(size);
                int i5 = fullSpanItem.f650c;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.f649b.remove(size);
                    } else {
                        fullSpanItem.f650c = i5 - i3;
                    }
                }
            }
        }

        public void n(int i2, d dVar) {
            c(i2);
            this.f648a[i2] = dVar.f668e;
        }

        public int o(int i2) {
            int length = this.f648a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f650c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f651d;
            public int[] s;
            public boolean x;

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
                this.f650c = parcel.readInt();
                this.f651d = parcel.readInt();
                this.x = parcel.readInt() == 1;
                int i2 = parcel.readInt();
                if (i2 > 0) {
                    int[] iArr = new int[i2];
                    this.s = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            public int a(int i2) {
                int[] iArr = this.s;
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
                return "FullSpanItem{mPosition=" + this.f650c + ", mGapDir=" + this.f651d + ", mHasUnwantedGapAfter=" + this.x + ", mGapPerSpan=" + Arrays.toString(this.s) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f650c);
                parcel.writeInt(this.f651d);
                parcel.writeInt(this.x ? 1 : 0);
                int[] iArr = this.s;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.s);
                }
            }

            public FullSpanItem() {
            }
        }
    }
}
