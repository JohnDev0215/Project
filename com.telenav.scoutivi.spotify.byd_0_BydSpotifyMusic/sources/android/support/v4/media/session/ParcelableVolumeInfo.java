package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f259e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f260g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f261h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f262i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f263j;

    class a implements Parcelable.Creator<ParcelableVolumeInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo[] newArray(int i7) {
            return new ParcelableVolumeInfo[i7];
        }
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f259e = parcel.readInt();
        this.f261h = parcel.readInt();
        this.f262i = parcel.readInt();
        this.f263j = parcel.readInt();
        this.f260g = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f259e);
        parcel.writeInt(this.f261h);
        parcel.writeInt(this.f262i);
        parcel.writeInt(this.f263j);
        parcel.writeInt(this.f260g);
    }
}
