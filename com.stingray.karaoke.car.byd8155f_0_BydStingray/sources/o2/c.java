package O2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.H;
import e2.J;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c implements J {
    public static final Parcelable.Creator<c> CREATOR = new g(7);
    public final byte[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5067m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5068n;

    public c(String str, String str2, byte[] bArr) {
        this.l = bArr;
        this.f5067m = str;
        this.f5068n = str2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // e2.J
    public final void e(H h10) {
        String str = this.f5067m;
        if (str != null) {
            h10.f13892a = str;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.l, ((c) obj).l);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.l);
    }

    public final String toString() {
        return "ICY: title=\"" + this.f5067m + "\", url=\"" + this.f5068n + "\", rawMetadata.length=\"" + this.l.length + "\"";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeByteArray(this.l);
        parcel.writeString(this.f5067m);
        parcel.writeString(this.f5068n);
    }

    public c(Parcel parcel) {
        byte[] bArrCreateByteArray = parcel.createByteArray();
        bArrCreateByteArray.getClass();
        this.l = bArrCreateByteArray;
        this.f5067m = parcel.readString();
        this.f5068n = parcel.readString();
    }
}
