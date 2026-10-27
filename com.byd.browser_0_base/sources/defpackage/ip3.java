package defpackage;

import android.app.Activity;
import android.content.DialogInterface;

/* JADX INFO: compiled from: WebApkOfflineDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ip3 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Activity e;

    public ip3(Activity activity) {
        this.e = activity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.e.finishAndRemoveTask();
    }
}
