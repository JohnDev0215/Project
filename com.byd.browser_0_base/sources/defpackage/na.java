package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: compiled from: AutocompleteMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class na implements View.OnClickListener {
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ Dialog f;

    public na(Runnable runnable, Dialog dialog) {
        this.e = runnable;
        this.f = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        n3.X0("MobileOmniboxDeleteRequested");
        this.e.run();
        this.f.dismiss();
    }
}
