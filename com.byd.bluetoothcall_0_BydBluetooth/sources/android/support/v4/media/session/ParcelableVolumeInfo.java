package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f141d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f142e;
    public int f;

    static class a implements Parcelable.Creator<ParcelableVolumeInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo[] newArray(int i) {
            return new ParcelableVolumeInfo[i];
        }
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f139b = parcel.readInt();
        this.f141d = parcel.readInt();
        this.f142e = parcel.readInt();
        this.f = parcel.readInt();
        this.f140c = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f139b);
        parcel.writeInt(this.f141d);
        parcel.writeInt(this.f142e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.f140c);
    }
}
