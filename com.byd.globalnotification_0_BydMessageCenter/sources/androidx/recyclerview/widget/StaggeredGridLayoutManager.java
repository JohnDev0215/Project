package androidx.recyclerview.widget;

import a.b.k.l;
import a.h.k.x.b;
import a.p.e.n;
import a.p.e.r;
import a.p.e.s;
import a.p.e.w;
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
public class StaggeredGridLayoutManager extends RecyclerView.m implements RecyclerView.x.b {
    public BitSet B;
    public boolean G;
    public boolean H;
    public e I;
    public int J;
    public int[] O;
    public int s;
    public f[] t;
    public w u;
    public w v;
    public int w;
    public int x;
    public final r y;
    public boolean z;
    public boolean A = false;
    public int C = -1;
    public int D = Integer.MIN_VALUE;
    public d E = new d();
    public int F = 2;
    public final Rect K = new Rect();
    public final b L = new b();
    public boolean M = false;
    public boolean N = true;
    public final Runnable P = new a();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.a1();
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1696a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1697b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1698c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1699d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1700e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f1701f;

        public b() {
            b();
        }

        public void a() {
            this.f1697b = this.f1698c ? StaggeredGridLayoutManager.this.u.g() : StaggeredGridLayoutManager.this.u.k();
        }

