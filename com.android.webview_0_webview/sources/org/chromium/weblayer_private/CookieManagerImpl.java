package org.chromium.weblayer_private;

import J.N;
import android.os.RemoteException;
import defpackage.AbstractBinderC1275oR;
import defpackage.C0571bs;
import defpackage.ZQ;
import java.lang.ref.WeakReference;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class CookieManagerImpl extends AbstractBinderC1275oR {
    public long a;

    public CookieManagerImpl(long j) {
        this.a = j;
    }

    public static final void c(WeakReference weakReference, int i) {
        CookieManagerImpl cookieManagerImpl = (CookieManagerImpl) weakReference.get();
        if (cookieManagerImpl == null || cookieManagerImpl.a == 0) {
            return;
        }
        N.MGwEaaJt(cookieManagerImpl.a, i);
    }

    public static void onCookieChange(ICookieChangedCallbackClient iCookieChangedCallbackClient, String str, int i) {
        int i2 = 1;
        switch (i) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                i2 = 0;
                break;
            case 2:
                i2 = 2;
                break;
            case 3:
                i2 = 3;
                break;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                i2 = 4;
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                i2 = 5;
                break;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                i2 = 6;
                break;
        }
        try {
            iCookieChangedCallbackClient.g(str, i2);
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }
}
