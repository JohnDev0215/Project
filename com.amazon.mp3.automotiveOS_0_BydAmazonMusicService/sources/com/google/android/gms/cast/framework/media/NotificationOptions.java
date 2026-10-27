package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.framework.media.internal.ResourceProvider;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "NotificationOptionsCreator")
@SafeParcelable.Reserved({1})
public class NotificationOptions extends AbstractSafeParcelable {
    public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000;
    public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000;

    @SafeParcelable.Field(getter = "getForwardTitleResId", id = 26)
    private final int zzA;

    @SafeParcelable.Field(getter = "getForward10TitleResId", id = 27)
    private final int zzB;

    @SafeParcelable.Field(getter = "getForward30TitleResId", id = 28)
    private final int zzC;

    @SafeParcelable.Field(getter = "getRewindTitleResId", id = 29)
    private final int zzD;

    @SafeParcelable.Field(getter = "getRewind10TitleResId", id = 30)
    private final int zzE;

    @SafeParcelable.Field(getter = "getRewind30TitleResId", id = 31)
    private final int zzF;

    @SafeParcelable.Field(getter = "getDisconnectTitleResId", id = 32)
    private final int zzG;

    @Nullable
    @SafeParcelable.Field(getter = "getNotificationActionsProviderAsBinder", id = 33, type = "android.os.IBinder")
    private final zzg zzH;

    @SafeParcelable.Field(getter = "getActions", id = 2)
    private final List zzc;

    @SafeParcelable.Field(getter = "getCompatActionIndices", id = 3)
    private final int[] zzd;

    @SafeParcelable.Field(getter = "getSkipStepMs", id = 4)
    private final long zze;

    @SafeParcelable.Field(getter = "getTargetActivityClassName", id = 5)
    private final String zzf;

    @SafeParcelable.Field(getter = "getSmallIconDrawableResId", id = 6)
    private final int zzg;

    @SafeParcelable.Field(getter = "getStopLiveStreamDrawableResId", id = 7)
    private final int zzh;

    @SafeParcelable.Field(getter = "getPauseDrawableResId", id = 8)
    private final int zzi;

    @SafeParcelable.Field(getter = "getPlayDrawableResId", id = 9)
    private final int zzj;

    @SafeParcelable.Field(getter = "getSkipNextDrawableResId", id = 10)
    private final int zzk;

    @SafeParcelable.Field(getter = "getSkipPrevDrawableResId", id = 11)
    private final int zzl;

    @SafeParcelable.Field(getter = "getForwardDrawableResId", id = 12)
    private final int zzm;

    @SafeParcelable.Field(getter = "getForward10DrawableResId", id = 13)
    private final int zzn;

    @SafeParcelable.Field(getter = "getForward30DrawableResId", id = 14)
    private final int zzo;

    @SafeParcelable.Field(getter = "getRewindDrawableResId", id = 15)
    private final int zzp;

    @SafeParcelable.Field(getter = "getRewind10DrawableResId", id = 16)
    private final int zzq;

    @SafeParcelable.Field(getter = "getRewind30DrawableResId", id = 17)
    private final int zzr;

    @SafeParcelable.Field(getter = "getDisconnectDrawableResId", id = 18)
    private final int zzs;

    @SafeParcelable.Field(getter = "getImageSizeDimenResId", id = 19)
    private final int zzt;

    @SafeParcelable.Field(getter = "getCastingToDeviceStringResId", id = 20)
    private final int zzu;

    @SafeParcelable.Field(getter = "getStopLiveStreamTitleResId", id = 21)
    private final int zzv;

    @SafeParcelable.Field(getter = "getPauseTitleResId", id = 22)
    private final int zzw;

    @SafeParcelable.Field(getter = "getPlayTitleResId", id = 23)
    private final int zzx;

    @SafeParcelable.Field(getter = "getSkipNextTitleResId", id = 24)
    private final int zzy;

    @SafeParcelable.Field(getter = "getSkipPrevTitleResId", id = 25)
    private final int zzz;
    private static final List zza = Arrays.asList(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_STOP_CASTING);
    private static final int[] zzb = {0, 1};

    @NonNull
    public static final Parcelable.Creator<NotificationOptions> CREATOR = new zzaa();

