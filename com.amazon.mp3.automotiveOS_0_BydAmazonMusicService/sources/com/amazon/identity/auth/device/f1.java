package com.amazon.identity.auth.device;

import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class f1 extends f4<Bundle> {
    public f1(AccountManagerFuture<Bundle> accountManagerFuture) {
        super(accountManagerFuture);
    }

    @Override // com.amazon.identity.auth.device.f4
    public Bundle a(Bundle bundle) throws MAPCallbackErrorException {
        Bundle bundle2 = bundle;
        if (y.a(bundle2)) {
            throw new MAPCallbackErrorException(bundle2);
        }
        return bundle2;
    }
}
