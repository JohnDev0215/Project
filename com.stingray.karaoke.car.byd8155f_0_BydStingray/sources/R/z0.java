package r;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class z0 extends K1.c {
    public static final Parcelable.Creator<z0> CREATOR = new K1.b(5);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21574n;
    public boolean o;

    public z0(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f21574n = parcel.readInt();
        this.o = parcel.readInt() != 0;
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeInt(this.f21574n);
        parcel.writeInt(this.o ? 1 : 0);
    }
}
