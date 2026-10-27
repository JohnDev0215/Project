package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.token.OAuthTokenManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q8 implements e9 {
    public static final String c = "com.amazon.identity.auth.device.q8";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f513a;
    public OAuthTokenManager b;

    public q8(Context context) {
        t5 t5VarA = t5.a(context);
        this.f513a = t5VarA;
        this.b = new OAuthTokenManager(t5VarA);
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        return h9.a(this.f513a).a(str, str2, bundle, callback, y5Var);
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        fa faVarA = fa.a(str2);
        if (!((bundle == null || !bundle.getBoolean("authorizationCode")) && "com.amazon.dcp.sso.token.oauth.amazon.access_token".equals(faVarA.c) && z9.d(this.f513a, faVarA.b))) {
            return h9.a(this.f513a).b(str, str2, bundle, callback, y5Var);
        }
        ga.a(c);
        s2 s2Var = new s2(callback);
        Account accountA = r9.a(this.f513a, str);
        if (accountA == null) {
            MAPError.AccountError accountError = MAPError.AccountError.CUSTOMER_NOT_FOUND;
            s2Var.a(2, a9.a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given Customer Does not exist"));
        } else if (TextUtils.isEmpty(str2)) {
            s2Var.a(2, a9.a(MAPError.CommonError.BAD_REQUEST, "Token key was empty.", 8, "Token key was empty."));
        } else {
            va.f637a.execute(new o8(this, str2, accountA, str, bundle, s2Var, y5Var));
        }
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> c(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        return h9.a(this.f513a).c(str, str2, bundle, callback, y5Var);
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        a9.a(s2Var, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED, "getTokenForActor is not supported in pre-merge devices", 6, "getTokenForActor is not supported in pre-merge devices");
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        a9.a(s2Var, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED, "getCookiesForActor is not supported in pre-merge devices", 6, "getCookiesForActor is not supported in pre-merge devices");
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, r8 r8Var, y5 y5Var) {
        s2 s2Var = new s2(callback);
        MAPErrorCallbackHelper.onError(s2Var, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED);
        return s2Var;
    }
}
