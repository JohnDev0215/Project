package androidx.recyclerview.widget;

import a.g.k.c0.c;
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

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.z.b {
    private BitSet B;
    private boolean G;
    private boolean H;
    private e I;
    private int J;
    private int[] O;
    f[] t;
    k u;
    k v;
    private int w;
    private int x;
    private final g y;
    private int s = -1;
    boolean z = false;
    boolean A = false;
    int C = -1;
    int D = Integer.MIN_VALUE;
    d E = new d();
    private int F = 2;
    private final Rect K = new Rect();
    private final b L = new b();
    private boolean M = false;
    private boolean N = true;
    private final Runnable P = new a();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.o2();
        }
    }

    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1504a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1505b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1506c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1507d;
        boolean e;
        int[] f;

        b() {
            c();
        }

        void a() {
            this.f1505b = this.f1506c ? StaggeredGridLayoutManager.this.u.i() : StaggeredGridLayoutManager.this.u.m();
        }

        void b(int i) {
            this.f1505b = this.f1506c ? StaggeredGridLayoutManager.this.u.i() - i : StaggeredGridLayoutManager.this.u.m() + i;
        }

        void c() {
            this.f1504a = -1;
            this.f1505b = Integer.MIN_VALUE;
            this.f1506c = false;
            this.f1507d = false;
            this.e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(f[] fVarArr) {
            int length = fVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = fVarArr[i].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {
        f f;
        boolean g;

        public c(int i, int i2) {
            super(i, i2);
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public final int y() {
            f fVar = this.f;
            if (fVar == null) {
                return -1;
            }
            return fVar.e;
        }

        public boolean z() {
            return this.g;
        }
    }

    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int[] f1508a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        List<a> f1509b;

        @SuppressLint({"BanParcelableUsage"})
        static class a implements Parcelable {
            public static final Parcelable.Creator<a> CREATOR = new C0044a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f1510b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1511c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int[] f1512d;
            boolean e;

            /* JADX INFO: renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a$a, reason: collision with other inner class name */
            static class C0044a implements Parcelable.Creator<a> {
                C0044a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public a createFromParcel(Parcel parcel) {
                    return new a(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public a[] newArray(int i) {
                    return new a[i];
                }
            }

            a() {
            }

            a(Parcel parcel) {
                this.f1510b = parcel.readInt();
                this.f1511c = parcel.readInt();
                this.e = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.f1512d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1510b + ", mGapDir=" + this.f1511c + ", mHasUnwantedGapAfter=" + this.e + ", mGapPerSpan=" + Arrays.toString(this.f1512d) + '}';
            }

            int u(int i) {
                int[] iArr = this.f1512d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1510b);
                parcel.writeInt(this.f1511c);
                parcel.writeInt(this.e ? 1 : 0);
                int[] iArr = this.f1512d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1512d);
                }
            }
        }

        d() {
        }

        private int i(int i) {
            if (this.f1509b == null) {
                return -1;
            }
            a aVarF = f(i);
            if (aVarF != null) {
                this.f1509b.remove(aVarF);
            }
            int size = this.f1509b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                if (this.f1509b.get(i2).f1510b >= i) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return -1;
            }
            a aVar = this.f1509b.get(i2);
            this.f1509b.remove(i2);
            return aVar.f1510b;
        }

        private void l(int i, int i2) {
            List<a> list = this.f1509b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1509b.get(size);
                int i3 = aVar.f1510b;
                if (i3 >= i) {
                    aVar.f1510b = i3 + i2;
                }
            }
        }

        private void m(int i, int i2) {
            List<a> list = this.f1509b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1509b.get(size);
                int i4 = aVar.f1510b;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.f1509b.remove(size);
                    } else {
                        aVar.f1510b = i4 - i2;
                    }
                }
            }
        }

        public void a(a aVar) {
            if (this.f1509b == null) {
                this.f1509b = new ArrayList();
            }
            int size = this.f1509b.size();
            for (int i = 0; i < size; i++) {
                a aVar2 = this.f1509b.get(i);
                if (aVar2.f1510b == aVar.f1510b) {
                    this.f1509b.remove(i);
                }
                if (aVar2.f1510b >= aVar.f1510b) {
                    this.f1509b.add(i, aVar);
                    return;
                }
            }
            this.f1509b.add(aVar);
        }

        void b() {
            int[] iArr = this.f1508a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1509b = null;
        }

        void c(int i) {
            int[] iArr = this.f1508a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1508a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[o(i)];
                this.f1508a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1508a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i) {
            List<a> list = this.f1509b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1509b.get(size).f1510b >= i) {
                        this.f1509b.remove(size);
                    }
                }
            }
            return h(i);
        }

        public a e(int i, int i2, int i3, boolean z) {
            List<a> list = this.f1509b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                a aVar = this.f1509b.get(i4);
                int i5 = aVar.f1510b;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || aVar.f1511c == i3 || (z && aVar.e))) {
                    return aVar;
                }
            }
            return null;
        }

        public a f(int i) {
            List<a> list = this.f1509b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1509b.get(size);
                if (aVar.f1510b == i) {
                    return aVar;
                }
            }
            return null;
        }

        int g(int i) {
            int[] iArr = this.f1508a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        int h(int i) {
            int[] iArr = this.f1508a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int i2 = i(i);
            if (i2 == -1) {
                int[] iArr2 = this.f1508a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.f1508a.length;
            }
            int i3 = i2 + 1;
            Arrays.fill(this.f1508a, i, i3, -1);
            return i3;
        }

        void j(int i, int i2) {
            int[] iArr = this.f1508a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1508a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1508a, i, i3, -1);
            l(i, i2);
        }

        void k(int i, int i2) {
            int[] iArr = this.f1508a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1508a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1508a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            m(i, i2);
        }

        void n(int i, f fVar) {
            c(i);
            this.f1508a[i] = fVar.e;
        }

        int o(int i) {
            int length = this.f1508a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1513b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1514c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1515d;
        int[] e;
        int f;
        int[] g;
        List<d.a> h;
        boolean i;
        boolean j;
        boolean k;

        static class a implements Parcelable.Creator<e> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public e[] newArray(int i) {
                return new e[i];
            }
        }

        public e() {
        }

        e(Parcel parcel) {
            this.f1513b = parcel.readInt();
            this.f1514c = parcel.readInt();
            int i = parcel.readInt();
            this.f1515d = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.e = iArr;
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
            this.h = parcel.readArrayList(d.a.class.getClassLoader());
        }

        public e(e eVar) {
            this.f1515d = eVar.f1515d;
            this.f1513b = eVar.f1513b;
            this.f1514c = eVar.f1514c;
            this.e = eVar.e;
            this.f = eVar.f;
            this.g = eVar.g;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.h = eVar.h;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        void u() {
            this.e = null;
            this.f1515d = 0;
            this.f1513b = -1;
            this.f1514c = -1;
        }

        void v() {
            this.e = null;
            this.f1515d = 0;
            this.f = 0;
            this.g = null;
            this.h = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1513b);
            parcel.writeInt(this.f1514c);
            parcel.writeInt(this.f1515d);
            if (this.f1515d > 0) {
                parcel.writeIntArray(this.e);
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
    }

    class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ArrayList<View> f1516a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1517b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1518c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1519d = 0;
        final int e;

        f(int i) {
            this.e = i;
        }

        void a(View view) {
            c cVarN = n(view);
            cVarN.f = this;
            this.f1516a.add(view);
            this.f1518c = Integer.MIN_VALUE;
            if (this.f1516a.size() == 1) {
                this.f1517b = Integer.MIN_VALUE;
            }
            if (cVarN.w() || cVarN.v()) {
                this.f1519d += StaggeredGridLayoutManager.this.u.e(view);
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
                    this.f1518c = iL;
                    this.f1517b = iL;
                }
            }
        }

        void c() {
            d.a aVarF;
            ArrayList<View> arrayList = this.f1516a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1518c = StaggeredGridLayoutManager.this.u.d(view);
            if (cVarN.g && (aVarF = StaggeredGridLayoutManager.this.E.f(cVarN.u())) != null && aVarF.f1511c == 1) {
                this.f1518c += aVarF.u(this.e);
            }
        }

        void d() {
            d.a aVarF;
            View view = this.f1516a.get(0);
            c cVarN = n(view);
            this.f1517b = StaggeredGridLayoutManager.this.u.g(view);
            if (cVarN.g && (aVarF = StaggeredGridLayoutManager.this.E.f(cVarN.u())) != null && aVarF.f1511c == -1) {
                this.f1517b -= aVarF.u(this.e);
            }
        }

        void e() {
            this.f1516a.clear();
            q();
            this.f1519d = 0;
        }

        public int f() {
            int size;
            int size2;
            if (StaggeredGridLayoutManager.this.z) {
                size = this.f1516a.size() - 1;
                size2 = -1;
            } else {
                size = 0;
                size2 = this.f1516a.size();
            }
            return i(size, size2, true);
        }

        public int g() {
            int size;
            int size2;
            if (StaggeredGridLayoutManager.this.z) {
                size = 0;
                size2 = this.f1516a.size();
            } else {
                size = this.f1516a.size() - 1;
                size2 = -1;
            }
            return i(size, size2, true);
        }

        int h(int i, int i2, boolean z, boolean z2, boolean z3) {
            int iM = StaggeredGridLayoutManager.this.u.m();
            int i3 = StaggeredGridLayoutManager.this.u.i();
            int i4 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1516a.get(i);
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
                            return StaggeredGridLayoutManager.this.z0(view);
                        }
                    } else if (z2 || iG < iM || iD > i3) {
                        return StaggeredGridLayoutManager.this.z0(view);
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
            return this.f1519d;
        }

        int k() {
            int i = this.f1518c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            c();
            return this.f1518c;
        }

        int l(int i) {
            int i2 = this.f1518c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1516a.size() == 0) {
                return i;
            }
            c();
            return this.f1518c;
        }

        public View m(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.f1516a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1516a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.z0(view2) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.z0(view2) <= i) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1516a.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.f1516a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.z0(view3) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.z0(view3) >= i) || !view3.hasFocusable()) {
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
            int i = this.f1517b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            d();
            return this.f1517b;
        }

        int p(int i) {
            int i2 = this.f1517b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1516a.size() == 0) {
                return i;
            }
            d();
            return this.f1517b;
        }

        void q() {
            this.f1517b = Integer.MIN_VALUE;
            this.f1518c = Integer.MIN_VALUE;
        }

        void r(int i) {
            int i2 = this.f1517b;
            if (i2 != Integer.MIN_VALUE) {
                this.f1517b = i2 + i;
            }
            int i3 = this.f1518c;
            if (i3 != Integer.MIN_VALUE) {
                this.f1518c = i3 + i;
            }
        }

        void s() {
            int size = this.f1516a.size();
            View viewRemove = this.f1516a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f = null;
            if (cVarN.w() || cVarN.v()) {
                this.f1519d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            if (size == 1) {
                this.f1517b = Integer.MIN_VALUE;
            }
            this.f1518c = Integer.MIN_VALUE;
        }

        void t() {
            View viewRemove = this.f1516a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f = null;
            if (this.f1516a.size() == 0) {
                this.f1518c = Integer.MIN_VALUE;
            }
            if (cVarN.w() || cVarN.v()) {
                this.f1519d -= StaggeredGridLayoutManager.this.u.e(viewRemove);
            }
            this.f1517b = Integer.MIN_VALUE;
        }

        void u(View view) {
            c cVarN = n(view);
            cVarN.f = this;
            this.f1516a.add(0, view);
            this.f1517b = Integer.MIN_VALUE;
            if (this.f1516a.size() == 1) {
                this.f1518c = Integer.MIN_VALUE;
            }
            if (cVarN.w() || cVarN.v()) {
                this.f1519d += StaggeredGridLayoutManager.this.u.e(view);
            }
        }

        void v(int i) {
            this.f1517b = i;
            this.f1518c = i;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d dVarA0 = RecyclerView.o.A0(context, attributeSet, i, i2);
        e3(dVarA0.f1476a);
        g3(dVarA0.f1477b);
        f3(dVarA0.f1478c);
        this.y = new g();
        w2();
    }

    private int C2(int i) {
        for (int iF0 = f0() - 1; iF0 >= 0; iF0--) {
            int iZ0 = z0(e0(iF0));
            if (iZ0 >= 0 && iZ0 < i) {
                return iZ0;
            }
        }
        return 0;
    }

    private void D2(RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int i;
        int iH2 = H2(Integer.MIN_VALUE);
        if (iH2 != Integer.MIN_VALUE && (i = this.u.i() - iH2) > 0) {
            int i2 = i - (-c3(-i, vVar, a0Var));
            if (!z || i2 <= 0) {
                return;
            }
            this.u.r(i2);
        }
    }

    private void E2(RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int iM;
        int iK2 = K2(Integer.MAX_VALUE);
        if (iK2 != Integer.MAX_VALUE && (iM = iK2 - this.u.m()) > 0) {
            int iC3 = iM - c3(iM, vVar, a0Var);
            if (!z || iC3 <= 0) {
                return;
            }
            this.u.r(-iC3);
        }
    }

    private int H2(int i) {
        int iL = this.t[0].l(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iL2 = this.t[i2].l(i);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int I2(int i) {
        int iP = this.t[0].p(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iP2 = this.t[i2].p(i);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private int J2(int i) {
        int iL = this.t[0].l(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iL2 = this.t[i2].l(i);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    private int K2(int i) {
        int iP = this.t[0].p(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iP2 = this.t[i2].p(i);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    private f L2(g gVar) {
        int i;
        int i2;
        int i3 = -1;
        if (U2(gVar.e)) {
            i = this.s - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.s;
            i2 = 1;
        }
        f fVar = null;
        if (gVar.e == 1) {
            int i4 = Integer.MAX_VALUE;
            int iM = this.u.m();
            while (i != i3) {
                f fVar2 = this.t[i];
                int iL = fVar2.l(iM);
                if (iL < i4) {
                    fVar = fVar2;
                    i4 = iL;
                }
                i += i2;
            }
            return fVar;
        }
        int i5 = Integer.MIN_VALUE;
        int i6 = this.u.i();
        while (i != i3) {
            f fVar3 = this.t[i];
            int iP = fVar3.p(i6);
            if (iP > i5) {
                fVar = fVar3;
                i5 = iP;
            }
            i += i2;
        }
        return fVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void N2(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.A
            if (r0 == 0) goto L9
            int r0 = r6.G2()
            goto Ld
        L9:
            int r0 = r6.F2()
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r4 = r6.E
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r7 = r6.E
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.A
            if (r7 == 0) goto L4d
            int r7 = r6.F2()
            goto L51
        L4d:
            int r7 = r6.G2()
        L51:
            if (r3 > r7) goto L56
            r6.O1()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.N2(int, int, int):void");
    }

    private void R2(View view, int i, int i2, boolean z) {
        F(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iO3 = o3(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iO32 = o3(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? d2(view, iO3, iO32, cVar) : b2(view, iO3, iO32, cVar)) {
            view.measure(iO3, iO32);
        }
    }

    private void S2(View view, c cVar, boolean z) {
        int iG0;
        int iG02;
        if (cVar.g) {
            if (this.w != 1) {
                R2(view, RecyclerView.o.g0(G0(), H0(), l() + b(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z);
                return;
            }
            iG0 = this.J;
        } else {
            if (this.w != 1) {
                iG0 = RecyclerView.o.g0(G0(), H0(), l() + b(), ((ViewGroup.MarginLayoutParams) cVar).width, true);
                iG02 = RecyclerView.o.g0(this.x, t0(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false);
                R2(view, iG0, iG02, z);
            }
            iG0 = RecyclerView.o.g0(this.x, H0(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false);
        }
        iG02 = RecyclerView.o.g0(s0(), t0(), j() + e(), ((ViewGroup.MarginLayoutParams) cVar).height, true);
        R2(view, iG0, iG02, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void T2(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.a0 r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.T2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0, boolean):void");
    }

    private boolean U2(int i) {
        if (this.w == 0) {
            return (i == -1) != this.A;
        }
        return ((i == -1) == this.A) == Q2();
    }

    private void W2(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].u(view);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0010, code lost:
    
        if (r4.e == (-1)) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void X2(androidx.recyclerview.widget.RecyclerView.v r3, androidx.recyclerview.widget.g r4) {
        /*
            r2 = this;
            boolean r0 = r4.f1591a
            if (r0 == 0) goto L4d
            boolean r0 = r4.i
            if (r0 == 0) goto L9
            goto L4d
        L9:
            int r0 = r4.f1592b
            r1 = -1
            if (r0 != 0) goto L1e
            int r0 = r4.e
            if (r0 != r1) goto L18
        L12:
            int r4 = r4.g
        L14:
            r2.Y2(r3, r4)
            goto L4d
        L18:
            int r4 = r4.f
        L1a:
            r2.Z2(r3, r4)
            goto L4d
        L1e:
            int r0 = r4.e
            if (r0 != r1) goto L37
            int r0 = r4.f
            int r1 = r2.I2(r0)
            int r0 = r0 - r1
            if (r0 >= 0) goto L2c
            goto L12
        L2c:
            int r1 = r4.g
            int r4 = r4.f1592b
            int r4 = java.lang.Math.min(r0, r4)
            int r4 = r1 - r4
            goto L14
        L37:
            int r0 = r4.g
            int r0 = r2.J2(r0)
            int r1 = r4.g
            int r0 = r0 - r1
            if (r0 >= 0) goto L43
            goto L18
        L43:
            int r1 = r4.f
            int r4 = r4.f1592b
            int r4 = java.lang.Math.min(r0, r4)
            int r4 = r4 + r1
            goto L1a
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.X2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.g):void");
    }

    private void Y2(RecyclerView.v vVar, int i) {
        for (int iF0 = f0() - 1; iF0 >= 0; iF0--) {
            View viewE0 = e0(iF0);
            if (this.u.g(viewE0) < i || this.u.q(viewE0) < i) {
                return;
            }
            c cVar = (c) viewE0.getLayoutParams();
            if (cVar.g) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1516a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].s();
                }
            } else if (cVar.f.f1516a.size() == 1) {
                return;
            } else {
                cVar.f.s();
            }
            H1(viewE0, vVar);
        }
    }

    private void Z2(RecyclerView.v vVar, int i) {
        while (f0() > 0) {
            View viewE0 = e0(0);
            if (this.u.d(viewE0) > i || this.u.p(viewE0) > i) {
                return;
            }
            c cVar = (c) viewE0.getLayoutParams();
            if (cVar.g) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1516a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].t();
                }
            } else if (cVar.f.f1516a.size() == 1) {
                return;
            } else {
                cVar.f.t();
            }
            H1(viewE0, vVar);
        }
    }

    private void a3() {
        if (this.v.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int iF0 = f0();
        for (int i = 0; i < iF0; i++) {
            View viewE0 = e0(i);
            float fE = this.v.e(viewE0);
            if (fE >= fMax) {
                if (((c) viewE0.getLayoutParams()).z()) {
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
        m3(iRound);
        if (this.x == i2) {
            return;
        }
        for (int i3 = 0; i3 < iF0; i3++) {
            View viewE02 = e0(i3);
            c cVar = (c) viewE02.getLayoutParams();
            if (!cVar.g) {
                if (Q2() && this.w == 1) {
                    int i4 = this.s;
                    int i5 = cVar.f.e;
                    viewE02.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.x) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = cVar.f.e;
                    int i7 = this.w;
                    int i8 = (this.x * i6) - (i6 * i2);
                    if (i7 == 1) {
                        viewE02.offsetLeftAndRight(i8);
                    } else {
                        viewE02.offsetTopAndBottom(i8);
                    }
                }
            }
        }
    }

    private void b3() {
        this.A = (this.w == 1 || !Q2()) ? this.z : !this.z;
    }

    private void d3(int i) {
        g gVar = this.y;
        gVar.e = i;
        gVar.f1594d = this.A != (i == -1) ? -1 : 1;
    }

    private void h3(int i, int i2) {
        for (int i3 = 0; i3 < this.s; i3++) {
            if (!this.t[i3].f1516a.isEmpty()) {
                n3(this.t[i3], i, i2);
            }
        }
    }

    private void i2(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].a(view);
        }
    }

    private boolean i3(RecyclerView.a0 a0Var, b bVar) {
        boolean z = this.G;
        int iB = a0Var.b();
        bVar.f1504a = z ? C2(iB) : y2(iB);
        bVar.f1505b = Integer.MIN_VALUE;
        return true;
    }

    private void j2(b bVar) {
        boolean z;
        e eVar = this.I;
        int i = eVar.f1515d;
        if (i > 0) {
            if (i == this.s) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    this.t[i2].e();
                    e eVar2 = this.I;
                    int i3 = eVar2.e[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        i3 += eVar2.j ? this.u.i() : this.u.m();
                    }
                    this.t[i2].v(i3);
                }
            } else {
                eVar.v();
                e eVar3 = this.I;
                eVar3.f1513b = eVar3.f1514c;
            }
        }
        e eVar4 = this.I;
        this.H = eVar4.k;
        f3(eVar4.i);
        b3();
        e eVar5 = this.I;
        int i4 = eVar5.f1513b;
        if (i4 != -1) {
            this.C = i4;
            z = eVar5.j;
        } else {
            z = this.A;
        }
        bVar.f1506c = z;
        if (eVar5.f > 1) {
            d dVar = this.E;
            dVar.f1508a = eVar5.g;
            dVar.f1509b = eVar5.h;
        }
    }

    private void l3(int i, RecyclerView.a0 a0Var) {
        int iN;
        int iN2;
        int iC;
        g gVar = this.y;
        boolean z = false;
        gVar.f1592b = 0;
        gVar.f1593c = i;
        if (!Q0() || (iC = a0Var.c()) == -1) {
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
        if (i0()) {
            this.y.f = this.u.m() - iN2;
            this.y.g = this.u.i() + iN;
        } else {
            this.y.g = this.u.h() + iN;
            this.y.f = -iN2;
        }
        g gVar2 = this.y;
        gVar2.h = false;
        gVar2.f1591a = true;
        if (this.u.k() == 0 && this.u.h() == 0) {
            z = true;
        }
        gVar2.i = z;
    }

    private void m2(View view, c cVar, g gVar) {
        if (gVar.e == 1) {
            if (cVar.g) {
                i2(view);
                return;
            } else {
                cVar.f.a(view);
                return;
            }
        }
        if (cVar.g) {
            W2(view);
        } else {
            cVar.f.u(view);
        }
    }

    private int n2(int i) {
        if (f0() == 0) {
            return this.A ? 1 : -1;
        }
        return (i < F2()) != this.A ? -1 : 1;
    }

    private void n3(f fVar, int i, int i2) {
        int iJ = fVar.j();
        if (i == -1) {
            if (fVar.o() + iJ > i2) {
                return;
            }
        } else if (fVar.k() - iJ < i2) {
            return;
        }
        this.B.set(fVar.e, false);
    }

    private int o3(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    private boolean p2(f fVar) {
        if (this.A) {
            if (fVar.k() < this.u.i()) {
                ArrayList<View> arrayList = fVar.f1516a;
                return !fVar.n(arrayList.get(arrayList.size() - 1)).g;
            }
        } else if (fVar.o() > this.u.m()) {
            return !fVar.n(fVar.f1516a.get(0)).g;
        }
        return false;
    }

    private int q2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        return m.a(a0Var, this.u, A2(!this.N), z2(!this.N), this, this.N);
    }

    private int r2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        return m.b(a0Var, this.u, A2(!this.N), z2(!this.N), this, this.N, this.A);
    }

    private int s2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        return m.c(a0Var, this.u, A2(!this.N), z2(!this.N), this, this.N);
    }

    private int t2(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.w == 1) ? 1 : Integer.MIN_VALUE : this.w == 0 ? 1 : Integer.MIN_VALUE : this.w == 1 ? -1 : Integer.MIN_VALUE : this.w == 0 ? -1 : Integer.MIN_VALUE : (this.w != 1 && Q2()) ? -1 : 1 : (this.w != 1 && Q2()) ? 1 : -1;
    }

    private d.a u2(int i) {
        d.a aVar = new d.a();
        aVar.f1512d = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            aVar.f1512d[i2] = i - this.t[i2].l(i);
        }
        return aVar;
    }

    private d.a v2(int i) {
        d.a aVar = new d.a();
        aVar.f1512d = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            aVar.f1512d[i2] = this.t[i2].p(i) - i;
        }
        return aVar;
    }

    private void w2() {
        this.u = k.b(this, this.w);
        this.v = k.b(this, 1 - this.w);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38, types: [androidx.recyclerview.widget.RecyclerView$o] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v52 */
    /* JADX WARN: Type inference failed for: r16v0, types: [androidx.recyclerview.widget.RecyclerView$o, androidx.recyclerview.widget.StaggeredGridLayoutManager] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int x2(RecyclerView.v vVar, g gVar, RecyclerView.a0 a0Var) {
        f fVarL2;
        int iE;
        int i;
        int iE2;
        int iE3;
        ?? r0;
        View view;
        int i2;
        int i3;
        boolean z;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        int i4 = this.y.i ? gVar.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : gVar.e == 1 ? gVar.g + gVar.f1592b : gVar.f - gVar.f1592b;
        h3(gVar.e, i4);
        int i5 = this.A ? this.u.i() : this.u.m();
        ?? r02 = false;
        while (gVar.a(a0Var) && (this.y.i || !this.B.isEmpty())) {
            View viewB = gVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iU = cVar.u();
            int iG = this.E.g(iU);
            ?? r3 = iG == -1 ? 1 : r9;
            if (r3 != 0) {
                fVarL2 = cVar.g ? this.t[r9] : L2(gVar);
                this.E.n(iU, fVarL2);
            } else {
                fVarL2 = this.t[iG];
            }
            f fVar = fVarL2;
            cVar.f = fVar;
            if (gVar.e == 1) {
                z(viewB);
            } else {
                A(viewB, r9);
            }
            S2(viewB, cVar, r9);
            if (gVar.e == 1) {
                int iH2 = cVar.g ? H2(i5) : fVar.l(i5);
                int iE4 = this.u.e(viewB) + iH2;
                if (r3 != 0 && cVar.g) {
                    d.a aVarU2 = u2(iH2);
                    aVarU2.f1511c = -1;
                    aVarU2.f1510b = iU;
                    this.E.a(aVarU2);
                }
                i = iE4;
                iE = iH2;
            } else {
                int iK2 = cVar.g ? K2(i5) : fVar.p(i5);
                iE = iK2 - this.u.e(viewB);
                if (r3 != 0 && cVar.g) {
                    d.a aVarV2 = v2(iK2);
                    aVarV2.f1511c = 1;
                    aVarV2.f1510b = iU;
                    this.E.a(aVarV2);
                }
                i = iK2;
            }
            if (cVar.g && gVar.f1594d == -1) {
                if (r3 == 0) {
                    if (!(gVar.e == 1 ? k2() : l2())) {
                        d.a aVarF = this.E.f(iU);
                        if (aVarF != null) {
                            aVarF.e = true;
                        }
                        this.M = true;
                    }
                } else {
                    this.M = true;
                }
            }
            m2(viewB, cVar, gVar);
            if (Q2() && this.w == 1) {
                int i6 = cVar.g ? this.v.i() : this.v.i() - (((this.s - 1) - fVar.e) * this.x);
                iE3 = i6;
                iE2 = i6 - this.v.e(viewB);
            } else {
                int iM = cVar.g ? this.v.m() : (fVar.e * this.x) + this.v.m();
                iE2 = iM;
                iE3 = this.v.e(viewB) + iM;
            }
            if (this.w == 1) {
                r0 = this;
                view = viewB;
                i2 = iE2;
                iE2 = iE;
                i3 = iE3;
            } else {
                r0 = this;
                view = viewB;
                i2 = iE;
                i3 = i;
                i = iE3;
            }
            r0.T0(view, i2, iE2, i3, i);
            if (cVar.g) {
                h3(this.y.e, i4);
            } else {
                n3(fVar, this.y.e, i4);
            }
            X2(vVar, this.y);
            if (!this.y.h || !viewB.hasFocusable()) {
                z = false;
            } else if (cVar.g) {
                this.B.clear();
                z = false;
            } else {
                z = false;
                this.B.set(fVar.e, false);
            }
            r9 = z;
            r02 = true;
        }
        ?? r32 = r9;
        if (r02 == false) {
            X2(vVar, this.y);
        }
        int iM2 = this.y.e == -1 ? this.u.m() - K2(this.u.m()) : H2(this.u.i()) - this.u.i();
        return iM2 > 0 ? Math.min(gVar.f1592b, iM2) : r32 == true ? 1 : 0;
    }

    private int y2(int i) {
        int iF0 = f0();
        for (int i2 = 0; i2 < iF0; i2++) {
            int iZ0 = z0(e0(i2));
            if (iZ0 >= 0 && iZ0 < i) {
                return iZ0;
            }
        }
        return 0;
    }

    View A2(boolean z) {
        int iM = this.u.m();
        int i = this.u.i();
        int iF0 = f0();
        View view = null;
        for (int i2 = 0; i2 < iF0; i2++) {
            View viewE0 = e0(i2);
            int iG = this.u.g(viewE0);
            if (this.u.d(viewE0) > iM && iG < i) {
                if (iG >= iM || !z) {
                    return viewE0;
                }
                if (view == null) {
                    view = viewE0;
                }
            }
        }
        return view;
    }

    int B2() {
        View viewZ2 = this.A ? z2(true) : A2(true);
        if (viewZ2 == null) {
            return -1;
        }
        return z0(viewZ2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C(String str) {
        if (this.I == null) {
            super.C(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int C0(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return this.w == 0 ? this.s : super.C0(vVar, a0Var);
    }

    int F2() {
        if (f0() == 0) {
            return 0;
        }
        return z0(e0(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean G() {
        return this.w == 0;
    }

    int G2() {
        int iF0 = f0();
        if (iF0 == 0) {
            return 0;
        }
        return z0(e0(iF0 - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean H() {
        return this.w == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean I(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void K(int i, int i2, RecyclerView.a0 a0Var, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.w != 0) {
            i = i2;
        }
        if (f0() == 0 || i == 0) {
            return;
        }
        V2(i, a0Var);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.s; i4++) {
            g gVar = this.y;
            if (gVar.f1594d == -1) {
                iL = gVar.f;
                iP = this.t[i4].p(iL);
            } else {
                iL = this.t[i4].l(gVar.g);
                iP = this.y.g;
            }
            int i5 = iL - iP;
            if (i5 >= 0) {
                this.O[i3] = i5;
                i3++;
            }
        }
        Arrays.sort(this.O, 0, i3);
        for (int i6 = 0; i6 < i3 && this.y.a(a0Var); i6++) {
            cVar.a(this.y.f1593c, this.O[i6]);
            g gVar2 = this.y;
            gVar2.f1593c += gVar2.f1594d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean K0() {
        return this.F != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int M(RecyclerView.a0 a0Var) {
        return q2(a0Var);
    }

    public int M2() {
        return this.w;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.a0 a0Var) {
        return r2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int O(RecyclerView.a0 a0Var) {
        return s2(a0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.View O2() {
        /*
            r12 = this;
            int r0 = r12.f0()
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
            boolean r3 = r12.Q2()
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
            android.view.View r7 = r12.e0(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.f
            int r9 = r9.e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.f
            boolean r9 = r12.p2(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.f
            int r9 = r9.e
            r2.clear(r9)
        L54:
            boolean r9 = r8.g
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.e0(r9)
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r8 = r8.f
            int r8 = r8.e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r9.f
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
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.O2():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int P(RecyclerView.a0 a0Var) {
        return q2(a0Var);
    }

    public void P2() {
        this.E.b();
        O1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int Q(RecyclerView.a0 a0Var) {
        return r2(a0Var);
    }

    boolean Q2() {
        return v0() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int R(RecyclerView.a0 a0Var) {
        return s2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int R1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return c3(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S1(int i) {
        e eVar = this.I;
        if (eVar != null && eVar.f1513b != i) {
            eVar.u();
        }
        this.C = i;
        this.D = Integer.MIN_VALUE;
        O1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int T1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return c3(i, vVar, a0Var);
    }

    void V2(int i, RecyclerView.a0 a0Var) {
        int i2;
        int iF2;
        if (i > 0) {
            iF2 = G2();
            i2 = 1;
        } else {
            i2 = -1;
            iF2 = F2();
        }
        this.y.f1591a = true;
        l3(iF2, a0Var);
        d3(i2);
        g gVar = this.y;
        gVar.f1593c = iF2 + gVar.f1594d;
        gVar.f1592b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(int i) {
        super.W0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].r(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(int i) {
        super.X0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].r(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y1(Rect rect, int i, int i2) {
        int iJ;
        int iJ2;
        int iL = l() + b();
        int iJ3 = j() + e();
        if (this.w == 1) {
            iJ2 = RecyclerView.o.J(i2, rect.height() + iJ3, x0());
            iJ = RecyclerView.o.J(i, (this.x * this.s) + iL, y0());
        } else {
            iJ = RecyclerView.o.J(i, rect.width() + iL, y0());
            iJ2 = RecyclerView.o.J(i2, (this.x * this.s) + iJ3, x0());
        }
        X1(iJ, iJ2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p Z() {
        return this.w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a0(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p b0(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.c1(recyclerView, vVar);
        J1(this.P);
        for (int i = 0; i < this.s; i++) {
            this.t[i].e();
        }
        recyclerView.requestLayout();
    }

    int c3(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (f0() == 0 || i == 0) {
            return 0;
        }
        V2(i, a0Var);
        int iX2 = x2(vVar, this.y, a0Var);
        if (this.y.f1592b >= iX2) {
            i = i < 0 ? -iX2 : iX2;
        }
        this.u.r(-i);
        this.G = this.A;
        g gVar = this.y;
        gVar.f1592b = 0;
        X2(vVar, gVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View d1(View view, int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        View viewX;
        View viewM;
        if (f0() == 0 || (viewX = X(view)) == null) {
            return null;
        }
        b3();
        int iT2 = t2(i);
        if (iT2 == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewX.getLayoutParams();
        boolean z = cVar.g;
        f fVar = cVar.f;
        int iG2 = iT2 == 1 ? G2() : F2();
        l3(iG2, a0Var);
        d3(iT2);
        g gVar = this.y;
        gVar.f1593c = gVar.f1594d + iG2;
        gVar.f1592b = (int) (this.u.n() * 0.33333334f);
        g gVar2 = this.y;
        gVar2.h = true;
        gVar2.f1591a = false;
        x2(vVar, gVar2, a0Var);
        this.G = this.A;
        if (!z && (viewM = fVar.m(iG2, iT2)) != null && viewM != viewX) {
            return viewM;
        }
        if (U2(iT2)) {
            for (int i2 = this.s - 1; i2 >= 0; i2--) {
                View viewM2 = this.t[i2].m(iG2, iT2);
                if (viewM2 != null && viewM2 != viewX) {
                    return viewM2;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.s; i3++) {
                View viewM3 = this.t[i3].m(iG2, iT2);
                if (viewM3 != null && viewM3 != viewX) {
                    return viewM3;
                }
            }
        }
        boolean z2 = (this.z ^ true) == (iT2 == -1);
        if (!z) {
            View viewY = Y(z2 ? fVar.f() : fVar.g());
            if (viewY != null && viewY != viewX) {
                return viewY;
            }
        }
        if (U2(iT2)) {
            for (int i4 = this.s - 1; i4 >= 0; i4--) {
                if (i4 != fVar.e) {
                    f[] fVarArr = this.t;
                    View viewY2 = Y(z2 ? fVarArr[i4].f() : fVarArr[i4].g());
                    if (viewY2 != null && viewY2 != viewX) {
                        return viewY2;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.s; i5++) {
                f[] fVarArr2 = this.t;
                View viewY3 = Y(z2 ? fVarArr2[i5].f() : fVarArr2[i5].g());
                if (viewY3 != null && viewY3 != viewX) {
                    return viewY3;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e1(AccessibilityEvent accessibilityEvent) {
        super.e1(accessibilityEvent);
        if (f0() > 0) {
            View viewA2 = A2(false);
            View viewZ2 = z2(false);
            if (viewA2 == null || viewZ2 == null) {
                return;
            }
            int iZ0 = z0(viewA2);
            int iZ02 = z0(viewZ2);
            if (iZ0 < iZ02) {
                accessibilityEvent.setFromIndex(iZ0);
                accessibilityEvent.setToIndex(iZ02);
            } else {
                accessibilityEvent.setFromIndex(iZ02);
                accessibilityEvent.setToIndex(iZ0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e2(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        h hVar = new h(recyclerView.getContext());
        hVar.p(i);
        f2(hVar);
    }

    public void e3(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        C(null);
        if (i == this.w) {
            return;
        }
        this.w = i;
        k kVar = this.u;
        this.u = this.v;
        this.v = kVar;
        O1();
    }

    public void f3(boolean z) {
        C(null);
        e eVar = this.I;
        if (eVar != null && eVar.i != z) {
            eVar.i = z;
        }
        this.z = z;
        O1();
    }

    public void g3(int i) {
        C(null);
        if (i != this.s) {
            P2();
            this.s = i;
            this.B = new BitSet(this.s);
            this.t = new f[this.s];
            for (int i2 = 0; i2 < this.s; i2++) {
                this.t[i2] = new f(i2);
            }
            O1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean h2() {
        return this.I == null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int j0(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return this.w == 1 ? this.s : super.j0(vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void j1(RecyclerView.v vVar, RecyclerView.a0 a0Var, View view, a.g.k.c0.c cVar) {
        int iY;
        int i;
        int iY2;
        int i2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.i1(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.w == 0) {
            iY = cVar2.y();
            i = cVar2.g ? this.s : 1;
            iY2 = -1;
            i2 = -1;
        } else {
            iY = -1;
            i = -1;
            iY2 = cVar2.y();
            i2 = cVar2.g ? this.s : 1;
        }
        cVar.a0(c.C0014c.a(iY, i, iY2, i2, false, false));
    }

    boolean j3(RecyclerView.a0 a0Var, b bVar) {
        int i;
        int iM;
        int iG;
        if (!a0Var.e() && (i = this.C) != -1) {
            if (i >= 0 && i < a0Var.b()) {
                e eVar = this.I;
                if (eVar == null || eVar.f1513b == -1 || eVar.f1515d < 1) {
                    View viewY = Y(this.C);
                    if (viewY != null) {
                        bVar.f1504a = this.A ? G2() : F2();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1506c) {
                                iM = this.u.i() - this.D;
                                iG = this.u.d(viewY);
                            } else {
                                iM = this.u.m() + this.D;
                                iG = this.u.g(viewY);
                            }
                            bVar.f1505b = iM - iG;
                            return true;
                        }
                        if (this.u.e(viewY) > this.u.n()) {
                            bVar.f1505b = bVar.f1506c ? this.u.i() : this.u.m();
                            return true;
                        }
                        int iG2 = this.u.g(viewY) - this.u.m();
                        if (iG2 < 0) {
                            bVar.f1505b = -iG2;
                            return true;
                        }
                        int i2 = this.u.i() - this.u.d(viewY);
                        if (i2 < 0) {
                            bVar.f1505b = i2;
                            return true;
                        }
                        bVar.f1505b = Integer.MIN_VALUE;
                    } else {
                        int i3 = this.C;
                        bVar.f1504a = i3;
                        int i4 = this.D;
                        if (i4 == Integer.MIN_VALUE) {
                            bVar.f1506c = n2(i3) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i4);
                        }
                        bVar.f1507d = true;
                    }
                } else {
                    bVar.f1505b = Integer.MIN_VALUE;
                    bVar.f1504a = this.C;
                }
                return true;
            }
            this.C = -1;
            this.D = Integer.MIN_VALUE;
        }
        return false;
    }

    boolean k2() {
        int iL = this.t[0].l(Integer.MIN_VALUE);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    void k3(RecyclerView.a0 a0Var, b bVar) {
        if (j3(a0Var, bVar) || i3(a0Var, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1504a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void l1(RecyclerView recyclerView, int i, int i2) {
        N2(i, i2, 1);
    }

    boolean l2() {
        int iP = this.t[0].p(Integer.MIN_VALUE);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void m1(RecyclerView recyclerView) {
        this.E.b();
        O1();
    }

    void m3(int i) {
        this.x = i / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i, this.v.k());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF n(int i) {
        int iN2 = n2(i);
        PointF pointF = new PointF();
        if (iN2 == 0) {
            return null;
        }
        if (this.w == 0) {
            pointF.x = iN2;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iN2;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void n1(RecyclerView recyclerView, int i, int i2, int i3) {
        N2(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o1(RecyclerView recyclerView, int i, int i2) {
        N2(i, i2, 2);
    }

    boolean o2() {
        int iF2;
        int iG2;
        if (f0() == 0 || this.F == 0 || !J0()) {
            return false;
        }
        if (this.A) {
            iF2 = G2();
            iG2 = F2();
        } else {
            iF2 = F2();
            iG2 = G2();
        }
        if (iF2 == 0 && O2() != null) {
            this.E.b();
        } else {
            if (!this.M) {
                return false;
            }
            int i = this.A ? -1 : 1;
            int i2 = iG2 + 1;
            d.a aVarE = this.E.e(iF2, i2, i, true);
            if (aVarE == null) {
                this.M = false;
                this.E.d(i2);
                return false;
            }
            d.a aVarE2 = this.E.e(iF2, aVarE.f1510b, i * (-1), true);
            if (aVarE2 == null) {
                this.E.d(aVarE.f1510b);
            } else {
                this.E.d(aVarE2.f1510b + 1);
            }
        }
        P1();
        O1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void q1(RecyclerView recyclerView, int i, int i2, Object obj) {
        N2(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void r1(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        T2(vVar, a0Var, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void s1(RecyclerView.a0 a0Var) {
        super.s1(a0Var);
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void w1(Parcelable parcelable) {
        if (parcelable instanceof e) {
            this.I = (e) parcelable;
            O1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable x1() {
        int iP;
        int iM;
        int[] iArr;
        if (this.I != null) {
            return new e(this.I);
        }
        e eVar = new e();
        eVar.i = this.z;
        eVar.j = this.G;
        eVar.k = this.H;
        d dVar = this.E;
        if (dVar == null || (iArr = dVar.f1508a) == null) {
            eVar.f = 0;
        } else {
            eVar.g = iArr;
            eVar.f = iArr.length;
            eVar.h = dVar.f1509b;
        }
        if (f0() > 0) {
            eVar.f1513b = this.G ? G2() : F2();
            eVar.f1514c = B2();
            int i = this.s;
            eVar.f1515d = i;
            eVar.e = new int[i];
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
                eVar.e[i2] = iP;
            }
        } else {
            eVar.f1513b = -1;
            eVar.f1514c = -1;
            eVar.f1515d = 0;
        }
        return eVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void y1(int i) {
        if (i == 0) {
            o2();
        }
    }

    View z2(boolean z) {
        int iM = this.u.m();
        int i = this.u.i();
        View view = null;
        for (int iF0 = f0() - 1; iF0 >= 0; iF0--) {
            View viewE0 = e0(iF0);
            int iG = this.u.g(viewE0);
            int iD = this.u.d(viewE0);
            if (iD > iM && iG < i) {
                if (iD <= i || !z) {
                    return viewE0;
                }
                if (view == null) {
                    view = viewE0;
                }
            }
        }
        return view;
    }
}
