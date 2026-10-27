package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.LinearLayoutCompat;
import b.b.p.j.g;
import b.b.p.j.i;
import b.b.p.j.m;
import b.b.p.j.n;
import b.b.q.k0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements g.b, n {
    public g O3;
    public Context P3;
    public int Q3;
    public boolean R3;
    public ActionMenuPresenter S3;
    public m.a T3;
    public g.a U3;
    public boolean V3;
    public int W3;
    public int X3;
    public int Y3;
    public e Z3;

    public interface a {
        boolean a();

        boolean b();
    }

    public static class b implements m.a {
        @Override // b.b.p.j.m.a
        public void a(g gVar, boolean z) {
        }

        @Override // b.b.p.j.m.a
        public boolean b(g gVar) {
            return false;
        }
    }

    public static class c extends LinearLayoutCompat.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f169c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f170d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f171e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f172f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f173g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f174h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f169c = cVar.f169c;
        }

        public c(int i2, int i3) {
            super(i2, i3);
            this.f169c = false;
        }
    }

    public class d implements g.a {
        public d() {
        }

        @Override // b.b.p.j.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.Z3;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // b.b.p.j.g.a
        public void b(g gVar) {
            g.a aVar = ActionMenuView.this.U3;
            if (aVar != null) {
                aVar.b(gVar);
            }
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public static int J(View view, int i2, int i3, int i4, int i5) {
        c cVar = (c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i4) - i5, View.MeasureSpec.getMode(i4));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = actionMenuItemView != null && actionMenuItemView.f();
        int i6 = 2;
        if (i3 <= 0 || (z && i3 < 2)) {
            i6 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i3 * i2, Integer.MIN_VALUE), iMakeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i7 = measuredWidth / i2;
            if (measuredWidth % i2 != 0) {
                i7++;
            }
            if (!z || i7 >= 2) {
                i6 = i7;
            }
        }
        cVar.f172f = !cVar.f169c && z;
        cVar.f170d = i6;
        view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i6, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
        return i6;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f231b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (cVar.f231b <= 0) {
            cVar.f231b = 16;
        }
        return cVar;
    }

    public c D() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.f169c = true;
        return cVarGenerateDefaultLayoutParams;
    }

    public boolean E(int i2) {
        boolean zA = false;
        if (i2 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i2 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof a)) {
            zA = false | ((a) childAt).a();
        }
        return (i2 <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).b();
    }

    public boolean F() {
        ActionMenuPresenter actionMenuPresenter = this.S3;
        return actionMenuPresenter != null && actionMenuPresenter.C();
    }

    public boolean G() {
        ActionMenuPresenter actionMenuPresenter = this.S3;
        return actionMenuPresenter != null && actionMenuPresenter.E();
    }

    public boolean H() {
        ActionMenuPresenter actionMenuPresenter = this.S3;
        return actionMenuPresenter != null && actionMenuPresenter.F();
    }

    public boolean I() {
        return this.R3;
    }

    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v14 */
    public final void K(int i2, int i3) {
        int i4;
        int i5;
        boolean z;
        int i6;
        int i7;
        boolean z2;
        boolean z3;
        int i8;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
        int i9 = size - paddingLeft;
        int i10 = this.X3;
        int i11 = i9 / i10;
        int i12 = i9 % i10;
        if (i11 == 0) {
            setMeasuredDimension(i9, 0);
            return;
        }
        int i13 = i10 + (i12 / i11);
        int childCount = getChildCount();
        int iMax = 0;
        int i14 = 0;
        boolean z4 = false;
        int i15 = 0;
        int iMax2 = 0;
        int i16 = 0;
        long j = 0;
        while (i14 < childCount) {
            View childAt = getChildAt(i14);
            int i17 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z5 = childAt instanceof ActionMenuItemView;
                int i18 = i15 + 1;
                if (z5) {
                    int i19 = this.Y3;
                    i8 = i18;
                    r14 = 0;
                    childAt.setPadding(i19, 0, i19, 0);
                } else {
                    i8 = i18;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.f174h = r14;
                cVar.f171e = r14;
                cVar.f170d = r14;
                cVar.f172f = r14;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r14;
                cVar.f173g = z5 && ((ActionMenuItemView) childAt).f();
                int iJ = J(childAt, i13, cVar.f169c ? 1 : i11, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iJ);
                if (cVar.f172f) {
                    i16++;
                }
                if (cVar.f169c) {
                    z4 = true;
                }
                i11 -= iJ;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iJ == 1) {
                    j |= (long) (1 << i14);
                    iMax = iMax;
                }
                i15 = i8;
            }
            i14++;
            size2 = i17;
        }
        int i20 = size2;
        boolean z6 = z4 && i15 == 2;
        boolean z7 = false;
        while (i16 > 0 && i11 > 0) {
            int i21 = Integer.MAX_VALUE;
            int i22 = 0;
            int i23 = 0;
            long j2 = 0;
            while (i23 < childCount) {
                boolean z8 = z7;
                c cVar2 = (c) getChildAt(i23).getLayoutParams();
                int i24 = iMax;
                if (cVar2.f172f) {
                    int i25 = cVar2.f170d;
                    if (i25 < i21) {
                        j2 = 1 << i23;
                        i21 = i25;
                        i22 = 1;
                    } else if (i25 == i21) {
                        i22++;
                        j2 |= 1 << i23;
                    }
                }
                i23++;
                iMax = i24;
                z7 = z8;
            }
            z = z7;
            i6 = iMax;
            j |= j2;
            if (i22 > i11) {
                i4 = mode;
                i5 = i9;
                break;
            }
            int i26 = i21 + 1;
            int i27 = 0;
            while (i27 < childCount) {
                View childAt2 = getChildAt(i27);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i28 = i9;
                int i29 = mode;
                long j3 = 1 << i27;
                if ((j2 & j3) == 0) {
                    if (cVar3.f170d == i26) {
                        j |= j3;
                    }
                    z3 = z6;
                } else {
                    if (z6 && cVar3.f173g && i11 == 1) {
                        int i30 = this.Y3;
                        z3 = z6;
                        childAt2.setPadding(i30 + i13, 0, i30, 0);
                    } else {
                        z3 = z6;
                    }
                    cVar3.f170d++;
                    cVar3.f174h = true;
                    i11--;
                }
                i27++;
                mode = i29;
                i9 = i28;
                z6 = z3;
            }
            iMax = i6;
            z7 = true;
        }
        i4 = mode;
        i5 = i9;
        z = z7;
        i6 = iMax;
        boolean z9 = !z4 && i15 == 1;
        if (i11 <= 0 || j == 0 || (i11 >= i15 - 1 && !z9 && iMax2 <= 1)) {
            i7 = 0;
            z2 = z;
        } else {
            float fBitCount = Long.bitCount(j);
            if (z9) {
                i7 = 0;
            } else {
                i7 = 0;
                if ((j & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f173g) {
                    fBitCount -= 0.5f;
                }
                int i31 = childCount - 1;
                if ((j & ((long) (1 << i31))) != 0 && !((c) getChildAt(i31).getLayoutParams()).f173g) {
                    fBitCount -= 0.5f;
                }
            }
            int i32 = fBitCount > 0.0f ? (int) ((i11 * i13) / fBitCount) : i7;
            z2 = z;
            for (int i33 = i7; i33 < childCount; i33++) {
                if ((j & ((long) (1 << i33))) != 0) {
                    View childAt3 = getChildAt(i33);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f171e = i32;
                        cVar4.f174h = true;
                        if (i33 == 0 && !cVar4.f173g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i32) / 2;
                        }
                        z2 = true;
                    } else if (cVar4.f169c) {
                        cVar4.f171e = i32;
                        cVar4.f174h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i32) / 2;
                        z2 = true;
                    } else {
                        if (i33 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i32 / 2;
                        }
                        if (i33 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i32 / 2;
                        }
                    }
                }
            }
        }
        if (z2) {
            for (int i34 = i7; i34 < childCount; i34++) {
                View childAt4 = getChildAt(i34);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.f174h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f170d * i13) + cVar5.f171e, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i5, i4 != 1073741824 ? i6 : i20);
    }

    public g L() {
        return this.O3;
    }

    public void M(m.a aVar, g.a aVar2) {
        this.T3 = aVar;
        this.U3 = aVar2;
    }

    public boolean N() {
        ActionMenuPresenter actionMenuPresenter = this.S3;
        return actionMenuPresenter != null && actionMenuPresenter.L();
    }

    @Override // b.b.p.j.g.b
    public boolean a(i iVar) {
        return this.O3.L(iVar, 0);
    }

    @Override // b.b.p.j.n
    public void b(g gVar) {
        this.O3 = gVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public Menu getMenu() {
        if (this.O3 == null) {
            Context context = getContext();
            g gVar = new g(context);
            this.O3 = gVar;
            gVar.R(new d());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.S3 = actionMenuPresenter;
            actionMenuPresenter.K(true);
            ActionMenuPresenter actionMenuPresenter2 = this.S3;
            m.a bVar = this.T3;
            if (bVar == null) {
                bVar = new b();
            }
            actionMenuPresenter2.h(bVar);
            this.O3.c(this.S3, this.P3);
            this.S3.I(this);
        }
        return this.O3;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.S3.B();
    }

    public int getPopupTheme() {
        return this.Q3;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.S3;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.j(false);
            if (this.S3.F()) {
                this.S3.C();
                this.S3.L();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        z();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int width;
        int paddingLeft;
        if (!this.V3) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i6 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i7 = i4 - i2;
        int paddingRight = (i7 - getPaddingRight()) - getPaddingLeft();
        boolean zB = k0.b(this);
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f169c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (E(i10)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zB) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i11 = i6 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i11, width, measuredHeight + i11);
                    paddingRight -= measuredWidth;
                    i8 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    E(i10);
                    i9++;
                }
            }
        }
        if (childCount == 1 && i8 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i12 = (i7 / 2) - (measuredWidth2 / 2);
            int i13 = i6 - (measuredHeight2 / 2);
            childAt2.layout(i12, i13, measuredWidth2 + i12, measuredHeight2 + i13);
            return;
        }
        int i14 = i9 - (i8 ^ 1);
        int iMax = Math.max(0, i14 > 0 ? paddingRight / i14 : 0);
        if (zB) {
            int width2 = getWidth() - getPaddingRight();
            for (int i15 = 0; i15 < childCount; i15++) {
                View childAt3 = getChildAt(i15);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f169c) {
                    int i16 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i17 = i6 - (measuredHeight3 / 2);
                    childAt3.layout(i16 - measuredWidth3, i17, i16, measuredHeight3 + i17);
                    width2 = i16 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt4 = getChildAt(i18);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f169c) {
                int i19 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i20 = i6 - (measuredHeight4 / 2);
                childAt4.layout(i19, i20, i19 + measuredWidth4, measuredHeight4 + i20);
                paddingLeft2 = i19 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax;
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i2, int i3) {
        g gVar;
        boolean z = this.V3;
        boolean z2 = View.MeasureSpec.getMode(i2) == 1073741824;
        this.V3 = z2;
        if (z != z2) {
            this.W3 = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (this.V3 && (gVar = this.O3) != null && size != this.W3) {
            this.W3 = size;
            gVar.K(true);
        }
        int childCount = getChildCount();
        if (this.V3 && childCount > 0) {
            K(i2, i3);
            return;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            c cVar = (c) getChildAt(i4).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i2, i3);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.S3.H(z);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.Z3 = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.S3.J(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.R3 = z;
    }

    public void setPopupTheme(int i2) {
        if (this.Q3 != i2) {
            this.Q3 = i2;
            if (i2 == 0) {
                this.P3 = getContext();
            } else {
                this.P3 = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.S3 = actionMenuPresenter;
        actionMenuPresenter.I(this);
    }

    public void z() {
        ActionMenuPresenter actionMenuPresenter = this.S3;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.z();
        }
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.X3 = (int) (56.0f * f2);
        this.Y3 = (int) (f2 * 4.0f);
        this.P3 = context;
        this.Q3 = 0;
    }
}
