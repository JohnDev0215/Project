package defpackage;

import android.content.DialogInterface;

/* JADX INFO: compiled from: ColorPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yu implements DialogInterface.OnCancelListener {
    public final /* synthetic */ av e;

    public yu(av avVar) {
        this.e = avVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        av avVar = this.e;
        int i = avVar.i;
        bs1 bs1Var = avVar.h;
        if (bs1Var != null) {
            bs1Var.a(i);
        }
    }
}
