package org.chromium.content.browser;

import android.app.Activity;
import android.util.SparseArray;
import defpackage.fn1;
import defpackage.kt3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class ContentNfcDelegate implements NfcDelegate {
    @CalledByNative
    public static ContentNfcDelegate create() {
        return new ContentNfcDelegate();
    }

    @Override // org.chromium.device.nfc.NfcDelegate
    public final void a(int i) {
        SparseArray<NfcHost> sparseArray = NfcHost.h;
        NfcHost nfcHost = sparseArray.get(i);
        nfcHost.g = null;
        kt3 kt3VarE = kt3.e(nfcHost.e);
        if (kt3VarE != null) {
            kt3VarE.e.c(nfcHost);
        }
        sparseArray.remove(nfcHost.f);
    }

    @Override // org.chromium.device.nfc.NfcDelegate
    public final void b(fn1.a aVar, int i) {
        NfcHost nfcHost = NfcHost.h.get(i);
        nfcHost.g = aVar;
        WebContents webContents = nfcHost.e;
        kt3 kt3VarE = kt3.e(webContents);
        if (kt3VarE != null) {
            kt3VarE.b(nfcHost);
        }
        WindowAndroid windowAndroidV = webContents.V();
        nfcHost.g.onResult(windowAndroidV != null ? (Activity) windowAndroidV.k().get() : null);
    }
}
