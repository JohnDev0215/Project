package T;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: T.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0736d0 implements Parcelable.ClassLoaderCreator {
    public static C0738e0 a(Parcel parcel, ClassLoader classLoader) {
        S s6;
        if (classLoader == null) {
            classLoader = C0736d0.class.getClassLoader();
        }
        Object value = parcel.readValue(classLoader);
        int i7 = parcel.readInt();
        if (i7 == 0) {
            s6 = S.f8222n;
        } else if (i7 == 1) {
            s6 = S.f8224q;
        } else {
            if (i7 != 2) {
                throw new IllegalStateException(A7.k.g("Unsupported MutableState policy ", i7, " was restored"));
            }
            s6 = S.o;
        }
        return new C0738e0(value, s6);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return a(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new C0738e0[i7];
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return a(parcel, null);
    }
}
