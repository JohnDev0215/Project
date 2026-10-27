package p;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class o00 extends x0 {
    public static final Parcelable.Creator<o00> CREATOR = new ya2(2);
    public SparseArray k;

    public o00(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int i = parcel.readInt();
        int[] iArr = new int[i];
        parcel.readIntArray(iArr);
        Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
        this.k = new SparseArray(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.k.append(iArr[i2], parcelableArray[i2]);
        }
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        SparseArray sparseArray = this.k;
        int size = sparseArray != null ? sparseArray.size() : 0;
        parcel.writeInt(size);
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.k.keyAt(i2);
            parcelableArr[i2] = (Parcelable) this.k.valueAt(i2);
        }
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i);
    }
}
