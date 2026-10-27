package p;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class cr1 extends dr1 {
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ cr1(y12 y12Var, int i) {
        super(y12Var);
        this.d = i;
    }

    @Override // p.dr1
    public final int b(View view) {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                z12 z12Var = (z12) view.getLayoutParams();
                y12Var.getClass();
                return view.getRight() + ((z12) view.getLayoutParams()).b.right + ((ViewGroup.MarginLayoutParams) z12Var).rightMargin;
            default:
                z12 z12Var2 = (z12) view.getLayoutParams();
                y12Var.getClass();
                return view.getBottom() + ((z12) view.getLayoutParams()).b.bottom + ((ViewGroup.MarginLayoutParams) z12Var2).bottomMargin;
        }
    }

    @Override // p.dr1
    public final int c(View view) {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                z12 z12Var = (z12) view.getLayoutParams();
                y12Var.getClass();
                Rect rect = ((z12) view.getLayoutParams()).b;
                return view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) z12Var).leftMargin + ((ViewGroup.MarginLayoutParams) z12Var).rightMargin;
            default:
                z12 z12Var2 = (z12) view.getLayoutParams();
                y12Var.getClass();
                Rect rect2 = ((z12) view.getLayoutParams()).b;
                return view.getMeasuredHeight() + rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) z12Var2).topMargin + ((ViewGroup.MarginLayoutParams) z12Var2).bottomMargin;
        }
    }

    @Override // p.dr1
    public final int d(View view) {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                z12 z12Var = (z12) view.getLayoutParams();
                y12Var.getClass();
                return (view.getLeft() - ((z12) view.getLayoutParams()).b.left) - ((ViewGroup.MarginLayoutParams) z12Var).leftMargin;
            default:
                z12 z12Var2 = (z12) view.getLayoutParams();
                y12Var.getClass();
                return (view.getTop() - ((z12) view.getLayoutParams()).b.top) - ((ViewGroup.MarginLayoutParams) z12Var2).topMargin;
        }
    }

    @Override // p.dr1
    public final int e() {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                return y12Var.n - y12Var.B();
            default:
                return y12Var.o - y12Var.z();
        }
    }

    @Override // p.dr1
    public final int f() {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                return y12Var.A();
            default:
                return y12Var.C();
        }
    }

    @Override // p.dr1
    public final int g() {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                return (y12Var.n - y12Var.A()) - y12Var.B();
            default:
                return (y12Var.o - y12Var.C()) - y12Var.z();
        }
    }

    @Override // p.dr1
    public final int i(View view) {
        int i = this.d;
        Rect rect = this.c;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                y12Var.G(view, rect);
                return rect.right;
            default:
                y12Var.G(view, rect);
                return rect.bottom;
        }
    }

    @Override // p.dr1
    public final int j(View view) {
        int i = this.d;
        Rect rect = this.c;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                y12Var.G(view, rect);
                return rect.left;
            default:
                y12Var.G(view, rect);
                return rect.top;
        }
    }

    @Override // p.dr1
    public final void k(int i) {
        int i2 = this.d;
        y12 y12Var = this.a;
        switch (i2) {
            case 0:
                y12Var.K(i);
                break;
            default:
                y12Var.L(i);
                break;
        }
    }

    public final int l(View view) {
        int i = this.d;
        y12 y12Var = this.a;
        switch (i) {
            case 0:
                z12 z12Var = (z12) view.getLayoutParams();
                y12Var.getClass();
                Rect rect = ((z12) view.getLayoutParams()).b;
                return view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) z12Var).topMargin + ((ViewGroup.MarginLayoutParams) z12Var).bottomMargin;
            default:
                z12 z12Var2 = (z12) view.getLayoutParams();
                y12Var.getClass();
                Rect rect2 = ((z12) view.getLayoutParams()).b;
                return view.getMeasuredWidth() + rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) z12Var2).leftMargin + ((ViewGroup.MarginLayoutParams) z12Var2).rightMargin;
        }
    }
}
