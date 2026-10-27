package com.ecarx.sdk.oauth2.base;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseResp implements IBase {
    private static final String KEY_ERRCODE = "opensdk_oauth_baseresp_key_errcode";
    private static final String KEY_ERRSTR = "opensdk_oauth_baseresp_key_errstr";
    private static final String KEY_OPENID = "opensdk_oauth_baseresp_key_openid";
    private static final String KEY_TRANSACTION = "opensdk_oauth_baseresp_key_transaction";
    public int errCode;
    public String errStr;
    public String openId;
    public String transaction;

    public interface ErrCode {
        public static final int ERR_AUTH_DENIED = -4;
        public static final int ERR_BAN = -6;
        public static final int ERR_COMM = -1;
        public static final int ERR_NOT_SUPPORT = -5;
        public static final int ERR_OK = 0;
        public static final int ERR_SENT_FAILED = -3;
        public static final int ERR_USER_CANCEL = -2;
    }

    @Override // com.ecarx.sdk.oauth2.base.IBase
    public void fromBundle(Bundle bundle) {
        this.errCode = bundle.getInt(KEY_ERRCODE);
        this.errStr = bundle.getString(KEY_ERRSTR);
        this.transaction = bundle.getString(KEY_TRANSACTION);
        this.openId = bundle.getString(KEY_OPENID);
    }

    @Override // com.ecarx.sdk.oauth2.base.IBase
    public void toBundle(Bundle bundle) {
        bundle.putInt(KEY_ERRCODE, this.errCode);
        bundle.putString(KEY_ERRSTR, this.errStr);
        bundle.putString(KEY_TRANSACTION, this.transaction);
        bundle.putString(KEY_OPENID, this.openId);
    }
}
