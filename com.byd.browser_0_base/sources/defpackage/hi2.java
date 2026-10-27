package defpackage;

import J.N;
import android.content.Intent;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SelectionPopupControllerImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hi2 implements WindowAndroid.c {
    public final /* synthetic */ SelectionPopupControllerImpl a;

    public hi2(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.a = selectionPopupControllerImpl;
    }

    @Override // org.chromium.ui.base.WindowAndroid.c
    public final void b(Intent intent, int i) {
        CharSequence charSequenceExtra;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.a;
        WebContentsImpl webContentsImpl = selectionPopupControllerImpl.i;
        if (webContentsImpl == null || i != -1 || intent == null || !selectionPopupControllerImpl.C || !selectionPopupControllerImpl.u || (charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT")) == null) {
            return;
        }
        String string = charSequenceExtra.toString();
        webContentsImpl.R();
        N.MevqfbP8(webContentsImpl.f, string);
    }
}
