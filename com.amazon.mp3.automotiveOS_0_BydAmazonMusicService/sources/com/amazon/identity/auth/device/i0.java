package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i0 implements ISubAuthenticatorResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ISubAuthenticatorResponse f375a;
    public final /* synthetic */ y5 b;
    public final /* synthetic */ String c;
    public final /* synthetic */ String d;
    public final /* synthetic */ n0 e;

    public i0(n0 n0Var, ISubAuthenticatorResponse iSubAuthenticatorResponse, y5 y5Var, String str, String str2) {
        this.e = n0Var;
        this.f375a = iSubAuthenticatorResponse;
        this.b = y5Var;
        this.c = str;
        this.d = str2;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.f375a.asBinder();
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
    public void onError(int i, String str) throws RemoteException {
        this.f375a.onError(i, str);
        y5 y5Var = this.b;
        if (y5Var != null) {
            y5Var.e.a();
        }
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
    public void onResult(Bundle bundle) throws RemoteException {
        n0 n0Var = this.e;
        String str = this.c;
        String str2 = this.d;
        n0Var.getClass();
        if (!bundle.containsKey(AccountConstants.KEY_DMS_ERROR_CODE)) {
            String strC = n0Var.d.c(str, str2);
            if (strC == null) {
                bundle = n0Var.a(104, "Requested token type was not found in authenticator cache.");
            } else {
                if (n0.z.equals(str2)) {
                    w1.a(n0Var.f442a, str, strC);
                }
                bundle.putString("authtoken", strC);
            }
        }
        this.f375a.onResult(bundle);
        y5 y5Var = this.b;
        if (y5Var != null) {
            y5Var.e.a();
        }
    }
}
