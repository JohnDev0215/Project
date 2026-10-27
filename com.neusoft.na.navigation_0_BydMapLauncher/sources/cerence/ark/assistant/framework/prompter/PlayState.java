package cerence.ark.assistant.framework.prompter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayState implements Parcelable {
    STARTED,
    PAUSED,
    STOPPED;

    public static final Parcelable.Creator<PlayState> CREATOR = new Parcelable.Creator<PlayState>() { // from class: cerence.ark.assistant.framework.prompter.PlayState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayState createFromParcel(Parcel parcel) {
            return PlayState.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayState[] newArray(int i) {
            return new PlayState[i];
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
