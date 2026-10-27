package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "NotificationActionCreator")
@SafeParcelable.Reserved({1})
public class NotificationAction extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<NotificationAction> CREATOR = new zzx();

    @SafeParcelable.Field(getter = "getAction", id = 2)
    private final String zza;

    @SafeParcelable.Field(getter = "getIconResId", id = 3)
    private final int zzb;

    @SafeParcelable.Field(getter = "getContentDescription", id = 4)
    private final String zzc;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public static final class Builder {
        String zza;
        int zzb;
        String zzc;

        @NonNull
        public NotificationAction build() {
            return new NotificationAction(this.zza, this.zzb, this.zzc);
        }

        @NonNull
        public Builder setAction(@NonNull String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("action cannot be null or an empty string.");
            }
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setContentDescription(@NonNull String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("contentDescription cannot be null  or an empty string.");
            }
            this.zzc = str;
            return this;
        }

        @NonNull
        public Builder setIconResId(int i) {
            this.zzb = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    NotificationAction(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) String str2) {
        this.zza = str;
        this.zzb = i;
        this.zzc = str2;
    }

    @NonNull
    public String getAction() {
        return this.zza;
    }

    @NonNull
    public String getContentDescription() {
        return this.zzc;
    }

    public int getIconResId() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAction(), false);
        SafeParcelWriter.writeInt(parcel, 3, getIconResId());
        SafeParcelWriter.writeString(parcel, 4, getContentDescription(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
