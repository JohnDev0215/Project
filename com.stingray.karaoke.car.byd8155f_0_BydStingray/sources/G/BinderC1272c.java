package g;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: renamed from: g.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC1272c extends Binder implements InterfaceC1271b {
    public static final /* synthetic */ int d = 0;
    public final /* synthetic */ C1273d c;

    public BinderC1272c(C1273d c1273d) {
        this.c = c1273d;
        attachInterface(this, InterfaceC1271b.f14658b);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // g.InterfaceC1271b
    public final void k1(int i7, Bundle bundle) {
        C1273d c1273d = this.c;
        c1273d.getClass();
        c1273d.a(i7, bundle);
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        String str = InterfaceC1271b.f14658b;
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface(str);
        }
        if (i7 == 1598968902) {
            parcel2.writeString(str);
            return true;
        }
        if (i7 != 1) {
            return super.onTransact(i7, parcel, parcel2, i10);
        }
        k1(parcel.readInt(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
        return true;
    }
}
