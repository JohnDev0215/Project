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
import j0.c;
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

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    d[] f2967t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    m f2968u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    m f2969v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f2970w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f2971x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final i f2972y;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f2966s = -1;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    boolean f2973z = false;
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

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2980e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2981g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f2982h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int[] f2983i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        int f2984j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        int[] f2985k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        List<LazySpanLookup.FullSpanItem> f2986l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        boolean f2987m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        boolean f2988n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        boolean f2989o;

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

        void b() {
            this.f2983i = null;
            this.f2982h = 0;
            this.f2980e = -1;
            this.f2981g = -1;
        }

        void c() {
            this.f2983i = null;
            this.f2982h = 0;
            this.f2984j = 0;
            this.f2985k = null;
            this.f2986l = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f2980e);
            parcel.writeInt(this.f2981g);
            parcel.writeInt(this.f2982h);
            if (this.f2982h > 0) {
                parcel.writeIntArray(this.f2983i);
            }
            parcel.writeInt(this.f2984j);
            if (this.f2984j > 0) {
                parcel.writeIntArray(this.f2985k);
            }
            parcel.writeInt(this.f2987m ? 1 : 0);
            parcel.writeInt(this.f2988n ? 1 : 0);
            parcel.writeInt(this.f2989o ? 1 : 0);
            parcel.writeList(this.f2986l);
        }

        SavedState(Parcel parcel) {
            this.f2980e = parcel.readInt();
            this.f2981g = parcel.readInt();
            int i7 = parcel.readInt();
            this.f2982h = i7;
            if (i7 > 0) {
                int[] iArr = new int[i7];
                this.f2983i = iArr;
                parcel.readIntArray(iArr);
            }
            int i8 = parcel.readInt();
            this.f2984j = i8;
            if (i8 > 0) {
                int[] iArr2 = new int[i8];
                this.f2985k = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f2987m = parcel.readInt() == 1;
            this.f2988n = parcel.readInt() == 1;
            this.f2989o = parcel.readInt() == 1;
            this.f2986l = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f2982h = savedState.f2982h;
            this.f2980e = savedState.f2980e;
            this.f2981g = savedState.f2981g;
            this.f2983i = savedState.f2983i;
            this.f2984j = savedState.f2984j;
            this.f2985k = savedState.f2985k;
            this.f2987m = savedState.f2987m;
            this.f2988n = savedState.f2988n;
            this.f2989o = savedState.f2989o;
            this.f2986l = savedState.f2986l;
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
        int f2991a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2992b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2993c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2994d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2995e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int[] f2996f;

        b() {
            c();
        }

        void a() {
            this.f2992b = this.f2993c ? StaggeredGridLayoutManager.this.f2968u.i() : StaggeredGridLayoutManager.this.f2968u.m();
        }

        void b(int i7) {
            if (this.f2993c) {
                this.f2992b = StaggeredGridLayoutManager.this.f2968u.i() - i7;
            } else {
                this.f2992b = StaggeredGridLayoutManager.this.f2968u.m() + i7;
            }
        }

        void c() {
            this.f2991a = -1;
            this.f2992b = Integer.MIN_VALUE;
            this.f2993c = false;
            this.f2994d = false;
            this.f2995e = false;
            int[] iArr = this.f2996f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f2996f;
            if (iArr == null || iArr.length < length) {
                this.f2996f = new int[StaggeredGridLayoutManager.this.f2967t.length];
            }
            for (int i7 = 0; i7 < length; i7++) {
                this.f2996f[i7] = dVarArr[i7].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        d f2998e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2999f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f2998e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f3004e;
        }

        public boolean f() {
            return this.f2999f;
        }

        public c(int i7, int i8) {
            super(i7, i8);
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
        ArrayList<View> f3000a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3001b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3002c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3003d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f3004e;

        d(int i7) {
            this.f3004e = i7;
        }

        void a(View view) {
            c cVarN = n(view);
            cVarN.f2998e = this;
            this.f3000a.add(view);
            this.f3002c = Integer.MIN_VALUE;
            if (this.f3000a.size() == 1) {
                this.f3001b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f3003d += StaggeredGridLayoutManager.this.f2968u.e(view);
            }
        }

        void b(boolean z6, int i7) {
            int iL = z6 ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (iL == Integer.MIN_VALUE) {
                return;
            }
            if (!z6 || iL >= StaggeredGridLayoutManager.this.f2968u.i()) {
                if (z6 || iL <= StaggeredGridLayoutManager.this.f2968u.m()) {
                    if (i7 != Integer.MIN_VALUE) {
                        iL += i7;
                    }
                    this.f3002c = iL;
                    this.f3001b = iL;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f3000a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f3002c = StaggeredGridLayoutManager.this.f2968u.d(view);
            if (cVarN.f2999f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f2977g == 1) {
                this.f3002c += fullSpanItemF.b(this.f3004e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f3000a.get(0);
            c cVarN = n(view);
            this.f3001b = StaggeredGridLayoutManager.this.f2968u.g(view);
            if (cVarN.f2999f && (fullSpanItemF = StaggeredGridLayoutManager.this.E.f(cVarN.a())) != null && fullSpanItemF.f2977g == -1) {
                this.f3001b -= fullSpanItemF.b(this.f3004e);
            }
        }

        void e() {
            this.f3000a.clear();
            q();
            this.f3003d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.f2973z ? i(this.f3000a.size() - 1, -1, true) : i(0, this.f3000a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.f2973z ? i(0, this.f3000a.size(), true) : i(this.f3000a.size() - 1, -1, true);
        }

        int h(int i7, int i8, boolean z6, boolean z7, boolean z8) {
            int iM = StaggeredGridLayoutManager.this.f2968u.m();
            int i9 = StaggeredGridLayoutManager.this.f2968u.i();
            int i10 = i8 > i7 ? 1 : -1;
            while (i7 != i8) {
                View view = this.f3000a.get(i7);
                int iG = StaggeredGridLayoutManager.this.f2968u.g(view);
                int iD = StaggeredGridLayoutManager.this.f2968u.d(view);
                boolean z9 = false;
                boolean z10 = !z8 ? iG >= i9 : iG > i9;
                if (!z8 ? iD > iM : iD >= iM) {
                    z9 = true;
                }
                if (z10 && z9) {
                    if (z6 && z7) {
                        if (iG >= iM && iD <= i9) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    } else {
                        if (z7) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                        if (iG < iM || iD > i9) {
                            return StaggeredGridLayoutManager.this.h0(view);
                        }
                    }
                }
                i7 += i10;
            }
            return -1;
        }

        int i(int i7, int i8, boolean z6) {
            return h(i7, i8, false, false, z6);
        }

        public int j() {
            return this.f3003d;
        }

        int k() {
            int i7 = this.f3002c;
            if (i7 != Integer.MIN_VALUE) {
                return i7;
            }
            c();
            return this.f3002c;
        }

        int l(int i7) {
            int i8 = this.f3002c;
            if (i8 != Integer.MIN_VALUE) {
                return i8;
            }
            if (this.f3000a.size() == 0) {
                return i7;
            }
            c();
            return this.f3002c;
        }

        public View m(int i7, int i8) {
            View view = null;
            if (i8 != -1) {
                int size = this.f3000a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f3000a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.f2973z && staggeredGridLayoutManager.h0(view2) >= i7) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.f2973z && staggeredGridLayoutManager2.h0(view2) <= i7) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f3000a.size();
                int i9 = 0;
                while (i9 < size2) {
                    View view3 = this.f3000a.get(i9);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.f2973z && staggeredGridLayoutManager3.h0(view3) <= i7) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.f2973z && staggeredGridLayoutManager4.h0(view3) >= i7) || !view3.hasFocusable()) {
                        break;
                    }
                    i9++;
                    view = view3;
                }
            }
            return view;
        }

        c n(View view) {
            return (c) view.getLayoutParams();
        }

        int o() {
            int i7 = this.f3001b;
            if (i7 != Integer.MIN_VALUE) {
                return i7;
            }
            d();
            return this.f3001b;
        }

        int p(int i7) {
            int i8 = this.f3001b;
            if (i8 != Integer.MIN_VALUE) {
                return i8;
            }
            if (this.f3000a.size() == 0) {
                return i7;
            }
            d();
            return this.f3001b;
        }

        void q() {
            this.f3001b = Integer.MIN_VALUE;
            this.f3002c = Integer.MIN_VALUE;
        }

        void r(int i7) {
            int i8 = this.f3001b;
            if (i8 != Integer.MIN_VALUE) {
                this.f3001b = i8 + i7;
            }
            int i9 = this.f3002c;
            if (i9 != Integer.MIN_VALUE) {
                this.f3002c = i9 + i7;
            }
        }

        void s() {
            int size = this.f3000a.size();
            View viewRemove = this.f3000a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f2998e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f3003d -= StaggeredGridLayoutManager.this.f2968u.e(viewRemove);
            }
            if (size == 1) {
                this.f3001b = Integer.MIN_VALUE;
            }
            this.f3002c = Integer.MIN_VALUE;
        }

        void t() {
            View viewRemove = this.f3000a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f2998e = null;
            if (this.f3000a.size() == 0) {
                this.f3002c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f3003d -= StaggeredGridLayoutManager.this.f2968u.e(viewRemove);
            }
            this.f3001b = Integer.MIN_VALUE;
        }

        void u(View view) {
            c cVarN = n(view);
            cVarN.f2998e = this;
            this.f3000a.add(0, view);
            this.f3001b = Integer.MIN_VALUE;
            if (this.f3000a.size() == 1) {
                this.f3002c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f3003d += StaggeredGridLayoutManager.this.f2968u.e(view);
            }
        }

        void v(int i7) {
            this.f3001b = i7;
            this.f3002c = i7;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        RecyclerView.o.d dVarI0 = RecyclerView.o.i0(context, attributeSet, i7, i8);
        H2(dVarI0.f2911a);
        J2(dVarI0.f2912b);
        I2(dVarI0.f2913c);
        this.f2972y = new i();
        a2();
    }

    private void A2(RecyclerView.v vVar, i iVar) {
        if (!iVar.f3146a || iVar.f3154i) {
            return;
        }
        if (iVar.f3147b == 0) {
            if (iVar.f3150e == -1) {
                B2(vVar, iVar.f3152g);
                return;
            } else {
                C2(vVar, iVar.f3151f);
                return;
            }
        }
        if (iVar.f3150e != -1) {
            int iN2 = n2(iVar.f3152g) - iVar.f3152g;
            C2(vVar, iN2 < 0 ? iVar.f3151f : Math.min(iN2, iVar.f3147b) + iVar.f3151f);
        } else {
            int i7 = iVar.f3151f;
            int iM2 = i7 - m2(i7);
            B2(vVar, iM2 < 0 ? iVar.f3152g : iVar.f3152g - Math.min(iM2, iVar.f3147b));
        }
    }

    private void B2(RecyclerView.v vVar, int i7) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            if (this.f2968u.g(viewI) < i7 || this.f2968u.q(viewI) < i7) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f2999f) {
                for (int i8 = 0; i8 < this.f2966s; i8++) {
                    if (this.f2967t[i8].f3000a.size() == 1) {
                        return;
                    }
                }
                for (int i9 = 0; i9 < this.f2966s; i9++) {
                    this.f2967t[i9].s();
                }
            } else if (cVar.f2998e.f3000a.size() == 1) {
                return;
            } else {
                cVar.f2998e.s();
            }
            m1(viewI, vVar);
        }
    }

    private void C2(RecyclerView.v vVar, int i7) {
        while (J() > 0) {
            View viewI = I(0);
            if (this.f2968u.d(viewI) > i7 || this.f2968u.p(viewI) > i7) {
                return;
            }
            c cVar = (c) viewI.getLayoutParams();
            if (cVar.f2999f) {
                for (int i8 = 0; i8 < this.f2966s; i8++) {
                    if (this.f2967t[i8].f3000a.size() == 1) {
                        return;
                    }
                }
                for (int i9 = 0; i9 < this.f2966s; i9++) {
                    this.f2967t[i9].t();
                }
            } else if (cVar.f2998e.f3000a.size() == 1) {
                return;
            } else {
                cVar.f2998e.t();
            }
            m1(viewI, vVar);
        }
    }

    private void D2() {
        if (this.f2969v.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int iJ = J();
        for (int i7 = 0; i7 < iJ; i7++) {
            View viewI = I(i7);
            float fE = this.f2969v.e(viewI);
            if (fE >= fMax) {
                if (((c) viewI.getLayoutParams()).f()) {
                    fE = (fE * 1.0f) / this.f2966s;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i8 = this.f2971x;
        int iRound = Math.round(fMax * this.f2966s);
        if (this.f2969v.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.f2969v.n());
        }
        P2(iRound);
        if (this.f2971x == i8) {
            return;
        }
        for (int i9 = 0; i9 < iJ; i9++) {
            View viewI2 = I(i9);
            c cVar = (c) viewI2.getLayoutParams();
            if (!cVar.f2999f) {
                if (t2() && this.f2970w == 1) {
                    int i10 = this.f2966s;
                    int i11 = cVar.f2998e.f3004e;
                    viewI2.offsetLeftAndRight(((-((i10 - 1) - i11)) * this.f2971x) - ((-((i10 - 1) - i11)) * i8));
                } else {
                    int i12 = cVar.f2998e.f3004e;
                    int i13 = this.f2971x * i12;
                    int i14 = i12 * i8;
                    if (this.f2970w == 1) {
                        viewI2.offsetLeftAndRight(i13 - i14);
                    } else {
                        viewI2.offsetTopAndBottom(i13 - i14);
                    }
                }
            }
        }
    }

    private void E2() {
        if (this.f2970w == 1 || !t2()) {
            this.A = this.f2973z;
        } else {
            this.A = !this.f2973z;
        }
    }

    private void G2(int i7) {
        i iVar = this.f2972y;
        iVar.f3150e = i7;
        iVar.f3149d = this.A != (i7 == -1) ? -1 : 1;
    }

    private void K2(int i7, int i8) {
        for (int i9 = 0; i9 < this.f2966s; i9++) {
            if (!this.f2967t[i9].f3000a.isEmpty()) {
                Q2(this.f2967t[i9], i7, i8);
            }
        }
    }

    private boolean L2(RecyclerView.z zVar, b bVar) {
        bVar.f2991a = this.G ? g2(zVar.b()) : c2(zVar.b());
        bVar.f2992b = Integer.MIN_VALUE;
        return true;
    }

    private void M1(View view) {
        for (int i7 = this.f2966s - 1; i7 >= 0; i7--) {
            this.f2967t[i7].a(view);
        }
    }

    private void N1(b bVar) {
        SavedState savedState = this.I;
        int i7 = savedState.f2982h;
        if (i7 > 0) {
            if (i7 == this.f2966s) {
                for (int i8 = 0; i8 < this.f2966s; i8++) {
                    this.f2967t[i8].e();
                    SavedState savedState2 = this.I;
                    int i9 = savedState2.f2983i[i8];
                    if (i9 != Integer.MIN_VALUE) {
                        i9 += savedState2.f2988n ? this.f2968u.i() : this.f2968u.m();
                    }
                    this.f2967t[i8].v(i9);
                }
            } else {
                savedState.c();
                SavedState savedState3 = this.I;
                savedState3.f2980e = savedState3.f2981g;
            }
        }
        SavedState savedState4 = this.I;
        this.H = savedState4.f2989o;
        I2(savedState4.f2987m);
        E2();
        SavedState savedState5 = this.I;
        int i10 = savedState5.f2980e;
        if (i10 != -1) {
            this.C = i10;
            bVar.f2993c = savedState5.f2988n;
        } else {
            bVar.f2993c = this.A;
        }
        if (savedState5.f2984j > 1) {
            LazySpanLookup lazySpanLookup = this.E;
            lazySpanLookup.f2974a = savedState5.f2985k;
            lazySpanLookup.f2975b = savedState5.f2986l;
        }
    }

    private void O2(int i7, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        i iVar = this.f2972y;
        boolean z6 = false;
        iVar.f3147b = 0;
        iVar.f3148c = i7;
        if (!x0() || (iC = zVar.c()) == -1) {
            iN = 0;
            iN2 = 0;
        } else {
            if (this.A == (iC < i7)) {
                iN = this.f2968u.n();
                iN2 = 0;
            } else {
                iN2 = this.f2968u.n();
                iN = 0;
            }
        }
        if (M()) {
            this.f2972y.f3151f = this.f2968u.m() - iN2;
            this.f2972y.f3152g = this.f2968u.i() + iN;
        } else {
            this.f2972y.f3152g = this.f2968u.h() + iN;
            this.f2972y.f3151f = -iN2;
        }
        i iVar2 = this.f2972y;
        iVar2.f3153h = false;
        iVar2.f3146a = true;
        if (this.f2968u.k() == 0 && this.f2968u.h() == 0) {
            z6 = true;
        }
        iVar2.f3154i = z6;
    }

    private void Q1(View view, c cVar, i iVar) {
        if (iVar.f3150e == 1) {
            if (cVar.f2999f) {
                M1(view);
                return;
            } else {
                cVar.f2998e.a(view);
                return;
            }
        }
        if (cVar.f2999f) {
            z2(view);
        } else {
            cVar.f2998e.u(view);
        }
    }

    private void Q2(d dVar, int i7, int i8) {
        int iJ = dVar.j();
        if (i7 == -1) {
            if (dVar.o() + iJ <= i8) {
                this.B.set(dVar.f3004e, false);
            }
        } else if (dVar.k() - iJ >= i8) {
            this.B.set(dVar.f3004e, false);
        }
    }

    private int R1(int i7) {
        if (J() == 0) {
            return this.A ? 1 : -1;
        }
        return (i7 < j2()) != this.A ? -1 : 1;
    }

    private int R2(int i7, int i8, int i9) {
        if (i8 == 0 && i9 == 0) {
            return i7;
        }
        int mode = View.MeasureSpec.getMode(i7);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i7) - i8) - i9), mode) : i7;
    }

    private boolean T1(d dVar) {
        if (this.A) {
            if (dVar.k() < this.f2968u.i()) {
                ArrayList<View> arrayList = dVar.f3000a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f2999f;
            }
        } else if (dVar.o() > this.f2968u.m()) {
            return !dVar.n(dVar.f3000a.get(0)).f2999f;
        }
        return false;
    }

    private int U1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return p.a(zVar, this.f2968u, e2(!this.N), d2(!this.N), this, this.N);
    }

    private int V1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return p.b(zVar, this.f2968u, e2(!this.N), d2(!this.N), this, this.N, this.A);
    }

    private int W1(RecyclerView.z zVar) {
        if (J() == 0) {
            return 0;
        }
        return p.c(zVar, this.f2968u, e2(!this.N), d2(!this.N), this, this.N);
    }

    private int X1(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 17 ? i7 != 33 ? i7 != 66 ? (i7 == 130 && this.f2970w == 1) ? 1 : Integer.MIN_VALUE : this.f2970w == 0 ? 1 : Integer.MIN_VALUE : this.f2970w == 1 ? -1 : Integer.MIN_VALUE : this.f2970w == 0 ? -1 : Integer.MIN_VALUE : (this.f2970w != 1 && t2()) ? -1 : 1 : (this.f2970w != 1 && t2()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem Y1(int i7) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f2978h = new int[this.f2966s];
        for (int i8 = 0; i8 < this.f2966s; i8++) {
            fullSpanItem.f2978h[i8] = i7 - this.f2967t[i8].l(i7);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem Z1(int i7) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f2978h = new int[this.f2966s];
        for (int i8 = 0; i8 < this.f2966s; i8++) {
            fullSpanItem.f2978h[i8] = this.f2967t[i8].p(i7) - i7;
        }
        return fullSpanItem;
    }

    private void a2() {
        this.f2968u = m.b(this, this.f2970w);
        this.f2969v = m.b(this, 1 - this.f2970w);
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
    private int b2(RecyclerView.v vVar, i iVar, RecyclerView.z zVar) {
        d dVarP2;
        int iE;
        int i7;
        int iE2;
        int iE3;
        boolean z6;
        ?? r9 = 0;
        this.B.set(0, this.f2966s, true);
        int i8 = this.f2972y.f3154i ? iVar.f3150e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : iVar.f3150e == 1 ? iVar.f3152g + iVar.f3147b : iVar.f3151f - iVar.f3147b;
        K2(iVar.f3150e, i8);
        int i9 = this.A ? this.f2968u.i() : this.f2968u.m();
        ?? r02 = false;
        while (iVar.a(zVar) && (this.f2972y.f3154i || !this.B.isEmpty())) {
            View viewB = iVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.E.g(iA);
            ?? r32 = iG == -1 ? 1 : r9;
            if (r32 != 0) {
                dVarP2 = cVar.f2999f ? this.f2967t[r9] : p2(iVar);
                this.E.n(iA, dVarP2);
            } else {
                dVarP2 = this.f2967t[iG];
            }
            d dVar = dVarP2;
            cVar.f2998e = dVar;
            if (iVar.f3150e == 1) {
                d(viewB);
            } else {
                e(viewB, r9);
            }
            v2(viewB, cVar, r9);
            if (iVar.f3150e == 1) {
                int iL2 = cVar.f2999f ? l2(i9) : dVar.l(i9);
                int iE4 = this.f2968u.e(viewB) + iL2;
                if (r32 != 0 && cVar.f2999f) {
                    LazySpanLookup.FullSpanItem fullSpanItemY1 = Y1(iL2);
                    fullSpanItemY1.f2977g = -1;
                    fullSpanItemY1.f2976e = iA;
                    this.E.a(fullSpanItemY1);
                }
                i7 = iE4;
                iE = iL2;
            } else {
                int iO2 = cVar.f2999f ? o2(i9) : dVar.p(i9);
                iE = iO2 - this.f2968u.e(viewB);
                if (r32 != 0 && cVar.f2999f) {
                    LazySpanLookup.FullSpanItem fullSpanItemZ1 = Z1(iO2);
                    fullSpanItemZ1.f2977g = 1;
                    fullSpanItemZ1.f2976e = iA;
                    this.E.a(fullSpanItemZ1);
                }
                i7 = iO2;
            }
            if (cVar.f2999f && iVar.f3149d == -1) {
                if (r32 != 0) {
                    this.M = true;
                } else {
                    if (!(iVar.f3150e == 1 ? O1() : P1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.E.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f2979i = true;
                        }
                        this.M = true;
                    }
                }
            }
            Q1(viewB, cVar, iVar);
            if (t2() && this.f2970w == 1) {
                int i10 = cVar.f2999f ? this.f2969v.i() : this.f2969v.i() - (((this.f2966s - 1) - dVar.f3004e) * this.f2971x);
                iE3 = i10;
                iE2 = i10 - this.f2969v.e(viewB);
            } else {
                int iM = cVar.f2999f ? this.f2969v.m() : (dVar.f3004e * this.f2971x) + this.f2969v.m();
                iE2 = iM;
                iE3 = this.f2969v.e(viewB) + iM;
            }
            if (this.f2970w == 1) {
                z0(viewB, iE2, iE, iE3, i7);
            } else {
                z0(viewB, iE, iE2, i7, iE3);
            }
            if (cVar.f2999f) {
                K2(this.f2972y.f3150e, i8);
            } else {
                Q2(dVar, this.f2972y.f3150e, i8);
            }
            A2(vVar, this.f2972y);
            if (!this.f2972y.f3153h || !viewB.hasFocusable()) {
                z6 = false;
            } else if (cVar.f2999f) {
                this.B.clear();
                z6 = false;
            } else {
                z6 = false;
                this.B.set(dVar.f3004e, false);
            }
            r9 = z6;
            r02 = true;
        }
        ?? r33 = r9;
        if (r02 == false) {
            A2(vVar, this.f2972y);
        }
        int iM2 = this.f2972y.f3150e == -1 ? this.f2968u.m() - o2(this.f2968u.m()) : l2(this.f2968u.i()) - this.f2968u.i();
        return iM2 > 0 ? Math.min(iVar.f3147b, iM2) : r33 == true ? 1 : 0;
    }

    private int c2(int i7) {
        int iJ = J();
        for (int i8 = 0; i8 < iJ; i8++) {
            int iH0 = h0(I(i8));
            if (iH0 >= 0 && iH0 < i7) {
                return iH0;
            }
        }
        return 0;
    }

    private int g2(int i7) {
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            int iH0 = h0(I(iJ));
            if (iH0 >= 0 && iH0 < i7) {
                return iH0;
            }
        }
        return 0;
    }

    private void h2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z6) {
        int i7;
        int iL2 = l2(Integer.MIN_VALUE);
        if (iL2 != Integer.MIN_VALUE && (i7 = this.f2968u.i() - iL2) > 0) {
            int i8 = i7 - (-F2(-i7, vVar, zVar));
            if (!z6 || i8 <= 0) {
                return;
            }
            this.f2968u.r(i8);
        }
    }

    private void i2(RecyclerView.v vVar, RecyclerView.z zVar, boolean z6) {
        int iM;
        int iO2 = o2(Integer.MAX_VALUE);
        if (iO2 != Integer.MAX_VALUE && (iM = iO2 - this.f2968u.m()) > 0) {
            int iF2 = iM - F2(iM, vVar, zVar);
            if (!z6 || iF2 <= 0) {
                return;
            }
            this.f2968u.r(-iF2);
        }
    }

    private int l2(int i7) {
        int iL = this.f2967t[0].l(i7);
        for (int i8 = 1; i8 < this.f2966s; i8++) {
            int iL2 = this.f2967t[i8].l(i7);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int m2(int i7) {
        int iP = this.f2967t[0].p(i7);
        for (int i8 = 1; i8 < this.f2966s; i8++) {
            int iP2 = this.f2967t[i8].p(i7);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private int n2(int i7) {
        int iL = this.f2967t[0].l(i7);
        for (int i8 = 1; i8 < this.f2966s; i8++) {
            int iL2 = this.f2967t[i8].l(i7);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int o2(int i7) {
        int iP = this.f2967t[0].p(i7);
        for (int i8 = 1; i8 < this.f2966s; i8++) {
            int iP2 = this.f2967t[i8].p(i7);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private d p2(i iVar) {
        int i7;
        int i8;
        int i9 = -1;
        if (x2(iVar.f3150e)) {
            i7 = this.f2966s - 1;
            i8 = -1;
        } else {
            i7 = 0;
            i9 = this.f2966s;
            i8 = 1;
        }
        d dVar = null;
        if (iVar.f3150e == 1) {
            int i10 = Integer.MAX_VALUE;
            int iM = this.f2968u.m();
            while (i7 != i9) {
                d dVar2 = this.f2967t[i7];
                int iL = dVar2.l(iM);
                if (iL < i10) {
                    dVar = dVar2;
                    i10 = iL;
                }
                i7 += i8;
            }
            return dVar;
        }
        int i11 = Integer.MIN_VALUE;
        int i12 = this.f2968u.i();
        while (i7 != i9) {
            d dVar3 = this.f2967t[i7];
            int iP = dVar3.p(i12);
            if (iP > i11) {
                dVar = dVar3;
                i11 = iP;
            }
            i7 += i8;
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

    private void u2(View view, int i7, int i8, boolean z6) {
        j(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iR2 = R2(i7, i9 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i10 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iR22 = R2(i8, i10 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z6 ? H1(view, iR2, iR22, cVar) : F1(view, iR2, iR22, cVar)) {
            view.measure(iR2, iR22);
        }
    }

    private void v2(View view, c cVar, boolean z6) {
        if (cVar.f2999f) {
            if (this.f2970w == 1) {
                u2(view, this.J, RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z6);
                return;
            } else {
                u2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z6);
                return;
            }
        }
        if (this.f2970w == 1) {
            u2(view, RecyclerView.o.K(this.f2971x, p0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.K(W(), X(), g0() + d0(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z6);
        } else {
            u2(view, RecyclerView.o.K(o0(), p0(), e0() + f0(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.K(this.f2971x, X(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z6);
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

    private boolean x2(int i7) {
        if (this.f2970w == 0) {
            return (i7 == -1) != this.A;
        }
        return ((i7 == -1) == this.A) == t2();
    }

    private void z2(View view) {
        for (int i7 = this.f2966s - 1; i7 >= 0; i7--) {
            this.f2967t[i7].u(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C0(int i7) {
        super.C0(i7);
        for (int i8 = 0; i8 < this.f2966s; i8++) {
            this.f2967t[i8].r(i7);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C1(Rect rect, int i7, int i8) {
        int iN;
        int iN2;
        int iE0 = e0() + f0();
        int iG0 = g0() + d0();
        if (this.f2970w == 1) {
            iN2 = RecyclerView.o.n(i8, rect.height() + iG0, b0());
            iN = RecyclerView.o.n(i7, (this.f2971x * this.f2966s) + iE0, c0());
        } else {
            iN = RecyclerView.o.n(i7, rect.width() + iE0, c0());
            iN2 = RecyclerView.o.n(i8, (this.f2971x * this.f2966s) + iG0, b0());
        }
        B1(iN, iN2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return this.f2970w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void D0(int i7) {
        super.D0(i7);
        for (int i8 = 0; i8 < this.f2966s; i8++) {
            this.f2967t[i8].r(i7);
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

    int F2(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (J() == 0 || i7 == 0) {
            return 0;
        }
        y2(i7, zVar);
        int iB2 = b2(vVar, this.f2972y, zVar);
        if (this.f2972y.f3147b >= iB2) {
            i7 = i7 < 0 ? -iB2 : iB2;
        }
        this.f2968u.r(-i7);
        this.G = this.A;
        i iVar = this.f2972y;
        iVar.f3147b = 0;
        A2(vVar, iVar);
        return i7;
    }

    public void H2(int i7) {
        if (i7 != 0 && i7 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        g(null);
        if (i7 == this.f2970w) {
            return;
        }
        this.f2970w = i7;
        m mVar = this.f2968u;
        this.f2968u = this.f2969v;
        this.f2969v = mVar;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I0(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.I0(recyclerView, vVar);
        o1(this.P);
        for (int i7 = 0; i7 < this.f2966s; i7++) {
            this.f2967t[i7].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void I1(RecyclerView recyclerView, RecyclerView.z zVar, int i7) {
        j jVar = new j(recyclerView.getContext());
        jVar.p(i7);
        J1(jVar);
    }

    public void I2(boolean z6) {
        g(null);
        SavedState savedState = this.I;
        if (savedState != null && savedState.f2987m != z6) {
            savedState.f2987m = z6;
        }
        this.f2973z = z6;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View J0(View view, int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewB;
        View viewM;
        if (J() == 0 || (viewB = B(view)) == null) {
            return null;
        }
        E2();
        int iX1 = X1(i7);
        if (iX1 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewB.getLayoutParams();
        boolean z6 = cVar.f2999f;
        d dVar = cVar.f2998e;
        int iK2 = iX1 == 1 ? k2() : j2();
        O2(iK2, zVar);
        G2(iX1);
        i iVar = this.f2972y;
        iVar.f3148c = iVar.f3149d + iK2;
        iVar.f3147b = (int) (this.f2968u.n() * 0.33333334f);
        i iVar2 = this.f2972y;
        iVar2.f3153h = true;
        iVar2.f3146a = false;
        b2(vVar, iVar2, zVar);
        this.G = this.A;
        if (!z6 && (viewM = dVar.m(iK2, iX1)) != null && viewM != viewB) {
            return viewM;
        }
        if (x2(iX1)) {
            for (int i8 = this.f2966s - 1; i8 >= 0; i8--) {
                View viewM2 = this.f2967t[i8].m(iK2, iX1);
                if (viewM2 != null && viewM2 != viewB) {
                    return viewM2;
                }
            }
        } else {
            for (int i9 = 0; i9 < this.f2966s; i9++) {
                View viewM3 = this.f2967t[i9].m(iK2, iX1);
                if (viewM3 != null && viewM3 != viewB) {
                    return viewM3;
                }
            }
        }
        boolean z7 = (this.f2973z ^ true) == (iX1 == -1);
        if (!z6) {
            View viewC = C(z7 ? dVar.f() : dVar.g());
            if (viewC != null && viewC != viewB) {
                return viewC;
            }
        }
        if (x2(iX1)) {
            for (int i10 = this.f2966s - 1; i10 >= 0; i10--) {
                if (i10 != dVar.f3004e) {
                    View viewC2 = C(z7 ? this.f2967t[i10].f() : this.f2967t[i10].g());
                    if (viewC2 != null && viewC2 != viewB) {
                        return viewC2;
                    }
                }
            }
        } else {
            for (int i11 = 0; i11 < this.f2966s; i11++) {
                View viewC3 = C(z7 ? this.f2967t[i11].f() : this.f2967t[i11].g());
                if (viewC3 != null && viewC3 != viewB) {
                    return viewC3;
                }
            }
        }
        return null;
    }

    public void J2(int i7) {
        g(null);
        if (i7 != this.f2966s) {
            s2();
            this.f2966s = i7;
            this.B = new BitSet(this.f2966s);
            this.f2967t = new d[this.f2966s];
            for (int i8 = 0; i8 < this.f2966s; i8++) {
                this.f2967t[i8] = new d(i8);
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
        int i7;
        if (!zVar.e() && (i7 = this.C) != -1) {
            if (i7 >= 0 && i7 < zVar.b()) {
                SavedState savedState = this.I;
                if (savedState == null || savedState.f2980e == -1 || savedState.f2982h < 1) {
                    View viewC = C(this.C);
                    if (viewC != null) {
                        bVar.f2991a = this.A ? k2() : j2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f2993c) {
                                bVar.f2992b = (this.f2968u.i() - this.D) - this.f2968u.d(viewC);
                            } else {
                                bVar.f2992b = (this.f2968u.m() + this.D) - this.f2968u.g(viewC);
                            }
                            return true;
                        }
                        if (this.f2968u.e(viewC) > this.f2968u.n()) {
                            bVar.f2992b = bVar.f2993c ? this.f2968u.i() : this.f2968u.m();
                            return true;
                        }
                        int iG = this.f2968u.g(viewC) - this.f2968u.m();
                        if (iG < 0) {
                            bVar.f2992b = -iG;
                            return true;
                        }
                        int i8 = this.f2968u.i() - this.f2968u.d(viewC);
                        if (i8 < 0) {
                            bVar.f2992b = i8;
                            return true;
                        }
                        bVar.f2992b = Integer.MIN_VALUE;
                    } else {
                        int i9 = this.C;
                        bVar.f2991a = i9;
                        int i10 = this.D;
                        if (i10 == Integer.MIN_VALUE) {
                            bVar.f2993c = R1(i9) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i10);
                        }
                        bVar.f2994d = true;
                    }
                } else {
                    bVar.f2992b = Integer.MIN_VALUE;
                    bVar.f2991a = this.C;
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
        return this.f2970w == 1 ? this.f2966s : super.N(vVar, zVar);
    }

    void N2(RecyclerView.z zVar, b bVar) {
        if (M2(zVar, bVar) || L2(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f2991a = 0;
    }

    boolean O1() {
        int iL = this.f2967t[0].l(Integer.MIN_VALUE);
        for (int i7 = 1; i7 < this.f2966s; i7++) {
            if (this.f2967t[i7].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView.v vVar, RecyclerView.z zVar, View view, j0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.O0(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.f2970w == 0) {
            cVar.S(c.C0090c.a(cVar2.e(), cVar2.f2999f ? this.f2966s : 1, -1, -1, false, false));
        } else {
            cVar.S(c.C0090c.a(-1, -1, cVar2.e(), cVar2.f2999f ? this.f2966s : 1, false, false));
        }
    }

    boolean P1() {
        int iP = this.f2967t[0].p(Integer.MIN_VALUE);
        for (int i7 = 1; i7 < this.f2966s; i7++) {
            if (this.f2967t[i7].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    void P2(int i7) {
        this.f2971x = i7 / this.f2966s;
        this.J = View.MeasureSpec.makeMeasureSpec(i7, this.f2969v.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i7, int i8) {
        q2(i7, i8, 1);
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
        int i7 = this.A ? -1 : 1;
        int i8 = iK2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.E.e(iJ2, i8, i7, true);
        if (fullSpanItemE == null) {
            this.M = false;
            this.E.d(i8);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.E.e(iJ2, fullSpanItemE.f2976e, i7 * (-1), true);
        if (fullSpanItemE2 == null) {
            this.E.d(fullSpanItemE.f2976e);
        } else {
            this.E.d(fullSpanItemE2.f2976e + 1);
        }
        u1();
        t1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i7, int i8, int i9) {
        q2(i7, i8, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i7, int i8) {
        q2(i7, i8, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i7, int i8, Object obj) {
        q2(i7, i8, 4);
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
    public PointF a(int i7) {
        int iR1 = R1(i7);
        PointF pointF = new PointF();
        if (iR1 == 0) {
            return null;
        }
        if (this.f2970w == 0) {
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
        savedState.f2987m = this.f2973z;
        savedState.f2988n = this.G;
        savedState.f2989o = this.H;
        LazySpanLookup lazySpanLookup = this.E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f2974a) == null) {
            savedState.f2984j = 0;
        } else {
            savedState.f2985k = iArr;
            savedState.f2984j = iArr.length;
            savedState.f2986l = lazySpanLookup.f2975b;
        }
        if (J() > 0) {
            savedState.f2980e = this.G ? k2() : j2();
            savedState.f2981g = f2();
            int i7 = this.f2966s;
            savedState.f2982h = i7;
            savedState.f2983i = new int[i7];
            for (int i8 = 0; i8 < this.f2966s; i8++) {
                if (this.G) {
                    iP = this.f2967t[i8].l(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.f2968u.i();
                        iP -= iM;
                    }
                } else {
                    iP = this.f2967t[i8].p(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.f2968u.m();
                        iP -= iM;
                    }
                }
                savedState.f2983i[i8] = iP;
            }
        } else {
            savedState.f2980e = -1;
            savedState.f2981g = -1;
            savedState.f2982h = 0;
        }
        return savedState;
    }

    View d2(boolean z6) {
        int iM = this.f2968u.m();
        int i7 = this.f2968u.i();
        View view = null;
        for (int iJ = J() - 1; iJ >= 0; iJ--) {
            View viewI = I(iJ);
            int iG = this.f2968u.g(viewI);
            int iD = this.f2968u.d(viewI);
            if (iD > iM && iG < i7) {
                if (iD <= i7 || !z6) {
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
    public void e1(int i7) {
        if (i7 == 0) {
            S1();
        }
    }

    View e2(boolean z6) {
        int iM = this.f2968u.m();
        int i7 = this.f2968u.i();
        int iJ = J();
        View view = null;
        for (int i8 = 0; i8 < iJ; i8++) {
            View viewI = I(i8);
            int iG = this.f2968u.g(viewI);
            if (this.f2968u.d(viewI) > iM && iG < i7) {
                if (iG >= iM || !z6) {
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
        return this.f2970w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int k0(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f2970w == 0 ? this.f2966s : super.k0(vVar, zVar);
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
        return this.f2970w == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean m(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o(int i7, int i8, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.f2970w != 0) {
            i7 = i8;
        }
        if (J() == 0 || i7 == 0) {
            return;
        }
        y2(i7, zVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.f2966s) {
            this.O = new int[this.f2966s];
        }
        int i9 = 0;
        for (int i10 = 0; i10 < this.f2966s; i10++) {
            i iVar = this.f2972y;
            if (iVar.f3149d == -1) {
                iL = iVar.f3151f;
                iP = this.f2967t[i10].p(iL);
            } else {
                iL = this.f2967t[i10].l(iVar.f3152g);
                iP = this.f2972y.f3152g;
            }
            int i11 = iL - iP;
            if (i11 >= 0) {
                this.O[i9] = i11;
                i9++;
            }
        }
        Arrays.sort(this.O, 0, i9);
        for (int i12 = 0; i12 < i9 && this.f2972y.a(zVar); i12++) {
            cVar.a(this.f2972y.f3148c, this.O[i12]);
            i iVar2 = this.f2972y;
            iVar2.f3148c += iVar2.f3149d;
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
            int r3 = r12.f2966s
            r2.<init>(r3)
            int r3 = r12.f2966s
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.f2970w
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f2998e
            int r9 = r9.f3004e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f2998e
            boolean r9 = r12.T1(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f2998e
            int r9 = r9.f3004e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f2999f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.I(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.m r10 = r12.f2968u
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.m r11 = r12.f2968u
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.m r10 = r12.f2968u
            int r10 = r10.g(r7)
            androidx.recyclerview.widget.m r11 = r12.f2968u
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f2998e
            int r8 = r8.f3004e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f2998e
            int r9 = r9.f3004e
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
    public int w1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i7, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void x1(int i7) {
        SavedState savedState = this.I;
        if (savedState != null && savedState.f2980e != i7) {
            savedState.b();
        }
        this.C = i7;
        this.D = Integer.MIN_VALUE;
        t1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        return F2(i7, vVar, zVar);
    }

    void y2(int i7, RecyclerView.z zVar) {
        int i8;
        int iJ2;
        if (i7 > 0) {
            iJ2 = k2();
            i8 = 1;
        } else {
            i8 = -1;
            iJ2 = j2();
        }
        this.f2972y.f3146a = true;
        O2(iJ2, zVar);
        G2(i8);
        i iVar = this.f2972y;
        iVar.f3148c = iJ2 + iVar.f3149d;
        iVar.f3147b = Math.abs(i7);
    }

    static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int[] f2974a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        List<FullSpanItem> f2975b;

        LazySpanLookup() {
        }

        private int i(int i7) {
            if (this.f2975b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i7);
            if (fullSpanItemF != null) {
                this.f2975b.remove(fullSpanItemF);
            }
            int size = this.f2975b.size();
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    i8 = -1;
                    break;
                }
                if (this.f2975b.get(i8).f2976e >= i7) {
                    break;
                }
                i8++;
            }
            if (i8 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f2975b.get(i8);
            this.f2975b.remove(i8);
            return fullSpanItem.f2976e;
        }

        private void l(int i7, int i8) {
            List<FullSpanItem> list = this.f2975b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f2975b.get(size);
                int i9 = fullSpanItem.f2976e;
                if (i9 >= i7) {
                    fullSpanItem.f2976e = i9 + i8;
                }
            }
        }

        private void m(int i7, int i8) {
            List<FullSpanItem> list = this.f2975b;
            if (list == null) {
                return;
            }
            int i9 = i7 + i8;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f2975b.get(size);
                int i10 = fullSpanItem.f2976e;
                if (i10 >= i7) {
                    if (i10 < i9) {
                        this.f2975b.remove(size);
                    } else {
                        fullSpanItem.f2976e = i10 - i8;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.f2975b == null) {
                this.f2975b = new ArrayList();
            }
            int size = this.f2975b.size();
            for (int i7 = 0; i7 < size; i7++) {
                FullSpanItem fullSpanItem2 = this.f2975b.get(i7);
                if (fullSpanItem2.f2976e == fullSpanItem.f2976e) {
                    this.f2975b.remove(i7);
                }
                if (fullSpanItem2.f2976e >= fullSpanItem.f2976e) {
                    this.f2975b.add(i7, fullSpanItem);
                    return;
                }
            }
            this.f2975b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.f2974a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f2975b = null;
        }

        void c(int i7) {
            int[] iArr = this.f2974a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i7, 10) + 1];
                this.f2974a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i7 >= iArr.length) {
                int[] iArr3 = new int[o(i7)];
                this.f2974a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f2974a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i7) {
            List<FullSpanItem> list = this.f2975b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f2975b.get(size).f2976e >= i7) {
                        this.f2975b.remove(size);
                    }
                }
            }
            return h(i7);
        }

        public FullSpanItem e(int i7, int i8, int i9, boolean z6) {
            List<FullSpanItem> list = this.f2975b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                FullSpanItem fullSpanItem = this.f2975b.get(i10);
                int i11 = fullSpanItem.f2976e;
                if (i11 >= i8) {
                    return null;
                }
                if (i11 >= i7 && (i9 == 0 || fullSpanItem.f2977g == i9 || (z6 && fullSpanItem.f2979i))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i7) {
            List<FullSpanItem> list = this.f2975b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f2975b.get(size);
                if (fullSpanItem.f2976e == i7) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i7) {
            int[] iArr = this.f2974a;
            if (iArr == null || i7 >= iArr.length) {
                return -1;
            }
            return iArr[i7];
        }

        int h(int i7) {
            int[] iArr = this.f2974a;
            if (iArr == null || i7 >= iArr.length) {
                return -1;
            }
            int i8 = i(i7);
            if (i8 == -1) {
                int[] iArr2 = this.f2974a;
                Arrays.fill(iArr2, i7, iArr2.length, -1);
                return this.f2974a.length;
            }
            int i9 = i8 + 1;
            Arrays.fill(this.f2974a, i7, i9, -1);
            return i9;
        }

        void j(int i7, int i8) {
            int[] iArr = this.f2974a;
            if (iArr == null || i7 >= iArr.length) {
                return;
            }
            int i9 = i7 + i8;
            c(i9);
            int[] iArr2 = this.f2974a;
            System.arraycopy(iArr2, i7, iArr2, i9, (iArr2.length - i7) - i8);
            Arrays.fill(this.f2974a, i7, i9, -1);
            l(i7, i8);
        }

        void k(int i7, int i8) {
            int[] iArr = this.f2974a;
            if (iArr == null || i7 >= iArr.length) {
                return;
            }
            int i9 = i7 + i8;
            c(i9);
            int[] iArr2 = this.f2974a;
            System.arraycopy(iArr2, i9, iArr2, i7, (iArr2.length - i7) - i8);
            int[] iArr3 = this.f2974a;
            Arrays.fill(iArr3, iArr3.length - i8, iArr3.length, -1);
            m(i7, i8);
        }

        void n(int i7, d dVar) {
            c(i7);
            this.f2974a[i7] = dVar.f3004e;
        }

        int o(int i7) {
            int length = this.f2974a.length;
            while (length <= i7) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            int f2976e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            int f2977g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            int[] f2978h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            boolean f2979i;

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
                public FullSpanItem[] newArray(int i7) {
                    return new FullSpanItem[i7];
                }
            }

            FullSpanItem(Parcel parcel) {
                this.f2976e = parcel.readInt();
                this.f2977g = parcel.readInt();
                this.f2979i = parcel.readInt() == 1;
                int i7 = parcel.readInt();
                if (i7 > 0) {
                    int[] iArr = new int[i7];
                    this.f2978h = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int b(int i7) {
                int[] iArr = this.f2978h;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i7];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f2976e + ", mGapDir=" + this.f2977g + ", mHasUnwantedGapAfter=" + this.f2979i + ", mGapPerSpan=" + Arrays.toString(this.f2978h) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i7) {
                parcel.writeInt(this.f2976e);
                parcel.writeInt(this.f2977g);
                parcel.writeInt(this.f2979i ? 1 : 0);
                int[] iArr = this.f2978h;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f2978h);
                }
            }

            FullSpanItem() {
            }
        }
    }
}
