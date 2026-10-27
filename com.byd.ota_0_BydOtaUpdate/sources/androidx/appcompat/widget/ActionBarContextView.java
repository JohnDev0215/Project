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
import b.b.f;
import b.b.j;
import b.b.p.b;
import b.b.p.j.g;
import b.b.q.e0;
import b.b.q.k0;
import b.f.m.s;
import b.f.m.w;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends b.b.q.a {
    public CharSequence H3;
    public CharSequence I3;
    public View J3;
    public View K3;
    public LinearLayout L3;
    public TextView M3;
    public TextView N3;
    public int O3;
    public int P3;
    public boolean Q3;
    public int R3;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b f157c;

        public a(b bVar) {
            this.f157c = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f157c.c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    @Override // b.b.q.a
    public /* bridge */ /* synthetic */ w f(int i2, long j) {
        return super.f(i2, j);
    }

    public void g() {
        if (this.J3 == null) {
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

    @Override // b.b.q.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // b.b.q.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.I3;
    }

    public CharSequence getTitle() {
        return this.H3;
    }

    public void h(b bVar) {
        View view = this.J3;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.R3, (ViewGroup) this, false);
            this.J3 = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.J3);
        }
        this.J3.findViewById(f.f748i).setOnClickListener(new a(bVar));
        g gVar = (g) bVar.e();
        ActionMenuPresenter actionMenuPresenter = this.x;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.z();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.x = actionMenuPresenter2;
        actionMenuPresenter2.K(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        gVar.c(this.x, this.f986d);
        ActionMenuView actionMenuView = (ActionMenuView) this.x.p(this);
        this.s = actionMenuView;
        s.U(actionMenuView, null);
        addView(this.s, layoutParams);
    }

    public final void i() {
        if (this.L3 == null) {
            LayoutInflater.from(getContext()).inflate(b.b.g.f749a, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.L3 = linearLayout;
            this.M3 = (TextView) linearLayout.findViewById(f.f744e);
            this.N3 = (TextView) this.L3.findViewById(f.f743d);
            if (this.O3 != 0) {
                this.M3.setTextAppearance(getContext(), this.O3);
            }
            if (this.P3 != 0) {
                this.N3.setTextAppearance(getContext(), this.P3);
            }
        }
        this.M3.setText(this.H3);
        this.N3.setText(this.I3);
        boolean z = !TextUtils.isEmpty(this.H3);
        boolean z2 = !TextUtils.isEmpty(this.I3);
        int i2 = 0;
        this.N3.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout2 = this.L3;
        if (!z && !z2) {
            i2 = 8;
        }
        linearLayout2.setVisibility(i2);
        if (this.L3.getParent() == null) {
            addView(this.L3);
        }
    }

    public boolean j() {
        return this.Q3;
    }

    public void k() {
        removeAllViews();
        this.K3 = null;
        this.s = null;
    }

    public boolean l() {
        ActionMenuPresenter actionMenuPresenter = this.x;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.L();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.x;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.C();
            this.x.D();
        }
    }

    @Override // b.b.q.a, android.view.View
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
        accessibilityEvent.setContentDescription(this.H3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        boolean zB = k0.b(this);
        int paddingRight = zB ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.J3;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.J3.getLayoutParams();
            int i6 = zB ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = zB ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iD = b.b.q.a.d(paddingRight, i6, zB);
            paddingRight = b.b.q.a.d(iD + e(this.J3, iD, paddingTop, paddingTop2, zB), i7, zB);
        }
        int iE = paddingRight;
        LinearLayout linearLayout = this.L3;
        if (linearLayout != null && this.K3 == null && linearLayout.getVisibility() != 8) {
            iE += e(this.L3, iE, paddingTop, paddingTop2, zB);
        }
        int i8 = iE;
        View view2 = this.K3;
        if (view2 != null) {
            e(view2, i8, paddingTop, paddingTop2, zB);
        }
        int paddingLeft = zB ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        ActionMenuView actionMenuView = this.s;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zB);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int i4 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i2);
        int size2 = this.y;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i3);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.J3;
        if (view != null) {
            int iC = c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.J3.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.s;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.s, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.L3;
        if (linearLayout != null && this.K3 == null) {
            if (this.Q3) {
                this.L3.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.L3.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.L3.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.K3;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i5 = layoutParams.width;
            int i6 = i5 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i5 >= 0) {
                paddingLeft = Math.min(i5, paddingLeft);
            }
            int i7 = layoutParams.height;
            if (i7 == -2) {
                i4 = Integer.MIN_VALUE;
            }
            if (i7 >= 0) {
                iMin = Math.min(i7, iMin);
            }
            this.K3.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i6), View.MeasureSpec.makeMeasureSpec(iMin, i4));
        }
        if (this.y > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            int measuredHeight = getChildAt(i9).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i8) {
                i8 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i8);
    }

    @Override // b.b.q.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // b.b.q.a
    public void setContentHeight(int i2) {
        this.y = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.K3;
        if (view2 != null) {
            removeView(view2);
        }
        this.K3 = view;
        if (view != null && (linearLayout = this.L3) != null) {
            removeView(linearLayout);
            this.L3 = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.I3 = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.H3 = charSequence;
        i();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.Q3) {
            requestLayout();
        }
        this.Q3 = z;
    }

    @Override // b.b.q.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.k);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        e0 e0VarU = e0.u(context, attributeSet, j.y, i2, 0);
        s.U(this, e0VarU.f(j.z));
        this.O3 = e0VarU.m(j.D, 0);
        this.P3 = e0VarU.m(j.C, 0);
        this.y = e0VarU.l(j.B, 0);
        this.R3 = e0VarU.m(j.A, b.b.g.f752d);
        e0VarU.v();
    }
}
