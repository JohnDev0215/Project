package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.b;
import androidx.constraintlayout.core.widgets.i;
import androidx.constraintlayout.core.widgets.k;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static f f1032b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    SparseArray<View> f1033c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ArrayList<ConstraintHelper> f1034d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected androidx.constraintlayout.core.widgets.d f1035e;
    private int f;
    private int g;
    private int h;
    private int i;
    protected boolean j;
    private int k;
    private androidx.constraintlayout.widget.b l;
    protected androidx.constraintlayout.widget.a m;
    private int n;
    private HashMap<String, Integer> o;
    private int p;
    private int q;
    int r;
    int s;
    int t;
    int u;
    private SparseArray<ConstraintWidget> v;
    c w;
    private int x;
    private int y;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1036a;

        static {
            int[] iArr = new int[ConstraintWidget.DimensionBehaviour.values().length];
            f1036a = iArr;
            try {
                iArr[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1036a[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1036a[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1036a[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class c implements b.InterfaceC0016b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ConstraintLayout f1043a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1044b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1045c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1046d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1047e;
        int f;
        int g;

        public c(ConstraintLayout constraintLayout) {
            this.f1043a = constraintLayout;
        }

        private boolean d(int i, int i2, int i3) {
            if (i == i2) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i);
            View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i3 == size;
            }
            return false;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.b.InterfaceC0016b
        public final void a() {
            int childCount = this.f1043a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f1043a.getChildAt(i);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).b(this.f1043a);
                }
            }
            int size = this.f1043a.f1034d.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    ((ConstraintHelper) this.f1043a.f1034d.get(i2)).s(this.f1043a);
                }
            }
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.b.InterfaceC0016b
        @SuppressLint({"WrongCall"})
        public final void b(ConstraintWidget constraintWidget, b.a aVar) {
            int iMakeMeasureSpec;
            int iMakeMeasureSpec2;
            int baseline;
            int iMax;
            int i;
            int measuredHeight;
            int i2;
            if (constraintWidget == null) {
                return;
            }
            if (constraintWidget.X() == 8 && !constraintWidget.l0()) {
                aVar.h = 0;
                aVar.i = 0;
                aVar.j = 0;
                return;
            }
            if (constraintWidget.M() == null) {
                return;
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f849d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = aVar.f850e;
            int i3 = aVar.f;
            int i4 = aVar.g;
            int i5 = this.f1044b + this.f1045c;
            int i6 = this.f1046d;
            View view = (View) constraintWidget.u();
            int[] iArr = a.f1036a;
            int i7 = iArr[dimensionBehaviour.ordinal()];
            if (i7 == 1) {
                iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            } else if (i7 == 2) {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i6, -2);
            } else if (i7 == 3) {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i6 + constraintWidget.D(), -1);
            } else if (i7 != 4) {
                iMakeMeasureSpec = 0;
            } else {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i6, -2);
                boolean z = constraintWidget.x == 1;
                int i8 = aVar.m;
                if (i8 == b.a.f847b || i8 == b.a.f848c) {
                    if (aVar.m == b.a.f848c || !z || (z && (view.getMeasuredHeight() == constraintWidget.z())) || (view instanceof Placeholder) || constraintWidget.p0()) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(constraintWidget.Y(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    }
                }
            }
            int i9 = iArr[dimensionBehaviour2.ordinal()];
            if (i9 == 1) {
                iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            } else if (i9 == 2) {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i5, -2);
            } else if (i9 == 3) {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i5 + constraintWidget.W(), -1);
            } else if (i9 != 4) {
                iMakeMeasureSpec2 = 0;
            } else {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i5, -2);
                boolean z2 = constraintWidget.y == 1;
                int i10 = aVar.m;
                if (i10 == b.a.f847b || i10 == b.a.f848c) {
                    if (aVar.m == b.a.f848c || !z2 || (z2 && (view.getMeasuredWidth() == constraintWidget.Y())) || (view instanceof Placeholder) || constraintWidget.q0()) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(constraintWidget.z(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    }
                }
            }
            androidx.constraintlayout.core.widgets.d dVar = (androidx.constraintlayout.core.widgets.d) constraintWidget.M();
            if (dVar != null && i.b(ConstraintLayout.this.k, 256) && view.getMeasuredWidth() == constraintWidget.Y() && view.getMeasuredWidth() < dVar.Y() && view.getMeasuredHeight() == constraintWidget.z() && view.getMeasuredHeight() < dVar.z() && view.getBaseline() == constraintWidget.r() && !constraintWidget.o0()) {
                if (d(constraintWidget.E(), iMakeMeasureSpec, constraintWidget.Y()) && d(constraintWidget.F(), iMakeMeasureSpec2, constraintWidget.z())) {
                    aVar.h = constraintWidget.Y();
                    aVar.i = constraintWidget.z();
                    aVar.j = constraintWidget.r();
                    return;
                }
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            boolean z3 = dimensionBehaviour == dimensionBehaviour3;
            boolean z4 = dimensionBehaviour2 == dimensionBehaviour3;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            boolean z5 = dimensionBehaviour2 == dimensionBehaviour4 || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED;
            boolean z6 = dimensionBehaviour == dimensionBehaviour4 || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED;
            boolean z7 = z3 && constraintWidget.g0 > 0.0f;
            boolean z8 = z4 && constraintWidget.g0 > 0.0f;
            if (view == null) {
                return;
            }
            b bVar = (b) view.getLayoutParams();
            int i11 = aVar.m;
            if (i11 != b.a.f847b && i11 != b.a.f848c && z3 && constraintWidget.x == 0 && z4 && constraintWidget.y == 0) {
                i2 = -1;
                measuredHeight = 0;
                baseline = 0;
                iMax = 0;
            } else {
                if ((view instanceof VirtualLayout) && (constraintWidget instanceof k)) {
                    ((VirtualLayout) view).x((k) constraintWidget, iMakeMeasureSpec, iMakeMeasureSpec2);
                } else {
                    view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                }
                constraintWidget.Y0(iMakeMeasureSpec, iMakeMeasureSpec2);
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                baseline = view.getBaseline();
                int i12 = constraintWidget.A;
                iMax = i12 > 0 ? Math.max(i12, measuredWidth) : measuredWidth;
                int i13 = constraintWidget.B;
                if (i13 > 0) {
                    iMax = Math.min(i13, iMax);
                }
                int i14 = constraintWidget.D;
                if (i14 > 0) {
                    measuredHeight = Math.max(i14, measuredHeight2);
                    i = iMakeMeasureSpec;
                } else {
                    i = iMakeMeasureSpec;
                    measuredHeight = measuredHeight2;
                }
                int i15 = constraintWidget.E;
                if (i15 > 0) {
                    measuredHeight = Math.min(i15, measuredHeight);
                }
                if (!i.b(ConstraintLayout.this.k, 1)) {
                    if (z7 && z5) {
                        iMax = (int) ((measuredHeight * constraintWidget.g0) + 0.5f);
                    } else if (z8 && z6) {
                        measuredHeight = (int) ((iMax / constraintWidget.g0) + 0.5f);
                    }
                }
                if (measuredWidth != iMax || measuredHeight2 != measuredHeight) {
                    int iMakeMeasureSpec3 = measuredWidth != iMax ? View.MeasureSpec.makeMeasureSpec(iMax, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : i;
                    if (measuredHeight2 != measuredHeight) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    }
                    view.measure(iMakeMeasureSpec3, iMakeMeasureSpec2);
                    constraintWidget.Y0(iMakeMeasureSpec3, iMakeMeasureSpec2);
                    iMax = view.getMeasuredWidth();
                    measuredHeight = view.getMeasuredHeight();
                    baseline = view.getBaseline();
                }
                i2 = -1;
            }
            boolean z9 = baseline != i2;
            aVar.l = (iMax == aVar.f && measuredHeight == aVar.g) ? false : true;
            if (bVar.g0) {
                z9 = true;
            }
            if (z9 && baseline != -1 && constraintWidget.r() != baseline) {
                aVar.l = true;
            }
            aVar.h = iMax;
            aVar.i = measuredHeight;
            aVar.k = z9;
            aVar.j = baseline;
        }

        public void c(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f1044b = i3;
            this.f1045c = i4;
            this.f1046d = i5;
            this.f1047e = i6;
            this.f = i;
            this.g = i2;
        }
    }

    public ConstraintLayout(Context context) {
        super(context);
        this.f1033c = new SparseArray<>();
        this.f1034d = new ArrayList<>(4);
        this.f1035e = new androidx.constraintlayout.core.widgets.d();
        this.f = 0;
        this.g = 0;
        this.h = Integer.MAX_VALUE;
        this.i = Integer.MAX_VALUE;
        this.j = true;
        this.k = 257;
        this.l = null;
        this.m = null;
        this.n = -1;
        this.o = new HashMap<>();
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = 0;
        this.u = 0;
        this.v = new SparseArray<>();
        this.w = new c(this);
        this.x = 0;
        this.y = 0;
        p(null, 0, 0);
    }

    private final ConstraintWidget g(int i) {
        if (i == 0) {
            return this.f1035e;
        }
        View viewFindViewById = this.f1033c.get(i);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.f1035e;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((b) viewFindViewById.getLayoutParams()).v0;
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int iMax2 = Build.VERSION.SDK_INT >= 17 ? Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart()) : 0;
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static f getSharedValues() {
        if (f1032b == null) {
            f1032b = new f();
        }
        return f1032b;
    }

    private void p(AttributeSet attributeSet, int i, int i2) {
        this.f1035e.E0(this);
        this.f1035e.Y1(this.w);
        this.f1033c.put(getId(), this);
        this.l = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.x1, i, i2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == e.H1) {
                    this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                } else if (index == e.I1) {
                    this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                } else if (index == e.F1) {
                    this.h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.h);
                } else if (index == e.G1) {
                    this.i = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.i);
                } else if (index == e.v3) {
                    this.k = typedArrayObtainStyledAttributes.getInt(index, this.k);
                } else if (index == e.q2) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            s(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.m = null;
                        }
                    }
                } else if (index == e.U1) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.l = bVar;
                        bVar.B(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.l = null;
                    }
                    this.n = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1035e.Z1(this.k);
    }

    private void r() {
        this.j = true;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = 0;
        this.u = 0;
    }

    private void v() {
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget constraintWidgetL = l(getChildAt(i));
            if (constraintWidgetL != null) {
                constraintWidgetL.v0();
            }
        }
        if (zIsInEditMode) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    w(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    g(childAt.getId()).F0(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.n != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt2 = getChildAt(i3);
                if (childAt2.getId() == this.n && (childAt2 instanceof Constraints)) {
                    this.l = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        androidx.constraintlayout.widget.b bVar = this.l;
        if (bVar != null) {
            bVar.k(this, true);
        }
        this.f1035e.x1();
        int size = this.f1034d.size();
        if (size > 0) {
            for (int i4 = 0; i4 < size; i4++) {
                this.f1034d.get(i4).v(this);
            }
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt3 = getChildAt(i5);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).c(this);
            }
        }
        this.v.clear();
        this.v.put(0, this.f1035e);
        this.v.put(getId(), this.f1035e);
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt4 = getChildAt(i6);
            this.v.put(childAt4.getId(), l(childAt4));
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt5 = getChildAt(i7);
            ConstraintWidget constraintWidgetL2 = l(childAt5);
            if (constraintWidgetL2 != null) {
                b bVar2 = (b) childAt5.getLayoutParams();
                this.f1035e.c(constraintWidgetL2);
                c(zIsInEditMode, childAt5, constraintWidgetL2, bVar2, this.v);
            }
        }
    }

    private void y(ConstraintWidget constraintWidget, b bVar, SparseArray<ConstraintWidget> sparseArray, int i, ConstraintAnchor.Type type) {
        View view = this.f1033c.get(i);
        ConstraintWidget constraintWidget2 = sparseArray.get(i);
        if (constraintWidget2 == null || view == null || !(view.getLayoutParams() instanceof b)) {
            return;
        }
        bVar.g0 = true;
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.BASELINE;
        if (type == type2) {
            b bVar2 = (b) view.getLayoutParams();
            bVar2.g0 = true;
            bVar2.v0.N0(true);
        }
        constraintWidget.q(type2).b(constraintWidget2.q(type), bVar.D, bVar.C, true);
        constraintWidget.N0(true);
        constraintWidget.q(ConstraintAnchor.Type.TOP).q();
        constraintWidget.q(ConstraintAnchor.Type.BOTTOM).q();
    }

    private boolean z() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            v();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(boolean z, View view, ConstraintWidget constraintWidget, b bVar, SparseArray<ConstraintWidget> sparseArray) {
        int i;
        float f;
        int i2;
        int i3;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        ConstraintWidget constraintWidget5;
        int i4;
        bVar.c();
        bVar.w0 = false;
        constraintWidget.m1(view.getVisibility());
        if (bVar.j0) {
            constraintWidget.W0(true);
            constraintWidget.m1(8);
        }
        constraintWidget.E0(view);
        if (view instanceof ConstraintHelper) {
            ((ConstraintHelper) view).q(constraintWidget, this.f1035e.S1());
        }
        if (bVar.h0) {
            androidx.constraintlayout.core.widgets.f fVar = (androidx.constraintlayout.core.widgets.f) constraintWidget;
            int i5 = bVar.s0;
            int i6 = bVar.t0;
            float f2 = bVar.u0;
            if (Build.VERSION.SDK_INT < 17) {
                i5 = bVar.f1037a;
                i6 = bVar.f1038b;
                f2 = bVar.f1039c;
            }
            if (f2 != -1.0f) {
                fVar.C1(f2);
                return;
            } else if (i5 != -1) {
                fVar.A1(i5);
                return;
            } else {
                if (i6 != -1) {
                    fVar.B1(i6);
                    return;
                }
                return;
            }
        }
        int i7 = bVar.l0;
        int i8 = bVar.m0;
        int i9 = bVar.n0;
        int i10 = bVar.o0;
        int i11 = bVar.p0;
        int i12 = bVar.q0;
        float f3 = bVar.r0;
        if (Build.VERSION.SDK_INT < 17) {
            i7 = bVar.f1041e;
            int i13 = bVar.f;
            int i14 = bVar.g;
            int i15 = bVar.h;
            int i16 = bVar.w;
            int i17 = bVar.y;
            float f4 = bVar.G;
            if (i7 == -1 && i13 == -1) {
                int i18 = bVar.t;
                if (i18 != -1) {
                    i7 = i18;
                } else {
                    int i19 = bVar.s;
                    if (i19 != -1) {
                        i13 = i19;
                    }
                }
            }
            if (i14 == -1 && i15 == -1) {
                i2 = bVar.u;
                if (i2 == -1) {
                    int i20 = bVar.v;
                    if (i20 != -1) {
                        i = i17;
                        f = f4;
                        i11 = i16;
                        i3 = i20;
                        i8 = i13;
                        i2 = i14;
                    }
                    i2 = i14;
                    i = i17;
                    f = f4;
                    i11 = i16;
                    i3 = i15;
                    i8 = i13;
                } else {
                    i = i17;
                    f = f4;
                    i11 = i16;
                    i3 = i15;
                    i8 = i13;
                }
            } else {
                i2 = i14;
                i = i17;
                f = f4;
                i11 = i16;
                i3 = i15;
                i8 = i13;
            }
        } else {
            i = i12;
            f = f3;
            i2 = i9;
            i3 = i10;
        }
        int i21 = bVar.p;
        if (i21 != -1) {
            ConstraintWidget constraintWidget6 = sparseArray.get(i21);
            if (constraintWidget6 != null) {
                constraintWidget.m(constraintWidget6, bVar.r, bVar.q);
            }
        } else {
            if (i7 != -1) {
                ConstraintWidget constraintWidget7 = sparseArray.get(i7);
                if (constraintWidget7 != null) {
                    ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                    constraintWidget.g0(type, constraintWidget7, type, ((ViewGroup.MarginLayoutParams) bVar).leftMargin, i11);
                }
            } else if (i8 != -1 && (constraintWidget2 = sparseArray.get(i8)) != null) {
                constraintWidget.g0(ConstraintAnchor.Type.LEFT, constraintWidget2, ConstraintAnchor.Type.RIGHT, ((ViewGroup.MarginLayoutParams) bVar).leftMargin, i11);
            }
            if (i2 != -1) {
                ConstraintWidget constraintWidget8 = sparseArray.get(i2);
                if (constraintWidget8 != null) {
                    constraintWidget.g0(ConstraintAnchor.Type.RIGHT, constraintWidget8, ConstraintAnchor.Type.LEFT, ((ViewGroup.MarginLayoutParams) bVar).rightMargin, i);
                }
            } else if (i3 != -1 && (constraintWidget3 = sparseArray.get(i3)) != null) {
                ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                constraintWidget.g0(type2, constraintWidget3, type2, ((ViewGroup.MarginLayoutParams) bVar).rightMargin, i);
            }
            int i22 = bVar.i;
            if (i22 != -1) {
                ConstraintWidget constraintWidget9 = sparseArray.get(i22);
                if (constraintWidget9 != null) {
                    ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                    constraintWidget.g0(type3, constraintWidget9, type3, ((ViewGroup.MarginLayoutParams) bVar).topMargin, bVar.x);
                }
            } else {
                int i23 = bVar.j;
                if (i23 != -1 && (constraintWidget4 = sparseArray.get(i23)) != null) {
                    constraintWidget.g0(ConstraintAnchor.Type.TOP, constraintWidget4, ConstraintAnchor.Type.BOTTOM, ((ViewGroup.MarginLayoutParams) bVar).topMargin, bVar.x);
                }
            }
            int i24 = bVar.k;
            if (i24 != -1) {
                ConstraintWidget constraintWidget10 = sparseArray.get(i24);
                if (constraintWidget10 != null) {
                    constraintWidget.g0(ConstraintAnchor.Type.BOTTOM, constraintWidget10, ConstraintAnchor.Type.TOP, ((ViewGroup.MarginLayoutParams) bVar).bottomMargin, bVar.z);
                }
            } else {
                int i25 = bVar.l;
                if (i25 != -1 && (constraintWidget5 = sparseArray.get(i25)) != null) {
                    ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
                    constraintWidget.g0(type4, constraintWidget5, type4, ((ViewGroup.MarginLayoutParams) bVar).bottomMargin, bVar.z);
                }
            }
            int i26 = bVar.m;
            if (i26 != -1) {
                y(constraintWidget, bVar, sparseArray, i26, ConstraintAnchor.Type.BASELINE);
            } else {
                int i27 = bVar.n;
                if (i27 != -1) {
                    y(constraintWidget, bVar, sparseArray, i27, ConstraintAnchor.Type.TOP);
                } else {
                    int i28 = bVar.o;
                    if (i28 != -1) {
                        y(constraintWidget, bVar, sparseArray, i28, ConstraintAnchor.Type.BOTTOM);
                    }
                }
            }
            if (f >= 0.0f) {
                constraintWidget.P0(f);
            }
            float f5 = bVar.H;
            if (f5 >= 0.0f) {
                constraintWidget.g1(f5);
            }
        }
        if (z && ((i4 = bVar.X) != -1 || bVar.Y != -1)) {
            constraintWidget.e1(i4, bVar.Y);
        }
        if (bVar.e0) {
            constraintWidget.S0(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.n1(((ViewGroup.MarginLayoutParams) bVar).width);
            if (((ViewGroup.MarginLayoutParams) bVar).width == -2) {
                constraintWidget.S0(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) bVar).width == -1) {
            if (bVar.a0) {
                constraintWidget.S0(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            } else {
                constraintWidget.S0(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
            }
            constraintWidget.q(ConstraintAnchor.Type.LEFT).g = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            constraintWidget.q(ConstraintAnchor.Type.RIGHT).g = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        } else {
            constraintWidget.S0(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            constraintWidget.n1(0);
        }
        if (bVar.f0) {
            constraintWidget.j1(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.O0(((ViewGroup.MarginLayoutParams) bVar).height);
            if (((ViewGroup.MarginLayoutParams) bVar).height == -2) {
                constraintWidget.j1(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) bVar).height == -1) {
            if (bVar.b0) {
                constraintWidget.j1(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            } else {
                constraintWidget.j1(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
            }
            constraintWidget.q(ConstraintAnchor.Type.TOP).g = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            constraintWidget.q(ConstraintAnchor.Type.BOTTOM).g = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        } else {
            constraintWidget.j1(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            constraintWidget.O0(0);
        }
        constraintWidget.G0(bVar.I);
        constraintWidget.U0(bVar.L);
        constraintWidget.l1(bVar.M);
        constraintWidget.Q0(bVar.N);
        constraintWidget.h1(bVar.O);
        constraintWidget.o1(bVar.d0);
        constraintWidget.T0(bVar.P, bVar.R, bVar.T, bVar.V);
        constraintWidget.k1(bVar.Q, bVar.S, bVar.U, bVar.W);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<ConstraintHelper> arrayList = this.f1034d;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                this.f1034d.get(i).t(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    if (strArrSplit.length == 4) {
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        int i5 = Integer.parseInt(strArrSplit[2]);
                        int i6 = (int) ((i3 / 1080.0f) * width);
                        int i7 = (int) ((i4 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i6;
                        float f2 = i7;
                        float f3 = i6 + ((int) ((i5 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float f4 = i7 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f3, f2, f3, f4, paint);
                        canvas.drawLine(f3, f4, f, f4, paint);
                        canvas.drawLine(f, f4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, f4, paint);
                        canvas.drawLine(f, f4, f3, f2, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    public Object f(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.o;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.o.get(str);
    }

    @Override // android.view.View
    public void forceLayout() {
        r();
        super.forceLayout();
    }

    public int getMaxHeight() {
        return this.i;
    }

    public int getMaxWidth() {
        return this.h;
    }

    public int getMinHeight() {
        return this.g;
    }

    public int getMinWidth() {
        return this.f;
    }

    public int getOptimizationLevel() {
        return this.f1035e.M1();
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (this.f1035e.p == null) {
            int id2 = getId();
            if (id2 != -1) {
                this.f1035e.p = getContext().getResources().getResourceEntryName(id2);
            } else {
                this.f1035e.p = "parent";
            }
        }
        if (this.f1035e.v() == null) {
            androidx.constraintlayout.core.widgets.d dVar = this.f1035e;
            dVar.F0(dVar.p);
            Log.v("ConstraintLayout", " setDebugName " + this.f1035e.v());
        }
        for (ConstraintWidget constraintWidget : this.f1035e.u1()) {
            View view = (View) constraintWidget.u();
            if (view != null) {
                if (constraintWidget.p == null && (id = view.getId()) != -1) {
                    constraintWidget.p = getContext().getResources().getResourceEntryName(id);
                }
                if (constraintWidget.v() == null) {
                    constraintWidget.F0(constraintWidget.p);
                    Log.v("ConstraintLayout", " setDebugName " + constraintWidget.v());
                }
            }
        }
        this.f1035e.Q(sb);
        return sb.toString();
    }

    public View k(int i) {
        return this.f1033c.get(i);
    }

    public final ConstraintWidget l(View view) {
        if (view == this) {
            return this.f1035e;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof b) {
            return ((b) view.getLayoutParams()).v0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof b) {
            return ((b) view.getLayoutParams()).v0;
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            b bVar = (b) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = bVar.v0;
            if ((childAt.getVisibility() != 8 || bVar.h0 || bVar.i0 || bVar.k0 || zIsInEditMode) && !bVar.j0) {
                int iZ = constraintWidget.Z();
                int iA0 = constraintWidget.a0();
                int iY = constraintWidget.Y() + iZ;
                int iZ2 = constraintWidget.z() + iA0;
                childAt.layout(iZ, iA0, iY, iZ2);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iZ, iA0, iY, iZ2);
                }
            }
        }
        int size = this.f1034d.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                this.f1034d.get(i6).r(this);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.x == i) {
            int i3 = this.y;
        }
        if (!this.j) {
            int childCount = getChildCount();
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    break;
                }
                if (getChildAt(i4).isLayoutRequested()) {
                    this.j = true;
                    break;
                }
                i4++;
            }
        }
        boolean z = this.j;
        this.x = i;
        this.y = i2;
        this.f1035e.b2(q());
        if (this.j) {
            this.j = false;
            if (z()) {
                this.f1035e.d2();
            }
        }
        u(this.f1035e, this.k, i, i2);
        t(i, i2, this.f1035e.Y(), this.f1035e.z(), this.f1035e.T1(), this.f1035e.R1());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget constraintWidgetL = l(view);
        if ((view instanceof Guideline) && !(constraintWidgetL instanceof androidx.constraintlayout.core.widgets.f)) {
            b bVar = (b) view.getLayoutParams();
            androidx.constraintlayout.core.widgets.f fVar = new androidx.constraintlayout.core.widgets.f();
            bVar.v0 = fVar;
            bVar.h0 = true;
            fVar.D1(bVar.Z);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.w();
            ((b) view.getLayoutParams()).i0 = true;
            if (!this.f1034d.contains(constraintHelper)) {
                this.f1034d.add(constraintHelper);
            }
        }
        this.f1033c.put(view.getId(), view);
        this.j = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f1033c.remove(view.getId());
        this.f1035e.w1(l(view));
        this.f1034d.remove(view);
        this.j = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean q() {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
        }
        return false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        r();
        super.requestLayout();
    }

    protected void s(int i) {
        this.m = new androidx.constraintlayout.widget.a(getContext(), this, i);
    }

    public void setConstraintSet(androidx.constraintlayout.widget.b bVar) {
        this.l = bVar;
    }

    @Override // android.view.View
    public void setId(int i) {
        this.f1033c.remove(getId());
        super.setId(i);
        this.f1033c.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.i) {
            return;
        }
        this.i = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.h) {
            return;
        }
        this.h = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.g) {
            return;
        }
        this.g = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.f) {
            return;
        }
        this.f = i;
        requestLayout();
    }

    public void setOnConstraintsChanged(androidx.constraintlayout.widget.c cVar) {
        androidx.constraintlayout.widget.a aVar = this.m;
        if (aVar != null) {
            aVar.c(cVar);
        }
    }

    public void setOptimizationLevel(int i) {
        this.k = i;
        this.f1035e.Z1(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void t(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        c cVar = this.w;
        int i5 = cVar.f1047e;
        int iResolveSizeAndState = ViewGroup.resolveSizeAndState(i3 + cVar.f1046d, i, 0);
        int iResolveSizeAndState2 = ViewGroup.resolveSizeAndState(i4 + i5, i2, 0) & 16777215;
        int iMin = Math.min(this.h, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.i, iResolveSizeAndState2);
        if (z) {
            iMin |= 16777216;
        }
        if (z2) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.p = iMin;
        this.q = iMin2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void u(androidx.constraintlayout.core.widgets.d dVar, int i, int i2, int i3) {
        int iMax;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int iMax2 = Math.max(0, getPaddingTop());
        int iMax3 = Math.max(0, getPaddingBottom());
        int i4 = iMax2 + iMax3;
        int paddingWidth = getPaddingWidth();
        this.w.c(i2, i3, iMax2, iMax3, paddingWidth, i4);
        if (Build.VERSION.SDK_INT >= 17) {
            int iMax4 = Math.max(0, getPaddingStart());
            int iMax5 = Math.max(0, getPaddingEnd());
            if (iMax4 <= 0 && iMax5 <= 0) {
                iMax4 = Math.max(0, getPaddingLeft());
            } else if (q()) {
                iMax4 = iMax5;
            }
            iMax = iMax4;
        } else {
            iMax = Math.max(0, getPaddingLeft());
        }
        int i5 = size - paddingWidth;
        int i6 = size2 - i4;
        x(dVar, mode, i5, mode2, i6);
        dVar.U1(i, mode, i5, mode2, i6, this.p, this.q, iMax, iMax2);
    }

    public void w(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.o == null) {
                this.o = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.o.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e A[PHI: r2
  0x003e: PHI (r2v4 androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour) = 
  (r2v3 androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour)
  (r2v0 androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour)
 binds: [B:21:0x004a, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x(androidx.constraintlayout.core.widgets.d r8, int r9, int r10, int r11, int r12) {
        /*
            r7 = this;
            androidx.constraintlayout.widget.ConstraintLayout$c r0 = r7.w
            int r1 = r0.f1047e
            int r0 = r0.f1046d
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
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
            int r9 = r7.h
            int r9 = r9 - r0
            int r10 = java.lang.Math.min(r9, r10)
            r9 = r2
            goto L38
        L23:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L18
            int r10 = r7.f
            int r10 = java.lang.Math.max(r6, r10)
            goto L38
        L2e:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L38
            int r10 = r7.f
            int r10 = java.lang.Math.max(r6, r10)
        L38:
            if (r11 == r5) goto L53
            if (r11 == 0) goto L48
            if (r11 == r4) goto L40
        L3e:
            r12 = r6
            goto L5d
        L40:
            int r11 = r7.i
            int r11 = r11 - r1
            int r12 = java.lang.Math.min(r11, r12)
            goto L5d
        L48:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L3e
            int r11 = r7.g
            int r12 = java.lang.Math.max(r6, r11)
            goto L5d
        L53:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L5d
            int r11 = r7.g
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
            int r11 = r7.h
            int r11 = r11 - r0
            r8.a1(r11)
            int r11 = r7.i
            int r11 = r11 - r1
            r8.Z0(r11)
            r8.d1(r6)
            r8.c1(r6)
            r8.S0(r9)
            r8.n1(r10)
            r8.j1(r2)
            r8.O0(r12)
            int r9 = r7.f
            int r9 = r9 - r0
            r8.d1(r9)
            int r9 = r7.g
            int r9 = r9 - r1
            r8.c1(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.x(androidx.constraintlayout.core.widgets.d, int, int, int, int):void");
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1033c = new SparseArray<>();
        this.f1034d = new ArrayList<>(4);
        this.f1035e = new androidx.constraintlayout.core.widgets.d();
        this.f = 0;
        this.g = 0;
        this.h = Integer.MAX_VALUE;
        this.i = Integer.MAX_VALUE;
        this.j = true;
        this.k = 257;
        this.l = null;
        this.m = null;
        this.n = -1;
        this.o = new HashMap<>();
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = 0;
        this.u = 0;
        this.v = new SparseArray<>();
        this.w = new c(this);
        this.x = 0;
        this.y = 0;
        p(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1033c = new SparseArray<>();
        this.f1034d = new ArrayList<>(4);
        this.f1035e = new androidx.constraintlayout.core.widgets.d();
        this.f = 0;
        this.g = 0;
        this.h = Integer.MAX_VALUE;
        this.i = Integer.MAX_VALUE;
        this.j = true;
        this.k = 257;
        this.l = null;
        this.m = null;
        this.n = -1;
        this.o = new HashMap<>();
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = 0;
        this.u = 0;
        this.v = new SparseArray<>();
        this.w = new c(this);
        this.x = 0;
        this.y = 0;
        p(attributeSet, i, 0);
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
        public int f1037a;
        public boolean a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1038b;
        public boolean b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1039c;
        public String c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1040d;
        public int d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1041e;
        boolean e0;
        public int f;
        boolean f0;
        public int g;
        boolean g0;
        public int h;
        boolean h0;
        public int i;
        boolean i0;
        public int j;
        boolean j0;
        public int k;
        boolean k0;
        public int l;
        int l0;
        public int m;
        int m0;
        public int n;
        int n0;
        public int o;
        int o0;
        public int p;
        int p0;
        public int q;
        int q0;
        public float r;
        float r0;
        public int s;
        int s0;
        public int t;
        int t0;
        public int u;
        float u0;
        public int v;
        ConstraintWidget v0;
        public int w;
        public boolean w0;
        public int x;
        public int y;
        public int z;

        private static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f1042a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f1042a = sparseIntArray;
                sparseIntArray.append(e.g3, 64);
                sparseIntArray.append(e.J2, 65);
                sparseIntArray.append(e.S2, 8);
                sparseIntArray.append(e.T2, 9);
                sparseIntArray.append(e.V2, 10);
                sparseIntArray.append(e.W2, 11);
                sparseIntArray.append(e.c3, 12);
                sparseIntArray.append(e.b3, 13);
                sparseIntArray.append(e.z2, 14);
                sparseIntArray.append(e.y2, 15);
                sparseIntArray.append(e.u2, 16);
                sparseIntArray.append(e.w2, 52);
                sparseIntArray.append(e.v2, 53);
                sparseIntArray.append(e.A2, 2);
                sparseIntArray.append(e.C2, 3);
                sparseIntArray.append(e.B2, 4);
                sparseIntArray.append(e.l3, 49);
                sparseIntArray.append(e.m3, 50);
                sparseIntArray.append(e.G2, 5);
                sparseIntArray.append(e.H2, 6);
                sparseIntArray.append(e.I2, 7);
                sparseIntArray.append(e.p2, 67);
                sparseIntArray.append(e.y1, 1);
                sparseIntArray.append(e.X2, 17);
                sparseIntArray.append(e.Y2, 18);
                sparseIntArray.append(e.F2, 19);
                sparseIntArray.append(e.E2, 20);
                sparseIntArray.append(e.q3, 21);
                sparseIntArray.append(e.t3, 22);
                sparseIntArray.append(e.r3, 23);
                sparseIntArray.append(e.o3, 24);
                sparseIntArray.append(e.s3, 25);
                sparseIntArray.append(e.p3, 26);
                sparseIntArray.append(e.n3, 55);
                sparseIntArray.append(e.u3, 54);
                sparseIntArray.append(e.O2, 29);
                sparseIntArray.append(e.d3, 30);
                sparseIntArray.append(e.D2, 44);
                sparseIntArray.append(e.Q2, 45);
                sparseIntArray.append(e.f3, 46);
                sparseIntArray.append(e.P2, 47);
                sparseIntArray.append(e.e3, 48);
                sparseIntArray.append(e.s2, 27);
                sparseIntArray.append(e.r2, 28);
                sparseIntArray.append(e.h3, 31);
                sparseIntArray.append(e.K2, 32);
                sparseIntArray.append(e.j3, 33);
                sparseIntArray.append(e.i3, 34);
                sparseIntArray.append(e.k3, 35);
                sparseIntArray.append(e.M2, 36);
                SparseIntArray sparseIntArray2 = f1042a;
                sparseIntArray2.append(e.L2, 37);
                sparseIntArray2.append(e.N2, 38);
                sparseIntArray2.append(e.R2, 39);
                sparseIntArray2.append(e.a3, 40);
                sparseIntArray2.append(e.U2, 41);
                sparseIntArray2.append(e.x2, 42);
                sparseIntArray2.append(e.t2, 43);
                sparseIntArray2.append(e.Z2, 51);
                sparseIntArray2.append(e.w3, 66);
            }
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1037a = -1;
            this.f1038b = -1;
            this.f1039c = -1.0f;
            this.f1040d = true;
            this.f1041e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
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
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = true;
            this.f0 = true;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new ConstraintWidget();
            this.w0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.x1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = a.f1042a.get(index);
                switch (i2) {
                    case 1:
                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        this.p = resourceId;
                        if (resourceId == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.q);
                        break;
                    case 4:
                        float f = typedArrayObtainStyledAttributes.getFloat(index, this.r) % 360.0f;
                        this.r = f;
                        if (f < 0.0f) {
                            this.r = (360.0f - f) % 360.0f;
                        }
                        break;
                    case 5:
                        this.f1037a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1037a);
                        break;
                    case 6:
                        this.f1038b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1038b);
                        break;
                    case 7:
                        this.f1039c = typedArrayObtainStyledAttributes.getFloat(index, this.f1039c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1041e);
                        this.f1041e = resourceId2;
                        if (resourceId2 == -1) {
                            this.f1041e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f);
                        this.f = resourceId3;
                        if (resourceId3 == -1) {
                            this.f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                        this.g = resourceId4;
                        if (resourceId4 == -1) {
                            this.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                        this.h = resourceId5;
                        if (resourceId5 == -1) {
                            this.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.i);
                        this.i = resourceId6;
                        if (resourceId6 == -1) {
                            this.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        this.j = resourceId7;
                        if (resourceId7 == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        this.k = resourceId8;
                        if (resourceId8 == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        this.l = resourceId9;
                        if (resourceId9 == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        this.m = resourceId10;
                        if (resourceId10 == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
                        this.s = resourceId11;
                        if (resourceId11 == -1) {
                            this.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.t);
                        this.t = resourceId12;
                        if (resourceId12 == -1) {
                            this.t = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.u);
                        this.u = resourceId13;
                        if (resourceId13 == -1) {
                            this.u = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.v);
                        this.v = resourceId14;
                        if (resourceId14 == -1) {
                            this.v = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case 22:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 23:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 24:
                        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.z);
                        break;
                    case 25:
                        this.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.A);
                        break;
                    case 26:
                        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.B);
                        break;
                    case 27:
                        this.a0 = typedArrayObtainStyledAttributes.getBoolean(index, this.a0);
                        break;
                    case 28:
                        this.b0 = typedArrayObtainStyledAttributes.getBoolean(index, this.b0);
                        break;
                    case 29:
                        this.G = typedArrayObtainStyledAttributes.getFloat(index, this.G);
                        break;
                    case 30:
                        this.H = typedArrayObtainStyledAttributes.getFloat(index, this.H);
                        break;
                    case 31:
                        int i3 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.P = i3;
                        if (i3 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.Q = i4;
                        if (i4 == 1) {
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
                        switch (i2) {
                            case 44:
                                androidx.constraintlayout.widget.b.G(this, typedArrayObtainStyledAttributes.getString(index));
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
                                this.c0 = typedArrayObtainStyledAttributes.getString(index);
                                break;
                            case 52:
                                int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, this.n);
                                this.n = resourceId15;
                                if (resourceId15 == -1) {
                                    this.n = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 53:
                                int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, this.o);
                                this.o = resourceId16;
                                if (resourceId16 == -1) {
                                    this.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 54:
                                this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                                break;
                            case 55:
                                this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            default:
                                switch (i2) {
                                    case 64:
                                        androidx.constraintlayout.widget.b.E(this, typedArrayObtainStyledAttributes, index, 0);
                                        this.E = true;
                                        break;
                                    case 65:
                                        androidx.constraintlayout.widget.b.E(this, typedArrayObtainStyledAttributes, index, 1);
                                        this.F = true;
                                        break;
                                    case 66:
                                        this.d0 = typedArrayObtainStyledAttributes.getInt(index, this.d0);
                                        break;
                                    case 67:
                                        this.f1040d = typedArrayObtainStyledAttributes.getBoolean(index, this.f1040d);
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            c();
        }

        public String a() {
            return this.c0;
        }

        public ConstraintWidget b() {
            return this.v0;
        }

        public void c() {
            this.h0 = false;
            this.e0 = true;
            this.f0 = true;
            int i = ((ViewGroup.MarginLayoutParams) this).width;
            if (i == -2 && this.a0) {
                this.e0 = false;
                if (this.P == 0) {
                    this.P = 1;
                }
            }
            int i2 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i2 == -2 && this.b0) {
                this.f0 = false;
                if (this.Q == 0) {
                    this.Q = 1;
                }
            }
            if (i == 0 || i == -1) {
                this.e0 = false;
                if (i == 0 && this.P == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.a0 = true;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.f0 = false;
                if (i2 == 0 && this.Q == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.b0 = true;
                }
            }
            if (this.f1039c == -1.0f && this.f1037a == -1 && this.f1038b == -1) {
                return;
            }
            this.h0 = true;
            this.e0 = true;
            this.f0 = true;
            if (!(this.v0 instanceof androidx.constraintlayout.core.widgets.f)) {
                this.v0 = new androidx.constraintlayout.core.widgets.f();
            }
            ((androidx.constraintlayout.core.widgets.f) this.v0).D1(this.Z);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @android.annotation.TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void resolveLayoutDirection(int r11) {
            /*
                Method dump skipped, instruction units count: 269
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.b.resolveLayoutDirection(int):void");
        }

        public b(int i, int i2) {
            super(i, i2);
            this.f1037a = -1;
            this.f1038b = -1;
            this.f1039c = -1.0f;
            this.f1040d = true;
            this.f1041e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
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
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = true;
            this.f0 = true;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new ConstraintWidget();
            this.w0 = false;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1037a = -1;
            this.f1038b = -1;
            this.f1039c = -1.0f;
            this.f1040d = true;
            this.f1041e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
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
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = true;
            this.f0 = true;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new ConstraintWidget();
            this.w0 = false;
        }
    }
}
