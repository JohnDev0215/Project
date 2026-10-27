package android.media;

import android.annotation.SystemApi;
import android.media.audiopolicy.AudioProductStrategy;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.proto.ProtoOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioAttributes implements Parcelable {
    public static final int ALLOW_CAPTURE_BY_ALL = 1;
    public static final int ALLOW_CAPTURE_BY_NONE = 3;
    public static final int ALLOW_CAPTURE_BY_SYSTEM = 2;
    private static final int ALL_PARCEL_FLAGS = 1;
    private static final int ATTR_PARCEL_IS_NULL_BUNDLE = -1977;
    private static final int ATTR_PARCEL_IS_VALID_BUNDLE = 1980;
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    public static final Parcelable.Creator<AudioAttributes> CREATOR;
    private static final int FLAG_ALL = 14335;
    private static final int FLAG_ALL_API_SET = 465;
    private static final int FLAG_ALL_PUBLIC = 273;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;

    @SystemApi
    public static final int FLAG_BEACON = 8;

    @SystemApi
    public static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;

    @SystemApi
    public static final int FLAG_BYPASS_MUTE = 128;
    public static final int FLAG_CAPTURE_PRIVATE = 8192;
    public static final int FLAG_DEEP_BUFFER = 512;
    public static final int FLAG_HW_AV_SYNC = 16;

    @SystemApi
    public static final int FLAG_HW_HOTWORD = 32;
    public static final int FLAG_LOW_LATENCY = 256;
    public static final int FLAG_MUTE_HAPTIC = 2048;
    public static final int FLAG_NO_MEDIA_PROJECTION = 1024;
    public static final int FLAG_NO_SYSTEM_CAPTURE = 4096;
    public static final int FLAG_SCO = 4;
    public static final int FLAG_SECURE = 2;
    public static final int FLATTEN_TAGS = 1;
    public static final int[] SDK_USAGES;
    public static final int SUPPRESSIBLE_ALARM = 4;
    public static final int SUPPRESSIBLE_CALL = 2;
    public static final int SUPPRESSIBLE_MEDIA = 5;
    public static final int SUPPRESSIBLE_NEVER = 3;
    public static final int SUPPRESSIBLE_NOTIFICATION = 1;
    public static final int SUPPRESSIBLE_SYSTEM = 6;
    public static final SparseIntArray SUPPRESSIBLE_USAGES;
    private static final int SYSTEM_USAGE_OFFSET = 1000;
    private static final String TAG = "AudioAttributes";
    public static final int USAGE_AAUTO_GUIDANCE = 34;
    public static final int USAGE_AAUTO_MEDIA = 33;
    public static final int USAGE_AAUTO_VR = 35;
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ALARM_SAFETY = 20;

    @SystemApi
    public static final int USAGE_ANNOUNCEMENT = 1003;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;

    @SystemApi
    public static final int USAGE_CALL_ASSISTANT = 17;
    public static final int USAGE_CAMERA = 23;
    public static final int USAGE_CP_ALT = 27;
    public static final int USAGE_CP_ALT_DUCK = 26;
    public static final int USAGE_CP_ESIRI = 29;
    public static final int USAGE_CP_ESIRI_DUCK = 30;
    public static final int USAGE_CP_MEDIA = 24;
    public static final int USAGE_CP_PHONE = 32;
    public static final int USAGE_CP_RING = 31;
    public static final int USAGE_CP_SIRI = 28;
    public static final int USAGE_CP_TEMP_MEDIA = 25;
    public static final int USAGE_ECALL = 21;

    @SystemApi
    public static final int USAGE_EMERGENCY = 1000;
    public static final int USAGE_GAME = 14;
    private static final int USAGE_INVALID = -1;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_POWER = 22;

    @SystemApi
    public static final int USAGE_SAFETY = 1001;
    public static final int USAGE_UE_ANNOUNCEMENT = 18;
    public static final int USAGE_UNKNOWN = 0;

    @SystemApi
    public static final int USAGE_VEHICLE_STATUS = 1002;
    public static final int USAGE_VEHICLE_STATUS_ALARM = 19;
    public static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    private static String[] mKtvPackageArry;
    private Bundle mBundle;
    private int mContentType;
    private int mFlags;
    private String mFormattedTags;
    private int mSource;
    private HashSet<String> mTags;
    private int mUsage;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeSdkUsage {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeSystemUsage {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeUsage {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CapturePolicy {
    }

    @SystemApi
    public static boolean isSystemUsage(int i) {
        return i == 17 || i == 1000 || i == 1001 || i == 1002 || i == 1003;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int usageForStreamType(int i) {
        switch (i) {
        }
        return 2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static /* synthetic */ int access$572(AudioAttributes audioAttributes, int i) {
        int i2 = i & audioAttributes.mFlags;
        audioAttributes.mFlags = i2;
        return i2;
    }

    static /* synthetic */ int access$576(AudioAttributes audioAttributes, int i) {
        int i2 = i | audioAttributes.mFlags;
        audioAttributes.mFlags = i2;
        return i2;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        SUPPRESSIBLE_USAGES = sparseIntArray;
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
        sparseIntArray.put(11, 3);
        sparseIntArray.put(2, 3);
        sparseIntArray.put(3, 3);
        sparseIntArray.put(4, 4);
        sparseIntArray.put(1, 5);
        sparseIntArray.put(12, 5);
        sparseIntArray.put(14, 5);
        sparseIntArray.put(16, 5);
        sparseIntArray.put(17, 3);
        sparseIntArray.put(18, 5);
        sparseIntArray.put(19, 5);
        sparseIntArray.put(20, 5);
        sparseIntArray.put(21, 2);
        sparseIntArray.put(22, 5);
        sparseIntArray.put(23, 5);
        sparseIntArray.put(24, 5);
        sparseIntArray.put(25, 5);
        sparseIntArray.put(26, 5);
        sparseIntArray.put(27, 3);
        sparseIntArray.put(28, 5);
        sparseIntArray.put(29, 5);
        sparseIntArray.put(30, 5);
        sparseIntArray.put(31, 2);
        sparseIntArray.put(32, 2);
        sparseIntArray.put(33, 5);
        sparseIntArray.put(34, 3);
        sparseIntArray.put(35, 5);
        sparseIntArray.put(0, 5);
        sparseIntArray.put(13, 6);
        SDK_USAGES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
        mKtvPackageArry = new String[]{"com.byd.sing", "com.stingray.karaoke.car", "com.huiaichang.byd.desktop", "com.tencentbyd.karaokecar", "com.kugou.android.ktvapp", "cn.banshenggua.aichang", "com.changba", "com.tencent.karaoke"};
        CREATOR = new Parcelable.Creator<AudioAttributes>() { // from class: android.media.AudioAttributes.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioAttributes createFromParcel(Parcel parcel) {
                return new AudioAttributes(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioAttributes[] newArray(int i) {
                return new AudioAttributes[i];
            }
        };
    }

    private AudioAttributes() {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mSource = -1;
        this.mFlags = 0;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public int getUsage() {
        if (isSystemUsage(this.mUsage)) {
            return 0;
        }
        return this.mUsage;
    }

    @SystemApi
    public int getSystemUsage() {
        return this.mUsage;
    }

    @SystemApi
    public int getCapturePreset() {
        return this.mSource;
    }

    public int getFlags() {
        return this.mFlags & 273;
    }

    @SystemApi
    public int getAllFlags() {
        return this.mFlags & FLAG_ALL;
    }

    @SystemApi
    public Bundle getBundle() {
        Bundle bundle = this.mBundle;
        return bundle == null ? bundle : new Bundle(this.mBundle);
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(this.mTags);
    }

    public boolean areHapticChannelsMuted() {
        return (this.mFlags & 2048) != 0;
    }

    public int getAllowedCapturePolicy() {
        int i = this.mFlags;
        if ((i & 4096) == 4096) {
            return 3;
        }
        return (i & 1024) == 1024 ? 2 : 1;
    }

    public static class Builder {
        private static final int PRIVACY_SENSITIVE_DEFAULT = -1;
        private static final int PRIVACY_SENSITIVE_DISABLED = 0;
        private static final int PRIVACY_SENSITIVE_ENABLED = 1;
        private Bundle mBundle;
        private int mContentType;
        private int mFlags;
        private boolean mMuteHapticChannels;
        private int mPrivacySensitive;
        private int mSource;
        private int mSystemUsage;
        private HashSet<String> mTags;
        private int mUsage;

        public Builder() {
            this.mUsage = -1;
            this.mSystemUsage = -1;
            this.mContentType = 0;
            this.mSource = -1;
            this.mFlags = 0;
            this.mMuteHapticChannels = true;
            this.mTags = new HashSet<>();
            this.mPrivacySensitive = -1;
        }

        public Builder(AudioAttributes audioAttributes) {
            this.mUsage = -1;
            this.mSystemUsage = -1;
            this.mContentType = 0;
            this.mSource = -1;
            this.mFlags = 0;
            this.mMuteHapticChannels = true;
            this.mTags = new HashSet<>();
            this.mPrivacySensitive = -1;
            this.mUsage = audioAttributes.mUsage;
            this.mContentType = audioAttributes.mContentType;
            this.mFlags = audioAttributes.getAllFlags();
            this.mTags = (HashSet) audioAttributes.mTags.clone();
            this.mMuteHapticChannels = audioAttributes.areHapticChannelsMuted();
        }

        public AudioAttributes build() {
            AudioAttributes audioAttributes = new AudioAttributes();
            audioAttributes.mContentType = this.mContentType;
            int i = this.mUsage;
            if (i == -1) {
                int i2 = this.mSystemUsage;
                if (i2 == -1) {
                    audioAttributes.mUsage = 0;
                } else {
                    audioAttributes.mUsage = i2;
                }
            } else if (this.mSystemUsage == -1) {
                audioAttributes.mUsage = i;
            } else {
                throw new IllegalArgumentException("Cannot set both usage and system usage on same builder");
            }
            audioAttributes.mSource = this.mSource;
            audioAttributes.mFlags = this.mFlags;
            if (this.mMuteHapticChannels) {
                AudioAttributes.access$576(audioAttributes, 2048);
            }
            int i3 = this.mPrivacySensitive;
            if (i3 == -1) {
                int i4 = this.mSource;
                if (i4 == 7 || i4 == 5) {
                    AudioAttributes.access$576(audioAttributes, 8192);
                } else {
                    AudioAttributes.access$572(audioAttributes, -8193);
                }
            } else if (i3 == 1) {
                AudioAttributes.access$576(audioAttributes, 8192);
            } else {
                AudioAttributes.access$572(audioAttributes, -8193);
            }
            audioAttributes.mTags = (HashSet) this.mTags.clone();
            audioAttributes.mFormattedTags = TextUtils.join(";", this.mTags);
            if (this.mBundle != null) {
                audioAttributes.mBundle = new Bundle(this.mBundle);
            }
            return audioAttributes;
        }

        public Builder setUsage(int i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                    this.mUsage = i;
                    return this;
                case 17:
                default:
                    throw new IllegalArgumentException("Invalid usage " + i);
            }
        }

        @SystemApi
        public Builder setSystemUsage(int i) {
            if (AudioAttributes.isSystemUsage(i)) {
                this.mSystemUsage = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid system usage " + i);
        }

        public Builder setContentType(int i) {
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
                this.mContentType = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid content type " + i);
        }

        public Builder setFlags(int i) {
            this.mFlags = (i & 465) | this.mFlags;
            return this;
        }

        public Builder setAllowedCapturePolicy(int i) {
            this.mFlags = AudioAttributes.capturePolicyToFlags(i, this.mFlags);
            return this;
        }

        public Builder replaceFlags(int i) {
            this.mFlags = i & AudioAttributes.FLAG_ALL;
            return this;
        }

        @SystemApi
        public Builder addBundle(Bundle bundle) {
            if (bundle == null) {
                throw new IllegalArgumentException("Illegal null bundle");
            }
            Bundle bundle2 = this.mBundle;
            if (bundle2 == null) {
                this.mBundle = new Bundle(bundle);
            } else {
                bundle2.putAll(bundle);
            }
            return this;
        }

        public Builder addTag(String str) {
            this.mTags.add(str);
            return this;
        }

        public Builder setLegacyStreamType(int i) {
            if (i == 10) {
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            }
            setInternalLegacyStreamType(i);
            return this;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public Builder setInternalLegacyStreamType(int i) {
            AudioAttributes audioAttributesForStrategyWithLegacyStreamType;
            this.mContentType = 0;
            this.mUsage = 0;
            if (AudioProductStrategy.getAudioProductStrategies().size() > 0 && (audioAttributesForStrategyWithLegacyStreamType = AudioProductStrategy.getAudioAttributesForStrategyWithLegacyStreamType(i)) != null) {
                this.mUsage = audioAttributesForStrategyWithLegacyStreamType.mUsage;
                this.mContentType = audioAttributesForStrategyWithLegacyStreamType.mContentType;
                this.mFlags = audioAttributesForStrategyWithLegacyStreamType.getAllFlags();
                this.mMuteHapticChannels = audioAttributesForStrategyWithLegacyStreamType.areHapticChannelsMuted();
                this.mTags = audioAttributesForStrategyWithLegacyStreamType.mTags;
                this.mBundle = audioAttributesForStrategyWithLegacyStreamType.mBundle;
                this.mSource = audioAttributesForStrategyWithLegacyStreamType.mSource;
            }
            if (this.mContentType == 0) {
                switch (i) {
                    case 0:
                        this.mContentType = 1;
                        break;
                    case 1:
                        this.mContentType = 4;
                        break;
                    case 2:
                        this.mContentType = 4;
                        break;
                    case 3:
                        this.mContentType = 2;
                        break;
                    case 4:
                        this.mContentType = 4;
                        break;
                    case 5:
                        this.mContentType = 4;
                        break;
                    case 6:
                        this.mContentType = 1;
                        this.mFlags |= 4;
                        break;
                    case 7:
                        this.mFlags = 1 | this.mFlags;
                        this.mContentType = 4;
                        break;
                    case 8:
                        this.mContentType = 4;
                        break;
                    case 9:
                        this.mContentType = 4;
                        this.mFlags |= 8;
                        break;
                    case 10:
                        this.mContentType = 1;
                        break;
                    default:
                        Log.e(AudioAttributes.TAG, "Invalid stream type " + i + " for AudioAttributes");
                        break;
                }
            }
            if (this.mUsage == 0) {
                this.mUsage = AudioAttributes.usageForStreamType(i);
            }
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0105  */
        @android.annotation.SystemApi
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.media.AudioAttributes.Builder setCapturePreset(int r4) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.AudioAttributes.Builder.setCapturePreset(int):android.media.AudioAttributes$Builder");
        }

        @SystemApi
        public Builder setInternalCapturePreset(int i) {
            Log.d(AudioAttributes.TAG, "sxy setInternalCapturePreset is : " + i);
            if (i == 1999 || i == 8 || i == 1998 || i == 3 || i == 2 || i == 4 || i == 1997) {
                Log.d(AudioAttributes.TAG, "sxy setInternalCapturePreset not go setCapturePreset" + i);
                this.mSource = i;
            } else {
                Log.d(AudioAttributes.TAG, "sxy before setInternalCapturePreset go setCapturePreset" + i);
                setCapturePreset(i);
                Log.d(AudioAttributes.TAG, "sxy aftersetInternalCapturePreset go setCapturePreset" + i);
            }
            return this;
        }

        public Builder setHapticChannelsMuted(boolean z) {
            this.mMuteHapticChannels = z;
            return this;
        }

        public Builder setPrivacySensitive(boolean z) {
            this.mPrivacySensitive = z ? 1 : 0;
            return this;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mUsage);
        parcel.writeInt(this.mContentType);
        parcel.writeInt(this.mSource);
        parcel.writeInt(this.mFlags);
        int i2 = i & 1;
        parcel.writeInt(i2);
        if (i2 == 0) {
            String[] strArr = new String[this.mTags.size()];
            this.mTags.toArray(strArr);
            parcel.writeStringArray(strArr);
        } else if (i2 == 1) {
            parcel.writeString(this.mFormattedTags);
        }
        if (this.mBundle == null) {
            parcel.writeInt(ATTR_PARCEL_IS_NULL_BUNDLE);
        } else {
            parcel.writeInt(1980);
            parcel.writeBundle(this.mBundle);
        }
    }

    private AudioAttributes(Parcel parcel) {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mSource = -1;
        this.mFlags = 0;
        this.mUsage = parcel.readInt();
        this.mContentType = parcel.readInt();
        this.mSource = parcel.readInt();
        this.mFlags = parcel.readInt();
        boolean z = (parcel.readInt() & 1) == 1;
        this.mTags = new HashSet<>();
        if (z) {
            String str = new String(parcel.readString());
            this.mFormattedTags = str;
            this.mTags.add(str);
        } else {
            String[] stringArray = parcel.readStringArray();
            for (int length = stringArray.length - 1; length >= 0; length--) {
                this.mTags.add(stringArray[length]);
            }
            this.mFormattedTags = TextUtils.join(";", this.mTags);
        }
        int i = parcel.readInt();
        if (i == ATTR_PARCEL_IS_NULL_BUNDLE) {
            this.mBundle = null;
        } else if (i == 1980) {
            this.mBundle = new Bundle(parcel.readBundle());
        } else {
            Log.e(TAG, "Illegal value unmarshalling AudioAttributes, can't initialize bundle");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioAttributes audioAttributes = (AudioAttributes) obj;
        return this.mContentType == audioAttributes.mContentType && this.mFlags == audioAttributes.mFlags && this.mSource == audioAttributes.mSource && this.mUsage == audioAttributes.mUsage && this.mFormattedTags.equals(audioAttributes.mFormattedTags);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mSource), Integer.valueOf(this.mUsage), this.mFormattedTags, this.mBundle);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("AudioAttributes: usage=").append(usageToString()).append(" content=").append(contentTypeToString()).append(" flags=0x").append(Integer.toHexString(this.mFlags).toUpperCase()).append(" tags=").append(this.mFormattedTags).append(" bundle=");
        Bundle bundle = this.mBundle;
        return new String(sbAppend.append(bundle == null ? "null" : bundle.toString()).toString());
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1159641169921L, this.mUsage);
        protoOutputStream.write(1159641169922L, this.mContentType);
        protoOutputStream.write(1120986464259L, this.mFlags);
        for (String str : this.mFormattedTags.split(";")) {
            String strTrim = str.trim();
            if (strTrim != "") {
                protoOutputStream.write(2237677961220L, strTrim);
            }
        }
        protoOutputStream.end(jStart);
    }

    public String usageToString() {
        return usageToString(this.mUsage);
    }

    public static String usageToString(int i) {
        switch (i) {
            case 0:
                return new String("USAGE_UNKNOWN");
            case 1:
                return new String("USAGE_MEDIA");
            case 2:
                return new String("USAGE_VOICE_COMMUNICATION");
            case 3:
                return new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");
            case 4:
                return new String("USAGE_ALARM");
            case 5:
                return new String("USAGE_NOTIFICATION");
            case 6:
                return new String("USAGE_NOTIFICATION_RINGTONE");
            case 7:
                return new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");
            case 8:
                return new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");
            case 9:
                return new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");
            case 10:
                return new String("USAGE_NOTIFICATION_EVENT");
            case 11:
                return new String("USAGE_ASSISTANCE_ACCESSIBILITY");
            case 12:
                return new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");
            case 13:
                return new String("USAGE_ASSISTANCE_SONIFICATION");
            case 14:
                return new String("USAGE_GAME");
            default:
                switch (i) {
                    case 16:
                        return new String("USAGE_ASSISTANT");
                    case 17:
                        return new String("USAGE_CALL_ASSISTANT");
                    case 18:
                        return new String("USAGE_UE_ANNOUNCEMENT");
                    case 19:
                        return new String("USAGE_VEHICLE_STATUS_ALARM");
                    case 20:
                        return new String("USAGE_ALARM_SAFETY");
                    case 21:
                        return new String("USAGE_ECALL");
                    case 22:
                        return new String("USAGE_POWER");
                    case 23:
                        return new String("USAGE_CAMERA");
                    case 24:
                        return new String("USAGE_CP_MEDIA");
                    case 25:
                        return new String("USAGE_CP_TEMP_MEDIA");
                    case 26:
                        return new String("USAGE_CP_ALT_DUCK");
                    case 27:
                        return new String("USAGE_CP_ALT");
                    case 28:
                        return new String("USAGE_CP_SIRI");
                    case 29:
                        return new String("USAGE_CP_ESIRI");
                    case 30:
                        return new String("USAGE_CP_ESIRI_DUCK");
                    case 31:
                        return new String("USAGE_CP_RING");
                    case 32:
                        return new String("USAGE_CP_PHONE");
                    case 33:
                        return new String("USAGE_AAUTO_MEDIA");
                    case 34:
                        return new String("USAGE_AAUTO_GUIDANCE");
                    case 35:
                        return new String("USAGE_AAUTO_VR");
                    default:
                        switch (i) {
                            case 1000:
                                return new String("USAGE_EMERGENCY");
                            case 1001:
                                return new String("USAGE_SAFETY");
                            case 1002:
                                return new String("USAGE_VEHICLE_STATUS");
                            case 1003:
                                return new String("USAGE_ANNOUNCEMENT");
                            default:
                                return new String("unknown usage " + i);
                        }
                }
        }
    }

    public String contentTypeToString() {
        int i = this.mContentType;
        if (i == 0) {
            return new String("CONTENT_TYPE_UNKNOWN");
        }
        if (i == 1) {
            return new String("CONTENT_TYPE_SPEECH");
        }
        if (i == 2) {
            return new String("CONTENT_TYPE_MUSIC");
        }
        if (i == 3) {
            return new String("CONTENT_TYPE_MOVIE");
        }
        if (i == 4) {
            return new String("CONTENT_TYPE_SONIFICATION");
        }
        return new String("unknown content type " + this.mContentType);
    }

    public int getVolumeControlStream() {
        return toVolumeStreamType(true, this);
    }

    public static int toLegacyStreamType(AudioAttributes audioAttributes) {
        return toVolumeStreamType(false, audioAttributes);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int toVolumeStreamType(boolean r6, android.media.AudioAttributes r7) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioAttributes.toVolumeStreamType(boolean, android.media.AudioAttributes):int");
    }

    public static int capturePolicyToFlags(int i, int i2) {
        if (i == 1) {
            return i2 & (-5121);
        }
        if (i == 2) {
            return (i2 | 1024) & (-4097);
        }
        if (i == 3) {
            return i2 | 5120;
        }
        throw new IllegalArgumentException("Unknown allow playback capture policy");
    }
}
