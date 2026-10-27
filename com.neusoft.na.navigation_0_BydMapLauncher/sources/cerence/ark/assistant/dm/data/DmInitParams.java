package cerence.ark.assistant.dm.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class DmInitParams {
    private final AudioPlaybackMode mAudioPlaybackMode;
    private final AudioRecordingMode mAudioRecordingMode;
    private final String mDataPath;
    private final String mDeviceId;
    private final String mDeviceModel;
    private final String mUpdateStorageDir;
    private final String mUserId;
    private final String mWritablePath;

    private DmInitParams(Builder builder) {
        this.mDataPath = builder.mDataPath;
        this.mWritablePath = builder.mWritablePath;
        this.mUserId = builder.mUserId;
        this.mDeviceModel = builder.mDeviceModel;
        this.mDeviceId = builder.mDeviceId;
        this.mAudioRecordingMode = builder.mAudioRecordingMode;
        this.mAudioPlaybackMode = builder.mAudioPlaybackMode;
        this.mUpdateStorageDir = builder.mUpdateStorageDir;
    }

    public String getDataPath() {
        return this.mDataPath;
    }

    public String getWritablePath() {
        return this.mWritablePath;
    }

    public String getUpdateStorageDir() {
        return this.mUpdateStorageDir;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public AudioRecordingMode getAudioRecordingMode() {
        return this.mAudioRecordingMode;
    }

    public AudioPlaybackMode getAudioPlaybackMode() {
        return this.mAudioPlaybackMode;
    }

    public enum AudioPlaybackMode implements Parcelable {
        APP,
        SDK;

        public static final Parcelable.Creator<AudioPlaybackMode> CREATOR = new Parcelable.Creator<AudioPlaybackMode>() { // from class: cerence.ark.assistant.dm.data.DmInitParams.AudioPlaybackMode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioPlaybackMode createFromParcel(Parcel parcel) {
                return AudioPlaybackMode.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioPlaybackMode[] newArray(int i) {
                return new AudioPlaybackMode[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    public enum AudioRecordingMode implements Parcelable {
        APP,
        SDK;

        public static final Parcelable.Creator<AudioRecordingMode> CREATOR = new Parcelable.Creator<AudioRecordingMode>() { // from class: cerence.ark.assistant.dm.data.DmInitParams.AudioRecordingMode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioRecordingMode createFromParcel(Parcel parcel) {
                return AudioRecordingMode.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioRecordingMode[] newArray(int i) {
                return new AudioRecordingMode[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    public static class Builder {
        private String mDataPath;
        private String mDeviceId;
        private String mDeviceModel;
        private String mUpdateStorageDir;
        private String mUserId;
        private String mWritablePath;
        private AudioRecordingMode mAudioRecordingMode = AudioRecordingMode.SDK;
        private AudioPlaybackMode mAudioPlaybackMode = AudioPlaybackMode.SDK;

        public Builder setDataPath(String str) {
            this.mDataPath = str;
            return this;
        }

        public Builder setWritablePath(String str) {
            this.mWritablePath = str;
            return this;
        }

        public Builder setUpdateStorageDir(String str) {
            this.mUpdateStorageDir = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.mUserId = str;
            return this;
        }

        public Builder setDeviceModel(String str) {
            this.mDeviceModel = str;
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

        public DmInitParams build() {
            return new DmInitParams(this);
        }
    }
}
