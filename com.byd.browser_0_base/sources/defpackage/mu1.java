package defpackage;

import android.view.View;

/* JADX INFO: compiled from: PageInfoDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mu1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ pu1 e;

    public mu1(pu1 pu1Var) {
        this.e = pu1Var;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        pu1 pu1Var = this.e;
        pu1Var.a.removeOnLayoutChangeListener(this);
        pu1Var.a.setVisibility(0);
    }
}
