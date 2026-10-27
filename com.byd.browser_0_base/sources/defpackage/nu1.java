package defpackage;

import android.app.Dialog;
import android.content.Context;

/* JADX INFO: compiled from: PageInfoDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nu1 extends Dialog {
    public final /* synthetic */ pu1 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nu1(pu1 pu1Var, Context context, int i) {
        super(context, i);
        this.e = pu1Var;
    }

    public static void a(nu1 nu1Var) {
        super.dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        pu1 pu1Var = this.e;
        pu1Var.getClass();
        pu1Var.getClass();
        pu1Var.a.removeCallbacks(null);
        super.dismiss();
    }
}
