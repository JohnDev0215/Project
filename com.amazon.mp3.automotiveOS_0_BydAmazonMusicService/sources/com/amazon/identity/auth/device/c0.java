package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.y8;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c0 implements y8.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s2 f254a;

    public c0(CentralAccountManagerCommunication centralAccountManagerCommunication, s2 s2Var) {
        this.f254a = s2Var;
    }

    @Override // com.amazon.identity.auth.device.y8.b
    public void a() {
        this.f254a.onSuccess(new Bundle());
    }

    @Override // com.amazon.identity.auth.device.y8.b
    public void a(String str, MAPError mAPError, Bundle bundle) {
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, mAPError.getErrorMessage());
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        this.f254a.onError(bundle);
    }

    @Override // com.amazon.identity.auth.device.y8.b
    public void a(String str, MAPError mAPError, String str2, int i, String str3) {
        if (i == MAPAccountManager.RegistrationError.NETWORK_FAILURE.value()) {
            yd.b("NetworkError15:CentralAccountManagerCommunication", new String[0]);
        }
        y.a(this.f254a, mAPError, str2, i, str3, null);
    }
}
