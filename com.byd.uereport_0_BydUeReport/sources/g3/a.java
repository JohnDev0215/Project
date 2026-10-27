package g3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.carsettings.db.model.MaintainMileBean;
import com.byd.carsettings.db.model.MaintainTimeBean;

/* JADX INFO: compiled from: IDialogAidlInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: g3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IDialogAidlInterface.java */
    public static abstract class AbstractBinderC0046a extends Binder implements a {
        public AbstractBinderC0046a() {
            attachInterface(this, "com.byd.carsettings.dialog.IDialogAidlInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1598968902) {
                parcel2.writeString("com.byd.carsettings.dialog.IDialogAidlInterface");
                return true;
            }
            switch (i8) {
                case 1:
                    parcel.enforceInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
                    o0(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
                    t(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
                    MaintainMileBean maintainMileBeanX = X();
                    parcel2.writeNoException();
                    if (maintainMileBeanX != null) {
                        parcel2.writeInt(1);
                        maintainMileBeanX.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
                    MaintainTimeBean maintainTimeBeanV = V();
                    parcel2.writeNoException();
                    if (maintainTimeBeanV != null) {
                        parcel2.writeInt(1);
                        maintainTimeBeanV.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
                    MaintainMileBean maintainMileBeanCreateFromParcel = parcel.readInt() != 0 ? MaintainMileBean.CREATOR.createFromParcel(parcel) : null;
                    M(maintainMileBeanCreateFromParcel);
                    parcel2.writeNoException();
                    if (maintainMileBeanCreateFromParcel != null) {
                        parcel2.writeInt(1);
                        maintainMileBeanCreateFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
                    MaintainTimeBean maintainTimeBeanCreateFromParcel = parcel.readInt() != 0 ? MaintainTimeBean.CREATOR.createFromParcel(parcel) : null;
                    D(maintainTimeBeanCreateFromParcel);
                    parcel2.writeNoException();
                    if (maintainTimeBeanCreateFromParcel != null) {
                        parcel2.writeInt(1);
                        maintainTimeBeanCreateFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i8, parcel, parcel2, i9);
            }
        }
    }

    void D(MaintainTimeBean maintainTimeBean) throws RemoteException;

    void M(MaintainMileBean maintainMileBean) throws RemoteException;

    MaintainTimeBean V() throws RemoteException;

    MaintainMileBean X() throws RemoteException;

    void o0(int i8, int i9) throws RemoteException;

    void t(int i8) throws RemoteException;
}
