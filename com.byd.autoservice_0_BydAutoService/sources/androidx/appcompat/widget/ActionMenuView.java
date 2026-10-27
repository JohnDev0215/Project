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
import n.g;
import n.i;
import n.m;
import n.n;
import o.k0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements g.b, n {
    public int A;
    public e B;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public g f396q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Context f397r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f398s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f399t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ActionMenuPresenter f400u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public m.a f401v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public g.a f402w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f403x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f404y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f405z;

    public interface a {
        boolean a();

        boolean b();
    }

    public static class b implements m.a {
        @Override // n.m.a
        public void a(g gVar, boolean z3) {
        }

        @Override // n.m.a
        public boolean b(g gVar) {
            return false;
        }
    }

    public static class c extends LinearLayoutCompat.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f406c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f407d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f408e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f409f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f410g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f411h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f406c = cVar.f406c;
        }

        public c(int i3, int i4) {
            super(i3, i4);
            this.f406c = false;
        }
    }

    public class d implements g.a {
        public d() {
        }

        @Override // n.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.B;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // n.g.a
        public void b(g gVar) {
            g.a aVar = ActionMenuView.this.f402w;
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

    public static int L(View view, int i3, int i4, int i5, int i6) {
        c cVar = (c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i5) - i6, View.MeasureSpec.getMode(i5));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z3 = actionMenuItemView != null && actionMenuItemView.f();
        int i7 = 2;
        if (i4 <= 0 || (z3 && i4 < 2)) {
            i7 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i4 * i3, Integer.MIN_VALUE), iMakeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i8 = measuredWidth / i3;
            if (measuredWidth % i3 != 0) {
                i8++;
            }
            if (!z3 || i8 >= 2) {
                i7 = i8;
            }
        }
        cVar.f409f = !cVar.f406c && z3;
        cVar.f407d = i7;
        view.measure(View.MeasureSpec.makeMeasureSpec(i3 * i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
        return i7;
    }

    public void B() {
        ActionMenuPresenter actionMenuPresenter = this.f400u;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.C();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f527b = 16;
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
        if (cVar.f527b <= 0) {
            cVar.f527b = 16;
        }
        return cVar;
    }

    public c F() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.f406c = true;
        return cVarGenerateDefaultLayoutParams;
    }

    public boolean G(int i3) {
        boolean zA = false;
        if (i3 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i3 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i3);
        if (i3 < getChildCount() && (childAt instanceof a)) {
            zA = false | ((a) childAt).a();
        }
        return (i3 <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).b();
    }

    public boolean H() {
        ActionMenuPresenter actionMenuPresenter = this.f400u;
        return actionMenuPresenter != null && actionMenuPresenter.F();
    }

    public boolean I() {
        ActionMenuPresenter actionMenuPresenter = this.f400u;
        return actionMenuPresenter != null && actionMenuPresenter.H();
    }

    public boolean J() {
        ActionMenuPresenter actionMenuPresenter = this.f400u;
        return actionMenuPresenter != null && actionMenuPresenter.I();
    }

    public boolean K() {
        return this.f399t;
    }

    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v14 */
    public final void M(int i3, int i4) {
        int i5;
        int i6;
        boolean z3;
        int i7;
        int i8;
        boolean z4;
        boolean z5;
        int i9;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i4);
        int size = View.MeasureSpec.getSize(i3);
        int size2 = View.MeasureSpec.getSize(i4);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i4, paddingTop, -2);
        int i10 = size - paddingLeft;
        int i11 = this.f405z;
        int i12 = i10 / i11;
        int i13 = i10 % i11;
        if (i12 == 0) {
            setMeasuredDimension(i10, 0);
            return;
        }
        int i14 = i11 + (i13 / i12);
        int childCount = getChildCount();
        int iMax = 0;
        int i15 = 0;
        boolean z6 = false;
        int i16 = 0;
        int iMax2 = 0;
        int i17 = 0;
        long j3 = 0;
        while (i15 < childCount) {
            View childAt = getChildAt(i15);
            int i18 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z7 = childAt instanceof ActionMenuItemView;
                int i19 = i16 + 1;
                if (z7) {
                    int i20 = this.A;
                    i9 = i19;
                    r14 = 0;
                    childAt.setPadding(i20, 0, i20, 0);
                } else {
                    i9 = i19;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.f411h = r14;
                cVar.f408e = r14;
                cVar.f407d = r14;
                cVar.f409f = r14;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r14;
                cVar.f410g = z7 && ((ActionMenuItemView) childAt).f();
                int iL = L(childAt, i14, cVar.f406c ? 1 : i12, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iL);
                if (cVar.f409f) {
                    i17++;
                }
                if (cVar.f406c) {
                    z6 = true;
                }
                i12 -= iL;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iL == 1) {
                    j3 |= (long) (1 << i15);
                    iMax = iMax;
                }
                i16 = i9;
            }
            i15++;
            size2 = i18;
        }
        int i21 = size2;
        boolean z8 = z6 && i16 == 2;
        boolean z9 = false;
        while (i17 > 0 && i12 > 0) {
            int i22 = Integer.MAX_VALUE;
            int i23 = 0;
            int i24 = 0;
            long j4 = 0;
            while (i24 < childCount) {
                boolean z10 = z9;
                c cVar2 = (c) getChildAt(i24).getLayoutParams();
                int i25 = iMax;
                if (cVar2.f409f) {
                    int i26 = cVar2.f407d;
                    if (i26 < i22) {
                        j4 = 1 << i24;
                        i22 = i26;
                        i23 = 1;
                    } else if (i26 == i22) {
                        i23++;
                        j4 |= 1 << i24;
                    }
                }
                i24++;
                iMax = i25;
                z9 = z10;
            }
            z3 = z9;
            i7 = iMax;
            j3 |= j4;
            if (i23 > i12) {
                i5 = mode;
                i6 = i10;
                break;
            }
            int i27 = i22 + 1;
            int i28 = 0;
            while (i28 < childCount) {
                View childAt2 = getChildAt(i28);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i29 = i10;
                int i30 = mode;
                long j5 = 1 << i28;
                if ((j4 & j5) == 0) {
                    if (cVar3.f407d == i27) {
                        j3 |= j5;
                    }
                    z5 = z8;
                } else {
                    if (z8 && cVar3.f410g && i12 == 1) {
                        int i31 = this.A;
                        z5 = z8;
                        childAt2.setPadding(i31 + i14, 0, i31, 0);
                    } else {
                        z5 = z8;
                    }
                    cVar3.f407d++;
                    cVar3.f411h = true;
                    i12--;
                }
                i28++;
                mode = i30;
                i10 = i29;
                z8 = z5;
            }
            iMax = i7;
            z9 = true;
        }
        i5 = mode;
        i6 = i10;
        z3 = z9;
        i7 = iMax;
        boolean z11 = !z6 && i16 == 1;
        if (i12 <= 0 || j3 == 0 || (i12 >= i16 - 1 && !z11 && iMax2 <= 1)) {
            i8 = 0;
            z4 = z3;
        } else {
            float fBitCount = Long.bitCount(j3);
            if (z11) {
                i8 = 0;
            } else {
                i8 = 0;
                if ((j3 & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f410g) {
                    fBitCount -= 0.5f;
                }
                int i32 = childCount - 1;
                if ((j3 & ((long) (1 << i32))) != 0 && !((c) getChildAt(i32).getLayoutParams()).f410g) {
                    fBitCount -= 0.5f;
                }
            }
            int i33 = fBitCount > 0.0f ? (int) ((i12 * i14) / fBitCount) : i8;
            z4 = z3;
            for (int i34 = i8; i34 < childCount; i34++) {
                if ((j3 & ((long) (1 << i34))) != 0) {
                    View childAt3 = getChildAt(i34);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f408e = i33;
                        cVar4.f411h = true;
                        if (i34 == 0 && !cVar4.f410g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i33) / 2;
                        }
                        z4 = true;
                    } else if (cVar4.f406c) {
                        cVar4.f408e = i33;
                        cVar4.f411h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i33) / 2;
                        z4 = true;
                    } else {
                        if (i34 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i33 / 2;
                        }
                        if (i34 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i33 / 2;
                        }
                    }
                }
            }
        }
        if (z4) {
            for (int i35 = i8; i35 < childCount; i35++) {
                View childAt4 = getChildAt(i35);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.f411h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f407d * i14) + cVar5.f408e, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i6, i5 != 1073741824 ? i7 : i21);
    }

    public g N() {
        return this.f396q;
    }

    public void O(m.a aVar, g.a aVar2) {
        this.f401v = aVar;
        this.f402w = aVar2;
    }

    public boolean P() {
        ActionMenuPresenter actionMenuPresenter = this.f400u;
        return actionMenuPresenter != null && actionMenuPresenter.O();
    }

    @Override // n.g.b
    public boolean a(i iVar) {
        return this.f396q.N(iVar, 0);
    }

    @Override // n.n
    public void b(g gVar) {
        this.f396q = gVar;
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
        if (this.f396q == null) {
            Context context = getContext();
            g gVar = new g(context);
            this.f396q = gVar;
            gVar.V(new d());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.f400u = actionMenuPresenter;
            actionMenuPresenter.N(true);
            ActionMenuPresenter actionMenuPresenter2 = this.f400u;
            m.a bVar = this.f401v;
            if (bVar == null) {
                bVar = new b();
            }
            actionMenuPresenter2.k(bVar);
            this.f396q.c(this.f400u, this.f397r);
            this.f400u.L(this);
        }
        return this.f396q;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f400u.E();
    }

    public int getPopupTheme() {
        return this.f398s;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.f400u;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.n(false);
            if (this.f400u.I()) {
                this.f400u.F();
                this.f400u.O();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        B();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        int width;
        int paddingLeft;
        if (!this.f403x) {
            super.onLayout(z3, i3, i4, i5, i6);
            return;
        }
        int childCount = getChildCount();
        int i7 = (i6 - i4) / 2;
        int dividerWidth = getDividerWidth();
        int i8 = i5 - i3;
        int paddingRight = (i8 - getPaddingRight()) - getPaddingLeft();
        boolean zB = k0.b(this);
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f406c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (G(i11)) {
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
                    int i12 = i7 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i12, width, measuredHeight + i12);
                    paddingRight -= measuredWidth;
                    i9 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    G(i11);
                    i10++;
                }
            }
        }
        if (childCount == 1 && i9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i13 = (i8 / 2) - (measuredWidth2 / 2);
            int i14 = i7 - (measuredHeight2 / 2);
            childAt2.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
            return;
        }
        int i15 = i10 - (i9 ^ 1);
        int iMax = Math.max(0, i15 > 0 ? paddingRight / i15 : 0);
        if (zB) {
            int width2 = getWidth() - getPaddingRight();
            for (int i16 = 0; i16 < childCount; i16++) {
                View childAt3 = getChildAt(i16);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f406c) {
                    int i17 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i18 = i7 - (measuredHeight3 / 2);
                    childAt3.layout(i17 - measuredWidth3, i18, i17, measuredHeight3 + i18);
                    width2 = i17 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt4 = getChildAt(i19);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f406c) {
                int i20 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i7 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft2 = i20 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax;
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i3, int i4) {
        g gVar;
        boolean z3 = this.f403x;
        boolean z4 = View.MeasureSpec.getMode(i3) == 1073741824;
        this.f403x = z4;
        if (z3 != z4) {
            this.f404y = 0;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (this.f403x && (gVar = this.f396q) != null && size != this.f404y) {
            this.f404y = size;
            gVar.M(true);
        }
        int childCount = getChildCount();
        if (this.f403x && childCount > 0) {
            M(i3, i4);
            return;
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            c cVar = (c) getChildAt(i5).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i3, i4);
    }

    public void setExpandedActionViewsExclusive(boolean z3) {
        this.f400u.K(z3);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.B = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f400u.M(drawable);
    }

    public void setOverflowReserved(boolean z3) {
        this.f399t = z3;
    }

    public void setPopupTheme(int i3) {
        if (this.f398s != i3) {
            this.f398s = i3;
            if (i3 == 0) {
                this.f397r = getContext();
            } else {
                this.f397r = new ContextThemeWrapper(getContext(), i3);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f400u = actionMenuPresenter;
        actionMenuPresenter.L(this);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f3 = context.getResources().getDisplayMetrics().density;
        this.f405z = (int) (56.0f * f3);
        this.A = (int) (f3 * 4.0f);
        this.f397r = context;
        this.f398s = 0;
    }
}
