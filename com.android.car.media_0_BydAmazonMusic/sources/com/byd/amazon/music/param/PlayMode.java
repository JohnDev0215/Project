package com.byd.amazon.music.param;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayMode implements Parcelable {
    /* JADX INFO: Fake field, exist only in values array */
    SINGLE_REPEAT,
    /* JADX INFO: Fake field, exist only in values array */
    LIST_LOOP,
    /* JADX INFO: Fake field, exist only in values array */
    SHUFFLE;

    public static final Parcelable.Creator<PlayMode> CREATOR = new Parcelable.Creator<PlayMode>() { // from class: com.byd.amazon.music.param.PlayMode.a
        @Override // android.os.Parcelable.Creator
        public final PlayMode createFromParcel(Parcel parcel) {
            return PlayMode.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final PlayMode[] newArray(int i) {
            return new PlayMode[i];
        }
    };

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
