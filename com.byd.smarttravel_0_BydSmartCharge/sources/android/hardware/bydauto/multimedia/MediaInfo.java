package android.hardware.bydauto.multimedia;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MediaInfo implements Parcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = null;
    public String albumName;
    public String artistName;
    public int duration;
    public String fileName;

    public MediaInfo() {
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public MediaInfo(Parcel parcel) {
    }
}
