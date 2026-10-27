package p;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class r3 extends ViewGroup.MarginLayoutParams {
    public int a;

    public r3(r3 r3Var) {
        super((ViewGroup.MarginLayoutParams) r3Var);
        this.a = 0;
        this.a = r3Var.a;
    }

    public r3(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.a = 0;
    }
}
