package i2;

import android.os.Parcel;
import android.os.Parcelable;
import e2.J;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class e implements J {
    public static final Parcelable.Creator<e> CREATOR = new android.support.v4.media.a(10);
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f15674m;

    public e(float f10, float f11) {
        AbstractC1346c.b("Invalid latitude or longitude", f10 >= -90.0f && f10 <= 90.0f && f11 >= -180.0f && f11 <= 180.0f);
        this.l = f10;
        this.f15674m = f11;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e.class != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return this.l == eVar.l && this.f15674m == eVar.f15674m;
    }

    public final int hashCode() {
        return Float.valueOf(this.f15674m).hashCode() + ((Float.valueOf(this.l).hashCode() + 527) * 31);
    }

    public final String toString() {
        return "xyz: latitude=" + this.l + ", longitude=" + this.f15674m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeFloat(this.l);
        parcel.writeFloat(this.f15674m);
    }

    public e(Parcel parcel) {
        this.l = parcel.readFloat();
        this.f15674m = parcel.readFloat();
    }
}
