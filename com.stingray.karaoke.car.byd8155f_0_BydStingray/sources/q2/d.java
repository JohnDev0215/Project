package Q2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.J;

/* JADX INFO: loaded from: classes.dex */
public final class d implements J {
    public static final Parcelable.Creator<d> CREATOR = new g(22);
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6122m;

    public d(float f10, int i7) {
        this.l = f10;
        this.f6122m = i7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        return this.l == dVar.l && this.f6122m == dVar.f6122m;
    }

    public final int hashCode() {
        return ((Float.valueOf(this.l).hashCode() + 527) * 31) + this.f6122m;
    }

    public final String toString() {
        return "smta: captureFrameRate=" + this.l + ", svcTemporalLayerCount=" + this.f6122m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeFloat(this.l);
        parcel.writeInt(this.f6122m);
    }

    public d(Parcel parcel) {
        this.l = parcel.readFloat();
        this.f6122m = parcel.readInt();
    }
}
