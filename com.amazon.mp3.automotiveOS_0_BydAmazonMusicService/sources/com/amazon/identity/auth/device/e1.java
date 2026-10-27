package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class e1 extends e4<Bundle> {
    public final Context b;

    public e1(Callback callback, Context context) {
        super(callback);
        this.b = context;
    }

    @Override // com.amazon.identity.auth.device.e4
    public void a(Callback callback, Bundle bundle) {
        Bundle bundle2 = bundle;
        Context context = this.b;
        if (bundle2 != null && bundle2.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME) && bundle2.containsKey(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE) && !bundle2.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
            String strB = r9.b(context, bundle2.getString(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE), bundle2.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME));
            if (TextUtils.isEmpty(strB)) {
                Log.e(ga.a(r9.f530a), "Could not get directed id from android account");
            } else {
                bundle2.putString("com.amazon.dcp.sso.property.account.acctId", strB);
            }
        }
        y.a(callback, bundle2);
    }
}
