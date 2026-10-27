package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "CastMediaOptionsCreator")
@SafeParcelable.Reserved({1})
public class CastMediaOptions extends AbstractSafeParcelable {

    @SafeParcelable.Field(getter = "getMediaIntentReceiverClassName", id = 2)
    private final String zzb;

    @SafeParcelable.Field(getter = "getExpandedControllerActivityClassName", id = 3)
    private final String zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getImagePickerAsBinder", id = 4, type = "android.os.IBinder")
    private final zzd zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getNotificationOptions", id = 5)
    private final NotificationOptions zze;

    @SafeParcelable.Field(getter = "getDisableRemoteControlNotification", id = 6)
    private final boolean zzf;

    @SafeParcelable.Field(getter = "getMediaSessionEnabled", id = 7)
    private final boolean zzg;
    private static final Logger zza = new Logger("CastMediaOptions");

    @NonNull
    public static final Parcelable.Creator<CastMediaOptions> CREATOR = new zza();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public static final class Builder {
        private String zzb;

        @Nullable
        private ImagePicker zzc;
        private String zza = "com.google.android.gms.cast.framework.media.MediaIntentReceiver";

        @Nullable
        private NotificationOptions zzd = new NotificationOptions.Builder().build();
        private boolean zze = true;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [android.os.IBinder] */
        @NonNull
        public CastMediaOptions build() {
            ImagePicker imagePicker = this.zzc;
            return new CastMediaOptions(this.zza, this.zzb, imagePicker == null ? null : imagePicker.zza(), this.zzd, false, this.zze);
        }

        @NonNull
        public Builder setExpandedControllerActivityClassName(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setImagePicker(@Nullable ImagePicker imagePicker) {
            this.zzc = imagePicker;
            return this;
        }

        @NonNull
        public Builder setMediaIntentReceiverClassName(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setMediaSessionEnabled(boolean z) {
            this.zze = z;
            return this;
        }

        @NonNull
        public Builder setNotificationOptions(@Nullable NotificationOptions notificationOptions) {
            this.zzd = notificationOptions;
            return this;
        }
    }

    @SafeParcelable.Constructor
    CastMediaOptions(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 5) NotificationOptions notificationOptions, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) boolean z2) {
        zzd zzbVar;
        this.zzb = str;
        this.zzc = str2;
        if (iBinder == null) {
            zzbVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
            zzbVar = iInterfaceQueryLocalInterface instanceof zzd ? (zzd) iInterfaceQueryLocalInterface : new zzb(iBinder);
        }
        this.zzd = zzbVar;
        this.zze = notificationOptions;
        this.zzf = z;
        this.zzg = z2;
    }

    @NonNull
    public String getExpandedControllerActivityClassName() {
        return this.zzc;
    }

    @Nullable
    public ImagePicker getImagePicker() {
        zzd zzdVar = this.zzd;
        if (zzdVar == null) {
            return null;
        }
        try {
            return (ImagePicker) ObjectWrapper.unwrap(zzdVar.zzg());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "getWrappedClientObject", zzd.class.getSimpleName());
            return null;
        }
    }

    @NonNull
    public String getMediaIntentReceiverClassName() {
        return this.zzb;
    }

    public boolean getMediaSessionEnabled() {
        return this.zzg;
    }

    @Nullable
    public NotificationOptions getNotificationOptions() {
        return this.zze;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getMediaIntentReceiverClassName(), false);
        SafeParcelWriter.writeString(parcel, 3, getExpandedControllerActivityClassName(), false);
        zzd zzdVar = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 4, zzdVar == null ? null : zzdVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getNotificationOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 7, getMediaSessionEnabled());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @ShowFirstParty
    public final boolean zza() {
        return this.zzf;
    }
}
