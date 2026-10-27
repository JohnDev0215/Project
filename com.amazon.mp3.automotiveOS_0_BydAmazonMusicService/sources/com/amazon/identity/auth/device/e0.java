package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class e0 extends e4<Boolean> {
    public final /* synthetic */ CentralAccountManagerCommunication b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(CentralAccountManagerCommunication centralAccountManagerCommunication, Callback callback) {
        super(callback);
        this.b = centralAccountManagerCommunication;
    }

    @Override // com.amazon.identity.auth.device.e4
    public void a(Callback callback, Boolean bool) {
        Boolean bool2 = bool;
        if (bool2 == null || !bool2.booleanValue()) {
            this.b.getClass();
            callback.onError(y.b(MAPError.AccountError.DEREGISTER_FAILED, "Deregister failed. Could not remove the account", MAPAccountManager.RegistrationError.DEREGISTER_FAILED.value(), "Could not remove the account"));
        } else {
            this.b.getClass();
            callback.onSuccess(new Bundle());
        }
    }
}
