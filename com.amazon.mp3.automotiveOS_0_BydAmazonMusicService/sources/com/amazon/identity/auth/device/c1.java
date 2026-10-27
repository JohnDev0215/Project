package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.kcpsdk.common.ParseError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c1 extends tb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Callback f255a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ d1 d;

    public c1(d1 d1Var, Callback callback, String str, String str2) {
        this.d = d1Var;
        this.f255a = callback;
        this.b = str;
        this.c = str2;
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a(Object obj) {
        String str = d1.c;
        Log.i(ga.a(str), "Completed the rename device request");
        rc rcVar = (rc) obj;
        if (rcVar == null) {
            this.d.a(this.f255a, MAPError.CommonError.INVALID_RESPONSE, "Received a null response from the server", 7, " Unrecognized response from server");
            return;
        }
        int i = rcVar.c;
        String str2 = rcVar.d;
        MAPError mAPError = rcVar.f532a;
        String str3 = rcVar.b;
        if (i != 0) {
            this.d.a(this.f255a, mAPError, str3, i, str2);
            return;
        }
        Log.i(ga.a(str), "Successfully completed the rename device request");
        d1 d1Var = this.d;
        String str4 = this.b;
        d1Var.getClass();
        String strA = str4 != null ? e.a(d1Var.b, str4, "com.amazon.dcp.sso.property.devicename") : "com.amazon.dcp.sso.property.devicename";
        String.format("Store the new device name with key: %s", strA);
        ga.a(str);
        this.d.f274a.f144a.d(this.c, strA, str2);
        Log.i(ga.a(str), "RenameDevice completed successfully.");
        SSOMetrics.a();
        SSOMetrics.a("RenameDeviceRequestSuccess", new String[0]);
        Callback callback = this.f255a;
        this.d.getClass();
        Bundle bundle = new Bundle();
        bundle.putString(CustomerAttributeStore.KEY_NEW_DEVICE_NAME, str2);
        callback.onSuccess(bundle);
    }

    @Override // com.amazon.identity.auth.device.tb
    public void b() {
        d1 d1Var = this.d;
        Callback callback = this.f255a;
        MAPError.CommonError commonError = MAPError.CommonError.NETWORK_ERROR;
        d1Var.a(callback, commonError, commonError.getErrorMessage(), 1, "Network failure performing rename device request");
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a() {
        d1 d1Var = this.d;
        Callback callback = this.f255a;
        MAPError.AccountError accountError = MAPError.AccountError.AUTHENTICATION_FAILED;
        d1Var.a(callback, accountError, accountError.getErrorMessage(), 2, "Authentication failure performing rename device request");
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a(ParseError parseError) {
        this.d.a(this.f255a, MAPError.CommonError.PARSE_ERROR, "A parsing error occurred while performing the rename device request", 4, "Parsing failure performing rename device request");
    }
}
