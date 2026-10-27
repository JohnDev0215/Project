package com.loostone.libserver.version2.bean;

import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class TokenData extends BaseResponseData<TokenItem> {

    public final class TokenItem {
        private String accessToken;
        private String clientId;
        public final /* synthetic */ TokenData this$0;
        private String validEndTime;

        public TokenItem(TokenData tokenData) {
            h.d(tokenData, "this$0");
            this.this$0 = tokenData;
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getClientId() {
            return this.clientId;
        }

        public final String getValidEndTime() {
            return this.validEndTime;
        }

        public final void setAccessToken(String str) {
            this.accessToken = str;
        }

        public final void setClientId(String str) {
            this.clientId = str;
        }

        public final void setValidEndTime(String str) {
            this.validEndTime = str;
        }
    }
}
