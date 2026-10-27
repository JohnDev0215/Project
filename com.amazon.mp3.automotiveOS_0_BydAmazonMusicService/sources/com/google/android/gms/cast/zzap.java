package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "EqualizerBandSettingsCreator")
@SafeParcelable.Reserved({1})
public final class zzap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzap> CREATOR = new zzaq();

    @SafeParcelable.Field(getter = "getFrequency", id = 2)
    private final float zza;

    @SafeParcelable.Field(getter = "getQFactor", id = 3)
    private final float zzb;

    @SafeParcelable.Field(getter = "getGainDb", id = 4)
    private final float zzc;

    @SafeParcelable.Constructor
    public zzap(@SafeParcelable.Param(id = 2) float f, @SafeParcelable.Param(id = 3) float f2, @SafeParcelable.Param(id = 4) float f3) {
        this.zza = f;
        this.zzb = f2;
        this.zzc = f3;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzap)) {
            return false;
        }
        zzap zzapVar = (zzap) obj;
        return this.zza == zzapVar.zza && this.zzb == zzapVar.zzb && this.zzc == zzapVar.zzc;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zza), Float.valueOf(this.zzb), Float.valueOf(this.zzc));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zza);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzb);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
