package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class mi1 implements Parcelable {

    @Deprecated
    public static final Parcelable.Creator<mi1> CREATOR = new bg1(12);
    public final String c;
    public final Map e;

    public mi1(String str, Map map) {
        this.c = str;
        this.e = map;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof mi1) {
            mi1 mi1Var = (mi1) obj;
            if (v03.b(this.c, mi1Var.c) && v03.b(this.e, mi1Var.e)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode() + (this.c.hashCode() * 31);
    }

    public final String toString() {
        return "Key(key=" + this.c + ", extras=" + this.e + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        Map map = this.e;
        parcel.writeInt(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            parcel.writeString(str);
            parcel.writeString(str2);
        }
    }
}
