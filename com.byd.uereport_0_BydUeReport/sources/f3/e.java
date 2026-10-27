package f3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.carsettings.db.model.EnergyBean;
import java.util.List;

/* JADX INFO: compiled from: IEnergyAidlInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends IInterface {

    /* JADX INFO: compiled from: IEnergyAidlInterface.java */
    public static abstract class a extends Binder implements e {
        public a() {
            attachInterface(this, "com.byd.carsettings.db.IEnergyAidlInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1) {
                parcel.enforceInterface("com.byd.carsettings.db.IEnergyAidlInterface");
                List<EnergyBean> listY = y();
                parcel2.writeNoException();
                parcel2.writeTypedList(listY);
                return true;
            }
            if (i8 == 2) {
                parcel.enforceInterface("com.byd.carsettings.db.IEnergyAidlInterface");
                List<EnergyBean> listL0 = l0();
                parcel2.writeNoException();
                parcel2.writeTypedList(listL0);
                return true;
            }
            if (i8 != 3) {
                if (i8 != 1598968902) {
                    return super.onTransact(i8, parcel, parcel2, i9);
                }
                parcel2.writeString("com.byd.carsettings.db.IEnergyAidlInterface");
                return true;
            }
            parcel.enforceInterface("com.byd.carsettings.db.IEnergyAidlInterface");
            n();
            parcel2.writeNoException();
            return true;
        }
    }

    List<EnergyBean> l0() throws RemoteException;

    void n() throws RemoteException;

    List<EnergyBean> y() throws RemoteException;
}
