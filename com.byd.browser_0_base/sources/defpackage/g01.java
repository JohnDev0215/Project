package defpackage;

import android.content.DialogInterface;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: InputDialogContainer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g01 implements DialogInterface.OnClickListener {
    public final /* synthetic */ d e;

    public g01(d dVar) {
        this.e = dVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.e.a();
    }
}
