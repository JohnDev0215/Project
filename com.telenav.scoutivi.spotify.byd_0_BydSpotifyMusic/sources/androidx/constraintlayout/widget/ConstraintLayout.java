package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import r.d;
import r.e;
import r.m;
import s.b;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    private static j C;
    private int A;
    private int B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    SparseArray<View> f1650e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<androidx.constraintlayout.widget.b> f1651g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected r.f f1652h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1653i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1654j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1655k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1656l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    protected boolean f1657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f1658n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private d f1659o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    protected androidx.constraintlayout.widget.c f1660p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f1661q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private HashMap<String, Integer> f1662r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f1663s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f1664t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    int f1665u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    int f1666v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    int f1667w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    int f1668x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private SparseArray<r.e> f1669y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    c f1670z;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1671a;

        static {
            int[] iArr = new int[e.b.values().length];
            f1671a = iArr;
            try {
                iArr[e.b.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1671a[e.b.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1671a[e.b.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1671a[e.b.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class c implements b.InterfaceC0143b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ConstraintLayout f1722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1723b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1724c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1725d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1726e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1727f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f1728g;

        public c(ConstraintLayout constraintLayout) {
            this.f1722a = constraintLayout;
        }

        private boolean d(int i7, int i8, int i9) {
            if (i7 == i8) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i7);
            View.MeasureSpec.getSize(i7);
            int mode2 = View.MeasureSpec.getMode(i8);
            int size = View.MeasureSpec.getSize(i8);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i9 == size;
            }
            return false;
        }

        @Override // s.b.InterfaceC0143b
        @SuppressLint({"WrongCall"})
        public final void a(r.e eVar, b.a aVar) {
            int iMakeMeasureSpec;
            int iMakeMeasureSpec2;
            int baseline;
            int iMax;
            int i7;
            int measuredHeight;
            int i8;
            if (eVar == null) {
                return;
            }
            if (eVar.X() == 8 && !eVar.l0()) {
                aVar.f9390e = 0;
                aVar.f9391f = 0;
                aVar.f9392g = 0;
                return;
            }
            if (eVar.M() == null) {
                return;
            }
            e.b bVar = aVar.f9386a;
            e.b bVar2 = aVar.f9387b;
            int i9 = aVar.f9388c;
            int i10 = aVar.f9389d;
            int i11 = this.f1723b + this.f1724c;
            int i12 = this.f1725d;
            View view = (View) eVar.u();
            int[] iArr = a.f1671a;
            int i13 = iArr[bVar.ordinal()];
            if (i13 == 1) {
                iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            } else if (i13 == 2) {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f1727f, i12, -2);
            } else if (i13 == 3) {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f1727f, i12 + eVar.D(), -1);
            } else if (i13 != 4) {
                iMakeMeasureSpec = 0;
            } else {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f1727f, i12, -2);
                boolean z6 = eVar.f9248w == 1;
                int i14 = aVar.f9395j;
                if (i14 == b.a.f9384l || i14 == b.a.f9385m) {
                    if (aVar.f9395j == b.a.f9385m || !z6 || (z6 && (view.getMeasuredHeight() == eVar.z())) || (view instanceof g) || eVar.p0()) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(eVar.Y(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    }
                }
            }
            int i15 = iArr[bVar2.ordinal()];
            if (i15 == 1) {
                iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            } else if (i15 == 2) {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f1728g, i11, -2);
            } else if (i15 == 3) {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f1728g, i11 + eVar.W(), -1);
            } else if (i15 != 4) {
                iMakeMeasureSpec2 = 0;
            } else {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f1728g, i11, -2);
                boolean z7 = eVar.f9250x == 1;
                int i16 = aVar.f9395j;
                if (i16 == b.a.f9384l || i16 == b.a.f9385m) {
                    if (aVar.f9395j == b.a.f9385m || !z7 || (z7 && (view.getMeasuredWidth() == eVar.Y())) || (view instanceof g) || eVar.q0()) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(eVar.z(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    }
                }
            }
            r.f fVar = (r.f) eVar.M();
            if (fVar != null && r.k.b(ConstraintLayout.this.f1658n, 256) && view.getMeasuredWidth() == eVar.Y() && view.getMeasuredWidth() < fVar.Y() && view.getMeasuredHeight() == eVar.z() && view.getMeasuredHeight() < fVar.z() && view.getBaseline() == eVar.r() && !eVar.o0()) {
                if (d(eVar.E(), iMakeMeasureSpec, eVar.Y()) && d(eVar.F(), iMakeMeasureSpec2, eVar.z())) {
                    aVar.f9390e = eVar.Y();
                    aVar.f9391f = eVar.z();
                    aVar.f9392g = eVar.r();
                    return;
                }
            }
            e.b bVar3 = e.b.MATCH_CONSTRAINT;
            boolean z8 = bVar == bVar3;
            boolean z9 = bVar2 == bVar3;
            e.b bVar4 = e.b.MATCH_PARENT;
            boolean z10 = bVar2 == bVar4 || bVar2 == e.b.FIXED;
            boolean z11 = bVar == bVar4 || bVar == e.b.FIXED;
            boolean z12 = z8 && eVar.f9215f0 > 0.0f;
            boolean z13 = z9 && eVar.f9215f0 > 0.0f;
            if (view == null) {
                return;
            }
            b bVar5 = (b) view.getLayoutParams();
            int i17 = aVar.f9395j;
            if (i17 != b.a.f9384l && i17 != b.a.f9385m && z8 && eVar.f9248w == 0 && z9 && eVar.f9250x == 0) {
                i8 = -1;
                measuredHeight = 0;
                baseline = 0;
                iMax = 0;
            } else {
                if ((view instanceof l) && (eVar instanceof m)) {
                    ((l) view).x((m) eVar, iMakeMeasureSpec, iMakeMeasureSpec2);
                } else {
                    view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                }
                eVar.Y0(iMakeMeasureSpec, iMakeMeasureSpec2);
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                baseline = view.getBaseline();
                int i18 = eVar.f9254z;
                iMax = i18 > 0 ? Math.max(i18, measuredWidth) : measuredWidth;
                int i19 = eVar.A;
                if (i19 > 0) {
                    iMax = Math.min(i19, iMax);
                }
                int i20 = eVar.C;
                if (i20 > 0) {
                    measuredHeight = Math.max(i20, measuredHeight2);
                    i7 = iMakeMeasureSpec;
                } else {
                    i7 = iMakeMeasureSpec;
                    measuredHeight = measuredHeight2;
                }
                int i21 = eVar.D;
                if (i21 > 0) {
                    measuredHeight = Math.min(i21, measuredHeight);
                }
                if (!r.k.b(ConstraintLayout.this.f1658n, 1)) {
                    if (z12 && z10) {
                        iMax = (int) ((measuredHeight * eVar.f9215f0) + 0.5f);
                    } else if (z13 && z11) {
                        measuredHeight = (int) ((iMax / eVar.f9215f0) + 0.5f);
                    }
                }
                if (measuredWidth != iMax || measuredHeight2 != measuredHeight) {
                    int iMakeMeasureSpec3 = measuredWidth != iMax ? View.MeasureSpec.makeMeasureSpec(iMax, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : i7;
                    if (measuredHeight2 != measuredHeight) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    }
                    view.measure(iMakeMeasureSpec3, iMakeMeasureSpec2);
                    eVar.Y0(iMakeMeasureSpec3, iMakeMeasureSpec2);
                    iMax = view.getMeasuredWidth();
                    measuredHeight = view.getMeasuredHeight();
                    baseline = view.getBaseline();
                }
                i8 = -1;
            }
            boolean z14 = baseline != i8;
            aVar.f9394i = (iMax == aVar.f9388c && measuredHeight == aVar.f9389d) ? false : true;
            if (bVar5.f1685g0) {
                z14 = true;
            }
            if (z14 && baseline != -1 && eVar.r() != baseline) {
                aVar.f9394i = true;
            }
            aVar.f9390e = iMax;
            aVar.f9391f = measuredHeight;
            aVar.f9393h = z14;
            aVar.f9392g = baseline;
        }

        @Override // s.b.InterfaceC0143b
        public final void b() {
            int childCount = this.f1722a.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = this.f1722a.getChildAt(i7);
                if (childAt instanceof g) {
                    ((g) childAt).a(this.f1722a);
                }
            }
            int size = this.f1722a.f1651g.size();
            if (size > 0) {
                for (int i8 = 0; i8 < size; i8++) {
                    ((androidx.constraintlayout.widget.b) this.f1722a.f1651g.get(i8)).s(this.f1722a);
                }
            }
        }

        public void c(int i7, int i8, int i9, int i10, int i11, int i12) {
            this.f1723b = i9;
            this.f1724c = i10;
            this.f1725d = i11;
            this.f1726e = i12;
            this.f1727f = i7;
            this.f1728g = i8;
        }
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1650e = new SparseArray<>();
        this.f1651g = new ArrayList<>(4);
        this.f1652h = new r.f();
        this.f1653i = 0;
        this.f1654j = 0;
        this.f1655k = Integer.MAX_VALUE;
        this.f1656l = Integer.MAX_VALUE;
        this.f1657m = true;
        this.f1658n = 257;
        this.f1659o = null;
        this.f1660p = null;
        this.f1661q = -1;
        this.f1662r = new HashMap<>();
        this.f1663s = -1;
        this.f1664t = -1;
        this.f1665u = -1;
        this.f1666v = -1;
        this.f1667w = 0;
        this.f1668x = 0;
        this.f1669y = new SparseArray<>();
        this.f1670z = new c(this);
        this.A = 0;
        this.B = 0;
        q(attributeSet, 0, 0);
    }

    private boolean A() {
        int childCount = getChildCount();
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            if (i7 >= childCount) {
                break;
            }
            if (getChildAt(i7).isLayoutRequested()) {
                z6 = true;
                break;
            }
            i7++;
        }
        if (z6) {
            w();
        }
        return z6;
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int iMax2 = Math.max(0, getPaddingStart()) + Math.max(0, getPaddingEnd());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static j getSharedValues() {
        if (C == null) {
            C = new j();
        }
        return C;
    }

    private final r.e k(int i7) {
        if (i7 == 0) {
            return this.f1652h;
        }
        View viewFindViewById = this.f1650e.get(i7);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i7)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.f1652h;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((b) viewFindViewById.getLayoutParams()).f1715v0;
    }

    private void q(AttributeSet attributeSet, int i7, int i8) {
        this.f1652h.E0(this);
        this.f1652h.Y1(this.f1670z);
        this.f1650e.put(getId(), this);
        this.f1659o = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout, i7, i8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                if (index == i.ConstraintLayout_Layout_android_minWidth) {
                    this.f1653i = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1653i);
                } else if (index == i.ConstraintLayout_Layout_android_minHeight) {
                    this.f1654j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1654j);
                } else if (index == i.ConstraintLayout_Layout_android_maxWidth) {
                    this.f1655k = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1655k);
                } else if (index == i.ConstraintLayout_Layout_android_maxHeight) {
                    this.f1656l = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1656l);
                } else if (index == i.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.f1658n = typedArrayObtainStyledAttributes.getInt(index, this.f1658n);
                } else if (index == i.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            t(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.f1660p = null;
                        }
                    }
                } else if (index == i.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        d dVar = new d();
                        this.f1659o = dVar;
                        dVar.D(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.f1659o = null;
                    }
                    this.f1661q = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1652h.Z1(this.f1658n);
    }

    private void s() {
        this.f1657m = true;
        this.f1663s = -1;
        this.f1664t = -1;
        this.f1665u = -1;
        this.f1666v = -1;
        this.f1667w = 0;
        this.f1668x = 0;
    }

    private void w() {
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            r.e eVarP = p(getChildAt(i7));
            if (eVarP != null) {
                eVarP.v0();
            }
        }
        if (zIsInEditMode) {
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    x(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    k(childAt.getId()).F0(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.f1661q != -1) {
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt2 = getChildAt(i9);
                if (childAt2.getId() == this.f1661q && (childAt2 instanceof e)) {
                    this.f1659o = ((e) childAt2).getConstraintSet();
                }
            }
        }
        d dVar = this.f1659o;
        if (dVar != null) {
            dVar.k(this, true);
        }
        this.f1652h.x1();
        int size = this.f1651g.size();
        if (size > 0) {
            for (int i10 = 0; i10 < size; i10++) {
                this.f1651g.get(i10).u(this);
            }
        }
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt3 = getChildAt(i11);
            if (childAt3 instanceof g) {
                ((g) childAt3).b(this);
            }
        }
        this.f1669y.clear();
        this.f1669y.put(0, this.f1652h);
        this.f1669y.put(getId(), this.f1652h);
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt4 = getChildAt(i12);
            this.f1669y.put(childAt4.getId(), p(childAt4));
        }
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt5 = getChildAt(i13);
            r.e eVarP2 = p(childAt5);
            if (eVarP2 != null) {
                b bVar = (b) childAt5.getLayoutParams();
                this.f1652h.c(eVarP2);
                d(zIsInEditMode, childAt5, eVarP2, bVar, this.f1669y);
            }
        }
    }

    private void z(r.e eVar, b bVar, SparseArray<r.e> sparseArray, int i7, d.b bVar2) {
        View view = this.f1650e.get(i7);
        r.e eVar2 = sparseArray.get(i7);
        if (eVar2 == null || view == null || !(view.getLayoutParams() instanceof b)) {
            return;
        }
        bVar.f1685g0 = true;
        d.b bVar3 = d.b.BASELINE;
        if (bVar2 == bVar3) {
            b bVar4 = (b) view.getLayoutParams();
            bVar4.f1685g0 = true;
            bVar4.f1715v0.N0(true);
        }
        eVar.q(bVar3).b(eVar2.q(bVar2), bVar.D, bVar.C, true);
        eVar.N0(true);
        eVar.q(d.b.TOP).q();
        eVar.q(d.b.BOTTOM).q();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(boolean z6, View view, r.e eVar, b bVar, SparseArray<r.e> sparseArray) {
        r.e eVar2;
        r.e eVar3;
        r.e eVar4;
        r.e eVar5;
        int i7;
        bVar.b();
        bVar.f1717w0 = false;
        eVar.m1(view.getVisibility());
        if (bVar.f1691j0) {
            eVar.W0(true);
            eVar.m1(8);
        }
        eVar.E0(view);
        if (view instanceof androidx.constraintlayout.widget.b) {
            ((androidx.constraintlayout.widget.b) view).q(eVar, this.f1652h.S1());
        }
        if (bVar.f1687h0) {
            r.h hVar = (r.h) eVar;
            int i8 = bVar.f1709s0;
            int i9 = bVar.f1711t0;
            float f7 = bVar.f1713u0;
            if (f7 != -1.0f) {
                hVar.C1(f7);
                return;
            } else if (i8 != -1) {
                hVar.A1(i8);
                return;
            } else {
                if (i9 != -1) {
                    hVar.B1(i9);
                    return;
                }
                return;
            }
        }
        int i10 = bVar.f1695l0;
        int i11 = bVar.f1697m0;
        int i12 = bVar.f1699n0;
        int i13 = bVar.f1701o0;
        int i14 = bVar.f1703p0;
        int i15 = bVar.f1705q0;
        float f8 = bVar.f1707r0;
        int i16 = bVar.f1702p;
        if (i16 != -1) {
            r.e eVar6 = sparseArray.get(i16);
            if (eVar6 != null) {
                eVar.m(eVar6, bVar.f1706r, bVar.f1704q);
            }
        } else {
            if (i10 != -1) {
                r.e eVar7 = sparseArray.get(i10);
                if (eVar7 != null) {
                    d.b bVar2 = d.b.LEFT;
                    eVar.g0(bVar2, eVar7, bVar2, ((ViewGroup.MarginLayoutParams) bVar).leftMargin, i14);
                }
            } else if (i11 != -1 && (eVar2 = sparseArray.get(i11)) != null) {
                eVar.g0(d.b.LEFT, eVar2, d.b.RIGHT, ((ViewGroup.MarginLayoutParams) bVar).leftMargin, i14);
            }
            if (i12 != -1) {
                r.e eVar8 = sparseArray.get(i12);
                if (eVar8 != null) {
                    eVar.g0(d.b.RIGHT, eVar8, d.b.LEFT, ((ViewGroup.MarginLayoutParams) bVar).rightMargin, i15);
                }
            } else if (i13 != -1 && (eVar3 = sparseArray.get(i13)) != null) {
                d.b bVar3 = d.b.RIGHT;
                eVar.g0(bVar3, eVar3, bVar3, ((ViewGroup.MarginLayoutParams) bVar).rightMargin, i15);
            }
            int i17 = bVar.f1688i;
            if (i17 != -1) {
                r.e eVar9 = sparseArray.get(i17);
                if (eVar9 != null) {
                    d.b bVar4 = d.b.TOP;
                    eVar.g0(bVar4, eVar9, bVar4, ((ViewGroup.MarginLayoutParams) bVar).topMargin, bVar.f1718x);
                }
            } else {
                int i18 = bVar.f1690j;
                if (i18 != -1 && (eVar4 = sparseArray.get(i18)) != null) {
                    eVar.g0(d.b.TOP, eVar4, d.b.BOTTOM, ((ViewGroup.MarginLayoutParams) bVar).topMargin, bVar.f1718x);
                }
            }
            int i19 = bVar.f1692k;
            if (i19 != -1) {
                r.e eVar10 = sparseArray.get(i19);
                if (eVar10 != null) {
                    eVar.g0(d.b.BOTTOM, eVar10, d.b.TOP, ((ViewGroup.MarginLayoutParams) bVar).bottomMargin, bVar.f1720z);
                }
            } else {
                int i20 = bVar.f1694l;
                if (i20 != -1 && (eVar5 = sparseArray.get(i20)) != null) {
                    d.b bVar5 = d.b.BOTTOM;
                    eVar.g0(bVar5, eVar5, bVar5, ((ViewGroup.MarginLayoutParams) bVar).bottomMargin, bVar.f1720z);
                }
            }
            int i21 = bVar.f1696m;
            if (i21 != -1) {
                z(eVar, bVar, sparseArray, i21, d.b.BASELINE);
            } else {
                int i22 = bVar.f1698n;
                if (i22 != -1) {
                    z(eVar, bVar, sparseArray, i22, d.b.TOP);
                } else {
                    int i23 = bVar.f1700o;
                    if (i23 != -1) {
                        z(eVar, bVar, sparseArray, i23, d.b.BOTTOM);
                    }
                }
            }
            if (f8 >= 0.0f) {
                eVar.P0(f8);
            }
            float f9 = bVar.H;
            if (f9 >= 0.0f) {
                eVar.g1(f9);
            }
        }
        if (z6 && ((i7 = bVar.X) != -1 || bVar.Y != -1)) {
            eVar.e1(i7, bVar.Y);
        }
        if (bVar.f1681e0) {
            eVar.S0(e.b.FIXED);
            eVar.n1(((ViewGroup.MarginLayoutParams) bVar).width);
            if (((ViewGroup.MarginLayoutParams) bVar).width == -2) {
                eVar.S0(e.b.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) bVar).width == -1) {
            if (bVar.f1673a0) {
                eVar.S0(e.b.MATCH_CONSTRAINT);
            } else {
                eVar.S0(e.b.MATCH_PARENT);
            }
            eVar.q(d.b.LEFT).f9199g = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            eVar.q(d.b.RIGHT).f9199g = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        } else {
            eVar.S0(e.b.MATCH_CONSTRAINT);
            eVar.n1(0);
        }
        if (bVar.f1683f0) {
            eVar.j1(e.b.FIXED);
            eVar.O0(((ViewGroup.MarginLayoutParams) bVar).height);
            if (((ViewGroup.MarginLayoutParams) bVar).height == -2) {
                eVar.j1(e.b.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) bVar).height == -1) {
            if (bVar.f1675b0) {
                eVar.j1(e.b.MATCH_CONSTRAINT);
            } else {
                eVar.j1(e.b.MATCH_PARENT);
            }
            eVar.q(d.b.TOP).f9199g = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            eVar.q(d.b.BOTTOM).f9199g = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        } else {
            eVar.j1(e.b.MATCH_CONSTRAINT);
            eVar.O0(0);
        }
        eVar.G0(bVar.I);
        eVar.U0(bVar.L);
        eVar.l1(bVar.M);
        eVar.Q0(bVar.N);
        eVar.h1(bVar.O);
        eVar.o1(bVar.f1679d0);
        eVar.T0(bVar.P, bVar.R, bVar.T, bVar.V);
        eVar.k1(bVar.Q, bVar.S, bVar.U, bVar.W);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<androidx.constraintlayout.widget.b> arrayList = this.f1651g;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                this.f1651g.get(i7).t(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i9 = Integer.parseInt(strArrSplit[0]);
                        int i10 = Integer.parseInt(strArrSplit[1]);
                        int i11 = Integer.parseInt(strArrSplit[2]);
                        int i12 = (int) ((i9 / 1080.0f) * width);
                        int i13 = (int) ((i10 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f7 = i12;
                        float f8 = i13;
                        float f9 = i12 + ((int) ((i11 / 1080.0f) * width));
                        canvas.drawLine(f7, f8, f9, f8, paint);
                        float f10 = i13 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f9, f8, f9, f10, paint);
                        canvas.drawLine(f9, f10, f7, f10, paint);
                        canvas.drawLine(f7, f10, f7, f8, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f7, f8, f9, f10, paint);
                        canvas.drawLine(f7, f10, f9, f8, paint);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    @Override // android.view.View
    public void forceLayout() {
        s();
        super.forceLayout();
    }

    public Object g(int i7, Object obj) {
        if (i7 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.f1662r;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.f1662r.get(str);
    }

    public int getMaxHeight() {
        return this.f1656l;
    }

    public int getMaxWidth() {
        return this.f1655k;
    }

    public int getMinHeight() {
        return this.f1654j;
    }

    public int getMinWidth() {
        return this.f1653i;
    }

    public int getOptimizationLevel() {
        return this.f1652h.M1();
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (this.f1652h.f9232o == null) {
            int id2 = getId();
            if (id2 != -1) {
                this.f1652h.f9232o = getContext().getResources().getResourceEntryName(id2);
            } else {
                this.f1652h.f9232o = "parent";
            }
        }
        if (this.f1652h.v() == null) {
            r.f fVar = this.f1652h;
            fVar.F0(fVar.f9232o);
            Log.v("ConstraintLayout", " setDebugName " + this.f1652h.v());
        }
        for (r.e eVar : this.f1652h.u1()) {
            View view = (View) eVar.u();
            if (view != null) {
                if (eVar.f9232o == null && (id = view.getId()) != -1) {
                    eVar.f9232o = getContext().getResources().getResourceEntryName(id);
                }
                if (eVar.v() == null) {
                    eVar.F0(eVar.f9232o);
                    Log.v("ConstraintLayout", " setDebugName " + eVar.v());
                }
            }
        }
        this.f1652h.Q(sb);
        return sb.toString();
    }

    public View l(int i7) {
        return this.f1650e.get(i7);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            b bVar = (b) childAt.getLayoutParams();
            r.e eVar = bVar.f1715v0;
            if ((childAt.getVisibility() != 8 || bVar.f1687h0 || bVar.f1689i0 || bVar.f1693k0 || zIsInEditMode) && !bVar.f1691j0) {
                int iZ = eVar.Z();
                int iA0 = eVar.a0();
                int iY = eVar.Y() + iZ;
                int iZ2 = eVar.z() + iA0;
                childAt.layout(iZ, iA0, iY, iZ2);
                if ((childAt instanceof g) && (content = ((g) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iZ, iA0, iY, iZ2);
                }
            }
        }
        int size = this.f1651g.size();
        if (size > 0) {
            for (int i12 = 0; i12 < size; i12++) {
                this.f1651g.get(i12).r(this);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        if (this.A == i7) {
            int i9 = this.B;
        }
        if (!this.f1657m) {
            int childCount = getChildCount();
            int i10 = 0;
            while (true) {
                if (i10 >= childCount) {
                    break;
                }
                if (getChildAt(i10).isLayoutRequested()) {
                    this.f1657m = true;
                    break;
                }
                i10++;
            }
        }
        boolean z6 = this.f1657m;
        this.A = i7;
        this.B = i8;
        this.f1652h.b2(r());
        if (this.f1657m) {
            this.f1657m = false;
            if (A()) {
                this.f1652h.d2();
            }
        }
        v(this.f1652h, this.f1658n, i7, i8);
        u(i7, i8, this.f1652h.Y(), this.f1652h.z(), this.f1652h.T1(), this.f1652h.R1());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        r.e eVarP = p(view);
        if ((view instanceof Guideline) && !(eVarP instanceof r.h)) {
            b bVar = (b) view.getLayoutParams();
            r.h hVar = new r.h();
            bVar.f1715v0 = hVar;
            bVar.f1687h0 = true;
            hVar.D1(bVar.Z);
        }
        if (view instanceof androidx.constraintlayout.widget.b) {
            androidx.constraintlayout.widget.b bVar2 = (androidx.constraintlayout.widget.b) view;
            bVar2.w();
            ((b) view.getLayoutParams()).f1689i0 = true;
            if (!this.f1651g.contains(bVar2)) {
                this.f1651g.add(bVar2);
            }
        }
        this.f1650e.put(view.getId(), view);
        this.f1657m = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f1650e.remove(view.getId());
        this.f1652h.w1(p(view));
        this.f1651g.remove(view);
        this.f1657m = true;
    }

    public final r.e p(View view) {
        if (view == this) {
            return this.f1652h;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof b) {
            return ((b) view.getLayoutParams()).f1715v0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof b) {
            return ((b) view.getLayoutParams()).f1715v0;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean r() {
        return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        s();
        super.requestLayout();
    }

    public void setConstraintSet(d dVar) {
        this.f1659o = dVar;
    }

    @Override // android.view.View
    public void setId(int i7) {
        this.f1650e.remove(getId());
        super.setId(i7);
        this.f1650e.put(getId(), this);
    }

    public void setMaxHeight(int i7) {
        if (i7 == this.f1656l) {
            return;
        }
        this.f1656l = i7;
        requestLayout();
    }

    public void setMaxWidth(int i7) {
        if (i7 == this.f1655k) {
            return;
        }
        this.f1655k = i7;
        requestLayout();
    }

    public void setMinHeight(int i7) {
        if (i7 == this.f1654j) {
            return;
        }
        this.f1654j = i7;
        requestLayout();
    }

    public void setMinWidth(int i7) {
        if (i7 == this.f1653i) {
            return;
        }
        this.f1653i = i7;
        requestLayout();
    }

    public void setOnConstraintsChanged(f fVar) {
        androidx.constraintlayout.widget.c cVar = this.f1660p;
        if (cVar != null) {
            cVar.c(fVar);
        }
    }

    public void setOptimizationLevel(int i7) {
        this.f1658n = i7;
        this.f1652h.Z1(i7);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected void t(int i7) {
        this.f1660p = new androidx.constraintlayout.widget.c(getContext(), this, i7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void u(int i7, int i8, int i9, int i10, boolean z6, boolean z7) {
        c cVar = this.f1670z;
        int i11 = cVar.f1726e;
        int iResolveSizeAndState = ViewGroup.resolveSizeAndState(i9 + cVar.f1725d, i7, 0);
        int iResolveSizeAndState2 = ViewGroup.resolveSizeAndState(i10 + i11, i8, 0) & 16777215;
        int iMin = Math.min(this.f1655k, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.f1656l, iResolveSizeAndState2);
        if (z6) {
            iMin |= 16777216;
        }
        if (z7) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.f1663s = iMin;
        this.f1664t = iMin2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void v(r.f fVar, int i7, int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i9);
        int size2 = View.MeasureSpec.getSize(i9);
        int iMax = Math.max(0, getPaddingTop());
        int iMax2 = Math.max(0, getPaddingBottom());
        int i10 = iMax + iMax2;
        int paddingWidth = getPaddingWidth();
        this.f1670z.c(i8, i9, iMax, iMax2, paddingWidth, i10);
        int iMax3 = Math.max(0, getPaddingStart());
        int iMax4 = Math.max(0, getPaddingEnd());
        int iMax5 = (iMax3 > 0 || iMax4 > 0) ? r() ? iMax4 : iMax3 : Math.max(0, getPaddingLeft());
        int i11 = size - paddingWidth;
        int i12 = size2 - i10;
        y(fVar, mode, i11, mode2, i12);
        fVar.U1(i7, mode, i11, mode2, i12, this.f1663s, this.f1664t, iMax5, iMax);
    }

    public void x(int i7, Object obj, Object obj2) {
        if (i7 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.f1662r == null) {
                this.f1662r = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.f1662r.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e A[PHI: r2
  0x003e: PHI (r2v4 r.e$b) = (r2v3 r.e$b), (r2v0 r.e$b) binds: [B:21:0x004a, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void y(r.f r8, int r9, int r10, int r11, int r12) {
        /*
            r7 = this;
            androidx.constraintlayout.widget.ConstraintLayout$c r0 = r7.f1670z
            int r1 = r0.f1726e
            int r0 = r0.f1725d
            r.e$b r2 = r.e.b.FIXED
            int r3 = r7.getChildCount()
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 0
            if (r9 == r5) goto L2e
            if (r9 == 0) goto L23
            if (r9 == r4) goto L1a
            r9 = r2
        L18:
            r10 = r6
            goto L38
        L1a:
            int r9 = r7.f1655k
            int r9 = r9 - r0
            int r10 = java.lang.Math.min(r9, r10)
            r9 = r2
            goto L38
        L23:
            r.e$b r9 = r.e.b.WRAP_CONTENT
            if (r3 != 0) goto L18
            int r10 = r7.f1653i
            int r10 = java.lang.Math.max(r6, r10)
            goto L38
        L2e:
            r.e$b r9 = r.e.b.WRAP_CONTENT
            if (r3 != 0) goto L38
            int r10 = r7.f1653i
            int r10 = java.lang.Math.max(r6, r10)
        L38:
            if (r11 == r5) goto L53
            if (r11 == 0) goto L48
            if (r11 == r4) goto L40
        L3e:
            r12 = r6
            goto L5d
        L40:
            int r11 = r7.f1656l
            int r11 = r11 - r1
            int r12 = java.lang.Math.min(r11, r12)
            goto L5d
        L48:
            r.e$b r2 = r.e.b.WRAP_CONTENT
            if (r3 != 0) goto L3e
            int r11 = r7.f1654j
            int r12 = java.lang.Math.max(r6, r11)
            goto L5d
        L53:
            r.e$b r2 = r.e.b.WRAP_CONTENT
            if (r3 != 0) goto L5d
            int r11 = r7.f1654j
            int r12 = java.lang.Math.max(r6, r11)
        L5d:
            int r11 = r8.Y()
            if (r10 != r11) goto L69
            int r11 = r8.z()
            if (r12 == r11) goto L6c
        L69:
            r8.Q1()
        L6c:
            r8.p1(r6)
            r8.q1(r6)
            int r11 = r7.f1655k
            int r11 = r11 - r0
            r8.a1(r11)
            int r11 = r7.f1656l
            int r11 = r11 - r1
            r8.Z0(r11)
            r8.d1(r6)
            r8.c1(r6)
            r8.S0(r9)
            r8.n1(r10)
            r8.j1(r2)
            r8.O0(r12)
            int r9 = r7.f1653i
            int r9 = r9 - r0
            r8.d1(r9)
            int r9 = r7.f1654j
            int r9 = r9 - r1
            r8.c1(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.y(r.f, int, int, int, int):void");
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1650e = new SparseArray<>();
        this.f1651g = new ArrayList<>(4);
        this.f1652h = new r.f();
        this.f1653i = 0;
        this.f1654j = 0;
        this.f1655k = Integer.MAX_VALUE;
        this.f1656l = Integer.MAX_VALUE;
        this.f1657m = true;
        this.f1658n = 257;
        this.f1659o = null;
        this.f1660p = null;
        this.f1661q = -1;
        this.f1662r = new HashMap<>();
        this.f1663s = -1;
        this.f1664t = -1;
        this.f1665u = -1;
        this.f1666v = -1;
        this.f1667w = 0;
        this.f1668x = 0;
        this.f1669y = new SparseArray<>();
        this.f1670z = new c(this);
        this.A = 0;
        this.B = 0;
        q(attributeSet, i7, 0);
    }

    public static class b extends ViewGroup.MarginLayoutParams {
        public int A;
        public int B;
        public int C;
        public int D;
        boolean E;
        boolean F;
        public float G;
        public float H;
        public String I;
        float J;
        int K;
        public float L;
        public float M;
        public int N;
        public int O;
        public int P;
        public int Q;
        public int R;
        public int S;
        public int T;
        public int U;
        public float V;
        public float W;
        public int X;
        public int Y;
        public int Z;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1672a;

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public boolean f1673a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1674b;

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public boolean f1675b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1676c;

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public String f1677c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1678d;

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public int f1679d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1680e;

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        boolean f1681e0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1682f;

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        boolean f1683f0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1684g;

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        boolean f1685g0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1686h;

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        boolean f1687h0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1688i;

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        boolean f1689i0;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1690j;

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        boolean f1691j0;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1692k;

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        boolean f1693k0;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1694l;

        /* JADX INFO: renamed from: l0, reason: collision with root package name */
        int f1695l0;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1696m;

        /* JADX INFO: renamed from: m0, reason: collision with root package name */
        int f1697m0;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1698n;

        /* JADX INFO: renamed from: n0, reason: collision with root package name */
        int f1699n0;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1700o;

        /* JADX INFO: renamed from: o0, reason: collision with root package name */
        int f1701o0;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1702p;

        /* JADX INFO: renamed from: p0, reason: collision with root package name */
        int f1703p0;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1704q;

        /* JADX INFO: renamed from: q0, reason: collision with root package name */
        int f1705q0;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public float f1706r;

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        float f1707r0;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f1708s;

        /* JADX INFO: renamed from: s0, reason: collision with root package name */
        int f1709s0;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f1710t;

        /* JADX INFO: renamed from: t0, reason: collision with root package name */
        int f1711t0;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f1712u;

        /* JADX INFO: renamed from: u0, reason: collision with root package name */
        float f1713u0;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f1714v;

        /* JADX INFO: renamed from: v0, reason: collision with root package name */
        r.e f1715v0;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f1716w;

        /* JADX INFO: renamed from: w0, reason: collision with root package name */
        public boolean f1717w0;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f1718x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f1719y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f1720z;

        private static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f1721a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f1721a = sparseIntArray;
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintWidth, 64);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHeight, 65);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBaseline_toTopOf, 52);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBaseline_toBottomOf, 53);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(i.ConstraintLayout_Layout_guidelineUseRtl, 67);
                sparseIntArray.append(i.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_goneMarginBaseline, 55);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_marginBaseline, 54);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintTag, 51);
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_wrapBehaviorInParent, 66);
            }
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1672a = -1;
            this.f1674b = -1;
            this.f1676c = -1.0f;
            this.f1678d = true;
            this.f1680e = -1;
            this.f1682f = -1;
            this.f1684g = -1;
            this.f1686h = -1;
            this.f1688i = -1;
            this.f1690j = -1;
            this.f1692k = -1;
            this.f1694l = -1;
            this.f1696m = -1;
            this.f1698n = -1;
            this.f1700o = -1;
            this.f1702p = -1;
            this.f1704q = 0;
            this.f1706r = 0.0f;
            this.f1708s = -1;
            this.f1710t = -1;
            this.f1712u = -1;
            this.f1714v = -1;
            this.f1716w = Integer.MIN_VALUE;
            this.f1718x = Integer.MIN_VALUE;
            this.f1719y = Integer.MIN_VALUE;
            this.f1720z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = true;
            this.F = true;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.f1673a0 = false;
            this.f1675b0 = false;
            this.f1677c0 = null;
            this.f1679d0 = 0;
            this.f1681e0 = true;
            this.f1683f0 = true;
            this.f1685g0 = false;
            this.f1687h0 = false;
            this.f1689i0 = false;
            this.f1691j0 = false;
            this.f1693k0 = false;
            this.f1695l0 = -1;
            this.f1697m0 = -1;
            this.f1699n0 = -1;
            this.f1701o0 = -1;
            this.f1703p0 = Integer.MIN_VALUE;
            this.f1705q0 = Integer.MIN_VALUE;
            this.f1707r0 = 0.5f;
            this.f1715v0 = new r.e();
            this.f1717w0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                int i8 = a.f1721a.get(index);
                switch (i8) {
                    case 1:
                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.f1702p);
                        this.f1702p = resourceId;
                        if (resourceId == -1) {
                            this.f1702p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.f1704q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1704q);
                        break;
                    case 4:
                        float f7 = typedArrayObtainStyledAttributes.getFloat(index, this.f1706r) % 360.0f;
                        this.f1706r = f7;
                        if (f7 < 0.0f) {
                            this.f1706r = (360.0f - f7) % 360.0f;
                        }
                        break;
                    case 5:
                        this.f1672a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1672a);
                        break;
                    case 6:
                        this.f1674b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1674b);
                        break;
                    case 7:
                        this.f1676c = typedArrayObtainStyledAttributes.getFloat(index, this.f1676c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1680e);
                        this.f1680e = resourceId2;
                        if (resourceId2 == -1) {
                            this.f1680e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1682f);
                        this.f1682f = resourceId3;
                        if (resourceId3 == -1) {
                            this.f1682f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1684g);
                        this.f1684g = resourceId4;
                        if (resourceId4 == -1) {
                            this.f1684g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1686h);
                        this.f1686h = resourceId5;
                        if (resourceId5 == -1) {
                            this.f1686h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1688i);
                        this.f1688i = resourceId6;
                        if (resourceId6 == -1) {
                            this.f1688i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1690j);
                        this.f1690j = resourceId7;
                        if (resourceId7 == -1) {
                            this.f1690j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1692k);
                        this.f1692k = resourceId8;
                        if (resourceId8 == -1) {
                            this.f1692k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1694l);
                        this.f1694l = resourceId9;
                        if (resourceId9 == -1) {
                            this.f1694l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1696m);
                        this.f1696m = resourceId10;
                        if (resourceId10 == -1) {
                            this.f1696m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1708s);
                        this.f1708s = resourceId11;
                        if (resourceId11 == -1) {
                            this.f1708s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1710t);
                        this.f1710t = resourceId12;
                        if (resourceId12 == -1) {
                            this.f1710t = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1712u);
                        this.f1712u = resourceId13;
                        if (resourceId13 == -1) {
                            this.f1712u = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1714v);
                        this.f1714v = resourceId14;
                        if (resourceId14 == -1) {
                            this.f1714v = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.f1716w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1716w);
                        break;
                    case 22:
                        this.f1718x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1718x);
                        break;
                    case 23:
                        this.f1719y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1719y);
                        break;
                    case 24:
                        this.f1720z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1720z);
                        break;
                    case 25:
                        this.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.A);
                        break;
                    case 26:
                        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.B);
                        break;
                    case 27:
                        this.f1673a0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1673a0);
                        break;
                    case 28:
                        this.f1675b0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1675b0);
                        break;
                    case 29:
                        this.G = typedArrayObtainStyledAttributes.getFloat(index, this.G);
                        break;
                    case 30:
                        this.H = typedArrayObtainStyledAttributes.getFloat(index, this.H);
                        break;
                    case 31:
                        int i9 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.P = i9;
                        if (i9 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i10 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.Q = i10;
                        if (i10 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case 33:
                        try {
                            this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.R) == -2) {
                                this.R = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.T) == -2) {
                                this.T = -2;
                            }
                        }
                        break;
                    case 35:
                        this.V = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.V));
                        this.P = 2;
                        break;
                    case 36:
                        try {
                            this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.S) == -2) {
                                this.S = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.U);
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.U) == -2) {
                                this.U = -2;
                            }
                        }
                        break;
                    case 38:
                        this.W = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.W));
                        this.Q = 2;
                        break;
                    default:
                        switch (i8) {
                            case 44:
                                d.I(this, typedArrayObtainStyledAttributes.getString(index));
                                break;
                            case 45:
                                this.L = typedArrayObtainStyledAttributes.getFloat(index, this.L);
                                break;
                            case 46:
                                this.M = typedArrayObtainStyledAttributes.getFloat(index, this.M);
                                break;
                            case 47:
                                this.N = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.O = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.X = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.X);
                                break;
                            case 50:
                                this.Y = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.Y);
                                break;
                            case 51:
                                this.f1677c0 = typedArrayObtainStyledAttributes.getString(index);
                                break;
                            case 52:
                                int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1698n);
                                this.f1698n = resourceId15;
                                if (resourceId15 == -1) {
                                    this.f1698n = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 53:
                                int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1700o);
                                this.f1700o = resourceId16;
                                if (resourceId16 == -1) {
                                    this.f1700o = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 54:
                                this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                                break;
                            case 55:
                                this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            default:
                                switch (i8) {
                                    case 64:
                                        d.G(this, typedArrayObtainStyledAttributes, index, 0);
                                        this.E = true;
                                        break;
                                    case 65:
                                        d.G(this, typedArrayObtainStyledAttributes, index, 1);
                                        this.F = true;
                                        break;
                                    case 66:
                                        this.f1679d0 = typedArrayObtainStyledAttributes.getInt(index, this.f1679d0);
                                        break;
                                    case 67:
                                        this.f1678d = typedArrayObtainStyledAttributes.getBoolean(index, this.f1678d);
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            b();
        }

        public String a() {
            return this.f1677c0;
        }

        public void b() {
            this.f1687h0 = false;
            this.f1681e0 = true;
            this.f1683f0 = true;
            int i7 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i7 == -2 && this.f1673a0) {
                this.f1681e0 = false;
                if (this.P == 0) {
                    this.P = 1;
                }
            }
            int i8 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i8 == -2 && this.f1675b0) {
                this.f1683f0 = false;
                if (this.Q == 0) {
                    this.Q = 1;
                }
            }
            if (i7 == 0 || i7 == -1) {
                this.f1681e0 = false;
                if (i7 == 0 && this.P == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.f1673a0 = true;
                }
            }
            if (i8 == 0 || i8 == -1) {
                this.f1683f0 = false;
                if (i8 == 0 && this.Q == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.f1675b0 = true;
                }
            }
            if (this.f1676c == -1.0f && this.f1672a == -1 && this.f1674b == -1) {
                return;
            }
            this.f1687h0 = true;
            this.f1681e0 = true;
            this.f1683f0 = true;
            if (!(this.f1715v0 instanceof r.h)) {
                this.f1715v0 = new r.h();
            }
            ((r.h) this.f1715v0).D1(this.Z);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0082  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @android.annotation.TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void resolveLayoutDirection(int r11) {
            /*
                Method dump skipped, instruction units count: 259
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.b.resolveLayoutDirection(int):void");
        }

        public b(int i7, int i8) {
            super(i7, i8);
            this.f1672a = -1;
            this.f1674b = -1;
            this.f1676c = -1.0f;
            this.f1678d = true;
            this.f1680e = -1;
            this.f1682f = -1;
            this.f1684g = -1;
            this.f1686h = -1;
            this.f1688i = -1;
            this.f1690j = -1;
            this.f1692k = -1;
            this.f1694l = -1;
            this.f1696m = -1;
            this.f1698n = -1;
            this.f1700o = -1;
            this.f1702p = -1;
            this.f1704q = 0;
            this.f1706r = 0.0f;
            this.f1708s = -1;
            this.f1710t = -1;
            this.f1712u = -1;
            this.f1714v = -1;
            this.f1716w = Integer.MIN_VALUE;
            this.f1718x = Integer.MIN_VALUE;
            this.f1719y = Integer.MIN_VALUE;
            this.f1720z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = true;
            this.F = true;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.f1673a0 = false;
            this.f1675b0 = false;
            this.f1677c0 = null;
            this.f1679d0 = 0;
            this.f1681e0 = true;
            this.f1683f0 = true;
            this.f1685g0 = false;
            this.f1687h0 = false;
            this.f1689i0 = false;
            this.f1691j0 = false;
            this.f1693k0 = false;
            this.f1695l0 = -1;
            this.f1697m0 = -1;
            this.f1699n0 = -1;
            this.f1701o0 = -1;
            this.f1703p0 = Integer.MIN_VALUE;
            this.f1705q0 = Integer.MIN_VALUE;
            this.f1707r0 = 0.5f;
            this.f1715v0 = new r.e();
            this.f1717w0 = false;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1672a = -1;
            this.f1674b = -1;
            this.f1676c = -1.0f;
            this.f1678d = true;
            this.f1680e = -1;
            this.f1682f = -1;
            this.f1684g = -1;
            this.f1686h = -1;
            this.f1688i = -1;
            this.f1690j = -1;
            this.f1692k = -1;
            this.f1694l = -1;
            this.f1696m = -1;
            this.f1698n = -1;
            this.f1700o = -1;
            this.f1702p = -1;
            this.f1704q = 0;
            this.f1706r = 0.0f;
            this.f1708s = -1;
            this.f1710t = -1;
            this.f1712u = -1;
            this.f1714v = -1;
            this.f1716w = Integer.MIN_VALUE;
            this.f1718x = Integer.MIN_VALUE;
            this.f1719y = Integer.MIN_VALUE;
            this.f1720z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = true;
            this.F = true;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.f1673a0 = false;
            this.f1675b0 = false;
            this.f1677c0 = null;
            this.f1679d0 = 0;
            this.f1681e0 = true;
            this.f1683f0 = true;
            this.f1685g0 = false;
            this.f1687h0 = false;
            this.f1689i0 = false;
            this.f1691j0 = false;
            this.f1693k0 = false;
            this.f1695l0 = -1;
            this.f1697m0 = -1;
            this.f1699n0 = -1;
            this.f1701o0 = -1;
            this.f1703p0 = Integer.MIN_VALUE;
            this.f1705q0 = Integer.MIN_VALUE;
            this.f1707r0 = 0.5f;
            this.f1715v0 = new r.e();
            this.f1717w0 = false;
        }
    }
}
