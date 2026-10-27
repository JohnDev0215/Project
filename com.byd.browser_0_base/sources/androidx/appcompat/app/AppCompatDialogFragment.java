package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import defpackage.r90;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDialogFragment extends r90 {
    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        return new AppCompatDialog(K(), this.e0);
    }

    @Override // defpackage.r90
    public final void L0(Dialog dialog, int i) {
        if (!(dialog instanceof AppCompatDialog)) {
            super.L0(dialog, i);
            return;
        }
        AppCompatDialog appCompatDialog = (AppCompatDialog) dialog;
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        appCompatDialog.c().x(1);
    }
}
