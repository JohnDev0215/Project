package androidx.appcompat.widget;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import l0.t;
import l0.x;
import m.b;
import n.g;
import o.e0;
import o.k0;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends o.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f330j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f331k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f332l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f333m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public LinearLayout f334n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TextView f335o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f336p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f337q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f338r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f339s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f340t;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b f341b;

        public a(b bVar) {
            this.f341b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f341b.c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    @Override // o.a
    public /* bridge */ /* synthetic */ x f(int i3, long j3) {
        return super.f(i3, j3);
    }

    public void g() {
        if (this.f332l == null) {
            k();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // o.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // o.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f331k;
    }

    public CharSequence getTitle() {
        return this.f330j;
    }

    public void h(b bVar) {
        View view = this.f332l;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f340t, (ViewGroup) this, false);
            this.f332l = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f332l);
        }
        this.f332l.findViewById(R$id.action_mode_close_button).setOnClickListener(new a(bVar));
        g gVar = (g) bVar.e();
        ActionMenuPresenter actionMenuPresenter = this.f4222e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.C();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.f4222e = actionMenuPresenter2;
        actionMenuPresenter2.N(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        gVar.c(this.f4222e, this.f4220c);
        ActionMenuView actionMenuView = (ActionMenuView) this.f4222e.s(this);
        this.f4221d = actionMenuView;
        t.m0(actionMenuView, null);
        addView(this.f4221d, layoutParams);
    }

    public final void i() {
        if (this.f334n == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f334n = linearLayout;
            this.f335o = (TextView) linearLayout.findViewById(R$id.action_bar_title);
            this.f336p = (TextView) this.f334n.findViewById(R$id.action_bar_subtitle);
            if (this.f337q != 0) {
                this.f335o.setTextAppearance(getContext(), this.f337q);
            }
            if (this.f338r != 0) {
                this.f336p.setTextAppearance(getContext(), this.f338r);
            }
        }
        this.f335o.setText(this.f330j);
        this.f336p.setText(this.f331k);
        boolean z3 = !TextUtils.isEmpty(this.f330j);
        boolean z4 = !TextUtils.isEmpty(this.f331k);
        int i3 = 0;
        this.f336p.setVisibility(z4 ? 0 : 8);
        LinearLayout linearLayout2 = this.f334n;
        if (!z3 && !z4) {
            i3 = 8;
        }
        linearLayout2.setVisibility(i3);
        if (this.f334n.getParent() == null) {
            addView(this.f334n);
        }
    }

    public boolean j() {
        return this.f339s;
    }

    public void k() {
        removeAllViews();
        this.f333m = null;
        this.f4221d = null;
    }

    public boolean l() {
        ActionMenuPresenter actionMenuPresenter = this.f4222e;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.O();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f4222e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.F();
            this.f4222e.G();
        }
    }

    @Override // o.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.f330j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        boolean zB = k0.b(this);
        int paddingRight = zB ? (i5 - i3) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i6 - i4) - getPaddingTop()) - getPaddingBottom();
        View view = this.f332l;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f332l.getLayoutParams();
            int i7 = zB ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i8 = zB ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iD = o.a.d(paddingRight, i7, zB);
            paddingRight = o.a.d(iD + e(this.f332l, iD, paddingTop, paddingTop2, zB), i8, zB);
        }
        int iE = paddingRight;
        LinearLayout linearLayout = this.f334n;
        if (linearLayout != null && this.f333m == null && linearLayout.getVisibility() != 8) {
            iE += e(this.f334n, iE, paddingTop, paddingTop2, zB);
        }
        int i9 = iE;
        View view2 = this.f333m;
        if (view2 != null) {
            e(view2, i9, paddingTop, paddingTop2, zB);
        }
        int paddingLeft = zB ? getPaddingLeft() : (i5 - i3) - getPaddingRight();
        ActionMenuView actionMenuView = this.f4221d;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zB);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int i5 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i4) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i3);
        int size2 = this.f4223f;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i4);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f332l;
        if (view != null) {
            int iC = c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f332l.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f4221d;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.f4221d, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.f334n;
        if (linearLayout != null && this.f333m == null) {
            if (this.f339s) {
                this.f334n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f334n.getMeasuredWidth();
                boolean z3 = measuredWidth <= paddingLeft;
                if (z3) {
                    paddingLeft -= measuredWidth;
                }
                this.f334n.setVisibility(z3 ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.f333m;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i6 = layoutParams.width;
            int i7 = i6 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i6 >= 0) {
                paddingLeft = Math.min(i6, paddingLeft);
            }
            int i8 = layoutParams.height;
            if (i8 == -2) {
                i5 = Integer.MIN_VALUE;
            }
            if (i8 >= 0) {
                iMin = Math.min(i8, iMin);
            }
            this.f333m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i7), View.MeasureSpec.makeMeasureSpec(iMin, i5));
        }
        if (this.f4223f > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            int measuredHeight = getChildAt(i10).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i9) {
                i9 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i9);
    }

    @Override // o.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // o.a
    public void setContentHeight(int i3) {
        this.f4223f = i3;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f333m;
        if (view2 != null) {
            removeView(view2);
        }
        this.f333m = view;
        if (view != null && (linearLayout = this.f334n) != null) {
            removeView(linearLayout);
            this.f334n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f331k = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.f330j = charSequence;
        i();
    }

    public void setTitleOptional(boolean z3) {
        if (z3 != this.f339s) {
            requestLayout();
        }
        this.f339s = z3;
    }

    @Override // o.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i3) {
        super.setVisibility(i3);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.ActionMode, i3, 0);
        t.m0(this, e0VarV.g(R$styleable.ActionMode_background));
        this.f337q = e0VarV.n(R$styleable.ActionMode_titleTextStyle, 0);
        this.f338r = e0VarV.n(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.f4223f = e0VarV.m(R$styleable.ActionMode_height, 0);
        this.f340t = e0VarV.n(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        e0VarV.w();
    }
}
