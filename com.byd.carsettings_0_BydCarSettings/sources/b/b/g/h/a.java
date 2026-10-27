package b.b.g.h;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.carsettings.db.model.MaintainMileBean;
import com.byd.carsettings.db.model.MaintainTimeBean;

/* JADX INFO: compiled from: IDialogAidlInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: b.b.g.h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IDialogAidlInterface.java */
    public static abstract class AbstractBinderC0044a extends Binder implements a {

        /* JADX INFO: renamed from: b.b.g.h.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IDialogAidlInterface.java */
        public static class C0045a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f801a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f802b;

            public C0045a(IBinder iBinder) {
                this.f802b = iBinder;
            }

            @Override // b.b.g.h.a
            public void A(MaintainTimeBean maintainTimeBean) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.dialog.IDialogAidlInterface");
                    if (maintainTimeBean != null) {
                        parcelObtain.writeInt(1);
                        maintainTimeBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f802b.transact(6, parcelObtain, parcelObtain2, 0) && AbstractBinderC0044a.e0() != null) {
                        AbstractBinderC0044a.e0().A(maintainTimeBean);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        maintainTimeBean.d(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.g.h.a
            public void E(MaintainMileBean maintainMileBean) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.dialog.IDialogAidlInterface");
                    if (maintainMileBean != null) {
                        parcelObtain.writeInt(1);
                        maintainMileBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f802b.transact(5, parcelObtain, parcelObtain2, 0) && AbstractBinderC0044a.e0() != null) {
                        AbstractBinderC0044a.e0().E(maintainMileBean);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        maintainMileBean.d(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.g.h.a
            public MaintainTimeBean I() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.dialog.IDialogAidlInterface");
                    if (!this.f802b.transact(4, parcelObtain, parcelObtain2, 0) && AbstractBinderC0044a.e0() != null) {
                        return AbstractBinderC0044a.e0().I();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MaintainTimeBean.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.g.h.a
            public MaintainMileBean K() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.dialog.IDialogAidlInterface");
                    if (!this.f802b.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0044a.e0() != null) {
                        return AbstractBinderC0044a.e0().K();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MaintainMileBean.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.g.h.a
            public void U(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.dialog.IDialogAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.f802b.transact(1, parcelObtain, parcelObtain2, 0) || AbstractBinderC0044a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0044a.e0().U(i, i2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f802b;
            }

            @Override // b.b.g.h.a
            public void u(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.dialog.IDialogAidlInterface");
                    parcelObtain.writeInt(i);
                    if (this.f802b.transact(2, parcelObtain, parcelObtain2, 0) || AbstractBinderC0044a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0044a.e0().u(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.carsettings.dialog.IDialogAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0045a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a e0() {
            return C0045a.f801a;
        }
    }

    void A(MaintainTimeBean maintainTimeBean) throws RemoteException;

    void E(MaintainMileBean maintainMileBean) throws RemoteException;

    MaintainTimeBean I() throws RemoteException;

    MaintainMileBean K() throws RemoteException;

    void U(int i, int i2) throws RemoteException;

    void u(int i) throws RemoteException;
}
