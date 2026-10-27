package com.ecarx.sdk.oauth2;

import com.ecarx.sdk.oauth2.base.BaseReq;
import com.ecarx.sdk.oauth2.base.BaseResp;

/* JADX INFO: loaded from: classes2.dex */
public interface IEventHandler {
    void onReq(BaseReq baseReq);

    void onResp(BaseResp baseResp);
}
