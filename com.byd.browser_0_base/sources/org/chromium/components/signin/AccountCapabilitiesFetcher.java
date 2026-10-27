package org.chromium.components.signin;

import defpackage.l51;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes3.dex */
public class AccountCapabilitiesFetcher {
    public final CoreAccountInfo a;
    public long b;

    @CalledByNative
    public AccountCapabilitiesFetcher(CoreAccountInfo coreAccountInfo, long j) {
        this.a = coreAccountInfo;
        this.b = j;
    }

    @CalledByNative
    public void startFetchingAccountCapabilities() {
        AccountManagerFacadeProvider.getInstance().d(CoreAccountInfo.a(this.a)).g(new l51(22, this));
    }
}
