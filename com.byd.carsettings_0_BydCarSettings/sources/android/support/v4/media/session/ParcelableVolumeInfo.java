package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f152d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f153e;

    public static class a implements Parcelable.Creator<ParcelableVolumeInfo> {
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
        this.f149a = parcel.readInt();
        this.f151c = parcel.readInt();
        this.f152d = parcel.readInt();
        this.f153e = parcel.readInt();
        this.f150b = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f149a);
        parcel.writeInt(this.f151c);
        parcel.writeInt(this.f152d);
        parcel.writeInt(this.f153e);
        parcel.writeInt(this.f150b);
    }
}
