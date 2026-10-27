package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.AbsActionBarView;
import androidx.appcompat.widget.ActionMenuPresenter;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends AbsActionBarView {
    public CharSequence m;
    public CharSequence n;
    public View o;
    public View p;
    public View q;
    public LinearLayout r;
    public TextView s;
    public TextView t;
    public final int u;
    public final int v;
    public boolean w;
    public final int x;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public final void e(final ActionMode actionMode) {
        View view = this.o;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.x, (ViewGroup) this, false);
            this.o = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.o);
        }
        View viewFindViewById = this.o.findViewById(R.id.action_mode_close_button);
        this.p = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: androidx.appcompat.widget.ActionBarContextView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                actionMode.c();
            }
        });
        MenuBuilder menuBuilderE = actionMode.e();
        ActionMenuPresenter actionMenuPresenter = this.h;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.o();
            ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu = actionMenuPresenter.y;
            if (actionButtonSubmenu != null && actionButtonSubmenu.b()) {
                actionButtonSubmenu.j.dismiss();
            }
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.h = actionMenuPresenter2;
        actionMenuPresenter2.q = true;
        actionMenuPresenter2.r = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        menuBuilderE.b(this.h, this.f);
        ActionMenuPresenter actionMenuPresenter3 = this.h;
        MenuView menuView = actionMenuPresenter3.l;
        if (menuView == null) {
            MenuView menuView2 = (MenuView) actionMenuPresenter3.h.inflate(actionMenuPresenter3.j, (ViewGroup) this, false);
            actionMenuPresenter3.l = menuView2;
            menuView2.b(actionMenuPresenter3.g);
            actionMenuPresenter3.d(true);
        }
        MenuView menuView3 = actionMenuPresenter3.l;
        if (menuView != menuView3) {
            ((ActionMenuView) menuView3).setPresenter(actionMenuPresenter3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) menuView3;
        this.g = actionMenuView;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(actionMenuView, null);
        addView(this.g, layoutParams);
    }

    public final void f() {
        if (this.r == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.r = linearLayout;
            this.s = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.t = (TextView) this.r.findViewById(R.id.action_bar_subtitle);
            int i = this.u;
            if (i != 0) {
                this.s.setTextAppearance(getContext(), i);
            }
            int i2 = this.v;
            if (i2 != 0) {
                this.t.setTextAppearance(getContext(), i2);
            }
        }
        this.s.setText(this.m);
        this.t.setText(this.n);
        boolean z = !TextUtils.isEmpty(this.m);
        boolean z2 = !TextUtils.isEmpty(this.n);
        int i3 = 0;
        this.t.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout2 = this.r;
        if (!z && !z2) {
            i3 = 8;
        }
        linearLayout2.setVisibility(i3);
        if (this.r.getParent() == null) {
            addView(this.r);
        }
    }

    public final void g() {
        removeAllViews();
        this.q = null;
        this.g = null;
        this.h = null;
        View view = this.p;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.n;
    }

    public CharSequence getTitle() {
        return this.m;
    }

    public final in3 h(int i, long j) {
        in3 in3Var = this.j;
        if (in3Var != null) {
            in3Var.b();
        }
        AbsActionBarView.VisibilityAnimListener visibilityAnimListener = this.e;
        if (i != 0) {
            in3 in3VarB = om3.b(this);
            in3VarB.a(0.0f);
            in3VarB.c(j);
            AbsActionBarView.this.j = in3VarB;
            visibilityAnimListener.b = i;
            in3VarB.d(visibilityAnimListener);
            return in3VarB;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        in3 in3VarB2 = om3.b(this);
        in3VarB2.a(1.0f);
        in3VarB2.c(j);
        AbsActionBarView.this.j = in3VarB2;
        visibilityAnimListener.b = i;
        in3VarB2.d(visibilityAnimListener);
        return in3VarB2;
    }

    public final boolean i() {
        ActionMenuPresenter actionMenuPresenter = this.h;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.r();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.h;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.o();
            ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu = this.h.y;
            if (actionButtonSubmenu == null || !actionButtonSubmenu.b()) {
                return;
            }
            actionButtonSubmenu.j.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean zA = ViewUtils.a(this);
        int paddingRight = zA ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.o;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.o.getLayoutParams();
            int i5 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i7 = zA ? paddingRight - i5 : paddingRight + i5;
            int iD = i7 + AbsActionBarView.d(i7, paddingTop, paddingTop2, this.o, zA);
            paddingRight = zA ? iD - i6 : iD + i6;
        }
        LinearLayout linearLayout = this.r;
        if (linearLayout != null && this.q == null && linearLayout.getVisibility() != 8) {
            paddingRight += AbsActionBarView.d(paddingRight, paddingTop, paddingTop2, this.r, zA);
        }
        View view2 = this.q;
        if (view2 != null) {
            AbsActionBarView.d(paddingRight, paddingTop, paddingTop2, view2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.g;
        if (actionMenuView != null) {
            AbsActionBarView.d(paddingLeft, paddingTop, paddingTop2, actionMenuView, !zA);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int i3 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = this.i;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i2);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.o;
        if (view != null) {
            int iC = AbsActionBarView.c(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.o.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.g;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = AbsActionBarView.c(this.g, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.r;
        if (linearLayout != null && this.q == null) {
            if (this.w) {
                this.r.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.r.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.r.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = AbsActionBarView.c(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.q;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i4 = layoutParams.width;
            int i5 = i4 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i4 >= 0) {
                paddingLeft = Math.min(i4, paddingLeft);
            }
            int i6 = layoutParams.height;
            if (i6 == -2) {
                i3 = Integer.MIN_VALUE;
            }
            if (i6 >= 0) {
                iMin = Math.min(i6, iMin);
            }
            this.q.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i5), View.MeasureSpec.makeMeasureSpec(iMin, i3));
        }
        if (this.i > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            int measuredHeight = getChildAt(i8).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i7) {
                i7 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i7);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public void setContentHeight(int i) {
        this.i = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.q;
        if (view2 != null) {
            removeView(view2);
        }
        this.q = view;
        if (view != null && (linearLayout = this.r) != null) {
            removeView(linearLayout);
            this.r = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.n = charSequence;
        f();
    }

    public void setTitle(CharSequence charSequence) {
        this.m = charSequence;
        f();
        om3.l(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.w) {
            requestLayout();
        }
        this.w = z;
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionMode, i, 0);
        TintTypedArray tintTypedArray = new TintTypedArray(context, typedArrayObtainStyledAttributes);
        Drawable drawableE = tintTypedArray.e(R.styleable.ActionMode_background);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(this, drawableE);
        this.u = tintTypedArray.i(R.styleable.ActionMode_titleTextStyle, 0);
        this.v = tintTypedArray.i(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.i = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ActionMode_height, 0);
        this.x = tintTypedArray.i(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        tintTypedArray.n();
    }
}
