package c.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.smarttravel.PlanBean;

/* JADX INFO: compiled from: ICallbackInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ICallbackInterface.java */
    public static abstract class AbstractBinderC0044a extends Binder implements a {
        public AbstractBinderC0044a() {
            attachInterface(this, "com.byd.smarttravel.ICallbackInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.byd.smarttravel.ICallbackInterface");
                return true;
            }
            parcel.enforceInterface("com.byd.smarttravel.ICallbackInterface");
            int i3 = parcel.readInt();
            PlanBean planBeanCreateFromParcel = parcel.readInt() != 0 ? PlanBean.CREATOR.createFromParcel(parcel) : null;
            b(i3, planBeanCreateFromParcel);
            parcel2.writeNoException();
            if (planBeanCreateFromParcel != null) {
                parcel2.writeInt(1);
                planBeanCreateFromParcel.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    void b(int i, PlanBean planBean) throws RemoteException;
}
