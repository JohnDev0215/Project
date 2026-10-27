package androidx.test.core.os;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class Parcelables {
    private Parcelables() {
    }

    public static <T extends Parcelable> T forceParcel(T t, Parcelable.Creator<T> creator) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            t.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } finally {
            parcelObtain.recycle();
        }
    }
}
