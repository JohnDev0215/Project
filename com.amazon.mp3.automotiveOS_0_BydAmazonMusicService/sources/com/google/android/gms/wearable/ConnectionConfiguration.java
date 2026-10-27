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
import com.google.firebase.perf.util.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "ConnectionConfigurationCreator")
@SafeParcelable.Reserved({1})
public class ConnectionConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<ConnectionConfiguration> CREATOR = new zzd();

    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 2)
    private final String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getAddress", id = 3)
    private final String zzb;

    @SafeParcelable.Field(getter = "getType", id = 4)
    private final int zzc;

    @SafeParcelable.Field(getter = "getRole", id = 5)
    private final int zzd;

    @SafeParcelable.Field(getter = Constants.ENABLE_DISABLE, id = 6)
    private final boolean zze;

    @SafeParcelable.Field(getter = "isConnected", id = 7)
    private volatile boolean zzf;

    @Nullable
    @SafeParcelable.Field(getter = "getPeerNodeId", id = 8)
    private volatile String zzg;

    @SafeParcelable.Field(getter = "getBtlePriority", id = 9)
    private boolean zzh;

    @Nullable
    @SafeParcelable.Field(getter = "getNodeId", id = 10)
    private String zzi;

    @Nullable
    @SafeParcelable.Field(getter = "getPackageName", id = 11)
    private String zzj;

    @SafeParcelable.Constructor
    ConnectionConfiguration(@Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) boolean z2, @Nullable @SafeParcelable.Param(id = 8) String str3, @SafeParcelable.Param(id = 9) boolean z3, @Nullable @SafeParcelable.Param(id = 10) String str4, @Nullable @SafeParcelable.Param(id = 11) String str5) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = i;
        this.zzd = i2;
        this.zze = z;
        this.zzf = z2;
        this.zzg = str3;
        this.zzh = z3;
        this.zzi = str4;
        this.zzj = str5;
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ConnectionConfiguration)) {
            return false;
        }
        ConnectionConfiguration connectionConfiguration = (ConnectionConfiguration) obj;
        return Objects.equal(this.zza, connectionConfiguration.zza) && Objects.equal(this.zzb, connectionConfiguration.zzb) && Objects.equal(Integer.valueOf(this.zzc), Integer.valueOf(connectionConfiguration.zzc)) && Objects.equal(Integer.valueOf(this.zzd), Integer.valueOf(connectionConfiguration.zzd)) && Objects.equal(Boolean.valueOf(this.zze), Boolean.valueOf(connectionConfiguration.zze)) && Objects.equal(Boolean.valueOf(this.zzh), Boolean.valueOf(connectionConfiguration.zzh));
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Boolean.valueOf(this.zze), Boolean.valueOf(this.zzh));
    }

    @RecentlyNonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder("ConnectionConfiguration[ ");
        String strValueOf = String.valueOf(this.zza);
        sb.append(strValueOf.length() != 0 ? "Name=".concat(strValueOf) : new String("Name="));
        String strValueOf2 = String.valueOf(this.zzb);
        sb.append(strValueOf2.length() != 0 ? ", Address=".concat(strValueOf2) : new String(", Address="));
        int i = this.zzc;
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append(", Type=");
        sb2.append(i);
        sb.append(sb2.toString());
        int i2 = this.zzd;
        StringBuilder sb3 = new StringBuilder(18);
        sb3.append(", Role=");
        sb3.append(i2);
        sb.append(sb3.toString());
        boolean z = this.zze;
        StringBuilder sb4 = new StringBuilder(15);
        sb4.append(", Enabled=");
        sb4.append(z);
        sb.append(sb4.toString());
        boolean z2 = this.zzf;
        StringBuilder sb5 = new StringBuilder(19);
        sb5.append(", IsConnected=");
        sb5.append(z2);
        sb.append(sb5.toString());
        String strValueOf3 = String.valueOf(this.zzg);
        sb.append(strValueOf3.length() != 0 ? ", PeerNodeId=".concat(strValueOf3) : new String(", PeerNodeId="));
        boolean z3 = this.zzh;
        StringBuilder sb6 = new StringBuilder(20);
        sb6.append(", BtlePriority=");
        sb6.append(z3);
        sb.append(sb6.toString());
        String strValueOf4 = String.valueOf(this.zzi);
        sb.append(strValueOf4.length() != 0 ? ", NodeId=".concat(strValueOf4) : new String(", NodeId="));
        String strValueOf5 = String.valueOf(this.zzj);
        sb.append(strValueOf5.length() != 0 ? ", PackageName=".concat(strValueOf5) : new String(", PackageName="));
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzc);
        SafeParcelWriter.writeInt(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzf);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeString(parcel, 10, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
