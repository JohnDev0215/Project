package org.chromium.chrome.browser.browsing_data;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.browsing_data.ConfirmImportantSitesDialogFragment;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: ConfirmImportantSitesDialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ ConfirmImportantSitesDialogFragment.d e;
    public final /* synthetic */ String f;
    public final /* synthetic */ ConfirmImportantSitesDialogFragment.c g;

    public a(ConfirmImportantSitesDialogFragment.c cVar, ConfirmImportantSitesDialogFragment.d dVar, String str) {
        this.g = cVar;
        this.e = dVar;
        this.f = str;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        ConfirmImportantSitesDialogFragment.d dVar = this.e;
        if (this != dVar.d) {
            return;
        }
        String str = this.f;
        ConfirmImportantSitesDialogFragment.c cVar = this.g;
        dVar.b.setImageDrawable(org.chromium.chrome.browser.ui.favicon.a.e(bitmap, str, i, cVar.g, ConfirmImportantSitesDialogFragment.this.N(), cVar.f));
    }
}
