package com.byd.amazon.music.param;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayState implements Parcelable {
    STATE_NONE,
    STATE_STOPPED,
    STATE_PLAYING,
    STATE_PAUSED,
    STATE_FAST_FORWARDING,
    STATE_REWINDING,
    STATE_BUFFERING,
    STATE_ERROR,
    STATE_CONNECTING,
    STATE_SKIPPING_TO_PREVIOUS,
    STATE_SKIPPING_TO_NEXT,
    STATE_SKIPPING_TO_QUEUE_ITEM;

    public static final Parcelable.Creator<PlayState> CREATOR = new Parcelable.Creator<PlayState>() { // from class: com.byd.amazon.music.param.PlayState.a
        @Override // android.os.Parcelable.Creator
        public final PlayState createFromParcel(Parcel parcel) {
            return PlayState.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final PlayState[] newArray(int i) {
            return new PlayState[i];
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
