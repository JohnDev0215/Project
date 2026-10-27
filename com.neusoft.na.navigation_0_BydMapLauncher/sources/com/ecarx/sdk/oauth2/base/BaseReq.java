package com.ecarx.sdk.oauth2.base;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseReq implements IBase {
    public static final String KEY_OPENID = "opensdk_oauth_basereq_key_openid";
    public static final String KEY_TRANSACTION = "opensdk_oauth_basereq_key_transaction";
    public String openId;
    public String transaction;

    @Override // com.ecarx.sdk.oauth2.base.IBase
    public void fromBundle(Bundle bundle) {
        this.transaction = bundle.getString(KEY_TRANSACTION);
        this.openId = bundle.getString(KEY_OPENID);
    }

    @Override // com.ecarx.sdk.oauth2.base.IBase
    public void toBundle(Bundle bundle) {
        bundle.putString(KEY_TRANSACTION, this.transaction);
        bundle.putString(KEY_OPENID, this.openId);
    }
}
