package defpackage;

import android.widget.Toast;

/* JADX INFO: compiled from: ToastManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fb3 extends Toast.Callback {
    public final /* synthetic */ Runnable a;

    public fb3(db3 db3Var) {
        this.a = db3Var;
    }

    @Override // android.widget.Toast.Callback
    public final void onToastHidden() {
        this.a.run();
    }
}
