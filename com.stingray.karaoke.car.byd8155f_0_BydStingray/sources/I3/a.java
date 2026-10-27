package I3;

import A7.m;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable {

    @Deprecated
    public static final Parcelable.Creator<a> CREATOR = new I1.g(1);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Map f2931m;

    public a(String str, Map map) {
        this.l = str;
        this.f2931m = map;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (m.a(this.l, aVar.l) && m.a(this.f2931m, aVar.f2931m)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f2931m.hashCode() + (this.l.hashCode() * 31);
    }

    public final String toString() {
        return "Key(key=" + this.l + ", extras=" + this.f2931m + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        Map map = this.f2931m;
        parcel.writeInt(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            parcel.writeString(str);
            parcel.writeString(str2);
        }
    }
}
