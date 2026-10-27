package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: compiled from: AddExceptionPreference.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q3 implements View.OnClickListener {
    public final /* synthetic */ Dialog e;

    public q3(Dialog dialog) {
        this.e = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.e.dismiss();
    }
}
