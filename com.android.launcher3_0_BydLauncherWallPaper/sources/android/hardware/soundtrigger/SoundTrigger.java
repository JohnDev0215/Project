package android.hardware.soundtrigger;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.Context;
import android.media.AudioFormat;
import android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService;
import android.media.soundtrigger_middleware.SoundTriggerModuleDescriptor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.system.OsConstants;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class SoundTrigger {
    public static final int RECOGNITION_MODE_GENERIC = 8;
    public static final int RECOGNITION_MODE_USER_AUTHENTICATION = 4;
    public static final int RECOGNITION_MODE_USER_IDENTIFICATION = 2;
    public static final int RECOGNITION_MODE_VOICE_TRIGGER = 1;
    public static final int RECOGNITION_STATUS_ABORT = 1;
    public static final int RECOGNITION_STATUS_FAILURE = 2;
    public static final int RECOGNITION_STATUS_GET_STATE_RESPONSE = 3;
    public static final int RECOGNITION_STATUS_SUCCESS = 0;
    public static final int SERVICE_STATE_DISABLED = 1;
    public static final int SERVICE_STATE_ENABLED = 0;
    public static final int SOUNDMODEL_STATUS_UPDATED = 0;
    public static final int STATUS_ERROR = Integer.MIN_VALUE;
    public static final int STATUS_OK = 0;
    private static final String TAG = "SoundTrigger";
    private static ISoundTriggerMiddlewareService mService;
    public static final int STATUS_PERMISSION_DENIED = -OsConstants.EPERM;
    public static final int STATUS_NO_INIT = -OsConstants.ENODEV;
    public static final int STATUS_BAD_VALUE = -OsConstants.EINVAL;
    public static final int STATUS_DEAD_OBJECT = -OsConstants.EPIPE;
    public static final int STATUS_INVALID_OPERATION = -OsConstants.ENOSYS;
    private static Object mServiceLock = new Object();

    @Retention(RetentionPolicy.SOURCE)
    public @interface RecognitionModes {
    }

    public interface StatusListener {
        void onRecognition(RecognitionEvent recognitionEvent);

        void onServiceDied();

        void onServiceStateChange(int i);

        void onSoundModelUpdate(SoundModelEvent soundModelEvent);
    }

    private SoundTrigger() {
    }

    public static final class ModuleProperties implements Parcelable {
        public static final int AUDIO_CAPABILITY_ECHO_CANCELLATION = 1;
        public static final int AUDIO_CAPABILITY_NOISE_SUPPRESSION = 2;
        public static final Parcelable.Creator<ModuleProperties> CREATOR = new Parcelable.Creator<ModuleProperties>() { // from class: android.hardware.soundtrigger.SoundTrigger.ModuleProperties.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ModuleProperties createFromParcel(Parcel parcel) {
                return ModuleProperties.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ModuleProperties[] newArray(int i) {
                return new ModuleProperties[i];
            }
        };
        private final int mAudioCapabilities;
        private final String mDescription;
        private final int mId;
        private final String mImplementor;
        private final int mMaxBufferMillis;
        private final int mMaxKeyphrases;
        private final int mMaxSoundModels;
        private final int mMaxUsers;
        private final int mPowerConsumptionMw;
        private final int mRecognitionModes;
        private final boolean mReturnsTriggerInEvent;
        private final String mSupportedModelArch;
        private final boolean mSupportsCaptureTransition;
        private final boolean mSupportsConcurrentCapture;
        private final UUID mUuid;
        private final int mVersion;

        @Retention(RetentionPolicy.SOURCE)
        public @interface AudioCapabilities {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ModuleProperties(int i, String str, String str2, String str3, int i2, String str4, int i3, int i4, int i5, int i6, boolean z, int i7, boolean z2, int i8, boolean z3, int i9) {
            this.mId = i;
            this.mImplementor = (String) Objects.requireNonNull(str);
            this.mDescription = (String) Objects.requireNonNull(str2);
            this.mUuid = UUID.fromString((String) Objects.requireNonNull(str3));
            this.mVersion = i2;
            this.mSupportedModelArch = (String) Objects.requireNonNull(str4);
            this.mMaxSoundModels = i3;
            this.mMaxKeyphrases = i4;
            this.mMaxUsers = i5;
            this.mRecognitionModes = i6;
            this.mSupportsCaptureTransition = z;
            this.mMaxBufferMillis = i7;
            this.mSupportsConcurrentCapture = z2;
            this.mPowerConsumptionMw = i8;
            this.mReturnsTriggerInEvent = z3;
            this.mAudioCapabilities = i9;
        }

        public int getId() {
            return this.mId;
        }

        public String getImplementor() {
            return this.mImplementor;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public UUID getUuid() {
            return this.mUuid;
        }

        public int getVersion() {
            return this.mVersion;
        }

        public String getSupportedModelArch() {
            return this.mSupportedModelArch;
        }

        public int getMaxSoundModels() {
            return this.mMaxSoundModels;
        }

        public int getMaxKeyphrases() {
            return this.mMaxKeyphrases;
        }

        public int getMaxUsers() {
            return this.mMaxUsers;
        }

        public int getRecognitionModes() {
            return this.mRecognitionModes;
        }

        public boolean isCaptureTransitionSupported() {
            return this.mSupportsCaptureTransition;
        }

        public int getMaxBufferMillis() {
            return this.mMaxBufferMillis;
        }

        public boolean isConcurrentCaptureSupported() {
            return this.mSupportsConcurrentCapture;
        }

        public int getPowerConsumptionMw() {
            return this.mPowerConsumptionMw;
        }

        public boolean isTriggerReturnedInEvent() {
            return this.mReturnsTriggerInEvent;
        }

        public int getAudioCapabilities() {
            return this.mAudioCapabilities;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ModuleProperties fromParcel(Parcel parcel) {
            return new ModuleProperties(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readByte() == 1, parcel.readInt(), parcel.readByte() == 1, parcel.readInt(), parcel.readByte() == 1, parcel.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(getId());
            parcel.writeString(getImplementor());
            parcel.writeString(getDescription());
            parcel.writeString(getUuid().toString());
            parcel.writeInt(getVersion());
            parcel.writeString(getSupportedModelArch());
            parcel.writeInt(getMaxSoundModels());
            parcel.writeInt(getMaxKeyphrases());
            parcel.writeInt(getMaxUsers());
            parcel.writeInt(getRecognitionModes());
            parcel.writeByte(isCaptureTransitionSupported() ? (byte) 1 : (byte) 0);
            parcel.writeInt(getMaxBufferMillis());
            parcel.writeByte(isConcurrentCaptureSupported() ? (byte) 1 : (byte) 0);
            parcel.writeInt(getPowerConsumptionMw());
            parcel.writeByte(isTriggerReturnedInEvent() ? (byte) 1 : (byte) 0);
            parcel.writeInt(getAudioCapabilities());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof ModuleProperties)) {
                return false;
            }
            ModuleProperties moduleProperties = (ModuleProperties) obj;
            return this.mId == moduleProperties.mId && this.mImplementor.equals(moduleProperties.mImplementor) && this.mDescription.equals(moduleProperties.mDescription) && this.mUuid.equals(moduleProperties.mUuid) && this.mVersion == moduleProperties.mVersion && this.mSupportedModelArch.equals(moduleProperties.mSupportedModelArch) && this.mMaxSoundModels == moduleProperties.mMaxSoundModels && this.mMaxKeyphrases == moduleProperties.mMaxKeyphrases && this.mMaxUsers == moduleProperties.mMaxUsers && this.mRecognitionModes == moduleProperties.mRecognitionModes && this.mSupportsCaptureTransition == moduleProperties.mSupportsCaptureTransition && this.mMaxBufferMillis == moduleProperties.mMaxBufferMillis && this.mSupportsConcurrentCapture == moduleProperties.mSupportsConcurrentCapture && this.mPowerConsumptionMw == moduleProperties.mPowerConsumptionMw && this.mReturnsTriggerInEvent == moduleProperties.mReturnsTriggerInEvent && this.mAudioCapabilities == moduleProperties.mAudioCapabilities;
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((this.mId + 31) * 31) + this.mImplementor.hashCode()) * 31) + this.mDescription.hashCode()) * 31) + this.mUuid.hashCode()) * 31) + this.mVersion) * 31) + this.mSupportedModelArch.hashCode()) * 31) + this.mMaxSoundModels) * 31) + this.mMaxKeyphrases) * 31) + this.mMaxUsers) * 31) + this.mRecognitionModes) * 31) + (this.mSupportsCaptureTransition ? 1 : 0)) * 31) + this.mMaxBufferMillis) * 31) + (this.mSupportsConcurrentCapture ? 1 : 0)) * 31) + this.mPowerConsumptionMw) * 31) + (this.mReturnsTriggerInEvent ? 1 : 0)) * 31) + this.mAudioCapabilities;
        }

        public String toString() {
            return "ModuleProperties [id=" + getId() + ", implementor=" + getImplementor() + ", description=" + getDescription() + ", uuid=" + getUuid() + ", version=" + getVersion() + " , supportedModelArch=" + getSupportedModelArch() + ", maxSoundModels=" + getMaxSoundModels() + ", maxKeyphrases=" + getMaxKeyphrases() + ", maxUsers=" + getMaxUsers() + ", recognitionModes=" + getRecognitionModes() + ", supportsCaptureTransition=" + isCaptureTransitionSupported() + ", maxBufferMs=" + getMaxBufferMillis() + ", supportsConcurrentCapture=" + isConcurrentCaptureSupported() + ", powerConsumptionMw=" + getPowerConsumptionMw() + ", returnsTriggerInEvent=" + isTriggerReturnedInEvent() + ", audioCapabilities=" + getAudioCapabilities() + "]";
        }
    }

    public static class SoundModel {
        public static final int TYPE_GENERIC_SOUND = 1;
        public static final int TYPE_KEYPHRASE = 0;
        public static final int TYPE_UNKNOWN = -1;
        private final byte[] mData;
        private final int mType;
        private final UUID mUuid;
        private final UUID mVendorUuid;
        private final int mVersion;

        @Retention(RetentionPolicy.SOURCE)
        public @interface SoundModelType {
        }

        public SoundModel(UUID uuid, UUID uuid2, int i, byte[] bArr, int i2) {
            this.mUuid = (UUID) Objects.requireNonNull(uuid);
            this.mVendorUuid = uuid2 == null ? new UUID(0L, 0L) : uuid2;
            this.mType = i;
            this.mVersion = i2;
            this.mData = bArr == null ? new byte[0] : bArr;
        }

        public UUID getUuid() {
            return this.mUuid;
        }

        public int getType() {
            return this.mType;
        }

        public UUID getVendorUuid() {
            return this.mVendorUuid;
        }

        public int getVersion() {
            return this.mVersion;
        }

        public byte[] getData() {
            return this.mData;
        }

        public int hashCode() {
            return ((((((((getVersion() + 31) * 31) + Arrays.hashCode(getData())) * 31) + getType()) * 31) + (getUuid() == null ? 0 : getUuid().hashCode())) * 31) + (getVendorUuid() != null ? getVendorUuid().hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof SoundModel)) {
                return false;
            }
            SoundModel soundModel = (SoundModel) obj;
            if (getType() != soundModel.getType()) {
                return false;
            }
            if (getUuid() == null) {
                if (soundModel.getUuid() != null) {
                    return false;
                }
            } else if (!getUuid().equals(soundModel.getUuid())) {
                return false;
            }
            if (getVendorUuid() == null) {
                if (soundModel.getVendorUuid() != null) {
                    return false;
                }
            } else if (!getVendorUuid().equals(soundModel.getVendorUuid())) {
                return false;
            }
            return Arrays.equals(getData(), soundModel.getData()) && getVersion() == soundModel.getVersion();
        }
    }

    public static final class Keyphrase implements Parcelable {
        public static final Parcelable.Creator<Keyphrase> CREATOR = new Parcelable.Creator<Keyphrase>() { // from class: android.hardware.soundtrigger.SoundTrigger.Keyphrase.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Keyphrase createFromParcel(Parcel parcel) {
                return Keyphrase.readFromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Keyphrase[] newArray(int i) {
                return new Keyphrase[i];
            }
        };
        private final int mId;
        private final Locale mLocale;
        private final int mRecognitionModes;
        private final String mText;
        private final int[] mUsers;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Keyphrase(int i, int i2, Locale locale, String str, int[] iArr) {
            this.mId = i;
            this.mRecognitionModes = i2;
            this.mLocale = (Locale) Objects.requireNonNull(locale);
            this.mText = (String) Objects.requireNonNull(str);
            this.mUsers = iArr == null ? new int[0] : iArr;
        }

        public int getId() {
            return this.mId;
        }

        public int getRecognitionModes() {
            return this.mRecognitionModes;
        }

        public Locale getLocale() {
            return this.mLocale;
        }

        public String getText() {
            return this.mText;
        }

        public int[] getUsers() {
            return this.mUsers;
        }

        public static Keyphrase readFromParcel(Parcel parcel) {
            int[] iArr;
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            Locale localeForLanguageTag = Locale.forLanguageTag(parcel.readString());
            String string = parcel.readString();
            int i3 = parcel.readInt();
            if (i3 >= 0) {
                int[] iArr2 = new int[i3];
                parcel.readIntArray(iArr2);
                iArr = iArr2;
            } else {
                iArr = null;
            }
            return new Keyphrase(i, i2, localeForLanguageTag, string, iArr);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(getId());
            parcel.writeInt(getRecognitionModes());
            parcel.writeString(getLocale().toLanguageTag());
            parcel.writeString(getText());
            if (getUsers() != null) {
                parcel.writeInt(getUsers().length);
                parcel.writeIntArray(getUsers());
            } else {
                parcel.writeInt(-1);
            }
        }

        public int hashCode() {
            return (((((((((getText() == null ? 0 : getText().hashCode()) + 31) * 31) + getId()) * 31) + (getLocale() != null ? getLocale().hashCode() : 0)) * 31) + getRecognitionModes()) * 31) + Arrays.hashCode(getUsers());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Keyphrase keyphrase = (Keyphrase) obj;
            if (getText() == null) {
                if (keyphrase.getText() != null) {
                    return false;
                }
            } else if (!getText().equals(keyphrase.getText())) {
                return false;
            }
            if (getId() != keyphrase.getId()) {
                return false;
            }
            if (getLocale() == null) {
                if (keyphrase.getLocale() != null) {
                    return false;
                }
            } else if (!getLocale().equals(keyphrase.getLocale())) {
                return false;
            }
            return getRecognitionModes() == keyphrase.getRecognitionModes() && Arrays.equals(getUsers(), keyphrase.getUsers());
        }

        public String toString() {
            return "Keyphrase [id=" + getId() + ", recognitionModes=" + getRecognitionModes() + ", locale=" + getLocale().toLanguageTag() + ", text=" + getText() + ", users=" + Arrays.toString(getUsers()) + "]";
        }
    }

    public static final class KeyphraseSoundModel extends SoundModel implements Parcelable {
        public static final Parcelable.Creator<KeyphraseSoundModel> CREATOR = new Parcelable.Creator<KeyphraseSoundModel>() { // from class: android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public KeyphraseSoundModel createFromParcel(Parcel parcel) {
                return KeyphraseSoundModel.readFromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public KeyphraseSoundModel[] newArray(int i) {
                return new KeyphraseSoundModel[i];
            }
        };
        private final Keyphrase[] mKeyphrases;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public KeyphraseSoundModel(UUID uuid, UUID uuid2, byte[] bArr, Keyphrase[] keyphraseArr, int i) {
            super(uuid, uuid2, 0, bArr, i);
            this.mKeyphrases = keyphraseArr == null ? new Keyphrase[0] : keyphraseArr;
        }

        public KeyphraseSoundModel(UUID uuid, UUID uuid2, byte[] bArr, Keyphrase[] keyphraseArr) {
            this(uuid, uuid2, bArr, keyphraseArr, -1);
        }

        public Keyphrase[] getKeyphrases() {
            return this.mKeyphrases;
        }

        public static KeyphraseSoundModel readFromParcel(Parcel parcel) {
            return new KeyphraseSoundModel(UUID.fromString(parcel.readString()), parcel.readInt() >= 0 ? UUID.fromString(parcel.readString()) : null, parcel.readBlob(), (Keyphrase[]) parcel.createTypedArray(Keyphrase.CREATOR), parcel.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(getUuid().toString());
            if (getVendorUuid() == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(getVendorUuid().toString().length());
                parcel.writeString(getVendorUuid().toString());
            }
            parcel.writeInt(getVersion());
            parcel.writeBlob(getData());
            parcel.writeTypedArray(getKeyphrases(), i);
        }

        public String toString() {
            return "KeyphraseSoundModel [keyphrases=" + Arrays.toString(getKeyphrases()) + ", uuid=" + getUuid() + ", vendorUuid=" + getVendorUuid() + ", type=" + getType() + ", data=" + (getData() == null ? 0 : getData().length) + ", version=" + getVersion() + "]";
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.SoundModel
        public int hashCode() {
            return (super.hashCode() * 31) + Arrays.hashCode(getKeyphrases());
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.SoundModel
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return super.equals(obj) && (obj instanceof KeyphraseSoundModel) && Arrays.equals(getKeyphrases(), ((KeyphraseSoundModel) obj).getKeyphrases());
        }
    }

    public static class GenericSoundModel extends SoundModel implements Parcelable {
        public static final Parcelable.Creator<GenericSoundModel> CREATOR = new Parcelable.Creator<GenericSoundModel>() { // from class: android.hardware.soundtrigger.SoundTrigger.GenericSoundModel.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GenericSoundModel createFromParcel(Parcel parcel) {
                return GenericSoundModel.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GenericSoundModel[] newArray(int i) {
                return new GenericSoundModel[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public GenericSoundModel(UUID uuid, UUID uuid2, byte[] bArr, int i) {
            super(uuid, uuid2, 1, bArr, i);
        }

        public GenericSoundModel(UUID uuid, UUID uuid2, byte[] bArr) {
            this(uuid, uuid2, bArr, -1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static GenericSoundModel fromParcel(Parcel parcel) {
            return new GenericSoundModel(UUID.fromString(parcel.readString()), parcel.readInt() >= 0 ? UUID.fromString(parcel.readString()) : null, parcel.readBlob(), parcel.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(getUuid().toString());
            if (getVendorUuid() == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(getVendorUuid().toString().length());
                parcel.writeString(getVendorUuid().toString());
            }
            parcel.writeBlob(getData());
            parcel.writeInt(getVersion());
        }

        public String toString() {
            return "GenericSoundModel [uuid=" + getUuid() + ", vendorUuid=" + getVendorUuid() + ", type=" + getType() + ", data=" + (getData() == null ? 0 : getData().length) + ", version=" + getVersion() + "]";
        }
    }

    public static final class ModelParamRange implements Parcelable {
        public static final Parcelable.Creator<ModelParamRange> CREATOR = new Parcelable.Creator<ModelParamRange>() { // from class: android.hardware.soundtrigger.SoundTrigger.ModelParamRange.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ModelParamRange createFromParcel(Parcel parcel) {
                return new ModelParamRange(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ModelParamRange[] newArray(int i) {
                return new ModelParamRange[i];
            }
        };
        private final int mEnd;
        private final int mStart;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ModelParamRange(int i, int i2) {
            this.mStart = i;
            this.mEnd = i2;
        }

        private ModelParamRange(Parcel parcel) {
            this.mStart = parcel.readInt();
            this.mEnd = parcel.readInt();
        }

        public int getStart() {
            return this.mStart;
        }

        public int getEnd() {
            return this.mEnd;
        }

        public int hashCode() {
            return ((this.mStart + 31) * 31) + this.mEnd;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ModelParamRange modelParamRange = (ModelParamRange) obj;
            return this.mStart == modelParamRange.mStart && this.mEnd == modelParamRange.mEnd;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mStart);
            parcel.writeInt(this.mEnd);
        }

        public String toString() {
            return "ModelParamRange [start=" + this.mStart + ", end=" + this.mEnd + "]";
        }
    }

    public static class RecognitionEvent {
        public static final Parcelable.Creator<RecognitionEvent> CREATOR = new Parcelable.Creator<RecognitionEvent>() { // from class: android.hardware.soundtrigger.SoundTrigger.RecognitionEvent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RecognitionEvent createFromParcel(Parcel parcel) {
                return RecognitionEvent.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RecognitionEvent[] newArray(int i) {
                return new RecognitionEvent[i];
            }
        };
        public final boolean captureAvailable;
        public final int captureDelayMs;
        public final AudioFormat captureFormat;
        public final int capturePreambleMs;
        public final int captureSession;
        public final byte[] data;
        public final int soundModelHandle;
        public final int status;
        public final boolean triggerInData;

        public int describeContents() {
            return 0;
        }

        public RecognitionEvent(int i, int i2, boolean z, int i3, int i4, int i5, boolean z2, AudioFormat audioFormat, byte[] bArr) {
            this.status = i;
            this.soundModelHandle = i2;
            this.captureAvailable = z;
            this.captureSession = i3;
            this.captureDelayMs = i4;
            this.capturePreambleMs = i5;
            this.triggerInData = z2;
            this.captureFormat = (AudioFormat) Objects.requireNonNull(audioFormat);
            this.data = bArr == null ? new byte[0] : bArr;
        }

        public boolean isCaptureAvailable() {
            return this.captureAvailable;
        }

        public AudioFormat getCaptureFormat() {
            return this.captureFormat;
        }

        public int getCaptureSession() {
            return this.captureSession;
        }

        public byte[] getData() {
            return this.data;
        }

        protected static RecognitionEvent fromParcel(Parcel parcel) {
            return new RecognitionEvent(parcel.readInt(), parcel.readInt(), parcel.readByte() == 1, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readByte() == 1, parcel.readByte() == 1 ? new AudioFormat.Builder().setChannelMask(parcel.readInt()).setEncoding(parcel.readInt()).setSampleRate(parcel.readInt()).build() : null, parcel.readBlob());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.status);
            parcel.writeInt(this.soundModelHandle);
            parcel.writeByte(this.captureAvailable ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.captureSession);
            parcel.writeInt(this.captureDelayMs);
            parcel.writeInt(this.capturePreambleMs);
            parcel.writeByte(this.triggerInData ? (byte) 1 : (byte) 0);
            if (this.captureFormat != null) {
                parcel.writeByte((byte) 1);
                parcel.writeInt(this.captureFormat.getSampleRate());
                parcel.writeInt(this.captureFormat.getEncoding());
                parcel.writeInt(this.captureFormat.getChannelMask());
            } else {
                parcel.writeByte((byte) 0);
            }
            parcel.writeBlob(this.data);
        }

        public int hashCode() {
            int sampleRate = (((((((((this.captureAvailable ? 1231 : 1237) + 31) * 31) + this.captureDelayMs) * 31) + this.capturePreambleMs) * 31) + this.captureSession) * 31) + (this.triggerInData ? 1231 : 1237);
            AudioFormat audioFormat = this.captureFormat;
            if (audioFormat != null) {
                sampleRate = (((((sampleRate * 31) + audioFormat.getSampleRate()) * 31) + this.captureFormat.getEncoding()) * 31) + this.captureFormat.getChannelMask();
            }
            return (((((sampleRate * 31) + Arrays.hashCode(this.data)) * 31) + this.soundModelHandle) * 31) + this.status;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RecognitionEvent recognitionEvent = (RecognitionEvent) obj;
            if (this.captureAvailable != recognitionEvent.captureAvailable || this.captureDelayMs != recognitionEvent.captureDelayMs || this.capturePreambleMs != recognitionEvent.capturePreambleMs || this.captureSession != recognitionEvent.captureSession || !Arrays.equals(this.data, recognitionEvent.data) || this.soundModelHandle != recognitionEvent.soundModelHandle || this.status != recognitionEvent.status || this.triggerInData != recognitionEvent.triggerInData) {
                return false;
            }
            AudioFormat audioFormat = this.captureFormat;
            if (audioFormat == null) {
                if (recognitionEvent.captureFormat != null) {
                    return false;
                }
            } else if (recognitionEvent.captureFormat == null || audioFormat.getSampleRate() != recognitionEvent.captureFormat.getSampleRate() || this.captureFormat.getEncoding() != recognitionEvent.captureFormat.getEncoding() || this.captureFormat.getChannelMask() != recognitionEvent.captureFormat.getChannelMask()) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sbAppend = new StringBuilder().append("RecognitionEvent [status=").append(this.status).append(", soundModelHandle=").append(this.soundModelHandle).append(", captureAvailable=").append(this.captureAvailable).append(", captureSession=").append(this.captureSession).append(", captureDelayMs=").append(this.captureDelayMs).append(", capturePreambleMs=").append(this.capturePreambleMs).append(", triggerInData=").append(this.triggerInData).append(this.captureFormat == null ? "" : ", sampleRate=" + this.captureFormat.getSampleRate()).append(this.captureFormat == null ? "" : ", encoding=" + this.captureFormat.getEncoding()).append(this.captureFormat != null ? ", channelMask=" + this.captureFormat.getChannelMask() : "").append(", data=");
            byte[] bArr = this.data;
            return sbAppend.append(bArr == null ? 0 : bArr.length).append("]").toString();
        }
    }

    public static class RecognitionConfig implements Parcelable {
        public static final Parcelable.Creator<RecognitionConfig> CREATOR = new Parcelable.Creator<RecognitionConfig>() { // from class: android.hardware.soundtrigger.SoundTrigger.RecognitionConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RecognitionConfig createFromParcel(Parcel parcel) {
                return RecognitionConfig.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RecognitionConfig[] newArray(int i) {
                return new RecognitionConfig[i];
            }
        };
        public final boolean allowMultipleTriggers;
        public final int audioCapabilities;
        public final boolean captureRequested;
        public final byte[] data;
        public final KeyphraseRecognitionExtra[] keyphrases;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public RecognitionConfig(boolean z, boolean z2, KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr, byte[] bArr, int i) {
            this.captureRequested = z;
            this.allowMultipleTriggers = z2;
            this.keyphrases = keyphraseRecognitionExtraArr == null ? new KeyphraseRecognitionExtra[0] : keyphraseRecognitionExtraArr;
            this.data = bArr == null ? new byte[0] : bArr;
            this.audioCapabilities = i;
        }

        public RecognitionConfig(boolean z, boolean z2, KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr, byte[] bArr) {
            this(z, z2, keyphraseRecognitionExtraArr, bArr, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RecognitionConfig fromParcel(Parcel parcel) {
            return new RecognitionConfig(parcel.readByte() == 1, parcel.readByte() == 1, (KeyphraseRecognitionExtra[]) parcel.createTypedArray(KeyphraseRecognitionExtra.CREATOR), parcel.readBlob(), parcel.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.captureRequested ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.allowMultipleTriggers ? (byte) 1 : (byte) 0);
            parcel.writeTypedArray(this.keyphrases, i);
            parcel.writeBlob(this.data);
            parcel.writeInt(this.audioCapabilities);
        }

        public String toString() {
            return "RecognitionConfig [captureRequested=" + this.captureRequested + ", allowMultipleTriggers=" + this.allowMultipleTriggers + ", keyphrases=" + Arrays.toString(this.keyphrases) + ", data=" + Arrays.toString(this.data) + ", audioCapabilities=" + Integer.toHexString(this.audioCapabilities) + "]";
        }
    }

    public static class ConfidenceLevel implements Parcelable {
        public static final Parcelable.Creator<ConfidenceLevel> CREATOR = new Parcelable.Creator<ConfidenceLevel>() { // from class: android.hardware.soundtrigger.SoundTrigger.ConfidenceLevel.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConfidenceLevel createFromParcel(Parcel parcel) {
                return ConfidenceLevel.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConfidenceLevel[] newArray(int i) {
                return new ConfidenceLevel[i];
            }
        };
        public final int confidenceLevel;
        public final int userId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ConfidenceLevel(int i, int i2) {
            this.userId = i;
            this.confidenceLevel = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ConfidenceLevel fromParcel(Parcel parcel) {
            return new ConfidenceLevel(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.userId);
            parcel.writeInt(this.confidenceLevel);
        }

        public int hashCode() {
            return ((this.confidenceLevel + 31) * 31) + this.userId;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ConfidenceLevel confidenceLevel = (ConfidenceLevel) obj;
            return this.confidenceLevel == confidenceLevel.confidenceLevel && this.userId == confidenceLevel.userId;
        }

        public String toString() {
            return "ConfidenceLevel [userId=" + this.userId + ", confidenceLevel=" + this.confidenceLevel + "]";
        }
    }

    public static class KeyphraseRecognitionExtra implements Parcelable {
        public static final Parcelable.Creator<KeyphraseRecognitionExtra> CREATOR = new Parcelable.Creator<KeyphraseRecognitionExtra>() { // from class: android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionExtra.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public KeyphraseRecognitionExtra createFromParcel(Parcel parcel) {
                return KeyphraseRecognitionExtra.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public KeyphraseRecognitionExtra[] newArray(int i) {
                return new KeyphraseRecognitionExtra[i];
            }
        };
        public final int coarseConfidenceLevel;
        public final ConfidenceLevel[] confidenceLevels;
        public final int id;
        public final int recognitionModes;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public KeyphraseRecognitionExtra(int i, int i2, int i3, ConfidenceLevel[] confidenceLevelArr) {
            this.id = i;
            this.recognitionModes = i2;
            this.coarseConfidenceLevel = i3;
            this.confidenceLevels = confidenceLevelArr == null ? new ConfidenceLevel[0] : confidenceLevelArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static KeyphraseRecognitionExtra fromParcel(Parcel parcel) {
            return new KeyphraseRecognitionExtra(parcel.readInt(), parcel.readInt(), parcel.readInt(), (ConfidenceLevel[]) parcel.createTypedArray(ConfidenceLevel.CREATOR));
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.id);
            parcel.writeInt(this.recognitionModes);
            parcel.writeInt(this.coarseConfidenceLevel);
            parcel.writeTypedArray(this.confidenceLevels, i);
        }

        public int hashCode() {
            return ((((((Arrays.hashCode(this.confidenceLevels) + 31) * 31) + this.id) * 31) + this.recognitionModes) * 31) + this.coarseConfidenceLevel;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            KeyphraseRecognitionExtra keyphraseRecognitionExtra = (KeyphraseRecognitionExtra) obj;
            return Arrays.equals(this.confidenceLevels, keyphraseRecognitionExtra.confidenceLevels) && this.id == keyphraseRecognitionExtra.id && this.recognitionModes == keyphraseRecognitionExtra.recognitionModes && this.coarseConfidenceLevel == keyphraseRecognitionExtra.coarseConfidenceLevel;
        }

        public String toString() {
            return "KeyphraseRecognitionExtra [id=" + this.id + ", recognitionModes=" + this.recognitionModes + ", coarseConfidenceLevel=" + this.coarseConfidenceLevel + ", confidenceLevels=" + Arrays.toString(this.confidenceLevels) + "]";
        }
    }

    public static class KeyphraseRecognitionEvent extends RecognitionEvent implements Parcelable {
        public static final Parcelable.Creator<KeyphraseRecognitionEvent> CREATOR = new Parcelable.Creator<KeyphraseRecognitionEvent>() { // from class: android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionEvent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public KeyphraseRecognitionEvent createFromParcel(Parcel parcel) {
                return KeyphraseRecognitionEvent.fromParcelForKeyphrase(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public KeyphraseRecognitionEvent[] newArray(int i) {
                return new KeyphraseRecognitionEvent[i];
            }
        };
        public final KeyphraseRecognitionExtra[] keyphraseExtras;

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public KeyphraseRecognitionEvent(int i, int i2, boolean z, int i3, int i4, int i5, boolean z2, AudioFormat audioFormat, byte[] bArr, KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr) {
            super(i, i2, z, i3, i4, i5, z2, audioFormat, bArr);
            this.keyphraseExtras = keyphraseRecognitionExtraArr == null ? new KeyphraseRecognitionExtra[0] : keyphraseRecognitionExtraArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static KeyphraseRecognitionEvent fromParcelForKeyphrase(Parcel parcel) {
            return new KeyphraseRecognitionEvent(parcel.readInt(), parcel.readInt(), parcel.readByte() == 1, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readByte() == 1, parcel.readByte() == 1 ? new AudioFormat.Builder().setChannelMask(parcel.readInt()).setEncoding(parcel.readInt()).setSampleRate(parcel.readInt()).build() : null, parcel.readBlob(), (KeyphraseRecognitionExtra[]) parcel.createTypedArray(KeyphraseRecognitionExtra.CREATOR));
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.status);
            parcel.writeInt(this.soundModelHandle);
            parcel.writeByte(this.captureAvailable ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.captureSession);
            parcel.writeInt(this.captureDelayMs);
            parcel.writeInt(this.capturePreambleMs);
            parcel.writeByte(this.triggerInData ? (byte) 1 : (byte) 0);
            if (this.captureFormat != null) {
                parcel.writeByte((byte) 1);
                parcel.writeInt(this.captureFormat.getSampleRate());
                parcel.writeInt(this.captureFormat.getEncoding());
                parcel.writeInt(this.captureFormat.getChannelMask());
            } else {
                parcel.writeByte((byte) 0);
            }
            parcel.writeBlob(this.data);
            parcel.writeTypedArray(this.keyphraseExtras, i);
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent
        public int hashCode() {
            return (super.hashCode() * 31) + Arrays.hashCode(this.keyphraseExtras);
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return super.equals(obj) && getClass() == obj.getClass() && Arrays.equals(this.keyphraseExtras, ((KeyphraseRecognitionEvent) obj).keyphraseExtras);
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent
        public String toString() {
            return "KeyphraseRecognitionEvent [keyphraseExtras=" + Arrays.toString(this.keyphraseExtras) + ", status=" + this.status + ", soundModelHandle=" + this.soundModelHandle + ", captureAvailable=" + this.captureAvailable + ", captureSession=" + this.captureSession + ", captureDelayMs=" + this.captureDelayMs + ", capturePreambleMs=" + this.capturePreambleMs + ", triggerInData=" + this.triggerInData + (this.captureFormat == null ? "" : ", sampleRate=" + this.captureFormat.getSampleRate()) + (this.captureFormat == null ? "" : ", encoding=" + this.captureFormat.getEncoding()) + (this.captureFormat != null ? ", channelMask=" + this.captureFormat.getChannelMask() : "") + ", data=" + (this.data == null ? 0 : this.data.length) + "]";
        }
    }

    public static class GenericRecognitionEvent extends RecognitionEvent implements Parcelable {
        public static final Parcelable.Creator<GenericRecognitionEvent> CREATOR = new Parcelable.Creator<GenericRecognitionEvent>() { // from class: android.hardware.soundtrigger.SoundTrigger.GenericRecognitionEvent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GenericRecognitionEvent createFromParcel(Parcel parcel) {
                return GenericRecognitionEvent.fromParcelForGeneric(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GenericRecognitionEvent[] newArray(int i) {
                return new GenericRecognitionEvent[i];
            }
        };

        public GenericRecognitionEvent(int i, int i2, boolean z, int i3, int i4, int i5, boolean z2, AudioFormat audioFormat, byte[] bArr) {
            super(i, i2, z, i3, i4, i5, z2, audioFormat, bArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static GenericRecognitionEvent fromParcelForGeneric(Parcel parcel) {
            RecognitionEvent recognitionEventFromParcel = RecognitionEvent.fromParcel(parcel);
            return new GenericRecognitionEvent(recognitionEventFromParcel.status, recognitionEventFromParcel.soundModelHandle, recognitionEventFromParcel.captureAvailable, recognitionEventFromParcel.captureSession, recognitionEventFromParcel.captureDelayMs, recognitionEventFromParcel.capturePreambleMs, recognitionEventFromParcel.triggerInData, recognitionEventFromParcel.captureFormat, recognitionEventFromParcel.data);
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return super.equals(obj);
        }

        @Override // android.hardware.soundtrigger.SoundTrigger.RecognitionEvent
        public String toString() {
            return "GenericRecognitionEvent ::" + super.toString();
        }
    }

    public static class SoundModelEvent implements Parcelable {
        public static final Parcelable.Creator<SoundModelEvent> CREATOR = new Parcelable.Creator<SoundModelEvent>() { // from class: android.hardware.soundtrigger.SoundTrigger.SoundModelEvent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SoundModelEvent createFromParcel(Parcel parcel) {
                return SoundModelEvent.fromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SoundModelEvent[] newArray(int i) {
                return new SoundModelEvent[i];
            }
        };
        public final byte[] data;
        public final int soundModelHandle;
        public final int status;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        SoundModelEvent(int i, int i2, byte[] bArr) {
            this.status = i;
            this.soundModelHandle = i2;
            this.data = bArr == null ? new byte[0] : bArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static SoundModelEvent fromParcel(Parcel parcel) {
            return new SoundModelEvent(parcel.readInt(), parcel.readInt(), parcel.readBlob());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.status);
            parcel.writeInt(this.soundModelHandle);
            parcel.writeBlob(this.data);
        }

        public int hashCode() {
            return ((((Arrays.hashCode(this.data) + 31) * 31) + this.soundModelHandle) * 31) + this.status;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SoundModelEvent soundModelEvent = (SoundModelEvent) obj;
            return Arrays.equals(this.data, soundModelEvent.data) && this.soundModelHandle == soundModelEvent.soundModelHandle && this.status == soundModelEvent.status;
        }

        public String toString() {
            StringBuilder sbAppend = new StringBuilder().append("SoundModelEvent [status=").append(this.status).append(", soundModelHandle=").append(this.soundModelHandle).append(", data=");
            byte[] bArr = this.data;
            return sbAppend.append(bArr == null ? 0 : bArr.length).append("]").toString();
        }
    }

    static String getCurrentOpPackageName() {
        String strCurrentOpPackageName = ActivityThread.currentOpPackageName();
        return strCurrentOpPackageName == null ? "" : strCurrentOpPackageName;
    }

    static int handleException(Exception exc) {
        Log.w(TAG, "Exception caught", exc);
        if (exc instanceof RemoteException) {
            return STATUS_DEAD_OBJECT;
        }
        if (exc instanceof ServiceSpecificException) {
            int i = ((ServiceSpecificException) exc).errorCode;
            if (i == 2) {
                return STATUS_INVALID_OPERATION;
            }
            if (i == 3) {
                return STATUS_PERMISSION_DENIED;
            }
            if (i != 4) {
                return Integer.MIN_VALUE;
            }
            return STATUS_DEAD_OBJECT;
        }
        if (exc instanceof SecurityException) {
            return STATUS_PERMISSION_DENIED;
        }
        if (exc instanceof IllegalStateException) {
            return STATUS_INVALID_OPERATION;
        }
        if ((exc instanceof IllegalArgumentException) || (exc instanceof NullPointerException)) {
            return STATUS_BAD_VALUE;
        }
        Log.e(TAG, "Escalating unexpected exception: ", exc);
        throw new RuntimeException(exc);
    }

    public static int listModules(ArrayList<ModuleProperties> arrayList) {
        try {
            SoundTriggerModuleDescriptor[] soundTriggerModuleDescriptorArrListModules = getService().listModules();
            arrayList.clear();
            arrayList.ensureCapacity(soundTriggerModuleDescriptorArrListModules.length);
            for (SoundTriggerModuleDescriptor soundTriggerModuleDescriptor : soundTriggerModuleDescriptorArrListModules) {
                arrayList.add(ConversionUtil.aidl2apiModuleDescriptor(soundTriggerModuleDescriptor));
            }
            return 0;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    public static SoundTriggerModule attachModule(int i, StatusListener statusListener, Handler handler) {
        try {
            return new SoundTriggerModule(getService(), i, statusListener, handler != null ? handler.getLooper() : Looper.getMainLooper());
        } catch (Exception e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    private static ISoundTriggerMiddlewareService getService() {
        ISoundTriggerMiddlewareService iSoundTriggerMiddlewareServiceAsInterface;
        synchronized (mServiceLock) {
            while (true) {
                try {
                    try {
                        IBinder serviceOrThrow = ServiceManager.getServiceOrThrow(Context.SOUND_TRIGGER_MIDDLEWARE_SERVICE);
                        serviceOrThrow.linkToDeath(new IBinder.DeathRecipient() { // from class: android.hardware.soundtrigger.-$$Lambda$SoundTrigger$htwpngtYLjV2T00G07GuFUFrWFM
                            @Override // android.os.IBinder.DeathRecipient
                            public final void binderDied() {
                                SoundTrigger.lambda$getService$0();
                            }
                        }, 0);
                        iSoundTriggerMiddlewareServiceAsInterface = ISoundTriggerMiddlewareService.Stub.asInterface(serviceOrThrow);
                        mService = iSoundTriggerMiddlewareServiceAsInterface;
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to bind to soundtrigger service", e);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return iSoundTriggerMiddlewareServiceAsInterface;
    }

    static /* synthetic */ void lambda$getService$0() {
        synchronized (mServiceLock) {
            mService = null;
        }
    }
}
