package com.byd.amazon.music.param;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum PlayMode implements Parcelable {
    SINGLE_REPEAT,
    LIST_LOOP,
    SHUFFLE;

    public static final Parcelable.Creator<PlayMode> CREATOR = new Parcelable.Creator<PlayMode>() { // from class: com.byd.amazon.music.param.PlayMode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayMode createFromParcel(Parcel parcel) {
            return PlayMode.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayMode[] newArray(int i) {
            return new PlayMode[i];
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
