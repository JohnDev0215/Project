package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "LaunchOptionsCreator")
@SafeParcelable.Reserved({1})
public class LaunchOptions extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new zzbu();

    @SafeParcelable.Field(getter = "getRelaunchIfRunning", id = 2)
    private boolean zza;

    @SafeParcelable.Field(getter = "getLanguage", id = 3)
    private String zzb;

    @SafeParcelable.Field(getter = "getAndroidReceiverCompatible", id = 4)
    private boolean zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getCredentialsData", id = 5)
    private CredentialsData zzd;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @VisibleForTesting
    public static final class Builder {
        private LaunchOptions zza;

        public Builder() {
            this.zza = new LaunchOptions();
        }

        @NonNull
        public LaunchOptions build() {
            return this.zza;
        }

        @NonNull
        public Builder setAndroidReceiverCompatible(boolean z) {
            this.zza.zzb(z);
            return this;
        }

        @NonNull
        public Builder setCredentialsData(@NonNull CredentialsData credentialsData) {
            this.zza.zzd = credentialsData;
            return this;
        }

        @NonNull
        public Builder setLocale(@NonNull Locale locale) {
            this.zza.setLanguage(CastUtils.zzd(locale));
            return this;
        }

        @NonNull
        public Builder setRelaunchIfRunning(boolean z) {
            this.zza.setRelaunchIfRunning(z);
            return this;
        }

        public Builder(@NonNull LaunchOptions launchOptions) {
            this.zza = new LaunchOptions(launchOptions.getRelaunchIfRunning(), launchOptions.getLanguage(), launchOptions.getAndroidReceiverCompatible(), launchOptions.getCredentialsData());
        }
    }

    public LaunchOptions() {
        this(false, CastUtils.zzd(Locale.getDefault()), false, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.zza == launchOptions.zza && CastUtils.zzh(this.zzb, launchOptions.zzb) && this.zzc == launchOptions.zzc && CastUtils.zzh(this.zzd, launchOptions.zzd);
    }

    public boolean getAndroidReceiverCompatible() {
        return this.zzc;
    }

    @Nullable
    public CredentialsData getCredentialsData() {
        return this.zzd;
    }

    @NonNull
    public String getLanguage() {
        return this.zzb;
    }

    public boolean getRelaunchIfRunning() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza), this.zzb, Boolean.valueOf(this.zzc), this.zzd);
    }

    public void setLanguage(@NonNull String str) {
        this.zzb = str;
    }

    public void setRelaunchIfRunning(boolean z) {
        this.zza = z;
    }

    @NonNull
    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s, androidReceiverCompatible: %b)", Boolean.valueOf(this.zza), this.zzb, Boolean.valueOf(this.zzc));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, getRelaunchIfRunning());
        SafeParcelWriter.writeString(parcel, 3, getLanguage(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, getAndroidReceiverCompatible());
        SafeParcelWriter.writeParcelable(parcel, 5, getCredentialsData(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final void zzb(boolean z) {
        this.zzc = z;
    }

    @SafeParcelable.Constructor
    LaunchOptions(@SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) boolean z2, @Nullable @SafeParcelable.Param(id = 5) CredentialsData credentialsData) {
        this.zza = z;
        this.zzb = str;
        this.zzc = z2;
        this.zzd = credentialsData;
    }
}
