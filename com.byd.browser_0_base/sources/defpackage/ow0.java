package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.core.content.UnusedAppRestrictionsBackportService;

/* JADX INFO: compiled from: IUnusedAppRestrictionsBackportService.java */
/* JADX INFO: loaded from: classes.dex */
public interface ow0 extends IInterface {
    public static final String c = "androidx$core$app$unusedapprestrictions$IUnusedAppRestrictionsBackportService".replace('$', '.');

    /* JADX INFO: compiled from: IUnusedAppRestrictionsBackportService.java */
    public static abstract class a extends Binder implements ow0 {
        public a() {
            attachInterface(this, ow0.c);
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            nw0 mw0Var;
            String str = ow0.c;
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
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                mw0Var = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface(nw0.b);
                mw0Var = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof nw0)) ? new mw0(strongBinder) : (nw0) iInterfaceQueryLocalInterface;
            }
            UnusedAppRestrictionsBackportService.a aVar = (UnusedAppRestrictionsBackportService.a) this;
            if (mw0Var != null) {
                UnusedAppRestrictionsBackportService.this.a();
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
