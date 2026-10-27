package N2;

import H7.H;
import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.J;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public class b implements J {
    public static final Parcelable.Creator<b> CREATOR = new g(5);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f4571m;

    public b(String str, String str2) {
        this.l = H.T(str);
        this.f4571m = str2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // e2.J
    public final void e(e2.H h10) {
        String str;
        String str2 = this.l;
        str2.getClass();
        str = this.f4571m;
        switch (str2) {
            case "ALBUM":
                h10.c = str;
                break;
            case "TITLE":
                h10.f13892a = str;
                break;
            case "DESCRIPTION":
                h10.f13896g = str;
                break;
            case "ALBUMARTIST":
                h10.d = str;
                break;
            case "ARTIST":
                h10.f13893b = str;
                break;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.l.equals(bVar.l) && this.f4571m.equals(bVar.f4571m);
    }

    public final int hashCode() {
        return this.f4571m.hashCode() + M5.b.c(this.l, 527, 31);
    }

    public final String toString() {
        return "VC: " + this.l + "=" + this.f4571m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeString(this.f4571m);
    }

    public b(Parcel parcel) {
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.l = string;
        this.f4571m = parcel.readString();
    }
}
