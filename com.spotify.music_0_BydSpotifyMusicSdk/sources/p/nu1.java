package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class nu1 implements Parcelable {
    public static final Parcelable.Creator<nu1> CREATOR = new bg1(28);
    public final Set c;

    public nu1(Set set) {
        if (set == null || set.isEmpty()) {
            this.c = Collections.unmodifiableSet(Collections.emptySet());
        } else {
            this.c = Collections.unmodifiableSet(set);
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof nu1) && this.c.equals(((nu1) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ux2.D(parcel, this.c);
    }

    public nu1(Parcel parcel) {
        this.c = ux2.x(parcel);
    }
}
