package p;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class q9 extends ib1 implements s9 {
    public CharSequence J;
    public ListAdapter K;
    public final Rect L;
    public int M;
    public final /* synthetic */ t9 N;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q9(t9 t9Var, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle, 0);
        this.N = t9Var;
        this.L = new Rect();
        this.w = t9Var;
        this.F = true;
        this.G.setFocusable(true);
        this.x = new f6(this, 1, t9Var);
    }

    @Override // p.s9
    public final void f(CharSequence charSequence) {
        this.J = charSequence;
    }

    @Override // p.s9
    public final void i(int i) {
        this.M = i;
    }

    @Override // p.s9
    public final void k(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        d9 d9Var = this.G;
        boolean zIsShowing = d9Var.isShowing();
        s();
        this.G.setInputMethodMode(2);
        c();
        ib0 ib0Var = this.k;
        ib0Var.setChoiceMode(1);
        l9.d(ib0Var, i);
        l9.c(ib0Var, i2);
        t9 t9Var = this.N;
        int selectedItemPosition = t9Var.getSelectedItemPosition();
        ib0 ib0Var2 = this.k;
        if (d9Var.isShowing() && ib0Var2 != null) {
            ib0Var2.setListSelectionHidden(false);
            ib0Var2.setSelection(selectedItemPosition);
            if (ib0Var2.getChoiceMode() != 0) {
                ib0Var2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (zIsShowing || (viewTreeObserver = t9Var.getViewTreeObserver()) == null) {
            return;
        }
        hq hqVar = new hq(3, this);
        viewTreeObserver.addOnGlobalLayoutListener(hqVar);
        this.G.setOnDismissListener(new p9(this, hqVar));
    }

    @Override // p.s9
    public final CharSequence m() {
        return this.J;
    }

    @Override // p.ib1, p.s9
    public final void p(ListAdapter listAdapter) {
        super.p(listAdapter);
        this.K = listAdapter;
    }

    public final void s() {
        int i;
        d9 d9Var = this.G;
        Drawable background = d9Var.getBackground();
        t9 t9Var = this.N;
        if (background != null) {
            background.getPadding(t9Var.f186p);
            boolean zA = ev2.a(t9Var);
            Rect rect = t9Var.f186p;
            i = zA ? rect.right : -rect.left;
        } else {
            Rect rect2 = t9Var.f186p;
            rect2.right = 0;
            rect2.left = 0;
            i = 0;
        }
        int paddingLeft = t9Var.getPaddingLeft();
        int paddingRight = t9Var.getPaddingRight();
        int width = t9Var.getWidth();
        int i2 = t9Var.o;
        if (i2 == -2) {
            int iA = t9Var.a((SpinnerAdapter) this.K, d9Var.getBackground());
            int i3 = t9Var.getContext().getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = t9Var.f186p;
            int i4 = (i3 - rect3.left) - rect3.right;
            if (iA > i4) {
                iA = i4;
            }
            r(Math.max(iA, (width - paddingLeft) - paddingRight));
        } else if (i2 == -1) {
            r((width - paddingLeft) - paddingRight);
        } else {
            r(i2);
        }
        this.n = ev2.a(t9Var) ? (((width - paddingRight) - this.m) - this.M) + i : paddingLeft + this.M + i;
    }
}
