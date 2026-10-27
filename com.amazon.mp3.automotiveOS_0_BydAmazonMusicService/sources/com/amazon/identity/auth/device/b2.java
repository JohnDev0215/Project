package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b2 implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RemoteCallbackWrapper f236a;
    public final /* synthetic */ String b;
    public final /* synthetic */ x3 c;
    public final /* synthetic */ AuthPortalUIActivity d;

    public b2(AuthPortalUIActivity authPortalUIActivity, RemoteCallbackWrapper remoteCallbackWrapper, String str, x3 x3Var) {
        this.d = authPortalUIActivity;
        this.f236a = remoteCallbackWrapper;
        this.b = str;
        this.c = x3Var;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        bundle.putBundle(MAPAccountManager.KEY_ADDITIONAL_RETURN_TO_URL_PARAMETERS, this.c.d());
        IsolatedModeSwitcher.switchAppToSSOModeIfNecessary(this.d.k);
        AuthPortalUIActivity.a(this.d, bundle, this.f236a);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        if (bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT)) {
            AuthPortalUIActivity authPortalUIActivity = this.d;
            Bundle bundle2 = new Bundle();
            RemoteCallbackWrapper remoteCallbackWrapper = this.f236a;
            String str = AuthPortalUIActivity.Q;
            authPortalUIActivity.a(bundle2, remoteCallbackWrapper);
            return;
        }
        String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (string == null) {
            Log.w(ga.a(AuthPortalUIActivity.Q), "directedId returned from registration was null. Not setting Auth Portal domain.");
        } else {
            Log.i(ga.a(AuthPortalUIActivity.Q), "Account registered with domain: " + this.d.p);
            o7 o7Var = new o7(string, null, null, null);
            o7Var.b.put("key_auth_portal_endpoint", this.d.p);
            o7Var.b.put("key_panda_endpoint", this.d.q);
            o7Var.b.put("authDomain", q9.a(this.d.p));
            o7Var.b.put("x-amzn-identity-auth-domain", this.d.q);
            if (!TextUtils.isEmpty(this.b)) {
                o7Var.c.put(AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN, this.b);
            }
            this.d.m.a(o7Var);
        }
        bundle.putBoolean(MAPAccountManager.KEY_IS_NEW_ACCOUNT, this.c.f().booleanValue());
        bundle.putString(MAPAccountManager.KEY_CLAIM_TYPE, this.c.f655a.get(MAPAccountManager.KEY_CLAIM_TYPE));
        bundle.putBundle(MAPAccountManager.KEY_ADDITIONAL_RETURN_TO_URL_PARAMETERS, this.c.d());
        this.d.a(bundle, this.f236a);
    }
}
