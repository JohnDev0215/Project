package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "CastOptionsCreator")
@SafeParcelable.Reserved({1})
public class CastOptions extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<CastOptions> CREATOR = new zzf();

    @SafeParcelable.Field(getter = "getReceiverApplicationId", id = 2)
    private String zza;

    @SafeParcelable.Field(getter = "getSupportedNamespaces", id = 3)
    private final List zzb;

    @SafeParcelable.Field(getter = "getStopReceiverApplicationWhenEndingSession", id = 4)
    private boolean zzc;

    @SafeParcelable.Field(getter = "getLaunchOptions", id = 5)
    private LaunchOptions zzd;

    @SafeParcelable.Field(getter = "getResumeSavedSession", id = 6)
    private final boolean zze;

    @Nullable
    @SafeParcelable.Field(getter = "getCastMediaOptions", id = 7)
    private final CastMediaOptions zzf;

    @SafeParcelable.Field(getter = "getEnableReconnectionService", id = 8)
    private final boolean zzg;

    @SafeParcelable.Field(getter = "getVolumeDeltaBeforeIceCreamSandwich", id = 9)
    private final double zzh;

    @SafeParcelable.Field(getter = "getEnableIpv6Support", id = 10)
    private final boolean zzi;

    @SafeParcelable.Field(getter = "getOutputSwitcherEnabled", id = 11)
    private final boolean zzj;

    @SafeParcelable.Field(getter = "getTransferToLocalEnabled", id = 12)
    private final boolean zzk;

    @SafeParcelable.Field(getter = "getRouteDiscoveryReceiverApplicationIds", id = 13)
    private List zzl;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    @VisibleForTesting
    public static final class Builder {
        private String zza;
        private boolean zzc;
        private List zzb = new ArrayList();
        private LaunchOptions zzd = new LaunchOptions();
        private boolean zze = true;

        @Nullable
        private zzdn zzf = null;
        private boolean zzg = true;
        private double zzh = 0.05000000074505806d;
        private List zzi = new ArrayList();

        @NonNull
        public Builder setCastMediaOptions(@NonNull CastMediaOptions castMediaOptions) {
            this.zzf = zzdn.zzb(castMediaOptions);
            return this;
        }

        @NonNull
        public Builder setEnableReconnectionService(boolean z) {
            this.zzg = z;
            return this;
        }

        @NonNull
        public Builder setLaunchOptions(@NonNull LaunchOptions launchOptions) {
            this.zzd = launchOptions;
            return this;
        }

        @NonNull
        public Builder setReceiverApplicationId(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setResumeSavedSession(boolean z) {
            this.zze = z;
            return this;
        }

        @NonNull
        public Builder setStopReceiverApplicationWhenEndingSession(boolean z) {
            this.zzc = z;
            return this;
        }

        @NonNull
        public Builder setSupportedNamespaces(@NonNull List<String> list) {
            this.zzb = list;
            return this;
        }

        @NonNull
        public Builder setVolumeDeltaBeforeIceCreamSandwich(double d) throws IllegalArgumentException {
            if (d <= 0.0d || d > 0.5d) {
                throw new IllegalArgumentException("volumeDelta must be greater than 0 and less or equal to 0.5");
            }
            this.zzh = d;
            return this;
        }

        @NonNull
        public CastOptions build() {
            zzdn zzdnVar = this.zzf;
            return new CastOptions(this.zza, this.zzb, this.zzc, this.zzd, this.zze, (CastMediaOptions) (zzdnVar != null ? zzdnVar.zza() : new CastMediaOptions.Builder().build()), this.zzg, this.zzh, false, false, false, this.zzi);
        }
    }

    @SafeParcelable.Constructor
    CastOptions(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) List list, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) LaunchOptions launchOptions, @SafeParcelable.Param(id = 6) boolean z2, @Nullable @SafeParcelable.Param(id = 7) CastMediaOptions castMediaOptions, @SafeParcelable.Param(id = 8) boolean z3, @SafeParcelable.Param(id = 9) double d, @SafeParcelable.Param(id = 10) boolean z4, @SafeParcelable.Param(id = 11) boolean z5, @SafeParcelable.Param(id = 12) boolean z6, @SafeParcelable.Param(id = 13) List list2) {
        this.zza = true == TextUtils.isEmpty(str) ? "" : str;
        int size = list == null ? 0 : list.size();
        ArrayList arrayList = new ArrayList(size);
        this.zzb = arrayList;
        if (size > 0) {
            arrayList.addAll(list);
        }
        this.zzc = z;
        this.zzd = launchOptions == null ? new LaunchOptions() : launchOptions;
        this.zze = z2;
        this.zzf = castMediaOptions;
        this.zzg = z3;
        this.zzh = d;
        this.zzi = z4;
        this.zzj = z5;
        this.zzk = z6;
        this.zzl = list2;
    }

    @Nullable
    public CastMediaOptions getCastMediaOptions() {
        return this.zzf;
    }

    public boolean getEnableReconnectionService() {
        return this.zzg;
    }

    @NonNull
    public LaunchOptions getLaunchOptions() {
        return this.zzd;
    }

    @NonNull
    public String getReceiverApplicationId() {
        return this.zza;
    }

    public boolean getResumeSavedSession() {
        return this.zze;
    }

    public boolean getStopReceiverApplicationWhenEndingSession() {
        return this.zzc;
    }

    @NonNull
    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzb);
    }

    public double getVolumeDeltaBeforeIceCreamSandwich() {
        return this.zzh;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getReceiverApplicationId(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedNamespaces(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, getStopReceiverApplicationWhenEndingSession());
        SafeParcelWriter.writeParcelable(parcel, 5, getLaunchOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, getResumeSavedSession());
        SafeParcelWriter.writeParcelable(parcel, 7, getCastMediaOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 8, getEnableReconnectionService());
        SafeParcelWriter.writeDouble(parcel, 9, getVolumeDeltaBeforeIceCreamSandwich());
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeStringList(parcel, 13, Collections.unmodifiableList(this.zzl), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @NonNull
    @ShowFirstParty
    public final List zza() {
        return Collections.unmodifiableList(this.zzl);
    }

    public final void zzb(@NonNull LaunchOptions launchOptions) {
        this.zzd = launchOptions;
    }

    public final void zzc(@NonNull String str) {
        this.zza = str;
    }

    public final boolean zzd() {
        return this.zzj;
    }

    public final boolean zze() {
        return this.zzk;
    }
}
