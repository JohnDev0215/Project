package T;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8228a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f8228a) {
            case 0:
                return new C0730a0(parcel.readFloat());
            case 1:
                return new C0732b0(parcel.readInt());
            default:
                return new C0734c0(parcel.readLong());
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f8228a) {
            case 0:
                return new C0730a0[i7];
            case 1:
                return new C0732b0[i7];
            default:
                return new C0734c0[i7];
        }
    }
}
