package G;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: G.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0295e implements Parcelable {
    public static final Parcelable.Creator<C0295e> CREATOR = new C0294d();
    public final int l;

    public C0295e(int i7) {
        this.l = i7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0295e) && this.l == ((C0295e) obj).l;
    }

    public final int hashCode() {
        return Integer.hashCode(this.l);
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("DefaultLazyKey(index="), this.l, ')');
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
    }
}
