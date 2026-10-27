package org.chromium.content.browser.picker;

import J.N;
import android.content.DialogInterface;
import org.chromium.content.browser.input.DateTimeChooserAndroid;

/* JADX INFO: compiled from: InputDialogContainer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c implements DialogInterface.OnDismissListener {
    public final /* synthetic */ d e;

    public c(d dVar) {
        this.e = dVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        d dVar = this.e;
        if (dVar.c != dialogInterface || dVar.b) {
            return;
        }
        dVar.b = true;
        DateTimeChooserAndroid dateTimeChooserAndroid = DateTimeChooserAndroid.this;
        long j = dateTimeChooserAndroid.a;
        if (j == 0) {
            return;
        }
        N.MCtaZNwj(j, dateTimeChooserAndroid);
    }
}
