package com.spotify.auto.parcel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;
import p.hf2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@Parcelize
public final class PlaybackSpeedActionParcelable implements Parcelable {
    public static final Parcelable.Creator<PlaybackSpeedActionParcelable> CREATOR = new Creator();
    private final String action;
    private final int iconRes;
    private final String name;

    public static final class Creator implements Parcelable.Creator<PlaybackSpeedActionParcelable> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PlaybackSpeedActionParcelable createFromParcel(Parcel parcel) {
            v03.j(parcel, "parcel");
            return new PlaybackSpeedActionParcelable(parcel.readString(), parcel.readString(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PlaybackSpeedActionParcelable[] newArray(int i) {
            return new PlaybackSpeedActionParcelable[i];
        }
    }

    public PlaybackSpeedActionParcelable(String str, String str2, int i) {
        v03.j(str, "name");
        v03.j(str2, "action");
        this.name = str;
        this.action = str2;
        this.iconRes = i;
    }

    public static /* synthetic */ PlaybackSpeedActionParcelable copy$default(PlaybackSpeedActionParcelable playbackSpeedActionParcelable, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = playbackSpeedActionParcelable.name;
        }
        if ((i2 & 2) != 0) {
            str2 = playbackSpeedActionParcelable.action;
        }
        if ((i2 & 4) != 0) {
            i = playbackSpeedActionParcelable.iconRes;
        }
        return playbackSpeedActionParcelable.copy(str, str2, i);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.action;
    }

    public final int component3() {
        return this.iconRes;
    }

    public final PlaybackSpeedActionParcelable copy(String str, String str2, int i) {
        v03.j(str, "name");
        v03.j(str2, "action");
        return new PlaybackSpeedActionParcelable(str, str2, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaybackSpeedActionParcelable)) {
            return false;
        }
        PlaybackSpeedActionParcelable playbackSpeedActionParcelable = (PlaybackSpeedActionParcelable) obj;
        return v03.b(this.name, playbackSpeedActionParcelable.name) && v03.b(this.action, playbackSpeedActionParcelable.action) && this.iconRes == playbackSpeedActionParcelable.iconRes;
    }

    public final String getAction() {
        return this.action;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return Integer.hashCode(this.iconRes) + hf2.b(this.action, this.name.hashCode() * 31, 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PlaybackSpeedActionParcelable(name=");
        sb.append(this.name);
        sb.append(", action=");
        sb.append(this.action);
        sb.append(", iconRes=");
        return u4.k(sb, this.iconRes, ')');
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.name);
        parcel.writeString(this.action);
        parcel.writeInt(this.iconRes);
    }
}
