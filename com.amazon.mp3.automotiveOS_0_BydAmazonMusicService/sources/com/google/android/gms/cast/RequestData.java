package com.google.android.gms.cast;

import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface RequestData {
    @Nullable
    JSONObject getCustomData();

    @KeepForSdk
    long getRequestId();
}
