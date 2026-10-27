package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum AudioRecordingMode implements Parcelable {
    APP,
    SDK,
    PAL;

    public static final Parcelable.Creator<AudioRecordingMode> CREATOR = new Parcelable.Creator<AudioRecordingMode>() { // from class: cerence.ark.assistant.framework.data.AudioRecordingMode.1
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
