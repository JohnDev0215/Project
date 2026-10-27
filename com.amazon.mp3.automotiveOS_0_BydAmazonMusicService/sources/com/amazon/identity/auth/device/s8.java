package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPFuture;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class s8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f543a;
    public e9 b;

    public s8(@NonNull Context context) {
        this.f543a = context;
    }

    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, r8 r8Var) {
        e9 e9Var;
        y5 y5Var = new y5("TokenManagement:UpgradeToken");
        de deVarA = y5Var.a(this.f543a, "Time");
        synchronized (this) {
            if (this.b == null) {
                this.b = e.e(this.f543a);
            }
            e9Var = this.b;
        }
        return e9Var.a(str, str2, bundle, new xd(deVarA, y5Var, false, callback, r8Var, null, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), r8Var, y5Var);
    }
}