    @SafeParcelable.Constructor
    public NotificationOptions(@NonNull @SafeParcelable.Param(id = 2) List list, @NonNull @SafeParcelable.Param(id = 3) int[] iArr, @SafeParcelable.Param(id = 4) long j, @NonNull @SafeParcelable.Param(id = 5) String str, @SafeParcelable.Param(id = 6) int i, @SafeParcelable.Param(id = 7) int i2, @SafeParcelable.Param(id = 8) int i3, @SafeParcelable.Param(id = 9) int i4, @SafeParcelable.Param(id = 10) int i5, @SafeParcelable.Param(id = 11) int i6, @SafeParcelable.Param(id = 12) int i7, @SafeParcelable.Param(id = 13) int i8, @SafeParcelable.Param(id = 14) int i9, @SafeParcelable.Param(id = 15) int i10, @SafeParcelable.Param(id = 16) int i11, @SafeParcelable.Param(id = 17) int i12, @SafeParcelable.Param(id = 18) int i13, @SafeParcelable.Param(id = 19) int i14, @SafeParcelable.Param(id = 20) int i15, @SafeParcelable.Param(id = 21) int i16, @SafeParcelable.Param(id = 22) int i17, @SafeParcelable.Param(id = 23) int i18, @SafeParcelable.Param(id = 24) int i19, @SafeParcelable.Param(id = 25) int i20, @SafeParcelable.Param(id = 26) int i21, @SafeParcelable.Param(id = 27) int i22, @SafeParcelable.Param(id = 28) int i23, @SafeParcelable.Param(id = 29) int i24, @SafeParcelable.Param(id = 30) int i25, @SafeParcelable.Param(id = 31) int i26, @SafeParcelable.Param(id = 32) int i27, @Nullable @SafeParcelable.Param(id = 33) IBinder iBinder) {
        this.zzc = new ArrayList(list);
        this.zzd = Arrays.copyOf(iArr, iArr.length);
        this.zze = j;
        this.zzf = str;
        this.zzg = i;
        this.zzh = i2;
        this.zzi = i3;
        this.zzj = i4;
        this.zzk = i5;
        this.zzl = i6;
        this.zzm = i7;
        this.zzn = i8;
        this.zzo = i9;
        this.zzp = i10;
        this.zzq = i11;
        this.zzr = i12;
        this.zzs = i13;
        this.zzt = i14;
        this.zzu = i15;
        this.zzv = i16;
        this.zzw = i17;
        this.zzx = i18;
        this.zzy = i19;
        this.zzz = i20;
        this.zzA = i21;
        this.zzB = i22;
        this.zzC = i23;
        this.zzD = i24;
        this.zzE = i25;
        this.zzF = i26;
        this.zzG = i27;
        if (iBinder == null) {
            this.zzH = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
            this.zzH = iInterfaceQueryLocalInterface instanceof zzg ? (zzg) iInterfaceQueryLocalInterface : new zze(iBinder);
        }
    }

    @NonNull
    public List<String> getActions() {
        return this.zzc;
    }

    public int getCastingToDeviceStringResId() {
        return this.zzu;
    }

