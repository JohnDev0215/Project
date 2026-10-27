package android.os;

import android.media.AudioAttributes;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.util.Slog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class VibrationAttributes implements Parcelable {
    public static final Parcelable.Creator<VibrationAttributes> CREATOR = new Parcelable.Creator<VibrationAttributes>() { // from class: android.os.VibrationAttributes.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibrationAttributes createFromParcel(Parcel parcel) {
            return new VibrationAttributes(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibrationAttributes[] newArray(int i) {
            return new VibrationAttributes[i];
        }
    };
    public static final int FLAG_BYPASS_INTERRUPTION_POLICY = 1;
    private static final long MAX_HAPTIC_FEEDBACK_DURATION = 5000;
    private static final String TAG = "VibrationAttributes";
    public static final int USAGE_ALARM = 17;
    public static final int USAGE_CLASS_ALARM = 1;
    public static final int USAGE_CLASS_FEEDBACK = 2;
    public static final int USAGE_CLASS_MASK = 15;
    public static final int USAGE_CLASS_UNKNOWN = 0;
    public static final int USAGE_COMMUNICATION_REQUEST = 65;
    public static final int USAGE_HARDWARE_FEEDBACK = 50;
    public static final int USAGE_NOTIFICATION = 49;
    public static final int USAGE_PHYSICAL_EMULATION = 34;
    public static final int USAGE_RINGTONE = 33;
    public static final int USAGE_TOUCH = 18;
    public static final int USAGE_UNKNOWN = 0;
    private final AudioAttributes mAudioAttributes;
    private final int mFlags;
    private final int mUsage;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Usage {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UsageClass {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VibrationAttributes(int i, int i2, AudioAttributes audioAttributes) {
        this.mUsage = i;
        this.mFlags = i2;
        this.mAudioAttributes = audioAttributes;
    }

    public int getUsageClass() {
        return this.mUsage & 15;
    }

    public int getUsage() {
        return this.mUsage;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean isFlagSet(int i) {
        return (this.mFlags & i) > 0;
    }

    @Deprecated
    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mUsage);
        parcel.writeInt(this.mFlags);
        parcel.writeParcelable(this.mAudioAttributes, i);
    }

    private VibrationAttributes(Parcel parcel) {
        this.mUsage = parcel.readInt();
        this.mFlags = parcel.readInt();
        this.mAudioAttributes = (AudioAttributes) parcel.readParcelable(AudioAttributes.class.getClassLoader());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VibrationAttributes vibrationAttributes = (VibrationAttributes) obj;
        return this.mUsage == vibrationAttributes.mUsage && this.mFlags == vibrationAttributes.mFlags;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mUsage), Integer.valueOf(this.mFlags));
    }

    public String toString() {
        return "VibrationAttributes: Usage=" + usageToString() + " Flags=" + this.mFlags;
    }

    public String usageToString() {
        return usageToString(this.mUsage);
    }

    public String usageToString(int i) {
        return i != 0 ? i != 65 ? i != 17 ? i != 18 ? i != 33 ? i != 34 ? i != 49 ? i != 50 ? "unknown usage " + i : "HARDWARE_FEEDBACK" : "NOTIFICATION" : "PHYSICAL_EMULATION" : "RIGNTONE" : "TOUCH" : "ALARM" : "COMMUNICATION_REQUEST" : "UNKNOWN";
    }

    public static final class Builder {
        private AudioAttributes mAudioAttributes;
        private int mFlags;
        private int mUsage;

        public Builder() {
            this.mUsage = 0;
            this.mFlags = 0;
            this.mAudioAttributes = new AudioAttributes.Builder().build();
        }

        public Builder(VibrationAttributes vibrationAttributes) {
            this.mUsage = 0;
            this.mFlags = 0;
            this.mAudioAttributes = new AudioAttributes.Builder().build();
            if (vibrationAttributes != null) {
                this.mUsage = vibrationAttributes.mUsage;
                this.mFlags = vibrationAttributes.mFlags;
                this.mAudioAttributes = vibrationAttributes.mAudioAttributes;
            }
        }

        public Builder(AudioAttributes audioAttributes, VibrationEffect vibrationEffect) {
            this.mUsage = 0;
            this.mFlags = 0;
            this.mAudioAttributes = new AudioAttributes.Builder().build();
            this.mAudioAttributes = audioAttributes;
            setUsage(audioAttributes);
            setFlags(audioAttributes);
            applyHapticFeedbackHeuristics(vibrationEffect);
        }

        private void applyHapticFeedbackHeuristics(VibrationEffect vibrationEffect) {
            if (vibrationEffect != null) {
                if (this.mUsage == 0 && (vibrationEffect instanceof VibrationEffect.Prebaked)) {
                    int id = ((VibrationEffect.Prebaked) vibrationEffect).getId();
                    if (id == 0 || id == 1 || id == 2 || id == 3 || id == 4 || id == 5 || id == 21) {
                        this.mUsage = 18;
                    } else {
                        Slog.w(VibrationAttributes.TAG, "Unknown prebaked vibration effect, assuming it isn't haptic feedback");
                    }
                }
                long duration = vibrationEffect.getDuration();
                if (this.mUsage != 0 || duration < 0 || duration >= 5000) {
                    return;
                }
                this.mUsage = 18;
            }
        }

        private void setUsage(AudioAttributes audioAttributes) {
            switch (audioAttributes.getUsage()) {
                case 4:
                    this.mUsage = 17;
                    break;
                case 5:
                case 8:
                case 9:
                case 10:
                    this.mUsage = 49;
                    break;
                case 6:
                    this.mUsage = 33;
                    break;
                case 7:
                case 11:
                    this.mUsage = 65;
                    break;
                case 12:
                default:
                    this.mUsage = 0;
                    break;
                case 13:
                    this.mUsage = 18;
                    break;
            }
        }

        private void setFlags(AudioAttributes audioAttributes) {
            if ((audioAttributes.getAllFlags() & 64) != 0) {
                this.mFlags |= 1;
            }
        }

        public VibrationAttributes build() {
            return new VibrationAttributes(this.mUsage, this.mFlags, this.mAudioAttributes);
        }

        public Builder setUsage(int i) {
            this.mUsage = i;
            return this;
        }

        public Builder replaceFlags(int i) {
            this.mFlags = i;
            return this;
        }

        public Builder setFlags(int i, int i2) {
            this.mFlags = (i & i2) | (this.mFlags & (~i2));
            return this;
        }
    }
}
