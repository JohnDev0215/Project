package com.ecarx.sdk.oauth2.event;

import android.os.Bundle;
import android.text.TextUtils;
import com.ecarx.sdk.oauth2.base.BaseReq;
import com.ecarx.sdk.oauth2.base.BaseResp;

/* JADX INFO: loaded from: classes2.dex */
public final class SendAuth {

    public static class Req extends BaseReq {
        public static final String KEY_SCOPE = "opensdk_oauth_sendauth_req_key_scope";
        public static final String KEY_SKIP_CONFIRM = "opensdk_oauth_sendauth_req_key_skipconfirm";
        public String scope;
        public boolean skipConfirm;

        @Override // com.ecarx.sdk.oauth2.base.IBase
        public int getType() {
            return 1;
        }

        @Override // com.ecarx.sdk.oauth2.base.BaseReq, com.ecarx.sdk.oauth2.base.IBase
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.scope = bundle.getString(KEY_SCOPE);
            this.skipConfirm = bundle.getBoolean(KEY_SKIP_CONFIRM);
        }

        @Override // com.ecarx.sdk.oauth2.base.BaseReq, com.ecarx.sdk.oauth2.base.IBase
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(KEY_SCOPE, this.scope);
            bundle.putBoolean(KEY_SKIP_CONFIRM, this.skipConfirm);
        }

        @Override // com.ecarx.sdk.oauth2.base.IBase
        public boolean checkArgs() {
            return !TextUtils.isEmpty(this.scope);
        }
    }

    public static class Resp extends BaseResp {
        public static final String KEY_CODE = "opensdk_oauth_sendauth_resp_key_code";
        public String code;

        @Override // com.ecarx.sdk.oauth2.base.IBase
        public int getType() {
            return 1;
        }

        public Resp() {
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.ecarx.sdk.oauth2.base.BaseResp, com.ecarx.sdk.oauth2.base.IBase
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.code = bundle.getString(KEY_CODE);
        }

        @Override // com.ecarx.sdk.oauth2.base.BaseResp, com.ecarx.sdk.oauth2.base.IBase
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(KEY_CODE, this.code);
        }

        @Override // com.ecarx.sdk.oauth2.base.IBase
        public boolean checkArgs() {
            return !TextUtils.isEmpty(this.code);
        }
    }
}
