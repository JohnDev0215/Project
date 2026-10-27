package s3;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: s3.M, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2374M extends K1.c {
    public static final Parcelable.Creator<C2374M> CREATOR = new K1.b(6);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Parcelable f22196n;

    public C2374M(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f22196n = parcel.readParcelable(classLoader == null ? AbstractC2367F.class.getClassLoader() : classLoader);
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeParcelable(this.f22196n, 0);
    }
}
