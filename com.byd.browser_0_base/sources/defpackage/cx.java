package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: compiled from: ConfirmationDialogHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cx implements View.OnClickListener {
    public final /* synthetic */ Dialog e;
    public final /* synthetic */ ex f;

    public cx(ex exVar, Dialog dialog) {
        this.f = exVar;
        this.e = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.f.f.run();
        this.e.dismiss();
    }
}
