package com.ecarx.sdk.oauth2;

import android.content.Intent;
import com.ecarx.sdk.oauth2.base.BaseReq;
import com.ecarx.sdk.oauth2.base.BaseResp;

/* JADX INFO: loaded from: classes2.dex */
public interface IOAuth2API {
    boolean handleIntent(Intent intent, IEventHandler iEventHandler);

    boolean hasLogin();

    boolean openApp();

    boolean sendReq(BaseReq baseReq);

    boolean sendResp(BaseResp baseResp);

    boolean setLogEnable(boolean z);

    boolean setLoginListener(ILoginListener iLoginListener);
}
