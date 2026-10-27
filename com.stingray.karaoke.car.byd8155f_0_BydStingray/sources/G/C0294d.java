package G;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: G.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0294d implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new C0295e(parcel.readInt());
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new C0295e[i7];
    }
}
