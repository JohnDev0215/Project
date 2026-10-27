package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: compiled from: AutocompleteMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oa implements View.OnClickListener {
    public final /* synthetic */ Dialog e;

    public oa(Dialog dialog) {
        this.e = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.e.dismiss();
    }
}
