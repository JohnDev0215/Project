package defpackage;

import android.content.DialogInterface;

/* JADX INFO: compiled from: ColorPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wu implements DialogInterface.OnClickListener {
    public final /* synthetic */ av e;

    public wu(av avVar) {
        this.e = avVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        av avVar = this.e;
        int i2 = avVar.j;
        bs1 bs1Var = avVar.h;
        if (bs1Var != null) {
            bs1Var.a(i2);
        }
    }
}
