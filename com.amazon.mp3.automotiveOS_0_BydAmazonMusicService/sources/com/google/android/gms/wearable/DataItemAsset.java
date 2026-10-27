package com.google.android.gms.wearable;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public interface DataItemAsset extends Freezable<DataItemAsset> {
    @RecentlyNonNull
    String getDataItemKey();

    @RecentlyNonNull
    String getId();
}
