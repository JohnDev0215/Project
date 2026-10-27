package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum AudioPlaybackMode implements Parcelable {
    APP,
    SDK,
    PAL;

    public static final Parcelable.Creator<AudioPlaybackMode> CREATOR = new Parcelable.Creator<AudioPlaybackMode>() { // from class: cerence.ark.assistant.framework.data.AudioPlaybackMode.1
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
