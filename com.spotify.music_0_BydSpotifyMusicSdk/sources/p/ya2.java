package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class ya2 implements Parcelable.ClassLoaderCreator {
    public final /* synthetic */ int a;

    public /* synthetic */ ya2(int i) {
        this.a = i;
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.a) {
            case 0:
                return new za2(parcel, classLoader);
            case 1:
                return new gm2(parcel, classLoader);
            case 2:
                return new o00(parcel, classLoader);
            case 3:
                if (parcel.readParcelable(classLoader) == null) {
                    return x0.e;
                }
                throw new IllegalStateException("superState must be null");
            case 4:
                return new h22(parcel, classLoader);
            case 5:
                return new q7(parcel, classLoader);
            case 6:
                return new ln(parcel, classLoader);
            case 7:
                return new af1(parcel, classLoader);
            case 8:
                return new ds(parcel, classLoader);
            default:
                return new vj2(parcel, classLoader);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new za2[i];
            case 1:
                return new gm2[i];
            case 2:
                return new o00[i];
            case 3:
                return new x0[i];
            case 4:
                return new h22[i];
            case 5:
                return new q7[i];
            case 6:
                return new ln[i];
            case 7:
                return new af1[i];
            case 8:
                return new ds[i];
            default:
                return new vj2[i];
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new za2(parcel, null);
            case 1:
                return new gm2(parcel, null);
            case 2:
                return new o00(parcel, null);
            case 3:
                if (parcel.readParcelable(null) == null) {
                    return x0.e;
                }
                throw new IllegalStateException("superState must be null");
            case 4:
                return new h22(parcel, null);
            case 5:
                return new q7(parcel, null);
            case 6:
                return new ln(parcel, (ClassLoader) null);
            case 7:
                return new af1(parcel, null);
            case 8:
                return new ds(parcel, null);
            default:
                return new vj2(parcel, null);
        }
    }
}
