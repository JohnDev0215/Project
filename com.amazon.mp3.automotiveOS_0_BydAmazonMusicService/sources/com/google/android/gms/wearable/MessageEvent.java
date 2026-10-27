package com.google.android.gms.wearable;

import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface MessageEvent {
    @RecentlyNonNull
    byte[] getData();

    @RecentlyNonNull
    String getPath();

    int getRequestId();

    @RecentlyNonNull
    String getSourceNodeId();
}
