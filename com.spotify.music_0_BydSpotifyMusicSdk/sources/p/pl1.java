package p;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class pl1 extends Binder implements v21 {
    public static final /* synthetic */ int c = 0;
    public final /* synthetic */ ql1 b;

    public pl1(ql1 ql1Var) {
        this.b = ql1Var;
        attachInterface(this, "androidx.room.IMultiInstanceInvalidationCallback");
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // p.v21
    public final void e(String[] strArr) {
        v03.j(strArr, "tables");
        ql1 ql1Var = this.b;
        ql1Var.c.execute(new ua(ql1Var, 6, strArr));
    }

    @Override // android.os.Binder
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationCallback");
        }
        if (i == 1598968902) {
            parcel2.writeString("androidx.room.IMultiInstanceInvalidationCallback");
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        e(parcel.createStringArray());
        return true;
    }
}
