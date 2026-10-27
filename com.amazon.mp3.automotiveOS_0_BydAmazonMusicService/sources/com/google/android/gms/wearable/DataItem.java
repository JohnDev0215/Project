package com.google.android.gms.wearable;

import android.net.Uri;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public interface DataItem extends Freezable<DataItem> {
    @RecentlyNonNull
    Map<String, DataItemAsset> getAssets();

    @RecentlyNonNull
    byte[] getData();

    @RecentlyNonNull
    Uri getUri();

    @RecentlyNonNull
    DataItem setData(@RecentlyNonNull byte[] bArr);
}
