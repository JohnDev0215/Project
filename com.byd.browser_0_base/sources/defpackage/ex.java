package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ConfirmationDialogHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ex implements DialogInterface.OnClickListener {
    public final WeakReference<Context> e;
    public Runnable f;

    public ex(WeakReference<Context> weakReference) {
        this.e = weakReference;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f.run();
    }
}
