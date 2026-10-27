package androidx.appcompat.widget;

import F1.H;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;
import h6.f;
import n.AbstractC1850a;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f11297m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f11298n;
    public CharSequence o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f11299p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f11300q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public LinearLayout f11301r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextView f11302s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public TextView f11303t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f11304u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f11305v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f11306w;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        int resourceId;
        super(context, attributeSet, R.attr.actionModeStyle);
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            new ContextThemeWrapper(context, typedValue.resourceId);
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC1850a.d, R.attr.actionModeStyle, 0);
        setBackground((!typedArrayObtainStyledAttributes.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(0) : f.k(context, resourceId));
        this.f11304u = typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.f11305v = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        this.l = typedArrayObtainStyledAttributes.getLayoutDimension(3, 0);
        typedArrayObtainStyledAttributes.getResourceId(2, R.layout.abc_action_mode_close_item_material);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static int b(View view, int i7, int i10, int i11, boolean z6) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i12 = ((i11 - measuredHeight) / 2) + i10;
        if (z6) {
            view.layout(i7 - measuredWidth, i12, i7, measuredHeight + i12);
        } else {
            view.layout(i7, i12, i7 + measuredWidth, measuredHeight + i12);
        }
        return z6 ? -measuredWidth : measuredWidth;
    }

    public final void a() {
        if (this.f11301r == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f11301r = linearLayout;
            this.f11302s = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.f11303t = (TextView) this.f11301r.findViewById(R.id.action_bar_subtitle);
            int i7 = this.f11304u;
            if (i7 != 0) {
                this.f11302s.setTextAppearance(getContext(), i7);
            }
            int i10 = this.f11305v;
            if (i10 != 0) {
                this.f11303t.setTextAppearance(getContext(), i10);
            }
        }
        this.f11302s.setText(this.o);
        this.f11303t.setText(this.f11299p);
        boolean zIsEmpty = TextUtils.isEmpty(this.o);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.f11299p);
        this.f11303t.setVisibility(!zIsEmpty2 ? 0 : 8);
        this.f11301r.setVisibility((zIsEmpty && zIsEmpty2) ? 8 : 0);
        if (this.f11301r.getParent() == null) {
            addView(this.f11301r);
        }
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i7) {
        if (i7 != getVisibility()) {
            super.setVisibility(i7);
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

    public int getAnimatedVisibility() {
        return getVisibility();
    }

    public int getContentHeight() {
        return this.l;
    }

    public CharSequence getSubtitle() {
        return this.f11299p;
    }

    public CharSequence getTitle() {
        return this.o;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC1850a.f19352a, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(13, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f11298n = false;
        }
        if (!this.f11298n) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f11298n = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f11298n = false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        boolean z10 = getLayoutDirection() == 1;
        int paddingRight = z10 ? (i11 - i7) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i12 - i10) - getPaddingTop()) - getPaddingBottom();
        LinearLayout linearLayout = this.f11301r;
        if (linearLayout != null && this.f11300q == null && linearLayout.getVisibility() != 8) {
            paddingRight += b(this.f11301r, paddingRight, paddingTop, paddingTop2, z10);
        }
        View view = this.f11300q;
        if (view != null) {
            b(view, paddingRight, paddingTop, paddingTop2, z10);
        }
        if (z10) {
            getPaddingLeft();
        } else {
            getPaddingRight();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        int mode = View.MeasureSpec.getMode(i7);
        int i11 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i10) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i7);
        int size2 = this.l;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i10);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        LinearLayout linearLayout = this.f11301r;
        if (linearLayout != null && this.f11300q == null) {
            if (this.f11306w) {
                this.f11301r.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f11301r.getMeasuredWidth();
                boolean z6 = measuredWidth <= paddingLeft;
                if (z6) {
                    paddingLeft -= measuredWidth;
                }
                this.f11301r.setVisibility(z6 ? 0 : 8);
            } else {
                linearLayout.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, Integer.MIN_VALUE), iMakeMeasureSpec);
                paddingLeft = Math.max(0, paddingLeft - linearLayout.getMeasuredWidth());
            }
        }
        View view = this.f11300q;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int i12 = layoutParams.width;
            int i13 = i12 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i12 >= 0) {
                paddingLeft = Math.min(i12, paddingLeft);
            }
            int i14 = layoutParams.height;
            if (i14 == -2) {
                i11 = Integer.MIN_VALUE;
            }
            if (i14 >= 0) {
                iMin = Math.min(i14, iMin);
            }
            this.f11300q.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i13), View.MeasureSpec.makeMeasureSpec(iMin, i11));
        }
        if (this.l > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            int measuredHeight = getChildAt(i16).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i15) {
                i15 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i15);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f11297m = false;
        }
        if (!this.f11297m) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f11297m = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f11297m = false;
        }
        return true;
    }

    public void setContentHeight(int i7) {
        this.l = i7;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f11300q;
        if (view2 != null) {
            removeView(view2);
        }
        this.f11300q = view;
        if (view != null && (linearLayout = this.f11301r) != null) {
            removeView(linearLayout);
            this.f11301r = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f11299p = charSequence;
        a();
    }

    public void setTitle(CharSequence charSequence) {
        this.o = charSequence;
        a();
        H.h(this, charSequence);
    }

    public void setTitleOptional(boolean z6) {
        if (z6 != this.f11306w) {
            requestLayout();
        }
        this.f11306w = z6;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
