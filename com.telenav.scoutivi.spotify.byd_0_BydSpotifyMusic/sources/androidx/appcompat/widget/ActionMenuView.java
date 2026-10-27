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
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.e;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.LinearLayoutCompat;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements e.b, androidx.appcompat.view.menu.k {
    e.a A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    e F;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private androidx.appcompat.view.menu.e f824u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Context f825v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f826w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f827x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ActionMenuPresenter f828y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private j.a f829z;

    public interface a {
        boolean a();

        boolean b();
    }

    private static class b implements j.a {
        b() {
        }

        @Override // androidx.appcompat.view.menu.j.a
        public void a(androidx.appcompat.view.menu.e eVar, boolean z6) {
        }

        @Override // androidx.appcompat.view.menu.j.a
        public boolean b(androidx.appcompat.view.menu.e eVar) {
            return false;
        }
    }

    public static class c extends LinearLayoutCompat.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f830a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f831b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f832c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f833d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f834e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f835f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f830a = cVar.f830a;
        }

        public c(int i7, int i8) {
            super(i7, i8);
            this.f830a = false;
        }
    }

    private class d implements e.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.e.a
        public boolean a(androidx.appcompat.view.menu.e eVar, MenuItem menuItem) {
            e eVar2 = ActionMenuView.this.F;
            return eVar2 != null && eVar2.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.e.a
        public void b(androidx.appcompat.view.menu.e eVar) {
            e.a aVar = ActionMenuView.this.A;
            if (aVar != null) {
                aVar.b(eVar);
            }
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    static int L(View view, int i7, int i8, int i9, int i10) {
        c cVar = (c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i9) - i10, View.MeasureSpec.getMode(i9));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z6 = actionMenuItemView != null && actionMenuItemView.f();
        int i11 = 2;
        if (i8 <= 0 || (z6 && i8 < 2)) {
            i11 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8 * i7, Integer.MIN_VALUE), iMakeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i12 = measuredWidth / i7;
            if (measuredWidth % i7 != 0) {
                i12++;
            }
            if (!z6 || i12 >= 2) {
                i11 = i12;
            }
        }
        cVar.f833d = !cVar.f830a && z6;
        cVar.f831b = i11;
        view.measure(View.MeasureSpec.makeMeasureSpec(i7 * i11, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
        return i11;
    }

    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v14 */
    private void M(int i7, int i8) {
        int i9;
        int i10;
        boolean z6;
        int i11;
        int i12;
        boolean z7;
        boolean z8;
        int i13;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i7);
        int size2 = View.MeasureSpec.getSize(i8);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, paddingTop, -2);
        int i14 = size - paddingLeft;
        int i15 = this.D;
        int i16 = i14 / i15;
        int i17 = i14 % i15;
        if (i16 == 0) {
            setMeasuredDimension(i14, 0);
            return;
        }
        int i18 = i15 + (i17 / i16);
        int childCount = getChildCount();
        int iMax = 0;
        int i19 = 0;
        boolean z9 = false;
        int i20 = 0;
        int iMax2 = 0;
        int i21 = 0;
        long j7 = 0;
        while (i19 < childCount) {
            View childAt = getChildAt(i19);
            int i22 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z10 = childAt instanceof ActionMenuItemView;
                int i23 = i20 + 1;
                if (z10) {
                    int i24 = this.E;
                    i13 = i23;
                    r14 = 0;
                    childAt.setPadding(i24, 0, i24, 0);
                } else {
                    i13 = i23;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.f835f = r14;
                cVar.f832c = r14;
                cVar.f831b = r14;
                cVar.f833d = r14;
                ((LinearLayout.LayoutParams) cVar).leftMargin = r14;
                ((LinearLayout.LayoutParams) cVar).rightMargin = r14;
                cVar.f834e = z10 && ((ActionMenuItemView) childAt).f();
                int iL = L(childAt, i18, cVar.f830a ? 1 : i16, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iL);
                if (cVar.f833d) {
                    i21++;
                }
                if (cVar.f830a) {
                    z9 = true;
                }
                i16 -= iL;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iL == 1) {
                    j7 |= (long) (1 << i19);
                    iMax = iMax;
                }
                i20 = i13;
            }
            i19++;
            size2 = i22;
        }
        int i25 = size2;
        boolean z11 = z9 && i20 == 2;
        boolean z12 = false;
        while (i21 > 0 && i16 > 0) {
            int i26 = Integer.MAX_VALUE;
            int i27 = 0;
            int i28 = 0;
            long j8 = 0;
            while (i28 < childCount) {
                boolean z13 = z12;
                c cVar2 = (c) getChildAt(i28).getLayoutParams();
                int i29 = iMax;
                if (cVar2.f833d) {
                    int i30 = cVar2.f831b;
                    if (i30 < i26) {
                        j8 = 1 << i28;
                        i26 = i30;
                        i27 = 1;
                    } else if (i30 == i26) {
                        i27++;
                        j8 |= 1 << i28;
                    }
                }
                i28++;
                iMax = i29;
                z12 = z13;
            }
            z6 = z12;
            i11 = iMax;
            j7 |= j8;
            if (i27 > i16) {
                i9 = mode;
                i10 = i14;
                break;
            }
            int i31 = i26 + 1;
            int i32 = 0;
            while (i32 < childCount) {
                View childAt2 = getChildAt(i32);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i33 = i14;
                int i34 = mode;
                long j9 = 1 << i32;
                if ((j8 & j9) == 0) {
                    if (cVar3.f831b == i31) {
                        j7 |= j9;
                    }
                    z8 = z11;
                } else {
                    if (z11 && cVar3.f834e && i16 == 1) {
                        int i35 = this.E;
                        z8 = z11;
                        childAt2.setPadding(i35 + i18, 0, i35, 0);
                    } else {
                        z8 = z11;
                    }
                    cVar3.f831b++;
                    cVar3.f835f = true;
                    i16--;
                }
                i32++;
                mode = i34;
                i14 = i33;
                z11 = z8;
            }
            iMax = i11;
            z12 = true;
        }
        i9 = mode;
        i10 = i14;
        z6 = z12;
        i11 = iMax;
        boolean z14 = !z9 && i20 == 1;
        if (i16 <= 0 || j7 == 0 || (i16 >= i20 - 1 && !z14 && iMax2 <= 1)) {
            i12 = 0;
            z7 = z6;
        } else {
            float fBitCount = Long.bitCount(j7);
            if (z14) {
                i12 = 0;
            } else {
                i12 = 0;
                if ((j7 & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f834e) {
                    fBitCount -= 0.5f;
                }
                int i36 = childCount - 1;
                if ((j7 & ((long) (1 << i36))) != 0 && !((c) getChildAt(i36).getLayoutParams()).f834e) {
                    fBitCount -= 0.5f;
                }
            }
            int i37 = fBitCount > 0.0f ? (int) ((i16 * i18) / fBitCount) : i12;
            z7 = z6;
            for (int i38 = i12; i38 < childCount; i38++) {
                if ((j7 & ((long) (1 << i38))) != 0) {
                    View childAt3 = getChildAt(i38);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f832c = i37;
                        cVar4.f835f = true;
                        if (i38 == 0 && !cVar4.f834e) {
                            ((LinearLayout.LayoutParams) cVar4).leftMargin = (-i37) / 2;
                        }
                        z7 = true;
                    } else if (cVar4.f830a) {
                        cVar4.f832c = i37;
                        cVar4.f835f = true;
                        ((LinearLayout.LayoutParams) cVar4).rightMargin = (-i37) / 2;
                        z7 = true;
                    } else {
                        if (i38 != 0) {
                            ((LinearLayout.LayoutParams) cVar4).leftMargin = i37 / 2;
                        }
                        if (i38 != childCount - 1) {
                            ((LinearLayout.LayoutParams) cVar4).rightMargin = i37 / 2;
                        }
                    }
                }
            }
        }
        if (z7) {
            for (int i39 = i12; i39 < childCount; i39++) {
                View childAt4 = getChildAt(i39);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.f835f) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f831b * i18) + cVar5.f832c, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i10, i9 != 1073741824 ? i11 : i25);
    }

    public void B() {
        ActionMenuPresenter actionMenuPresenter = this.f828y;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.y();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        ((LinearLayout.LayoutParams) cVar).gravity = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (((LinearLayout.LayoutParams) cVar).gravity <= 0) {
            ((LinearLayout.LayoutParams) cVar).gravity = 16;
        }
        return cVar;
    }

    public c F() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.f830a = true;
        return cVarGenerateDefaultLayoutParams;
    }

    protected boolean G(int i7) {
        boolean zA = false;
        if (i7 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i7 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i7);
        if (i7 < getChildCount() && (childAt instanceof a)) {
            zA = false | ((a) childAt).a();
        }
        return (i7 <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).b();
    }

    public boolean H() {
        ActionMenuPresenter actionMenuPresenter = this.f828y;
        return actionMenuPresenter != null && actionMenuPresenter.B();
    }

    public boolean I() {
        ActionMenuPresenter actionMenuPresenter = this.f828y;
        return actionMenuPresenter != null && actionMenuPresenter.D();
    }

    public boolean J() {
        ActionMenuPresenter actionMenuPresenter = this.f828y;
        return actionMenuPresenter != null && actionMenuPresenter.E();
    }

    public boolean K() {
        return this.f827x;
    }

    public androidx.appcompat.view.menu.e N() {
        return this.f824u;
    }

    public void O(j.a aVar, e.a aVar2) {
        this.f829z = aVar;
        this.A = aVar2;
    }

    public boolean P() {
        ActionMenuPresenter actionMenuPresenter = this.f828y;
        return actionMenuPresenter != null && actionMenuPresenter.K();
    }

    @Override // androidx.appcompat.view.menu.e.b
    public boolean a(androidx.appcompat.view.menu.g gVar) {
        return this.f824u.L(gVar, 0);
    }

    @Override // androidx.appcompat.view.menu.k
    public void b(androidx.appcompat.view.menu.e eVar) {
        this.f824u = eVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public Menu getMenu() {
        if (this.f824u == null) {
            Context context = getContext();
            androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(context);
            this.f824u = eVar;
            eVar.R(new d());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.f828y = actionMenuPresenter;
            actionMenuPresenter.J(true);
            ActionMenuPresenter actionMenuPresenter2 = this.f828y;
            j.a bVar = this.f829z;
            if (bVar == null) {
                bVar = new b();
            }
            actionMenuPresenter2.h(bVar);
            this.f824u.c(this.f828y, this.f825v);
            this.f828y.H(this);
        }
        return this.f824u;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f828y.A();
    }

    public int getPopupTheme() {
        return this.f826w;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.f828y;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.j(false);
            if (this.f828y.E()) {
                this.f828y.B();
                this.f828y.K();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        B();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int width;
        int paddingLeft;
        if (!this.B) {
            super.onLayout(z6, i7, i8, i9, i10);
            return;
        }
        int childCount = getChildCount();
        int i11 = (i10 - i8) / 2;
        int dividerWidth = getDividerWidth();
        int i12 = i9 - i7;
        int paddingRight = (i12 - getPaddingRight()) - getPaddingLeft();
        boolean zB = w0.b(this);
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f830a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (G(i15)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zB) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) cVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) cVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i16 = i11 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i16, width, measuredHeight + i16);
                    paddingRight -= measuredWidth;
                    i13 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) cVar).leftMargin) + ((LinearLayout.LayoutParams) cVar).rightMargin;
                    G(i15);
                    i14++;
                }
            }
        }
        if (childCount == 1 && i13 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i17 = (i12 / 2) - (measuredWidth2 / 2);
            int i18 = i11 - (measuredHeight2 / 2);
            childAt2.layout(i17, i18, measuredWidth2 + i17, measuredHeight2 + i18);
            return;
        }
        int i19 = i14 - (i13 ^ 1);
        int iMax = Math.max(0, i19 > 0 ? paddingRight / i19 : 0);
        if (zB) {
            int width2 = getWidth() - getPaddingRight();
            for (int i20 = 0; i20 < childCount; i20++) {
                View childAt3 = getChildAt(i20);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f830a) {
                    int i21 = width2 - ((LinearLayout.LayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i22 = i11 - (measuredHeight3 / 2);
                    childAt3.layout(i21 - measuredWidth3, i22, i21, measuredHeight3 + i22);
                    width2 = i21 - ((measuredWidth3 + ((LinearLayout.LayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i23 = 0; i23 < childCount; i23++) {
            View childAt4 = getChildAt(i23);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f830a) {
                int i24 = paddingLeft2 + ((LinearLayout.LayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i25 = i11 - (measuredHeight4 / 2);
                childAt4.layout(i24, i25, i24 + measuredWidth4, measuredHeight4 + i25);
                paddingLeft2 = i24 + measuredWidth4 + ((LinearLayout.LayoutParams) cVar3).rightMargin + iMax;
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    protected void onMeasure(int i7, int i8) {
        androidx.appcompat.view.menu.e eVar;
        boolean z6 = this.B;
        boolean z7 = View.MeasureSpec.getMode(i7) == 1073741824;
        this.B = z7;
        if (z6 != z7) {
            this.C = 0;
        }
        int size = View.MeasureSpec.getSize(i7);
        if (this.B && (eVar = this.f824u) != null && size != this.C) {
            this.C = size;
            eVar.K(true);
        }
        int childCount = getChildCount();
        if (this.B && childCount > 0) {
            M(i7, i8);
            return;
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            c cVar = (c) getChildAt(i9).getLayoutParams();
            ((LinearLayout.LayoutParams) cVar).rightMargin = 0;
            ((LinearLayout.LayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i7, i8);
    }

    public void setExpandedActionViewsExclusive(boolean z6) {
        this.f828y.G(z6);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.F = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f828y.I(drawable);
    }

    public void setOverflowReserved(boolean z6) {
        this.f827x = z6;
    }

    public void setPopupTheme(int i7) {
        if (this.f826w != i7) {
            this.f826w = i7;
            if (i7 == 0) {
                this.f825v = getContext();
            } else {
                this.f825v = new ContextThemeWrapper(getContext(), i7);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f828y = actionMenuPresenter;
        actionMenuPresenter.H(this);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.D = (int) (56.0f * f7);
        this.E = (int) (f7 * 4.0f);
        this.f825v = context;
        this.f826w = 0;
    }
}
