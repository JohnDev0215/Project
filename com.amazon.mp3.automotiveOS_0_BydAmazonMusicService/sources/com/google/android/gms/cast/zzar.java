package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "EqualizerSettingsCreator")
@SafeParcelable.Reserved({1})
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();

    @Nullable
    @SafeParcelable.Field(getter = "getLowShelf", id = 2)
    private final zzap zza;

    @Nullable
    @SafeParcelable.Field(getter = "getHighShelf", id = 3)
    private final zzap zzb;

    @SafeParcelable.Constructor
    public zzar(@Nullable @SafeParcelable.Param(id = 2) zzap zzapVar, @Nullable @SafeParcelable.Param(id = 3) zzap zzapVar2) {
        this.zza = zzapVar;
        this.zzb = zzapVar2;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzar)) {
            return false;
        }
        zzar zzarVar = (zzar) obj;
        return CastUtils.zzh(this.zza, zzarVar.zza) && CastUtils.zzh(this.zzb, zzarVar.zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
