package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h;
import androidx.recyclerview.widget.k;
import com.google.android.flexbox.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FlexboxLayoutManager extends RecyclerView.o implements com.google.android.flexbox.a, RecyclerView.z.b {
    private static final Rect s = new Rect();
    private RecyclerView.v C;
    private RecyclerView.a0 D;
    private d E;
    private k G;
    private k H;
    private e I;
    private boolean N;
    private final Context P;
    private View Q;
    private int t;
    private int u;
    private int v;
    private int w;
    private boolean y;
    private boolean z;
    private int x = -1;
    private List<com.google.android.flexbox.c> A = new ArrayList();
    private final com.google.android.flexbox.d B = new com.google.android.flexbox.d(this);
    private b F = new b();
    private int J = -1;
    private int K = Integer.MIN_VALUE;
    private int L = Integer.MIN_VALUE;
    private int M = Integer.MIN_VALUE;
    private SparseArray<View> O = new SparseArray<>();
    private int R = -1;
    private d.a S = new d.a();

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2924a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2925b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2926c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2927d;
        private boolean e;
        private boolean f;
        private boolean g;

        private b() {
            this.f2927d = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void q() {
            /*
                r2 = this;
                com.google.android.flexbox.FlexboxLayoutManager r0 = com.google.android.flexbox.FlexboxLayoutManager.this
                boolean r0 = r0.v()
                if (r0 != 0) goto L27
                com.google.android.flexbox.FlexboxLayoutManager r0 = com.google.android.flexbox.FlexboxLayoutManager.this
                boolean r0 = com.google.android.flexbox.FlexboxLayoutManager.k2(r0)
                if (r0 == 0) goto L27
                boolean r0 = r2.e
                if (r0 == 0) goto L15
                goto L2b
            L15:
                com.google.android.flexbox.FlexboxLayoutManager r0 = com.google.android.flexbox.FlexboxLayoutManager.this
                int r0 = r0.G0()
                com.google.android.flexbox.FlexboxLayoutManager r1 = com.google.android.flexbox.FlexboxLayoutManager.this
                androidx.recyclerview.widget.k r1 = com.google.android.flexbox.FlexboxLayoutManager.l2(r1)
                int r1 = r1.m()
                int r0 = r0 - r1
                goto L40
            L27:
                boolean r0 = r2.e
                if (r0 == 0) goto L36
            L2b:
                com.google.android.flexbox.FlexboxLayoutManager r0 = com.google.android.flexbox.FlexboxLayoutManager.this
                androidx.recyclerview.widget.k r0 = com.google.android.flexbox.FlexboxLayoutManager.l2(r0)
                int r0 = r0.i()
                goto L40
            L36:
                com.google.android.flexbox.FlexboxLayoutManager r0 = com.google.android.flexbox.FlexboxLayoutManager.this
                androidx.recyclerview.widget.k r0 = com.google.android.flexbox.FlexboxLayoutManager.l2(r0)
                int r0 = r0.m()
            L40:
                r2.f2926c = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.b.q():void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void r(View view) {
            int iG;
            int iD;
            if (FlexboxLayoutManager.this.v() || !FlexboxLayoutManager.this.y) {
                if (this.e) {
                    iD = FlexboxLayoutManager.this.G.d(view);
                    iG = iD + FlexboxLayoutManager.this.G.o();
                } else {
                    iG = FlexboxLayoutManager.this.G.g(view);
                }
            } else if (this.e) {
                iD = FlexboxLayoutManager.this.G.g(view);
                iG = iD + FlexboxLayoutManager.this.G.o();
            } else {
                iG = FlexboxLayoutManager.this.G.d(view);
            }
            this.f2926c = iG;
            this.f2924a = FlexboxLayoutManager.this.z0(view);
            this.g = false;
            int[] iArr = FlexboxLayoutManager.this.B.f2940c;
            int i = this.f2924a;
            if (i == -1) {
                i = 0;
            }
            int i2 = iArr[i];
            this.f2925b = i2 != -1 ? i2 : 0;
            if (FlexboxLayoutManager.this.A.size() > this.f2925b) {
                this.f2924a = ((com.google.android.flexbox.c) FlexboxLayoutManager.this.A.get(this.f2925b)).o;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s() {
            this.f2924a = -1;
            this.f2925b = -1;
            this.f2926c = Integer.MIN_VALUE;
            boolean z = false;
            this.f = false;
            this.g = false;
            if (!FlexboxLayoutManager.this.v() ? !(FlexboxLayoutManager.this.u != 0 ? FlexboxLayoutManager.this.u != 2 : FlexboxLayoutManager.this.t != 3) : !(FlexboxLayoutManager.this.u != 0 ? FlexboxLayoutManager.this.u != 2 : FlexboxLayoutManager.this.t != 1)) {
                z = true;
            }
            this.e = z;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f2924a + ", mFlexLinePosition=" + this.f2925b + ", mCoordinate=" + this.f2926c + ", mPerpendicularCoordinate=" + this.f2927d + ", mLayoutFromEnd=" + this.e + ", mValid=" + this.f + ", mAssignedFromSavedState=" + this.g + '}';
        }
    }

    public static class c extends RecyclerView.p implements com.google.android.flexbox.b {
        public static final Parcelable.Creator<c> CREATOR = new a();
        private float f;
        private float g;
        private int h;
        private float i;
        private int j;
        private int k;
        private int l;
        private int m;
        private boolean n;

        static class a implements Parcelable.Creator<c> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public c[] newArray(int i) {
                return new c[i];
            }
        }

        public c(int i, int i2) {
            super(i, i2);
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = -1;
            this.i = -1.0f;
            this.l = 16777215;
            this.m = 16777215;
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = -1;
            this.i = -1.0f;
            this.l = 16777215;
            this.m = 16777215;
        }

        protected c(Parcel parcel) {
            super(-2, -2);
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = -1;
            this.i = -1.0f;
            this.l = 16777215;
            this.m = 16777215;
            this.f = parcel.readFloat();
            this.g = parcel.readFloat();
            this.h = parcel.readInt();
            this.i = parcel.readFloat();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
            this.m = parcel.readInt();
            this.n = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }

        @Override // com.google.android.flexbox.b
        public int a() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.b
        public int c() {
            return this.k;
        }

        @Override // com.google.android.flexbox.b
        public int d() {
            return this.j;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.b
        public boolean e() {
            return this.n;
        }

        @Override // com.google.android.flexbox.b
        public float f() {
            return this.f;
        }

        @Override // com.google.android.flexbox.b
        public int g() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.b
        public int h() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.b
        public int k() {
            return this.m;
        }

        @Override // com.google.android.flexbox.b
        public int l() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.b
        public float m() {
            return this.i;
        }

        @Override // com.google.android.flexbox.b
        public int n() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.b
        public int o() {
            return this.h;
        }

        @Override // com.google.android.flexbox.b
        public float q() {
            return this.g;
        }

        @Override // com.google.android.flexbox.b
        public int r() {
            return this.l;
        }

        @Override // com.google.android.flexbox.b
        public int t() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.f);
            parcel.writeFloat(this.g);
            parcel.writeInt(this.h);
            parcel.writeFloat(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
            parcel.writeInt(this.m);
            parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }
    }

    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2928a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2929b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2930c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2931d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private boolean j;

        private d() {
            this.h = 1;
            this.i = 1;
        }

        static /* synthetic */ int i(d dVar) {
            int i = dVar.f2930c;
            dVar.f2930c = i + 1;
            return i;
        }

        static /* synthetic */ int j(d dVar) {
            int i = dVar.f2930c;
            dVar.f2930c = i - 1;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean w(RecyclerView.a0 a0Var, List<com.google.android.flexbox.c> list) {
            int i;
            int i2 = this.f2931d;
            return i2 >= 0 && i2 < a0Var.b() && (i = this.f2930c) >= 0 && i < list.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.f2928a + ", mFlexLinePosition=" + this.f2930c + ", mPosition=" + this.f2931d + ", mOffset=" + this.e + ", mScrollingOffset=" + this.f + ", mLastScrollDelta=" + this.g + ", mItemDirection=" + this.h + ", mLayoutDirection=" + this.i + '}';
        }
    }

    private static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2932b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2933c;

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

        e() {
        }

        private e(Parcel parcel) {
            this.f2932b = parcel.readInt();
            this.f2933c = parcel.readInt();
        }

        private e(e eVar) {
            this.f2932b = eVar.f2932b;
            this.f2933c = eVar.f2933c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean A(int i) {
            int i2 = this.f2932b;
            return i2 >= 0 && i2 < i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void B() {
            this.f2932b = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.f2932b + ", mAnchorOffset=" + this.f2933c + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f2932b);
            parcel.writeInt(this.f2933c);
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        int i3;
        RecyclerView.o.d dVarA0 = RecyclerView.o.A0(context, attributeSet, i, i2);
        int i4 = dVarA0.f1476a;
        if (i4 != 0) {
            if (i4 == 1) {
                i3 = dVarA0.f1478c ? 3 : 2;
                a3(i3);
            }
        } else if (dVarA0.f1478c) {
            a3(1);
        } else {
            i3 = 0;
            a3(i3);
        }
        b3(1);
        Z2(4);
        U1(true);
        this.P = context;
    }

    private View A2(int i) {
        View viewE2 = E2(f0() - 1, -1, i);
        if (viewE2 == null) {
            return null;
        }
        return B2(viewE2, this.A.get(this.B.f2940c[z0(viewE2)]));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View B2(android.view.View r6, com.google.android.flexbox.c r7) {
        /*
            r5 = this;
            boolean r0 = r5.v()
            int r1 = r5.f0()
            int r1 = r1 + (-2)
            int r2 = r5.f0()
            int r7 = r7.h
            int r2 = r2 - r7
            int r2 = r2 + (-1)
        L13:
            if (r1 <= r2) goto L4b
            android.view.View r7 = r5.e0(r1)
            if (r7 == 0) goto L48
            int r3 = r7.getVisibility()
            r4 = 8
            if (r3 != r4) goto L24
            goto L48
        L24:
            boolean r3 = r5.y
            if (r3 == 0) goto L39
            if (r0 != 0) goto L39
            androidx.recyclerview.widget.k r3 = r5.G
            int r3 = r3.g(r6)
            androidx.recyclerview.widget.k r4 = r5.G
            int r4 = r4.g(r7)
            if (r3 <= r4) goto L48
            goto L47
        L39:
            androidx.recyclerview.widget.k r3 = r5.G
            int r3 = r3.d(r6)
            androidx.recyclerview.widget.k r4 = r5.G
            int r4 = r4.d(r7)
            if (r3 >= r4) goto L48
        L47:
            r6 = r7
        L48:
            int r1 = r1 + (-1)
            goto L13
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.B2(android.view.View, com.google.android.flexbox.c):android.view.View");
    }

    private View D2(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View viewE0 = e0(i);
            if (P2(viewE0, z)) {
                return viewE0;
            }
            i += i3;
        }
        return null;
    }

    private View E2(int i, int i2, int i3) {
        v2();
        u2();
        int iM = this.G.m();
        int i4 = this.G.i();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewE0 = e0(i);
            int iZ0 = z0(viewE0);
            if (iZ0 >= 0 && iZ0 < i3) {
                if (((RecyclerView.p) viewE0.getLayoutParams()).w()) {
                    if (view2 == null) {
                        view2 = viewE0;
                    }
                } else {
                    if (this.G.g(viewE0) >= iM && this.G.d(viewE0) <= i4) {
                        return viewE0;
                    }
                    if (view == null) {
                        view = viewE0;
                    }
                }
            }
            i += i5;
        }
        return view != null ? view : view2;
    }

    private int F2(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int iN2;
        int i2;
        if (!v() && this.y) {
            int iM = i - this.G.m();
            if (iM <= 0) {
                return 0;
            }
            iN2 = N2(iM, vVar, a0Var);
        } else {
            int i3 = this.G.i() - i;
            if (i3 <= 0) {
                return 0;
            }
            iN2 = -N2(-i3, vVar, a0Var);
        }
        int i4 = i + iN2;
        if (!z || (i2 = this.G.i() - i4) <= 0) {
            return iN2;
        }
        this.G.r(i2);
        return i2 + iN2;
    }

    private int G2(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int iN2;
        int iM;
        if (v() || !this.y) {
            int iM2 = i - this.G.m();
            if (iM2 <= 0) {
                return 0;
            }
            iN2 = -N2(iM2, vVar, a0Var);
        } else {
            int i2 = this.G.i() - i;
            if (i2 <= 0) {
                return 0;
            }
            iN2 = N2(-i2, vVar, a0Var);
        }
        int i3 = i + iN2;
        if (!z || (iM = i3 - this.G.m()) <= 0) {
            return iN2;
        }
        this.G.r(-iM);
        return iN2 - iM;
    }

    private int H2(View view) {
        return k0(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
    }

    private View I2() {
        return e0(0);
    }

    private int J2(View view) {
        return m0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
    }

    private int K2(View view) {
        return p0(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
    }

    private int L2(View view) {
        return q0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
    }

    private int N2(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (f0() == 0 || i == 0) {
            return 0;
        }
        v2();
        int i2 = 1;
        this.E.j = true;
        boolean z = !v() && this.y;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int iAbs = Math.abs(i);
        h3(i2, iAbs);
        int iW2 = this.E.f + w2(vVar, a0Var, this.E);
        if (iW2 < 0) {
            return 0;
        }
        if (z) {
            if (iAbs > iW2) {
                i = (-i2) * iW2;
            }
        } else if (iAbs > iW2) {
            i = i2 * iW2;
        }
        this.G.r(-i);
        this.E.g = i;
        return i;
    }

    private int O2(int i) {
        int iMin;
        if (f0() == 0 || i == 0) {
            return 0;
        }
        v2();
        boolean zV = v();
        View view = this.Q;
        int width = zV ? view.getWidth() : view.getHeight();
        int iG0 = zV ? G0() : s0();
        if (v0() == 1) {
            int iAbs = Math.abs(i);
            if (i < 0) {
                iMin = Math.min((iG0 + this.F.f2927d) - width, iAbs);
                return -iMin;
            }
            if (this.F.f2927d + i <= 0) {
                return i;
            }
        } else {
            if (i > 0) {
                return Math.min((iG0 - this.F.f2927d) - width, i);
            }
            if (this.F.f2927d + i >= 0) {
                return i;
            }
        }
        iMin = this.F.f2927d;
        return -iMin;
    }

    private static boolean P0(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    private boolean P2(View view, boolean z) {
        int iL = l();
        int iJ = j();
        int iG0 = G0() - b();
        int iS0 = s0() - e();
        int iJ2 = J2(view);
        int iL2 = L2(view);
        int iK2 = K2(view);
        int iH2 = H2(view);
        return z ? (iL <= iJ2 && iG0 >= iK2) && (iJ <= iL2 && iS0 >= iH2) : (iJ2 >= iG0 || iK2 >= iL) && (iL2 >= iS0 || iH2 >= iJ);
    }

    private int Q2(com.google.android.flexbox.c cVar, d dVar) {
        return v() ? R2(cVar, dVar) : S2(cVar, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int R2(com.google.android.flexbox.c r22, com.google.android.flexbox.FlexboxLayoutManager.d r23) {
        /*
            Method dump skipped, instruction units count: 425
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.R2(com.google.android.flexbox.c, com.google.android.flexbox.FlexboxLayoutManager$d):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int S2(com.google.android.flexbox.c r26, com.google.android.flexbox.FlexboxLayoutManager.d r27) {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.S2(com.google.android.flexbox.c, com.google.android.flexbox.FlexboxLayoutManager$d):int");
    }

    private void T2(RecyclerView.v vVar, d dVar) {
        if (dVar.j) {
            if (dVar.i == -1) {
                V2(vVar, dVar);
            } else {
                W2(vVar, dVar);
            }
        }
    }

    private void U2(RecyclerView.v vVar, int i, int i2) {
        while (i2 >= i) {
            I1(i2, vVar);
            i2--;
        }
    }

    private void V2(RecyclerView.v vVar, d dVar) {
        if (dVar.f < 0) {
            return;
        }
        this.G.h();
        int unused = dVar.f;
        int iF0 = f0();
        if (iF0 == 0) {
            return;
        }
        int i = iF0 - 1;
        int i2 = this.B.f2940c[z0(e0(i))];
        if (i2 == -1) {
            return;
        }
        com.google.android.flexbox.c cVar = this.A.get(i2);
        int i3 = i;
        while (true) {
            if (i3 < 0) {
                break;
            }
            View viewE0 = e0(i3);
            if (!o2(viewE0, dVar.f)) {
                break;
            }
            if (cVar.o == z0(viewE0)) {
                if (i2 <= 0) {
                    iF0 = i3;
                    break;
                } else {
                    i2 += dVar.i;
                    cVar = this.A.get(i2);
                    iF0 = i3;
                }
            }
            i3--;
        }
        U2(vVar, iF0, i);
    }

    private void W2(RecyclerView.v vVar, d dVar) {
        int iF0;
        if (dVar.f >= 0 && (iF0 = f0()) != 0) {
            int i = this.B.f2940c[z0(e0(0))];
            int i2 = -1;
            if (i == -1) {
                return;
            }
            com.google.android.flexbox.c cVar = this.A.get(i);
            int i3 = 0;
            while (true) {
                if (i3 >= iF0) {
                    break;
                }
                View viewE0 = e0(i3);
                if (!p2(viewE0, dVar.f)) {
                    break;
                }
                if (cVar.p == z0(viewE0)) {
                    if (i >= this.A.size() - 1) {
                        i2 = i3;
                        break;
                    } else {
                        i += dVar.i;
                        cVar = this.A.get(i);
                        i2 = i3;
                    }
                }
                i3++;
            }
            U2(vVar, 0, i2);
        }
    }

    private void X2() {
        int iT0 = v() ? t0() : H0();
        this.E.f2929b = iT0 == 0 || iT0 == Integer.MIN_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void Y2() {
        /*
            r6 = this;
            int r0 = r6.v0()
            int r1 = r6.t
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L43
            if (r1 == r4) goto L36
            if (r1 == r2) goto L27
            r5 = 3
            if (r1 == r5) goto L17
            r6.y = r3
        L14:
            r6.z = r3
            goto L4f
        L17:
            if (r0 != r4) goto L1a
            r3 = r4
        L1a:
            r6.y = r3
            int r0 = r6.u
            if (r0 != r2) goto L24
            r0 = r3 ^ 1
            r6.y = r0
        L24:
            r6.z = r4
            goto L4f
        L27:
            if (r0 != r4) goto L2b
            r0 = r4
            goto L2c
        L2b:
            r0 = r3
        L2c:
            r6.y = r0
            int r1 = r6.u
            if (r1 != r2) goto L14
            r0 = r0 ^ r4
            r6.y = r0
            goto L14
        L36:
            if (r0 == r4) goto L3a
            r0 = r4
            goto L3b
        L3a:
            r0 = r3
        L3b:
            r6.y = r0
            int r0 = r6.u
            if (r0 != r2) goto L14
        L41:
            r3 = r4
            goto L14
        L43:
            if (r0 != r4) goto L47
            r0 = r4
            goto L48
        L47:
            r0 = r3
        L48:
            r6.y = r0
            int r0 = r6.u
            if (r0 != r2) goto L14
            goto L41
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.Y2():void");
    }

    private boolean b2(View view, int i, int i2, RecyclerView.p pVar) {
        return (!view.isLayoutRequested() && O0() && P0(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && P0(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
    }

    private boolean c3(RecyclerView.a0 a0Var, b bVar) {
        if (f0() == 0) {
            return false;
        }
        View viewA2 = bVar.e ? A2(a0Var.b()) : x2(a0Var.b());
        if (viewA2 == null) {
            return false;
        }
        bVar.r(viewA2);
        if (!a0Var.e() && h2()) {
            if (this.G.g(viewA2) >= this.G.i() || this.G.d(viewA2) < this.G.m()) {
                bVar.f2926c = bVar.e ? this.G.i() : this.G.m();
            }
        }
        return true;
    }

    private boolean d3(RecyclerView.a0 a0Var, b bVar, e eVar) {
        int i;
        if (!a0Var.e() && (i = this.J) != -1) {
            if (i >= 0 && i < a0Var.b()) {
                bVar.f2924a = this.J;
                bVar.f2925b = this.B.f2940c[bVar.f2924a];
                e eVar2 = this.I;
                if (eVar2 != null && eVar2.A(a0Var.b())) {
                    bVar.f2926c = this.G.m() + eVar.f2933c;
                    bVar.g = true;
                    bVar.f2925b = -1;
                    return true;
                }
                if (this.K != Integer.MIN_VALUE) {
                    bVar.f2926c = (v() || !this.y) ? this.G.m() + this.K : this.K - this.G.j();
                    return true;
                }
                View viewY = Y(this.J);
                if (viewY == null) {
                    if (f0() > 0) {
                        bVar.e = this.J < z0(e0(0));
                    }
                    bVar.q();
                } else {
                    if (this.G.e(viewY) > this.G.n()) {
                        bVar.q();
                        return true;
                    }
                    if (this.G.g(viewY) - this.G.m() < 0) {
                        bVar.f2926c = this.G.m();
                        bVar.e = false;
                        return true;
                    }
                    if (this.G.i() - this.G.d(viewY) < 0) {
                        bVar.f2926c = this.G.i();
                        bVar.e = true;
                        return true;
                    }
                    bVar.f2926c = bVar.e ? this.G.d(viewY) + this.G.o() : this.G.g(viewY);
                }
                return true;
            }
            this.J = -1;
            this.K = Integer.MIN_VALUE;
        }
        return false;
    }

    private void e3(RecyclerView.a0 a0Var, b bVar) {
        if (d3(a0Var, bVar, this.I) || c3(a0Var, bVar)) {
            return;
        }
        bVar.q();
        bVar.f2924a = 0;
        bVar.f2925b = 0;
    }

    private void f3(int i) {
        int iZ2 = z2();
        int iC2 = C2();
        if (i >= iC2) {
            return;
        }
        int iF0 = f0();
        this.B.m(iF0);
        this.B.n(iF0);
        this.B.l(iF0);
        if (i >= this.B.f2940c.length) {
            return;
        }
        this.R = i;
        View viewI2 = I2();
        if (viewI2 == null) {
            return;
        }
        if (iZ2 > i || i > iC2) {
            this.J = z0(viewI2);
            this.K = (v() || !this.y) ? this.G.g(viewI2) - this.G.m() : this.G.d(viewI2) + this.G.j();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0064 A[PHI: r5
  0x0064: PHI (r5v3 boolean) = (r5v2 boolean), (r5v13 boolean) binds: [B:18:0x0055, B:10:0x0038] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void g3(int r12) {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.g3(int):void");
    }

    private void h3(int i, int i2) {
        this.E.i = i;
        boolean zV = v();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(G0(), H0());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(s0(), t0());
        boolean z = !zV && this.y;
        if (i == 1) {
            View viewE0 = e0(f0() - 1);
            this.E.e = this.G.d(viewE0);
            int iZ0 = z0(viewE0);
            View viewB2 = B2(viewE0, this.A.get(this.B.f2940c[iZ0]));
            this.E.h = 1;
            d dVar = this.E;
            dVar.f2931d = iZ0 + dVar.h;
            if (this.B.f2940c.length <= this.E.f2931d) {
                this.E.f2930c = -1;
            } else {
                d dVar2 = this.E;
                dVar2.f2930c = this.B.f2940c[dVar2.f2931d];
            }
            if (z) {
                this.E.e = this.G.g(viewB2);
                this.E.f = (-this.G.g(viewB2)) + this.G.m();
                d dVar3 = this.E;
                dVar3.f = dVar3.f >= 0 ? this.E.f : 0;
            } else {
                this.E.e = this.G.d(viewB2);
                this.E.f = this.G.d(viewB2) - this.G.i();
            }
            if ((this.E.f2930c == -1 || this.E.f2930c > this.A.size() - 1) && this.E.f2931d <= h()) {
                int i3 = i2 - this.E.f;
                this.S.a();
                if (i3 > 0) {
                    com.google.android.flexbox.d dVar4 = this.B;
                    d.a aVar = this.S;
                    int i4 = this.E.f2931d;
                    List<com.google.android.flexbox.c> list = this.A;
                    if (zV) {
                        dVar4.c(aVar, iMakeMeasureSpec, iMakeMeasureSpec2, i3, i4, list);
                    } else {
                        dVar4.e(aVar, iMakeMeasureSpec, iMakeMeasureSpec2, i3, i4, list);
                    }
                    this.B.j(iMakeMeasureSpec, iMakeMeasureSpec2, this.E.f2931d);
                    this.B.O(this.E.f2931d);
                }
            }
        } else {
            View viewE02 = e0(0);
            this.E.e = this.G.g(viewE02);
            int iZ02 = z0(viewE02);
            View viewY2 = y2(viewE02, this.A.get(this.B.f2940c[iZ02]));
            this.E.h = 1;
            int i5 = this.B.f2940c[iZ02];
            if (i5 == -1) {
                i5 = 0;
            }
            if (i5 > 0) {
                this.E.f2931d = iZ02 - this.A.get(i5 - 1).b();
            } else {
                this.E.f2931d = -1;
            }
            this.E.f2930c = i5 > 0 ? i5 - 1 : 0;
            d dVar5 = this.E;
            k kVar = this.G;
            if (z) {
                dVar5.e = kVar.d(viewY2);
                this.E.f = this.G.d(viewY2) - this.G.i();
                d dVar6 = this.E;
                dVar6.f = dVar6.f >= 0 ? this.E.f : 0;
            } else {
                dVar5.e = kVar.g(viewY2);
                this.E.f = (-this.G.g(viewY2)) + this.G.m();
            }
        }
        d dVar7 = this.E;
        dVar7.f2928a = i2 - dVar7.f;
    }

    private void i3(b bVar, boolean z, boolean z2) {
        d dVar;
        int i;
        int iB;
        if (z2) {
            X2();
        } else {
            this.E.f2929b = false;
        }
        if (v() || !this.y) {
            dVar = this.E;
            i = this.G.i();
            iB = bVar.f2926c;
        } else {
            dVar = this.E;
            i = bVar.f2926c;
            iB = b();
        }
        dVar.f2928a = i - iB;
        this.E.f2931d = bVar.f2924a;
        this.E.h = 1;
        this.E.i = 1;
        this.E.e = bVar.f2926c;
        this.E.f = Integer.MIN_VALUE;
        this.E.f2930c = bVar.f2925b;
        if (!z || this.A.size() <= 1 || bVar.f2925b < 0 || bVar.f2925b >= this.A.size() - 1) {
            return;
        }
        com.google.android.flexbox.c cVar = this.A.get(bVar.f2925b);
        d.i(this.E);
        this.E.f2931d += cVar.b();
    }

    private void j3(b bVar, boolean z, boolean z2) {
        d dVar;
        int width;
        if (z2) {
            X2();
        } else {
            this.E.f2929b = false;
        }
        if (v() || !this.y) {
            dVar = this.E;
            width = bVar.f2926c;
        } else {
            dVar = this.E;
            width = this.Q.getWidth() - bVar.f2926c;
        }
        dVar.f2928a = width - this.G.m();
        this.E.f2931d = bVar.f2924a;
        this.E.h = 1;
        this.E.i = -1;
        this.E.e = bVar.f2926c;
        this.E.f = Integer.MIN_VALUE;
        this.E.f2930c = bVar.f2925b;
        if (!z || bVar.f2925b <= 0 || this.A.size() <= bVar.f2925b) {
            return;
        }
        com.google.android.flexbox.c cVar = this.A.get(bVar.f2925b);
        d.j(this.E);
        this.E.f2931d -= cVar.b();
    }

    private boolean o2(View view, int i) {
        return (v() || !this.y) ? this.G.g(view) >= this.G.h() - i : this.G.d(view) <= i;
    }

    private boolean p2(View view, int i) {
        return (v() || !this.y) ? this.G.d(view) <= i : this.G.h() - this.G.g(view) <= i;
    }

    private void q2() {
        this.A.clear();
        this.F.s();
        this.F.f2927d = 0;
    }

    private int r2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        int iB = a0Var.b();
        v2();
        View viewX2 = x2(iB);
        View viewA2 = A2(iB);
        if (a0Var.b() == 0 || viewX2 == null || viewA2 == null) {
            return 0;
        }
        return Math.min(this.G.n(), this.G.d(viewA2) - this.G.g(viewX2));
    }

    private int s2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        int iB = a0Var.b();
        View viewX2 = x2(iB);
        View viewA2 = A2(iB);
        if (a0Var.b() != 0 && viewX2 != null && viewA2 != null) {
            int iZ0 = z0(viewX2);
            int iZ02 = z0(viewA2);
            int iAbs = Math.abs(this.G.d(viewA2) - this.G.g(viewX2));
            int i = this.B.f2940c[iZ0];
            if (i != 0 && i != -1) {
                return Math.round((i * (iAbs / ((r4[iZ02] - i) + 1))) + (this.G.m() - this.G.g(viewX2)));
            }
        }
        return 0;
    }

    private int t2(RecyclerView.a0 a0Var) {
        if (f0() == 0) {
            return 0;
        }
        int iB = a0Var.b();
        View viewX2 = x2(iB);
        View viewA2 = A2(iB);
        if (a0Var.b() == 0 || viewX2 == null || viewA2 == null) {
            return 0;
        }
        int iZ2 = z2();
        return (int) ((Math.abs(this.G.d(viewA2) - this.G.g(viewX2)) / ((C2() - iZ2) + 1)) * a0Var.b());
    }

    private void u2() {
        if (this.E == null) {
            this.E = new d();
        }
    }

    private void v2() {
        k kVarC;
        if (this.G != null) {
            return;
        }
        if (!v() ? this.u == 0 : this.u != 0) {
            this.G = k.a(this);
            kVarC = k.c(this);
        } else {
            this.G = k.c(this);
            kVarC = k.a(this);
        }
        this.H = kVarC;
    }

    private int w2(RecyclerView.v vVar, RecyclerView.a0 a0Var, d dVar) {
        if (dVar.f != Integer.MIN_VALUE) {
            if (dVar.f2928a < 0) {
                dVar.f += dVar.f2928a;
            }
            T2(vVar, dVar);
        }
        int i = dVar.f2928a;
        int iA = dVar.f2928a;
        int iQ2 = 0;
        boolean zV = v();
        while (true) {
            if ((iA <= 0 && !this.E.f2929b) || !dVar.w(a0Var, this.A)) {
                break;
            }
            com.google.android.flexbox.c cVar = this.A.get(dVar.f2930c);
            dVar.f2931d = cVar.o;
            iQ2 += Q2(cVar, dVar);
            dVar.e = (zV || !this.y) ? dVar.e + (cVar.a() * dVar.i) : dVar.e - (cVar.a() * dVar.i);
            iA -= cVar.a();
        }
        dVar.f2928a -= iQ2;
        if (dVar.f != Integer.MIN_VALUE) {
            dVar.f += iQ2;
            if (dVar.f2928a < 0) {
                dVar.f += dVar.f2928a;
            }
            T2(vVar, dVar);
        }
        return i - dVar.f2928a;
    }

    private View x2(int i) {
        View viewE2 = E2(0, f0(), i);
        if (viewE2 == null) {
            return null;
        }
        int i2 = this.B.f2940c[z0(viewE2)];
        if (i2 == -1) {
            return null;
        }
        return y2(viewE2, this.A.get(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View y2(android.view.View r6, com.google.android.flexbox.c r7) {
        /*
            r5 = this;
            boolean r0 = r5.v()
            int r7 = r7.h
            r1 = 1
        L7:
            if (r1 >= r7) goto L3f
            android.view.View r2 = r5.e0(r1)
            if (r2 == 0) goto L3c
            int r3 = r2.getVisibility()
            r4 = 8
            if (r3 != r4) goto L18
            goto L3c
        L18:
            boolean r3 = r5.y
            if (r3 == 0) goto L2d
            if (r0 != 0) goto L2d
            androidx.recyclerview.widget.k r3 = r5.G
            int r3 = r3.d(r6)
            androidx.recyclerview.widget.k r4 = r5.G
            int r4 = r4.d(r2)
            if (r3 >= r4) goto L3c
            goto L3b
        L2d:
            androidx.recyclerview.widget.k r3 = r5.G
            int r3 = r3.g(r6)
            androidx.recyclerview.widget.k r4 = r5.G
            int r4 = r4.g(r2)
            if (r3 <= r4) goto L3c
        L3b:
            r6 = r2
        L3c:
            int r1 = r1 + 1
            goto L7
        L3f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.y2(android.view.View, com.google.android.flexbox.c):android.view.View");
    }

    public int C2() {
        View viewD2 = D2(f0() - 1, -1, false);
        if (viewD2 == null) {
            return -1;
        }
        return z0(viewD2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean G() {
        return !v() || G0() > this.Q.getWidth();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean H() {
        return v() || s0() > this.Q.getHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean I(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int M(RecyclerView.a0 a0Var) {
        return r2(a0Var);
    }

    public View M2(int i) {
        View view = this.O.get(i);
        return view != null ? view : this.C.o(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.a0 a0Var) {
        s2(a0Var);
        return s2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int O(RecyclerView.a0 a0Var) {
        return t2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int P(RecyclerView.a0 a0Var) {
        return r2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int Q(RecyclerView.a0 a0Var) {
        return s2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int R(RecyclerView.a0 a0Var) {
        return t2(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int R1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (!v()) {
            int iN2 = N2(i, vVar, a0Var);
            this.O.clear();
            return iN2;
        }
        int iO2 = O2(i);
        this.F.f2927d += iO2;
        this.H.r(-iO2);
        return iO2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S1(int i) {
        this.J = i;
        this.K = Integer.MIN_VALUE;
        e eVar = this.I;
        if (eVar != null) {
            eVar.B();
        }
        O1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int T1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (v()) {
            int iN2 = N2(i, vVar, a0Var);
            this.O.clear();
            return iN2;
        }
        int iO2 = O2(i);
        this.F.f2927d += iO2;
        this.H.r(-iO2);
        return iO2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.g gVar, RecyclerView.g gVar2) {
        E1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p Z() {
        return new c(-2, -2);
    }

    public void Z2(int i) {
        int i2 = this.w;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                E1();
                q2();
            }
            this.w = i;
            O1();
        }
    }

    @Override // com.google.android.flexbox.a
    public int a() {
        if (this.A.size() == 0) {
            return 0;
        }
        int iMax = Integer.MIN_VALUE;
        int size = this.A.size();
        for (int i = 0; i < size; i++) {
            iMax = Math.max(iMax, this.A.get(i).e);
        }
        return iMax;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a0(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a1(RecyclerView recyclerView) {
        super.a1(recyclerView);
        this.Q = (View) recyclerView.getParent();
    }

    public void a3(int i) {
        if (this.t != i) {
            E1();
            this.t = i;
            this.G = null;
            this.H = null;
            q2();
            O1();
        }
    }

    public void b3(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        int i2 = this.u;
        if (i2 != i) {
            if (i2 == 0 || i == 0) {
                E1();
                q2();
            }
            this.u = i;
            this.G = null;
            this.H = null;
            O1();
        }
    }

    @Override // com.google.android.flexbox.a
    public int c(View view, int i, int i2) {
        int iE0;
        int iD0;
        if (v()) {
            iE0 = w0(view);
            iD0 = B0(view);
        } else {
            iE0 = E0(view);
            iD0 = d0(view);
        }
        return iE0 + iD0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.c1(recyclerView, vVar);
        if (this.N) {
            F1(vVar);
            vVar.c();
        }
    }

    @Override // com.google.android.flexbox.a
    public int d() {
        return this.u;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e2(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        h hVar = new h(recyclerView.getContext());
        hVar.p(i);
        f2(hVar);
    }

    @Override // com.google.android.flexbox.a
    public List<com.google.android.flexbox.c> g() {
        return this.A;
    }

    @Override // com.google.android.flexbox.a
    public int h() {
        return this.D.b();
    }

    @Override // com.google.android.flexbox.a
    public void i(com.google.android.flexbox.c cVar) {
    }

    @Override // com.google.android.flexbox.a
    public int k(int i, int i2, int i3) {
        return RecyclerView.o.g0(s0(), t0(), i2, i3, H());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void l1(RecyclerView recyclerView, int i, int i2) {
        super.l1(recyclerView, i, i2);
        f3(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF n(int i) {
        if (f0() == 0) {
            return null;
        }
        int i2 = i < z0(e0(0)) ? -1 : 1;
        return v() ? new PointF(0.0f, i2) : new PointF(i2, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void n1(RecyclerView recyclerView, int i, int i2, int i3) {
        super.n1(recyclerView, i, i2, i3);
        f3(Math.min(i, i2));
    }

    @Override // com.google.android.flexbox.a
    public View o(int i) {
        return M2(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o1(RecyclerView recyclerView, int i, int i2) {
        super.o1(recyclerView, i, i2);
        f3(i);
    }

    @Override // com.google.android.flexbox.a
    public void p(View view, int i, int i2, com.google.android.flexbox.c cVar) {
        int iE0;
        int iD0;
        F(view, s);
        if (v()) {
            iE0 = w0(view);
            iD0 = B0(view);
        } else {
            iE0 = E0(view);
            iD0 = d0(view);
        }
        int i3 = iE0 + iD0;
        cVar.e += i3;
        cVar.f += i3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void p1(RecyclerView recyclerView, int i, int i2) {
        super.p1(recyclerView, i, i2);
        f3(i);
    }

    @Override // com.google.android.flexbox.a
    public int q(int i, int i2, int i3) {
        return RecyclerView.o.g0(G0(), H0(), i2, i3, G());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void q1(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.q1(recyclerView, i, i2, obj);
        f3(i);
    }

    @Override // com.google.android.flexbox.a
    public int r() {
        return this.t;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void r1(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int i;
        int i2;
        this.C = vVar;
        this.D = a0Var;
        int iB = a0Var.b();
        if (iB == 0 && a0Var.e()) {
            return;
        }
        Y2();
        v2();
        u2();
        this.B.m(iB);
        this.B.n(iB);
        this.B.l(iB);
        this.E.j = false;
        e eVar = this.I;
        if (eVar != null && eVar.A(iB)) {
            this.J = this.I.f2932b;
        }
        if (!this.F.f || this.J != -1 || this.I != null) {
            this.F.s();
            e3(a0Var, this.F);
            this.F.f = true;
        }
        S(vVar);
        if (this.F.e) {
            j3(this.F, false, true);
        } else {
            i3(this.F, false, true);
        }
        g3(iB);
        if (this.F.e) {
            w2(vVar, a0Var, this.E);
            i2 = this.E.e;
            i3(this.F, true, false);
            w2(vVar, a0Var, this.E);
            i = this.E.e;
        } else {
            w2(vVar, a0Var, this.E);
            i = this.E.e;
            j3(this.F, true, false);
            w2(vVar, a0Var, this.E);
            i2 = this.E.e;
        }
        if (f0() > 0) {
            if (this.F.e) {
                G2(i2 + F2(i, vVar, a0Var, true), vVar, a0Var, false);
            } else {
                F2(i + G2(i2, vVar, a0Var, true), vVar, a0Var, false);
            }
        }
    }

    @Override // com.google.android.flexbox.a
    public int s() {
        return this.x;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void s1(RecyclerView.a0 a0Var) {
        super.s1(a0Var);
        this.I = null;
        this.J = -1;
        this.K = Integer.MIN_VALUE;
        this.R = -1;
        this.F.s();
        this.O.clear();
    }

    @Override // com.google.android.flexbox.a
    public int t() {
        return this.w;
    }

    @Override // com.google.android.flexbox.a
    public void u(int i, View view) {
        this.O.put(i, view);
    }

    @Override // com.google.android.flexbox.a
    public boolean v() {
        int i = this.t;
        return i == 0 || i == 1;
    }

    @Override // com.google.android.flexbox.a
    public int w(View view) {
        int iW0;
        int iB0;
        if (v()) {
            iW0 = E0(view);
            iB0 = d0(view);
        } else {
            iW0 = w0(view);
            iB0 = B0(view);
        }
        return iW0 + iB0;
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
        if (this.I != null) {
            return new e(this.I);
        }
        e eVar = new e();
        if (f0() > 0) {
            View viewI2 = I2();
            eVar.f2932b = z0(viewI2);
            eVar.f2933c = this.G.g(viewI2) - this.G.m();
        } else {
            eVar.B();
        }
        return eVar;
    }

    public int z2() {
        View viewD2 = D2(0, f0(), false);
        if (viewD2 == null) {
            return -1;
        }
        return z0(viewD2);
    }
}
