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
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.LinearLayoutCompat;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    public boolean A;
    public int B;
    public final int C;
    public final int D;
    public OnMenuItemClickListener E;
    public MenuBuilder t;
    public Context u;
    public int v;
    public boolean w;
    public ActionMenuPresenter x;
    public MenuPresenter.Callback y;
    public MenuBuilder.Callback z;

    public interface ActionMenuChildView {
        boolean a();

        boolean b();
    }

    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {

        @ViewDebug.ExportedProperty
        public boolean a;

        @ViewDebug.ExportedProperty
        public int b;

        @ViewDebug.ExportedProperty
        public int c;

        @ViewDebug.ExportedProperty
        public boolean d;

        @ViewDebug.ExportedProperty
        public boolean e;
        public boolean f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.a = layoutParams.a;
        }

        public LayoutParams() {
            super(-2, -2);
            this.a = false;
        }
    }

    public class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            OnMenuItemClickListener onMenuItemClickListener = ActionMenuView.this.E;
            return onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void b(MenuBuilder menuBuilder) {
            MenuBuilder.Callback callback = ActionMenuView.this.z;
            if (callback != null) {
                callback.b(menuBuilder);
            }
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public static LayoutParams j(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            LayoutParams layoutParams2 = new LayoutParams();
            ((LinearLayout.LayoutParams) layoutParams2).gravity = 16;
            return layoutParams2;
        }
        LayoutParams layoutParams3 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
        if (((LinearLayout.LayoutParams) layoutParams3).gravity <= 0) {
            ((LinearLayout.LayoutParams) layoutParams3).gravity = 16;
        }
        return layoutParams3;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public final boolean a(MenuItemImpl menuItemImpl) {
        return this.t.q(menuItemImpl, null, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public final void b(MenuBuilder menuBuilder) {
        this.t = menuBuilder;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: f */
    public final LinearLayoutCompat.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams();
        ((LinearLayout.LayoutParams) layoutParams).gravity = 16;
        return layoutParams;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: g */
    public final LinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams();
        ((LinearLayout.LayoutParams) layoutParams).gravity = 16;
        return layoutParams;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public Menu getMenu() {
        if (this.t == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.t = menuBuilder;
            menuBuilder.e = new MenuBuilderCallback();
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.x = actionMenuPresenter;
            actionMenuPresenter.q = true;
            actionMenuPresenter.r = true;
            MenuPresenter.Callback actionMenuPresenterCallback = this.y;
            if (actionMenuPresenterCallback == null) {
                actionMenuPresenterCallback = new ActionMenuPresenterCallback();
            }
            actionMenuPresenter.i = actionMenuPresenterCallback;
            this.t.b(this.x, this.u);
            ActionMenuPresenter actionMenuPresenter2 = this.x;
            actionMenuPresenter2.l = this;
            this.t = actionMenuPresenter2.g;
        }
        return this.t;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        ActionMenuPresenter actionMenuPresenter = this.x;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton = actionMenuPresenter.n;
        if (overflowMenuButton != null) {
            return overflowMenuButton.getDrawable();
        }
        if (actionMenuPresenter.p) {
            return actionMenuPresenter.o;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.v;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* JADX INFO: renamed from: h */
    public final /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public final boolean k(int i) {
        boolean zA = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            zA = false | ((ActionMenuChildView) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? zA : zA | ((ActionMenuChildView) childAt2).b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.x;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.d(false);
            if (this.x.p()) {
                this.x.o();
                this.x.r();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.x;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.o();
            ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu = actionMenuPresenter.y;
            if (actionButtonSubmenu == null || !actionButtonSubmenu.b()) {
                return;
            }
            actionButtonSubmenu.j.dismiss();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        int paddingLeft;
        if (!this.A) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i5 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i6 = i3 - i;
        int paddingRight = (i6 - getPaddingRight()) - getPaddingLeft();
        boolean zA = ViewUtils.a(this);
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (k(i9)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) layoutParams).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i10 = i5 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i10, width, measuredHeight + i10);
                    paddingRight -= measuredWidth;
                    i7 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) layoutParams).leftMargin) + ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                    k(i9);
                    i8++;
                }
            }
        }
        if (childCount == 1 && i7 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i11 = (i6 / 2) - (measuredWidth2 / 2);
            int i12 = i5 - (measuredHeight2 / 2);
            childAt2.layout(i11, i12, measuredWidth2 + i11, measuredHeight2 + i12);
            return;
        }
        int i13 = i8 - (i7 ^ 1);
        int iMax = Math.max(0, i13 > 0 ? paddingRight / i13 : 0);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt3 = getChildAt(i14);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.a) {
                    int i15 = width2 - ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i16 = i5 - (measuredHeight3 / 2);
                    childAt3.layout(i15 - measuredWidth3, i16, i15, measuredHeight3 + i16);
                    width2 = i15 - ((measuredWidth3 + ((LinearLayout.LayoutParams) layoutParams2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt4 = getChildAt(i17);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.a) {
                int i18 = paddingLeft2 + ((LinearLayout.LayoutParams) layoutParams3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i19 = i5 - (measuredHeight4 / 2);
                childAt4.layout(i18, i19, i18 + measuredWidth4, measuredHeight4 + i19);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) layoutParams3).rightMargin + iMax + i18;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v36 */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        boolean z;
        int i4;
        boolean z2;
        int i5;
        int i6;
        int i7;
        ?? r4;
        int i8;
        int i9;
        int i10;
        MenuBuilder menuBuilder;
        boolean z3 = this.A;
        boolean z4 = View.MeasureSpec.getMode(i) == 1073741824;
        this.A = z4;
        if (z3 != z4) {
            this.B = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.A && (menuBuilder = this.t) != null && size != this.B) {
            this.B = size;
            menuBuilder.p(true);
        }
        int childCount = getChildCount();
        if (!this.A || childCount <= 0) {
            for (int i11 = 0; i11 < childCount; i11++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i11).getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).rightMargin = 0;
                ((LinearLayout.LayoutParams) layoutParams).leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i);
        int size3 = View.MeasureSpec.getSize(i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int i12 = size2 - paddingRight;
        int i13 = this.C;
        int i14 = i12 / i13;
        int i15 = i12 % i13;
        if (i14 == 0) {
            setMeasuredDimension(i12, 0);
            return;
        }
        int i16 = (i15 / i14) + i13;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i17 = 0;
        int iMax2 = 0;
        int i18 = 0;
        boolean z5 = false;
        int i19 = 0;
        long j = 0;
        while (true) {
            i3 = this.D;
            if (i18 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i18);
            int i20 = size3;
            int i21 = i12;
            if (childAt.getVisibility() == 8) {
                i8 = mode;
                i9 = paddingBottom;
            } else {
                boolean z6 = childAt instanceof ActionMenuItemView;
                int i22 = i17 + 1;
                if (z6) {
                    childAt.setPadding(i3, 0, i3, 0);
                }
                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                layoutParams2.f = false;
                layoutParams2.c = 0;
                layoutParams2.b = 0;
                layoutParams2.d = false;
                ((LinearLayout.LayoutParams) layoutParams2).leftMargin = 0;
                ((LinearLayout.LayoutParams) layoutParams2).rightMargin = 0;
                layoutParams2.e = z6 && ((ActionMenuItemView) childAt).q();
                int i23 = layoutParams2.a ? 1 : i14;
                LayoutParams layoutParams3 = (LayoutParams) childAt.getLayoutParams();
                i8 = mode;
                i9 = paddingBottom;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z6 ? (ActionMenuItemView) childAt : null;
                boolean z7 = actionMenuItemView != null && actionMenuItemView.q();
                if (i23 <= 0 || (z7 && i23 < 2)) {
                    i10 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i23 * i16, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i10 = measuredWidth / i16;
                    if (measuredWidth % i16 != 0) {
                        i10++;
                    }
                    if (z7 && i10 < 2) {
                        i10 = 2;
                    }
                }
                layoutParams3.d = !layoutParams3.a && z7;
                layoutParams3.b = i10;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i16 * i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                iMax2 = Math.max(iMax2, i10);
                if (layoutParams2.d) {
                    i19++;
                }
                if (layoutParams2.a) {
                    z5 = true;
                }
                i14 -= i10;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (i10 == 1) {
                    j |= (long) (1 << i18);
                }
                i17 = i22;
            }
            i18++;
            size3 = i20;
            i12 = i21;
            paddingBottom = i9;
            mode = i8;
        }
        int i24 = mode;
        int i25 = i12;
        int i26 = size3;
        boolean z8 = z5 && i17 == 2;
        boolean z9 = false;
        while (i19 > 0 && i14 > 0) {
            int i27 = Integer.MAX_VALUE;
            int i28 = 0;
            int i29 = 0;
            long j2 = 0;
            while (i29 < childCount2) {
                LayoutParams layoutParams4 = (LayoutParams) getChildAt(i29).getLayoutParams();
                boolean z10 = z9;
                if (layoutParams4.d) {
                    int i30 = layoutParams4.b;
                    if (i30 < i27) {
                        j2 = 1 << i29;
                        i27 = i30;
                        i28 = 1;
                    } else if (i30 == i27) {
                        j2 |= 1 << i29;
                        i28++;
                    }
                }
                i29++;
                z9 = z10;
            }
            z = z9;
            j |= j2;
            if (i28 > i14) {
                break;
            }
            int i31 = i27 + 1;
            int i32 = 0;
            while (i32 < childCount2) {
                View childAt2 = getChildAt(i32);
                LayoutParams layoutParams5 = (LayoutParams) childAt2.getLayoutParams();
                int i33 = iMax;
                int i34 = childMeasureSpec;
                int i35 = childCount2;
                long j3 = 1 << i32;
                if ((j2 & j3) != 0) {
                    if (z8 && layoutParams5.e) {
                        r4 = 1;
                        r4 = 1;
                        if (i14 == 1) {
                            childAt2.setPadding(i3 + i16, 0, i3, 0);
                        }
                    } else {
                        r4 = 1;
                    }
                    layoutParams5.b += r4;
                    layoutParams5.f = r4;
                    i14--;
                } else if (layoutParams5.b == i31) {
                    j |= j3;
                }
                i32++;
                childMeasureSpec = i34;
                iMax = i33;
                childCount2 = i35;
            }
            z9 = true;
        }
        z = z9;
        int i36 = iMax;
        int i37 = childMeasureSpec;
        int i38 = childCount2;
        boolean z11 = !z5 && i17 == 1;
        if (i14 <= 0 || j == 0 || (i14 >= i17 - 1 && !z11 && iMax2 <= 1)) {
            i4 = i38;
            z2 = z;
        } else {
            float fBitCount = Long.bitCount(j);
            if (!z11) {
                if ((j & 1) != 0 && !((LayoutParams) getChildAt(0).getLayoutParams()).e) {
                    fBitCount -= 0.5f;
                }
                int i39 = i38 - 1;
                if ((j & ((long) (1 << i39))) != 0 && !((LayoutParams) getChildAt(i39).getLayoutParams()).e) {
                    fBitCount -= 0.5f;
                }
            }
            int i40 = fBitCount > 0.0f ? (int) ((i14 * i16) / fBitCount) : 0;
            boolean z12 = z;
            i4 = i38;
            for (int i41 = 0; i41 < i4; i41++) {
                if ((j & ((long) (1 << i41))) != 0) {
                    View childAt3 = getChildAt(i41);
                    LayoutParams layoutParams6 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams6.c = i40;
                        layoutParams6.f = true;
                        if (i41 == 0 && !layoutParams6.e) {
                            ((LinearLayout.LayoutParams) layoutParams6).leftMargin = (-i40) / 2;
                        }
                        z12 = true;
                    } else {
                        if (layoutParams6.a) {
                            layoutParams6.c = i40;
                            layoutParams6.f = true;
                            ((LinearLayout.LayoutParams) layoutParams6).rightMargin = (-i40) / 2;
                            z12 = true;
                        } else {
                            if (i41 != 0) {
                                ((LinearLayout.LayoutParams) layoutParams6).leftMargin = i40 / 2;
                            }
                            if (i41 != i4 - 1) {
                                ((LinearLayout.LayoutParams) layoutParams6).rightMargin = i40 / 2;
                            }
                        }
                    }
                }
            }
            z2 = z12;
        }
        if (z2) {
            int i42 = 0;
            while (i42 < i4) {
                View childAt4 = getChildAt(i42);
                LayoutParams layoutParams7 = (LayoutParams) childAt4.getLayoutParams();
                if (layoutParams7.f) {
                    i7 = i37;
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((layoutParams7.b * i16) + layoutParams7.c, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i7);
                } else {
                    i7 = i37;
                }
                i42++;
                i37 = i7;
            }
        }
        if (i24 != 1073741824) {
            i6 = i25;
            i5 = i36;
        } else {
            i5 = i26;
            i6 = i25;
        }
        setMeasuredDimension(i6, i5);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.x.v = z;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.E = onMenuItemClickListener;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        ActionMenuPresenter actionMenuPresenter = this.x;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton = actionMenuPresenter.n;
        if (overflowMenuButton != null) {
            overflowMenuButton.setImageDrawable(drawable);
        } else {
            actionMenuPresenter.p = true;
            actionMenuPresenter.o = drawable;
        }
    }

    public void setOverflowReserved(boolean z) {
        this.w = z;
    }

    public void setPopupTheme(int i) {
        if (this.v != i) {
            this.v = i;
            if (i == 0) {
                this.u = getContext();
            } else {
                this.u = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.x = actionMenuPresenter;
        actionMenuPresenter.l = this;
        this.t = actionMenuPresenter.g;
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.C = (int) (56.0f * f);
        this.D = (int) (f * 4.0f);
        this.u = context;
        this.v = 0;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final boolean d(MenuBuilder menuBuilder) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final void c(MenuBuilder menuBuilder, boolean z) {
        }
    }
}