    @NonNull
    public int[] getCompatActionIndices() {
        int[] iArr = this.zzd;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public int getDisconnectDrawableResId() {
        return this.zzs;
    }

    public int getForward10DrawableResId() {
        return this.zzn;
    }

    public int getForward30DrawableResId() {
        return this.zzo;
    }

    public int getForwardDrawableResId() {
        return this.zzm;
    }

    public int getPauseDrawableResId() {
        return this.zzi;
    }

    public int getPlayDrawableResId() {
        return this.zzj;
    }

    public int getRewind10DrawableResId() {
        return this.zzq;
    }

    public int getRewind30DrawableResId() {
        return this.zzr;
    }

    public int getRewindDrawableResId() {
        return this.zzp;
    }

    public int getSkipNextDrawableResId() {
        return this.zzk;
    }

    public int getSkipPrevDrawableResId() {
        return this.zzl;
    }

    public long getSkipStepMs() {
        return this.zze;
    }

    public int getSmallIconDrawableResId() {
        return this.zzg;
    }

    public int getStopLiveStreamDrawableResId() {
        return this.zzh;
    }

    public int getStopLiveStreamTitleResId() {
        return this.zzv;
    }

    @NonNull
    public String getTargetActivityClassName() {
        return this.zzf;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, getActions(), false);
        SafeParcelWriter.writeIntArray(parcel, 3, getCompatActionIndices(), false);
        SafeParcelWriter.writeLong(parcel, 4, getSkipStepMs());
        SafeParcelWriter.writeString(parcel, 5, getTargetActivityClassName(), false);
        SafeParcelWriter.writeInt(parcel, 6, getSmallIconDrawableResId());
        SafeParcelWriter.writeInt(parcel, 7, getStopLiveStreamDrawableResId());
        SafeParcelWriter.writeInt(parcel, 8, getPauseDrawableResId());
        SafeParcelWriter.writeInt(parcel, 9, getPlayDrawableResId());
        SafeParcelWriter.writeInt(parcel, 10, getSkipNextDrawableResId());
        SafeParcelWriter.writeInt(parcel, 11, getSkipPrevDrawableResId());
        SafeParcelWriter.writeInt(parcel, 12, getForwardDrawableResId());
        SafeParcelWriter.writeInt(parcel, 13, getForward10DrawableResId());
        SafeParcelWriter.writeInt(parcel, 14, getForward30DrawableResId());
        SafeParcelWriter.writeInt(parcel, 15, getRewindDrawableResId());
        SafeParcelWriter.writeInt(parcel, 16, getRewind10DrawableResId());
        SafeParcelWriter.writeInt(parcel, 17, getRewind30DrawableResId());
        SafeParcelWriter.writeInt(parcel, 18, getDisconnectDrawableResId());
        SafeParcelWriter.writeInt(parcel, 19, this.zzt);
        SafeParcelWriter.writeInt(parcel, 20, getCastingToDeviceStringResId());
        SafeParcelWriter.writeInt(parcel, 21, getStopLiveStreamTitleResId());
        SafeParcelWriter.writeInt(parcel, 22, this.zzw);
        SafeParcelWriter.writeInt(parcel, 23, this.zzx);
        SafeParcelWriter.writeInt(parcel, 24, this.zzy);
        SafeParcelWriter.writeInt(parcel, 25, this.zzz);
        SafeParcelWriter.writeInt(parcel, 26, this.zzA);
        SafeParcelWriter.writeInt(parcel, 27, this.zzB);
        SafeParcelWriter.writeInt(parcel, 28, this.zzC);
        SafeParcelWriter.writeInt(parcel, 29, this.zzD);
        SafeParcelWriter.writeInt(parcel, 30, this.zzE);
        SafeParcelWriter.writeInt(parcel, 31, this.zzF);
        SafeParcelWriter.writeInt(parcel, 32, this.zzG);
        zzg zzgVar = this.zzH;
        SafeParcelWriter.writeIBinder(parcel, 33, zzgVar == null ? null : zzgVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zzG;
    }

    public final int zzb() {
        return this.zzB;
    }

    public final int zzc() {
        return this.zzC;
    }

    public final int zzd() {
        return this.zzA;
    }

    public final int zze() {
        return this.zzt;
    }

    public final int zzf() {
        return this.zzw;
    }

    public final int zzg() {
        return this.zzx;
    }

    public final int zzh() {
        return this.zzE;
    }

    public final int zzi() {
        return this.zzF;
    }

    public final int zzj() {
        return this.zzD;
    }

    public final int zzk() {
        return this.zzy;
    }

    public final int zzl() {
        return this.zzz;
    }

    @Nullable
    public final zzg zzm() {
        return this.zzH;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public static final class Builder {
        private String zza;
        private NotificationActionsProvider zzc;
        private List zzb = NotificationOptions.zza;
        private int[] zzd = NotificationOptions.zzb;
        private int zze = zza("smallIconDrawableResId");
        private int zzf = zza("stopLiveStreamDrawableResId");
        private int zzg = zza("pauseDrawableResId");
        private int zzh = zza("playDrawableResId");
        private int zzi = zza("skipNextDrawableResId");
        private int zzj = zza("skipPrevDrawableResId");
        private int zzk = zza("forwardDrawableResId");
        private int zzl = zza("forward10DrawableResId");
        private int zzm = zza("forward30DrawableResId");
        private int zzn = zza("rewindDrawableResId");
        private int zzo = zza("rewind10DrawableResId");
        private int zzp = zza("rewind30DrawableResId");
        private int zzq = zza("disconnectDrawableResId");
        private long zzr = 10000;

        private static int zza(String str) {
            try {
                int i = ResourceProvider.$r8$clinit;
                Integer num = (Integer) ResourceProvider.class.getMethod("findResourceByName", String.class).invoke(null, str);
                if (num == null) {
                    return 0;
                }
                return num.intValue();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return 0;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r35v0, types: [android.os.IBinder] */
        @NonNull
        public NotificationOptions build() {
            NotificationActionsProvider notificationActionsProvider = this.zzc;
            return new NotificationOptions(this.zzb, this.zzd, this.zzr, this.zza, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, zza("notificationImageSizeDimenResId"), zza("castingToDeviceStringResId"), zza("stopLiveStreamStringResId"), zza("pauseStringResId"), zza("playStringResId"), zza("skipNextStringResId"), zza("skipPrevStringResId"), zza("forwardStringResId"), zza("forward10StringResId"), zza("forward30StringResId"), zza("rewindStringResId"), zza("rewind10StringResId"), zza("rewind30StringResId"), zza("disconnectStringResId"), notificationActionsProvider == null ? null : notificationActionsProvider.zza());
        }

        @NonNull
        public Builder setDisconnectDrawableResId(int i) {
            this.zzq = i;
            return this;
        }

        @NonNull
        public Builder setForward10DrawableResId(int i) {
            this.zzl = i;
            return this;
        }

        @NonNull
        public Builder setForward30DrawableResId(int i) {
            this.zzm = i;
            return this;
        }

        @NonNull
        public Builder setForwardDrawableResId(int i) {
            this.zzk = i;
            return this;
        }

        @NonNull
        public Builder setNotificationActionsProvider(@NonNull NotificationActionsProvider notificationActionsProvider) {
            if (notificationActionsProvider == null) {
                throw new IllegalArgumentException("notificationActionsProvider cannot be null.");
            }
            this.zzc = notificationActionsProvider;
            return this;
        }

        @NonNull
        public Builder setPauseDrawableResId(int i) {
            this.zzg = i;
            return this;
        }

        @NonNull
        public Builder setPlayDrawableResId(int i) {
            this.zzh = i;
            return this;
        }

        @NonNull
        public Builder setRewind10DrawableResId(int i) {
            this.zzo = i;
            return this;
        }

        @NonNull
        public Builder setRewind30DrawableResId(int i) {
            this.zzp = i;
            return this;
        }

        @NonNull
        public Builder setRewindDrawableResId(int i) {
            this.zzn = i;
            return this;
        }

        @NonNull
        public Builder setSkipNextDrawableResId(int i) {
            this.zzi = i;
            return this;
        }

        @NonNull
        public Builder setSkipPrevDrawableResId(int i) {
            this.zzj = i;
            return this;
        }

        @NonNull
        public Builder setSkipStepMs(long j) {
            Preconditions.checkArgument(j > 0, "skipStepMs must be positive.");
            this.zzr = j;
            return this;
        }

        @NonNull
        public Builder setSmallIconDrawableResId(int i) {
            this.zze = i;
            return this;
        }

        @NonNull
        public Builder setStopLiveStreamDrawableResId(int i) {
            this.zzf = i;
            return this;
        }

        @NonNull
        public Builder setTargetActivityClassName(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setActions(@Nullable List<String> list, @Nullable int[] iArr) {
            if (list == null && iArr != null) {
                throw new IllegalArgumentException("When setting actions to null, you must also set compatActionIndices to null.");
            }
            if (list != null && iArr == null) {
                throw new IllegalArgumentException("When setting compatActionIndices to null, you must also set actions to null.");
            }
            if (list == null || iArr == null) {
                this.zzb = NotificationOptions.zza;
                this.zzd = NotificationOptions.zzb;
            } else {
                int size = list.size();
                int length = iArr.length;
                if (length > size) {
                    throw new IllegalArgumentException(String.format(Locale.ROOT, "Invalid number of compat actions: %d > %d.", Integer.valueOf(length), Integer.valueOf(size)));
                }
                for (int i : iArr) {
                    if (i < 0 || i >= size) {
                        throw new IllegalArgumentException(String.format(Locale.ROOT, "Index %d in compatActionIndices out of range: [0, %d]", Integer.valueOf(i), Integer.valueOf(size - 1)));
                    }
                }
                this.zzb = new ArrayList(list);
                this.zzd = Arrays.copyOf(iArr, iArr.length);
            }
            return this;
        }
    }
}
