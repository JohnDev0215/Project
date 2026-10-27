package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public class PutDataMapRequest {
    private final PutDataRequest zza;
    private final DataMap zzb;

    private PutDataMapRequest(PutDataRequest putDataRequest, @Nullable DataMap dataMap) {
        this.zza = putDataRequest;
        DataMap dataMap2 = new DataMap();
        this.zzb = dataMap2;
        if (dataMap != null) {
            dataMap2.putAll(dataMap);
        }
    }

    @RecentlyNonNull
    public static PutDataMapRequest create(@RecentlyNonNull String str) {
        Asserts.checkNotNull(str, "path must not be null");
        return new PutDataMapRequest(PutDataRequest.create(str), null);
    }

    @RecentlyNonNull
    public static PutDataMapRequest createFromDataMapItem(@RecentlyNonNull DataMapItem dataMapItem) {
        Asserts.checkNotNull(dataMapItem, "source must not be null");
        return new PutDataMapRequest(PutDataRequest.zza(dataMapItem.getUri()), dataMapItem.getDataMap());
    }

    @RecentlyNonNull
    public static PutDataMapRequest createWithAutoAppendedId(@RecentlyNonNull String str) {
        Asserts.checkNotNull(str, "pathPrefix must not be null");
        return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(str), null);
    }

    @RecentlyNonNull
    public PutDataRequest asPutDataRequest() {
        com.google.android.gms.internal.wearable.zzj zzjVarZza = com.google.android.gms.internal.wearable.zzk.zza(this.zzb);
        this.zza.setData(zzjVarZza.zza.zzI());
        int size = zzjVarZza.zzb.size();
        for (int i = 0; i < size; i++) {
            String string = Integer.toString(i);
            Asset asset = zzjVarZza.zzb.get(i);
            if (string == null) {
                String strValueOf = String.valueOf(asset);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 26);
                sb.append("asset key cannot be null: ");
                sb.append(strValueOf);
                throw new IllegalStateException(sb.toString());
            }
            if (asset == null) {
                throw new IllegalStateException(string.length() != 0 ? "asset cannot be null: key=".concat(string) : new String("asset cannot be null: key="));
            }
            if (Log.isLoggable(DataMap.TAG, 3)) {
                String strValueOf2 = String.valueOf(asset);
                StringBuilder sb2 = new StringBuilder(string.length() + 33 + strValueOf2.length());
                sb2.append("asPutDataRequest: adding asset: ");
                sb2.append(string);
                sb2.append(" ");
                sb2.append(strValueOf2);
                Log.d(DataMap.TAG, sb2.toString());
            }
            this.zza.putAsset(string, asset);
        }
        return this.zza;
    }

    @RecentlyNonNull
    public DataMap getDataMap() {
        return this.zzb;
    }

    @RecentlyNonNull
    public Uri getUri() {
        return this.zza.getUri();
    }

    public boolean isUrgent() {
        return this.zza.isUrgent();
    }

    @RecentlyNonNull
    public PutDataMapRequest setUrgent() {
        this.zza.setUrgent();
        return this;
    }
}
