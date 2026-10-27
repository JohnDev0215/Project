package cerence.ark.assistant.framework.data;

import android.app.Notification;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class Config implements Parcelable {
    public static final Parcelable.Creator<Config> CREATOR = new Parcelable.Creator<Config>() { // from class: cerence.ark.assistant.framework.data.Config.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Config createFromParcel(Parcel parcel) {
            return new Config(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Config[] newArray(int i) {
            return new Config[i];
        }
    };
    private final String mAsrLanguage;
    private final AudioPlaybackMode mAudioPlaybackMode;
    private final AudioRecordingMode mAudioRecordingMode;
    private final String mDataPath;
    private final String mDeviceId;
    private final String mDeviceModel;
    private final boolean mIsSaveInputAudio;
    private final boolean mIsSaveOutputAudio;
    private final long mMaxAudioLogSize;
    private final Notification mNotification;
    private final String mUserId;
    private final String mWritablePath;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private Config(Builder builder) {
        this.mIsSaveOutputAudio = builder.mIsSaveOutputAudio;
        this.mIsSaveInputAudio = builder.mIsSaveInputAudio;
        this.mMaxAudioLogSize = builder.mMaxAudioLogSize;
        this.mUserId = builder.mUserId;
        this.mAsrLanguage = builder.mAsrLanguage;
        this.mDataPath = builder.mDataPath;
        this.mWritablePath = builder.mWritablePath;
        this.mNotification = builder.mNotification;
        this.mDeviceId = builder.mDeviceId;
        this.mAudioRecordingMode = builder.mAudioRecordingMode;
        this.mAudioPlaybackMode = builder.mAudioPlaybackMode;
        this.mDeviceModel = builder.mDeviceModel;
    }

    protected Config(Parcel parcel) {
        this.mIsSaveOutputAudio = parcel.readByte() != 0;
        this.mIsSaveInputAudio = parcel.readByte() != 0;
        this.mMaxAudioLogSize = parcel.readLong();
        this.mUserId = parcel.readString();
        this.mAsrLanguage = parcel.readString();
        this.mDataPath = parcel.readString();
        this.mWritablePath = parcel.readString();
        this.mNotification = (Notification) parcel.readParcelable(Notification.class.getClassLoader());
        this.mDeviceId = parcel.readString();
        this.mAudioRecordingMode = (AudioRecordingMode) parcel.readParcelable(AudioRecordingMode.class.getClassLoader());
        this.mAudioPlaybackMode = (AudioPlaybackMode) parcel.readParcelable(AudioPlaybackMode.class.getClassLoader());
        this.mDeviceModel = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsSaveOutputAudio ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsSaveInputAudio ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mMaxAudioLogSize);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mAsrLanguage);
        parcel.writeString(this.mDataPath);
        parcel.writeString(this.mWritablePath);
        parcel.writeParcelable(this.mNotification, i);
        parcel.writeString(this.mDeviceId);
        parcel.writeParcelable(this.mAudioRecordingMode, i);
        parcel.writeParcelable(this.mAudioPlaybackMode, i);
        parcel.writeString(this.mDeviceModel);
    }

    public boolean isSaveOutputAudio() {
        return this.mIsSaveOutputAudio;
    }

    public boolean isSaveInputAudio() {
        return this.mIsSaveInputAudio;
    }

    public long getMaxAudioLogSize() {
        return this.mMaxAudioLogSize;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getAsrLanguage() {
        return this.mAsrLanguage;
    }

    public String getDataPath() {
        return this.mDataPath;
    }

    public String getWritablePath() {
        return this.mWritablePath;
    }

    public Notification getForegroundServiceNotification() {
        return this.mNotification;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public AudioRecordingMode getAudioRecordingMode() {
        return this.mAudioRecordingMode;
    }

    public AudioPlaybackMode getAudioPlaybackMode() {
        return this.mAudioPlaybackMode;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public String toString() {
        return "Config: \nmIsSaveOutputAudio  = " + this.mIsSaveOutputAudio + "\nmIsSaveInputAudio   = " + this.mIsSaveInputAudio + "\nmMaxAudioLogSize    = " + this.mMaxAudioLogSize + "\nmUserId             = " + this.mUserId + "\nmAsrLanguage        = " + this.mAsrLanguage + "\nmDataPath           = " + this.mDataPath + "\nmWritablePath       = " + this.mWritablePath + "\nmNotification       = " + this.mNotification + "\nmDeviceId           = " + this.mDeviceId + "\nmAudioRecordingMode = " + this.mAudioRecordingMode + "\nmAudioPlaybackMode  = " + this.mAudioPlaybackMode + "\nmDeviceModel        = " + this.mDeviceModel;
    }

    public static class Builder {
        private String mAsrLanguage;
        private String mDataPath;
        private String mDeviceId;
        private String mDeviceModel;
        private boolean mIsSaveInputAudio;
        private boolean mIsSaveOutputAudio;
        private Notification mNotification;
        private String mUserId;
        private String mWritablePath;
        private long mMaxAudioLogSize = 104857600;
        private AudioRecordingMode mAudioRecordingMode = AudioRecordingMode.SDK;
        private AudioPlaybackMode mAudioPlaybackMode = AudioPlaybackMode.SDK;

        public Builder isSaveOutputAudio(boolean z) {
            this.mIsSaveOutputAudio = z;
            return this;
        }

        public Builder isSaveInputAudio(boolean z) {
            this.mIsSaveInputAudio = z;
            return this;
        }

        public Builder setMaxAudioLogSize(long j) {
            this.mMaxAudioLogSize = j;
            return this;
        }

        public Builder userId(String str) {
            this.mUserId = str;
            return this;
        }

        public Builder setAsrLanguage(String str) {
            this.mAsrLanguage = str;
            return this;
        }

        public Builder dataPath(String str) {
            this.mDataPath = str;
            return this;
        }

        public Builder writablePath(String str) {
            this.mWritablePath = str;
            return this;
        }

        public Builder setForegroundServiceNotification(Notification notification) {
            this.mNotification = notification;
            return this;
        }

        public Builder setDeviceId(String str) {
            this.mDeviceId = str;
            return this;
        }

        public Builder setAudioRecordingMode(AudioRecordingMode audioRecordingMode) {
            this.mAudioRecordingMode = audioRecordingMode;
            return this;
        }

        public Builder setAudioPlaybackMode(AudioPlaybackMode audioPlaybackMode) {
            this.mAudioPlaybackMode = audioPlaybackMode;
            return this;
        }

        public Builder setDeviceModel(String str) {
            this.mDeviceModel = str;
            return this;
        }

        public Config build() {
            return new Config(this);
        }
    }
}
