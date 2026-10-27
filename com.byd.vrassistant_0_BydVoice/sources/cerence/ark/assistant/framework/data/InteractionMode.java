package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum InteractionMode implements Parcelable {
    WUW,
    PTT;

    public static final Parcelable.Creator<InteractionMode> CREATOR = new Parcelable.Creator<InteractionMode>() { // from class: cerence.ark.assistant.framework.data.InteractionMode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InteractionMode createFromParcel(Parcel parcel) {
            return InteractionMode.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InteractionMode[] newArray(int i) {
            return new InteractionMode[i];
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
