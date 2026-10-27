package com.google.android.gms.wearable;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.internal.zzdf;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public class DataItemBuffer extends EntityBuffer<DataItem> implements Result {
    private final Status zza;

    public DataItemBuffer(@RecentlyNonNull DataHolder dataHolder) {
        super(dataHolder);
        this.zza = new Status(dataHolder.getStatusCode());
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    @RecentlyNonNull
    protected final /* bridge */ /* synthetic */ DataItem getEntry(int i, int i2) {
        return new zzdf(this.mDataHolder, i, i2);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    @RecentlyNonNull
    protected final String getPrimaryDataMarkerColumn() {
        return "path";
    }

    @Override // com.google.android.gms.common.api.Result
    @RecentlyNonNull
    public Status getStatus() {
        return this.zza;
    }
}
