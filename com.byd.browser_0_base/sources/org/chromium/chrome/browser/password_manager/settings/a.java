package org.chromium.chrome.browser.password_manager.settings;

import android.content.DialogInterface;
import defpackage.oj2;
import org.chromium.chrome.browser.password_manager.settings.ExportWarningDialogFragment;

/* JADX INFO: compiled from: ExportFlow.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements ExportWarningDialogFragment.a {
    public final /* synthetic */ b e;

    public a(b bVar) {
        this.e = bVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            oj2.U(2, 3, "PasswordManager.PasswordExport.Event");
            b bVar = this.e;
            bVar.a = 2;
            if (bVar.e != null) {
                bVar.a();
            } else {
                bVar.b();
            }
        }
    }
}
