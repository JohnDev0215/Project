package p;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class q90 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ s90 c;

    public q90(s90 s90Var) {
        this.c = s90Var;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        s90 s90Var = this.c;
        Dialog dialog = s90Var.m0;
        if (dialog != null) {
            s90Var.onDismiss(dialog);
        }
    }
}
