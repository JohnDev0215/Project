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
import o0.t;
import o0.x;
import p.b;
import q.g;
import r.e0;
import r.k0;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends r.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f437l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f438m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f439n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f440o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public LinearLayout f441p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f442q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f443r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f444s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f445t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f446u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f447v;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b f448d;

        public a(b bVar) {
            this.f448d = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f448d.c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    @Override // r.a
    public /* bridge */ /* synthetic */ x f(int i8, long j8) {
        return super.f(i8, j8);
    }

    public void g() {
        if (this.f439n == null) {
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

    @Override // r.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // r.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f438m;
    }

    public CharSequence getTitle() {
        return this.f437l;
    }

    public void h(b bVar) {
        View view = this.f439n;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f447v, (ViewGroup) this, false);
            this.f439n = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f439n);
        }
        this.f439n.findViewById(R$id.action_mode_close_button).setOnClickListener(new a(bVar));
        g gVar = (g) bVar.e();
        ActionMenuPresenter actionMenuPresenter = this.f7481g;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.C();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.f7481g = actionMenuPresenter2;
        actionMenuPresenter2.N(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        gVar.c(this.f7481g, this.f7479e);
        ActionMenuView actionMenuView = (ActionMenuView) this.f7481g.s(this);
        this.f7480f = actionMenuView;
        t.o0(actionMenuView, null);
        addView(this.f7480f, layoutParams);
    }

    public final void i() {
        if (this.f441p == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f441p = linearLayout;
            this.f442q = (TextView) linearLayout.findViewById(R$id.action_bar_title);
            this.f443r = (TextView) this.f441p.findViewById(R$id.action_bar_subtitle);
            if (this.f444s != 0) {
                this.f442q.setTextAppearance(getContext(), this.f444s);
            }
            if (this.f445t != 0) {
                this.f443r.setTextAppearance(getContext(), this.f445t);
            }
        }
        this.f442q.setText(this.f437l);
        this.f443r.setText(this.f438m);
        boolean z7 = !TextUtils.isEmpty(this.f437l);
        boolean z8 = !TextUtils.isEmpty(this.f438m);
        int i8 = 0;
        this.f443r.setVisibility(z8 ? 0 : 8);
        LinearLayout linearLayout2 = this.f441p;
        if (!z7 && !z8) {
            i8 = 8;
        }
        linearLayout2.setVisibility(i8);
        if (this.f441p.getParent() == null) {
            addView(this.f441p);
        }
    }

    public boolean j() {
        return this.f446u;
    }

    public void k() {
        removeAllViews();
        this.f440o = null;
        this.f7480f = null;
    }

    public boolean l() {
        ActionMenuPresenter actionMenuPresenter = this.f7481g;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.O();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f7481g;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.F();
            this.f7481g.G();
        }
    }

    @Override // r.a, android.view.View
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
        accessibilityEvent.setContentDescription(this.f437l);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        boolean zB = k0.b(this);
        int paddingRight = zB ? (i10 - i8) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i11 - i9) - getPaddingTop()) - getPaddingBottom();
        View view = this.f439n;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f439n.getLayoutParams();
            int i12 = zB ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i13 = zB ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iD = r.a.d(paddingRight, i12, zB);
            paddingRight = r.a.d(iD + e(this.f439n, iD, paddingTop, paddingTop2, zB), i13, zB);
        }
        int iE = paddingRight;
        LinearLayout linearLayout = this.f441p;
        if (linearLayout != null && this.f440o == null && linearLayout.getVisibility() != 8) {
            iE += e(this.f441p, iE, paddingTop, paddingTop2, zB);
        }
        int i14 = iE;
        View view2 = this.f440o;
        if (view2 != null) {
            e(view2, i14, paddingTop, paddingTop2, zB);
        }
        int paddingLeft = zB ? getPaddingLeft() : (i10 - i8) - getPaddingRight();
        ActionMenuView actionMenuView = this.f7480f;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zB);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        int i10 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i9) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i8);
        int size2 = this.f7482h;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i9);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f439n;
        if (view != null) {
            int iC = c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f439n.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f7480f;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.f7480f, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.f441p;
        if (linearLayout != null && this.f440o == null) {
            if (this.f446u) {
                this.f441p.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f441p.getMeasuredWidth();
                boolean z7 = measuredWidth <= paddingLeft;
                if (z7) {
                    paddingLeft -= measuredWidth;
                }
                this.f441p.setVisibility(z7 ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.f440o;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i11 = layoutParams.width;
            int i12 = i11 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i11 >= 0) {
                paddingLeft = Math.min(i11, paddingLeft);
            }
            int i13 = layoutParams.height;
            if (i13 == -2) {
                i10 = Integer.MIN_VALUE;
            }
            if (i13 >= 0) {
                iMin = Math.min(i13, iMin);
            }
            this.f440o.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i12), View.MeasureSpec.makeMeasureSpec(iMin, i10));
        }
        if (this.f7482h > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            int measuredHeight = getChildAt(i15).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i14) {
                i14 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i14);
    }

    @Override // r.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // r.a
    public void setContentHeight(int i8) {
        this.f7482h = i8;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f440o;
        if (view2 != null) {
            removeView(view2);
        }
        this.f440o = view;
        if (view != null && (linearLayout = this.f441p) != null) {
            removeView(linearLayout);
            this.f441p = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f438m = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.f437l = charSequence;
        i();
    }

    public void setTitleOptional(boolean z7) {
        if (z7 != this.f446u) {
            requestLayout();
        }
        this.f446u = z7;
    }

    @Override // r.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i8) {
        super.setVisibility(i8);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.ActionMode, i8, 0);
        t.o0(this, e0VarV.g(R$styleable.ActionMode_background));
        this.f444s = e0VarV.n(R$styleable.ActionMode_titleTextStyle, 0);
        this.f445t = e0VarV.n(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.f7482h = e0VarV.m(R$styleable.ActionMode_height, 0);
        this.f447v = e0VarV.n(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        e0VarV.w();
    }
}