        public void b() {
            this.f1696a = -1;
            this.f1697b = Integer.MIN_VALUE;
            this.f1698c = false;
            this.f1699d = false;
            this.f1700e = false;
            int[] iArr = this.f1701f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }
    }

    public static class c extends RecyclerView.n {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public f f1702e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1703f;

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
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f1704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<a> f1705b;

        @SuppressLint({"BanParcelableUsage"})
        public static class a implements Parcelable {
            public static final Parcelable.Creator<a> CREATOR = new C0031a();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1706a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1707b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int[] f1708c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1709d;

            /* JADX INFO: renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a$a, reason: collision with other inner class name */
            public static class C0031a implements Parcelable.Creator<a> {
                @Override // android.os.Parcelable.Creator
                public a createFromParcel(Parcel parcel) {
                    return new a(parcel);
                }

                @Override // android.os.Parcelable.Creator
                public a[] newArray(int i) {
                    return new a[i];
                }
            }

            public a() {
            }

            public a(Parcel parcel) {
                this.f1706a = parcel.readInt();
                this.f1707b = parcel.readInt();
                this.f1709d = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.f1708c = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                StringBuilder sbD = b.a.a.a.a.d("FullSpanItem{mPosition=");
                sbD.append(this.f1706a);
                sbD.append(", mGapDir=");
                sbD.append(this.f1707b);
                sbD.append(", mHasUnwantedGapAfter=");
                sbD.append(this.f1709d);
                sbD.append(", mGapPerSpan=");
                sbD.append(Arrays.toString(this.f1708c));
                sbD.append('}');
                return sbD.toString();
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1706a);
                parcel.writeInt(this.f1707b);
                parcel.writeInt(this.f1709d ? 1 : 0);
                int[] iArr = this.f1708c;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1708c);
                }
            }
        }

        public void a(a aVar) {
            if (this.f1705b == null) {
                this.f1705b = new ArrayList();
            }
            int size = this.f1705b.size();
            for (int i = 0; i < size; i++) {
                a aVar2 = this.f1705b.get(i);
                if (aVar2.f1706a == aVar.f1706a) {
                    this.f1705b.remove(i);
                }
                if (aVar2.f1706a >= aVar.f1706a) {
                    this.f1705b.add(i, aVar);
                    return;
                }
            }
            this.f1705b.add(aVar);
        }

        public void b() {
            int[] iArr = this.f1704a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1705b = null;
        }

        public void c(int i) {
            int[] iArr = this.f1704a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1704a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int length = iArr.length;
                while (length <= i) {
                    length *= 2;
                }
                int[] iArr3 = new int[length];
                this.f1704a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1704a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i) {
            List<a> list = this.f1705b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1705b.get(size).f1706a >= i) {
                        this.f1705b.remove(size);
                    }
                }
            }
            return g(i);
        }

        public a e(int i, int i2, int i3, boolean z) {
            List<a> list = this.f1705b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                a aVar = this.f1705b.get(i4);
                int i5 = aVar.f1706a;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || aVar.f1707b == i3 || (z && aVar.f1709d))) {
                    return aVar;
                }
            }
            return null;
        }

        public a f(int i) {
            List<a> list = this.f1705b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1705b.get(size);
                if (aVar.f1706a == i) {
                    return aVar;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x000e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int g(int r5) {
            /*
                r4 = this;
                int[] r0 = r4.f1704a
                r1 = -1
                if (r0 != 0) goto L6
                return r1
            L6:
                int r0 = r0.length
                if (r5 < r0) goto La
                return r1
            La:
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a> r0 = r4.f1705b
                if (r0 != 0) goto L10
            Le:
                r0 = r1
                goto L46
            L10:
                androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a r0 = r4.f(r5)
                if (r0 == 0) goto L1b
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a> r2 = r4.f1705b
                r2.remove(r0)
            L1b:
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a> r0 = r4.f1705b
                int r0 = r0.size()
                r2 = 0
            L22:
                if (r2 >= r0) goto L34
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a> r3 = r4.f1705b
                java.lang.Object r3 = r3.get(r2)
                androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.d.a) r3
                int r3 = r3.f1706a
                if (r3 < r5) goto L31
                goto L35
            L31:
                int r2 = r2 + 1
                goto L22
            L34:
                r2 = r1
            L35:
                if (r2 == r1) goto Le
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a> r0 = r4.f1705b
                java.lang.Object r0 = r0.get(r2)
                androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a r0 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.d.a) r0
                java.util.List<androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a> r3 = r4.f1705b
                r3.remove(r2)
                int r0 = r0.f1706a
            L46:
                if (r0 != r1) goto L52
                int[] r0 = r4.f1704a
                int r2 = r0.length
                java.util.Arrays.fill(r0, r5, r2, r1)
                int[] r4 = r4.f1704a
                int r4 = r4.length
                return r4
            L52:
                int[] r4 = r4.f1704a
                int r0 = r0 + 1
                java.util.Arrays.fill(r4, r5, r0, r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.d.g(int):int");
        }

        public void h(int i, int i2) {
            int[] iArr = this.f1704a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1704a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1704a, i, i3, -1);
            List<a> list = this.f1705b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1705b.get(size);
                int i4 = aVar.f1706a;
                if (i4 >= i) {
                    aVar.f1706a = i4 + i2;
                }
            }
        }

        public void i(int i, int i2) {
            int[] iArr = this.f1704a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            c(i3);
            int[] iArr2 = this.f1704a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1704a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            List<a> list = this.f1705b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1705b.get(size);
                int i4 = aVar.f1706a;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.f1705b.remove(size);
                    } else {
                        aVar.f1706a = i4 - i2;
                    }
                }
            }
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1710a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1711b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1712c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int[] f1713d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1714e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f1715f;
        public List<d.a> g;
        public boolean h;
        public boolean i;
        public boolean j;

        public static class a implements Parcelable.Creator<e> {
            @Override // android.os.Parcelable.Creator
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public e[] newArray(int i) {
                return new e[i];
            }
        }

        public e() {
        }

        public e(Parcel parcel) {
            this.f1710a = parcel.readInt();
            this.f1711b = parcel.readInt();
            int i = parcel.readInt();
            this.f1712c = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.f1713d = iArr;
                parcel.readIntArray(iArr);
            }
            int i2 = parcel.readInt();
            this.f1714e = i2;
            if (i2 > 0) {
                int[] iArr2 = new int[i2];
                this.f1715f = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.h = parcel.readInt() == 1;
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.g = parcel.readArrayList(d.a.class.getClassLoader());
        }

        public e(e eVar) {
            this.f1712c = eVar.f1712c;
            this.f1710a = eVar.f1710a;
            this.f1711b = eVar.f1711b;
            this.f1713d = eVar.f1713d;
            this.f1714e = eVar.f1714e;
            this.f1715f = eVar.f1715f;
            this.h = eVar.h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.g = eVar.g;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1710a);
            parcel.writeInt(this.f1711b);
            parcel.writeInt(this.f1712c);
            if (this.f1712c > 0) {
                parcel.writeIntArray(this.f1713d);
            }
            parcel.writeInt(this.f1714e);
            if (this.f1714e > 0) {
                parcel.writeIntArray(this.f1715f);
            }
            parcel.writeInt(this.h ? 1 : 0);
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeList(this.g);
        }
    }

    public class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList<View> f1716a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1717b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1718c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1719d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1720e;

        public f(int i) {
            this.f1720e = i;
        }

        public void a(View view) {
            c cVarJ = j(view);
            cVarJ.f1702e = this;
            this.f1716a.add(view);
            this.f1718c = Integer.MIN_VALUE;
            if (this.f1716a.size() == 1) {
                this.f1717b = Integer.MIN_VALUE;
            }
            if (cVarJ.c() || cVarJ.b()) {
                this.f1719d = StaggeredGridLayoutManager.this.u.c(view) + this.f1719d;
            }
        }

        public void b() {
            d.a aVarF;
            ArrayList<View> arrayList = this.f1716a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarJ = j(view);
            this.f1718c = StaggeredGridLayoutManager.this.u.b(view);
            if (cVarJ.f1703f && (aVarF = StaggeredGridLayoutManager.this.E.f(cVarJ.a())) != null && aVarF.f1707b == 1) {
                int i = this.f1718c;
                int i2 = this.f1720e;
                int[] iArr = aVarF.f1708c;
                this.f1718c = i + (iArr == null ? 0 : iArr[i2]);
            }
        }

        public void c() {
            d.a aVarF;
            View view = this.f1716a.get(0);
            c cVarJ = j(view);
            this.f1717b = StaggeredGridLayoutManager.this.u.e(view);
            if (cVarJ.f1703f && (aVarF = StaggeredGridLayoutManager.this.E.f(cVarJ.a())) != null && aVarF.f1707b == -1) {
                int i = this.f1717b;
                int i2 = this.f1720e;
                int[] iArr = aVarF.f1708c;
                this.f1717b = i - (iArr != null ? iArr[i2] : 0);
            }
        }

        public void d() {
            this.f1716a.clear();
            this.f1717b = Integer.MIN_VALUE;
            this.f1718c = Integer.MIN_VALUE;
            this.f1719d = 0;
        }

        public int e() {
            int size;
            int size2;
            if (StaggeredGridLayoutManager.this.z) {
                size = this.f1716a.size() - 1;
                size2 = -1;
            } else {
                size = 0;
                size2 = this.f1716a.size();
            }
            return g(size, size2, true);
        }

        public int f() {
            int size;
            int size2;
            if (StaggeredGridLayoutManager.this.z) {
                size = 0;
                size2 = this.f1716a.size();
            } else {
                size = this.f1716a.size() - 1;
                size2 = -1;
            }
            return g(size, size2, true);
        }

        public int g(int i, int i2, boolean z) {
            int iK = StaggeredGridLayoutManager.this.u.k();
            int iG = StaggeredGridLayoutManager.this.u.g();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1716a.get(i);
                int iE = StaggeredGridLayoutManager.this.u.e(view);
                int iB = StaggeredGridLayoutManager.this.u.b(view);
                boolean z2 = false;
                boolean z3 = !z ? iE >= iG : iE > iG;
                if (!z ? iB > iK : iB >= iK) {
                    z2 = true;
                }
                if (z3 && z2 && (iE < iK || iB > iG)) {
                    return StaggeredGridLayoutManager.this.R(view);
                }
                i += i3;
            }
            return -1;
        }

        public int h(int i) {
            int i2 = this.f1718c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1716a.size() == 0) {
                return i;
            }
            b();
            return this.f1718c;
        }

        public View i(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.f1716a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1716a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.R(view2) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.R(view2) <= i) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1716a.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.f1716a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.R(view3) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.R(view3) >= i) || !view3.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view3;
                }
            }
            return view;
        }

        public c j(View view) {
            return (c) view.getLayoutParams();
        }

        public int k(int i) {
            int i2 = this.f1717b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1716a.size() == 0) {
                return i;
            }
            c();
            return this.f1717b;
        }

        public void l() {
            int size = this.f1716a.size();
            View viewRemove = this.f1716a.remove(size - 1);
            c cVarJ = j(viewRemove);
            cVarJ.f1702e = null;
            if (cVarJ.c() || cVarJ.b()) {
                this.f1719d -= StaggeredGridLayoutManager.this.u.c(viewRemove);
            }
            if (size == 1) {
                this.f1717b = Integer.MIN_VALUE;
            }
            this.f1718c = Integer.MIN_VALUE;
        }

        public void m() {
            View viewRemove = this.f1716a.remove(0);
            c cVarJ = j(viewRemove);
            cVarJ.f1702e = null;
            if (this.f1716a.size() == 0) {
                this.f1718c = Integer.MIN_VALUE;
            }
            if (cVarJ.c() || cVarJ.b()) {
                this.f1719d -= StaggeredGridLayoutManager.this.u.c(viewRemove);
            }
            this.f1717b = Integer.MIN_VALUE;
        }

        public void n(View view) {
            c cVarJ = j(view);
            cVarJ.f1702e = this;
            this.f1716a.add(0, view);
            this.f1717b = Integer.MIN_VALUE;
            if (this.f1716a.size() == 1) {
                this.f1718c = Integer.MIN_VALUE;
            }
            if (cVarJ.c() || cVarJ.b()) {
                this.f1719d = StaggeredGridLayoutManager.this.u.c(view) + this.f1719d;
            }
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.s = -1;
        this.z = false;
        RecyclerView.m.d dVarS = RecyclerView.m.S(context, attributeSet, i, i2);
        int i3 = dVarS.f1655a;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        d(null);
        if (i3 != this.w) {
            this.w = i3;
            w wVar = this.u;
            this.u = this.v;
            this.v = wVar;
            K0();
        }
        int i4 = dVarS.f1656b;
        d(null);
        if (i4 != this.s) {
            this.E.b();
            K0();
            this.s = i4;
            this.B = new BitSet(this.s);
            this.t = new f[this.s];
            for (int i5 = 0; i5 < this.s; i5++) {
                this.t[i5] = new f(i5);
            }
            K0();
        }
        boolean z = dVarS.f1657c;
        d(null);
        e eVar = this.I;
        if (eVar != null && eVar.h != z) {
            eVar.h = z;
        }
        this.z = z;
        K0();
        this.y = new r();
        this.u = w.a(this, this.w);
        this.v = w.a(this, 1 - this.w);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public Parcelable A0() {
        int iK;
        int iK2;
        int[] iArr;
        e eVar = this.I;
        if (eVar != null) {
            return new e(eVar);
        }
        e eVar2 = new e();
        eVar2.h = this.z;
        eVar2.i = this.G;
        eVar2.j = this.H;
        d dVar = this.E;
        if (dVar == null || (iArr = dVar.f1704a) == null) {
            eVar2.f1714e = 0;
        } else {
            eVar2.f1715f = iArr;
            eVar2.f1714e = iArr.length;
            eVar2.g = dVar.f1705b;
        }
        if (z() > 0) {
            eVar2.f1710a = this.G ? k1() : j1();
            View viewF1 = this.A ? f1(true) : g1(true);
            eVar2.f1711b = viewF1 != null ? R(viewF1) : -1;
            int i = this.s;
            eVar2.f1712c = i;
            eVar2.f1713d = new int[i];
            for (int i2 = 0; i2 < this.s; i2++) {
                if (this.G) {
                    iK = this.t[i2].h(Integer.MIN_VALUE);
                    if (iK != Integer.MIN_VALUE) {
                        iK2 = this.u.g();
                        iK -= iK2;
                    }
                } else {
                    iK = this.t[i2].k(Integer.MIN_VALUE);
                    if (iK != Integer.MIN_VALUE) {
                        iK2 = this.u.k();
                        iK -= iK2;
                    }
                }
                eVar2.f1713d[i2] = iK;
            }
        } else {
            eVar2.f1710a = -1;
            eVar2.f1711b = -1;
            eVar2.f1712c = 0;
        }
        return eVar2;
    }

    public final void A1(int i, int i2) {
        for (int i3 = 0; i3 < this.s; i3++) {
            if (!this.t[i3].f1716a.isEmpty()) {
                C1(this.t[i3], i, i2);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int B(RecyclerView.t tVar, RecyclerView.y yVar) {
        return this.w == 1 ? this.s : super.B(tVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void B0(int i) {
        if (i == 0) {
            a1();
        }
    }

    public final void B1(int i, RecyclerView.y yVar) {
        int iL;
        int iL2;
        int i2;
        r rVar = this.y;
        boolean z = false;
        rVar.f1258b = 0;
        rVar.f1259c = i;
        RecyclerView.x xVar = this.g;
        if (!(xVar != null && xVar.f1681e) || (i2 = yVar.f1689a) == -1) {
            iL = 0;
            iL2 = 0;
        } else {
            if (this.A == (i2 < i)) {
                iL = this.u.l();
                iL2 = 0;
            } else {
                iL2 = this.u.l();
                iL = 0;
            }
        }
        RecyclerView recyclerView = this.f1648b;
        if (recyclerView != null && recyclerView.g) {
            this.y.f1262f = this.u.k() - iL2;
            this.y.g = this.u.g() + iL;
        } else {
            this.y.g = this.u.f() + iL;
            this.y.f1262f = -iL2;
        }
        r rVar2 = this.y;
        rVar2.h = false;
        rVar2.f1257a = true;
        if (this.u.i() == 0 && this.u.f() == 0) {
            z = true;
        }
        rVar2.i = z;
    }

    public final void C1(f fVar, int i, int i2) {
        int i3 = fVar.f1719d;
        if (i == -1) {
            int i4 = fVar.f1717b;
            if (i4 == Integer.MIN_VALUE) {
                fVar.c();
                i4 = fVar.f1717b;
            }
            if (i4 + i3 > i2) {
                return;
            }
        } else {
            int i5 = fVar.f1718c;
            if (i5 == Integer.MIN_VALUE) {
                fVar.b();
                i5 = fVar.f1718c;
            }
            if (i5 - i3 < i2) {
                return;
            }
        }
        this.B.set(fVar.f1720e, false);
    }

    public final int D1(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int L0(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        return y1(i, tVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void M0(int i) {
        e eVar = this.I;
        if (eVar != null && eVar.f1710a != i) {
            eVar.f1713d = null;
            eVar.f1712c = 0;
            eVar.f1710a = -1;
            eVar.f1711b = -1;
        }
        this.C = i;
        this.D = Integer.MIN_VALUE;
        K0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int N0(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        return y1(i, tVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void Q0(Rect rect, int i, int i2) {
        int i3;
        int i4;
        int iP = P() + O();
        int iN = N() + Q();
        if (this.w == 1) {
            i4 = RecyclerView.m.i(i2, rect.height() + iN, L());
            i3 = RecyclerView.m.i(i, (this.x * this.s) + iP, M());
        } else {
            i3 = RecyclerView.m.i(i, rect.width() + iP, M());
            i4 = RecyclerView.m.i(i2, (this.x * this.s) + iN, L());
        }
        this.f1648b.setMeasuredDimension(i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int T(RecyclerView.t tVar, RecyclerView.y yVar) {
        return this.w == 0 ? this.s : super.T(tVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean W() {
        return this.F != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView recyclerView, RecyclerView.y yVar, int i) {
        s sVar = new s(recyclerView.getContext());
        sVar.f1677a = i;
        X0(sVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean Y0() {
        return this.I == null;
    }

    public final int Z0(int i) {
        if (z() == 0) {
            return this.A ? 1 : -1;
        }
        return (i < j1()) != this.A ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.x.b
    public PointF a(int i) {
        int iZ0 = Z0(i);
        PointF pointF = new PointF();
        if (iZ0 == 0) {
            return null;
        }
        if (this.w == 0) {
            pointF.x = iZ0;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iZ0;
        }
        return pointF;
    }

    public boolean a1() {
        int iJ1;
        int iK1;
        if (z() == 0 || this.F == 0 || !this.i) {
            return false;
        }
        if (this.A) {
            iJ1 = k1();
            iK1 = j1();
        } else {
            iJ1 = j1();
            iK1 = k1();
        }
        if (iJ1 == 0 && o1() != null) {
            this.E.b();
            this.h = true;
            K0();
            return true;
        }
        if (!this.M) {
            return false;
        }
        int i = this.A ? -1 : 1;
        int i2 = iK1 + 1;
        d.a aVarE = this.E.e(iJ1, i2, i, true);
        if (aVarE == null) {
            this.M = false;
            this.E.d(i2);
            return false;
        }
        d.a aVarE2 = this.E.e(iJ1, aVarE.f1706a, i * (-1), true);
        if (aVarE2 == null) {
            this.E.d(aVarE.f1706a);
        } else {
            this.E.d(aVarE2.f1706a + 1);
        }
        this.h = true;
        K0();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void b0(int i) {
        super.b0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            f fVar = this.t[i2];
            int i3 = fVar.f1717b;
            if (i3 != Integer.MIN_VALUE) {
                fVar.f1717b = i3 + i;
            }
            int i4 = fVar.f1718c;
            if (i4 != Integer.MIN_VALUE) {
                fVar.f1718c = i4 + i;
            }
        }
    }

    public final int b1(RecyclerView.y yVar) {
        if (z() == 0) {
            return 0;
        }
        return l.i.l(yVar, this.u, g1(!this.N), f1(!this.N), this, this.N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void c0(int i) {
        super.c0(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            f fVar = this.t[i2];
            int i3 = fVar.f1717b;
            if (i3 != Integer.MIN_VALUE) {
                fVar.f1717b = i3 + i;
            }
            int i4 = fVar.f1718c;
            if (i4 != Integer.MIN_VALUE) {
                fVar.f1718c = i4 + i;
            }
        }
    }

    public final int c1(RecyclerView.y yVar) {
        if (z() == 0) {
            return 0;
        }
        return l.i.m(yVar, this.u, g1(!this.N), f1(!this.N), this, this.N, this.A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void d(String str) {
        RecyclerView recyclerView;
        if (this.I != null || (recyclerView = this.f1648b) == null) {
            return;
        }
        recyclerView.i(str);
    }

    public final int d1(RecyclerView.y yVar) {
        if (z() == 0) {
            return 0;
        }
        return l.i.n(yVar, this.u, g1(!this.N), f1(!this.N), this, this.N);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:113:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01cf  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v7, types: [int] */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r19v0, types: [androidx.recyclerview.widget.RecyclerView$t] */
    /* JADX WARN: Type inference failed for: r1v105 */
    /* JADX WARN: Type inference failed for: r1v106 */
    /* JADX WARN: Type inference failed for: r1v107 */
    /* JADX WARN: Type inference failed for: r1v108 */
    /* JADX WARN: Type inference failed for: r1v109 */
    /* JADX WARN: Type inference failed for: r1v110 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v90 */
    /* JADX WARN: Type inference failed for: r1v91 */
    /* JADX WARN: Type inference failed for: r1v92, types: [int] */
    /* JADX WARN: Type inference failed for: r1v95, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int e1(androidx.recyclerview.widget.RecyclerView.t r19, a.p.e.r r20, androidx.recyclerview.widget.RecyclerView.y r21) {
        /*
            Method dump skipped, instruction units count: 933
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.e1(androidx.recyclerview.widget.RecyclerView$t, a.p.e.r, androidx.recyclerview.widget.RecyclerView$y):int");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean f() {
        return this.w == 0;
    }

    public View f1(boolean z) {
        int iK = this.u.k();
        int iG = this.u.g();
        View view = null;
        for (int iZ = z() - 1; iZ >= 0; iZ--) {
            View viewY = y(iZ);
            int iE = this.u.e(viewY);
            int iB = this.u.b(viewY);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z) {
                    return viewY;
                }
                if (view == null) {
                    view = viewY;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean g() {
        return this.w == 1;
    }

    public View g1(boolean z) {
        int iK = this.u.k();
        int iG = this.u.g();
        int iZ = z();
        View view = null;
        for (int i = 0; i < iZ; i++) {
            View viewY = y(i);
            int iE = this.u.e(viewY);
            if (this.u.b(viewY) > iK && iE < iG) {
                if (iE >= iK || !z) {
                    return viewY;
                }
                if (view == null) {
                    view = viewY;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean h(RecyclerView.n nVar) {
        return nVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void h0(RecyclerView recyclerView, RecyclerView.t tVar) {
        g0();
        Runnable runnable = this.P;
        RecyclerView recyclerView2 = this.f1648b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(runnable);
        }
        for (int i = 0; i < this.s; i++) {
            this.t[i].d();
        }
        recyclerView.requestLayout();
    }

    public final void h1(RecyclerView.t tVar, RecyclerView.y yVar, boolean z) {
        int iG;
        int iL1 = l1(Integer.MIN_VALUE);
        if (iL1 != Integer.MIN_VALUE && (iG = this.u.g() - iL1) > 0) {
            int i = iG - (-y1(-iG, tVar, yVar));
            if (!z || i <= 0) {
                return;
            }
            this.u.p(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x005b  */
    @Override // androidx.recyclerview.widget.RecyclerView.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View i0(android.view.View r10, int r11, androidx.recyclerview.widget.RecyclerView.t r12, androidx.recyclerview.widget.RecyclerView.y r13) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.i0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$y):android.view.View");
    }

    public final void i1(RecyclerView.t tVar, RecyclerView.y yVar, boolean z) {
        int iK;
        int iM1 = m1(Integer.MAX_VALUE);
        if (iM1 != Integer.MAX_VALUE && (iK = iM1 - this.u.k()) > 0) {
            int iY1 = iK - y1(iK, tVar, yVar);
            if (!z || iY1 <= 0) {
                return;
            }
            this.u.p(-iY1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void j(int i, int i2, RecyclerView.y yVar, RecyclerView.m.c cVar) {
        int iH;
        int iK;
        if (this.w != 0) {
            i = i2;
        }
        if (z() == 0 || i == 0) {
            return;
        }
        t1(i, yVar);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.s; i4++) {
            r rVar = this.y;
            if (rVar.f1260d == -1) {
                iH = rVar.f1262f;
                iK = this.t[i4].k(iH);
            } else {
                iH = this.t[i4].h(rVar.g);
                iK = this.y.g;
            }
            int i5 = iH - iK;
            if (i5 >= 0) {
                this.O[i3] = i5;
                i3++;
            }
        }
        Arrays.sort(this.O, 0, i3);
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = this.y.f1259c;
            if (!(i7 >= 0 && i7 < yVar.b())) {
                return;
            }
            ((n.b) cVar).a(this.y.f1259c, this.O[i6]);
            r rVar2 = this.y;
            rVar2.f1259c += rVar2.f1260d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void j0(AccessibilityEvent accessibilityEvent) {
        RecyclerView.t tVar = this.f1648b.f1616b;
        k0(accessibilityEvent);
        if (z() > 0) {
            View viewG1 = g1(false);
            View viewF1 = f1(false);
            if (viewG1 == null || viewF1 == null) {
                return;
            }
            int iR = R(viewG1);
            int iR2 = R(viewF1);
            if (iR < iR2) {
                accessibilityEvent.setFromIndex(iR);
                accessibilityEvent.setToIndex(iR2);
            } else {
                accessibilityEvent.setFromIndex(iR2);
                accessibilityEvent.setToIndex(iR);
            }
        }
    }

    public int j1() {
        if (z() == 0) {
            return 0;
        }
        return R(y(0));
    }

    public int k1() {
        int iZ = z();
        if (iZ == 0) {
            return 0;
        }
        return R(y(iZ - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int l(RecyclerView.y yVar) {
        return b1(yVar);
    }

    public final int l1(int i) {
        int iH = this.t[0].h(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iH2 = this.t[i2].h(i);
            if (iH2 > iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int m(RecyclerView.y yVar) {
        return c1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void m0(RecyclerView.t tVar, RecyclerView.y yVar, View view, a.h.k.x.b bVar) {
        b.c cVarA;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.l0(view, bVar);
            return;
        }
        c cVar = (c) layoutParams;
        if (this.w == 0) {
            f fVar = cVar.f1702e;
            cVarA = b.c.a(fVar != null ? fVar.f1720e : -1, cVar.f1703f ? this.s : 1, -1, -1, false, false);
        } else {
            f fVar2 = cVar.f1702e;
            cVarA = b.c.a(-1, -1, fVar2 != null ? fVar2.f1720e : -1, cVar.f1703f ? this.s : 1, false, false);
        }
        bVar.i(cVarA);
    }

    public final int m1(int i) {
        int iK = this.t[0].k(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int iK2 = this.t[i2].k(i);
            if (iK2 < iK) {
                iK = iK2;
            }
        }
        return iK;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int n(RecyclerView.y yVar) {
        return d1(yVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n1(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.A
            if (r0 == 0) goto L9
            int r0 = r6.k1()
            goto Ld
        L9:
            int r0 = r6.j1()
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
            r4.g(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.i(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r7 = r6.E
            r7.h(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.i(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.h(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.A
            if (r7 == 0) goto L4d
            int r7 = r6.j1()
            goto L51
        L4d:
            int r7 = r6.k1()
        L51:
            if (r3 > r7) goto L56
            r6.K0()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.n1(int, int, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int o(RecyclerView.y yVar) {
        return b1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void o0(RecyclerView recyclerView, int i, int i2) {
        n1(i, i2, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View o1() {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.o1():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int p(RecyclerView.y yVar) {
        return c1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void p0(RecyclerView recyclerView) {
        this.E.b();
        K0();
    }

    public boolean p1() {
        return K() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int q(RecyclerView.y yVar) {
        return d1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void q0(RecyclerView recyclerView, int i, int i2, int i3) {
        n1(i, i2, 8);
    }

    public final void q1(View view, int i, int i2, boolean z) {
        e(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int iD1 = D1(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int iD12 = D1(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z ? V0(view, iD1, iD12, cVar) : T0(view, iD1, iD12, cVar)) {
            view.measure(iD1, iD12);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void r0(RecyclerView recyclerView, int i, int i2) {
        n1(i, i2, 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x03ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r1(androidx.recyclerview.widget.RecyclerView.t r13, androidx.recyclerview.widget.RecyclerView.y r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 1054
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.r1(androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$y, boolean):void");
    }

    public final boolean s1(int i) {
        if (this.w == 0) {
            return (i == -1) != this.A;
        }
        return ((i == -1) == this.A) == p1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void t0(RecyclerView recyclerView, int i, int i2, Object obj) {
        n1(i, i2, 4);
    }

    public void t1(int i, RecyclerView.y yVar) {
        int i2;
        int iJ1;
        if (i > 0) {
            iJ1 = k1();
            i2 = 1;
        } else {
            i2 = -1;
            iJ1 = j1();
        }
        this.y.f1257a = true;
        B1(iJ1, yVar);
        z1(i2);
        r rVar = this.y;
        rVar.f1259c = iJ1 + rVar.f1260d;
        rVar.f1258b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void u0(RecyclerView.t tVar, RecyclerView.y yVar) {
        r1(tVar, yVar, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0011, code lost:
    
        if (r6.f1261e == (-1)) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u1(androidx.recyclerview.widget.RecyclerView.t r5, a.p.e.r r6) {
        /*
            r4 = this;
            boolean r0 = r6.f1257a
            if (r0 == 0) goto L7c
            boolean r0 = r6.i
            if (r0 == 0) goto La
            goto L7c
        La:
            int r0 = r6.f1258b
            r1 = -1
            if (r0 != 0) goto L1f
            int r0 = r6.f1261e
            if (r0 != r1) goto L19
        L13:
            int r6 = r6.g
        L15:
            r4.v1(r5, r6)
            goto L7c
        L19:
            int r6 = r6.f1262f
        L1b:
            r4.w1(r5, r6)
            goto L7c
        L1f:
            int r0 = r6.f1261e
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L50
            int r0 = r6.f1262f
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f[] r1 = r4.t
            r1 = r1[r2]
            int r1 = r1.k(r0)
        L2f:
            int r2 = r4.s
            if (r3 >= r2) goto L41
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f[] r2 = r4.t
            r2 = r2[r3]
            int r2 = r2.k(r0)
            if (r2 <= r1) goto L3e
            r1 = r2
        L3e:
            int r3 = r3 + 1
            goto L2f
        L41:
            int r0 = r0 - r1
            if (r0 >= 0) goto L45
            goto L13
        L45:
            int r1 = r6.g
            int r6 = r6.f1258b
            int r6 = java.lang.Math.min(r0, r6)
            int r6 = r1 - r6
            goto L15
        L50:
            int r0 = r6.g
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f[] r1 = r4.t
            r1 = r1[r2]
            int r1 = r1.h(r0)
        L5a:
            int r2 = r4.s
            if (r3 >= r2) goto L6c
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f[] r2 = r4.t
            r2 = r2[r3]
            int r2 = r2.h(r0)
            if (r2 >= r1) goto L69
            r1 = r2
        L69:
            int r3 = r3 + 1
            goto L5a
        L6c:
            int r0 = r6.g
            int r1 = r1 - r0
            if (r1 >= 0) goto L72
            goto L19
        L72:
            int r0 = r6.f1262f
            int r6 = r6.f1258b
            int r6 = java.lang.Math.min(r1, r6)
            int r6 = r6 + r0
            goto L1b
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.u1(androidx.recyclerview.widget.RecyclerView$t, a.p.e.r):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n v() {
        return this.w == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void v0(RecyclerView.y yVar) {
        this.C = -1;
        this.D = Integer.MIN_VALUE;
        this.I = null;
        this.L.b();
    }

    public final void v1(RecyclerView.t tVar, int i) {
        for (int iZ = z() - 1; iZ >= 0; iZ--) {
            View viewY = y(iZ);
            if (this.u.e(viewY) < i || this.u.o(viewY) < i) {
                return;
            }
            c cVar = (c) viewY.getLayoutParams();
            if (cVar.f1703f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1716a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].l();
                }
            } else if (cVar.f1702e.f1716a.size() == 1) {
                return;
            } else {
                cVar.f1702e.l();
            }
            G0(viewY, tVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n w(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    public final void w1(RecyclerView.t tVar, int i) {
        while (z() > 0) {
            View viewY = y(0);
            if (this.u.b(viewY) > i || this.u.n(viewY) > i) {
                return;
            }
            c cVar = (c) viewY.getLayoutParams();
            if (cVar.f1703f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1716a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].m();
                }
            } else if (cVar.f1702e.f1716a.size() == 1) {
                return;
            } else {
                cVar.f1702e.m();
            }
            G0(viewY, tVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n x(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public final void x1() {
        this.A = (this.w == 1 || !p1()) ? this.z : !this.z;
    }

    public int y1(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        if (z() == 0 || i == 0) {
            return 0;
        }
        t1(i, yVar);
        int iE1 = e1(tVar, this.y, yVar);
        if (this.y.f1258b >= iE1) {
            i = i < 0 ? -iE1 : iE1;
        }
        this.u.p(-i);
        this.G = this.A;
        r rVar = this.y;
        rVar.f1258b = 0;
        u1(tVar, rVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void z0(Parcelable parcelable) {
        if (parcelable instanceof e) {
            this.I = (e) parcelable;
            K0();
        }
    }

    public final void z1(int i) {
        r rVar = this.y;
        rVar.f1261e = i;
        rVar.f1260d = this.A != (i == -1) ? -1 : 1;
    }
}
