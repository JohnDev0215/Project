package Q4;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class b extends K1.c {
    public static final Parcelable.Creator<b> CREATOR = new K1.b(2);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6222n;

    public b(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        if (classLoader == null) {
            b.class.getClassLoader();
        }
        this.f6222n = parcel.readInt() == 1;
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeInt(this.f6222n ? 1 : 0);
    }
}
