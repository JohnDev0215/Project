package K1;

import P4.d;
import a5.v;
import android.os.Parcel;
import android.os.Parcelable;
import r.z0;
import s3.C2374M;
import t1.C2430f;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Parcelable.ClassLoaderCreator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3506a;

    public /* synthetic */ b(int i7) {
        this.f3506a = i7;
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.f3506a) {
            case 0:
                if (parcel.readParcelable(classLoader) == null) {
                    return c.f3507m;
                }
                throw new IllegalStateException("superState must be null");
            case 1:
                return new d(parcel, classLoader);
            case 2:
                return new Q4.b(parcel, classLoader);
            case 3:
                return new U4.a(parcel, classLoader);
            case 4:
                return new v(parcel, classLoader);
            case 5:
                return new z0(parcel, classLoader);
            case 6:
                return new C2374M(parcel, classLoader);
            default:
                return new C2430f(parcel, classLoader);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f3506a) {
            case 0:
                return new c[i7];
            case 1:
                return new d[i7];
            case 2:
                return new Q4.b[i7];
            case 3:
                return new U4.a[i7];
            case 4:
                return new v[i7];
            case 5:
                return new z0[i7];
            case 6:
                return new C2374M[i7];
            default:
                return new C2430f[i7];
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f3506a) {
            case 0:
                if (parcel.readParcelable(null) == null) {
                    return c.f3507m;
                }
                throw new IllegalStateException("superState must be null");
            case 1:
                return new d(parcel, (ClassLoader) null);
            case 2:
                return new Q4.b(parcel, null);
            case 3:
                return new U4.a(parcel, null);
            case 4:
                return new v(parcel, null);
            case 5:
                return new z0(parcel, null);
            case 6:
                return new C2374M(parcel, null);
            default:
                return new C2430f(parcel, null);
        }
    }
}
