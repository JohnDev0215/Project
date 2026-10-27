package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class y2 extends s2 {
    public final /* synthetic */ Context f;
    public final /* synthetic */ s2 g;

    public y2(Context context, s2 s2Var) {
        this.f = context;
        this.g = s2Var;
    }

    @Override // com.amazon.identity.auth.device.s2, com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        super.onError(bundle);
        if (bundle.getInt(MAPError.KEY_ERROR_CODE) == MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED.getErrorCode()) {
            Log.i(ga.a(z2.f), "Cleaning CBL code for ACCOUNT_ALREADY_REGISTERED error.");
            new f8(this.f, "cbl_storage", 0).a();
        }
        Log.i(ga.a(z2.f), "Register with link code was not successful.");
        this.g.onError(bundle);
    }

    @Override // com.amazon.identity.auth.device.s2, com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        a(1, bundle);
        Log.i(ga.a(z2.f), "Register with link code was successful. Clearing the cbl data in MAP");
        new f8(this.f, "cbl_storage", 0).a();
        this.g.onSuccess(bundle);
    }
}
