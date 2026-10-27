package t1;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* JADX INFO: renamed from: t1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2430f extends K1.c {
    public static final Parcelable.Creator<C2430f> CREATOR = new K1.b(7);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SparseArray f22441n;

    public C2430f(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int i7 = parcel.readInt();
        int[] iArr = new int[i7];
        parcel.readIntArray(iArr);
        Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
        this.f22441n = new SparseArray(i7);
        for (int i10 = 0; i10 < i7; i10++) {
            this.f22441n.append(iArr[i10], parcelableArray[i10]);
        }
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        SparseArray sparseArray = this.f22441n;
        int size = sparseArray != null ? sparseArray.size() : 0;
        parcel.writeInt(size);
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr[i10] = this.f22441n.keyAt(i10);
            parcelableArr[i10] = (Parcelable) this.f22441n.valueAt(i10);
        }
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i7);
    }
}
