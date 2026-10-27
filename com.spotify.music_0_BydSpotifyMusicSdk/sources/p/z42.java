package p;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class z42 extends Binder implements z21 {
    public static final /* synthetic */ int c = 0;
    public final /* synthetic */ b52 b;

    public z42(b52 b52Var) {
        this.b = b52Var;
        attachInterface(this, z21.a);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // p.z21
    public final void e0(int i, Bundle bundle) {
        b52 b52Var = this.b;
        b52Var.getClass();
        b52Var.a(i, bundle);
    }

    @Override // android.os.Binder
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        String str = z21.a;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface(str);
        }
        if (i == 1598968902) {
            parcel2.writeString(str);
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        e0(parcel.readInt(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
        return true;
    }
}
