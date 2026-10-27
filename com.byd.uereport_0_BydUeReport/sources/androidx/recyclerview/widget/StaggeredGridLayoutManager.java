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
import c1.f;
import c1.g;
import c1.j;
import c1.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import p0.c;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.y.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d[] f1674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f1675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f1676d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f1679g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public BitSet f1682j;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1687o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1688p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public SavedState f1689q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1690r;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int[] f1695w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1673a = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1680h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1681i = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1683k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1684l = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public LazySpanLookup f1685m = new LazySpanLookup();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1686n = 2;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f1691s = new Rect();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b f1692t = new b();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1693u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1694v = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Runnable f1696x = new a();

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1703d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1704e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1705f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int[] f1706g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1707h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int[] f1708i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public List<LazySpanLookup.FullSpanItem> f1709j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1710k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1711l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f1712m;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void f() {
            this.f1706g = null;
            this.f1705f = 0;
            this.f1703d = -1;
            this.f1704e = -1;
        }

        public void g() {
            this.f1706g = null;
            this.f1705f = 0;
            this.f1707h = 0;
            this.f1708i = null;
            this.f1709j = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeInt(this.f1703d);
            parcel.writeInt(this.f1704e);
            parcel.writeInt(this.f1705f);
            if (this.f1705f > 0) {
                parcel.writeIntArray(this.f1706g);
            }
            parcel.writeInt(this.f1707h);
            if (this.f1707h > 0) {
                parcel.writeIntArray(this.f1708i);
            }
            parcel.writeInt(this.f1710k ? 1 : 0);
            parcel.writeInt(this.f1711l ? 1 : 0);
            parcel.writeInt(this.f1712m ? 1 : 0);
            parcel.writeList(this.f1709j);
        }

        public SavedState(Parcel parcel) {
            this.f1703d = parcel.readInt();
            this.f1704e = parcel.readInt();
            int i8 = parcel.readInt();
            this.f1705f = i8;
            if (i8 > 0) {
                int[] iArr = new int[i8];
                this.f1706g = iArr;
                parcel.readIntArray(iArr);
            }
            int i9 = parcel.readInt();
            this.f1707h = i9;
            if (i9 > 0) {
                int[] iArr2 = new int[i9];
                this.f1708i = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f1710k = parcel.readInt() == 1;
            this.f1711l = parcel.readInt() == 1;
            this.f1712m = parcel.readInt() == 1;
            this.f1709j = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f1705f = savedState.f1705f;
            this.f1703d = savedState.f1703d;
            this.f1704e = savedState.f1704e;
            this.f1706g = savedState.f1706g;
            this.f1707h = savedState.f1707h;
            this.f1708i = savedState.f1708i;
            this.f1710k = savedState.f1710k;
            this.f1711l = savedState.f1711l;
            this.f1712m = savedState.f1712m;
            this.f1709j = savedState.f1709j;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.g();
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1714a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1715b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1716c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1717d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1718e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int[] f1719f;

        public b() {
            c();
        }

        public void a() {
            this.f1715b = this.f1716c ? StaggeredGridLayoutManager.this.f1675c.i() : StaggeredGridLayoutManager.this.f1675c.m();
        }

        public void b(int i8) {
            if (this.f1716c) {
                this.f1715b = StaggeredGridLayoutManager.this.f1675c.i() - i8;
            } else {
                this.f1715b = StaggeredGridLayoutManager.this.f1675c.m() + i8;
            }
        }

        public void c() {
            this.f1714a = -1;
            this.f1715b = Integer.MIN_VALUE;
            this.f1716c = false;
            this.f1717d = false;
            this.f1718e = false;
            int[] iArr = this.f1719f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void d(d[] dVarArr) {
            int length = dVarArr.length;
            int[] iArr = this.f1719f;
            if (iArr == null || iArr.length < length) {
                this.f1719f = new int[StaggeredGridLayoutManager.this.f1674b.length];
            }
            for (int i8 = 0; i8 < length; i8++) {
                this.f1719f[i8] = dVarArr[i8].p(Integer.MIN_VALUE);
            }
        }
    }

    public static class c extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d f1721e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1722f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            d dVar = this.f1721e;
            if (dVar == null) {
                return -1;
            }
            return dVar.f1727e;
        }

        public boolean f() {
            return this.f1722f;
        }

        public c(int i8, int i9) {
            super(i8, i9);
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
        public ArrayList<View> f1723a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1724b = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1725c = Integer.MIN_VALUE;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1726d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1727e;

        public d(int i8) {
            this.f1727e = i8;
        }

        public void a(View view) {
            c cVarN = n(view);
            cVarN.f1721e = this;
            this.f1723a.add(view);
            this.f1725c = Integer.MIN_VALUE;
            if (this.f1723a.size() == 1) {
                this.f1724b = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1726d += StaggeredGridLayoutManager.this.f1675c.e(view);
            }
        }

        public void b(boolean z7, int i8) {
            int iL = z7 ? l(Integer.MIN_VALUE) : p(Integer.MIN_VALUE);
            e();
            if (iL == Integer.MIN_VALUE) {
                return;
            }
            if (!z7 || iL >= StaggeredGridLayoutManager.this.f1675c.i()) {
                if (z7 || iL <= StaggeredGridLayoutManager.this.f1675c.m()) {
                    if (i8 != Integer.MIN_VALUE) {
                        iL += i8;
                    }
                    this.f1725c = iL;
                    this.f1724b = iL;
                }
            }
        }

        public void c() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            ArrayList<View> arrayList = this.f1723a;
            View view = arrayList.get(arrayList.size() - 1);
            c cVarN = n(view);
            this.f1725c = StaggeredGridLayoutManager.this.f1675c.d(view);
            if (cVarN.f1722f && (fullSpanItemF = StaggeredGridLayoutManager.this.f1685m.f(cVarN.a())) != null && fullSpanItemF.f1700e == 1) {
                this.f1725c += fullSpanItemF.f(this.f1727e);
            }
        }

        public void d() {
            LazySpanLookup.FullSpanItem fullSpanItemF;
            View view = this.f1723a.get(0);
            c cVarN = n(view);
            this.f1724b = StaggeredGridLayoutManager.this.f1675c.g(view);
            if (cVarN.f1722f && (fullSpanItemF = StaggeredGridLayoutManager.this.f1685m.f(cVarN.a())) != null && fullSpanItemF.f1700e == -1) {
                this.f1724b -= fullSpanItemF.f(this.f1727e);
            }
        }

        public void e() {
            this.f1723a.clear();
            q();
            this.f1726d = 0;
        }

        public int f() {
            return StaggeredGridLayoutManager.this.f1680h ? i(this.f1723a.size() - 1, -1, true) : i(0, this.f1723a.size(), true);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.f1680h ? i(0, this.f1723a.size(), true) : i(this.f1723a.size() - 1, -1, true);
        }

        public int h(int i8, int i9, boolean z7, boolean z8, boolean z9) {
            int iM = StaggeredGridLayoutManager.this.f1675c.m();
            int i10 = StaggeredGridLayoutManager.this.f1675c.i();
            int i11 = i9 > i8 ? 1 : -1;
            while (i8 != i9) {
                View view = this.f1723a.get(i8);
                int iG = StaggeredGridLayoutManager.this.f1675c.g(view);
                int iD = StaggeredGridLayoutManager.this.f1675c.d(view);
                boolean z10 = false;
                boolean z11 = !z9 ? iG >= i10 : iG > i10;
                if (!z9 ? iD > iM : iD >= iM) {
                    z10 = true;
                }
                if (z11 && z10) {
                    if (z7 && z8) {
                        if (iG >= iM && iD <= i10) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    } else {
                        if (z8) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                        if (iG < iM || iD > i10) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    }
                }
                i8 += i11;
            }
            return -1;
        }

        public int i(int i8, int i9, boolean z7) {
            return h(i8, i9, false, false, z7);
        }

        public int j() {
            return this.f1726d;
        }

        public int k() {
            int i8 = this.f1725c;
            if (i8 != Integer.MIN_VALUE) {
                return i8;
            }
            c();
            return this.f1725c;
        }

        public int l(int i8) {
            int i9 = this.f1725c;
            if (i9 != Integer.MIN_VALUE) {
                return i9;
            }
            if (this.f1723a.size() == 0) {
                return i8;
            }
            c();
            return this.f1725c;
        }

        public View m(int i8, int i9) {
            View view = null;
            if (i9 != -1) {
                int size = this.f1723a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f1723a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.f1680h && staggeredGridLayoutManager.getPosition(view2) >= i8) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.f1680h && staggeredGridLayoutManager2.getPosition(view2) <= i8) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f1723a.size();
                int i10 = 0;
                while (i10 < size2) {
                    View view3 = this.f1723a.get(i10);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.f1680h && staggeredGridLayoutManager3.getPosition(view3) <= i8) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.f1680h && staggeredGridLayoutManager4.getPosition(view3) >= i8) || !view3.hasFocusable()) {
                        break;
                    }
                    i10++;
                    view = view3;
                }
            }
            return view;
        }

        public c n(View view) {
            return (c) view.getLayoutParams();
        }

        public int o() {
            int i8 = this.f1724b;
            if (i8 != Integer.MIN_VALUE) {
                return i8;
            }
            d();
            return this.f1724b;
        }

        public int p(int i8) {
            int i9 = this.f1724b;
            if (i9 != Integer.MIN_VALUE) {
                return i9;
            }
            if (this.f1723a.size() == 0) {
                return i8;
            }
            d();
            return this.f1724b;
        }

        public void q() {
            this.f1724b = Integer.MIN_VALUE;
            this.f1725c = Integer.MIN_VALUE;
        }

        public void r(int i8) {
            int i9 = this.f1724b;
            if (i9 != Integer.MIN_VALUE) {
                this.f1724b = i9 + i8;
            }
            int i10 = this.f1725c;
            if (i10 != Integer.MIN_VALUE) {
                this.f1725c = i10 + i8;
            }
        }

        public void s() {
            int size = this.f1723a.size();
            View viewRemove = this.f1723a.remove(size - 1);
            c cVarN = n(viewRemove);
            cVarN.f1721e = null;
            if (cVarN.c() || cVarN.b()) {
                this.f1726d -= StaggeredGridLayoutManager.this.f1675c.e(viewRemove);
            }
            if (size == 1) {
                this.f1724b = Integer.MIN_VALUE;
            }
            this.f1725c = Integer.MIN_VALUE;
        }

        public void t() {
            View viewRemove = this.f1723a.remove(0);
            c cVarN = n(viewRemove);
            cVarN.f1721e = null;
            if (this.f1723a.size() == 0) {
                this.f1725c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1726d -= StaggeredGridLayoutManager.this.f1675c.e(viewRemove);
            }
            this.f1724b = Integer.MIN_VALUE;
        }

        public void u(View view) {
            c cVarN = n(view);
            cVarN.f1721e = this;
            this.f1723a.add(0, view);
            this.f1724b = Integer.MIN_VALUE;
            if (this.f1723a.size() == 1) {
                this.f1725c = Integer.MIN_VALUE;
            }
            if (cVarN.c() || cVarN.b()) {
                this.f1726d += StaggeredGridLayoutManager.this.f1675c.e(view);
            }
        }

        public void v(int i8) {
            this.f1724b = i8;
            this.f1725c = i8;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i8, int i9) {
        RecyclerView.o.d properties = RecyclerView.o.getProperties(context, attributeSet, i8, i9);
        setOrientation(properties.f1625a);
        O(properties.f1626b);
        setReverseLayout(properties.f1627c);
        this.f1679g = new f();
        k();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void A(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.f1681i
            if (r0 == 0) goto L9
            int r0 = r6.u()
            goto Ld
        L9:
            int r0 = r6.t()
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.f1685m
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.f1685m
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.f1685m
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.f1685m
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.f1685m
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.f1681i
            if (r7 == 0) goto L4d
            int r7 = r6.t()
            goto L51
        L4d:
            int r7 = r6.u()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.A(int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View B() {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.f1673a
            r2.<init>(r3)
            int r3 = r12.f1673a
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.f1677e
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.isLayoutRTL()
            if (r3 == 0) goto L20
            r3 = r1
            goto L21
        L20:
            r3 = r5
        L21:
            boolean r6 = r12.f1681i
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
            android.view.View r7 = r12.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1721e
            int r9 = r9.f1727e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1721e
            boolean r9 = r12.h(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r8.f1721e
            int r9 = r9.f1727e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f1722f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.getChildAt(r9)
            boolean r10 = r12.f1681i
            if (r10 == 0) goto L77
            c1.j r10 = r12.f1675c
            int r10 = r10.d(r7)
            c1.j r11 = r12.f1675c
            int r11 = r11.d(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            c1.j r10 = r12.f1675c
            int r10 = r10.g(r7)
            c1.j r11 = r12.f1675c
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
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r8 = r8.f1721e
            int r8 = r8.f1727e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r9.f1721e
            int r9 = r9.f1727e
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.B():android.view.View");
    }

    public void C() {
        this.f1685m.b();
        requestLayout();
    }

    public final void D(View view, int i8, int i9, boolean z7) {
        calculateItemDecorationsForChild(view, this.f1691s);
        c cVar = (c) view.getLayoutParams();
        int i10 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.f1691s;
        int iW = W(i8, i10 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i11 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.f1691s;
        int iW2 = W(i9, i11 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z7 ? shouldReMeasureChild(view, iW, iW2, cVar) : shouldMeasureChild(view, iW, iW2, cVar)) {
            view.measure(iW, iW2);
        }
    }

    public final void E(View view, c cVar, boolean z7) {
        if (cVar.f1722f) {
            if (this.f1677e == 1) {
                D(view, this.f1690r, RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z7);
                return;
            } else {
                D(view, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.f1690r, z7);
                return;
            }
        }
        if (this.f1677e == 1) {
            D(view, RecyclerView.o.getChildMeasureSpec(this.f1678f, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z7);
        } else {
            D(view, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.getChildMeasureSpec(this.f1678f, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.z r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.F(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, boolean):void");
    }

    public final boolean G(int i8) {
        if (this.f1677e == 0) {
            return (i8 == -1) != this.f1681i;
        }
        return ((i8 == -1) == this.f1681i) == isLayoutRTL();
    }

    public void H(int i8, RecyclerView.z zVar) {
        int i9;
        int iT;
        if (i8 > 0) {
            iT = u();
            i9 = 1;
        } else {
            i9 = -1;
            iT = t();
        }
        this.f1679g.f2259a = true;
        T(iT, zVar);
        N(i9);
        f fVar = this.f1679g;
        fVar.f2261c = iT + fVar.f2262d;
        fVar.f2260b = Math.abs(i8);
    }

    public final void I(View view) {
        for (int i8 = this.f1673a - 1; i8 >= 0; i8--) {
            this.f1674b[i8].u(view);
        }
    }

    public final void J(RecyclerView.v vVar, f fVar) {
        if (!fVar.f2259a || fVar.f2267i) {
            return;
        }
        if (fVar.f2260b == 0) {
            if (fVar.f2263e == -1) {
                K(vVar, fVar.f2265g);
                return;
            } else {
                L(vVar, fVar.f2264f);
                return;
            }
        }
        if (fVar.f2263e != -1) {
            int iX = x(fVar.f2265g) - fVar.f2265g;
            L(vVar, iX < 0 ? fVar.f2264f : Math.min(iX, fVar.f2260b) + fVar.f2264f);
        } else {
            int i8 = fVar.f2264f;
            int iW = i8 - w(i8);
            K(vVar, iW < 0 ? fVar.f2265g : fVar.f2265g - Math.min(iW, fVar.f2260b));
        }
    }

    public final void K(RecyclerView.v vVar, int i8) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.f1675c.g(childAt) < i8 || this.f1675c.q(childAt) < i8) {
                return;
            }
            c cVar = (c) childAt.getLayoutParams();
            if (cVar.f1722f) {
                for (int i9 = 0; i9 < this.f1673a; i9++) {
                    if (this.f1674b[i9].f1723a.size() == 1) {
                        return;
                    }
                }
                for (int i10 = 0; i10 < this.f1673a; i10++) {
                    this.f1674b[i10].s();
                }
            } else if (cVar.f1721e.f1723a.size() == 1) {
                return;
            } else {
                cVar.f1721e.s();
            }
            removeAndRecycleView(childAt, vVar);
        }
    }

    public final void L(RecyclerView.v vVar, int i8) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.f1675c.d(childAt) > i8 || this.f1675c.p(childAt) > i8) {
                return;
            }
            c cVar = (c) childAt.getLayoutParams();
            if (cVar.f1722f) {
                for (int i9 = 0; i9 < this.f1673a; i9++) {
                    if (this.f1674b[i9].f1723a.size() == 1) {
                        return;
                    }
                }
                for (int i10 = 0; i10 < this.f1673a; i10++) {
                    this.f1674b[i10].t();
                }
            } else if (cVar.f1721e.f1723a.size() == 1) {
                return;
            } else {
                cVar.f1721e.t();
            }
            removeAndRecycleView(childAt, vVar);
        }
    }

    public final void M() {
        if (this.f1676d.k() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            float fE = this.f1676d.e(childAt);
            if (fE >= fMax) {
                if (((c) childAt.getLayoutParams()).f()) {
                    fE = (fE * 1.0f) / this.f1673a;
                }
                fMax = Math.max(fMax, fE);
            }
        }
        int i9 = this.f1678f;
        int iRound = Math.round(fMax * this.f1673a);
        if (this.f1676d.k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.f1676d.n());
        }
        U(iRound);
        if (this.f1678f == i9) {
            return;
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            c cVar = (c) childAt2.getLayoutParams();
            if (!cVar.f1722f) {
                if (isLayoutRTL() && this.f1677e == 1) {
                    int i11 = this.f1673a;
                    int i12 = cVar.f1721e.f1727e;
                    childAt2.offsetLeftAndRight(((-((i11 - 1) - i12)) * this.f1678f) - ((-((i11 - 1) - i12)) * i9));
                } else {
                    int i13 = cVar.f1721e.f1727e;
                    int i14 = this.f1678f * i13;
                    int i15 = i13 * i9;
                    if (this.f1677e == 1) {
                        childAt2.offsetLeftAndRight(i14 - i15);
                    } else {
                        childAt2.offsetTopAndBottom(i14 - i15);
                    }
                }
            }
        }
    }

    public final void N(int i8) {
        f fVar = this.f1679g;
        fVar.f2263e = i8;
        fVar.f2262d = this.f1681i != (i8 == -1) ? -1 : 1;
    }

    public void O(int i8) {
        assertNotInLayoutOrScroll(null);
        if (i8 != this.f1673a) {
            C();
            this.f1673a = i8;
            this.f1682j = new BitSet(this.f1673a);
            this.f1674b = new d[this.f1673a];
            for (int i9 = 0; i9 < this.f1673a; i9++) {
                this.f1674b[i9] = new d(i9);
            }
            requestLayout();
        }
    }

    public final void P(int i8, int i9) {
        for (int i10 = 0; i10 < this.f1673a; i10++) {
            if (!this.f1674b[i10].f1723a.isEmpty()) {
                V(this.f1674b[i10], i8, i9);
            }
        }
    }

    public final boolean Q(RecyclerView.z zVar, b bVar) {
        bVar.f1714a = this.f1687o ? q(zVar.b()) : m(zVar.b());
        bVar.f1715b = Integer.MIN_VALUE;
        return true;
    }

    public boolean R(RecyclerView.z zVar, b bVar) {
        int i8;
        if (!zVar.e() && (i8 = this.f1683k) != -1) {
            if (i8 >= 0 && i8 < zVar.b()) {
                SavedState savedState = this.f1689q;
                if (savedState == null || savedState.f1703d == -1 || savedState.f1705f < 1) {
                    View viewFindViewByPosition = findViewByPosition(this.f1683k);
                    if (viewFindViewByPosition != null) {
                        bVar.f1714a = this.f1681i ? u() : t();
                        if (this.f1684l != Integer.MIN_VALUE) {
                            if (bVar.f1716c) {
                                bVar.f1715b = (this.f1675c.i() - this.f1684l) - this.f1675c.d(viewFindViewByPosition);
                            } else {
                                bVar.f1715b = (this.f1675c.m() + this.f1684l) - this.f1675c.g(viewFindViewByPosition);
                            }
                            return true;
                        }
                        if (this.f1675c.e(viewFindViewByPosition) > this.f1675c.n()) {
                            bVar.f1715b = bVar.f1716c ? this.f1675c.i() : this.f1675c.m();
                            return true;
                        }
                        int iG = this.f1675c.g(viewFindViewByPosition) - this.f1675c.m();
                        if (iG < 0) {
                            bVar.f1715b = -iG;
                            return true;
                        }
                        int i9 = this.f1675c.i() - this.f1675c.d(viewFindViewByPosition);
                        if (i9 < 0) {
                            bVar.f1715b = i9;
                            return true;
                        }
                        bVar.f1715b = Integer.MIN_VALUE;
                    } else {
                        int i10 = this.f1683k;
                        bVar.f1714a = i10;
                        int i11 = this.f1684l;
                        if (i11 == Integer.MIN_VALUE) {
                            bVar.f1716c = f(i10) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i11);
                        }
                        bVar.f1717d = true;
                    }
                } else {
                    bVar.f1715b = Integer.MIN_VALUE;
                    bVar.f1714a = this.f1683k;
                }
                return true;
            }
            this.f1683k = -1;
            this.f1684l = Integer.MIN_VALUE;
        }
        return false;
    }

    public void S(RecyclerView.z zVar, b bVar) {
        if (R(zVar, bVar) || Q(zVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1714a = 0;
    }

    public final void T(int i8, RecyclerView.z zVar) {
        int iN;
        int iN2;
        int iC;
        f fVar = this.f1679g;
        boolean z7 = false;
        fVar.f2260b = 0;
        fVar.f2261c = i8;
        if (!isSmoothScrolling() || (iC = zVar.c()) == -1) {
            iN = 0;
            iN2 = 0;
        } else {
            if (this.f1681i == (iC < i8)) {
                iN = this.f1675c.n();
                iN2 = 0;
            } else {
                iN2 = this.f1675c.n();
                iN = 0;
            }
        }
        if (getClipToPadding()) {
            this.f1679g.f2264f = this.f1675c.m() - iN2;
            this.f1679g.f2265g = this.f1675c.i() + iN;
        } else {
            this.f1679g.f2265g = this.f1675c.h() + iN;
            this.f1679g.f2264f = -iN2;
        }
        f fVar2 = this.f1679g;
        fVar2.f2266h = false;
        fVar2.f2259a = true;
        if (this.f1675c.k() == 0 && this.f1675c.h() == 0) {
            z7 = true;
        }
        fVar2.f2267i = z7;
    }

    public void U(int i8) {
        this.f1678f = i8 / this.f1673a;
        this.f1690r = View.MeasureSpec.makeMeasureSpec(i8, this.f1676d.k());
    }

    public final void V(d dVar, int i8, int i9) {
        int iJ = dVar.j();
        if (i8 == -1) {
            if (dVar.o() + iJ <= i9) {
                this.f1682j.set(dVar.f1727e, false);
            }
        } else if (dVar.k() - iJ >= i9) {
            this.f1682j.set(dVar.f1727e, false);
        }
    }

    public final int W(int i8, int i9, int i10) {
        if (i9 == 0 && i10 == 0) {
            return i8;
        }
        int mode = View.MeasureSpec.getMode(i8);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i8) - i9) - i10), mode) : i8;
    }

    public final void a(View view) {
        for (int i8 = this.f1673a - 1; i8 >= 0; i8--) {
            this.f1674b[i8].a(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void assertNotInLayoutOrScroll(String str) {
        if (this.f1689q == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public final void b(b bVar) {
        SavedState savedState = this.f1689q;
        int i8 = savedState.f1705f;
        if (i8 > 0) {
            if (i8 == this.f1673a) {
                for (int i9 = 0; i9 < this.f1673a; i9++) {
                    this.f1674b[i9].e();
                    SavedState savedState2 = this.f1689q;
                    int i10 = savedState2.f1706g[i9];
                    if (i10 != Integer.MIN_VALUE) {
                        i10 += savedState2.f1711l ? this.f1675c.i() : this.f1675c.m();
                    }
                    this.f1674b[i9].v(i10);
                }
            } else {
                savedState.g();
                SavedState savedState3 = this.f1689q;
                savedState3.f1703d = savedState3.f1704e;
            }
        }
        SavedState savedState4 = this.f1689q;
        this.f1688p = savedState4.f1712m;
        setReverseLayout(savedState4.f1710k);
        resolveShouldLayoutReverse();
        SavedState savedState5 = this.f1689q;
        int i11 = savedState5.f1703d;
        if (i11 != -1) {
            this.f1683k = i11;
            bVar.f1716c = savedState5.f1711l;
        } else {
            bVar.f1716c = this.f1681i;
        }
        if (savedState5.f1707h > 1) {
            LazySpanLookup lazySpanLookup = this.f1685m;
            lazySpanLookup.f1697a = savedState5.f1708i;
            lazySpanLookup.f1698b = savedState5.f1709j;
        }
    }

    public boolean c() {
        int iL = this.f1674b[0].l(Integer.MIN_VALUE);
        for (int i8 = 1; i8 < this.f1673a; i8++) {
            if (this.f1674b[i8].l(Integer.MIN_VALUE) != iL) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollHorizontally() {
        return this.f1677e == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollVertically() {
        return this.f1677e == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean checkLayoutParams(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectAdjacentPrefetchPositions(int i8, int i9, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        int iL;
        int iP;
        if (this.f1677e != 0) {
            i8 = i9;
        }
        if (getChildCount() == 0 || i8 == 0) {
            return;
        }
        H(i8, zVar);
        int[] iArr = this.f1695w;
        if (iArr == null || iArr.length < this.f1673a) {
            this.f1695w = new int[this.f1673a];
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.f1673a; i11++) {
            f fVar = this.f1679g;
            if (fVar.f2262d == -1) {
                iL = fVar.f2264f;
                iP = this.f1674b[i11].p(iL);
            } else {
                iL = this.f1674b[i11].l(fVar.f2265g);
                iP = this.f1679g.f2265g;
            }
            int i12 = iL - iP;
            if (i12 >= 0) {
                this.f1695w[i10] = i12;
                i10++;
            }
        }
        Arrays.sort(this.f1695w, 0, i10);
        for (int i13 = 0; i13 < i10 && this.f1679g.a(zVar); i13++) {
            cVar.a(this.f1679g.f2261c, this.f1695w[i13]);
            f fVar2 = this.f1679g;
            fVar2.f2261c += fVar2.f2262d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollExtent(RecyclerView.z zVar) {
        return computeScrollExtent(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollOffset(RecyclerView.z zVar) {
        return computeScrollOffset(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollRange(RecyclerView.z zVar) {
        return computeScrollRange(zVar);
    }

    public final int computeScrollExtent(RecyclerView.z zVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return m.a(zVar, this.f1675c, o(!this.f1694v), n(!this.f1694v), this, this.f1694v);
    }

    public final int computeScrollOffset(RecyclerView.z zVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return m.b(zVar, this.f1675c, o(!this.f1694v), n(!this.f1694v), this, this.f1694v, this.f1681i);
    }

    public final int computeScrollRange(RecyclerView.z zVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return m.c(zVar, this.f1675c, o(!this.f1694v), n(!this.f1694v), this, this.f1694v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF computeScrollVectorForPosition(int i8) {
        int iF = f(i8);
        PointF pointF = new PointF();
        if (iF == 0) {
            return null;
        }
        if (this.f1677e == 0) {
            pointF.x = iF;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iF;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollExtent(RecyclerView.z zVar) {
        return computeScrollExtent(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollOffset(RecyclerView.z zVar) {
        return computeScrollOffset(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollRange(RecyclerView.z zVar) {
        return computeScrollRange(zVar);
    }

    public final int convertFocusDirectionToLayoutDirection(int i8) {
        return i8 != 1 ? i8 != 2 ? i8 != 17 ? i8 != 33 ? i8 != 66 ? (i8 == 130 && this.f1677e == 1) ? 1 : Integer.MIN_VALUE : this.f1677e == 0 ? 1 : Integer.MIN_VALUE : this.f1677e == 1 ? -1 : Integer.MIN_VALUE : this.f1677e == 0 ? -1 : Integer.MIN_VALUE : (this.f1677e != 1 && isLayoutRTL()) ? -1 : 1 : (this.f1677e != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public boolean d() {
        int iP = this.f1674b[0].p(Integer.MIN_VALUE);
        for (int i8 = 1; i8 < this.f1673a; i8++) {
            if (this.f1674b[i8].p(Integer.MIN_VALUE) != iP) {
                return false;
            }
        }
        return true;
    }

    public final void e(View view, c cVar, f fVar) {
        if (fVar.f2263e == 1) {
            if (cVar.f1722f) {
                a(view);
                return;
            } else {
                cVar.f1721e.a(view);
                return;
            }
        }
        if (cVar.f1722f) {
            I(view);
        } else {
            cVar.f1721e.u(view);
        }
    }

    public final int f(int i8) {
        if (getChildCount() == 0) {
            return this.f1681i ? 1 : -1;
        }
        return (i8 < t()) != this.f1681i ? -1 : 1;
    }

    public boolean g() {
        int iT;
        int iU;
        if (getChildCount() == 0 || this.f1686n == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.f1681i) {
            iT = u();
            iU = t();
        } else {
            iT = t();
            iU = u();
        }
        if (iT == 0 && B() != null) {
            this.f1685m.b();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
        if (!this.f1693u) {
            return false;
        }
        int i8 = this.f1681i ? -1 : 1;
        int i9 = iU + 1;
        LazySpanLookup.FullSpanItem fullSpanItemE = this.f1685m.e(iT, i9, i8, true);
        if (fullSpanItemE == null) {
            this.f1693u = false;
            this.f1685m.d(i9);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemE2 = this.f1685m.e(iT, fullSpanItemE.f1699d, i8 * (-1), true);
        if (fullSpanItemE2 == null) {
            this.f1685m.d(fullSpanItemE.f1699d);
        } else {
            this.f1685m.d(fullSpanItemE2.f1699d + 1);
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateDefaultLayoutParams() {
        return this.f1677e == 0 ? new c(-2, -1) : new c(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getColumnCountForAccessibility(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f1677e == 1 ? this.f1673a : super.getColumnCountForAccessibility(vVar, zVar);
    }

    public int getOrientation() {
        return this.f1677e;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getRowCountForAccessibility(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.f1677e == 0 ? this.f1673a : super.getRowCountForAccessibility(vVar, zVar);
    }

    public final boolean h(d dVar) {
        if (this.f1681i) {
            if (dVar.k() < this.f1675c.i()) {
                ArrayList<View> arrayList = dVar.f1723a;
                return !dVar.n(arrayList.get(arrayList.size() - 1)).f1722f;
            }
        } else if (dVar.o() > this.f1675c.m()) {
            return !dVar.n(dVar.f1723a.get(0)).f1722f;
        }
        return false;
    }

    public final LazySpanLookup.FullSpanItem i(int i8) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1701f = new int[this.f1673a];
        for (int i9 = 0; i9 < this.f1673a; i9++) {
            fullSpanItem.f1701f[i9] = i8 - this.f1674b[i9].l(i8);
        }
        return fullSpanItem;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean isAutoMeasureEnabled() {
        return this.f1686n != 0;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public final LazySpanLookup.FullSpanItem j(int i8) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f1701f = new int[this.f1673a];
        for (int i9 = 0; i9 < this.f1673a; i9++) {
            fullSpanItem.f1701f[i9] = this.f1674b[i9].p(i8) - i8;
        }
        return fullSpanItem;
    }

    public final void k() {
        this.f1675c = j.b(this, this.f1677e);
        this.f1676d = j.b(this, 1 - this.f1677e);
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
    public final int l(RecyclerView.v vVar, f fVar, RecyclerView.z zVar) {
        d dVarZ;
        int iE;
        int i8;
        int iE2;
        int iE3;
        boolean z7;
        ?? r9 = 0;
        this.f1682j.set(0, this.f1673a, true);
        int i9 = this.f1679g.f2267i ? fVar.f2263e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : fVar.f2263e == 1 ? fVar.f2265g + fVar.f2260b : fVar.f2264f - fVar.f2260b;
        P(fVar.f2263e, i9);
        int i10 = this.f1681i ? this.f1675c.i() : this.f1675c.m();
        ?? r02 = false;
        while (fVar.a(zVar) && (this.f1679g.f2267i || !this.f1682j.isEmpty())) {
            View viewB = fVar.b(vVar);
            c cVar = (c) viewB.getLayoutParams();
            int iA = cVar.a();
            int iG = this.f1685m.g(iA);
            ?? r32 = iG == -1 ? 1 : r9;
            if (r32 != 0) {
                dVarZ = cVar.f1722f ? this.f1674b[r9] : z(fVar);
                this.f1685m.n(iA, dVarZ);
            } else {
                dVarZ = this.f1674b[iG];
            }
            d dVar = dVarZ;
            cVar.f1721e = dVar;
            if (fVar.f2263e == 1) {
                addView(viewB);
            } else {
                addView(viewB, r9);
            }
            E(viewB, cVar, r9);
            if (fVar.f2263e == 1) {
                int iV = cVar.f1722f ? v(i10) : dVar.l(i10);
                int iE4 = this.f1675c.e(viewB) + iV;
                if (r32 != 0 && cVar.f1722f) {
                    LazySpanLookup.FullSpanItem fullSpanItemI = i(iV);
                    fullSpanItemI.f1700e = -1;
                    fullSpanItemI.f1699d = iA;
                    this.f1685m.a(fullSpanItemI);
                }
                i8 = iE4;
                iE = iV;
            } else {
                int iY = cVar.f1722f ? y(i10) : dVar.p(i10);
                iE = iY - this.f1675c.e(viewB);
                if (r32 != 0 && cVar.f1722f) {
                    LazySpanLookup.FullSpanItem fullSpanItemJ = j(iY);
                    fullSpanItemJ.f1700e = 1;
                    fullSpanItemJ.f1699d = iA;
                    this.f1685m.a(fullSpanItemJ);
                }
                i8 = iY;
            }
            if (cVar.f1722f && fVar.f2262d == -1) {
                if (r32 != 0) {
                    this.f1693u = true;
                } else {
                    if (!(fVar.f2263e == 1 ? c() : d())) {
                        LazySpanLookup.FullSpanItem fullSpanItemF = this.f1685m.f(iA);
                        if (fullSpanItemF != null) {
                            fullSpanItemF.f1702g = true;
                        }
                        this.f1693u = true;
                    }
                }
            }
            e(viewB, cVar, fVar);
            if (isLayoutRTL() && this.f1677e == 1) {
                int i11 = cVar.f1722f ? this.f1676d.i() : this.f1676d.i() - (((this.f1673a - 1) - dVar.f1727e) * this.f1678f);
                iE3 = i11;
                iE2 = i11 - this.f1676d.e(viewB);
            } else {
                int iM = cVar.f1722f ? this.f1676d.m() : (dVar.f1727e * this.f1678f) + this.f1676d.m();
                iE2 = iM;
                iE3 = this.f1676d.e(viewB) + iM;
            }
            if (this.f1677e == 1) {
                layoutDecoratedWithMargins(viewB, iE2, iE, iE3, i8);
            } else {
                layoutDecoratedWithMargins(viewB, iE, iE2, i8, iE3);
            }
            if (cVar.f1722f) {
                P(this.f1679g.f2263e, i9);
            } else {
                V(dVar, this.f1679g.f2263e, i9);
            }
            J(vVar, this.f1679g);
            if (!this.f1679g.f2266h || !viewB.hasFocusable()) {
                z7 = false;
            } else if (cVar.f1722f) {
                this.f1682j.clear();
                z7 = false;
            } else {
                z7 = false;
                this.f1682j.set(dVar.f1727e, false);
            }
            r9 = z7;
            r02 = true;
        }
        ?? r33 = r9;
        if (r02 == false) {
            J(vVar, this.f1679g);
        }
        int iM2 = this.f1679g.f2263e == -1 ? this.f1675c.m() - y(this.f1675c.m()) : v(this.f1675c.i()) - this.f1675c.i();
        return iM2 > 0 ? Math.min(fVar.f2260b, iM2) : r33 == true ? 1 : 0;
    }

    public final int m(int i8) {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            int position = getPosition(getChildAt(i9));
            if (position >= 0 && position < i8) {
                return position;
            }
        }
        return 0;
    }

    public View n(boolean z7) {
        int iM = this.f1675c.m();
        int i8 = this.f1675c.i();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int iG = this.f1675c.g(childAt);
            int iD = this.f1675c.d(childAt);
            if (iD > iM && iG < i8) {
                if (iD <= i8 || !z7) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public View o(boolean z7) {
        int iM = this.f1675c.m();
        int i8 = this.f1675c.i();
        int childCount = getChildCount();
        View view = null;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            int iG = this.f1675c.g(childAt);
            if (this.f1675c.d(childAt) > iM && iG < i8) {
                if (iG >= iM || !z7) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void offsetChildrenHorizontal(int i8) {
        super.offsetChildrenHorizontal(i8);
        for (int i9 = 0; i9 < this.f1673a; i9++) {
            this.f1674b[i9].r(i8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void offsetChildrenVertical(int i8) {
        super.offsetChildrenVertical(i8);
        for (int i9 = 0; i9 < this.f1673a; i9++) {
            this.f1674b[i9].r(i8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.onDetachedFromWindow(recyclerView, vVar);
        removeCallbacks(this.f1696x);
        for (int i8 = 0; i8 < this.f1673a; i8++) {
            this.f1674b[i8].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View onFocusSearchFailed(View view, int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        View viewFindContainingItemView;
        View viewM;
        if (getChildCount() == 0 || (viewFindContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i8);
        if (iConvertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) viewFindContainingItemView.getLayoutParams();
        boolean z7 = cVar.f1722f;
        d dVar = cVar.f1721e;
        int iU = iConvertFocusDirectionToLayoutDirection == 1 ? u() : t();
        T(iU, zVar);
        N(iConvertFocusDirectionToLayoutDirection);
        f fVar = this.f1679g;
        fVar.f2261c = fVar.f2262d + iU;
        fVar.f2260b = (int) (this.f1675c.n() * 0.33333334f);
        f fVar2 = this.f1679g;
        fVar2.f2266h = true;
        fVar2.f2259a = false;
        l(vVar, fVar2, zVar);
        this.f1687o = this.f1681i;
        if (!z7 && (viewM = dVar.m(iU, iConvertFocusDirectionToLayoutDirection)) != null && viewM != viewFindContainingItemView) {
            return viewM;
        }
        if (G(iConvertFocusDirectionToLayoutDirection)) {
            for (int i9 = this.f1673a - 1; i9 >= 0; i9--) {
                View viewM2 = this.f1674b[i9].m(iU, iConvertFocusDirectionToLayoutDirection);
                if (viewM2 != null && viewM2 != viewFindContainingItemView) {
                    return viewM2;
                }
            }
        } else {
            for (int i10 = 0; i10 < this.f1673a; i10++) {
                View viewM3 = this.f1674b[i10].m(iU, iConvertFocusDirectionToLayoutDirection);
                if (viewM3 != null && viewM3 != viewFindContainingItemView) {
                    return viewM3;
                }
            }
        }
        boolean z8 = (this.f1680h ^ true) == (iConvertFocusDirectionToLayoutDirection == -1);
        if (!z7) {
            View viewFindViewByPosition = findViewByPosition(z8 ? dVar.f() : dVar.g());
            if (viewFindViewByPosition != null && viewFindViewByPosition != viewFindContainingItemView) {
                return viewFindViewByPosition;
            }
        }
        if (G(iConvertFocusDirectionToLayoutDirection)) {
            for (int i11 = this.f1673a - 1; i11 >= 0; i11--) {
                if (i11 != dVar.f1727e) {
                    View viewFindViewByPosition2 = findViewByPosition(z8 ? this.f1674b[i11].f() : this.f1674b[i11].g());
                    if (viewFindViewByPosition2 != null && viewFindViewByPosition2 != viewFindContainingItemView) {
                        return viewFindViewByPosition2;
                    }
                }
            }
        } else {
            for (int i12 = 0; i12 < this.f1673a; i12++) {
                View viewFindViewByPosition3 = findViewByPosition(z8 ? this.f1674b[i12].f() : this.f1674b[i12].g());
                if (viewFindViewByPosition3 != null && viewFindViewByPosition3 != viewFindContainingItemView) {
                    return viewFindViewByPosition3;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View viewO = o(false);
            View viewN = n(false);
            if (viewO == null || viewN == null) {
                return;
            }
            int position = getPosition(viewO);
            int position2 = getPosition(viewN);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
            } else {
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.v vVar, RecyclerView.z zVar, View view, p0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.f1677e == 0) {
            cVar.f0(c.C0101c.a(cVar2.e(), cVar2.f1722f ? this.f1673a : 1, -1, -1, false, false));
        } else {
            cVar.f0(c.C0101c.a(-1, -1, cVar2.e(), cVar2.f1722f ? this.f1673a : 1, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsAdded(RecyclerView recyclerView, int i8, int i9) {
        A(i8, i9, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsChanged(RecyclerView recyclerView) {
        this.f1685m.b();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsMoved(RecyclerView recyclerView, int i8, int i9, int i10) {
        A(i8, i9, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsRemoved(RecyclerView recyclerView, int i8, int i9) {
        A(i8, i9, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsUpdated(RecyclerView recyclerView, int i8, int i9, Object obj) {
        A(i8, i9, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.v vVar, RecyclerView.z zVar) {
        F(vVar, zVar, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.z zVar) {
        super.onLayoutCompleted(zVar);
        this.f1683k = -1;
        this.f1684l = Integer.MIN_VALUE;
        this.f1689q = null;
        this.f1692t.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f1689q = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable onSaveInstanceState() {
        int iP;
        int iM;
        int[] iArr;
        if (this.f1689q != null) {
            return new SavedState(this.f1689q);
        }
        SavedState savedState = new SavedState();
        savedState.f1710k = this.f1680h;
        savedState.f1711l = this.f1687o;
        savedState.f1712m = this.f1688p;
        LazySpanLookup lazySpanLookup = this.f1685m;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1697a) == null) {
            savedState.f1707h = 0;
        } else {
            savedState.f1708i = iArr;
            savedState.f1707h = iArr.length;
            savedState.f1709j = lazySpanLookup.f1698b;
        }
        if (getChildCount() > 0) {
            savedState.f1703d = this.f1687o ? u() : t();
            savedState.f1704e = p();
            int i8 = this.f1673a;
            savedState.f1705f = i8;
            savedState.f1706g = new int[i8];
            for (int i9 = 0; i9 < this.f1673a; i9++) {
                if (this.f1687o) {
                    iP = this.f1674b[i9].l(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.f1675c.i();
                        iP -= iM;
                    }
                } else {
                    iP = this.f1674b[i9].p(Integer.MIN_VALUE);
                    if (iP != Integer.MIN_VALUE) {
                        iM = this.f1675c.m();
                        iP -= iM;
                    }
                }
                savedState.f1706g[i9] = iP;
            }
        } else {
            savedState.f1703d = -1;
            savedState.f1704e = -1;
            savedState.f1705f = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onScrollStateChanged(int i8) {
        if (i8 == 0) {
            g();
        }
    }

    public int p() {
        View viewN = this.f1681i ? n(true) : o(true);
        if (viewN == null) {
            return -1;
        }
        return getPosition(viewN);
    }

    public final int q(int i8) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i8) {
                return position;
            }
        }
        return 0;
    }

    public final void r(RecyclerView.v vVar, RecyclerView.z zVar, boolean z7) {
        int i8;
        int iV = v(Integer.MIN_VALUE);
        if (iV != Integer.MIN_VALUE && (i8 = this.f1675c.i() - iV) > 0) {
            int i9 = i8 - (-scrollBy(-i8, vVar, zVar));
            if (!z7 || i9 <= 0) {
                return;
            }
            this.f1675c.r(i9);
        }
    }

    public final void resolveShouldLayoutReverse() {
        if (this.f1677e == 1 || !isLayoutRTL()) {
            this.f1681i = this.f1680h;
        } else {
            this.f1681i = !this.f1680h;
        }
    }

    public final void s(RecyclerView.v vVar, RecyclerView.z zVar, boolean z7) {
        int iM;
        int iY = y(Integer.MAX_VALUE);
        if (iY != Integer.MAX_VALUE && (iM = iY - this.f1675c.m()) > 0) {
            int iScrollBy = iM - scrollBy(iM, vVar, zVar);
            if (!z7 || iScrollBy <= 0) {
                return;
            }
            this.f1675c.r(-iScrollBy);
        }
    }

    public int scrollBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (getChildCount() == 0 || i8 == 0) {
            return 0;
        }
        H(i8, zVar);
        int iL = l(vVar, this.f1679g, zVar);
        if (this.f1679g.f2260b >= iL) {
            i8 = i8 < 0 ? -iL : iL;
        }
        this.f1675c.r(-i8);
        this.f1687o = this.f1681i;
        f fVar = this.f1679g;
        fVar.f2260b = 0;
        J(vVar, fVar);
        return i8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        return scrollBy(i8, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void scrollToPosition(int i8) {
        SavedState savedState = this.f1689q;
        if (savedState != null && savedState.f1703d != i8) {
            savedState.f();
        }
        this.f1683k = i8;
        this.f1684l = Integer.MIN_VALUE;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        return scrollBy(i8, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void setMeasuredDimension(Rect rect, int i8, int i9) {
        int iChooseSize;
        int iChooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.f1677e == 1) {
            iChooseSize2 = RecyclerView.o.chooseSize(i9, rect.height() + paddingTop, getMinimumHeight());
            iChooseSize = RecyclerView.o.chooseSize(i8, (this.f1678f * this.f1673a) + paddingLeft, getMinimumWidth());
        } else {
            iChooseSize = RecyclerView.o.chooseSize(i8, rect.width() + paddingLeft, getMinimumWidth());
            iChooseSize2 = RecyclerView.o.chooseSize(i9, (this.f1678f * this.f1673a) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    public void setOrientation(int i8) {
        if (i8 != 0 && i8 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i8 == this.f1677e) {
            return;
        }
        this.f1677e = i8;
        j jVar = this.f1675c;
        this.f1675c = this.f1676d;
        this.f1676d = jVar;
        requestLayout();
    }

    public void setReverseLayout(boolean z7) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.f1689q;
        if (savedState != null && savedState.f1710k != z7) {
            savedState.f1710k = z7;
        }
        this.f1680h = z7;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.z zVar, int i8) {
        g gVar = new g(recyclerView.getContext());
        gVar.setTargetPosition(i8);
        startSmoothScroll(gVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        return this.f1689q == null;
    }

    public int t() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int u() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public final int v(int i8) {
        int iL = this.f1674b[0].l(i8);
        for (int i9 = 1; i9 < this.f1673a; i9++) {
            int iL2 = this.f1674b[i9].l(i8);
            if (iL2 > iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    public final int w(int i8) {
        int iP = this.f1674b[0].p(i8);
        for (int i9 = 1; i9 < this.f1673a; i9++) {
            int iP2 = this.f1674b[i9].p(i8);
            if (iP2 > iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final int x(int i8) {
        int iL = this.f1674b[0].l(i8);
        for (int i9 = 1; i9 < this.f1673a; i9++) {
            int iL2 = this.f1674b[i9].l(i8);
            if (iL2 < iL) {
                iL = iL2;
            }
        }
        return iL;
    }

    public final int y(int i8) {
        int iP = this.f1674b[0].p(i8);
        for (int i9 = 1; i9 < this.f1673a; i9++) {
            int iP2 = this.f1674b[i9].p(i8);
            if (iP2 < iP) {
                iP = iP2;
            }
        }
        return iP;
    }

    public final d z(f fVar) {
        int i8;
        int i9;
        int i10 = -1;
        if (G(fVar.f2263e)) {
            i8 = this.f1673a - 1;
            i9 = -1;
        } else {
            i8 = 0;
            i10 = this.f1673a;
            i9 = 1;
        }
        d dVar = null;
        if (fVar.f2263e == 1) {
            int i11 = Integer.MAX_VALUE;
            int iM = this.f1675c.m();
            while (i8 != i10) {
                d dVar2 = this.f1674b[i8];
                int iL = dVar2.l(iM);
                if (iL < i11) {
                    dVar = dVar2;
                    i11 = iL;
                }
                i8 += i9;
            }
            return dVar;
        }
        int i12 = Integer.MIN_VALUE;
        int i13 = this.f1675c.i();
        while (i8 != i10) {
            d dVar3 = this.f1674b[i8];
            int iP = dVar3.p(i13);
            if (iP > i12) {
                dVar = dVar3;
                i12 = iP;
            }
            i8 += i9;
        }
        return dVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    public static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f1697a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<FullSpanItem> f1698b;

        public void a(FullSpanItem fullSpanItem) {
            if (this.f1698b == null) {
                this.f1698b = new ArrayList();
            }
            int size = this.f1698b.size();
            for (int i8 = 0; i8 < size; i8++) {
                FullSpanItem fullSpanItem2 = this.f1698b.get(i8);
                if (fullSpanItem2.f1699d == fullSpanItem.f1699d) {
                    this.f1698b.remove(i8);
                }
                if (fullSpanItem2.f1699d >= fullSpanItem.f1699d) {
                    this.f1698b.add(i8, fullSpanItem);
                    return;
                }
            }
            this.f1698b.add(fullSpanItem);
        }

        public void b() {
            int[] iArr = this.f1697a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1698b = null;
        }

        public void c(int i8) {
            int[] iArr = this.f1697a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i8, 10) + 1];
                this.f1697a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i8 >= iArr.length) {
                int[] iArr3 = new int[o(i8)];
                this.f1697a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1697a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int d(int i8) {
            List<FullSpanItem> list = this.f1698b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1698b.get(size).f1699d >= i8) {
                        this.f1698b.remove(size);
                    }
                }
            }
            return h(i8);
        }

        public FullSpanItem e(int i8, int i9, int i10, boolean z7) {
            List<FullSpanItem> list = this.f1698b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i11 = 0; i11 < size; i11++) {
                FullSpanItem fullSpanItem = this.f1698b.get(i11);
                int i12 = fullSpanItem.f1699d;
                if (i12 >= i9) {
                    return null;
                }
                if (i12 >= i8 && (i10 == 0 || fullSpanItem.f1700e == i10 || (z7 && fullSpanItem.f1702g))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i8) {
            List<FullSpanItem> list = this.f1698b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1698b.get(size);
                if (fullSpanItem.f1699d == i8) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int g(int i8) {
            int[] iArr = this.f1697a;
            if (iArr == null || i8 >= iArr.length) {
                return -1;
            }
            return iArr[i8];
        }

        public int h(int i8) {
            int[] iArr = this.f1697a;
            if (iArr == null || i8 >= iArr.length) {
                return -1;
            }
            int i9 = i(i8);
            if (i9 == -1) {
                int[] iArr2 = this.f1697a;
                Arrays.fill(iArr2, i8, iArr2.length, -1);
                return this.f1697a.length;
            }
            int i10 = i9 + 1;
            Arrays.fill(this.f1697a, i8, i10, -1);
            return i10;
        }

        public final int i(int i8) {
            if (this.f1698b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemF = f(i8);
            if (fullSpanItemF != null) {
                this.f1698b.remove(fullSpanItemF);
            }
            int size = this.f1698b.size();
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    i9 = -1;
                    break;
                }
                if (this.f1698b.get(i9).f1699d >= i8) {
                    break;
                }
                i9++;
            }
            if (i9 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f1698b.get(i9);
            this.f1698b.remove(i9);
            return fullSpanItem.f1699d;
        }

        public void j(int i8, int i9) {
            int[] iArr = this.f1697a;
            if (iArr == null || i8 >= iArr.length) {
                return;
            }
            int i10 = i8 + i9;
            c(i10);
            int[] iArr2 = this.f1697a;
            System.arraycopy(iArr2, i8, iArr2, i10, (iArr2.length - i8) - i9);
            Arrays.fill(this.f1697a, i8, i10, -1);
            l(i8, i9);
        }

        public void k(int i8, int i9) {
            int[] iArr = this.f1697a;
            if (iArr == null || i8 >= iArr.length) {
                return;
            }
            int i10 = i8 + i9;
            c(i10);
            int[] iArr2 = this.f1697a;
            System.arraycopy(iArr2, i10, iArr2, i8, (iArr2.length - i8) - i9);
            int[] iArr3 = this.f1697a;
            Arrays.fill(iArr3, iArr3.length - i9, iArr3.length, -1);
            m(i8, i9);
        }

        public final void l(int i8, int i9) {
            List<FullSpanItem> list = this.f1698b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1698b.get(size);
                int i10 = fullSpanItem.f1699d;
                if (i10 >= i8) {
                    fullSpanItem.f1699d = i10 + i9;
                }
            }
        }

        public final void m(int i8, int i9) {
            List<FullSpanItem> list = this.f1698b;
            if (list == null) {
                return;
            }
            int i10 = i8 + i9;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f1698b.get(size);
                int i11 = fullSpanItem.f1699d;
                if (i11 >= i8) {
                    if (i11 < i10) {
                        this.f1698b.remove(size);
                    } else {
                        fullSpanItem.f1699d = i11 - i9;
                    }
                }
            }
        }

        public void n(int i8, d dVar) {
            c(i8);
            this.f1697a[i8] = dVar.f1727e;
        }

        public int o(int i8) {
            int length = this.f1697a.length;
            while (length <= i8) {
                length *= 2;
            }
            return length;
        }

        @SuppressLint({"BanParcelableUsage"})
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1699d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public int f1700e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public int[] f1701f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public boolean f1702g;

            public static class a implements Parcelable.Creator<FullSpanItem> {
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public FullSpanItem[] newArray(int i8) {
                    return new FullSpanItem[i8];
                }
            }

            public FullSpanItem(Parcel parcel) {
                this.f1699d = parcel.readInt();
                this.f1700e = parcel.readInt();
                this.f1702g = parcel.readInt() == 1;
                int i8 = parcel.readInt();
                if (i8 > 0) {
                    int[] iArr = new int[i8];
                    this.f1701f = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public int f(int i8) {
                int[] iArr = this.f1701f;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i8];
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1699d + ", mGapDir=" + this.f1700e + ", mHasUnwantedGapAfter=" + this.f1702g + ", mGapPerSpan=" + Arrays.toString(this.f1701f) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i8) {
                parcel.writeInt(this.f1699d);
                parcel.writeInt(this.f1700e);
                parcel.writeInt(this.f1702g ? 1 : 0);
                int[] iArr = this.f1701f;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1701f);
                }
            }

            public FullSpanItem() {
            }
        }
    }
}
