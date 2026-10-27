package defpackage;

import android.view.View;

/* JADX INFO: compiled from: DropdownPopupWindowImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class re0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ te0 e;

    public re0(te0 te0Var) {
        this.e = te0Var;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        te0 te0Var = this.e;
        if (view == te0Var.f) {
            te0Var.b();
        }
    }
}
