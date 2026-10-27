package p;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class ln extends x0 {
    public static final Parcelable.Creator<ln> CREATOR = new ya2(6);
    public final int k;
    public final int l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    public ln(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt() == 1;
        this.n = parcel.readInt() == 1;
        this.o = parcel.readInt() == 1;
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m ? 1 : 0);
        parcel.writeInt(this.n ? 1 : 0);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public ln(AbsSavedState absSavedState, BottomSheetBehavior bottomSheetBehavior) {
        super(absSavedState);
        this.k = bottomSheetBehavior.G;
        this.l = bottomSheetBehavior.d;
        this.m = bottomSheetBehavior.b;
        this.n = bottomSheetBehavior.D;
        this.o = bottomSheetBehavior.E;
    }
}
