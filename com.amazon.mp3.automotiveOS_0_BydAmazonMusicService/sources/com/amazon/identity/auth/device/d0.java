package com.amazon.identity.auth.device;

import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class d0 extends f4<Boolean> {
    public final /* synthetic */ CentralAccountManagerCommunication c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(CentralAccountManagerCommunication centralAccountManagerCommunication, AccountManagerFuture accountManagerFuture) {
        super(accountManagerFuture);
        this.c = centralAccountManagerCommunication;
    }

    @Override // com.amazon.identity.auth.device.f4
    public Bundle a(Boolean bool) throws MAPCallbackErrorException {
        Boolean bool2 = bool;
        if (bool2 == null || !bool2.booleanValue()) {
            this.c.getClass();
            throw new MAPCallbackErrorException(y.b(MAPError.AccountError.DEREGISTER_FAILED, "Deregister failed. Could not remove the account", MAPAccountManager.RegistrationError.DEREGISTER_FAILED.value(), "Could not remove the account"));
        }
        this.c.getClass();
        return new Bundle();
    }
}
