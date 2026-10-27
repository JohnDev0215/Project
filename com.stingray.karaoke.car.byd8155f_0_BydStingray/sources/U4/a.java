package U4;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class a extends K1.c {
    public static final Parcelable.Creator<a> CREATOR = new K1.b(3);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9016n;

    public a(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f9016n = parcel.readInt() == 1;
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeInt(this.f9016n ? 1 : 0);
    }
}
