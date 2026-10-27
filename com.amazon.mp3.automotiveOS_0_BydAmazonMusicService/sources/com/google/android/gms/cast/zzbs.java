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
@SafeParcelable.Class(creator = "JoinOptionsCreator")
@SafeParcelable.Reserved({1})
public final class zzbs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbs> CREATOR = new zzbt();

    @SafeParcelable.Field(getter = "getConnectionType", id = 2)
    private final int zza;

    public zzbs() {
        this.zza = 0;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzbs) && this.zza == ((zzbs) obj).zza;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza));
    }

    public final String toString() {
        int i = this.zza;
        return String.format("joinOptions(connectionType=%s)", i != 0 ? i != 2 ? "UNKNOWN" : "INVISIBLE" : "STRONG");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzbs(@SafeParcelable.Param(id = 2) int i) {
        this.zza = i;
    }
}
