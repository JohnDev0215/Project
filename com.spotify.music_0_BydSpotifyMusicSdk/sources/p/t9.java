package p;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class t9 extends Spinner {
    public static final int[] q = {R.attr.spinnerMode};
    private final v7 c;
    public final Context e;
    public final e4 k;
    public SpinnerAdapter l;
    public final boolean m;
    public final s9 n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f186p;

    /* JADX WARN: Removed duplicated region for block: B:25:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public t9(android.content.Context r12, android.util.AttributeSet r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.t9.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        Rect rect = this.f186p;
        drawable.getPadding(rect);
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        s9 s9Var = this.n;
        return s9Var != null ? s9Var.b() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        s9 s9Var = this.n;
        return s9Var != null ? s9Var.l() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.n != null ? this.o : super.getDropDownWidth();
    }

    public final s9 getInternalPopup() {
        return this.n;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        s9 s9Var = this.n;
        return s9Var != null ? s9Var.d() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.e;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        s9 s9Var = this.n;
        return s9Var != null ? s9Var.m() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        s9 s9Var = this.n;
        if (s9Var == null || !s9Var.a()) {
            return;
        }
        s9Var.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.n == null || View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        r9 r9Var = (r9) parcelable;
        super.onRestoreInstanceState(r9Var.getSuperState());
        if (!r9Var.c || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new hq(2, this));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        r9 r9Var = new r9(super.onSaveInstanceState());
        s9 s9Var = this.n;
        r9Var.c = s9Var != null && s9Var.a();
        return r9Var;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        e4 e4Var = this.k;
        if (e4Var == null || !e4Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        s9 s9Var = this.n;
        if (s9Var == null) {
            return super.performClick();
        }
        if (s9Var.a()) {
            return true;
        }
        this.n.k(l9.b(this), l9.a(this));
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i) {
        s9 s9Var = this.n;
        if (s9Var == null) {
            super.setDropDownHorizontalOffset(i);
        } else {
            s9Var.i(i);
            s9Var.j(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i) {
        s9 s9Var = this.n;
        if (s9Var != null) {
            s9Var.h(i);
        } else {
            super.setDropDownVerticalOffset(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i) {
        if (this.n != null) {
            this.o = i;
        } else {
            super.setDropDownWidth(i);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        s9 s9Var = this.n;
        if (s9Var != null) {
            s9Var.g(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(l13.f(getPopupContext(), i));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        s9 s9Var = this.n;
        if (s9Var != null) {
            s9Var.f(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.m) {
            this.l = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        s9 s9Var = this.n;
        if (s9Var != null) {
            Context context = this.e;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            o9 o9Var = new o9();
            o9Var.c = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                o9Var.e = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                m9.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
            s9Var.p(o9Var);
        }
    }
}
