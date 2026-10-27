package org.chromium.components.browser_ui.photo_picker;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import org.chromium.components.browser_ui.photo_picker.e;

/* JADX INFO: compiled from: ImageDecoder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends e.a {
    public boolean f;

    public static void A(Bundle bundle, f fVar) {
        try {
            fVar.n(bundle);
        } catch (RemoteException e) {
            Log.e("cr_ImageDecoder", "Remote error while replying: " + e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // org.chromium.components.browser_ui.photo_picker.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(android.os.Bundle r21, org.chromium.components.browser_ui.photo_picker.f r22) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.photo_picker.g.e(android.os.Bundle, org.chromium.components.browser_ui.photo_picker.f):void");
    }
}
