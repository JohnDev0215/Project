package p;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class dr1 {
    public final y12 a;
    public int b = Integer.MIN_VALUE;
    public final Rect c = new Rect();

    public dr1(y12 y12Var) {
        this.a = y12Var;
    }

    public static cr1 a(y12 y12Var, int i) {
        if (i == 0) {
            return new cr1(y12Var, 0);
        }
        int i2 = 1;
        if (i == 1) {
            return new cr1(y12Var, i2);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public final int h() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return g() - this.b;
    }

    public abstract int i(View view);

    public abstract int j(View view);

    public abstract void k(int i);
}
