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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class StaggeredGridLayoutManager extends RecyclerView.m {
    private BitSet B;
    private boolean G;
    private boolean H;
    private SavedState I;
    private int J;
    private int[] O;
    d[] t;

    @NonNull
    k u;

    @NonNull
    k v;
    private int w;
    private int x;

    @NonNull
    private final h y;
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

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1137c;
        int d;
        int e;
        int[] f;
        int g;
        int[] h;
        List<LazySpanLookup.FullSpanItem> i;
        boolean j;
        boolean k;
        boolean l;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Parcelable.Creator<SavedState> {
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
            this.f = null;
            this.e = 0;
            this.f1137c = -1;
            this.d = -1;
        }

        void b() {
            this.f = null;
            this.e = 0;
            this.g = 0;
            this.h = null;
            this.i = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1137c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            parcel.writeInt(this.g);
            if (this.g > 0) {
                parcel.writeIntArray(this.h);
            }
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeInt(this.l ? 1 : 0);
            parcel.writeList(this.i);
        }

        SavedState(Parcel parcel) {
            this.f1137c = parcel.readInt();
            this.d = parcel.readInt();
            int i = parcel.readInt();
            this.e = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.f = iArr;
                parcel.readIntArray(iArr);
            }
            int i2 = parcel.readInt();
            this.g = i2;
            if (i2 > 0) {
                int[] iArr2 = new int[i2];
                this.h = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.j = parcel.readInt() == 1;
            this.k = parcel.readInt() == 1;
            this.l = parcel.readInt() == 1;
            this.i = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.e = savedState.e;
            this.f1137c = savedState.f1137c;
            this.d = savedState.d;
            this.f = savedState.f;
            this.g = savedState.g;
            this.h = savedState.h;
            this.j = savedState.j;
            this.k = savedState.k;
            this.l = savedState.l;
            this.i = savedState.i;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.M1();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1139a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1140b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1141c;
        boolean d;
        boolean e;
        int[] f;

        b() {
            c();
        }

        void a() {
            this.f1140b = this.f1141c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        void b(int i) {
            if (this.f1141c) {
                this.f1140b = StaggeredGridLayoutManager.this.u.i() - i;
            } else {
                this.f1140b = StaggeredGridLayoutManager.this.u.m() + i;
            }
        }

        void c() {
            this.f1139a = -1;
            this.f1140b = Integer.MIN_VALUE;
            this.f1141c = false;
            this.d = false;
            this.e = false;
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

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c extends RecyclerView.n {
        d e;
        boolean f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public boolean e() {
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

    /* JADX INFO: compiled from: caradapter-bug */
    class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ArrayList<View> f1142a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1143b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1144c = Integer.MIN_VALUE;
        int d = 0;
        final int e;

        d(int i) {
            this.e = i;
        }

        void a(View view) {
            c cVarN = n(view);
            cVarN.e = this;
            this.f1142a.add(view);
            this.f1144c = Integer.MIN_VALUE;
            if (this.f1142a.size() == 1) {
                this.f1143b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.d += StaggeredGridLayoutManager.this.u.e(view);
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
                    this.f1144c = iL;
                    this.f1143b = iL;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1142a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1144c = StaggeredGridLayoutManager.this.u.d(view);
            if (cVarN.f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.d == 1) {
                this.f1144c += fullSpanItemF.a(this.e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1142a.get(0);
            c cVarN = n(view);
            this.f1143b = StaggeredGridLayoutManager.this.u.g(view);
            if (cVarN.f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.d == -1) {
                this.f1143b -= fullSpanItemF.a(this.e);
            }
        }

        void e() {
            this.f1142a.clear();
            q();
            this.d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.z ? i(this.f1142a.size() - 1, -1, true) : i(0, this.f1142a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.z ? i(0, this.f1142a.size(), true) : i(this.f1142a.size() - 1, -1, true);
        }

        int h(int i, int i2, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.u.m();
            int i3 = StaggeredGridLayoutManager.this.u.i();
            int i4 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1142a.get(i);
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
                            return StaggeredGridLayoutManager.this.f0(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.f0(view);
                        }
                        if (iG < iM || iD > i3) {
                            return StaggeredGridLayoutManager.this.f0(view);
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
            return this.d;
        }

        int k() {
            int i = this.f1144c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.f1144c;
        }

        int l(int i) {
            int i2 = this.f1144c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1142a.size() == 0) {
                return i;
            }
            c();
            return this.f1144c;
        }

        public View m(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.f1142a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1142a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.f0(view2) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.f0(view2) <= i) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1142a.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.f1142a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.f0(view3) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.f0(view3) >= i) || !view3.hasFocusable()) {
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
            int i = this.f1143b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            d();
            return this.f1143b;
        }

        int p(int i) {
            int i2 = this.f1143b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1142a.size() == 0) {
                return i;
            }
            d();
            return this.f1143b;
        }

        void q() {
            this.f1143b = Integer.MIN_VALUE;
            this.f1144c = Integer.MIN_VALUE;
        }

        void r(int i) {
            int i2 = this.f1143b;
            if (i2 != Integer.MIN_VALUE) {
                this.f1143b = i2 + i;
            }
            int i3 = this.f1144c;
            if (i3 != Integer.MIN_VALUE) {
                this.f1144c = i3 + i;
            }
        }

        void s() {
            int size = this.f1142a.size();
            View viewRemove = this.f1142a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.e = null;
            if (cVarN.c() || cVarN.b()) {
                this.d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            if (size == 1) {
                this.f1143b = Integer.MIN_VALUE;
            }
            this.f1144c = Integer.MIN_VALUE;
        }

        void t() {
            View viewRemove = this.f1142a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.e = null;
            if (this.f1142a.size() == 0) {
                this.f1144c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            this.f1143b = Integer.MIN_VALUE;
        }

        void u(View view) {
            c cVarN = n(view);
            cVarN.e = this;
            this.f1142a.add(0, view);
            this.f1143b = Integer.MIN_VALUE;
            if (this.f1142a.size() == 1) {
                this.f1144c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void v(int i) {
            this.f1143b = i;
            this.f1144c = i;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.m.d dVarG0 = RecyclerView.m.g0(context, attributeSet, i, i2);
        B2(dVarG0.f1115a);
        D2(dVarG0.f1116b);
        C2(dVarG0.f1117c);
        this.y = new h();
        U1();
    }

    private void A2(int i) {
        h hVar = this.y;
        hVar.e = i;
        hVar.d = this.A != (i == -1) ? -1 : 1;
    }

    private void E2(int i, int i2) {
        for (int i3 = 0; i3 < this.s; i3++) {
            if (!this.t[i3].f1142a.isEmpty()) {
                K2(this.t[i3], i, i2);
            }
        }
    }

    private boolean F2(RecyclerView.x xVar, b bVar) {
        bVar.f1139a = this.G ? a2(xVar.b()) : W1(xVar.b());
        bVar.f1140b = Integer.MIN_VALUE;
        return true;
    }

    private void G1(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].a(view);
        }
    }

    private void H1(b bVar) {
        SavedState savedState = this.I;
        int i = savedState.e;
        if (i > 0) {
            if (i == this.s) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    this.t[i2].e();
                    SavedState savedState2 = this.I;
                    int i3 = savedState2.f[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        i3 += savedState2.k ? this.u.i() : this.u.m();
                    }
                    this.t[i2].v(i3);
                }
            } else {
                savedState.b();
                SavedState savedState3 = this.I;
                savedState3.f1137c = savedState3.d;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.l;
        C2(savedState4.j);
        y2();
        SavedState savedState5 = this.I;
        int i4 = savedState5.f1137c;
        if (i4 != -1) {
            this.C = i4;
            bVar.f1141c = savedState5.k;
        } else {
            bVar.f1141c = this.A;
        }
        SavedState savedState6 = this.I;
        if (savedState6.g > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f1134a = savedState6.h;
            lazySpanLookup.f1135b = savedState6.i;
        }
    }

    private void I2(int i, RecyclerView.x xVar) {
        int iN;
        int iN2;
        int iC;
        h hVar = this.y;
        boolean z = false;
        hVar.f1215b = 0;
        hVar.f1216c = i;
        if (!v0() || (iC = xVar.c()) == -1) {
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
        if (L()) {
            this.y.f = this.u.m() - iN2;
            this.y.g = this.u.i() + iN;
        } else {
            this.y.g = this.u.h() + iN;
            this.y.f = -iN2;
        }
        h hVar2 = this.y;
        hVar2.h = false;
        hVar2.f1214a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        hVar2.i = z;
    }

    private void K1(View view, c cVar, h hVar) {
        if (hVar.e == 1) {
            if (cVar.f) {
                G1(view);
                return;
            } else {
                cVar.e.a(view);
                return;
            }
        }
        if (cVar.f) {
            t2(view);
        } else {
            cVar.e.u(view);
        }
    }

    private void K2(d dVar, int i, int i2) {
        int iJ = dVar.j();
        if (i == -1) {
            if (dVar.o() + iJ <= i2) {
                this.B.set(dVar.e, false);
            }
        } else if (dVar.k() - iJ >= i2) {
            this.B.set(dVar.e, false);
        }
    }

    private int L1(int i) {
        if (I() == 0) {
            return this.A ? 1 : -1;
        }
        return (i < d2()) != this.A ? -1 : 1;
    }

    private int L2(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    private boolean N1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.u.i()) {
                ArrayList<View> arrayList = dVar.f1142a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f;
            }
        } else if (dVar.o() > this.u.m()) {
            return !dVar.n(dVar.f1142a.get(0)).f;
        }
        return false;
    }

    private int O1(RecyclerView.x xVar) {
        if (I() == 0) {
            return 0;
        }
        return m.a(xVar, this.u, Y1(!this.N), X1(!this.N), this, this.N);
    }

    private int P1(RecyclerView.x xVar) {
        if (I() == 0) {
            return 0;
        }
        return m.b(xVar, this.u, Y1(!this.N), X1(!this.N), this, this.N, this.A);
    }

    private int Q1(RecyclerView.x xVar) {
        if (I() == 0) {
            return 0;
        }
        return m.c(xVar, this.u, Y1(!this.N), X1(!this.N), this, this.N);
    }

    private int R1(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && n2()) ? -1 : 1 : (this.w != 1 && n2()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem S1(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.e = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            fullSpanItem.e[i2] = i - this.t[i2].l(i);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem T1(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.e = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            fullSpanItem.e[i2] = this.t[i2].p(i) - i;
        }
        return fullSpanItem;
    }

    private void U1() {
        this.u = k.b(this, this.w);
        this.v = k.b(this, 1 - this.w);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r16v0, types: [androidx.recyclerview.widget.RecyclerView$m, androidx.recyclerview.widget.StaggeredGridLayoutManager] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int V1(RecyclerView.t tVar, h hVar, RecyclerView.x xVar) {
        d dVarJ2;
        int iE;
        int i;
        int iE2;
        int iE3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        int i2 = this.y.i ? hVar.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : hVar.e == 1 ? hVar.g + hVar.f1215b : hVar.f - hVar.f1215b;
        E2(hVar.e, i2);
        int i3 = this.A ? this.u.i() : this.u.m();
        ?? r0 = false;
        while (hVar.a(xVar) && (this.y.i || !this.B.isEmpty())) {
            View viewB = hVar.b(tVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                dVarJ2 = cVar.f ? this.t[r9] : j2(hVar);
                this.E.n(iA, dVarJ2);
            } else {
                dVarJ2 = this.t[iG];
            }
            d dVar = dVarJ2;
            cVar.e = dVar;
            if (hVar.e == 1) {
                c(viewB);
            } else {
                d(viewB, r9);
            }
            p2(viewB, cVar, r9);
            if (hVar.e == 1) {
                int iF2 = cVar.f ? f2(i3) : dVar.l(i3);
                int iE4 = this.u.e(viewB) + iF2;
                if (r3 != 0 && cVar.f) {
                    LazySpanLookup.FullSpanItem fullSpanItemS1 = S1(iF2);
                    fullSpanItemS1.d = -1;
                    fullSpanItemS1.f1136c = iA;
                    this.E.a(fullSpanItemS1);
                }
                i = iE4;
                iE = iF2;
            } else {
                int iI2 = cVar.f ? i2(i3) : dVar.p(i3);
                iE = iI2 - this.u.e(viewB);
                if (r3 != 0 && cVar.f) {
                    LazySpanLookup.FullSpanItem fullSpanItemT1 = T1(iI2);
                    fullSpanItemT1.d = 1;
                    fullSpanItemT1.f1136c = iA;
                    this.E.a(fullSpanItemT1);
                }
                i = iI2;
            }
            if (cVar.f && hVar.d == -1) {
                if (r3 != 0) {
                    this.M = true;
                } else {
                    if (!(hVar.e == 1 ? I1() : J1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f = true;
                        }
                        this.M = true;
                    }
                }
            }
            K1(viewB, cVar, hVar);
            if (n2() && this.w == 1) {
                int i4 = cVar.f ? this.v.i() : this.v.i() - (((this.s - 1) - dVar.e) * this.x);
                iE3 = i4;
                iE2 = i4 - this.v.e(viewB);
            } else {
                int iM = cVar.f ? this.v.m() : (dVar.e * this.x) + this.v.m();
                iE2 = iM;
                iE3 = this.v.e(viewB) + iM;
            }
            if (this.w == 1) {
                x0(viewB, iE2, iE, iE3, i);
            } else {
                x0(viewB, iE, iE2, i, iE3);
            }
            if (cVar.f) {
                E2(this.y.e, i2);
            } else {
                K2(dVar, this.y.e, i2);
            }
            u2(tVar, this.y);
            if (!this.y.h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.f) {
                this.B.clear();
                z = false;
            } else {
                z = false;
                this.B.set(dVar.e, false);
            }
            r9 = z;
            r0 = true;
        }
        ?? r32 = r9;
        if (r0 == false) {
            u2(tVar, this.y);
        }
        int iM2 = this.y.e == -1 ? this.u.m() - i2(this.u.m()) : f2(this.u.i()) - this.u.i();
        return iM2 > 0 ? Math.min(hVar.f1215b, iM2) : r32 == true ? 1 : 0;
    }

    private int W1(int i) {
        int I = I();
        for (int i2 = 0; i2 < I; i2++) {
            int iF0 = f0(H(i2));
            if (iF0 >= 0 && iF0 < i) {
                return iF0;
            }
        }
        return 0;
    }

    private int a2(int i) {
        for (int I = I() - 1; I >= 0; I--) {
            int iF0 = f0(H(I));
            if (iF0 >= 0 && iF0 < i) {
                return iF0;
            }
        }
        return 0;
    }

    private void b2(RecyclerView.t tVar, RecyclerView.x xVar, boolean z) {
        int i;
        int iF2 = f2(Integer.MIN_VALUE);
        if (iF2 != Integer.MIN_VALUE && (i = this.u.i() - iF2) > 0) {
            int i2 = i - (-z2(-i, tVar, xVar));
            if (!z || i2 <= 0) {
                return;
            }
            this.u.r(i2);
        }
    }

    private void c2(RecyclerView.t tVar, RecyclerView.x xVar, boolean z) {
        int iM;
        int iI2 = i2(Integer.MAX_VALUE);
        if (iI2 != Integer.MAX_VALUE && (iM = iI2 - this.u.m()) > 0) {
            int iZ2 = iM - z2(iM, tVar, xVar);
            if (!z || iZ2 <= 0) {
                return;
            }
            this.u.r(-iZ2);
        }
    }

    private int f2(int i) {
        int iL = this.t[0].l(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iL2 = this.t[i2].l(i);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int g2(int i) {
        int iP = this.t[0].p(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iP2 = this.t[i2].p(i);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private int h2(int i) {
        int iL = this.t[0].l(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iL2 = this.t[i2].l(i);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int i2(int i) {
        int iP = this.t[0].p(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iP2 = this.t[i2].p(i);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private d j2(h hVar) {
        int i;
        int i2;
        int i3 = -1;
        if (r2(hVar.e)) {
            i = this.s - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.s;
            i2 = 1;
        }
        d dVar = null;
        if (hVar.e == 1) {
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
    private void k2(int r7, int r8, int r9) {
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

    private void o2(View view, int i, int i2, boolean z) {
        i(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iL2 = L2(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iL22 = L2(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? D1(view, iL2, iL22, cVar) : B1(view, iL2, iL22, cVar)) {
            view.measure(iL2, iL22);
        }
    }

    private void p2(View view, c cVar, boolean z) {
        if (cVar.f) {
            if (this.w == 1) {
                o2(view, this.J, RecyclerView.m.J(V(), W(), e0() + b0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
                return;
            } else {
                o2(view, RecyclerView.m.J(m0(), n0(), c0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z);
                return;
            }
        }
        if (this.w == 1) {
            o2(view, RecyclerView.m.J(this.x, n0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.m.J(V(), W(), e0() + b0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            o2(view, RecyclerView.m.J(m0(), n0(), c0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.m.J(this.x, W(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void q2(androidx.recyclerview.widget.RecyclerView.t r9, androidx.recyclerview.widget.RecyclerView.x r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.q2(androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$x, boolean):void");
    }

    private boolean r2(int i) {
        if (this.w == 0) {
            return (i == -1) != this.A;
        }
        return ((i == -1) == this.A) == n2();
    }

    private void t2(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].u(view);
        }
    }

    private void u2(RecyclerView.t tVar, h hVar) {
        if (!hVar.f1214a || hVar.i) {
            return;
        }
        if (hVar.f1215b == 0) {
            if (hVar.e == -1) {
                v2(tVar, hVar.g);
                return;
            } else {
                w2(tVar, hVar.f);
                return;
            }
        }
        if (hVar.e != -1) {
            int iH2 = h2(hVar.g) - hVar.g;
            w2(tVar, iH2 < 0 ? hVar.f : Math.min(iH2, hVar.f1215b) + hVar.f);
        } else {
            int i = hVar.f;
            int iG2 = i - g2(i);
            v2(tVar, iG2 < 0 ? hVar.g : hVar.g - Math.min(iG2, hVar.f1215b));
        }
    }

    private void v2(RecyclerView.t tVar, int i) {
        for (int I = I() - 1; I >= 0; I--) {
            View viewH = H(I);
            if (this.u.g(viewH) < i || this.u.q(viewH) < i) {
                return;
            }
            c cVar = (c) viewH.getLayoutParams();
            if (cVar.f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1142a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].s();
                }
            } else if (cVar.e.f1142a.size() == 1) {
                return;
            } else {
                cVar.e.s();
            }
            j1(viewH, tVar);
        }
    }

    private void w2(RecyclerView.t tVar, int i) {
        while (I() > 0) {
            View viewH = H(0);
            if (this.u.d(viewH) > i || this.u.p(viewH) > i) {
                return;
            }
            c cVar = (c) viewH.getLayoutParams();
            if (cVar.f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1142a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].t();
                }
            } else if (cVar.e.f1142a.size() == 1) {
                return;
            } else {
                cVar.e.t();
            }
            j1(viewH, tVar);
        }
    }

    private void x2() {
        if (this.v.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int I = I();
        for (int i = 0; i < I; i++) {
            View viewH = H(i);
            float fE = this.v.e(viewH);
            if (fE >= fMax) {
                if (((c) viewH.getLayoutParams()).e()) {
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
        J2(iRound);
        if (this.x == i2) {
            return;
        }
        for (int i3 = 0; i3 < I; i3++) {
            View viewH2 = H(i3);
            c cVar = (c) viewH2.getLayoutParams();
            if (!cVar.f) {
                if (n2() && this.w == 1) {
                    int i4 = this.s;
                    int i5 = cVar.e.e;
                    viewH2.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.x) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = cVar.e.e;
                    int i7 = this.x * i6;
                    int i8 = i6 * i2;
                    if (this.w == 1) {
                        viewH2.offsetLeftAndRight(i7 - i8);
                    } else {
                        viewH2.offsetTopAndBottom(i7 - i8);
                    }
                }
            }
        }
    }

    private void y2() {
        if (this.w == 1 || !n2()) {
            this.A = this.z;
        } else {
            this.A = !this.z;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void A0(int i) {
        super.A0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].r(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void B0(int i) {
        super.B0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].r(i);
        }
    }

    public void B2(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        f(null);
        if (i == this.w) {
            return;
        }
        this.w = i;
        k kVar = this.u;
        this.u = this.v;
        this.v = kVar;
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n C() {
        return this.w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void C0(@Nullable RecyclerView.Adapter adapter, @Nullable RecyclerView.Adapter adapter2) {
        this.E.b();
        for (int i = 0; i < this.s; i++) {
            this.t[i].e();
        }
    }

    public void C2(boolean z) {
        f(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.j != z) {
            savedState.j = z;
        }
        this.z = z;
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n D(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    public void D2(int i) {
        f(null);
        if (i != this.s) {
            m2();
            this.s = i;
            this.B = new BitSet(this.s);
            this.t = new d[this.s];
            for (int i2 = 0; i2 < this.s; i2++) {
                this.t[i2] = new d(i2);
            }
            q1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n E(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean F1() {
        return this.I == null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void G0(RecyclerView recyclerView, RecyclerView.t tVar) {
        super.G0(recyclerView, tVar);
        l1(this.P);
        for (int i = 0; i < this.s; i++) {
            this.t[i].e();
        }
        recyclerView.requestLayout();
    }

    boolean G2(RecyclerView.x xVar, b bVar) {
        int i;
        if (!xVar.e() && (i = this.C) != -1) {
            if (i >= 0 && i < xVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f1137c == -1 || savedState.e < 1) {
                    View viewB = B(this.C);
                    if (viewB != null) {
                        bVar.f1139a = this.A ? e2() : d2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1141c) {
                                bVar.f1140b = (this.u.i() - this.D) - this.u.d(viewB);
                            } else {
                                bVar.f1140b = (this.u.m() + this.D) - this.u.g(viewB);
                            }
                            return true;
                        }
                        if (this.u.e(viewB) > this.u.n()) {
                            bVar.f1140b = bVar.f1141c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int iG = this.u.g(viewB) - this.u.m();
                        if (iG < 0) {
                            bVar.f1140b = -iG;
                            return true;
                        }
                        int i2 = this.u.i() - this.u.d(viewB);
                        if (i2 < 0) {
                            bVar.f1140b = i2;
                            return true;
                        }
                        bVar.f1140b = Integer.MIN_VALUE;
                    } else {
                        int i3 = this.C;
                        bVar.f1139a = i3;
                        int i4 = this.D;
                        if (i4 == Integer.MIN_VALUE) {
                            bVar.f1141c = L1(i3) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i4);
                        }
                        bVar.d = true;
                    }
                } else {
                    bVar.f1140b = Integer.MIN_VALUE;
                    bVar.f1139a = this.C;
                }
                return true;
            }
            this.C = -1;
            this.D = Integer.MIN_VALUE;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @Nullable
    public View H0(View view, int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        View viewA;
        View viewM;
        if (I() == 0 || (viewA = A(view)) == null) {
            return null;
        }
        y2();
        int iR1 = R1(i);
        if (iR1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewA.getLayoutParams();
        boolean z = cVar.f;
        d dVar = cVar.e;
        int iE2 = iR1 == 1 ? e2() : d2();
        I2(iE2, xVar);
        A2(iR1);
        h hVar = this.y;
        hVar.f1216c = hVar.d + iE2;
        hVar.f1215b = (int) (this.u.n() * 0.33333334f);
        h hVar2 = this.y;
        hVar2.h = true;
        hVar2.f1214a = false;
        V1(tVar, hVar2, xVar);
        this.G = this.A;
        if (!z && (viewM = dVar.m(iE2, iR1)) != null && viewM != viewA) {
            return viewM;
        }
        if (r2(iR1)) {
            for (int i2 = this.s - 1; i2 >= 0; i2--) {
                View viewM2 = this.t[i2].m(iE2, iR1);
                if (viewM2 != null && viewM2 != viewA) {
                    return viewM2;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.s; i3++) {
                View viewM3 = this.t[i3].m(iE2, iR1);
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
            for (int i4 = this.s - 1; i4 >= 0; i4--) {
                if (i4 != dVar.e) {
                    View viewB2 = B(z2 ? this.t[i4].f() : this.t[i4].g());
                    if (viewB2 != null && viewB2 != viewA) {
                        return viewB2;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.s; i5++) {
                View viewB3 = B(z2 ? this.t[i5].f() : this.t[i5].g());
                if (viewB3 != null && viewB3 != viewA) {
                    return viewB3;
                }
            }
        }
        return null;
    }

    void H2(RecyclerView.x xVar, b bVar) {
        if (G2(xVar, bVar) || F2(xVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1139a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
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

    boolean I1() {
        int iL = this.t[0].l(Integer.MIN_VALUE);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    boolean J1() {
        int iP = this.t[0].p(Integer.MIN_VALUE);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    void J2(int i) {
        this.x = i / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i, this.v.k());
    }

    boolean M1() {
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
        int i = this.A ? -1 : 1;
        int i2 = iE2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.E.e(iD2, i2, i, true);
        if (fullSpanItemE == null) {
            this.M = false;
            this.E.d(i2);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iD2, fullSpanItemE.f1136c, i * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f1136c);
        } else {
            this.E.d(fullSpanItemE2.f1136c + 1);
        }
        r1();
        q1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void P0(RecyclerView recyclerView, int i, int i2) {
        k2(i, i2, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void Q0(RecyclerView recyclerView) {
        this.E.b();
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void R0(RecyclerView recyclerView, int i, int i2, int i3) {
        k2(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void S0(RecyclerView recyclerView, int i, int i2) {
        k2(i, i2, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void U0(RecyclerView recyclerView, int i, int i2, Object obj) {
        k2(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void V0(RecyclerView.t tVar, RecyclerView.x xVar) {
        q2(tVar, xVar, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView.x xVar) {
        super.W0(xVar);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    View X1(boolean z) {
        int iM = this.u.m();
        int i = this.u.i();
        View view = null;
        for (int I = I() - 1; I >= 0; I--) {
            View viewH = H(I);
            int iG = this.u.g(viewH);
            int iD = this.u.d(viewH);
            if (iD > iM && iG < i) {
                if (iD <= i || !z) {
                    return viewH;
                }
                if (view == null) {
                    view = viewH;
                }
            }
        }
        return view;
    }

    View Y1(boolean z) {
        int iM = this.u.m();
        int i = this.u.i();
        int I = I();
        View view = null;
        for (int i2 = 0; i2 < I; i2++) {
            View viewH = H(i2);
            int iG = this.u.g(viewH);
            if (this.u.d(viewH) > iM && iG < i) {
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

    int Z1() {
        View viewX1 = this.A ? X1(true) : Y1(true);
        if (viewX1 == null) {
            return -1;
        }
        return f0(viewX1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void a1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.I = savedState;
            if (this.C != -1) {
                savedState.a();
                this.I.b();
            }
            q1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public Parcelable b1() {
        int iP;
        int iM;
        int[] iArr;
        if (this.I != null) {
            return new SavedState(this.I);
        }
        SavedState savedState = new SavedState();
        savedState.j = this.z;
        savedState.k = this.G;
        savedState.l = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1134a) == null) {
            savedState.g = 0;
        } else {
            savedState.h = iArr;
            savedState.g = iArr.length;
            savedState.i = lazySpanLookup.f1135b;
        }
        if (I() > 0) {
            savedState.f1137c = this.G ? e2() : d2();
            savedState.d = Z1();
            int i = this.s;
            savedState.e = i;
            savedState.f = new int[i];
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
                savedState.f[i2] = iP;
            }
        } else {
            savedState.f1137c = -1;
            savedState.d = -1;
            savedState.e = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void c1(int i) {
        if (i == 0) {
            M1();
        }
    }

    int d2() {
        if (I() == 0) {
            return 0;
        }
        return f0(H(0));
    }

    int e2() {
        int I = I();
        if (I == 0) {
            return 0;
        }
        return f0(H(I - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void f(String str) {
        if (this.I == null) {
            super.f(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean j() {
        return this.w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean k() {
        return this.w == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean l(RecyclerView.n nVar) {
        return nVar instanceof c;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.View l2() {
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.e
            int r9 = r9.e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.e
            boolean r9 = r12.N1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.e
            int r9 = r9.e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.H(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.k r10 = r12.u
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.k r11 = r12.u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.k r10 = r12.u
            int r10 = r10.g(r7)
            androidx.recyclerview.widget.k r11 = r12.u
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.e
            int r8 = r8.e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.e
            int r9 = r9.e
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
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.l2():android.view.View");
    }

    public void m2() {
        this.E.b();
        q1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void n(int i, int i2, RecyclerView.x xVar, RecyclerView.m.c cVar) {
        int iL;
        int iP;
        if (this.w != 0) {
            i = i2;
        }
        if (I() == 0 || i == 0) {
            return;
        }
        s2(i, xVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.s; i4++) {
            h hVar = this.y;
            if (hVar.d == -1) {
                iL = hVar.f;
                iP = this.t[i4].p(iL);
            } else {
                iL = this.t[i4].l(hVar.g);
                iP = this.y.g;
            }
            int i5 = iL - iP;
            if (i5 >= 0) {
                this.O[i3] = i5;
                i3++;
            }
        }
        Arrays.sort(this.O, 0, i3);
        for (int i6 = 0; i6 < i3 && this.y.a(xVar); i6++) {
            cVar.a(this.y.f1216c, this.O[i6]);
            h hVar2 = this.y;
            hVar2.f1216c += hVar2.d;
        }
    }

    boolean n2() {
        return X() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int p(RecyclerView.x xVar) {
        return O1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int q(RecyclerView.x xVar) {
        return P1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean q0() {
        return this.F != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int r(RecyclerView.x xVar) {
        return Q1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int s(RecyclerView.x xVar) {
        return O1(xVar);
    }

    void s2(int i, RecyclerView.x xVar) {
        int i2;
        int iD2;
        if (i > 0) {
            iD2 = e2();
            i2 = 1;
        } else {
            i2 = -1;
            iD2 = d2();
        }
        this.y.f1214a = true;
        I2(iD2, xVar);
        A2(i2);
        h hVar = this.y;
        hVar.f1216c = iD2 + hVar.d;
        hVar.f1215b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int t(RecyclerView.x xVar) {
        return P1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int t1(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        return z2(i, tVar, xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int u(RecyclerView.x xVar) {
        return Q1(xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int u1(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        return z2(i, tVar, xVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void y1(Rect rect, int i, int i2) {
        int iM;
        int iM2;
        int iC0 = c0() + d0();
        int iE0 = e0() + b0();
        if (this.w == 1) {
            iM2 = RecyclerView.m.m(i2, rect.height() + iE0, Z());
            iM = RecyclerView.m.m(i, (this.x * this.s) + iC0, a0());
        } else {
            iM = RecyclerView.m.m(i, rect.width() + iC0, a0());
            iM2 = RecyclerView.m.m(i2, (this.x * this.s) + iE0, Z());
        }
        x1(iM, iM2);
    }

    int z2(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        if (I() == 0 || i == 0) {
            return 0;
        }
        s2(i, xVar);
        int iV1 = V1(tVar, this.y, xVar);
        if (this.y.f1215b >= iV1) {
            i = i < 0 ? -iV1 : iV1;
        }
        this.u.r(-i);
        this.G = this.A;
        h hVar = this.y;
        hVar.f1215b = 0;
        u2(tVar, hVar);
        return i;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int[] f1134a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        List<FullSpanItem> f1135b;

        LazySpanLookup() {
        }

        private int i(int i) {
            if (this.f1135b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i);
            if (fullSpanItemF != null) {
                this.f1135b.remove(fullSpanItemF);
            }
            int size = this.f1135b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                if (this.f1135b.get(i2).f1136c >= i) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1135b.get(i2);
            this.f1135b.remove(i2);
            return fullSpanItem.f1136c;
        }

        private void l(int i, int i2) {
            List<FullSpanItem> list = this.f1135b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1135b.get(size);
                int i3 = fullSpanItem.f1136c;
                if (i3 >= i) {
                    fullSpanItem.f1136c = i3 + i2;
                }
            }
        }

        private void m(int i, int i2) {
            List<FullSpanItem> list = this.f1135b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1135b.get(size);
                int i4 = fullSpanItem.f1136c;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.f1135b.remove(size);
                    } else {
                        fullSpanItem.f1136c = i4 - i2;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1135b == null) {
                this.f1135b = new ArrayList();
            }
            int size = this.f1135b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.f1135b.get(i);
                if (fullSpanItem2.f1136c == fullSpanItem.f1136c) {
                    this.f1135b.remove(i);
                }
                if (fullSpanItem2.f1136c >= fullSpanItem.f1136c) {
                    this.f1135b.add(i, fullSpanItem);
                    return;
                }
            }
            this.f1135b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.f1134a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1135b = null;
        }

        void c(int i) {
            int[] iArr = this.f1134a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1134a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[o(i)];
                this.f1134a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1134a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i) {
            List<FullSpanItem> list = this.f1135b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1135b.get(size).f1136c >= i) {
                        this.f1135b.remove(size);
                    }
                }
            }
            return h(i);
        }

        public FullSpanItem e(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.f1135b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.f1135b.get(i4);
                int i5 = fullSpanItem.f1136c;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || fullSpanItem.d == i3 || (z && fullSpanItem.f))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i) {
            List<FullSpanItem> list = this.f1135b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1135b.get(size);
                if (fullSpanItem.f1136c == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i) {
            int[] iArr = this.f1134a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        int h(int i) {
            int[] iArr = this.f1134a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int i2 = i(i);
            if (i2 == -1) {
                int[] iArr2 = this.f1134a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.f1134a.length;
            }
            int i3 = i2 + 1;
            Arrays.fill(this.f1134a, i, i3, -1);
            return i3;
        }

        void j(int i, int i2) {
            int[] iArr = this.f1134a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1134a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1134a, i, i3, -1);
            l(i, i2);
        }

        void k(int i, int i2) {
            int[] iArr = this.f1134a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1134a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1134a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            m(i, i2);
        }

        void n(int i, d dVar) {
            c(i);
            this.f1134a[i] = dVar.e;
        }

        int o(int i) {
            int length = this.f1134a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        /* JADX INFO: compiled from: caradapter-bug */
        @SuppressLint({"BanParcelableUsage"})
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1136c;
            int d;
            int[] e;
            boolean f;

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements Parcelable.Creator<FullSpanItem> {
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
                this.f1136c = parcel.readInt();
                this.d = parcel.readInt();
                this.f = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.e = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int a(int i) {
                int[] iArr = this.e;
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
                return "FullSpanItem{mPosition=" + this.f1136c + ", mGapDir=" + this.d + ", mHasUnwantedGapAfter=" + this.f + ", mGapPerSpan=" + Arrays.toString(this.e) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1136c);
                parcel.writeInt(this.d);
                parcel.writeInt(this.f ? 1 : 0);
                int[] iArr = this.e;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.e);
                }
            }

            FullSpanItem() {
            }
        }
    }
}
