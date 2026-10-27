package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "MessageOptionsCreator")
@SafeParcelable.Reserved({1})
public class MessageOptions extends AbstractSafeParcelable implements ReflectedParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<MessageOptions> CREATOR = new zzf();
    public static final int MESSAGE_PRIORITY_HIGH = 1;
    public static final int MESSAGE_PRIORITY_LOW = 0;

    @SafeParcelable.Field(getter = "getPriority", id = 2)
    private final int zza;

    @SafeParcelable.Constructor
    public MessageOptions(@SafeParcelable.Param(id = 2) int i) {
        this.zza = i;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof MessageOptions) && this.zza == ((MessageOptions) obj).zza;
    }

    public int getPriority() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza));
    }

    @RecentlyNonNull
    public String toString() {
        return "MessageOptions[ priority=" + this.zza + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getPriority());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
