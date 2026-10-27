package defpackage;

import android.content.DialogInterface;
import org.chromium.content.browser.input.d;

/* JADX INFO: compiled from: SelectPopupDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vh2 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ d e;

    public vh2(d dVar) {
        this.e = dVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        this.e.d(null);
    }
}
