package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f160c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f161d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f162e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f163f;

    public static class a implements Parcelable.Creator<ParcelableVolumeInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo[] newArray(int i2) {
            return new ParcelableVolumeInfo[i2];
        }
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f159b = parcel.readInt();
        this.f161d = parcel.readInt();
        this.f162e = parcel.readInt();
        this.f163f = parcel.readInt();
        this.f160c = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f159b);
        parcel.writeInt(this.f161d);
        parcel.writeInt(this.f162e);
        parcel.writeInt(this.f163f);
        parcel.writeInt(this.f160c);
    }
}
