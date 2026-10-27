package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f194d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f195e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f196f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f197g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f198h;

    public static class a implements Parcelable.Creator<ParcelableVolumeInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo[] newArray(int i8) {
            return new ParcelableVolumeInfo[i8];
        }
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f194d = parcel.readInt();
        this.f196f = parcel.readInt();
        this.f197g = parcel.readInt();
        this.f198h = parcel.readInt();
        this.f195e = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f194d);
        parcel.writeInt(this.f196f);
        parcel.writeInt(this.f197g);
        parcel.writeInt(this.f198h);
        parcel.writeInt(this.f195e);
    }
}
