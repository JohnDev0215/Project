package q4;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.smarttravel.PlanBean;
import q4.b;

/* JADX INFO: compiled from: IChargeAidlInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {

    /* JADX INFO: compiled from: IChargeAidlInterface.java */
    public static abstract class a extends Binder implements c {
        public a() {
            attachInterface(this, "com.byd.smarttravel.IChargeAidlInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1) {
                parcel.enforceInterface("com.byd.smarttravel.IChargeAidlInterface");
                PlanBean planBeanM0 = m0();
                parcel2.writeNoException();
                if (planBeanM0 != null) {
                    parcel2.writeInt(1);
                    planBeanM0.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i8 == 2) {
                parcel.enforceInterface("com.byd.smarttravel.IChargeAidlInterface");
                PlanBean planBeanCreateFromParcel = parcel.readInt() != 0 ? PlanBean.CREATOR.createFromParcel(parcel) : null;
                x0(planBeanCreateFromParcel);
                parcel2.writeNoException();
                if (planBeanCreateFromParcel != null) {
                    parcel2.writeInt(1);
                    planBeanCreateFromParcel.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i8 == 3) {
                parcel.enforceInterface("com.byd.smarttravel.IChargeAidlInterface");
                Y(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i8 == 4) {
                parcel.enforceInterface("com.byd.smarttravel.IChargeAidlInterface");
                m(b.a.A0(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i8 != 5) {
                if (i8 != 1598968902) {
                    return super.onTransact(i8, parcel, parcel2, i9);
                }
                parcel2.writeString("com.byd.smarttravel.IChargeAidlInterface");
                return true;
            }
            parcel.enforceInterface("com.byd.smarttravel.IChargeAidlInterface");
            C(b.a.A0(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void C(b bVar) throws RemoteException;

    void Y(int i8) throws RemoteException;

    void m(b bVar) throws RemoteException;

    PlanBean m0() throws RemoteException;

    void x0(PlanBean planBean) throws RemoteException;
}
