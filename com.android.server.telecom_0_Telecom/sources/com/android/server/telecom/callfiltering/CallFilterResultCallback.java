package com.android.server.telecom.callfiltering;

import com.android.server.telecom.Call;

/* JADX INFO: loaded from: classes.dex */
public interface CallFilterResultCallback {
    void onCallFilteringComplete(Call call, CallFilteringResult callFilteringResult);
}
