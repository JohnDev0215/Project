package android.support.v4.media;

import android.annotation.SuppressLint;
import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import p.bg1;
import p.l02;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new bg1(3);
    public final int c;
    public final float e;
    public Object k;

    public RatingCompat(float f, int i) {
        this.c = i;
        this.e = f;
    }

    public static RatingCompat a(Object obj) {
        RatingCompat ratingCompat;
        float f;
        RatingCompat ratingCompat2 = null;
        if (obj != null) {
            Rating rating = (Rating) obj;
            int iB = l02.b(rating);
            if (!l02.e(rating)) {
                switch (iB) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        ratingCompat2 = new RatingCompat(-1.0f, iB);
                        break;
                }
            } else {
                switch (iB) {
                    case 1:
                        ratingCompat = new RatingCompat(l02.d(rating) ? 1.0f : 0.0f, 1);
                        ratingCompat2 = ratingCompat;
                        break;
                    case 2:
                        ratingCompat = new RatingCompat(l02.f(rating) ? 1.0f : 0.0f, 2);
                        ratingCompat2 = ratingCompat;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        float fC = l02.c(rating);
                        if (iB == 3) {
                            f = 3.0f;
                        } else if (iB == 4) {
                            f = 4.0f;
                        } else if (iB != 5) {
                            Log.e("Rating", "Invalid rating style (" + iB + ") for a star rating");
                        } else {
                            f = 5.0f;
                        }
                        if (fC >= 0.0f && fC <= f) {
                            ratingCompat2 = new RatingCompat(fC, iB);
                        } else {
                            Log.e("Rating", "Trying to set out of range star-based rating");
                        }
                        break;
                    case 6:
                        float fA = l02.a(rating);
                        if (fA >= 0.0f && fA <= 100.0f) {
                            ratingCompat2 = new RatingCompat(fA, 6);
                        } else {
                            Log.e("Rating", "Invalid percentage-based rating value");
                        }
                        break;
                    default:
                        return null;
                }
            }
            ratingCompat2.k = obj;
        }
        return ratingCompat2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.c);
        sb.append(" rating=");
        float f = this.e;
        sb.append(f < 0.0f ? "unrated" : String.valueOf(f));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeFloat(this.e);
    }
}
