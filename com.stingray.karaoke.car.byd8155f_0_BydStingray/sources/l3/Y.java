package l3;

import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class Y implements Parcelable {
    public static final Parcelable.Creator<Y> CREATOR = new android.support.v4.media.a(23);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f18562m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f18563n;

    public Y(float f10, int i7) {
        this.l = i7;
        this.f18562m = f10;
    }

    public static Y a(Object obj) {
        Y y10;
        Y y11 = null;
        if (obj != null) {
            Rating rating = (Rating) obj;
            int ratingStyle = rating.getRatingStyle();
            if (!rating.isRated()) {
                switch (ratingStyle) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        y11 = new Y(-1.0f, ratingStyle);
                        break;
                }
            } else {
                switch (ratingStyle) {
                    case 1:
                        y10 = new Y(rating.hasHeart() ? 1.0f : 0.0f, 1);
                        y11 = y10;
                        break;
                    case 2:
                        y10 = new Y(rating.isThumbUp() ? 1.0f : 0.0f, 2);
                        y11 = y10;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        y11 = h(rating.getStarRating(), ratingStyle);
                        break;
                    case 6:
                        y11 = g(rating.getPercentRating());
                        break;
                    default:
                        return null;
                }
            }
            y11.getClass();
            y11.f18563n = obj;
        }
        return y11;
    }

    public static Y g(float f10) {
        if (f10 >= 0.0f && f10 <= 100.0f) {
            return new Y(f10, 6);
        }
        h6.n.j("Rating", "Invalid percentage-based rating value");
        return null;
    }

    public static Y h(float f10, int i7) {
        float f11;
        if (i7 == 3) {
            f11 = 3.0f;
        } else if (i7 == 4) {
            f11 = 4.0f;
        } else {
            if (i7 != 5) {
                h6.n.j("Rating", "Invalid rating style (" + i7 + ") for a star rating");
                return null;
            }
            f11 = 5.0f;
        }
        if (f10 >= 0.0f && f10 <= f11) {
            return new Y(f10, i7);
        }
        h6.n.j("Rating", "Trying to set out of range star-based rating");
        return null;
    }

    public final float d() {
        int i7 = this.l;
        if ((i7 == 3 || i7 == 4 || i7 == 5) && f()) {
            return this.f18562m;
        }
        return -1.0f;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.l;
    }

    public final boolean f() {
        return this.f18562m >= 0.0f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.l);
        sb.append(" rating=");
        float f10 = this.f18562m;
        sb.append(f10 < 0.0f ? "unrated" : String.valueOf(f10));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeFloat(this.f18562m);
    }
}
