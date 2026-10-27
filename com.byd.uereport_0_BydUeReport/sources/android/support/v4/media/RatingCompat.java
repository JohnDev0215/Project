package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f166e;

    public static class a implements Parcelable.Creator<RatingCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RatingCompat[] newArray(int i8) {
            return new RatingCompat[i8];
        }
    }

    public RatingCompat(int i8, float f8) {
        this.f165d = i8;
        this.f166e = f8;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.f165d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rating:style=");
        sb.append(this.f165d);
        sb.append(" rating=");
        float f8 = this.f166e;
        sb.append(f8 < 0.0f ? "unrated" : String.valueOf(f8));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f165d);
        parcel.writeFloat(this.f166e);
    }
}
