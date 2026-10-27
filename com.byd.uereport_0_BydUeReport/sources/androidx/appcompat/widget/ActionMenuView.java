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
import q.g;
import q.i;
import q.m;
import q.n;
import r.k0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements g.b, n {
    public int A;
    public int B;
    public int C;
    public e D;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g f499s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Context f500t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f501u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f502v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ActionMenuPresenter f503w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public m.a f504x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public g.a f505y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f506z;

    public interface a {
        boolean a();

        boolean b();
    }

    public static class b implements m.a {
        @Override // q.m.a
        public void a(g gVar, boolean z7) {
        }

        @Override // q.m.a
        public boolean b(g gVar) {
            return false;
        }
    }

    public static class c extends LinearLayoutCompat.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f507c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f508d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f509e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f510f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f511g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f512h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f507c = cVar.f507c;
        }

        public c(int i8, int i9) {
            super(i8, i9);
            this.f507c = false;
        }
    }

    public class d implements g.a {
        public d() {
        }

        @Override // q.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.D;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // q.g.a
        public void b(g gVar) {
            g.a aVar = ActionMenuView.this.f505y;
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

    public static int L(View view, int i8, int i9, int i10, int i11) {
        c cVar = (c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i10) - i11, View.MeasureSpec.getMode(i10));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z7 = actionMenuItemView != null && actionMenuItemView.f();
        int i12 = 2;
        if (i9 <= 0 || (z7 && i9 < 2)) {
            i12 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i9 * i8, Integer.MIN_VALUE), iMakeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i13 = measuredWidth / i8;
            if (measuredWidth % i8 != 0) {
                i13++;
            }
            if (!z7 || i13 >= 2) {
                i12 = i13;
            }
        }
        cVar.f510f = !cVar.f507c && z7;
        cVar.f508d = i12;
        view.measure(View.MeasureSpec.makeMeasureSpec(i8 * i12, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
        return i12;
    }

    public void B() {
        ActionMenuPresenter actionMenuPresenter = this.f503w;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.C();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f628b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (cVar.f628b <= 0) {
            cVar.f628b = 16;
        }
        return cVar;
    }

    public c F() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.f507c = true;
        return cVarGenerateDefaultLayoutParams;
    }

    public boolean G(int i8) {
        boolean zA = false;
        if (i8 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i8 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i8);
        if (i8 < getChildCount() && (childAt instanceof a)) {
            zA = false | ((a) childAt).a();
        }
        return (i8 <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).b();
    }

    public boolean H() {
        ActionMenuPresenter actionMenuPresenter = this.f503w;
        return actionMenuPresenter != null && actionMenuPresenter.F();
    }

    public boolean I() {
        ActionMenuPresenter actionMenuPresenter = this.f503w;
        return actionMenuPresenter != null && actionMenuPresenter.H();
    }

    public boolean J() {
        ActionMenuPresenter actionMenuPresenter = this.f503w;
        return actionMenuPresenter != null && actionMenuPresenter.I();
    }

    public boolean K() {
        return this.f502v;
    }

    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v14 */
    public final void M(int i8, int i9) {
        int i10;
        int i11;
        boolean z7;
        int i12;
        int i13;
        boolean z8;
        boolean z9;
        int i14;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i8);
        int size2 = View.MeasureSpec.getSize(i9);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingTop, -2);
        int i15 = size - paddingLeft;
        int i16 = this.B;
        int i17 = i15 / i16;
        int i18 = i15 % i16;
        if (i17 == 0) {
            setMeasuredDimension(i15, 0);
            return;
        }
        int i19 = i16 + (i18 / i17);
        int childCount = getChildCount();
        int iMax = 0;
        int i20 = 0;
        boolean z10 = false;
        int i21 = 0;
        int iMax2 = 0;
        int i22 = 0;
        long j8 = 0;
        while (i20 < childCount) {
            View childAt = getChildAt(i20);
            int i23 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z11 = childAt instanceof ActionMenuItemView;
                int i24 = i21 + 1;
                if (z11) {
                    int i25 = this.C;
                    i14 = i24;
                    r14 = 0;
                    childAt.setPadding(i25, 0, i25, 0);
                } else {
                    i14 = i24;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.f512h = r14;
                cVar.f509e = r14;
                cVar.f508d = r14;
                cVar.f510f = r14;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r14;
                cVar.f511g = z11 && ((ActionMenuItemView) childAt).f();
                int iL = L(childAt, i19, cVar.f507c ? 1 : i17, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iL);
                if (cVar.f510f) {
                    i22++;
                }
                if (cVar.f507c) {
                    z10 = true;
                }
                i17 -= iL;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iL == 1) {
                    j8 |= (long) (1 << i20);
                    iMax = iMax;
                }
                i21 = i14;
            }
            i20++;
            size2 = i23;
        }
        int i26 = size2;
        boolean z12 = z10 && i21 == 2;
        boolean z13 = false;
        while (i22 > 0 && i17 > 0) {
            int i27 = Integer.MAX_VALUE;
            int i28 = 0;
            int i29 = 0;
            long j9 = 0;
            while (i29 < childCount) {
                boolean z14 = z13;
                c cVar2 = (c) getChildAt(i29).getLayoutParams();
                int i30 = iMax;
                if (cVar2.f510f) {
                    int i31 = cVar2.f508d;
                    if (i31 < i27) {
                        j9 = 1 << i29;
                        i27 = i31;
                        i28 = 1;
                    } else if (i31 == i27) {
                        i28++;
                        j9 |= 1 << i29;
                    }
                }
                i29++;
                iMax = i30;
                z13 = z14;
            }
            z7 = z13;
            i12 = iMax;
            j8 |= j9;
            if (i28 > i17) {
                i10 = mode;
                i11 = i15;
                break;
            }
            int i32 = i27 + 1;
            int i33 = 0;
            while (i33 < childCount) {
                View childAt2 = getChildAt(i33);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i34 = i15;
                int i35 = mode;
                long j10 = 1 << i33;
                if ((j9 & j10) == 0) {
                    if (cVar3.f508d == i32) {
                        j8 |= j10;
                    }
                    z9 = z12;
                } else {
                    if (z12 && cVar3.f511g && i17 == 1) {
                        int i36 = this.C;
                        z9 = z12;
                        childAt2.setPadding(i36 + i19, 0, i36, 0);
                    } else {
                        z9 = z12;
                    }
                    cVar3.f508d++;
                    cVar3.f512h = true;
                    i17--;
                }
                i33++;
                mode = i35;
                i15 = i34;
                z12 = z9;
            }
            iMax = i12;
            z13 = true;
        }
        i10 = mode;
        i11 = i15;
        z7 = z13;
        i12 = iMax;
        boolean z15 = !z10 && i21 == 1;
        if (i17 <= 0 || j8 == 0 || (i17 >= i21 - 1 && !z15 && iMax2 <= 1)) {
            i13 = 0;
            z8 = z7;
        } else {
            float fBitCount = Long.bitCount(j8);
            if (z15) {
                i13 = 0;
            } else {
                i13 = 0;
                if ((j8 & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f511g) {
                    fBitCount -= 0.5f;
                }
                int i37 = childCount - 1;
                if ((j8 & ((long) (1 << i37))) != 0 && !((c) getChildAt(i37).getLayoutParams()).f511g) {
                    fBitCount -= 0.5f;
                }
            }
            int i38 = fBitCount > 0.0f ? (int) ((i17 * i19) / fBitCount) : i13;
            z8 = z7;
            for (int i39 = i13; i39 < childCount; i39++) {
                if ((j8 & ((long) (1 << i39))) != 0) {
                    View childAt3 = getChildAt(i39);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f509e = i38;
                        cVar4.f512h = true;
                        if (i39 == 0 && !cVar4.f511g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i38) / 2;
                        }
                        z8 = true;
                    } else if (cVar4.f507c) {
                        cVar4.f509e = i38;
                        cVar4.f512h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i38) / 2;
                        z8 = true;
                    } else {
                        if (i39 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i38 / 2;
                        }
                        if (i39 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i38 / 2;
                        }
                    }
                }
            }
        }
        if (z8) {
            for (int i40 = i13; i40 < childCount; i40++) {
                View childAt4 = getChildAt(i40);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.f512h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f508d * i19) + cVar5.f509e, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i11, i10 != 1073741824 ? i12 : i26);
    }

    public g N() {
        return this.f499s;
    }

    public void O(m.a aVar, g.a aVar2) {
        this.f504x = aVar;
        this.f505y = aVar2;
    }

    public boolean P() {
        ActionMenuPresenter actionMenuPresenter = this.f503w;
        return actionMenuPresenter != null && actionMenuPresenter.O();
    }

    @Override // q.g.b
    public boolean a(i iVar) {
        return this.f499s.N(iVar, 0);
    }

    @Override // q.n
    public void b(g gVar) {
        this.f499s = gVar;
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
        if (this.f499s == null) {
            Context context = getContext();
            g gVar = new g(context);
            this.f499s = gVar;
            gVar.V(new d());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.f503w = actionMenuPresenter;
            actionMenuPresenter.N(true);
            ActionMenuPresenter actionMenuPresenter2 = this.f503w;
            m.a bVar = this.f504x;
            if (bVar == null) {
                bVar = new b();
            }
            actionMenuPresenter2.n(bVar);
            this.f499s.c(this.f503w, this.f500t);
            this.f503w.L(this);
        }
        return this.f499s;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f503w.E();
    }

    public int getPopupTheme() {
        return this.f501u;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.f503w;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.g(false);
            if (this.f503w.I()) {
                this.f503w.F();
                this.f503w.O();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        B();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int width;
        int paddingLeft;
        if (!this.f506z) {
            super.onLayout(z7, i8, i9, i10, i11);
            return;
        }
        int childCount = getChildCount();
        int i12 = (i11 - i9) / 2;
        int dividerWidth = getDividerWidth();
        int i13 = i10 - i8;
        int paddingRight = (i13 - getPaddingRight()) - getPaddingLeft();
        boolean zB = k0.b(this);
        int i14 = 0;
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f507c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (G(i16)) {
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
                    int i17 = i12 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i17, width, measuredHeight + i17);
                    paddingRight -= measuredWidth;
                    i14 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    G(i16);
                    i15++;
                }
            }
        }
        if (childCount == 1 && i14 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i18 = (i13 / 2) - (measuredWidth2 / 2);
            int i19 = i12 - (measuredHeight2 / 2);
            childAt2.layout(i18, i19, measuredWidth2 + i18, measuredHeight2 + i19);
            return;
        }
        int i20 = i15 - (i14 ^ 1);
        int iMax = Math.max(0, i20 > 0 ? paddingRight / i20 : 0);
        if (zB) {
            int width2 = getWidth() - getPaddingRight();
            for (int i21 = 0; i21 < childCount; i21++) {
                View childAt3 = getChildAt(i21);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f507c) {
                    int i22 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i23 = i12 - (measuredHeight3 / 2);
                    childAt3.layout(i22 - measuredWidth3, i23, i22, measuredHeight3 + i23);
                    width2 = i22 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i24 = 0; i24 < childCount; i24++) {
            View childAt4 = getChildAt(i24);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f507c) {
                int i25 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i26 = i12 - (measuredHeight4 / 2);
                childAt4.layout(i25, i26, i25 + measuredWidth4, measuredHeight4 + i26);
                paddingLeft2 = i25 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax;
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i8, int i9) {
        g gVar;
        boolean z7 = this.f506z;
        boolean z8 = View.MeasureSpec.getMode(i8) == 1073741824;
        this.f506z = z8;
        if (z7 != z8) {
            this.A = 0;
        }
        int size = View.MeasureSpec.getSize(i8);
        if (this.f506z && (gVar = this.f499s) != null && size != this.A) {
            this.A = size;
            gVar.M(true);
        }
        int childCount = getChildCount();
        if (this.f506z && childCount > 0) {
            M(i8, i9);
            return;
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            c cVar = (c) getChildAt(i10).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i8, i9);
    }

    public void setExpandedActionViewsExclusive(boolean z7) {
        this.f503w.K(z7);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.D = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f503w.M(drawable);
    }

    public void setOverflowReserved(boolean z7) {
        this.f502v = z7;
    }

    public void setPopupTheme(int i8) {
        if (this.f501u != i8) {
            this.f501u = i8;
            if (i8 == 0) {
                this.f500t = getContext();
            } else {
                this.f500t = new ContextThemeWrapper(getContext(), i8);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f503w = actionMenuPresenter;
        actionMenuPresenter.L(this);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f8 = context.getResources().getDisplayMetrics().density;
        this.B = (int) (56.0f * f8);
        this.C = (int) (f8 * 4.0f);
        this.f500t = context;
        this.f501u = 0;
    }
}
