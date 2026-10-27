package P4;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class d extends K1.c {
    public static final Parcelable.Creator<d> CREATOR = new K1.b(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f5335n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f5336p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f5337q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f5338r;

    public d(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f5335n = parcel.readInt();
        this.o = parcel.readInt();
        this.f5336p = parcel.readInt() == 1;
        this.f5337q = parcel.readInt() == 1;
        this.f5338r = parcel.readInt() == 1;
    }

    @Override // K1.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeInt(this.f5335n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.f5336p ? 1 : 0);
        parcel.writeInt(this.f5337q ? 1 : 0);
        parcel.writeInt(this.f5338r ? 1 : 0);
    }

    public d(AbsSavedState absSavedState, BottomSheetBehavior bottomSheetBehavior) {
        super(absSavedState);
        this.f5335n = bottomSheetBehavior.f12755F;
        this.o = bottomSheetBehavior.d;
        this.f5336p = bottomSheetBehavior.f12774b;
        this.f5337q = bottomSheetBehavior.f12752C;
        this.f5338r = bottomSheetBehavior.f12753D;
    }
}
