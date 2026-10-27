package com.spotify.auto.parcel;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackSpeedActionParcelable implements Parcelable {
    public static final Parcelable.Creator<PlaybackSpeedActionParcelable> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5009e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5010g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5011h;

    class a implements Parcelable.Creator<PlaybackSpeedActionParcelable> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlaybackSpeedActionParcelable createFromParcel(Parcel parcel) {
            return new PlaybackSpeedActionParcelable(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlaybackSpeedActionParcelable[] newArray(int i7) {
            return new PlaybackSpeedActionParcelable[i7];
        }
    }

    public PlaybackSpeedActionParcelable() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5009e);
        parcel.writeString(this.f5010g);
        parcel.writeInt(this.f5011h);
    }

    protected PlaybackSpeedActionParcelable(Parcel parcel) {
        this.f5009e = parcel.readString();
        this.f5010g = parcel.readString();
        this.f5011h = parcel.readInt();
    }
}
