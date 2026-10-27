package com.amazon.device.ads;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
interface AuthenticationPlatformAdapter {
    String getAccountName();

    String getPreferredMarketplace();

    WebRequest makeAuthenticatedWebRequest(String str);

    AuthenticationPlatformAdapter setContext(Context context);
}
