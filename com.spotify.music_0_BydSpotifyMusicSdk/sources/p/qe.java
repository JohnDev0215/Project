package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class qe extends re {
    public static final Parcelable.Creator<qe> CREATOR = new bg1(18);
    public final String c;
    public final byte[] e;

    public qe(String str, byte[] bArr) {
        v03.j(str, "username");
        v03.j(bArr, "storedCredentials");
        this.c = str;
        this.e = bArr;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof qe)) {
            return false;
        }
        qe qeVar = (qe) obj;
        return v03.b(this.c, qeVar.c) && v03.b(this.e, qeVar.e);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.e) + (this.c.hashCode() * 31);
    }

    public final String toString() {
        return "StoredCredentials(username=" + this.c + ", storedCredentials=" + Arrays.toString(this.e) + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.c);
        parcel.writeByteArray(this.e);
    }
}
