package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class za2 extends x0 {
    public static final Parcelable.Creator<za2> CREATOR = new ya2(0);
    public boolean k;

    public za2(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.k = ((Boolean) parcel.readValue(null)).booleanValue();
    }

    public final String toString() {
        return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.k + "}";
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeValue(Boolean.valueOf(this.k));
    }
}
