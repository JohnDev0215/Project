package p;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class p90 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ s90 c;

    public p90(s90 s90Var) {
        this.c = s90Var;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        s90 s90Var = this.c;
        Dialog dialog = s90Var.m0;
        if (dialog != null) {
            s90Var.onCancel(dialog);
        }
    }
}
