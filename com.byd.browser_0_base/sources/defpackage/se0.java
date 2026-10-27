package defpackage;

import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: DropdownPopupWindowImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class se0 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ te0 e;

    public se0(te0 te0Var) {
        this.e = te0Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        te0 te0Var = this.e;
        te0Var.k.a();
        re0 re0Var = te0Var.i;
        View view = te0Var.f;
        view.removeOnLayoutChangeListener(re0Var);
        view.setTag(null);
    }
}
