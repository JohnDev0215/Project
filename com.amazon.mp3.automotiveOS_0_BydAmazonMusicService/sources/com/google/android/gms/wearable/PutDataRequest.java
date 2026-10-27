package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
@SafeParcelable.Class(creator = "PutDataRequestCreator")
@SafeParcelable.Reserved({1})
public class PutDataRequest extends AbstractSafeParcelable {

    @RecentlyNonNull
    public static final String WEAR_URI_SCHEME = "wear";

    @SafeParcelable.Field(getter = "getUri", id = 2)
    private final Uri zzc;

    @SafeParcelable.Field(getter = "getAssetsInternal", id = 4)
    private final Bundle zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getData", id = 5)
    private byte[] zze;

    @SafeParcelable.Field(getter = "getSyncDeadline", id = 6)
    private long zzf;

    @RecentlyNonNull
    public static final Parcelable.Creator<PutDataRequest> CREATOR = new zzg();
    private static final long zza = TimeUnit.MINUTES.toMillis(30);
    private static final Random zzb = new SecureRandom();

    private PutDataRequest(Uri uri) {
        this(uri, new Bundle(), null, zza);
    }

    @RecentlyNonNull
    public static PutDataRequest create(@RecentlyNonNull String str) {
        Preconditions.checkNotNull(str, "path must not be null");
        return zza(zzb(str));
    }

    @RecentlyNonNull
    public static PutDataRequest createFromDataItem(@RecentlyNonNull DataItem dataItem) {
        Preconditions.checkNotNull(dataItem, "source must not be null");
        PutDataRequest putDataRequestZza = zza(dataItem.getUri());
        for (Map.Entry<String, DataItemAsset> entry : dataItem.getAssets().entrySet()) {
            if (entry.getValue().getId() == null) {
                String strValueOf = String.valueOf(entry.getKey());
                throw new IllegalStateException(strValueOf.length() != 0 ? "Cannot create an asset for a put request without a digest: ".concat(strValueOf) : new String("Cannot create an asset for a put request without a digest: "));
            }
            putDataRequestZza.putAsset(entry.getKey(), Asset.createFromRef(entry.getValue().getId()));
        }
        putDataRequestZza.setData(dataItem.getData());
        return putDataRequestZza;
    }

    @RecentlyNonNull
    public static PutDataRequest createWithAutoAppendedId(@RecentlyNonNull String str) {
        Preconditions.checkNotNull(str, "pathPrefix must not be null");
        StringBuilder sb = new StringBuilder(str);
        if (!str.endsWith("/")) {
            sb.append("/");
        }
        sb.append("PN");
        sb.append(zzb.nextLong());
        return new PutDataRequest(zzb(sb.toString()));
    }

    @RecentlyNonNull
    public static PutDataRequest zza(@RecentlyNonNull Uri uri) {
        Preconditions.checkNotNull(uri, "uri must not be null");
        return new PutDataRequest(uri);
    }

    private static Uri zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("An empty path was supplied.");
        }
        if (!str.startsWith("/")) {
            throw new IllegalArgumentException("A path must start with a single / .");
        }
        if (str.startsWith("//")) {
            throw new IllegalArgumentException("A path must start with a single / .");
        }
        return new Uri.Builder().scheme(WEAR_URI_SCHEME).path(str).build();
    }

    @RecentlyNullable
    @VisibleForTesting
    public Asset getAsset(@RecentlyNonNull String str) {
        Preconditions.checkNotNull(str, "key must not be null");
        return (Asset) this.zzd.getParcelable(str);
    }

    @RecentlyNonNull
    public Map<String, Asset> getAssets() {
        HashMap map = new HashMap();
        for (String str : this.zzd.keySet()) {
            map.put(str, (Asset) this.zzd.getParcelable(str));
        }
        return Collections.unmodifiableMap(map);
    }

    @RecentlyNullable
    @VisibleForTesting
    public byte[] getData() {
        return this.zze;
    }

    @RecentlyNonNull
    public Uri getUri() {
        return this.zzc;
    }

    public boolean hasAsset(@RecentlyNonNull String str) {
        Preconditions.checkNotNull(str, "key must not be null");
        return this.zzd.containsKey(str);
    }

    public boolean isUrgent() {
        return this.zzf == 0;
    }

    @RecentlyNonNull
    public PutDataRequest putAsset(@RecentlyNonNull String str, @RecentlyNonNull Asset asset) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(asset);
        this.zzd.putParcelable(str, asset);
        return this;
    }

    @RecentlyNonNull
    public PutDataRequest removeAsset(@RecentlyNonNull String str) {
        Preconditions.checkNotNull(str, "key must not be null");
        this.zzd.remove(str);
        return this;
    }

    @RecentlyNonNull
    public PutDataRequest setData(@RecentlyNonNull byte[] bArr) {
        this.zze = bArr;
        return this;
    }

    @RecentlyNonNull
    public PutDataRequest setUrgent() {
        this.zzf = 0L;
        return this;
    }

    @RecentlyNonNull
    public String toString() {
        return toString(Log.isLoggable(DataMap.TAG, 3));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel, "dest must not be null");
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getUri(), i, false);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeByteArray(parcel, 5, getData(), false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    PutDataRequest(@SafeParcelable.Param(id = 2) Uri uri, @SafeParcelable.Param(id = 4) Bundle bundle, @Nullable @SafeParcelable.Param(id = 5) byte[] bArr, @SafeParcelable.Param(id = 6) long j) {
        this.zzc = uri;
        this.zzd = bundle;
        bundle.setClassLoader((ClassLoader) Preconditions.checkNotNull(DataItemAssetParcelable.class.getClassLoader()));
        this.zze = bArr;
        this.zzf = j;
    }

    @RecentlyNonNull
    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder("PutDataRequest[");
        byte[] bArr = this.zze;
        String strValueOf = String.valueOf(bArr == null ? "null" : Integer.valueOf(bArr.length));
        StringBuilder sb2 = new StringBuilder(strValueOf.length() + 7);
        sb2.append("dataSz=");
        sb2.append(strValueOf);
        sb.append(sb2.toString());
        int size = this.zzd.size();
        StringBuilder sb3 = new StringBuilder(23);
        sb3.append(", numAssets=");
        sb3.append(size);
        sb.append(sb3.toString());
        String strValueOf2 = String.valueOf(this.zzc);
        StringBuilder sb4 = new StringBuilder(strValueOf2.length() + 6);
        sb4.append(", uri=");
        sb4.append(strValueOf2);
        sb.append(sb4.toString());
        long j = this.zzf;
        StringBuilder sb5 = new StringBuilder(35);
        sb5.append(", syncDeadline=");
        sb5.append(j);
        sb.append(sb5.toString());
        if (!z) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (String str : this.zzd.keySet()) {
            String strValueOf3 = String.valueOf(this.zzd.getParcelable(str));
            StringBuilder sb6 = new StringBuilder(String.valueOf(str).length() + 7 + strValueOf3.length());
            sb6.append("\n    ");
            sb6.append(str);
            sb6.append(": ");
            sb6.append(strValueOf3);
            sb.append(sb6.toString());
        }
        sb.append("\n  ]");
        return sb.toString();
    }
}
