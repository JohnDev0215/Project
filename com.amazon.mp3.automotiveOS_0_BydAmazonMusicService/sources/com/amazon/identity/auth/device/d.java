package com.amazon.identity.auth.device;

import android.accounts.IAccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.RemoteException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class d extends IAccountAuthenticatorResponse.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Callback f273a;

    public d(Callback callback) {
        this.f273a = callback;
    }

    @Override // android.accounts.IAccountAuthenticatorResponse
    public void onError(int i, String str) throws RemoteException {
        y.a(this.f273a, MAPError.CommonError.INTERNAL_ERROR, str, i, str);
    }

    @Override // android.accounts.IAccountAuthenticatorResponse
    public void onRequestContinued() throws RemoteException {
    }

    @Override // android.accounts.IAccountAuthenticatorResponse
    public void onResult(Bundle bundle) throws RemoteException {
        y.a(this.f273a, bundle);
    }
}
