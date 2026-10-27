package p;

import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Parcelable {
    public final Boolean c;
    public final Boolean e;
    public final Boolean k;

    public b(Boolean bool, Boolean bool2, Boolean bool3) {
        this.c = bool;
        this.e = bool2;
        this.k = bool3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        Boolean bool = this.c;
        if (bool != null ? bool.equals(bVar.c) : bVar.c == null) {
            Boolean bool2 = this.e;
            if (bool2 != null ? bool2.equals(bVar.e) : bVar.e == null) {
                Boolean bool3 = this.k;
                if (bool3 == null) {
                    if (bVar.k == null) {
                        return true;
                    }
                } else if (bool3.equals(bVar.k)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Boolean bool = this.c;
        int iHashCode = ((bool == null ? 0 : bool.hashCode()) ^ 1000003) * 1000003;
        Boolean bool2 = this.e;
        int iHashCode2 = (iHashCode ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.k;
        return (bool3 != null ? bool3.hashCode() : 0) ^ iHashCode2;
    }

    public final String toString() {
        return "PlayerOptionsOverrides{shufflingContext=" + this.c + ", repeatingContext=" + this.e + ", repeatingTrack=" + this.k + "}";
    }
}
