package l3;

import android.os.Parcel;
import android.os.Parcelable;
import x3.InterfaceC2676d;

/* JADX INFO: loaded from: classes.dex */
public final class O implements Parcelable {
    public static final Parcelable.Creator<O> CREATOR = new android.support.v4.media.a(19);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f18534m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public InterfaceC1750f f18535n;
    public final Object l = new Object();
    public InterfaceC2676d o = null;

    public O(Object obj, InterfaceC1750f interfaceC1750f) {
        this.f18534m = obj;
        this.f18535n = interfaceC1750f;
    }

    public final InterfaceC1750f a() {
        InterfaceC1750f interfaceC1750f;
        synchronized (this.l) {
            interfaceC1750f = this.f18535n;
        }
        return interfaceC1750f;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof O)) {
            return false;
        }
        O o = (O) obj;
        Object obj2 = this.f18534m;
        if (obj2 == null) {
            return o.f18534m == null;
        }
        Object obj3 = o.f18534m;
        if (obj3 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public final int hashCode() {
        Object obj = this.f18534m;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable((Parcelable) this.f18534m, i7);
    }
}
