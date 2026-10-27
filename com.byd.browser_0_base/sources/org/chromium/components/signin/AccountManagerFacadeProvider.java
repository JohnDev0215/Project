package org.chromium.components.signin;

import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AccountManagerFacadeProvider {
    public static final AtomicReference<AccountManagerFacade> a = new AtomicReference<>();
    public static AccountManagerFacade b;

    @CalledByNative
    public static AccountManagerFacade getInstance() {
        AccountManagerFacade accountManagerFacade = a.get();
        if (accountManagerFacade != null) {
            return accountManagerFacade;
        }
        throw new IllegalStateException("AccountManagerFacade is not yet initialized!");
    }
}
