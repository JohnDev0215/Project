package p;

import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class fj1 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ hj1 c;

    public fj1(hj1 hj1Var) {
        this.c = hj1Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.c.c();
    }
}
