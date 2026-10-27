package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface Callback {
    @FireOsSdk
    void onError(Bundle bundle);

    @FireOsSdk
    void onSuccess(Bundle bundle);
}
