package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
@SafeParcelable.Class(creator = "AssetCreator")
@SafeParcelable.Reserved({1})
public class Asset extends AbstractSafeParcelable implements ReflectedParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<Asset> CREATOR = new zzc();

    @RecentlyNullable
    @SafeParcelable.Field(id = 4)
    public ParcelFileDescriptor zza;

    @RecentlyNullable
    @SafeParcelable.Field(id = 5)
    public Uri zzb;

    @Nullable
    @SafeParcelable.Field(getter = "getData", id = 2)
    private byte[] zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getDigest", id = 3)
    private String zzd;

    @SafeParcelable.Constructor
    Asset(@Nullable @SafeParcelable.Param(id = 2) byte[] bArr, @Nullable @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) ParcelFileDescriptor parcelFileDescriptor, @Nullable @SafeParcelable.Param(id = 5) Uri uri) {
        this.zzc = bArr;
        this.zzd = str;
        this.zza = parcelFileDescriptor;
        this.zzb = uri;
    }

    @RecentlyNonNull
    @VisibleForTesting
    public static Asset createFromBytes(@RecentlyNonNull byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        return new Asset(bArr, null, null, null);
    }

    @RecentlyNonNull
    @VisibleForTesting
    public static Asset createFromFd(@RecentlyNonNull ParcelFileDescriptor parcelFileDescriptor) {
        Preconditions.checkNotNull(parcelFileDescriptor);
        return new Asset(null, null, parcelFileDescriptor, null);
    }

    @RecentlyNonNull
    public static Asset createFromRef(@RecentlyNonNull String str) {
        Preconditions.checkNotNull(str);
        return new Asset(null, str, null, null);
    }

    @RecentlyNonNull
    @VisibleForTesting
    public static Asset createFromUri(@RecentlyNonNull Uri uri) {
        Preconditions.checkNotNull(uri);
        return new Asset(null, null, null, uri);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) obj;
        return Arrays.equals(this.zzc, asset.zzc) && Objects.equal(this.zzd, asset.zzd) && Objects.equal(this.zza, asset.zza) && Objects.equal(this.zzb, asset.zzb);
    }

    @RecentlyNullable
    public String getDigest() {
        return this.zzd;
    }

    @RecentlyNullable
    public ParcelFileDescriptor getFd() {
        return this.zza;
    }

    @RecentlyNullable
    public Uri getUri() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.zzc, this.zzd, this.zza, this.zzb});
    }

    @RecentlyNonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asset[@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.zzd == null) {
            sb.append(", nodigest");
        } else {
            sb.append(", ");
            sb.append(this.zzd);
        }
        if (this.zzc != null) {
            sb.append(", size=");
            sb.append(((byte[]) Preconditions.checkNotNull(this.zzc)).length);
        }
        if (this.zza != null) {
            sb.append(", fd=");
            sb.append(this.zza);
        }
        if (this.zzb != null) {
            sb.append(", uri=");
            sb.append(this.zzb);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int i2 = i | 1;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 3, getDigest(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zza, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @RecentlyNullable
    public final byte[] zza() {
        return this.zzc;
    }
}
