package defpackage;

import android.view.View;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class kc1 implements wr1 {
    public final /* synthetic */ int e;
    public final /* synthetic */ View f;
    public final /* synthetic */ int g;

    public kc1(int i, View view, int i2) {
        this.e = i;
        this.f = view;
        this.g = i2;
    }

    @Override // defpackage.wr1
    public final pt3 k(View view, pt3 pt3Var) {
        int i = pt3Var.a(7).b;
        View view2 = this.f;
        int i2 = this.e;
        if (i2 >= 0) {
            view2.getLayoutParams().height = i2 + i;
            view2.setLayoutParams(view2.getLayoutParams());
        }
        view2.setPadding(view2.getPaddingLeft(), this.g + i, view2.getPaddingRight(), view2.getPaddingBottom());
        return pt3Var;
    }
}
