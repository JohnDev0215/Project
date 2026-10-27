package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum ErrorCode implements Parcelable {
    WRITABLE_PATH_INVALID,
    DATAPACK_PATH_INVALID,
    DATAPACK_VERSION_INCOMPATIBLE,
    DATAPACK_INVALID,
    PARAMETER_INVALID,
    ASSISTANT_ALREADY_INITIALIZED,
    ASSISTANT_NOT_INITIALIZED,
    ASSISTANT_NOT_STARTED,
    OOM,
    UPDATER_ALREADY_INITIALIZED,
    UPDATER_NOT_INITIALIZED,
    RECOVERY_MODE_ALREADY_INITIALIZED,
    RECOVERY_MODE_NOT_INITIALIZED,
    UNKNOWN,
    ASSISTANT_ALREADY_STARTED,
    BIND_SPEECH_SERVICE_ERROR,
    AUDIO_RECORD_PERMISSION_DENIED,
    AUDIO_RECORD_INIT_FAIL,
    AUDIO_PLAYER_INIT_FAIL,
    VOICE_CONFIG_INVALID,
    VOICE_NOT_FOUND,
    PROMPTER_VOICES_EMPTY,
    INVALID_OPERATION,
    EXCEPTION,
    VIS_DESTROYED,
    VIS_NOT_READY,
    UPDATE_STORAGE_PATH_INVALID,
    ASSISTANT_CONFIGURATION_NULL,
    LANGUAGE_NULL,
    CONFIG_NULL;

    public static final Parcelable.Creator<ErrorCode> CREATOR = new Parcelable.Creator<ErrorCode>() { // from class: cerence.ark.assistant.framework.data.ErrorCode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorCode createFromParcel(Parcel parcel) {
            return ErrorCode.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorCode[] newArray(int i) {
            return new ErrorCode[i];
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
