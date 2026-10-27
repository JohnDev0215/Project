package p;

import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Parcelable {
    public final boolean c;
    public final boolean e;
    public final boolean k;

    public a(boolean z, boolean z2, boolean z3) {
        this.c = z;
        this.e = z2;
        this.k = z3;
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.c == aVar.c && this.e == aVar.e && this.k == aVar.k;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final int hashCode() {
        Boolean boolValueOf = Boolean.valueOf(this.c);
        boolean z = this.k;
        return Arrays.hashCode(new Object[]{boolValueOf, Boolean.valueOf(z), Boolean.valueOf(z)});
    }

    public final String toString() {
        return "PlayerOptions{shufflingContext=" + this.c + ", repeatingContext=" + this.e + ", repeatingTrack=" + this.k + "}";
    }
}
