package org.chromium.chrome.browser.password_manager;

import defpackage.ay1;
import defpackage.vd1;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordSyncControllerDelegateBridgeImpl {
    public PasswordSyncControllerDelegateBridgeImpl(long j) {
    }

    @CalledByNative
    public static PasswordSyncControllerDelegateBridgeImpl create(long j) {
        Object obj = ThreadUtils.a;
        if (vd1.h == null) {
            vd1.h = new ay1();
        }
        vd1.h.getClass();
        return new PasswordSyncControllerDelegateBridgeImpl(j);
    }

    @CalledByNative
    public void notifyCredentialManagerWhenNotSyncing() {
        throw null;
    }

    @CalledByNative
    public void notifyCredentialManagerWhenSyncing(String str) {
        throw null;
    }
}
