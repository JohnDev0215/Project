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

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends androidx.appcompat.widget.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private CharSequence f765n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private CharSequence f766o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private View f767p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private View f768q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private View f769r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private LinearLayout f770s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private TextView f771t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private TextView f772u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f773v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f774w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f775x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f776y;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ i.b f777e;

        a(i.b bVar) {
            this.f777e = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f777e.c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void i() {
        if (this.f770s == null) {
            LayoutInflater.from(getContext()).inflate(e.g.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f770s = linearLayout;
            this.f771t = (TextView) linearLayout.findViewById(e.f.action_bar_title);
            this.f772u = (TextView) this.f770s.findViewById(e.f.action_bar_subtitle);
            if (this.f773v != 0) {
                this.f771t.setTextAppearance(getContext(), this.f773v);
            }
            if (this.f774w != 0) {
                this.f772u.setTextAppearance(getContext(), this.f774w);
            }
        }
        this.f771t.setText(this.f765n);
        this.f772u.setText(this.f766o);
        boolean z6 = !TextUtils.isEmpty(this.f765n);
        boolean z7 = !TextUtils.isEmpty(this.f766o);
        int i7 = 0;
        this.f772u.setVisibility(z7 ? 0 : 8);
        LinearLayout linearLayout2 = this.f770s;
        if (!z6 && !z7) {
            i7 = 8;
        }
        linearLayout2.setVisibility(i7);
        if (this.f770s.getParent() == null) {
            addView(this.f770s);
        }
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ i0.y f(int i7, long j7) {
        return super.f(i7, j7);
    }

    public void g() {
        if (this.f767p == null) {
            k();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f766o;
    }

    public CharSequence getTitle() {
        return this.f765n;
    }

    public void h(i.b bVar) {
        View view = this.f767p;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f776y, (ViewGroup) this, false);
            this.f767p = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f767p);
        }
        View viewFindViewById = this.f767p.findViewById(e.f.action_mode_close_button);
        this.f768q = viewFindViewById;
        viewFindViewById.setOnClickListener(new a(bVar));
        androidx.appcompat.view.menu.e eVar = (androidx.appcompat.view.menu.e) bVar.e();
        ActionMenuPresenter actionMenuPresenter = this.f986i;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.y();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.f986i = actionMenuPresenter2;
        actionMenuPresenter2.J(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        eVar.c(this.f986i, this.f984g);
        ActionMenuView actionMenuView = (ActionMenuView) this.f986i.o(this);
        this.f985h = actionMenuView;
        i0.u.n0(actionMenuView, null);
        addView(this.f985h, layoutParams);
    }

    public boolean j() {
        return this.f775x;
    }

    public void k() {
        removeAllViews();
        this.f769r = null;
        this.f985h = null;
        this.f986i = null;
        View view = this.f768q;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    public boolean l() {
        ActionMenuPresenter actionMenuPresenter = this.f986i;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.K();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f986i;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.B();
            this.f986i.C();
        }
    }

    @Override // androidx.appcompat.widget.a, android.view.View
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
        accessibilityEvent.setContentDescription(this.f765n);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        boolean zB = w0.b(this);
        int paddingRight = zB ? (i9 - i7) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i10 - i8) - getPaddingTop()) - getPaddingBottom();
        View view = this.f767p;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f767p.getLayoutParams();
            int i11 = zB ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i12 = zB ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iD = androidx.appcompat.widget.a.d(paddingRight, i11, zB);
            paddingRight = androidx.appcompat.widget.a.d(iD + e(this.f767p, iD, paddingTop, paddingTop2, zB), i12, zB);
        }
        int iE = paddingRight;
        LinearLayout linearLayout = this.f770s;
        if (linearLayout != null && this.f769r == null && linearLayout.getVisibility() != 8) {
            iE += e(this.f770s, iE, paddingTop, paddingTop2, zB);
        }
        int i13 = iE;
        View view2 = this.f769r;
        if (view2 != null) {
            e(view2, i13, paddingTop, paddingTop2, zB);
        }
        int paddingLeft = zB ? getPaddingLeft() : (i9 - i7) - getPaddingRight();
        ActionMenuView actionMenuView = this.f985h;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zB);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int i9 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i8) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i7);
        int size2 = this.f987j;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i8);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f767p;
        if (view != null) {
            int iC = c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f767p.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f985h;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.f985h, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.f770s;
        if (linearLayout != null && this.f769r == null) {
            if (this.f775x) {
                this.f770s.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f770s.getMeasuredWidth();
                boolean z6 = measuredWidth <= paddingLeft;
                if (z6) {
                    paddingLeft -= measuredWidth;
                }
                this.f770s.setVisibility(z6 ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.f769r;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i10 = layoutParams.width;
            int i11 = i10 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i10 >= 0) {
                paddingLeft = Math.min(i10, paddingLeft);
            }
            int i12 = layoutParams.height;
            if (i12 == -2) {
                i9 = Integer.MIN_VALUE;
            }
            if (i12 >= 0) {
                iMin = Math.min(i12, iMin);
            }
            this.f769r.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i11), View.MeasureSpec.makeMeasureSpec(iMin, i9));
        }
        if (this.f987j > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            int measuredHeight = getChildAt(i14).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i13) {
                i13 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i13);
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.widget.a
    public void setContentHeight(int i7) {
        this.f987j = i7;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f769r;
        if (view2 != null) {
            removeView(view2);
        }
        this.f769r = view;
        if (view != null && (linearLayout = this.f770s) != null) {
            removeView(linearLayout);
            this.f770s = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f766o = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.f765n = charSequence;
        i();
    }

    public void setTitleOptional(boolean z6) {
        if (z6 != this.f775x) {
            requestLayout();
        }
        this.f775x = z6;
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i7) {
        super.setVisibility(i7);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        s0 s0VarV = s0.v(context, attributeSet, e.j.ActionMode, i7, 0);
        i0.u.n0(this, s0VarV.g(e.j.ActionMode_background));
        this.f773v = s0VarV.n(e.j.ActionMode_titleTextStyle, 0);
        this.f774w = s0VarV.n(e.j.ActionMode_subtitleTextStyle, 0);
        this.f987j = s0VarV.m(e.j.ActionMode_height, 0);
        this.f776y = s0VarV.n(e.j.ActionMode_closeItemLayout, e.g.abc_action_mode_close_item_material);
        s0VarV.w();
    }
}
